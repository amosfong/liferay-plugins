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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the meeting participant service. This utility wraps {@link MeetingParticipantPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingParticipantPersistence
 * @see MeetingParticipantPersistenceImpl
 * @generated
 */
public class MeetingParticipantUtil {
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
	public static void clearCache(MeetingParticipant meetingParticipant) {
		getPersistence().clearCache(meetingParticipant);
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
	public static List<MeetingParticipant> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MeetingParticipant> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MeetingParticipant> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static MeetingParticipant update(
		MeetingParticipant meetingParticipant) throws SystemException {
		return getPersistence().update(meetingParticipant);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static MeetingParticipant update(
		MeetingParticipant meetingParticipant, ServiceContext serviceContext)
		throws SystemException {
		return getPersistence().update(meetingParticipant, serviceContext);
	}

	/**
	* Returns all the meeting participants where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @return the matching meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.MeetingParticipant> findByMeetingEntryId(
		long meetingEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMeetingEntryId(meetingEntryId);
	}

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
	public static java.util.List<com.liferay.bbb.model.MeetingParticipant> findByMeetingEntryId(
		long meetingEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMeetingEntryId(meetingEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.bbb.model.MeetingParticipant> findByMeetingEntryId(
		long meetingEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetingEntryId(meetingEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first meeting participant in the ordered set where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meeting participant
	* @throws com.liferay.bbb.NoSuchMeetingParticipantException if a matching meeting participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingParticipant findByMeetingEntryId_First(
		long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetingEntryId_First(meetingEntryId, orderByComparator);
	}

	/**
	* Returns the first meeting participant in the ordered set where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meeting participant, or <code>null</code> if a matching meeting participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingParticipant fetchByMeetingEntryId_First(
		long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMeetingEntryId_First(meetingEntryId,
			orderByComparator);
	}

	/**
	* Returns the last meeting participant in the ordered set where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meeting participant
	* @throws com.liferay.bbb.NoSuchMeetingParticipantException if a matching meeting participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingParticipant findByMeetingEntryId_Last(
		long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetingEntryId_Last(meetingEntryId, orderByComparator);
	}

	/**
	* Returns the last meeting participant in the ordered set where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meeting participant, or <code>null</code> if a matching meeting participant could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingParticipant fetchByMeetingEntryId_Last(
		long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMeetingEntryId_Last(meetingEntryId, orderByComparator);
	}

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
	public static com.liferay.bbb.model.MeetingParticipant[] findByMeetingEntryId_PrevAndNext(
		long meetingParticipantId, long meetingEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetingEntryId_PrevAndNext(meetingParticipantId,
			meetingEntryId, orderByComparator);
	}

	/**
	* Removes all the meeting participants where meetingEntryId = &#63; from the database.
	*
	* @param meetingEntryId the meeting entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMeetingEntryId(long meetingEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByMeetingEntryId(meetingEntryId);
	}

	/**
	* Returns the number of meeting participants where meetingEntryId = &#63;.
	*
	* @param meetingEntryId the meeting entry ID
	* @return the number of matching meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMeetingEntryId(long meetingEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMeetingEntryId(meetingEntryId);
	}

	/**
	* Caches the meeting participant in the entity cache if it is enabled.
	*
	* @param meetingParticipant the meeting participant
	*/
	public static void cacheResult(
		com.liferay.bbb.model.MeetingParticipant meetingParticipant) {
		getPersistence().cacheResult(meetingParticipant);
	}

	/**
	* Caches the meeting participants in the entity cache if it is enabled.
	*
	* @param meetingParticipants the meeting participants
	*/
	public static void cacheResult(
		java.util.List<com.liferay.bbb.model.MeetingParticipant> meetingParticipants) {
		getPersistence().cacheResult(meetingParticipants);
	}

	/**
	* Creates a new meeting participant with the primary key. Does not add the meeting participant to the database.
	*
	* @param meetingParticipantId the primary key for the new meeting participant
	* @return the new meeting participant
	*/
	public static com.liferay.bbb.model.MeetingParticipant create(
		long meetingParticipantId) {
		return getPersistence().create(meetingParticipantId);
	}

	/**
	* Removes the meeting participant with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetingParticipantId the primary key of the meeting participant
	* @return the meeting participant that was removed
	* @throws com.liferay.bbb.NoSuchMeetingParticipantException if a meeting participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingParticipant remove(
		long meetingParticipantId)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(meetingParticipantId);
	}

	public static com.liferay.bbb.model.MeetingParticipant updateImpl(
		com.liferay.bbb.model.MeetingParticipant meetingParticipant)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(meetingParticipant);
	}

	/**
	* Returns the meeting participant with the primary key or throws a {@link com.liferay.bbb.NoSuchMeetingParticipantException} if it could not be found.
	*
	* @param meetingParticipantId the primary key of the meeting participant
	* @return the meeting participant
	* @throws com.liferay.bbb.NoSuchMeetingParticipantException if a meeting participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingParticipant findByPrimaryKey(
		long meetingParticipantId)
		throws com.liferay.bbb.NoSuchMeetingParticipantException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(meetingParticipantId);
	}

	/**
	* Returns the meeting participant with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetingParticipantId the primary key of the meeting participant
	* @return the meeting participant, or <code>null</code> if a meeting participant with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingParticipant fetchByPrimaryKey(
		long meetingParticipantId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(meetingParticipantId);
	}

	/**
	* Returns all the meeting participants.
	*
	* @return the meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.MeetingParticipant> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.bbb.model.MeetingParticipant> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.bbb.model.MeetingParticipant> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the meeting participants from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of meeting participants.
	*
	* @return the number of meeting participants
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MeetingParticipantPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MeetingParticipantPersistence)PortletBeanLocatorUtil.locate(com.liferay.bbb.service.ClpSerializer.getServletContextName(),
					MeetingParticipantPersistence.class.getName());

			ReferenceRegistry.registerReference(MeetingParticipantUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(MeetingParticipantPersistence persistence) {
	}

	private static MeetingParticipantPersistence _persistence;
}