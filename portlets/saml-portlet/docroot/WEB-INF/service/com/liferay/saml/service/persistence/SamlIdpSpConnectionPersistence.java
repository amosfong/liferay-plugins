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

import com.liferay.saml.model.SamlIdpSpConnection;

/**
 * The persistence interface for the saml idp sp connection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSpConnectionPersistenceImpl
 * @see SamlIdpSpConnectionUtil
 * @generated
 */
public interface SamlIdpSpConnectionPersistence extends BasePersistence<SamlIdpSpConnection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SamlIdpSpConnectionUtil} to access the saml idp sp connection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the saml idp sp connections where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the saml idp sp connections where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of saml idp sp connections
	* @param end the upper bound of the range of saml idp sp connections (not inclusive)
	* @return the range of matching saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the saml idp sp connections where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of saml idp sp connections
	* @param end the upper bound of the range of saml idp sp connections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first saml idp sp connection in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml idp sp connection
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException;

	/**
	* Returns the first saml idp sp connection in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml idp sp connection, or <code>null</code> if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last saml idp sp connection in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml idp sp connection
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException;

	/**
	* Returns the last saml idp sp connection in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml idp sp connection, or <code>null</code> if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the saml idp sp connections before and after the current saml idp sp connection in the ordered set where companyId = &#63;.
	*
	* @param samlIdpSpConnectionId the primary key of the current saml idp sp connection
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next saml idp sp connection
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a saml idp sp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection[] findByCompanyId_PrevAndNext(
		long samlIdpSpConnectionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException;

	/**
	* Removes all the saml idp sp connections where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of saml idp sp connections where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the saml idp sp connection where companyId = &#63; and samlSpEntityId = &#63; or throws a {@link com.liferay.saml.NoSuchIdpSpConnectionException} if it could not be found.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the matching saml idp sp connection
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection findByC_SSEI(
		long companyId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException;

	/**
	* Returns the saml idp sp connection where companyId = &#63; and samlSpEntityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the matching saml idp sp connection, or <code>null</code> if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection fetchByC_SSEI(
		long companyId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the saml idp sp connection where companyId = &#63; and samlSpEntityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching saml idp sp connection, or <code>null</code> if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection fetchByC_SSEI(
		long companyId, java.lang.String samlSpEntityId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the saml idp sp connection where companyId = &#63; and samlSpEntityId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the saml idp sp connection that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection removeByC_SSEI(
		long companyId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException;

	/**
	* Returns the number of saml idp sp connections where companyId = &#63; and samlSpEntityId = &#63;.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the number of matching saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_SSEI(long companyId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the saml idp sp connection in the entity cache if it is enabled.
	*
	* @param samlIdpSpConnection the saml idp sp connection
	*/
	public void cacheResult(
		com.liferay.saml.model.SamlIdpSpConnection samlIdpSpConnection);

	/**
	* Caches the saml idp sp connections in the entity cache if it is enabled.
	*
	* @param samlIdpSpConnections the saml idp sp connections
	*/
	public void cacheResult(
		java.util.List<com.liferay.saml.model.SamlIdpSpConnection> samlIdpSpConnections);

	/**
	* Creates a new saml idp sp connection with the primary key. Does not add the saml idp sp connection to the database.
	*
	* @param samlIdpSpConnectionId the primary key for the new saml idp sp connection
	* @return the new saml idp sp connection
	*/
	public com.liferay.saml.model.SamlIdpSpConnection create(
		long samlIdpSpConnectionId);

	/**
	* Removes the saml idp sp connection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpConnectionId the primary key of the saml idp sp connection
	* @return the saml idp sp connection that was removed
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a saml idp sp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection remove(
		long samlIdpSpConnectionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException;

	public com.liferay.saml.model.SamlIdpSpConnection updateImpl(
		com.liferay.saml.model.SamlIdpSpConnection samlIdpSpConnection)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the saml idp sp connection with the primary key or throws a {@link com.liferay.saml.NoSuchIdpSpConnectionException} if it could not be found.
	*
	* @param samlIdpSpConnectionId the primary key of the saml idp sp connection
	* @return the saml idp sp connection
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a saml idp sp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection findByPrimaryKey(
		long samlIdpSpConnectionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException;

	/**
	* Returns the saml idp sp connection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samlIdpSpConnectionId the primary key of the saml idp sp connection
	* @return the saml idp sp connection, or <code>null</code> if a saml idp sp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpConnection fetchByPrimaryKey(
		long samlIdpSpConnectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the saml idp sp connections.
	*
	* @return the saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the saml idp sp connections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml idp sp connections
	* @param end the upper bound of the range of saml idp sp connections (not inclusive)
	* @return the range of saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the saml idp sp connections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml idp sp connections
	* @param end the upper bound of the range of saml idp sp connections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the saml idp sp connections from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of saml idp sp connections.
	*
	* @return the number of saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}