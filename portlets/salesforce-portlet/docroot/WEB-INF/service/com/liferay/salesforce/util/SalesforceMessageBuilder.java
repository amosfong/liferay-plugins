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

package com.liferay.salesforce.util;

import com.liferay.portal.kernel.messaging.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class SalesforceMessageBuilder {

	public SalesforceMessageBuilder(String objectType) {
		this(objectType, null);
	}

	public SalesforceMessageBuilder(String objectType, String objectId) {
		_message.put(SalesforceMessageConstants.SOBJECT_ID, objectId);
		_message.put(SalesforceMessageConstants.SOBJECT_TYPE, objectType);
	}

	public void addAttribute(String name, Object value) {
		Map<String, Object> attributes =
			(HashMap<String, Object>)_message.getPayload();

		if (attributes == null) {
			attributes = new HashMap<String, Object>();

			_message.setPayload(attributes);
		}

		attributes.put(name, value);
	}

	public Message getMessage() {
		return _message;
	}

	public void setFieldsToNull(String[] fieldsToNull) {
		_message.put(
			SalesforceMessageConstants.SOBJECT_FIELDS_TO_NULL, fieldsToNull);
	}

	private Message _message = new Message();

}