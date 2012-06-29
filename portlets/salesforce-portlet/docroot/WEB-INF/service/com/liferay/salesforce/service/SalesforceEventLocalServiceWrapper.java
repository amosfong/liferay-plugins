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

package com.liferay.salesforce.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SalesforceEventLocalService}.
 * </p>
 *
 * @author    Michael C. Han
 * @see       SalesforceEventLocalService
 * @generated
 */
public class SalesforceEventLocalServiceWrapper
	implements SalesforceEventLocalService,
		ServiceWrapper<SalesforceEventLocalService> {
	public SalesforceEventLocalServiceWrapper(
		SalesforceEventLocalService salesforceEventLocalService) {
		_salesforceEventLocalService = salesforceEventLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _salesforceEventLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_salesforceEventLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _salesforceEventLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.portal.kernel.messaging.MessageBatch getEventsByAccountId(
		long companyId, java.lang.String accountId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceEventLocalService.getEventsByAccountId(companyId,
			accountId, fieldNames);
	}

	public com.liferay.portal.kernel.messaging.MessageBatch getEventsByUser(
		long companyId, java.lang.String userId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceEventLocalService.getEventsByUser(companyId, userId,
			fieldNames);
	}

	public com.liferay.portal.kernel.messaging.MessageBatch getEventsByUserName(
		long companyId, java.lang.String userName,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceEventLocalService.getEventsByUserName(companyId,
			userName, fieldNames);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SalesforceEventLocalService getWrappedSalesforceEventLocalService() {
		return _salesforceEventLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSalesforceEventLocalService(
		SalesforceEventLocalService salesforceEventLocalService) {
		_salesforceEventLocalService = salesforceEventLocalService;
	}

	public SalesforceEventLocalService getWrappedService() {
		return _salesforceEventLocalService;
	}

	public void setWrappedService(
		SalesforceEventLocalService salesforceEventLocalService) {
		_salesforceEventLocalService = salesforceEventLocalService;
	}

	private SalesforceEventLocalService _salesforceEventLocalService;
}