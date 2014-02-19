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

package com.liferay.portal.workflow.kaleo.designer.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.designer.DuplicateKaleoDraftDefinitionNameException;
import com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionContentException;
import com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionNameException;
import com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.base.KaleoDraftDefinitionLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.designer.util.KaleoDesignerUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 */
public class KaleoDraftDefinitionLocalServiceImpl
	extends KaleoDraftDefinitionLocalServiceBaseImpl {

	public KaleoDraftDefinition addKaleoDraftDefinition(
			long userId, long groupId, String name,
			Map<Locale, String> titleMap, String content, int version,
			int draftVersion, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Kaleo draft definition

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(user.getCompanyId(), name, version, draftVersion);

		long kaleoDraftDefinitionId = counterLocalService.increment();

		KaleoDraftDefinition kaleoDraftDefinition =
			kaleoDraftDefinitionPersistence.create(kaleoDraftDefinitionId);

		kaleoDraftDefinition.setGroupId(groupId);
		kaleoDraftDefinition.setCompanyId(user.getCompanyId());
		kaleoDraftDefinition.setUserId(user.getUserId());
		kaleoDraftDefinition.setUserName(user.getFullName());
		kaleoDraftDefinition.setCreateDate(now);
		kaleoDraftDefinition.setModifiedDate(now);
		kaleoDraftDefinition.setName(name);
		kaleoDraftDefinition.setTitleMap(titleMap);
		kaleoDraftDefinition.setContent(content);
		kaleoDraftDefinition.setVersion(version);
		kaleoDraftDefinition.setDraftVersion(draftVersion);

		kaleoDraftDefinitionPersistence.update(kaleoDraftDefinition);

		// Resources

		resourceLocalService.addModelResources(
			kaleoDraftDefinition, serviceContext);

		return kaleoDraftDefinition;
	}

	public KaleoDraftDefinition deleteKaleoDraftDefinition(
			String name, int version, int draftVersion,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Kaleo draft definition

		KaleoDraftDefinition kaleoDraftDefinition = getKaleoDraftDefinition(
			name, version, draftVersion, serviceContext);

		kaleoDraftDefinitionPersistence.remove(kaleoDraftDefinition);

		// Resources

		resourceLocalService.deleteResource(
			kaleoDraftDefinition, ResourceConstants.SCOPE_COMPANY);

		return kaleoDraftDefinition;
	}

	public void deleteKaleoDraftDefinitions(
			String name, int version, ServiceContext serviceContext)
		throws SystemException {

		List<KaleoDraftDefinition> kaleoDraftDefinitions =
			kaleoDraftDefinitionPersistence.findByC_N_V(
				serviceContext.getCompanyId(), name, version);

		for (KaleoDraftDefinition kaleoDraftDefinition :
				kaleoDraftDefinitions) {

			deleteKaleoDraftDefinition(kaleoDraftDefinition);
		}
	}

	public KaleoDraftDefinition getKaleoDraftDefinition(
			String name, int version, int draftVersion,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return kaleoDraftDefinitionPersistence.findByC_N_V_D(
			serviceContext.getCompanyId(), name, version, draftVersion);
	}

	public List<KaleoDraftDefinition> getKaleoDraftDefinitions(
			String name, int version, int start, int end,
			OrderByComparator orderByComparator, ServiceContext serviceContext)
		throws SystemException {

		return kaleoDraftDefinitionPersistence.findByC_N_V(
			serviceContext.getCompanyId(), name, version, start, end,
			orderByComparator);
	}

	public int getKaleoDraftDefinitionsCount(
			String name, int version, ServiceContext serviceContext)
		throws SystemException {

		return kaleoDraftDefinitionPersistence.countByC_N_V(
			serviceContext.getCompanyId(), name, version);
	}

	public KaleoDraftDefinition getLatestKaleoDraftDefinition(
			String name, int version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<KaleoDraftDefinition> kaleoDraftDefinitions =
			kaleoDraftDefinitionPersistence.findByC_N_V(
				serviceContext.getCompanyId(), name, version, 0, 1);

		if (kaleoDraftDefinitions.isEmpty()) {
			throw new NoSuchKaleoDraftDefinitionException();
		}

		return kaleoDraftDefinitions.get(0);
	}

	public List<KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
			long companyId, int version, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		List<Object> kaleoDraftDefinitioIds = getKaleoDraftDefinitionIds(
			companyId, version);

		if (kaleoDraftDefinitioIds.isEmpty()) {
			return Collections.emptyList();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoDraftDefinition.class, getClassLoader());

		Property property = PropertyFactoryUtil.forName(
			"kaleoDraftDefinitionId");

		dynamicQuery.add(property.in(kaleoDraftDefinitioIds));

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int getLatestKaleoDraftDefinitionsCount(long companyId, int version)
		throws SystemException {

		List<Object> kaleoDraftDefinitioIds = getKaleoDraftDefinitionIds(
			companyId, version);

		if (kaleoDraftDefinitioIds.isEmpty()) {
			return 0;
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoDraftDefinition.class, getClassLoader());

		Property property = PropertyFactoryUtil.forName(
			"kaleoDraftDefinitionId");

		dynamicQuery.add(property.in(kaleoDraftDefinitioIds));

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public KaleoDraftDefinition incrementKaleoDraftDefinitionDraftVersion(
			long userId, String name, int version,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoDraftDefinition kaleoDraftDefinition =
			getLatestKaleoDraftDefinition(name, version, serviceContext);

		return addKaleoDraftDefinition(
			userId, kaleoDraftDefinition.getGroupId(),
			kaleoDraftDefinition.getName(), kaleoDraftDefinition.getTitleMap(),
			kaleoDraftDefinition.getContent(),
			kaleoDraftDefinition.getVersion(),
			kaleoDraftDefinition.getDraftVersion() + 1, serviceContext);
	}

	public KaleoDraftDefinition publishKaleoDraftDefinition(
			long userId, long groupId, String name,
			Map<Locale, String> titleMap, String content,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		validate(content);

		WorkflowDefinition workflowDefinition =
			KaleoDesignerUtil.deployWorkflowDefinition(
				serviceContext.getCompanyId(), serviceContext.getUserId(),
				titleMap, content);

		int version = workflowDefinition.getVersion();

		KaleoDraftDefinition kaleoDraftDefinition =
			addKaleoDraftDefinition(
				userId, groupId, name, titleMap, content, version, 1,
				serviceContext);

		if (version == 1) {
			deleteKaleoDraftDefinitions(name, 0, serviceContext);
		}

		return kaleoDraftDefinition;
	}

	public KaleoDraftDefinition updateKaleoDraftDefinition(
			long userId, String name, Map<Locale, String> titleMap,
			String content, int version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoDraftDefinition kaleoDraftDefinition =
			incrementKaleoDraftDefinitionDraftVersion(
				userId, name, version, serviceContext);

		kaleoDraftDefinition.setTitleMap(titleMap);
		kaleoDraftDefinition.setContent(content);

		kaleoDraftDefinitionPersistence.update(kaleoDraftDefinition);

		return kaleoDraftDefinition;
	}

	protected List<Object> getKaleoDraftDefinitionIds(
			long companyId, int version)
		throws SystemException {

		List<Object> kaleoDraftDefinitionIds = new ArrayList<Object>();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoDraftDefinition.class, getClassLoader());

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(companyId));

		if (version >= 0) {
			dynamicQuery.add(
				PropertyFactoryUtil.forName("version").eq(version));
		}

		ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

		projectionList.add(ProjectionFactoryUtil.max("kaleoDraftDefinitionId"));
		projectionList.add(ProjectionFactoryUtil.groupProperty("name"));

		dynamicQuery.setProjection(projectionList);

		List<Object[]> results = dynamicQuery(dynamicQuery);

		for (Object[] result : results) {
			kaleoDraftDefinitionIds.add(result[0]);
		}

		return kaleoDraftDefinitionIds;
	}

	protected void validate(
			long companyId, String name, int version, int draftVersion)
		throws PortalException, SystemException {

		if (Validator.isNull(name)) {
			throw new KaleoDraftDefinitionNameException();
		}

		if (kaleoDraftDefinitionPersistence.countByC_N_V_D(
				companyId, name, version, draftVersion) > 0) {

			throw new DuplicateKaleoDraftDefinitionNameException();
		}
	}

	protected void validate(
			long companyId, String name, String content, int version,
			int draftVersion)
		throws PortalException, SystemException {

		validate(companyId, name, version, draftVersion);
		validate(content);
	}

	protected void validate(String content) throws PortalException {
		try {
			WorkflowDefinitionManagerUtil.validateWorkflowDefinition(
				content.getBytes());
		}
		catch (WorkflowException we) {
			throw new KaleoDraftDefinitionContentException(we);
		}
	}

}