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

package com.liferay.oauth.hook.security.auth;

import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.OAuthUserLocalServiceUtil;
import com.liferay.oauth.util.DefaultOAuthAccessor;
import com.liferay.oauth.util.OAuthAccessor;
import com.liferay.oauth.util.OAuthConsumer;
import com.liferay.oauth.util.OAuthMessage;
import com.liferay.oauth.util.OAuthUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.AccessControlContext;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthVerifier;
import com.liferay.portal.security.auth.AuthVerifierResult;

import java.io.IOException;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Tomas Polesovsky
 */
public class OAuthVerifier implements AuthVerifier {

	@Override
	public String getAuthType() {
		return OAuthVerifier.class.getSimpleName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		HttpServletRequest request = accessControlContext.getRequest();

		if (!isUsingOAuth(request)) {
			return authVerifierResult;
		}

		try {
			OAuthMessage oAuthMessage = OAuthUtil.getOAuthMessage(request);

			OAuthUser oAuthUser = getOAuthUser(oAuthMessage);

			OAuthAccessor oAuthAccessor = getOAuthAccessor(
				oAuthMessage, oAuthUser);

			OAuthUtil.validateOAuthMessage(oAuthMessage, oAuthAccessor);

			authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
			authVerifierResult.setUserId(oAuthUser.getUserId());
		}
		catch (Exception e) {
			try {
				boolean sendBody = GetterUtil.getBoolean(
					properties.getProperty("send.body"));

				OAuthUtil.handleException(
					request, accessControlContext.getResponse(), e, sendBody);

				authVerifierResult.setState(
					AuthVerifierResult.State.INVALID_CREDENTIALS);
			}
			catch (OAuthException oae) {
				throw new AuthException(oae);
			}
		}

		return authVerifierResult;
	}

	protected OAuthAccessor getOAuthAccessor(
			OAuthMessage oAuthMessage, OAuthUser oAuthUser)
		throws PortalException, SystemException {

		OAuthConsumer oAuthConsumer = OAuthUtil.getOAuthConsumer(oAuthMessage);

		OAuthAccessor oAuthAccessor = new DefaultOAuthAccessor(oAuthConsumer);

		oAuthAccessor.setAccessToken(oAuthUser.getAccessToken());
		oAuthAccessor.setRequestToken(null);
		oAuthAccessor.setTokenSecret(oAuthUser.getAccessSecret());

		return oAuthAccessor;
	}

	protected OAuthUser getOAuthUser(OAuthMessage oAuthMessage)
		throws IOException, OAuthException, SystemException {

		if (Validator.isNull(oAuthMessage) ||
			Validator.isNull(oAuthMessage.getToken())) {

			net.oauth.OAuthException oAuthException =
				new net.oauth.OAuthProblemException(
					net.oauth.OAuth.Problems.PARAMETER_ABSENT);

			throw new OAuthException(oAuthException);
		}

		OAuthUser oAuthUser = OAuthUserLocalServiceUtil.fetchOAuthUser(
			oAuthMessage.getToken());

		if (oAuthUser == null) {
			net.oauth.OAuthException oAuthException =
				new net.oauth.OAuthProblemException(
					net.oauth.OAuth.Problems.TOKEN_REJECTED);

			throw new OAuthException(oAuthException);
		}

		return oAuthUser;
	}

	protected boolean isUsingOAuth(HttpServletRequest request) {
		String oAuthToken = ParamUtil.getString(
			request, net.oauth.OAuth.OAUTH_TOKEN);

		if (Validator.isNotNull(oAuthToken)) {
			return true;
		}

		String authorization = GetterUtil.getString(
			request.getHeader(HttpHeaders.AUTHORIZATION));

		if (Validator.isNotNull(authorization)) {
			String authScheme = authorization.substring(0, 5);

			if (StringUtil.equalsIgnoreCase(authScheme, _OAUTH)) {
				return true;
			}
		}

		return false;
	}

	private static final String _OAUTH = "OAuth";

}