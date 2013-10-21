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

import com.liferay.oauth.model.OAuthUser;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the o auth user service. This utility wraps {@link OAuthUserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthUserPersistence
 * @see OAuthUserPersistenceImpl
 * @generated
 */
public class OAuthUserUtil {
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
	public static void clearCache(OAuthUser oAuthUser) {
		getPersistence().clearCache(oAuthUser);
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
	public static List<OAuthUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OAuthUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OAuthUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static OAuthUser update(OAuthUser oAuthUser)
		throws SystemException {
		return getPersistence().update(oAuthUser);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static OAuthUser update(OAuthUser oAuthUser,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(oAuthUser, serviceContext);
	}

	/**
	* Returns all the o auth users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the o auth users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @return the range of matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the o auth users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first o auth user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first o auth user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth user, or <code>null</code> if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last o auth user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last o auth user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth user, or <code>null</code> if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the o auth users before and after the current o auth user in the ordered set where userId = &#63;.
	*
	* @param oAuthUserId the primary key of the current o auth user
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser[] findByUserId_PrevAndNext(
		long oAuthUserId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(oAuthUserId, userId,
			orderByComparator);
	}

	/**
	* Returns all the o auth users that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching o auth users that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> filterFindByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUserId(userId);
	}

	/**
	* Returns a range of all the o auth users that the user has permission to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @return the range of matching o auth users that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> filterFindByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterFindByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the o auth users that the user has permissions to view where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth users that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> filterFindByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the o auth users before and after the current o auth user in the ordered set of o auth users that the user has permission to view where userId = &#63;.
	*
	* @param oAuthUserId the primary key of the current o auth user
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser[] filterFindByUserId_PrevAndNext(
		long oAuthUserId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByUserId_PrevAndNext(oAuthUserId, userId,
			orderByComparator);
	}

	/**
	* Removes all the o auth users where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of o auth users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of o auth users that the user has permission to view where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching o auth users that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().filterCountByUserId(userId);
	}

	/**
	* Returns all the o auth users where oAuthApplicationId = &#63;.
	*
	* @param oAuthApplicationId the o auth application ID
	* @return the matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> findByOAuthApplicationId(
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOAuthApplicationId(oAuthApplicationId);
	}

	/**
	* Returns a range of all the o auth users where oAuthApplicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param oAuthApplicationId the o auth application ID
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @return the range of matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> findByOAuthApplicationId(
		long oAuthApplicationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOAuthApplicationId(oAuthApplicationId, start, end);
	}

	/**
	* Returns an ordered range of all the o auth users where oAuthApplicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param oAuthApplicationId the o auth application ID
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> findByOAuthApplicationId(
		long oAuthApplicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOAuthApplicationId(oAuthApplicationId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first o auth user in the ordered set where oAuthApplicationId = &#63;.
	*
	* @param oAuthApplicationId the o auth application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser findByOAuthApplicationId_First(
		long oAuthApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOAuthApplicationId_First(oAuthApplicationId,
			orderByComparator);
	}

	/**
	* Returns the first o auth user in the ordered set where oAuthApplicationId = &#63;.
	*
	* @param oAuthApplicationId the o auth application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth user, or <code>null</code> if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser fetchByOAuthApplicationId_First(
		long oAuthApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOAuthApplicationId_First(oAuthApplicationId,
			orderByComparator);
	}

	/**
	* Returns the last o auth user in the ordered set where oAuthApplicationId = &#63;.
	*
	* @param oAuthApplicationId the o auth application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser findByOAuthApplicationId_Last(
		long oAuthApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOAuthApplicationId_Last(oAuthApplicationId,
			orderByComparator);
	}

	/**
	* Returns the last o auth user in the ordered set where oAuthApplicationId = &#63;.
	*
	* @param oAuthApplicationId the o auth application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth user, or <code>null</code> if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser fetchByOAuthApplicationId_Last(
		long oAuthApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOAuthApplicationId_Last(oAuthApplicationId,
			orderByComparator);
	}

	/**
	* Returns the o auth users before and after the current o auth user in the ordered set where oAuthApplicationId = &#63;.
	*
	* @param oAuthUserId the primary key of the current o auth user
	* @param oAuthApplicationId the o auth application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser[] findByOAuthApplicationId_PrevAndNext(
		long oAuthUserId, long oAuthApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOAuthApplicationId_PrevAndNext(oAuthUserId,
			oAuthApplicationId, orderByComparator);
	}

	/**
	* Returns all the o auth users that the user has permission to view where oAuthApplicationId = &#63;.
	*
	* @param oAuthApplicationId the o auth application ID
	* @return the matching o auth users that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> filterFindByOAuthApplicationId(
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByOAuthApplicationId(oAuthApplicationId);
	}

	/**
	* Returns a range of all the o auth users that the user has permission to view where oAuthApplicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param oAuthApplicationId the o auth application ID
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @return the range of matching o auth users that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> filterFindByOAuthApplicationId(
		long oAuthApplicationId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByOAuthApplicationId(oAuthApplicationId, start,
			end);
	}

	/**
	* Returns an ordered range of all the o auth users that the user has permissions to view where oAuthApplicationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param oAuthApplicationId the o auth application ID
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth users that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> filterFindByOAuthApplicationId(
		long oAuthApplicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByOAuthApplicationId(oAuthApplicationId, start,
			end, orderByComparator);
	}

	/**
	* Returns the o auth users before and after the current o auth user in the ordered set of o auth users that the user has permission to view where oAuthApplicationId = &#63;.
	*
	* @param oAuthUserId the primary key of the current o auth user
	* @param oAuthApplicationId the o auth application ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser[] filterFindByOAuthApplicationId_PrevAndNext(
		long oAuthUserId, long oAuthApplicationId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterFindByOAuthApplicationId_PrevAndNext(oAuthUserId,
			oAuthApplicationId, orderByComparator);
	}

	/**
	* Removes all the o auth users where oAuthApplicationId = &#63; from the database.
	*
	* @param oAuthApplicationId the o auth application ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOAuthApplicationId(long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOAuthApplicationId(oAuthApplicationId);
	}

	/**
	* Returns the number of o auth users where oAuthApplicationId = &#63;.
	*
	* @param oAuthApplicationId the o auth application ID
	* @return the number of matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOAuthApplicationId(long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOAuthApplicationId(oAuthApplicationId);
	}

	/**
	* Returns the number of o auth users that the user has permission to view where oAuthApplicationId = &#63;.
	*
	* @param oAuthApplicationId the o auth application ID
	* @return the number of matching o auth users that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public static int filterCountByOAuthApplicationId(long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .filterCountByOAuthApplicationId(oAuthApplicationId);
	}

	/**
	* Returns the o auth user where accessToken = &#63; or throws a {@link com.liferay.oauth.NoSuchUserException} if it could not be found.
	*
	* @param accessToken the access token
	* @return the matching o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser findByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccessToken(accessToken);
	}

	/**
	* Returns the o auth user where accessToken = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accessToken the access token
	* @return the matching o auth user, or <code>null</code> if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser fetchByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByAccessToken(accessToken);
	}

	/**
	* Returns the o auth user where accessToken = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accessToken the access token
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth user, or <code>null</code> if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser fetchByAccessToken(
		java.lang.String accessToken, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccessToken(accessToken, retrieveFromCache);
	}

	/**
	* Removes the o auth user where accessToken = &#63; from the database.
	*
	* @param accessToken the access token
	* @return the o auth user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser removeByAccessToken(
		java.lang.String accessToken)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByAccessToken(accessToken);
	}

	/**
	* Returns the number of o auth users where accessToken = &#63;.
	*
	* @param accessToken the access token
	* @return the number of matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccessToken(java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccessToken(accessToken);
	}

	/**
	* Returns the o auth user where userId = &#63; and oAuthApplicationId = &#63; or throws a {@link com.liferay.oauth.NoSuchUserException} if it could not be found.
	*
	* @param userId the user ID
	* @param oAuthApplicationId the o auth application ID
	* @return the matching o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser findByU_OAI(long userId,
		long oAuthApplicationId)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_OAI(userId, oAuthApplicationId);
	}

	/**
	* Returns the o auth user where userId = &#63; and oAuthApplicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param oAuthApplicationId the o auth application ID
	* @return the matching o auth user, or <code>null</code> if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser fetchByU_OAI(long userId,
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_OAI(userId, oAuthApplicationId);
	}

	/**
	* Returns the o auth user where userId = &#63; and oAuthApplicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param oAuthApplicationId the o auth application ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth user, or <code>null</code> if a matching o auth user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser fetchByU_OAI(long userId,
		long oAuthApplicationId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_OAI(userId, oAuthApplicationId, retrieveFromCache);
	}

	/**
	* Removes the o auth user where userId = &#63; and oAuthApplicationId = &#63; from the database.
	*
	* @param userId the user ID
	* @param oAuthApplicationId the o auth application ID
	* @return the o auth user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser removeByU_OAI(long userId,
		long oAuthApplicationId)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByU_OAI(userId, oAuthApplicationId);
	}

	/**
	* Returns the number of o auth users where userId = &#63; and oAuthApplicationId = &#63;.
	*
	* @param userId the user ID
	* @param oAuthApplicationId the o auth application ID
	* @return the number of matching o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_OAI(long userId, long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_OAI(userId, oAuthApplicationId);
	}

	/**
	* Caches the o auth user in the entity cache if it is enabled.
	*
	* @param oAuthUser the o auth user
	*/
	public static void cacheResult(com.liferay.oauth.model.OAuthUser oAuthUser) {
		getPersistence().cacheResult(oAuthUser);
	}

	/**
	* Caches the o auth users in the entity cache if it is enabled.
	*
	* @param oAuthUsers the o auth users
	*/
	public static void cacheResult(
		java.util.List<com.liferay.oauth.model.OAuthUser> oAuthUsers) {
		getPersistence().cacheResult(oAuthUsers);
	}

	/**
	* Creates a new o auth user with the primary key. Does not add the o auth user to the database.
	*
	* @param oAuthUserId the primary key for the new o auth user
	* @return the new o auth user
	*/
	public static com.liferay.oauth.model.OAuthUser create(long oAuthUserId) {
		return getPersistence().create(oAuthUserId);
	}

	/**
	* Removes the o auth user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthUserId the primary key of the o auth user
	* @return the o auth user that was removed
	* @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser remove(long oAuthUserId)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(oAuthUserId);
	}

	public static com.liferay.oauth.model.OAuthUser updateImpl(
		com.liferay.oauth.model.OAuthUser oAuthUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(oAuthUser);
	}

	/**
	* Returns the o auth user with the primary key or throws a {@link com.liferay.oauth.NoSuchUserException} if it could not be found.
	*
	* @param oAuthUserId the primary key of the o auth user
	* @return the o auth user
	* @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser findByPrimaryKey(
		long oAuthUserId)
		throws com.liferay.oauth.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(oAuthUserId);
	}

	/**
	* Returns the o auth user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oAuthUserId the primary key of the o auth user
	* @return the o auth user, or <code>null</code> if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser fetchByPrimaryKey(
		long oAuthUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(oAuthUserId);
	}

	/**
	* Returns all the o auth users.
	*
	* @return the o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the o auth users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @return the range of o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the o auth users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.oauth.model.OAuthUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the o auth users from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of o auth users.
	*
	* @return the number of o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static OAuthUserPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OAuthUserPersistence)PortletBeanLocatorUtil.locate(com.liferay.oauth.service.ClpSerializer.getServletContextName(),
					OAuthUserPersistence.class.getName());

			ReferenceRegistry.registerReference(OAuthUserUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(OAuthUserPersistence persistence) {
	}

	private static OAuthUserPersistence _persistence;
}