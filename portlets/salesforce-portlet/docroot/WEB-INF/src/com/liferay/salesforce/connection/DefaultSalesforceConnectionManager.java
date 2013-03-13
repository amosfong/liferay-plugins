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

package com.liferay.salesforce.connection;

import com.liferay.salesforce.util.PortletPropsKeys;
import com.liferay.salesforce.util.PrefsPortletPropsUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class DefaultSalesforceConnectionManager
	implements SalesforceConnectionManager {

	public static SalesforceConnectionManager getInstance() {
		return _instance;
	}

	public SalesforceConnection getSalesforceConnection(long companyId) {
		try {
			return _getSalesforceConnection(companyId);
		}
		catch (Exception e) {
			throw new IllegalStateException(
				"Unable to connect to Salesforce.com", e);
		}
	}

	private SalesforceConnection _getSalesforceConnection(long companyId)
		throws Exception {

		SalesforceConnection salesforceConnection = _salesforceConnections.get(
			companyId);

		if (salesforceConnection == null) {
			salesforceConnection = new SalesforceConnection();

			salesforceConnection.setPassword(
				PrefsPortletPropsUtil.getString(
					companyId, PortletPropsKeys.SALESFORCE_PASSWORD));
			salesforceConnection.setServerUrl(
				PrefsPortletPropsUtil.getString(
					companyId, PortletPropsKeys.SALESFORCE_SERVER_URL));
			salesforceConnection.setUserName(
				PrefsPortletPropsUtil.getString(
					companyId, PortletPropsKeys.SALESFORCE_USER_NAME));

			salesforceConnection.login();

			_salesforceConnections.put(companyId, salesforceConnection);
		}

		salesforceConnection.login();

		salesforceConnection.setUseDefaultAssignmentRule(true);

		return salesforceConnection;
	}

	private static SalesforceConnectionManager _instance =
		new DefaultSalesforceConnectionManager();

	private Map<Long, SalesforceConnection> _salesforceConnections =
		new HashMap<Long, SalesforceConnection>();

}