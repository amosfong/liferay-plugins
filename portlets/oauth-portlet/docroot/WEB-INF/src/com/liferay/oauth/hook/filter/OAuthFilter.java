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

package com.liferay.oauth.hook.filter;

import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.OAuthUserLocalServiceUtil;
import com.liferay.oauth.util.DefaultOAuthAccessor;
import com.liferay.oauth.util.OAuthAccessor;
import com.liferay.oauth.util.OAuthConsumer;
import com.liferay.oauth.util.OAuthMessage;
import com.liferay.oauth.util.OAuthUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.ProtectedServletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Tomas Polesovsky
 */
public class OAuthFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain)
		throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;

		if (!isUsingOAuth(request)) {
			filterChain.doFilter(servletRequest, servletResponse);

			return;
		}

		try {
			OAuthMessage oAuthMessage = OAuthUtil.getOAuthMessage(request);

			OAuthUser oAuthUser = getOAuthUser(oAuthMessage);

			OAuthAccessor oAuthAccessor = getOAuthAccessor(
				oAuthMessage, oAuthUser);

			OAuthUtil.validateOAuthMessage(oAuthMessage, oAuthAccessor);

			servletRequest = new ProtectedServletRequest(
				request, String.valueOf(oAuthUser.getUserId()), _OAUTH);

			filterChain.doFilter(servletRequest, servletResponse);
		}
		catch (Exception e) {
			handleException(request, response, e);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {
		_sendBodyOnError = GetterUtil.getBoolean(
			filterConfig.getInitParameter("send-body-on-error"));
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

			throw new OAuthException(net.oauth.OAuth.Problems.PARAMETER_ABSENT);
		}

		OAuthUser oAuthUser = OAuthUserLocalServiceUtil.fetchOAuthUser(
			oAuthMessage.getToken());

		if (oAuthUser == null) {
			throw new OAuthException(net.oauth.OAuth.Problems.TOKEN_REJECTED);
		}

		return oAuthUser;
	}

	protected void handleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception e1)
		throws IOException {

		// OAuthServlet is only able to process OAuthProblemException

		net.oauth.OAuthProblemException oAuthProblemException = null;

		Throwable cause = e1;

		while (cause != null) {
			if (cause instanceof net.oauth.OAuthProblemException) {
				oAuthProblemException = (net.oauth.OAuthProblemException)cause;

				cause = null;
			}
			else {
				cause = e1.getCause();
			}
		}

		// Provide net.oauth.OAuth.Problems inside OAuthException

		if ((oAuthProblemException == null) &&
			(e1 instanceof OAuthException) &&
			Validator.isNotNull(e1.getMessage())) {

			oAuthProblemException = new net.oauth.OAuthProblemException(
				e1.getMessage());
		}

		if (oAuthProblemException != null) {
			try {
				OAuthUtil.handleException(
					request, response, oAuthProblemException, _sendBodyOnError);

				return;
			}
			catch (Exception e2) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to process OAuth exception", e2);
				}
			}
		}

		_log.error(e1, e1);

		response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
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

	private static Log _log = LogFactoryUtil.getLog(OAuthFilter.class);

	private boolean _sendBodyOnError;

}