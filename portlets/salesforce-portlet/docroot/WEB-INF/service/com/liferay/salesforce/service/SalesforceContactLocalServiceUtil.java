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
 * Provides the local service utility for SalesforceContact. This utility wraps
 * {@link com.liferay.salesforce.service.impl.SalesforceContactLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Michael C. Han
 * @see SalesforceContactLocalService
 * @see com.liferay.salesforce.service.base.SalesforceContactLocalServiceBaseImpl
 * @see com.liferay.salesforce.service.impl.SalesforceContactLocalServiceImpl
 * @generated
 */
public class SalesforceContactLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.salesforce.service.impl.SalesforceContactLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static com.liferay.portal.kernel.messaging.MessageBatch getContactsByAccountId(
		long companyId, java.lang.String accountId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getContactsByAccountId(companyId, accountId, fieldNames);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch getContactsByOwnerId(
		long companyId, java.lang.String userId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getContactsByOwnerId(companyId, userId, fieldNames);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch getContactsByUserName(
		long companyId, java.lang.String userName,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getContactsByUserName(companyId, userName, fieldNames);
	}

	public static void clearService() {
		_service = null;
	}

	public static SalesforceContactLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SalesforceContactLocalService.class.getName());

			if (invokableLocalService instanceof SalesforceContactLocalService) {
				_service = (SalesforceContactLocalService)invokableLocalService;
			}
			else {
				_service = new SalesforceContactLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SalesforceContactLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SalesforceContactLocalService service) {
	}

	private static SalesforceContactLocalService _service;
}