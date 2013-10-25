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

import com.liferay.saml.model.SamlIdpSpConnection;

import java.util.List;

/**
 * The persistence utility for the saml idp sp connection service. This utility wraps {@link SamlIdpSpConnectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSpConnectionPersistence
 * @see SamlIdpSpConnectionPersistenceImpl
 * @generated
 */
public class SamlIdpSpConnectionUtil {
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
	public static void clearCache(SamlIdpSpConnection samlIdpSpConnection) {
		getPersistence().clearCache(samlIdpSpConnection);
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
	public static List<SamlIdpSpConnection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SamlIdpSpConnection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SamlIdpSpConnection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SamlIdpSpConnection update(
		SamlIdpSpConnection samlIdpSpConnection) throws SystemException {
		return getPersistence().update(samlIdpSpConnection);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SamlIdpSpConnection update(
		SamlIdpSpConnection samlIdpSpConnection, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(samlIdpSpConnection, serviceContext);
	}

	/**
	* Returns all the saml idp sp connections where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

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
	public static java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

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
	public static java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	/**
	* Returns the first saml idp sp connection in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml idp sp connection
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the first saml idp sp connection in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching saml idp sp connection, or <code>null</code> if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_First(companyId, orderByComparator);
	}

	/**
	* Returns the last saml idp sp connection in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml idp sp connection
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	/**
	* Returns the last saml idp sp connection in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching saml idp sp connection, or <code>null</code> if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCompanyId_Last(companyId, orderByComparator);
	}

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
	public static com.liferay.saml.model.SamlIdpSpConnection[] findByCompanyId_PrevAndNext(
		long samlIdpSpConnectionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(samlIdpSpConnectionId,
			companyId, orderByComparator);
	}

	/**
	* Removes all the saml idp sp connections where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	/**
	* Returns the number of saml idp sp connections where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	/**
	* Returns the saml idp sp connection where companyId = &#63; and samlSpEntityId = &#63; or throws a {@link com.liferay.saml.NoSuchIdpSpConnectionException} if it could not be found.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the matching saml idp sp connection
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection findByC_SSEI(
		long companyId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException {
		return getPersistence().findByC_SSEI(companyId, samlSpEntityId);
	}

	/**
	* Returns the saml idp sp connection where companyId = &#63; and samlSpEntityId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the matching saml idp sp connection, or <code>null</code> if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection fetchByC_SSEI(
		long companyId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_SSEI(companyId, samlSpEntityId);
	}

	/**
	* Returns the saml idp sp connection where companyId = &#63; and samlSpEntityId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching saml idp sp connection, or <code>null</code> if a matching saml idp sp connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection fetchByC_SSEI(
		long companyId, java.lang.String samlSpEntityId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_SSEI(companyId, samlSpEntityId, retrieveFromCache);
	}

	/**
	* Removes the saml idp sp connection where companyId = &#63; and samlSpEntityId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the saml idp sp connection that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection removeByC_SSEI(
		long companyId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException {
		return getPersistence().removeByC_SSEI(companyId, samlSpEntityId);
	}

	/**
	* Returns the number of saml idp sp connections where companyId = &#63; and samlSpEntityId = &#63;.
	*
	* @param companyId the company ID
	* @param samlSpEntityId the saml sp entity ID
	* @return the number of matching saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_SSEI(long companyId,
		java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_SSEI(companyId, samlSpEntityId);
	}

	/**
	* Caches the saml idp sp connection in the entity cache if it is enabled.
	*
	* @param samlIdpSpConnection the saml idp sp connection
	*/
	public static void cacheResult(
		com.liferay.saml.model.SamlIdpSpConnection samlIdpSpConnection) {
		getPersistence().cacheResult(samlIdpSpConnection);
	}

	/**
	* Caches the saml idp sp connections in the entity cache if it is enabled.
	*
	* @param samlIdpSpConnections the saml idp sp connections
	*/
	public static void cacheResult(
		java.util.List<com.liferay.saml.model.SamlIdpSpConnection> samlIdpSpConnections) {
		getPersistence().cacheResult(samlIdpSpConnections);
	}

	/**
	* Creates a new saml idp sp connection with the primary key. Does not add the saml idp sp connection to the database.
	*
	* @param samlIdpSpConnectionId the primary key for the new saml idp sp connection
	* @return the new saml idp sp connection
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection create(
		long samlIdpSpConnectionId) {
		return getPersistence().create(samlIdpSpConnectionId);
	}

	/**
	* Removes the saml idp sp connection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpConnectionId the primary key of the saml idp sp connection
	* @return the saml idp sp connection that was removed
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a saml idp sp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection remove(
		long samlIdpSpConnectionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException {
		return getPersistence().remove(samlIdpSpConnectionId);
	}

	public static com.liferay.saml.model.SamlIdpSpConnection updateImpl(
		com.liferay.saml.model.SamlIdpSpConnection samlIdpSpConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(samlIdpSpConnection);
	}

	/**
	* Returns the saml idp sp connection with the primary key or throws a {@link com.liferay.saml.NoSuchIdpSpConnectionException} if it could not be found.
	*
	* @param samlIdpSpConnectionId the primary key of the saml idp sp connection
	* @return the saml idp sp connection
	* @throws com.liferay.saml.NoSuchIdpSpConnectionException if a saml idp sp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection findByPrimaryKey(
		long samlIdpSpConnectionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchIdpSpConnectionException {
		return getPersistence().findByPrimaryKey(samlIdpSpConnectionId);
	}

	/**
	* Returns the saml idp sp connection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samlIdpSpConnectionId the primary key of the saml idp sp connection
	* @return the saml idp sp connection, or <code>null</code> if a saml idp sp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlIdpSpConnection fetchByPrimaryKey(
		long samlIdpSpConnectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(samlIdpSpConnectionId);
	}

	/**
	* Returns all the saml idp sp connections.
	*
	* @return the saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.saml.model.SamlIdpSpConnection> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the saml idp sp connections from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of saml idp sp connections.
	*
	* @return the number of saml idp sp connections
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SamlIdpSpConnectionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SamlIdpSpConnectionPersistence)PortletBeanLocatorUtil.locate(com.liferay.saml.service.ClpSerializer.getServletContextName(),
					SamlIdpSpConnectionPersistence.class.getName());

			ReferenceRegistry.registerReference(SamlIdpSpConnectionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(SamlIdpSpConnectionPersistence persistence) {
	}

	private static SamlIdpSpConnectionPersistence _persistence;
}