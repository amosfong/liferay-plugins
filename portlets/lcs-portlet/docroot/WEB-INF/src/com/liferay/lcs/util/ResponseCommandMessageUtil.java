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

package com.liferay.lcs.util;

import com.liferay.lcs.messaging.Message;
import com.liferay.lcs.messaging.RequestCommandMessage;
import com.liferay.lcs.messaging.ResponseCommandMessage;

/**
 * @author Ivica Cardic
 */
public class ResponseCommandMessageUtil {

	public static ResponseCommandMessage createResponseCommandMessage(
		RequestCommandMessage requestCommandMessage, Object payload) {

		return createResponseCommandMessage(
			requestCommandMessage, payload, null);
	}

	public static ResponseCommandMessage createResponseCommandMessage(
		RequestCommandMessage requestCommandMessage, Object payload,
		String error) {

		ResponseCommandMessage responseCommandMessage =
			new ResponseCommandMessage();

		if (error != null) {
			responseCommandMessage.put(Message.KEY_ERROR, error);
		}

		responseCommandMessage.setCommandType(
			requestCommandMessage.getCommandType());
		responseCommandMessage.setCorrelationId(
			requestCommandMessage.getCorrelationId());
		responseCommandMessage.setCreateTime(System.currentTimeMillis());
		responseCommandMessage.setKey(requestCommandMessage.getKey());

		if (payload != null) {
			responseCommandMessage.setPayload(payload);
		}

		return responseCommandMessage;
	}

}