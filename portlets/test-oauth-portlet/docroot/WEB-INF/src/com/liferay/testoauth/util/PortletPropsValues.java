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

package com.liferay.testoauth.util;

import com.liferay.util.portlet.PortletProps;

/**
 * @author Peter Shin
 */
public class PortletPropsValues {

	public static final String OAUTH_ACCESS_TOKEN = PortletProps.get(
		PortletPropsKeys.OAUTH_ACCESS_TOKEN);

	public static final String OAUTH_AUTHORIZE = PortletProps.get(
		PortletPropsKeys.OAUTH_AUTHORIZE);

	public static final String OAUTH_REQUEST_TOKEN = PortletProps.get(
		PortletPropsKeys.OAUTH_REQUEST_TOKEN);

	public static final String PORTAL_URL = PortletProps.get(
		PortletPropsKeys.PORTAL_URL);

}