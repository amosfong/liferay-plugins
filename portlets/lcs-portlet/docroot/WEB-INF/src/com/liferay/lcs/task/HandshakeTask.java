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
import com.liferay.lcs.util.LCSClusterNodeUtil;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.comparator.MessagePriorityComparator;
import com.liferay.osb.lcs.service.LCSClusterNodeService;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.license.util.LicenseManagerUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 */
public class HandshakeTask implements Runnable {

	public void destroy() {
		_scheduledExecutorService.shutdown();

		try {
			if (!_scheduledExecutorService.awaitTermination(
					5, TimeUnit.SECONDS)) {

				_scheduledExecutorService.shutdownNow();
			}
		}
		catch (final InterruptedException ie) {
			_scheduledExecutorService.shutdownNow();
		}
	}

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			_handshakeManager.setPending(false);
			_handshakeManager.setReady(false);

			_log.error(e, e);
		}
	}

	public void setHandshakeReplyReads(int handshakeReplyReads) {
		_handshakeReplyReads = handshakeReplyReads;
	}

	public void setHandshakeWaitTime(long handshakeWaitTime) {
		_handshakeWaitTime = handshakeWaitTime;
	}

	public void setHeartbeatInterval(long heartbeatInterval) {
		_heartbeatInterval = heartbeatInterval;
	}

	protected void doRun() throws Exception {
		boolean registered = false;

		String key = _keyGenerator.getKey();

		try {
			if (_lcsClusterNodeService.getLCSClusterNode(key) != null) {
				registered = true;
			}
		}
		catch (Exception e) {
		}

		if (!registered) {
			registered = LCSClusterNodeUtil.registerLCSClusterNode();

			if (!registered) {
				if (_log.isWarnEnabled()) {
					_log.warn("This node is not registered");
				}

				_handshakeManager.setPending(false);

				return;
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Initiate handshake");
		}

		HandshakeMessage handshakeMessage = new HandshakeMessage();

		handshakeMessage.put(
			Message.KEY_BUILD_NUMBER, ReleaseInfo.getBuildNumber());
		handshakeMessage.put(
			Message.KEY_HEARTBEAT_INTERVAL, String.valueOf(_heartbeatInterval));
		handshakeMessage.put(Message.PORTAL_EDITION, getPortalEdition());
		handshakeMessage.setKey(key);

		_lcsGatewayService.sendMessage(handshakeMessage);

		int attempt = 0;
		List<Message> delayedMessages = new ArrayList<Message>();
		List<Message> receivedMessages = null;

		while (true) {
			if (attempt++ > _handshakeReplyReads) {
				_log.error(
					"Unable to establish a connection after " +
						_handshakeReplyReads + " handshakes");

				_handshakeManager.setPending(false);

				return;
			}

			receivedMessages = _lcsGatewayService.getMessages(key);

			if (receivedMessages.isEmpty()) {
				try {
					TimeUnit.MILLISECONDS.sleep(
						_handshakeWaitTime / _handshakeReplyReads);
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

		Map<String, String> lcsConnectionMetadata =
			_handshakeManager.getLCSConnectionMetadata();

		lcsConnectionMetadata.put(
			"handshakeTime", String.valueOf(System.currentTimeMillis()));
		lcsConnectionMetadata.put(
			"jvmMetricsTaskInterval", String.valueOf(60000));
		lcsConnectionMetadata.put("messageTaskInterval", String.valueOf(10000));

		List<ScheduledFuture<?>> scheduledFutures =
			_handshakeManager.getScheduledFutures();

		scheduledFutures.add(
			_scheduledExecutorService.scheduleAtFixedRate(
				_cacheMetricsTask, 60000, 60000, TimeUnit.MILLISECONDS));
		scheduledFutures.add(
			_scheduledExecutorService.scheduleAtFixedRate(
				_commandMessageTask,
				LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD,
				LCSConstants.COMMAND_MESSAGE_TASK_SCHEDULE_PERIOD,
				TimeUnit.SECONDS));
		scheduledFutures.add(
			_scheduledExecutorService.scheduleAtFixedRate(
				_heartbeatTask, _heartbeatInterval, _heartbeatInterval,
				TimeUnit.MILLISECONDS));
		scheduledFutures.add(
			_scheduledExecutorService.scheduleAtFixedRate(
				_jvmMetricsTask, 60000, 60000, TimeUnit.MILLISECONDS));
		scheduledFutures.add(
			_scheduledExecutorService.scheduleAtFixedRate(
				_serverMetricsTask, 60000, 60000, TimeUnit.MILLISECONDS));

		_handshakeManager.setPending(false);
		_handshakeManager.setReady(true);

		if (_log.isInfoEnabled()) {
			_log.info("Established connection");
		}
	}

	protected String getPortalEdition() {
		String portalEdition = LCSConstants.PORTAL_EDITION_CE;

		int licenceState = LicenseManagerUtil.getLicenseState("Portal");

		if (licenceState == LicenseManagerUtil.STATE_GOOD) {
			portalEdition = LCSConstants.PORTAL_EDITION_EE;
		}

		return portalEdition;
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

	private static Log _log = LogFactoryUtil.getLog(HandshakeTask.class);

	private static ScheduledExecutorService _scheduledExecutorService =
		Executors.newScheduledThreadPool(6);

	@BeanReference(type = CacheMetricsTask.class)
	private CacheMetricsTask _cacheMetricsTask;

	@BeanReference(type = CommandMessageTask.class)
	private CommandMessageTask _commandMessageTask;

	@BeanReference(type = HandshakeManager.class)
	private HandshakeManager _handshakeManager;

	private int _handshakeReplyReads;
	private long _handshakeWaitTime;
	private long _heartbeatInterval;

	@BeanReference(type = HeartbeatTask.class)
	private HeartbeatTask _heartbeatTask;

	@BeanReference(type = JVMMetricsTask.class)
	private JVMMetricsTask _jvmMetricsTask;

	@BeanReference(type = KeyGenerator.class)
	private KeyGenerator _keyGenerator;

	@BeanReference(type = LCSClusterNodeService.class)
	private LCSClusterNodeService _lcsClusterNodeService;

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

	@BeanReference(name = "messageSender.lcs_commands")
	private SingleDestinationMessageSender _messageSender;

	@BeanReference(type = ServerMetricsTask.class)
	private ServerMetricsTask _serverMetricsTask;

}