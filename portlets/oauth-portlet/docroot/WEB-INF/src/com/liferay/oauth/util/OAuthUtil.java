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

package com.liferay.oauth.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.service.ServiceContext;

import java.io.OutputStream;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivica Cardic
 * @author Raymond Augé
 */
public class OAuthUtil {

	public static String addParameters(String url, String... parameters)
		throws OAuthException {

		return getOAuth().addParameters(url, parameters);
	}

	public static void authorize(
			OAuthAccessor oAuthAccessor, long userId,
			ServiceContext serviceContext)
		throws PortalException {

		getOAuth().authorize(oAuthAccessor, userId, serviceContext);
	}

	public static void formEncode(
			String token, String tokenSecret, OutputStream outputStream)
		throws OAuthException {

		getOAuth().formEncode(token, tokenSecret, outputStream);
	}

	public static void generateAccessToken(
			OAuthAccessor oAuthAccessor, long userId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		getOAuth().generateAccessToken(oAuthAccessor, userId, serviceContext);
	}

	public static void generateRequestToken(OAuthAccessor oAuthAccessor) {
		getOAuth().generateRequestToken(oAuthAccessor);
	}

	public static OAuth getOAuth() {
		return _oAuth;
	}

	public static OAuthAccessor getOAuthAccessor(OAuthMessage oAuthMessage)
		throws PortalException {

		return getOAuth().getOAuthAccessor(oAuthMessage);
	}

	public static OAuthConsumer getOAuthConsumer(OAuthMessage oAuthMessage)
		throws PortalException, SystemException {

		return getOAuth().getOAuthConsumer(oAuthMessage);
	}

	public static OAuthMessage getOAuthMessage(HttpServletRequest request) {
		return getOAuth().getOAuthMessage(request);
	}

	public static OAuthMessage getOAuthMessage(
		HttpServletRequest request, String url) {

		return getOAuth().getOAuthMessage(request, url);
	}

	public static OAuthMessage getOAuthMessage(PortletRequest portletRequest) {
		return getOAuth().getOAuthMessage(portletRequest);
	}

	public static OAuthMessage getOAuthMessage(
		PortletRequest portletRequest, String url) {

		return getOAuth().getOAuthMessage(portletRequest, url);
	}

	public static void handleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception exception, boolean sendBody)
		throws OAuthException {

		getOAuth().handleException(request, response, exception, sendBody);
	}

	public static String randomizeToken(String token) {
		return getOAuth().randomizeToken(token);
	}

	public static void validateOAuthMessage(
			OAuthMessage message, OAuthAccessor oAuthAccessor)
		throws OAuthException {

		getOAuth().validateOAuthMessage(message, oAuthAccessor);
	}

	public void setOAuth(OAuth oAuth) {
		_oAuth = oAuth;
	}

	private static OAuth _oAuth;

}