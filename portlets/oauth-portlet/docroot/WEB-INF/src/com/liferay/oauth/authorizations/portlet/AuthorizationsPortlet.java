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

package com.liferay.oauth.authorizations.portlet;

import com.liferay.oauth.service.OAuthUserServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Igor Beslic
 */
public class AuthorizationsPortlet extends MVCPortlet {

	public void deleteOAuthUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long oAuthApplicationId = ParamUtil.getLong(
			actionRequest, "oAuthApplicationId");

		OAuthUserServiceUtil.deleteOAuthUser(oAuthApplicationId);
	}

}