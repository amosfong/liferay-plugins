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

package com.liferay.portal.workflow.kaleo.forms.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;

import java.util.List;

/**
 * The persistence utility for the kaleo process link service. This utility wraps {@link KaleoProcessLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLinkPersistence
 * @see KaleoProcessLinkPersistenceImpl
 * @generated
 */
public class KaleoProcessLinkUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(KaleoProcessLink kaleoProcessLink) {
		getPersistence().clearCache(kaleoProcessLink);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoProcessLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoProcessLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoProcessLink> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoProcessLink update(KaleoProcessLink kaleoProcessLink)
		throws SystemException {
		return getPersistence().update(kaleoProcessLink);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static KaleoProcessLink update(KaleoProcessLink kaleoProcessLink,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoProcessLink, serviceContext);
	}

	/**
	* Returns all the kaleo process links where kaleoProcessId = &#63;.
	*
	* @param kaleoProcessId the kaleo process ID
	* @return the matching kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> findByKaleoProcessId(
		long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoProcessId(kaleoProcessId);
	}

	/**
	* Returns a range of all the kaleo process links where kaleoProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoProcessId the kaleo process ID
	* @param start the lower bound of the range of kaleo process links
	* @param end the upper bound of the range of kaleo process links (not inclusive)
	* @return the range of matching kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> findByKaleoProcessId(
		long kaleoProcessId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoProcessId(kaleoProcessId, start, end);
	}

	/**
	* Returns an ordered range of all the kaleo process links where kaleoProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param kaleoProcessId the kaleo process ID
	* @param start the lower bound of the range of kaleo process links
	* @param end the upper bound of the range of kaleo process links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> findByKaleoProcessId(
		long kaleoProcessId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoProcessId(kaleoProcessId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first kaleo process link in the ordered set where kaleoProcessId = &#63;.
	*
	* @param kaleoProcessId the kaleo process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo process link
	* @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a matching kaleo process link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink findByKaleoProcessId_First(
		long kaleoProcessId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException {
		return getPersistence()
				   .findByKaleoProcessId_First(kaleoProcessId, orderByComparator);
	}

	/**
	* Returns the first kaleo process link in the ordered set where kaleoProcessId = &#63;.
	*
	* @param kaleoProcessId the kaleo process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching kaleo process link, or <code>null</code> if a matching kaleo process link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchByKaleoProcessId_First(
		long kaleoProcessId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoProcessId_First(kaleoProcessId,
			orderByComparator);
	}

	/**
	* Returns the last kaleo process link in the ordered set where kaleoProcessId = &#63;.
	*
	* @param kaleoProcessId the kaleo process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo process link
	* @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a matching kaleo process link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink findByKaleoProcessId_Last(
		long kaleoProcessId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException {
		return getPersistence()
				   .findByKaleoProcessId_Last(kaleoProcessId, orderByComparator);
	}

	/**
	* Returns the last kaleo process link in the ordered set where kaleoProcessId = &#63;.
	*
	* @param kaleoProcessId the kaleo process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching kaleo process link, or <code>null</code> if a matching kaleo process link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchByKaleoProcessId_Last(
		long kaleoProcessId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoProcessId_Last(kaleoProcessId, orderByComparator);
	}

	/**
	* Returns the kaleo process links before and after the current kaleo process link in the ordered set where kaleoProcessId = &#63;.
	*
	* @param kaleoProcessLinkId the primary key of the current kaleo process link
	* @param kaleoProcessId the kaleo process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next kaleo process link
	* @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a kaleo process link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink[] findByKaleoProcessId_PrevAndNext(
		long kaleoProcessLinkId, long kaleoProcessId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException {
		return getPersistence()
				   .findByKaleoProcessId_PrevAndNext(kaleoProcessLinkId,
			kaleoProcessId, orderByComparator);
	}

	/**
	* Removes all the kaleo process links where kaleoProcessId = &#63; from the database.
	*
	* @param kaleoProcessId the kaleo process ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByKaleoProcessId(long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoProcessId(kaleoProcessId);
	}

	/**
	* Returns the number of kaleo process links where kaleoProcessId = &#63;.
	*
	* @param kaleoProcessId the kaleo process ID
	* @return the number of matching kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKaleoProcessId(long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoProcessId(kaleoProcessId);
	}

	/**
	* Returns the kaleo process link where kaleoProcessId = &#63; and workflowTaskName = &#63; or throws a {@link com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException} if it could not be found.
	*
	* @param kaleoProcessId the kaleo process ID
	* @param workflowTaskName the workflow task name
	* @return the matching kaleo process link
	* @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a matching kaleo process link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink findByKPI_WTN(
		long kaleoProcessId, java.lang.String workflowTaskName)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException {
		return getPersistence().findByKPI_WTN(kaleoProcessId, workflowTaskName);
	}

	/**
	* Returns the kaleo process link where kaleoProcessId = &#63; and workflowTaskName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param kaleoProcessId the kaleo process ID
	* @param workflowTaskName the workflow task name
	* @return the matching kaleo process link, or <code>null</code> if a matching kaleo process link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchByKPI_WTN(
		long kaleoProcessId, java.lang.String workflowTaskName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKPI_WTN(kaleoProcessId, workflowTaskName);
	}

	/**
	* Returns the kaleo process link where kaleoProcessId = &#63; and workflowTaskName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param kaleoProcessId the kaleo process ID
	* @param workflowTaskName the workflow task name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching kaleo process link, or <code>null</code> if a matching kaleo process link could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchByKPI_WTN(
		long kaleoProcessId, java.lang.String workflowTaskName,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKPI_WTN(kaleoProcessId, workflowTaskName,
			retrieveFromCache);
	}

	/**
	* Removes the kaleo process link where kaleoProcessId = &#63; and workflowTaskName = &#63; from the database.
	*
	* @param kaleoProcessId the kaleo process ID
	* @param workflowTaskName the workflow task name
	* @return the kaleo process link that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink removeByKPI_WTN(
		long kaleoProcessId, java.lang.String workflowTaskName)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException {
		return getPersistence().removeByKPI_WTN(kaleoProcessId, workflowTaskName);
	}

	/**
	* Returns the number of kaleo process links where kaleoProcessId = &#63; and workflowTaskName = &#63;.
	*
	* @param kaleoProcessId the kaleo process ID
	* @param workflowTaskName the workflow task name
	* @return the number of matching kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKPI_WTN(long kaleoProcessId,
		java.lang.String workflowTaskName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKPI_WTN(kaleoProcessId, workflowTaskName);
	}

	/**
	* Caches the kaleo process link in the entity cache if it is enabled.
	*
	* @param kaleoProcessLink the kaleo process link
	*/
	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink) {
		getPersistence().cacheResult(kaleoProcessLink);
	}

	/**
	* Caches the kaleo process links in the entity cache if it is enabled.
	*
	* @param kaleoProcessLinks the kaleo process links
	*/
	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> kaleoProcessLinks) {
		getPersistence().cacheResult(kaleoProcessLinks);
	}

	/**
	* Creates a new kaleo process link with the primary key. Does not add the kaleo process link to the database.
	*
	* @param kaleoProcessLinkId the primary key for the new kaleo process link
	* @return the new kaleo process link
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink create(
		long kaleoProcessLinkId) {
		return getPersistence().create(kaleoProcessLinkId);
	}

	/**
	* Removes the kaleo process link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @return the kaleo process link that was removed
	* @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a kaleo process link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink remove(
		long kaleoProcessLinkId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException {
		return getPersistence().remove(kaleoProcessLinkId);
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateImpl(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoProcessLink);
	}

	/**
	* Returns the kaleo process link with the primary key or throws a {@link com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException} if it could not be found.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @return the kaleo process link
	* @throws com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException if a kaleo process link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink findByPrimaryKey(
		long kaleoProcessLinkId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessLinkException {
		return getPersistence().findByPrimaryKey(kaleoProcessLinkId);
	}

	/**
	* Returns the kaleo process link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @return the kaleo process link, or <code>null</code> if a kaleo process link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchByPrimaryKey(
		long kaleoProcessLinkId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoProcessLinkId);
	}

	/**
	* Returns all the kaleo process links.
	*
	* @return the kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the kaleo process links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo process links
	* @param end the upper bound of the range of kaleo process links (not inclusive)
	* @return the range of kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the kaleo process links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo process links
	* @param end the upper bound of the range of kaleo process links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the kaleo process links from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of kaleo process links.
	*
	* @return the number of kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoProcessLinkPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoProcessLinkPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.forms.service.ClpSerializer.getServletContextName(),
					KaleoProcessLinkPersistence.class.getName());

			ReferenceRegistry.registerReference(KaleoProcessLinkUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(KaleoProcessLinkPersistence persistence) {
	}

	private static KaleoProcessLinkPersistence _persistence;
}