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

package com.liferay.oauth.service.base;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.service.OAuthApplicationLocalService;
import com.liferay.oauth.service.OAuthApplicationService;
import com.liferay.oauth.service.OAuthUserLocalService;
import com.liferay.oauth.service.OAuthUserService;
import com.liferay.oauth.service.persistence.OAuthApplicationPersistence;
import com.liferay.oauth.service.persistence.OAuthUserPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.BaseServiceImpl;
import com.liferay.portal.service.ImageLocalService;
import com.liferay.portal.service.ImageService;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ImagePersistence;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import javax.sql.DataSource;

/**
 * The base implementation of the o auth application remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.oauth.service.impl.OAuthApplicationServiceImpl}.
 * </p>
 *
 * @author Ivica Cardic
 * @see com.liferay.oauth.service.impl.OAuthApplicationServiceImpl
 * @see com.liferay.oauth.service.OAuthApplicationServiceUtil
 * @generated
 */
public abstract class OAuthApplicationServiceBaseImpl extends BaseServiceImpl
	implements OAuthApplicationService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.oauth.service.OAuthApplicationServiceUtil} to access the o auth application remote service.
	 */

	/**
	 * Returns the o auth application local service.
	 *
	 * @return the o auth application local service
	 */
	public OAuthApplicationLocalService getOAuthApplicationLocalService() {
		return oAuthApplicationLocalService;
	}

	/**
	 * Sets the o auth application local service.
	 *
	 * @param oAuthApplicationLocalService the o auth application local service
	 */
	public void setOAuthApplicationLocalService(
		OAuthApplicationLocalService oAuthApplicationLocalService) {
		this.oAuthApplicationLocalService = oAuthApplicationLocalService;
	}

	/**
	 * Returns the o auth application remote service.
	 *
	 * @return the o auth application remote service
	 */
	public OAuthApplicationService getOAuthApplicationService() {
		return oAuthApplicationService;
	}

	/**
	 * Sets the o auth application remote service.
	 *
	 * @param oAuthApplicationService the o auth application remote service
	 */
	public void setOAuthApplicationService(
		OAuthApplicationService oAuthApplicationService) {
		this.oAuthApplicationService = oAuthApplicationService;
	}

	/**
	 * Returns the o auth application persistence.
	 *
	 * @return the o auth application persistence
	 */
	public OAuthApplicationPersistence getOAuthApplicationPersistence() {
		return oAuthApplicationPersistence;
	}

	/**
	 * Sets the o auth application persistence.
	 *
	 * @param oAuthApplicationPersistence the o auth application persistence
	 */
	public void setOAuthApplicationPersistence(
		OAuthApplicationPersistence oAuthApplicationPersistence) {
		this.oAuthApplicationPersistence = oAuthApplicationPersistence;
	}

	/**
	 * Returns the o auth user local service.
	 *
	 * @return the o auth user local service
	 */
	public OAuthUserLocalService getOAuthUserLocalService() {
		return oAuthUserLocalService;
	}

	/**
	 * Sets the o auth user local service.
	 *
	 * @param oAuthUserLocalService the o auth user local service
	 */
	public void setOAuthUserLocalService(
		OAuthUserLocalService oAuthUserLocalService) {
		this.oAuthUserLocalService = oAuthUserLocalService;
	}

	/**
	 * Returns the o auth user remote service.
	 *
	 * @return the o auth user remote service
	 */
	public OAuthUserService getOAuthUserService() {
		return oAuthUserService;
	}

	/**
	 * Sets the o auth user remote service.
	 *
	 * @param oAuthUserService the o auth user remote service
	 */
	public void setOAuthUserService(OAuthUserService oAuthUserService) {
		this.oAuthUserService = oAuthUserService;
	}

	/**
	 * Returns the o auth user persistence.
	 *
	 * @return the o auth user persistence
	 */
	public OAuthUserPersistence getOAuthUserPersistence() {
		return oAuthUserPersistence;
	}

	/**
	 * Sets the o auth user persistence.
	 *
	 * @param oAuthUserPersistence the o auth user persistence
	 */
	public void setOAuthUserPersistence(
		OAuthUserPersistence oAuthUserPersistence) {
		this.oAuthUserPersistence = oAuthUserPersistence;
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
	 * Returns the image local service.
	 *
	 * @return the image local service
	 */
	public ImageLocalService getImageLocalService() {
		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(ImageLocalService imageLocalService) {
		this.imageLocalService = imageLocalService;
	}

	/**
	 * Returns the image remote service.
	 *
	 * @return the image remote service
	 */
	public ImageService getImageService() {
		return imageService;
	}

	/**
	 * Sets the image remote service.
	 *
	 * @param imageService the image remote service
	 */
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	/**
	 * Returns the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
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
	 * Returns the resource remote service.
	 *
	 * @return the resource remote service
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * Sets the resource remote service.
	 *
	 * @param resourceService the resource remote service
	 */
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	/**
	 * Returns the resource persistence.
	 *
	 * @return the resource persistence
	 */
	public ResourcePersistence getResourcePersistence() {
		return resourcePersistence;
	}

	/**
	 * Sets the resource persistence.
	 *
	 * @param resourcePersistence the resource persistence
	 */
	public void setResourcePersistence(ResourcePersistence resourcePersistence) {
		this.resourcePersistence = resourcePersistence;
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
	}

	public void destroy() {
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

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
		return OAuthApplication.class;
	}

	protected String getModelClassName() {
		return OAuthApplication.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = oAuthApplicationPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = OAuthApplicationLocalService.class)
	protected OAuthApplicationLocalService oAuthApplicationLocalService;
	@BeanReference(type = OAuthApplicationService.class)
	protected OAuthApplicationService oAuthApplicationService;
	@BeanReference(type = OAuthApplicationPersistence.class)
	protected OAuthApplicationPersistence oAuthApplicationPersistence;
	@BeanReference(type = OAuthUserLocalService.class)
	protected OAuthUserLocalService oAuthUserLocalService;
	@BeanReference(type = OAuthUserService.class)
	protected OAuthUserService oAuthUserService;
	@BeanReference(type = OAuthUserPersistence.class)
	protected OAuthUserPersistence oAuthUserPersistence;
	@BeanReference(type = CounterLocalService.class)
	protected CounterLocalService counterLocalService;
	@BeanReference(type = ImageLocalService.class)
	protected ImageLocalService imageLocalService;
	@BeanReference(type = ImageService.class)
	protected ImageService imageService;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = ResourceLocalService.class)
	protected ResourceLocalService resourceLocalService;
	@BeanReference(type = ResourceService.class)
	protected ResourceService resourceService;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserLocalService.class)
	protected UserLocalService userLocalService;
	@BeanReference(type = UserService.class)
	protected UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private OAuthApplicationServiceClpInvoker _clpInvoker = new OAuthApplicationServiceClpInvoker();
}