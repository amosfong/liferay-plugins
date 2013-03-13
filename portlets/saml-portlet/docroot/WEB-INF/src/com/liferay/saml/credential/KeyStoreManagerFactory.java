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

package com.liferay.saml.credential;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.saml.util.PortletPropsValues;

/**
 * @author Mika Koivisto
 */
public class KeyStoreManagerFactory {

	public static KeyStoreManager getInstance() {
		if (_keyStoreManager != null) {
			return _keyStoreManager;
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		try {
			_keyStoreManager = (KeyStoreManager)InstanceFactory.newInstance(
				classLoader, PortletPropsValues.SAML_KEYSTORE_MANAGER_IMPL);
		}
		catch (Exception e) {
			_log.error(
				"Unable to load keystore manager class " +
					PortletPropsValues.SAML_KEYSTORE_MANAGER_IMPL,
				e);
		}

		return _keyStoreManager;
	}

	private static Log _log = LogFactoryUtil.getLog(
		KeyStoreManagerFactory.class);

	private static KeyStoreManager _keyStoreManager;

}