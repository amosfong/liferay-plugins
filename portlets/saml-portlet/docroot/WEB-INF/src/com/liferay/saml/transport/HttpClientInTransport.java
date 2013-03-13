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

package com.liferay.saml.transport;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.PostMethod;

import org.opensaml.ws.transport.http.HTTPInTransport;
import org.opensaml.xml.security.credential.Credential;

/**
 * @author Mika Koivisto
 */
public class HttpClientInTransport implements HTTPInTransport {

	public HttpClientInTransport(PostMethod postMethod, String location) {
		_postMethod = postMethod;
		_location = location;
	}

	public Object getAttribute(String name) {
		return null;
	}

	public String getCharacterEncoding() {
		return _postMethod.getResponseCharSet();
	}

	public String getHeaderValue(String name) {
		return null;
	}

	public String getHTTPMethod() {
		return _postMethod.getName();
	}

	public InputStream getIncomingStream() {
		try {
			return _postMethod.getResponseBodyAsStream();
		}
		catch (IOException ioe) {
			return null;
		}
	}

	public String getLocalAddress() {
		return _location;
	}

	public Credential getLocalCredential() {
		return null;
	}

	public String getParameterValue(String name) {
		return null;
	}

	public List<String> getParameterValues(String name) {
		return null;
	}

	public String getPeerAddress() {
		return null;
	}

	public Credential getPeerCredential() {
		return null;
	}

	public String getPeerDomainName() {
		return null;
	}

	public int getStatusCode() {
		return _postMethod.getStatusCode();
	}

	public HTTP_VERSION getVersion() {
		return null;
	}

	public boolean isAuthenticated() {
		return false;
	}

	public boolean isConfidential() {
		try {
			URI uri = _postMethod.getURI();

			String scheme = uri.getScheme();

			if (scheme.equals("https")) {
				return true;
			}
		}
		catch (URIException urie) {
		}

		return false;
	}

	public boolean isIntegrityProtected() {
		return false;
	}

	public void setAuthenticated(boolean authenticated) {
	}

	public void setConfidential(boolean confidential) {
	}

	public void setIntegrityProtected(boolean integrityProtected) {
	}

	private String _location;
	private PostMethod _postMethod;

}