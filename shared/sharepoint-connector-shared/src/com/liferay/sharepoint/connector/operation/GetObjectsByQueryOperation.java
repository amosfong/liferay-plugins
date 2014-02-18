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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.SharepointObject.Permission;
import com.liferay.sharepoint.connector.schema.query.Query;
import com.liferay.sharepoint.connector.schema.query.QueryField;
import com.liferay.sharepoint.connector.schema.query.QueryFieldsList;
import com.liferay.sharepoint.connector.schema.query.QueryOptionsList;

import com.microsoft.schemas.sharepoint.soap.GetListItemsQuery;
import com.microsoft.schemas.sharepoint.soap.GetListItemsQueryOptions;
import com.microsoft.schemas.sharepoint.soap.GetListItemsResponseGetListItemsResult;
import com.microsoft.schemas.sharepoint.soap.GetListItemsViewFields;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;

import java.rmi.RemoteException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.apache.axis.message.MessageElement;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Ivan Zaera
 */
public class GetObjectsByQueryOperation extends BaseOperation {

	public GetObjectsByQueryOperation(
		ListsSoap listsSoap, String libraryName, String sitePath) {

		_listsSoap = listsSoap;
		_libraryName = libraryName;
		_sitePath = sitePath;

		_pathPrefixToRemoveLength = _sitePath.length() + _libraryName.length();
	}

	public List<SharepointObject> execute(
			Query query, QueryOptionsList queryOptionsList,
			String... queryFieldNames)
		throws SharepointException {

		GetListItemsQuery getListItemsQuery = _getGetListItemsQuery(query);

		GetListItemsViewFields getListItemsViewFields =
			_getGetListItemsViewFields(queryFieldNames);

		GetListItemsQueryOptions getListItemsQueryOptions =
			_getGetListItemsQueryOptions(queryOptionsList);

		GetListItemsResponseGetListItemsResult
			getListItemsResponseGetListItemsResult = null;

		try {
			getListItemsResponseGetListItemsResult = _listsSoap.getListItems(
				_libraryName, SharepointConstants.VIEW_DEFAULT,
				getListItemsQuery, getListItemsViewFields,
				SharepointConstants.ROW_LIMIT_DEFAULT, getListItemsQueryOptions,
				SharepointConstants.WEB_ID_DEFAULT);
		}
		catch (RemoteException re) {
			throw new SharepointException(
				"Unable to communicate with the Sharepoint server", re);
		}

		if (_log.isDebugEnabled()) {
			_logDebug(
				query, queryOptionsList,
				getListItemsResponseGetListItemsResult);
		}

		return _getSharepointObjects(getListItemsResponseGetListItemsResult);
	}

	private Date _getDate(String sharepointObjectDate) {
		try {
			DateFormat format = new SimpleDateFormat(
				SharepointConstants.SHAREPOINT_OBJECT_DATE_FORMAT);

			format.setTimeZone(SharepointConstants.SHAREPOINT_OBJECT_TIME_ZONE);

			return format.parse(sharepointObjectDate);
		}
		catch (ParseException pe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to parse Sharepoint object date '" +
					sharepointObjectDate + "'", pe);
			}

			return new Date(0);
		}
	}

	private GetListItemsQuery _getGetListItemsQuery(Query query) {
		GetListItemsQuery getListItemsQuery = new GetListItemsQuery();

		Element queryElement = xmlHelper.toElement(query);

		MessageElement queryMessageElement = new MessageElement(queryElement);

		getListItemsQuery.set_any(new MessageElement[] {queryMessageElement});

		return getListItemsQuery;
	}

	private GetListItemsQueryOptions _getGetListItemsQueryOptions(
		QueryOptionsList queryOptionsList) {

		Element queryOptionsListElement = xmlHelper.toElement(queryOptionsList);

		MessageElement queryOptionsListMessageElement = new MessageElement(
			queryOptionsListElement);

		GetListItemsQueryOptions getListItemsQueryOptions =
				new GetListItemsQueryOptions();

		getListItemsQueryOptions.set_any(
			new MessageElement[] {queryOptionsListMessageElement});

		return getListItemsQueryOptions;
	}

	private GetListItemsViewFields _getGetListItemsViewFields(
		String... queryFieldNames) {

		QueryFieldsList queryFieldsList = new QueryFieldsList(
			_toQueryFields(queryFieldNames));

		Element queryFieldsListElement = xmlHelper.toElement(queryFieldsList);

		MessageElement queryFieldsListMessageElement = new MessageElement(
			queryFieldsListElement);

		GetListItemsViewFields getListItemsViewFields =
				new GetListItemsViewFields();

		getListItemsViewFields.set_any(
			new MessageElement[] {queryFieldsListMessageElement});

		return getListItemsViewFields;
	}

	private String _getNodeValue(Node node, int index) {
		String nodeValue = node.getNodeValue();

		String[] parts = nodeValue.split(
			SharepointConstants.PATTERN_MULTI_VALUE_SEPARATOR);

		if (index < parts.length) {
			return parts[index];
		}
		else {
			return null;
		}
	}

	private Set<Permission> _getPermissions(String permissionsHexMask) {
		long permisssionsMask = Long.valueOf(
			permissionsHexMask.substring(2), 16);

		Set<Permission> permissions = EnumSet.noneOf(Permission.class);

		for (Permission permission : Permission.values()) {
			long permissionBit = (permisssionsMask & permission.getMask());

			if (permissionBit != 0) {
				permissions.add(permission);
			}
		}

		return permissions;
	}

	private List<SharepointObject> _getSharepointObjects(
			GetListItemsResponseGetListItemsResult
				getListItemsResponseGetListItemsResult)
		throws SharepointException {

		List<SharepointObject> sharepointObjects =
			new ArrayList<SharepointObject>();

		Element getListItemsResponseGetListItemsResultElement =
			xmlHelper.getElement(getListItemsResponseGetListItemsResult);

		Element dataElement = xmlHelper.getElement(
			"Data", getListItemsResponseGetListItemsResultElement);

		NodeList nodeList = dataElement.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			String localName = node.getLocalName();

			if ((localName == null) ||
				!StringUtil.equalsIgnoreCase(localName, "result")) {

				continue;
			}

			NamedNodeMap namedNodeMap = node.getAttributes();

			Node ows_FileRefNode = namedNodeMap.getNamedItem("ows_FileRef");
			Node ows_AuthorNode = namedNodeMap.getNamedItem("ows_Author");
			Node ows_File_x0020_SizeNode = namedNodeMap.getNamedItem(
				"ows_File_x0020_Size");
			Node ows_Created_x0020_DateNode = namedNodeMap.getNamedItem(
				"ows_Created_x0020_Date");
			Node ows_Last_x0020_ModifiedNode = namedNodeMap.getNamedItem(
				"ows_Last_x0020_Modified");
			Node ows_CheckedOutUserIdNode = namedNodeMap.getNamedItem(
				"ows_CheckedOutUserId");
			Node ows_PermMaskNode = namedNodeMap.getNamedItem("ows_PermMask");
			Node ows_FSObjTypeNode = namedNodeMap.getNamedItem("ows_FSObjType");

			String path = _getNodeValue(ows_FileRefNode, 1).substring(
				_pathPrefixToRemoveLength);

			SharepointObject sharepointObject = new SharepointObject(
				_getNodeValue(ows_AuthorNode, 1),
				_getNodeValue(ows_CheckedOutUserIdNode, 1),
				_getDate(_getNodeValue(ows_Created_x0020_DateNode, 1)),
				_getNodeValue(ows_FSObjTypeNode, 1).equals(
					SharepointConstants.FS_OBJ_TYPE_FOLDER),
				_getDate(_getNodeValue(ows_Last_x0020_ModifiedNode, 1)), path,
				_getPermissions(ows_PermMaskNode.getNodeValue()),
				GetterUtil.getLong(_getNodeValue(ows_FileRefNode, 0)),
				GetterUtil.getLong(_getNodeValue(ows_File_x0020_SizeNode, 1)),
				urlHelper.toURL(path));

			sharepointObjects.add(sharepointObject);
		}

		return sharepointObjects;
	}

	private void _logDebug(
		Query query, QueryOptionsList queryOptionsList,
		GetListItemsResponseGetListItemsResult result) {

		Element resultElement = xmlHelper.getElement(result);

		String resultElementString = xmlHelper.toString(resultElement);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Query: " + query + "\nQueryOptions: " + queryOptionsList +
				"\nResult: " + resultElementString);
		}
	}

	private QueryField[] _toQueryFields(String[] queryFieldNames) {
		QueryField[] queryFields = new QueryField[queryFieldNames.length];

		int i = 0;

		for (String queryFieldName : queryFieldNames) {
			queryFields[i++] = new QueryField(queryFieldName);
		}

		return queryFields;
	}

	private static Log _log = LogFactoryUtil.getLog(
		GetObjectsByQueryOperation.class);

	private String _libraryName;
	private ListsSoap _listsSoap;
	private int _pathPrefixToRemoveLength;
	private String _sitePath;

}