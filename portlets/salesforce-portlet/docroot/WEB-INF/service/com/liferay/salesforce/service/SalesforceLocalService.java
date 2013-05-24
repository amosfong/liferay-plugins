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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service interface for Salesforce. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Michael C. Han
 * @see SalesforceLocalServiceUtil
 * @see com.liferay.salesforce.service.base.SalesforceLocalServiceBaseImpl
 * @see com.liferay.salesforce.service.impl.SalesforceLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SalesforceLocalService extends BaseLocalService,
	InvokableLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SalesforceLocalServiceUtil} to access the salesforce local service. Add custom service methods to {@link com.liferay.salesforce.service.impl.SalesforceLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public void executeAdd(long companyId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException;

	public java.lang.String executeAdd(long companyId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException;

	public void executeAddOrUpdate(long companyId, java.lang.String externalId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException;

	public void executeAddOrUpdate(long companyId, java.lang.String externalId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException;

	public void executeDelete(long companyId,
		java.util.List<java.lang.String> objectIds)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException;

	public boolean executeDelete(long companyId, java.lang.String objectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException;

	public com.liferay.portal.kernel.messaging.MessageBatch executeQuery(
		long companyId, java.lang.String queryString)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.messaging.Message executeQuery(
		long companyId, java.lang.String objectId, java.lang.String objectType,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.messaging.MessageBatch executeQueryMore(
		long companyId, java.lang.String queryLocator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.messaging.MessageBatch executeSearch(
		long companyId, java.lang.String searchString)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void executeUpdate(long companyId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException;

	public void executeUpdate(long companyId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException;
}