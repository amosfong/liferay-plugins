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

import com.liferay.saml.model.SamlSpMessage;

/**
 * The persistence interface for the saml sp message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpMessagePersistenceImpl
 * @see SamlSpMessageUtil
 * @generated
 */
public interface SamlSpMessagePersistence extends BasePersistence<SamlSpMessage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SamlSpMessageUtil} to access the saml sp message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or throws a {@link com.liferay.saml.NoSuchSpMessageException} if it could not be found.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @return the matching saml sp message
	* @throws com.liferay.saml.NoSuchSpMessageException if a matching saml sp message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpMessage findBySIEI_SIRK(
		java.lang.String samlIdpEntityId, java.lang.String samlIdpResponseKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpMessageException;

	/**
	* Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @return the matching saml sp message, or <code>null</code> if a matching saml sp message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpMessage fetchBySIEI_SIRK(
		java.lang.String samlIdpEntityId, java.lang.String samlIdpResponseKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching saml sp message, or <code>null</code> if a matching saml sp message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpMessage fetchBySIEI_SIRK(
		java.lang.String samlIdpEntityId, java.lang.String samlIdpResponseKey,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; from the database.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @return the saml sp message that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpMessage removeBySIEI_SIRK(
		java.lang.String samlIdpEntityId, java.lang.String samlIdpResponseKey)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpMessageException;

	/**
	* Returns the number of saml sp messages where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63;.
	*
	* @param samlIdpEntityId the saml idp entity ID
	* @param samlIdpResponseKey the saml idp response key
	* @return the number of matching saml sp messages
	* @throws SystemException if a system exception occurred
	*/
	public int countBySIEI_SIRK(java.lang.String samlIdpEntityId,
		java.lang.String samlIdpResponseKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the saml sp message in the entity cache if it is enabled.
	*
	* @param samlSpMessage the saml sp message
	*/
	public void cacheResult(com.liferay.saml.model.SamlSpMessage samlSpMessage);

	/**
	* Caches the saml sp messages in the entity cache if it is enabled.
	*
	* @param samlSpMessages the saml sp messages
	*/
	public void cacheResult(
		java.util.List<com.liferay.saml.model.SamlSpMessage> samlSpMessages);

	/**
	* Creates a new saml sp message with the primary key. Does not add the saml sp message to the database.
	*
	* @param samlSpMessageId the primary key for the new saml sp message
	* @return the new saml sp message
	*/
	public com.liferay.saml.model.SamlSpMessage create(long samlSpMessageId);

	/**
	* Removes the saml sp message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpMessageId the primary key of the saml sp message
	* @return the saml sp message that was removed
	* @throws com.liferay.saml.NoSuchSpMessageException if a saml sp message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpMessage remove(long samlSpMessageId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpMessageException;

	public com.liferay.saml.model.SamlSpMessage updateImpl(
		com.liferay.saml.model.SamlSpMessage samlSpMessage)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the saml sp message with the primary key or throws a {@link com.liferay.saml.NoSuchSpMessageException} if it could not be found.
	*
	* @param samlSpMessageId the primary key of the saml sp message
	* @return the saml sp message
	* @throws com.liferay.saml.NoSuchSpMessageException if a saml sp message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpMessage findByPrimaryKey(
		long samlSpMessageId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.saml.NoSuchSpMessageException;

	/**
	* Returns the saml sp message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param samlSpMessageId the primary key of the saml sp message
	* @return the saml sp message, or <code>null</code> if a saml sp message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpMessage fetchByPrimaryKey(
		long samlSpMessageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the saml sp messages.
	*
	* @return the saml sp messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlSpMessage> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.saml.model.SamlSpMessage> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.saml.model.SamlSpMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the saml sp messages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of saml sp messages.
	*
	* @return the number of saml sp messages
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}