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

package com.liferay.portal.resiliency.spi.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.monitor.SPIDefinitionMonitorUtil;
import com.liferay.portal.resiliency.spi.service.SPIDefinitionLocalServiceUtil;
import com.liferay.portal.resiliency.spi.util.PortletPropsKeys;
import com.liferay.portal.resiliency.spi.util.SPIAdminConstants;
import com.liferay.util.portlet.PortletProps;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Michael C. Han
 */
public class SPIAdminServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		SPIDefinitionMonitorUtil.unregister();

		List<SPIDefinition> spiDefinitions =
			SPIDefinitionLocalServiceUtil.getSPIDefinitions();

		for (SPIDefinition spiDefinition : spiDefinitions) {
			try {
				if (spiDefinition.isAlive()) {
					SPIDefinitionLocalServiceUtil.stopSPI(
						spiDefinition.getSpiDefinitionId());
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to stop SPI " + spiDefinition.getName());
				}
			}
		}
	}

	@Override
	protected void doPortalInit() throws Exception {

		List<SPIDefinition> spiDefinitions =
			SPIDefinitionLocalServiceUtil.getSPIDefinitions();

		for (SPIDefinition spiDefinition : spiDefinitions) {
			SPIDefinitionLocalServiceUtil.updateSPIDefinition(
				spiDefinition.getSpiDefinitionId(),
				SPIAdminConstants.STATUS_STOPPED, null);
		}

		if (!GetterUtil.getBoolean(
				PortletProps.get(
					PortletPropsKeys.SPI_START_ON_PORTAL_STARTUP))) {

			return;
		}

		for (SPIDefinition spiDefinition : spiDefinitions) {
			SPIDefinitionLocalServiceUtil.startSPIinBackground(
				0, spiDefinition.getSpiDefinitionId());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SPIAdminServletContextListener.class);

}