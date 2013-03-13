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

package com.liferay.reports.admin.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

/**
 * @author Gavin Wan
 * @author Peter Shin
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");

		if (tabs2.equals("delivery-email")) {
			validateEmailDelivery(actionRequest);
		}
		else if (tabs2.equals("email-from")) {
			validateEmailFrom(actionRequest);
		}
		else if (tabs2.equals("notifications-email")) {
			validateEmailNotifications(actionRequest);
		}

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	protected void validateEmailDelivery(ActionRequest actionRequest)
		throws Exception {

		String emailDeliverySubject = getParameter(
			actionRequest, "emailDeliverySubject");
		String emailDeliveryBody = getParameter(
			actionRequest, "emailDeliveryBody");

		if (Validator.isNull(emailDeliverySubject)) {
			SessionErrors.add(actionRequest, "emailDeliverySubject");
		}
		else if (Validator.isNull(emailDeliveryBody)) {
			SessionErrors.add(actionRequest, "emailDeliveryBody");
		}
	}

	protected void validateEmailFrom(ActionRequest actionRequest)
		throws Exception {

		String emailFromName = getParameter(actionRequest, "emailFromName");
		String emailFromAddress = getParameter(
			actionRequest, "emailFromAddress");

		if (Validator.isNull(emailFromName)) {
			SessionErrors.add(actionRequest, "emailFromName");
		}
		else if (!Validator.isEmailAddress(emailFromAddress) &&
				 !Validator.isVariableTerm(emailFromAddress)) {

			SessionErrors.add(actionRequest, "emailFromAddress");
		}
	}

	protected void validateEmailNotifications(ActionRequest actionRequest)
		throws Exception {

		String emailNotificationsSubject = getParameter(
			actionRequest, "emailNotificationsSubject");
		String emailNotificationsBody = getParameter(
			actionRequest, "emailNotificationsBody");

		if (Validator.isNull(emailNotificationsSubject)) {
			SessionErrors.add(actionRequest, "emailNotificationsSubject");
		}
		else if (Validator.isNull(emailNotificationsBody)) {
			SessionErrors.add(actionRequest, "emailNotificationsBody");
		}
	}

}