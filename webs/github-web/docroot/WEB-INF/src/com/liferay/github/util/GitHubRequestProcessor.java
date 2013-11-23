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

import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 */
public class GitHubRequestProcessor {

	public static synchronized void process(HttpServletRequest request)
		throws Exception {

		String payload = request.getParameter("payload");

		JSONObject payloadJSONObject = new JSONObject(payload);

		JSONObject repositoryJSONObject = payloadJSONObject.getJSONObject(
			"repository");

		String repositoryName = repositoryJSONObject.getString("name");

		JSONObject ownerJSONObject = repositoryJSONObject.getJSONObject(
			"owner");

		String ownerName = ownerJSONObject.getString("name");

		System.out.println("Key: " + repositoryName + "/" + ownerName);

		System.out.println("Payload: " + payload);
	}

}