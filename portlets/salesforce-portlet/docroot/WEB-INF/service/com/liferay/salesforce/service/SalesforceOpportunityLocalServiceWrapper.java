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
 * Provides a wrapper for {@link SalesforceOpportunityLocalService}.
 *
 * @author Michael C. Han
 * @see SalesforceOpportunityLocalService
 * @generated
 */
public class SalesforceOpportunityLocalServiceWrapper
	implements SalesforceOpportunityLocalService,
		ServiceWrapper<SalesforceOpportunityLocalService> {
	public SalesforceOpportunityLocalServiceWrapper(
		SalesforceOpportunityLocalService salesforceOpportunityLocalService) {
		_salesforceOpportunityLocalService = salesforceOpportunityLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _salesforceOpportunityLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_salesforceOpportunityLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _salesforceOpportunityLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getOpportunitiesByAccountId(
		long companyId, java.lang.String accountId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceOpportunityLocalService.getOpportunitiesByAccountId(companyId,
			accountId, fieldNames);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getOpportunitiesByUserId(
		long companyId, java.lang.String userId,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceOpportunityLocalService.getOpportunitiesByUserId(companyId,
			userId, fieldNames);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch getOpportunitiesByUserName(
		long companyId, java.lang.String userName,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceOpportunityLocalService.getOpportunitiesByUserName(companyId,
			userName, fieldNames);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SalesforceOpportunityLocalService getWrappedSalesforceOpportunityLocalService() {
		return _salesforceOpportunityLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSalesforceOpportunityLocalService(
		SalesforceOpportunityLocalService salesforceOpportunityLocalService) {
		_salesforceOpportunityLocalService = salesforceOpportunityLocalService;
	}

	@Override
	public SalesforceOpportunityLocalService getWrappedService() {
		return _salesforceOpportunityLocalService;
	}

	@Override
	public void setWrappedService(
		SalesforceOpportunityLocalService salesforceOpportunityLocalService) {
		_salesforceOpportunityLocalService = salesforceOpportunityLocalService;
	}

	private SalesforceOpportunityLocalService _salesforceOpportunityLocalService;
}