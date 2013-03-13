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

package com.liferay.reports.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Source}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Source
 * @generated
 */
public class SourceWrapper implements Source, ModelWrapper<Source> {
	public SourceWrapper(Source source) {
		_source = source;
	}

	public Class<?> getModelClass() {
		return Source.class;
	}

	public String getModelClassName() {
		return Source.class.getName();
	}

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

	/**
	* Returns the primary key of this source.
	*
	* @return the primary key of this source
	*/
	public long getPrimaryKey() {
		return _source.getPrimaryKey();
	}

	/**
	* Sets the primary key of this source.
	*
	* @param primaryKey the primary key of this source
	*/
	public void setPrimaryKey(long primaryKey) {
		_source.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this source.
	*
	* @return the uuid of this source
	*/
	public java.lang.String getUuid() {
		return _source.getUuid();
	}

	/**
	* Sets the uuid of this source.
	*
	* @param uuid the uuid of this source
	*/
	public void setUuid(java.lang.String uuid) {
		_source.setUuid(uuid);
	}

	/**
	* Returns the source ID of this source.
	*
	* @return the source ID of this source
	*/
	public long getSourceId() {
		return _source.getSourceId();
	}

	/**
	* Sets the source ID of this source.
	*
	* @param sourceId the source ID of this source
	*/
	public void setSourceId(long sourceId) {
		_source.setSourceId(sourceId);
	}

	/**
	* Returns the group ID of this source.
	*
	* @return the group ID of this source
	*/
	public long getGroupId() {
		return _source.getGroupId();
	}

	/**
	* Sets the group ID of this source.
	*
	* @param groupId the group ID of this source
	*/
	public void setGroupId(long groupId) {
		_source.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this source.
	*
	* @return the company ID of this source
	*/
	public long getCompanyId() {
		return _source.getCompanyId();
	}

	/**
	* Sets the company ID of this source.
	*
	* @param companyId the company ID of this source
	*/
	public void setCompanyId(long companyId) {
		_source.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this source.
	*
	* @return the user ID of this source
	*/
	public long getUserId() {
		return _source.getUserId();
	}

	/**
	* Sets the user ID of this source.
	*
	* @param userId the user ID of this source
	*/
	public void setUserId(long userId) {
		_source.setUserId(userId);
	}

	/**
	* Returns the user uuid of this source.
	*
	* @return the user uuid of this source
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _source.getUserUuid();
	}

	/**
	* Sets the user uuid of this source.
	*
	* @param userUuid the user uuid of this source
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_source.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this source.
	*
	* @return the user name of this source
	*/
	public java.lang.String getUserName() {
		return _source.getUserName();
	}

	/**
	* Sets the user name of this source.
	*
	* @param userName the user name of this source
	*/
	public void setUserName(java.lang.String userName) {
		_source.setUserName(userName);
	}

	/**
	* Returns the create date of this source.
	*
	* @return the create date of this source
	*/
	public java.util.Date getCreateDate() {
		return _source.getCreateDate();
	}

	/**
	* Sets the create date of this source.
	*
	* @param createDate the create date of this source
	*/
	public void setCreateDate(java.util.Date createDate) {
		_source.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this source.
	*
	* @return the modified date of this source
	*/
	public java.util.Date getModifiedDate() {
		return _source.getModifiedDate();
	}

	/**
	* Sets the modified date of this source.
	*
	* @param modifiedDate the modified date of this source
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_source.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this source.
	*
	* @return the name of this source
	*/
	public java.lang.String getName() {
		return _source.getName();
	}

	/**
	* Returns the localized name of this source in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this source
	*/
	public java.lang.String getName(java.util.Locale locale) {
		return _source.getName(locale);
	}

	/**
	* Returns the localized name of this source in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this source. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _source.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this source in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this source
	*/
	public java.lang.String getName(java.lang.String languageId) {
		return _source.getName(languageId);
	}

	/**
	* Returns the localized name of this source in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this source
	*/
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _source.getName(languageId, useDefault);
	}

	public java.lang.String getNameCurrentLanguageId() {
		return _source.getNameCurrentLanguageId();
	}

	public java.lang.String getNameCurrentValue() {
		return _source.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this source.
	*
	* @return the locales and localized names of this source
	*/
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _source.getNameMap();
	}

	/**
	* Sets the name of this source.
	*
	* @param name the name of this source
	*/
	public void setName(java.lang.String name) {
		_source.setName(name);
	}

	/**
	* Sets the localized name of this source in the language.
	*
	* @param name the localized name of this source
	* @param locale the locale of the language
	*/
	public void setName(java.lang.String name, java.util.Locale locale) {
		_source.setName(name, locale);
	}

	/**
	* Sets the localized name of this source in the language, and sets the default locale.
	*
	* @param name the localized name of this source
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_source.setName(name, locale, defaultLocale);
	}

	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_source.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this source from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this source
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_source.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this source from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this source
	* @param defaultLocale the default locale
	*/
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_source.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Returns the driver class name of this source.
	*
	* @return the driver class name of this source
	*/
	public java.lang.String getDriverClassName() {
		return _source.getDriverClassName();
	}

	/**
	* Sets the driver class name of this source.
	*
	* @param driverClassName the driver class name of this source
	*/
	public void setDriverClassName(java.lang.String driverClassName) {
		_source.setDriverClassName(driverClassName);
	}

	/**
	* Returns the driver url of this source.
	*
	* @return the driver url of this source
	*/
	public java.lang.String getDriverUrl() {
		return _source.getDriverUrl();
	}

	/**
	* Sets the driver url of this source.
	*
	* @param driverUrl the driver url of this source
	*/
	public void setDriverUrl(java.lang.String driverUrl) {
		_source.setDriverUrl(driverUrl);
	}

	/**
	* Returns the driver user name of this source.
	*
	* @return the driver user name of this source
	*/
	public java.lang.String getDriverUserName() {
		return _source.getDriverUserName();
	}

	/**
	* Sets the driver user name of this source.
	*
	* @param driverUserName the driver user name of this source
	*/
	public void setDriverUserName(java.lang.String driverUserName) {
		_source.setDriverUserName(driverUserName);
	}

	/**
	* Returns the driver password of this source.
	*
	* @return the driver password of this source
	*/
	public java.lang.String getDriverPassword() {
		return _source.getDriverPassword();
	}

	/**
	* Sets the driver password of this source.
	*
	* @param driverPassword the driver password of this source
	*/
	public void setDriverPassword(java.lang.String driverPassword) {
		_source.setDriverPassword(driverPassword);
	}

	public boolean isNew() {
		return _source.isNew();
	}

	public void setNew(boolean n) {
		_source.setNew(n);
	}

	public boolean isCachedModel() {
		return _source.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_source.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _source.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _source.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_source.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _source.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_source.setExpandoBridgeAttributes(baseModel);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_source.setExpandoBridgeAttributes(expandoBridge);
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_source.setExpandoBridgeAttributes(serviceContext);
	}

	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_source.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new SourceWrapper((Source)_source.clone());
	}

	public int compareTo(com.liferay.reports.model.Source source) {
		return _source.compareTo(source);
	}

	@Override
	public int hashCode() {
		return _source.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.reports.model.Source> toCacheModel() {
		return _source.toCacheModel();
	}

	public com.liferay.reports.model.Source toEscapedModel() {
		return new SourceWrapper(_source.toEscapedModel());
	}

	public com.liferay.reports.model.Source toUnescapedModel() {
		return new SourceWrapper(_source.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _source.toString();
	}

	public java.lang.String toXmlString() {
		return _source.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_source.persist();
	}

	public java.lang.String getAttachmentsDir() {
		return _source.getAttachmentsDir();
	}

	public java.lang.String[] getAttachmentsFiles()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _source.getAttachmentsFiles();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Source getWrappedSource() {
		return _source;
	}

	public Source getWrappedModel() {
		return _source;
	}

	public void resetOriginalValues() {
		_source.resetOriginalValues();
	}

	private Source _source;
}