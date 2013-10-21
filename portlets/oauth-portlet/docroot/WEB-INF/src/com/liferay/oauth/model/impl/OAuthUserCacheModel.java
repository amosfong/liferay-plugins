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

package com.liferay.oauth.model.impl;

import com.liferay.oauth.model.OAuthUser;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing OAuthUser in entity cache.
 *
 * @author Ivica Cardic
 * @see OAuthUser
 * @generated
 */
public class OAuthUserCacheModel implements CacheModel<OAuthUser>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{oAuthUserId=");
		sb.append(oAuthUserId);
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
		sb.append(", oAuthApplicationId=");
		sb.append(oAuthApplicationId);
		sb.append(", accessToken=");
		sb.append(accessToken);
		sb.append(", accessSecret=");
		sb.append(accessSecret);
		sb.append("}");

		return sb.toString();
	}

	public OAuthUser toEntityModel() {
		OAuthUserImpl oAuthUserImpl = new OAuthUserImpl();

		oAuthUserImpl.setOAuthUserId(oAuthUserId);
		oAuthUserImpl.setCompanyId(companyId);
		oAuthUserImpl.setUserId(userId);

		if (userName == null) {
			oAuthUserImpl.setUserName(StringPool.BLANK);
		}
		else {
			oAuthUserImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			oAuthUserImpl.setCreateDate(null);
		}
		else {
			oAuthUserImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			oAuthUserImpl.setModifiedDate(null);
		}
		else {
			oAuthUserImpl.setModifiedDate(new Date(modifiedDate));
		}

		oAuthUserImpl.setOAuthApplicationId(oAuthApplicationId);

		if (accessToken == null) {
			oAuthUserImpl.setAccessToken(StringPool.BLANK);
		}
		else {
			oAuthUserImpl.setAccessToken(accessToken);
		}

		if (accessSecret == null) {
			oAuthUserImpl.setAccessSecret(StringPool.BLANK);
		}
		else {
			oAuthUserImpl.setAccessSecret(accessSecret);
		}

		oAuthUserImpl.resetOriginalValues();

		return oAuthUserImpl;
	}

	public long oAuthUserId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long oAuthApplicationId;
	public String accessToken;
	public String accessSecret;
}