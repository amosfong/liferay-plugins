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
import com.liferay.portal.kernel.util.StringBundler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.security.KeyStore;
import java.security.KeyStoreException;

/**
 * @author Mika Koivisto
 */
public class FileSystemKeyStoreManagerImpl extends BaseKeyStoreManagerImpl {

	public FileSystemKeyStoreManagerImpl() {
		init();
	}

	@Override
	public KeyStore getKeyStore() {
		if (_keyStore == null) {
			init();
		}

		return _keyStore;
	}

	@Override
	public void saveKeyStore(KeyStore keyStore) throws Exception {
		String samlKeyStorePath = getSamlKeyStorePath();
		String samlKeyStorePassword = getSamlKeyStorePassword();

		keyStore.store(
			new FileOutputStream(samlKeyStorePath),
			samlKeyStorePassword.toCharArray());
	}

	protected void init() {
		InputStream inputStream = null;

		String samlKeyStoreType = getSamlKeyStoreType();

		try {
			_keyStore = KeyStore.getInstance(samlKeyStoreType);
		}
		catch (KeyStoreException kse) {
			_log.error(
				"Unable instantiate keystore with type " + samlKeyStoreType,
				kse);

			return;
		}

		String samlKeyStorePath = getSamlKeyStorePath();

		if (samlKeyStorePath.startsWith("classpath:")) {
			Class<?> clazz = getClass();

			inputStream = clazz.getResourceAsStream(
				samlKeyStorePath.substring(10));
		}
		else {
			try {
				inputStream = new FileInputStream(samlKeyStorePath);
			}
			catch (FileNotFoundException fnfe) {
				try {
					String samlKeyStorePassword = getSamlKeyStorePassword();

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
			String samlKeyStorePassword = getSamlKeyStorePassword();

			_keyStore.load(inputStream, samlKeyStorePassword.toCharArray());
		}
		catch (Exception e) {
			_log.error("Unable to load keystore", e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		FileSystemKeyStoreManagerImpl.class);

	private KeyStore _keyStore;

}