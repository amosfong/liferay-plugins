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

package com.liferay.portal.resiliency.spi.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.service.base.SPIDefinitionServiceBaseImpl;
import com.liferay.portal.resiliency.spi.service.permission.SPIDefinitionPermissionUtil;
import com.liferay.portal.resiliency.spi.util.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.permission.PortalPermissionUtil;

import java.util.Collection;

/**
 * @author Michael C. Han
 */
public class SPIDefinitionServiceImpl extends SPIDefinitionServiceBaseImpl {

	@Override
	public SPIDefinition addSPIDefinition(
			String name, String description, String applications,
			String jvmArguments, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.ADD_SPI_DEFINITION);

		return spiDefinitionLocalService.addSPIDefinition(
			getUserId(), name, description, applications, jvmArguments,
			typeSettings, serviceContext);
	}

	@Override
	public SPIDefinition deleteSPIDefinition(long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinitionPermissionUtil.check(
			getPermissionChecker(), spiDefinitionId, ActionKeys.DELETE);

		return spiDefinitionLocalService.deleteSPIDefinition(spiDefinitionId);
	}

	@Override
	public SPIDefinition getSPIDefinition(long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinitionPermissionUtil.check(
			getPermissionChecker(), spiDefinitionId, ActionKeys.VIEW);

		return spiDefinitionLocalService.getSPIDefinition(spiDefinitionId);
	}

	@Override
	public SPIDefinition getSPIDefinition(long companyId, String name)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition =
			spiDefinitionLocalService.getSPIDefinition(companyId, name);

		SPIDefinitionPermissionUtil.check(
			getPermissionChecker(), spiDefinition, ActionKeys.VIEW);

		return spiDefinition;
	}

	@Override
	public Collection<SPIDefinition> getSPIDefinitions()
		throws PortalException, SystemException {

		PortalPermissionUtil.check(
			getPermissionChecker(), ActionKeys.VIEW_SPI_DEFINITIONS);

		return spiDefinitionLocalService.getSPIDefinitions();
	}

	@Override
	public SPIDefinition updateSPIDefinition(
			long spiDefinitionId, String description, String applications,
			String jvmArguments, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		SPIDefinitionPermissionUtil.check(
			getPermissionChecker(), spiDefinitionId, ActionKeys.UPDATE);

		return spiDefinitionLocalService.updateSPIDefinition(
			getUserId(), spiDefinitionId, description, applications,
			jvmArguments, typeSettings, serviceContext);
	}

}