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

package com.liferay.saml.profile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.saml.model.SamlSpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 */
public class WebSsoProfileUtil {

	public static SamlSpSession getSamlSpSession(HttpServletRequest request)
		throws SystemException {

		return getWebSsoProfile().getSamlSpSession(request);
	}

	public static WebSsoProfile getWebSsoProfile() {
		return _webSsoProfile;
	}

	public static void processAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		getWebSsoProfile().processAuthnRequest(request, response);
	}

	public static void processResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		getWebSsoProfile().processResponse(request, response);
	}

	public static void sendAuthnRequest(
			HttpServletRequest request, HttpServletResponse response,
			String relayState)
		throws PortalException, SystemException {

		getWebSsoProfile().sendAuthnRequest(request, response, relayState);
	}

	public void setWebSsoProfile(WebSsoProfile webSsoProfile) {
		_webSsoProfile = webSsoProfile;
	}

	private static WebSsoProfile _webSsoProfile;

}