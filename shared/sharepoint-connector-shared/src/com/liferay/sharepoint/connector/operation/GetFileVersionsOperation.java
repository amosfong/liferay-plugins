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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.SharepointVersion;
import com.liferay.sharepoint.connector.schema.XMLHelper;

import com.microsoft.schemas.sharepoint.soap.GetVersionsResponseGetVersionsResult;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import com.microsoft.schemas.sharepoint.soap.VersionsSoap;

import java.net.URL;

import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Ivan Zaera
 */
public class GetFileVersionsOperation extends BaseOperation {

	public GetFileVersionsOperation(
		VersionsSoap versionsSoap, ListsSoap listsSoap) {

		_versionsSoap = versionsSoap;

		_getObjectByPathOperation = new GetObjectByPathOperation(listsSoap);
	}

	public List<SharepointVersion> execute(String filePath)
		throws SharepointException {

		try {
			SharepointObject sharepointObject =
				_getObjectByPathOperation.execute(filePath);

			if (sharepointObject == null) {
				throw new SharepointException(
					"Sharepoint object with path '" + filePath + "' not found");
			}

			long sharepointObjectId = sharepointObject.getId();

			String fileFullPath = toFullPath(filePath);

			GetVersionsResponseGetVersionsResult
				getVersionsResponseGetVersionsResult =
					_versionsSoap.getVersions(fileFullPath);

			Element getVersionsResponseGetVersionsResultElement =
				_xmlHelper.getElement(getVersionsResponseGetVersionsResult);

			return _parseGetVersionsResponseGetVersionsResultElement(
				sharepointObjectId,
				getVersionsResponseGetVersionsResultElement);
		}
		catch (RemoteException re) {
			throw new SharepointException(
				"Unable to communicate with the Sharepoint server", re);
		}
	}

	private String _normalizeIfLatestVersion(String version) {
		if (version.startsWith(StringPool.AT)) {
			version = version.substring(1);
		}

		return version;
	}

	private List<SharepointVersion>
		_parseGetVersionsResponseGetVersionsResultElement(
			long sharepointObjectId,
			Element getVersionsResponseGetVersionsResultElement)
		throws SharepointException {

		List<SharepointVersion> sharepointVersions =
			new ArrayList<SharepointVersion>();

		NodeList nodeList =
			getVersionsResponseGetVersionsResultElement.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			String nodeLocalName = node.getLocalName();

			if ((nodeLocalName != null) &&
				StringUtil.equalsIgnoreCase(nodeLocalName, "result")) {

				NamedNodeMap nodeAttributes = node.getAttributes();

				Node urlNode = nodeAttributes.getNamedItem("url");

				String urlNodeValue = urlNode.getNodeValue();

				Node versionNode = nodeAttributes.getNamedItem("version");

				String versionNodeValue = versionNode.getNodeValue();

				Node commentsNode = nodeAttributes.getNamedItem("comments");

				String commentsNodeValue = commentsNode.getNodeValue();

				Node sizeNode = nodeAttributes.getNamedItem("size");

				String sizeNodeValue = sizeNode.getNodeValue();

				Node createdRawNode = nodeAttributes.getNamedItem("createdRaw");

				String createdRawNodeValue = createdRawNode.getNodeValue();

				Node createdByNode = nodeAttributes.getNamedItem("createdBy");

				String createdByNodeValue = createdByNode.getNodeValue();

				String comments = commentsNodeValue;

				String createdBy = createdByNodeValue;

				Date createdDate = _toDate(createdRawNodeValue);

				String sharepointVersionId = _toVersionId(
					sharepointObjectId, versionNodeValue);

				long size = Long.valueOf(sizeNodeValue);

				URL url = _urlHelper.toURL(urlNodeValue);

				String version = _normalizeIfLatestVersion(versionNodeValue);

				SharepointVersion sharepointVersion = new SharepointVersion(
					comments, createdBy, createdDate, sharepointVersionId, size,
					url, version );

				sharepointVersions.add(sharepointVersion);
			}
		}

		Collections.sort(sharepointVersions, _sharepointVersionComparator);

		return sharepointVersions;
	}

	private Date _toDate(String createdRawNodeValue) {
		return DatatypeConverter.parseDateTime(createdRawNodeValue).getTime();
	}

	private String _toVersionId(long sharepointObjectId, String version) {
		return sharepointObjectId + StringPool.AT + version;
	}

	private static final Comparator<SharepointVersion>
		_sharepointVersionComparator = new SharepointVersionComparator();
	private static final URLHelper _urlHelper = new URLHelper();
	private static final XMLHelper _xmlHelper = new XMLHelper();

	private GetObjectByPathOperation _getObjectByPathOperation;
	private VersionsSoap _versionsSoap;

}