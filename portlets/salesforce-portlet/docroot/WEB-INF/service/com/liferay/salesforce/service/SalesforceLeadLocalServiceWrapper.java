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
 * Provides a wrapper for {@link SalesforceLeadLocalService}.
 *
 * @author Michael C. Han
 * @see SalesforceLeadLocalService
 * @generated
 */
public class SalesforceLeadLocalServiceWrapper
	implements SalesforceLeadLocalService,
		ServiceWrapper<SalesforceLeadLocalService> {
	public SalesforceLeadLocalServiceWrapper(
		SalesforceLeadLocalService salesforceLeadLocalService) {
		_salesforceLeadLocalService = salesforceLeadLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _salesforceLeadLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_salesforceLeadLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _salesforceLeadLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getLeadsByCountry(
		long companyId, java.lang.String country,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _salesforceLeadLocalService.getLeadsByCountry(companyId,
			country, fieldNames);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getLeadsBySource(
		long companyId, java.lang.String source,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _salesforceLeadLocalService.getLeadsBySource(companyId, source,
			fieldNames);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getLeadsByStatus(
		long companyId, java.lang.String status,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _salesforceLeadLocalService.getLeadsByStatus(companyId, status,
			fieldNames);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getLeadsByUserId(
		long companyId, java.lang.String userId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceLeadLocalService.getLeadsByUserId(companyId, userId,
			fieldNames);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getLeadsByUserName(
		long companyId, java.lang.String userName,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceLeadLocalService.getLeadsByUserName(companyId,
			userName, fieldNames);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SalesforceLeadLocalService getWrappedSalesforceLeadLocalService() {
		return _salesforceLeadLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSalesforceLeadLocalService(
		SalesforceLeadLocalService salesforceLeadLocalService) {
		_salesforceLeadLocalService = salesforceLeadLocalService;
	}

	@Override
	public SalesforceLeadLocalService getWrappedService() {
		return _salesforceLeadLocalService;
	}

	@Override
	public void setWrappedService(
		SalesforceLeadLocalService salesforceLeadLocalService) {
		_salesforceLeadLocalService = salesforceLeadLocalService;
	}

	private SalesforceLeadLocalService _salesforceLeadLocalService;
}