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

import com.liferay.compat.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.license.util.LicenseManagerUtil;
import com.liferay.util.Encryptor;

import java.io.FileInputStream;
import java.io.InputStream;

import java.security.Key;
import java.security.KeyStore;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class KeyGeneratorImpl implements KeyGenerator {

	@Override
	public String getKey() {
		if (_key == null) {
			String serverId = getServerId();

			_key = DigesterUtil.digestHex(Digester.MD5, serverId);
		}

		return _key;
	}

	public void setKeyAlias(String keyAlias) {
		_keyAlias = keyAlias;
	}

	public void setKeyStorePassword(String keyStorePassword) {
		_keyStorePassword = keyStorePassword;
	}

	public void setKeyStorePath(String keyStorePath) {
		_keyStorePath = keyStorePath;
	}

	public void setKeyStoreType(String keyStoreType) {
		_keyStoreType = keyStoreType;
	}

	protected byte[] generateServerId() throws SystemException {
		try {
			Properties serverIdProperties = new Properties();

			serverIdProperties.put(
				"hostName", LicenseManagerUtil.getHostName());
			serverIdProperties.put(
				"ipAddresses",
				StringUtil.merge(LicenseManagerUtil.getIpAddresses()));
			serverIdProperties.put(
				"macAddresses",
				StringUtil.merge(LicenseManagerUtil.getMacAddresses()));
			serverIdProperties.put("salt", String.valueOf(UUID.randomUUID()));

			String serverIdPropertiesString = PropertiesUtil.toString(
				serverIdProperties);

			return Encryptor.encryptUnencoded(
				getGeneratorKey(),
				serverIdPropertiesString.getBytes(StringPool.UTF8));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected Key getGeneratorKey() throws Exception {
		KeyStore.ProtectionParameter protectionParameter =
			new KeyStore.PasswordProtection(_keyStorePassword.toCharArray());

		KeyStore keyStore = getKeyStore();

		KeyStore.SecretKeyEntry secretKeyEntry =
			(KeyStore.SecretKeyEntry)keyStore.getEntry(
				_keyAlias, protectionParameter);

		return secretKeyEntry.getSecretKey();
	}

	protected KeyStore getKeyStore() throws Exception {
		if (_keyStore != null) {
			return _keyStore;
		}

		InputStream inputStream = null;

		try {
			KeyStore keyStore = KeyStore.getInstance(_keyStoreType);

			int index = _keyStorePath.indexOf("classpath:");

			if (index != -1) {
				Class<?> clazz = getClass();

				inputStream = clazz.getResourceAsStream(
					_keyStorePath.substring(index + 10));
			}
			else {
				inputStream = new FileInputStream(_keyStorePath);
			}

			keyStore.load(inputStream, _keyStorePassword.toCharArray());

			_keyStore = keyStore;

			return _keyStore;
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	protected String getServerId() {
		try {
			byte[] serverIdBytes = (byte[])invokePortalClassMethod(
				"com.liferay.portal.license.util.LicenseUtil",
				"getServerIdBytes");

			if (serverIdBytes.length == 0) {
				serverIdBytes = generateServerId();

				writeServerProperties(serverIdBytes);
			}

			return Arrays.toString(serverIdBytes);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return StringPool.BLANK;
	}

	protected void writeServerProperties(byte[] serverIdBytes)
		throws Exception {

		invokePortalClassMethod(
			"com.liferay.portal.license.util.LicenseUtil",
			"writeServerProperties", serverIdBytes);
	}

	private Object invokePortalClassMethod(
			String clazz, String method, Object... parameterTypes)
		throws Exception {

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> portalClass = classLoader.loadClass(clazz);

		Class<?>[] parameterTypesClasses = null;

		if (parameterTypes != null) {
			parameterTypesClasses = new Class<?>[parameterTypes.length];

			for (int i = 0; i < parameterTypes.length; i++) {
				parameterTypesClasses[i] = parameterTypes[i].getClass();
			}
		}

		MethodKey methodKey = new MethodKey(
			portalClass, method, parameterTypesClasses);

		return PortalClassInvoker.invoke(methodKey, parameterTypes);
	}

	private static Log _log = LogFactoryUtil.getLog(KeyGeneratorImpl.class);

	private String _key;
	private String _keyAlias;
	private KeyStore _keyStore;
	private String _keyStorePassword;
	private String _keyStorePath;
	private String _keyStoreType;

}