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

package com.liferay.lcs.messaging;

import com.liferay.lcs.service.LCSGatewayService;
import com.liferay.lcs.util.HandshakeManagerUtil;
import com.liferay.lcs.util.KeyGenerator;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.monitoring.statistics.DataSample;
import com.liferay.portal.kernel.util.TextFormatter;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class PortalMetricsMessageListener implements MessageListener {

	@Override
	public void receive(Message message) {
		if (!HandshakeManagerUtil.isReady()) {
			if (_log.isDebugEnabled()) {
				_log.debug("Waiting for handshake manager");
			}

			return;
		}

		MetricsMessage metricsMessage = new MetricsMessage();

		metricsMessage.setCreateTime(System.currentTimeMillis());
		metricsMessage.setKey(_keyGenerator.getKey());

		Object payload = null;

		if (message.getPayload() instanceof DataSample) {
			DataSample dataSample = (DataSample)message.getPayload();

			metricsMessage.setMetricsType(getMetricsType(dataSample));

			payload = getPerformanceMetrics(dataSample);
		}
		else {
			List<Object> performanceMetricsList = new ArrayList<Object>();

			List<DataSample> dataSamples =
				(List<DataSample>)message.getPayload();

			for (DataSample dataSample : dataSamples) {
				Map<String, Object> performanceMetrics = getPerformanceMetrics(
					dataSample);

				performanceMetrics.put(
					"metricsType", getMetricsType(dataSample));

				performanceMetricsList.add(performanceMetrics);
			}

			metricsMessage.setMetricsType(MetricsMessage.METRICS_TYPE_PORTAL);

			payload = performanceMetricsList;
		}

		metricsMessage.setPayload(payload);

		if (_log.isDebugEnabled()) {
			_log.debug("Sending " + metricsMessage);
		}

		try {
			_lcsGatewayService.sendMessage(metricsMessage);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected String getMetricsType(DataSample dataSample) {
		String namespace = dataSample.getNamespace();

		if (namespace.contains("Portal")) {
			return MetricsMessage.METRICS_TYPE_LAYOUT;
		}
		else if (namespace.contains("Portlet")) {
			return MetricsMessage.METRICS_TYPE_PORTLET;
		}
		else {
			return MetricsMessage.METRICS_TYPE_SERVICE;
		}
	}

	protected Map<String, Object> getPerformanceMetrics(DataSample dataSample) {
		Map<String, Object> performanceMetrics = new HashMap<String, Object>();

		Class<?> clazz = dataSample.getClass();

		Method[] methods = clazz.getMethods();

		for (Method method : methods) {
			String methodName = method.getName();

			if (!methodName.startsWith("get") ||
				methodName.equals("getClass")) {

				continue;
			}

			String name = methodName.substring(3, methodName.length());

			name = TextFormatter.format(name, TextFormatter.I);

			Object value = null;

			try {
				value = method.invoke(dataSample);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}

				continue;
			}

			if ((value != null) && !(value instanceof Number) &&
				!(value instanceof String)) {

				value = String.valueOf(value);
			}

			if (value != null) {
				performanceMetrics.put(name, value);
			}
		}

		return performanceMetrics;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortalMetricsMessageListener.class);

	@BeanReference(type = KeyGenerator.class)
	private KeyGenerator _keyGenerator;

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

}