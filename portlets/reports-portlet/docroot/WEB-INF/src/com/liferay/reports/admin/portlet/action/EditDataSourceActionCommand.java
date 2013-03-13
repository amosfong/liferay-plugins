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

package com.liferay.reports.admin.portlet.action;

import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.SourceServiceUtil;
import com.liferay.util.bridges.mvc.BaseActionCommand;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Gavin Wan
 */
public class EditDataSourceActionCommand extends BaseActionCommand {

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long sourceId = ParamUtil.getLong(portletRequest, "sourceId");

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			portletRequest, "name");
		String driverClassName = ParamUtil.getString(
			portletRequest, "driverClassName");
		String driverUrl = ParamUtil.getString(portletRequest, "driverUrl");
		String driverUserName = ParamUtil.getString(
			portletRequest, "driverUserName");
		String driverPassword = ParamUtil.getString(
			portletRequest, "driverPassword");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Source.class.getName(), portletRequest);

		if (sourceId <= 0) {
			SourceServiceUtil.addSource(
				themeDisplay.getScopeGroupId(), nameMap, driverClassName,
				driverUrl, driverUserName, driverPassword, serviceContext);
		}
		else {
			SourceServiceUtil.updateSource(
				sourceId, nameMap, driverClassName, driverUrl, driverUserName,
				driverPassword, serviceContext);
		}
	}

}