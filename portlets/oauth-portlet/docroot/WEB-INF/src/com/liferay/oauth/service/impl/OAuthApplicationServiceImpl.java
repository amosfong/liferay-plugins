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

package com.liferay.oauth.service.impl;

import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.service.base.OAuthApplicationServiceBaseImpl;
import com.liferay.oauth.service.permission.OAuthApplicationPermission;
import com.liferay.oauth.service.permission.OAuthPermission;
import com.liferay.oauth.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;

import java.io.InputStream;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class OAuthApplicationServiceImpl
	extends OAuthApplicationServiceBaseImpl {

	@Override
	public OAuthApplication addOAuthApplication(
			String name, String description, int accessLevel,
			boolean shareableAccessToken, String callbackURI, String websiteURL,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		OAuthPermission.check(
			getPermissionChecker(), ActionKeys.ADD_APPLICATION);

		return oAuthApplicationLocalService.addOAuthApplication(
			getUserId(), name, description, accessLevel, shareableAccessToken,
			callbackURI, websiteURL, serviceContext);
	}

	@Override
	public void deleteLogo(long oAuthApplicationId)
		throws PortalException, SystemException {

		OAuthApplicationPermission.check(
			getPermissionChecker(), oAuthApplicationId, ActionKeys.UPDATE);

		oAuthApplicationLocalService.deleteLogo(oAuthApplicationId);
	}

	@Override
	public OAuthApplication deleteOAuthApplication(long oAuthApplicationId)
		throws PortalException, SystemException {

		OAuthApplication oAuthApplication =
			oAuthApplicationPersistence.findByPrimaryKey(oAuthApplicationId);

		OAuthApplicationPermission.check(
			getPermissionChecker(), oAuthApplication, ActionKeys.DELETE);

		return oAuthApplicationLocalService.deleteOAuthApplication(
			oAuthApplication);
	}

	@Override
	public OAuthApplication updateLogo(
			long oAuthApplicationId, InputStream inputStream)
		throws PortalException, SystemException {

		OAuthApplicationPermission.check(
			getPermissionChecker(), oAuthApplicationId, ActionKeys.UPDATE);

		return oAuthApplicationLocalService.updateLogo(
			oAuthApplicationId, inputStream);
	}

	@Override
	public OAuthApplication updateOAuthApplication(
			long oAuthApplicationId, String name, String description,
			boolean shareableAccessToken, String callbackURI, String websiteURL,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		OAuthApplicationPermission.check(
			getPermissionChecker(), oAuthApplicationId, ActionKeys.UPDATE);

		return oAuthApplicationLocalService.updateOAuthApplication(
			oAuthApplicationId, name, description, shareableAccessToken,
			callbackURI, websiteURL, serviceContext);
	}

}