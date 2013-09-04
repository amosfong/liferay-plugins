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

package com.liferay.meeting.portlet.action;

import com.liferay.meeting.webex.model.WebExSite;
import com.liferay.meeting.webex.service.WebExSiteServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.BaseActionCommand;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Daniela Zapata Riesco
 */
public class AddWebExSiteActionCommand extends BaseActionCommand {

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String name = ParamUtil.getString(portletRequest, "name");
		String apiURL = ParamUtil.getString(portletRequest, "apiURL");
		String login = ParamUtil.getString(portletRequest, "login");
		String password = ParamUtil.getString(portletRequest, "password");
		String partnerKey = ParamUtil.getString(portletRequest, "partnerKey");
		long siteKey = ParamUtil.getLong(portletRequest, "siteKey");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			WebExSite.class.getName(), portletRequest);

		WebExSiteServiceUtil.addWebExSite(
			themeDisplay.getScopeGroupId(), name, apiURL, login, password,
			partnerKey, siteKey, serviceContext);
	}

}