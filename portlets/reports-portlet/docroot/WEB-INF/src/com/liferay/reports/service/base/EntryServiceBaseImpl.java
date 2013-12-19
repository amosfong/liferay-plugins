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
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.reports.model.Entry;
import com.liferay.reports.service.EntryService;
import com.liferay.reports.service.persistence.DefinitionPersistence;
import com.liferay.reports.service.persistence.EntryPersistence;
import com.liferay.reports.service.persistence.SourcePersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the entry remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.reports.service.impl.EntryServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.reports.service.impl.EntryServiceImpl
 * @see com.liferay.reports.service.EntryServiceUtil
 * @generated
 */
public abstract class EntryServiceBaseImpl extends BaseServiceImpl
	implements EntryService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.reports.service.EntryServiceUtil} to access the entry remote service.
	 */

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
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the portlet preferences local service.
	 *
	 * @return the portlet preferences local service
	 */
	public com.liferay.portal.service.PortletPreferencesLocalService getPortletPreferencesLocalService() {
		return portletPreferencesLocalService;
	}

	/**
	 * Sets the portlet preferences local service.
	 *
	 * @param portletPreferencesLocalService the portlet preferences local service
	 */
	public void setPortletPreferencesLocalService(
		com.liferay.portal.service.PortletPreferencesLocalService portletPreferencesLocalService) {
		this.portletPreferencesLocalService = portletPreferencesLocalService;
	}

	/**
	 * Returns the portlet preferences remote service.
	 *
	 * @return the portlet preferences remote service
	 */
	public com.liferay.portal.service.PortletPreferencesService getPortletPreferencesService() {
		return portletPreferencesService;
	}

	/**
	 * Sets the portlet preferences remote service.
	 *
	 * @param portletPreferencesService the portlet preferences remote service
	 */
	public void setPortletPreferencesService(
		com.liferay.portal.service.PortletPreferencesService portletPreferencesService) {
		this.portletPreferencesService = portletPreferencesService;
	}

	/**
	 * Returns the portlet preferences persistence.
	 *
	 * @return the portlet preferences persistence
	 */
	public PortletPreferencesPersistence getPortletPreferencesPersistence() {
		return portletPreferencesPersistence;
	}

	/**
	 * Sets the portlet preferences persistence.
	 *
	 * @param portletPreferencesPersistence the portlet preferences persistence
	 */
	public void setPortletPreferencesPersistence(
		PortletPreferencesPersistence portletPreferencesPersistence) {
		this.portletPreferencesPersistence = portletPreferencesPersistence;
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
	}

	public void destroy() {
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
		return Entry.class;
	}

	protected String getModelClassName() {
		return Entry.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = entryPersistence.getDataSource();

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
	@BeanReference(type = com.liferay.portal.service.ClassNameLocalService.class)
	protected com.liferay.portal.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameService.class)
	protected com.liferay.portal.service.ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.service.PortletPreferencesLocalService.class)
	protected com.liferay.portal.service.PortletPreferencesLocalService portletPreferencesLocalService;
	@BeanReference(type = com.liferay.portal.service.PortletPreferencesService.class)
	protected com.liferay.portal.service.PortletPreferencesService portletPreferencesService;
	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;
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
	private EntryServiceClpInvoker _clpInvoker = new EntryServiceClpInvoker();
}