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

import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.resiliency.PortalResiliencyException;
import com.liferay.portal.kernel.resiliency.mpi.MPIHelperUtil;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.resiliency.spi.SPIConfiguration;
import com.liferay.portal.kernel.resiliency.spi.provider.SPIProvider;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.resiliency.spi.DuplicateSPIDefinitionConnectorException;
import com.liferay.portal.resiliency.spi.DuplicateSPIDefinitionException;
import com.liferay.portal.resiliency.spi.InvalidSPIDefinitionConnectorException;
import com.liferay.portal.resiliency.spi.SPIDefinitionActiveException;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.service.base.SPIDefinitionLocalServiceBaseImpl;
import com.liferay.portal.resiliency.spi.util.SPIConfigurationTemplate;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import java.rmi.RemoteException;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

/**
 * @author Michael C. Han
 */
public class SPIDefinitionLocalServiceImpl
	extends SPIDefinitionLocalServiceBaseImpl {

	@Override
	public SPIDefinition addSPIDefinition(
			long userId, String name, String connectorAddress,
			int connectorPort, String description, String jvmArguments,
			String portletIds, String servletContextNames, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// SPI definition

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validateName(user.getCompanyId(), name);
		validateConnector(connectorAddress, connectorPort);

		long spiDefinitionId = counterLocalService.increment();

		SPIDefinition spiDefinition = spiDefinitionPersistence.create(
			spiDefinitionId);

		spiDefinition.setCompanyId(user.getCompanyId());
		spiDefinition.setCreateDate(serviceContext.getCreateDate(now));
		spiDefinition.setModifiedDate(serviceContext.getModifiedDate(now));
		spiDefinition.setUserId(user.getUserId());
		spiDefinition.setUserName(user.getFullName());
		spiDefinition.setName(name);
		spiDefinition.setConnectorAddress(connectorAddress);
		spiDefinition.setConnectorPort(connectorPort);
		spiDefinition.setDescription(description);

		if (Validator.isNull(jvmArguments)) {
			jvmArguments = SPIConfigurationTemplate.getJVMArguments();
		}

		spiDefinition.setJvmArguments(jvmArguments);

		spiDefinition.setPortletIds(portletIds);
		spiDefinition.setServletContextNames(servletContextNames);
		spiDefinition.setTypeSettings(normalizeTypeSettings(typeSettings));
		spiDefinition.setExpandoBridgeAttributes(serviceContext);

		spiDefinitionPersistence.update(spiDefinition);

		// Resources

		resourceLocalService.addModelResources(spiDefinition, serviceContext);

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

		// SPI definition

		spiDefinitionLocalService.stopSPI(spiDefinition.getSpiDefinitionId());

		spiDefinitionPersistence.remove(spiDefinition);

		// Resources

		resourceLocalService.deleteResource(
			spiDefinition, ResourceConstants.SCOPE_INDIVIDUAL);

		// Expando

		expandoRowLocalService.deleteRows(spiDefinition.getSpiDefinitionId());

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
	public List<SPIDefinition> getSPIDefinitions() throws SystemException {
		return spiDefinitionPersistence.findAll();
	}

	@Clusterable
	@Override
	public void startSPI(long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		String name = spiDefinition.getName();

		SPI spi = MPIHelperUtil.getSPI(
			SPIConfigurationTemplate.getSPIProviderName(),
			String.valueOf(spiDefinition.getSpiDefinitionId()));

		if (spi == null) {
			spi = createSPI(spiDefinition);
		}

		try {
			spi.init();

			if (_log.isInfoEnabled()) {
				_log.info("Initialized SPI " + name);
			}

			String portalContextPath = PortalUtil.getPathContext();

			ServletContext portalServletContext = ServletContextPool.get(
				portalContextPath);

			String portalDirName = portalServletContext.getRealPath(
				portalContextPath);

			spi.addWebapp(portalContextPath, portalDirName);

			if (_log.isInfoEnabled()) {
				_log.info("Add portal " + portalDirName + " to SPI " + spi);
			}

			spi.start();

			if (_log.isInfoEnabled()) {
				_log.info("Started SPI " + spi);
			}

			SPIConfiguration spiConfiguration = spi.getSPIConfiguration();

			String webappsDirName = portalDirName.substring(
				0, portalDirName.length() - 5);

			for (String servletContextName :
					spiConfiguration.getServletContextNames()) {

				ServletContext servletContext = ServletContextPool.get(
					servletContextName);

				String contextPath = servletContext.getContextPath();

				spi.addWebapp(contextPath, webappsDirName.concat(contextPath));

				if (_log.isInfoEnabled()) {
					_log.info("Add plugin " + contextPath + " to SPI " + spi);
				}
			}
		}
		catch (RemoteException re) {
			throw new PortalException(
				"Unable to initialize SPI " + spiDefinitionId, re);
		}
	}

	@Clusterable
	@Override
	public void stopSPI(long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		SPI spi = MPIHelperUtil.getSPI(
			SPIConfigurationTemplate.getSPIProviderName(),
			String.valueOf(spiDefinitionId));

		if (spi == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No SPI with name " + spiDefinition.getName());
			}

			return;
		}

		try {
			if (spi.isAlive()) {
				spi.stop();
			}

			spi.destroy();
		}
		catch (RemoteException e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to stop SPI " + spiDefinitionId, e);
			}
		}
	}

	@Override
	public SPIDefinition updateSPIDefinition(
			long userId, long spiDefinitionId, String connectorAddress,
			int connectorPort, String description, String jvmArguments,
			String portletIds, String servletContextNames, String typeSettings,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		if (!spiDefinition.getConnectorAddress().equals(connectorAddress) &&
			(spiDefinition.getConnectorPort() != connectorPort)) {

			validateConnector(connectorAddress, connectorPort);
		}

		if (spiDefinition.isAlive()) {
			throw new SPIDefinitionActiveException();
		}

		spiDefinition.setCompanyId(user.getCompanyId());
		spiDefinition.setModifiedDate(serviceContext.getModifiedDate(null));
		spiDefinition.setUserId(user.getUserId());
		spiDefinition.setUserName(user.getFullName());
		spiDefinition.setConnectorAddress(connectorAddress);
		spiDefinition.setConnectorPort(connectorPort);
		spiDefinition.setDescription(description);
		spiDefinition.setJvmArguments(jvmArguments);
		spiDefinition.setPortletIds(portletIds);
		spiDefinition.setServletContextNames(servletContextNames);
		spiDefinition.setTypeSettings(normalizeTypeSettings(typeSettings));
		spiDefinition.setExpandoBridgeAttributes(serviceContext);

		spiDefinitionPersistence.update(spiDefinition);

		return spiDefinition;
	}

	protected SPI createSPI(SPIDefinition spiDefinition)
		throws PortalException {

		SPIProvider spiProvider = MPIHelperUtil.getSPIProvider(
			SPIConfigurationTemplate.getSPIProviderName());

		SPIConfiguration spiConfiguration = new SPIConfiguration(
			String.valueOf(spiDefinition.getSpiDefinitionId()),
			spiDefinition.getJavaExecutable(), spiDefinition.getJvmArguments(),
			spiDefinition.getAgentClassName(), spiDefinition.getConnectorPort(),
			PortalUtil.getPathContext(),
			StringUtil.split(spiDefinition.getPortletIds()),
			StringUtil.split(spiDefinition.getServletContextNames()),
			spiDefinition.getPingInterval(), spiDefinition.getRegisterTimeout(),
			spiDefinition.getShutdownTimeout(),
			spiDefinition.getTypeSettings());

		try {
			return spiProvider.createSPI(spiConfiguration);
		}
		catch (PortalResiliencyException pre) {
			throw new PortalException(pre);
		}
	}

	protected String normalizeTypeSettings(String typeSettings) {
		UnicodeProperties typeSettingsProperties = new UnicodeProperties(true);

		typeSettingsProperties.fastLoad(typeSettings);

		typeSettingsProperties.setProperty(
			"agent-class-name",
			SPIConfigurationTemplate.getSPIAgentClassName());

		String javaExecutable = GetterUtil.getString(
			typeSettingsProperties.getProperty("java-executable"));

		if (Validator.isNull(javaExecutable)) {
			typeSettingsProperties.setProperty(
				"java-executable",
				SPIConfigurationTemplate.getJavaExecutable());
		}

		int maxThreads = GetterUtil.getInteger(
			typeSettingsProperties.getProperty("max-threads"));

		if (maxThreads <= 0) {
			typeSettingsProperties.setProperty(
				"max-threads",
				String.valueOf(SPIConfigurationTemplate.getMaxThreads()));
		}

		long pingInterval = GetterUtil.getLong(
			typeSettingsProperties.getProperty("ping-interval"));

		if (pingInterval <= 0) {
			typeSettingsProperties.setProperty(
				"ping-interval",
				String.valueOf(SPIConfigurationTemplate.getSPIPingInterval()));
		}

		long registerTimeout = GetterUtil.getLong(
			typeSettingsProperties.getProperty("register-timeout"));

		if (registerTimeout <= 0) {
			typeSettingsProperties.setProperty(
				"register-timeout",
				String.valueOf(
					SPIConfigurationTemplate.getSPIRegisterTimeout()));
		}

		long shutdownTimeout = GetterUtil.getLong(
			typeSettingsProperties.getProperty("shutdown-timeout"));

		if (shutdownTimeout <= 0) {
			typeSettingsProperties.setProperty(
				"shutdown-timeout",
				String.valueOf(
					SPIConfigurationTemplate.getSPIShutdownTimeout()));
		}

		return typeSettingsProperties.toString();
	}

	protected void validateConnector(String connectorAddress, int connectorPort)
		throws PortalException, SystemException {

		if (Validator.isNull(connectorAddress) || (connectorPort < 0)) {
			throw new InvalidSPIDefinitionConnectorException();
		}

		SPIDefinition spiDefinition = spiDefinitionPersistence.fetchByCA_CP(
			connectorAddress, connectorPort);

		if (spiDefinition != null) {
			throw new DuplicateSPIDefinitionConnectorException();
		}
	}

	protected void validateName(long companyId, String name)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.fetchByC_N(
			companyId, name);

		if (spiDefinition != null) {
			throw new DuplicateSPIDefinitionException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SPIDefinitionLocalServiceImpl.class);

}