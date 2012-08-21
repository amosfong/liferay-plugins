/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.saml.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.InputStream;

import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.parse.ParserPool;

import org.w3c.dom.Document;

/**
 * @author Mika Koivisto
 */
public class MetadataUtil {

	public static InputStream fetchMetadata(String url) {
		GetMethod getMethod = new GetMethod(url);

		try {
			_httpClient.executeMethod(getMethod);

			if (getMethod.getStatusCode() != HttpStatus.SC_OK) {
				return null;
			}

			InputStream ins = getMethod.getResponseBodyAsStream();

			Header httpHeader = getMethod.getResponseHeader("Content-Encoding");

			if (httpHeader != null) {
				String contentEncoding = httpHeader.getValue();

				if (contentEncoding.equalsIgnoreCase("deflate")) {
					ins = new InflaterInputStream(ins);
				}

				if (contentEncoding.equalsIgnoreCase("gzip")) {
					ins = new GZIPInputStream(ins);
				}
			}

			return ins;
		}
		catch (Exception e) {
			_log.warn("Unable to fetch metadata from " + url, e);
		}

		return null;
	}

	public static String parseMetadataXml(InputStream is, String entityId)
			throws Exception {

		try {
			Document document = _parserPool.parse(is);

			XMLObject metadataXmlObject = OpenSamlUtil.unmarshallXmlObject(
				document.getDocumentElement());

			EntityDescriptor entityDescriptor =
				SamlUtil.getEntityDescriptorById(entityId, metadataXmlObject);

			if (entityDescriptor == null) {
				return null;
			}

			String metadataXml = OpenSamlUtil.marshallElement(
				entityDescriptor.getDOM());

			return metadataXml;
		}
		finally {
			if (is != null) {
				StreamUtil.cleanUp(is);
			}
		}
	}

	public void setHttpClient(HttpClient httpClient) {
		_httpClient = httpClient;
	}

	public void setParserPool(ParserPool parserPool) {
		_parserPool = parserPool;
	}

	private static final Log _log = LogFactoryUtil.getLog(MetadataUtil.class);

	private static HttpClient _httpClient;
	private static ParserPool _parserPool;

}