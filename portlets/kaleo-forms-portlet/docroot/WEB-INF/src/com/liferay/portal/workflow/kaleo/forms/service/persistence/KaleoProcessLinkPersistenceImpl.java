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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;
import com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkImpl;
import com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo process link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLinkPersistence
 * @see KaleoProcessLinkUtil
 * @generated
 */
public class KaleoProcessLinkPersistenceImpl extends BasePersistenceImpl<KaleoProcessLink>
	implements KaleoProcessLinkPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoProcessLinkUtil} to access the kaleo process link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoProcessLinkImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkModelImpl.FINDER_CACHE_ENABLED,
			KaleoProcessLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkModelImpl.FINDER_CACHE_ENABLED,
			KaleoProcessLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOPROCESSID =
		new FinderPath(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkModelImpl.FINDER_CACHE_ENABLED,
			KaleoProcessLinkImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByKaleoProcessId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOPROCESSID =
		new FinderPath(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkModelImpl.FINDER_CACHE_ENABLED,
			KaleoProcessLinkImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByKaleoProcessId",
			new String[] { Long.class.getName() },
			KaleoProcessLinkModelImpl.KALEOPROCESSID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KALEOPROCESSID = new FinderPath(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKaleoProcessId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo process links where kaleoProcessId = &#63;.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @return the matching kaleo process links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcessLink> findByKaleoProcessId(long kaleoProcessId)
		throws SystemException {
		return findByKaleoProcessId(kaleoProcessId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo process links where kaleoProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param start the lower bound of the range of kaleo process links
	 * @param end the upper bound of the range of kaleo process links (not inclusive)
	 * @return the range of matching kaleo process links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcessLink> findByKaleoProcessId(long kaleoProcessId,
		int start, int end) throws SystemException {
		return findByKaleoProcessId(kaleoProcessId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo process links where kaleoProcessId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param start the lower bound of the range of kaleo process links
	 * @param end the upper bound of the range of kaleo process links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo process links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcessLink> findByKaleoProcessId(long kaleoProcessId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOPROCESSID;
			finderArgs = new Object[] { kaleoProcessId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_KALEOPROCESSID;
			finderArgs = new Object[] {
					kaleoProcessId,
					
					start, end, orderByComparator
				};
		}

		List<KaleoProcessLink> list = (List<KaleoProcessLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoProcessLink kaleoProcessLink : list) {
				if ((kaleoProcessId != kaleoProcessLink.getKaleoProcessId())) {
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

			query.append(_SQL_SELECT_KALEOPROCESSLINK_WHERE);

			query.append(_FINDER_COLUMN_KALEOPROCESSID_KALEOPROCESSID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoProcessLinkModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoProcessId);

				if (!pagination) {
					list = (List<KaleoProcessLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoProcessLink>(list);
				}
				else {
					list = (List<KaleoProcessLink>)QueryUtil.list(q,
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
	 * Returns the first kaleo process link in the ordered set where kaleoProcessId = &#63;.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo process link
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a matching kaleo process link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink findByKaleoProcessId_First(long kaleoProcessId,
		OrderByComparator orderByComparator)
		throws NoSuchKaleoProcessLinkException, SystemException {
		KaleoProcessLink kaleoProcessLink = fetchByKaleoProcessId_First(kaleoProcessId,
				orderByComparator);

		if (kaleoProcessLink != null) {
			return kaleoProcessLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoProcessId=");
		msg.append(kaleoProcessId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchKaleoProcessLinkException(msg.toString());
	}

	/**
	 * Returns the first kaleo process link in the ordered set where kaleoProcessId = &#63;.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo process link, or <code>null</code> if a matching kaleo process link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink fetchByKaleoProcessId_First(long kaleoProcessId,
		OrderByComparator orderByComparator) throws SystemException {
		List<KaleoProcessLink> list = findByKaleoProcessId(kaleoProcessId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo process link in the ordered set where kaleoProcessId = &#63;.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo process link
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a matching kaleo process link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink findByKaleoProcessId_Last(long kaleoProcessId,
		OrderByComparator orderByComparator)
		throws NoSuchKaleoProcessLinkException, SystemException {
		KaleoProcessLink kaleoProcessLink = fetchByKaleoProcessId_Last(kaleoProcessId,
				orderByComparator);

		if (kaleoProcessLink != null) {
			return kaleoProcessLink;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("kaleoProcessId=");
		msg.append(kaleoProcessId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchKaleoProcessLinkException(msg.toString());
	}

	/**
	 * Returns the last kaleo process link in the ordered set where kaleoProcessId = &#63;.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo process link, or <code>null</code> if a matching kaleo process link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink fetchByKaleoProcessId_Last(long kaleoProcessId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByKaleoProcessId(kaleoProcessId);

		if (count == 0) {
			return null;
		}

		List<KaleoProcessLink> list = findByKaleoProcessId(kaleoProcessId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo process links before and after the current kaleo process link in the ordered set where kaleoProcessId = &#63;.
	 *
	 * @param kaleoProcessLinkId the primary key of the current kaleo process link
	 * @param kaleoProcessId the kaleo process ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo process link
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a kaleo process link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink[] findByKaleoProcessId_PrevAndNext(
		long kaleoProcessLinkId, long kaleoProcessId,
		OrderByComparator orderByComparator)
		throws NoSuchKaleoProcessLinkException, SystemException {
		KaleoProcessLink kaleoProcessLink = findByPrimaryKey(kaleoProcessLinkId);

		Session session = null;

		try {
			session = openSession();

			KaleoProcessLink[] array = new KaleoProcessLinkImpl[3];

			array[0] = getByKaleoProcessId_PrevAndNext(session,
					kaleoProcessLink, kaleoProcessId, orderByComparator, true);

			array[1] = kaleoProcessLink;

			array[2] = getByKaleoProcessId_PrevAndNext(session,
					kaleoProcessLink, kaleoProcessId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoProcessLink getByKaleoProcessId_PrevAndNext(
		Session session, KaleoProcessLink kaleoProcessLink,
		long kaleoProcessId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEOPROCESSLINK_WHERE);

		query.append(_FINDER_COLUMN_KALEOPROCESSID_KALEOPROCESSID_2);

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
			query.append(KaleoProcessLinkModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(kaleoProcessId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoProcessLink);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoProcessLink> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo process links where kaleoProcessId = &#63; from the database.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByKaleoProcessId(long kaleoProcessId)
		throws SystemException {
		for (KaleoProcessLink kaleoProcessLink : findByKaleoProcessId(
				kaleoProcessId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoProcessLink);
		}
	}

	/**
	 * Returns the number of kaleo process links where kaleoProcessId = &#63;.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @return the number of matching kaleo process links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByKaleoProcessId(long kaleoProcessId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KALEOPROCESSID;

		Object[] finderArgs = new Object[] { kaleoProcessId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEOPROCESSLINK_WHERE);

			query.append(_FINDER_COLUMN_KALEOPROCESSID_KALEOPROCESSID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoProcessId);

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

	private static final String _FINDER_COLUMN_KALEOPROCESSID_KALEOPROCESSID_2 = "kaleoProcessLink.kaleoProcessId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_KPI_WTN = new FinderPath(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkModelImpl.FINDER_CACHE_ENABLED,
			KaleoProcessLinkImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByKPI_WTN",
			new String[] { Long.class.getName(), String.class.getName() },
			KaleoProcessLinkModelImpl.KALEOPROCESSID_COLUMN_BITMASK |
			KaleoProcessLinkModelImpl.WORKFLOWTASKNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_KPI_WTN = new FinderPath(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByKPI_WTN",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns the kaleo process link where kaleoProcessId = &#63; and workflowTaskName = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException} if it could not be found.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param workflowTaskName the workflow task name
	 * @return the matching kaleo process link
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a matching kaleo process link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink findByKPI_WTN(long kaleoProcessId,
		String workflowTaskName)
		throws NoSuchKaleoProcessLinkException, SystemException {
		KaleoProcessLink kaleoProcessLink = fetchByKPI_WTN(kaleoProcessId,
				workflowTaskName);

		if (kaleoProcessLink == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("kaleoProcessId=");
			msg.append(kaleoProcessId);

			msg.append(", workflowTaskName=");
			msg.append(workflowTaskName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchKaleoProcessLinkException(msg.toString());
		}

		return kaleoProcessLink;
	}

	/**
	 * Returns the kaleo process link where kaleoProcessId = &#63; and workflowTaskName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param workflowTaskName the workflow task name
	 * @return the matching kaleo process link, or <code>null</code> if a matching kaleo process link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink fetchByKPI_WTN(long kaleoProcessId,
		String workflowTaskName) throws SystemException {
		return fetchByKPI_WTN(kaleoProcessId, workflowTaskName, true);
	}

	/**
	 * Returns the kaleo process link where kaleoProcessId = &#63; and workflowTaskName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param workflowTaskName the workflow task name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo process link, or <code>null</code> if a matching kaleo process link could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink fetchByKPI_WTN(long kaleoProcessId,
		String workflowTaskName, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { kaleoProcessId, workflowTaskName };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_KPI_WTN,
					finderArgs, this);
		}

		if (result instanceof KaleoProcessLink) {
			KaleoProcessLink kaleoProcessLink = (KaleoProcessLink)result;

			if ((kaleoProcessId != kaleoProcessLink.getKaleoProcessId()) ||
					!Validator.equals(workflowTaskName,
						kaleoProcessLink.getWorkflowTaskName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KALEOPROCESSLINK_WHERE);

			query.append(_FINDER_COLUMN_KPI_WTN_KALEOPROCESSID_2);

			boolean bindWorkflowTaskName = false;

			if (workflowTaskName == null) {
				query.append(_FINDER_COLUMN_KPI_WTN_WORKFLOWTASKNAME_1);
			}
			else if (workflowTaskName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KPI_WTN_WORKFLOWTASKNAME_3);
			}
			else {
				bindWorkflowTaskName = true;

				query.append(_FINDER_COLUMN_KPI_WTN_WORKFLOWTASKNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoProcessId);

				if (bindWorkflowTaskName) {
					qPos.add(workflowTaskName);
				}

				List<KaleoProcessLink> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KPI_WTN,
						finderArgs, list);
				}
				else {
					KaleoProcessLink kaleoProcessLink = list.get(0);

					result = kaleoProcessLink;

					cacheResult(kaleoProcessLink);

					if ((kaleoProcessLink.getKaleoProcessId() != kaleoProcessId) ||
							(kaleoProcessLink.getWorkflowTaskName() == null) ||
							!kaleoProcessLink.getWorkflowTaskName()
												 .equals(workflowTaskName)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KPI_WTN,
							finderArgs, kaleoProcessLink);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KPI_WTN,
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
			return (KaleoProcessLink)result;
		}
	}

	/**
	 * Removes the kaleo process link where kaleoProcessId = &#63; and workflowTaskName = &#63; from the database.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param workflowTaskName the workflow task name
	 * @return the kaleo process link that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink removeByKPI_WTN(long kaleoProcessId,
		String workflowTaskName)
		throws NoSuchKaleoProcessLinkException, SystemException {
		KaleoProcessLink kaleoProcessLink = findByKPI_WTN(kaleoProcessId,
				workflowTaskName);

		return remove(kaleoProcessLink);
	}

	/**
	 * Returns the number of kaleo process links where kaleoProcessId = &#63; and workflowTaskName = &#63;.
	 *
	 * @param kaleoProcessId the kaleo process ID
	 * @param workflowTaskName the workflow task name
	 * @return the number of matching kaleo process links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByKPI_WTN(long kaleoProcessId, String workflowTaskName)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_KPI_WTN;

		Object[] finderArgs = new Object[] { kaleoProcessId, workflowTaskName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KALEOPROCESSLINK_WHERE);

			query.append(_FINDER_COLUMN_KPI_WTN_KALEOPROCESSID_2);

			boolean bindWorkflowTaskName = false;

			if (workflowTaskName == null) {
				query.append(_FINDER_COLUMN_KPI_WTN_WORKFLOWTASKNAME_1);
			}
			else if (workflowTaskName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_KPI_WTN_WORKFLOWTASKNAME_3);
			}
			else {
				bindWorkflowTaskName = true;

				query.append(_FINDER_COLUMN_KPI_WTN_WORKFLOWTASKNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(kaleoProcessId);

				if (bindWorkflowTaskName) {
					qPos.add(workflowTaskName);
				}

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

	private static final String _FINDER_COLUMN_KPI_WTN_KALEOPROCESSID_2 = "kaleoProcessLink.kaleoProcessId = ? AND ";
	private static final String _FINDER_COLUMN_KPI_WTN_WORKFLOWTASKNAME_1 = "kaleoProcessLink.workflowTaskName IS NULL";
	private static final String _FINDER_COLUMN_KPI_WTN_WORKFLOWTASKNAME_2 = "kaleoProcessLink.workflowTaskName = ?";
	private static final String _FINDER_COLUMN_KPI_WTN_WORKFLOWTASKNAME_3 = "(kaleoProcessLink.workflowTaskName IS NULL OR kaleoProcessLink.workflowTaskName = '')";

	public KaleoProcessLinkPersistenceImpl() {
		setModelClass(KaleoProcessLink.class);
	}

	/**
	 * Caches the kaleo process link in the entity cache if it is enabled.
	 *
	 * @param kaleoProcessLink the kaleo process link
	 */
	@Override
	public void cacheResult(KaleoProcessLink kaleoProcessLink) {
		EntityCacheUtil.putResult(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkImpl.class, kaleoProcessLink.getPrimaryKey(),
			kaleoProcessLink);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KPI_WTN,
			new Object[] {
				kaleoProcessLink.getKaleoProcessId(),
				kaleoProcessLink.getWorkflowTaskName()
			}, kaleoProcessLink);

		kaleoProcessLink.resetOriginalValues();
	}

	/**
	 * Caches the kaleo process links in the entity cache if it is enabled.
	 *
	 * @param kaleoProcessLinks the kaleo process links
	 */
	@Override
	public void cacheResult(List<KaleoProcessLink> kaleoProcessLinks) {
		for (KaleoProcessLink kaleoProcessLink : kaleoProcessLinks) {
			if (EntityCacheUtil.getResult(
						KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
						KaleoProcessLinkImpl.class,
						kaleoProcessLink.getPrimaryKey()) == null) {
				cacheResult(kaleoProcessLink);
			}
			else {
				kaleoProcessLink.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo process links.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoProcessLinkImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoProcessLinkImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo process link.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoProcessLink kaleoProcessLink) {
		EntityCacheUtil.removeResult(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkImpl.class, kaleoProcessLink.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(kaleoProcessLink);
	}

	@Override
	public void clearCache(List<KaleoProcessLink> kaleoProcessLinks) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoProcessLink kaleoProcessLink : kaleoProcessLinks) {
			EntityCacheUtil.removeResult(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
				KaleoProcessLinkImpl.class, kaleoProcessLink.getPrimaryKey());

			clearUniqueFindersCache(kaleoProcessLink);
		}
	}

	protected void cacheUniqueFindersCache(KaleoProcessLink kaleoProcessLink) {
		if (kaleoProcessLink.isNew()) {
			Object[] args = new Object[] {
					kaleoProcessLink.getKaleoProcessId(),
					kaleoProcessLink.getWorkflowTaskName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KPI_WTN, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KPI_WTN, args,
				kaleoProcessLink);
		}
		else {
			KaleoProcessLinkModelImpl kaleoProcessLinkModelImpl = (KaleoProcessLinkModelImpl)kaleoProcessLink;

			if ((kaleoProcessLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_KPI_WTN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoProcessLink.getKaleoProcessId(),
						kaleoProcessLink.getWorkflowTaskName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_KPI_WTN, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_KPI_WTN, args,
					kaleoProcessLink);
			}
		}
	}

	protected void clearUniqueFindersCache(KaleoProcessLink kaleoProcessLink) {
		KaleoProcessLinkModelImpl kaleoProcessLinkModelImpl = (KaleoProcessLinkModelImpl)kaleoProcessLink;

		Object[] args = new Object[] {
				kaleoProcessLink.getKaleoProcessId(),
				kaleoProcessLink.getWorkflowTaskName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KPI_WTN, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KPI_WTN, args);

		if ((kaleoProcessLinkModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_KPI_WTN.getColumnBitmask()) != 0) {
			args = new Object[] {
					kaleoProcessLinkModelImpl.getOriginalKaleoProcessId(),
					kaleoProcessLinkModelImpl.getOriginalWorkflowTaskName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KPI_WTN, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_KPI_WTN, args);
		}
	}

	/**
	 * Creates a new kaleo process link with the primary key. Does not add the kaleo process link to the database.
	 *
	 * @param kaleoProcessLinkId the primary key for the new kaleo process link
	 * @return the new kaleo process link
	 */
	@Override
	public KaleoProcessLink create(long kaleoProcessLinkId) {
		KaleoProcessLink kaleoProcessLink = new KaleoProcessLinkImpl();

		kaleoProcessLink.setNew(true);
		kaleoProcessLink.setPrimaryKey(kaleoProcessLinkId);

		return kaleoProcessLink;
	}

	/**
	 * Removes the kaleo process link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcessLinkId the primary key of the kaleo process link
	 * @return the kaleo process link that was removed
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a kaleo process link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink remove(long kaleoProcessLinkId)
		throws NoSuchKaleoProcessLinkException, SystemException {
		return remove((Serializable)kaleoProcessLinkId);
	}

	/**
	 * Removes the kaleo process link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo process link
	 * @return the kaleo process link that was removed
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a kaleo process link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink remove(Serializable primaryKey)
		throws NoSuchKaleoProcessLinkException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoProcessLink kaleoProcessLink = (KaleoProcessLink)session.get(KaleoProcessLinkImpl.class,
					primaryKey);

			if (kaleoProcessLink == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchKaleoProcessLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoProcessLink);
		}
		catch (NoSuchKaleoProcessLinkException nsee) {
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
	protected KaleoProcessLink removeImpl(KaleoProcessLink kaleoProcessLink)
		throws SystemException {
		kaleoProcessLink = toUnwrappedModel(kaleoProcessLink);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoProcessLink)) {
				kaleoProcessLink = (KaleoProcessLink)session.get(KaleoProcessLinkImpl.class,
						kaleoProcessLink.getPrimaryKeyObj());
			}

			if (kaleoProcessLink != null) {
				session.delete(kaleoProcessLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoProcessLink != null) {
			clearCache(kaleoProcessLink);
		}

		return kaleoProcessLink;
	}

	@Override
	public KaleoProcessLink updateImpl(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink)
		throws SystemException {
		kaleoProcessLink = toUnwrappedModel(kaleoProcessLink);

		boolean isNew = kaleoProcessLink.isNew();

		KaleoProcessLinkModelImpl kaleoProcessLinkModelImpl = (KaleoProcessLinkModelImpl)kaleoProcessLink;

		Session session = null;

		try {
			session = openSession();

			if (kaleoProcessLink.isNew()) {
				session.save(kaleoProcessLink);

				kaleoProcessLink.setNew(false);
			}
			else {
				session.merge(kaleoProcessLink);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoProcessLinkModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoProcessLinkModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOPROCESSID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoProcessLinkModelImpl.getOriginalKaleoProcessId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOPROCESSID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOPROCESSID,
					args);

				args = new Object[] {
						kaleoProcessLinkModelImpl.getKaleoProcessId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_KALEOPROCESSID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_KALEOPROCESSID,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
			KaleoProcessLinkImpl.class, kaleoProcessLink.getPrimaryKey(),
			kaleoProcessLink);

		clearUniqueFindersCache(kaleoProcessLink);
		cacheUniqueFindersCache(kaleoProcessLink);

		return kaleoProcessLink;
	}

	protected KaleoProcessLink toUnwrappedModel(
		KaleoProcessLink kaleoProcessLink) {
		if (kaleoProcessLink instanceof KaleoProcessLinkImpl) {
			return kaleoProcessLink;
		}

		KaleoProcessLinkImpl kaleoProcessLinkImpl = new KaleoProcessLinkImpl();

		kaleoProcessLinkImpl.setNew(kaleoProcessLink.isNew());
		kaleoProcessLinkImpl.setPrimaryKey(kaleoProcessLink.getPrimaryKey());

		kaleoProcessLinkImpl.setKaleoProcessLinkId(kaleoProcessLink.getKaleoProcessLinkId());
		kaleoProcessLinkImpl.setKaleoProcessId(kaleoProcessLink.getKaleoProcessId());
		kaleoProcessLinkImpl.setWorkflowTaskName(kaleoProcessLink.getWorkflowTaskName());
		kaleoProcessLinkImpl.setDDMTemplateId(kaleoProcessLink.getDDMTemplateId());

		return kaleoProcessLinkImpl;
	}

	/**
	 * Returns the kaleo process link with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo process link
	 * @return the kaleo process link
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a kaleo process link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink findByPrimaryKey(Serializable primaryKey)
		throws NoSuchKaleoProcessLinkException, SystemException {
		KaleoProcessLink kaleoProcessLink = fetchByPrimaryKey(primaryKey);

		if (kaleoProcessLink == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchKaleoProcessLinkException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoProcessLink;
	}

	/**
	 * Returns the kaleo process link with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException} if it could not be found.
	 *
	 * @param kaleoProcessLinkId the primary key of the kaleo process link
	 * @return the kaleo process link
	 * @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a kaleo process link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink findByPrimaryKey(long kaleoProcessLinkId)
		throws NoSuchKaleoProcessLinkException, SystemException {
		return findByPrimaryKey((Serializable)kaleoProcessLinkId);
	}

	/**
	 * Returns the kaleo process link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo process link
	 * @return the kaleo process link, or <code>null</code> if a kaleo process link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		KaleoProcessLink kaleoProcessLink = (KaleoProcessLink)EntityCacheUtil.getResult(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
				KaleoProcessLinkImpl.class, primaryKey);

		if (kaleoProcessLink == _nullKaleoProcessLink) {
			return null;
		}

		if (kaleoProcessLink == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoProcessLink = (KaleoProcessLink)session.get(KaleoProcessLinkImpl.class,
						primaryKey);

				if (kaleoProcessLink != null) {
					cacheResult(kaleoProcessLink);
				}
				else {
					EntityCacheUtil.putResult(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
						KaleoProcessLinkImpl.class, primaryKey,
						_nullKaleoProcessLink);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoProcessLinkModelImpl.ENTITY_CACHE_ENABLED,
					KaleoProcessLinkImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoProcessLink;
	}

	/**
	 * Returns the kaleo process link with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoProcessLinkId the primary key of the kaleo process link
	 * @return the kaleo process link, or <code>null</code> if a kaleo process link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink fetchByPrimaryKey(long kaleoProcessLinkId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)kaleoProcessLinkId);
	}

	/**
	 * Returns all the kaleo process links.
	 *
	 * @return the kaleo process links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcessLink> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo process links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo process links
	 * @param end the upper bound of the range of kaleo process links (not inclusive)
	 * @return the range of kaleo process links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcessLink> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo process links.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo process links
	 * @param end the upper bound of the range of kaleo process links (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo process links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoProcessLink> findAll(int start, int end,
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

		List<KaleoProcessLink> list = (List<KaleoProcessLink>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEOPROCESSLINK);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEOPROCESSLINK;

				if (pagination) {
					sql = sql.concat(KaleoProcessLinkModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoProcessLink>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoProcessLink>(list);
				}
				else {
					list = (List<KaleoProcessLink>)QueryUtil.list(q,
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
	 * Removes all the kaleo process links from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (KaleoProcessLink kaleoProcessLink : findAll()) {
			remove(kaleoProcessLink);
		}
	}

	/**
	 * Returns the number of kaleo process links.
	 *
	 * @return the number of kaleo process links
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

				Query q = session.createQuery(_SQL_COUNT_KALEOPROCESSLINK);

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
	 * Initializes the kaleo process link persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoProcessLink>> listenersList = new ArrayList<ModelListener<KaleoProcessLink>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoProcessLink>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoProcessLinkImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEOPROCESSLINK = "SELECT kaleoProcessLink FROM KaleoProcessLink kaleoProcessLink";
	private static final String _SQL_SELECT_KALEOPROCESSLINK_WHERE = "SELECT kaleoProcessLink FROM KaleoProcessLink kaleoProcessLink WHERE ";
	private static final String _SQL_COUNT_KALEOPROCESSLINK = "SELECT COUNT(kaleoProcessLink) FROM KaleoProcessLink kaleoProcessLink";
	private static final String _SQL_COUNT_KALEOPROCESSLINK_WHERE = "SELECT COUNT(kaleoProcessLink) FROM KaleoProcessLink kaleoProcessLink WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoProcessLink.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoProcessLink exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoProcessLink exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoProcessLinkPersistenceImpl.class);
	private static KaleoProcessLink _nullKaleoProcessLink = new KaleoProcessLinkImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoProcessLink> toCacheModel() {
				return _nullKaleoProcessLinkCacheModel;
			}
		};

	private static CacheModel<KaleoProcessLink> _nullKaleoProcessLinkCacheModel = new CacheModel<KaleoProcessLink>() {
			@Override
			public KaleoProcessLink toEntityModel() {
				return _nullKaleoProcessLink;
			}
		};
}