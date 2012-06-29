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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionContentException;
import com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionTitleException;
import com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.base.KaleoDraftDefinitionLocalServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.designer.util.KaleoDesignerUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Eduardo Lundgren
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

		validate(titleMap, content);

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

	public KaleoDraftDefinition addWorkflowDefinitionKaleoDraftDefinition(
			long userId, String name, Map<Locale, String> titleMap,
			String content, int version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		validate(titleMap, content);

		WorkflowDefinition workflowDefinition = null;

		try {
			workflowDefinition =
				WorkflowDefinitionManagerUtil.getWorkflowDefinition(
					user.getCompanyId(), name, version);
		}
		catch (Exception e) {
			workflowDefinition = KaleoDesignerUtil.deployWorkflowDefinition(
				user.getCompanyId(), userId,
				titleMap.get(LocaleUtil.getDefault()), content, false);
		}

		return addKaleoDraftDefinition(
			userId, workflowDefinition.getName(), titleMap,
			workflowDefinition.getContent(), workflowDefinition.getVersion(), 1,
			serviceContext);
	}

	public KaleoDraftDefinition deleteKaleoDraftDefinition(
			String name, int version, int draftVersion,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoDraftDefinition kaleoDraftDefinition = getKaleoDraftDefinition(
			name, version, draftVersion, serviceContext);

		return kaleoDraftDefinitionPersistence.remove(kaleoDraftDefinition);
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

	public KaleoDraftDefinition incrementKaleoDraftDefinitionVersion(
			long userId, String name, int version,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoDraftDefinition kaleoDraftDefinition =
			getLatestKaleoDraftDefinition(name, version, serviceContext);

		return addKaleoDraftDefinition(
			userId, kaleoDraftDefinition.getName(),
			kaleoDraftDefinition.getTitleMap(),
			kaleoDraftDefinition.getContent(),
			kaleoDraftDefinition.getVersion() + 1, 1, serviceContext);
	}

	public KaleoDraftDefinition publishKaleoDraftDefinition(
			long userId, String name, Map<Locale, String> titleMap,
			String content, ServiceContext serviceContext)
		throws PortalException, SystemException {

		validate(titleMap, content);

		WorkflowDefinition workflowDefinition =
			WorkflowDefinitionManagerUtil.getLatestKaleoDefinition(
				serviceContext.getCompanyId(), name);

		KaleoDesignerUtil.deployWorkflowDefinition(
			serviceContext.getCompanyId(), serviceContext.getUserId(),
			titleMap.get(serviceContext.getLocale()), content, true);

		KaleoDraftDefinition kaleoDraftDefinition =
			incrementKaleoDraftDefinitionVersion(
				userId, name, workflowDefinition.getVersion(), serviceContext);

		kaleoDraftDefinition = updateKaleoDraftDefinition(
			name, titleMap, content, kaleoDraftDefinition.getVersion(),
			kaleoDraftDefinition.getDraftVersion(), serviceContext);

		return kaleoDraftDefinition;
	}

	public KaleoDraftDefinition updateKaleoDraftDefinition(
			String name, Map<Locale, String> titleMap, String content,
			int version, int draftVersion, ServiceContext serviceContext)
		throws PortalException, SystemException {

		validate(titleMap, content);

		KaleoDraftDefinition kaleoDraftDefinition =
			kaleoDraftDefinitionPersistence.findByC_N_V_D(
				serviceContext.getCompanyId(), name, version, draftVersion);

		kaleoDraftDefinition.setTitleMap(titleMap);
		kaleoDraftDefinition.setContent(content);

		kaleoDraftDefinitionPersistence.update(kaleoDraftDefinition, false);

		return kaleoDraftDefinition;
	}

	public void validate(Map<Locale, String> titleMap, String content)
		throws PortalException {

		String title = titleMap.get(LocaleUtil.getDefault());

		if (Validator.isNull(title)) {
			throw new KaleoDraftDefinitionTitleException();
		}

		try {
			InputStream inputStream = new ByteArrayInputStream(
				content.getBytes());

			WorkflowDefinitionManagerUtil.validateWorkflowDefinition(
				inputStream);
		}
		catch (Exception e) {
			throw new KaleoDraftDefinitionContentException(e);
		}
	}

}