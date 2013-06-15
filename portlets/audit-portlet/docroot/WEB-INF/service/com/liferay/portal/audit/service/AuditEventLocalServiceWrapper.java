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

package com.liferay.portal.audit.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuditEventLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEventLocalService
 * @generated
 */
public class AuditEventLocalServiceWrapper implements AuditEventLocalService,
	ServiceWrapper<AuditEventLocalService> {
	public AuditEventLocalServiceWrapper(
		AuditEventLocalService auditEventLocalService) {
		_auditEventLocalService = auditEventLocalService;
	}

	/**
	* Adds the audit event to the database. Also notifies the appropriate model listeners.
	*
	* @param auditEvent the audit event
	* @return the audit event that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.audit.model.AuditEvent addAuditEvent(
		com.liferay.portal.audit.model.AuditEvent auditEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.addAuditEvent(auditEvent);
	}

	/**
	* Creates a new audit event with the primary key. Does not add the audit event to the database.
	*
	* @param auditEventId the primary key for the new audit event
	* @return the new audit event
	*/
	@Override
	public com.liferay.portal.audit.model.AuditEvent createAuditEvent(
		long auditEventId) {
		return _auditEventLocalService.createAuditEvent(auditEventId);
	}

	/**
	* Deletes the audit event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEventId the primary key of the audit event
	* @return the audit event that was removed
	* @throws PortalException if a audit event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.audit.model.AuditEvent deleteAuditEvent(
		long auditEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.deleteAuditEvent(auditEventId);
	}

	/**
	* Deletes the audit event from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEvent the audit event
	* @return the audit event that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.audit.model.AuditEvent deleteAuditEvent(
		com.liferay.portal.audit.model.AuditEvent auditEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.deleteAuditEvent(auditEvent);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _auditEventLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.audit.model.impl.AuditEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.audit.model.impl.AuditEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.audit.model.AuditEvent fetchAuditEvent(
		long auditEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.fetchAuditEvent(auditEventId);
	}

	/**
	* Returns the audit event with the primary key.
	*
	* @param auditEventId the primary key of the audit event
	* @return the audit event
	* @throws PortalException if a audit event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.audit.model.AuditEvent getAuditEvent(
		long auditEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.getAuditEvent(auditEventId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the audit events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.audit.model.impl.AuditEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit events
	* @param end the upper bound of the range of audit events (not inclusive)
	* @return the range of audit events
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.audit.model.AuditEvent> getAuditEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.getAuditEvents(start, end);
	}

	/**
	* Returns the number of audit events.
	*
	* @return the number of audit events
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getAuditEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.getAuditEventsCount();
	}

	/**
	* Updates the audit event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param auditEvent the audit event
	* @return the audit event that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.audit.model.AuditEvent updateAuditEvent(
		com.liferay.portal.audit.model.AuditEvent auditEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.updateAuditEvent(auditEvent);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _auditEventLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_auditEventLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _auditEventLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	@Override
	public com.liferay.portal.audit.model.AuditEvent addAuditEvent(
		com.liferay.portal.kernel.audit.AuditMessage auditMessage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.addAuditEvent(auditMessage);
	}

	@Override
	public java.util.List<com.liferay.portal.audit.model.AuditEvent> getAuditEvents(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.getAuditEvents(companyId, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.portal.audit.model.AuditEvent> getAuditEvents(
		long companyId, long userId, java.lang.String userName,
		java.util.Date createDateGT, java.util.Date createDateLT,
		java.lang.String eventType, java.lang.String className,
		java.lang.String classPK, java.lang.String clientHost,
		java.lang.String clientIP, java.lang.String serverName, int serverPort,
		java.lang.String sessionID, boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.getAuditEvents(companyId, userId,
			userName, createDateGT, createDateLT, eventType, className,
			classPK, clientHost, clientIP, serverName, serverPort, sessionID,
			andSearch, start, end, orderByComparator);
	}

	@Override
	public int getAuditEventsCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.getAuditEventsCount(companyId);
	}

	@Override
	public int getAuditEventsCount(long companyId, long userId,
		java.lang.String userName, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.String eventType,
		java.lang.String className, java.lang.String classPK,
		java.lang.String clientHost, java.lang.String clientIP,
		java.lang.String serverName, int serverPort,
		java.lang.String sessionID, boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _auditEventLocalService.getAuditEventsCount(companyId, userId,
			userName, createDateGT, createDateLT, eventType, className,
			classPK, clientHost, clientIP, serverName, serverPort, sessionID,
			andSearch);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public AuditEventLocalService getWrappedAuditEventLocalService() {
		return _auditEventLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedAuditEventLocalService(
		AuditEventLocalService auditEventLocalService) {
		_auditEventLocalService = auditEventLocalService;
	}

	@Override
	public AuditEventLocalService getWrappedService() {
		return _auditEventLocalService;
	}

	@Override
	public void setWrappedService(AuditEventLocalService auditEventLocalService) {
		_auditEventLocalService = auditEventLocalService;
	}

	private AuditEventLocalService _auditEventLocalService;
}