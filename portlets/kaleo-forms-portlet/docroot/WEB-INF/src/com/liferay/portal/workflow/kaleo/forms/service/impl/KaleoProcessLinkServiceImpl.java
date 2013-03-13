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
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;
import com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessLinkServiceBaseImpl;

/**
 * @author Marcellus Tavares
 */
public class KaleoProcessLinkServiceImpl
	extends KaleoProcessLinkServiceBaseImpl {

	public KaleoProcessLink fetchKaleoProcessLink(
			long kaleoProcessId, String workflowTaskName)
		throws SystemException {

		return kaleoProcessLinkLocalService.fetchKaleoProcessLink(
			kaleoProcessId, workflowTaskName);
	}

	public KaleoProcessLink updateKaleoProcessLink(
			long kaleoProcessLinkId, long kaleoProcessId,
			String workflowTaskName, long ddmTemplateId)
		throws PortalException, SystemException {

		return kaleoProcessLinkLocalService.updateKaleoProcessLink(
			kaleoProcessLinkId, kaleoProcessId, workflowTaskName,
			ddmTemplateId);
	}

	public KaleoProcessLink updateKaleoProcessLink(
			long kaleoProcessId, String workflowTaskName, long ddmTemplateId)
		throws SystemException {

		return kaleoProcessLinkLocalService.updateKaleoProcessLink(
			kaleoProcessId, workflowTaskName, ddmTemplateId);
	}

}