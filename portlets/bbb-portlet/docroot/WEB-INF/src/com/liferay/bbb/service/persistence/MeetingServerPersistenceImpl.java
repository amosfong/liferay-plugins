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

import com.liferay.bbb.NoSuchMeetingServerException;
import com.liferay.bbb.model.MeetingServer;
import com.liferay.bbb.model.impl.MeetingServerImpl;
import com.liferay.bbb.model.impl.MeetingServerModelImpl;

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
 * The persistence implementation for the meeting server service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingServerPersistence
 * @see MeetingServerUtil
 * @generated
 */
public class MeetingServerPersistenceImpl extends BasePersistenceImpl<MeetingServer>
	implements MeetingServerPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MeetingServerUtil} to access the meeting server persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MeetingServerImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
			MeetingServerModelImpl.FINDER_CACHE_ENABLED,
			MeetingServerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
			MeetingServerModelImpl.FINDER_CACHE_ENABLED,
			MeetingServerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
			MeetingServerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
			MeetingServerModelImpl.FINDER_CACHE_ENABLED,
			MeetingServerImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
			MeetingServerModelImpl.FINDER_CACHE_ENABLED,
			MeetingServerImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByGroupId", new String[] { Long.class.getName() },
			MeetingServerModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
			MeetingServerModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the meeting servers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching meeting servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MeetingServer> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<MeetingServer> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

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
	@Override
	public List<MeetingServer> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<MeetingServer> list = (List<MeetingServer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (MeetingServer meetingServer : list) {
				if ((groupId != meetingServer.getGroupId())) {
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

			query.append(_SQL_SELECT_MEETINGSERVER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MeetingServerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<MeetingServer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<MeetingServer>(list);
				}
				else {
					list = (List<MeetingServer>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first meeting server in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meeting server
	 * @throws com.liferay.bbb.NoSuchMeetingServerException if a matching meeting server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetingServerException, SystemException {
		MeetingServer meetingServer = fetchByGroupId_First(groupId,
				orderByComparator);

		if (meetingServer != null) {
			return meetingServer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingServerException(msg.toString());
	}

	/**
	 * Returns the first meeting server in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching meeting server, or <code>null</code> if a matching meeting server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<MeetingServer> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last meeting server in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meeting server
	 * @throws com.liferay.bbb.NoSuchMeetingServerException if a matching meeting server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchMeetingServerException, SystemException {
		MeetingServer meetingServer = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (meetingServer != null) {
			return meetingServer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMeetingServerException(msg.toString());
	}

	/**
	 * Returns the last meeting server in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching meeting server, or <code>null</code> if a matching meeting server could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<MeetingServer> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

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
	@Override
	public MeetingServer[] findByGroupId_PrevAndNext(long meetingServerId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchMeetingServerException, SystemException {
		MeetingServer meetingServer = findByPrimaryKey(meetingServerId);

		Session session = null;

		try {
			session = openSession();

			MeetingServer[] array = new MeetingServerImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, meetingServer,
					groupId, orderByComparator, true);

			array[1] = meetingServer;

			array[2] = getByGroupId_PrevAndNext(session, meetingServer,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MeetingServer getByGroupId_PrevAndNext(Session session,
		MeetingServer meetingServer, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_MEETINGSERVER_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(MeetingServerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(meetingServer);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MeetingServer> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the meeting servers where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (MeetingServer meetingServer : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(meetingServer);
		}
	}

	/**
	 * Returns the number of meeting servers where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching meeting servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_MEETINGSERVER_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "meetingServer.groupId = ?";

	public MeetingServerPersistenceImpl() {
		setModelClass(MeetingServer.class);
	}

	/**
	 * Caches the meeting server in the entity cache if it is enabled.
	 *
	 * @param meetingServer the meeting server
	 */
	@Override
	public void cacheResult(MeetingServer meetingServer) {
		EntityCacheUtil.putResult(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
			MeetingServerImpl.class, meetingServer.getPrimaryKey(),
			meetingServer);

		meetingServer.resetOriginalValues();
	}

	/**
	 * Caches the meeting servers in the entity cache if it is enabled.
	 *
	 * @param meetingServers the meeting servers
	 */
	@Override
	public void cacheResult(List<MeetingServer> meetingServers) {
		for (MeetingServer meetingServer : meetingServers) {
			if (EntityCacheUtil.getResult(
						MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
						MeetingServerImpl.class, meetingServer.getPrimaryKey()) == null) {
				cacheResult(meetingServer);
			}
			else {
				meetingServer.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all meeting servers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(MeetingServerImpl.class.getName());
		}

		EntityCacheUtil.clearCache(MeetingServerImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the meeting server.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MeetingServer meetingServer) {
		EntityCacheUtil.removeResult(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
			MeetingServerImpl.class, meetingServer.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<MeetingServer> meetingServers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MeetingServer meetingServer : meetingServers) {
			EntityCacheUtil.removeResult(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
				MeetingServerImpl.class, meetingServer.getPrimaryKey());
		}
	}

	/**
	 * Creates a new meeting server with the primary key. Does not add the meeting server to the database.
	 *
	 * @param meetingServerId the primary key for the new meeting server
	 * @return the new meeting server
	 */
	@Override
	public MeetingServer create(long meetingServerId) {
		MeetingServer meetingServer = new MeetingServerImpl();

		meetingServer.setNew(true);
		meetingServer.setPrimaryKey(meetingServerId);

		return meetingServer;
	}

	/**
	 * Removes the meeting server with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param meetingServerId the primary key of the meeting server
	 * @return the meeting server that was removed
	 * @throws com.liferay.bbb.NoSuchMeetingServerException if a meeting server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer remove(long meetingServerId)
		throws NoSuchMeetingServerException, SystemException {
		return remove((Serializable)meetingServerId);
	}

	/**
	 * Removes the meeting server with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the meeting server
	 * @return the meeting server that was removed
	 * @throws com.liferay.bbb.NoSuchMeetingServerException if a meeting server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer remove(Serializable primaryKey)
		throws NoSuchMeetingServerException, SystemException {
		Session session = null;

		try {
			session = openSession();

			MeetingServer meetingServer = (MeetingServer)session.get(MeetingServerImpl.class,
					primaryKey);

			if (meetingServer == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMeetingServerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(meetingServer);
		}
		catch (NoSuchMeetingServerException nsee) {
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
	protected MeetingServer removeImpl(MeetingServer meetingServer)
		throws SystemException {
		meetingServer = toUnwrappedModel(meetingServer);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(meetingServer)) {
				meetingServer = (MeetingServer)session.get(MeetingServerImpl.class,
						meetingServer.getPrimaryKeyObj());
			}

			if (meetingServer != null) {
				session.delete(meetingServer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (meetingServer != null) {
			clearCache(meetingServer);
		}

		return meetingServer;
	}

	@Override
	public MeetingServer updateImpl(
		com.liferay.bbb.model.MeetingServer meetingServer)
		throws SystemException {
		meetingServer = toUnwrappedModel(meetingServer);

		boolean isNew = meetingServer.isNew();

		MeetingServerModelImpl meetingServerModelImpl = (MeetingServerModelImpl)meetingServer;

		Session session = null;

		try {
			session = openSession();

			if (meetingServer.isNew()) {
				session.save(meetingServer);

				meetingServer.setNew(false);
			}
			else {
				session.merge(meetingServer);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MeetingServerModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((meetingServerModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						meetingServerModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { meetingServerModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
			MeetingServerImpl.class, meetingServer.getPrimaryKey(),
			meetingServer);

		return meetingServer;
	}

	protected MeetingServer toUnwrappedModel(MeetingServer meetingServer) {
		if (meetingServer instanceof MeetingServerImpl) {
			return meetingServer;
		}

		MeetingServerImpl meetingServerImpl = new MeetingServerImpl();

		meetingServerImpl.setNew(meetingServer.isNew());
		meetingServerImpl.setPrimaryKey(meetingServer.getPrimaryKey());

		meetingServerImpl.setMeetingServerId(meetingServer.getMeetingServerId());
		meetingServerImpl.setGroupId(meetingServer.getGroupId());
		meetingServerImpl.setCompanyId(meetingServer.getCompanyId());
		meetingServerImpl.setUserId(meetingServer.getUserId());
		meetingServerImpl.setUserName(meetingServer.getUserName());
		meetingServerImpl.setCreateDate(meetingServer.getCreateDate());
		meetingServerImpl.setModifiedDate(meetingServer.getModifiedDate());
		meetingServerImpl.setName(meetingServer.getName());
		meetingServerImpl.setUrl(meetingServer.getUrl());
		meetingServerImpl.setSecret(meetingServer.getSecret());

		return meetingServerImpl;
	}

	/**
	 * Returns the meeting server with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the meeting server
	 * @return the meeting server
	 * @throws com.liferay.bbb.NoSuchMeetingServerException if a meeting server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMeetingServerException, SystemException {
		MeetingServer meetingServer = fetchByPrimaryKey(primaryKey);

		if (meetingServer == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMeetingServerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return meetingServer;
	}

	/**
	 * Returns the meeting server with the primary key or throws a {@link com.liferay.bbb.NoSuchMeetingServerException} if it could not be found.
	 *
	 * @param meetingServerId the primary key of the meeting server
	 * @return the meeting server
	 * @throws com.liferay.bbb.NoSuchMeetingServerException if a meeting server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer findByPrimaryKey(long meetingServerId)
		throws NoSuchMeetingServerException, SystemException {
		return findByPrimaryKey((Serializable)meetingServerId);
	}

	/**
	 * Returns the meeting server with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the meeting server
	 * @return the meeting server, or <code>null</code> if a meeting server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		MeetingServer meetingServer = (MeetingServer)EntityCacheUtil.getResult(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
				MeetingServerImpl.class, primaryKey);

		if (meetingServer == _nullMeetingServer) {
			return null;
		}

		if (meetingServer == null) {
			Session session = null;

			try {
				session = openSession();

				meetingServer = (MeetingServer)session.get(MeetingServerImpl.class,
						primaryKey);

				if (meetingServer != null) {
					cacheResult(meetingServer);
				}
				else {
					EntityCacheUtil.putResult(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
						MeetingServerImpl.class, primaryKey, _nullMeetingServer);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(MeetingServerModelImpl.ENTITY_CACHE_ENABLED,
					MeetingServerImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return meetingServer;
	}

	/**
	 * Returns the meeting server with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param meetingServerId the primary key of the meeting server
	 * @return the meeting server, or <code>null</code> if a meeting server with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public MeetingServer fetchByPrimaryKey(long meetingServerId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)meetingServerId);
	}

	/**
	 * Returns all the meeting servers.
	 *
	 * @return the meeting servers
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<MeetingServer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	@Override
	public List<MeetingServer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

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
	@Override
	public List<MeetingServer> findAll(int start, int end,
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

		List<MeetingServer> list = (List<MeetingServer>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MEETINGSERVER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEETINGSERVER;

				if (pagination) {
					sql = sql.concat(MeetingServerModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MeetingServer>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<MeetingServer>(list);
				}
				else {
					list = (List<MeetingServer>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the meeting servers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (MeetingServer meetingServer : findAll()) {
			remove(meetingServer);
		}
	}

	/**
	 * Returns the number of meeting servers.
	 *
	 * @return the number of meeting servers
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

				Query q = session.createQuery(_SQL_COUNT_MEETINGSERVER);

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
	 * Initializes the meeting server persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.bbb.model.MeetingServer")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<MeetingServer>> listenersList = new ArrayList<ModelListener<MeetingServer>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<MeetingServer>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(MeetingServerImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_MEETINGSERVER = "SELECT meetingServer FROM MeetingServer meetingServer";
	private static final String _SQL_SELECT_MEETINGSERVER_WHERE = "SELECT meetingServer FROM MeetingServer meetingServer WHERE ";
	private static final String _SQL_COUNT_MEETINGSERVER = "SELECT COUNT(meetingServer) FROM MeetingServer meetingServer";
	private static final String _SQL_COUNT_MEETINGSERVER_WHERE = "SELECT COUNT(meetingServer) FROM MeetingServer meetingServer WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "meetingServer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MeetingServer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MeetingServer exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(MeetingServerPersistenceImpl.class);
	private static MeetingServer _nullMeetingServer = new MeetingServerImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<MeetingServer> toCacheModel() {
				return _nullMeetingServerCacheModel;
			}
		};

	private static CacheModel<MeetingServer> _nullMeetingServerCacheModel = new CacheModel<MeetingServer>() {
			@Override
			public MeetingServer toEntityModel() {
				return _nullMeetingServer;
			}
		};
}