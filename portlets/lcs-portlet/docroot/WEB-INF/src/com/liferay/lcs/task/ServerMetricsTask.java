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

package com.liferay.lcs.task;

import com.liferay.lcs.messaging.MetricsMessage;
import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.lcs.util.KeyGenerator;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.lang.management.ManagementFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Ivica Cardic
 */
public class ServerMetricsTask implements Runnable {

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void doRun() throws Exception {
		MetricsMessage metricsMessage = new MetricsMessage();

		metricsMessage.setCreateTime(System.currentTimeMillis());
		metricsMessage.setKey(_keyGenerator.getKey());
		metricsMessage.setMetricsType(MetricsMessage.METRICS_TYPE_SERVER);
		metricsMessage.setPayload(getPayload());

		if (_log.isDebugEnabled()) {
			_log.debug("Sending " + metricsMessage);
		}

		_lcsGatewayService.sendMessage(metricsMessage);
	}

	protected Map<String, Map<String, Object>> getCurrentThreadsMetrics()
		throws Exception {

		if (!ServerDetector.isTomcat()) {
			return Collections.emptyMap();
		}

		Map<String, Map<String, Object>> currentThreadsMetrics =
			new HashMap<String, Map<String, Object>>();

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		Set<ObjectName> objectNames = mBeanServer.queryNames(
			new ObjectName("Catalina:type=ThreadPool,*"), null);

		for (ObjectName objectName : objectNames) {
			String name = (String)mBeanServer.getAttribute(objectName, "name");

			Map<String, Object> values = new HashMap<String, Object>();

			Object currentThreadsBusy = mBeanServer.getAttribute(
				objectName, "currentThreadsBusy");

			values.put("currentThreadsBusy", currentThreadsBusy);

			Object currentThreadCount = mBeanServer.getAttribute(
				objectName, "currentThreadCount");

			values.put("currentThreadCount", currentThreadCount);

			currentThreadsMetrics.put(name, values);
		}

		return currentThreadsMetrics;
	}

	protected Map<String, Map<String, Object>> getJDBCConnectionPoolsMetrics()
		throws Exception {

		Map<String, Map<String, Object>> jdbcConnectionPoolsMetrics =
			new HashMap<String, Map<String, Object>>();

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		Properties properties = PropsUtil.getProperties("jdbc.default.", true);

		String jndiName = properties.getProperty("jndi.name");

		if (Validator.isNotNull(jndiName)) {
			if (!ServerDetector.isTomcat()) {
				return Collections.emptyMap();
			}

			ObjectName objectName = new ObjectName(
				"Catalina:type=DataSource,context=/,host=localhost," +
					"class=javax.sql.DataSource,name=\"" + jndiName + "\"");

			Map<String, Object> values = new HashMap<String, Object>();

			Object numActive = mBeanServer.getAttribute(
				objectName, "numActive");

			values.put("numActive", numActive);

			Object numIdle = mBeanServer.getAttribute(objectName, "numIdle");

			values.put("numIdle", numIdle);

			jdbcConnectionPoolsMetrics.put(jndiName, values);
		}
		else {
			String liferayPoolProvider = properties.getProperty(
				"liferay.pool.provider");

			if (StringUtil.equalsIgnoreCase(liferayPoolProvider, "c3p0") ||
				StringUtil.equalsIgnoreCase(liferayPoolProvider, "c3po")) {

				Set<ObjectName> objectNames = mBeanServer.queryNames(
					new ObjectName(
						"com.mchange.v2.c3p0:identityToken=*,name=*,type=" +
							"PooledDataSource"),
					null);

				for (ObjectName objectName : objectNames) {
					String dataSourceName = (String)mBeanServer.getAttribute(
						objectName, "dataSourceName");

					Map<String, Object> values = new HashMap<String, Object>();

					Object numBusyConnections = mBeanServer.getAttribute(
						objectName, "numBusyConnections");

					values.put("numActive", numBusyConnections);

					Object numIdleConnections = mBeanServer.getAttribute(
						objectName, "numIdleConnections");

					values.put("numIdle", numIdleConnections);

					jdbcConnectionPoolsMetrics.put(dataSourceName, values);
				}
			}
			else if (StringUtil.equalsIgnoreCase(liferayPoolProvider, "dbcp")) {
				jdbcConnectionPoolsMetrics = Collections.emptyMap();
			}
			else {
				Set<ObjectName> objectNames = mBeanServer.queryNames(
					new ObjectName(
						"TomcatJDBCPool:type=ConnectionPool,*"),
					null);

				for (ObjectName objectName : objectNames) {
					String name = (String)mBeanServer.getAttribute(
						objectName, "Name");

					Map<String, Object> values = new HashMap<String, Object>();

					Object numActive = mBeanServer.getAttribute(
						objectName, "NumActive");

					values.put("numActive", numActive);

					Object numIdle = mBeanServer.getAttribute(
						objectName, "NumIdle");

					values.put("numIdle", numIdle);

					jdbcConnectionPoolsMetrics.put(name, values);
				}
			}
		}

		return jdbcConnectionPoolsMetrics;
	}

	protected Map<String, Object> getPayload() throws Exception {
		Map<String, Object> payload = new HashMap<String, Object>();

		payload.put("currentThreadsMetrics", getCurrentThreadsMetrics());
		payload.put(
			"jdbcConnectionPoolsMetrics", getJDBCConnectionPoolsMetrics());

		return payload;
	}

	private static Log _log = LogFactoryUtil.getLog(ServerMetricsTask.class);

	@BeanReference(type = KeyGenerator.class)
	private KeyGenerator _keyGenerator;

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

}