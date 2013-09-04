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

import com.liferay.meeting.webex.NoSuchSiteException;
import com.liferay.meeting.webex.model.WebExSite;
import com.liferay.meeting.webex.model.impl.WebExSiteImpl;
import com.liferay.meeting.webex.model.impl.WebExSiteModelImpl;

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
 * The persistence implementation for the web ex site service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Anant Singh
 * @see WebExSitePersistence
 * @see WebExSiteUtil
 * @generated
 */
public class WebExSitePersistenceImpl extends BasePersistenceImpl<WebExSite>
	implements WebExSitePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link WebExSiteUtil} to access the web ex site persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = WebExSiteImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			WebExSiteModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			WebExSiteModelImpl.UUID_COLUMN_BITMASK |
			WebExSiteModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			WebExSiteModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FETCH_BY_SITEKEY = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchBySiteKey",
			new String[] { Long.class.getName() },
			WebExSiteModelImpl.SITEKEY_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SITEKEY = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySiteKey",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, WebExSiteImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the web ex site in the entity cache if it is enabled.
	 *
	 * @param webExSite the web ex site
	 */
	public void cacheResult(WebExSite webExSite) {
		EntityCacheUtil.putResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteImpl.class, webExSite.getPrimaryKey(), webExSite);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] {
				webExSite.getUuid(), Long.valueOf(webExSite.getGroupId())
			}, webExSite);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SITEKEY,
			new Object[] { Long.valueOf(webExSite.getSiteKey()) }, webExSite);

		webExSite.resetOriginalValues();
	}

	/**
	 * Caches the web ex sites in the entity cache if it is enabled.
	 *
	 * @param webExSites the web ex sites
	 */
	public void cacheResult(List<WebExSite> webExSites) {
		for (WebExSite webExSite : webExSites) {
			if (EntityCacheUtil.getResult(
						WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
						WebExSiteImpl.class, webExSite.getPrimaryKey()) == null) {
				cacheResult(webExSite);
			}
			else {
				webExSite.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all web ex sites.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(WebExSiteImpl.class.getName());
		}

		EntityCacheUtil.clearCache(WebExSiteImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the web ex site.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(WebExSite webExSite) {
		EntityCacheUtil.removeResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteImpl.class, webExSite.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(webExSite);
	}

	@Override
	public void clearCache(List<WebExSite> webExSites) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (WebExSite webExSite : webExSites) {
			EntityCacheUtil.removeResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
				WebExSiteImpl.class, webExSite.getPrimaryKey());

			clearUniqueFindersCache(webExSite);
		}
	}

	protected void cacheUniqueFindersCache(WebExSite webExSite) {
		if (webExSite.isNew()) {
			Object[] args = new Object[] {
					webExSite.getUuid(), Long.valueOf(webExSite.getGroupId())
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				webExSite);

			args = new Object[] { Long.valueOf(webExSite.getSiteKey()) };

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SITEKEY, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SITEKEY, args,
				webExSite);
		}
		else {
			WebExSiteModelImpl webExSiteModelImpl = (WebExSiteModelImpl)webExSite;

			if ((webExSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExSite.getUuid(),
						Long.valueOf(webExSite.getGroupId())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					webExSite);
			}

			if ((webExSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_SITEKEY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(webExSite.getSiteKey())
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SITEKEY, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SITEKEY, args,
					webExSite);
			}
		}
	}

	protected void clearUniqueFindersCache(WebExSite webExSite) {
		WebExSiteModelImpl webExSiteModelImpl = (WebExSiteModelImpl)webExSite;

		Object[] args = new Object[] {
				webExSite.getUuid(), Long.valueOf(webExSite.getGroupId())
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((webExSiteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					webExSiteModelImpl.getOriginalUuid(),
					Long.valueOf(webExSiteModelImpl.getOriginalGroupId())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] { Long.valueOf(webExSite.getSiteKey()) };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SITEKEY, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SITEKEY, args);

		if ((webExSiteModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_SITEKEY.getColumnBitmask()) != 0) {
			args = new Object[] {
					Long.valueOf(webExSiteModelImpl.getOriginalSiteKey())
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SITEKEY, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SITEKEY, args);
		}
	}

	/**
	 * Creates a new web ex site with the primary key. Does not add the web ex site to the database.
	 *
	 * @param webExSiteId the primary key for the new web ex site
	 * @return the new web ex site
	 */
	public WebExSite create(long webExSiteId) {
		WebExSite webExSite = new WebExSiteImpl();

		webExSite.setNew(true);
		webExSite.setPrimaryKey(webExSiteId);

		String uuid = PortalUUIDUtil.generate();

		webExSite.setUuid(uuid);

		return webExSite;
	}

	/**
	 * Removes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param webExSiteId the primary key of the web ex site
	 * @return the web ex site that was removed
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite remove(long webExSiteId)
		throws NoSuchSiteException, SystemException {
		return remove(Long.valueOf(webExSiteId));
	}

	/**
	 * Removes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the web ex site
	 * @return the web ex site that was removed
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WebExSite remove(Serializable primaryKey)
		throws NoSuchSiteException, SystemException {
		Session session = null;

		try {
			session = openSession();

			WebExSite webExSite = (WebExSite)session.get(WebExSiteImpl.class,
					primaryKey);

			if (webExSite == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSiteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(webExSite);
		}
		catch (NoSuchSiteException nsee) {
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
	protected WebExSite removeImpl(WebExSite webExSite)
		throws SystemException {
		webExSite = toUnwrappedModel(webExSite);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, webExSite);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(webExSite);

		return webExSite;
	}

	@Override
	public WebExSite updateImpl(
		com.liferay.meeting.webex.model.WebExSite webExSite, boolean merge)
		throws SystemException {
		webExSite = toUnwrappedModel(webExSite);

		boolean isNew = webExSite.isNew();

		WebExSiteModelImpl webExSiteModelImpl = (WebExSiteModelImpl)webExSite;

		if (Validator.isNull(webExSite.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			webExSite.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, webExSite, merge);

			webExSite.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !WebExSiteModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((webExSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						webExSiteModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { webExSiteModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((webExSiteModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(webExSiteModelImpl.getOriginalGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] {
						Long.valueOf(webExSiteModelImpl.getGroupId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}
		}

		EntityCacheUtil.putResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
			WebExSiteImpl.class, webExSite.getPrimaryKey(), webExSite);

		clearUniqueFindersCache(webExSite);
		cacheUniqueFindersCache(webExSite);

		return webExSite;
	}

	protected WebExSite toUnwrappedModel(WebExSite webExSite) {
		if (webExSite instanceof WebExSiteImpl) {
			return webExSite;
		}

		WebExSiteImpl webExSiteImpl = new WebExSiteImpl();

		webExSiteImpl.setNew(webExSite.isNew());
		webExSiteImpl.setPrimaryKey(webExSite.getPrimaryKey());

		webExSiteImpl.setUuid(webExSite.getUuid());
		webExSiteImpl.setWebExSiteId(webExSite.getWebExSiteId());
		webExSiteImpl.setGroupId(webExSite.getGroupId());
		webExSiteImpl.setCompanyId(webExSite.getCompanyId());
		webExSiteImpl.setUserId(webExSite.getUserId());
		webExSiteImpl.setUserName(webExSite.getUserName());
		webExSiteImpl.setCreateDate(webExSite.getCreateDate());
		webExSiteImpl.setModifiedDate(webExSite.getModifiedDate());
		webExSiteImpl.setName(webExSite.getName());
		webExSiteImpl.setApiURL(webExSite.getApiURL());
		webExSiteImpl.setLogin(webExSite.getLogin());
		webExSiteImpl.setPassword(webExSite.getPassword());
		webExSiteImpl.setPartnerKey(webExSite.getPartnerKey());
		webExSiteImpl.setSiteKey(webExSite.getSiteKey());

		return webExSiteImpl;
	}

	/**
	 * Returns the web ex site with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the web ex site
	 * @return the web ex site
	 * @throws com.liferay.portal.NoSuchModelException if a web ex site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WebExSite findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the web ex site with the primary key or throws a {@link com.liferay.meeting.webex.NoSuchSiteException} if it could not be found.
	 *
	 * @param webExSiteId the primary key of the web ex site
	 * @return the web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite findByPrimaryKey(long webExSiteId)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = fetchByPrimaryKey(webExSiteId);

		if (webExSite == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + webExSiteId);
			}

			throw new NoSuchSiteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				webExSiteId);
		}

		return webExSite;
	}

	/**
	 * Returns the web ex site with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the web ex site
	 * @return the web ex site, or <code>null</code> if a web ex site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public WebExSite fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the web ex site with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param webExSiteId the primary key of the web ex site
	 * @return the web ex site, or <code>null</code> if a web ex site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite fetchByPrimaryKey(long webExSiteId)
		throws SystemException {
		WebExSite webExSite = (WebExSite)EntityCacheUtil.getResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
				WebExSiteImpl.class, webExSiteId);

		if (webExSite == _nullWebExSite) {
			return null;
		}

		if (webExSite == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				webExSite = (WebExSite)session.get(WebExSiteImpl.class,
						Long.valueOf(webExSiteId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (webExSite != null) {
					cacheResult(webExSite);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(WebExSiteModelImpl.ENTITY_CACHE_ENABLED,
						WebExSiteImpl.class, webExSiteId, _nullWebExSite);
				}

				closeSession(session);
			}
		}

		return webExSite;
	}

	/**
	 * Returns all the web ex sites where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex sites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @return the range of matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex sites where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> findByUuid(String uuid, int start, int end,
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

		List<WebExSite> list = (List<WebExSite>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WebExSite webExSite : list) {
				if (!Validator.equals(uuid, webExSite.getUuid())) {
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

			query.append(_SQL_SELECT_WEBEXSITE_WHERE);

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

				list = (List<WebExSite>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = fetchByUuid_First(uuid, orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the first web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<WebExSite> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = fetchByUuid_Last(uuid, orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the last web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<WebExSite> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex sites before and after the current web ex site in the ordered set where uuid = &#63;.
	 *
	 * @param webExSiteId the primary key of the current web ex site
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite[] findByUuid_PrevAndNext(long webExSiteId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = findByPrimaryKey(webExSiteId);

		Session session = null;

		try {
			session = openSession();

			WebExSite[] array = new WebExSiteImpl[3];

			array[0] = getByUuid_PrevAndNext(session, webExSite, uuid,
					orderByComparator, true);

			array[1] = webExSite;

			array[2] = getByUuid_PrevAndNext(session, webExSite, uuid,
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

	protected WebExSite getByUuid_PrevAndNext(Session session,
		WebExSite webExSite, String uuid, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WEBEXSITE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(webExSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the web ex site where uuid = &#63; and groupId = &#63; or throws a {@link com.liferay.meeting.webex.NoSuchSiteException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite findByUUID_G(String uuid, long groupId)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = fetchByUUID_G(uuid, groupId);

		if (webExSite == null) {
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

			throw new NoSuchSiteException(msg.toString());
		}

		return webExSite;
	}

	/**
	 * Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite fetchByUUID_G(String uuid, long groupId)
		throws SystemException {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the web ex site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof WebExSite) {
			WebExSite webExSite = (WebExSite)result;

			if (!Validator.equals(uuid, webExSite.getUuid()) ||
					(groupId != webExSite.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_WEBEXSITE_WHERE);

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

				List<WebExSite> list = q.list();

				result = list;

				WebExSite webExSite = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					webExSite = list.get(0);

					cacheResult(webExSite);

					if ((webExSite.getUuid() == null) ||
							!webExSite.getUuid().equals(uuid) ||
							(webExSite.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, webExSite);
					}
				}

				return webExSite;
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
				return (WebExSite)result;
			}
		}
	}

	/**
	 * Returns all the web ex sites where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex sites where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @return the range of matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex sites where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> findByGroupId(long groupId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<WebExSite> list = (List<WebExSite>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (WebExSite webExSite : list) {
				if ((groupId != webExSite.getGroupId())) {
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

			query.append(_SQL_SELECT_WEBEXSITE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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

				list = (List<WebExSite>)QueryUtil.list(q, getDialect(), start,
						end);
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
	 * Returns the first web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = fetchByGroupId_First(groupId, orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the first web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<WebExSite> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = fetchByGroupId_Last(groupId, orderByComparator);

		if (webExSite != null) {
			return webExSite;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchSiteException(msg.toString());
	}

	/**
	 * Returns the last web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		List<WebExSite> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the web ex sites before and after the current web ex site in the ordered set where groupId = &#63;.
	 *
	 * @param webExSiteId the primary key of the current web ex site
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite[] findByGroupId_PrevAndNext(long webExSiteId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = findByPrimaryKey(webExSiteId);

		Session session = null;

		try {
			session = openSession();

			WebExSite[] array = new WebExSiteImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, webExSite, groupId,
					orderByComparator, true);

			array[1] = webExSite;

			array[2] = getByGroupId_PrevAndNext(session, webExSite, groupId,
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

	protected WebExSite getByGroupId_PrevAndNext(Session session,
		WebExSite webExSite, long groupId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_WEBEXSITE_WHERE);

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

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(webExSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the web ex sites that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching web ex sites that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> filterFindByGroupId(long groupId)
		throws SystemException {
		return filterFindByGroupId(groupId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex sites that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @return the range of matching web ex sites that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> filterFindByGroupId(long groupId, int start, int end)
		throws SystemException {
		return filterFindByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex sites that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching web ex sites that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> filterFindByGroupId(long groupId, int start,
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
			query = new StringBundler(2);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_2);
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
				WebExSite.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, WebExSiteImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, WebExSiteImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			return (List<WebExSite>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the web ex sites before and after the current web ex site in the ordered set of web ex sites that the user has permission to view where groupId = &#63;.
	 *
	 * @param webExSiteId the primary key of the current web ex site
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a web ex site with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite[] filterFindByGroupId_PrevAndNext(long webExSiteId,
		long groupId, OrderByComparator orderByComparator)
		throws NoSuchSiteException, SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(webExSiteId, groupId,
				orderByComparator);
		}

		WebExSite webExSite = findByPrimaryKey(webExSiteId);

		Session session = null;

		try {
			session = openSession();

			WebExSite[] array = new WebExSiteImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(session, webExSite,
					groupId, orderByComparator, true);

			array[1] = webExSite;

			array[2] = filterGetByGroupId_PrevAndNext(session, webExSite,
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

	protected WebExSite filterGetByGroupId_PrevAndNext(Session session,
		WebExSite webExSite, long groupId, OrderByComparator orderByComparator,
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
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_2);
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
				WebExSite.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, WebExSiteImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, WebExSiteImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(webExSite);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<WebExSite> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the web ex site where siteKey = &#63; or throws a {@link com.liferay.meeting.webex.NoSuchSiteException} if it could not be found.
	 *
	 * @param siteKey the site key
	 * @return the matching web ex site
	 * @throws com.liferay.meeting.webex.NoSuchSiteException if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite findBySiteKey(long siteKey)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = fetchBySiteKey(siteKey);

		if (webExSite == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("siteKey=");
			msg.append(siteKey);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchSiteException(msg.toString());
		}

		return webExSite;
	}

	/**
	 * Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param siteKey the site key
	 * @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite fetchBySiteKey(long siteKey) throws SystemException {
		return fetchBySiteKey(siteKey, true);
	}

	/**
	 * Returns the web ex site where siteKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param siteKey the site key
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching web ex site, or <code>null</code> if a matching web ex site could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite fetchBySiteKey(long siteKey, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { siteKey };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SITEKEY,
					finderArgs, this);
		}

		if (result instanceof WebExSite) {
			WebExSite webExSite = (WebExSite)result;

			if ((siteKey != webExSite.getSiteKey())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_WEBEXSITE_WHERE);

			query.append(_FINDER_COLUMN_SITEKEY_SITEKEY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(siteKey);

				List<WebExSite> list = q.list();

				result = list;

				WebExSite webExSite = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SITEKEY,
						finderArgs, list);
				}
				else {
					webExSite = list.get(0);

					cacheResult(webExSite);

					if ((webExSite.getSiteKey() != siteKey)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SITEKEY,
							finderArgs, webExSite);
					}
				}

				return webExSite;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SITEKEY,
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
				return (WebExSite)result;
			}
		}
	}

	/**
	 * Returns all the web ex sites.
	 *
	 * @return the web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the web ex sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @return the range of web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the web ex sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of web ex sites
	 * @param end the upper bound of the range of web ex sites (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public List<WebExSite> findAll(int start, int end,
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

		List<WebExSite> list = (List<WebExSite>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_WEBEXSITE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_WEBEXSITE;
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<WebExSite>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the web ex sites where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (WebExSite webExSite : findByUuid(uuid)) {
			remove(webExSite);
		}
	}

	/**
	 * Removes the web ex site where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the web ex site that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite removeByUUID_G(String uuid, long groupId)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = findByUUID_G(uuid, groupId);

		return remove(webExSite);
	}

	/**
	 * Removes all the web ex sites where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGroupId(long groupId) throws SystemException {
		for (WebExSite webExSite : findByGroupId(groupId)) {
			remove(webExSite);
		}
	}

	/**
	 * Removes the web ex site where siteKey = &#63; from the database.
	 *
	 * @param siteKey the site key
	 * @return the web ex site that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public WebExSite removeBySiteKey(long siteKey)
		throws NoSuchSiteException, SystemException {
		WebExSite webExSite = findBySiteKey(siteKey);

		return remove(webExSite);
	}

	/**
	 * Removes all the web ex sites from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (WebExSite webExSite : findAll()) {
			remove(webExSite);
		}
	}

	/**
	 * Returns the number of web ex sites where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {
		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WEBEXSITE_WHERE);

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
	 * Returns the number of web ex sites where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUUID_G(String uuid, long groupId)
		throws SystemException {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_UUID_G,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_WEBEXSITE_WHERE);

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
	 * Returns the number of web ex sites where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGroupId(long groupId) throws SystemException {
		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_GROUPID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WEBEXSITE_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

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

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_GROUPID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of web ex sites that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching web ex sites that the user has permission to view
	 * @throws SystemException if a system exception occurred
	 */
	public int filterCountByGroupId(long groupId) throws SystemException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler query = new StringBundler(2);

		query.append(_FILTER_SQL_COUNT_WEBEXSITE_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				WebExSite.class.getName(),
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

	/**
	 * Returns the number of web ex sites where siteKey = &#63;.
	 *
	 * @param siteKey the site key
	 * @return the number of matching web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySiteKey(long siteKey) throws SystemException {
		Object[] finderArgs = new Object[] { siteKey };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_SITEKEY,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_WEBEXSITE_WHERE);

			query.append(_FINDER_COLUMN_SITEKEY_SITEKEY_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(siteKey);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SITEKEY,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of web ex sites.
	 *
	 * @return the number of web ex sites
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_WEBEXSITE);

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
	 * Initializes the web ex site persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.meeting.webex.model.WebExSite")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<WebExSite>> listenersList = new ArrayList<ModelListener<WebExSite>>();

				for (String listenerClassName : listenerClassNames) {
					Class<?> clazz = getClass();

					listenersList.add((ModelListener<WebExSite>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(WebExSiteImpl.class.getName());
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
	private static final String _SQL_SELECT_WEBEXSITE = "SELECT webExSite FROM WebExSite webExSite";
	private static final String _SQL_SELECT_WEBEXSITE_WHERE = "SELECT webExSite FROM WebExSite webExSite WHERE ";
	private static final String _SQL_COUNT_WEBEXSITE = "SELECT COUNT(webExSite) FROM WebExSite webExSite";
	private static final String _SQL_COUNT_WEBEXSITE_WHERE = "SELECT COUNT(webExSite) FROM WebExSite webExSite WHERE ";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "webExSite.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "webExSite.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(webExSite.uuid IS NULL OR webExSite.uuid = ?)";
	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "webExSite.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "webExSite.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(webExSite.uuid IS NULL OR webExSite.uuid = ?) AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "webExSite.groupId = ?";
	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "webExSite.groupId = ?";
	private static final String _FINDER_COLUMN_SITEKEY_SITEKEY_2 = "webExSite.siteKey = ?";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "webExSite.webExSiteId";
	private static final String _FILTER_SQL_SELECT_WEBEXSITE_WHERE = "SELECT DISTINCT {webExSite.*} FROM WebEx_WebExSite webExSite WHERE ";
	private static final String _FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {WebEx_WebExSite.*} FROM (SELECT DISTINCT webExSite.webExSiteId FROM WebEx_WebExSite webExSite WHERE ";
	private static final String _FILTER_SQL_SELECT_WEBEXSITE_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN WebEx_WebExSite ON TEMP_TABLE.webExSiteId = WebEx_WebExSite.webExSiteId";
	private static final String _FILTER_SQL_COUNT_WEBEXSITE_WHERE = "SELECT COUNT(DISTINCT webExSite.webExSiteId) AS COUNT_VALUE FROM WebEx_WebExSite webExSite WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "webExSite";
	private static final String _FILTER_ENTITY_TABLE = "WebEx_WebExSite";
	private static final String _ORDER_BY_ENTITY_ALIAS = "webExSite.";
	private static final String _ORDER_BY_ENTITY_TABLE = "WebEx_WebExSite.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No WebExSite exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No WebExSite exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(WebExSitePersistenceImpl.class);
	private static WebExSite _nullWebExSite = new WebExSiteImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<WebExSite> toCacheModel() {
				return _nullWebExSiteCacheModel;
			}
		};

	private static CacheModel<WebExSite> _nullWebExSiteCacheModel = new CacheModel<WebExSite>() {
			public WebExSite toEntityModel() {
				return _nullWebExSite;
			}
		};
}