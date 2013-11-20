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
import com.liferay.oauth.service.base.OAuthUserLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;

import java.util.Date;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class OAuthUserLocalServiceImpl extends OAuthUserLocalServiceBaseImpl {

	@Override
	public OAuthUser addOAuthUser(
			long userId, long oAuthApplicationId, String accessToken,
			String accessSecret, ServiceContext serviceContext)
		throws PortalException, SystemException {

		// OAuth user

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(oAuthApplicationId);

		long oAuthUserId = counterLocalService.increment();

		OAuthUser oAuthUser = oAuthUserPersistence.create(oAuthUserId);

		oAuthUser.setCompanyId(user.getCompanyId());
		oAuthUser.setUserId(user.getUserId());
		oAuthUser.setUserName(user.getFullName());
		oAuthUser.setCreateDate(serviceContext.getCreateDate(now));
		oAuthUser.setModifiedDate(serviceContext.getModifiedDate(now));
		oAuthUser.setOAuthApplicationId(oAuthApplicationId);
		oAuthUser.setAccessToken(accessToken);
		oAuthUser.setAccessSecret(accessSecret);

		oAuthUserPersistence.update(oAuthUser);

		// Resources

		resourceLocalService.addModelResources(oAuthUser, serviceContext);

		return oAuthUser;
	}

	@Override
	public OAuthUser deleteOAuthUser(long userId, long oAuthApplicationId)
		throws PortalException, SystemException {

		OAuthUser oAuthUser = oAuthUserPersistence.findByU_OAI(
			userId, oAuthApplicationId);

		return deleteOAuthUser(oAuthUser);
	}

	@Override
	public OAuthUser deleteOAuthUser(OAuthUser oAuthUser)
		throws PortalException, SystemException {

		// OAuth user

		oAuthUserPersistence.remove(oAuthUser);

		// Resources

		resourceLocalService.deleteResource(
			oAuthUser, ResourceConstants.SCOPE_INDIVIDUAL);

		return oAuthUser;
	}

	@Override
	public OAuthUser fetchOAuthUser(long userId, long oAuthApplicationId)
		throws SystemException {

		return oAuthUserPersistence.fetchByU_OAI(userId, oAuthApplicationId);
	}

	@Override
	public OAuthUser fetchOAuthUser(String accessToken) throws SystemException {
		return oAuthUserPersistence.fetchByAccessToken(accessToken);
	}

	@Override
	public List<OAuthUser> getOAuthApplicationOAuthUsers(
			long oAuthApplicationId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return oAuthUserPersistence.findByOAuthApplicationId(
			oAuthApplicationId, start, end, orderByComparator);
	}

	@Override
	public int getOAuthApplicationOAuthUsersCount(long oAuthApplicationId)
		throws SystemException {

		return oAuthUserPersistence.countByOAuthApplicationId(
			oAuthApplicationId);
	}

	@Override
	public OAuthUser getOAuthUser(long userId, long oAuthApplicationId)
		throws PortalException, SystemException {

		return oAuthUserPersistence.findByU_OAI(userId, oAuthApplicationId);
	}

	@Override
	public OAuthUser getOAuthUser(String accessToken)
		throws PortalException, SystemException {

		return oAuthUserPersistence.findByAccessToken(accessToken);
	}

	@Override
	public List<OAuthUser> getUserOAuthUsers(
			long userId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return oAuthUserPersistence.findByUserId(
			userId, start, end, orderByComparator);
	}

	@Override
	public int getUserOAuthUsersCount(long userId) throws SystemException {
		return oAuthUserPersistence.countByUserId(userId);
	}

	@Override
	public OAuthUser updateOAuthUser(
			long userId, long oAuthApplicationId, String accessToken,
			String accessSecret, ServiceContext serviceContext)
		throws PortalException, SystemException {

		OAuthUser oAuthUser = oAuthUserPersistence.findByU_OAI(
			userId, oAuthApplicationId);

		oAuthUser.setAccessToken(accessToken);
		oAuthUser.setAccessSecret(accessSecret);

		oAuthUserPersistence.update(oAuthUser);

		return oAuthUser;
	}

	protected void validate(long oAuthApplicationId)
		throws PortalException, SystemException {

		oAuthApplicationPersistence.findByPrimaryKey(oAuthApplicationId);
	}

}