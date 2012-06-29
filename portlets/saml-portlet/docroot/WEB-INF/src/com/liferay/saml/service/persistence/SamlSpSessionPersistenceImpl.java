/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.saml.NoSuchSpSessionException;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.model.impl.SamlSpSessionImpl;
import com.liferay.saml.model.impl.SamlSpSessionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAMEIDVALUE =
		new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlSpSessionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByNameIdValue",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
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
	public static final FinderPath FINDER_PATH_FETCH_BY_JSESSIONID = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlSpSessionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByJSessionId", new String[] { String.class.getName() },
			SamlSpSessionModelImpl.JSESSIONID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_JSESSIONID = new FinderPath(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByJSessionId",
			new String[] { String.class.getName() });
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

	/**
	 * Caches the saml sp session in the entity cache if it is enabled.
	 *
	 * @param samlSpSession the saml sp session
	 */
	public void cacheResult(SamlSpSession samlSpSession) {
		EntityCacheUtil.putResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpSessionImpl.class, samlSpSession.getPrimaryKey(),
			samlSpSession);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID,
			new Object[] { samlSpSession.getJSessionId() }, samlSpSession);

		samlSpSession.resetOriginalValues();
	}

	/**
	 * Caches the saml sp sessions in the entity cache if it is enabled.
	 *
	 * @param samlSpSessions the saml sp sessions
	 */
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

	protected void clearUniqueFindersCache(SamlSpSession samlSpSession) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_JSESSIONID,
			new Object[] { samlSpSession.getJSessionId() });
	}

	/**
	 * Creates a new saml sp session with the primary key. Does not add the saml sp session to the database.
	 *
	 * @param samlSpSessionId the primary key for the new saml sp session
	 * @return the new saml sp session
	 */
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
	public SamlSpSession remove(long samlSpSessionId)
		throws NoSuchSpSessionException, SystemException {
		return remove(Long.valueOf(samlSpSessionId));
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

			BatchSessionUtil.delete(session, samlSpSession);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(samlSpSession);

		return samlSpSession;
	}

	@Override
	public SamlSpSession updateImpl(
		com.liferay.saml.model.SamlSpSession samlSpSession, boolean merge)
		throws SystemException {
		samlSpSession = toUnwrappedModel(samlSpSession);

		boolean isNew = samlSpSession.isNew();

		SamlSpSessionModelImpl samlSpSessionModelImpl = (SamlSpSessionModelImpl)samlSpSession;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, samlSpSession, merge);

			samlSpSession.setNew(false);
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

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID,
				new Object[] { samlSpSession.getJSessionId() }, samlSpSession);
		}
		else {
			if ((samlSpSessionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_JSESSIONID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						samlSpSessionModelImpl.getOriginalJSessionId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_JSESSIONID,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_JSESSIONID,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID,
					new Object[] { samlSpSession.getJSessionId() },
					samlSpSession);
			}
		}

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
		samlSpSessionImpl.setJSessionId(samlSpSession.getJSessionId());
		samlSpSessionImpl.setNameIdFormat(samlSpSession.getNameIdFormat());
		samlSpSessionImpl.setNameIdValue(samlSpSession.getNameIdValue());
		samlSpSessionImpl.setTerminated(samlSpSession.isTerminated());

		return samlSpSessionImpl;
	}

	/**
	 * Returns the saml sp session with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml sp session
	 * @return the saml sp session
	 * @throws com.liferay.portal.NoSuchModelException if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the saml sp session with the primary key or throws a {@link com.liferay.saml.NoSuchSpSessionException} if it could not be found.
	 *
	 * @param samlSpSessionId the primary key of the saml sp session
	 * @return the saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SamlSpSession findByPrimaryKey(long samlSpSessionId)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = fetchByPrimaryKey(samlSpSessionId);

		if (samlSpSession == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + samlSpSessionId);
			}

			throw new NoSuchSpSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				samlSpSessionId);
		}

		return samlSpSession;
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
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the saml sp session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samlSpSessionId the primary key of the saml sp session
	 * @return the saml sp session, or <code>null</code> if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SamlSpSession fetchByPrimaryKey(long samlSpSessionId)
		throws SystemException {
		SamlSpSession samlSpSession = (SamlSpSession)EntityCacheUtil.getResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
				SamlSpSessionImpl.class, samlSpSessionId);

		if (samlSpSession == _nullSamlSpSession) {
			return null;
		}

		if (samlSpSession == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				samlSpSession = (SamlSpSession)session.get(SamlSpSessionImpl.class,
						Long.valueOf(samlSpSessionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (samlSpSession != null) {
					cacheResult(samlSpSession);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SamlSpSessionModelImpl.ENTITY_CACHE_ENABLED,
						SamlSpSessionImpl.class, samlSpSessionId,
						_nullSamlSpSession);
				}

				closeSession(session);
			}
		}

		return samlSpSession;
	}

	/**
	 * Returns all the saml sp sessions where nameIdValue = &#63;.
	 *
	 * @param nameIdValue the name ID value
	 * @return the matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SamlSpSession> findByNameIdValue(String nameIdValue)
		throws SystemException {
		return findByNameIdValue(nameIdValue, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml sp sessions where nameIdValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param nameIdValue the name ID value
	 * @param start the lower bound of the range of saml sp sessions
	 * @param end the upper bound of the range of saml sp sessions (not inclusive)
	 * @return the range of matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SamlSpSession> findByNameIdValue(String nameIdValue, int start,
		int end) throws SystemException {
		return findByNameIdValue(nameIdValue, start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml sp sessions where nameIdValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param nameIdValue the name ID value
	 * @param start the lower bound of the range of saml sp sessions
	 * @param end the upper bound of the range of saml sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SamlSpSession> findByNameIdValue(String nameIdValue, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_SAMLSPSESSION_WHERE);

			if (nameIdValue == null) {
				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_1);
			}
			else {
				if (nameIdValue.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (nameIdValue != null) {
					qPos.add(nameIdValue);
				}

				list = (List<SamlSpSession>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first saml sp session in the ordered set where nameIdValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param nameIdValue the name ID value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SamlSpSession findByNameIdValue_First(String nameIdValue,
		OrderByComparator orderByComparator)
		throws NoSuchSpSessionException, SystemException {
		List<SamlSpSession> list = findByNameIdValue(nameIdValue, 0, 1,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("nameIdValue=");
			msg.append(nameIdValue);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSpSessionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last saml sp session in the ordered set where nameIdValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param nameIdValue the name ID value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SamlSpSession findByNameIdValue_Last(String nameIdValue,
		OrderByComparator orderByComparator)
		throws NoSuchSpSessionException, SystemException {
		int count = countByNameIdValue(nameIdValue);

		List<SamlSpSession> list = findByNameIdValue(nameIdValue, count - 1,
				count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("nameIdValue=");
			msg.append(nameIdValue);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchSpSessionException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	/**
	 * Returns the saml sp sessions before and after the current saml sp session in the ordered set where nameIdValue = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param samlSpSessionId the primary key of the current saml sp session
	 * @param nameIdValue the name ID value
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a saml sp session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
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

		if (nameIdValue == null) {
			query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_1);
		}
		else {
			if (nameIdValue.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_3);
			}
			else {
				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_2);
			}
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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (nameIdValue != null) {
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
	 * Returns the saml sp session where jSessionId = &#63; or throws a {@link com.liferay.saml.NoSuchSpSessionException} if it could not be found.
	 *
	 * @param jSessionId the j session ID
	 * @return the matching saml sp session
	 * @throws com.liferay.saml.NoSuchSpSessionException if a matching saml sp session could not be found
	 * @throws SystemException if a system exception occurred
	 */
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
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_SAMLSPSESSION_WHERE);

			if (jSessionId == null) {
				query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_1);
			}
			else {
				if (jSessionId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jSessionId != null) {
					qPos.add(jSessionId);
				}

				List<SamlSpSession> list = q.list();

				result = list;

				SamlSpSession samlSpSession = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID,
						finderArgs, list);
				}
				else {
					samlSpSession = list.get(0);

					cacheResult(samlSpSession);

					if ((samlSpSession.getJSessionId() == null) ||
							!samlSpSession.getJSessionId().equals(jSessionId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_JSESSIONID,
							finderArgs, samlSpSession);
					}
				}

				return samlSpSession;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_JSESSIONID,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (SamlSpSession)result;
			}
		}
	}

	/**
	 * Returns all the saml sp sessions.
	 *
	 * @return the saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SamlSpSession> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml sp sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp sessions
	 * @param end the upper bound of the range of saml sp sessions (not inclusive)
	 * @return the range of saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SamlSpSession> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml sp sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp sessions
	 * @param end the upper bound of the range of saml sp sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	public List<SamlSpSession> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
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
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SamlSpSession>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SamlSpSession>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the saml sp sessions where nameIdValue = &#63; from the database.
	 *
	 * @param nameIdValue the name ID value
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByNameIdValue(String nameIdValue)
		throws SystemException {
		for (SamlSpSession samlSpSession : findByNameIdValue(nameIdValue)) {
			remove(samlSpSession);
		}
	}

	/**
	 * Removes the saml sp session where jSessionId = &#63; from the database.
	 *
	 * @param jSessionId the j session ID
	 * @return the saml sp session that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public SamlSpSession removeByJSessionId(String jSessionId)
		throws NoSuchSpSessionException, SystemException {
		SamlSpSession samlSpSession = findByJSessionId(jSessionId);

		return remove(samlSpSession);
	}

	/**
	 * Removes all the saml sp sessions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SamlSpSession samlSpSession : findAll()) {
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
	public int countByNameIdValue(String nameIdValue) throws SystemException {
		Object[] finderArgs = new Object[] { nameIdValue };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_NAMEIDVALUE,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMLSPSESSION_WHERE);

			if (nameIdValue == null) {
				query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_1);
			}
			else {
				if (nameIdValue.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_3);
				}
				else {
					query.append(_FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (nameIdValue != null) {
					qPos.add(nameIdValue);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_NAMEIDVALUE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of saml sp sessions where jSessionId = &#63;.
	 *
	 * @param jSessionId the j session ID
	 * @return the number of matching saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByJSessionId(String jSessionId) throws SystemException {
		Object[] finderArgs = new Object[] { jSessionId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_JSESSIONID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMLSPSESSION_WHERE);

			if (jSessionId == null) {
				query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_1);
			}
			else {
				if (jSessionId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_JSESSIONID_JSESSIONID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (jSessionId != null) {
					qPos.add(jSessionId);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_JSESSIONID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of saml sp sessions.
	 *
	 * @return the number of saml sp sessions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SAMLSPSESSION);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
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
							listenerClassName));
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
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = SamlIdpSpSessionPersistence.class)
	protected SamlIdpSpSessionPersistence samlIdpSpSessionPersistence;
	@BeanReference(type = SamlIdpSsoSessionPersistence.class)
	protected SamlIdpSsoSessionPersistence samlIdpSsoSessionPersistence;
	@BeanReference(type = SamlSpAuthRequestPersistence.class)
	protected SamlSpAuthRequestPersistence samlSpAuthRequestPersistence;
	@BeanReference(type = SamlSpMessagePersistence.class)
	protected SamlSpMessagePersistence samlSpMessagePersistence;
	@BeanReference(type = SamlSpSessionPersistence.class)
	protected SamlSpSessionPersistence samlSpSessionPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SAMLSPSESSION = "SELECT samlSpSession FROM SamlSpSession samlSpSession";
	private static final String _SQL_SELECT_SAMLSPSESSION_WHERE = "SELECT samlSpSession FROM SamlSpSession samlSpSession WHERE ";
	private static final String _SQL_COUNT_SAMLSPSESSION = "SELECT COUNT(samlSpSession) FROM SamlSpSession samlSpSession";
	private static final String _SQL_COUNT_SAMLSPSESSION_WHERE = "SELECT COUNT(samlSpSession) FROM SamlSpSession samlSpSession WHERE ";
	private static final String _FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_1 = "samlSpSession.nameIdValue IS NULL";
	private static final String _FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_2 = "samlSpSession.nameIdValue = ?";
	private static final String _FINDER_COLUMN_NAMEIDVALUE_NAMEIDVALUE_3 = "(samlSpSession.nameIdValue IS NULL OR samlSpSession.nameIdValue = ?)";
	private static final String _FINDER_COLUMN_JSESSIONID_JSESSIONID_1 = "samlSpSession.jSessionId IS NULL";
	private static final String _FINDER_COLUMN_JSESSIONID_JSESSIONID_2 = "samlSpSession.jSessionId = ?";
	private static final String _FINDER_COLUMN_JSESSIONID_JSESSIONID_3 = "(samlSpSession.jSessionId IS NULL OR samlSpSession.jSessionId = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "samlSpSession.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SamlSpSession exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SamlSpSession exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SamlSpSessionPersistenceImpl.class);
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
			public SamlSpSession toEntityModel() {
				return _nullSamlSpSession;
			}
		};
}