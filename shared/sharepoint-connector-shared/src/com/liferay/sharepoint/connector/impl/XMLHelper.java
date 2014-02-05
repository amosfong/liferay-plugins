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

package com.liferay.sharepoint.connector.impl;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.sharepoint.connector.exception.SharepointRuntimeException;
import com.liferay.sharepoint.connector.schema.Node;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.axis.encoding.AnyContentType;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Ivan Zaera
 */
public class XMLHelper {

	public Element getChild(String tagName, org.w3c.dom.Node parent) {
		for (org.w3c.dom.Node child = parent.getFirstChild();
				child != null;
					child = child.getNextSibling()) {

			String localName = child.getLocalName();

			if ((localName != null) &&
				StringUtil.equalsIgnoreCase(localName, tagName)) {

				return (Element)child;
			}
		}

		return null;
	}

	public Element getElement(AnyContentType container) {
		try {
			return container.get_any()[0].getAsDOM();
		}
		catch (Exception e) {
			throw new SharepointRuntimeException(
				"Cannot parse response from Sharepoint server", e);
		}
	}

	public Element toElement(Node node) {
		return _parseXML(node.toXmlString());
	}

	public String toString(Element element) {
		TransformerFactory transformerFactory =
			TransformerFactory.newInstance();

		Transformer transformer = null;

		try {
			transformer = transformerFactory.newTransformer();
		}
		catch (TransformerConfigurationException tce) {
			throw new RuntimeException(tce);
		}

		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

		StringWriter writer = new StringWriter();

		try {
			transformer.transform(
				new DOMSource(element), new StreamResult(writer));
		}
		catch (TransformerException te) {
			throw new RuntimeException(te);
		}

		return writer.getBuffer().toString();
	}

	private Element _parseXML(String xml) {
		try {
			DocumentBuilderFactory documentBuilderFactory =
				DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder =
				documentBuilderFactory.newDocumentBuilder();

			StringReader stringReader = new StringReader(xml);

			InputSource inputSource = new InputSource(stringReader);

			Document document = documentBuilder.parse(inputSource);

			return document.getDocumentElement();
		}
		catch (SAXException saxe) {
			throw new RuntimeException(saxe);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		catch (ParserConfigurationException pce) {
			throw new RuntimeException(pce);
		}
	}

}