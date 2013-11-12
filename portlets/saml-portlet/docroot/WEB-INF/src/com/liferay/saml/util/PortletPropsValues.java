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

package com.liferay.saml.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * @author Mika Koivisto
 */
public class PortletPropsValues {

	public static final String SAML_KEYSTORE_MANAGER_IMPL = PortletProps.get(
		PortletPropsKeys.SAML_KEYSTORE_MANAGER_IMPL);

	public static final long SAML_METADATA_MAX_REFRESH_DELAY =
		GetterUtil.getLong(
			PortletProps.get(
				PortletPropsKeys.SAML_METADATA_MAX_REFRESH_DELAY), 14400000);

	public static final int SAML_METADATA_MIN_REFRESH_DELAY =
		GetterUtil.getInteger(
			PortletProps.get(
				PortletPropsKeys.SAML_METADATA_MIN_REFRESH_DELAY), 300000);

	public static final int SAML_REPLAY_CACHE_DURATION = GetterUtil.getInteger(
		PortletProps.get(PortletPropsKeys.SAML_REPLAY_CACHE_DURATION), 3600000);

}