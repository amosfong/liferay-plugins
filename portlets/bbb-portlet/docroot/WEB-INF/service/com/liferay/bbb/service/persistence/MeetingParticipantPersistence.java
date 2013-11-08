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

import com.liferay.bbb.model.MeetingParticipant;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the meeting participant service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingParticipantPersistenceImpl
 * @see MeetingParticipantUtil
 * @generated
 */
public interface MeetingParticipantPersistence extends BasePersistence<MeetingParticipant> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MeetingParticipantUtil} to access the meeting participant persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the meeting participants where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @return the matching meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingParticipant> findByMeetingEntryId(
		long meetingEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meeting participants where meetingEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetingEntryId the meeting entry ID
	* @param start the lower bound of the range of meeting participants
	* @param end the upper bound of the range of meeting participants (not inclusive)
	* @return the range of matching meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingParticipant> findByMeetingEntryId(
		long meetingEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meeting participants where meetingEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetingEntryId the meeting entry ID
	* @param start the lower bound of the range of meeting participants
	* @param end the upper bound of the range of meeting participants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingParticipant> findByMeetingEntryId(
		long meetingEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first meeting participant in the ordered set where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meeting participant
	* @throws com.liferay.bbb.NoSuchMeetingParticipantException if a matching meeting participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingParticipant findByMeetingEntryId_First(
		long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first meeting participant in the ordered set where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meeting participant, or <code>null</code> if a matching meeting participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingParticipant fetchByMeetingEntryId_First(
		long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last meeting participant in the ordered set where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meeting participant
	* @throws com.liferay.bbb.NoSuchMeetingParticipantException if a matching meeting participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingParticipant findByMeetingEntryId_Last(
		long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last meeting participant in the ordered set where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meeting participant, or <code>null</code> if a matching meeting participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingParticipant fetchByMeetingEntryId_Last(
		long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meeting participants before and after the current meeting participant in the ordered set where meetingEntryId = &#63;.
	*
	* @param meetingParticipantId the primary key of the current meeting participant
	* @param meetingEntryId the meeting entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meeting participant
	* @throws com.liferay.bbb.NoSuchMeetingParticipantException if a meeting participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingParticipant[] findByMeetingEntryId_PrevAndNext(
		long meetingParticipantId, long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the meeting participants where meetingEntryId = &#63; from the database.
	*
	* @param meetingEntryId the meeting entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMeetingEntryId(long meetingEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meeting participants where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @return the number of matching meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public int countByMeetingEntryId(long meetingEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the meeting participant in the entity cache if it is enabled.
	*
	* @param meetingParticipant the meeting participant
	*/
	public void cacheResult(
		com.liferay.bbb.model.MeetingParticipant meetingParticipant);

	/**
	* Caches the meeting participants in the entity cache if it is enabled.
	*
	* @param meetingParticipants the meeting participants
	*/
	public void cacheResult(
		java.util.List<com.liferay.bbb.model.MeetingParticipant> meetingParticipants);

	/**
	* Creates a new meeting participant with the primary key. Does not add the meeting participant to the database.
	*
	* @param meetingParticipantId the primary key for the new meeting participant
	* @return the new meeting participant
	*/
	public com.liferay.bbb.model.MeetingParticipant create(
		long meetingParticipantId);

	/**
	* Removes the meeting participant with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetingParticipantId the primary key of the meeting participant
	* @return the meeting participant that was removed
	* @throws com.liferay.bbb.NoSuchMeetingParticipantException if a meeting participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingParticipant remove(
		long meetingParticipantId)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.bbb.model.MeetingParticipant updateImpl(
		com.liferay.bbb.model.MeetingParticipant meetingParticipant)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meeting participant with the primary key or throws a {@link com.liferay.bbb.NoSuchMeetingParticipantException} if it could not be found.
	*
	* @param meetingParticipantId the primary key of the meeting participant
	* @return the meeting participant
	* @throws com.liferay.bbb.NoSuchMeetingParticipantException if a meeting participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingParticipant findByPrimaryKey(
		long meetingParticipantId)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meeting participant with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetingParticipantId the primary key of the meeting participant
	* @return the meeting participant, or <code>null</code> if a meeting participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.bbb.model.MeetingParticipant fetchByPrimaryKey(
		long meetingParticipantId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the meeting participants.
	*
	* @return the meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingParticipant> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meeting participants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meeting participants
	* @param end the upper bound of the range of meeting participants (not inclusive)
	* @return the range of meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingParticipant> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meeting participants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingParticipantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meeting participants
	* @param end the upper bound of the range of meeting participants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.bbb.model.MeetingParticipant> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the meeting participants from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meeting participants.
	*
	* @return the number of meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}