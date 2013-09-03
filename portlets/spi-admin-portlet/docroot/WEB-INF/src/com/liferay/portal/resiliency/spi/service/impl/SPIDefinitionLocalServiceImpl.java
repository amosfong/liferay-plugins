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
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.resiliency.spi.DuplicateSPIDefinitionException;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.service.base.SPIDefinitionLocalServiceBaseImpl;
import com.liferay.portal.service.ServiceContext;

import java.util.Collection;
import java.util.Date;

/**
 * @author Michael C. Han
 */
public class SPIDefinitionLocalServiceImpl
	extends SPIDefinitionLocalServiceBaseImpl {

	@Override
	public SPIDefinition addSPIDefinition(
			long userId, String name, String description, String applications,
			String jvmArguments, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		validate(user.getCompanyId(), name);

		long spiDefinitionId = counterLocalService.increment();

		SPIDefinition spiDefinition = spiDefinitionPersistence.create(
			spiDefinitionId);

		Date now = new Date();

		spiDefinition.setCompanyId(user.getCompanyId());
		spiDefinition.setCreateDate(serviceContext.getCreateDate(now));
		spiDefinition.setModifiedDate(serviceContext.getModifiedDate(now));
		spiDefinition.setUserId(user.getUserId());
		spiDefinition.setUserName(user.getFullName());

		spiDefinition.setName(name);
		spiDefinition.setApplications(applications);
		spiDefinition.setDescription(description);
		spiDefinition.setJvmArguments(jvmArguments);
		spiDefinition.setTypeSettings(typeSettings);

		spiDefinition.setExpandoBridgeAttributes(serviceContext);

		spiDefinitionPersistence.update(spiDefinition);

		// Resources

		resourceLocalService.addResources(
			user.getCompanyId(), 0, userId, SPIDefinition.class.getName(),
			spiDefinitionId, false, false, false);

		return spiDefinition;
	}

	@Override
	public SPIDefinition deleteSPIDefinition(long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		return deleteSPIDefinition(spiDefinition);
	}

	@Override
	public SPIDefinition deleteSPIDefinition(SPIDefinition spiDefinition)
		throws PortalException, SystemException {

		// Expando

		expandoRowLocalService.deleteRows(spiDefinition.getSpiDefinitionId());

		// Resources

		resourceLocalService.deleteResource(
			spiDefinition.getCompanyId(), SPIDefinition.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			spiDefinition.getSpiDefinitionId());

		spiDefinitionPersistence.remove(spiDefinition);

		return spiDefinition;
	}

	@Override
	public SPIDefinition getSPIDefinition(long spiDefinitionId)
		throws PortalException, SystemException {

		return spiDefinitionPersistence.findByPrimaryKey(spiDefinitionId);
	}

	@Override
	public SPIDefinition getSPIDefinition(long companyId, String name)
		throws PortalException, SystemException {

		return spiDefinitionPersistence.findByC_N(companyId, name);
	}

	@Override
	public Collection<SPIDefinition> getSPIDefinitions()
		throws SystemException {

		return spiDefinitionPersistence.findAll();
	}

	@Override
	public SPIDefinition updateSPIDefinition(
			long userId, long spiDefinitionId, String description,
			String applications, String jvmArguments, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		spiDefinition.setCompanyId(user.getCompanyId());
		spiDefinition.setModifiedDate(serviceContext.getModifiedDate(null));
		spiDefinition.setUserId(user.getUserId());
		spiDefinition.setUserName(user.getFullName());

		spiDefinition.setApplications(applications);
		spiDefinition.setDescription(description);
		spiDefinition.setJvmArguments(jvmArguments);
		spiDefinition.setTypeSettings(typeSettings);

		spiDefinition.setExpandoBridgeAttributes(serviceContext);

		spiDefinitionPersistence.update(spiDefinition);

		// Resources

		resourceLocalService.updateResources(
			user.getCompanyId(), 0, SPIDefinition.class.getName(),
			spiDefinitionId, serviceContext.getGroupPermissions(),
			serviceContext.getGuestPermissions());

		return spiDefinition;
	}

	protected void validate(long companyId, String name)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.fetchByC_N(
			companyId, name);

		if (spiDefinition != null) {
			throw new DuplicateSPIDefinitionException(
				"SPI definition already exists with name: " + name);
		}
	}

}