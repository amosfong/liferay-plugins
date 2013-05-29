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

package com.liferay.salesforce.portlet.action;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.salesforce.connection.SalesforceConnectionManager;
import com.liferay.salesforce.service.ClpSerializer;
import com.liferay.salesforce.util.PortletPropsKeys;
import com.liferay.salesforce.util.PrefsPortletPropsUtil;
import com.liferay.util.bridges.mvc.BaseActionCommand;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 */
public class ConfigureSalesforceConnectionActionCommand
	extends BaseActionCommand {

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		String salesforceServerURL = ParamUtil.getString(
			portletRequest, "salesforceServerURL");
		String salesforceUserName = ParamUtil.getString(
			portletRequest, "salesforceUserName");
		String salesforcePassword = ParamUtil.getString(
			portletRequest, "salesforcePassword");

		if (Validator.isNull(salesforceUserName)) {
			SessionErrors.add(portletRequest, "userNameRequired");
		}
		else if (Validator.isNull(salesforcePassword)) {
			SessionErrors.add(portletRequest, "passwordRequired");
		}

		if (!SessionErrors.isEmpty(portletRequest)) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletPreferences preferences =
			PrefsPortletPropsUtil.getPortletPreferences(
				themeDisplay.getCompanyId());

		preferences.setValue(
			PortletPropsKeys.SALESFORCE_PASSWORD, salesforcePassword);
		preferences.setValue(
			PortletPropsKeys.SALESFORCE_SERVER_URL, salesforceServerURL);
		preferences.setValue(
			PortletPropsKeys.SALESFORCE_USER_NAME, salesforceUserName);

		preferences.store();

		if (!testSalesforceConnection(themeDisplay.getCompanyId())) {
			SessionErrors.add(portletRequest, "connectionFailed");
		}
	}

	protected boolean testSalesforceConnection(long companyId) {
		try {
			SalesforceConnectionManager salesforceConnectionManager =
				(SalesforceConnectionManager)PortletBeanLocatorUtil.locate(
					ClpSerializer.getServletContextName(),
					SalesforceConnectionManager.class.getName());

			salesforceConnectionManager.getSalesforceConnection(companyId);

			return true;
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ConfigureSalesforceConnectionActionCommand.class);

}