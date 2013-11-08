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

import com.liferay.bbb.NoSuchMeetingParticipantException;
import com.liferay.bbb.model.MeetingParticipant;
import com.liferay.bbb.model.impl.MeetingParticipantImpl;
import com.liferay.bbb.model.impl.MeetingParticipantModelImpl;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the meeting participant service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingParticipantPersistence
 * @see MeetingParticipantUtil
 * @generated
 */
public class MeetingParticipantPersistenceImpl extends BasePersistenceImpl<MeetingParticipant>
	implements MeetingParticipantPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MeetingParticipantUtil} to access the meeting participant persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MeetingParticipantImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
			MeetingParticipantModelImpl.FINDER_CACHE_ENABLED,
			MeetingParticipantImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
			MeetingParticipantModelImpl.FINDER_CACHE_ENABLED,
			MeetingParticipantImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
			MeetingParticipantModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MEETINGENTRYID =
		new FinderPath(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
			MeetingParticipantModelImpl.FINDER_CACHE_ENABLED,
			MeetingParticipantImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMeetingEntryId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGENTRYID =
		new FinderPath(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
			MeetingParticipantModelImpl.FINDER_CACHE_ENABLED,
			MeetingParticipantImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMeetingEntryId",
			new String[] { Long.class.getName() },
			MeetingParticipantModelImpl.MEETINGENTRYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MEETINGENTRYID = new FinderPath(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
			MeetingParticipantModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMeetingEntryId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the meeting participants where meetingEntryId = &#63;.
	 *
	 * @param meetingEntryId the meeting entry ID
	 * @return the matching meeting participants
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MeetingParticipant> findByMeetingEntryId(long meetingEntryId)
		throws SystemException {
		return findByMeetingEntryId(meetingEntryId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<MeetingParticipant> findByMeetingEntryId(long meetingEntryId,
		int start, int end) throws SystemException {
		return findByMeetingEntryId(meetingEntryId, start, end, null);
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
	@Override
	public List<MeetingParticipant> findByMeetingEntryId(long meetingEntryId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGENTRYID;
			finderArgs = new Object[] { meetingEntryId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MEETINGENTRYID;
			finderArgs = new Object[] {
					meetingEntryId,
					
					start, end, orderByComparator
				};
		}

		List<MeetingParticipant> list = (List<MeetingParticipant>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MeetingParticipant meetingParticipant : list) {
				if ((meetingEntryId != meetingParticipant.getMeetingEntryId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_MEETINGPARTICIPANT_WHERE);

			query.append(_FINDER_COLUMN_MEETINGENTRYID_MEETINGENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MeetingParticipantModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetingEntryId);

				if (!pagination) {
					list = (List<MeetingParticipant>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<MeetingParticipant>(list);
				}
				else {
					list = (List<MeetingParticipant>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public MeetingParticipant findByMeetingEntryId_First(long meetingEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetingParticipantException, SystemException {
		MeetingParticipant meetingParticipant = fetchByMeetingEntryId_First(meetingEntryId,
				orderByComparator);

		if (meetingParticipant != null) {
			return meetingParticipant;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("meetingEntryId=");
		msg.append(meetingEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingParticipantException(msg.toString());
	}

	/**
	 * Returns the first meeting participant in the ordered set where meetingEntryId = &#63;.
	 *
	 * @param meetingEntryId the meeting entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meeting participant, or <code>null</code> if a matching meeting participant could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingParticipant fetchByMeetingEntryId_First(long meetingEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MeetingParticipant> list = findByMeetingEntryId(meetingEntryId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MeetingParticipant findByMeetingEntryId_Last(long meetingEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetingParticipantException, SystemException {
		MeetingParticipant meetingParticipant = fetchByMeetingEntryId_Last(meetingEntryId,
				orderByComparator);

		if (meetingParticipant != null) {
			return meetingParticipant;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("meetingEntryId=");
		msg.append(meetingEntryId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingParticipantException(msg.toString());
	}

	/**
	 * Returns the last meeting participant in the ordered set where meetingEntryId = &#63;.
	 *
	 * @param meetingEntryId the meeting entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meeting participant, or <code>null</code> if a matching meeting participant could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingParticipant fetchByMeetingEntryId_Last(long meetingEntryId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMeetingEntryId(meetingEntryId);

		if (count == 0) {
			return null;
		}

		List<MeetingParticipant> list = findByMeetingEntryId(meetingEntryId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MeetingParticipant[] findByMeetingEntryId_PrevAndNext(
		long meetingParticipantId, long meetingEntryId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetingParticipantException, SystemException {
		MeetingParticipant meetingParticipant = findByPrimaryKey(meetingParticipantId);

		Session session = null;

		try {
			session = openSession();

			MeetingParticipant[] array = new MeetingParticipantImpl[3];

			array[0] = getByMeetingEntryId_PrevAndNext(session,
					meetingParticipant, meetingEntryId, orderByComparator, true);

			array[1] = meetingParticipant;

			array[2] = getByMeetingEntryId_PrevAndNext(session,
					meetingParticipant, meetingEntryId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MeetingParticipant getByMeetingEntryId_PrevAndNext(
		Session session, MeetingParticipant meetingParticipant,
		long meetingEntryId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEETINGPARTICIPANT_WHERE);

		query.append(_FINDER_COLUMN_MEETINGENTRYID_MEETINGENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(MeetingParticipantModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(meetingEntryId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(meetingParticipant);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MeetingParticipant> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the meeting participants where meetingEntryId = &#63; from the database.
	 *
	 * @param meetingEntryId the meeting entry ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByMeetingEntryId(long meetingEntryId)
		throws SystemException {
		for (MeetingParticipant meetingParticipant : findByMeetingEntryId(
				meetingEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(meetingParticipant);
		}
	}

	/**
	 * Returns the number of meeting participants where meetingEntryId = &#63;.
	 *
	 * @param meetingEntryId the meeting entry ID
	 * @return the number of matching meeting participants
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByMeetingEntryId(long meetingEntryId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MEETINGENTRYID;

		Object[] finderArgs = new Object[] { meetingEntryId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEETINGPARTICIPANT_WHERE);

			query.append(_FINDER_COLUMN_MEETINGENTRYID_MEETINGENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetingEntryId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_MEETINGENTRYID_MEETINGENTRYID_2 = "meetingParticipant.meetingEntryId = ?";

	public MeetingParticipantPersistenceImpl() {
		setModelClass(MeetingParticipant.class);
	}

	/**
	 * Caches the meeting participant in the entity cache if it is enabled.
	 *
	 * @param meetingParticipant the meeting participant
	 */
	@Override
	public void cacheResult(MeetingParticipant meetingParticipant) {
		EntityCacheUtil.putResult(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
			MeetingParticipantImpl.class, meetingParticipant.getPrimaryKey(),
			meetingParticipant);

		meetingParticipant.resetOriginalValues();
	}

	/**
	 * Caches the meeting participants in the entity cache if it is enabled.
	 *
	 * @param meetingParticipants the meeting participants
	 */
	@Override
	public void cacheResult(List<MeetingParticipant> meetingParticipants) {
		for (MeetingParticipant meetingParticipant : meetingParticipants) {
			if (EntityCacheUtil.getResult(
						MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
						MeetingParticipantImpl.class,
						meetingParticipant.getPrimaryKey()) == null) {
				cacheResult(meetingParticipant);
			}
			else {
				meetingParticipant.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all meeting participants.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MeetingParticipantImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MeetingParticipantImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the meeting participant.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MeetingParticipant meetingParticipant) {
		EntityCacheUtil.removeResult(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
			MeetingParticipantImpl.class, meetingParticipant.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MeetingParticipant> meetingParticipants) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MeetingParticipant meetingParticipant : meetingParticipants) {
			EntityCacheUtil.removeResult(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
				MeetingParticipantImpl.class, meetingParticipant.getPrimaryKey());
		}
	}

	/**
	 * Creates a new meeting participant with the primary key. Does not add the meeting participant to the database.
	 *
	 * @param meetingParticipantId the primary key for the new meeting participant
	 * @return the new meeting participant
	 */
	@Override
	public MeetingParticipant create(long meetingParticipantId) {
		MeetingParticipant meetingParticipant = new MeetingParticipantImpl();

		meetingParticipant.setNew(true);
		meetingParticipant.setPrimaryKey(meetingParticipantId);

		return meetingParticipant;
	}

	/**
	 * Removes the meeting participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetingParticipantId the primary key of the meeting participant
	 * @return the meeting participant that was removed
	 * @throws com.liferay.bbb.NoSuchMeetingParticipantException if a meeting participant with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingParticipant remove(long meetingParticipantId)
		throws NoSuchMeetingParticipantException, SystemException {
		return remove((Serializable)meetingParticipantId);
	}

	/**
	 * Removes the meeting participant with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the meeting participant
	 * @return the meeting participant that was removed
	 * @throws com.liferay.bbb.NoSuchMeetingParticipantException if a meeting participant with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingParticipant remove(Serializable primaryKey)
		throws NoSuchMeetingParticipantException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetingParticipant meetingParticipant = (MeetingParticipant)session.get(MeetingParticipantImpl.class,
					primaryKey);

			if (meetingParticipant == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMeetingParticipantException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(meetingParticipant);
		}
		catch (NoSuchMeetingParticipantException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected MeetingParticipant removeImpl(
		MeetingParticipant meetingParticipant) throws SystemException {
		meetingParticipant = toUnwrappedModel(meetingParticipant);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(meetingParticipant)) {
				meetingParticipant = (MeetingParticipant)session.get(MeetingParticipantImpl.class,
						meetingParticipant.getPrimaryKeyObj());
			}

			if (meetingParticipant != null) {
				session.delete(meetingParticipant);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (meetingParticipant != null) {
			clearCache(meetingParticipant);
		}

		return meetingParticipant;
	}

	@Override
	public MeetingParticipant updateImpl(
		com.liferay.bbb.model.MeetingParticipant meetingParticipant)
		throws SystemException {
		meetingParticipant = toUnwrappedModel(meetingParticipant);

		boolean isNew = meetingParticipant.isNew();

		MeetingParticipantModelImpl meetingParticipantModelImpl = (MeetingParticipantModelImpl)meetingParticipant;

		Session session = null;

		try {
			session = openSession();

			if (meetingParticipant.isNew()) {
				session.save(meetingParticipant);

				meetingParticipant.setNew(false);
			}
			else {
				session.merge(meetingParticipant);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MeetingParticipantModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((meetingParticipantModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGENTRYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						meetingParticipantModelImpl.getOriginalMeetingEntryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MEETINGENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGENTRYID,
					args);

				args = new Object[] {
						meetingParticipantModelImpl.getMeetingEntryId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MEETINGENTRYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGENTRYID,
					args);
			}
		}

		EntityCacheUtil.putResult(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
			MeetingParticipantImpl.class, meetingParticipant.getPrimaryKey(),
			meetingParticipant);

		return meetingParticipant;
	}

	protected MeetingParticipant toUnwrappedModel(
		MeetingParticipant meetingParticipant) {
		if (meetingParticipant instanceof MeetingParticipantImpl) {
			return meetingParticipant;
		}

		MeetingParticipantImpl meetingParticipantImpl = new MeetingParticipantImpl();

		meetingParticipantImpl.setNew(meetingParticipant.isNew());
		meetingParticipantImpl.setPrimaryKey(meetingParticipant.getPrimaryKey());

		meetingParticipantImpl.setMeetingParticipantId(meetingParticipant.getMeetingParticipantId());
		meetingParticipantImpl.setGroupId(meetingParticipant.getGroupId());
		meetingParticipantImpl.setCompanyId(meetingParticipant.getCompanyId());
		meetingParticipantImpl.setUserId(meetingParticipant.getUserId());
		meetingParticipantImpl.setUserName(meetingParticipant.getUserName());
		meetingParticipantImpl.setCreateDate(meetingParticipant.getCreateDate());
		meetingParticipantImpl.setModifiedDate(meetingParticipant.getModifiedDate());
		meetingParticipantImpl.setMeetingEntryId(meetingParticipant.getMeetingEntryId());
		meetingParticipantImpl.setName(meetingParticipant.getName());
		meetingParticipantImpl.setEmailAddress(meetingParticipant.getEmailAddress());

		return meetingParticipantImpl;
	}

	/**
	 * Returns the meeting participant with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the meeting participant
	 * @return the meeting participant
	 * @throws com.liferay.bbb.NoSuchMeetingParticipantException if a meeting participant with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingParticipant findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMeetingParticipantException, SystemException {
		MeetingParticipant meetingParticipant = fetchByPrimaryKey(primaryKey);

		if (meetingParticipant == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMeetingParticipantException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return meetingParticipant;
	}

	/**
	 * Returns the meeting participant with the primary key or throws a {@link com.liferay.bbb.NoSuchMeetingParticipantException} if it could not be found.
	 *
	 * @param meetingParticipantId the primary key of the meeting participant
	 * @return the meeting participant
	 * @throws com.liferay.bbb.NoSuchMeetingParticipantException if a meeting participant with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingParticipant findByPrimaryKey(long meetingParticipantId)
		throws NoSuchMeetingParticipantException, SystemException {
		return findByPrimaryKey((Serializable)meetingParticipantId);
	}

	/**
	 * Returns the meeting participant with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the meeting participant
	 * @return the meeting participant, or <code>null</code> if a meeting participant with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingParticipant fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		MeetingParticipant meetingParticipant = (MeetingParticipant)EntityCacheUtil.getResult(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
				MeetingParticipantImpl.class, primaryKey);

		if (meetingParticipant == _nullMeetingParticipant) {
			return null;
		}

		if (meetingParticipant == null) {
			Session session = null;

			try {
				session = openSession();

				meetingParticipant = (MeetingParticipant)session.get(MeetingParticipantImpl.class,
						primaryKey);

				if (meetingParticipant != null) {
					cacheResult(meetingParticipant);
				}
				else {
					EntityCacheUtil.putResult(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
						MeetingParticipantImpl.class, primaryKey,
						_nullMeetingParticipant);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(MeetingParticipantModelImpl.ENTITY_CACHE_ENABLED,
					MeetingParticipantImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return meetingParticipant;
	}

	/**
	 * Returns the meeting participant with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param meetingParticipantId the primary key of the meeting participant
	 * @return the meeting participant, or <code>null</code> if a meeting participant with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingParticipant fetchByPrimaryKey(long meetingParticipantId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)meetingParticipantId);
	}

	/**
	 * Returns all the meeting participants.
	 *
	 * @return the meeting participants
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MeetingParticipant> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MeetingParticipant> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<MeetingParticipant> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<MeetingParticipant> list = (List<MeetingParticipant>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MEETINGPARTICIPANT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEETINGPARTICIPANT;

				if (pagination) {
					sql = sql.concat(MeetingParticipantModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MeetingParticipant>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<MeetingParticipant>(list);
				}
				else {
					list = (List<MeetingParticipant>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the meeting participants from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (MeetingParticipant meetingParticipant : findAll()) {
			remove(meetingParticipant);
		}
	}

	/**
	 * Returns the number of meeting participants.
	 *
	 * @return the number of meeting participants
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEETINGPARTICIPANT);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the meeting participant persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.bbb.model.MeetingParticipant")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MeetingParticipant>> listenersList = new ArrayList<ModelListener<MeetingParticipant>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MeetingParticipant>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(MeetingParticipantImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_MEETINGPARTICIPANT = "SELECT meetingParticipant FROM MeetingParticipant meetingParticipant";
	private static final String _SQL_SELECT_MEETINGPARTICIPANT_WHERE = "SELECT meetingParticipant FROM MeetingParticipant meetingParticipant WHERE ";
	private static final String _SQL_COUNT_MEETINGPARTICIPANT = "SELECT COUNT(meetingParticipant) FROM MeetingParticipant meetingParticipant";
	private static final String _SQL_COUNT_MEETINGPARTICIPANT_WHERE = "SELECT COUNT(meetingParticipant) FROM MeetingParticipant meetingParticipant WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "meetingParticipant.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MeetingParticipant exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MeetingParticipant exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MeetingParticipantPersistenceImpl.class);
	private static MeetingParticipant _nullMeetingParticipant = new MeetingParticipantImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MeetingParticipant> toCacheModel() {
				return _nullMeetingParticipantCacheModel;
			}
		};

	private static CacheModel<MeetingParticipant> _nullMeetingParticipantCacheModel =
		new CacheModel<MeetingParticipant>() {
			@Override
			public MeetingParticipant toEntityModel() {
				return _nullMeetingParticipant;
			}
		};
}