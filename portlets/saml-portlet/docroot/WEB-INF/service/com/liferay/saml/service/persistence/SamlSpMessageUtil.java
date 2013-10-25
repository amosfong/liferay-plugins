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

import com.liferay.saml.model.SamlSpMessage;

import java.util.List;

/**
 * The persistence utility for the saml sp message service. This utility wraps {@link SamlSpMessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpMessagePersistence
 * @see SamlSpMessagePersistenceImpl
 * @generated
 */
public class SamlSpMessageUtil {
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
	public static void clearCache(SamlSpMessage samlSpMessage) {
		getPersistence().clearCache(samlSpMessage);
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
	public static List<SamlSpMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SamlSpMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SamlSpMessage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static SamlSpMessage update(SamlSpMessage samlSpMessage)
		throws SystemException {
		return getPersistence().update(samlSpMessage);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static SamlSpMessage update(SamlSpMessage samlSpMessage,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(samlSpMessage, serviceContext);
	}

	/**
	* Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or throws a {@link com.liferay.saml.NoSuchSpMessageException} if it could not be found.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @return the matching saml sp message
	* @throws com.liferay.saml.NoSuchSpMessageException if a matching saml sp message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpMessage findBySIEI_SIRK(
		java.lang.String samlIdpEntityId, java.lang.String samlIdpResponseKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpMessageException {
		return getPersistence()
				   .findBySIEI_SIRK(samlIdpEntityId, samlIdpResponseKey);
	}

	/**
	* Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @return the matching saml sp message, or <code>null</code> if a matching saml sp message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpMessage fetchBySIEI_SIRK(
		java.lang.String samlIdpEntityId, java.lang.String samlIdpResponseKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySIEI_SIRK(samlIdpEntityId, samlIdpResponseKey);
	}

	/**
	* Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching saml sp message, or <code>null</code> if a matching saml sp message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpMessage fetchBySIEI_SIRK(
		java.lang.String samlIdpEntityId, java.lang.String samlIdpResponseKey,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySIEI_SIRK(samlIdpEntityId, samlIdpResponseKey,
			retrieveFromCache);
	}

	/**
	* Removes the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; from the database.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @return the saml sp message that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpMessage removeBySIEI_SIRK(
		java.lang.String samlIdpEntityId, java.lang.String samlIdpResponseKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpMessageException {
		return getPersistence()
				   .removeBySIEI_SIRK(samlIdpEntityId, samlIdpResponseKey);
	}

	/**
	* Returns the number of saml sp messages where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63;.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @return the number of matching saml sp messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySIEI_SIRK(java.lang.String samlIdpEntityId,
		java.lang.String samlIdpResponseKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBySIEI_SIRK(samlIdpEntityId, samlIdpResponseKey);
	}

	/**
	* Caches the saml sp message in the entity cache if it is enabled.
	*
	* @param samlSpMessage the saml sp message
	*/
	public static void cacheResult(
		com.liferay.saml.model.SamlSpMessage samlSpMessage) {
		getPersistence().cacheResult(samlSpMessage);
	}

	/**
	* Caches the saml sp messages in the entity cache if it is enabled.
	*
	* @param samlSpMessages the saml sp messages
	*/
	public static void cacheResult(
		java.util.List<com.liferay.saml.model.SamlSpMessage> samlSpMessages) {
		getPersistence().cacheResult(samlSpMessages);
	}

	/**
	* Creates a new saml sp message with the primary key. Does not add the saml sp message to the database.
	*
	* @param samlSpMessageId the primary key for the new saml sp message
	* @return the new saml sp message
	*/
	public static com.liferay.saml.model.SamlSpMessage create(
		long samlSpMessageId) {
		return getPersistence().create(samlSpMessageId);
	}

	/**
	* Removes the saml sp message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpMessageId the primary key of the saml sp message
	* @return the saml sp message that was removed
	* @throws com.liferay.saml.NoSuchSpMessageException if a saml sp message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpMessage remove(
		long samlSpMessageId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpMessageException {
		return getPersistence().remove(samlSpMessageId);
	}

	public static com.liferay.saml.model.SamlSpMessage updateImpl(
		com.liferay.saml.model.SamlSpMessage samlSpMessage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(samlSpMessage);
	}

	/**
	* Returns the saml sp message with the primary key or throws a {@link com.liferay.saml.NoSuchSpMessageException} if it could not be found.
	*
	* @param samlSpMessageId the primary key of the saml sp message
	* @return the saml sp message
	* @throws com.liferay.saml.NoSuchSpMessageException if a saml sp message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpMessage findByPrimaryKey(
		long samlSpMessageId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpMessageException {
		return getPersistence().findByPrimaryKey(samlSpMessageId);
	}

	/**
	* Returns the saml sp message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samlSpMessageId the primary key of the saml sp message
	* @return the saml sp message, or <code>null</code> if a saml sp message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpMessage fetchByPrimaryKey(
		long samlSpMessageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(samlSpMessageId);
	}

	/**
	* Returns all the saml sp messages.
	*
	* @return the saml sp messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpMessage> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the saml sp messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml sp messages
	* @param end the upper bound of the range of saml sp messages (not inclusive)
	* @return the range of saml sp messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpMessage> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the saml sp messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml sp messages
	* @param end the upper bound of the range of saml sp messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of saml sp messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the saml sp messages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of saml sp messages.
	*
	* @return the number of saml sp messages
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SamlSpMessagePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SamlSpMessagePersistence)PortletBeanLocatorUtil.locate(com.liferay.saml.service.ClpSerializer.getServletContextName(),
					SamlSpMessagePersistence.class.getName());

			ReferenceRegistry.registerReference(SamlSpMessageUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(SamlSpMessagePersistence persistence) {
	}

	private static SamlSpMessagePersistence _persistence;
}