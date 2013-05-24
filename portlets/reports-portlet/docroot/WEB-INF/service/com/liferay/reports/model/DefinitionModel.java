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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the Definition service. Represents a row in the &quot;Reports_Definition&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.reports.model.impl.DefinitionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.reports.model.impl.DefinitionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Definition
 * @see com.liferay.reports.model.impl.DefinitionImpl
 * @see com.liferay.reports.model.impl.DefinitionModelImpl
 * @generated
 */
public interface DefinitionModel extends BaseModel<Definition>,
	StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a definition model instance should use the {@link Definition} interface instead.
	 */

	/**
	 * Returns the primary key of this definition.
	 *
	 * @return the primary key of this definition
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this definition.
	 *
	 * @param primaryKey the primary key of this definition
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this definition.
	 *
	 * @return the uuid of this definition
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this definition.
	 *
	 * @param uuid the uuid of this definition
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the definition ID of this definition.
	 *
	 * @return the definition ID of this definition
	 */
	public long getDefinitionId();

	/**
	 * Sets the definition ID of this definition.
	 *
	 * @param definitionId the definition ID of this definition
	 */
	public void setDefinitionId(long definitionId);

	/**
	 * Returns the group ID of this definition.
	 *
	 * @return the group ID of this definition
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this definition.
	 *
	 * @param groupId the group ID of this definition
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this definition.
	 *
	 * @return the company ID of this definition
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this definition.
	 *
	 * @param companyId the company ID of this definition
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this definition.
	 *
	 * @return the user ID of this definition
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this definition.
	 *
	 * @param userId the user ID of this definition
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this definition.
	 *
	 * @return the user uuid of this definition
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this definition.
	 *
	 * @param userUuid the user uuid of this definition
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this definition.
	 *
	 * @return the user name of this definition
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this definition.
	 *
	 * @param userName the user name of this definition
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this definition.
	 *
	 * @return the create date of this definition
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this definition.
	 *
	 * @param createDate the create date of this definition
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this definition.
	 *
	 * @return the modified date of this definition
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this definition.
	 *
	 * @param modifiedDate the modified date of this definition
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this definition.
	 *
	 * @return the name of this definition
	 */
	public String getName();

	/**
	 * Returns the localized name of this definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this definition
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this definition
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this definition
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this definition.
	 *
	 * @return the locales and localized names of this definition
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this definition.
	 *
	 * @param name the name of this definition
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this definition in the language.
	 *
	 * @param name the localized name of this definition
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this definition in the language, and sets the default locale.
	 *
	 * @param name the localized name of this definition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this definition from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this definition
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this definition from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this definition
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this definition.
	 *
	 * @return the description of this definition
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this definition
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this definition. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this definition in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this definition
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this definition in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this definition
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this definition.
	 *
	 * @return the locales and localized descriptions of this definition
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this definition.
	 *
	 * @param description the description of this definition
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this definition in the language.
	 *
	 * @param description the localized description of this definition
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this definition in the language, and sets the default locale.
	 *
	 * @param description the localized description of this definition
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this definition from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this definition
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this definition from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this definition
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the source ID of this definition.
	 *
	 * @return the source ID of this definition
	 */
	public long getSourceId();

	/**
	 * Sets the source ID of this definition.
	 *
	 * @param sourceId the source ID of this definition
	 */
	public void setSourceId(long sourceId);

	/**
	 * Returns the report name of this definition.
	 *
	 * @return the report name of this definition
	 */
	@AutoEscape
	public String getReportName();

	/**
	 * Sets the report name of this definition.
	 *
	 * @param reportName the report name of this definition
	 */
	public void setReportName(String reportName);

	/**
	 * Returns the report parameters of this definition.
	 *
	 * @return the report parameters of this definition
	 */
	@AutoEscape
	public String getReportParameters();

	/**
	 * Sets the report parameters of this definition.
	 *
	 * @param reportParameters the report parameters of this definition
	 */
	public void setReportParameters(String reportParameters);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(Definition definition);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Definition> toCacheModel();

	@Override
	public Definition toEscapedModel();

	@Override
	public Definition toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}