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

package com.liferay.github.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 */
public class GitHubRequestProcessor {

	public static synchronized void process(HttpServletRequest request)
		throws Exception {

		String payload = request.getParameter("payload");

		if (_log.isDebugEnabled()) {
			_log.debug("Payload: " + payload);
		}

		JSONObject payloadJSONObject = new JSONObject(payload);

		JSONObject repositoryJSONObject = payloadJSONObject.getJSONObject(
			"repository");

		JSONObject ownerJSONObject = repositoryJSONObject.getJSONObject(
			"owner");

		String ownerName = ownerJSONObject.getString("name");

		String repositoryName = repositoryJSONObject.getString("name");

		_callRedeploy(ownerName, repositoryName);
	}

	private static void _callRedeploy(String ownerName, String repositoryName)
		throws Exception {

		String[] hostnames = _getHostnames(ownerName, repositoryName);

		for (String hostname : hostnames) {
			HttpClient httpClient = new HttpClient();

			HttpMethod httpMethod = new GetMethod(
				"http://" + hostname + ":1220/protected/redeploy.php");

			try {
				httpClient.executeMethod(httpMethod);
			}
			finally {
				httpMethod.releaseConnection();
			}
		}
	}

	private static String[] _getHostnames(
		String ownerName, String repositoryName) {

		return new String[] {"lrdcom-vm-16"};
	}

	private static Log _log = LogFactory.getLog(GitHubRequestProcessor.class);

}