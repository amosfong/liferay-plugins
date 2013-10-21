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

package com.liferay.oauth.service.persistence;

import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.OAuthUserLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Ivica Cardic
 * @generated
 */
public abstract class OAuthUserActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public OAuthUserActionableDynamicQuery() throws SystemException {
		setBaseLocalService(OAuthUserLocalServiceUtil.getService());
		setClass(OAuthUser.class);

		setClassLoader(com.liferay.oauth.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("oAuthUserId");
	}
}