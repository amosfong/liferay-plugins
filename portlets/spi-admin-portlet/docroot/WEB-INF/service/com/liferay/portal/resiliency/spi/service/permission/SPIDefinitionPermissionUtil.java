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

package com.liferay.portal.resiliency.spi.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Michael C. Han
 */
public class SPIDefinitionPermissionUtil {

	public static void check(
			PermissionChecker permissionChecker, long spiDefinitionId,
			String actionId)
		throws PortalException, SystemException {

		getSpiDefinitionPermission().check(
			permissionChecker, spiDefinitionId, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, SPIDefinition spiDefinition,
			String actionId)
		throws PortalException, SystemException {

		getSpiDefinitionPermission().check(
			permissionChecker, spiDefinition, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long spiDefinitionId,
			String actionId)
		throws PortalException, SystemException {

		return getSpiDefinitionPermission().contains(
			permissionChecker, spiDefinitionId, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, SPIDefinition spiDefinition,
			String actionId)
		throws PortalException, SystemException {

		return getSpiDefinitionPermission().contains(
			permissionChecker, spiDefinition, actionId);
	}

	public static SPIDefinitionPermission getSpiDefinitionPermission() {

		PortalRuntimePermission.checkGetBeanProperty(
			SPIDefinitionPermissionUtil.class);

		return _spiDefinitionPermission;
	}

	public void setSPIDefinitionPermission(
		SPIDefinitionPermission spiDefinitionPermission) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_spiDefinitionPermission = spiDefinitionPermission;
	}

	private static SPIDefinitionPermission _spiDefinitionPermission;

}