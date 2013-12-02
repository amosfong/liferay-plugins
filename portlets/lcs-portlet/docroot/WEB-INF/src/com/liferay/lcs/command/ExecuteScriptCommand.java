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
import com.liferay.lcs.messaging.ResponseCommandMessage;
import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.lcs.util.ResponseCommandMessageUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncPrintWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scripting.ScriptingException;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Ivica Cardic
 */
public class ExecuteScriptCommand implements Command {

	@Override
	public void execute(RequestCommandMessage requestCommandMessage)
		throws PortalException, SystemException {

		Map<String, Object> inputObjects = new HashMap<String, Object>();

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		UnsyncPrintWriter unsyncPrintWriter = UnsyncPrintWriterPool.borrow(
			unsyncByteArrayOutputStream);

		inputObjects.put("out", unsyncPrintWriter);

		String script = (String)requestCommandMessage.getPayload();

		if (_log.isDebugEnabled()) {
			_log.debug("Executing script " + script);
		}

		String payload = null;

		String error = null;

		try {
			ScriptingUtil.exec(
				(Set<String>)null, inputObjects, "groovy", script,
				new String[]{});

			unsyncPrintWriter.flush();

			payload = unsyncByteArrayOutputStream.toString();
		}
		catch (ScriptingException se) {
			error = se.getMessage();
		}

		ResponseCommandMessage responseCommandMessage =
			ResponseCommandMessageUtil.createResponseCommandMessage(
				requestCommandMessage, payload, error);

		_lcsGatewayService.sendMessage(responseCommandMessage);
	}

	private static Log _log = LogFactoryUtil.getLog(ExecuteScriptCommand.class);

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

}