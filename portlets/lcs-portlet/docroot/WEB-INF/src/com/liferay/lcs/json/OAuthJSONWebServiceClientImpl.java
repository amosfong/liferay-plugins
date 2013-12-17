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

package com.liferay.lcs.json;

import com.liferay.lcs.oauth.OAuthUtil;

import java.io.IOException;

import javax.security.auth.login.CredentialException;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.HttpRequestBase;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

/**
 * @author Igor Beslic
 */
public class OAuthJSONWebServiceClientImpl extends JSONWebServiceClientImpl {

	@Override
	public void resetHttpClient() {
	}

	@Override
	public void setLogin(String login) {
		_login = login;
	}

	@Override
	public void setPassword(String password) {
		_password = password;
	}

	@Override
	protected String execute(HttpRequestBase httpRequestBase)
		throws CredentialException, IOException {

		try {
			if ((_login == null) && (_password == null)) {
				throw new CredentialException("OAuth credentials are not set");
			}

			Token token = new Token(_login, _password);

			String requestURL = OAuthUtil.buildURL(
				getHostName(), getPort(), getProtocol(),
				String.valueOf(httpRequestBase.getURI()));

			OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, requestURL);

			OAuthService oAuthService = OAuthUtil.getOAuthService();

			oAuthService.signRequest(token, oAuthRequest);

			Response response = oAuthRequest.send();

			if (response.getCode() == HttpServletResponse.SC_UNAUTHORIZED) {
				String value = response.getHeader("WWW-Authenticate");

				throw new CredentialException(value);
			}

			if (response.getCode() == HttpServletResponse.SC_OK) {
				return response.getBody();
			}
			else {
				return "{\"exception\":\"Server returned " +
					response.getCode() + ".\"}";
			}
		}
		finally {
			httpRequestBase.releaseConnection();
		}
	}

	private String _login;
	private String _password;

}