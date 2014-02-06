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

package com.liferay.portal.resiliency.spi.monitor.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.service.SPIDefinitionLocalServiceUtil;
import com.liferay.portal.resiliency.spi.util.SPIAdminConstants;
import com.liferay.portal.service.ServiceContext;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class SPIRestartMessageListener extends BaseSPIStatusMessageListener {

	public SPIRestartMessageListener() {
		setInterestedStatus(SPIAdminConstants.STATUS_STOPPED);
	}

	@Override
	protected void processSPIStatus(
			PortletPreferences portletPreferences, SPIDefinition spiDefinition,
			int status)
		throws Exception {

		if ((spiDefinition.getStatus() == SPIAdminConstants.STATUS_STOPPED) &&
			(status == SPIAdminConstants.STATUS_STOPPED)) {

			return;
		}

		if ((spiDefinition.getStatus() == SPIAdminConstants.STATUS_STARTING) ||
			(spiDefinition.getStatus() == SPIAdminConstants.STATUS_STOPPING)) {

			return;
		}

		int maxRestartAttempts = spiDefinition.getMaxRestartAttempts();

		if (maxRestartAttempts < 0) {
			maxRestartAttempts = GetterUtil.getInteger(
				portletPreferences.getValue("maxRestartAttempts", null), 0);
		}

		int restartAttempts = spiDefinition.getRestartAttempts();

		if (maxRestartAttempts < restartAttempts++) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Restart attempt " + restartAttempts +
						" is ignored because it exceeds the limit of " +
							maxRestartAttempts + " restart attempts");
			}

			return;
		}

		spiDefinition.setRestartAttempts(restartAttempts);

		SPIDefinitionLocalServiceUtil.updateTypeSettings(
			spiDefinition.getUserId(), spiDefinition.getSpiDefinitionId(),
			spiDefinition.getTypeSettings(), new ServiceContext());

		SPIDefinitionLocalServiceUtil.startSPI(
			spiDefinition.getSpiDefinitionId());
	}

	private static Log _log = LogFactoryUtil.getLog(
		SPIRestartMessageListener.class);

}