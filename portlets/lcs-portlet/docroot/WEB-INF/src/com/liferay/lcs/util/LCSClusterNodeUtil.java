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

package com.liferay.lcs.util;

import com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderPool;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.license.util.LicenseManagerUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeUtil {

	public static Map<String, Object> getServerInfo() {
		if (_log.isDebugEnabled()) {
			_log.debug("Get server information");
		}

		Map<String, Object> serverInfo = new HashMap<String, Object>();

		try {
			serverInfo.put("key", KeyGeneratorUtil.getKey());

			if (LCSUtil.getCredentialsStatus() == LCSUtil.CREDENTIALS_SET) {
				serverInfo.put(
					"registered",
					LCSClusterNodeServiceUtil.isRegistered(
						KeyGeneratorUtil.getKey()));
			}
			else {
				serverInfo.put("registered", false);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Server information " + MapUtil.toString(serverInfo));
		}

		return serverInfo;
	}

	public static boolean registerLCSClusterNode() throws Exception {
		if (!ClusterExecutorUtil.isEnabled()) {
			return false;
		}

		String siblingKey = null;

		ClusterNode localClusterNode =
			ClusterExecutorUtil.getLocalClusterNode();

		String localClusterNodeId = localClusterNode.getClusterNodeId();

		List<ClusterNode> clusterNodes = ClusterExecutorUtil.getClusterNodes();

		for (ClusterNode clusterNode : clusterNodes) {
			String clusterNodeId = clusterNode.getClusterNodeId();

			if (!clusterNodeId.equals(localClusterNodeId)) {
				Map<String, Object> serverInfo = _getServerInfo(clusterNodeId);

				boolean registered = false;

				if ((serverInfo != null) &&
					serverInfo.containsKey("registered")) {

					registered = (Boolean)serverInfo.get("registered");
				}

				if (registered) {
					siblingKey = (String)serverInfo.get("key");

					break;
				}
			}
		}

		if (siblingKey != null) {
			LCSClusterNodeServiceUtil.addLCSClusterNode(
				siblingKey, LicenseManagerUtil.getHostName(), StringPool.BLANK,
				KeyGeneratorUtil.getKey(),
				StringUtil.merge(LicenseManagerUtil.getIpAddresses()));

			return true;
		}

		return false;
	}

	public static Map<String, Object> registerLCSClusterNode(
			long lcsClusterEntryId, String name, String description,
			String location)
		throws Exception {

		if (!ClusterExecutorUtil.isEnabled()) {
			LCSClusterNodeServiceUtil.addLCSClusterNode(
				lcsClusterEntryId, name, description,
				ReleaseInfo.getBuildNumber(), KeyGeneratorUtil.getKey(),
				location);

			HandshakeManagerUtil.start();

			return Collections.emptyMap();
		}

		Map<String, Object> map = new HashMap<String, Object>();

		ClusterNode localClusterNode =
			ClusterExecutorUtil.getLocalClusterNode();

		String localClusterNodeId = localClusterNode.getClusterNodeId();

		String siblingKey = null;
		List<String> unregisteredClusterNodeIds = new ArrayList<String>();

		List<ClusterNode> clusterNodes = ClusterExecutorUtil.getClusterNodes();

		for (ClusterNode clusterNode : clusterNodes) {
			String clusterNodeId = clusterNode.getClusterNodeId();

			if (!clusterNodeId.equals(localClusterNodeId)) {
				Map<String, Object> serverInfo = _getServerInfo(clusterNodeId);

				boolean registered = (Boolean)serverInfo.get("registered");

				if (registered) {
					siblingKey = (String)serverInfo.get("key");
				}
				else {
					unregisteredClusterNodeIds.add(clusterNodeId);
				}
			}
		}

		if (siblingKey == null) {
			siblingKey = KeyGeneratorUtil.getKey();

			LCSClusterNodeServiceUtil.addLCSClusterNode(
				lcsClusterEntryId, name, description,
				ReleaseInfo.getBuildNumber(), siblingKey, location);
		}
		else {
			LCSClusterNodeServiceUtil.addLCSClusterNode(
				siblingKey, LicenseManagerUtil.getHostName(), StringPool.BLANK,
				KeyGeneratorUtil.getKey(),
				StringUtil.merge(LicenseManagerUtil.getIpAddresses()));
		}

		MethodHandler registerLCSClusterNodeMethodHandler = new MethodHandler(
			_registerLCSClusterNodeMethodKey, siblingKey);

		MethodHandler invokeMethodHandler = new MethodHandler(
			_invokeMethodKey, registerLCSClusterNodeMethodHandler,
			_getServletContextName());

		for (String clusterNodeId : unregisteredClusterNodeIds) {
			ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
				invokeMethodHandler, clusterNodeId);

			FutureClusterResponses futureClusterResponses =
				ClusterExecutorUtil.execute(clusterRequest);

			ClusterNodeResponses clusterNodeResponses =
				futureClusterResponses.get(20000, TimeUnit.MILLISECONDS);

			ClusterNodeResponse clusterNodeResponse =
				clusterNodeResponses.getClusterResponse(clusterNodeId);

			Map<String, Object> result =
				(Map<String, Object>)clusterNodeResponse.getResult();

			for (Map.Entry<String, Object> entry : result.entrySet()) {
				map.put(
					clusterNodeId + StringPool.UNDERLINE + entry.getKey(),
					entry.getValue());
			}
		}

		HandshakeManagerUtil.start();

		return map;
	}

	private static Map<String, Object> _getServerInfo(String clusterNodeId)
		throws Exception {

		MethodHandler methodHandler = new MethodHandler(
			_invokeMethodKey, _getServerInfoMethodHandler,
			_getServletContextName());

		ClusterRequest clusterRequest = ClusterRequest.createUnicastRequest(
			methodHandler, clusterNodeId);

		FutureClusterResponses futureClusterResponses =
			ClusterExecutorUtil.execute(clusterRequest);

		ClusterNodeResponses clusterNodeResponses = futureClusterResponses.get(
			20000, TimeUnit.MILLISECONDS);

		ClusterNodeResponse clusterNodeResponse =
			clusterNodeResponses.getClusterResponse(clusterNodeId);

		return (Map<String, Object>)clusterNodeResponse.getResult();
	}

	private static String _getServletContextName() {
		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		return ClassLoaderPool.getContextName(classLoader);
	}

	@SuppressWarnings("unused")
	private static Map<String, Object> _registerLCSClusterNode(
		String siblingKey) {

		if (_log.isDebugEnabled()) {
			_log.debug("Register LCS cluster node");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		String key = KeyGeneratorUtil.getKey();

		try {
			LCSUtil.setupCredentials();

			LCSClusterNodeServiceUtil.addLCSClusterNode(
				siblingKey, LicenseManagerUtil.getHostName(), StringPool.BLANK,
				key, StringUtil.merge(LicenseManagerUtil.getIpAddresses()));

			HandshakeManagerUtil.start();

			map.put("success", key);
		}
		catch (Exception e) {
			_log.error(e, e);

			map.put("error", key);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Register LCS cluster node results " + MapUtil.toString(map));
		}

		return map;
	}

	private static Log _log = LogFactoryUtil.getLog(LCSClusterNodeUtil.class);

	private static MethodHandler _getServerInfoMethodHandler =
		new MethodHandler(
			new MethodKey(LCSClusterNodeUtil.class.getName(), "getServerInfo"));
	private static MethodKey _invokeMethodKey = new MethodKey(
		"com.liferay.lcs.util.ClusterLinkHelper", "_invoke",
		MethodHandler.class, String.class);
	private static MethodKey _registerLCSClusterNodeMethodKey = new MethodKey(
		LCSClusterNodeUtil.class.getName(), "_registerLCSClusterNode",
		String.class);

}