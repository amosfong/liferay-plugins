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

package com.liferay.portal.workflow.kaleo.forms.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoProcessLinkService}.
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLinkService
 * @generated
 */
public class KaleoProcessLinkServiceWrapper implements KaleoProcessLinkService,
	ServiceWrapper<KaleoProcessLinkService> {
	public KaleoProcessLinkServiceWrapper(
		KaleoProcessLinkService kaleoProcessLinkService) {
		_kaleoProcessLinkService = kaleoProcessLinkService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kaleoProcessLinkService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoProcessLinkService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoProcessLinkService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkService.fetchKaleoProcessLink(kaleoProcessId,
			workflowTaskName);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessLinkId, long kaleoProcessId,
		java.lang.String workflowTaskName, long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkService.updateKaleoProcessLink(kaleoProcessLinkId,
			kaleoProcessId, workflowTaskName, ddmTemplateId);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName,
		long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkService.updateKaleoProcessLink(kaleoProcessId,
			workflowTaskName, ddmTemplateId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public KaleoProcessLinkService getWrappedKaleoProcessLinkService() {
		return _kaleoProcessLinkService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedKaleoProcessLinkService(
		KaleoProcessLinkService kaleoProcessLinkService) {
		_kaleoProcessLinkService = kaleoProcessLinkService;
	}

	@Override
	public KaleoProcessLinkService getWrappedService() {
		return _kaleoProcessLinkService;
	}

	@Override
	public void setWrappedService(
		KaleoProcessLinkService kaleoProcessLinkService) {
		_kaleoProcessLinkService = kaleoProcessLinkService;
	}

	private KaleoProcessLinkService _kaleoProcessLinkService;
}