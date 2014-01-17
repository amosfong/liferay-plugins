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

package com.liferay.testoauth.oauth;

import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.http.HttpServletResponse;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * @author Igor Beslic
 */
public class LiferayOAuthPlatformTest {

	public LiferayOAuthPlatformTest(
		String hostName, String hostPort,
		OAuthServiceHandler oAuthServiceHandler) {

		_hostName = hostName;
		_hostPort = hostPort;
		_oAuthServiceHandler = oAuthServiceHandler;
	}

	public String getRemotePortalCompany(
		String accessToken, String accessSecret, String webId) {

		String apiJSONWSURL =
			_URL_COMPANY_GET_COMPANY_BY_WEB_ID + "web-id/" + webId;

		Token token = new Token(accessToken, accessSecret);

		String requestURL = OAuthUtil.buildURL(
			_hostName, Integer.parseInt(_hostPort), "http", apiJSONWSURL);

		OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, requestURL);

		OAuthService oAuthService = _oAuthServiceHandler.getOAuthService();

		oAuthService.signRequest(token, oAuthRequest);

		Response response = oAuthRequest.send();

		if (response.getCode() == HttpServletResponse.SC_UNAUTHORIZED) {
			String value = response.getHeader("WWW-Authenticate");

			return "Unauthorized request: " + value;
		}

		if (response.getCode() == HttpServletResponse.SC_OK) {
			return formatJSON(response.getBody());
		}
		else {
			return "{\"exception\":\"Server returned " +
				response.getCode() + ".\"}";
		}
	}

	protected String formatJSON(String json) {
		StringBuffer sb = new StringBuffer();

		for (String token : json.split(StringPool.COMMA)) {
			if (token.startsWith(StringPool.OPEN_CURLY_BRACE)) {
				sb.append(StringPool.OPEN_CURLY_BRACE);
				sb.append(StringPool.NEW_LINE);

				token = token.substring(1);
			}

			sb.append(StringPool.DOUBLE_SPACE);

			if (token.endsWith(StringPool.CLOSE_CURLY_BRACE)) {
				sb.append(token.substring(0, (token.length() -1)));
				sb.append(StringPool.NEW_LINE);
				sb.append(StringPool.CLOSE_CURLY_BRACE);
			}
			else {
				sb.append(token);
				sb.append(StringPool.COMMA);
				sb.append(StringPool.NEW_LINE);
			}
		}

		return sb.toString();
	}

	private static String _URL_COMPANY_GET_COMPANY_BY_WEB_ID =
		"/api/secure/jsonws/company/get-company-by-web-id/";

	private String _hostName;
	private String _hostPort;
	private OAuthServiceHandler _oAuthServiceHandler;

}