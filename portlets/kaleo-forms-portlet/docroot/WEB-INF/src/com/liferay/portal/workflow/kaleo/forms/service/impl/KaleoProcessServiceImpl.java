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

package com.liferay.portal.workflow.kaleo.forms.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessServiceBaseImpl;
import com.liferay.portal.workflow.kaleo.forms.service.permission.KaleoFormsPermission;
import com.liferay.portal.workflow.kaleo.forms.service.permission.KaleoProcessPermission;
import com.liferay.portal.workflow.kaleo.forms.util.ActionKeys;

import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class KaleoProcessServiceImpl extends KaleoProcessServiceBaseImpl {

	public KaleoProcess addKaleoProcess(
			long groupId, long ddlRecordSetId, long ddmTemplateId,
			long[] kaleoProcessLinkIds, ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoFormsPermission.check(
			getPermissionChecker(), groupId, ActionKeys.ADD_PROCESS);

		return kaleoProcessLocalService.addKaleoProcess(
			getUserId(), groupId, ddlRecordSetId, ddmTemplateId,
			kaleoProcessLinkIds, serviceContext);
	}

	public KaleoProcess deleteKaleoProcess(long kaleoProcessId)
		throws PortalException, SystemException {

		KaleoProcessPermission.check(
			getPermissionChecker(), kaleoProcessId, ActionKeys.DELETE);

		return kaleoProcessLocalService.deleteKaleoProcess(kaleoProcessId);
	}

	public void deleteKaleoProcessData(long kaleoProcessId)
		throws PortalException, SystemException {

		KaleoProcessPermission.check(
			getPermissionChecker(), kaleoProcessId, ActionKeys.DELETE);

		kaleoProcessLocalService.deleteKaleoProcessData(kaleoProcessId);
	}

	public KaleoProcess getKaleoProcess(long kaleoProcessId)
		throws PortalException, SystemException {

		KaleoProcessPermission.check(
			getPermissionChecker(), kaleoProcessId, ActionKeys.VIEW);

		return kaleoProcessLocalService.getKaleoProcess(kaleoProcessId);
	}

	public List<KaleoProcess> getKaleoProcesses(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return kaleoProcessPersistence.filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	public int getKaleoProcessesCount(long groupId) throws SystemException {
		return kaleoProcessPersistence.filterCountByGroupId(groupId);
	}

	public KaleoProcess updateKaleoProcess(
			long kaleoProcessId, long ddmTemplateId, long[] kaleoProcessLinkIds,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		KaleoProcessPermission.check(
			getPermissionChecker(), kaleoProcessId, ActionKeys.UPDATE);

		return kaleoProcessLocalService.updateKaleoProcess(
			kaleoProcessId, ddmTemplateId, kaleoProcessLinkIds, serviceContext);
	}

}