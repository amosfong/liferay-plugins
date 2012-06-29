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

package com.liferay.reports.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.reports.service.SourceLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class SourceClp extends BaseModelImpl<Source> implements Source {
	public SourceClp() {
	}

	public Class<?> getModelClass() {
		return Source.class;
	}

	public String getModelClassName() {
		return Source.class.getName();
	}

	public long getPrimaryKey() {
		return _sourceId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSourceId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_sourceId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("sourceId", getSourceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("driverClassName", getDriverClassName());
		attributes.put("driverUrl", getDriverUrl());
		attributes.put("driverUserName", getDriverUserName());
		attributes.put("driverPassword", getDriverPassword());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long sourceId = (Long)attributes.get("sourceId");

		if (sourceId != null) {
			setSourceId(sourceId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String driverClassName = (String)attributes.get("driverClassName");

		if (driverClassName != null) {
			setDriverClassName(driverClassName);
		}

		String driverUrl = (String)attributes.get("driverUrl");

		if (driverUrl != null) {
			setDriverUrl(driverUrl);
		}

		String driverUserName = (String)attributes.get("driverUserName");

		if (driverUserName != null) {
			setDriverUserName(driverUserName);
		}

		String driverPassword = (String)attributes.get("driverPassword");

		if (driverPassword != null) {
			setDriverPassword(driverPassword);
		}
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSourceId() {
		return _sourceId;
	}

	public void setSourceId(long sourceId) {
		_sourceId = sourceId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getName(), languageId,
			useDefault);
	}

	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	public void setName(String name) {
		_name = name;
	}

	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(LocalizationUtil.updateLocalization(getName(), "Name",
					name, languageId, defaultLanguageId));
		}
		else {
			setName(LocalizationUtil.removeLocalization(getName(), "Name",
					languageId));
		}
	}

	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(portalClassLoader);
			}

			setName(LocalizationUtil.updateLocalization(nameMap, getName(),
					"Name", LocaleUtil.toLanguageId(defaultLocale)));
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	public String getDriverClassName() {
		return _driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		_driverClassName = driverClassName;
	}

	public String getDriverUrl() {
		return _driverUrl;
	}

	public void setDriverUrl(String driverUrl) {
		_driverUrl = driverUrl;
	}

	public String getDriverUserName() {
		return _driverUserName;
	}

	public void setDriverUserName(String driverUserName) {
		_driverUserName = driverUserName;
	}

	public String getDriverPassword() {
		return _driverPassword;
	}

	public void setDriverPassword(String driverPassword) {
		_driverPassword = driverPassword;
	}

	public java.lang.String[] getAttachmentsFiles() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getAttachmentsDir() {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getSourceRemoteModel() {
		return _sourceRemoteModel;
	}

	public void setSourceRemoteModel(BaseModel<?> sourceRemoteModel) {
		_sourceRemoteModel = sourceRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SourceLocalServiceUtil.addSource(this);
		}
		else {
			SourceLocalServiceUtil.updateSource(this);
		}
	}

	@Override
	public Source toEscapedModel() {
		return (Source)Proxy.newProxyInstance(Source.class.getClassLoader(),
			new Class[] { Source.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SourceClp clone = new SourceClp();

		clone.setUuid(getUuid());
		clone.setSourceId(getSourceId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setDriverClassName(getDriverClassName());
		clone.setDriverUrl(getDriverUrl());
		clone.setDriverUserName(getDriverUserName());
		clone.setDriverPassword(getDriverPassword());

		return clone;
	}

	public int compareTo(Source source) {
		long primaryKey = source.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		SourceClp source = null;

		try {
			source = (SourceClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = source.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", sourceId=");
		sb.append(getSourceId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", driverClassName=");
		sb.append(getDriverClassName());
		sb.append(", driverUrl=");
		sb.append(getDriverUrl());
		sb.append(", driverUserName=");
		sb.append(getDriverUserName());
		sb.append(", driverPassword=");
		sb.append(getDriverPassword());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.reports.model.Source");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sourceId</column-name><column-value><![CDATA[");
		sb.append(getSourceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>driverClassName</column-name><column-value><![CDATA[");
		sb.append(getDriverClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>driverUrl</column-name><column-value><![CDATA[");
		sb.append(getDriverUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>driverUserName</column-name><column-value><![CDATA[");
		sb.append(getDriverUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>driverPassword</column-name><column-value><![CDATA[");
		sb.append(getDriverPassword());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private String _uuid;
	private long _sourceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _nameCurrentLanguageId;
	private String _driverClassName;
	private String _driverUrl;
	private String _driverUserName;
	private String _driverPassword;
	private BaseModel<?> _sourceRemoteModel;
}