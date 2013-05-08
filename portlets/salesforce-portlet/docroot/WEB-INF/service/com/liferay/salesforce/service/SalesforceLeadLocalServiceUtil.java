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
 * Provides the local service utility for SalesforceLead. This utility wraps
 * {@link com.liferay.salesforce.service.impl.SalesforceLeadLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Michael C. Han
 * @see SalesforceLeadLocalService
 * @see com.liferay.salesforce.service.base.SalesforceLeadLocalServiceBaseImpl
 * @see com.liferay.salesforce.service.impl.SalesforceLeadLocalServiceImpl
 * @generated
 */
public class SalesforceLeadLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.salesforce.service.impl.SalesforceLeadLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static com.liferay.portal.kernel.messaging.MessageBatch getLeadsByCountry(
		long companyId, java.lang.String country,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLeadsByCountry(companyId, country, fieldNames);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch getLeadsBySource(
		long companyId, java.lang.String source,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLeadsBySource(companyId, source, fieldNames);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch getLeadsByStatus(
		long companyId, java.lang.String status,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLeadsByStatus(companyId, status, fieldNames);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch getLeadsByUserId(
		long companyId, java.lang.String userId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLeadsByUserId(companyId, userId, fieldNames);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch getLeadsByUserName(
		long companyId, java.lang.String userName,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLeadsByUserName(companyId, userName, fieldNames);
	}

	public static void clearService() {
		_service = null;
	}

	public static SalesforceLeadLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SalesforceLeadLocalService.class.getName());

			if (invokableLocalService instanceof SalesforceLeadLocalService) {
				_service = (SalesforceLeadLocalService)invokableLocalService;
			}
			else {
				_service = new SalesforceLeadLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SalesforceLeadLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SalesforceLeadLocalService service) {
	}

	private static SalesforceLeadLocalService _service;
}