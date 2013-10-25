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

import com.liferay.saml.model.SamlIdpSsoSession;

import java.util.List;

/**
 * The persistence utility for the saml idp sso session service. This utility wraps {@link SamlIdpSsoSessionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSsoSessionPersistence
 * @see SamlIdpSsoSessionPersistenceImpl
 * @generated
 */
public class SamlIdpSsoSessionUtil {
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
	public static void clearCache(SamlIdpSsoSession samlIdpSsoSession) {
		getPersistence().clearCache(samlIdpSsoSession);
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
	public static List<SamlIdpSsoSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SamlIdpSsoSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SamlIdpSsoSession> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SamlIdpSsoSession update(SamlIdpSsoSession samlIdpSsoSession)
		throws SystemException {
		return getPersistence().update(samlIdpSsoSession);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SamlIdpSsoSession update(
		SamlIdpSsoSession samlIdpSsoSession, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(samlIdpSsoSession, serviceContext);
	}

	/**
	* Returns the saml idp sso session where samlIdpSsoSessionKey = &#63; or throws a {@link com.liferay.saml.NoSuchIdpSsoSessionException} if it could not be found.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @return the matching saml idp sso session
	* @throws com.liferay.saml.NoSuchIdpSsoSessionException if a matching saml idp sso session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSsoSession findBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSsoSessionException {
		return getPersistence().findBySamlIdpSsoSessionKey(samlIdpSsoSessionKey);
	}

	/**
	* Returns the saml idp sso session where samlIdpSsoSessionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @return the matching saml idp sso session, or <code>null</code> if a matching saml idp sso session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSsoSession fetchBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBySamlIdpSsoSessionKey(samlIdpSsoSessionKey);
	}

	/**
	* Returns the saml idp sso session where samlIdpSsoSessionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching saml idp sso session, or <code>null</code> if a matching saml idp sso session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSsoSession fetchBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySamlIdpSsoSessionKey(samlIdpSsoSessionKey,
			retrieveFromCache);
	}

	/**
	* Removes the saml idp sso session where samlIdpSsoSessionKey = &#63; from the database.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @return the saml idp sso session that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSsoSession removeBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSsoSessionException {
		return getPersistence()
				   .removeBySamlIdpSsoSessionKey(samlIdpSsoSessionKey);
	}

	/**
	* Returns the number of saml idp sso sessions where samlIdpSsoSessionKey = &#63;.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @return the number of matching saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySamlIdpSsoSessionKey(samlIdpSsoSessionKey);
	}

	/**
	* Caches the saml idp sso session in the entity cache if it is enabled.
	*
	* @param samlIdpSsoSession the saml idp sso session
	*/
	public static void cacheResult(
		com.liferay.saml.model.SamlIdpSsoSession samlIdpSsoSession) {
		getPersistence().cacheResult(samlIdpSsoSession);
	}

	/**
	* Caches the saml idp sso sessions in the entity cache if it is enabled.
	*
	* @param samlIdpSsoSessions the saml idp sso sessions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.saml.model.SamlIdpSsoSession> samlIdpSsoSessions) {
		getPersistence().cacheResult(samlIdpSsoSessions);
	}

	/**
	* Creates a new saml idp sso session with the primary key. Does not add the saml idp sso session to the database.
	*
	* @param samlIdpSsoSessionId the primary key for the new saml idp sso session
	* @return the new saml idp sso session
	*/
	public static com.liferay.saml.model.SamlIdpSsoSession create(
		long samlIdpSsoSessionId) {
		return getPersistence().create(samlIdpSsoSessionId);
	}

	/**
	* Removes the saml idp sso session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSsoSessionId the primary key of the saml idp sso session
	* @return the saml idp sso session that was removed
	* @throws com.liferay.saml.NoSuchIdpSsoSessionException if a saml idp sso session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSsoSession remove(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSsoSessionException {
		return getPersistence().remove(samlIdpSsoSessionId);
	}

	public static com.liferay.saml.model.SamlIdpSsoSession updateImpl(
		com.liferay.saml.model.SamlIdpSsoSession samlIdpSsoSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(samlIdpSsoSession);
	}

	/**
	* Returns the saml idp sso session with the primary key or throws a {@link com.liferay.saml.NoSuchIdpSsoSessionException} if it could not be found.
	*
	* @param samlIdpSsoSessionId the primary key of the saml idp sso session
	* @return the saml idp sso session
	* @throws com.liferay.saml.NoSuchIdpSsoSessionException if a saml idp sso session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSsoSession findByPrimaryKey(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSsoSessionException {
		return getPersistence().findByPrimaryKey(samlIdpSsoSessionId);
	}

	/**
	* Returns the saml idp sso session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samlIdpSsoSessionId the primary key of the saml idp sso session
	* @return the saml idp sso session, or <code>null</code> if a saml idp sso session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSsoSession fetchByPrimaryKey(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(samlIdpSsoSessionId);
	}

	/**
	* Returns all the saml idp sso sessions.
	*
	* @return the saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSsoSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the saml idp sso sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSsoSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml idp sso sessions
	* @param end the upper bound of the range of saml idp sso sessions (not inclusive)
	* @return the range of saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSsoSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the saml idp sso sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSsoSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml idp sso sessions
	* @param end the upper bound of the range of saml idp sso sessions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSsoSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the saml idp sso sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of saml idp sso sessions.
	*
	* @return the number of saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SamlIdpSsoSessionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SamlIdpSsoSessionPersistence)PortletBeanLocatorUtil.locate(com.liferay.saml.service.ClpSerializer.getServletContextName(),
					SamlIdpSsoSessionPersistence.class.getName());

			ReferenceRegistry.registerReference(SamlIdpSsoSessionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(SamlIdpSsoSessionPersistence persistence) {
	}

	private static SamlIdpSsoSessionPersistence _persistence;
}