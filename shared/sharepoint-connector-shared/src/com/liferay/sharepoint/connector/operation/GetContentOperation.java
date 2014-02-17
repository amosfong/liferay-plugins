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

package com.liferay.sharepoint.connector.operation;

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.SharepointVersion;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

/**
 * @author Ivan Zaera
 */
public class GetContentOperation extends BaseOperation {

	public GetContentOperation(String username, String password) {
		_username = username;
		_password = password;
	}

	public InputStream execute(SharepointObject sharepointObject)
		throws SharepointException {

		URL sharepointObjectURL = sharepointObject.getURL();

		return _execute(sharepointObjectURL);
	}

	public InputStream execute(SharepointVersion sharepointVersion)
		throws SharepointException {

		URL sharepointVersionURL = sharepointVersion.getURL();

		return _execute(sharepointVersionURL);
	}

	private void _authenticate(HttpClient httpClient, URL url) {
		HttpClientParams httpClientParams = httpClient.getParams();

		httpClientParams.setAuthenticationPreemptive(true);

		AuthScope authScope = new AuthScope(
			url.getHost(), url.getPort(), url.getHost(), "BASIC");

		UsernamePasswordCredentials usernamePasswordCredentials =
			new UsernamePasswordCredentials(_username, _password);

		HttpState httpClientState = httpClient.getState();

		httpClientState.setCredentials(authScope, usernamePasswordCredentials);
	}

	private InputStream _execute(URL sharepointURL) throws SharepointException {
		sharepointURL = _urlHelper.escapeSharepointURL(sharepointURL);

		HttpClient httpClient = new HttpClient();

		_authenticate(httpClient, sharepointURL);

		GetMethod getMethod = new GetMethod(sharepointURL.toString());

		getMethod.setDoAuthentication(true);

		try {
			int httpStatus = httpClient.executeMethod(getMethod);

			if (httpStatus == HttpStatus.SC_OK) {
				InputStream inputStream = getMethod.getResponseBodyAsStream();

				byte[] bytes = null;

				try {
					bytes = FileUtil.getBytes(inputStream);
				}
				catch (IOException ioe) {
					throw new SharepointException(
						"Unable to read input stream", ioe);
				}

				return new ByteArrayInputStream(bytes);
			}
			else {
				throw new SharepointException(
					"Unable to download " + sharepointURL + " (HTTP " +
						httpStatus + ")");
			}
		}
		catch (IOException ioe) {
			throw new SharepointException(
				"Unable to communicate with the Sharepoint server", ioe);
		}
		finally {
			getMethod.releaseConnection();
		}
	}

	private static final URLHelper _urlHelper = new URLHelper();

	private String _password;
	private String _username;

}