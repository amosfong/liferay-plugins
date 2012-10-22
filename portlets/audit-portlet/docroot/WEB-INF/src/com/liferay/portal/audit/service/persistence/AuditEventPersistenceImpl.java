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

package com.liferay.portal.audit.service.persistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.audit.NoSuchEventException;
import com.liferay.portal.audit.model.AuditEvent;
import com.liferay.portal.audit.model.impl.AuditEventImpl;
import com.liferay.portal.audit.model.impl.AuditEventModelImpl;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the audit event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEventPersistence
 * @see AuditEventUtil
 * @generated
 */
public class AuditEventPersistenceImpl extends BasePersistenceImpl<AuditEvent>
	implements AuditEventPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AuditEventUtil} to access the audit event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AuditEventImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
			AuditEventModelImpl.FINDER_CACHE_ENABLED, AuditEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
			AuditEventModelImpl.FINDER_CACHE_ENABLED, AuditEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			AuditEventModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
			AuditEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
			AuditEventModelImpl.FINDER_CACHE_ENABLED, AuditEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
			AuditEventModelImpl.FINDER_CACHE_ENABLED, AuditEventImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
			AuditEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the audit event in the entity cache if it is enabled.
	 *
	 * @param auditEvent the audit event
	 */
	public void cacheResult(AuditEvent auditEvent) {
		EntityCacheUtil.putResult(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
			AuditEventImpl.class, auditEvent.getPrimaryKey(), auditEvent);

		auditEvent.resetOriginalValues();
	}

	/**
	 * Caches the audit events in the entity cache if it is enabled.
	 *
	 * @param auditEvents the audit events
	 */
	public void cacheResult(List<AuditEvent> auditEvents) {
		for (AuditEvent auditEvent : auditEvents) {
			if (EntityCacheUtil.getResult(
						AuditEventModelImpl.ENTITY_CACHE_ENABLED,
						AuditEventImpl.class, auditEvent.getPrimaryKey()) == null) {
				cacheResult(auditEvent);
			}
			else {
				auditEvent.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all audit events.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(AuditEventImpl.class.getName());
		}

		EntityCacheUtil.clearCache(AuditEventImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the audit event.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AuditEvent auditEvent) {
		EntityCacheUtil.removeResult(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
			AuditEventImpl.class, auditEvent.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<AuditEvent> auditEvents) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AuditEvent auditEvent : auditEvents) {
			EntityCacheUtil.removeResult(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
				AuditEventImpl.class, auditEvent.getPrimaryKey());
		}
	}

	/**
	 * Creates a new audit event with the primary key. Does not add the audit event to the database.
	 *
	 * @param auditEventId the primary key for the new audit event
	 * @return the new audit event
	 */
	public AuditEvent create(long auditEventId) {
		AuditEvent auditEvent = new AuditEventImpl();

		auditEvent.setNew(true);
		auditEvent.setPrimaryKey(auditEventId);

		return auditEvent;
	}

	/**
	 * Removes the audit event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditEventId the primary key of the audit event
	 * @return the audit event that was removed
	 * @throws com.liferay.portal.audit.NoSuchEventException if a audit event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEvent remove(long auditEventId)
		throws NoSuchEventException, SystemException {
		return remove(Long.valueOf(auditEventId));
	}

	/**
	 * Removes the audit event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the audit event
	 * @return the audit event that was removed
	 * @throws com.liferay.portal.audit.NoSuchEventException if a audit event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditEvent remove(Serializable primaryKey)
		throws NoSuchEventException, SystemException {
		Session session = null;

		try {
			session = openSession();

			AuditEvent auditEvent = (AuditEvent)session.get(AuditEventImpl.class,
					primaryKey);

			if (auditEvent == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(auditEvent);
		}
		catch (NoSuchEventException nsee) {
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
	protected AuditEvent removeImpl(AuditEvent auditEvent)
		throws SystemException {
		auditEvent = toUnwrappedModel(auditEvent);

		Session session = null;

		try {
			session = openSession();

			if (auditEvent.isCachedModel()) {
				auditEvent = (AuditEvent)session.get(AuditEventImpl.class,
						auditEvent.getPrimaryKeyObj());
			}

			session.delete(auditEvent);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(auditEvent);

		return auditEvent;
	}

	@Override
	public AuditEvent updateImpl(
		com.liferay.portal.audit.model.AuditEvent auditEvent)
		throws SystemException {
		auditEvent = toUnwrappedModel(auditEvent);

		boolean isNew = auditEvent.isNew();

		AuditEventModelImpl auditEventModelImpl = (AuditEventModelImpl)auditEvent;

		Session session = null;

		try {
			session = openSession();

			if (auditEvent.isNew()) {
				session.save(auditEvent);

				auditEvent.setNew(false);
			}
			else {
				session.merge(auditEvent);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AuditEventModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((auditEventModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(auditEventModelImpl.getOriginalCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] {
						Long.valueOf(auditEventModelImpl.getCompanyId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}
		}

		EntityCacheUtil.putResult(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
			AuditEventImpl.class, auditEvent.getPrimaryKey(), auditEvent);

		return auditEvent;
	}

	protected AuditEvent toUnwrappedModel(AuditEvent auditEvent) {
		if (auditEvent instanceof AuditEventImpl) {
			return auditEvent;
		}

		AuditEventImpl auditEventImpl = new AuditEventImpl();

		auditEventImpl.setNew(auditEvent.isNew());
		auditEventImpl.setPrimaryKey(auditEvent.getPrimaryKey());

		auditEventImpl.setAuditEventId(auditEvent.getAuditEventId());
		auditEventImpl.setCompanyId(auditEvent.getCompanyId());
		auditEventImpl.setUserId(auditEvent.getUserId());
		auditEventImpl.setUserName(auditEvent.getUserName());
		auditEventImpl.setCreateDate(auditEvent.getCreateDate());
		auditEventImpl.setEventType(auditEvent.getEventType());
		auditEventImpl.setClassName(auditEvent.getClassName());
		auditEventImpl.setClassPK(auditEvent.getClassPK());
		auditEventImpl.setMessage(auditEvent.getMessage());
		auditEventImpl.setClientHost(auditEvent.getClientHost());
		auditEventImpl.setClientIP(auditEvent.getClientIP());
		auditEventImpl.setServerName(auditEvent.getServerName());
		auditEventImpl.setServerPort(auditEvent.getServerPort());
		auditEventImpl.setSessionID(auditEvent.getSessionID());
		auditEventImpl.setAdditionalInfo(auditEvent.getAdditionalInfo());

		return auditEventImpl;
	}

	/**
	 * Returns the audit event with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit event
	 * @return the audit event
	 * @throws com.liferay.portal.NoSuchModelException if a audit event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditEvent findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit event with the primary key or throws a {@link com.liferay.portal.audit.NoSuchEventException} if it could not be found.
	 *
	 * @param auditEventId the primary key of the audit event
	 * @return the audit event
	 * @throws com.liferay.portal.audit.NoSuchEventException if a audit event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEvent findByPrimaryKey(long auditEventId)
		throws NoSuchEventException, SystemException {
		AuditEvent auditEvent = fetchByPrimaryKey(auditEventId);

		if (auditEvent == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + auditEventId);
			}

			throw new NoSuchEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				auditEventId);
		}

		return auditEvent;
	}

	/**
	 * Returns the audit event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the audit event
	 * @return the audit event, or <code>null</code> if a audit event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AuditEvent fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the audit event with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditEventId the primary key of the audit event
	 * @return the audit event, or <code>null</code> if a audit event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEvent fetchByPrimaryKey(long auditEventId)
		throws SystemException {
		AuditEvent auditEvent = (AuditEvent)EntityCacheUtil.getResult(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
				AuditEventImpl.class, auditEventId);

		if (auditEvent == _nullAuditEvent) {
			return null;
		}

		if (auditEvent == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				auditEvent = (AuditEvent)session.get(AuditEventImpl.class,
						Long.valueOf(auditEventId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (auditEvent != null) {
					cacheResult(auditEvent);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(AuditEventModelImpl.ENTITY_CACHE_ENABLED,
						AuditEventImpl.class, auditEventId, _nullAuditEvent);
				}

				closeSession(session);
			}
		}

		return auditEvent;
	}

	/**
	 * Returns all the audit events where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching audit events
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEvent> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the audit events where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of audit events
	 * @param end the upper bound of the range of audit events (not inclusive)
	 * @return the range of matching audit events
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEvent> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit events where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of audit events
	 * @param end the upper bound of the range of audit events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit events
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEvent> findByCompanyId(long companyId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<AuditEvent> list = (List<AuditEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AuditEvent auditEvent : list) {
				if ((companyId != auditEvent.getCompanyId())) {
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

			query.append(_SQL_SELECT_AUDITEVENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(AuditEventModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<AuditEvent>)QueryUtil.list(q, getDialect(), start,
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
	 * Returns the first audit event in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit event
	 * @throws com.liferay.portal.audit.NoSuchEventException if a matching audit event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEvent findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		AuditEvent auditEvent = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (auditEvent != null) {
			return auditEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventException(msg.toString());
	}

	/**
	 * Returns the first audit event in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit event, or <code>null</code> if a matching audit event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEvent fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<AuditEvent> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last audit event in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit event
	 * @throws com.liferay.portal.audit.NoSuchEventException if a matching audit event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEvent findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		AuditEvent auditEvent = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (auditEvent != null) {
			return auditEvent;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEventException(msg.toString());
	}

	/**
	 * Returns the last audit event in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit event, or <code>null</code> if a matching audit event could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEvent fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<AuditEvent> list = findByCompanyId(companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the audit events before and after the current audit event in the ordered set where companyId = &#63;.
	 *
	 * @param auditEventId the primary key of the current audit event
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit event
	 * @throws com.liferay.portal.audit.NoSuchEventException if a audit event with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AuditEvent[] findByCompanyId_PrevAndNext(long auditEventId,
		long companyId, OrderByComparator orderByComparator)
		throws NoSuchEventException, SystemException {
		AuditEvent auditEvent = findByPrimaryKey(auditEventId);

		Session session = null;

		try {
			session = openSession();

			AuditEvent[] array = new AuditEventImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session, auditEvent,
					companyId, orderByComparator, true);

			array[1] = auditEvent;

			array[2] = getByCompanyId_PrevAndNext(session, auditEvent,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AuditEvent getByCompanyId_PrevAndNext(Session session,
		AuditEvent auditEvent, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AUDITEVENT_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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
			query.append(AuditEventModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(auditEvent);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AuditEvent> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the audit events.
	 *
	 * @return the audit events
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEvent> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the audit events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit events
	 * @param end the upper bound of the range of audit events (not inclusive)
	 * @return the range of audit events
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEvent> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the audit events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit events
	 * @param end the upper bound of the range of audit events (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit events
	 * @throws SystemException if a system exception occurred
	 */
	public List<AuditEvent> findAll(int start, int end,
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

		List<AuditEvent> list = (List<AuditEvent>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_AUDITEVENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AUDITEVENT.concat(AuditEventModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<AuditEvent>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<AuditEvent>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the audit events where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (AuditEvent auditEvent : findByCompanyId(companyId)) {
			remove(auditEvent);
		}
	}

	/**
	 * Removes all the audit events from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AuditEvent auditEvent : findAll()) {
			remove(auditEvent);
		}
	}

	/**
	 * Returns the number of audit events where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching audit events
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AUDITEVENT_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of audit events.
	 *
	 * @return the number of audit events
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AUDITEVENT);

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
	 * Initializes the audit event persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.audit.model.AuditEvent")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<AuditEvent>> listenersList = new ArrayList<ModelListener<AuditEvent>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<AuditEvent>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(AuditEventImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = AuditEventPersistence.class)
	protected AuditEventPersistence auditEventPersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_AUDITEVENT = "SELECT auditEvent FROM AuditEvent auditEvent";
	private static final String _SQL_SELECT_AUDITEVENT_WHERE = "SELECT auditEvent FROM AuditEvent auditEvent WHERE ";
	private static final String _SQL_COUNT_AUDITEVENT = "SELECT COUNT(auditEvent) FROM AuditEvent auditEvent";
	private static final String _SQL_COUNT_AUDITEVENT_WHERE = "SELECT COUNT(auditEvent) FROM AuditEvent auditEvent WHERE ";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "auditEvent.companyId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "auditEvent.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AuditEvent exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AuditEvent exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(AuditEventPersistenceImpl.class);
	private static AuditEvent _nullAuditEvent = new AuditEventImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AuditEvent> toCacheModel() {
				return _nullAuditEventCacheModel;
			}
		};

	private static CacheModel<AuditEvent> _nullAuditEventCacheModel = new CacheModel<AuditEvent>() {
			public AuditEvent toEntityModel() {
				return _nullAuditEvent;
			}
		};
}