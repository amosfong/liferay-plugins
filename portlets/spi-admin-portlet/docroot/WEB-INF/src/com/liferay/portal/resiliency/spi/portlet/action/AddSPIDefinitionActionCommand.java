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

package com.liferay.portal.resiliency.spi.portlet.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.service.SPIDefinitionServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.BaseActionCommand;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 */
public class AddSPIDefinitionActionCommand extends BaseActionCommand {

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		String name = ParamUtil.getString(portletRequest, "name");
		int connectorPort = ParamUtil.getInteger(
			portletRequest, "connectorPort");
		String description = ParamUtil.getString(portletRequest, "description");
		String jvmArguments = ParamUtil.getString(
			portletRequest, "jvmArguments");
		String portletIds = ParamUtil.getString(portletRequest, "portletIds");
		String servletContextNames = ParamUtil.getString(
			portletRequest, "servletContextNames");

		UnicodeProperties typeSettingsProperties =
			PropertiesParamUtil.getProperties(
				portletRequest, "TypeSettingsProperties--");

		boolean useDefaultNotificationOptions = ParamUtil.getBoolean(
			portletRequest, "useDefaultNotificationOptions", true);

		if (useDefaultNotificationOptions) {
			typeSettingsProperties.remove("notification-recipients");
		}

		boolean useDefaultRestartOptions = ParamUtil.getBoolean(
			portletRequest, "useDefaultRestartOptions", true);

		if (useDefaultRestartOptions) {
			typeSettingsProperties.remove("max-restart-attempts");
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			SPIDefinition.class.getName(), portletRequest);

		SPIDefinitionServiceUtil.addSPIDefinition(
			name, "localhost", connectorPort, description, jvmArguments,
			portletIds, servletContextNames, typeSettingsProperties.toString(),
			serviceContext);
	}

}