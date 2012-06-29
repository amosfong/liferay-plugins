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

import com.liferay.reports.model.Definition;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Definition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Definition
 * @generated
 */
public class DefinitionCacheModel implements CacheModel<Definition>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", definitionId=");
		sb.append(definitionId);
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
		sb.append(", description=");
		sb.append(description);
		sb.append(", sourceId=");
		sb.append(sourceId);
		sb.append(", reportName=");
		sb.append(reportName);
		sb.append(", reportParameters=");
		sb.append(reportParameters);
		sb.append("}");

		return sb.toString();
	}

	public Definition toEntityModel() {
		DefinitionImpl definitionImpl = new DefinitionImpl();

		if (uuid == null) {
			definitionImpl.setUuid(StringPool.BLANK);
		}
		else {
			definitionImpl.setUuid(uuid);
		}

		definitionImpl.setDefinitionId(definitionId);
		definitionImpl.setGroupId(groupId);
		definitionImpl.setCompanyId(companyId);
		definitionImpl.setUserId(userId);

		if (userName == null) {
			definitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			definitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			definitionImpl.setCreateDate(null);
		}
		else {
			definitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			definitionImpl.setModifiedDate(null);
		}
		else {
			definitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			definitionImpl.setName(StringPool.BLANK);
		}
		else {
			definitionImpl.setName(name);
		}

		if (description == null) {
			definitionImpl.setDescription(StringPool.BLANK);
		}
		else {
			definitionImpl.setDescription(description);
		}

		definitionImpl.setSourceId(sourceId);

		if (reportName == null) {
			definitionImpl.setReportName(StringPool.BLANK);
		}
		else {
			definitionImpl.setReportName(reportName);
		}

		if (reportParameters == null) {
			definitionImpl.setReportParameters(StringPool.BLANK);
		}
		else {
			definitionImpl.setReportParameters(reportParameters);
		}

		definitionImpl.resetOriginalValues();

		return definitionImpl;
	}

	public String uuid;
	public long definitionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public long sourceId;
	public String reportName;
	public String reportParameters;
}