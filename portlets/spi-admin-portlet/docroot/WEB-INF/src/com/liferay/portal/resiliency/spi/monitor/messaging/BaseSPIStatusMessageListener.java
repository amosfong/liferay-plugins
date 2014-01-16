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

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.service.SPIDefinitionLocalServiceUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public abstract class BaseSPIStatusMessageListener extends BaseMessageListener {

	public void setInterestedStatus(Integer... interestedStatuses) {
		_interestedStatuses.addAll(Arrays.asList(interestedStatuses));
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		long spiDefinitionId = GetterUtil.getLong(
			message.get("spiDefinitionId"));

		if (spiDefinitionId == 0) {
			return;
		}

		int status = GetterUtil.getInteger(message.get("status"));

		if (!_interestedStatuses.contains(status)) {
			return;
		}

		SPIDefinition spiDefinition =
			SPIDefinitionLocalServiceUtil.getSPIDefinition(spiDefinitionId);

		processSPIStatus(spiDefinition, status);
	}

	protected abstract void processSPIStatus(
			SPIDefinition spiDefinition, int status)
		throws Exception;

	private Set<Integer> _interestedStatuses = new HashSet<Integer>();

}