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

package com.liferay.meeting.webex.model.impl;

import com.liferay.meeting.webex.model.WebExSite;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing WebExSite in entity cache.
 *
 * @author Anant Singh
 * @see WebExSite
 * @generated
 */
public class WebExSiteCacheModel implements CacheModel<WebExSite>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", webExSiteId=");
		sb.append(webExSiteId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", apiURL=");
		sb.append(apiURL);
		sb.append(", login=");
		sb.append(login);
		sb.append(", password=");
		sb.append(password);
		sb.append(", partnerKey=");
		sb.append(partnerKey);
		sb.append(", siteKey=");
		sb.append(siteKey);
		sb.append("}");

		return sb.toString();
	}

	public WebExSite toEntityModel() {
		WebExSiteImpl webExSiteImpl = new WebExSiteImpl();

		if (uuid == null) {
			webExSiteImpl.setUuid(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setUuid(uuid);
		}

		webExSiteImpl.setWebExSiteId(webExSiteId);
		webExSiteImpl.setGroupId(groupId);
		webExSiteImpl.setCompanyId(companyId);
		webExSiteImpl.setUserId(userId);

		if (userName == null) {
			webExSiteImpl.setUserName(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			webExSiteImpl.setCreateDate(null);
		}
		else {
			webExSiteImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			webExSiteImpl.setModifiedDate(null);
		}
		else {
			webExSiteImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			webExSiteImpl.setName(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setName(name);
		}

		if (apiURL == null) {
			webExSiteImpl.setApiURL(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setApiURL(apiURL);
		}

		if (login == null) {
			webExSiteImpl.setLogin(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setLogin(login);
		}

		if (password == null) {
			webExSiteImpl.setPassword(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setPassword(password);
		}

		if (partnerKey == null) {
			webExSiteImpl.setPartnerKey(StringPool.BLANK);
		}
		else {
			webExSiteImpl.setPartnerKey(partnerKey);
		}

		webExSiteImpl.setSiteKey(siteKey);

		webExSiteImpl.resetOriginalValues();

		return webExSiteImpl;
	}

	public String uuid;
	public long webExSiteId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String apiURL;
	public String login;
	public String password;
	public String partnerKey;
	public long siteKey;
}