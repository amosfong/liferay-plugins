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

import java.net.ConnectException;

import java.util.Properties;

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

		if (hostnames.length == 0) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"There are no hostnames associated with " + ownerName +
						" and " + repositoryName);
			}
		}

		for (String hostname : hostnames) {
			HttpClient httpClient = new HttpClient();

			String url = "http://" + hostname + ":1220/protected/redeploy.php";

			if (_log.isInfoEnabled()) {
				_log.info("Invoke URL " + url);
			}

			HttpMethod httpMethod = new GetMethod(url);

			try {
				int responseCode = httpClient.executeMethod(httpMethod);

				if (_log.isInfoEnabled()) {
					_log.info("Response code " + responseCode);
				}
			}
			catch (ConnectException ce) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to connect to " + hostname);
				}

				continue;
			}
			finally {
				httpMethod.releaseConnection();
			}
		}
	}

	private static String[] _getHostnames(
			String ownerName, String repositoryName)
		throws Exception {

		if (_peekProperties == null) {
			Properties peekProperties = new Properties();

			peekProperties.load(
				GitHubRequestProcessor.class.getResourceAsStream(
					"/peek.properties"));

			_peekProperties = peekProperties;
		}

		String hostnames = _peekProperties.getProperty(
			ownerName + "." + repositoryName);

		if (hostnames == null) {
			return new String[0];
		}

		String[] hostnamesArray = hostnames.split(",");

		for (int i = 0; i < hostnamesArray.length; i++) {
			String hostname = hostnamesArray[i];

			if (hostname.equals("lrdcom-vm-16")) {
				hostnamesArray[i] = "172.16.168.126";
			}
		}

		return hostnamesArray;
	}

	private static Log _log = LogFactory.getLog(GitHubRequestProcessor.class);

	private static Properties _peekProperties;

}