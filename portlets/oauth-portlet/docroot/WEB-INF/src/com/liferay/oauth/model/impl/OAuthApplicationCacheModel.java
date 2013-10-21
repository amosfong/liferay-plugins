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

import com.liferay.oauth.model.OAuthApplication;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing OAuthApplication in entity cache.
 *
 * @author Ivica Cardic
 * @see OAuthApplication
 * @generated
 */
public class OAuthApplicationCacheModel implements CacheModel<OAuthApplication>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{oAuthApplicationId=");
		sb.append(oAuthApplicationId);
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
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", consumerKey=");
		sb.append(consumerKey);
		sb.append(", consumerSecret=");
		sb.append(consumerSecret);
		sb.append(", accessLevel=");
		sb.append(accessLevel);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", callbackURI=");
		sb.append(callbackURI);
		sb.append(", websiteURL=");
		sb.append(websiteURL);
		sb.append("}");

		return sb.toString();
	}

	public OAuthApplication toEntityModel() {
		OAuthApplicationImpl oAuthApplicationImpl = new OAuthApplicationImpl();

		oAuthApplicationImpl.setOAuthApplicationId(oAuthApplicationId);
		oAuthApplicationImpl.setCompanyId(companyId);
		oAuthApplicationImpl.setUserId(userId);

		if (userName == null) {
			oAuthApplicationImpl.setUserName(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			oAuthApplicationImpl.setCreateDate(null);
		}
		else {
			oAuthApplicationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			oAuthApplicationImpl.setModifiedDate(null);
		}
		else {
			oAuthApplicationImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			oAuthApplicationImpl.setName(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setName(name);
		}

		if (description == null) {
			oAuthApplicationImpl.setDescription(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setDescription(description);
		}

		if (consumerKey == null) {
			oAuthApplicationImpl.setConsumerKey(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setConsumerKey(consumerKey);
		}

		if (consumerSecret == null) {
			oAuthApplicationImpl.setConsumerSecret(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setConsumerSecret(consumerSecret);
		}

		oAuthApplicationImpl.setAccessLevel(accessLevel);
		oAuthApplicationImpl.setLogoId(logoId);

		if (callbackURI == null) {
			oAuthApplicationImpl.setCallbackURI(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setCallbackURI(callbackURI);
		}

		if (websiteURL == null) {
			oAuthApplicationImpl.setWebsiteURL(StringPool.BLANK);
		}
		else {
			oAuthApplicationImpl.setWebsiteURL(websiteURL);
		}

		oAuthApplicationImpl.resetOriginalValues();

		return oAuthApplicationImpl;
	}

	public long oAuthApplicationId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public String consumerKey;
	public String consumerSecret;
	public int accessLevel;
	public long logoId;
	public String callbackURI;
	public String websiteURL;
}