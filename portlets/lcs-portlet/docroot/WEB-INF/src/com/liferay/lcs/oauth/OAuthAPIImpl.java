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
import com.liferay.portal.kernel.util.Validator;

import org.scribe.builder.api.DefaultApi10a;

/**
 * @author Igor Beslic
 */
public class OAuthAPIImpl extends DefaultApi10a {

	@Override
	protected String getAccessTokenEndpoint() {
		if (Validator.isNull(_accessTokenEndpoint)) {
			_accessTokenEndpoint = OAuthUtil.buildURL(
				PortletPropsValues.OSB_LCS_PORTLET_HOST_NAME,
				Integer.parseInt(PortletPropsValues.OSB_LCS_PORTLET_HOST_PORT),
				"http",
				PortletPropsValues.OSB_LCS_PORTLET_OAUTH_ACCESS_TOKEN_URI);
		}

		return _accessTokenEndpoint;
	}

	@Override
	protected String getRequestTokenEndpoint() {
		if (Validator.isNull(_requestTokenEndpoint)) {
			_requestTokenEndpoint = OAuthUtil.buildURL(
				PortletPropsValues.OSB_LCS_PORTLET_HOST_NAME,
				Integer.parseInt(PortletPropsValues.OSB_LCS_PORTLET_HOST_PORT),
				"http",
				PortletPropsValues.OSB_LCS_PORTLET_OAUTH_REQUEST_TOKEN_URI);
		}

		return _requestTokenEndpoint;
	}

	private String _accessTokenEndpoint;
	private String _requestTokenEndpoint;

}