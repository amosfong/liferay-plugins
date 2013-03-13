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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.saml.model.SamlIdpSsoSession;

/**
 * The persistence interface for the saml idp sso session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSsoSessionPersistenceImpl
 * @see SamlIdpSsoSessionUtil
 * @generated
 */
public interface SamlIdpSsoSessionPersistence extends BasePersistence<SamlIdpSsoSession> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SamlIdpSsoSessionUtil} to access the saml idp sso session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the saml idp sso session where samlIdpSsoSessionKey = &#63; or throws a {@link com.liferay.saml.NoSuchIdpSsoSessionException} if it could not be found.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @return the matching saml idp sso session
	* @throws com.liferay.saml.NoSuchIdpSsoSessionException if a matching saml idp sso session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSsoSession findBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSsoSessionException;

	/**
	* Returns the saml idp sso session where samlIdpSsoSessionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @return the matching saml idp sso session, or <code>null</code> if a matching saml idp sso session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSsoSession fetchBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the saml idp sso session where samlIdpSsoSessionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching saml idp sso session, or <code>null</code> if a matching saml idp sso session could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSsoSession fetchBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the saml idp sso session where samlIdpSsoSessionKey = &#63; from the database.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @return the saml idp sso session that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSsoSession removeBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSsoSessionException;

	/**
	* Returns the number of saml idp sso sessions where samlIdpSsoSessionKey = &#63;.
	*
	* @param samlIdpSsoSessionKey the saml idp sso session key
	* @return the number of matching saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countBySamlIdpSsoSessionKey(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the saml idp sso session in the entity cache if it is enabled.
	*
	* @param samlIdpSsoSession the saml idp sso session
	*/
	public void cacheResult(
		com.liferay.saml.model.SamlIdpSsoSession samlIdpSsoSession);

	/**
	* Caches the saml idp sso sessions in the entity cache if it is enabled.
	*
	* @param samlIdpSsoSessions the saml idp sso sessions
	*/
	public void cacheResult(
		java.util.List<com.liferay.saml.model.SamlIdpSsoSession> samlIdpSsoSessions);

	/**
	* Creates a new saml idp sso session with the primary key. Does not add the saml idp sso session to the database.
	*
	* @param samlIdpSsoSessionId the primary key for the new saml idp sso session
	* @return the new saml idp sso session
	*/
	public com.liferay.saml.model.SamlIdpSsoSession create(
		long samlIdpSsoSessionId);

	/**
	* Removes the saml idp sso session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSsoSessionId the primary key of the saml idp sso session
	* @return the saml idp sso session that was removed
	* @throws com.liferay.saml.NoSuchIdpSsoSessionException if a saml idp sso session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSsoSession remove(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSsoSessionException;

	public com.liferay.saml.model.SamlIdpSsoSession updateImpl(
		com.liferay.saml.model.SamlIdpSsoSession samlIdpSsoSession)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the saml idp sso session with the primary key or throws a {@link com.liferay.saml.NoSuchIdpSsoSessionException} if it could not be found.
	*
	* @param samlIdpSsoSessionId the primary key of the saml idp sso session
	* @return the saml idp sso session
	* @throws com.liferay.saml.NoSuchIdpSsoSessionException if a saml idp sso session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSsoSession findByPrimaryKey(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSsoSessionException;

	/**
	* Returns the saml idp sso session with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samlIdpSsoSessionId the primary key of the saml idp sso session
	* @return the saml idp sso session, or <code>null</code> if a saml idp sso session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSsoSession fetchByPrimaryKey(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the saml idp sso sessions.
	*
	* @return the saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlIdpSsoSession> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.saml.model.SamlIdpSsoSession> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.saml.model.SamlIdpSsoSession> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the saml idp sso sessions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of saml idp sso sessions.
	*
	* @return the number of saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}