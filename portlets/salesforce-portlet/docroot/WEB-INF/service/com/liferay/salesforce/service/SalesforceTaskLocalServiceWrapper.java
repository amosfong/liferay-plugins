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
 * This class is a wrapper for {@link SalesforceTaskLocalService}.
 * </p>
 *
 * @author    Michael C. Han
 * @see       SalesforceTaskLocalService
 * @generated
 */
public class SalesforceTaskLocalServiceWrapper
	implements SalesforceTaskLocalService,
		ServiceWrapper<SalesforceTaskLocalService> {
	public SalesforceTaskLocalServiceWrapper(
		SalesforceTaskLocalService salesforceTaskLocalService) {
		_salesforceTaskLocalService = salesforceTaskLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _salesforceTaskLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_salesforceTaskLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _salesforceTaskLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.portal.kernel.messaging.MessageBatch getTasksByAccountId(
		long companyId, java.lang.String accountId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceTaskLocalService.getTasksByAccountId(companyId,
			accountId, fieldNames);
	}

	public com.liferay.portal.kernel.messaging.MessageBatch getTasksByUserId(
		long companyId, java.lang.String userId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceTaskLocalService.getTasksByUserId(companyId, userId,
			fieldNames);
	}

	public com.liferay.portal.kernel.messaging.MessageBatch getTasksByUserName(
		long companyId, java.lang.String userName,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceTaskLocalService.getTasksByUserName(companyId,
			userName, fieldNames);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SalesforceTaskLocalService getWrappedSalesforceTaskLocalService() {
		return _salesforceTaskLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSalesforceTaskLocalService(
		SalesforceTaskLocalService salesforceTaskLocalService) {
		_salesforceTaskLocalService = salesforceTaskLocalService;
	}

	public SalesforceTaskLocalService getWrappedService() {
		return _salesforceTaskLocalService;
	}

	public void setWrappedService(
		SalesforceTaskLocalService salesforceTaskLocalService) {
		_salesforceTaskLocalService = salesforceTaskLocalService;
	}

	private SalesforceTaskLocalService _salesforceTaskLocalService;
}