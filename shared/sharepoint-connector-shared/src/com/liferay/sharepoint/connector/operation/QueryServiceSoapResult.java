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

import com.liferay.sharepoint.connector.SharepointException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author Iván Zaera
 */
public class QueryServiceSoapResult {

	public QueryServiceSoapResult(String queryServiceSoapResultString)
		throws SharepointException {

		try {
			XMLReader xmlReader = XMLReaderFactory.createXMLReader();

			xmlReader.setContentHandler(_defaultHandler);

			xmlReader.setErrorHandler(_defaultHandler);

			parse(xmlReader, queryServiceSoapResultString);
		}
		catch (SAXException saxe) {
			throw new SharepointException(
				"Unable to parse response from the Sharepoint server", saxe);
		}
		catch (IOException ioe) {
			throw new SharepointException(
				"Unable to parse response from the Sharepoint server", ioe);
		}
	}

	public String getDebugErrorMessage() {
		return _debugErrorMessage;
	}

	public List<String> getLinkUrls() {
		return _linkUrls;
	}

	public String getStatus() {
		return _status;
	}

	public boolean isEmpty() {
		if (isSuccess() &&
			_status.equals(SharepointConstants.ERROR_NO_RESULTS_FOUND)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean isSuccess() {
		if (!_status.equals(SharepointConstants.SUCCESS) &&
			!_status.equals(SharepointConstants.ERROR_NO_RESULTS_FOUND)) {

			return true;
		}

		return false;
	}

	protected void parse(XMLReader reader, String queryServiceSoapResultString)
		throws IOException, SAXException {

		byte[] bytes = queryServiceSoapResultString.getBytes("UTF-8");

		reader.parse(new InputSource(new ByteArrayInputStream(bytes)));
	}

	private String _debugErrorMessage;

	private DefaultHandler _defaultHandler = new DefaultHandler() {

		@Override
		public void characters(char[] ch, int start, int length) {
			_nodeContent.append(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName) {
			if (localName.equals("Status")) {
				_status = _nodeContent.toString();
			}
			else if (localName.equals("DebugErrorMessage")) {
				_debugErrorMessage = _nodeContent.toString();
			}
			else if (localName.equals("LinkUrl")) {
				_linkUrls.add(_nodeContent.toString());
			}
		}

		@Override
		public void startElement(
			String uri, String localName, String qName, Attributes attributes) {

			_nodeContent.setLength(0);
		}

		private StringBuilder _nodeContent = new StringBuilder();

	};

	private final List<String> _linkUrls = new ArrayList<String>();
	private String _status;

}