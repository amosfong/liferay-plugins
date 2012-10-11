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
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionContentException;
import com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionNameException;
import com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.base.KaleoDraftDefinitionLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.designer.util.KaleoDesignerUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.ArrayList;
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
			long userId, String name, Map<Locale, String> titleMap,
			String content, int version, int draftVersion,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		validate(name);

		long kaleoDraftDefinitionId = counterLocalService.increment();

		KaleoDraftDefinition kaleoDraftDefinition =
			kaleoDraftDefinitionPersistence.create(kaleoDraftDefinitionId);

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

		kaleoDraftDefinitionPersistence.update(kaleoDraftDefinition, false);

		return kaleoDraftDefinition;
	}

	public KaleoDraftDefinition deleteKaleoDraftDefinition(
			String name, int version, int draftVersion,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoDraftDefinition kaleoDraftDefinition = getKaleoDraftDefinition(
			name, version, draftVersion, serviceContext);

		return kaleoDraftDefinitionPersistence.remove(kaleoDraftDefinition);
	}

	public void deleteKaleoDraftDefinitions(
			String name, int version, ServiceContext serviceContext)
		throws PortalException, SystemException {

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

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoDraftDefinition.class, getClassLoader());

		Property kaleoDraftDefinitionId = PropertyFactoryUtil.forName(
			"kaleoDraftDefinitionId");

		dynamicQuery.add(
			kaleoDraftDefinitionId.in(
				getKaleoDraftDefinitionIds(companyId, version)));

		return dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public int getLatestKaleoDraftDefinitionsCount(long companyId, int version)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoDraftDefinition.class, getClassLoader());

		Property kaleoDraftDefinitionId = PropertyFactoryUtil.forName(
			"kaleoDraftDefinitionId");

		dynamicQuery.add(
			kaleoDraftDefinitionId.in(
				getKaleoDraftDefinitionIds(companyId, version)));

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public KaleoDraftDefinition incrementKaleoDraftDefinitionDraftVersion(
			long userId, String name, int version,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoDraftDefinition kaleoDraftDefinition =
			getLatestKaleoDraftDefinition(name, version, serviceContext);

		return addKaleoDraftDefinition(
			userId, kaleoDraftDefinition.getName(),
			kaleoDraftDefinition.getTitleMap(),
			kaleoDraftDefinition.getContent(),
			kaleoDraftDefinition.getVersion(),
			kaleoDraftDefinition.getDraftVersion() + 1, serviceContext);
	}

	public KaleoDraftDefinition publishKaleoDraftDefinition(
			long userId, String name, Map<Locale, String> titleMap,
			String content, ServiceContext serviceContext)
		throws PortalException, SystemException {

		validate(name, content);

		WorkflowDefinition workflowDefinition =
			KaleoDesignerUtil.deployWorkflowDefinition(
				serviceContext.getCompanyId(), serviceContext.getUserId(),
				titleMap, content);

		int version = workflowDefinition.getVersion();

		KaleoDraftDefinition kaleoDraftDefinition =
			addKaleoDraftDefinition(
				userId, name, titleMap, content, version, 1, serviceContext);

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

		kaleoDraftDefinitionPersistence.update(kaleoDraftDefinition, false);

		return kaleoDraftDefinition;
	}

	protected List<Object> getKaleoDraftDefinitionIds(
		long companyId, int version) throws SystemException {

		List<Object> kaleoDraftDefinitionIds = new ArrayList<Object>();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			KaleoDraftDefinition.class, getClassLoader());

		ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

		projectionList.add(ProjectionFactoryUtil.max("kaleoDraftDefinitionId"));
		projectionList.add(ProjectionFactoryUtil.groupProperty("name"));

		dynamicQuery.setProjection(projectionList);

		dynamicQuery.add(
			PropertyFactoryUtil.forName("companyId").eq(companyId));
		dynamicQuery.add(PropertyFactoryUtil.forName("version").eq(version));

		List<Object[]> results = dynamicQuery(dynamicQuery);

		for (Object[] result : results) {
			kaleoDraftDefinitionIds.add(result[0]);
		}

		return kaleoDraftDefinitionIds;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new KaleoDraftDefinitionNameException();
		}
	}

	protected void validate(String name, String content)
		throws PortalException {

		validate(name);

		try {
			InputStream inputStream = new ByteArrayInputStream(
				content.getBytes());

			WorkflowDefinitionManagerUtil.validateWorkflowDefinition(
				inputStream);
		}
		catch (WorkflowException we) {
			throw new KaleoDraftDefinitionContentException(we);
		}
	}

}