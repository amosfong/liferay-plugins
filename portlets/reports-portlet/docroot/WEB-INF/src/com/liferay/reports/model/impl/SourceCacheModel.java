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

package com.liferay.reports.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.reports.model.Source;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Source in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Source
 * @generated
 */
public class SourceCacheModel implements CacheModel<Source>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", sourceId=");
		sb.append(sourceId);
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
		sb.append(", driverClassName=");
		sb.append(driverClassName);
		sb.append(", driverUrl=");
		sb.append(driverUrl);
		sb.append(", driverUserName=");
		sb.append(driverUserName);
		sb.append(", driverPassword=");
		sb.append(driverPassword);
		sb.append("}");

		return sb.toString();
	}

	public Source toEntityModel() {
		SourceImpl sourceImpl = new SourceImpl();

		if (uuid == null) {
			sourceImpl.setUuid(StringPool.BLANK);
		}
		else {
			sourceImpl.setUuid(uuid);
		}

		sourceImpl.setSourceId(sourceId);
		sourceImpl.setGroupId(groupId);
		sourceImpl.setCompanyId(companyId);
		sourceImpl.setUserId(userId);

		if (userName == null) {
			sourceImpl.setUserName(StringPool.BLANK);
		}
		else {
			sourceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			sourceImpl.setCreateDate(null);
		}
		else {
			sourceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			sourceImpl.setModifiedDate(null);
		}
		else {
			sourceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			sourceImpl.setName(StringPool.BLANK);
		}
		else {
			sourceImpl.setName(name);
		}

		if (driverClassName == null) {
			sourceImpl.setDriverClassName(StringPool.BLANK);
		}
		else {
			sourceImpl.setDriverClassName(driverClassName);
		}

		if (driverUrl == null) {
			sourceImpl.setDriverUrl(StringPool.BLANK);
		}
		else {
			sourceImpl.setDriverUrl(driverUrl);
		}

		if (driverUserName == null) {
			sourceImpl.setDriverUserName(StringPool.BLANK);
		}
		else {
			sourceImpl.setDriverUserName(driverUserName);
		}

		if (driverPassword == null) {
			sourceImpl.setDriverPassword(StringPool.BLANK);
		}
		else {
			sourceImpl.setDriverPassword(driverPassword);
		}

		sourceImpl.resetOriginalValues();

		return sourceImpl;
	}

	public String uuid;
	public long sourceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String driverClassName;
	public String driverUrl;
	public String driverUserName;
	public String driverPassword;
}