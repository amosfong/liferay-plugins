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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.forms.KaleoProcessDDMTemplateIdException;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessLocalServiceBaseImpl;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;

import java.util.Date;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class KaleoProcessLocalServiceImpl
	extends KaleoProcessLocalServiceBaseImpl {

	public KaleoProcess addKaleoProcess(
			long userId, long groupId, long ddlRecordSetId, long ddmTemplateId,
			long[] kaleoProcessLinkIds, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Kaleo process

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(ddmTemplateId);

		long kaleoProcessId = counterLocalService.increment();

		KaleoProcess kaleoProcess = kaleoProcessPersistence.create(
			kaleoProcessId);

		kaleoProcess.setGroupId(groupId);
		kaleoProcess.setCompanyId(user.getCompanyId());
		kaleoProcess.setUserId(user.getUserId());
		kaleoProcess.setUserName(user.getFullName());
		kaleoProcess.setCreateDate(serviceContext.getCreateDate(now));
		kaleoProcess.setModifiedDate(serviceContext.getModifiedDate(now));
		kaleoProcess.setDDLRecordSetId(ddlRecordSetId);
		kaleoProcess.setDDMTemplateId(ddmTemplateId);

		kaleoProcessPersistence.update(kaleoProcess);

		// Resources

		resourceLocalService.addModelResources(kaleoProcess, serviceContext);

		// Kaleo process links

		for (long kaleoProcessLinkId : kaleoProcessLinkIds) {
			kaleoProcessLinkLocalService.updateKaleoProcessLink(
				kaleoProcessLinkId, kaleoProcessId);
		}

		return kaleoProcess;
	}

	@Override
	public KaleoProcess deleteKaleoProcess(KaleoProcess kaleoProcess)
		throws PortalException, SystemException {

		// Kaleo process

		kaleoProcessPersistence.remove(kaleoProcess);

		// Kaleo process links

		kaleoProcessLinkLocalService.deleteKaleoProcessLinks(
			kaleoProcess.getPrimaryKey());

		// Workflow

		workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(
			kaleoProcess.getCompanyId(), kaleoProcess.getGroupId(),
			KaleoProcess.class.getName(), kaleoProcess.getPrimaryKey(), 0);

		workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
			kaleoProcess.getCompanyId(), kaleoProcess.getGroupId(),
			KaleoProcess.class.getName(), kaleoProcess.getPrimaryKey());

		// Dynamic data lists record set

		ddlRecordSetLocalService.deleteRecordSet(
			kaleoProcess.getDDLRecordSetId());

		return kaleoProcess;
	}

	@Override
	public KaleoProcess deleteKaleoProcess(long kaleoProcessId)
		throws PortalException, SystemException {

		KaleoProcess kaleoProcess = kaleoProcessPersistence.findByPrimaryKey(
			kaleoProcessId);

		return deleteKaleoProcess(kaleoProcess);
	}

	public void deleteKaleoProcessData(long kaleoProcessId)
		throws PortalException, SystemException {

		KaleoProcess kaleoProcess = kaleoProcessPersistence.findByPrimaryKey(
			kaleoProcessId);

		workflowDefinitionLinkLocalService.deleteWorkflowDefinitionLink(
			kaleoProcess.getCompanyId(), kaleoProcess.getGroupId(),
			KaleoProcess.class.getName(), kaleoProcess.getKaleoProcessId(), 0);

		List<DDLRecord> ddlRecords = ddlRecordLocalService.getRecords(
			kaleoProcess.getDDLRecordSetId());

		for (DDLRecord ddlRecord : ddlRecords) {
			workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
				kaleoProcess.getCompanyId(), kaleoProcess.getGroupId(),
				KaleoProcess.class.getName(), ddlRecord.getRecordId());

			ddlRecordLocalService.deleteRecord(ddlRecord.getRecordId());
		}
	}

	public KaleoProcess getDDLRecordSetKaleoProcess(long ddlRecordSetId)
		throws PortalException, SystemException {

		return kaleoProcessPersistence.findByDDLRecordSetId(ddlRecordSetId);
	}

	@Override
	public KaleoProcess getKaleoProcess(long kaleoProcessId)
		throws PortalException, SystemException {

		return kaleoProcessPersistence.findByPrimaryKey(kaleoProcessId);
	}

	public List<KaleoProcess> getKaleoProcesses(long groupId)
		throws SystemException {

		return kaleoProcessPersistence.findByGroupId(groupId);
	}

	public List<KaleoProcess> getKaleoProcesses(
			long groupId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return kaleoProcessPersistence.findByGroupId(
			groupId, start, end, orderByComparator);
	}

	public int getKaleoProcessesCount(long groupId) throws SystemException {
		return kaleoProcessPersistence.countByGroupId(groupId);
	}

	public KaleoProcess updateKaleoProcess(
			long kaleoProcessId, long ddmTemplateId, long[] kaleoProcessLinkIds,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Kaleo process

		validate(ddmTemplateId);

		KaleoProcess kaleoProcess = kaleoProcessPersistence.findByPrimaryKey(
			kaleoProcessId);

		kaleoProcess.setModifiedDate(serviceContext.getModifiedDate(null));
		kaleoProcess.setDDMTemplateId(ddmTemplateId);

		kaleoProcessPersistence.update(kaleoProcess);

		// Kaleo process links

		for (long kaleoProcessLinkId : kaleoProcessLinkIds) {
			kaleoProcessLinkLocalService.updateKaleoProcessLink(
				kaleoProcessLinkId, kaleoProcessId);
		}

		return kaleoProcess;
	}

	protected void validate(long ddmTemplateId) throws PortalException {
		if (Validator.isNull(ddmTemplateId)) {
			throw new KaleoProcessDDMTemplateIdException();
		}
	}

}