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
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.resiliency.spi.DuplicateSPIDefinitionConnectorException;
import com.liferay.portal.resiliency.spi.DuplicateSPIDefinitionException;
import com.liferay.portal.resiliency.spi.InvalidSPIDefinitionConnectorException;
import com.liferay.portal.resiliency.spi.SPIDefinitionActiveException;
import com.liferay.portal.resiliency.spi.backgroundtask.SPIStartBackgroundTaskExecutor;
import com.liferay.portal.resiliency.spi.backgroundtask.SPIStopBackgroundTaskExecutor;
import com.liferay.portal.resiliency.spi.model.SPIDefinition;
import com.liferay.portal.resiliency.spi.service.base.SPIDefinitionLocalServiceBaseImpl;
import com.liferay.portal.resiliency.spi.util.SPIAdminConstants;
import com.liferay.portal.resiliency.spi.util.SPIConfigurationTemplate;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.rmi.RemoteException;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

		setPortletIdsAndServletContextNames(
			spiDefinition, portletIds, servletContextNames, spiDefinitionId);

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
	public Tuple getAllSPIServletContextNamesAndPortletIds()
		throws PortalException, SystemException {

		return doGetAllSPIServletContextNamesAndPortletIds(0);
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

	@Override
	public List<SPIDefinition> getSPIDefinitions(long companyId, int status)
		throws SystemException {

		return spiDefinitionPersistence.findByC_S(companyId, status);
	}

	@Override
	public List<SPIDefinition> getSPIDefinitions(long companyId, int[] statuses)
		throws SystemException {

		return spiDefinitionPersistence.findByC_S(companyId, statuses);
	}

	@Clusterable
	@Override
	public void startSPI(long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		String name = spiDefinition.getName();

		SPI spi = spiDefinition.getSPI();

		if (spi == null) {
			spi = createSPI(spiDefinition);
		}

		if (spiDefinition.isAlive()) {
			return;
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

			spiDefinition.setStatus(SPIAdminConstants.STATUS_STARTED);
			spiDefinition.setStatusMessage(null);

			spiDefinitionPersistence.update(spiDefinition);
		}
		catch (RemoteException re) {
			throw new PortalException(
				"Unable to initialize SPI " + spiDefinitionId, re);
		}
	}

	@Override
	public long startSPIinBackground(long userId, long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		Map<String, Serializable> taskContextMap =
			new HashMap<String, Serializable>();

		taskContextMap.put("spiDefinitionId", spiDefinitionId);

		StringBundler taskName = new StringBundler(4);
		taskName.append("start_");
		taskName.append(spiDefinition.getName());
		taskName.append("_");
		taskName.append(System.currentTimeMillis());

		BackgroundTask backgroundTask =
			backgroundTaskLocalService.addBackgroundTask(
				userId, 0, taskName.toString(),
				new String[] {_SERVLET_CONTEXT_NAME},
				SPIStartBackgroundTaskExecutor.class, taskContextMap,
				new ServiceContext());

		long backgroundTaskId = backgroundTask.getBackgroundTaskId();

		spiDefinition.setStatus(SPIAdminConstants.STATUS_STARTING);
		spiDefinition.setStatusMessage(null);

		UnicodeProperties typeSettingsProperties =
			spiDefinition.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"backgroundTaskId", String.valueOf(backgroundTaskId));

		spiDefinition.setTypeSettingsProperties(typeSettingsProperties);

		spiDefinitionPersistence.update(spiDefinition);

		return backgroundTaskId;
	}

	@Clusterable
	@Override
	public void stopSPI(long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		SPI spi = spiDefinition.getSPI();

		if (spi == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No SPI with name " + spiDefinition.getName());
			}

			return;
		}

		try {
			if (spiDefinition.isAlive()) {
				spi.stop();
			}

			spi.destroy();

			spiDefinition.setStatusMessage(null);
		}
		catch (RemoteException e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to stop SPI " + spiDefinitionId, e);
			}

			spiDefinition.setStatusMessage(StackTraceUtil.getStackTrace(e));
		}
		finally {
			spiDefinition.setStatus(SPIAdminConstants.STATUS_STOPPED);
		}

		spiDefinitionPersistence.update(spiDefinition);
	}

	@Override
	public long stopSPIinBackground(long userId, long spiDefinitionId)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		Map<String, Serializable> taskContextMap =
			new HashMap<String, Serializable>();

		taskContextMap.put("spiDefinitionId", spiDefinitionId);

		StringBundler taskName = new StringBundler(4);
		taskName.append("stop_");
		taskName.append(spiDefinition.getName());
		taskName.append("_");
		taskName.append(System.currentTimeMillis());

		BackgroundTask backgroundTask =
			backgroundTaskLocalService.addBackgroundTask(
				userId, 0, taskName.toString(),
				new String[] {_SERVLET_CONTEXT_NAME},
				SPIStopBackgroundTaskExecutor.class, taskContextMap,
				new ServiceContext());

		long backgroundTaskId = backgroundTask.getBackgroundTaskId();

		spiDefinition.setStatus(SPIAdminConstants.STATUS_STOPPING);
		spiDefinition.setStatusMessage(null);

		UnicodeProperties typeSettingsProperties =
			spiDefinition.getTypeSettingsProperties();

		typeSettingsProperties.setProperty(
			"backgroundTaskId", String.valueOf(backgroundTaskId));

		spiDefinition.setTypeSettingsProperties(typeSettingsProperties);

		spiDefinitionPersistence.update(spiDefinition);

		return backgroundTaskId;
	}

	@Override
	public SPIDefinition updateSPIDefinition(
			long spiDefinitionId, int status, String statusMessage)
		throws PortalException, SystemException {

		SPIDefinition spiDefinition = spiDefinitionPersistence.findByPrimaryKey(
			spiDefinitionId);

		spiDefinition.setStatus(status);
		spiDefinition.setStatusMessage(statusMessage);

		spiDefinitionPersistence.update(spiDefinition);

		return spiDefinition;
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
		setPortletIdsAndServletContextNames(
			spiDefinition, portletIds, servletContextNames, spiDefinitionId);
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

	protected Tuple doGetAllSPIServletContextNamesAndPortletIds(
			long skipSPIDefinitionId)
		throws PortalException, SystemException {

		List<SPIDefinition> spiDefinitions = getSPIDefinitions();

		Set<String> uniquePortletIds = new HashSet<String>();
		Set<String> uniqueServletContextNames = new HashSet<String>();

		for (SPIDefinition spiDefinition : spiDefinitions) {
			if (spiDefinition.getSpiDefinitionId() == skipSPIDefinitionId) {
				continue;
			}

			String servletContextNames = spiDefinition.getServletContextNames();

			populateSet(uniqueServletContextNames, servletContextNames);

			String portletIds = spiDefinition.getPortletIds();

			populateSet(uniquePortletIds, portletIds);
		}

		return new Tuple(uniquePortletIds, uniqueServletContextNames);
	}

	protected Set<String> getUniqueValues(
		String commaSeparatedValues, Set<String> usedValues) {

		String[] valuesArray = StringUtil.split(commaSeparatedValues);
		Set<String> newValues = new HashSet<String>();

		for (String portletId : valuesArray) {
			if (!usedValues.contains(portletId)) {
				newValues.add((portletId));
			}
		}

		return newValues;
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

	protected void populateSet(Set<String> uniqueValues, String values) {
		String[] valuesArray = StringUtil.split(values);

		uniqueValues.addAll(Arrays.asList(valuesArray));
	}

	protected void setPortletIdsAndServletContextNames(
			SPIDefinition spiDefinition, String portletIds,
			String servletContextNames, long skipSPIDefinitionId)
		throws PortalException, SystemException {

		Tuple tuple = doGetAllSPIServletContextNamesAndPortletIds(
			skipSPIDefinitionId);

		Set<String> usedPortletIds = (Set<String>)tuple.getObject(0);
		Set<String> newPortletIds = getUniqueValues(portletIds, usedPortletIds);

		spiDefinition.setPortletIds(StringUtil.merge(newPortletIds));

		Set<String> usedServletContextNames = (Set<String>)tuple.getObject(1);
		Set<String> newServletContextNames = getUniqueValues(
			servletContextNames, usedServletContextNames);

		spiDefinition.setServletContextNames(
			StringUtil.merge(newServletContextNames));
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

	private static final String _SERVLET_CONTEXT_NAME = "spi-admin-portlet";

	private static Log _log = LogFactoryUtil.getLog(
		SPIDefinitionLocalServiceImpl.class);

}