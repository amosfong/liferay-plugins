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

import com.liferay.bbb.model.MeetingEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the meeting entry service. This utility wraps {@link MeetingEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingEntryPersistence
 * @see MeetingEntryPersistenceImpl
 * @generated
 */
public class MeetingEntryUtil {
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
	public static void clearCache(MeetingEntry meetingEntry) {
		getPersistence().clearCache(meetingEntry);
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
	public static List<MeetingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MeetingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MeetingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static MeetingEntry update(MeetingEntry meetingEntry)
		throws SystemException {
		return getPersistence().update(meetingEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static MeetingEntry update(MeetingEntry meetingEntry,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(meetingEntry, serviceContext);
	}

	/**
	* Returns all the meeting entries where meetingServerId = &#63;.
	*
	* @param meetingServerId the meeting server ID
	* @return the matching meeting entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.MeetingEntry> findByMeetingServerId(
		long meetingServerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMeetingServerId(meetingServerId);
	}

	/**
	* Returns a range of all the meeting entries where meetingServerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetingServerId the meeting server ID
	* @param start the lower bound of the range of meeting entries
	* @param end the upper bound of the range of meeting entries (not inclusive)
	* @return the range of matching meeting entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.MeetingEntry> findByMeetingServerId(
		long meetingServerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetingServerId(meetingServerId, start, end);
	}

	/**
	* Returns an ordered range of all the meeting entries where meetingServerId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param meetingServerId the meeting server ID
	* @param start the lower bound of the range of meeting entries
	* @param end the upper bound of the range of meeting entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meeting entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.MeetingEntry> findByMeetingServerId(
		long meetingServerId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetingServerId(meetingServerId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first meeting entry in the ordered set where meetingServerId = &#63;.
	*
	* @param meetingServerId the meeting server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meeting entry
	* @throws com.liferay.bbb.NoSuchMeetingEntryException if a matching meeting entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingEntry findByMeetingServerId_First(
		long meetingServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetingServerId_First(meetingServerId,
			orderByComparator);
	}

	/**
	* Returns the first meeting entry in the ordered set where meetingServerId = &#63;.
	*
	* @param meetingServerId the meeting server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meeting entry, or <code>null</code> if a matching meeting entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingEntry fetchByMeetingServerId_First(
		long meetingServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMeetingServerId_First(meetingServerId,
			orderByComparator);
	}

	/**
	* Returns the last meeting entry in the ordered set where meetingServerId = &#63;.
	*
	* @param meetingServerId the meeting server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meeting entry
	* @throws com.liferay.bbb.NoSuchMeetingEntryException if a matching meeting entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingEntry findByMeetingServerId_Last(
		long meetingServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetingServerId_Last(meetingServerId,
			orderByComparator);
	}

	/**
	* Returns the last meeting entry in the ordered set where meetingServerId = &#63;.
	*
	* @param meetingServerId the meeting server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meeting entry, or <code>null</code> if a matching meeting entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingEntry fetchByMeetingServerId_Last(
		long meetingServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMeetingServerId_Last(meetingServerId,
			orderByComparator);
	}

	/**
	* Returns the meeting entries before and after the current meeting entry in the ordered set where meetingServerId = &#63;.
	*
	* @param meetingEntryId the primary key of the current meeting entry
	* @param meetingServerId the meeting server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meeting entry
	* @throws com.liferay.bbb.NoSuchMeetingEntryException if a meeting entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingEntry[] findByMeetingServerId_PrevAndNext(
		long meetingEntryId, long meetingServerId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.bbb.NoSuchMeetingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMeetingServerId_PrevAndNext(meetingEntryId,
			meetingServerId, orderByComparator);
	}

	/**
	* Removes all the meeting entries where meetingServerId = &#63; from the database.
	*
	* @param meetingServerId the meeting server ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMeetingServerId(long meetingServerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByMeetingServerId(meetingServerId);
	}

	/**
	* Returns the number of meeting entries where meetingServerId = &#63;.
	*
	* @param meetingServerId the meeting server ID
	* @return the number of matching meeting entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMeetingServerId(long meetingServerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMeetingServerId(meetingServerId);
	}

	/**
	* Caches the meeting entry in the entity cache if it is enabled.
	*
	* @param meetingEntry the meeting entry
	*/
	public static void cacheResult(
		com.liferay.bbb.model.MeetingEntry meetingEntry) {
		getPersistence().cacheResult(meetingEntry);
	}

	/**
	* Caches the meeting entries in the entity cache if it is enabled.
	*
	* @param meetingEntries the meeting entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.bbb.model.MeetingEntry> meetingEntries) {
		getPersistence().cacheResult(meetingEntries);
	}

	/**
	* Creates a new meeting entry with the primary key. Does not add the meeting entry to the database.
	*
	* @param meetingEntryId the primary key for the new meeting entry
	* @return the new meeting entry
	*/
	public static com.liferay.bbb.model.MeetingEntry create(long meetingEntryId) {
		return getPersistence().create(meetingEntryId);
	}

	/**
	* Removes the meeting entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetingEntryId the primary key of the meeting entry
	* @return the meeting entry that was removed
	* @throws com.liferay.bbb.NoSuchMeetingEntryException if a meeting entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingEntry remove(long meetingEntryId)
		throws com.liferay.bbb.NoSuchMeetingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(meetingEntryId);
	}

	public static com.liferay.bbb.model.MeetingEntry updateImpl(
		com.liferay.bbb.model.MeetingEntry meetingEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(meetingEntry);
	}

	/**
	* Returns the meeting entry with the primary key or throws a {@link com.liferay.bbb.NoSuchMeetingEntryException} if it could not be found.
	*
	* @param meetingEntryId the primary key of the meeting entry
	* @return the meeting entry
	* @throws com.liferay.bbb.NoSuchMeetingEntryException if a meeting entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingEntry findByPrimaryKey(
		long meetingEntryId)
		throws com.liferay.bbb.NoSuchMeetingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(meetingEntryId);
	}

	/**
	* Returns the meeting entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetingEntryId the primary key of the meeting entry
	* @return the meeting entry, or <code>null</code> if a meeting entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.bbb.model.MeetingEntry fetchByPrimaryKey(
		long meetingEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(meetingEntryId);
	}

	/**
	* Returns all the meeting entries.
	*
	* @return the meeting entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.MeetingEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the meeting entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meeting entries
	* @param end the upper bound of the range of meeting entries (not inclusive)
	* @return the range of meeting entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.MeetingEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the meeting entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.bbb.model.impl.MeetingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of meeting entries
	* @param end the upper bound of the range of meeting entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of meeting entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.bbb.model.MeetingEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the meeting entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of meeting entries.
	*
	* @return the number of meeting entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static MeetingEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (MeetingEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.bbb.service.ClpSerializer.getServletContextName(),
					MeetingEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(MeetingEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(MeetingEntryPersistence persistence) {
	}

	private static MeetingEntryPersistence _persistence;
}