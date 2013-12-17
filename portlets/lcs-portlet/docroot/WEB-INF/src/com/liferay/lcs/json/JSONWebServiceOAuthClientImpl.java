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

import org.apache.http.client.methods.HttpRequestBase;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

/**
 * @author Igor Beslic
 */
public class JSONWebServiceOAuthClientImpl extends JSONWebServiceClientImpl {

	@Override
	public void resetHttpClient() {
	}

	@Override
	public void setLogin(String login) {
		_accessToken = login;
	}

	@Override
	public void setPassword(String password) {
		_accessSecret = password;
	}

	@Override
	protected String execute(HttpRequestBase httpRequestBase)
		throws CredentialException, IOException {

		try {
			if ((_accessToken == null) && (_accessSecret == null)) {
				throw new CredentialException("OAuth credentials not set.");
			}

			String requestURL = OAuthUtil.buildURL(
				getHostName(), getPort(), getProtocol(),
				httpRequestBase.getURI().toString());

			Token token = new Token(_accessToken, _accessSecret);

			OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST, requestURL);

			OAuthUtil.getOAuthService().signRequest(token, oAuthRequest);

			Response response = oAuthRequest.send();

			if (response.getCode() == 401) {
				String header = response.getHeader("WWW-Authenticate");

				throw new CredentialException(header);
			}

			if (response.getCode() == 200) {
				return response.getBody();
			}
			else {
				return
					"{\"exception\":\"Server returned " +
					String.valueOf(response.getCode()) +
					".\"}";
			}
		}
		finally {
			httpRequestBase.releaseConnection();
		}
	}

	private String _accessSecret;
	private String _accessToken;

}