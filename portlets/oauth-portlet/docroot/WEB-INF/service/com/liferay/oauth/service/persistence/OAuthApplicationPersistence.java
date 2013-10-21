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

package com.liferay.oauth.service.persistence;

import com.liferay.oauth.model.OAuthApplication;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the o auth application service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthApplicationPersistenceImpl
 * @see OAuthApplicationUtil
 * @generated
 */
public interface OAuthApplicationPersistence extends BasePersistence<OAuthApplication> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OAuthApplicationUtil} to access the o auth application persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the o auth applications where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth application in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth application in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set where companyId = &#63;.
	*
	* @param oAuthApplicationId the primary key of the current o auth application
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication[] findByCompanyId_PrevAndNext(
		long oAuthApplicationId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications that the user has permission to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications that the user has permissions to view where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set of o auth applications that the user has permission to view where companyId = &#63;.
	*
	* @param oAuthApplicationId the primary key of the current o auth application
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication[] filterFindByCompanyId_PrevAndNext(
		long oAuthApplicationId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications that the user has permission to view where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth application in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth application in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set where userId = &#63;.
	*
	* @param oAuthApplicationId the primary key of the current o auth application
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication[] findByUserId_PrevAndNext(
		long oAuthApplicationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications that the user has permission to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications that the user has permissions to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set of o auth applications that the user has permission to view where userId = &#63;.
	*
	* @param oAuthApplicationId the primary key of the current o auth application
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication[] filterFindByUserId_PrevAndNext(
		long oAuthApplicationId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth application where consumerKey = &#63; or throws a {@link com.liferay.oauth.NoSuchApplicationException} if it could not be found.
	*
	* @param consumerKey the consumer key
	* @return the matching o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByConsumerKey(
		java.lang.String consumerKey)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth application where consumerKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param consumerKey the consumer key
	* @return the matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByConsumerKey(
		java.lang.String consumerKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth application where consumerKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param consumerKey the consumer key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByConsumerKey(
		java.lang.String consumerKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the o auth application where consumerKey = &#63; from the database.
	*
	* @param consumerKey the consumer key
	* @return the o auth application that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication removeByConsumerKey(
		java.lang.String consumerKey)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where consumerKey = &#63;.
	*
	* @param consumerKey the consumer key
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByConsumerKey(java.lang.String consumerKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByC_N(
		long companyId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByC_N(
		long companyId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByC_N_First(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByC_N_First(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth application in the ordered set where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByC_N_Last(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth application in the ordered set where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByC_N_Last(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set where companyId = &#63; and name LIKE &#63;.
	*
	* @param oAuthApplicationId the primary key of the current o auth application
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication[] findByC_N_PrevAndNext(
		long oAuthApplicationId, long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByC_N(
		long companyId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications that the user has permissions to view where companyId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByC_N(
		long companyId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set of o auth applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	*
	* @param oAuthApplicationId the primary key of the current o auth application
	* @param companyId the company ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication[] filterFindByC_N_PrevAndNext(
		long oAuthApplicationId, long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications where companyId = &#63; and name LIKE &#63; from the database.
	*
	* @param companyId the company ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications that the user has permission to view where companyId = &#63; and name LIKE &#63;.
	*
	* @param companyId the company ID
	* @param name the name
	* @return the number of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications where userId = &#63; and name LIKE &#63;.
	*
	* @param userId the user ID
	* @param name the name
	* @return the matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByU_N(
		long userId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications where userId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByU_N(
		long userId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications where userId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findByU_N(
		long userId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where userId = &#63; and name LIKE &#63;.
	*
	* @param userId the user ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByU_N_First(
		long userId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth application in the ordered set where userId = &#63; and name LIKE &#63;.
	*
	* @param userId the user ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByU_N_First(
		long userId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth application in the ordered set where userId = &#63; and name LIKE &#63;.
	*
	* @param userId the user ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByU_N_Last(
		long userId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth application in the ordered set where userId = &#63; and name LIKE &#63;.
	*
	* @param userId the user ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth application, or <code>null</code> if a matching o auth application could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByU_N_Last(
		long userId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set where userId = &#63; and name LIKE &#63;.
	*
	* @param oAuthApplicationId the primary key of the current o auth application
	* @param userId the user ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication[] findByU_N_PrevAndNext(
		long oAuthApplicationId, long userId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications that the user has permission to view where userId = &#63; and name LIKE &#63;.
	*
	* @param userId the user ID
	* @param name the name
	* @return the matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByU_N(
		long userId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications that the user has permission to view where userId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByU_N(
		long userId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications that the user has permissions to view where userId = &#63; and name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param name the name
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> filterFindByU_N(
		long userId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth applications before and after the current o auth application in the ordered set of o auth applications that the user has permission to view where userId = &#63; and name LIKE &#63;.
	*
	* @param oAuthApplicationId the primary key of the current o auth application
	* @param userId the user ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication[] filterFindByU_N_PrevAndNext(
		long oAuthApplicationId, long userId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications where userId = &#63; and name LIKE &#63; from the database.
	*
	* @param userId the user ID
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_N(long userId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications where userId = &#63; and name LIKE &#63;.
	*
	* @param userId the user ID
	* @param name the name
	* @return the number of matching o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_N(long userId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications that the user has permission to view where userId = &#63; and name LIKE &#63;.
	*
	* @param userId the user ID
	* @param name the name
	* @return the number of matching o auth applications that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByU_N(long userId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the o auth application in the entity cache if it is enabled.
	*
	* @param oAuthApplication the o auth application
	*/
	public void cacheResult(
		com.liferay.oauth.model.OAuthApplication oAuthApplication);

	/**
	* Caches the o auth applications in the entity cache if it is enabled.
	*
	* @param oAuthApplications the o auth applications
	*/
	public void cacheResult(
		java.util.List<com.liferay.oauth.model.OAuthApplication> oAuthApplications);

	/**
	* Creates a new o auth application with the primary key. Does not add the o auth application to the database.
	*
	* @param oAuthApplicationId the primary key for the new o auth application
	* @return the new o auth application
	*/
	public com.liferay.oauth.model.OAuthApplication create(
		long oAuthApplicationId);

	/**
	* Removes the o auth application with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthApplicationId the primary key of the o auth application
	* @return the o auth application that was removed
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication remove(
		long oAuthApplicationId)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.oauth.model.OAuthApplication updateImpl(
		com.liferay.oauth.model.OAuthApplication oAuthApplication)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth application with the primary key or throws a {@link com.liferay.oauth.NoSuchApplicationException} if it could not be found.
	*
	* @param oAuthApplicationId the primary key of the o auth application
	* @return the o auth application
	* @throws com.liferay.oauth.NoSuchApplicationException if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication findByPrimaryKey(
		long oAuthApplicationId)
		throws com.liferay.oauth.NoSuchApplicationException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth application with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oAuthApplicationId the primary key of the o auth application
	* @return the o auth application, or <code>null</code> if a o auth application with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthApplication fetchByPrimaryKey(
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth applications.
	*
	* @return the o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth applications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @return the range of o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth applications.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthApplicationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth applications
	* @param end the upper bound of the range of o auth applications (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthApplication> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth applications from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth applications.
	*
	* @return the number of o auth applications
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}