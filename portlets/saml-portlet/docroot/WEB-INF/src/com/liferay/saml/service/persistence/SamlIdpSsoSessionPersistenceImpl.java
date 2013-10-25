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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.saml.NoSuchIdpSsoSessionException;
import com.liferay.saml.model.SamlIdpSsoSession;
import com.liferay.saml.model.impl.SamlIdpSsoSessionImpl;
import com.liferay.saml.model.impl.SamlIdpSsoSessionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the saml idp sso session service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlIdpSsoSessionPersistence
 * @see SamlIdpSsoSessionUtil
 * @generated
 */
public class SamlIdpSsoSessionPersistenceImpl extends BasePersistenceImpl<SamlIdpSsoSession>
	implements SamlIdpSsoSessionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SamlIdpSsoSessionUtil} to access the saml idp sso session persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SamlIdpSsoSessionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSsoSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlIdpSsoSessionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSsoSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlIdpSsoSessionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSsoSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY = new FinderPath(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSsoSessionModelImpl.FINDER_CACHE_ENABLED,
			SamlIdpSsoSessionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySamlIdpSsoSessionKey",
			new String[] { String.class.getName() },
			SamlIdpSsoSessionModelImpl.SAMLIDPSSOSESSIONKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONKEY = new FinderPath(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSsoSessionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countBySamlIdpSsoSessionKey",
			new String[] { String.class.getName() });

	/**
	 * Returns the saml idp sso session where samlIdpSsoSessionKey = &#63; or throws a {@link com.liferay.saml.NoSuchIdpSsoSessionException} if it could not be found.
	 *
	 * @param samlIdpSsoSessionKey the saml idp sso session key
	 * @return the matching saml idp sso session
	 * @throws com.liferay.saml.NoSuchIdpSsoSessionException if a matching saml idp sso session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession findBySamlIdpSsoSessionKey(
		String samlIdpSsoSessionKey)
		throws NoSuchIdpSsoSessionException, SystemException {
		SamlIdpSsoSession samlIdpSsoSession = fetchBySamlIdpSsoSessionKey(samlIdpSsoSessionKey);

		if (samlIdpSsoSession == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("samlIdpSsoSessionKey=");
			msg.append(samlIdpSsoSessionKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchIdpSsoSessionException(msg.toString());
		}

		return samlIdpSsoSession;
	}

	/**
	 * Returns the saml idp sso session where samlIdpSsoSessionKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param samlIdpSsoSessionKey the saml idp sso session key
	 * @return the matching saml idp sso session, or <code>null</code> if a matching saml idp sso session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession fetchBySamlIdpSsoSessionKey(
		String samlIdpSsoSessionKey) throws SystemException {
		return fetchBySamlIdpSsoSessionKey(samlIdpSsoSessionKey, true);
	}

	/**
	 * Returns the saml idp sso session where samlIdpSsoSessionKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param samlIdpSsoSessionKey the saml idp sso session key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching saml idp sso session, or <code>null</code> if a matching saml idp sso session could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession fetchBySamlIdpSsoSessionKey(
		String samlIdpSsoSessionKey, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { samlIdpSsoSessionKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY,
					finderArgs, this);
		}

		if (result instanceof SamlIdpSsoSession) {
			SamlIdpSsoSession samlIdpSsoSession = (SamlIdpSsoSession)result;

			if (!Validator.equals(samlIdpSsoSessionKey,
						samlIdpSsoSession.getSamlIdpSsoSessionKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SAMLIDPSSOSESSION_WHERE);

			boolean bindSamlIdpSsoSessionKey = false;

			if (samlIdpSsoSessionKey == null) {
				query.append(_FINDER_COLUMN_SAMLIDPSSOSESSIONKEY_SAMLIDPSSOSESSIONKEY_1);
			}
			else if (samlIdpSsoSessionKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SAMLIDPSSOSESSIONKEY_SAMLIDPSSOSESSIONKEY_3);
			}
			else {
				bindSamlIdpSsoSessionKey = true;

				query.append(_FINDER_COLUMN_SAMLIDPSSOSESSIONKEY_SAMLIDPSSOSESSIONKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSamlIdpSsoSessionKey) {
					qPos.add(samlIdpSsoSessionKey);
				}

				List<SamlIdpSsoSession> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SamlIdpSsoSessionPersistenceImpl.fetchBySamlIdpSsoSessionKey(String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SamlIdpSsoSession samlIdpSsoSession = list.get(0);

					result = samlIdpSsoSession;

					cacheResult(samlIdpSsoSession);

					if ((samlIdpSsoSession.getSamlIdpSsoSessionKey() == null) ||
							!samlIdpSsoSession.getSamlIdpSsoSessionKey()
												  .equals(samlIdpSsoSessionKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY,
							finderArgs, samlIdpSsoSession);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY,
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
			return (SamlIdpSsoSession)result;
		}
	}

	/**
	 * Removes the saml idp sso session where samlIdpSsoSessionKey = &#63; from the database.
	 *
	 * @param samlIdpSsoSessionKey the saml idp sso session key
	 * @return the saml idp sso session that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession removeBySamlIdpSsoSessionKey(
		String samlIdpSsoSessionKey)
		throws NoSuchIdpSsoSessionException, SystemException {
		SamlIdpSsoSession samlIdpSsoSession = findBySamlIdpSsoSessionKey(samlIdpSsoSessionKey);

		return remove(samlIdpSsoSession);
	}

	/**
	 * Returns the number of saml idp sso sessions where samlIdpSsoSessionKey = &#63;.
	 *
	 * @param samlIdpSsoSessionKey the saml idp sso session key
	 * @return the number of matching saml idp sso sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySamlIdpSsoSessionKey(String samlIdpSsoSessionKey)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONKEY;

		Object[] finderArgs = new Object[] { samlIdpSsoSessionKey };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SAMLIDPSSOSESSION_WHERE);

			boolean bindSamlIdpSsoSessionKey = false;

			if (samlIdpSsoSessionKey == null) {
				query.append(_FINDER_COLUMN_SAMLIDPSSOSESSIONKEY_SAMLIDPSSOSESSIONKEY_1);
			}
			else if (samlIdpSsoSessionKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SAMLIDPSSOSESSIONKEY_SAMLIDPSSOSESSIONKEY_3);
			}
			else {
				bindSamlIdpSsoSessionKey = true;

				query.append(_FINDER_COLUMN_SAMLIDPSSOSESSIONKEY_SAMLIDPSSOSESSIONKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSamlIdpSsoSessionKey) {
					qPos.add(samlIdpSsoSessionKey);
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

	private static final String _FINDER_COLUMN_SAMLIDPSSOSESSIONKEY_SAMLIDPSSOSESSIONKEY_1 =
		"samlIdpSsoSession.samlIdpSsoSessionKey IS NULL";
	private static final String _FINDER_COLUMN_SAMLIDPSSOSESSIONKEY_SAMLIDPSSOSESSIONKEY_2 =
		"samlIdpSsoSession.samlIdpSsoSessionKey = ?";
	private static final String _FINDER_COLUMN_SAMLIDPSSOSESSIONKEY_SAMLIDPSSOSESSIONKEY_3 =
		"(samlIdpSsoSession.samlIdpSsoSessionKey IS NULL OR samlIdpSsoSession.samlIdpSsoSessionKey = '')";

	public SamlIdpSsoSessionPersistenceImpl() {
		setModelClass(SamlIdpSsoSession.class);
	}

	/**
	 * Caches the saml idp sso session in the entity cache if it is enabled.
	 *
	 * @param samlIdpSsoSession the saml idp sso session
	 */
	@Override
	public void cacheResult(SamlIdpSsoSession samlIdpSsoSession) {
		EntityCacheUtil.putResult(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSsoSessionImpl.class, samlIdpSsoSession.getPrimaryKey(),
			samlIdpSsoSession);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY,
			new Object[] { samlIdpSsoSession.getSamlIdpSsoSessionKey() },
			samlIdpSsoSession);

		samlIdpSsoSession.resetOriginalValues();
	}

	/**
	 * Caches the saml idp sso sessions in the entity cache if it is enabled.
	 *
	 * @param samlIdpSsoSessions the saml idp sso sessions
	 */
	@Override
	public void cacheResult(List<SamlIdpSsoSession> samlIdpSsoSessions) {
		for (SamlIdpSsoSession samlIdpSsoSession : samlIdpSsoSessions) {
			if (EntityCacheUtil.getResult(
						SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
						SamlIdpSsoSessionImpl.class,
						samlIdpSsoSession.getPrimaryKey()) == null) {
				cacheResult(samlIdpSsoSession);
			}
			else {
				samlIdpSsoSession.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all saml idp sso sessions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SamlIdpSsoSessionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SamlIdpSsoSessionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the saml idp sso session.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SamlIdpSsoSession samlIdpSsoSession) {
		EntityCacheUtil.removeResult(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSsoSessionImpl.class, samlIdpSsoSession.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(samlIdpSsoSession);
	}

	@Override
	public void clearCache(List<SamlIdpSsoSession> samlIdpSsoSessions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SamlIdpSsoSession samlIdpSsoSession : samlIdpSsoSessions) {
			EntityCacheUtil.removeResult(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
				SamlIdpSsoSessionImpl.class, samlIdpSsoSession.getPrimaryKey());

			clearUniqueFindersCache(samlIdpSsoSession);
		}
	}

	protected void cacheUniqueFindersCache(SamlIdpSsoSession samlIdpSsoSession) {
		if (samlIdpSsoSession.isNew()) {
			Object[] args = new Object[] {
					samlIdpSsoSession.getSamlIdpSsoSessionKey()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONKEY,
				args, Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY,
				args, samlIdpSsoSession);
		}
		else {
			SamlIdpSsoSessionModelImpl samlIdpSsoSessionModelImpl = (SamlIdpSsoSessionModelImpl)samlIdpSsoSession;

			if ((samlIdpSsoSessionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						samlIdpSsoSession.getSamlIdpSsoSessionKey()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONKEY,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY,
					args, samlIdpSsoSession);
			}
		}
	}

	protected void clearUniqueFindersCache(SamlIdpSsoSession samlIdpSsoSession) {
		SamlIdpSsoSessionModelImpl samlIdpSsoSessionModelImpl = (SamlIdpSsoSessionModelImpl)samlIdpSsoSession;

		Object[] args = new Object[] { samlIdpSsoSession.getSamlIdpSsoSessionKey() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONKEY,
			args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY,
			args);

		if ((samlIdpSsoSessionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					samlIdpSsoSessionModelImpl.getOriginalSamlIdpSsoSessionKey()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SAMLIDPSSOSESSIONKEY,
				args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SAMLIDPSSOSESSIONKEY,
				args);
		}
	}

	/**
	 * Creates a new saml idp sso session with the primary key. Does not add the saml idp sso session to the database.
	 *
	 * @param samlIdpSsoSessionId the primary key for the new saml idp sso session
	 * @return the new saml idp sso session
	 */
	@Override
	public SamlIdpSsoSession create(long samlIdpSsoSessionId) {
		SamlIdpSsoSession samlIdpSsoSession = new SamlIdpSsoSessionImpl();

		samlIdpSsoSession.setNew(true);
		samlIdpSsoSession.setPrimaryKey(samlIdpSsoSessionId);

		return samlIdpSsoSession;
	}

	/**
	 * Removes the saml idp sso session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param samlIdpSsoSessionId the primary key of the saml idp sso session
	 * @return the saml idp sso session that was removed
	 * @throws com.liferay.saml.NoSuchIdpSsoSessionException if a saml idp sso session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession remove(long samlIdpSsoSessionId)
		throws NoSuchIdpSsoSessionException, SystemException {
		return remove((Serializable)samlIdpSsoSessionId);
	}

	/**
	 * Removes the saml idp sso session with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the saml idp sso session
	 * @return the saml idp sso session that was removed
	 * @throws com.liferay.saml.NoSuchIdpSsoSessionException if a saml idp sso session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession remove(Serializable primaryKey)
		throws NoSuchIdpSsoSessionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SamlIdpSsoSession samlIdpSsoSession = (SamlIdpSsoSession)session.get(SamlIdpSsoSessionImpl.class,
					primaryKey);

			if (samlIdpSsoSession == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIdpSsoSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(samlIdpSsoSession);
		}
		catch (NoSuchIdpSsoSessionException nsee) {
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
	protected SamlIdpSsoSession removeImpl(SamlIdpSsoSession samlIdpSsoSession)
		throws SystemException {
		samlIdpSsoSession = toUnwrappedModel(samlIdpSsoSession);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(samlIdpSsoSession)) {
				samlIdpSsoSession = (SamlIdpSsoSession)session.get(SamlIdpSsoSessionImpl.class,
						samlIdpSsoSession.getPrimaryKeyObj());
			}

			if (samlIdpSsoSession != null) {
				session.delete(samlIdpSsoSession);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (samlIdpSsoSession != null) {
			clearCache(samlIdpSsoSession);
		}

		return samlIdpSsoSession;
	}

	@Override
	public SamlIdpSsoSession updateImpl(
		com.liferay.saml.model.SamlIdpSsoSession samlIdpSsoSession)
		throws SystemException {
		samlIdpSsoSession = toUnwrappedModel(samlIdpSsoSession);

		boolean isNew = samlIdpSsoSession.isNew();

		Session session = null;

		try {
			session = openSession();

			if (samlIdpSsoSession.isNew()) {
				session.save(samlIdpSsoSession);

				samlIdpSsoSession.setNew(false);
			}
			else {
				session.merge(samlIdpSsoSession);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SamlIdpSsoSessionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
			SamlIdpSsoSessionImpl.class, samlIdpSsoSession.getPrimaryKey(),
			samlIdpSsoSession);

		clearUniqueFindersCache(samlIdpSsoSession);
		cacheUniqueFindersCache(samlIdpSsoSession);

		return samlIdpSsoSession;
	}

	protected SamlIdpSsoSession toUnwrappedModel(
		SamlIdpSsoSession samlIdpSsoSession) {
		if (samlIdpSsoSession instanceof SamlIdpSsoSessionImpl) {
			return samlIdpSsoSession;
		}

		SamlIdpSsoSessionImpl samlIdpSsoSessionImpl = new SamlIdpSsoSessionImpl();

		samlIdpSsoSessionImpl.setNew(samlIdpSsoSession.isNew());
		samlIdpSsoSessionImpl.setPrimaryKey(samlIdpSsoSession.getPrimaryKey());

		samlIdpSsoSessionImpl.setSamlIdpSsoSessionId(samlIdpSsoSession.getSamlIdpSsoSessionId());
		samlIdpSsoSessionImpl.setCompanyId(samlIdpSsoSession.getCompanyId());
		samlIdpSsoSessionImpl.setUserId(samlIdpSsoSession.getUserId());
		samlIdpSsoSessionImpl.setUserName(samlIdpSsoSession.getUserName());
		samlIdpSsoSessionImpl.setCreateDate(samlIdpSsoSession.getCreateDate());
		samlIdpSsoSessionImpl.setModifiedDate(samlIdpSsoSession.getModifiedDate());
		samlIdpSsoSessionImpl.setSamlIdpSsoSessionKey(samlIdpSsoSession.getSamlIdpSsoSessionKey());

		return samlIdpSsoSessionImpl;
	}

	/**
	 * Returns the saml idp sso session with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml idp sso session
	 * @return the saml idp sso session
	 * @throws com.liferay.saml.NoSuchIdpSsoSessionException if a saml idp sso session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIdpSsoSessionException, SystemException {
		SamlIdpSsoSession samlIdpSsoSession = fetchByPrimaryKey(primaryKey);

		if (samlIdpSsoSession == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIdpSsoSessionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return samlIdpSsoSession;
	}

	/**
	 * Returns the saml idp sso session with the primary key or throws a {@link com.liferay.saml.NoSuchIdpSsoSessionException} if it could not be found.
	 *
	 * @param samlIdpSsoSessionId the primary key of the saml idp sso session
	 * @return the saml idp sso session
	 * @throws com.liferay.saml.NoSuchIdpSsoSessionException if a saml idp sso session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession findByPrimaryKey(long samlIdpSsoSessionId)
		throws NoSuchIdpSsoSessionException, SystemException {
		return findByPrimaryKey((Serializable)samlIdpSsoSessionId);
	}

	/**
	 * Returns the saml idp sso session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml idp sso session
	 * @return the saml idp sso session, or <code>null</code> if a saml idp sso session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SamlIdpSsoSession samlIdpSsoSession = (SamlIdpSsoSession)EntityCacheUtil.getResult(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
				SamlIdpSsoSessionImpl.class, primaryKey);

		if (samlIdpSsoSession == _nullSamlIdpSsoSession) {
			return null;
		}

		if (samlIdpSsoSession == null) {
			Session session = null;

			try {
				session = openSession();

				samlIdpSsoSession = (SamlIdpSsoSession)session.get(SamlIdpSsoSessionImpl.class,
						primaryKey);

				if (samlIdpSsoSession != null) {
					cacheResult(samlIdpSsoSession);
				}
				else {
					EntityCacheUtil.putResult(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
						SamlIdpSsoSessionImpl.class, primaryKey,
						_nullSamlIdpSsoSession);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SamlIdpSsoSessionModelImpl.ENTITY_CACHE_ENABLED,
					SamlIdpSsoSessionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return samlIdpSsoSession;
	}

	/**
	 * Returns the saml idp sso session with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samlIdpSsoSessionId the primary key of the saml idp sso session
	 * @return the saml idp sso session, or <code>null</code> if a saml idp sso session with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlIdpSsoSession fetchByPrimaryKey(long samlIdpSsoSessionId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)samlIdpSsoSessionId);
	}

	/**
	 * Returns all the saml idp sso sessions.
	 *
	 * @return the saml idp sso sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlIdpSsoSession> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml idp sso sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSsoSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml idp sso sessions
	 * @param end the upper bound of the range of saml idp sso sessions (not inclusive)
	 * @return the range of saml idp sso sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlIdpSsoSession> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml idp sso sessions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSsoSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml idp sso sessions
	 * @param end the upper bound of the range of saml idp sso sessions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of saml idp sso sessions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlIdpSsoSession> findAll(int start, int end,
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

		List<SamlIdpSsoSession> list = (List<SamlIdpSsoSession>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SAMLIDPSSOSESSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SAMLIDPSSOSESSION;

				if (pagination) {
					sql = sql.concat(SamlIdpSsoSessionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SamlIdpSsoSession>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SamlIdpSsoSession>(list);
				}
				else {
					list = (List<SamlIdpSsoSession>)QueryUtil.list(q,
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
	 * Removes all the saml idp sso sessions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SamlIdpSsoSession samlIdpSsoSession : findAll()) {
			remove(samlIdpSsoSession);
		}
	}

	/**
	 * Returns the number of saml idp sso sessions.
	 *
	 * @return the number of saml idp sso sessions
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

				Query q = session.createQuery(_SQL_COUNT_SAMLIDPSSOSESSION);

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
	 * Initializes the saml idp sso session persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.saml.model.SamlIdpSsoSession")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SamlIdpSsoSession>> listenersList = new ArrayList<ModelListener<SamlIdpSsoSession>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SamlIdpSsoSession>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SamlIdpSsoSessionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SAMLIDPSSOSESSION = "SELECT samlIdpSsoSession FROM SamlIdpSsoSession samlIdpSsoSession";
	private static final String _SQL_SELECT_SAMLIDPSSOSESSION_WHERE = "SELECT samlIdpSsoSession FROM SamlIdpSsoSession samlIdpSsoSession WHERE ";
	private static final String _SQL_COUNT_SAMLIDPSSOSESSION = "SELECT COUNT(samlIdpSsoSession) FROM SamlIdpSsoSession samlIdpSsoSession";
	private static final String _SQL_COUNT_SAMLIDPSSOSESSION_WHERE = "SELECT COUNT(samlIdpSsoSession) FROM SamlIdpSsoSession samlIdpSsoSession WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "samlIdpSsoSession.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SamlIdpSsoSession exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SamlIdpSsoSession exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SamlIdpSsoSessionPersistenceImpl.class);
	private static SamlIdpSsoSession _nullSamlIdpSsoSession = new SamlIdpSsoSessionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SamlIdpSsoSession> toCacheModel() {
				return _nullSamlIdpSsoSessionCacheModel;
			}
		};

	private static CacheModel<SamlIdpSsoSession> _nullSamlIdpSsoSessionCacheModel =
		new CacheModel<SamlIdpSsoSession>() {
			@Override
			public SamlIdpSsoSession toEntityModel() {
				return _nullSamlIdpSsoSession;
			}
		};
}