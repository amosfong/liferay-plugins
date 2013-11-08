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

package com.liferay.bbb.service.persistence;

import com.liferay.bbb.model.MeetingServer;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the meeting server service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingServerPersistenceImpl
 * @see MeetingServerUtil
 * @generated
 */
public interface MeetingServerPersistence extends BasePersistence<MeetingServer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MeetingServerUtil} to access the meeting server persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the meeting servers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching meeting servers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingServer> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meeting servers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of meeting servers
	* @param end the upper bound of the range of meeting servers (not inclusive)
	* @return the range of matching meeting servers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingServer> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meeting servers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of meeting servers
	* @param end the upper bound of the range of meeting servers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meeting servers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingServer> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first meeting server in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meeting server
	* @throws com.liferay.bbb.NoSuchMeetingServerException if a matching meeting server could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingServer findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingServerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first meeting server in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meeting server, or <code>null</code> if a matching meeting server could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingServer fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last meeting server in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meeting server
	* @throws com.liferay.bbb.NoSuchMeetingServerException if a matching meeting server could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingServer findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingServerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last meeting server in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meeting server, or <code>null</code> if a matching meeting server could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingServer fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meeting servers before and after the current meeting server in the ordered set where groupId = &#63;.
	*
	* @param meetingServerId the primary key of the current meeting server
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meeting server
	* @throws com.liferay.bbb.NoSuchMeetingServerException if a meeting server with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingServer[] findByGroupId_PrevAndNext(
		long meetingServerId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingServerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the meeting servers where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meeting servers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching meeting servers
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the meeting server in the entity cache if it is enabled.
	*
	* @param meetingServer the meeting server
	*/
	public void cacheResult(com.liferay.bbb.model.MeetingServer meetingServer);

	/**
	* Caches the meeting servers in the entity cache if it is enabled.
	*
	* @param meetingServers the meeting servers
	*/
	public void cacheResult(
		java.util.List<com.liferay.bbb.model.MeetingServer> meetingServers);

	/**
	* Creates a new meeting server with the primary key. Does not add the meeting server to the database.
	*
	* @param meetingServerId the primary key for the new meeting server
	* @return the new meeting server
	*/
	public com.liferay.bbb.model.MeetingServer create(long meetingServerId);

	/**
	* Removes the meeting server with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetingServerId the primary key of the meeting server
	* @return the meeting server that was removed
	* @throws com.liferay.bbb.NoSuchMeetingServerException if a meeting server with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingServer remove(long meetingServerId)
		throws com.liferay.bbb.NoSuchMeetingServerException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.bbb.model.MeetingServer updateImpl(
		com.liferay.bbb.model.MeetingServer meetingServer)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meeting server with the primary key or throws a {@link com.liferay.bbb.NoSuchMeetingServerException} if it could not be found.
	*
	* @param meetingServerId the primary key of the meeting server
	* @return the meeting server
	* @throws com.liferay.bbb.NoSuchMeetingServerException if a meeting server with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingServer findByPrimaryKey(
		long meetingServerId)
		throws com.liferay.bbb.NoSuchMeetingServerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meeting server with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetingServerId the primary key of the meeting server
	* @return the meeting server, or <code>null</code> if a meeting server with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingServer fetchByPrimaryKey(
		long meetingServerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the meeting servers.
	*
	* @return the meeting servers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingServer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meeting servers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meeting servers
	* @param end the upper bound of the range of meeting servers (not inclusive)
	* @return the range of meeting servers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingServer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meeting servers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingServerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meeting servers
	* @param end the upper bound of the range of meeting servers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of meeting servers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingServer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the meeting servers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meeting servers.
	*
	* @return the number of meeting servers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}