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

package com.liferay.saml.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.saml.model.SamlSpSession;

import java.util.List;

/**
 * The persistence utility for the saml sp session service. This utility wraps {@link SamlSpSessionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpSessionPersistence
 * @see SamlSpSessionPersistenceImpl
 * @generated
 */
public class SamlSpSessionUtil {
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
	public static void clearCache(SamlSpSession samlSpSession) {
		getPersistence().clearCache(samlSpSession);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SamlSpSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SamlSpSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SamlSpSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SamlSpSession update(SamlSpSession samlSpSession)
		throws SystemException {
		return getPersistence().update(samlSpSession);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SamlSpSession update(SamlSpSession samlSpSession,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(samlSpSession, serviceContext);
	}

	/**
	* Caches the saml sp session in the entity cache if it is enabled.
	*
	* @param samlSpSession the saml sp session
	*/
	public static void cacheResult(
		com.liferay.saml.model.SamlSpSession samlSpSession) {
		getPersistence().cacheResult(samlSpSession);
	}

	/**
	* Caches the saml sp sessions in the entity cache if it is enabled.
	*
	* @param samlSpSessions the saml sp sessions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.saml.model.SamlSpSession> samlSpSessions) {
		getPersistence().cacheResult(samlSpSessions);
	}

	/**
	* Creates a new saml sp session with the primary key. Does not add the saml sp session to the database.
	*
	* @param samlSpSessionId the primary key for the new saml sp session
	* @return the new saml sp session
	*/
	public static com.liferay.saml.model.SamlSpSession create(
		long samlSpSessionId) {
		return getPersistence().create(samlSpSessionId);
	}

	/**
	* Removes the saml sp session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpSessionId the primary key of the saml sp session
	* @return the saml sp session that was removed
	* @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession remove(
		long samlSpSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpSessionException {
		return getPersistence().remove(samlSpSessionId);
	}

	public static com.liferay.saml.model.SamlSpSession updateImpl(
		com.liferay.saml.model.SamlSpSession samlSpSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(samlSpSession);
	}

	/**
	* Returns the saml sp session with the primary key or throws a {@link com.liferay.saml.NoSuchSpSessionException} if it could not be found.
	*
	* @param samlSpSessionId the primary key of the saml sp session
	* @return the saml sp session
	* @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession findByPrimaryKey(
		long samlSpSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpSessionException {
		return getPersistence().findByPrimaryKey(samlSpSessionId);
	}

	/**
	* Returns the saml sp session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samlSpSessionId the primary key of the saml sp session
	* @return the saml sp session, or <code>null</code> if a saml sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession fetchByPrimaryKey(
		long samlSpSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(samlSpSessionId);
	}

	/**
	* Returns all the saml sp sessions where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @return the matching saml sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpSession> findByNameIdValue(
		java.lang.String nameIdValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByNameIdValue(nameIdValue);
	}

	/**
	* Returns a range of all the saml sp sessions where nameIdValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param nameIdValue the name ID value
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @return the range of matching saml sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpSession> findByNameIdValue(
		java.lang.String nameIdValue, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByNameIdValue(nameIdValue, start, end);
	}

	/**
	* Returns an ordered range of all the saml sp sessions where nameIdValue = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param nameIdValue the name ID value
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching saml sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpSession> findByNameIdValue(
		java.lang.String nameIdValue, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByNameIdValue(nameIdValue, start, end, orderByComparator);
	}

	/**
	* Returns the first saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml sp session
	* @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession findByNameIdValue_First(
		java.lang.String nameIdValue,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpSessionException {
		return getPersistence()
				   .findByNameIdValue_First(nameIdValue, orderByComparator);
	}

	/**
	* Returns the first saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession fetchByNameIdValue_First(
		java.lang.String nameIdValue,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByNameIdValue_First(nameIdValue, orderByComparator);
	}

	/**
	* Returns the last saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml sp session
	* @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession findByNameIdValue_Last(
		java.lang.String nameIdValue,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpSessionException {
		return getPersistence()
				   .findByNameIdValue_Last(nameIdValue, orderByComparator);
	}

	/**
	* Returns the last saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession fetchByNameIdValue_Last(
		java.lang.String nameIdValue,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByNameIdValue_Last(nameIdValue, orderByComparator);
	}

	/**
	* Returns the saml sp sessions before and after the current saml sp session in the ordered set where nameIdValue = &#63;.
	*
	* @param samlSpSessionId the primary key of the current saml sp session
	* @param nameIdValue the name ID value
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next saml sp session
	* @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession[] findByNameIdValue_PrevAndNext(
		long samlSpSessionId, java.lang.String nameIdValue,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpSessionException {
		return getPersistence()
				   .findByNameIdValue_PrevAndNext(samlSpSessionId, nameIdValue,
			orderByComparator);
	}

	/**
	* Returns the saml sp session where jSessionId = &#63; or throws a {@link com.liferay.saml.NoSuchSpSessionException} if it could not be found.
	*
	* @param jSessionId the j session ID
	* @return the matching saml sp session
	* @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession findByJSessionId(
		java.lang.String jSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpSessionException {
		return getPersistence().findByJSessionId(jSessionId);
	}

	/**
	* Returns the saml sp session where jSessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param jSessionId the j session ID
	* @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession fetchByJSessionId(
		java.lang.String jSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByJSessionId(jSessionId);
	}

	/**
	* Returns the saml sp session where jSessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param jSessionId the j session ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession fetchByJSessionId(
		java.lang.String jSessionId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByJSessionId(jSessionId, retrieveFromCache);
	}

	/**
	* Returns all the saml sp sessions.
	*
	* @return the saml sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the saml sp sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @return the range of saml sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the saml sp sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of saml sp sessions
	* @param end the upper bound of the range of saml sp sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of saml sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the saml sp sessions where nameIdValue = &#63; from the database.
	*
	* @param nameIdValue the name ID value
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByNameIdValue(java.lang.String nameIdValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByNameIdValue(nameIdValue);
	}

	/**
	* Removes the saml sp session where jSessionId = &#63; from the database.
	*
	* @param jSessionId the j session ID
	* @return the saml sp session that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpSession removeByJSessionId(
		java.lang.String jSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpSessionException {
		return getPersistence().removeByJSessionId(jSessionId);
	}

	/**
	* Removes all the saml sp sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of saml sp sessions where nameIdValue = &#63;.
	*
	* @param nameIdValue the name ID value
	* @return the number of matching saml sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByNameIdValue(java.lang.String nameIdValue)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByNameIdValue(nameIdValue);
	}

	/**
	* Returns the number of saml sp sessions where jSessionId = &#63;.
	*
	* @param jSessionId the j session ID
	* @return the number of matching saml sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByJSessionId(java.lang.String jSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByJSessionId(jSessionId);
	}

	/**
	* Returns the number of saml sp sessions.
	*
	* @return the number of saml sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SamlSpSessionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SamlSpSessionPersistence)PortletBeanLocatorUtil.locate(com.liferay.saml.service.ClpSerializer.getServletContextName(),
					SamlSpSessionPersistence.class.getName());

			ReferenceRegistry.registerReference(SamlSpSessionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(SamlSpSessionPersistence persistence) {
	}

	private static SamlSpSessionPersistence _persistence;
}