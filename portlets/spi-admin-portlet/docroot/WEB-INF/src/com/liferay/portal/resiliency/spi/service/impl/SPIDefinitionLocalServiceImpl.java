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
import com.liferay.portal.resiliency.spi.DuplicateSPIDefinitionAddressAndPortException;
import com.liferay.portal.resiliency.spi.DuplicateSPIDefinitionException;
import com.liferay.portal.resiliency.spi.InvalidSPIDefinitionAddressAndPortException;
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

		validate(user.getCompanyId(), name);
		validate(connectorAddress, connectorPort);

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
			jvmArguments = SPIConfigurationTemplate.getJvmArguments();
		}

		spiDefinition.setJvmArguments(jvmArguments);
		spiDefinition.setPortletIds(portletIds);
		spiDefinition.setServletContextNames(servletContextNames);

		typeSettings = normalizeTypeSettings(typeSettings);

		spiDefinition.setTypeSettings(typeSettings);

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

		spiDefinitionLocalService.stopSPI(spiDefinition.getSpiDefinitionId());

		// SPI definition

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
			SPIConfigurationTemplate.getSpiProviderName(),
			String.valueOf(spiDefinition.getSpiDefinitionId()));

		if (spi == null) {
			spi = createSPI(spiDefinition);
		}

		try {
			spi.init();

			if (_log.isInfoEnabled()) {
				_log.info("Initialized SPI: " + name);
			}

			String portalContextPath = PortalUtil.getPathContext();

			ServletContext rootServletContext = ServletContextPool.get(
				portalContextPath);

			String rootDir = rootServletContext.getRealPath(portalContextPath);

			spi.addWebapp(portalContextPath, rootDir);

			if (_log.isInfoEnabled()) {
				_log.info(
					"Add portal with baseDir: " + rootDir + " to SPI : " +
					spi);
			}

			spi.start();

			if (_log.isInfoEnabled()) {
				_log.info("Started SPI : " + spi);
			}

			SPIConfiguration spiConfiguration = spi.getSPIConfiguration();

			String webappsDir = rootDir.substring(0, rootDir.length() - 5);

			for (String servletContextName :
					spiConfiguration.getServletContextNames()) {

				ServletContext servletContext = ServletContextPool.get(
					servletContextName);

				String contextPath = servletContext.getContextPath();
				String docBasePath = webappsDir.concat(contextPath);

				spi.addWebapp(contextPath, docBasePath);

				if (_log.isInfoEnabled()) {
					_log.info(
						"Add webapp contextPath : " + contextPath +
						" to SPI : " + spi);
				}
			}
		}
		catch (RemoteException e) {
			throw new PortalException("Unable to initialize SPI", e);
		}
	}

	@Clusterable
	@Override
	public void stopSPI(long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		SPI spi = MPIHelperUtil.getSPI(
			SPIConfigurationTemplate.getSpiProviderName(),
			String.valueOf(spiDefinitionId));

		if (spi == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("SPI not registered: " + spiDefinition.getName());
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
				_log.warn("Unable to communicate with SPI", e);
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

			validate(connectorAddress, connectorPort);
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

		typeSettings = normalizeTypeSettings(typeSettings);

		spiDefinition.setTypeSettings(typeSettings);

		spiDefinition.setExpandoBridgeAttributes(serviceContext);

		spiDefinitionPersistence.update(spiDefinition);

		return spiDefinition;
	}

	protected SPI createSPI(SPIDefinition spiDefinition)
		throws PortalException {

		String jvmArguments = spiDefinition.getJvmArguments();

		String[] portletIds = StringUtil.split(spiDefinition.getPortletIds());

		String[] servletContextNames = StringUtil.split(
			spiDefinition.getServletContextNames());

		UnicodeProperties typeSettings =
			spiDefinition.getTypeSettingsProperties();

		String agentClassName = GetterUtil.getString(
			typeSettings.getProperty("agent-class-name"));

		String baseDir = PortalUtil.getPathContext();

		String javaExecutable = GetterUtil.getString(
			typeSettings.getProperty("java-executable"));

		long pingInterval = GetterUtil.getInteger(
			typeSettings.getProperty("ping-interval"));

		long registerTimeout = GetterUtil.getLong(
			typeSettings.getProperty("register-timeout"));

		long shutdownTimeout = GetterUtil.getLong(
			typeSettings.getProperty("shutdown-timeout"));

		SPIConfiguration spiConfiguration = new SPIConfiguration(
			String.valueOf(spiDefinition.getSpiDefinitionId()), javaExecutable,
			jvmArguments, agentClassName, spiDefinition.getConnectorPort(),
			baseDir, portletIds, servletContextNames, pingInterval,
			registerTimeout, shutdownTimeout, spiDefinition.getTypeSettings());

		SPIProvider spiProvider = MPIHelperUtil.getSPIProvider(
			SPIConfigurationTemplate.getSpiProviderName());

		try {
			return spiProvider.createSPI(spiConfiguration);
		}
		catch (PortalResiliencyException e) {
			throw new PortalException(e);
		}
	}

	protected String normalizeTypeSettings(String typeSettings) {
		UnicodeProperties typeSettingsProperties = new UnicodeProperties(true);

		typeSettingsProperties.fastLoad(typeSettings);

		typeSettingsProperties.setProperty(
			"agent-class-name",
			SPIConfigurationTemplate.getSpiAgentClassName());

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

		int pingInterval = GetterUtil.getInteger(
			typeSettingsProperties.getProperty("ping-interval"));

		if (pingInterval <= 0) {
			typeSettingsProperties.setProperty(
				"ping-interval",
				String.valueOf(SPIConfigurationTemplate.getSpiPingInterval()));
		}

		int registerTimeout = GetterUtil.getInteger(
			typeSettingsProperties.getProperty("register-timeout"));

		if (registerTimeout <= 0) {
			typeSettingsProperties.setProperty(
				"register-timeout",
				String.valueOf(
					SPIConfigurationTemplate.getSpiRegisterTimeout()));
		}

		int shutdownTimeout = GetterUtil.getInteger(
			typeSettingsProperties.getProperty("shutdown-timeout"));

		if (shutdownTimeout <= 0) {
			typeSettingsProperties.setProperty(
				"shutdown-timeout",
				String.valueOf(
					SPIConfigurationTemplate.getSpiShutdownTimeout()));
		}

		return typeSettingsProperties.toString();
	}

	protected void validate(long companyId, String name)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.fetchByC_N(
			companyId, name);

		if (spiDefinition != null) {
			throw new DuplicateSPIDefinitionException();
		}
	}

	protected void validate(String connectorAddress, int connectorPort)
		throws PortalException, SystemException {

		if (Validator.isNull(connectorAddress) || (connectorPort < 0)) {
			throw new InvalidSPIDefinitionAddressAndPortException();
		}

		SPIDefinition spiDefinition = spiDefinitionPersistence.fetchByCA_CP(
			connectorAddress, connectorPort);

		if (spiDefinition != null) {
			throw new DuplicateSPIDefinitionAddressAndPortException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SPIDefinitionLocalServiceImpl.class);

}