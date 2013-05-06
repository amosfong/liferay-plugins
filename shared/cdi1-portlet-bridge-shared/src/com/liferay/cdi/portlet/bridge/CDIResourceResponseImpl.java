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

package com.liferay.cdi.portlet.bridge;

import java.io.IOException;

import java.util.Collection;

import javax.portlet.ResourceResponse;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;

/**
 * @author Neil Griffin
 */
public class CDIResourceResponseImpl extends CDIResourceResponse {

	public CDIResourceResponseImpl(
		ResourceResponse resourceResponse,
		HttpServletResponseAdapter httpServletResponseAdapter) {

		super(resourceResponse);

		_httpServletResponseAdapter = httpServletResponseAdapter;
	}

	public void addCookie(Cookie cookie) {
		_httpServletResponseAdapter.addCookie(cookie);
	}

	public void addDateHeader(String name, long value) {
		_httpServletResponseAdapter.addDateHeader(name, value);
	}

	public void addHeader(String name, String value) {
		_httpServletResponseAdapter.addHeader(name, value);
	}

	public void addIntHeader(String name, int value) {
		_httpServletResponseAdapter.addIntHeader(name, value);
	}

	public boolean containsHeader(String name) {
		return _httpServletResponseAdapter.containsHeader(name);
	}

	@SuppressWarnings("deprecation")
	public String encodeRedirectUrl(String url) {
		return _httpServletResponseAdapter.encodeRedirectUrl(url);
	}

	public String encodeRedirectURL(String url) {
		return _httpServletResponseAdapter.encodeRedirectURL(url);
	}

	@SuppressWarnings("deprecation")
	public String encodeUrl(String url) {
		return _httpServletResponseAdapter.encodeUrl(url);
	}

	public String getHeader(String name) {
		return _httpServletResponseAdapter.getHeader(name);
	}

	public Collection<String> getHeaderNames() {
		return _httpServletResponseAdapter.getHeaderNames();
	}

	public Collection<String> getHeaders(String name) {
		return _httpServletResponseAdapter.getHeaders(name);
	}

	public ServletOutputStream getOutputStream() throws IOException {
		return _httpServletResponseAdapter.getOutputStream();
	}

	public int getStatus() {
		return _httpServletResponseAdapter.getStatus();
	}

	public void sendError(int status) throws IOException {
		_httpServletResponseAdapter.sendError(status);
	}

	public void sendError(int status, String msg) throws IOException {
		_httpServletResponseAdapter.sendError(status, msg);
	}

	public void sendRedirect(String location) throws IOException {
		_httpServletResponseAdapter.sendRedirect(location);
	}

	public void setDateHeader(String name, long value) {
		_httpServletResponseAdapter.setDateHeader(name, value);
	}

	public void setHeader(String name, String value) {
		_httpServletResponseAdapter.setHeader(name, value);
	}

	public void setIntHeader(String name, int value) {
		_httpServletResponseAdapter.setIntHeader(name, value);
	}

	public void setStatus(int status) {
		_httpServletResponseAdapter.setStatus(status);
	}

	@SuppressWarnings("deprecation")
	public void setStatus(int status, String msg) {
		_httpServletResponseAdapter.setStatus(status, msg);
	}

	private HttpServletResponseAdapter _httpServletResponseAdapter;

}