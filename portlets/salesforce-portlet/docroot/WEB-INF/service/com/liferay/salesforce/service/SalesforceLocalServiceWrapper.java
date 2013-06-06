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
 * Provides a wrapper for {@link SalesforceLocalService}.
 *
 * @author Michael C. Han
 * @see SalesforceLocalService
 * @generated
 */
public class SalesforceLocalServiceWrapper implements SalesforceLocalService,
	ServiceWrapper<SalesforceLocalService> {
	public SalesforceLocalServiceWrapper(
		SalesforceLocalService salesforceLocalService) {
		_salesforceLocalService = salesforceLocalService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _salesforceLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_salesforceLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _salesforceLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public void executeAdd(long companyId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		_salesforceLocalService.executeAdd(companyId, messages);
	}

	@Override
	public java.lang.String executeAdd(long companyId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		return _salesforceLocalService.executeAdd(companyId, message);
	}

	@Override
	public void executeAddOrUpdate(long companyId, java.lang.String externalId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		_salesforceLocalService.executeAddOrUpdate(companyId, externalId,
			messages);
	}

	@Override
	public void executeAddOrUpdate(long companyId, java.lang.String externalId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		_salesforceLocalService.executeAddOrUpdate(companyId, externalId,
			message);
	}

	@Override
	public void executeDelete(long companyId,
		java.util.List<java.lang.String> objectIds)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		_salesforceLocalService.executeDelete(companyId, objectIds);
	}

	@Override
	public boolean executeDelete(long companyId, java.lang.String objectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		return _salesforceLocalService.executeDelete(companyId, objectId);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch executeQuery(
		long companyId, java.lang.String queryString)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _salesforceLocalService.executeQuery(companyId, queryString);
	}

	@Override
	public com.liferay.portal.kernel.messaging.Message executeQuery(
		long companyId, java.lang.String objectId, java.lang.String objectType,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		return _salesforceLocalService.executeQuery(companyId, objectId,
			objectType, fieldNames);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch executeQueryMore(
		long companyId, java.lang.String queryLocator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _salesforceLocalService.executeQueryMore(companyId, queryLocator);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch executeSearch(
		long companyId, java.lang.String searchString)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _salesforceLocalService.executeSearch(companyId, searchString);
	}

	@Override
	public void executeUpdate(long companyId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		_salesforceLocalService.executeUpdate(companyId, messages);
	}

	@Override
	public void executeUpdate(long companyId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		_salesforceLocalService.executeUpdate(companyId, message);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SalesforceLocalService getWrappedSalesforceLocalService() {
		return _salesforceLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSalesforceLocalService(
		SalesforceLocalService salesforceLocalService) {
		_salesforceLocalService = salesforceLocalService;
	}

	@Override
	public SalesforceLocalService getWrappedService() {
		return _salesforceLocalService;
	}

	@Override
	public void setWrappedService(SalesforceLocalService salesforceLocalService) {
		_salesforceLocalService = salesforceLocalService;
	}

	private SalesforceLocalService _salesforceLocalService;
}