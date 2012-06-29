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

package com.liferay.saml.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.saml.model.SamlIdpSsoSession;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing SamlIdpSsoSession in entity cache.
 *
 * @author Mika Koivisto
 * @see SamlIdpSsoSession
 * @generated
 */
public class SamlIdpSsoSessionCacheModel implements CacheModel<SamlIdpSsoSession>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{samlIdpSsoSessionId=");
		sb.append(samlIdpSsoSessionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", samlIdpSsoSessionKey=");
		sb.append(samlIdpSsoSessionKey);
		sb.append("}");

		return sb.toString();
	}

	public SamlIdpSsoSession toEntityModel() {
		SamlIdpSsoSessionImpl samlIdpSsoSessionImpl = new SamlIdpSsoSessionImpl();

		samlIdpSsoSessionImpl.setSamlIdpSsoSessionId(samlIdpSsoSessionId);
		samlIdpSsoSessionImpl.setCompanyId(companyId);
		samlIdpSsoSessionImpl.setUserId(userId);

		if (userName == null) {
			samlIdpSsoSessionImpl.setUserName(StringPool.BLANK);
		}
		else {
			samlIdpSsoSessionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			samlIdpSsoSessionImpl.setCreateDate(null);
		}
		else {
			samlIdpSsoSessionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			samlIdpSsoSessionImpl.setModifiedDate(null);
		}
		else {
			samlIdpSsoSessionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (samlIdpSsoSessionKey == null) {
			samlIdpSsoSessionImpl.setSamlIdpSsoSessionKey(StringPool.BLANK);
		}
		else {
			samlIdpSsoSessionImpl.setSamlIdpSsoSessionKey(samlIdpSsoSessionKey);
		}

		samlIdpSsoSessionImpl.resetOriginalValues();

		return samlIdpSsoSessionImpl;
	}

	public long samlIdpSsoSessionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String samlIdpSsoSessionKey;
}