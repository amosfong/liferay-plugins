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

package com.liferay.saml.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.saml.model.SamlIdpSpSession;

import java.util.List;

/**
 * The persistence utility for the saml idp sp session service. This utility wraps {@link SamlIdpSpSessionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSpSessionPersistence
 * @see SamlIdpSpSessionPersistenceImpl
 * @generated
 */
public class SamlIdpSpSessionUtil {
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
	public static void clearCache(SamlIdpSpSession samlIdpSpSession) {
		getPersistence().clearCache(samlIdpSpSession);
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
	public static List<SamlIdpSpSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SamlIdpSpSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SamlIdpSpSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SamlIdpSpSession update(SamlIdpSpSession samlIdpSpSession)
		throws SystemException {
		return getPersistence().update(samlIdpSpSession);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SamlIdpSpSession update(SamlIdpSpSession samlIdpSpSession,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(samlIdpSpSession, serviceContext);
	}

	/**
	* Returns all the saml idp sp sessions where samlIdpSsoSessionId = &#63;.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @return the matching saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSpSession> findBySamlIdpSsoSessionId(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySamlIdpSsoSessionId(samlIdpSsoSessionId);
	}

	/**
	* Returns a range of all the saml idp sp sessions where samlIdpSsoSessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param start the lower bound of the range of saml idp sp sessions
	* @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	* @return the range of matching saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSpSession> findBySamlIdpSsoSessionId(
		long samlIdpSsoSessionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySamlIdpSsoSessionId(samlIdpSsoSessionId, start, end);
	}

	/**
	* Returns an ordered range of all the saml idp sp sessions where samlIdpSsoSessionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param start the lower bound of the range of saml idp sp sessions
	* @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSpSession> findBySamlIdpSsoSessionId(
		long samlIdpSsoSessionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySamlIdpSsoSessionId(samlIdpSsoSessionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml idp sp session
	* @throws com.liferay.saml.NoSuchIdpSpSessionException if a matching saml idp sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession findBySamlIdpSsoSessionId_First(
		long samlIdpSsoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpSessionException {
		return getPersistence()
				   .findBySamlIdpSsoSessionId_First(samlIdpSsoSessionId,
			orderByComparator);
	}

	/**
	* Returns the first saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession fetchBySamlIdpSsoSessionId_First(
		long samlIdpSsoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySamlIdpSsoSessionId_First(samlIdpSsoSessionId,
			orderByComparator);
	}

	/**
	* Returns the last saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml idp sp session
	* @throws com.liferay.saml.NoSuchIdpSpSessionException if a matching saml idp sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession findBySamlIdpSsoSessionId_Last(
		long samlIdpSsoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpSessionException {
		return getPersistence()
				   .findBySamlIdpSsoSessionId_Last(samlIdpSsoSessionId,
			orderByComparator);
	}

	/**
	* Returns the last saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession fetchBySamlIdpSsoSessionId_Last(
		long samlIdpSsoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySamlIdpSsoSessionId_Last(samlIdpSsoSessionId,
			orderByComparator);
	}

	/**
	* Returns the saml idp sp sessions before and after the current saml idp sp session in the ordered set where samlIdpSsoSessionId = &#63;.
	*
	* @param samlIdpSpSessionId the primary key of the current saml idp sp session
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next saml idp sp session
	* @throws com.liferay.saml.NoSuchIdpSpSessionException if a saml idp sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession[] findBySamlIdpSsoSessionId_PrevAndNext(
		long samlIdpSpSessionId, long samlIdpSsoSessionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpSessionException {
		return getPersistence()
				   .findBySamlIdpSsoSessionId_PrevAndNext(samlIdpSpSessionId,
			samlIdpSsoSessionId, orderByComparator);
	}

	/**
	* Removes all the saml idp sp sessions where samlIdpSsoSessionId = &#63; from the database.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySamlIdpSsoSessionId(long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySamlIdpSsoSessionId(samlIdpSsoSessionId);
	}

	/**
	* Returns the number of saml idp sp sessions where samlIdpSsoSessionId = &#63;.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @return the number of matching saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySamlIdpSsoSessionId(long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySamlIdpSsoSessionId(samlIdpSsoSessionId);
	}

	/**
	* Returns the saml idp sp session where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63; or throws a {@link com.liferay.saml.NoSuchIdpSpSessionException} if it could not be found.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the matching saml idp sp session
	* @throws com.liferay.saml.NoSuchIdpSpSessionException if a matching saml idp sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession findBySISSI_SSEI(
		long samlIdpSsoSessionId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpSessionException {
		return getPersistence()
				   .findBySISSI_SSEI(samlIdpSsoSessionId, samlSpEntityId);
	}

	/**
	* Returns the saml idp sp session where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession fetchBySISSI_SSEI(
		long samlIdpSsoSessionId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySISSI_SSEI(samlIdpSsoSessionId, samlSpEntityId);
	}

	/**
	* Returns the saml idp sp session where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param samlSpEntityId the saml sp entity ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching saml idp sp session, or <code>null</code> if a matching saml idp sp session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession fetchBySISSI_SSEI(
		long samlIdpSsoSessionId, java.lang.String samlSpEntityId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySISSI_SSEI(samlIdpSsoSessionId, samlSpEntityId,
			retrieveFromCache);
	}

	/**
	* Removes the saml idp sp session where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63; from the database.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the saml idp sp session that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession removeBySISSI_SSEI(
		long samlIdpSsoSessionId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpSessionException {
		return getPersistence()
				   .removeBySISSI_SSEI(samlIdpSsoSessionId, samlSpEntityId);
	}

	/**
	* Returns the number of saml idp sp sessions where samlIdpSsoSessionId = &#63; and samlSpEntityId = &#63;.
	*
	* @param samlIdpSsoSessionId the saml idp sso session ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the number of matching saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySISSI_SSEI(long samlIdpSsoSessionId,
		java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBySISSI_SSEI(samlIdpSsoSessionId, samlSpEntityId);
	}

	/**
	* Caches the saml idp sp session in the entity cache if it is enabled.
	*
	* @param samlIdpSpSession the saml idp sp session
	*/
	public static void cacheResult(
		com.liferay.saml.model.SamlIdpSpSession samlIdpSpSession) {
		getPersistence().cacheResult(samlIdpSpSession);
	}

	/**
	* Caches the saml idp sp sessions in the entity cache if it is enabled.
	*
	* @param samlIdpSpSessions the saml idp sp sessions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.saml.model.SamlIdpSpSession> samlIdpSpSessions) {
		getPersistence().cacheResult(samlIdpSpSessions);
	}

	/**
	* Creates a new saml idp sp session with the primary key. Does not add the saml idp sp session to the database.
	*
	* @param samlIdpSpSessionId the primary key for the new saml idp sp session
	* @return the new saml idp sp session
	*/
	public static com.liferay.saml.model.SamlIdpSpSession create(
		long samlIdpSpSessionId) {
		return getPersistence().create(samlIdpSpSessionId);
	}

	/**
	* Removes the saml idp sp session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpSessionId the primary key of the saml idp sp session
	* @return the saml idp sp session that was removed
	* @throws com.liferay.saml.NoSuchIdpSpSessionException if a saml idp sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession remove(
		long samlIdpSpSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpSessionException {
		return getPersistence().remove(samlIdpSpSessionId);
	}

	public static com.liferay.saml.model.SamlIdpSpSession updateImpl(
		com.liferay.saml.model.SamlIdpSpSession samlIdpSpSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(samlIdpSpSession);
	}

	/**
	* Returns the saml idp sp session with the primary key or throws a {@link com.liferay.saml.NoSuchIdpSpSessionException} if it could not be found.
	*
	* @param samlIdpSpSessionId the primary key of the saml idp sp session
	* @return the saml idp sp session
	* @throws com.liferay.saml.NoSuchIdpSpSessionException if a saml idp sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession findByPrimaryKey(
		long samlIdpSpSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpSessionException {
		return getPersistence().findByPrimaryKey(samlIdpSpSessionId);
	}

	/**
	* Returns the saml idp sp session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samlIdpSpSessionId the primary key of the saml idp sp session
	* @return the saml idp sp session, or <code>null</code> if a saml idp sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpSession fetchByPrimaryKey(
		long samlIdpSpSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(samlIdpSpSessionId);
	}

	/**
	* Returns all the saml idp sp sessions.
	*
	* @return the saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSpSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the saml idp sp sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml idp sp sessions
	* @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	* @return the range of saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSpSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the saml idp sp sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml idp sp sessions
	* @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSpSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the saml idp sp sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of saml idp sp sessions.
	*
	* @return the number of saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SamlIdpSpSessionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SamlIdpSpSessionPersistence)PortletBeanLocatorUtil.locate(com.liferay.saml.service.ClpSerializer.getServletContextName(),
					SamlIdpSpSessionPersistence.class.getName());

			ReferenceRegistry.registerReference(SamlIdpSpSessionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(SamlIdpSpSessionPersistence persistence) {
	}

	private static SamlIdpSpSessionPersistence _persistence;
}