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

package com.liferay.oauth.service.persistence;

import com.liferay.oauth.NoSuchUserException;
import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.model.impl.OAuthUserImpl;
import com.liferay.oauth.model.impl.OAuthUserModelImpl;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the o auth user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthUserPersistence
 * @see OAuthUserUtil
 * @generated
 */
public class OAuthUserPersistenceImpl extends BasePersistenceImpl<OAuthUser>
	implements OAuthUserPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OAuthUserUtil} to access the o auth user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OAuthUserImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, OAuthUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, OAuthUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, OAuthUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, OAuthUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			OAuthUserModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the o auth users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> findByUserId(long userId) throws SystemException {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @return the range of matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> findByUserId(long userId, int start, int end)
		throws SystemException {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth users where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> findByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<OAuthUser> list = (List<OAuthUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthUser oAuthUser : list) {
				if ((userId != oAuthUser.getUserId())) {
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

			query.append(_SQL_SELECT_OAUTHUSER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OAuthUserModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<OAuthUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<OAuthUser>(list);
				}
				else {
					list = (List<OAuthUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first o auth user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser findByUserId_First(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = fetchByUserId_First(userId, orderByComparator);

		if (oAuthUser != null) {
			return oAuthUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the first o auth user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth user, or <code>null</code> if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByUserId_First(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		List<OAuthUser> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last o auth user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser findByUserId_Last(long userId,
		OrderByComparator orderByComparator)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = fetchByUserId_Last(userId, orderByComparator);

		if (oAuthUser != null) {
			return oAuthUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the last o auth user in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth user, or <code>null</code> if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByUserId_Last(long userId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<OAuthUser> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the o auth users before and after the current o auth user in the ordered set where userId = &#63;.
	 *
	 * @param oAuthUserId the primary key of the current o auth user
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser[] findByUserId_PrevAndNext(long oAuthUserId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = findByPrimaryKey(oAuthUserId);

		Session session = null;

		try {
			session = openSession();

			OAuthUser[] array = new OAuthUserImpl[3];

			array[0] = getByUserId_PrevAndNext(session, oAuthUser, userId,
					orderByComparator, true);

			array[1] = oAuthUser;

			array[2] = getByUserId_PrevAndNext(session, oAuthUser, userId,
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

	protected OAuthUser getByUserId_PrevAndNext(Session session,
		OAuthUser oAuthUser, long userId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHUSER_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(OAuthUserModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth users that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching o auth users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> filterFindByUserId(long userId)
		throws SystemException {
		return filterFindByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the o auth users that the user has permission to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @return the range of matching o auth users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> filterFindByUserId(long userId, int start, int end)
		throws SystemException {
		return filterFindByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth users that the user has permissions to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> filterFindByUserId(long userId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId(userId, start, end, orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OAuthUserModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OAuthUserModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OAuthUser.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OAuthUserImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OAuthUserImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

			return (List<OAuthUser>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the o auth users before and after the current o auth user in the ordered set of o auth users that the user has permission to view where userId = &#63;.
	 *
	 * @param oAuthUserId the primary key of the current o auth user
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser[] filterFindByUserId_PrevAndNext(long oAuthUserId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchUserException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId_PrevAndNext(oAuthUserId, userId,
				orderByComparator);
		}

		OAuthUser oAuthUser = findByPrimaryKey(oAuthUserId);

		Session session = null;

		try {
			session = openSession();

			OAuthUser[] array = new OAuthUserImpl[3];

			array[0] = filterGetByUserId_PrevAndNext(session, oAuthUser,
					userId, orderByComparator, true);

			array[1] = oAuthUser;

			array[2] = filterGetByUserId_PrevAndNext(session, oAuthUser,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthUser filterGetByUserId_PrevAndNext(Session session,
		OAuthUser oAuthUser, long userId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OAuthUserModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OAuthUserModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OAuthUser.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OAuthUserImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OAuthUserImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the o auth users where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUserId(long userId) throws SystemException {
		for (OAuthUser oAuthUser : findByUserId(userId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(oAuthUser);
		}
	}

	/**
	 * Returns the number of o auth users where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByUserId(long userId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHUSER_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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
	 * Returns the number of o auth users that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching o auth users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByUserId(long userId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUserId(userId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_OAUTHUSER_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OAuthUser.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "oAuthUser.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_OAUTHAPPLICATIONID =
		new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, OAuthUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByOAuthApplicationId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OAUTHAPPLICATIONID =
		new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, OAuthUserImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByOAuthApplicationId", new String[] { Long.class.getName() },
			OAuthUserModelImpl.OAUTHAPPLICATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_OAUTHAPPLICATIONID = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByOAuthApplicationId", new String[] { Long.class.getName() });

	/**
	 * Returns all the o auth users where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @return the matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> findByOAuthApplicationId(long oAuthApplicationId)
		throws SystemException {
		return findByOAuthApplicationId(oAuthApplicationId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth users where oAuthApplicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @return the range of matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> findByOAuthApplicationId(long oAuthApplicationId,
		int start, int end) throws SystemException {
		return findByOAuthApplicationId(oAuthApplicationId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth users where oAuthApplicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> findByOAuthApplicationId(long oAuthApplicationId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OAUTHAPPLICATIONID;
			finderArgs = new Object[] { oAuthApplicationId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_OAUTHAPPLICATIONID;
			finderArgs = new Object[] {
					oAuthApplicationId,
					
					start, end, orderByComparator
				};
		}

		List<OAuthUser> list = (List<OAuthUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthUser oAuthUser : list) {
				if ((oAuthApplicationId != oAuthUser.getOAuthApplicationId())) {
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

			query.append(_SQL_SELECT_OAUTHUSER_WHERE);

			query.append(_FINDER_COLUMN_OAUTHAPPLICATIONID_OAUTHAPPLICATIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(OAuthUserModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(oAuthApplicationId);

				if (!pagination) {
					list = (List<OAuthUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<OAuthUser>(list);
				}
				else {
					list = (List<OAuthUser>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first o auth user in the ordered set where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser findByOAuthApplicationId_First(long oAuthApplicationId,
		OrderByComparator orderByComparator)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = fetchByOAuthApplicationId_First(oAuthApplicationId,
				orderByComparator);

		if (oAuthUser != null) {
			return oAuthUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("oAuthApplicationId=");
		msg.append(oAuthApplicationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the first o auth user in the ordered set where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching o auth user, or <code>null</code> if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByOAuthApplicationId_First(long oAuthApplicationId,
		OrderByComparator orderByComparator) throws SystemException {
		List<OAuthUser> list = findByOAuthApplicationId(oAuthApplicationId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last o auth user in the ordered set where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser findByOAuthApplicationId_Last(long oAuthApplicationId,
		OrderByComparator orderByComparator)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = fetchByOAuthApplicationId_Last(oAuthApplicationId,
				orderByComparator);

		if (oAuthUser != null) {
			return oAuthUser;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("oAuthApplicationId=");
		msg.append(oAuthApplicationId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the last o auth user in the ordered set where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching o auth user, or <code>null</code> if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByOAuthApplicationId_Last(long oAuthApplicationId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByOAuthApplicationId(oAuthApplicationId);

		if (count == 0) {
			return null;
		}

		List<OAuthUser> list = findByOAuthApplicationId(oAuthApplicationId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the o auth users before and after the current o auth user in the ordered set where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthUserId the primary key of the current o auth user
	 * @param oAuthApplicationId the o auth application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser[] findByOAuthApplicationId_PrevAndNext(long oAuthUserId,
		long oAuthApplicationId, OrderByComparator orderByComparator)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = findByPrimaryKey(oAuthUserId);

		Session session = null;

		try {
			session = openSession();

			OAuthUser[] array = new OAuthUserImpl[3];

			array[0] = getByOAuthApplicationId_PrevAndNext(session, oAuthUser,
					oAuthApplicationId, orderByComparator, true);

			array[1] = oAuthUser;

			array[2] = getByOAuthApplicationId_PrevAndNext(session, oAuthUser,
					oAuthApplicationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthUser getByOAuthApplicationId_PrevAndNext(Session session,
		OAuthUser oAuthUser, long oAuthApplicationId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHUSER_WHERE);

		query.append(_FINDER_COLUMN_OAUTHAPPLICATIONID_OAUTHAPPLICATIONID_2);

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
			query.append(OAuthUserModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(oAuthApplicationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth users that the user has permission to view where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @return the matching o auth users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> filterFindByOAuthApplicationId(
		long oAuthApplicationId) throws SystemException {
		return filterFindByOAuthApplicationId(oAuthApplicationId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth users that the user has permission to view where oAuthApplicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @return the range of matching o auth users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> filterFindByOAuthApplicationId(
		long oAuthApplicationId, int start, int end) throws SystemException {
		return filterFindByOAuthApplicationId(oAuthApplicationId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the o auth users that the user has permissions to view where oAuthApplicationId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching o auth users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> filterFindByOAuthApplicationId(
		long oAuthApplicationId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByOAuthApplicationId(oAuthApplicationId, start, end,
				orderByComparator);
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
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_OAUTHAPPLICATIONID_OAUTHAPPLICATIONID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OAuthUserModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OAuthUserModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OAuthUser.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, OAuthUserImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, OAuthUserImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(oAuthApplicationId);

			return (List<OAuthUser>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the o auth users before and after the current o auth user in the ordered set of o auth users that the user has permission to view where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthUserId the primary key of the current o auth user
	 * @param oAuthApplicationId the o auth application ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser[] filterFindByOAuthApplicationId_PrevAndNext(
		long oAuthUserId, long oAuthApplicationId,
		OrderByComparator orderByComparator)
		throws NoSuchUserException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByOAuthApplicationId_PrevAndNext(oAuthUserId,
				oAuthApplicationId, orderByComparator);
		}

		OAuthUser oAuthUser = findByPrimaryKey(oAuthUserId);

		Session session = null;

		try {
			session = openSession();

			OAuthUser[] array = new OAuthUserImpl[3];

			array[0] = filterGetByOAuthApplicationId_PrevAndNext(session,
					oAuthUser, oAuthApplicationId, orderByComparator, true);

			array[1] = oAuthUser;

			array[2] = filterGetByOAuthApplicationId_PrevAndNext(session,
					oAuthUser, oAuthApplicationId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthUser filterGetByOAuthApplicationId_PrevAndNext(
		Session session, OAuthUser oAuthUser, long oAuthApplicationId,
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
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_OAUTHAPPLICATIONID_OAUTHAPPLICATIONID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(OAuthUserModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(OAuthUserModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OAuthUser.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, OAuthUserImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, OAuthUserImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(oAuthApplicationId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthUser);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthUser> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the o auth users where oAuthApplicationId = &#63; from the database.
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByOAuthApplicationId(long oAuthApplicationId)
		throws SystemException {
		for (OAuthUser oAuthUser : findByOAuthApplicationId(
				oAuthApplicationId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(oAuthUser);
		}
	}

	/**
	 * Returns the number of o auth users where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @return the number of matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByOAuthApplicationId(long oAuthApplicationId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_OAUTHAPPLICATIONID;

		Object[] finderArgs = new Object[] { oAuthApplicationId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHUSER_WHERE);

			query.append(_FINDER_COLUMN_OAUTHAPPLICATIONID_OAUTHAPPLICATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(oAuthApplicationId);

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
	 * Returns the number of o auth users that the user has permission to view where oAuthApplicationId = &#63;.
	 *
	 * @param oAuthApplicationId the o auth application ID
	 * @return the number of matching o auth users that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int filterCountByOAuthApplicationId(long oAuthApplicationId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByOAuthApplicationId(oAuthApplicationId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_OAUTHUSER_WHERE);

		query.append(_FINDER_COLUMN_OAUTHAPPLICATIONID_OAUTHAPPLICATIONID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				OAuthUser.class.getName(), _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(oAuthApplicationId);

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

	private static final String _FINDER_COLUMN_OAUTHAPPLICATIONID_OAUTHAPPLICATIONID_2 =
		"oAuthUser.oAuthApplicationId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_ACCESSTOKEN = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, OAuthUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAccessToken",
			new String[] { String.class.getName() },
			OAuthUserModelImpl.ACCESSTOKEN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_ACCESSTOKEN = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccessToken",
			new String[] { String.class.getName() });

	/**
	 * Returns the o auth user where accessToken = &#63; or throws a {@link com.liferay.oauth.NoSuchUserException} if it could not be found.
	 *
	 * @param accessToken the access token
	 * @return the matching o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser findByAccessToken(String accessToken)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = fetchByAccessToken(accessToken);

		if (oAuthUser == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accessToken=");
			msg.append(accessToken);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return oAuthUser;
	}

	/**
	 * Returns the o auth user where accessToken = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accessToken the access token
	 * @return the matching o auth user, or <code>null</code> if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByAccessToken(String accessToken)
		throws SystemException {
		return fetchByAccessToken(accessToken, true);
	}

	/**
	 * Returns the o auth user where accessToken = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accessToken the access token
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching o auth user, or <code>null</code> if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByAccessToken(String accessToken,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { accessToken };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
					finderArgs, this);
		}

		if (result instanceof OAuthUser) {
			OAuthUser oAuthUser = (OAuthUser)result;

			if (!Validator.equals(accessToken, oAuthUser.getAccessToken())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_OAUTHUSER_WHERE);

			boolean bindAccessToken = false;

			if (accessToken == null) {
				query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_1);
			}
			else if (accessToken.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_3);
			}
			else {
				bindAccessToken = true;

				query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAccessToken) {
					qPos.add(accessToken);
				}

				List<OAuthUser> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
						finderArgs, list);
				}
				else {
					OAuthUser oAuthUser = list.get(0);

					result = oAuthUser;

					cacheResult(oAuthUser);

					if ((oAuthUser.getAccessToken() == null) ||
							!oAuthUser.getAccessToken().equals(accessToken)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
							finderArgs, oAuthUser);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
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
			return (OAuthUser)result;
		}
	}

	/**
	 * Removes the o auth user where accessToken = &#63; from the database.
	 *
	 * @param accessToken the access token
	 * @return the o auth user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser removeByAccessToken(String accessToken)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = findByAccessToken(accessToken);

		return remove(oAuthUser);
	}

	/**
	 * Returns the number of o auth users where accessToken = &#63;.
	 *
	 * @param accessToken the access token
	 * @return the number of matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByAccessToken(String accessToken) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_ACCESSTOKEN;

		Object[] finderArgs = new Object[] { accessToken };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHUSER_WHERE);

			boolean bindAccessToken = false;

			if (accessToken == null) {
				query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_1);
			}
			else if (accessToken.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_3);
			}
			else {
				bindAccessToken = true;

				query.append(_FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAccessToken) {
					qPos.add(accessToken);
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

	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_1 = "oAuthUser.accessToken IS NULL";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_2 = "oAuthUser.accessToken = ?";
	private static final String _FINDER_COLUMN_ACCESSTOKEN_ACCESSTOKEN_3 = "(oAuthUser.accessToken IS NULL OR oAuthUser.accessToken = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_OAI = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, OAuthUserImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_OAI",
			new String[] { Long.class.getName(), Long.class.getName() },
			OAuthUserModelImpl.USERID_COLUMN_BITMASK |
			OAuthUserModelImpl.OAUTHAPPLICATIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_OAI = new FinderPath(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_OAI",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the o auth user where userId = &#63; and oAuthApplicationId = &#63; or throws a {@link com.liferay.oauth.NoSuchUserException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param oAuthApplicationId the o auth application ID
	 * @return the matching o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser findByU_OAI(long userId, long oAuthApplicationId)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = fetchByU_OAI(userId, oAuthApplicationId);

		if (oAuthUser == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", oAuthApplicationId=");
			msg.append(oAuthApplicationId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return oAuthUser;
	}

	/**
	 * Returns the o auth user where userId = &#63; and oAuthApplicationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param oAuthApplicationId the o auth application ID
	 * @return the matching o auth user, or <code>null</code> if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByU_OAI(long userId, long oAuthApplicationId)
		throws SystemException {
		return fetchByU_OAI(userId, oAuthApplicationId, true);
	}

	/**
	 * Returns the o auth user where userId = &#63; and oAuthApplicationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param oAuthApplicationId the o auth application ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching o auth user, or <code>null</code> if a matching o auth user could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByU_OAI(long userId, long oAuthApplicationId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { userId, oAuthApplicationId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_U_OAI,
					finderArgs, this);
		}

		if (result instanceof OAuthUser) {
			OAuthUser oAuthUser = (OAuthUser)result;

			if ((userId != oAuthUser.getUserId()) ||
					(oAuthApplicationId != oAuthUser.getOAuthApplicationId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_OAUTHUSER_WHERE);

			query.append(_FINDER_COLUMN_U_OAI_USERID_2);

			query.append(_FINDER_COLUMN_U_OAI_OAUTHAPPLICATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(oAuthApplicationId);

				List<OAuthUser> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_OAI,
						finderArgs, list);
				}
				else {
					OAuthUser oAuthUser = list.get(0);

					result = oAuthUser;

					cacheResult(oAuthUser);

					if ((oAuthUser.getUserId() != userId) ||
							(oAuthUser.getOAuthApplicationId() != oAuthApplicationId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_OAI,
							finderArgs, oAuthUser);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_OAI,
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
			return (OAuthUser)result;
		}
	}

	/**
	 * Removes the o auth user where userId = &#63; and oAuthApplicationId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param oAuthApplicationId the o auth application ID
	 * @return the o auth user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser removeByU_OAI(long userId, long oAuthApplicationId)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = findByU_OAI(userId, oAuthApplicationId);

		return remove(oAuthUser);
	}

	/**
	 * Returns the number of o auth users where userId = &#63; and oAuthApplicationId = &#63;.
	 *
	 * @param userId the user ID
	 * @param oAuthApplicationId the o auth application ID
	 * @return the number of matching o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByU_OAI(long userId, long oAuthApplicationId)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_OAI;

		Object[] finderArgs = new Object[] { userId, oAuthApplicationId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_OAUTHUSER_WHERE);

			query.append(_FINDER_COLUMN_U_OAI_USERID_2);

			query.append(_FINDER_COLUMN_U_OAI_OAUTHAPPLICATIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(oAuthApplicationId);

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

	private static final String _FINDER_COLUMN_U_OAI_USERID_2 = "oAuthUser.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_OAI_OAUTHAPPLICATIONID_2 = "oAuthUser.oAuthApplicationId = ?";

	public OAuthUserPersistenceImpl() {
		setModelClass(OAuthUser.class);
	}

	/**
	 * Caches the o auth user in the entity cache if it is enabled.
	 *
	 * @param oAuthUser the o auth user
	 */
	@Override
	public void cacheResult(OAuthUser oAuthUser) {
		EntityCacheUtil.putResult(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserImpl.class, oAuthUser.getPrimaryKey(), oAuthUser);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
			new Object[] { oAuthUser.getAccessToken() }, oAuthUser);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_OAI,
			new Object[] {
				oAuthUser.getUserId(), oAuthUser.getOAuthApplicationId()
			}, oAuthUser);

		oAuthUser.resetOriginalValues();
	}

	/**
	 * Caches the o auth users in the entity cache if it is enabled.
	 *
	 * @param oAuthUsers the o auth users
	 */
	@Override
	public void cacheResult(List<OAuthUser> oAuthUsers) {
		for (OAuthUser oAuthUser : oAuthUsers) {
			if (EntityCacheUtil.getResult(
						OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
						OAuthUserImpl.class, oAuthUser.getPrimaryKey()) == null) {
				cacheResult(oAuthUser);
			}
			else {
				oAuthUser.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all o auth users.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OAuthUserImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OAuthUserImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the o auth user.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OAuthUser oAuthUser) {
		EntityCacheUtil.removeResult(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserImpl.class, oAuthUser.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(oAuthUser);
	}

	@Override
	public void clearCache(List<OAuthUser> oAuthUsers) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OAuthUser oAuthUser : oAuthUsers) {
			EntityCacheUtil.removeResult(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
				OAuthUserImpl.class, oAuthUser.getPrimaryKey());

			clearUniqueFindersCache(oAuthUser);
		}
	}

	protected void cacheUniqueFindersCache(OAuthUser oAuthUser) {
		if (oAuthUser.isNew()) {
			Object[] args = new Object[] { oAuthUser.getAccessToken() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN, args,
				oAuthUser);

			args = new Object[] {
					oAuthUser.getUserId(), oAuthUser.getOAuthApplicationId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_OAI, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_OAI, args,
				oAuthUser);
		}
		else {
			OAuthUserModelImpl oAuthUserModelImpl = (OAuthUserModelImpl)oAuthUser;

			if ((oAuthUserModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_ACCESSTOKEN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { oAuthUser.getAccessToken() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN,
					args, oAuthUser);
			}

			if ((oAuthUserModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_OAI.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthUser.getUserId(), oAuthUser.getOAuthApplicationId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_U_OAI, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_U_OAI, args,
					oAuthUser);
			}
		}
	}

	protected void clearUniqueFindersCache(OAuthUser oAuthUser) {
		OAuthUserModelImpl oAuthUserModelImpl = (OAuthUserModelImpl)oAuthUser;

		Object[] args = new Object[] { oAuthUser.getAccessToken() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN, args);

		if ((oAuthUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_ACCESSTOKEN.getColumnBitmask()) != 0) {
			args = new Object[] { oAuthUserModelImpl.getOriginalAccessToken() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACCESSTOKEN, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_ACCESSTOKEN, args);
		}

		args = new Object[] {
				oAuthUser.getUserId(), oAuthUser.getOAuthApplicationId()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_OAI, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_OAI, args);

		if ((oAuthUserModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_OAI.getColumnBitmask()) != 0) {
			args = new Object[] {
					oAuthUserModelImpl.getOriginalUserId(),
					oAuthUserModelImpl.getOriginalOAuthApplicationId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_U_OAI, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_U_OAI, args);
		}
	}

	/**
	 * Creates a new o auth user with the primary key. Does not add the o auth user to the database.
	 *
	 * @param oAuthUserId the primary key for the new o auth user
	 * @return the new o auth user
	 */
	@Override
	public OAuthUser create(long oAuthUserId) {
		OAuthUser oAuthUser = new OAuthUserImpl();

		oAuthUser.setNew(true);
		oAuthUser.setPrimaryKey(oAuthUserId);

		return oAuthUser;
	}

	/**
	 * Removes the o auth user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param oAuthUserId the primary key of the o auth user
	 * @return the o auth user that was removed
	 * @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser remove(long oAuthUserId)
		throws NoSuchUserException, SystemException {
		return remove((Serializable)oAuthUserId);
	}

	/**
	 * Removes the o auth user with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o auth user
	 * @return the o auth user that was removed
	 * @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser remove(Serializable primaryKey)
		throws NoSuchUserException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OAuthUser oAuthUser = (OAuthUser)session.get(OAuthUserImpl.class,
					primaryKey);

			if (oAuthUser == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(oAuthUser);
		}
		catch (NoSuchUserException nsee) {
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
	protected OAuthUser removeImpl(OAuthUser oAuthUser)
		throws SystemException {
		oAuthUser = toUnwrappedModel(oAuthUser);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(oAuthUser)) {
				oAuthUser = (OAuthUser)session.get(OAuthUserImpl.class,
						oAuthUser.getPrimaryKeyObj());
			}

			if (oAuthUser != null) {
				session.delete(oAuthUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (oAuthUser != null) {
			clearCache(oAuthUser);
		}

		return oAuthUser;
	}

	@Override
	public OAuthUser updateImpl(com.liferay.oauth.model.OAuthUser oAuthUser)
		throws SystemException {
		oAuthUser = toUnwrappedModel(oAuthUser);

		boolean isNew = oAuthUser.isNew();

		OAuthUserModelImpl oAuthUserModelImpl = (OAuthUserModelImpl)oAuthUser;

		Session session = null;

		try {
			session = openSession();

			if (oAuthUser.isNew()) {
				session.save(oAuthUser);

				oAuthUser.setNew(false);
			}
			else {
				session.merge(oAuthUser);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OAuthUserModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((oAuthUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthUserModelImpl.getOriginalUserId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { oAuthUserModelImpl.getUserId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((oAuthUserModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OAUTHAPPLICATIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthUserModelImpl.getOriginalOAuthApplicationId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OAUTHAPPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OAUTHAPPLICATIONID,
					args);

				args = new Object[] { oAuthUserModelImpl.getOAuthApplicationId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_OAUTHAPPLICATIONID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_OAUTHAPPLICATIONID,
					args);
			}
		}

		EntityCacheUtil.putResult(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
			OAuthUserImpl.class, oAuthUser.getPrimaryKey(), oAuthUser);

		clearUniqueFindersCache(oAuthUser);
		cacheUniqueFindersCache(oAuthUser);

		return oAuthUser;
	}

	protected OAuthUser toUnwrappedModel(OAuthUser oAuthUser) {
		if (oAuthUser instanceof OAuthUserImpl) {
			return oAuthUser;
		}

		OAuthUserImpl oAuthUserImpl = new OAuthUserImpl();

		oAuthUserImpl.setNew(oAuthUser.isNew());
		oAuthUserImpl.setPrimaryKey(oAuthUser.getPrimaryKey());

		oAuthUserImpl.setOAuthUserId(oAuthUser.getOAuthUserId());
		oAuthUserImpl.setCompanyId(oAuthUser.getCompanyId());
		oAuthUserImpl.setUserId(oAuthUser.getUserId());
		oAuthUserImpl.setUserName(oAuthUser.getUserName());
		oAuthUserImpl.setCreateDate(oAuthUser.getCreateDate());
		oAuthUserImpl.setModifiedDate(oAuthUser.getModifiedDate());
		oAuthUserImpl.setOAuthApplicationId(oAuthUser.getOAuthApplicationId());
		oAuthUserImpl.setAccessToken(oAuthUser.getAccessToken());
		oAuthUserImpl.setAccessSecret(oAuthUser.getAccessSecret());

		return oAuthUserImpl;
	}

	/**
	 * Returns the o auth user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth user
	 * @return the o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser findByPrimaryKey(Serializable primaryKey)
		throws NoSuchUserException, SystemException {
		OAuthUser oAuthUser = fetchByPrimaryKey(primaryKey);

		if (oAuthUser == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return oAuthUser;
	}

	/**
	 * Returns the o auth user with the primary key or throws a {@link com.liferay.oauth.NoSuchUserException} if it could not be found.
	 *
	 * @param oAuthUserId the primary key of the o auth user
	 * @return the o auth user
	 * @throws com.liferay.oauth.NoSuchUserException if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser findByPrimaryKey(long oAuthUserId)
		throws NoSuchUserException, SystemException {
		return findByPrimaryKey((Serializable)oAuthUserId);
	}

	/**
	 * Returns the o auth user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth user
	 * @return the o auth user, or <code>null</code> if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		OAuthUser oAuthUser = (OAuthUser)EntityCacheUtil.getResult(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
				OAuthUserImpl.class, primaryKey);

		if (oAuthUser == _nullOAuthUser) {
			return null;
		}

		if (oAuthUser == null) {
			Session session = null;

			try {
				session = openSession();

				oAuthUser = (OAuthUser)session.get(OAuthUserImpl.class,
						primaryKey);

				if (oAuthUser != null) {
					cacheResult(oAuthUser);
				}
				else {
					EntityCacheUtil.putResult(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
						OAuthUserImpl.class, primaryKey, _nullOAuthUser);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(OAuthUserModelImpl.ENTITY_CACHE_ENABLED,
					OAuthUserImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return oAuthUser;
	}

	/**
	 * Returns the o auth user with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param oAuthUserId the primary key of the o auth user
	 * @return the o auth user, or <code>null</code> if a o auth user with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthUser fetchByPrimaryKey(long oAuthUserId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)oAuthUserId);
	}

	/**
	 * Returns all the o auth users.
	 *
	 * @return the o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the o auth users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @return the range of o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the o auth users.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.oauth.model.impl.OAuthUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of o auth users
	 * @param end the upper bound of the range of o auth users (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of o auth users
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<OAuthUser> findAll(int start, int end,
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

		List<OAuthUser> list = (List<OAuthUser>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OAUTHUSER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OAUTHUSER;

				if (pagination) {
					sql = sql.concat(OAuthUserModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<OAuthUser>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<OAuthUser>(list);
				}
				else {
					list = (List<OAuthUser>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the o auth users from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (OAuthUser oAuthUser : findAll()) {
			remove(oAuthUser);
		}
	}

	/**
	 * Returns the number of o auth users.
	 *
	 * @return the number of o auth users
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

				Query q = session.createQuery(_SQL_COUNT_OAUTHUSER);

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
	 * Initializes the o auth user persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.oauth.model.OAuthUser")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OAuthUser>> listenersList = new ArrayList<ModelListener<OAuthUser>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OAuthUser>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(OAuthUserImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_OAUTHUSER = "SELECT oAuthUser FROM OAuthUser oAuthUser";
	private static final String _SQL_SELECT_OAUTHUSER_WHERE = "SELECT oAuthUser FROM OAuthUser oAuthUser WHERE ";
	private static final String _SQL_COUNT_OAUTHUSER = "SELECT COUNT(oAuthUser) FROM OAuthUser oAuthUser";
	private static final String _SQL_COUNT_OAUTHUSER_WHERE = "SELECT COUNT(oAuthUser) FROM OAuthUser oAuthUser WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "oAuthUser.oAuthUserId";
	private static final String _FILTER_SQL_SELECT_OAUTHUSER_WHERE = "SELECT DISTINCT {oAuthUser.*} FROM OAuth_OAuthUser oAuthUser WHERE ";
	private static final String _FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {OAuth_OAuthUser.*} FROM (SELECT DISTINCT oAuthUser.oAuthUserId FROM OAuth_OAuthUser oAuthUser WHERE ";
	private static final String _FILTER_SQL_SELECT_OAUTHUSER_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN OAuth_OAuthUser ON TEMP_TABLE.oAuthUserId = OAuth_OAuthUser.oAuthUserId";
	private static final String _FILTER_SQL_COUNT_OAUTHUSER_WHERE = "SELECT COUNT(DISTINCT oAuthUser.oAuthUserId) AS COUNT_VALUE FROM OAuth_OAuthUser oAuthUser WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "oAuthUser";
	private static final String _FILTER_ENTITY_TABLE = "OAuth_OAuthUser";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthUser.";
	private static final String _ORDER_BY_ENTITY_TABLE = "OAuth_OAuthUser.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuthUser exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OAuthUser exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OAuthUserPersistenceImpl.class);
	private static OAuthUser _nullOAuthUser = new OAuthUserImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OAuthUser> toCacheModel() {
				return _nullOAuthUserCacheModel;
			}
		};

	private static CacheModel<OAuthUser> _nullOAuthUserCacheModel = new CacheModel<OAuthUser>() {
			@Override
			public OAuthUser toEntityModel() {
				return _nullOAuthUser;
			}
		};
}