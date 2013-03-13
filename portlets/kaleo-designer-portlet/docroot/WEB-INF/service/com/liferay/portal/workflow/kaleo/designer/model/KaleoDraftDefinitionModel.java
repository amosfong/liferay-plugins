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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the KaleoDraftDefinition service. Represents a row in the &quot;KaleoDraftDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinition
 * @see com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionImpl
 * @see com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl
 * @generated
 */
public interface KaleoDraftDefinitionModel extends BaseModel<KaleoDraftDefinition>,
	GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a kaleo draft definition model instance should use the {@link KaleoDraftDefinition} interface instead.
	 */

	/**
	 * Returns the primary key of this kaleo draft definition.
	 *
	 * @return the primary key of this kaleo draft definition
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this kaleo draft definition.
	 *
	 * @param primaryKey the primary key of this kaleo draft definition
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the kaleo draft definition ID of this kaleo draft definition.
	 *
	 * @return the kaleo draft definition ID of this kaleo draft definition
	 */
	public long getKaleoDraftDefinitionId();

	/**
	 * Sets the kaleo draft definition ID of this kaleo draft definition.
	 *
	 * @param kaleoDraftDefinitionId the kaleo draft definition ID of this kaleo draft definition
	 */
	public void setKaleoDraftDefinitionId(long kaleoDraftDefinitionId);

	/**
	 * Returns the group ID of this kaleo draft definition.
	 *
	 * @return the group ID of this kaleo draft definition
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this kaleo draft definition.
	 *
	 * @param groupId the group ID of this kaleo draft definition
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this kaleo draft definition.
	 *
	 * @return the company ID of this kaleo draft definition
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this kaleo draft definition.
	 *
	 * @param companyId the company ID of this kaleo draft definition
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this kaleo draft definition.
	 *
	 * @return the user ID of this kaleo draft definition
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this kaleo draft definition.
	 *
	 * @param userId the user ID of this kaleo draft definition
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this kaleo draft definition.
	 *
	 * @return the user uuid of this kaleo draft definition
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this kaleo draft definition.
	 *
	 * @param userUuid the user uuid of this kaleo draft definition
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this kaleo draft definition.
	 *
	 * @return the user name of this kaleo draft definition
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this kaleo draft definition.
	 *
	 * @param userName the user name of this kaleo draft definition
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this kaleo draft definition.
	 *
	 * @return the create date of this kaleo draft definition
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this kaleo draft definition.
	 *
	 * @param createDate the create date of this kaleo draft definition
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this kaleo draft definition.
	 *
	 * @return the modified date of this kaleo draft definition
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this kaleo draft definition.
	 *
	 * @param modifiedDate the modified date of this kaleo draft definition
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this kaleo draft definition.
	 *
	 * @return the name of this kaleo draft definition
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this kaleo draft definition.
	 *
	 * @param name the name of this kaleo draft definition
	 */
	public void setName(String name);

	/**
	 * Returns the title of this kaleo draft definition.
	 *
	 * @return the title of this kaleo draft definition
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this kaleo draft definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this kaleo draft definition
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this kaleo draft definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this kaleo draft definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this kaleo draft definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this kaleo draft definition
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this kaleo draft definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this kaleo draft definition
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this kaleo draft definition.
	 *
	 * @return the locales and localized titles of this kaleo draft definition
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this kaleo draft definition.
	 *
	 * @param title the title of this kaleo draft definition
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this kaleo draft definition in the language.
	 *
	 * @param title the localized title of this kaleo draft definition
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this kaleo draft definition in the language, and sets the default locale.
	 *
	 * @param title the localized title of this kaleo draft definition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this kaleo draft definition from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this kaleo draft definition
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this kaleo draft definition from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this kaleo draft definition
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the content of this kaleo draft definition.
	 *
	 * @return the content of this kaleo draft definition
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this kaleo draft definition.
	 *
	 * @param content the content of this kaleo draft definition
	 */
	public void setContent(String content);

	/**
	 * Returns the version of this kaleo draft definition.
	 *
	 * @return the version of this kaleo draft definition
	 */
	public int getVersion();

	/**
	 * Sets the version of this kaleo draft definition.
	 *
	 * @param version the version of this kaleo draft definition
	 */
	public void setVersion(int version);

	/**
	 * Returns the draft version of this kaleo draft definition.
	 *
	 * @return the draft version of this kaleo draft definition
	 */
	public int getDraftVersion();

	/**
	 * Sets the draft version of this kaleo draft definition.
	 *
	 * @param draftVersion the draft version of this kaleo draft definition
	 */
	public void setDraftVersion(int draftVersion);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	public Object clone();

	public int compareTo(KaleoDraftDefinition kaleoDraftDefinition);

	public int hashCode();

	public CacheModel<KaleoDraftDefinition> toCacheModel();

	public KaleoDraftDefinition toEscapedModel();

	public KaleoDraftDefinition toUnescapedModel();

	public String toString();

	public String toXmlString();
}