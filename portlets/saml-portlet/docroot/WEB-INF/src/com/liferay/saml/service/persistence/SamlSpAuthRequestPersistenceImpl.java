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

import com.liferay.saml.NoSuchSpAuthRequestException;
import com.liferay.saml.model.SamlSpAuthRequest;
import com.liferay.saml.model.impl.SamlSpAuthRequestImpl;
import com.liferay.saml.model.impl.SamlSpAuthRequestModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the saml sp auth request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpAuthRequestPersistence
 * @see SamlSpAuthRequestUtil
 * @generated
 */
public class SamlSpAuthRequestPersistenceImpl extends BasePersistenceImpl<SamlSpAuthRequest>
	implements SamlSpAuthRequestPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SamlSpAuthRequestUtil} to access the saml sp auth request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SamlSpAuthRequestImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpAuthRequestModelImpl.FINDER_CACHE_ENABLED,
			SamlSpAuthRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpAuthRequestModelImpl.FINDER_CACHE_ENABLED,
			SamlSpAuthRequestImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpAuthRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_SIEI_SSARK = new FinderPath(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpAuthRequestModelImpl.FINDER_CACHE_ENABLED,
			SamlSpAuthRequestImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySIEI_SSARK",
			new String[] { String.class.getName(), String.class.getName() },
			SamlSpAuthRequestModelImpl.SAMLIDPENTITYID_COLUMN_BITMASK |
			SamlSpAuthRequestModelImpl.SAMLSPAUTHREQUESTKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIEI_SSARK = new FinderPath(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpAuthRequestModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySIEI_SSARK",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the saml sp auth request where samlIdpEntityId = &#63; and samlSpAuthRequestKey = &#63; or throws a {@link com.liferay.saml.NoSuchSpAuthRequestException} if it could not be found.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlSpAuthRequestKey the saml sp auth request key
	 * @return the matching saml sp auth request
	 * @throws com.liferay.saml.NoSuchSpAuthRequestException if a matching saml sp auth request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest findBySIEI_SSARK(String samlIdpEntityId,
		String samlSpAuthRequestKey)
		throws NoSuchSpAuthRequestException, SystemException {
		SamlSpAuthRequest samlSpAuthRequest = fetchBySIEI_SSARK(samlIdpEntityId,
				samlSpAuthRequestKey);

		if (samlSpAuthRequest == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("samlIdpEntityId=");
			msg.append(samlIdpEntityId);

			msg.append(", samlSpAuthRequestKey=");
			msg.append(samlSpAuthRequestKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSpAuthRequestException(msg.toString());
		}

		return samlSpAuthRequest;
	}

	/**
	 * Returns the saml sp auth request where samlIdpEntityId = &#63; and samlSpAuthRequestKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlSpAuthRequestKey the saml sp auth request key
	 * @return the matching saml sp auth request, or <code>null</code> if a matching saml sp auth request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest fetchBySIEI_SSARK(String samlIdpEntityId,
		String samlSpAuthRequestKey) throws SystemException {
		return fetchBySIEI_SSARK(samlIdpEntityId, samlSpAuthRequestKey, true);
	}

	/**
	 * Returns the saml sp auth request where samlIdpEntityId = &#63; and samlSpAuthRequestKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlSpAuthRequestKey the saml sp auth request key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching saml sp auth request, or <code>null</code> if a matching saml sp auth request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest fetchBySIEI_SSARK(String samlIdpEntityId,
		String samlSpAuthRequestKey, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { samlIdpEntityId, samlSpAuthRequestKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SIEI_SSARK,
					finderArgs, this);
		}

		if (result instanceof SamlSpAuthRequest) {
			SamlSpAuthRequest samlSpAuthRequest = (SamlSpAuthRequest)result;

			if (!Validator.equals(samlIdpEntityId,
						samlSpAuthRequest.getSamlIdpEntityId()) ||
					!Validator.equals(samlSpAuthRequestKey,
						samlSpAuthRequest.getSamlSpAuthRequestKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SAMLSPAUTHREQUEST_WHERE);

			boolean bindSamlIdpEntityId = false;

			if (samlIdpEntityId == null) {
				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLIDPENTITYID_1);
			}
			else if (samlIdpEntityId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLIDPENTITYID_3);
			}
			else {
				bindSamlIdpEntityId = true;

				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLIDPENTITYID_2);
			}

			boolean bindSamlSpAuthRequestKey = false;

			if (samlSpAuthRequestKey == null) {
				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLSPAUTHREQUESTKEY_1);
			}
			else if (samlSpAuthRequestKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLSPAUTHREQUESTKEY_3);
			}
			else {
				bindSamlSpAuthRequestKey = true;

				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLSPAUTHREQUESTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSamlIdpEntityId) {
					qPos.add(samlIdpEntityId);
				}

				if (bindSamlSpAuthRequestKey) {
					qPos.add(samlSpAuthRequestKey);
				}

				List<SamlSpAuthRequest> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SSARK,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SamlSpAuthRequestPersistenceImpl.fetchBySIEI_SSARK(String, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SamlSpAuthRequest samlSpAuthRequest = list.get(0);

					result = samlSpAuthRequest;

					cacheResult(samlSpAuthRequest);

					if ((samlSpAuthRequest.getSamlIdpEntityId() == null) ||
							!samlSpAuthRequest.getSamlIdpEntityId()
												  .equals(samlIdpEntityId) ||
							(samlSpAuthRequest.getSamlSpAuthRequestKey() == null) ||
							!samlSpAuthRequest.getSamlSpAuthRequestKey()
												  .equals(samlSpAuthRequestKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SSARK,
							finderArgs, samlSpAuthRequest);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIEI_SSARK,
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
			return (SamlSpAuthRequest)result;
		}
	}

	/**
	 * Removes the saml sp auth request where samlIdpEntityId = &#63; and samlSpAuthRequestKey = &#63; from the database.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlSpAuthRequestKey the saml sp auth request key
	 * @return the saml sp auth request that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest removeBySIEI_SSARK(String samlIdpEntityId,
		String samlSpAuthRequestKey)
		throws NoSuchSpAuthRequestException, SystemException {
		SamlSpAuthRequest samlSpAuthRequest = findBySIEI_SSARK(samlIdpEntityId,
				samlSpAuthRequestKey);

		return remove(samlSpAuthRequest);
	}

	/**
	 * Returns the number of saml sp auth requests where samlIdpEntityId = &#63; and samlSpAuthRequestKey = &#63;.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlSpAuthRequestKey the saml sp auth request key
	 * @return the number of matching saml sp auth requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySIEI_SSARK(String samlIdpEntityId,
		String samlSpAuthRequestKey) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIEI_SSARK;

		Object[] finderArgs = new Object[] { samlIdpEntityId, samlSpAuthRequestKey };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SAMLSPAUTHREQUEST_WHERE);

			boolean bindSamlIdpEntityId = false;

			if (samlIdpEntityId == null) {
				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLIDPENTITYID_1);
			}
			else if (samlIdpEntityId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLIDPENTITYID_3);
			}
			else {
				bindSamlIdpEntityId = true;

				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLIDPENTITYID_2);
			}

			boolean bindSamlSpAuthRequestKey = false;

			if (samlSpAuthRequestKey == null) {
				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLSPAUTHREQUESTKEY_1);
			}
			else if (samlSpAuthRequestKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLSPAUTHREQUESTKEY_3);
			}
			else {
				bindSamlSpAuthRequestKey = true;

				query.append(_FINDER_COLUMN_SIEI_SSARK_SAMLSPAUTHREQUESTKEY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSamlIdpEntityId) {
					qPos.add(samlIdpEntityId);
				}

				if (bindSamlSpAuthRequestKey) {
					qPos.add(samlSpAuthRequestKey);
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

	private static final String _FINDER_COLUMN_SIEI_SSARK_SAMLIDPENTITYID_1 = "samlSpAuthRequest.samlIdpEntityId IS NULL AND ";
	private static final String _FINDER_COLUMN_SIEI_SSARK_SAMLIDPENTITYID_2 = "samlSpAuthRequest.samlIdpEntityId = ? AND ";
	private static final String _FINDER_COLUMN_SIEI_SSARK_SAMLIDPENTITYID_3 = "(samlSpAuthRequest.samlIdpEntityId IS NULL OR samlSpAuthRequest.samlIdpEntityId = '') AND ";
	private static final String _FINDER_COLUMN_SIEI_SSARK_SAMLSPAUTHREQUESTKEY_1 =
		"samlSpAuthRequest.samlSpAuthRequestKey IS NULL";
	private static final String _FINDER_COLUMN_SIEI_SSARK_SAMLSPAUTHREQUESTKEY_2 =
		"samlSpAuthRequest.samlSpAuthRequestKey = ?";
	private static final String _FINDER_COLUMN_SIEI_SSARK_SAMLSPAUTHREQUESTKEY_3 =
		"(samlSpAuthRequest.samlSpAuthRequestKey IS NULL OR samlSpAuthRequest.samlSpAuthRequestKey = '')";

	public SamlSpAuthRequestPersistenceImpl() {
		setModelClass(SamlSpAuthRequest.class);
	}

	/**
	 * Caches the saml sp auth request in the entity cache if it is enabled.
	 *
	 * @param samlSpAuthRequest the saml sp auth request
	 */
	@Override
	public void cacheResult(SamlSpAuthRequest samlSpAuthRequest) {
		EntityCacheUtil.putResult(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpAuthRequestImpl.class, samlSpAuthRequest.getPrimaryKey(),
			samlSpAuthRequest);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SSARK,
			new Object[] {
				samlSpAuthRequest.getSamlIdpEntityId(),
				samlSpAuthRequest.getSamlSpAuthRequestKey()
			}, samlSpAuthRequest);

		samlSpAuthRequest.resetOriginalValues();
	}

	/**
	 * Caches the saml sp auth requests in the entity cache if it is enabled.
	 *
	 * @param samlSpAuthRequests the saml sp auth requests
	 */
	@Override
	public void cacheResult(List<SamlSpAuthRequest> samlSpAuthRequests) {
		for (SamlSpAuthRequest samlSpAuthRequest : samlSpAuthRequests) {
			if (EntityCacheUtil.getResult(
						SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
						SamlSpAuthRequestImpl.class,
						samlSpAuthRequest.getPrimaryKey()) == null) {
				cacheResult(samlSpAuthRequest);
			}
			else {
				samlSpAuthRequest.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all saml sp auth requests.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SamlSpAuthRequestImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SamlSpAuthRequestImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the saml sp auth request.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SamlSpAuthRequest samlSpAuthRequest) {
		EntityCacheUtil.removeResult(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpAuthRequestImpl.class, samlSpAuthRequest.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(samlSpAuthRequest);
	}

	@Override
	public void clearCache(List<SamlSpAuthRequest> samlSpAuthRequests) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SamlSpAuthRequest samlSpAuthRequest : samlSpAuthRequests) {
			EntityCacheUtil.removeResult(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
				SamlSpAuthRequestImpl.class, samlSpAuthRequest.getPrimaryKey());

			clearUniqueFindersCache(samlSpAuthRequest);
		}
	}

	protected void cacheUniqueFindersCache(SamlSpAuthRequest samlSpAuthRequest) {
		if (samlSpAuthRequest.isNew()) {
			Object[] args = new Object[] {
					samlSpAuthRequest.getSamlIdpEntityId(),
					samlSpAuthRequest.getSamlSpAuthRequestKey()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SIEI_SSARK, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SSARK, args,
				samlSpAuthRequest);
		}
		else {
			SamlSpAuthRequestModelImpl samlSpAuthRequestModelImpl = (SamlSpAuthRequestModelImpl)samlSpAuthRequest;

			if ((samlSpAuthRequestModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SIEI_SSARK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						samlSpAuthRequest.getSamlIdpEntityId(),
						samlSpAuthRequest.getSamlSpAuthRequestKey()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SIEI_SSARK,
					args, Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SSARK,
					args, samlSpAuthRequest);
			}
		}
	}

	protected void clearUniqueFindersCache(SamlSpAuthRequest samlSpAuthRequest) {
		SamlSpAuthRequestModelImpl samlSpAuthRequestModelImpl = (SamlSpAuthRequestModelImpl)samlSpAuthRequest;

		Object[] args = new Object[] {
				samlSpAuthRequest.getSamlIdpEntityId(),
				samlSpAuthRequest.getSamlSpAuthRequestKey()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIEI_SSARK, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIEI_SSARK, args);

		if ((samlSpAuthRequestModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SIEI_SSARK.getColumnBitmask()) != 0) {
			args = new Object[] {
					samlSpAuthRequestModelImpl.getOriginalSamlIdpEntityId(),
					samlSpAuthRequestModelImpl.getOriginalSamlSpAuthRequestKey()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIEI_SSARK, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIEI_SSARK, args);
		}
	}

	/**
	 * Creates a new saml sp auth request with the primary key. Does not add the saml sp auth request to the database.
	 *
	 * @param samlSpAuthnRequestId the primary key for the new saml sp auth request
	 * @return the new saml sp auth request
	 */
	@Override
	public SamlSpAuthRequest create(long samlSpAuthnRequestId) {
		SamlSpAuthRequest samlSpAuthRequest = new SamlSpAuthRequestImpl();

		samlSpAuthRequest.setNew(true);
		samlSpAuthRequest.setPrimaryKey(samlSpAuthnRequestId);

		return samlSpAuthRequest;
	}

	/**
	 * Removes the saml sp auth request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param samlSpAuthnRequestId the primary key of the saml sp auth request
	 * @return the saml sp auth request that was removed
	 * @throws com.liferay.saml.NoSuchSpAuthRequestException if a saml sp auth request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest remove(long samlSpAuthnRequestId)
		throws NoSuchSpAuthRequestException, SystemException {
		return remove((Serializable)samlSpAuthnRequestId);
	}

	/**
	 * Removes the saml sp auth request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the saml sp auth request
	 * @return the saml sp auth request that was removed
	 * @throws com.liferay.saml.NoSuchSpAuthRequestException if a saml sp auth request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest remove(Serializable primaryKey)
		throws NoSuchSpAuthRequestException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SamlSpAuthRequest samlSpAuthRequest = (SamlSpAuthRequest)session.get(SamlSpAuthRequestImpl.class,
					primaryKey);

			if (samlSpAuthRequest == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSpAuthRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(samlSpAuthRequest);
		}
		catch (NoSuchSpAuthRequestException nsee) {
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
	protected SamlSpAuthRequest removeImpl(SamlSpAuthRequest samlSpAuthRequest)
		throws SystemException {
		samlSpAuthRequest = toUnwrappedModel(samlSpAuthRequest);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(samlSpAuthRequest)) {
				samlSpAuthRequest = (SamlSpAuthRequest)session.get(SamlSpAuthRequestImpl.class,
						samlSpAuthRequest.getPrimaryKeyObj());
			}

			if (samlSpAuthRequest != null) {
				session.delete(samlSpAuthRequest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (samlSpAuthRequest != null) {
			clearCache(samlSpAuthRequest);
		}

		return samlSpAuthRequest;
	}

	@Override
	public SamlSpAuthRequest updateImpl(
		com.liferay.saml.model.SamlSpAuthRequest samlSpAuthRequest)
		throws SystemException {
		samlSpAuthRequest = toUnwrappedModel(samlSpAuthRequest);

		boolean isNew = samlSpAuthRequest.isNew();

		Session session = null;

		try {
			session = openSession();

			if (samlSpAuthRequest.isNew()) {
				session.save(samlSpAuthRequest);

				samlSpAuthRequest.setNew(false);
			}
			else {
				session.merge(samlSpAuthRequest);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SamlSpAuthRequestModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpAuthRequestImpl.class, samlSpAuthRequest.getPrimaryKey(),
			samlSpAuthRequest);

		clearUniqueFindersCache(samlSpAuthRequest);
		cacheUniqueFindersCache(samlSpAuthRequest);

		return samlSpAuthRequest;
	}

	protected SamlSpAuthRequest toUnwrappedModel(
		SamlSpAuthRequest samlSpAuthRequest) {
		if (samlSpAuthRequest instanceof SamlSpAuthRequestImpl) {
			return samlSpAuthRequest;
		}

		SamlSpAuthRequestImpl samlSpAuthRequestImpl = new SamlSpAuthRequestImpl();

		samlSpAuthRequestImpl.setNew(samlSpAuthRequest.isNew());
		samlSpAuthRequestImpl.setPrimaryKey(samlSpAuthRequest.getPrimaryKey());

		samlSpAuthRequestImpl.setSamlSpAuthnRequestId(samlSpAuthRequest.getSamlSpAuthnRequestId());
		samlSpAuthRequestImpl.setCompanyId(samlSpAuthRequest.getCompanyId());
		samlSpAuthRequestImpl.setCreateDate(samlSpAuthRequest.getCreateDate());
		samlSpAuthRequestImpl.setSamlIdpEntityId(samlSpAuthRequest.getSamlIdpEntityId());
		samlSpAuthRequestImpl.setSamlSpAuthRequestKey(samlSpAuthRequest.getSamlSpAuthRequestKey());

		return samlSpAuthRequestImpl;
	}

	/**
	 * Returns the saml sp auth request with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml sp auth request
	 * @return the saml sp auth request
	 * @throws com.liferay.saml.NoSuchSpAuthRequestException if a saml sp auth request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSpAuthRequestException, SystemException {
		SamlSpAuthRequest samlSpAuthRequest = fetchByPrimaryKey(primaryKey);

		if (samlSpAuthRequest == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSpAuthRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return samlSpAuthRequest;
	}

	/**
	 * Returns the saml sp auth request with the primary key or throws a {@link com.liferay.saml.NoSuchSpAuthRequestException} if it could not be found.
	 *
	 * @param samlSpAuthnRequestId the primary key of the saml sp auth request
	 * @return the saml sp auth request
	 * @throws com.liferay.saml.NoSuchSpAuthRequestException if a saml sp auth request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest findByPrimaryKey(long samlSpAuthnRequestId)
		throws NoSuchSpAuthRequestException, SystemException {
		return findByPrimaryKey((Serializable)samlSpAuthnRequestId);
	}

	/**
	 * Returns the saml sp auth request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml sp auth request
	 * @return the saml sp auth request, or <code>null</code> if a saml sp auth request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SamlSpAuthRequest samlSpAuthRequest = (SamlSpAuthRequest)EntityCacheUtil.getResult(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
				SamlSpAuthRequestImpl.class, primaryKey);

		if (samlSpAuthRequest == _nullSamlSpAuthRequest) {
			return null;
		}

		if (samlSpAuthRequest == null) {
			Session session = null;

			try {
				session = openSession();

				samlSpAuthRequest = (SamlSpAuthRequest)session.get(SamlSpAuthRequestImpl.class,
						primaryKey);

				if (samlSpAuthRequest != null) {
					cacheResult(samlSpAuthRequest);
				}
				else {
					EntityCacheUtil.putResult(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
						SamlSpAuthRequestImpl.class, primaryKey,
						_nullSamlSpAuthRequest);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SamlSpAuthRequestModelImpl.ENTITY_CACHE_ENABLED,
					SamlSpAuthRequestImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return samlSpAuthRequest;
	}

	/**
	 * Returns the saml sp auth request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samlSpAuthnRequestId the primary key of the saml sp auth request
	 * @return the saml sp auth request, or <code>null</code> if a saml sp auth request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpAuthRequest fetchByPrimaryKey(long samlSpAuthnRequestId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)samlSpAuthnRequestId);
	}

	/**
	 * Returns all the saml sp auth requests.
	 *
	 * @return the saml sp auth requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpAuthRequest> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml sp auth requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpAuthRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp auth requests
	 * @param end the upper bound of the range of saml sp auth requests (not inclusive)
	 * @return the range of saml sp auth requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpAuthRequest> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml sp auth requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpAuthRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp auth requests
	 * @param end the upper bound of the range of saml sp auth requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of saml sp auth requests
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpAuthRequest> findAll(int start, int end,
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

		List<SamlSpAuthRequest> list = (List<SamlSpAuthRequest>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SAMLSPAUTHREQUEST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SAMLSPAUTHREQUEST;

				if (pagination) {
					sql = sql.concat(SamlSpAuthRequestModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SamlSpAuthRequest>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SamlSpAuthRequest>(list);
				}
				else {
					list = (List<SamlSpAuthRequest>)QueryUtil.list(q,
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
	 * Removes all the saml sp auth requests from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SamlSpAuthRequest samlSpAuthRequest : findAll()) {
			remove(samlSpAuthRequest);
		}
	}

	/**
	 * Returns the number of saml sp auth requests.
	 *
	 * @return the number of saml sp auth requests
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

				Query q = session.createQuery(_SQL_COUNT_SAMLSPAUTHREQUEST);

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
	 * Initializes the saml sp auth request persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.saml.model.SamlSpAuthRequest")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SamlSpAuthRequest>> listenersList = new ArrayList<ModelListener<SamlSpAuthRequest>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SamlSpAuthRequest>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SamlSpAuthRequestImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SAMLSPAUTHREQUEST = "SELECT samlSpAuthRequest FROM SamlSpAuthRequest samlSpAuthRequest";
	private static final String _SQL_SELECT_SAMLSPAUTHREQUEST_WHERE = "SELECT samlSpAuthRequest FROM SamlSpAuthRequest samlSpAuthRequest WHERE ";
	private static final String _SQL_COUNT_SAMLSPAUTHREQUEST = "SELECT COUNT(samlSpAuthRequest) FROM SamlSpAuthRequest samlSpAuthRequest";
	private static final String _SQL_COUNT_SAMLSPAUTHREQUEST_WHERE = "SELECT COUNT(samlSpAuthRequest) FROM SamlSpAuthRequest samlSpAuthRequest WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "samlSpAuthRequest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SamlSpAuthRequest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SamlSpAuthRequest exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SamlSpAuthRequestPersistenceImpl.class);
	private static SamlSpAuthRequest _nullSamlSpAuthRequest = new SamlSpAuthRequestImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SamlSpAuthRequest> toCacheModel() {
				return _nullSamlSpAuthRequestCacheModel;
			}
		};

	private static CacheModel<SamlSpAuthRequest> _nullSamlSpAuthRequestCacheModel =
		new CacheModel<SamlSpAuthRequest>() {
			@Override
			public SamlSpAuthRequest toEntityModel() {
				return _nullSamlSpAuthRequest;
			}
		};
}