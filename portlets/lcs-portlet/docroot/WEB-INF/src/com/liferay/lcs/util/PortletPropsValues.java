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

package com.liferay.lcs.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Igor Beslic
 */
public class PortletPropsValues {

	public static final String OSB_LCS_PORTLET_HOST_NAME = PortletProps.get(
		PortletPropsKeys.OSB_LCS_PORTLET_HOST_NAME);

	public static final String OSB_LCS_PORTLET_HOST_PORT = PortletProps.get(
		PortletPropsKeys.OSB_LCS_PORTLET_HOST_PORT);

	public static final String OSB_LCS_PORTLET_OAUTH_ACCESS_TOKEN_URI =
		PortletProps.get(
			PortletPropsKeys.OSB_LCS_PORTLET_OAUTH_ACCESS_TOKEN_URI);

	public static final String OSB_LCS_PORTLET_OAUTH_AUTHORIZE_URI =
		PortletProps.get(PortletPropsKeys.OSB_LCS_PORTLET_OAUTH_AUTHORIZE_URI);

	public static final String OSB_LCS_PORTLET_OAUTH_CONSUMER_KEY =
		PortletProps.get(PortletPropsKeys.OSB_LCS_PORTLET_OAUTH_CONSUMER_KEY);

	public static final String OSB_LCS_PORTLET_OAUTH_CONSUMER_SECRET =
		PortletProps.get(
			PortletPropsKeys.OSB_LCS_PORTLET_OAUTH_CONSUMER_SECRET);

	public static final String OSB_LCS_PORTLET_OAUTH_REQUEST_TOKEN_URI =
		PortletProps.get(
			PortletPropsKeys.OSB_LCS_PORTLET_OAUTH_REQUEST_TOKEN_URI);

}