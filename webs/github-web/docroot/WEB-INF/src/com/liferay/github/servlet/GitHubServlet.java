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

package com.liferay.github.servlet;

import com.liferay.github.util.GitHubRequestProcessor;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class GitHubServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String remoteAddr = request.getRemoteAddr();

		if (_log.isInfoEnabled()) {
			_log.info("Remote address: " + remoteAddr);
		}

		if (!isValidIP(remoteAddr)) {
			sendError(response, "IP " + remoteAddr + " is invalid.");

			return;
		}

		try {
			GitHubRequestProcessor.process(request);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void init(ServletConfig servletConfig) {
		String validIpsString = servletConfig.getInitParameter("validIps");

		_validIps = validIpsString.split(",");
	}

	protected boolean isValidIP(String remoteAddr) {
		for (String validIp : _validIps) {
			if (remoteAddr.equals(validIp) ||
				remoteAddr.startsWith(validIp + ".")) {

				return true;
			}
		}

		return false;
	}

	protected void sendError(HttpServletResponse response, String message)
		throws IOException {

		write(response, new ByteArrayInputStream(message.getBytes()));
	}

	protected void write(HttpServletResponse response, InputStream inputStream)
		throws IOException {

		OutputStream outputStream = null;

		try {
			response.setHeader("Cache-Control", "public");

			if (!response.isCommitted()) {
				outputStream = new BufferedOutputStream(
					response.getOutputStream());

				int c = inputStream.read();

				while (c != -1) {
					outputStream.write(c);

					c = inputStream.read();
				}
			}
		}
		finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}

			try {
				if (outputStream != null) {
					outputStream.close();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}

			try {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e);
				}
			}
		}
	}

	private static Log _log = LogFactory.getLog(GitHubServlet.class);

	private String[] _validIps;

}