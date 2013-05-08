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

package com.liferay.salesforce.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SalesforceAccount. This utility wraps
 * {@link com.liferay.salesforce.service.impl.SalesforceAccountLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Michael C. Han
 * @see SalesforceAccountLocalService
 * @see com.liferay.salesforce.service.base.SalesforceAccountLocalServiceBaseImpl
 * @see com.liferay.salesforce.service.impl.SalesforceAccountLocalServiceImpl
 * @generated
 */
public class SalesforceAccountLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.salesforce.service.impl.SalesforceAccountLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static com.liferay.portal.kernel.messaging.MessageBatch getAccountsByName(
		long companyId, java.lang.String name,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountsByName(companyId, name, fieldNames);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch getAccountsByOwnerId(
		long companyId, java.lang.String ownerId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountsByOwnerId(companyId, ownerId, fieldNames);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch getAccountsByUserName(
		long companyId, java.lang.String userName,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAccountsByUserName(companyId, userName, fieldNames);
	}

	public static void clearService() {
		_service = null;
	}

	public static SalesforceAccountLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SalesforceAccountLocalService.class.getName());

			if (invokableLocalService instanceof SalesforceAccountLocalService) {
				_service = (SalesforceAccountLocalService)invokableLocalService;
			}
			else {
				_service = new SalesforceAccountLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SalesforceAccountLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SalesforceAccountLocalService service) {
	}

	private static SalesforceAccountLocalService _service;
}