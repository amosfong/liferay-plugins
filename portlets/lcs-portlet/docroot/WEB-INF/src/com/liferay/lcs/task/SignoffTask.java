/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.lcs.task;

import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.HandshakeMessage;
import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.ResponseCommandMessage;
import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.lcs.util.HandshakeManager;
import com.liferay.lcs.util.KeyGenerator;
import com.liferay.lcs.util.comparator.MessagePriorityComparator;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 */
public class SignoffTask implements Runnable {

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			_handshakeManager.setPending(false);

			_log.error(e);
		}
	}

	public void setDeregister(boolean deregister) {
		_deregister = deregister;
	}

	public void setHandshakeReplyReads(int handshakeReplyReads) {
		_handshakeReplyReads = handshakeReplyReads;
	}

	public void setHeartbeatInterval(long heartbeatInterval) {
		_heartbeatInterval = heartbeatInterval;
	}

	public void setStopReplyReads(int stopReplyReads) {
		_stopReplyReads = stopReplyReads;
	}

	public void setStopWaitTime(long stopWaitTime) {
		_stopWaitTime = stopWaitTime;
	}

	protected void doRun() throws PortalException, SystemException {
		if (_log.isInfoEnabled()) {
			_log.info("Initiate sign off");
		}

		List<ScheduledFuture<?>> scheduledFutures =
			_handshakeManager.getScheduledFutures();

		for (ScheduledFuture<?> scheduledFuture : scheduledFutures) {
			while (!scheduledFuture.isCancelled()) {
				scheduledFuture.cancel(true);
			}
		}

		scheduledFutures.clear();

		String key = _keyGenerator.getKey();

		if (!_deregister) {
			HandshakeMessage handshakeMessage = new HandshakeMessage();

			handshakeMessage.put(
				Message.KEY_SIGN_OFF, String.valueOf(_heartbeatInterval));
			handshakeMessage.setKey(key);

			_lcsGatewayService.sendMessage(handshakeMessage);
		}

		int attempt = 0;
		List<Message> delayedMessages = new ArrayList<Message>();
		List<Message> receivedMessages = null;

		long waitTime = _stopWaitTime / _stopReplyReads;

		while (true) {
			if (attempt++ > _handshakeReplyReads) {
				_handshakeManager.setPending(false);

				if (_log.isInfoEnabled()) {
					_log.info("Terminated connection");
				}

				return;
			}

			receivedMessages = _lcsGatewayService.getMessages(key);

			if (receivedMessages.isEmpty()) {
				try {
					TimeUnit.MILLISECONDS.sleep(waitTime);
				}
				catch (InterruptedException ie) {
				}
			}
			else {
				if (processResponse(receivedMessages, delayedMessages)) {
					break;
				}
			}
		}

		Collections.sort(delayedMessages, new MessagePriorityComparator());

		for (Message delayedMessage : delayedMessages) {
			if (delayedMessage instanceof CommandMessage) {
				_messageSender.send(delayedMessage);
			}
			else {
				_log.error(
					"There are no handlers for message " + delayedMessage);
			}
		}

		_handshakeManager.setPending(false);

		if (_log.isInfoEnabled()) {
			_log.info("Terminated connection");
		}
	}

	protected boolean processResponse(
		List<Message> receivedMessages, List<Message> delayedMessages) {

		boolean receivedHandshakeResponse = false;

		for (Message receivedMessage : receivedMessages) {
			if (receivedMessage instanceof ResponseCommandMessage) {
				ResponseCommandMessage responseCommandMessage =
					(ResponseCommandMessage)receivedMessage;

				if (CommandMessage.COMMAND_TYPE_INITIATE_HANDSHAKE.equals(
						responseCommandMessage.getCommandType())) {

					if (responseCommandMessage.contains("error")) {
						throw new RuntimeException(
							(String)responseCommandMessage.get("error"));
					}

					receivedHandshakeResponse = true;
				}
			}
			else {
				delayedMessages.add(receivedMessage);
			}
		}

		return receivedHandshakeResponse;
	}

	private static Log _log = LogFactoryUtil.getLog(SignoffTask.class);

	private boolean _deregister;

	@BeanReference(type = HandshakeManager.class)
	private HandshakeManager _handshakeManager;

	private int _handshakeReplyReads;
	private long _heartbeatInterval;

	@BeanReference(type = KeyGenerator.class)
	private KeyGenerator _keyGenerator;

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

	@BeanReference(name = "messageSender.lcs_commands")
	private SingleDestinationMessageSender _messageSender;

	private int _stopReplyReads;
	private long _stopWaitTime;

}