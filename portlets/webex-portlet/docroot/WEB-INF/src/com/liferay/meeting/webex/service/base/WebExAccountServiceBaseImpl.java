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

package com.liferay.meeting.webex.service.base;

import com.liferay.meeting.webex.model.WebExAccount;
import com.liferay.meeting.webex.service.WebExAccountService;
import com.liferay.meeting.webex.service.persistence.WebExAccountPersistence;
import com.liferay.meeting.webex.service.persistence.WebExSitePersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.portlet.expando.service.persistence.ExpandoValuePersistence;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the web ex account remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.meeting.webex.service.impl.WebExAccountServiceImpl}.
 * </p>
 *
 * @author Anant Singh
 * @see com.liferay.meeting.webex.service.impl.WebExAccountServiceImpl
 * @see com.liferay.meeting.webex.service.WebExAccountServiceUtil
 * @generated
 */
public abstract class WebExAccountServiceBaseImpl extends BaseServiceImpl
	implements WebExAccountService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.meeting.webex.service.WebExAccountServiceUtil} to access the web ex account remote service.
	 */

	/**
	 * Returns the web ex account local service.
	 *
	 * @return the web ex account local service
	 */
	public com.liferay.meeting.webex.service.WebExAccountLocalService getWebExAccountLocalService() {
		return webExAccountLocalService;
	}

	/**
	 * Sets the web ex account local service.
	 *
	 * @param webExAccountLocalService the web ex account local service
	 */
	public void setWebExAccountLocalService(
		com.liferay.meeting.webex.service.WebExAccountLocalService webExAccountLocalService) {
		this.webExAccountLocalService = webExAccountLocalService;
	}

	/**
	 * Returns the web ex account remote service.
	 *
	 * @return the web ex account remote service
	 */
	public com.liferay.meeting.webex.service.WebExAccountService getWebExAccountService() {
		return webExAccountService;
	}

	/**
	 * Sets the web ex account remote service.
	 *
	 * @param webExAccountService the web ex account remote service
	 */
	public void setWebExAccountService(
		com.liferay.meeting.webex.service.WebExAccountService webExAccountService) {
		this.webExAccountService = webExAccountService;
	}

	/**
	 * Returns the web ex account persistence.
	 *
	 * @return the web ex account persistence
	 */
	public WebExAccountPersistence getWebExAccountPersistence() {
		return webExAccountPersistence;
	}

	/**
	 * Sets the web ex account persistence.
	 *
	 * @param webExAccountPersistence the web ex account persistence
	 */
	public void setWebExAccountPersistence(
		WebExAccountPersistence webExAccountPersistence) {
		this.webExAccountPersistence = webExAccountPersistence;
	}

	/**
	 * Returns the web ex site local service.
	 *
	 * @return the web ex site local service
	 */
	public com.liferay.meeting.webex.service.WebExSiteLocalService getWebExSiteLocalService() {
		return webExSiteLocalService;
	}

	/**
	 * Sets the web ex site local service.
	 *
	 * @param webExSiteLocalService the web ex site local service
	 */
	public void setWebExSiteLocalService(
		com.liferay.meeting.webex.service.WebExSiteLocalService webExSiteLocalService) {
		this.webExSiteLocalService = webExSiteLocalService;
	}

	/**
	 * Returns the web ex site remote service.
	 *
	 * @return the web ex site remote service
	 */
	public com.liferay.meeting.webex.service.WebExSiteService getWebExSiteService() {
		return webExSiteService;
	}

	/**
	 * Sets the web ex site remote service.
	 *
	 * @param webExSiteService the web ex site remote service
	 */
	public void setWebExSiteService(
		com.liferay.meeting.webex.service.WebExSiteService webExSiteService) {
		this.webExSiteService = webExSiteService;
	}

	/**
	 * Returns the web ex site persistence.
	 *
	 * @return the web ex site persistence
	 */
	public WebExSitePersistence getWebExSitePersistence() {
		return webExSitePersistence;
	}

	/**
	 * Sets the web ex site persistence.
	 *
	 * @param webExSitePersistence the web ex site persistence
	 */
	public void setWebExSitePersistence(
		WebExSitePersistence webExSitePersistence) {
		this.webExSitePersistence = webExSitePersistence;
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

	/**
	 * Returns the expando value local service.
	 *
	 * @return the expando value local service
	 */
	public com.liferay.portlet.expando.service.ExpandoValueLocalService getExpandoValueLocalService() {
		return expandoValueLocalService;
	}

	/**
	 * Sets the expando value local service.
	 *
	 * @param expandoValueLocalService the expando value local service
	 */
	public void setExpandoValueLocalService(
		com.liferay.portlet.expando.service.ExpandoValueLocalService expandoValueLocalService) {
		this.expandoValueLocalService = expandoValueLocalService;
	}

	/**
	 * Returns the expando value remote service.
	 *
	 * @return the expando value remote service
	 */
	public com.liferay.portlet.expando.service.ExpandoValueService getExpandoValueService() {
		return expandoValueService;
	}

	/**
	 * Sets the expando value remote service.
	 *
	 * @param expandoValueService the expando value remote service
	 */
	public void setExpandoValueService(
		com.liferay.portlet.expando.service.ExpandoValueService expandoValueService) {
		this.expandoValueService = expandoValueService;
	}

	/**
	 * Returns the expando value persistence.
	 *
	 * @return the expando value persistence
	 */
	public ExpandoValuePersistence getExpandoValuePersistence() {
		return expandoValuePersistence;
	}

	/**
	 * Sets the expando value persistence.
	 *
	 * @param expandoValuePersistence the expando value persistence
	 */
	public void setExpandoValuePersistence(
		ExpandoValuePersistence expandoValuePersistence) {
		this.expandoValuePersistence = expandoValuePersistence;
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
		return WebExAccount.class;
	}

	protected String getModelClassName() {
		return WebExAccount.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = webExAccountPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.meeting.webex.service.WebExAccountLocalService.class)
	protected com.liferay.meeting.webex.service.WebExAccountLocalService webExAccountLocalService;
	@BeanReference(type = com.liferay.meeting.webex.service.WebExAccountService.class)
	protected com.liferay.meeting.webex.service.WebExAccountService webExAccountService;
	@BeanReference(type = WebExAccountPersistence.class)
	protected WebExAccountPersistence webExAccountPersistence;
	@BeanReference(type = com.liferay.meeting.webex.service.WebExSiteLocalService.class)
	protected com.liferay.meeting.webex.service.WebExSiteLocalService webExSiteLocalService;
	@BeanReference(type = com.liferay.meeting.webex.service.WebExSiteService.class)
	protected com.liferay.meeting.webex.service.WebExSiteService webExSiteService;
	@BeanReference(type = WebExSitePersistence.class)
	protected WebExSitePersistence webExSitePersistence;
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
	@BeanReference(type = com.liferay.portlet.expando.service.ExpandoValueLocalService.class)
	protected com.liferay.portlet.expando.service.ExpandoValueLocalService expandoValueLocalService;
	@BeanReference(type = com.liferay.portlet.expando.service.ExpandoValueService.class)
	protected com.liferay.portlet.expando.service.ExpandoValueService expandoValueService;
	@BeanReference(type = ExpandoValuePersistence.class)
	protected ExpandoValuePersistence expandoValuePersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private WebExAccountServiceClpInvoker _clpInvoker = new WebExAccountServiceClpInvoker();
}