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

import com.liferay.meeting.webex.model.WebExSite;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the web ex site service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see WebExSitePersistenceImpl
 * @see WebExSiteUtil
 * @generated
 */
public interface WebExSitePersistence extends BasePersistence<WebExSite> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebExSiteUtil} to access the web ex site persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the web ex sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set where uuid = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite[] findByUuid_PrevAndNext(
		long webExSiteId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the web ex sites where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex site where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.meeting.webex.NoSuchSiteException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite findByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchByUUID_G(
		java.lang.String uuid, long groupId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the web ex site where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the web ex site that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite removeByUUID_G(
		java.lang.String uuid, long groupId)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex sites where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public int countByUUID_G(java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite[] findByUuid_C_PrevAndNext(
		long webExSiteId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the web ex sites where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the web ex sites where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex sites where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex sites where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last web ex site in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set where groupId = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite[] findByGroupId_PrevAndNext(
		long webExSiteId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the web ex sites that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching web ex sites that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> filterFindByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex sites that the user has permission to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of matching web ex sites that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> filterFindByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex sites that the user has permissions to view where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching web ex sites that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex sites before and after the current web ex site in the ordered set of web ex sites that the user has permission to view where groupId = &#63;.
	*
	* @param webExSiteId the primary key of the current web ex site
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite[] filterFindByGroupId_PrevAndNext(
		long webExSiteId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the web ex sites where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex sites where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex sites that the user has permission to view where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching web ex sites that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex site where siteKey = &#63; or throws a {@link com.liferay.meeting.webex.NoSuchSiteException} if it could not be found.
	*
	* @param siteKey the site key
	* @return the matching web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite findBySiteKey(long siteKey)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param siteKey the site key
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchBySiteKey(
		long siteKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param siteKey the site key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchBySiteKey(
		long siteKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the web ex site where siteKey = &#63; from the database.
	*
	* @param siteKey the site key
	* @return the web ex site that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite removeBySiteKey(
		long siteKey)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex sites where siteKey = &#63;.
	*
	* @param siteKey the site key
	* @return the number of matching web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public int countBySiteKey(long siteKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the web ex site in the entity cache if it is enabled.
	*
	* @param webExSite the web ex site
	*/
	public void cacheResult(com.liferay.meeting.webex.model.WebExSite webExSite);

	/**
	* Caches the web ex sites in the entity cache if it is enabled.
	*
	* @param webExSites the web ex sites
	*/
	public void cacheResult(
		java.util.List<com.liferay.meeting.webex.model.WebExSite> webExSites);

	/**
	* Creates a new web ex site with the primary key. Does not add the web ex site to the database.
	*
	* @param webExSiteId the primary key for the new web ex site
	* @return the new web ex site
	*/
	public com.liferay.meeting.webex.model.WebExSite create(long webExSiteId);

	/**
	* Removes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site that was removed
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite remove(long webExSiteId)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.meeting.webex.model.WebExSite updateImpl(
		com.liferay.meeting.webex.model.WebExSite webExSite)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex site with the primary key or throws a {@link com.liferay.meeting.webex.NoSuchSiteException} if it could not be found.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site
	* @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite findByPrimaryKey(
		long webExSiteId)
		throws com.liferay.meeting.webex.NoSuchSiteException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the web ex site with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site, or <code>null</code> if a web ex site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite fetchByPrimaryKey(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the web ex sites.
	*
	* @return the web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the web ex sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the web ex sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.meeting.webex.model.impl.WebExSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the web ex sites from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of web ex sites.
	*
	* @return the number of web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}