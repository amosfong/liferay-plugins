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

package com.liferay.portal.workflow.kaleo.forms.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessImpl;
import com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo process service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessPersistence
 * @see KaleoProcessUtil
 * @generated
 */
public class KaleoProcessPersistenceImpl extends BasePersistenceImpl<KaleoProcess>
	implements KaleoProcessPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoProcessUtil} to access the kaleo process persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoProcessImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessModelImpl.FINDER_CACHE_ENABLED, KaleoProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessModelImpl.FINDER_CACHE_ENABLED, KaleoProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessModelImpl.FINDER_CACHE_ENABLED, KaleoProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessModelImpl.FINDER_CACHE_ENABLED, KaleoProcessImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			KaleoProcessModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo processes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching kaleo processes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcess> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo processes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of kaleo processes
	 * @param end the upper bound of the range of kaleo processes (not inclusive)
	 * @return the range of matching kaleo processes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcess> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo processes where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of kaleo processes
	 * @param end the upper bound of the range of kaleo processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo processes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcess> findByGroupId(long groupId, int start, int end,
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

		List<KaleoProcess> list = (List<KaleoProcess>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoProcess kaleoProcess : list) {
				if ((groupId != kaleoProcess.getGroupId())) {
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

			query.append(_SQL_SELECT_KALEOPROCESS_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoProcessModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<KaleoProcess>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoProcess>(list);
				}
				else {
					list = (List<KaleoProcess>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first kaleo process in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo process
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException if a matching kaleo process could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchKaleoProcessException, SystemException {
		KaleoProcess kaleoProcess = fetchByGroupId_First(groupId,
				orderByComparator);

		if (kaleoProcess != null) {
			return kaleoProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchKaleoProcessException(msg.toString());
	}

	/**
	 * Returns the first kaleo process in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo process, or <code>null</code> if a matching kaleo process could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<KaleoProcess> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo process in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo process
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException if a matching kaleo process could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchKaleoProcessException, SystemException {
		KaleoProcess kaleoProcess = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (kaleoProcess != null) {
			return kaleoProcess;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchKaleoProcessException(msg.toString());
	}

	/**
	 * Returns the last kaleo process in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo process, or <code>null</code> if a matching kaleo process could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<KaleoProcess> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo processes before and after the current kaleo process in the ordered set where groupId = &#63;.
	 *
	 * @param kaleoProcessId the primary key of the current kaleo process
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo process
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException if a kaleo process with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess[] findByGroupId_PrevAndNext(long kaleoProcessId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchKaleoProcessException, SystemException {
		KaleoProcess kaleoProcess = findByPrimaryKey(kaleoProcessId);

		Session session = null;

		try {
			session = openSession();

			KaleoProcess[] array = new KaleoProcessImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, kaleoProcess, groupId,
					orderByComparator, true);

			array[1] = kaleoProcess;

			array[2] = getByGroupId_PrevAndNext(session, kaleoProcess, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoProcess getByGroupId_PrevAndNext(Session session,
		KaleoProcess kaleoProcess, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOPROCESS_WHERE);

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
			query.append(KaleoProcessModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoProcess);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the kaleo processes that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching kaleo processes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcess> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo processes that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of kaleo processes
	 * @param end the upper bound of the range of kaleo processes (not inclusive)
	 * @return the range of matching kaleo processes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcess> filterFindByGroupId(long groupId, int start,
		int end) throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo processes that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of kaleo processes
	 * @param end the upper bound of the range of kaleo processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo processes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcess> filterFindByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(3 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KALEOPROCESS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KALEOPROCESS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KALEOPROCESS_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator, true);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				query.append(KaleoProcessModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KaleoProcessModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KaleoProcess.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KaleoProcessImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KaleoProcessImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<KaleoProcess>)QueryUtil.list(q, getDialect(), start,
				end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the kaleo processes before and after the current kaleo process in the ordered set of kaleo processes that the user has permission to view where groupId = &#63;.
	 *
	 * @param kaleoProcessId the primary key of the current kaleo process
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo process
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException if a kaleo process with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess[] filterFindByGroupId_PrevAndNext(long kaleoProcessId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchKaleoProcessException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(kaleoProcessId, groupId,
				orderByComparator);
		}

		KaleoProcess kaleoProcess = findByPrimaryKey(kaleoProcessId);

		Session session = null;

		try {
			session = openSession();

			KaleoProcess[] array = new KaleoProcessImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, kaleoProcess,
					groupId, orderByComparator, true);

			array[1] = kaleoProcess;

			array[2] = filterGetByGroupId_PrevAndNext(session, kaleoProcess,
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

	protected KaleoProcess filterGetByGroupId_PrevAndNext(Session session,
		KaleoProcess kaleoProcess, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KALEOPROCESS_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KALEOPROCESS_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KALEOPROCESS_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					query.append(_ORDER_BY_ENTITY_ALIAS);
				}
				else {
					query.append(_ORDER_BY_ENTITY_TABLE);
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				query.append(KaleoProcessModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KaleoProcessModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KaleoProcess.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KaleoProcessImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KaleoProcessImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoProcess);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoProcess> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo processes where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (KaleoProcess kaleoProcess : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoProcess);
		}
	}

	/**
	 * Returns the number of kaleo processes where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching kaleo processes
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

			query.append(_SQL_COUNT_KALEOPROCESS_WHERE);

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

	/**
	 * Returns the number of kaleo processes that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching kaleo processes that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_KALEOPROCESS_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KaleoProcess.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			Long count = (Long)q.uniqueResult();

			return count.intValue();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "kaleoProcess.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_DDLRECORDSETID = new FinderPath(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessModelImpl.FINDER_CACHE_ENABLED, KaleoProcessImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByDDLRecordSetId",
			new String[] { Long.class.getName() },
			KaleoProcessModelImpl.DDLRECORDSETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_DDLRECORDSETID = new FinderPath(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDDLRecordSetId",
			new String[] { Long.class.getName() });

	/**
	 * Returns the kaleo process where DDLRecordSetId = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException} if it could not be found.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @return the matching kaleo process
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException if a matching kaleo process could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess findByDDLRecordSetId(long DDLRecordSetId)
		throws NoSuchKaleoProcessException, SystemException {
		KaleoProcess kaleoProcess = fetchByDDLRecordSetId(DDLRecordSetId);

		if (kaleoProcess == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("DDLRecordSetId=");
			msg.append(DDLRecordSetId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchKaleoProcessException(msg.toString());
		}

		return kaleoProcess;
	}

	/**
	 * Returns the kaleo process where DDLRecordSetId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @return the matching kaleo process, or <code>null</code> if a matching kaleo process could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess fetchByDDLRecordSetId(long DDLRecordSetId)
		throws SystemException {
		return fetchByDDLRecordSetId(DDLRecordSetId, true);
	}

	/**
	 * Returns the kaleo process where DDLRecordSetId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo process, or <code>null</code> if a matching kaleo process could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess fetchByDDLRecordSetId(long DDLRecordSetId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { DDLRecordSetId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
					finderArgs, this);
		}

		if (result instanceof KaleoProcess) {
			KaleoProcess kaleoProcess = (KaleoProcess)result;

			if ((DDLRecordSetId != kaleoProcess.getDDLRecordSetId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_KALEOPROCESS_WHERE);

			query.append(_FINDER_COLUMN_DDLRECORDSETID_DDLRECORDSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(DDLRecordSetId);

				List<KaleoProcess> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"KaleoProcessPersistenceImpl.fetchByDDLRecordSetId(long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					KaleoProcess kaleoProcess = list.get(0);

					result = kaleoProcess;

					cacheResult(kaleoProcess);

					if ((kaleoProcess.getDDLRecordSetId() != DDLRecordSetId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
							finderArgs, kaleoProcess);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (KaleoProcess)result;
		}
	}

	/**
	 * Removes the kaleo process where DDLRecordSetId = &#63; from the database.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @return the kaleo process that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess removeByDDLRecordSetId(long DDLRecordSetId)
		throws NoSuchKaleoProcessException, SystemException {
		KaleoProcess kaleoProcess = findByDDLRecordSetId(DDLRecordSetId);

		return remove(kaleoProcess);
	}

	/**
	 * Returns the number of kaleo processes where DDLRecordSetId = &#63;.
	 *
	 * @param DDLRecordSetId the d d l record set ID
	 * @return the number of matching kaleo processes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByDDLRecordSetId(long DDLRecordSetId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_DDLRECORDSETID;

		Object[] finderArgs = new Object[] { DDLRecordSetId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOPROCESS_WHERE);

			query.append(_FINDER_COLUMN_DDLRECORDSETID_DDLRECORDSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(DDLRecordSetId);

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

	private static final String _FINDER_COLUMN_DDLRECORDSETID_DDLRECORDSETID_2 = "kaleoProcess.DDLRecordSetId = ?";

	public KaleoProcessPersistenceImpl() {
		setModelClass(KaleoProcess.class);
	}

	/**
	 * Caches the kaleo process in the entity cache if it is enabled.
	 *
	 * @param kaleoProcess the kaleo process
	 */
	@Override
	public void cacheResult(KaleoProcess kaleoProcess) {
		EntityCacheUtil.putResult(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessImpl.class, kaleoProcess.getPrimaryKey(), kaleoProcess);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
			new Object[] { kaleoProcess.getDDLRecordSetId() }, kaleoProcess);

		kaleoProcess.resetOriginalValues();
	}

	/**
	 * Caches the kaleo processes in the entity cache if it is enabled.
	 *
	 * @param kaleoProcesses the kaleo processes
	 */
	@Override
	public void cacheResult(List<KaleoProcess> kaleoProcesses) {
		for (KaleoProcess kaleoProcess : kaleoProcesses) {
			if (EntityCacheUtil.getResult(
						KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
						KaleoProcessImpl.class, kaleoProcess.getPrimaryKey()) == null) {
				cacheResult(kaleoProcess);
			}
			else {
				kaleoProcess.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo processes.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoProcessImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoProcessImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo process.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoProcess kaleoProcess) {
		EntityCacheUtil.removeResult(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessImpl.class, kaleoProcess.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(kaleoProcess);
	}

	@Override
	public void clearCache(List<KaleoProcess> kaleoProcesses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoProcess kaleoProcess : kaleoProcesses) {
			EntityCacheUtil.removeResult(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
				KaleoProcessImpl.class, kaleoProcess.getPrimaryKey());

			clearUniqueFindersCache(kaleoProcess);
		}
	}

	protected void cacheUniqueFindersCache(KaleoProcess kaleoProcess) {
		if (kaleoProcess.isNew()) {
			Object[] args = new Object[] { kaleoProcess.getDDLRecordSetId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
				args, kaleoProcess);
		}
		else {
			KaleoProcessModelImpl kaleoProcessModelImpl = (KaleoProcessModelImpl)kaleoProcess;

			if ((kaleoProcessModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_DDLRECORDSETID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { kaleoProcess.getDDLRecordSetId() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
					args, kaleoProcess);
			}
		}
	}

	protected void clearUniqueFindersCache(KaleoProcess kaleoProcess) {
		KaleoProcessModelImpl kaleoProcessModelImpl = (KaleoProcessModelImpl)kaleoProcess;

		Object[] args = new Object[] { kaleoProcess.getDDLRecordSetId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID, args);

		if ((kaleoProcessModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_DDLRECORDSETID.getColumnBitmask()) != 0) {
			args = new Object[] {
					kaleoProcessModelImpl.getOriginalDDLRecordSetId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DDLRECORDSETID,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DDLRECORDSETID,
				args);
		}
	}

	/**
	 * Creates a new kaleo process with the primary key. Does not add the kaleo process to the database.
	 *
	 * @param kaleoProcessId the primary key for the new kaleo process
	 * @return the new kaleo process
	 */
	@Override
	public KaleoProcess create(long kaleoProcessId) {
		KaleoProcess kaleoProcess = new KaleoProcessImpl();

		kaleoProcess.setNew(true);
		kaleoProcess.setPrimaryKey(kaleoProcessId);

		return kaleoProcess;
	}

	/**
	 * Removes the kaleo process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcessId the primary key of the kaleo process
	 * @return the kaleo process that was removed
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException if a kaleo process with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess remove(long kaleoProcessId)
		throws NoSuchKaleoProcessException, SystemException {
		return remove((Serializable)kaleoProcessId);
	}

	/**
	 * Removes the kaleo process with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo process
	 * @return the kaleo process that was removed
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException if a kaleo process with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess remove(Serializable primaryKey)
		throws NoSuchKaleoProcessException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoProcess kaleoProcess = (KaleoProcess)session.get(KaleoProcessImpl.class,
					primaryKey);

			if (kaleoProcess == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchKaleoProcessException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoProcess);
		}
		catch (NoSuchKaleoProcessException nsee) {
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
	protected KaleoProcess removeImpl(KaleoProcess kaleoProcess)
		throws SystemException {
		kaleoProcess = toUnwrappedModel(kaleoProcess);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoProcess)) {
				kaleoProcess = (KaleoProcess)session.get(KaleoProcessImpl.class,
						kaleoProcess.getPrimaryKeyObj());
			}

			if (kaleoProcess != null) {
				session.delete(kaleoProcess);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoProcess != null) {
			clearCache(kaleoProcess);
		}

		return kaleoProcess;
	}

	@Override
	public KaleoProcess updateImpl(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess kaleoProcess)
		throws SystemException {
		kaleoProcess = toUnwrappedModel(kaleoProcess);

		boolean isNew = kaleoProcess.isNew();

		KaleoProcessModelImpl kaleoProcessModelImpl = (KaleoProcessModelImpl)kaleoProcess;

		Session session = null;

		try {
			session = openSession();

			if (kaleoProcess.isNew()) {
				session.save(kaleoProcess);

				kaleoProcess.setNew(false);
			}
			else {
				session.merge(kaleoProcess);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoProcessModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoProcessModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoProcessModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { kaleoProcessModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessImpl.class, kaleoProcess.getPrimaryKey(), kaleoProcess);

		clearUniqueFindersCache(kaleoProcess);
		cacheUniqueFindersCache(kaleoProcess);

		return kaleoProcess;
	}

	protected KaleoProcess toUnwrappedModel(KaleoProcess kaleoProcess) {
		if (kaleoProcess instanceof KaleoProcessImpl) {
			return kaleoProcess;
		}

		KaleoProcessImpl kaleoProcessImpl = new KaleoProcessImpl();

		kaleoProcessImpl.setNew(kaleoProcess.isNew());
		kaleoProcessImpl.setPrimaryKey(kaleoProcess.getPrimaryKey());

		kaleoProcessImpl.setKaleoProcessId(kaleoProcess.getKaleoProcessId());
		kaleoProcessImpl.setGroupId(kaleoProcess.getGroupId());
		kaleoProcessImpl.setCompanyId(kaleoProcess.getCompanyId());
		kaleoProcessImpl.setUserId(kaleoProcess.getUserId());
		kaleoProcessImpl.setUserName(kaleoProcess.getUserName());
		kaleoProcessImpl.setCreateDate(kaleoProcess.getCreateDate());
		kaleoProcessImpl.setModifiedDate(kaleoProcess.getModifiedDate());
		kaleoProcessImpl.setDDLRecordSetId(kaleoProcess.getDDLRecordSetId());
		kaleoProcessImpl.setDDMTemplateId(kaleoProcess.getDDMTemplateId());

		return kaleoProcessImpl;
	}

	/**
	 * Returns the kaleo process with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo process
	 * @return the kaleo process
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException if a kaleo process with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess findByPrimaryKey(Serializable primaryKey)
		throws NoSuchKaleoProcessException, SystemException {
		KaleoProcess kaleoProcess = fetchByPrimaryKey(primaryKey);

		if (kaleoProcess == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchKaleoProcessException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoProcess;
	}

	/**
	 * Returns the kaleo process with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException} if it could not be found.
	 *
	 * @param kaleoProcessId the primary key of the kaleo process
	 * @return the kaleo process
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException if a kaleo process with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess findByPrimaryKey(long kaleoProcessId)
		throws NoSuchKaleoProcessException, SystemException {
		return findByPrimaryKey((Serializable)kaleoProcessId);
	}

	/**
	 * Returns the kaleo process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo process
	 * @return the kaleo process, or <code>null</code> if a kaleo process with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		KaleoProcess kaleoProcess = (KaleoProcess)EntityCacheUtil.getResult(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
				KaleoProcessImpl.class, primaryKey);

		if (kaleoProcess == _nullKaleoProcess) {
			return null;
		}

		if (kaleoProcess == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoProcess = (KaleoProcess)session.get(KaleoProcessImpl.class,
						primaryKey);

				if (kaleoProcess != null) {
					cacheResult(kaleoProcess);
				}
				else {
					EntityCacheUtil.putResult(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
						KaleoProcessImpl.class, primaryKey, _nullKaleoProcess);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoProcessModelImpl.ENTITY_CACHE_ENABLED,
					KaleoProcessImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoProcess;
	}

	/**
	 * Returns the kaleo process with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoProcessId the primary key of the kaleo process
	 * @return the kaleo process, or <code>null</code> if a kaleo process with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcess fetchByPrimaryKey(long kaleoProcessId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)kaleoProcessId);
	}

	/**
	 * Returns all the kaleo processes.
	 *
	 * @return the kaleo processes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcess> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo processes
	 * @param end the upper bound of the range of kaleo processes (not inclusive)
	 * @return the range of kaleo processes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcess> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo processes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo processes
	 * @param end the upper bound of the range of kaleo processes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo processes
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcess> findAll(int start, int end,
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

		List<KaleoProcess> list = (List<KaleoProcess>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOPROCESS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOPROCESS;

				if (pagination) {
					sql = sql.concat(KaleoProcessModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoProcess>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoProcess>(list);
				}
				else {
					list = (List<KaleoProcess>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the kaleo processes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (KaleoProcess kaleoProcess : findAll()) {
			remove(kaleoProcess);
		}
	}

	/**
	 * Returns the number of kaleo processes.
	 *
	 * @return the number of kaleo processes
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

				Query q = session.createQuery(_SQL_COUNT_KALEOPROCESS);

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
	 * Initializes the kaleo process persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoProcess>> listenersList = new ArrayList<ModelListener<KaleoProcess>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoProcess>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoProcessImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOPROCESS = "SELECT kaleoProcess FROM KaleoProcess kaleoProcess";
	private static final String _SQL_SELECT_KALEOPROCESS_WHERE = "SELECT kaleoProcess FROM KaleoProcess kaleoProcess WHERE ";
	private static final String _SQL_COUNT_KALEOPROCESS = "SELECT COUNT(kaleoProcess) FROM KaleoProcess kaleoProcess";
	private static final String _SQL_COUNT_KALEOPROCESS_WHERE = "SELECT COUNT(kaleoProcess) FROM KaleoProcess kaleoProcess WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "kaleoProcess.kaleoProcessId";
	private static final String _FILTER_SQL_SELECT_KALEOPROCESS_WHERE = "SELECT DISTINCT {kaleoProcess.*} FROM KaleoProcess kaleoProcess WHERE ";
	private static final String _FILTER_SQL_SELECT_KALEOPROCESS_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {KaleoProcess.*} FROM (SELECT DISTINCT kaleoProcess.kaleoProcessId FROM KaleoProcess kaleoProcess WHERE ";
	private static final String _FILTER_SQL_SELECT_KALEOPROCESS_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN KaleoProcess ON TEMP_TABLE.kaleoProcessId = KaleoProcess.kaleoProcessId";
	private static final String _FILTER_SQL_COUNT_KALEOPROCESS_WHERE = "SELECT COUNT(DISTINCT kaleoProcess.kaleoProcessId) AS COUNT_VALUE FROM KaleoProcess kaleoProcess WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "kaleoProcess";
	private static final String _FILTER_ENTITY_TABLE = "KaleoProcess";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoProcess.";
	private static final String _ORDER_BY_ENTITY_TABLE = "KaleoProcess.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoProcess exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoProcess exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoProcessPersistenceImpl.class);
	private static KaleoProcess _nullKaleoProcess = new KaleoProcessImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoProcess> toCacheModel() {
				return _nullKaleoProcessCacheModel;
			}
		};

	private static CacheModel<KaleoProcess> _nullKaleoProcessCacheModel = new CacheModel<KaleoProcess>() {
			@Override
			public KaleoProcess toEntityModel() {
				return _nullKaleoProcess;
			}
		};
}