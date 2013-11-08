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

import com.liferay.bbb.NoSuchMeetingEntryException;
import com.liferay.bbb.model.MeetingEntry;
import com.liferay.bbb.model.impl.MeetingEntryImpl;
import com.liferay.bbb.model.impl.MeetingEntryModelImpl;

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
 * The persistence implementation for the meeting entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingEntryPersistence
 * @see MeetingEntryUtil
 * @generated
 */
public class MeetingEntryPersistenceImpl extends BasePersistenceImpl<MeetingEntry>
	implements MeetingEntryPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MeetingEntryUtil} to access the meeting entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MeetingEntryImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetingEntryModelImpl.FINDER_CACHE_ENABLED, MeetingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetingEntryModelImpl.FINDER_CACHE_ENABLED, MeetingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MEETINGSERVERID =
		new FinderPath(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetingEntryModelImpl.FINDER_CACHE_ENABLED, MeetingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByMeetingServerId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGSERVERID =
		new FinderPath(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetingEntryModelImpl.FINDER_CACHE_ENABLED, MeetingEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByMeetingServerId",
			new String[] { Long.class.getName() },
			MeetingEntryModelImpl.MEETINGSERVERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MEETINGSERVERID = new FinderPath(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetingEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByMeetingServerId", new String[] { Long.class.getName() });

	/**
	 * Returns all the meeting entries where meetingServerId = &#63;.
	 *
	 * @param meetingServerId the meeting server ID
	 * @return the matching meeting entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MeetingEntry> findByMeetingServerId(long meetingServerId)
		throws SystemException {
		return findByMeetingServerId(meetingServerId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<MeetingEntry> findByMeetingServerId(long meetingServerId,
		int start, int end) throws SystemException {
		return findByMeetingServerId(meetingServerId, start, end, null);
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
	@Override
	public List<MeetingEntry> findByMeetingServerId(long meetingServerId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGSERVERID;
			finderArgs = new Object[] { meetingServerId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MEETINGSERVERID;
			finderArgs = new Object[] {
					meetingServerId,
					
					start, end, orderByComparator
				};
		}

		List<MeetingEntry> list = (List<MeetingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MeetingEntry meetingEntry : list) {
				if ((meetingServerId != meetingEntry.getMeetingServerId())) {
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

			query.append(_SQL_SELECT_MEETINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_MEETINGSERVERID_MEETINGSERVERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MeetingEntryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetingServerId);

				if (!pagination) {
					list = (List<MeetingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<MeetingEntry>(list);
				}
				else {
					list = (List<MeetingEntry>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first meeting entry in the ordered set where meetingServerId = &#63;.
	 *
	 * @param meetingServerId the meeting server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meeting entry
	 * @throws com.liferay.bbb.NoSuchMeetingEntryException if a matching meeting entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingEntry findByMeetingServerId_First(long meetingServerId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetingEntryException, SystemException {
		MeetingEntry meetingEntry = fetchByMeetingServerId_First(meetingServerId,
				orderByComparator);

		if (meetingEntry != null) {
			return meetingEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("meetingServerId=");
		msg.append(meetingServerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingEntryException(msg.toString());
	}

	/**
	 * Returns the first meeting entry in the ordered set where meetingServerId = &#63;.
	 *
	 * @param meetingServerId the meeting server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meeting entry, or <code>null</code> if a matching meeting entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingEntry fetchByMeetingServerId_First(long meetingServerId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MeetingEntry> list = findByMeetingServerId(meetingServerId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MeetingEntry findByMeetingServerId_Last(long meetingServerId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetingEntryException, SystemException {
		MeetingEntry meetingEntry = fetchByMeetingServerId_Last(meetingServerId,
				orderByComparator);

		if (meetingEntry != null) {
			return meetingEntry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("meetingServerId=");
		msg.append(meetingServerId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingEntryException(msg.toString());
	}

	/**
	 * Returns the last meeting entry in the ordered set where meetingServerId = &#63;.
	 *
	 * @param meetingServerId the meeting server ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meeting entry, or <code>null</code> if a matching meeting entry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingEntry fetchByMeetingServerId_Last(long meetingServerId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMeetingServerId(meetingServerId);

		if (count == 0) {
			return null;
		}

		List<MeetingEntry> list = findByMeetingServerId(meetingServerId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public MeetingEntry[] findByMeetingServerId_PrevAndNext(
		long meetingEntryId, long meetingServerId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetingEntryException, SystemException {
		MeetingEntry meetingEntry = findByPrimaryKey(meetingEntryId);

		Session session = null;

		try {
			session = openSession();

			MeetingEntry[] array = new MeetingEntryImpl[3];

			array[0] = getByMeetingServerId_PrevAndNext(session, meetingEntry,
					meetingServerId, orderByComparator, true);

			array[1] = meetingEntry;

			array[2] = getByMeetingServerId_PrevAndNext(session, meetingEntry,
					meetingServerId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MeetingEntry getByMeetingServerId_PrevAndNext(Session session,
		MeetingEntry meetingEntry, long meetingServerId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEETINGENTRY_WHERE);

		query.append(_FINDER_COLUMN_MEETINGSERVERID_MEETINGSERVERID_2);

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
			query.append(MeetingEntryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(meetingServerId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(meetingEntry);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MeetingEntry> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the meeting entries where meetingServerId = &#63; from the database.
	 *
	 * @param meetingServerId the meeting server ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByMeetingServerId(long meetingServerId)
		throws SystemException {
		for (MeetingEntry meetingEntry : findByMeetingServerId(
				meetingServerId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(meetingEntry);
		}
	}

	/**
	 * Returns the number of meeting entries where meetingServerId = &#63;.
	 *
	 * @param meetingServerId the meeting server ID
	 * @return the number of matching meeting entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByMeetingServerId(long meetingServerId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_MEETINGSERVERID;

		Object[] finderArgs = new Object[] { meetingServerId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEETINGENTRY_WHERE);

			query.append(_FINDER_COLUMN_MEETINGSERVERID_MEETINGSERVERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(meetingServerId);

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

	private static final String _FINDER_COLUMN_MEETINGSERVERID_MEETINGSERVERID_2 =
		"meetingEntry.meetingServerId = ?";

	public MeetingEntryPersistenceImpl() {
		setModelClass(MeetingEntry.class);
	}

	/**
	 * Caches the meeting entry in the entity cache if it is enabled.
	 *
	 * @param meetingEntry the meeting entry
	 */
	@Override
	public void cacheResult(MeetingEntry meetingEntry) {
		EntityCacheUtil.putResult(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetingEntryImpl.class, meetingEntry.getPrimaryKey(), meetingEntry);

		meetingEntry.resetOriginalValues();
	}

	/**
	 * Caches the meeting entries in the entity cache if it is enabled.
	 *
	 * @param meetingEntries the meeting entries
	 */
	@Override
	public void cacheResult(List<MeetingEntry> meetingEntries) {
		for (MeetingEntry meetingEntry : meetingEntries) {
			if (EntityCacheUtil.getResult(
						MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
						MeetingEntryImpl.class, meetingEntry.getPrimaryKey()) == null) {
				cacheResult(meetingEntry);
			}
			else {
				meetingEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all meeting entries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MeetingEntryImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MeetingEntryImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the meeting entry.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MeetingEntry meetingEntry) {
		EntityCacheUtil.removeResult(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetingEntryImpl.class, meetingEntry.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MeetingEntry> meetingEntries) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MeetingEntry meetingEntry : meetingEntries) {
			EntityCacheUtil.removeResult(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
				MeetingEntryImpl.class, meetingEntry.getPrimaryKey());
		}
	}

	/**
	 * Creates a new meeting entry with the primary key. Does not add the meeting entry to the database.
	 *
	 * @param meetingEntryId the primary key for the new meeting entry
	 * @return the new meeting entry
	 */
	@Override
	public MeetingEntry create(long meetingEntryId) {
		MeetingEntry meetingEntry = new MeetingEntryImpl();

		meetingEntry.setNew(true);
		meetingEntry.setPrimaryKey(meetingEntryId);

		return meetingEntry;
	}

	/**
	 * Removes the meeting entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetingEntryId the primary key of the meeting entry
	 * @return the meeting entry that was removed
	 * @throws com.liferay.bbb.NoSuchMeetingEntryException if a meeting entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingEntry remove(long meetingEntryId)
		throws NoSuchMeetingEntryException, SystemException {
		return remove((Serializable)meetingEntryId);
	}

	/**
	 * Removes the meeting entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the meeting entry
	 * @return the meeting entry that was removed
	 * @throws com.liferay.bbb.NoSuchMeetingEntryException if a meeting entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingEntry remove(Serializable primaryKey)
		throws NoSuchMeetingEntryException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetingEntry meetingEntry = (MeetingEntry)session.get(MeetingEntryImpl.class,
					primaryKey);

			if (meetingEntry == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMeetingEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(meetingEntry);
		}
		catch (NoSuchMeetingEntryException nsee) {
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
	protected MeetingEntry removeImpl(MeetingEntry meetingEntry)
		throws SystemException {
		meetingEntry = toUnwrappedModel(meetingEntry);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(meetingEntry)) {
				meetingEntry = (MeetingEntry)session.get(MeetingEntryImpl.class,
						meetingEntry.getPrimaryKeyObj());
			}

			if (meetingEntry != null) {
				session.delete(meetingEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (meetingEntry != null) {
			clearCache(meetingEntry);
		}

		return meetingEntry;
	}

	@Override
	public MeetingEntry updateImpl(
		com.liferay.bbb.model.MeetingEntry meetingEntry)
		throws SystemException {
		meetingEntry = toUnwrappedModel(meetingEntry);

		boolean isNew = meetingEntry.isNew();

		MeetingEntryModelImpl meetingEntryModelImpl = (MeetingEntryModelImpl)meetingEntry;

		Session session = null;

		try {
			session = openSession();

			if (meetingEntry.isNew()) {
				session.save(meetingEntry);

				meetingEntry.setNew(false);
			}
			else {
				session.merge(meetingEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MeetingEntryModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((meetingEntryModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGSERVERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						meetingEntryModelImpl.getOriginalMeetingServerId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MEETINGSERVERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGSERVERID,
					args);

				args = new Object[] { meetingEntryModelImpl.getMeetingServerId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MEETINGSERVERID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MEETINGSERVERID,
					args);
			}
		}

		EntityCacheUtil.putResult(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
			MeetingEntryImpl.class, meetingEntry.getPrimaryKey(), meetingEntry);

		return meetingEntry;
	}

	protected MeetingEntry toUnwrappedModel(MeetingEntry meetingEntry) {
		if (meetingEntry instanceof MeetingEntryImpl) {
			return meetingEntry;
		}

		MeetingEntryImpl meetingEntryImpl = new MeetingEntryImpl();

		meetingEntryImpl.setNew(meetingEntry.isNew());
		meetingEntryImpl.setPrimaryKey(meetingEntry.getPrimaryKey());

		meetingEntryImpl.setMeetingEntryId(meetingEntry.getMeetingEntryId());
		meetingEntryImpl.setGroupId(meetingEntry.getGroupId());
		meetingEntryImpl.setCompanyId(meetingEntry.getCompanyId());
		meetingEntryImpl.setUserId(meetingEntry.getUserId());
		meetingEntryImpl.setUserName(meetingEntry.getUserName());
		meetingEntryImpl.setCreateDate(meetingEntry.getCreateDate());
		meetingEntryImpl.setModifiedDate(meetingEntry.getModifiedDate());
		meetingEntryImpl.setMeetingServerId(meetingEntry.getMeetingServerId());
		meetingEntryImpl.setName(meetingEntry.getName());
		meetingEntryImpl.setAttendeePassword(meetingEntry.getAttendeePassword());
		meetingEntryImpl.setModeratorPassword(meetingEntry.getModeratorPassword());
		meetingEntryImpl.setStatus(meetingEntry.getStatus());

		return meetingEntryImpl;
	}

	/**
	 * Returns the meeting entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the meeting entry
	 * @return the meeting entry
	 * @throws com.liferay.bbb.NoSuchMeetingEntryException if a meeting entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMeetingEntryException, SystemException {
		MeetingEntry meetingEntry = fetchByPrimaryKey(primaryKey);

		if (meetingEntry == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMeetingEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return meetingEntry;
	}

	/**
	 * Returns the meeting entry with the primary key or throws a {@link com.liferay.bbb.NoSuchMeetingEntryException} if it could not be found.
	 *
	 * @param meetingEntryId the primary key of the meeting entry
	 * @return the meeting entry
	 * @throws com.liferay.bbb.NoSuchMeetingEntryException if a meeting entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingEntry findByPrimaryKey(long meetingEntryId)
		throws NoSuchMeetingEntryException, SystemException {
		return findByPrimaryKey((Serializable)meetingEntryId);
	}

	/**
	 * Returns the meeting entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the meeting entry
	 * @return the meeting entry, or <code>null</code> if a meeting entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingEntry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		MeetingEntry meetingEntry = (MeetingEntry)EntityCacheUtil.getResult(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
				MeetingEntryImpl.class, primaryKey);

		if (meetingEntry == _nullMeetingEntry) {
			return null;
		}

		if (meetingEntry == null) {
			Session session = null;

			try {
				session = openSession();

				meetingEntry = (MeetingEntry)session.get(MeetingEntryImpl.class,
						primaryKey);

				if (meetingEntry != null) {
					cacheResult(meetingEntry);
				}
				else {
					EntityCacheUtil.putResult(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
						MeetingEntryImpl.class, primaryKey, _nullMeetingEntry);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(MeetingEntryModelImpl.ENTITY_CACHE_ENABLED,
					MeetingEntryImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return meetingEntry;
	}

	/**
	 * Returns the meeting entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param meetingEntryId the primary key of the meeting entry
	 * @return the meeting entry, or <code>null</code> if a meeting entry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingEntry fetchByPrimaryKey(long meetingEntryId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)meetingEntryId);
	}

	/**
	 * Returns all the meeting entries.
	 *
	 * @return the meeting entries
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MeetingEntry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<MeetingEntry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<MeetingEntry> findAll(int start, int end,
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

		List<MeetingEntry> list = (List<MeetingEntry>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MEETINGENTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEETINGENTRY;

				if (pagination) {
					sql = sql.concat(MeetingEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MeetingEntry>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<MeetingEntry>(list);
				}
				else {
					list = (List<MeetingEntry>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the meeting entries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (MeetingEntry meetingEntry : findAll()) {
			remove(meetingEntry);
		}
	}

	/**
	 * Returns the number of meeting entries.
	 *
	 * @return the number of meeting entries
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

				Query q = session.createQuery(_SQL_COUNT_MEETINGENTRY);

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
	 * Initializes the meeting entry persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.bbb.model.MeetingEntry")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MeetingEntry>> listenersList = new ArrayList<ModelListener<MeetingEntry>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MeetingEntry>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(MeetingEntryImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_MEETINGENTRY = "SELECT meetingEntry FROM MeetingEntry meetingEntry";
	private static final String _SQL_SELECT_MEETINGENTRY_WHERE = "SELECT meetingEntry FROM MeetingEntry meetingEntry WHERE ";
	private static final String _SQL_COUNT_MEETINGENTRY = "SELECT COUNT(meetingEntry) FROM MeetingEntry meetingEntry";
	private static final String _SQL_COUNT_MEETINGENTRY_WHERE = "SELECT COUNT(meetingEntry) FROM MeetingEntry meetingEntry WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "meetingEntry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MeetingEntry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MeetingEntry exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MeetingEntryPersistenceImpl.class);
	private static MeetingEntry _nullMeetingEntry = new MeetingEntryImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MeetingEntry> toCacheModel() {
				return _nullMeetingEntryCacheModel;
			}
		};

	private static CacheModel<MeetingEntry> _nullMeetingEntryCacheModel = new CacheModel<MeetingEntry>() {
			@Override
			public MeetingEntry toEntityModel() {
				return _nullMeetingEntry;
			}
		};
}