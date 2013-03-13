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

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.CompanyThreadLocal;

/**
 * @author Mika Koivisto
 */
public class PortletPrefsPropsUtil {

	public static boolean getBoolean(String key) {
		return GetterUtil.getBoolean(getString(key));
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		return GetterUtil.getBoolean(getString(key), defaultValue);
	}

	public static int getInteger(String key) {
		return GetterUtil.getInteger(getString(key));
	}

	public static int getInteger(String key, int defaultValue) {
		return GetterUtil.getInteger(getString(key), defaultValue);
	}

	public static long getLong(String key) {
		return GetterUtil.getLong(getString(key));
	}

	public static long getLong(String key, long defaultValue) {
		return GetterUtil.getLong(getString(key), defaultValue);
	}

	public static String getString(String key) {
		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			return PrefsPropsUtil.getString(companyId, key, PropsUtil.get(key));
		}
		catch (Exception e) {
		}

		return PropsUtil.get(key);
	}

	public static String getString(String key, Filter filter) {
		String selector = filter.getSelectors()[0];

		String value = getString(key.concat("[").concat(selector).concat("]"));

		if (Validator.isNotNull(value)) {
			return value;
		}

		return PropsUtil.get(key, filter);
	}

	public static String getString(String key, String defaultValue) {
		String value = getString(key);

		if (Validator.isNull(value)) {
			return defaultValue;
		}

		return value;
	}

}