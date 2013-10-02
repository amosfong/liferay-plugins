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

package com.liferay.portal.audit.service.persistence;

import com.liferay.portal.audit.model.AuditEvent;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the audit event service. This utility wraps {@link AuditEventPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEventPersistence
 * @see AuditEventPersistenceImpl
 * @generated
 */
public class AuditEventUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(AuditEvent auditEvent) {
		getPersistence().clearCache(auditEvent);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuditEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditEvent> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static AuditEvent update(AuditEvent auditEvent)
		throws SystemException {
		return getPersistence().update(auditEvent);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static AuditEvent update(AuditEvent auditEvent,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(auditEvent, serviceContext);
	}

	/**
	* Returns all the audit events where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching audit events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.audit.model.AuditEvent> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	/**
	* Returns a range of all the audit events where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.audit.model.impl.AuditEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of audit events
	* @param end the upper bound of the range of audit events (not inclusive)
	* @return the range of matching audit events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.audit.model.AuditEvent> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	/**
	* Returns an ordered range of all the audit events where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.audit.model.impl.AuditEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of audit events
	* @param end the upper bound of the range of audit events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.audit.model.AuditEvent> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first audit event in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit event
	* @throws com.liferay.portal.audit.NoSuchEventException if a matching audit event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.audit.model.AuditEvent findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.audit.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first audit event in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit event, or <code>null</code> if a matching audit event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.audit.model.AuditEvent fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last audit event in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit event
	* @throws com.liferay.portal.audit.NoSuchEventException if a matching audit event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.audit.model.AuditEvent findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.audit.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last audit event in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit event, or <code>null</code> if a matching audit event could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.audit.model.AuditEvent fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the audit events before and after the current audit event in the ordered set where companyId = &#63;.
	*
	* @param auditEventId the primary key of the current audit event
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit event
	* @throws com.liferay.portal.audit.NoSuchEventException if a audit event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.audit.model.AuditEvent[] findByCompanyId_PrevAndNext(
		long auditEventId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.audit.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(auditEventId, companyId,
			orderByComparator);
	}

	/**
	* Removes all the audit events where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of audit events where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching audit events
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Caches the audit event in the entity cache if it is enabled.
	*
	* @param auditEvent the audit event
	*/
	public static void cacheResult(
		com.liferay.portal.audit.model.AuditEvent auditEvent) {
		getPersistence().cacheResult(auditEvent);
	}

	/**
	* Caches the audit events in the entity cache if it is enabled.
	*
	* @param auditEvents the audit events
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.audit.model.AuditEvent> auditEvents) {
		getPersistence().cacheResult(auditEvents);
	}

	/**
	* Creates a new audit event with the primary key. Does not add the audit event to the database.
	*
	* @param auditEventId the primary key for the new audit event
	* @return the new audit event
	*/
	public static com.liferay.portal.audit.model.AuditEvent create(
		long auditEventId) {
		return getPersistence().create(auditEventId);
	}

	/**
	* Removes the audit event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEventId the primary key of the audit event
	* @return the audit event that was removed
	* @throws com.liferay.portal.audit.NoSuchEventException if a audit event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.audit.model.AuditEvent remove(
		long auditEventId)
		throws com.liferay.portal.audit.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(auditEventId);
	}

	public static com.liferay.portal.audit.model.AuditEvent updateImpl(
		com.liferay.portal.audit.model.AuditEvent auditEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(auditEvent);
	}

	/**
	* Returns the audit event with the primary key or throws a {@link com.liferay.portal.audit.NoSuchEventException} if it could not be found.
	*
	* @param auditEventId the primary key of the audit event
	* @return the audit event
	* @throws com.liferay.portal.audit.NoSuchEventException if a audit event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.audit.model.AuditEvent findByPrimaryKey(
		long auditEventId)
		throws com.liferay.portal.audit.NoSuchEventException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(auditEventId);
	}

	/**
	* Returns the audit event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditEventId the primary key of the audit event
	* @return the audit event, or <code>null</code> if a audit event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.audit.model.AuditEvent fetchByPrimaryKey(
		long auditEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(auditEventId);
	}

	/**
	* Returns all the audit events.
	*
	* @return the audit events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.audit.model.AuditEvent> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.portal.audit.model.AuditEvent> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the audit events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.audit.model.impl.AuditEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit events
	* @param end the upper bound of the range of audit events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.audit.model.AuditEvent> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the audit events from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of audit events.
	*
	* @return the number of audit events
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AuditEventPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AuditEventPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.audit.service.ClpSerializer.getServletContextName(),
					AuditEventPersistence.class.getName());

			ReferenceRegistry.registerReference(AuditEventUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(AuditEventPersistence persistence) {
	}

	private static AuditEventPersistence _persistence;
}