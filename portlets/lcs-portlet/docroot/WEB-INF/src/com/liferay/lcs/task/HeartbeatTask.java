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

import com.liferay.lcs.messaging.HeartbeatMessage;
import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.lcs.util.HandshakeManagerUtil;
import com.liferay.lcs.util.KeyGenerator;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class HeartbeatTask implements Runnable {

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void doRun() throws Exception {
		if (!HandshakeManagerUtil.isReady()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Waiting for handshake manager");
			}

			return;
		}

		HeartbeatMessage heartbeatMessage = new HeartbeatMessage();

		heartbeatMessage.setCreateTime(System.currentTimeMillis());
		heartbeatMessage.setKey(_keyGenerator.getKey());

		if (_log.isDebugEnabled()) {
			_log.debug("Sending " + heartbeatMessage);
		}

		_lcsGatewayService.sendMessage(heartbeatMessage);
	}

	private static Log _log = LogFactoryUtil.getLog(HeartbeatTask.class);

	@BeanReference(type = KeyGenerator.class)
	private KeyGenerator _keyGenerator;

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

}