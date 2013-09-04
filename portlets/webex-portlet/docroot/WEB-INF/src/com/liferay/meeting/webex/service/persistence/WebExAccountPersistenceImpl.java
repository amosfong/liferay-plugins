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

package com.liferay.meeting.webex.service.persistence;

import com.liferay.meeting.webex.NoSuchAccountException;
import com.liferay.meeting.webex.model.WebExAccount;
import com.liferay.meeting.webex.model.impl.WebExAccountImpl;
import com.liferay.meeting.webex.model.impl.WebExAccountModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the web ex account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see WebExAccountPersistence
 * @see WebExAccountUtil
 * @generated
 */
public class WebExAccountPersistenceImpl extends BasePersistenceImpl<WebExAccount>
	implements WebExAccountPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WebExAccountUtil} to access the web ex account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WebExAccountImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			WebExAccountModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			WebExAccountModelImpl.UUID_COLUMN_BITMASK |
			WebExAccountModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_W = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_W",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_W",
			new String[] { Long.class.getName(), Long.class.getName() },
			WebExAccountModelImpl.GROUPID_COLUMN_BITMASK |
			WebExAccountModelImpl.WEBEXSITEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_W = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_W",
			new String[] { Long.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, WebExAccountImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the web ex account in the entity cache if it is enabled.
	 *
	 * @param webExAccount the web ex account
	 */
	public void cacheResult(WebExAccount webExAccount) {
		EntityCacheUtil.putResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountImpl.class, webExAccount.getPrimaryKey(), webExAccount);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				webExAccount.getUuid(), Long.valueOf(webExAccount.getGroupId())
			}, webExAccount);

		webExAccount.resetOriginalValues();
	}

	/**
	 * Caches the web ex accounts in the entity cache if it is enabled.
	 *
	 * @param webExAccounts the web ex accounts
	 */
	public void cacheResult(List<WebExAccount> webExAccounts) {
		for (WebExAccount webExAccount : webExAccounts) {
			if (EntityCacheUtil.getResult(
						WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
						WebExAccountImpl.class, webExAccount.getPrimaryKey()) == null) {
				cacheResult(webExAccount);
			}
			else {
				webExAccount.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all web ex accounts.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WebExAccountImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WebExAccountImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the web ex account.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WebExAccount webExAccount) {
		EntityCacheUtil.removeResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountImpl.class, webExAccount.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(webExAccount);
	}

	@Override
	public void clearCache(List<WebExAccount> webExAccounts) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WebExAccount webExAccount : webExAccounts) {
			EntityCacheUtil.removeResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
				WebExAccountImpl.class, webExAccount.getPrimaryKey());

			clearUniqueFindersCache(webExAccount);
		}
	}

	protected void cacheUniqueFindersCache(WebExAccount webExAccount) {
		if (webExAccount.isNew()) {
			Object[] args = new Object[] {
					webExAccount.getUuid(),
					Long.valueOf(webExAccount.getGroupId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				webExAccount);
		}
		else {
			WebExAccountModelImpl webExAccountModelImpl = (WebExAccountModelImpl)webExAccount;

			if ((webExAccountModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExAccount.getUuid(),
						Long.valueOf(webExAccount.getGroupId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					webExAccount);
			}
		}
	}

	protected void clearUniqueFindersCache(WebExAccount webExAccount) {
		WebExAccountModelImpl webExAccountModelImpl = (WebExAccountModelImpl)webExAccount;

		Object[] args = new Object[] {
				webExAccount.getUuid(), Long.valueOf(webExAccount.getGroupId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((webExAccountModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					webExAccountModelImpl.getOriginalUuid(),
					Long.valueOf(webExAccountModelImpl.getOriginalGroupId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}
	}

	/**
	 * Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	 *
	 * @param webExAccountId the primary key for the new web ex account
	 * @return the new web ex account
	 */
	public WebExAccount create(long webExAccountId) {
		WebExAccount webExAccount = new WebExAccountImpl();

		webExAccount.setNew(true);
		webExAccount.setPrimaryKey(webExAccountId);

		String uuid = PortalUUIDUtil.generate();

		webExAccount.setUuid(uuid);

		return webExAccount;
	}

	/**
	 * Removes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param webExAccountId the primary key of the web ex account
	 * @return the web ex account that was removed
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount remove(long webExAccountId)
		throws NoSuchAccountException, SystemException {
		return remove(Long.valueOf(webExAccountId));
	}

	/**
	 * Removes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the web ex account
	 * @return the web ex account that was removed
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WebExAccount remove(Serializable primaryKey)
		throws NoSuchAccountException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WebExAccount webExAccount = (WebExAccount)session.get(WebExAccountImpl.class,
					primaryKey);

			if (webExAccount == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(webExAccount);
		}
		catch (NoSuchAccountException nsee) {
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
	protected WebExAccount removeImpl(WebExAccount webExAccount)
		throws SystemException {
		webExAccount = toUnwrappedModel(webExAccount);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, webExAccount);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(webExAccount);

		return webExAccount;
	}

	@Override
	public WebExAccount updateImpl(
		com.liferay.meeting.webex.model.WebExAccount webExAccount, boolean merge)
		throws SystemException {
		webExAccount = toUnwrappedModel(webExAccount);

		boolean isNew = webExAccount.isNew();

		WebExAccountModelImpl webExAccountModelImpl = (WebExAccountModelImpl)webExAccount;

		if (Validator.isNull(webExAccount.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			webExAccount.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, webExAccount, merge);

			webExAccount.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WebExAccountModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((webExAccountModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExAccountModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { webExAccountModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((webExAccountModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(webExAccountModelImpl.getOriginalGroupId()),
						Long.valueOf(webExAccountModelImpl.getOriginalWebExSiteId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_W, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W,
					args);

				args = new Object[] {
						Long.valueOf(webExAccountModelImpl.getGroupId()),
						Long.valueOf(webExAccountModelImpl.getWebExSiteId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_W, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W,
					args);
			}
		}

		EntityCacheUtil.putResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
			WebExAccountImpl.class, webExAccount.getPrimaryKey(), webExAccount);

		clearUniqueFindersCache(webExAccount);
		cacheUniqueFindersCache(webExAccount);

		return webExAccount;
	}

	protected WebExAccount toUnwrappedModel(WebExAccount webExAccount) {
		if (webExAccount instanceof WebExAccountImpl) {
			return webExAccount;
		}

		WebExAccountImpl webExAccountImpl = new WebExAccountImpl();

		webExAccountImpl.setNew(webExAccount.isNew());
		webExAccountImpl.setPrimaryKey(webExAccount.getPrimaryKey());

		webExAccountImpl.setUuid(webExAccount.getUuid());
		webExAccountImpl.setWebExAccountId(webExAccount.getWebExAccountId());
		webExAccountImpl.setGroupId(webExAccount.getGroupId());
		webExAccountImpl.setCompanyId(webExAccount.getCompanyId());
		webExAccountImpl.setUserId(webExAccount.getUserId());
		webExAccountImpl.setUserName(webExAccount.getUserName());
		webExAccountImpl.setCreateDate(webExAccount.getCreateDate());
		webExAccountImpl.setModifiedDate(webExAccount.getModifiedDate());
		webExAccountImpl.setWebExSiteId(webExAccount.getWebExSiteId());
		webExAccountImpl.setLogin(webExAccount.getLogin());
		webExAccountImpl.setPassword(webExAccount.getPassword());

		return webExAccountImpl;
	}

	/**
	 * Returns the web ex account with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the web ex account
	 * @return the web ex account
	 * @throws com.liferay.portal.NoSuchModelException if a web ex account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WebExAccount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the web ex account with the primary key or throws a {@link com.liferay.meeting.webex.NoSuchAccountException} if it could not be found.
	 *
	 * @param webExAccountId the primary key of the web ex account
	 * @return the web ex account
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount findByPrimaryKey(long webExAccountId)
		throws NoSuchAccountException, SystemException {
		WebExAccount webExAccount = fetchByPrimaryKey(webExAccountId);

		if (webExAccount == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + webExAccountId);
			}

			throw new NoSuchAccountException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				webExAccountId);
		}

		return webExAccount;
	}

	/**
	 * Returns the web ex account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the web ex account
	 * @return the web ex account, or <code>null</code> if a web ex account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WebExAccount fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the web ex account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param webExAccountId the primary key of the web ex account
	 * @return the web ex account, or <code>null</code> if a web ex account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount fetchByPrimaryKey(long webExAccountId)
		throws SystemException {
		WebExAccount webExAccount = (WebExAccount)EntityCacheUtil.getResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
				WebExAccountImpl.class, webExAccountId);

		if (webExAccount == _nullWebExAccount) {
			return null;
		}

		if (webExAccount == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				webExAccount = (WebExAccount)session.get(WebExAccountImpl.class,
						Long.valueOf(webExAccountId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (webExAccount != null) {
					cacheResult(webExAccount);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(WebExAccountModelImpl.ENTITY_CACHE_ENABLED,
						WebExAccountImpl.class, webExAccountId,
						_nullWebExAccount);
				}

				closeSession(session);
			}
		}

		return webExAccount;
	}

	/**
	 * Returns all the web ex accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @return the range of matching web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<WebExAccount> list = (List<WebExAccount>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WebExAccount webExAccount : list) {
				if (!Validator.equals(uuid, webExAccount.getUuid())) {
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

			query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
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

				if (uuid != null) {
					qPos.add(uuid);
				}

				list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		WebExAccount webExAccount = fetchByUuid_First(uuid, orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the first web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<WebExAccount> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		WebExAccount webExAccount = fetchByUuid_Last(uuid, orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the last web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<WebExAccount> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex accounts before and after the current web ex account in the ordered set where uuid = &#63;.
	 *
	 * @param webExAccountId the primary key of the current web ex account
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex account
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount[] findByUuid_PrevAndNext(long webExAccountId,
		String uuid, OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		WebExAccount webExAccount = findByPrimaryKey(webExAccountId);

		Session session = null;

		try {
			session = openSession();

			WebExAccount[] array = new WebExAccountImpl[3];

			array[0] = getByUuid_PrevAndNext(session, webExAccount, uuid,
					orderByComparator, true);

			array[1] = webExAccount;

			array[2] = getByUuid_PrevAndNext(session, webExAccount, uuid,
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

	protected WebExAccount getByUuid_PrevAndNext(Session session,
		WebExAccount webExAccount, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
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

		if (uuid != null) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(webExAccount);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExAccount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the web ex account where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.meeting.webex.NoSuchAccountException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching web ex account
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount findByUUID_G(String uuid, long groupId)
		throws NoSuchAccountException, SystemException {
		WebExAccount webExAccount = fetchByUUID_G(uuid, groupId);

		if (webExAccount == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchAccountException(msg.toString());
		}

		return webExAccount;
	}

	/**
	 * Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the web ex account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof WebExAccount) {
			WebExAccount webExAccount = (WebExAccount)result;

			if (!Validator.equals(uuid, webExAccount.getUuid()) ||
					(groupId != webExAccount.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<WebExAccount> list = q.list();

				result = list;

				WebExAccount webExAccount = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					webExAccount = list.get(0);

					cacheResult(webExAccount);

					if ((webExAccount.getUuid() == null) ||
							!webExAccount.getUuid().equals(uuid) ||
							(webExAccount.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, webExAccount);
					}
				}

				return webExAccount;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G,
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
				return (WebExAccount)result;
			}
		}
	}

	/**
	 * Returns all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @return the matching web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> findByG_W(long groupId, long webExSiteId)
		throws SystemException {
		return findByG_W(groupId, webExSiteId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @return the range of matching web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> findByG_W(long groupId, long webExSiteId,
		int start, int end) throws SystemException {
		return findByG_W(groupId, webExSiteId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> findByG_W(long groupId, long webExSiteId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_W;
			finderArgs = new Object[] { groupId, webExSiteId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_W;
			finderArgs = new Object[] {
					groupId, webExSiteId,
					
					start, end, orderByComparator
				};
		}

		List<WebExAccount> list = (List<WebExAccount>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WebExAccount webExAccount : list) {
				if ((groupId != webExAccount.getGroupId()) ||
						(webExSiteId != webExAccount.getWebExSiteId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_G_W_GROUPID_2);

			query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

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

				qPos.add(groupId);

				qPos.add(webExSiteId);

				list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount findByG_W_First(long groupId, long webExSiteId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		WebExAccount webExAccount = fetchByG_W_First(groupId, webExSiteId,
				orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", webExSiteId=");
		msg.append(webExSiteId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the first web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount fetchByG_W_First(long groupId, long webExSiteId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WebExAccount> list = findByG_W(groupId, webExSiteId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount findByG_W_Last(long groupId, long webExSiteId,
		OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		WebExAccount webExAccount = fetchByG_W_Last(groupId, webExSiteId,
				orderByComparator);

		if (webExAccount != null) {
			return webExAccount;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", webExSiteId=");
		msg.append(webExSiteId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchAccountException(msg.toString());
	}

	/**
	 * Returns the last web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex account, or <code>null</code> if a matching web ex account could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount fetchByG_W_Last(long groupId, long webExSiteId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_W(groupId, webExSiteId);

		List<WebExAccount> list = findByG_W(groupId, webExSiteId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex accounts before and after the current web ex account in the ordered set where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param webExAccountId the primary key of the current web ex account
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex account
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount[] findByG_W_PrevAndNext(long webExAccountId,
		long groupId, long webExSiteId, OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		WebExAccount webExAccount = findByPrimaryKey(webExAccountId);

		Session session = null;

		try {
			session = openSession();

			WebExAccount[] array = new WebExAccountImpl[3];

			array[0] = getByG_W_PrevAndNext(session, webExAccount, groupId,
					webExSiteId, orderByComparator, true);

			array[1] = webExAccount;

			array[2] = getByG_W_PrevAndNext(session, webExAccount, groupId,
					webExSiteId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WebExAccount getByG_W_PrevAndNext(Session session,
		WebExAccount webExAccount, long groupId, long webExSiteId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WEBEXACCOUNT_WHERE);

		query.append(_FINDER_COLUMN_G_W_GROUPID_2);

		query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

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

		qPos.add(groupId);

		qPos.add(webExSiteId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(webExAccount);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExAccount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @return the matching web ex accounts that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> filterFindByG_W(long groupId, long webExSiteId)
		throws SystemException {
		return filterFindByG_W(groupId, webExSiteId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @return the range of matching web ex accounts that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> filterFindByG_W(long groupId, long webExSiteId,
		int start, int end) throws SystemException {
		return filterFindByG_W(groupId, webExSiteId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex accounts that the user has permissions to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex accounts that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> filterFindByG_W(long groupId, long webExSiteId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_W(groupId, webExSiteId, start, end, orderByComparator);
		}

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_W_GROUPID_2);

		query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_TABLE,
					orderByComparator);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExAccount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, WebExAccountImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, WebExAccountImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(webExSiteId);

			return (List<WebExAccount>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the web ex accounts before and after the current web ex account in the ordered set of web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param webExAccountId the primary key of the current web ex account
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex account
	 * @throws com.liferay.meeting.webex.NoSuchAccountException if a web ex account with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount[] filterFindByG_W_PrevAndNext(long webExAccountId,
		long groupId, long webExSiteId, OrderByComparator orderByComparator)
		throws NoSuchAccountException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_W_PrevAndNext(webExAccountId, groupId, webExSiteId,
				orderByComparator);
		}

		WebExAccount webExAccount = findByPrimaryKey(webExAccountId);

		Session session = null;

		try {
			session = openSession();

			WebExAccount[] array = new WebExAccountImpl[3];

			array[0] = filterGetByG_W_PrevAndNext(session, webExAccount,
					groupId, webExSiteId, orderByComparator, true);

			array[1] = webExAccount;

			array[2] = filterGetByG_W_PrevAndNext(session, webExAccount,
					groupId, webExSiteId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected WebExAccount filterGetByG_W_PrevAndNext(Session session,
		WebExAccount webExAccount, long groupId, long webExSiteId,
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
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_W_GROUPID_2);

		query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_2);
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

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExAccount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, WebExAccountImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, WebExAccountImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(webExSiteId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(webExAccount);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExAccount> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the web ex accounts.
	 *
	 * @return the web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @return the range of web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex accounts
	 * @param end the upper bound of the range of web ex accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExAccount> findAll(int start, int end,
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

		List<WebExAccount> list = (List<WebExAccount>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WEBEXACCOUNT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WEBEXACCOUNT;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WebExAccount>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the web ex accounts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (WebExAccount webExAccount : findByUuid(uuid)) {
			remove(webExAccount);
		}
	}

	/**
	 * Removes the web ex account where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the web ex account that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public WebExAccount removeByUUID_G(String uuid, long groupId)
		throws NoSuchAccountException, SystemException {
		WebExAccount webExAccount = findByUUID_G(uuid, groupId);

		return remove(webExAccount);
	}

	/**
	 * Removes all the web ex accounts where groupId = &#63; and webExSiteId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByG_W(long groupId, long webExSiteId)
		throws SystemException {
		for (WebExAccount webExAccount : findByG_W(groupId, webExSiteId)) {
			remove(webExAccount);
		}
	}

	/**
	 * Removes all the web ex accounts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (WebExAccount webExAccount : findAll()) {
			remove(webExAccount);
		}
	}

	/**
	 * Returns the number of web ex accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WEBEXACCOUNT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of web ex accounts where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WEBEXACCOUNT_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_1);
			}
			else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_G_UUID_3);
				}
				else {
					query.append(_FINDER_COLUMN_UUID_G_UUID_2);
				}
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (uuid != null) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of web ex accounts where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @return the number of matching web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByG_W(long groupId, long webExSiteId)
		throws SystemException {
		Object[] finderArgs = new Object[] { groupId, webExSiteId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_G_W,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WEBEXACCOUNT_WHERE);

			query.append(_FINDER_COLUMN_G_W_GROUPID_2);

			query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(webExSiteId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_W, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of web ex accounts that the user has permission to view where groupId = &#63; and webExSiteId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param webExSiteId the web ex site ID
	 * @return the number of matching web ex accounts that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByG_W(long groupId, long webExSiteId)
		throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_W(groupId, webExSiteId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_WEBEXACCOUNT_WHERE);

		query.append(_FINDER_COLUMN_G_W_GROUPID_2);

		query.append(_FINDER_COLUMN_G_W_WEBEXSITEID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExAccount.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(webExSiteId);

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

	/**
	 * Returns the number of web ex accounts.
	 *
	 * @return the number of web ex accounts
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WEBEXACCOUNT);

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
	 * Initializes the web ex account persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.meeting.webex.model.WebExAccount")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WebExAccount>> listenersList = new ArrayList<ModelListener<WebExAccount>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<WebExAccount>)InstanceFactory.newInstance(
							clazz.getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(WebExAccountImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = WebExAccountPersistence.class)
	protected WebExAccountPersistence webExAccountPersistence;
	@BeanReference(type = WebExSitePersistence.class)
	protected WebExSitePersistence webExSitePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	private static final String _SQL_SELECT_WEBEXACCOUNT = "SELECT webExAccount FROM WebExAccount webExAccount";
	private static final String _SQL_SELECT_WEBEXACCOUNT_WHERE = "SELECT webExAccount FROM WebExAccount webExAccount WHERE ";
	private static final String _SQL_COUNT_WEBEXACCOUNT = "SELECT COUNT(webExAccount) FROM WebExAccount webExAccount";
	private static final String _SQL_COUNT_WEBEXACCOUNT_WHERE = "SELECT COUNT(webExAccount) FROM WebExAccount webExAccount WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "webExAccount.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "webExAccount.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(webExAccount.uuid IS NULL OR webExAccount.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "webExAccount.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "webExAccount.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(webExAccount.uuid IS NULL OR webExAccount.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "webExAccount.groupId = ?";
	private static final String _FINDER_COLUMN_G_W_GROUPID_2 = "webExAccount.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_W_WEBEXSITEID_2 = "webExAccount.webExSiteId = ?";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "webExAccount.webExAccountId";
	private static final String _FILTER_SQL_SELECT_WEBEXACCOUNT_WHERE = "SELECT DISTINCT {webExAccount.*} FROM WebEx_WebExAccount webExAccount WHERE ";
	private static final String _FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {WebEx_WebExAccount.*} FROM (SELECT DISTINCT webExAccount.webExAccountId FROM WebEx_WebExAccount webExAccount WHERE ";
	private static final String _FILTER_SQL_SELECT_WEBEXACCOUNT_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN WebEx_WebExAccount ON TEMP_TABLE.webExAccountId = WebEx_WebExAccount.webExAccountId";
	private static final String _FILTER_SQL_COUNT_WEBEXACCOUNT_WHERE = "SELECT COUNT(DISTINCT webExAccount.webExAccountId) AS COUNT_VALUE FROM WebEx_WebExAccount webExAccount WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "webExAccount";
	private static final String _FILTER_ENTITY_TABLE = "WebEx_WebExAccount";
	private static final String _ORDER_BY_ENTITY_ALIAS = "webExAccount.";
	private static final String _ORDER_BY_ENTITY_TABLE = "WebEx_WebExAccount.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WebExAccount exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WebExAccount exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WebExAccountPersistenceImpl.class);
	private static WebExAccount _nullWebExAccount = new WebExAccountImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WebExAccount> toCacheModel() {
				return _nullWebExAccountCacheModel;
			}
		};

	private static CacheModel<WebExAccount> _nullWebExAccountCacheModel = new CacheModel<WebExAccount>() {
			public WebExAccount toEntityModel() {
				return _nullWebExAccount;
			}
		};
}