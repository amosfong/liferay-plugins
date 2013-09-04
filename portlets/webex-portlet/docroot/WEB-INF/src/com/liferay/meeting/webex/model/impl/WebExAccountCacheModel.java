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

import com.liferay.meeting.webex.model.WebExAccount;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing WebExAccount in entity cache.
 *
 * @author Anant Singh
 * @see WebExAccount
 * @generated
 */
public class WebExAccountCacheModel implements CacheModel<WebExAccount>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", webExAccountId=");
		sb.append(webExAccountId);
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
		sb.append(", webExSiteId=");
		sb.append(webExSiteId);
		sb.append(", login=");
		sb.append(login);
		sb.append(", password=");
		sb.append(password);
		sb.append("}");

		return sb.toString();
	}

	public WebExAccount toEntityModel() {
		WebExAccountImpl webExAccountImpl = new WebExAccountImpl();

		if (uuid == null) {
			webExAccountImpl.setUuid(StringPool.BLANK);
		}
		else {
			webExAccountImpl.setUuid(uuid);
		}

		webExAccountImpl.setWebExAccountId(webExAccountId);
		webExAccountImpl.setGroupId(groupId);
		webExAccountImpl.setCompanyId(companyId);
		webExAccountImpl.setUserId(userId);

		if (userName == null) {
			webExAccountImpl.setUserName(StringPool.BLANK);
		}
		else {
			webExAccountImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			webExAccountImpl.setCreateDate(null);
		}
		else {
			webExAccountImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			webExAccountImpl.setModifiedDate(null);
		}
		else {
			webExAccountImpl.setModifiedDate(new Date(modifiedDate));
		}

		webExAccountImpl.setWebExSiteId(webExSiteId);

		if (login == null) {
			webExAccountImpl.setLogin(StringPool.BLANK);
		}
		else {
			webExAccountImpl.setLogin(login);
		}

		if (password == null) {
			webExAccountImpl.setPassword(StringPool.BLANK);
		}
		else {
			webExAccountImpl.setPassword(password);
		}

		webExAccountImpl.resetOriginalValues();

		return webExAccountImpl;
	}

	public String uuid;
	public long webExAccountId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long webExSiteId;
	public String login;
	public String password;
}