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

import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.service.SPIDefinitionLocalServiceUtil;
import com.liferay.portal.resiliency.spi.util.SPIAdminConstants;

/**
 * @author Michael C. Han
 */
public class SPIRestartMessageListener extends BaseSPIStatusMessageListener {

	public SPIRestartMessageListener() {
		setInterestedStatus(SPIAdminConstants.STATUS_STOPPED);
	}

	@Override
	protected boolean processSPIStatus(SPIDefinition spiDefinition, int status)
		throws Exception {

		if ((spiDefinition.getStatus() == SPIAdminConstants.STATUS_STOPPED) &&
			(status == SPIAdminConstants.STATUS_STOPPED)) {

			return false;
		}

		if ((spiDefinition.getStatus() == SPIAdminConstants.STATUS_STARTING) ||
			(spiDefinition.getStatus() == SPIAdminConstants.STATUS_STOPPING)) {

			return false;
		}

		SPIDefinitionLocalServiceUtil.startSPI(
			spiDefinition.getSpiDefinitionId());

		return true;
	}

}