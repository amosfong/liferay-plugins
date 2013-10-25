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

import com.liferay.saml.NoSuchSpMessageException;
import com.liferay.saml.model.SamlSpMessage;
import com.liferay.saml.model.impl.SamlSpMessageImpl;
import com.liferay.saml.model.impl.SamlSpMessageModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the saml sp message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Mika Koivisto
 * @see SamlSpMessagePersistence
 * @see SamlSpMessageUtil
 * @generated
 */
public class SamlSpMessagePersistenceImpl extends BasePersistenceImpl<SamlSpMessage>
	implements SamlSpMessagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SamlSpMessageUtil} to access the saml sp message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SamlSpMessageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpMessageModelImpl.FINDER_CACHE_ENABLED,
			SamlSpMessageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpMessageModelImpl.FINDER_CACHE_ENABLED,
			SamlSpMessageImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_SIEI_SIRK = new FinderPath(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpMessageModelImpl.FINDER_CACHE_ENABLED,
			SamlSpMessageImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchBySIEI_SIRK",
			new String[] { String.class.getName(), String.class.getName() },
			SamlSpMessageModelImpl.SAMLIDPENTITYID_COLUMN_BITMASK |
			SamlSpMessageModelImpl.SAMLIDPRESPONSEKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SIEI_SIRK = new FinderPath(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySIEI_SIRK",
			new String[] { String.class.getName(), String.class.getName() });

	/**
	 * Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or throws a {@link com.liferay.saml.NoSuchSpMessageException} if it could not be found.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlIdpResponseKey the saml idp response key
	 * @return the matching saml sp message
	 * @throws com.liferay.saml.NoSuchSpMessageException if a matching saml sp message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage findBySIEI_SIRK(String samlIdpEntityId,
		String samlIdpResponseKey)
		throws NoSuchSpMessageException, SystemException {
		SamlSpMessage samlSpMessage = fetchBySIEI_SIRK(samlIdpEntityId,
				samlIdpResponseKey);

		if (samlSpMessage == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("samlIdpEntityId=");
			msg.append(samlIdpEntityId);

			msg.append(", samlIdpResponseKey=");
			msg.append(samlIdpResponseKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSpMessageException(msg.toString());
		}

		return samlSpMessage;
	}

	/**
	 * Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlIdpResponseKey the saml idp response key
	 * @return the matching saml sp message, or <code>null</code> if a matching saml sp message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage fetchBySIEI_SIRK(String samlIdpEntityId,
		String samlIdpResponseKey) throws SystemException {
		return fetchBySIEI_SIRK(samlIdpEntityId, samlIdpResponseKey, true);
	}

	/**
	 * Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlIdpResponseKey the saml idp response key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching saml sp message, or <code>null</code> if a matching saml sp message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage fetchBySIEI_SIRK(String samlIdpEntityId,
		String samlIdpResponseKey, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { samlIdpEntityId, samlIdpResponseKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
					finderArgs, this);
		}

		if (result instanceof SamlSpMessage) {
			SamlSpMessage samlSpMessage = (SamlSpMessage)result;

			if (!Validator.equals(samlIdpEntityId,
						samlSpMessage.getSamlIdpEntityId()) ||
					!Validator.equals(samlIdpResponseKey,
						samlSpMessage.getSamlIdpResponseKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SAMLSPMESSAGE_WHERE);

			boolean bindSamlIdpEntityId = false;

			if (samlIdpEntityId == null) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_1);
			}
			else if (samlIdpEntityId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_3);
			}
			else {
				bindSamlIdpEntityId = true;

				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_2);
			}

			boolean bindSamlIdpResponseKey = false;

			if (samlIdpResponseKey == null) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_1);
			}
			else if (samlIdpResponseKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_3);
			}
			else {
				bindSamlIdpResponseKey = true;

				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_2);
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

				if (bindSamlIdpResponseKey) {
					qPos.add(samlIdpResponseKey);
				}

				List<SamlSpMessage> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"SamlSpMessagePersistenceImpl.fetchBySIEI_SIRK(String, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					SamlSpMessage samlSpMessage = list.get(0);

					result = samlSpMessage;

					cacheResult(samlSpMessage);

					if ((samlSpMessage.getSamlIdpEntityId() == null) ||
							!samlSpMessage.getSamlIdpEntityId()
											  .equals(samlIdpEntityId) ||
							(samlSpMessage.getSamlIdpResponseKey() == null) ||
							!samlSpMessage.getSamlIdpResponseKey()
											  .equals(samlIdpResponseKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
							finderArgs, samlSpMessage);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
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
			return (SamlSpMessage)result;
		}
	}

	/**
	 * Removes the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; from the database.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlIdpResponseKey the saml idp response key
	 * @return the saml sp message that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage removeBySIEI_SIRK(String samlIdpEntityId,
		String samlIdpResponseKey)
		throws NoSuchSpMessageException, SystemException {
		SamlSpMessage samlSpMessage = findBySIEI_SIRK(samlIdpEntityId,
				samlIdpResponseKey);

		return remove(samlSpMessage);
	}

	/**
	 * Returns the number of saml sp messages where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63;.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlIdpResponseKey the saml idp response key
	 * @return the number of matching saml sp messages
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySIEI_SIRK(String samlIdpEntityId,
		String samlIdpResponseKey) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SIEI_SIRK;

		Object[] finderArgs = new Object[] { samlIdpEntityId, samlIdpResponseKey };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SAMLSPMESSAGE_WHERE);

			boolean bindSamlIdpEntityId = false;

			if (samlIdpEntityId == null) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_1);
			}
			else if (samlIdpEntityId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_3);
			}
			else {
				bindSamlIdpEntityId = true;

				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_2);
			}

			boolean bindSamlIdpResponseKey = false;

			if (samlIdpResponseKey == null) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_1);
			}
			else if (samlIdpResponseKey.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_3);
			}
			else {
				bindSamlIdpResponseKey = true;

				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_2);
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

				if (bindSamlIdpResponseKey) {
					qPos.add(samlIdpResponseKey);
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

	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_1 = "samlSpMessage.samlIdpEntityId IS NULL AND ";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_2 = "samlSpMessage.samlIdpEntityId = ? AND ";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_3 = "(samlSpMessage.samlIdpEntityId IS NULL OR samlSpMessage.samlIdpEntityId = '') AND ";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_1 = "samlSpMessage.samlIdpResponseKey IS NULL";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_2 = "samlSpMessage.samlIdpResponseKey = ?";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_3 = "(samlSpMessage.samlIdpResponseKey IS NULL OR samlSpMessage.samlIdpResponseKey = '')";

	public SamlSpMessagePersistenceImpl() {
		setModelClass(SamlSpMessage.class);
	}

	/**
	 * Caches the saml sp message in the entity cache if it is enabled.
	 *
	 * @param samlSpMessage the saml sp message
	 */
	@Override
	public void cacheResult(SamlSpMessage samlSpMessage) {
		EntityCacheUtil.putResult(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpMessageImpl.class, samlSpMessage.getPrimaryKey(),
			samlSpMessage);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
			new Object[] {
				samlSpMessage.getSamlIdpEntityId(),
				samlSpMessage.getSamlIdpResponseKey()
			}, samlSpMessage);

		samlSpMessage.resetOriginalValues();
	}

	/**
	 * Caches the saml sp messages in the entity cache if it is enabled.
	 *
	 * @param samlSpMessages the saml sp messages
	 */
	@Override
	public void cacheResult(List<SamlSpMessage> samlSpMessages) {
		for (SamlSpMessage samlSpMessage : samlSpMessages) {
			if (EntityCacheUtil.getResult(
						SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
						SamlSpMessageImpl.class, samlSpMessage.getPrimaryKey()) == null) {
				cacheResult(samlSpMessage);
			}
			else {
				samlSpMessage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all saml sp messages.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SamlSpMessageImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SamlSpMessageImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the saml sp message.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SamlSpMessage samlSpMessage) {
		EntityCacheUtil.removeResult(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpMessageImpl.class, samlSpMessage.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(samlSpMessage);
	}

	@Override
	public void clearCache(List<SamlSpMessage> samlSpMessages) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SamlSpMessage samlSpMessage : samlSpMessages) {
			EntityCacheUtil.removeResult(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
				SamlSpMessageImpl.class, samlSpMessage.getPrimaryKey());

			clearUniqueFindersCache(samlSpMessage);
		}
	}

	protected void cacheUniqueFindersCache(SamlSpMessage samlSpMessage) {
		if (samlSpMessage.isNew()) {
			Object[] args = new Object[] {
					samlSpMessage.getSamlIdpEntityId(),
					samlSpMessage.getSamlIdpResponseKey()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SIEI_SIRK, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SIRK, args,
				samlSpMessage);
		}
		else {
			SamlSpMessageModelImpl samlSpMessageModelImpl = (SamlSpMessageModelImpl)samlSpMessage;

			if ((samlSpMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SIEI_SIRK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						samlSpMessage.getSamlIdpEntityId(),
						samlSpMessage.getSamlIdpResponseKey()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SIEI_SIRK, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SIRK, args,
					samlSpMessage);
			}
		}
	}

	protected void clearUniqueFindersCache(SamlSpMessage samlSpMessage) {
		SamlSpMessageModelImpl samlSpMessageModelImpl = (SamlSpMessageModelImpl)samlSpMessage;

		Object[] args = new Object[] {
				samlSpMessage.getSamlIdpEntityId(),
				samlSpMessage.getSamlIdpResponseKey()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIEI_SIRK, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIEI_SIRK, args);

		if ((samlSpMessageModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SIEI_SIRK.getColumnBitmask()) != 0) {
			args = new Object[] {
					samlSpMessageModelImpl.getOriginalSamlIdpEntityId(),
					samlSpMessageModelImpl.getOriginalSamlIdpResponseKey()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIEI_SIRK, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIEI_SIRK, args);
		}
	}

	/**
	 * Creates a new saml sp message with the primary key. Does not add the saml sp message to the database.
	 *
	 * @param samlSpMessageId the primary key for the new saml sp message
	 * @return the new saml sp message
	 */
	@Override
	public SamlSpMessage create(long samlSpMessageId) {
		SamlSpMessage samlSpMessage = new SamlSpMessageImpl();

		samlSpMessage.setNew(true);
		samlSpMessage.setPrimaryKey(samlSpMessageId);

		return samlSpMessage;
	}

	/**
	 * Removes the saml sp message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param samlSpMessageId the primary key of the saml sp message
	 * @return the saml sp message that was removed
	 * @throws com.liferay.saml.NoSuchSpMessageException if a saml sp message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage remove(long samlSpMessageId)
		throws NoSuchSpMessageException, SystemException {
		return remove((Serializable)samlSpMessageId);
	}

	/**
	 * Removes the saml sp message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the saml sp message
	 * @return the saml sp message that was removed
	 * @throws com.liferay.saml.NoSuchSpMessageException if a saml sp message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage remove(Serializable primaryKey)
		throws NoSuchSpMessageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SamlSpMessage samlSpMessage = (SamlSpMessage)session.get(SamlSpMessageImpl.class,
					primaryKey);

			if (samlSpMessage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSpMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(samlSpMessage);
		}
		catch (NoSuchSpMessageException nsee) {
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
	protected SamlSpMessage removeImpl(SamlSpMessage samlSpMessage)
		throws SystemException {
		samlSpMessage = toUnwrappedModel(samlSpMessage);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(samlSpMessage)) {
				samlSpMessage = (SamlSpMessage)session.get(SamlSpMessageImpl.class,
						samlSpMessage.getPrimaryKeyObj());
			}

			if (samlSpMessage != null) {
				session.delete(samlSpMessage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (samlSpMessage != null) {
			clearCache(samlSpMessage);
		}

		return samlSpMessage;
	}

	@Override
	public SamlSpMessage updateImpl(
		com.liferay.saml.model.SamlSpMessage samlSpMessage)
		throws SystemException {
		samlSpMessage = toUnwrappedModel(samlSpMessage);

		boolean isNew = samlSpMessage.isNew();

		Session session = null;

		try {
			session = openSession();

			if (samlSpMessage.isNew()) {
				session.save(samlSpMessage);

				samlSpMessage.setNew(false);
			}
			else {
				session.merge(samlSpMessage);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SamlSpMessageModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
			SamlSpMessageImpl.class, samlSpMessage.getPrimaryKey(),
			samlSpMessage);

		clearUniqueFindersCache(samlSpMessage);
		cacheUniqueFindersCache(samlSpMessage);

		return samlSpMessage;
	}

	protected SamlSpMessage toUnwrappedModel(SamlSpMessage samlSpMessage) {
		if (samlSpMessage instanceof SamlSpMessageImpl) {
			return samlSpMessage;
		}

		SamlSpMessageImpl samlSpMessageImpl = new SamlSpMessageImpl();

		samlSpMessageImpl.setNew(samlSpMessage.isNew());
		samlSpMessageImpl.setPrimaryKey(samlSpMessage.getPrimaryKey());

		samlSpMessageImpl.setSamlSpMessageId(samlSpMessage.getSamlSpMessageId());
		samlSpMessageImpl.setCompanyId(samlSpMessage.getCompanyId());
		samlSpMessageImpl.setCreateDate(samlSpMessage.getCreateDate());
		samlSpMessageImpl.setSamlIdpEntityId(samlSpMessage.getSamlIdpEntityId());
		samlSpMessageImpl.setSamlIdpResponseKey(samlSpMessage.getSamlIdpResponseKey());
		samlSpMessageImpl.setExpirationDate(samlSpMessage.getExpirationDate());

		return samlSpMessageImpl;
	}

	/**
	 * Returns the saml sp message with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml sp message
	 * @return the saml sp message
	 * @throws com.liferay.saml.NoSuchSpMessageException if a saml sp message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSpMessageException, SystemException {
		SamlSpMessage samlSpMessage = fetchByPrimaryKey(primaryKey);

		if (samlSpMessage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSpMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return samlSpMessage;
	}

	/**
	 * Returns the saml sp message with the primary key or throws a {@link com.liferay.saml.NoSuchSpMessageException} if it could not be found.
	 *
	 * @param samlSpMessageId the primary key of the saml sp message
	 * @return the saml sp message
	 * @throws com.liferay.saml.NoSuchSpMessageException if a saml sp message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage findByPrimaryKey(long samlSpMessageId)
		throws NoSuchSpMessageException, SystemException {
		return findByPrimaryKey((Serializable)samlSpMessageId);
	}

	/**
	 * Returns the saml sp message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the saml sp message
	 * @return the saml sp message, or <code>null</code> if a saml sp message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SamlSpMessage samlSpMessage = (SamlSpMessage)EntityCacheUtil.getResult(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
				SamlSpMessageImpl.class, primaryKey);

		if (samlSpMessage == _nullSamlSpMessage) {
			return null;
		}

		if (samlSpMessage == null) {
			Session session = null;

			try {
				session = openSession();

				samlSpMessage = (SamlSpMessage)session.get(SamlSpMessageImpl.class,
						primaryKey);

				if (samlSpMessage != null) {
					cacheResult(samlSpMessage);
				}
				else {
					EntityCacheUtil.putResult(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
						SamlSpMessageImpl.class, primaryKey, _nullSamlSpMessage);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
					SamlSpMessageImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return samlSpMessage;
	}

	/**
	 * Returns the saml sp message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samlSpMessageId the primary key of the saml sp message
	 * @return the saml sp message, or <code>null</code> if a saml sp message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage fetchByPrimaryKey(long samlSpMessageId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)samlSpMessageId);
	}

	/**
	 * Returns all the saml sp messages.
	 *
	 * @return the saml sp messages
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpMessage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml sp messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp messages
	 * @param end the upper bound of the range of saml sp messages (not inclusive)
	 * @return the range of saml sp messages
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpMessage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml sp messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp messages
	 * @param end the upper bound of the range of saml sp messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of saml sp messages
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SamlSpMessage> findAll(int start, int end,
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

		List<SamlSpMessage> list = (List<SamlSpMessage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SAMLSPMESSAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SAMLSPMESSAGE;

				if (pagination) {
					sql = sql.concat(SamlSpMessageModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SamlSpMessage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SamlSpMessage>(list);
				}
				else {
					list = (List<SamlSpMessage>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the saml sp messages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SamlSpMessage samlSpMessage : findAll()) {
			remove(samlSpMessage);
		}
	}

	/**
	 * Returns the number of saml sp messages.
	 *
	 * @return the number of saml sp messages
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

				Query q = session.createQuery(_SQL_COUNT_SAMLSPMESSAGE);

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
	 * Initializes the saml sp message persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.saml.model.SamlSpMessage")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SamlSpMessage>> listenersList = new ArrayList<ModelListener<SamlSpMessage>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SamlSpMessage>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SamlSpMessageImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SAMLSPMESSAGE = "SELECT samlSpMessage FROM SamlSpMessage samlSpMessage";
	private static final String _SQL_SELECT_SAMLSPMESSAGE_WHERE = "SELECT samlSpMessage FROM SamlSpMessage samlSpMessage WHERE ";
	private static final String _SQL_COUNT_SAMLSPMESSAGE = "SELECT COUNT(samlSpMessage) FROM SamlSpMessage samlSpMessage";
	private static final String _SQL_COUNT_SAMLSPMESSAGE_WHERE = "SELECT COUNT(samlSpMessage) FROM SamlSpMessage samlSpMessage WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "samlSpMessage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SamlSpMessage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SamlSpMessage exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SamlSpMessagePersistenceImpl.class);
	private static SamlSpMessage _nullSamlSpMessage = new SamlSpMessageImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SamlSpMessage> toCacheModel() {
				return _nullSamlSpMessageCacheModel;
			}
		};

	private static CacheModel<SamlSpMessage> _nullSamlSpMessageCacheModel = new CacheModel<SamlSpMessage>() {
			@Override
			public SamlSpMessage toEntityModel() {
				return _nullSamlSpMessage;
			}
		};
}