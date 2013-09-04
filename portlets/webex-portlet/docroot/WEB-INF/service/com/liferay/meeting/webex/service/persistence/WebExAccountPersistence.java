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

package com.liferay.meeting.webex.service.persistence;

import com.liferay.meeting.webex.model.WebExAccount;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the web ex account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see WebExAccountPersistenceImpl
 * @see WebExAccountUtil
 * @generated
 */
public interface WebExAccountPersistence extends BasePersistence<WebExAccount> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebExAccountUtil} to access the web ex account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the web ex accounts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex accounts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex accounts where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex accounts before and after the current web ex account in the ordered set where uuid = &#63;.
	*
	* @param webExAccountId the primary key of the current web ex account
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount[] findByUuid_PrevAndNext(
		long webExAccountId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the web ex accounts where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex accounts where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex account where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.meeting.webex.NoSuchAccountException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the web ex account where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the web ex account that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex accounts where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex accounts before and after the current web ex account in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param webExAccountId the primary key of the current web ex account
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount[] findByUuid_C_PrevAndNext(
		long webExAccountId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the web ex accounts where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex accounts where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findByG_W(
		long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findByG_W(
		long groupId, long webExSiteId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findByG_W(
		long groupId, long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount findByG_W_First(
		long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount fetchByG_W_First(
		long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount findByG_W_Last(
		long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount fetchByG_W_Last(
		long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex accounts before and after the current web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param webExAccountId the primary key of the current web ex account
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount[] findByG_W_PrevAndNext(
		long webExAccountId, long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the matching web ex accounts that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> filterFindByG_W(
		long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of matching web ex accounts that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> filterFindByG_W(
		long groupId, long webExSiteId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex accounts that the user has permissions to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex accounts that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> filterFindByG_W(
		long groupId, long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex accounts before and after the current web ex account in the ordered set of web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param webExAccountId the primary key of the current web ex account
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount[] filterFindByG_W_PrevAndNext(
		long webExAccountId, long groupId, long webExSiteId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the web ex accounts where groupId = &#63; and webExSiteId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_W(long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the number of matching web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_W(long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	*
	* @param groupId the group ID
	* @param webExSiteId the web ex site ID
	* @return the number of matching web ex accounts that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByG_W(long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the web ex account in the entity cache if it is enabled.
	*
	* @param webExAccount the web ex account
	*/
	public void cacheResult(
		com.liferay.meeting.webex.model.WebExAccount webExAccount);

	/**
	* Caches the web ex accounts in the entity cache if it is enabled.
	*
	* @param webExAccounts the web ex accounts
	*/
	public void cacheResult(
		java.util.List<com.liferay.meeting.webex.model.WebExAccount> webExAccounts);

	/**
	* Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	*
	* @param webExAccountId the primary key for the new web ex account
	* @return the new web ex account
	*/
	public com.liferay.meeting.webex.model.WebExAccount create(
		long webExAccountId);

	/**
	* Removes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account that was removed
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount remove(
		long webExAccountId)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.meeting.webex.model.WebExAccount updateImpl(
		com.liferay.meeting.webex.model.WebExAccount webExAccount)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex account with the primary key or throws a {@link com.liferay.meeting.webex.NoSuchAccountException} if it could not be found.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account
	* @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount findByPrimaryKey(
		long webExAccountId)
		throws com.liferay.meeting.webex.NoSuchAccountException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex account with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account, or <code>null</code> if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount fetchByPrimaryKey(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the web ex accounts.
	*
	* @return the web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExAccountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the web ex accounts from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex accounts.
	*
	* @return the number of web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}