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
import com.liferay.portal.service.persistence.UserPersistence;
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

	/**
	 * Caches the saml sp message in the entity cache if it is enabled.
	 *
	 * @param samlSpMessage the saml sp message
	 */
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

	protected void clearUniqueFindersCache(SamlSpMessage samlSpMessage) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
			new Object[] {
				samlSpMessage.getSamlIdpEntityId(),
				
			samlSpMessage.getSamlIdpResponseKey()
			});
	}

	/**
	 * Creates a new saml sp message with the primary key. Does not add the saml sp message to the database.
	 *
	 * @param samlSpMessageId the primary key for the new saml sp message
	 * @return the new saml sp message
	 */
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
	public SamlSpMessage remove(long samlSpMessageId)
		throws NoSuchSpMessageException, SystemException {
		return remove(Long.valueOf(samlSpMessageId));
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

			if (samlSpMessage.isCachedModel()) {
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

		SamlSpMessageModelImpl samlSpMessageModelImpl = (SamlSpMessageModelImpl)samlSpMessage;

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

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
				new Object[] {
					samlSpMessage.getSamlIdpEntityId(),
					
				samlSpMessage.getSamlIdpResponseKey()
				}, samlSpMessage);
		}
		else {
			if ((samlSpMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SIEI_SIRK.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						samlSpMessageModelImpl.getOriginalSamlIdpEntityId(),
						
						samlSpMessageModelImpl.getOriginalSamlIdpResponseKey()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SIEI_SIRK,
					args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
					args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
					new Object[] {
						samlSpMessage.getSamlIdpEntityId(),
						
					samlSpMessage.getSamlIdpResponseKey()
					}, samlSpMessage);
			}
		}

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
	 * @throws com.liferay.portal.NoSuchModelException if a saml sp message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SamlSpMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the saml sp message with the primary key or throws a {@link com.liferay.saml.NoSuchSpMessageException} if it could not be found.
	 *
	 * @param samlSpMessageId the primary key of the saml sp message
	 * @return the saml sp message
	 * @throws com.liferay.saml.NoSuchSpMessageException if a saml sp message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SamlSpMessage findByPrimaryKey(long samlSpMessageId)
		throws NoSuchSpMessageException, SystemException {
		SamlSpMessage samlSpMessage = fetchByPrimaryKey(samlSpMessageId);

		if (samlSpMessage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + samlSpMessageId);
			}

			throw new NoSuchSpMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				samlSpMessageId);
		}

		return samlSpMessage;
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
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the saml sp message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param samlSpMessageId the primary key of the saml sp message
	 * @return the saml sp message, or <code>null</code> if a saml sp message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public SamlSpMessage fetchByPrimaryKey(long samlSpMessageId)
		throws SystemException {
		SamlSpMessage samlSpMessage = (SamlSpMessage)EntityCacheUtil.getResult(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
				SamlSpMessageImpl.class, samlSpMessageId);

		if (samlSpMessage == _nullSamlSpMessage) {
			return null;
		}

		if (samlSpMessage == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				samlSpMessage = (SamlSpMessage)session.get(SamlSpMessageImpl.class,
						Long.valueOf(samlSpMessageId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (samlSpMessage != null) {
					cacheResult(samlSpMessage);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(SamlSpMessageModelImpl.ENTITY_CACHE_ENABLED,
						SamlSpMessageImpl.class, samlSpMessageId,
						_nullSamlSpMessage);
				}

				closeSession(session);
			}
		}

		return samlSpMessage;
	}

	/**
	 * Returns the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; or throws a {@link com.liferay.saml.NoSuchSpMessageException} if it could not be found.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlIdpResponseKey the saml idp response key
	 * @return the matching saml sp message
	 * @throws com.liferay.saml.NoSuchSpMessageException if a matching saml sp message could not be found
	 * @throws SystemException if a system exception occurred
	 */
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
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_SAMLSPMESSAGE_WHERE);

			if (samlIdpEntityId == null) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_1);
			}
			else {
				if (samlIdpEntityId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_3);
				}
				else {
					query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_2);
				}
			}

			if (samlIdpResponseKey == null) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_1);
			}
			else {
				if (samlIdpResponseKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (samlIdpEntityId != null) {
					qPos.add(samlIdpEntityId);
				}

				if (samlIdpResponseKey != null) {
					qPos.add(samlIdpResponseKey);
				}

				List<SamlSpMessage> list = q.list();

				result = list;

				SamlSpMessage samlSpMessage = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
						finderArgs, list);
				}
				else {
					samlSpMessage = list.get(0);

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

				return samlSpMessage;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SIEI_SIRK,
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
				return (SamlSpMessage)result;
			}
		}
	}

	/**
	 * Returns all the saml sp messages.
	 *
	 * @return the saml sp messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<SamlSpMessage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the saml sp messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp messages
	 * @param end the upper bound of the range of saml sp messages (not inclusive)
	 * @return the range of saml sp messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<SamlSpMessage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the saml sp messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of saml sp messages
	 * @param end the upper bound of the range of saml sp messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of saml sp messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<SamlSpMessage> findAll(int start, int end,
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
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<SamlSpMessage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<SamlSpMessage>)QueryUtil.list(q, getDialect(),
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
	 * Removes the saml sp message where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63; from the database.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlIdpResponseKey the saml idp response key
	 * @return the saml sp message that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public SamlSpMessage removeBySIEI_SIRK(String samlIdpEntityId,
		String samlIdpResponseKey)
		throws NoSuchSpMessageException, SystemException {
		SamlSpMessage samlSpMessage = findBySIEI_SIRK(samlIdpEntityId,
				samlIdpResponseKey);

		return remove(samlSpMessage);
	}

	/**
	 * Removes all the saml sp messages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (SamlSpMessage samlSpMessage : findAll()) {
			remove(samlSpMessage);
		}
	}

	/**
	 * Returns the number of saml sp messages where samlIdpEntityId = &#63; and samlIdpResponseKey = &#63;.
	 *
	 * @param samlIdpEntityId the saml idp entity ID
	 * @param samlIdpResponseKey the saml idp response key
	 * @return the number of matching saml sp messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySIEI_SIRK(String samlIdpEntityId,
		String samlIdpResponseKey) throws SystemException {
		Object[] finderArgs = new Object[] { samlIdpEntityId, samlIdpResponseKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SIEI_SIRK,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SAMLSPMESSAGE_WHERE);

			if (samlIdpEntityId == null) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_1);
			}
			else {
				if (samlIdpEntityId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_3);
				}
				else {
					query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_2);
				}
			}

			if (samlIdpResponseKey == null) {
				query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_1);
			}
			else {
				if (samlIdpResponseKey.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_3);
				}
				else {
					query.append(_FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (samlIdpEntityId != null) {
					qPos.add(samlIdpEntityId);
				}

				if (samlIdpResponseKey != null) {
					qPos.add(samlIdpResponseKey);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SIEI_SIRK,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of saml sp messages.
	 *
	 * @return the number of saml sp messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SAMLSPMESSAGE);

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
		EntityCacheUtil.removeCache(SamlSpMessageImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = SamlIdpSpConnectionPersistence.class)
	protected SamlIdpSpConnectionPersistence samlIdpSpConnectionPersistence;
	@BeanReference(type = SamlIdpSpSessionPersistence.class)
	protected SamlIdpSpSessionPersistence samlIdpSpSessionPersistence;
	@BeanReference(type = SamlIdpSsoSessionPersistence.class)
	protected SamlIdpSsoSessionPersistence samlIdpSsoSessionPersistence;
	@BeanReference(type = SamlSpAuthRequestPersistence.class)
	protected SamlSpAuthRequestPersistence samlSpAuthRequestPersistence;
	@BeanReference(type = SamlSpIdpConnectionPersistence.class)
	protected SamlSpIdpConnectionPersistence samlSpIdpConnectionPersistence;
	@BeanReference(type = SamlSpMessagePersistence.class)
	protected SamlSpMessagePersistence samlSpMessagePersistence;
	@BeanReference(type = SamlSpSessionPersistence.class)
	protected SamlSpSessionPersistence samlSpSessionPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_SAMLSPMESSAGE = "SELECT samlSpMessage FROM SamlSpMessage samlSpMessage";
	private static final String _SQL_SELECT_SAMLSPMESSAGE_WHERE = "SELECT samlSpMessage FROM SamlSpMessage samlSpMessage WHERE ";
	private static final String _SQL_COUNT_SAMLSPMESSAGE = "SELECT COUNT(samlSpMessage) FROM SamlSpMessage samlSpMessage";
	private static final String _SQL_COUNT_SAMLSPMESSAGE_WHERE = "SELECT COUNT(samlSpMessage) FROM SamlSpMessage samlSpMessage WHERE ";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_1 = "samlSpMessage.samlIdpEntityId IS NULL AND ";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_2 = "samlSpMessage.samlIdpEntityId = ? AND ";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPENTITYID_3 = "(samlSpMessage.samlIdpEntityId IS NULL OR samlSpMessage.samlIdpEntityId = ?) AND ";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_1 = "samlSpMessage.samlIdpResponseKey IS NULL";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_2 = "samlSpMessage.samlIdpResponseKey = ?";
	private static final String _FINDER_COLUMN_SIEI_SIRK_SAMLIDPRESPONSEKEY_3 = "(samlSpMessage.samlIdpResponseKey IS NULL OR samlSpMessage.samlIdpResponseKey = ?)";
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
			public SamlSpMessage toEntityModel() {
				return _nullSamlSpMessage;
			}
		};
}