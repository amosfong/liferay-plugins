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

package com.liferay.salesforce.service.impl;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.test.TestCase;
import com.liferay.salesforce.connection.SalesforceConnection;
import com.liferay.salesforce.connection.SalesforceConnectionManager;

import java.util.Map;

/**
 * @author Michael C. Han
 */
public abstract class BaseSalesforceServiceTestCase extends TestCase {

	@Override
	public void setUp() throws Exception {
		final SalesforceConnection salesforceConnection =
			new SalesforceConnection();

		salesforceConnection.setPassword(
			"Liferay1testn9TCQgjQ1MC2dwQulW9n8b4m");
		salesforceConnection.setUserName("mhan810@yahoo.com");

		salesforceConnection.login();

		salesforceConnectionManager = new SalesforceConnectionManager() {

			public SalesforceConnection getSalesforceConnection(
				long companyId) {

				return salesforceConnection;
			}

		};

		salesforceLocalServiceImpl = new SalesforceLocalServiceImpl();

		salesforceLocalServiceImpl.setSalesforceConnectionManager(
			salesforceConnectionManager);
	}

	protected Object getAttributeObject(Message message, String key) {
		Map<String, Object> attributes =
			(Map<String, Object>)message.getPayload();

		return attributes.get(key);
	}

	protected String getAttributeString(Message message, String key) {
		return (String)getAttributeObject(message, key);
	}

	protected void setAttribute(Message message, String key, Object value) {
		Map<String, Object> attributes =
			(Map<String, Object>)message.getPayload();

		attributes.put(key, value);
	}

	protected SalesforceConnectionManager salesforceConnectionManager;
	protected SalesforceLocalServiceImpl salesforceLocalServiceImpl;

}