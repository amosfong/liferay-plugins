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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.saml.util.PortletPropsKeys;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseKeyStoreManagerImpl implements KeyStoreManager {

	protected long getCompanyId() {
		return CompanyThreadLocal.getCompanyId();
	}

	protected String getDefaultSamlKeyStorePath() {
		String liferayHome = PropsUtil.get(PropsKeys.LIFERAY_HOME);

		return liferayHome.concat("/data/keystore.jks");
	}

	protected String getSamlKeyStorePassword() {
		return GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_PASSWORD), "liferay");
	}

	protected String getSamlKeyStorePath() {
		return GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_PATH),
			getDefaultSamlKeyStorePath());
	}

	protected String getSamlKeyStoreType() {
		return GetterUtil.getString(
			PropsUtil.get(PortletPropsKeys.SAML_KEYSTORE_TYPE), "jks");
	}

}