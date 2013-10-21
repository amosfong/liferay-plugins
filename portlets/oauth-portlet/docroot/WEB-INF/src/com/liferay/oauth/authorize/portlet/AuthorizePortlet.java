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

package com.liferay.oauth.authorize.portlet;

import com.liferay.oauth.util.OAuthAccessor;
import com.liferay.oauth.util.OAuthConsumer;
import com.liferay.oauth.util.OAuthMessage;
import com.liferay.oauth.util.OAuthUtil;
import com.liferay.oauth.util.OAuthWebKeys;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Ivica Cardic
 */
public class AuthorizePortlet extends MVCPortlet {

	public void authorize(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		OAuthMessage oAuthMessage = OAuthUtil.getOAuthMessage(
			actionRequest, null);

		OAuthAccessor oAuthAccessor = OAuthUtil.getOAuthAccessor(oAuthMessage);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		OAuthUtil.authorize(
			oAuthAccessor, serviceContext.getUserId(), serviceContext);

		String oAuthCallbackURL = ParamUtil.getString(
			actionRequest, net.oauth.OAuth.OAUTH_CALLBACK);

		OAuthConsumer oAuthConsumer = oAuthAccessor.getOAuthConsumer();

		String callbackURL = oAuthConsumer.getCallbackURL();

		if (Validator.isNull(oAuthCallbackURL) &&
			Validator.isNotNull(callbackURL)) {

			oAuthCallbackURL = callbackURL;
		}

		String requestToken = oAuthAccessor.getRequestToken();

		String oAuthVerifier = DigesterUtil.digestHex(
			Digester.MD5, oAuthCallbackURL + System.nanoTime() + requestToken);

		if (Validator.isNull(oAuthCallbackURL)) {
			actionRequest.setAttribute(
				OAuthWebKeys.OAUTH_ACCESSOR, oAuthAccessor);
			actionRequest.setAttribute(
				OAuthWebKeys.OAUTH_VERIFIER, oAuthVerifier);
		}
		else {
			if (requestToken != null) {
				oAuthCallbackURL = OAuthUtil.addParameters(
					oAuthCallbackURL, net.oauth.OAuth.OAUTH_TOKEN, requestToken,
					net.oauth.OAuth.OAUTH_VERIFIER, oAuthVerifier);
			}

			actionResponse.sendRedirect(oAuthCallbackURL);
		}
	}

}