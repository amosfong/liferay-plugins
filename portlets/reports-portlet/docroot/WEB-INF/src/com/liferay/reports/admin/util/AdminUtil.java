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

package com.liferay.reports.admin.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.reports.util.PortletPropsValues;
import com.liferay.util.ContentUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 */
public class AdminUtil {

	public static String getEmailDeliveryBody(PortletPreferences preferences) {
		String emailDeliveryBody = preferences.getValue(
			"emailDeliveryBody", StringPool.BLANK);

		if (Validator.isNotNull(emailDeliveryBody)) {
			return emailDeliveryBody;
		}

		return ContentUtil.get(PortletPropsValues.ADMIN_EMAIL_DELIVERY_BODY);
	}

	public static String getEmailDeliverySubject(
		PortletPreferences preferences) {

		String emailDeliverySubject = preferences.getValue(
			"emailDeliverySubject", StringPool.BLANK);

		if (Validator.isNotNull(emailDeliverySubject)) {
			return emailDeliverySubject;
		}

		return ContentUtil.get(PortletPropsValues.ADMIN_EMAIL_DELIVERY_SUBJECT);
	}

	public static String getEmailFromAddress(PortletPreferences preferences) {
		String emailFromAddress = PortletPropsValues.ADMIN_EMAIL_FROM_ADDRESS;

		return preferences.getValue("emailFromAddress", emailFromAddress);
	}

	public static String getEmailFromName(PortletPreferences preferences) {
		String emailFromName = PortletPropsValues.ADMIN_EMAIL_FROM_NAME;

		return preferences.getValue("emailFromName", emailFromName);
	}

	public static String getEmailNotificationsBody(
		PortletPreferences preferences) {

		String emailNotificationsBody = preferences.getValue(
			"emailNotificationsBody", StringPool.BLANK);

		if (Validator.isNotNull(emailNotificationsBody)) {
			return emailNotificationsBody;
		}

		return ContentUtil.get(
			PortletPropsValues.ADMIN_EMAIL_NOTIFICATIONS_BODY);
	}

	public static String getEmailNotificationsSubject(
		PortletPreferences preferences) {

		String emailNotificationsSubject = preferences.getValue(
			"emailNotificationsSubject", StringPool.BLANK);

		if (Validator.isNotNull(emailNotificationsSubject)) {
			return emailNotificationsSubject;
		}

		return ContentUtil.get(
			PortletPropsValues.ADMIN_EMAIL_NOTIFICATIONS_SUBJECT);
	}

}