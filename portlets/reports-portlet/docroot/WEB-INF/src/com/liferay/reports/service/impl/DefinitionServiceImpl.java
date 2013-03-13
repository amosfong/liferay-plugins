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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.reports.model.Definition;
import com.liferay.reports.service.base.DefinitionServiceBaseImpl;
import com.liferay.reports.service.permission.ActionKeys;
import com.liferay.reports.service.permission.AdminPermission;
import com.liferay.reports.service.permission.DefinitionPermission;

import java.io.InputStream;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 */
public class DefinitionServiceImpl extends DefinitionServiceBaseImpl {

	public Definition addDefinition(
			long groupId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, long sourceId,
			String reportParameters, String fileName, InputStream inputStream,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AdminPermission.check(
			getPermissionChecker(), serviceContext.getScopeGroupId(),
			ActionKeys.ADD_DEFINITION);

		return definitionLocalService.addDefinition(
			getUserId(), groupId, nameMap, descriptionMap, sourceId,
			reportParameters, fileName, inputStream, serviceContext);
	}

	public Definition deleteDefinition(long definitionId)
		throws PortalException, SystemException {

		DefinitionPermission.check(
			getPermissionChecker(), definitionId, ActionKeys.DELETE);

		return definitionLocalService.deleteDefinition(definitionId);
	}

	public Definition getDefinition(long definitionId)
		throws PortalException, SystemException {

		DefinitionPermission.check(
			getPermissionChecker(), definitionId, ActionKeys.VIEW);

		return definitionLocalService.getDefinition(definitionId);
	}

	public List<Definition> getDefinitions(
			long groupId, String definitionName, String description,
			String sourceId, String reportName, boolean andSearch, int start,
			int end, OrderByComparator orderByComparator)
		throws PortalException, SystemException {

		List<Definition> definitions = definitionLocalService.getDefinitions(
			groupId, definitionName, description, sourceId, reportName,
			andSearch, start, end, orderByComparator);

		return filterDefinitions(definitions);
	}

	public int getDefinitionsCount(
			long groupId, String definitionName, String description,
			String sourceId, String reportName, boolean andSearch)
		throws SystemException {

		return definitionLocalService.getDefinitionsCount(
			groupId, definitionName, description, sourceId, reportName,
			andSearch);
	}

	public Definition updateDefinition(
			long definitionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, long sourceId,
			String reportParameters, String fileName, InputStream inputStream,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DefinitionPermission.check(
			getPermissionChecker(), definitionId, ActionKeys.UPDATE);

		return definitionLocalService.updateDefinition(
			definitionId, nameMap, descriptionMap, sourceId, reportParameters,
			fileName, inputStream, serviceContext);
	}

	protected List<Definition> filterDefinitions(List<Definition> definitions)
		throws PortalException {

		definitions = ListUtil.copy(definitions);

		Iterator<Definition> itr = definitions.iterator();

		while (itr.hasNext()) {
			if (!DefinitionPermission.contains(
					getPermissionChecker(), itr.next(), ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return definitions;
	}

}