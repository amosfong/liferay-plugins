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

package com.liferay.meeting.webex.service.persistence;

import com.liferay.meeting.webex.model.WebExAccount;
import com.liferay.meeting.webex.service.WebExAccountLocalServiceUtil;

import com.liferay.portal.kernel.dao.orm.BaseActionableDynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Anant Singh
 * @generated
 */
public abstract class WebExAccountActionableDynamicQuery
	extends BaseActionableDynamicQuery {
	public WebExAccountActionableDynamicQuery() throws SystemException {
		setBaseLocalService(WebExAccountLocalServiceUtil.getService());
		setClass(WebExAccount.class);

		setClassLoader(com.liferay.meeting.webex.service.ClpSerializer.class.getClassLoader());

		setPrimaryKeyPropertyName("webExAccountId");
	}
}