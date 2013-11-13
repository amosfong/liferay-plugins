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

import java.lang.management.ManagementFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Ivica Cardic
 */
public class CacheMetricsTask implements Runnable {

	@Override
	public void run() {
		try {
			doRun();
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected Map<String, Object> getHibernateMetrics() throws Exception {
		Map<String, Object> hibernateMetrics = new HashMap<String, Object>();

		ObjectName objectName = new ObjectName("Hibernate:name=statistics");

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		Object queryCacheHitCount = mBeanServer.getAttribute(
			objectName, "QueryCacheHitCount");

		hibernateMetrics.put("queryCacheHitCount", queryCacheHitCount);

		Object queryCacheMissCount = mBeanServer.getAttribute(
			objectName, "QueryCacheMissCount");

		hibernateMetrics.put("queryCacheMissCount", queryCacheMissCount);

		Object queryExecutionCount = mBeanServer.getAttribute(
			objectName, "QueryExecutionCount");

		hibernateMetrics.put("queryExecutionCount", queryExecutionCount);

		Object queryExecutionMaxTime = mBeanServer.getAttribute(
			objectName, "QueryExecutionMaxTime");

		hibernateMetrics.put("queryExecutionMaxTime", queryExecutionMaxTime);

		return hibernateMetrics;
	}

	protected Map<String, Map<String, Object>> getLiferayMultiVMMetrics()
		throws Exception {

		Map<String, Map<String, Object>> liferayMultiVMMetrics =
			new TreeMap<String, Map<String, Object>>();

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		Set<ObjectName> objectNames = mBeanServer.queryNames(
			new ObjectName(
				"net.sf.ehcache:type=CacheStatistics,CacheManager=" +
					"liferay-multi-vm-clustered,name=*"),
			null);

		for (ObjectName objectName : objectNames) {
			String name = objectName.getKeyProperty("name");

			Map<String, Object> values = new HashMap<String, Object>();

			Object cacheHits = mBeanServer.getAttribute(
				objectName, "CacheHits");

			values.put("cacheHits", cacheHits);

			Object cacheMisses = mBeanServer.getAttribute(
				objectName, "CacheMisses");

			values.put("cacheMisses", cacheMisses);

			Object objectCount = mBeanServer.getAttribute(
				objectName, "ObjectCount");

			values.put("objectCount", objectCount);

			liferayMultiVMMetrics.put(name, values);
		}

		return liferayMultiVMMetrics;
	}

	protected Map<String, Map<String, Object>> getLiferaySingleVMMetrics()
		throws Exception {

		Map<String, Map<String, Object>> liferaySingleVMMetrics =
			new TreeMap<String, Map<String, Object>>();

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

		Set<ObjectName> objectNames = mBeanServer.queryNames(
			new ObjectName(
				"net.sf.ehcache:type=CacheStatistics,CacheManager=" +
					"liferay-single-vm,name=*"),
			null);

		for (ObjectName objectName : objectNames) {
			String name = objectName.getKeyProperty("name");

			Map<String, Object> values = new HashMap<String, Object>();

			Object cacheHits = mBeanServer.getAttribute(
				objectName, "CacheHits");

			values.put("cacheHits", cacheHits);

			Object cacheMisses = mBeanServer.getAttribute(
				objectName, "CacheMisses");

			values.put("cacheMisses", cacheMisses);

			Object objectCount = mBeanServer.getAttribute(
				objectName, "ObjectCount");

			values.put("objectCount", objectCount);

			liferaySingleVMMetrics.put(name, values);
		}

		return liferaySingleVMMetrics;
	}

	protected Object getPayload() throws Exception {
		Map<String, Object> payload = new HashMap<String, Object>();

		payload.put("hibernateMetrics", getHibernateMetrics());
		payload.put("liferayMultiVMMetrics", getLiferayMultiVMMetrics());
		payload.put("liferaySingleVMMetrics", getLiferaySingleVMMetrics());

		return payload;
	}

	private void doRun() throws Exception {
		MetricsMessage metricsMessage = new MetricsMessage();

		metricsMessage.setCreateTime(System.currentTimeMillis());
		metricsMessage.setKey(_keyGenerator.getKey());
		metricsMessage.setMetricsType(MetricsMessage.METRICS_TYPE_CACHE);
		metricsMessage.setPayload(getPayload());

		if (_log.isDebugEnabled()) {
			_log.debug("Sending " + metricsMessage);
		}

		_lcsGatewayService.sendMessage(metricsMessage);
	}

	private static Log _log = LogFactoryUtil.getLog(CacheMetricsTask.class);

	@BeanReference(type = KeyGenerator.class)
	private KeyGenerator _keyGenerator;

	@BeanReference(type = LCSGatewayService.class)
	private LCSGatewayService _lcsGatewayService;

}