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
 * Provides the local service utility for Salesforce. This utility wraps
 * {@link com.liferay.salesforce.service.impl.SalesforceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Michael C. Han
 * @see SalesforceLocalService
 * @see com.liferay.salesforce.service.base.SalesforceLocalServiceBaseImpl
 * @see com.liferay.salesforce.service.impl.SalesforceLocalServiceImpl
 * @generated
 */
public class SalesforceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.salesforce.service.impl.SalesforceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static void executeAdd(long companyId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		getService().executeAdd(companyId, messages);
	}

	public static java.lang.String executeAdd(long companyId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		return getService().executeAdd(companyId, message);
	}

	public static void executeAddOrUpdate(long companyId,
		java.lang.String externalId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		getService().executeAddOrUpdate(companyId, externalId, messages);
	}

	public static void executeAddOrUpdate(long companyId,
		java.lang.String externalId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		getService().executeAddOrUpdate(companyId, externalId, message);
	}

	public static void executeDelete(long companyId,
		java.util.List<java.lang.String> objectIds)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		getService().executeDelete(companyId, objectIds);
	}

	public static boolean executeDelete(long companyId,
		java.lang.String objectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		return getService().executeDelete(companyId, objectId);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch executeQuery(
		long companyId, java.lang.String queryString)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().executeQuery(companyId, queryString);
	}

	public static com.liferay.portal.kernel.messaging.Message executeQuery(
		long companyId, java.lang.String objectId, java.lang.String objectType,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .executeQuery(companyId, objectId, objectType, fieldNames);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch executeQueryMore(
		long companyId, java.lang.String queryLocator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().executeQueryMore(companyId, queryLocator);
	}

	public static com.liferay.portal.kernel.messaging.MessageBatch executeSearch(
		long companyId, java.lang.String searchString)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().executeSearch(companyId, searchString);
	}

	public static void executeUpdate(long companyId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		getService().executeUpdate(companyId, messages);
	}

	public static void executeUpdate(long companyId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		getService().executeUpdate(companyId, message);
	}

	public static void clearService() {
		_service = null;
	}

	public static SalesforceLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SalesforceLocalService.class.getName());

			if (invokableLocalService instanceof SalesforceLocalService) {
				_service = (SalesforceLocalService)invokableLocalService;
			}
			else {
				_service = new SalesforceLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SalesforceLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SalesforceLocalService service) {
	}

	private static SalesforceLocalService _service;
}