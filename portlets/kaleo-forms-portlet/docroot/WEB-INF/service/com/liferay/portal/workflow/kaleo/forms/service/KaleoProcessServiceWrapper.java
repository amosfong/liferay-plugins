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

package com.liferay.portal.workflow.kaleo.forms.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KaleoProcessService}.
 * </p>
 *
 * @author    Marcellus Tavares
 * @see       KaleoProcessService
 * @generated
 */
public class KaleoProcessServiceWrapper implements KaleoProcessService,
	ServiceWrapper<KaleoProcessService> {
	public KaleoProcessServiceWrapper(KaleoProcessService kaleoProcessService) {
		_kaleoProcessService = kaleoProcessService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kaleoProcessService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoProcessService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoProcessService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess addKaleoProcess(
		long groupId, long ddlRecordSetId, long ddmTemplateId,
		long[] kaleoProcessLinkIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessService.addKaleoProcess(groupId, ddlRecordSetId,
			ddmTemplateId, kaleoProcessLinkIds, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess deleteKaleoProcess(
		long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessService.deleteKaleoProcess(kaleoProcessId);
	}

	public void deleteKaleoProcessData(long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoProcessService.deleteKaleoProcessData(kaleoProcessId);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess getKaleoProcess(
		long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessService.getKaleoProcess(kaleoProcessId);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess updateKaleoProcess(
		long kaleoProcessId, long ddmTemplateId, long[] kaleoProcessLinkIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessService.updateKaleoProcess(kaleoProcessId,
			ddmTemplateId, kaleoProcessLinkIds, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public KaleoProcessService getWrappedKaleoProcessService() {
		return _kaleoProcessService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedKaleoProcessService(
		KaleoProcessService kaleoProcessService) {
		_kaleoProcessService = kaleoProcessService;
	}

	public KaleoProcessService getWrappedService() {
		return _kaleoProcessService;
	}

	public void setWrappedService(KaleoProcessService kaleoProcessService) {
		_kaleoProcessService = kaleoProcessService;
	}

	private KaleoProcessService _kaleoProcessService;
}