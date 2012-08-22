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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portlet.documentlibrary.NoSuchFileException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.saml.util.PortletPropsKeys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * @author Mika Koivisto
 */
public class DLKeyStoreManagerImpl implements KeyStoreManager {

	public KeyStore getKeyStore() {
		long companyId = CompanyThreadLocal.getCompanyId();

		String samlKeyStoreType = GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_TYPE), "jks");

		KeyStore keyStore = null;

		try {
			keyStore = KeyStore.getInstance(samlKeyStoreType);
		}
		catch (KeyStoreException kse) {
			_log.error(
				"Unable instantiate keystore with type " + samlKeyStoreType,
				kse);

			return null;
		}

		String samlKeyStorePassword = GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_PASSWORD), "liferay");

		try {
			InputStream inputStream = DLStoreUtil.getFileAsStream(
				companyId, CompanyConstants.SYSTEM, _SAML_KEYSTORE_PATH);

			keyStore.load(inputStream, samlKeyStorePassword.toCharArray());
		}
		catch (NoSuchFileException nsfe) {
			try {
				keyStore.load(null, null);
			}
			catch (Exception e) {
				_log.error("Unable to load keystore ", e);
			}
		}
		catch (Exception e) {
			_log.error("Unable to load keystore ", e);
		}

		return keyStore;
	}

	public void saveKeyStore(KeyStore keyStore) throws Exception {
		String samlKeyStorePassword = GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_PASSWORD), "liferay");

		File tempFile = FileUtil.createTempFile("jks");

		try {
			keyStore.store(
				new FileOutputStream(tempFile),
				samlKeyStorePassword.toCharArray());

			long companyId = CompanyThreadLocal.getCompanyId();

			if (!DLStoreUtil.hasDirectory(
					companyId,CompanyConstants.SYSTEM, _SAML_KEYSTORE_DIR)) {

				DLStoreUtil.addDirectory(
					companyId, CompanyConstants.SYSTEM, _SAML_KEYSTORE_DIR);
			}

			if (DLStoreUtil.hasFile(
					companyId, CompanyConstants.SYSTEM, _SAML_KEYSTORE_PATH)) {

				DLStoreUtil.deleteFile(
					companyId, CompanyConstants.SYSTEM, _SAML_KEYSTORE_PATH);
			}

			DLStoreUtil.addFile(
				companyId, CompanyConstants.SYSTEM, _SAML_KEYSTORE_PATH,
				new FileInputStream(tempFile));
		}
		finally {
			tempFile.delete();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DLKeyStoreManagerImpl.class);

	private static final String _SAML_KEYSTORE_DIR = "/saml";
	private static final String _SAML_KEYSTORE_PATH = "/saml/keystore.jks";

}
