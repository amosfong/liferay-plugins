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

package com.liferay.saml.service.persistence;

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
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.saml.NoSuchSpSessionException;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.model.impl.SamlSpSessionImpl;
import com.liferay.saml.model.impl.SamlSpSessionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the saml sp session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpSessionPersistence
 * @see SamlSpSessionUtil
 * @generated
 */
public class SamlSpSessionPersistenceImpl extends BasePersistenceImpl<SamlSpSession>
	implements SamlSpSessionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SamlSpSessionUtil} to access the saml sp session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SamlSpSessionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlSpSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlSpSessionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlSpSessionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySamlSpSessionKey", new String[] { String.class.getName() },
			SamlSpSessionModelImpl.SAMLSPSESSIONKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SAMLSPSESSIONKEY = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySamlSpSessionKey", new String[] { String.class.getName() });

	/**
	 * Returns the saml sp session where samlSpSessionKey = &#63; or throws a {@link com.liferay.saml.NoSuchSpSessionException} if it could not be found.
	 *
	 * @param samlSpSessionKey the saml sp session key
	 * @return the matching saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession findBySamlSpSessionKey(String samlSpSessionKey)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = fetchBySamlSpSessionKey(samlSpSessionKey);

		if (samlSpSession == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("samlSpSessionKey=");
			msg.append(samlSpSessionKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSpSessionException(msg.toString());
		}

		return samlSpSession;
	}

	/**
	 * Returns the saml sp session where samlSpSessionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param samlSpSessionKey the saml sp session key
	 * @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchBySamlSpSessionKey(String samlSpSessionKey)
		throws SystemException {
		return fetchBySamlSpSessionKey(samlSpSessionKey, true);
	}

	/**
	 * Returns the saml sp session where samlSpSessionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param samlSpSessionKey the saml sp session key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchBySamlSpSessionKey(String samlSpSessionKey,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { samlSpSessionKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY,
					finderArgs, this);
		}

		if (result instanceof SamlSpSession) {
			SamlSpSession samlSpSession = (SamlSpSession)result;

			if (!Validator.equals(samlSpSessionKey,
						samlSpSession.getSamlSpSessionKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SAMLSPSESSION_WHERE);

			boolean bindSamlSpSessionKey = false;

			if (samlSpSessionKey == null) {
				query.append(_FINDER_COLUMN_SAMLSPSESSIONKEY_SAMLSPSESSIONKEY_1);
			}
			else if (samlSpSessionKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SAMLSPSESSIONKEY_SAMLSPSESSIONKEY_3);
			}
			else {
				bindSamlSpSessionKey = true;

				query.append(_FINDER_COLUMN_SAMLSPSESSIONKEY_SAMLSPSESSIONKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSamlSpSessionKey) {
					qPos.add(samlSpSessionKey);
				}

				List<SamlSpSession> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY,
						finderArgs, list);
				}
				else {
					SamlSpSession samlSpSession = list.get(0);

					result = samlSpSession;

					cacheResult(samlSpSession);

					if ((samlSpSession.getSamlSpSessionKey() == null) ||
							!samlSpSession.getSamlSpSessionKey()
											  .equals(samlSpSessionKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY,
							finderArgs, samlSpSession);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY,
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
			return (SamlSpSession)result;
		}
	}

	/**
	 * Removes the saml sp session where samlSpSessionKey = &#63; from the database.
	 *
	 * @param samlSpSessionKey the saml sp session key
	 * @return the saml sp session that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession removeBySamlSpSessionKey(String samlSpSessionKey)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = findBySamlSpSessionKey(samlSpSessionKey);

		return remove(samlSpSession);
	}

	/**
	 * Returns the number of saml sp sessions where samlSpSessionKey = &#63;.
	 *
	 * @param samlSpSessionKey the saml sp session key
	 * @return the number of matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySamlSpSessionKey(String samlSpSessionKey)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SAMLSPSESSIONKEY;

		Object[] finderArgs = new Object[] { samlSpSessionKey };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMLSPSESSION_WHERE);

			boolean bindSamlSpSessionKey = false;

			if (samlSpSessionKey == null) {
				query.append(_FINDER_COLUMN_SAMLSPSESSIONKEY_SAMLSPSESSIONKEY_1);
			}
			else if (samlSpSessionKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SAMLSPSESSIONKEY_SAMLSPSESSIONKEY_3);
			}
			else {
				bindSamlSpSessionKey = true;

				query.append(_FINDER_COLUMN_SAMLSPSESSIONKEY_SAMLSPSESSIONKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSamlSpSessionKey) {
					qPos.add(samlSpSessionKey);
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

	private static final String _FINDER_COLUMN_SAMLSPSESSIONKEY_SAMLSPSESSIONKEY_1 =
		"samlSpSession.samlSpSessionKey IS NULL";
	private static final String _FINDER_COLUMN_SAMLSPSESSIONKEY_SAMLSPSESSIONKEY_2 =
		"samlSpSession.samlSpSessionKey = ?";
	private static final String _FINDER_COLUMN_SAMLSPSESSIONKEY_SAMLSPSESSIONKEY_3 =
		"(samlSpSession.samlSpSessionKey IS NULL OR samlSpSession.samlSpSessionKey = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAMEIDVALUE =
		new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlSpSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByNameIdValue",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEIDVALUE =
		new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlSpSessionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByNameIdValue", new String[] { String.class.getName() },
			SamlSpSessionModelImpl.NAMEIDVALUE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAMEIDVALUE = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNameIdValue",
			new String[] { String.class.getName() });

	/**
	 * Returns all the saml sp sessions where nameIdValue = &#63;.
	 *
	 * @param nameIdValue the name ID value
	 * @return the matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpSession> findByNameIdValue(String nameIdValue)
		throws SystemException {
		return findByNameIdValue(nameIdValue, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml sp sessions where nameIdValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param nameIdValue the name ID value
	 * @param start the lower bound of the range of saml sp sessions
	 * @param end the upper bound of the range of saml sp sessions (not inclusive)
	 * @return the range of matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpSession> findByNameIdValue(String nameIdValue, int start,
		int end) throws SystemException {
		return findByNameIdValue(nameIdValue, start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml sp sessions where nameIdValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param nameIdValue the name ID value
	 * @param start the lower bound of the range of saml sp sessions
	 * @param end the upper bound of the range of saml sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpSession> findByNameIdValue(String nameIdValue, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEIDVALUE;
			finderArgs = new Object[] { nameIdValue };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAMEIDVALUE;
			finderArgs = new Object[] { nameIdValue, start, end, orderByComparator };
		}

		List<SamlSpSession> list = (List<SamlSpSession>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SamlSpSession samlSpSession : list) {
				if (!Validator.equals(nameIdValue,
							samlSpSession.getNameIdValue())) {
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

			query.append(_SQL_SELECT_SAMLSPSESSION_WHERE);

			boolean bindNameIdValue = false;

			if (nameIdValue == null) {
				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_1);
			}
			else if (nameIdValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_3);
			}
			else {
				bindNameIdValue = true;

				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SamlSpSessionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindNameIdValue) {
					qPos.add(nameIdValue);
				}

				if (!pagination) {
					list = (List<SamlSpSession>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SamlSpSession>(list);
				}
				else {
					list = (List<SamlSpSession>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first saml sp session in the ordered set where nameIdValue = &#63;.
	 *
	 * @param nameIdValue the name ID value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession findByNameIdValue_First(String nameIdValue,
		OrderByComparator orderByComparator)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = fetchByNameIdValue_First(nameIdValue,
				orderByComparator);

		if (samlSpSession != null) {
			return samlSpSession;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("nameIdValue=");
		msg.append(nameIdValue);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSpSessionException(msg.toString());
	}

	/**
	 * Returns the first saml sp session in the ordered set where nameIdValue = &#63;.
	 *
	 * @param nameIdValue the name ID value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchByNameIdValue_First(String nameIdValue,
		OrderByComparator orderByComparator) throws SystemException {
		List<SamlSpSession> list = findByNameIdValue(nameIdValue, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last saml sp session in the ordered set where nameIdValue = &#63;.
	 *
	 * @param nameIdValue the name ID value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession findByNameIdValue_Last(String nameIdValue,
		OrderByComparator orderByComparator)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = fetchByNameIdValue_Last(nameIdValue,
				orderByComparator);

		if (samlSpSession != null) {
			return samlSpSession;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("nameIdValue=");
		msg.append(nameIdValue);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSpSessionException(msg.toString());
	}

	/**
	 * Returns the last saml sp session in the ordered set where nameIdValue = &#63;.
	 *
	 * @param nameIdValue the name ID value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchByNameIdValue_Last(String nameIdValue,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByNameIdValue(nameIdValue);

		if (count == 0) {
			return null;
		}

		List<SamlSpSession> list = findByNameIdValue(nameIdValue, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the saml sp sessions before and after the current saml sp session in the ordered set where nameIdValue = &#63;.
	 *
	 * @param samlSpSessionId the primary key of the current saml sp session
	 * @param nameIdValue the name ID value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession[] findByNameIdValue_PrevAndNext(long samlSpSessionId,
		String nameIdValue, OrderByComparator orderByComparator)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = findByPrimaryKey(samlSpSessionId);

		Session session = null;

		try {
			session = openSession();

			SamlSpSession[] array = new SamlSpSessionImpl[3];

			array[0] = getByNameIdValue_PrevAndNext(session, samlSpSession,
					nameIdValue, orderByComparator, true);

			array[1] = samlSpSession;

			array[2] = getByNameIdValue_PrevAndNext(session, samlSpSession,
					nameIdValue, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SamlSpSession getByNameIdValue_PrevAndNext(Session session,
		SamlSpSession samlSpSession, String nameIdValue,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SAMLSPSESSION_WHERE);

		boolean bindNameIdValue = false;

		if (nameIdValue == null) {
			query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_1);
		}
		else if (nameIdValue.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_3);
		}
		else {
			bindNameIdValue = true;

			query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_2);
		}

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
			query.append(SamlSpSessionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindNameIdValue) {
			qPos.add(nameIdValue);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(samlSpSession);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SamlSpSession> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the saml sp sessions where nameIdValue = &#63; from the database.
	 *
	 * @param nameIdValue the name ID value
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByNameIdValue(String nameIdValue)
		throws SystemException {
		for (SamlSpSession samlSpSession : findByNameIdValue(nameIdValue,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(samlSpSession);
		}
	}

	/**
	 * Returns the number of saml sp sessions where nameIdValue = &#63;.
	 *
	 * @param nameIdValue the name ID value
	 * @return the number of matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByNameIdValue(String nameIdValue) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAMEIDVALUE;

		Object[] finderArgs = new Object[] { nameIdValue };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMLSPSESSION_WHERE);

			boolean bindNameIdValue = false;

			if (nameIdValue == null) {
				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_1);
			}
			else if (nameIdValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_3);
			}
			else {
				bindNameIdValue = true;

				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindNameIdValue) {
					qPos.add(nameIdValue);
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

	private static final String _FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_1 = "samlSpSession.nameIdValue IS NULL";
	private static final String _FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_2 = "samlSpSession.nameIdValue = ?";
	private static final String _FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_3 = "(samlSpSession.nameIdValue IS NULL OR samlSpSession.nameIdValue = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_JSESSIONID = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlSpSessionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByJSessionId", new String[] { String.class.getName() },
			SamlSpSessionModelImpl.JSESSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JSESSIONID = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJSessionId",
			new String[] { String.class.getName() });

	/**
	 * Returns the saml sp session where jSessionId = &#63; or throws a {@link com.liferay.saml.NoSuchSpSessionException} if it could not be found.
	 *
	 * @param jSessionId the j session ID
	 * @return the matching saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession findByJSessionId(String jSessionId)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = fetchByJSessionId(jSessionId);

		if (samlSpSession == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("jSessionId=");
			msg.append(jSessionId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSpSessionException(msg.toString());
		}

		return samlSpSession;
	}

	/**
	 * Returns the saml sp session where jSessionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param jSessionId the j session ID
	 * @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchByJSessionId(String jSessionId)
		throws SystemException {
		return fetchByJSessionId(jSessionId, true);
	}

	/**
	 * Returns the saml sp session where jSessionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param jSessionId the j session ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchByJSessionId(String jSessionId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { jSessionId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_JSESSIONID,
					finderArgs, this);
		}

		if (result instanceof SamlSpSession) {
			SamlSpSession samlSpSession = (SamlSpSession)result;

			if (!Validator.equals(jSessionId, samlSpSession.getJSessionId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SAMLSPSESSION_WHERE);

			boolean bindJSessionId = false;

			if (jSessionId == null) {
				query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_1);
			}
			else if (jSessionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_3);
			}
			else {
				bindJSessionId = true;

				query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindJSessionId) {
					qPos.add(jSessionId);
				}

				List<SamlSpSession> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SamlSpSessionPersistenceImpl.fetchByJSessionId(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SamlSpSession samlSpSession = list.get(0);

					result = samlSpSession;

					cacheResult(samlSpSession);

					if ((samlSpSession.getJSessionId() == null) ||
							!samlSpSession.getJSessionId().equals(jSessionId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID,
							finderArgs, samlSpSession);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_JSESSIONID,
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
			return (SamlSpSession)result;
		}
	}

	/**
	 * Removes the saml sp session where jSessionId = &#63; from the database.
	 *
	 * @param jSessionId the j session ID
	 * @return the saml sp session that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession removeByJSessionId(String jSessionId)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = findByJSessionId(jSessionId);

		return remove(samlSpSession);
	}

	/**
	 * Returns the number of saml sp sessions where jSessionId = &#63;.
	 *
	 * @param jSessionId the j session ID
	 * @return the number of matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByJSessionId(String jSessionId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_JSESSIONID;

		Object[] finderArgs = new Object[] { jSessionId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMLSPSESSION_WHERE);

			boolean bindJSessionId = false;

			if (jSessionId == null) {
				query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_1);
			}
			else if (jSessionId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_3);
			}
			else {
				bindJSessionId = true;

				query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindJSessionId) {
					qPos.add(jSessionId);
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

	private static final String _FINDER_COLUMN_JSESSIONID_JSESSIONID_1 = "samlSpSession.jSessionId IS NULL";
	private static final String _FINDER_COLUMN_JSESSIONID_JSESSIONID_2 = "samlSpSession.jSessionId = ?";
	private static final String _FINDER_COLUMN_JSESSIONID_JSESSIONID_3 = "(samlSpSession.jSessionId IS NULL OR samlSpSession.jSessionId = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_SESSIONINDEX = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlSpSessionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySessionIndex", new String[] { String.class.getName() },
			SamlSpSessionModelImpl.SESSIONINDEX_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SESSIONINDEX = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySessionIndex",
			new String[] { String.class.getName() });

	/**
	 * Returns the saml sp session where sessionIndex = &#63; or throws a {@link com.liferay.saml.NoSuchSpSessionException} if it could not be found.
	 *
	 * @param sessionIndex the session index
	 * @return the matching saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession findBySessionIndex(String sessionIndex)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = fetchBySessionIndex(sessionIndex);

		if (samlSpSession == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("sessionIndex=");
			msg.append(sessionIndex);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSpSessionException(msg.toString());
		}

		return samlSpSession;
	}

	/**
	 * Returns the saml sp session where sessionIndex = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sessionIndex the session index
	 * @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchBySessionIndex(String sessionIndex)
		throws SystemException {
		return fetchBySessionIndex(sessionIndex, true);
	}

	/**
	 * Returns the saml sp session where sessionIndex = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sessionIndex the session index
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching saml sp session, or <code>null</code> if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchBySessionIndex(String sessionIndex,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { sessionIndex };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SESSIONINDEX,
					finderArgs, this);
		}

		if (result instanceof SamlSpSession) {
			SamlSpSession samlSpSession = (SamlSpSession)result;

			if (!Validator.equals(sessionIndex, samlSpSession.getSessionIndex())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SAMLSPSESSION_WHERE);

			boolean bindSessionIndex = false;

			if (sessionIndex == null) {
				query.append(_FINDER_COLUMN_SESSIONINDEX_SESSIONINDEX_1);
			}
			else if (sessionIndex.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SESSIONINDEX_SESSIONINDEX_3);
			}
			else {
				bindSessionIndex = true;

				query.append(_FINDER_COLUMN_SESSIONINDEX_SESSIONINDEX_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSessionIndex) {
					qPos.add(sessionIndex);
				}

				List<SamlSpSession> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SESSIONINDEX,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SamlSpSessionPersistenceImpl.fetchBySessionIndex(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SamlSpSession samlSpSession = list.get(0);

					result = samlSpSession;

					cacheResult(samlSpSession);

					if ((samlSpSession.getSessionIndex() == null) ||
							!samlSpSession.getSessionIndex().equals(sessionIndex)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SESSIONINDEX,
							finderArgs, samlSpSession);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SESSIONINDEX,
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
			return (SamlSpSession)result;
		}
	}

	/**
	 * Removes the saml sp session where sessionIndex = &#63; from the database.
	 *
	 * @param sessionIndex the session index
	 * @return the saml sp session that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession removeBySessionIndex(String sessionIndex)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = findBySessionIndex(sessionIndex);

		return remove(samlSpSession);
	}

	/**
	 * Returns the number of saml sp sessions where sessionIndex = &#63;.
	 *
	 * @param sessionIndex the session index
	 * @return the number of matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySessionIndex(String sessionIndex)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SESSIONINDEX;

		Object[] finderArgs = new Object[] { sessionIndex };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMLSPSESSION_WHERE);

			boolean bindSessionIndex = false;

			if (sessionIndex == null) {
				query.append(_FINDER_COLUMN_SESSIONINDEX_SESSIONINDEX_1);
			}
			else if (sessionIndex.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SESSIONINDEX_SESSIONINDEX_3);
			}
			else {
				bindSessionIndex = true;

				query.append(_FINDER_COLUMN_SESSIONINDEX_SESSIONINDEX_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSessionIndex) {
					qPos.add(sessionIndex);
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

	private static final String _FINDER_COLUMN_SESSIONINDEX_SESSIONINDEX_1 = "samlSpSession.sessionIndex IS NULL";
	private static final String _FINDER_COLUMN_SESSIONINDEX_SESSIONINDEX_2 = "samlSpSession.sessionIndex = ?";
	private static final String _FINDER_COLUMN_SESSIONINDEX_SESSIONINDEX_3 = "(samlSpSession.sessionIndex IS NULL OR samlSpSession.sessionIndex = '')";

	public SamlSpSessionPersistenceImpl() {
		setModelClass(SamlSpSession.class);
	}

	/**
	 * Caches the saml sp session in the entity cache if it is enabled.
	 *
	 * @param samlSpSession the saml sp session
	 */
	@Override
	public void cacheResult(SamlSpSession samlSpSession) {
		EntityCacheUtil.putResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionImpl.class, samlSpSession.getPrimaryKey(),
			samlSpSession);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY,
			new Object[] { samlSpSession.getSamlSpSessionKey() }, samlSpSession);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID,
			new Object[] { samlSpSession.getJSessionId() }, samlSpSession);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SESSIONINDEX,
			new Object[] { samlSpSession.getSessionIndex() }, samlSpSession);

		samlSpSession.resetOriginalValues();
	}

	/**
	 * Caches the saml sp sessions in the entity cache if it is enabled.
	 *
	 * @param samlSpSessions the saml sp sessions
	 */
	@Override
	public void cacheResult(List<SamlSpSession> samlSpSessions) {
		for (SamlSpSession samlSpSession : samlSpSessions) {
			if (EntityCacheUtil.getResult(
						SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
						SamlSpSessionImpl.class, samlSpSession.getPrimaryKey()) == null) {
				cacheResult(samlSpSession);
			}
			else {
				samlSpSession.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all saml sp sessions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SamlSpSessionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SamlSpSessionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the saml sp session.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SamlSpSession samlSpSession) {
		EntityCacheUtil.removeResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionImpl.class, samlSpSession.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(samlSpSession);
	}

	@Override
	public void clearCache(List<SamlSpSession> samlSpSessions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SamlSpSession samlSpSession : samlSpSessions) {
			EntityCacheUtil.removeResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
				SamlSpSessionImpl.class, samlSpSession.getPrimaryKey());

			clearUniqueFindersCache(samlSpSession);
		}
	}

	protected void cacheUniqueFindersCache(SamlSpSession samlSpSession) {
		if (samlSpSession.isNew()) {
			Object[] args = new Object[] { samlSpSession.getSamlSpSessionKey() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SAMLSPSESSIONKEY,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY,
				args, samlSpSession);

			args = new Object[] { samlSpSession.getJSessionId() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JSESSIONID, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID, args,
				samlSpSession);

			args = new Object[] { samlSpSession.getSessionIndex() };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SESSIONINDEX, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SESSIONINDEX, args,
				samlSpSession);
		}
		else {
			SamlSpSessionModelImpl samlSpSessionModelImpl = (SamlSpSessionModelImpl)samlSpSession;

			if ((samlSpSessionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { samlSpSession.getSamlSpSessionKey() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SAMLSPSESSIONKEY,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY,
					args, samlSpSession);
			}

			if ((samlSpSessionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_JSESSIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { samlSpSession.getJSessionId() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JSESSIONID,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID,
					args, samlSpSession);
			}

			if ((samlSpSessionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SESSIONINDEX.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { samlSpSession.getSessionIndex() };

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SESSIONINDEX,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SESSIONINDEX,
					args, samlSpSession);
			}
		}
	}

	protected void clearUniqueFindersCache(SamlSpSession samlSpSession) {
		SamlSpSessionModelImpl samlSpSessionModelImpl = (SamlSpSessionModelImpl)samlSpSession;

		Object[] args = new Object[] { samlSpSession.getSamlSpSessionKey() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SAMLSPSESSIONKEY, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY, args);

		if ((samlSpSessionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					samlSpSessionModelImpl.getOriginalSamlSpSessionKey()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SAMLSPSESSIONKEY,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SAMLSPSESSIONKEY,
				args);
		}

		args = new Object[] { samlSpSession.getJSessionId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JSESSIONID, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_JSESSIONID, args);

		if ((samlSpSessionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_JSESSIONID.getColumnBitmask()) != 0) {
			args = new Object[] { samlSpSessionModelImpl.getOriginalJSessionId() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JSESSIONID, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_JSESSIONID, args);
		}

		args = new Object[] { samlSpSession.getSessionIndex() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SESSIONINDEX, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SESSIONINDEX, args);

		if ((samlSpSessionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SESSIONINDEX.getColumnBitmask()) != 0) {
			args = new Object[] { samlSpSessionModelImpl.getOriginalSessionIndex() };

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SESSIONINDEX, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SESSIONINDEX, args);
		}
	}

	/**
	 * Creates a new saml sp session with the primary key. Does not add the saml sp session to the database.
	 *
	 * @param samlSpSessionId the primary key for the new saml sp session
	 * @return the new saml sp session
	 */
	@Override
	public SamlSpSession create(long samlSpSessionId) {
		SamlSpSession samlSpSession = new SamlSpSessionImpl();

		samlSpSession.setNew(true);
		samlSpSession.setPrimaryKey(samlSpSessionId);

		return samlSpSession;
	}

	/**
	 * Removes the saml sp session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param samlSpSessionId the primary key of the saml sp session
	 * @return the saml sp session that was removed
	 * @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession remove(long samlSpSessionId)
		throws NoSuchSpSessionException, SystemException {
		return remove((Serializable)samlSpSessionId);
	}

	/**
	 * Removes the saml sp session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the saml sp session
	 * @return the saml sp session that was removed
	 * @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession remove(Serializable primaryKey)
		throws NoSuchSpSessionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SamlSpSession samlSpSession = (SamlSpSession)session.get(SamlSpSessionImpl.class,
					primaryKey);

			if (samlSpSession == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSpSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(samlSpSession);
		}
		catch (NoSuchSpSessionException nsee) {
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
	protected SamlSpSession removeImpl(SamlSpSession samlSpSession)
		throws SystemException {
		samlSpSession = toUnwrappedModel(samlSpSession);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(samlSpSession)) {
				samlSpSession = (SamlSpSession)session.get(SamlSpSessionImpl.class,
						samlSpSession.getPrimaryKeyObj());
			}

			if (samlSpSession != null) {
				session.delete(samlSpSession);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (samlSpSession != null) {
			clearCache(samlSpSession);
		}

		return samlSpSession;
	}

	@Override
	public SamlSpSession updateImpl(
		com.liferay.saml.model.SamlSpSession samlSpSession)
		throws SystemException {
		samlSpSession = toUnwrappedModel(samlSpSession);

		boolean isNew = samlSpSession.isNew();

		SamlSpSessionModelImpl samlSpSessionModelImpl = (SamlSpSessionModelImpl)samlSpSession;

		Session session = null;

		try {
			session = openSession();

			if (samlSpSession.isNew()) {
				session.save(samlSpSession);

				samlSpSession.setNew(false);
			}
			else {
				session.merge(samlSpSession);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SamlSpSessionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((samlSpSessionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEIDVALUE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						samlSpSessionModelImpl.getOriginalNameIdValue()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAMEIDVALUE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEIDVALUE,
					args);

				args = new Object[] { samlSpSessionModelImpl.getNameIdValue() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAMEIDVALUE,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAMEIDVALUE,
					args);
			}
		}

		EntityCacheUtil.putResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionImpl.class, samlSpSession.getPrimaryKey(),
			samlSpSession);

		clearUniqueFindersCache(samlSpSession);
		cacheUniqueFindersCache(samlSpSession);

		return samlSpSession;
	}

	protected SamlSpSession toUnwrappedModel(SamlSpSession samlSpSession) {
		if (samlSpSession instanceof SamlSpSessionImpl) {
			return samlSpSession;
		}

		SamlSpSessionImpl samlSpSessionImpl = new SamlSpSessionImpl();

		samlSpSessionImpl.setNew(samlSpSession.isNew());
		samlSpSessionImpl.setPrimaryKey(samlSpSession.getPrimaryKey());

		samlSpSessionImpl.setSamlSpSessionId(samlSpSession.getSamlSpSessionId());
		samlSpSessionImpl.setCompanyId(samlSpSession.getCompanyId());
		samlSpSessionImpl.setUserId(samlSpSession.getUserId());
		samlSpSessionImpl.setUserName(samlSpSession.getUserName());
		samlSpSessionImpl.setCreateDate(samlSpSession.getCreateDate());
		samlSpSessionImpl.setModifiedDate(samlSpSession.getModifiedDate());
		samlSpSessionImpl.setSamlSpSessionKey(samlSpSession.getSamlSpSessionKey());
		samlSpSessionImpl.setAssertionXml(samlSpSession.getAssertionXml());
		samlSpSessionImpl.setJSessionId(samlSpSession.getJSessionId());
		samlSpSessionImpl.setNameIdFormat(samlSpSession.getNameIdFormat());
		samlSpSessionImpl.setNameIdValue(samlSpSession.getNameIdValue());
		samlSpSessionImpl.setSessionIndex(samlSpSession.getSessionIndex());
		samlSpSessionImpl.setTerminated(samlSpSession.isTerminated());

		return samlSpSessionImpl;
	}

	/**
	 * Returns the saml sp session with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml sp session
	 * @return the saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = fetchByPrimaryKey(primaryKey);

		if (samlSpSession == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSpSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return samlSpSession;
	}

	/**
	 * Returns the saml sp session with the primary key or throws a {@link com.liferay.saml.NoSuchSpSessionException} if it could not be found.
	 *
	 * @param samlSpSessionId the primary key of the saml sp session
	 * @return the saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession findByPrimaryKey(long samlSpSessionId)
		throws NoSuchSpSessionException, SystemException {
		return findByPrimaryKey((Serializable)samlSpSessionId);
	}

	/**
	 * Returns the saml sp session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml sp session
	 * @return the saml sp session, or <code>null</code> if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SamlSpSession samlSpSession = (SamlSpSession)EntityCacheUtil.getResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
				SamlSpSessionImpl.class, primaryKey);

		if (samlSpSession == _nullSamlSpSession) {
			return null;
		}

		if (samlSpSession == null) {
			Session session = null;

			try {
				session = openSession();

				samlSpSession = (SamlSpSession)session.get(SamlSpSessionImpl.class,
						primaryKey);

				if (samlSpSession != null) {
					cacheResult(samlSpSession);
				}
				else {
					EntityCacheUtil.putResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
						SamlSpSessionImpl.class, primaryKey, _nullSamlSpSession);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
					SamlSpSessionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return samlSpSession;
	}

	/**
	 * Returns the saml sp session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samlSpSessionId the primary key of the saml sp session
	 * @return the saml sp session, or <code>null</code> if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession fetchByPrimaryKey(long samlSpSessionId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)samlSpSessionId);
	}

	/**
	 * Returns all the saml sp sessions.
	 *
	 * @return the saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpSession> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml sp sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp sessions
	 * @param end the upper bound of the range of saml sp sessions (not inclusive)
	 * @return the range of saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpSession> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml sp sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp sessions
	 * @param end the upper bound of the range of saml sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpSession> findAll(int start, int end,
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

		List<SamlSpSession> list = (List<SamlSpSession>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SAMLSPSESSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SAMLSPSESSION;

				if (pagination) {
					sql = sql.concat(SamlSpSessionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SamlSpSession>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SamlSpSession>(list);
				}
				else {
					list = (List<SamlSpSession>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the saml sp sessions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SamlSpSession samlSpSession : findAll()) {
			remove(samlSpSession);
		}
	}

	/**
	 * Returns the number of saml sp sessions.
	 *
	 * @return the number of saml sp sessions
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

				Query q = session.createQuery(_SQL_COUNT_SAMLSPSESSION);

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

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the saml sp session persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.saml.model.SamlSpSession")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SamlSpSession>> listenersList = new ArrayList<ModelListener<SamlSpSession>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SamlSpSession>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SamlSpSessionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SAMLSPSESSION = "SELECT samlSpSession FROM SamlSpSession samlSpSession";
	private static final String _SQL_SELECT_SAMLSPSESSION_WHERE = "SELECT samlSpSession FROM SamlSpSession samlSpSession WHERE ";
	private static final String _SQL_COUNT_SAMLSPSESSION = "SELECT COUNT(samlSpSession) FROM SamlSpSession samlSpSession";
	private static final String _SQL_COUNT_SAMLSPSESSION_WHERE = "SELECT COUNT(samlSpSession) FROM SamlSpSession samlSpSession WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "samlSpSession.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SamlSpSession exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SamlSpSession exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SamlSpSessionPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"terminated"
			});
	private static SamlSpSession _nullSamlSpSession = new SamlSpSessionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SamlSpSession> toCacheModel() {
				return _nullSamlSpSessionCacheModel;
			}
		};

	private static CacheModel<SamlSpSession> _nullSamlSpSessionCacheModel = new CacheModel<SamlSpSession>() {
			@Override
			public SamlSpSession toEntityModel() {
				return _nullSamlSpSession;
			}
		};
}