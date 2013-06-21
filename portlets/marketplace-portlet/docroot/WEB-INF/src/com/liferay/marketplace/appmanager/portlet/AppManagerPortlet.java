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

package com.liferay.marketplace.appmanager.portlet;

import com.liferay.marketplace.service.AppServiceUtil;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.model.PluginSetting;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.PluginSettingLocalServiceUtil;
import com.liferay.portal.service.PluginSettingServiceUtil;
import com.liferay.portal.service.PortletServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.Arrays;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.ServletContext;

/**
 * @author Ryan Park
 */
public class AppManagerPortlet extends MVCPortlet {

	public void uninstallApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long remoteAppId = ParamUtil.getLong(actionRequest, "remoteAppId");

		if (remoteAppId > 0) {
			AppServiceUtil.uninstallApp(remoteAppId);
		}
		else {
			String[] contextNames = StringUtil.split(
				ParamUtil.getString(actionRequest, "contextNames"));

			for (String contextName : contextNames) {
				DeployManagerUtil.undeploy(contextName);
			}
		}

		SessionMessages.add(actionRequest, "triggeredPortletUndeploy");
	}

	public void updatePluginSettings(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(actionRequest);

		String[] contextNames = StringUtil.split(
			ParamUtil.getString(actionRequest, "contextNames"));

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		for (String contextName : contextNames) {
			ServletContext servletContext = ServletContextPool.get(contextName);

			List<LayoutTemplate> layoutTemplates =
				(List<LayoutTemplate>)servletContext.getAttribute(
					"PLUGIN_LAYOUT_TEMPLATES");

			if (layoutTemplates != null) {
				for (LayoutTemplate layoutTemplate : layoutTemplates) {
					PluginSetting pluginSetting =
						PluginSettingLocalServiceUtil.getPluginSetting(
							companyId, layoutTemplate.getLayoutTemplateId(),
							Plugin.TYPE_LAYOUT_TEMPLATE);

					PluginSettingServiceUtil.updatePluginSetting(
						companyId, layoutTemplate.getLayoutTemplateId(),
						Plugin.TYPE_LAYOUT_TEMPLATE, pluginSetting.getRoles(),
						active);
				}
			}

			List<Portlet> portlets = (List<Portlet>)servletContext.getAttribute(
				"PLUGIN_PORTLETS");

			if (portlets != null) {
				for (Portlet portlet : portlets) {
					PortletServiceUtil.updatePortlet(
						companyId, portlet.getPortletId(), StringPool.BLANK,
						active);
				}
			}

			List<Theme> themes = (List<Theme>)servletContext.getAttribute(
				"PLUGIN_THEMES");

			if (themes != null) {
				for (Theme theme : themes) {
					PluginSetting pluginSetting =
						PluginSettingLocalServiceUtil.getPluginSetting(
							companyId, theme.getThemeId(), Plugin.TYPE_THEME);

					PluginSettingServiceUtil.updatePluginSetting(
						companyId, theme.getThemeId(), Plugin.TYPE_THEME,
						pluginSetting.getRoles(), active);
				}
			}
		}
	}

	protected void updatePluginSetting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long companyId = PortalUtil.getCompanyId(actionRequest);
		String pluginId = ParamUtil.getString(actionRequest, "pluginId");
		String pluginType = ParamUtil.getString(actionRequest, "pluginType");

		String[] rolesArray = StringUtil.split(
			ParamUtil.getString(actionRequest, "roles"), '\n');

		Arrays.sort(rolesArray);

		String roles = StringUtil.merge(rolesArray);

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		if (pluginType.equals(Plugin.TYPE_PORTLET)) {
			PortletServiceUtil.updatePortlet(
				companyId, pluginId, StringPool.BLANK, active);
		}
		else {
			PluginSettingServiceUtil.updatePluginSetting(
				companyId, pluginId, pluginType, roles, active);
		}
	}

}