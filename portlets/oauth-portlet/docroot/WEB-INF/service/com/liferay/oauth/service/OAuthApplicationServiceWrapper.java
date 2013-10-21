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

package com.liferay.oauth.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OAuthApplicationService}.
 * </p>
 *
 * @author    Ivica Cardic
 * @see       OAuthApplicationService
 * @generated
 */
public class OAuthApplicationServiceWrapper implements OAuthApplicationService,
	ServiceWrapper<OAuthApplicationService> {
	public OAuthApplicationServiceWrapper(
		OAuthApplicationService oAuthApplicationService) {
		_oAuthApplicationService = oAuthApplicationService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _oAuthApplicationService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_oAuthApplicationService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _oAuthApplicationService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.oauth.model.OAuthApplication addOAuthApplication(
		java.lang.String name, java.lang.String description, int accessLevel,
		java.lang.String callbackURI, java.lang.String websiteURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationService.addOAuthApplication(name, description,
			accessLevel, callbackURI, websiteURL, serviceContext);
	}

	public void deleteLogo(long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_oAuthApplicationService.deleteLogo(oAuthApplicationId);
	}

	public com.liferay.oauth.model.OAuthApplication deleteOAuthApplication(
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationService.deleteOAuthApplication(oAuthApplicationId);
	}

	public com.liferay.oauth.model.OAuthApplication updateLogo(
		long oAuthApplicationId, java.io.InputStream inputStream)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationService.updateLogo(oAuthApplicationId,
			inputStream);
	}

	public com.liferay.oauth.model.OAuthApplication updateOAuthApplication(
		long oAuthApplicationId, java.lang.String name,
		java.lang.String description, java.lang.String callbackURI,
		java.lang.String websiteURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthApplicationService.updateOAuthApplication(oAuthApplicationId,
			name, description, callbackURI, websiteURL, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OAuthApplicationService getWrappedOAuthApplicationService() {
		return _oAuthApplicationService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOAuthApplicationService(
		OAuthApplicationService oAuthApplicationService) {
		_oAuthApplicationService = oAuthApplicationService;
	}

	public OAuthApplicationService getWrappedService() {
		return _oAuthApplicationService;
	}

	public void setWrappedService(
		OAuthApplicationService oAuthApplicationService) {
		_oAuthApplicationService = oAuthApplicationService;
	}

	private OAuthApplicationService _oAuthApplicationService;
}