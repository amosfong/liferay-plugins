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

package com.liferay.reports.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.reports.model.Definition;
import com.liferay.reports.service.DefinitionLocalService;
import com.liferay.reports.service.persistence.DefinitionPersistence;
import com.liferay.reports.service.persistence.EntryPersistence;
import com.liferay.reports.service.persistence.SourcePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the definition local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.reports.service.impl.DefinitionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.reports.service.impl.DefinitionLocalServiceImpl
 * @see com.liferay.reports.service.DefinitionLocalServiceUtil
 * @generated
 */
public abstract class DefinitionLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DefinitionLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.reports.service.DefinitionLocalServiceUtil} to access the definition local service.
	 */

	/**
	 * Adds the definition to the database. Also notifies the appropriate model listeners.
	 *
	 * @param definition the definition
	 * @return the definition that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Definition addDefinition(Definition definition)
		throws SystemException {
		definition.setNew(true);

		return definitionPersistence.update(definition);
	}

	/**
	 * Creates a new definition with the primary key. Does not add the definition to the database.
	 *
	 * @param definitionId the primary key for the new definition
	 * @return the new definition
	 */
	@Override
	public Definition createDefinition(long definitionId) {
		return definitionPersistence.create(definitionId);
	}

	/**
	 * Deletes the definition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param definitionId the primary key of the definition
	 * @return the definition that was removed
	 * @throws PortalException if a definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Definition deleteDefinition(long definitionId)
		throws PortalException, SystemException {
		return definitionPersistence.remove(definitionId);
	}

	/**
	 * Deletes the definition from the database. Also notifies the appropriate model listeners.
	 *
	 * @param definition the definition
	 * @return the definition that was removed
	 * @throws PortalException
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Definition deleteDefinition(Definition definition)
		throws PortalException, SystemException {
		return definitionPersistence.remove(definition);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Definition.class,
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
		return definitionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.reports.model.impl.DefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return definitionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.reports.model.impl.DefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return definitionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return definitionPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return definitionPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public Definition fetchDefinition(long definitionId)
		throws SystemException {
		return definitionPersistence.fetchByPrimaryKey(definitionId);
	}

	/**
	 * Returns the definition with the primary key.
	 *
	 * @param definitionId the primary key of the definition
	 * @return the definition
	 * @throws PortalException if a definition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Definition getDefinition(long definitionId)
		throws PortalException, SystemException {
		return definitionPersistence.findByPrimaryKey(definitionId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return definitionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the definition matching the UUID and group.
	 *
	 * @param uuid the definition's UUID
	 * @param groupId the primary key of the group
	 * @return the matching definition
	 * @throws PortalException if a matching definition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Definition getDefinitionByUuidAndGroupId(String uuid, long groupId)
		throws PortalException, SystemException {
		return definitionPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the definitions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.reports.model.impl.DefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of definitions
	 * @param end the upper bound of the range of definitions (not inclusive)
	 * @return the range of definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Definition> getDefinitions(int start, int end)
		throws SystemException {
		return definitionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of definitions.
	 *
	 * @return the number of definitions
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getDefinitionsCount() throws SystemException {
		return definitionPersistence.countAll();
	}

	/**
	 * Updates the definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param definition the definition
	 * @return the definition that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Definition updateDefinition(Definition definition)
		throws SystemException {
		return definitionPersistence.update(definition);
	}

	/**
	 * Returns the definition local service.
	 *
	 * @return the definition local service
	 */
	public com.liferay.reports.service.DefinitionLocalService getDefinitionLocalService() {
		return definitionLocalService;
	}

	/**
	 * Sets the definition local service.
	 *
	 * @param definitionLocalService the definition local service
	 */
	public void setDefinitionLocalService(
		com.liferay.reports.service.DefinitionLocalService definitionLocalService) {
		this.definitionLocalService = definitionLocalService;
	}

	/**
	 * Returns the definition remote service.
	 *
	 * @return the definition remote service
	 */
	public com.liferay.reports.service.DefinitionService getDefinitionService() {
		return definitionService;
	}

	/**
	 * Sets the definition remote service.
	 *
	 * @param definitionService the definition remote service
	 */
	public void setDefinitionService(
		com.liferay.reports.service.DefinitionService definitionService) {
		this.definitionService = definitionService;
	}

	/**
	 * Returns the definition persistence.
	 *
	 * @return the definition persistence
	 */
	public DefinitionPersistence getDefinitionPersistence() {
		return definitionPersistence;
	}

	/**
	 * Sets the definition persistence.
	 *
	 * @param definitionPersistence the definition persistence
	 */
	public void setDefinitionPersistence(
		DefinitionPersistence definitionPersistence) {
		this.definitionPersistence = definitionPersistence;
	}

	/**
	 * Returns the entry local service.
	 *
	 * @return the entry local service
	 */
	public com.liferay.reports.service.EntryLocalService getEntryLocalService() {
		return entryLocalService;
	}

	/**
	 * Sets the entry local service.
	 *
	 * @param entryLocalService the entry local service
	 */
	public void setEntryLocalService(
		com.liferay.reports.service.EntryLocalService entryLocalService) {
		this.entryLocalService = entryLocalService;
	}

	/**
	 * Returns the entry remote service.
	 *
	 * @return the entry remote service
	 */
	public com.liferay.reports.service.EntryService getEntryService() {
		return entryService;
	}

	/**
	 * Sets the entry remote service.
	 *
	 * @param entryService the entry remote service
	 */
	public void setEntryService(
		com.liferay.reports.service.EntryService entryService) {
		this.entryService = entryService;
	}

	/**
	 * Returns the entry persistence.
	 *
	 * @return the entry persistence
	 */
	public EntryPersistence getEntryPersistence() {
		return entryPersistence;
	}

	/**
	 * Sets the entry persistence.
	 *
	 * @param entryPersistence the entry persistence
	 */
	public void setEntryPersistence(EntryPersistence entryPersistence) {
		this.entryPersistence = entryPersistence;
	}

	/**
	 * Returns the source local service.
	 *
	 * @return the source local service
	 */
	public com.liferay.reports.service.SourceLocalService getSourceLocalService() {
		return sourceLocalService;
	}

	/**
	 * Sets the source local service.
	 *
	 * @param sourceLocalService the source local service
	 */
	public void setSourceLocalService(
		com.liferay.reports.service.SourceLocalService sourceLocalService) {
		this.sourceLocalService = sourceLocalService;
	}

	/**
	 * Returns the source remote service.
	 *
	 * @return the source remote service
	 */
	public com.liferay.reports.service.SourceService getSourceService() {
		return sourceService;
	}

	/**
	 * Sets the source remote service.
	 *
	 * @param sourceService the source remote service
	 */
	public void setSourceService(
		com.liferay.reports.service.SourceService sourceService) {
		this.sourceService = sourceService;
	}

	/**
	 * Returns the source persistence.
	 *
	 * @return the source persistence
	 */
	public SourcePersistence getSourcePersistence() {
		return sourcePersistence;
	}

	/**
	 * Sets the source persistence.
	 *
	 * @param sourcePersistence the source persistence
	 */
	public void setSourcePersistence(SourcePersistence sourcePersistence) {
		this.sourcePersistence = sourcePersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
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

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.reports.model.Definition",
			definitionLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.reports.model.Definition");
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
		return Definition.class;
	}

	protected String getModelClassName() {
		return Definition.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = definitionPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.reports.service.DefinitionLocalService.class)
	protected com.liferay.reports.service.DefinitionLocalService definitionLocalService;
	@BeanReference(type = com.liferay.reports.service.DefinitionService.class)
	protected com.liferay.reports.service.DefinitionService definitionService;
	@BeanReference(type = DefinitionPersistence.class)
	protected DefinitionPersistence definitionPersistence;
	@BeanReference(type = com.liferay.reports.service.EntryLocalService.class)
	protected com.liferay.reports.service.EntryLocalService entryLocalService;
	@BeanReference(type = com.liferay.reports.service.EntryService.class)
	protected com.liferay.reports.service.EntryService entryService;
	@BeanReference(type = EntryPersistence.class)
	protected EntryPersistence entryPersistence;
	@BeanReference(type = com.liferay.reports.service.SourceLocalService.class)
	protected com.liferay.reports.service.SourceLocalService sourceLocalService;
	@BeanReference(type = com.liferay.reports.service.SourceService.class)
	protected com.liferay.reports.service.SourceService sourceService;
	@BeanReference(type = SourcePersistence.class)
	protected SourcePersistence sourcePersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private DefinitionLocalServiceClpInvoker _clpInvoker = new DefinitionLocalServiceClpInvoker();
}