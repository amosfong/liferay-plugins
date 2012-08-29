/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.so.configurations.portlet;

import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.so.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Jonathan Lee
 */
public class ConfigurationsPortlet extends MVCPortlet {

	public void updateOrganizationsRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		System.out.println("update organizations role");

		// Update Users Role
	}

	public void updateUsersRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		System.out.println("update users role");

		// Update Users Role
	}

	public void updateUserGroupsRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		System.out.println("update usergroups role");

		// Update Users Role
	}

}