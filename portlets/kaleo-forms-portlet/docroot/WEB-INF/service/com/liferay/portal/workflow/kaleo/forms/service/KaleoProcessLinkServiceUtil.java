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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the kaleo process link remote service. This utility wraps {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLinkService
 * @see com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessLinkServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkServiceImpl
 * @generated
 */
public class KaleoProcessLinkServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchKaleoProcessLink(kaleoProcessId, workflowTaskName);
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessLinkId, long kaleoProcessId,
		java.lang.String workflowTaskName, long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoProcessLink(kaleoProcessLinkId, kaleoProcessId,
			workflowTaskName, ddmTemplateId);
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName,
		long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoProcessLink(kaleoProcessId, workflowTaskName,
			ddmTemplateId);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoProcessLinkService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoProcessLinkService.class.getName());

			if (invokableService instanceof KaleoProcessLinkService) {
				_service = (KaleoProcessLinkService)invokableService;
			}
			else {
				_service = new KaleoProcessLinkServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(KaleoProcessLinkServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(KaleoProcessLinkService service) {
	}

	private static KaleoProcessLinkService _service;
}