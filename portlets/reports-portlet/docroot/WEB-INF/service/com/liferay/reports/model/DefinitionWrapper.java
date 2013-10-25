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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Definition}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Definition
 * @generated
 */
public class DefinitionWrapper implements Definition, ModelWrapper<Definition> {
	public DefinitionWrapper(Definition definition) {
		_definition = definition;
	}

	@Override
	public Class<?> getModelClass() {
		return Definition.class;
	}

	@Override
	public String getModelClassName() {
		return Definition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("definitionId", getDefinitionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("sourceId", getSourceId());
		attributes.put("reportName", getReportName());
		attributes.put("reportParameters", getReportParameters());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long definitionId = (Long)attributes.get("definitionId");

		if (definitionId != null) {
			setDefinitionId(definitionId);
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

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long sourceId = (Long)attributes.get("sourceId");

		if (sourceId != null) {
			setSourceId(sourceId);
		}

		String reportName = (String)attributes.get("reportName");

		if (reportName != null) {
			setReportName(reportName);
		}

		String reportParameters = (String)attributes.get("reportParameters");

		if (reportParameters != null) {
			setReportParameters(reportParameters);
		}
	}

	/**
	* Returns the primary key of this definition.
	*
	* @return the primary key of this definition
	*/
	@Override
	public long getPrimaryKey() {
		return _definition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this definition.
	*
	* @param primaryKey the primary key of this definition
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_definition.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this definition.
	*
	* @return the uuid of this definition
	*/
	@Override
	public java.lang.String getUuid() {
		return _definition.getUuid();
	}

	/**
	* Sets the uuid of this definition.
	*
	* @param uuid the uuid of this definition
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_definition.setUuid(uuid);
	}

	/**
	* Returns the definition ID of this definition.
	*
	* @return the definition ID of this definition
	*/
	@Override
	public long getDefinitionId() {
		return _definition.getDefinitionId();
	}

	/**
	* Sets the definition ID of this definition.
	*
	* @param definitionId the definition ID of this definition
	*/
	@Override
	public void setDefinitionId(long definitionId) {
		_definition.setDefinitionId(definitionId);
	}

	/**
	* Returns the group ID of this definition.
	*
	* @return the group ID of this definition
	*/
	@Override
	public long getGroupId() {
		return _definition.getGroupId();
	}

	/**
	* Sets the group ID of this definition.
	*
	* @param groupId the group ID of this definition
	*/
	@Override
	public void setGroupId(long groupId) {
		_definition.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this definition.
	*
	* @return the company ID of this definition
	*/
	@Override
	public long getCompanyId() {
		return _definition.getCompanyId();
	}

	/**
	* Sets the company ID of this definition.
	*
	* @param companyId the company ID of this definition
	*/
	@Override
	public void setCompanyId(long companyId) {
		_definition.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this definition.
	*
	* @return the user ID of this definition
	*/
	@Override
	public long getUserId() {
		return _definition.getUserId();
	}

	/**
	* Sets the user ID of this definition.
	*
	* @param userId the user ID of this definition
	*/
	@Override
	public void setUserId(long userId) {
		_definition.setUserId(userId);
	}

	/**
	* Returns the user uuid of this definition.
	*
	* @return the user uuid of this definition
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definition.getUserUuid();
	}

	/**
	* Sets the user uuid of this definition.
	*
	* @param userUuid the user uuid of this definition
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_definition.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this definition.
	*
	* @return the user name of this definition
	*/
	@Override
	public java.lang.String getUserName() {
		return _definition.getUserName();
	}

	/**
	* Sets the user name of this definition.
	*
	* @param userName the user name of this definition
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_definition.setUserName(userName);
	}

	/**
	* Returns the create date of this definition.
	*
	* @return the create date of this definition
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _definition.getCreateDate();
	}

	/**
	* Sets the create date of this definition.
	*
	* @param createDate the create date of this definition
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_definition.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this definition.
	*
	* @return the modified date of this definition
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _definition.getModifiedDate();
	}

	/**
	* Sets the modified date of this definition.
	*
	* @param modifiedDate the modified date of this definition
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_definition.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this definition.
	*
	* @return the name of this definition
	*/
	@Override
	public java.lang.String getName() {
		return _definition.getName();
	}

	/**
	* Returns the localized name of this definition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized name of this definition
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale) {
		return _definition.getName(locale);
	}

	/**
	* Returns the localized name of this definition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getName(java.util.Locale locale, boolean useDefault) {
		return _definition.getName(locale, useDefault);
	}

	/**
	* Returns the localized name of this definition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized name of this definition
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId) {
		return _definition.getName(languageId);
	}

	/**
	* Returns the localized name of this definition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized name of this definition
	*/
	@Override
	public java.lang.String getName(java.lang.String languageId,
		boolean useDefault) {
		return _definition.getName(languageId, useDefault);
	}

	@Override
	public java.lang.String getNameCurrentLanguageId() {
		return _definition.getNameCurrentLanguageId();
	}

	@Override
	public java.lang.String getNameCurrentValue() {
		return _definition.getNameCurrentValue();
	}

	/**
	* Returns a map of the locales and localized names of this definition.
	*
	* @return the locales and localized names of this definition
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getNameMap() {
		return _definition.getNameMap();
	}

	/**
	* Sets the name of this definition.
	*
	* @param name the name of this definition
	*/
	@Override
	public void setName(java.lang.String name) {
		_definition.setName(name);
	}

	/**
	* Sets the localized name of this definition in the language.
	*
	* @param name the localized name of this definition
	* @param locale the locale of the language
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale) {
		_definition.setName(name, locale);
	}

	/**
	* Sets the localized name of this definition in the language, and sets the default locale.
	*
	* @param name the localized name of this definition
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setName(java.lang.String name, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_definition.setName(name, locale, defaultLocale);
	}

	@Override
	public void setNameCurrentLanguageId(java.lang.String languageId) {
		_definition.setNameCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized names of this definition from the map of locales and localized names.
	*
	* @param nameMap the locales and localized names of this definition
	*/
	@Override
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap) {
		_definition.setNameMap(nameMap);
	}

	/**
	* Sets the localized names of this definition from the map of locales and localized names, and sets the default locale.
	*
	* @param nameMap the locales and localized names of this definition
	* @param defaultLocale the default locale
	*/
	@Override
	public void setNameMap(
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Locale defaultLocale) {
		_definition.setNameMap(nameMap, defaultLocale);
	}

	/**
	* Returns the description of this definition.
	*
	* @return the description of this definition
	*/
	@Override
	public java.lang.String getDescription() {
		return _definition.getDescription();
	}

	/**
	* Returns the localized description of this definition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized description of this definition
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale) {
		return _definition.getDescription(locale);
	}

	/**
	* Returns the localized description of this definition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getDescription(java.util.Locale locale,
		boolean useDefault) {
		return _definition.getDescription(locale, useDefault);
	}

	/**
	* Returns the localized description of this definition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized description of this definition
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId) {
		return _definition.getDescription(languageId);
	}

	/**
	* Returns the localized description of this definition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized description of this definition
	*/
	@Override
	public java.lang.String getDescription(java.lang.String languageId,
		boolean useDefault) {
		return _definition.getDescription(languageId, useDefault);
	}

	@Override
	public java.lang.String getDescriptionCurrentLanguageId() {
		return _definition.getDescriptionCurrentLanguageId();
	}

	@Override
	public java.lang.String getDescriptionCurrentValue() {
		return _definition.getDescriptionCurrentValue();
	}

	/**
	* Returns a map of the locales and localized descriptions of this definition.
	*
	* @return the locales and localized descriptions of this definition
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getDescriptionMap() {
		return _definition.getDescriptionMap();
	}

	/**
	* Sets the description of this definition.
	*
	* @param description the description of this definition
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_definition.setDescription(description);
	}

	/**
	* Sets the localized description of this definition in the language.
	*
	* @param description the localized description of this definition
	* @param locale the locale of the language
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale) {
		_definition.setDescription(description, locale);
	}

	/**
	* Sets the localized description of this definition in the language, and sets the default locale.
	*
	* @param description the localized description of this definition
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescription(java.lang.String description,
		java.util.Locale locale, java.util.Locale defaultLocale) {
		_definition.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(java.lang.String languageId) {
		_definition.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized descriptions of this definition from the map of locales and localized descriptions.
	*
	* @param descriptionMap the locales and localized descriptions of this definition
	*/
	@Override
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap) {
		_definition.setDescriptionMap(descriptionMap);
	}

	/**
	* Sets the localized descriptions of this definition from the map of locales and localized descriptions, and sets the default locale.
	*
	* @param descriptionMap the locales and localized descriptions of this definition
	* @param defaultLocale the default locale
	*/
	@Override
	public void setDescriptionMap(
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.util.Locale defaultLocale) {
		_definition.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	* Returns the source ID of this definition.
	*
	* @return the source ID of this definition
	*/
	@Override
	public long getSourceId() {
		return _definition.getSourceId();
	}

	/**
	* Sets the source ID of this definition.
	*
	* @param sourceId the source ID of this definition
	*/
	@Override
	public void setSourceId(long sourceId) {
		_definition.setSourceId(sourceId);
	}

	/**
	* Returns the report name of this definition.
	*
	* @return the report name of this definition
	*/
	@Override
	public java.lang.String getReportName() {
		return _definition.getReportName();
	}

	/**
	* Sets the report name of this definition.
	*
	* @param reportName the report name of this definition
	*/
	@Override
	public void setReportName(java.lang.String reportName) {
		_definition.setReportName(reportName);
	}

	/**
	* Returns the report parameters of this definition.
	*
	* @return the report parameters of this definition
	*/
	@Override
	public java.lang.String getReportParameters() {
		return _definition.getReportParameters();
	}

	/**
	* Sets the report parameters of this definition.
	*
	* @param reportParameters the report parameters of this definition
	*/
	@Override
	public void setReportParameters(java.lang.String reportParameters) {
		_definition.setReportParameters(reportParameters);
	}

	@Override
	public boolean isNew() {
		return _definition.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_definition.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _definition.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_definition.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _definition.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _definition.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_definition.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _definition.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_definition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_definition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_definition.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _definition.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _definition.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_definition.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_definition.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new DefinitionWrapper((Definition)_definition.clone());
	}

	@Override
	public int compareTo(com.liferay.reports.model.Definition definition) {
		return _definition.compareTo(definition);
	}

	@Override
	public int hashCode() {
		return _definition.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.reports.model.Definition> toCacheModel() {
		return _definition.toCacheModel();
	}

	@Override
	public com.liferay.reports.model.Definition toEscapedModel() {
		return new DefinitionWrapper(_definition.toEscapedModel());
	}

	@Override
	public com.liferay.reports.model.Definition toUnescapedModel() {
		return new DefinitionWrapper(_definition.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _definition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _definition.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_definition.persist();
	}

	@Override
	public java.lang.String getAttachmentsDir() {
		return _definition.getAttachmentsDir();
	}

	@Override
	public java.lang.String[] getAttachmentsFiles()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _definition.getAttachmentsFiles();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DefinitionWrapper)) {
			return false;
		}

		DefinitionWrapper definitionWrapper = (DefinitionWrapper)obj;

		if (Validator.equals(_definition, definitionWrapper._definition)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _definition.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Definition getWrappedDefinition() {
		return _definition;
	}

	@Override
	public Definition getWrappedModel() {
		return _definition;
	}

	@Override
	public void resetOriginalValues() {
		_definition.resetOriginalValues();
	}

	private Definition _definition;
}