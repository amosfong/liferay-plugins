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

package com.liferay.portal.workflow.kaleo.designer.service.persistence;

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
import com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionImpl;
import com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the kaleo draft definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinitionPersistence
 * @see KaleoDraftDefinitionUtil
 * @generated
 */
public class KaleoDraftDefinitionPersistenceImpl extends BasePersistenceImpl<KaleoDraftDefinition>
	implements KaleoDraftDefinitionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KaleoDraftDefinitionUtil} to access the kaleo draft definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KaleoDraftDefinitionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID =
		new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyId",
			new String[] { Long.class.getName() },
			KaleoDraftDefinitionModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoDraftDefinitionModelImpl.DRAFTVERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the kaleo draft definitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoDraftDefinition> findByCompanyId(long companyId)
		throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the kaleo draft definitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo draft definitions
	 * @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	 * @return the range of matching kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoDraftDefinition> findByCompanyId(long companyId,
		int start, int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo draft definitions where companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param start the lower bound of the range of kaleo draft definitions
	 * @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoDraftDefinition> findByCompanyId(long companyId,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_COMPANYID;
			finderArgs = new Object[] { companyId, start, end, orderByComparator };
		}

		List<KaleoDraftDefinition> list = (List<KaleoDraftDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoDraftDefinition kaleoDraftDefinition : list) {
				if ((companyId != kaleoDraftDefinition.getCompanyId())) {
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

			query.append(_SQL_SELECT_KALEODRAFTDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoDraftDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (!pagination) {
					list = (List<KaleoDraftDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoDraftDefinition>(list);
				}
				else {
					list = (List<KaleoDraftDefinition>)QueryUtil.list(q,
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
	 * Returns the first kaleo draft definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo draft definition
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition findByCompanyId_First(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = fetchByCompanyId_First(companyId,
				orderByComparator);

		if (kaleoDraftDefinition != null) {
			return kaleoDraftDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchKaleoDraftDefinitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo draft definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition fetchByCompanyId_First(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<KaleoDraftDefinition> list = findByCompanyId(companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo draft definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo draft definition
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition findByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = fetchByCompanyId_Last(companyId,
				orderByComparator);

		if (kaleoDraftDefinition != null) {
			return kaleoDraftDefinition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchKaleoDraftDefinitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo draft definition in the ordered set where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition fetchByCompanyId_Last(long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		if (count == 0) {
			return null;
		}

		List<KaleoDraftDefinition> list = findByCompanyId(companyId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo draft definitions before and after the current kaleo draft definition in the ordered set where companyId = &#63;.
	 *
	 * @param kaleoDraftDefinitionId the primary key of the current kaleo draft definition
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo draft definition
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition[] findByCompanyId_PrevAndNext(
		long kaleoDraftDefinitionId, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = findByPrimaryKey(kaleoDraftDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDraftDefinition[] array = new KaleoDraftDefinitionImpl[3];

			array[0] = getByCompanyId_PrevAndNext(session,
					kaleoDraftDefinition, companyId, orderByComparator, true);

			array[1] = kaleoDraftDefinition;

			array[2] = getByCompanyId_PrevAndNext(session,
					kaleoDraftDefinition, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDraftDefinition getByCompanyId_PrevAndNext(Session session,
		KaleoDraftDefinition kaleoDraftDefinition, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODRAFTDEFINITION_WHERE);

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
			query.append(KaleoDraftDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoDraftDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDraftDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo draft definitions where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCompanyId(long companyId) throws SystemException {
		for (KaleoDraftDefinition kaleoDraftDefinition : findByCompanyId(
				companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kaleoDraftDefinition);
		}
	}

	/**
	 * Returns the number of kaleo draft definitions where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCompanyId(long companyId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_COMPANYID;

		Object[] finderArgs = new Object[] { companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KALEODRAFTDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

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

	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "kaleoDraftDefinition.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_V = new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_V = new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			},
			KaleoDraftDefinitionModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoDraftDefinitionModelImpl.NAME_COLUMN_BITMASK |
			KaleoDraftDefinitionModelImpl.VERSION_COLUMN_BITMASK |
			KaleoDraftDefinitionModelImpl.DRAFTVERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_V = new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N_V",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName()
			});

	/**
	 * Returns all the kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the matching kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoDraftDefinition> findByC_N_V(long companyId, String name,
		int version) throws SystemException {
		return findByC_N_V(companyId, name, version, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param start the lower bound of the range of kaleo draft definitions
	 * @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	 * @return the range of matching kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoDraftDefinition> findByC_N_V(long companyId, String name,
		int version, int start, int end) throws SystemException {
		return findByC_N_V(companyId, name, version, start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param start the lower bound of the range of kaleo draft definitions
	 * @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoDraftDefinition> findByC_N_V(long companyId, String name,
		int version, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_V;
			finderArgs = new Object[] { companyId, name, version };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_N_V;
			finderArgs = new Object[] {
					companyId, name, version,
					
					start, end, orderByComparator
				};
		}

		List<KaleoDraftDefinition> list = (List<KaleoDraftDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KaleoDraftDefinition kaleoDraftDefinition : list) {
				if ((companyId != kaleoDraftDefinition.getCompanyId()) ||
						!Validator.equals(name, kaleoDraftDefinition.getName()) ||
						(version != kaleoDraftDefinition.getVersion())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_KALEODRAFTDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_V_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_V_VERSION_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KaleoDraftDefinitionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(version);

				if (!pagination) {
					list = (List<KaleoDraftDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoDraftDefinition>(list);
				}
				else {
					list = (List<KaleoDraftDefinition>)QueryUtil.list(q,
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
	 * Returns the first kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo draft definition
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition findByC_N_V_First(long companyId, String name,
		int version, OrderByComparator orderByComparator)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = fetchByC_N_V_First(companyId,
				name, version, orderByComparator);

		if (kaleoDraftDefinition != null) {
			return kaleoDraftDefinition;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", version=");
		msg.append(version);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchKaleoDraftDefinitionException(msg.toString());
	}

	/**
	 * Returns the first kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition fetchByC_N_V_First(long companyId, String name,
		int version, OrderByComparator orderByComparator)
		throws SystemException {
		List<KaleoDraftDefinition> list = findByC_N_V(companyId, name, version,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo draft definition
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition findByC_N_V_Last(long companyId, String name,
		int version, OrderByComparator orderByComparator)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = fetchByC_N_V_Last(companyId,
				name, version, orderByComparator);

		if (kaleoDraftDefinition != null) {
			return kaleoDraftDefinition;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", name=");
		msg.append(name);

		msg.append(", version=");
		msg.append(version);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchKaleoDraftDefinitionException(msg.toString());
	}

	/**
	 * Returns the last kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition fetchByC_N_V_Last(long companyId, String name,
		int version, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByC_N_V(companyId, name, version);

		if (count == 0) {
			return null;
		}

		List<KaleoDraftDefinition> list = findByC_N_V(companyId, name, version,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the kaleo draft definitions before and after the current kaleo draft definition in the ordered set where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param kaleoDraftDefinitionId the primary key of the current kaleo draft definition
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next kaleo draft definition
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition[] findByC_N_V_PrevAndNext(
		long kaleoDraftDefinitionId, long companyId, String name, int version,
		OrderByComparator orderByComparator)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = findByPrimaryKey(kaleoDraftDefinitionId);

		Session session = null;

		try {
			session = openSession();

			KaleoDraftDefinition[] array = new KaleoDraftDefinitionImpl[3];

			array[0] = getByC_N_V_PrevAndNext(session, kaleoDraftDefinition,
					companyId, name, version, orderByComparator, true);

			array[1] = kaleoDraftDefinition;

			array[2] = getByC_N_V_PrevAndNext(session, kaleoDraftDefinition,
					companyId, name, version, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KaleoDraftDefinition getByC_N_V_PrevAndNext(Session session,
		KaleoDraftDefinition kaleoDraftDefinition, long companyId, String name,
		int version, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KALEODRAFTDEFINITION_WHERE);

		query.append(_FINDER_COLUMN_C_N_V_COMPANYID_2);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_C_N_V_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_N_V_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_C_N_V_NAME_2);
		}

		query.append(_FINDER_COLUMN_C_N_V_VERSION_2);

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
			query.append(KaleoDraftDefinitionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindName) {
			qPos.add(name);
		}

		qPos.add(version);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kaleoDraftDefinition);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KaleoDraftDefinition> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_N_V(long companyId, String name, int version)
		throws SystemException {
		for (KaleoDraftDefinition kaleoDraftDefinition : findByC_N_V(
				companyId, name, version, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				null)) {
			remove(kaleoDraftDefinition);
		}
	}

	/**
	 * Returns the number of kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @return the number of matching kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_N_V(long companyId, String name, int version)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N_V;

		Object[] finderArgs = new Object[] { companyId, name, version };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KALEODRAFTDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_V_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_V_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_V_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_V_VERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(version);

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

	private static final String _FINDER_COLUMN_C_N_V_COMPANYID_2 = "kaleoDraftDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_V_NAME_1 = "kaleoDraftDefinition.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_V_NAME_2 = "kaleoDraftDefinition.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_V_NAME_3 = "(kaleoDraftDefinition.name IS NULL OR kaleoDraftDefinition.name = '') AND ";
	private static final String _FINDER_COLUMN_C_N_V_VERSION_2 = "kaleoDraftDefinition.version = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_N_V_D = new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByC_N_V_D",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			},
			KaleoDraftDefinitionModelImpl.COMPANYID_COLUMN_BITMASK |
			KaleoDraftDefinitionModelImpl.NAME_COLUMN_BITMASK |
			KaleoDraftDefinitionModelImpl.VERSION_COLUMN_BITMASK |
			KaleoDraftDefinitionModelImpl.DRAFTVERSION_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_N_V_D = new FinderPath(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_N_V_D",
			new String[] {
				Long.class.getName(), String.class.getName(),
				Integer.class.getName(), Integer.class.getName()
			});

	/**
	 * Returns the kaleo draft definition where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param draftVersion the draft version
	 * @return the matching kaleo draft definition
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition findByC_N_V_D(long companyId, String name,
		int version, int draftVersion)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = fetchByC_N_V_D(companyId,
				name, version, draftVersion);

		if (kaleoDraftDefinition == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", name=");
			msg.append(name);

			msg.append(", version=");
			msg.append(version);

			msg.append(", draftVersion=");
			msg.append(draftVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchKaleoDraftDefinitionException(msg.toString());
		}

		return kaleoDraftDefinition;
	}

	/**
	 * Returns the kaleo draft definition where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param draftVersion the draft version
	 * @return the matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition fetchByC_N_V_D(long companyId, String name,
		int version, int draftVersion) throws SystemException {
		return fetchByC_N_V_D(companyId, name, version, draftVersion, true);
	}

	/**
	 * Returns the kaleo draft definition where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param draftVersion the draft version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching kaleo draft definition, or <code>null</code> if a matching kaleo draft definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition fetchByC_N_V_D(long companyId, String name,
		int version, int draftVersion, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				companyId, name, version, draftVersion
			};

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_N_V_D,
					finderArgs, this);
		}

		if (result instanceof KaleoDraftDefinition) {
			KaleoDraftDefinition kaleoDraftDefinition = (KaleoDraftDefinition)result;

			if ((companyId != kaleoDraftDefinition.getCompanyId()) ||
					!Validator.equals(name, kaleoDraftDefinition.getName()) ||
					(version != kaleoDraftDefinition.getVersion()) ||
					(draftVersion != kaleoDraftDefinition.getDraftVersion())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_KALEODRAFTDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_V_D_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_V_D_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_V_D_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_V_D_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_V_D_VERSION_2);

			query.append(_FINDER_COLUMN_C_N_V_D_DRAFTVERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(version);

				qPos.add(draftVersion);

				List<KaleoDraftDefinition> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V_D,
						finderArgs, list);
				}
				else {
					KaleoDraftDefinition kaleoDraftDefinition = list.get(0);

					result = kaleoDraftDefinition;

					cacheResult(kaleoDraftDefinition);

					if ((kaleoDraftDefinition.getCompanyId() != companyId) ||
							(kaleoDraftDefinition.getName() == null) ||
							!kaleoDraftDefinition.getName().equals(name) ||
							(kaleoDraftDefinition.getVersion() != version) ||
							(kaleoDraftDefinition.getDraftVersion() != draftVersion)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V_D,
							finderArgs, kaleoDraftDefinition);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V_D,
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
			return (KaleoDraftDefinition)result;
		}
	}

	/**
	 * Removes the kaleo draft definition where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param draftVersion the draft version
	 * @return the kaleo draft definition that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition removeByC_N_V_D(long companyId, String name,
		int version, int draftVersion)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = findByC_N_V_D(companyId,
				name, version, draftVersion);

		return remove(kaleoDraftDefinition);
	}

	/**
	 * Returns the number of kaleo draft definitions where companyId = &#63; and name = &#63; and version = &#63; and draftVersion = &#63;.
	 *
	 * @param companyId the company ID
	 * @param name the name
	 * @param version the version
	 * @param draftVersion the draft version
	 * @return the number of matching kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_N_V_D(long companyId, String name, int version,
		int draftVersion) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_N_V_D;

		Object[] finderArgs = new Object[] {
				companyId, name, version, draftVersion
			};

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_KALEODRAFTDEFINITION_WHERE);

			query.append(_FINDER_COLUMN_C_N_V_D_COMPANYID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_C_N_V_D_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_N_V_D_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_C_N_V_D_NAME_2);
			}

			query.append(_FINDER_COLUMN_C_N_V_D_VERSION_2);

			query.append(_FINDER_COLUMN_C_N_V_D_DRAFTVERSION_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindName) {
					qPos.add(name);
				}

				qPos.add(version);

				qPos.add(draftVersion);

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

	private static final String _FINDER_COLUMN_C_N_V_D_COMPANYID_2 = "kaleoDraftDefinition.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_N_V_D_NAME_1 = "kaleoDraftDefinition.name IS NULL AND ";
	private static final String _FINDER_COLUMN_C_N_V_D_NAME_2 = "kaleoDraftDefinition.name = ? AND ";
	private static final String _FINDER_COLUMN_C_N_V_D_NAME_3 = "(kaleoDraftDefinition.name IS NULL OR kaleoDraftDefinition.name = '') AND ";
	private static final String _FINDER_COLUMN_C_N_V_D_VERSION_2 = "kaleoDraftDefinition.version = ? AND ";
	private static final String _FINDER_COLUMN_C_N_V_D_DRAFTVERSION_2 = "kaleoDraftDefinition.draftVersion = ?";

	public KaleoDraftDefinitionPersistenceImpl() {
		setModelClass(KaleoDraftDefinition.class);
	}

	/**
	 * Caches the kaleo draft definition in the entity cache if it is enabled.
	 *
	 * @param kaleoDraftDefinition the kaleo draft definition
	 */
	@Override
	public void cacheResult(KaleoDraftDefinition kaleoDraftDefinition) {
		EntityCacheUtil.putResult(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class,
			kaleoDraftDefinition.getPrimaryKey(), kaleoDraftDefinition);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V_D,
			new Object[] {
				kaleoDraftDefinition.getCompanyId(),
				kaleoDraftDefinition.getName(),
				kaleoDraftDefinition.getVersion(),
				kaleoDraftDefinition.getDraftVersion()
			}, kaleoDraftDefinition);

		kaleoDraftDefinition.resetOriginalValues();
	}

	/**
	 * Caches the kaleo draft definitions in the entity cache if it is enabled.
	 *
	 * @param kaleoDraftDefinitions the kaleo draft definitions
	 */
	@Override
	public void cacheResult(List<KaleoDraftDefinition> kaleoDraftDefinitions) {
		for (KaleoDraftDefinition kaleoDraftDefinition : kaleoDraftDefinitions) {
			if (EntityCacheUtil.getResult(
						KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoDraftDefinitionImpl.class,
						kaleoDraftDefinition.getPrimaryKey()) == null) {
				cacheResult(kaleoDraftDefinition);
			}
			else {
				kaleoDraftDefinition.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all kaleo draft definitions.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(KaleoDraftDefinitionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(KaleoDraftDefinitionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the kaleo draft definition.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KaleoDraftDefinition kaleoDraftDefinition) {
		EntityCacheUtil.removeResult(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class, kaleoDraftDefinition.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(kaleoDraftDefinition);
	}

	@Override
	public void clearCache(List<KaleoDraftDefinition> kaleoDraftDefinitions) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KaleoDraftDefinition kaleoDraftDefinition : kaleoDraftDefinitions) {
			EntityCacheUtil.removeResult(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoDraftDefinitionImpl.class,
				kaleoDraftDefinition.getPrimaryKey());

			clearUniqueFindersCache(kaleoDraftDefinition);
		}
	}

	protected void cacheUniqueFindersCache(
		KaleoDraftDefinition kaleoDraftDefinition) {
		if (kaleoDraftDefinition.isNew()) {
			Object[] args = new Object[] {
					kaleoDraftDefinition.getCompanyId(),
					kaleoDraftDefinition.getName(),
					kaleoDraftDefinition.getVersion(),
					kaleoDraftDefinition.getDraftVersion()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N_V_D, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V_D, args,
				kaleoDraftDefinition);
		}
		else {
			KaleoDraftDefinitionModelImpl kaleoDraftDefinitionModelImpl = (KaleoDraftDefinitionModelImpl)kaleoDraftDefinition;

			if ((kaleoDraftDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_N_V_D.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoDraftDefinition.getCompanyId(),
						kaleoDraftDefinition.getName(),
						kaleoDraftDefinition.getVersion(),
						kaleoDraftDefinition.getDraftVersion()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_N_V_D, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_N_V_D, args,
					kaleoDraftDefinition);
			}
		}
	}

	protected void clearUniqueFindersCache(
		KaleoDraftDefinition kaleoDraftDefinition) {
		KaleoDraftDefinitionModelImpl kaleoDraftDefinitionModelImpl = (KaleoDraftDefinitionModelImpl)kaleoDraftDefinition;

		Object[] args = new Object[] {
				kaleoDraftDefinition.getCompanyId(),
				kaleoDraftDefinition.getName(),
				kaleoDraftDefinition.getVersion(),
				kaleoDraftDefinition.getDraftVersion()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N_V_D, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V_D, args);

		if ((kaleoDraftDefinitionModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_N_V_D.getColumnBitmask()) != 0) {
			args = new Object[] {
					kaleoDraftDefinitionModelImpl.getOriginalCompanyId(),
					kaleoDraftDefinitionModelImpl.getOriginalName(),
					kaleoDraftDefinitionModelImpl.getOriginalVersion(),
					kaleoDraftDefinitionModelImpl.getOriginalDraftVersion()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N_V_D, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_N_V_D, args);
		}
	}

	/**
	 * Creates a new kaleo draft definition with the primary key. Does not add the kaleo draft definition to the database.
	 *
	 * @param kaleoDraftDefinitionId the primary key for the new kaleo draft definition
	 * @return the new kaleo draft definition
	 */
	@Override
	public KaleoDraftDefinition create(long kaleoDraftDefinitionId) {
		KaleoDraftDefinition kaleoDraftDefinition = new KaleoDraftDefinitionImpl();

		kaleoDraftDefinition.setNew(true);
		kaleoDraftDefinition.setPrimaryKey(kaleoDraftDefinitionId);

		return kaleoDraftDefinition;
	}

	/**
	 * Removes the kaleo draft definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	 * @return the kaleo draft definition that was removed
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition remove(long kaleoDraftDefinitionId)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		return remove((Serializable)kaleoDraftDefinitionId);
	}

	/**
	 * Removes the kaleo draft definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the kaleo draft definition
	 * @return the kaleo draft definition that was removed
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition remove(Serializable primaryKey)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			KaleoDraftDefinition kaleoDraftDefinition = (KaleoDraftDefinition)session.get(KaleoDraftDefinitionImpl.class,
					primaryKey);

			if (kaleoDraftDefinition == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchKaleoDraftDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kaleoDraftDefinition);
		}
		catch (NoSuchKaleoDraftDefinitionException nsee) {
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
	protected KaleoDraftDefinition removeImpl(
		KaleoDraftDefinition kaleoDraftDefinition) throws SystemException {
		kaleoDraftDefinition = toUnwrappedModel(kaleoDraftDefinition);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kaleoDraftDefinition)) {
				kaleoDraftDefinition = (KaleoDraftDefinition)session.get(KaleoDraftDefinitionImpl.class,
						kaleoDraftDefinition.getPrimaryKeyObj());
			}

			if (kaleoDraftDefinition != null) {
				session.delete(kaleoDraftDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kaleoDraftDefinition != null) {
			clearCache(kaleoDraftDefinition);
		}

		return kaleoDraftDefinition;
	}

	@Override
	public KaleoDraftDefinition updateImpl(
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition kaleoDraftDefinition)
		throws SystemException {
		kaleoDraftDefinition = toUnwrappedModel(kaleoDraftDefinition);

		boolean isNew = kaleoDraftDefinition.isNew();

		KaleoDraftDefinitionModelImpl kaleoDraftDefinitionModelImpl = (KaleoDraftDefinitionModelImpl)kaleoDraftDefinition;

		Session session = null;

		try {
			session = openSession();

			if (kaleoDraftDefinition.isNew()) {
				session.save(kaleoDraftDefinition);

				kaleoDraftDefinition.setNew(false);
			}
			else {
				session.merge(kaleoDraftDefinition);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KaleoDraftDefinitionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kaleoDraftDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoDraftDefinitionModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);

				args = new Object[] { kaleoDraftDefinitionModelImpl.getCompanyId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_COMPANYID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_COMPANYID,
					args);
			}

			if ((kaleoDraftDefinitionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_V.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kaleoDraftDefinitionModelImpl.getOriginalCompanyId(),
						kaleoDraftDefinitionModelImpl.getOriginalName(),
						kaleoDraftDefinitionModelImpl.getOriginalVersion()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N_V, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_V,
					args);

				args = new Object[] {
						kaleoDraftDefinitionModelImpl.getCompanyId(),
						kaleoDraftDefinitionModelImpl.getName(),
						kaleoDraftDefinitionModelImpl.getVersion()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_N_V, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_N_V,
					args);
			}
		}

		EntityCacheUtil.putResult(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
			KaleoDraftDefinitionImpl.class,
			kaleoDraftDefinition.getPrimaryKey(), kaleoDraftDefinition);

		clearUniqueFindersCache(kaleoDraftDefinition);
		cacheUniqueFindersCache(kaleoDraftDefinition);

		return kaleoDraftDefinition;
	}

	protected KaleoDraftDefinition toUnwrappedModel(
		KaleoDraftDefinition kaleoDraftDefinition) {
		if (kaleoDraftDefinition instanceof KaleoDraftDefinitionImpl) {
			return kaleoDraftDefinition;
		}

		KaleoDraftDefinitionImpl kaleoDraftDefinitionImpl = new KaleoDraftDefinitionImpl();

		kaleoDraftDefinitionImpl.setNew(kaleoDraftDefinition.isNew());
		kaleoDraftDefinitionImpl.setPrimaryKey(kaleoDraftDefinition.getPrimaryKey());

		kaleoDraftDefinitionImpl.setKaleoDraftDefinitionId(kaleoDraftDefinition.getKaleoDraftDefinitionId());
		kaleoDraftDefinitionImpl.setGroupId(kaleoDraftDefinition.getGroupId());
		kaleoDraftDefinitionImpl.setCompanyId(kaleoDraftDefinition.getCompanyId());
		kaleoDraftDefinitionImpl.setUserId(kaleoDraftDefinition.getUserId());
		kaleoDraftDefinitionImpl.setUserName(kaleoDraftDefinition.getUserName());
		kaleoDraftDefinitionImpl.setCreateDate(kaleoDraftDefinition.getCreateDate());
		kaleoDraftDefinitionImpl.setModifiedDate(kaleoDraftDefinition.getModifiedDate());
		kaleoDraftDefinitionImpl.setName(kaleoDraftDefinition.getName());
		kaleoDraftDefinitionImpl.setTitle(kaleoDraftDefinition.getTitle());
		kaleoDraftDefinitionImpl.setContent(kaleoDraftDefinition.getContent());
		kaleoDraftDefinitionImpl.setVersion(kaleoDraftDefinition.getVersion());
		kaleoDraftDefinitionImpl.setDraftVersion(kaleoDraftDefinition.getDraftVersion());

		return kaleoDraftDefinitionImpl;
	}

	/**
	 * Returns the kaleo draft definition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo draft definition
	 * @return the kaleo draft definition
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = fetchByPrimaryKey(primaryKey);

		if (kaleoDraftDefinition == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchKaleoDraftDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kaleoDraftDefinition;
	}

	/**
	 * Returns the kaleo draft definition with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException} if it could not be found.
	 *
	 * @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	 * @return the kaleo draft definition
	 * @throws com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException if a kaleo draft definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition findByPrimaryKey(long kaleoDraftDefinitionId)
		throws NoSuchKaleoDraftDefinitionException, SystemException {
		return findByPrimaryKey((Serializable)kaleoDraftDefinitionId);
	}

	/**
	 * Returns the kaleo draft definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the kaleo draft definition
	 * @return the kaleo draft definition, or <code>null</code> if a kaleo draft definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		KaleoDraftDefinition kaleoDraftDefinition = (KaleoDraftDefinition)EntityCacheUtil.getResult(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
				KaleoDraftDefinitionImpl.class, primaryKey);

		if (kaleoDraftDefinition == _nullKaleoDraftDefinition) {
			return null;
		}

		if (kaleoDraftDefinition == null) {
			Session session = null;

			try {
				session = openSession();

				kaleoDraftDefinition = (KaleoDraftDefinition)session.get(KaleoDraftDefinitionImpl.class,
						primaryKey);

				if (kaleoDraftDefinition != null) {
					cacheResult(kaleoDraftDefinition);
				}
				else {
					EntityCacheUtil.putResult(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
						KaleoDraftDefinitionImpl.class, primaryKey,
						_nullKaleoDraftDefinition);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KaleoDraftDefinitionModelImpl.ENTITY_CACHE_ENABLED,
					KaleoDraftDefinitionImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kaleoDraftDefinition;
	}

	/**
	 * Returns the kaleo draft definition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	 * @return the kaleo draft definition, or <code>null</code> if a kaleo draft definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoDraftDefinition fetchByPrimaryKey(long kaleoDraftDefinitionId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)kaleoDraftDefinitionId);
	}

	/**
	 * Returns all the kaleo draft definitions.
	 *
	 * @return the kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoDraftDefinition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the kaleo draft definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo draft definitions
	 * @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	 * @return the range of kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoDraftDefinition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the kaleo draft definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of kaleo draft definitions
	 * @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of kaleo draft definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<KaleoDraftDefinition> findAll(int start, int end,
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

		List<KaleoDraftDefinition> list = (List<KaleoDraftDefinition>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KALEODRAFTDEFINITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KALEODRAFTDEFINITION;

				if (pagination) {
					sql = sql.concat(KaleoDraftDefinitionModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KaleoDraftDefinition>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<KaleoDraftDefinition>(list);
				}
				else {
					list = (List<KaleoDraftDefinition>)QueryUtil.list(q,
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
	 * Removes all the kaleo draft definitions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (KaleoDraftDefinition kaleoDraftDefinition : findAll()) {
			remove(kaleoDraftDefinition);
		}
	}

	/**
	 * Returns the number of kaleo draft definitions.
	 *
	 * @return the number of kaleo draft definitions
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

				Query q = session.createQuery(_SQL_COUNT_KALEODRAFTDEFINITION);

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
	 * Initializes the kaleo draft definition persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<KaleoDraftDefinition>> listenersList = new ArrayList<ModelListener<KaleoDraftDefinition>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<KaleoDraftDefinition>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(KaleoDraftDefinitionImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KALEODRAFTDEFINITION = "SELECT kaleoDraftDefinition FROM KaleoDraftDefinition kaleoDraftDefinition";
	private static final String _SQL_SELECT_KALEODRAFTDEFINITION_WHERE = "SELECT kaleoDraftDefinition FROM KaleoDraftDefinition kaleoDraftDefinition WHERE ";
	private static final String _SQL_COUNT_KALEODRAFTDEFINITION = "SELECT COUNT(kaleoDraftDefinition) FROM KaleoDraftDefinition kaleoDraftDefinition";
	private static final String _SQL_COUNT_KALEODRAFTDEFINITION_WHERE = "SELECT COUNT(kaleoDraftDefinition) FROM KaleoDraftDefinition kaleoDraftDefinition WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kaleoDraftDefinition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KaleoDraftDefinition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KaleoDraftDefinition exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(KaleoDraftDefinitionPersistenceImpl.class);
	private static KaleoDraftDefinition _nullKaleoDraftDefinition = new KaleoDraftDefinitionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KaleoDraftDefinition> toCacheModel() {
				return _nullKaleoDraftDefinitionCacheModel;
			}
		};

	private static CacheModel<KaleoDraftDefinition> _nullKaleoDraftDefinitionCacheModel =
		new CacheModel<KaleoDraftDefinition>() {
			@Override
			public KaleoDraftDefinition toEntityModel() {
				return _nullKaleoDraftDefinition;
			}
		};
}