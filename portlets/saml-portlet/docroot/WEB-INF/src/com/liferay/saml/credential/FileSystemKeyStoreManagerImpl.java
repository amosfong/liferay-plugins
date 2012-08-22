/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.saml.util.PortletPropsKeys;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.security.KeyStore;
import java.security.KeyStoreException;

/**
 * @author Mika Koivisto
 */
public class FileSystemKeyStoreManagerImpl implements KeyStoreManager {

	public FileSystemKeyStoreManagerImpl() {
		init();
	}

	public KeyStore getKeyStore() {
		if (_keyStore == null) {
			init();
		}

		return _keyStore;
	}

	protected void init() {
		InputStream inputStream = null;

		String liferayHome = PropsUtil.get(PropsKeys.LIFERAY_HOME);

		String defaultSamlKeyStorePath = liferayHome.concat(
			"/data/keystore.jks");

		String samlKeyStorePath = GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_PATH),
			defaultSamlKeyStorePath);

		String samlKeyStoreType = GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_TYPE), "jks");

		try {
			_keyStore = KeyStore.getInstance(samlKeyStoreType);
		}
		catch (KeyStoreException kse) {
			_log.error(
				"Unable instantiate keystore with type " + samlKeyStoreType,
				kse);

			return;
		}

		String samlKeyStorePassword = GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_PASSWORD), "liferay");

		if (samlKeyStorePath.startsWith("classpath:")) {
			inputStream = FileSystemKeyStoreManagerImpl.class.getResourceAsStream(
				samlKeyStorePath.substring(10));
		}
		else {
			try {
				inputStream = new FileInputStream(samlKeyStorePath);
			}
			catch (FileNotFoundException fnfe) {
				try {
					if (_log.isWarnEnabled()) {
						StringBundler sb = new StringBundler(5);

						sb.append("Keystore ");
						sb.append(samlKeyStorePath);
						sb.append(" not found. Creating a new default ");
						sb.append("keystore with password ");
						sb.append(samlKeyStorePassword);

						_log.warn(sb.toString());
					}

					_keyStore.load(null, null);

					_keyStore.store(
						new FileOutputStream(samlKeyStorePath),
						samlKeyStorePassword.toCharArray());

					inputStream = new FileInputStream(samlKeyStorePath);
				}
				catch (Exception e) {
					_log.error(
						"Unable to create keystore " + samlKeyStorePath, e);

					return;
				}
			}
		}

		try {
			_keyStore.load(inputStream, samlKeyStorePassword.toCharArray());
		}
		catch (Exception e) {
			_log.error("Unable to load keystore", e);
		}
	}

	public void saveKeyStore(KeyStore keyStore) throws Exception {
		String samlKeyStorePassword = GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_PASSWORD), "liferay");
		String liferayHome = PropsUtil.get(PropsKeys.LIFERAY_HOME);

		String defaultSamlKeyStorePath = liferayHome.concat(
			"/data/keystore.jks");

		String samlKeyStorePath = GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_PATH),
			defaultSamlKeyStorePath);

		keyStore.store(
			new FileOutputStream(samlKeyStorePath),
			samlKeyStorePassword.toCharArray());
	}

	private static Log _log = LogFactoryUtil.getLog(FileSystemKeyStoreManagerImpl.class);

	private KeyStore _keyStore;

}