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

/**
 * @author Mika Koivisto
 */
public interface PortletWebKeys {

	public static final String FORCE_REAUHENTICATION = "FORCE_REAUTHENTICATION";

	public static final String SAML_SESSION_KEEP_ALIVE_URLS =
		"SAML_SESSION_KEEP_ALIVE_URLS";

	public static final String SAML_SLO_CONTEXT = "SAML_SLO_CONTEXT";

	public static final String SAML_SLO_REQUEST_INFO = "SAML_SLO_REQUEST_INFO";

	public static final String SAML_SP_ATTRIBUTES = "SAML_SP_ATTRIBUTES";

	public static final String SAML_SP_NAME_ID_FORMAT =
		"SAML_SP_NAME_ID_FORMAT";

	public static final String SAML_SP_NAME_ID_VALUE = "SAML_SP_NAME_ID_VALUE";

	public static final String SAML_SP_SESSION_KEY = "SAML_SP_SESSION_KEY";

	public static final String SAML_SSO_REQUEST_CONTEXT =
		"SAML_SSO_REQUEST_CONTEXT";

	public static final String SAML_SSO_SESSION_ID = "SAML_SSO_SESSION_ID";

}