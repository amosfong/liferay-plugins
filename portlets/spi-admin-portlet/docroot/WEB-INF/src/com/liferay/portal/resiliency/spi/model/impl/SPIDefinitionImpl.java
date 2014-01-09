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

package com.liferay.portal.resiliency.spi.model.impl;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.resiliency.mpi.MPIHelperUtil;
import com.liferay.portal.kernel.resiliency.spi.SPI;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.resiliency.spi.util.SPIAdminConstants;
import com.liferay.portal.resiliency.spi.util.SPIConfigurationTemplate;

import java.io.File;

import java.rmi.RemoteException;

/**
 * @author Michael C. Han
 */
public class SPIDefinitionImpl extends SPIDefinitionBaseImpl {

	@Override
	public void deleteBaseDir() {
		String baseDirName = getBaseDirName();

		File baseDir = new File(baseDirName);

		FileUtil.deltree(baseDir);
	}

	@Override
	public String getAgentClassName() {
		return GetterUtil.getString(
			getTypeSettingsProperty("agent-class-name"));
	}

	@Override
	public String getBaseDir() throws SystemException {
		String baseDirName = getBaseDirName();

		File baseDir = new File(baseDirName);

		FileUtil.deltree(baseDir);

		if (!baseDir.mkdir()) {
			throw new SystemException(
				"Unable to create base directory " + baseDirName);
		}

		return baseDirName;
	}

	@Override
	public String getJavaExecutable() {
		return GetterUtil.getString(getTypeSettingsProperty("java-executable"));
	}

	@Override
	public int getMaxThreads() {
		return GetterUtil.getInteger(getTypeSettingsProperty("max-threads"));
	}

	@Override
	public int getMinThreads() {
		return GetterUtil.getInteger(getTypeSettingsProperty("min-threads"));
	}

	@Override
	public long getPingInterval() {
		return GetterUtil.getLong(getTypeSettingsProperty("ping-interval"));
	}

	@Override
	public long getRegisterTimeout() {
		return GetterUtil.getLong(getTypeSettingsProperty("register-timeout"));
	}

	@Override
	public long getShutdownTimeout() {
		return GetterUtil.getLong(getTypeSettingsProperty("shutdown-timeout"));
	}

	@Override
	public SPI getSPI() {
		if (_spi == null) {
			_spi = MPIHelperUtil.getSPI(
				SPIConfigurationTemplate.getSPIProviderName(),
				String.valueOf(getSpiDefinitionId()));
		}

		return _spi;
	}

	@Override
	public String getStatusLabel() {
		int status = getStatus();

		return SPIAdminConstants.getStatusLabel(status);
	}

	@Override
	public String getTypeSettings() {
		if (_typeSettingsProperties == null) {
			return super.getTypeSettings();
		}
		else {
			return _typeSettingsProperties.toString();
		}
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties() {
		if (_typeSettingsProperties == null) {
			_typeSettingsProperties = new UnicodeProperties(true);

			_typeSettingsProperties.fastLoad(super.getTypeSettings());
		}

		return _typeSettingsProperties;
	}

	@Override
	public String getTypeSettingsProperty(String key) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return typeSettingsProperties.getProperty(key);
	}

	@Override
	public String getTypeSettingsProperty(String key, String defaultValue) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return typeSettingsProperties.getProperty(key, defaultValue);
	}

	@Override
	public boolean isAlive() {
		SPI spi = getSPI();

		if (spi == null) {
			return false;
		}

		try {
			return spi.isAlive();
		}
		catch (RemoteException e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to determine whether " + getName() +
						" is alive or not",
					e);
			}

			return false;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettingsProperties = null;

		super.setTypeSettings(typeSettings);
	}

	@Override
	public void setTypeSettingsProperties(
		UnicodeProperties typeSettingsProperties) {

		_typeSettingsProperties = typeSettingsProperties;

		super.setTypeSettings(_typeSettingsProperties.toString());
	}

	protected String getBaseDirName() {
		return System.getProperty("java.io.tmpdir") + File.separator +
			getSpiDefinitionId();
	}

	private static Log _log = LogFactoryUtil.getLog(SPIDefinitionImpl.class);

	private SPI _spi;
	private UnicodeProperties _typeSettingsProperties;

}