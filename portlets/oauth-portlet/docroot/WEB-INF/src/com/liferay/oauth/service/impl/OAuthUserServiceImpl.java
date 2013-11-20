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

import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.base.OAuthUserServiceBaseImpl;
import com.liferay.oauth.service.permission.OAuthUserPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class OAuthUserServiceImpl extends OAuthUserServiceBaseImpl {

	@Override
	public OAuthUser deleteOAuthUser(long oAuthApplicationId)
		throws PortalException, SystemException {

		OAuthUser oAuthUser = oAuthUserPersistence.findByU_OAI(
			getUserId(), oAuthApplicationId);

		OAuthUserPermission.check(
			getPermissionChecker(), oAuthUser, ActionKeys.DELETE);

		return oAuthUserLocalService.deleteOAuthUser(
			getUserId(), oAuthApplicationId);
	}

}