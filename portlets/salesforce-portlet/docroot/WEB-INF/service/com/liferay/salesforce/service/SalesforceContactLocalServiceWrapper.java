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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SalesforceContactLocalService}.
 *
 * @author Michael C. Han
 * @see SalesforceContactLocalService
 * @generated
 */
public class SalesforceContactLocalServiceWrapper
	implements SalesforceContactLocalService,
		ServiceWrapper<SalesforceContactLocalService> {
	public SalesforceContactLocalServiceWrapper(
		SalesforceContactLocalService salesforceContactLocalService) {
		_salesforceContactLocalService = salesforceContactLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _salesforceContactLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_salesforceContactLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _salesforceContactLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getContactsByAccountId(
		long companyId, java.lang.String accountId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceContactLocalService.getContactsByAccountId(companyId,
			accountId, fieldNames);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getContactsByOwnerId(
		long companyId, java.lang.String userId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceContactLocalService.getContactsByOwnerId(companyId,
			userId, fieldNames);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getContactsByUserName(
		long companyId, java.lang.String userName,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceContactLocalService.getContactsByUserName(companyId,
			userName, fieldNames);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SalesforceContactLocalService getWrappedSalesforceContactLocalService() {
		return _salesforceContactLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSalesforceContactLocalService(
		SalesforceContactLocalService salesforceContactLocalService) {
		_salesforceContactLocalService = salesforceContactLocalService;
	}

	@Override
	public SalesforceContactLocalService getWrappedService() {
		return _salesforceContactLocalService;
	}

	@Override
	public void setWrappedService(
		SalesforceContactLocalService salesforceContactLocalService) {
		_salesforceContactLocalService = salesforceContactLocalService;
	}

	private SalesforceContactLocalService _salesforceContactLocalService;
}