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

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.OAuthApplicationLocalServiceUtil;
import com.liferay.oauth.service.OAuthUserLocalServiceUtil;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.oauth.OAuthException;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.PwdGenerator;

import java.io.IOException;
import java.io.OutputStream;

import java.util.List;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.oauth.OAuthProblemException;
import net.oauth.server.OAuthServlet;

/**
 * @author Ivica Cardic
 * @author Raymond Augé
 */
public class V10aOAuth implements OAuth {

	public V10aOAuth(OAuthValidator oAuthValidator) {
		_oAuthValidator = oAuthValidator;
	}

	@Override
	public String addParameters(String url, String... parameters)
		throws OAuthException {

		try {
			return net.oauth.OAuth.addParameters(url, parameters);
		}
		catch (IOException ioe) {
			throw new OAuthException(ioe);
		}
	}

	@Override
	public void authorize(
			OAuthAccessor oAuthAccessor, long userId,
			ServiceContext serviceContext)
		throws PortalException {

		Boolean authorized = (Boolean)oAuthAccessor.getProperty(
			OAuthAccessorConstants.AUTHORIZED);

		if ((authorized != null) && authorized.booleanValue() &&
			Validator.isNotNull(oAuthAccessor.getRequestToken())) {

			throw new OAuthException(net.oauth.OAuth.Problems.TOKEN_EXPIRED);
		}

		oAuthAccessor.setProperty(
			OAuthAccessorConstants.AUTHORIZED, Boolean.TRUE);
		oAuthAccessor.setProperty(OAuthAccessorConstants.USER_ID, userId);

		_portalCache.put(oAuthAccessor.getRequestToken(), oAuthAccessor);
	}

	@Override
	public void formEncode(
			String token, String tokenSecret, OutputStream outputStream)
		throws OAuthException {

		List<net.oauth.OAuth.Parameter> parameters = net.oauth.OAuth.newList(
			net.oauth.OAuth.OAUTH_TOKEN, token,
			net.oauth.OAuth.OAUTH_TOKEN_SECRET, tokenSecret);

		try {
			net.oauth.OAuth.formEncode(parameters, outputStream);
		}
		catch (IOException ioe) {
			throw new OAuthException(ioe);
		}
	}

	@Override
	public void generateAccessToken(
			OAuthAccessor oAuthAccessor, long userId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Boolean authorized = (Boolean)oAuthAccessor.getProperty(
			OAuthAccessorConstants.AUTHORIZED);

		if ((authorized != null) && authorized.booleanValue() &&
			Validator.isNotNull(oAuthAccessor.getAccessToken())) {

			throw new OAuthException(net.oauth.OAuth.Problems.TOKEN_EXPIRED);
		}

		OAuthConsumer oAuthConsumer = oAuthAccessor.getOAuthConsumer();

		OAuthApplication oAuthApplication = oAuthConsumer.getOAuthApplication();

		String consumerKey = oAuthApplication.getConsumerKey();

		String token = randomizeToken(consumerKey);

		oAuthAccessor.setAccessToken(token);

		oAuthAccessor.setRequestToken(null);

		String tokenSecret = randomizeToken(consumerKey.concat(token));

		oAuthAccessor.setTokenSecret(tokenSecret);

		OAuthUser oAuthUser = OAuthUserLocalServiceUtil.fetchOAuthUser(
			userId, oAuthApplication.getOAuthApplicationId());

		if (oAuthUser == null) {
			OAuthUserLocalServiceUtil.addOAuthUser(
				userId, oAuthApplication.getOAuthApplicationId(),
				oAuthAccessor.getAccessToken(), oAuthAccessor.getTokenSecret(),
				serviceContext);
		}
		else {
			if (oAuthApplication.isShareableAccessToken()) {
				oAuthAccessor.setAccessToken(oAuthUser.getAccessToken());
				oAuthAccessor.setTokenSecret(oAuthUser.getAccessSecret());
			}
			else {
				OAuthUserLocalServiceUtil.updateOAuthUser(
					userId, oAuthUser.getOAuthApplicationId(),
					oAuthAccessor.getAccessToken(),
					oAuthAccessor.getTokenSecret(), serviceContext);
			}
		}

		_portalCache.put(token, oAuthAccessor);
	}

	@Override
	public void generateRequestToken(OAuthAccessor oAuthAccessor) {
		OAuthConsumer oAuthConsumer = oAuthAccessor.getOAuthConsumer();

		OAuthApplication oAuthApplication = oAuthConsumer.getOAuthApplication();

		String consumerKey = oAuthApplication.getConsumerKey();

		oAuthAccessor.setAccessToken(null);

		String token = randomizeToken(consumerKey);

		oAuthAccessor.setRequestToken(token);

		String tokenSecret = randomizeToken(consumerKey.concat(token));

		oAuthAccessor.setTokenSecret(tokenSecret);

		_portalCache.put(token, oAuthAccessor);
	}

	@Override
	public OAuthAccessor getOAuthAccessor(OAuthMessage oAuthMessage)
		throws OAuthException {

		String token = null;

		try {
			token = oAuthMessage.getToken();
		}
		catch (IOException ioe) {
			throw new OAuthException(ioe);
		}

		OAuthAccessor oAuthAccessor = _portalCache.get(token);

		if (oAuthAccessor == null) {
			net.oauth.OAuthException oAuthException =
				new OAuthProblemException(
					net.oauth.OAuth.Problems.TOKEN_EXPIRED);

			throw new OAuthException(oAuthException);
		}

		return oAuthAccessor;
	}

	@Override
	public OAuthConsumer getOAuthConsumer(OAuthMessage requestMessage)
		throws PortalException, SystemException {

		String consumerKey = null;

		try {
			consumerKey = requestMessage.getConsumerKey();
		}
		catch (IOException ioe) {
			net.oauth.OAuthException oAuthException =
				new OAuthProblemException(
					net.oauth.OAuth.Problems.CONSUMER_KEY_UNKNOWN);

			oAuthException.initCause(ioe);

			throw new OAuthException(oAuthException);
		}

		OAuthApplication oAuthApplication =
			OAuthApplicationLocalServiceUtil.fetchOAuthApplication(consumerKey);

		if (oAuthApplication == null) {
			net.oauth.OAuthException oAuthException =
				new OAuthProblemException(
					net.oauth.OAuth.Problems.CONSUMER_KEY_REFUSED);

			throw new OAuthException(oAuthException);
		}

		return new DefaultOAuthConsumer(oAuthApplication);
	}

	@Override
	public OAuthMessage getOAuthMessage(HttpServletRequest request) {
		return getOAuthMessage(request, null);
	}

	@Override
	public OAuthMessage getOAuthMessage(
		HttpServletRequest request, String url) {

		return new DefaultOAuthMessage(OAuthServlet.getMessage(request, url));
	}

	@Override
	public OAuthMessage getOAuthMessage(PortletRequest portletRequest) {
		return getOAuthMessage(portletRequest, null);
	}

	@Override
	public OAuthMessage getOAuthMessage(
		PortletRequest portletRequest, String url) {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return getOAuthMessage(request, url);
	}

	@Override
	public void handleException(
			HttpServletRequest request, HttpServletResponse response,
			Exception exception, boolean sendBody)
		throws OAuthException {

		String realm = Http.HTTP_WITH_SLASH;

		if (request.isSecure()) {
			realm = Http.HTTPS_WITH_SLASH;
		}

		realm = realm.concat(request.getLocalName());

		if (exception.getCause() != null) {
			exception = (Exception)exception.getCause();
		}

		try {
			OAuthServlet.handleException(response, exception, realm, sendBody);
		}
		catch (Exception e) {
			throw new OAuthException(e);
		}
	}

	@Override
	public String randomizeToken(String token) {
		return DigesterUtil.digestHex(
			Digester.MD5, token, PwdGenerator.getPassword());
	}

	@Override
	public void validateOAuthMessage(
			OAuthMessage oAuthMessage, OAuthAccessor accessor)
		throws OAuthException {

		_oAuthValidator.validateOAuthMessage(oAuthMessage, accessor);
	}

	private static PortalCache<String, OAuthAccessor> _portalCache =
		MultiVMPoolUtil.getCache(V10aOAuth.class.getName());

	private OAuthValidator _oAuthValidator;

}