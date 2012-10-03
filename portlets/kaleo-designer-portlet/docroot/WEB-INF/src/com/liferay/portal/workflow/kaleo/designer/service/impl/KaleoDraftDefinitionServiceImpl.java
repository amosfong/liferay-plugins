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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.base.KaleoDraftDefinitionServiceBaseImpl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Gregory Amerson
 * @author Eduardo Lundgren
 */
public class KaleoDraftDefinitionServiceImpl
	extends KaleoDraftDefinitionServiceBaseImpl {

	public KaleoDraftDefinition addKaleoDraftDefinition(
			long userId, String name, Map<Locale, String> titleMap,
			String content, int version, int draftVersion,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		return kaleoDraftDefinitionLocalService.addKaleoDraftDefinition(
			userId, name, titleMap, content, version, draftVersion,
			serviceContext);
	}

	public KaleoDraftDefinition getKaleoDraftDefinition(
		String name, int version, int draftVersion,
		ServiceContext serviceContext)
		throws PortalException, SystemException {

		return kaleoDraftDefinitionLocalService.getKaleoDraftDefinition(
			name, version, draftVersion, serviceContext);
	}

	public List<KaleoDraftDefinition> getKaleoDraftDefinitions()
		throws SystemException {

		return kaleoDraftDefinitionLocalService.getKaleoDraftDefinitions(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public KaleoDraftDefinition getLatestKaleoDraftDefinition(
			String name, int version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return kaleoDraftDefinitionLocalService.getLatestKaleoDraftDefinition(
			name, version, serviceContext);
	}

	public KaleoDraftDefinition publishKaleoDraftDefinition(
			long userId, String name, Map<Locale, String> titleMap,
			String content, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return kaleoDraftDefinitionLocalService.publishKaleoDraftDefinition(
			userId, name, titleMap, content, serviceContext);
	}

	public KaleoDraftDefinition updateKaleoDraftDefinition(
			long userId, String name, Map<Locale, String> titleMap,
			String content, int version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoDraftDefinition kaleoDraftDefinition =
			kaleoDraftDefinitionLocalService.updateKaleoDraftDefinition(
				userId, name, titleMap, content, version, serviceContext);

		return kaleoDraftDefinition;
	}

}