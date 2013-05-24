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

package com.liferay.portal.workflow.kaleo.forms.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkLocalService;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkService;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalService;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessService;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessLinkPersistence;
import com.liferay.portal.workflow.kaleo.forms.service.persistence.KaleoProcessPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the kaleo process link local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkLocalServiceImpl}.
 * </p>
 *
 * @author Marcellus Tavares
 * @see com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkLocalServiceImpl
 * @see com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkLocalServiceUtil
 * @generated
 */
public abstract class KaleoProcessLinkLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements KaleoProcessLinkLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkLocalServiceUtil} to access the kaleo process link local service.
	 */

	/**
	 * Adds the kaleo process link to the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcessLink the kaleo process link
	 * @return the kaleo process link that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KaleoProcessLink addKaleoProcessLink(
		KaleoProcessLink kaleoProcessLink) throws SystemException {
		kaleoProcessLink.setNew(true);

		return kaleoProcessLinkPersistence.update(kaleoProcessLink);
	}

	/**
	 * Creates a new kaleo process link with the primary key. Does not add the kaleo process link to the database.
	 *
	 * @param kaleoProcessLinkId the primary key for the new kaleo process link
	 * @return the new kaleo process link
	 */
	@Override
	public KaleoProcessLink createKaleoProcessLink(long kaleoProcessLinkId) {
		return kaleoProcessLinkPersistence.create(kaleoProcessLinkId);
	}

	/**
	 * Deletes the kaleo process link with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcessLinkId the primary key of the kaleo process link
	 * @return the kaleo process link that was removed
	 * @throws PortalException if a kaleo process link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KaleoProcessLink deleteKaleoProcessLink(long kaleoProcessLinkId)
		throws PortalException, SystemException {
		return kaleoProcessLinkPersistence.remove(kaleoProcessLinkId);
	}

	/**
	 * Deletes the kaleo process link from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcessLink the kaleo process link
	 * @return the kaleo process link that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public KaleoProcessLink deleteKaleoProcessLink(
		KaleoProcessLink kaleoProcessLink) throws SystemException {
		return kaleoProcessLinkPersistence.remove(kaleoProcessLink);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(KaleoProcessLink.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return kaleoProcessLinkPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return kaleoProcessLinkPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return kaleoProcessLinkPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return kaleoProcessLinkPersistence.countWithDynamicQuery(dynamicQuery);
	}

	@Override
	public KaleoProcessLink fetchKaleoProcessLink(long kaleoProcessLinkId)
		throws SystemException {
		return kaleoProcessLinkPersistence.fetchByPrimaryKey(kaleoProcessLinkId);
	}

	/**
	 * Returns the kaleo process link with the primary key.
	 *
	 * @param kaleoProcessLinkId the primary key of the kaleo process link
	 * @return the kaleo process link
	 * @throws PortalException if a kaleo process link with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public KaleoProcessLink getKaleoProcessLink(long kaleoProcessLinkId)
		throws PortalException, SystemException {
		return kaleoProcessLinkPersistence.findByPrimaryKey(kaleoProcessLinkId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return kaleoProcessLinkPersistence.findByPrimaryKey(primaryKeyObj);
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
	@Override
	public List<KaleoProcessLink> getKaleoProcessLinks(int start, int end)
		throws SystemException {
		return kaleoProcessLinkPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of kaleo process links.
	 *
	 * @return the number of kaleo process links
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getKaleoProcessLinksCount() throws SystemException {
		return kaleoProcessLinkPersistence.countAll();
	}

	/**
	 * Updates the kaleo process link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param kaleoProcessLink the kaleo process link
	 * @return the kaleo process link that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public KaleoProcessLink updateKaleoProcessLink(
		KaleoProcessLink kaleoProcessLink) throws SystemException {
		return kaleoProcessLinkPersistence.update(kaleoProcessLink);
	}

	/**
	 * Returns the kaleo process local service.
	 *
	 * @return the kaleo process local service
	 */
	public KaleoProcessLocalService getKaleoProcessLocalService() {
		return kaleoProcessLocalService;
	}

	/**
	 * Sets the kaleo process local service.
	 *
	 * @param kaleoProcessLocalService the kaleo process local service
	 */
	public void setKaleoProcessLocalService(
		KaleoProcessLocalService kaleoProcessLocalService) {
		this.kaleoProcessLocalService = kaleoProcessLocalService;
	}

	/**
	 * Returns the kaleo process remote service.
	 *
	 * @return the kaleo process remote service
	 */
	public KaleoProcessService getKaleoProcessService() {
		return kaleoProcessService;
	}

	/**
	 * Sets the kaleo process remote service.
	 *
	 * @param kaleoProcessService the kaleo process remote service
	 */
	public void setKaleoProcessService(KaleoProcessService kaleoProcessService) {
		this.kaleoProcessService = kaleoProcessService;
	}

	/**
	 * Returns the kaleo process persistence.
	 *
	 * @return the kaleo process persistence
	 */
	public KaleoProcessPersistence getKaleoProcessPersistence() {
		return kaleoProcessPersistence;
	}

	/**
	 * Sets the kaleo process persistence.
	 *
	 * @param kaleoProcessPersistence the kaleo process persistence
	 */
	public void setKaleoProcessPersistence(
		KaleoProcessPersistence kaleoProcessPersistence) {
		this.kaleoProcessPersistence = kaleoProcessPersistence;
	}

	/**
	 * Returns the kaleo process link local service.
	 *
	 * @return the kaleo process link local service
	 */
	public KaleoProcessLinkLocalService getKaleoProcessLinkLocalService() {
		return kaleoProcessLinkLocalService;
	}

	/**
	 * Sets the kaleo process link local service.
	 *
	 * @param kaleoProcessLinkLocalService the kaleo process link local service
	 */
	public void setKaleoProcessLinkLocalService(
		KaleoProcessLinkLocalService kaleoProcessLinkLocalService) {
		this.kaleoProcessLinkLocalService = kaleoProcessLinkLocalService;
	}

	/**
	 * Returns the kaleo process link remote service.
	 *
	 * @return the kaleo process link remote service
	 */
	public KaleoProcessLinkService getKaleoProcessLinkService() {
		return kaleoProcessLinkService;
	}

	/**
	 * Sets the kaleo process link remote service.
	 *
	 * @param kaleoProcessLinkService the kaleo process link remote service
	 */
	public void setKaleoProcessLinkService(
		KaleoProcessLinkService kaleoProcessLinkService) {
		this.kaleoProcessLinkService = kaleoProcessLinkService;
	}

	/**
	 * Returns the kaleo process link persistence.
	 *
	 * @return the kaleo process link persistence
	 */
	public KaleoProcessLinkPersistence getKaleoProcessLinkPersistence() {
		return kaleoProcessLinkPersistence;
	}

	/**
	 * Sets the kaleo process link persistence.
	 *
	 * @param kaleoProcessLinkPersistence the kaleo process link persistence
	 */
	public void setKaleoProcessLinkPersistence(
		KaleoProcessLinkPersistence kaleoProcessLinkPersistence) {
		this.kaleoProcessLinkPersistence = kaleoProcessLinkPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink",
			kaleoProcessLinkLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return KaleoProcessLink.class;
	}

	protected String getModelClassName() {
		return KaleoProcessLink.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = kaleoProcessLinkPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = KaleoProcessLocalService.class)
	protected KaleoProcessLocalService kaleoProcessLocalService;
	@BeanReference(type = KaleoProcessService.class)
	protected KaleoProcessService kaleoProcessService;
	@BeanReference(type = KaleoProcessPersistence.class)
	protected KaleoProcessPersistence kaleoProcessPersistence;
	@BeanReference(type = KaleoProcessLinkLocalService.class)
	protected KaleoProcessLinkLocalService kaleoProcessLinkLocalService;
	@BeanReference(type = KaleoProcessLinkService.class)
	protected KaleoProcessLinkService kaleoProcessLinkService;
	@BeanReference(type = KaleoProcessLinkPersistence.class)
	protected KaleoProcessLinkPersistence kaleoProcessLinkPersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private KaleoProcessLinkLocalServiceClpInvoker _clpInvoker = new KaleoProcessLinkLocalServiceClpInvoker();
}