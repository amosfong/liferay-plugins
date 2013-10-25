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

package com.liferay.portal.workflow.kaleo.designer.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoDraftDefinition}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinition
 * @generated
 */
public class KaleoDraftDefinitionWrapper implements KaleoDraftDefinition,
	ModelWrapper<KaleoDraftDefinition> {
	public KaleoDraftDefinitionWrapper(
		KaleoDraftDefinition kaleoDraftDefinition) {
		_kaleoDraftDefinition = kaleoDraftDefinition;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoDraftDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoDraftDefinition.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoDraftDefinitionId", getKaleoDraftDefinitionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("title", getTitle());
		attributes.put("content", getContent());
		attributes.put("version", getVersion());
		attributes.put("draftVersion", getDraftVersion());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoDraftDefinitionId = (Long)attributes.get(
				"kaleoDraftDefinitionId");

		if (kaleoDraftDefinitionId != null) {
			setKaleoDraftDefinitionId(kaleoDraftDefinitionId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Integer draftVersion = (Integer)attributes.get("draftVersion");

		if (draftVersion != null) {
			setDraftVersion(draftVersion);
		}
	}

	/**
	* Returns the primary key of this kaleo draft definition.
	*
	* @return the primary key of this kaleo draft definition
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoDraftDefinition.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo draft definition.
	*
	* @param primaryKey the primary key of this kaleo draft definition
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoDraftDefinition.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo draft definition ID of this kaleo draft definition.
	*
	* @return the kaleo draft definition ID of this kaleo draft definition
	*/
	@Override
	public long getKaleoDraftDefinitionId() {
		return _kaleoDraftDefinition.getKaleoDraftDefinitionId();
	}

	/**
	* Sets the kaleo draft definition ID of this kaleo draft definition.
	*
	* @param kaleoDraftDefinitionId the kaleo draft definition ID of this kaleo draft definition
	*/
	@Override
	public void setKaleoDraftDefinitionId(long kaleoDraftDefinitionId) {
		_kaleoDraftDefinition.setKaleoDraftDefinitionId(kaleoDraftDefinitionId);
	}

	/**
	* Returns the group ID of this kaleo draft definition.
	*
	* @return the group ID of this kaleo draft definition
	*/
	@Override
	public long getGroupId() {
		return _kaleoDraftDefinition.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo draft definition.
	*
	* @param groupId the group ID of this kaleo draft definition
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoDraftDefinition.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo draft definition.
	*
	* @return the company ID of this kaleo draft definition
	*/
	@Override
	public long getCompanyId() {
		return _kaleoDraftDefinition.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo draft definition.
	*
	* @param companyId the company ID of this kaleo draft definition
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoDraftDefinition.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo draft definition.
	*
	* @return the user ID of this kaleo draft definition
	*/
	@Override
	public long getUserId() {
		return _kaleoDraftDefinition.getUserId();
	}

	/**
	* Sets the user ID of this kaleo draft definition.
	*
	* @param userId the user ID of this kaleo draft definition
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoDraftDefinition.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo draft definition.
	*
	* @return the user uuid of this kaleo draft definition
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinition.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo draft definition.
	*
	* @param userUuid the user uuid of this kaleo draft definition
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoDraftDefinition.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo draft definition.
	*
	* @return the user name of this kaleo draft definition
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoDraftDefinition.getUserName();
	}

	/**
	* Sets the user name of this kaleo draft definition.
	*
	* @param userName the user name of this kaleo draft definition
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoDraftDefinition.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo draft definition.
	*
	* @return the create date of this kaleo draft definition
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kaleoDraftDefinition.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo draft definition.
	*
	* @param createDate the create date of this kaleo draft definition
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kaleoDraftDefinition.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo draft definition.
	*
	* @return the modified date of this kaleo draft definition
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kaleoDraftDefinition.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo draft definition.
	*
	* @param modifiedDate the modified date of this kaleo draft definition
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoDraftDefinition.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this kaleo draft definition.
	*
	* @return the name of this kaleo draft definition
	*/
	@Override
	public java.lang.String getName() {
		return _kaleoDraftDefinition.getName();
	}

	/**
	* Sets the name of this kaleo draft definition.
	*
	* @param name the name of this kaleo draft definition
	*/
	@Override
	public void setName(java.lang.String name) {
		_kaleoDraftDefinition.setName(name);
	}

	/**
	* Returns the title of this kaleo draft definition.
	*
	* @return the title of this kaleo draft definition
	*/
	@Override
	public java.lang.String getTitle() {
		return _kaleoDraftDefinition.getTitle();
	}

	/**
	* Returns the localized title of this kaleo draft definition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param locale the locale of the language
	* @return the localized title of this kaleo draft definition
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale) {
		return _kaleoDraftDefinition.getTitle(locale);
	}

	/**
	* Returns the localized title of this kaleo draft definition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param locale the local of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this kaleo draft definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	*/
	@Override
	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _kaleoDraftDefinition.getTitle(locale, useDefault);
	}

	/**
	* Returns the localized title of this kaleo draft definition in the language. Uses the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @return the localized title of this kaleo draft definition
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId) {
		return _kaleoDraftDefinition.getTitle(languageId);
	}

	/**
	* Returns the localized title of this kaleo draft definition in the language, optionally using the default language if no localization exists for the requested language.
	*
	* @param languageId the ID of the language
	* @param useDefault whether to use the default language if no localization exists for the requested language
	* @return the localized title of this kaleo draft definition
	*/
	@Override
	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _kaleoDraftDefinition.getTitle(languageId, useDefault);
	}

	@Override
	public java.lang.String getTitleCurrentLanguageId() {
		return _kaleoDraftDefinition.getTitleCurrentLanguageId();
	}

	@Override
	public java.lang.String getTitleCurrentValue() {
		return _kaleoDraftDefinition.getTitleCurrentValue();
	}

	/**
	* Returns a map of the locales and localized titles of this kaleo draft definition.
	*
	* @return the locales and localized titles of this kaleo draft definition
	*/
	@Override
	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _kaleoDraftDefinition.getTitleMap();
	}

	/**
	* Sets the title of this kaleo draft definition.
	*
	* @param title the title of this kaleo draft definition
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_kaleoDraftDefinition.setTitle(title);
	}

	/**
	* Sets the localized title of this kaleo draft definition in the language.
	*
	* @param title the localized title of this kaleo draft definition
	* @param locale the locale of the language
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale) {
		_kaleoDraftDefinition.setTitle(title, locale);
	}

	/**
	* Sets the localized title of this kaleo draft definition in the language, and sets the default locale.
	*
	* @param title the localized title of this kaleo draft definition
	* @param locale the locale of the language
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitle(java.lang.String title, java.util.Locale locale,
		java.util.Locale defaultLocale) {
		_kaleoDraftDefinition.setTitle(title, locale, defaultLocale);
	}

	@Override
	public void setTitleCurrentLanguageId(java.lang.String languageId) {
		_kaleoDraftDefinition.setTitleCurrentLanguageId(languageId);
	}

	/**
	* Sets the localized titles of this kaleo draft definition from the map of locales and localized titles.
	*
	* @param titleMap the locales and localized titles of this kaleo draft definition
	*/
	@Override
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_kaleoDraftDefinition.setTitleMap(titleMap);
	}

	/**
	* Sets the localized titles of this kaleo draft definition from the map of locales and localized titles, and sets the default locale.
	*
	* @param titleMap the locales and localized titles of this kaleo draft definition
	* @param defaultLocale the default locale
	*/
	@Override
	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.util.Locale defaultLocale) {
		_kaleoDraftDefinition.setTitleMap(titleMap, defaultLocale);
	}

	/**
	* Returns the content of this kaleo draft definition.
	*
	* @return the content of this kaleo draft definition
	*/
	@Override
	public java.lang.String getContent() {
		return _kaleoDraftDefinition.getContent();
	}

	/**
	* Sets the content of this kaleo draft definition.
	*
	* @param content the content of this kaleo draft definition
	*/
	@Override
	public void setContent(java.lang.String content) {
		_kaleoDraftDefinition.setContent(content);
	}

	/**
	* Returns the version of this kaleo draft definition.
	*
	* @return the version of this kaleo draft definition
	*/
	@Override
	public int getVersion() {
		return _kaleoDraftDefinition.getVersion();
	}

	/**
	* Sets the version of this kaleo draft definition.
	*
	* @param version the version of this kaleo draft definition
	*/
	@Override
	public void setVersion(int version) {
		_kaleoDraftDefinition.setVersion(version);
	}

	/**
	* Returns the draft version of this kaleo draft definition.
	*
	* @return the draft version of this kaleo draft definition
	*/
	@Override
	public int getDraftVersion() {
		return _kaleoDraftDefinition.getDraftVersion();
	}

	/**
	* Sets the draft version of this kaleo draft definition.
	*
	* @param draftVersion the draft version of this kaleo draft definition
	*/
	@Override
	public void setDraftVersion(int draftVersion) {
		_kaleoDraftDefinition.setDraftVersion(draftVersion);
	}

	@Override
	public boolean isNew() {
		return _kaleoDraftDefinition.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoDraftDefinition.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoDraftDefinition.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoDraftDefinition.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoDraftDefinition.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoDraftDefinition.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoDraftDefinition.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoDraftDefinition.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoDraftDefinition.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoDraftDefinition.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoDraftDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.String[] getAvailableLanguageIds() {
		return _kaleoDraftDefinition.getAvailableLanguageIds();
	}

	@Override
	public java.lang.String getDefaultLanguageId() {
		return _kaleoDraftDefinition.getDefaultLanguageId();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.LocaleException {
		_kaleoDraftDefinition.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
		java.util.Locale defaultImportLocale)
		throws com.liferay.portal.LocaleException {
		_kaleoDraftDefinition.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoDraftDefinitionWrapper((KaleoDraftDefinition)_kaleoDraftDefinition.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition kaleoDraftDefinition) {
		return _kaleoDraftDefinition.compareTo(kaleoDraftDefinition);
	}

	@Override
	public int hashCode() {
		return _kaleoDraftDefinition.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> toCacheModel() {
		return _kaleoDraftDefinition.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition toEscapedModel() {
		return new KaleoDraftDefinitionWrapper(_kaleoDraftDefinition.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition toUnescapedModel() {
		return new KaleoDraftDefinitionWrapper(_kaleoDraftDefinition.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoDraftDefinition.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoDraftDefinition.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoDraftDefinition.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoDraftDefinitionWrapper)) {
			return false;
		}

		KaleoDraftDefinitionWrapper kaleoDraftDefinitionWrapper = (KaleoDraftDefinitionWrapper)obj;

		if (Validator.equals(_kaleoDraftDefinition,
					kaleoDraftDefinitionWrapper._kaleoDraftDefinition)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public KaleoDraftDefinition getWrappedKaleoDraftDefinition() {
		return _kaleoDraftDefinition;
	}

	@Override
	public KaleoDraftDefinition getWrappedModel() {
		return _kaleoDraftDefinition;
	}

	@Override
	public void resetOriginalValues() {
		_kaleoDraftDefinition.resetOriginalValues();
	}

	private KaleoDraftDefinition _kaleoDraftDefinition;
}