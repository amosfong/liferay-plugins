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

package com.liferay.lcs.command;

import com.liferay.lcs.messaging.RequestCommandMessage;
import com.liferay.lcs.util.HandshakeManager;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Igor Beslic
 */
public class DeregisterCommand implements Command {

	@Override
	public void execute(RequestCommandMessage requestCommandMessage) {
		_handshakeManager.deregister();

		if (_log.isDebugEnabled()) {
			_log.debug("Unregistered server from LCS");
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DeregisterCommand.class);

	@BeanReference(type = HandshakeManager.class)
	private HandshakeManager _handshakeManager;

}