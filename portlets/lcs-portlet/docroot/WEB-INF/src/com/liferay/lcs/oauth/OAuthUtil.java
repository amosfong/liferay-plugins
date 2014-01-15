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

package com.liferay.lcs.oauth;

import com.liferay.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * @author Igor Beslic
 */
public class OAuthUtil {

	public static String buildURL(
		String hostName, int port, String protocol, String uri) {

		StringBundler sb = new StringBundler(7);

		sb.append(protocol);
		sb.append(StringPool.COLON);
		sb.append(StringPool.DOUBLE_SLASH);
		sb.append(hostName);

		if (port != 80) {
			sb.append(StringPool.COLON);
			sb.append(port);
		}

		sb.append(uri);

		return sb.toString();
	}

	public static Token extractAccessToken(
		Token requestToken, String oAuthVerifier) {

		Verifier verifier = new Verifier(oAuthVerifier);

		OAuthService oAuthService = getOAuthService();

		return oAuthService.getAccessToken(requestToken, verifier);
	}

	public static String getAuthorizeURL(
		String callbackURL, Token requestToken) {

		String authorizeRequestURL = getOAuthService().getAuthorizationUrl(
			requestToken);

		if (Validator.isNotNull(callbackURL)) {
			authorizeRequestURL = HttpUtil.addParameter(
				authorizeRequestURL, "oauth_callback", callbackURL);
		}

		return authorizeRequestURL.replace("{0}", requestToken.getToken());
	}

	public static OAuthService getOAuthService() {
		if (_oAuthService == null) {
			ServiceBuilder oAuthServiceBuilder = new ServiceBuilder();

			oAuthServiceBuilder.apiKey(
				PortletPropsValues.OSB_LCS_PORTLET_OAUTH_CONSUMER_KEY);
			oAuthServiceBuilder.apiSecret(
				PortletPropsValues.OSB_LCS_PORTLET_OAUTH_CONSUMER_SECRET);
			oAuthServiceBuilder.provider(OAuthAPIImpl.class);

			_oAuthService = oAuthServiceBuilder.build();
		}

		return _oAuthService;
	}

	public static Token getRequestToken() {
		OAuthService oAuthService = getOAuthService();

		return oAuthService.getRequestToken();
	}

	private static OAuthService _oAuthService;

}