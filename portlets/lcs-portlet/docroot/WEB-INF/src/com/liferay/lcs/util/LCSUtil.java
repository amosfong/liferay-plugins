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

import com.liferay.lcs.json.JSONWebServiceClient;
import com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.security.auth.login.CredentialException;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class LCSUtil {

	public static final int CREDENTIALS_INVALID = 2;

	public static final int CREDENTIALS_MISSING = 1;

	public static final int CREDENTIALS_NOT_AVAILABLE = 0;

	public static final int CREDENTIALS_SET = 3;

	public static int getCredentialsStatus() {
		javax.portlet.PortletPreferences jxPortletPreferences = null;

		try {
			jxPortletPreferences = getJxPortletPreferences();
		}
		catch (PortalException e) {
			return CREDENTIALS_MISSING;
		}
		catch (SystemException e) {
			return CREDENTIALS_NOT_AVAILABLE;
		}

		String lcsAccessToken = jxPortletPreferences.getValue(
			"lcsAccessToken", null);
		String lcsAccessSecret = jxPortletPreferences.getValue(
			"lcsAccessSecret", null);

		if (Validator.isNull(lcsAccessToken) ||
			Validator.isNull(lcsAccessSecret)) {

			return CREDENTIALS_INVALID;
		}

		try {
			LCSClusterNodeServiceUtil.isRegistered(KeyGeneratorUtil.getKey());

			return CREDENTIALS_SET;
		}
		catch (Exception e) {
			if (e.getCause() instanceof CredentialException) {
				return CREDENTIALS_INVALID;
			}

			throw new RuntimeException(e);
		}
	}

	public static boolean isCredentialsSet() {
		if (getCredentialsStatus() == CREDENTIALS_SET) {
			return true;
		}

		return false;
	}

	public static void setupCredentials()
		throws PortalException, SystemException {

		javax.portlet.PortletPreferences jxPortletPreferences =
			getJxPortletPreferences();

		String lcsAccessToken = jxPortletPreferences.getValue(
			"lcsAccessToken", null);
		String lcsAccessSecret = jxPortletPreferences.getValue(
			"lcsAccessSecret", null);

		if (Validator.isNull(lcsAccessToken) ||
			Validator.isNull(lcsAccessSecret)) {

			throw new SystemException("Unable to setup LCS credentials");
		}

		_jsonWebServiceClient.setLogin(lcsAccessToken);
		_jsonWebServiceClient.setPassword(lcsAccessSecret);

		_jsonWebServiceClient.resetHttpClient();
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	protected static javax.portlet.PortletPreferences getJxPortletPreferences()
		throws PortalException, SystemException {

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPortletPreferences(
				CompanyConstants.SYSTEM, PortletKeys.PREFS_OWNER_TYPE_COMPANY,
				0, PortletKeys.MONITORING);

		return PortletPreferencesFactoryUtil.fromDefaultXML(
				portletPreferences.getPreferences());
	}

	private static JSONWebServiceClient _jsonWebServiceClient;

}