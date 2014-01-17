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

package com.liferay.oauth.admin.portlet;

import com.liferay.oauth.service.OAuthApplicationServiceUtil;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Igor Beslic
 */
public class AdminPortlet extends MVCPortlet {

	public void addOAuthApplication(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		int accessLevel = ParamUtil.getInteger(actionRequest, "accessType");
		boolean shareableAccessToken = ParamUtil.getBoolean(
			actionRequest, "shareableAccessToken");
		String callbackURI = ParamUtil.getString(actionRequest, "callbackURI");
		String websiteURL = ParamUtil.getString(actionRequest, "websiteURL");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		OAuthApplicationServiceUtil.addOAuthApplication(
			name, description, accessLevel, shareableAccessToken, callbackURI,
			websiteURL, serviceContext);
	}

	public void deleteOAuthApplication(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long oAuthApplicationId = ParamUtil.getLong(
			actionRequest, "oAuthApplicationId");

		OAuthApplicationServiceUtil.deleteOAuthApplication(oAuthApplicationId);
	}

	public void updateLogo(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long oAuthApplicationId = ParamUtil.getLong(
			actionRequest, "oAuthApplicationId");

		InputStream inputStream = null;

		try {
			inputStream = uploadPortletRequest.getFileAsStream("fileName");

			if (inputStream == null) {
				throw new UploadException();
			}

			OAuthApplicationServiceUtil.updateLogo(
				oAuthApplicationId, inputStream);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	public void updateOAuthApplication(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long oAuthApplicationId = ParamUtil.getLong(
			actionRequest, "oAuthApplicationId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		boolean shareableAccessToken = ParamUtil.getBoolean(
			actionRequest, "shareableAccessToken");
		String callbackURI = ParamUtil.getString(actionRequest, "callbackURI");
		String websiteURL = ParamUtil.getString(actionRequest, "websiteURL");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		OAuthApplicationServiceUtil.updateOAuthApplication(
			oAuthApplicationId, name, description, shareableAccessToken,
			callbackURI, websiteURL, serviceContext);

		boolean deleteLogo = ParamUtil.getBoolean(actionRequest, "deleteLogo");

		if (deleteLogo) {
			OAuthApplicationServiceUtil.deleteLogo(oAuthApplicationId);
		}
	}

}