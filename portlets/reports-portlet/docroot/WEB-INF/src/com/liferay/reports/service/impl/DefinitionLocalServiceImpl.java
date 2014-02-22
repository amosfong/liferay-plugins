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

package com.liferay.reports.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.DuplicateDirectoryException;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.reports.DefinitionNameException;
import com.liferay.reports.model.Definition;
import com.liferay.reports.service.base.DefinitionLocalServiceBaseImpl;

import java.io.InputStream;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Gavin Wan
 */
public class DefinitionLocalServiceImpl extends DefinitionLocalServiceBaseImpl {

	public Definition addDefinition(
			long userId, long groupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, long sourceId,
			String reportParameters, String fileName, InputStream inputStream,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Definition

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(nameMap);

		long definitionId = counterLocalService.increment();

		Definition definition = definitionPersistence.create(definitionId);

		definition.setUuid(serviceContext.getUuid());
		definition.setGroupId(groupId);
		definition.setCompanyId(user.getCompanyId());
		definition.setUserId(user.getUserId());
		definition.setUserName(user.getFullName());
		definition.setCreateDate(serviceContext.getCreateDate(now));
		definition.setModifiedDate(serviceContext.getModifiedDate(now));
		definition.setNameMap(nameMap);
		definition.setDescriptionMap(descriptionMap);
		definition.setSourceId(sourceId);
		definition.setReportName(
			StringUtil.extractFirst(fileName, StringPool.PERIOD));
		definition.setReportParameters(reportParameters);

		definitionPersistence.update(definition);

		// Resources

		resourceLocalService.addModelResources(definition, serviceContext);

		// Attachments

		if (Validator.isNotNull(fileName) && (inputStream != null)) {
			addDefinitionFile(
				user.getCompanyId(), definition, fileName, inputStream);
		}

		return definition;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Definition deleteDefinition(Definition definition)
		throws PortalException, SystemException {

		// Definition

		definitionPersistence.remove(definition);

		// Resources

		resourceLocalService.deleteResource(
			definition.getCompanyId(), Definition.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL, definition.getDefinitionId());

		// Attachments

		deleteDefinitionTemplates(
			definition.getCompanyId(), definition.getAttachmentsDir());

		return definition;
	}

	@Override
	public Definition deleteDefinition(long definitionId)
		throws PortalException, SystemException {

		Definition definition = definitionPersistence.findByPrimaryKey(
			definitionId);

		return definitionLocalService.deleteDefinition(definition);
	}

	@Override
	public void deleteDefinitions(long groupId)
		throws PortalException, SystemException {

		List<Definition> definitions = definitionPersistence.findByGroupId(
			groupId);

		for (Definition definition : definitions) {
			definitionLocalService.deleteDefinition(definition);
		}
	}

	public void deleteDefinitionTemplates(
			long companyId, String attachmentsDirectory)
		throws PortalException, SystemException {

		try {
			DLStoreUtil.deleteDirectory(
				companyId, CompanyConstants.SYSTEM, attachmentsDirectory);
		}
		catch (NoSuchDirectoryException nsde) {
		}
	}

	@SuppressWarnings("unchecked")
	public List<Definition> getDefinitions(
			long groupId, String definitionName, String description,
			String sourceId, String reportName, boolean andSearch, int start,
			int end, OrderByComparator orderByComparator)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, definitionName, description, sourceId, reportName,
			andSearch);

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int getDefinitionsCount(
			long groupId, String definitionName, String description,
			String sourceId, String reportName, boolean andSearch)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			groupId, definitionName, description, sourceId, reportName,
			andSearch);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public Definition updateDefinition(
			long definitionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, long sourceId,
			String reportParameters, String fileName, InputStream inputStream,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Definition

		Definition definition = definitionPersistence.findByPrimaryKey(
			definitionId);

		validate(nameMap);

		definition.setModifiedDate(serviceContext.getModifiedDate(null));
		definition.setNameMap(nameMap);
		definition.setDescriptionMap(descriptionMap);
		definition.setSourceId(sourceId);

		if (Validator.isNotNull(fileName)) {
			definition.setReportName(
				StringUtil.extractFirst(fileName, StringPool.PERIOD));
		}

		definition.setReportParameters(reportParameters);

		definitionPersistence.update(definition);

		// Resources

		if ((serviceContext.getGroupPermissions() != null) ||
			(serviceContext.getGuestPermissions() != null)) {

			updateDefinitionResources(
				definition, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Attachments

		if (Validator.isNotNull(fileName) && (inputStream != null)) {
			long companyId = definition.getCompanyId();

			try {
				DLStoreUtil.deleteDirectory(
					companyId, CompanyConstants.SYSTEM,
					definition.getAttachmentsDir());
			}
			catch (NoSuchDirectoryException nsde) {
			}

			addDefinitionFile(companyId, definition, fileName, inputStream);
		}

		return definition;
	}

	public void updateDefinitionResources(
			Definition definition, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.updateResources(
			definition.getCompanyId(), definition.getGroupId(),
			Definition.class.getName(), definition.getDefinitionId(),
			communityPermissions, guestPermissions);
	}

	protected void addDefinitionFile(
			long companyId, Definition definition, String fileName,
			InputStream inputStream)
		throws PortalException, SystemException {

		String directoryName = definition.getAttachmentsDir();

		try {
			DLStoreUtil.addDirectory(
				companyId, CompanyConstants.SYSTEM, directoryName);
		}
		catch (DuplicateDirectoryException dde) {
		}

		String fileLocation = directoryName.concat(
			StringPool.SLASH).concat(fileName);

		DLStoreUtil.addFile(
			companyId, CompanyConstants.SYSTEM, fileLocation, false,
			inputStream);
	}

	protected DynamicQuery buildDynamicQuery(
		long groupId, String definitionName, String description,
		String sourceId, String reportName, boolean andSearch) {

		Junction junction = null;

		if (andSearch) {
			junction = RestrictionsFactoryUtil.conjunction();
		}
		else {
			junction = RestrictionsFactoryUtil.disjunction();
		}

		if (Validator.isNotNull(definitionName)) {
			Property property = PropertyFactoryUtil.forName("name");

			String value =
				StringPool.PERCENT + definitionName + StringPool.PERCENT;

			junction.add(property.like(value));
		}

		if (Validator.isNotNull(description)) {
			Property property = PropertyFactoryUtil.forName("description");

			String value =
				StringPool.PERCENT + description + StringPool.PERCENT;

			junction.add(property.like(value));
		}

		if (Validator.isNotNull(sourceId)) {
			Property property = PropertyFactoryUtil.forName("sourceId");

			junction.add(property.eq(GetterUtil.getLong(sourceId)));
		}

		if (Validator.isNotNull(reportName)) {
			Property property = PropertyFactoryUtil.forName("reportName");

			String value = StringPool.PERCENT + reportName + StringPool.PERCENT;

			junction.add(property.like(value));
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Definition.class, getClassLoader());

		if (groupId > 0) {
			Property property = PropertyFactoryUtil.forName("groupId");

			dynamicQuery.add(property.eq(groupId));
		}

		dynamicQuery.add(junction);

		return dynamicQuery;
	}

	protected void validate(Map<Locale, String> nameMap)
		throws PortalException {

		Locale locale = LocaleUtil.getDefault();

		String name = nameMap.get(locale);

		if (Validator.isNull(name)) {
			throw new DefinitionNameException();
		}
	}

}