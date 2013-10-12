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

package com.liferay.portal.resiliency.spi.util;

/**
 * @author Michael C. Han
 */
public class SPIConfigurationTemplate {

	public static int getConnectorPortMax() {
		return _connectorPortMax;
	}

	public static int getConnectorPortMin() {
		return _connectorPortMin;
	}

	public static String getJavaExecutable() {
		return _javaExecutable;
	}

	public static String getJVMArguments() {
		return _jvmArguments;
	}

	public static int getMaxThreads() {
		return _maxThreads;
	}

	public static int getMinThreads() {
		return _minThreads;
	}

	public static String getSPIAgentClassName() {
		return _spiAgentClassName;
	}

	public static int getSPIPingInterval() {
		return _spiPingInterval;
	}

	public static String getSPIProviderName() {
		return _spiProviderName;
	}

	public static int getSPIRegisterTimeout() {
		return _spiRegisterTimeout;
	}

	public static int getSPIShutdownTimeout() {
		return _spiShutdownTimeout;
	}

	public void setConnectorPortMax(int connectorPortMax) {
		_connectorPortMax = connectorPortMax;
	}

	public void setConnectorPortMin(int connectorPortMin) {
		_connectorPortMin = connectorPortMin;
	}

	public void setJavaExecutable(String javaExecutable) {
		_javaExecutable = javaExecutable;
	}

	public void setJVMArguments(String jvmArguments) {
		_jvmArguments = jvmArguments;
	}

	public void setMaxThreads(int maxThreads) {
		_maxThreads = maxThreads;
	}

	public void setMinThreads(int minThreads) {
		_minThreads = minThreads;
	}

	public void setSPIAgentClassName(String spiAgentClassName) {
		_spiAgentClassName = spiAgentClassName;
	}

	public void setSPIPingInterval(int spiPingInterval) {
		_spiPingInterval = spiPingInterval;
	}

	public void setSPIProviderName(String spiProviderName) {
		_spiProviderName = spiProviderName;
	}

	public void setSPIRegisterTimeout(int spiRegisterTimeout) {
		_spiRegisterTimeout = spiRegisterTimeout;
	}

	public void setSPIShutdownTimeout(int spiShutdownTimeout) {
		_spiShutdownTimeout = spiShutdownTimeout;
	}

	private static int _connectorPortMax;
	private static int _connectorPortMin;
	private static String _javaExecutable;
	private static String _jvmArguments;
	private static int _maxThreads;
	private static int _minThreads;
	private static String _spiAgentClassName;
	private static int _spiPingInterval;
	private static String _spiProviderName;
	private static int _spiRegisterTimeout;
	private static int _spiShutdownTimeout;

}