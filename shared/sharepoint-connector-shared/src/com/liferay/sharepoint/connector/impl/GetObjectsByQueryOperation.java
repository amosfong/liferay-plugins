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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.SharepointObject.Permission;
import com.liferay.sharepoint.connector.exception.SharepointException;
import com.liferay.sharepoint.connector.schema.query.Query;
import com.liferay.sharepoint.connector.schema.query.QueryField;
import com.liferay.sharepoint.connector.schema.query.QueryFieldsList;
import com.liferay.sharepoint.connector.schema.query.QueryOptionsList;

import com.microsoft.schemas.sharepoint.soap.GetListItemsQuery;
import com.microsoft.schemas.sharepoint.soap.GetListItemsQueryOptions;
import com.microsoft.schemas.sharepoint.soap.GetListItemsResponseGetListItemsResult;
import com.microsoft.schemas.sharepoint.soap.GetListItemsViewFields;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;

import java.net.URL;

import java.rmi.RemoteException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.axis.message.MessageElement;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Ivan Zaera
 */
public class GetObjectsByQueryOperation {

	public GetObjectsByQueryOperation(
		SharepointConnectionImpl sharepointConnectionImpl) {

		_sharepointConnectionImpl = sharepointConnectionImpl;

		_listsSoap = sharepointConnectionImpl.getListsSoap();

		_libraryName = sharepointConnectionImpl.getLibraryName();
		_sitePath = sharepointConnectionImpl.getSitePath();

		_pathPrefixToRemoveLength = _sitePath.length() + _libraryName.length();
	}

	public List<SharepointObject> execute(
			Query query, QueryOptionsList queryOptionsList,
			String... queryFieldNames)
		throws SharepointException {

		String listName = _libraryName;

		String viewName = StringPool.BLANK;

		GetListItemsQuery queryContainer = new GetListItemsQuery();

		Element queryElement = _XML_HELPER.toElement(query);

		queryContainer.set_any(new MessageElement[] {
			new MessageElement(queryElement)
		});

		GetListItemsViewFields viewFieldsContainer =
			new GetListItemsViewFields();

		QueryField[] queryFields = _toQueryFields(queryFieldNames);

		QueryFieldsList queryFieldsList = new QueryFieldsList(queryFields);

		Element queryFieldsListElement = _XML_HELPER.toElement(queryFieldsList);

		viewFieldsContainer.set_any(new MessageElement[] {
			new MessageElement(queryFieldsListElement)
		});

		String rowLimit = StringPool.BLANK;

		GetListItemsQueryOptions queryOptionsContainer =
			new GetListItemsQueryOptions();

		Element queryOptionsListElement = _XML_HELPER.toElement(
			queryOptionsList);

		queryOptionsContainer.set_any(new MessageElement[] {
			new MessageElement(queryOptionsListElement)
		});

		String webId = StringPool.BLANK;

		GetListItemsResponseGetListItemsResult result = null;

		try {
			result = _listsSoap.getListItems(
				listName, viewName, queryContainer, viewFieldsContainer,
				rowLimit, queryOptionsContainer, webId);
		}
		catch (RemoteException re) {
			throw new SharepointException(
				"Failure communicating with Sharepoint server", re);
		}

		_logQueryAndResultIfEnabled(query, queryOptionsList, result);

		return _parseGetListItemsResult(result);
	}

	private String _getAttributeValue(NamedNodeMap attributes, String name) {
		Node node = attributes.getNamedItem(name);

		return node.getNodeValue();
	}

	private String _getMultiValuedFieldValue(
		String multiValuedFieldValue, int index) {

		String[] multiValuedFieldValues = multiValuedFieldValue.split(
			_SHAREPOINT_MULTI_VALUE_SEPARATOR_PATTERN);

		if (index < multiValuedFieldValues.length) {
			return multiValuedFieldValues[index];
		}
		else {
			return null;
		}
	}

	private void _logQueryAndResultIfEnabled(
		Query query, QueryOptionsList queryOptionsList,
		GetListItemsResponseGetListItemsResult result) {

		if (_log.isDebugEnabled()) {
			Element resultElement = _XML_HELPER.getElement(result);

			String resultElementString = _XML_HELPER.toString(resultElement);

			_log.debug(
				"Query: " + query + "; QueryOptionsList: " + queryOptionsList +
				"; Result: " +resultElementString);
		}
	}

	private List<SharepointObject> _parseGetListItemsResult(
			GetListItemsResponseGetListItemsResult getListItemsResult)
		throws SharepointException {

		Element getListItemsResultElement = _XML_HELPER.getElement(
			getListItemsResult);

		Node data = _XML_HELPER.getChild("Data", getListItemsResultElement);

		return _parseRows(_sharepointConnectionImpl, data.getChildNodes());
	}

	private List<SharepointObject> _parseRows(
			SharepointConnectionImpl sharepointConnectionImpl, NodeList rows) {

		List<SharepointObject> sharepointObjects =
			new ArrayList<SharepointObject>();

		for (int i = 0; i < rows.getLength(); i++) {
			Node row = rows.item(i);

			String localName = row.getLocalName();

			if ((localName != null) && localName.equals("row")) {
				NamedNodeMap attributes = row.getAttributes();

				String ows_FileRef = _getAttributeValue(
					attributes, "ows_FileRef");
				String ows_Author = _getAttributeValue(
					attributes, "ows_Author");
				String ows_File_x0020_Size = _getAttributeValue(
					attributes, "ows_File_x0020_Size");
				String ows_Created_x0020_Date = _getAttributeValue(
					attributes, "ows_Created_x0020_Date");
				String ows_Last_x0020_Modified = _getAttributeValue(
					attributes, "ows_Last_x0020_Modified");
				String ows_CheckedOutUserId = _getAttributeValue(
					attributes, "ows_CheckedOutUserId");
				String ows_PermMask = _getAttributeValue(
					attributes, "ows_PermMask");
				String ows_FSObjType = _getAttributeValue(
					attributes, "ows_FSObjType");

				long id = GetterUtil.getLong(
					_getMultiValuedFieldValue(ows_FileRef, 0));

				String path = _getMultiValuedFieldValue(ows_FileRef, 1);

				path = path.substring(_pathPrefixToRemoveLength);

				URL url = sharepointConnectionImpl.getObjectURL(path);

				long size = GetterUtil.getLong(
					_getMultiValuedFieldValue(ows_File_x0020_Size, 1));

				Date createdDate = _toDate(
					_getMultiValuedFieldValue(ows_Created_x0020_Date, 1));

				Date lastModifiedDate = _toDate(
					_getMultiValuedFieldValue(ows_Last_x0020_Modified, 1));

				String author = _getMultiValuedFieldValue(ows_Author, 1);

				String checkedOutBy = _getMultiValuedFieldValue(
					ows_CheckedOutUserId, 1);

				boolean isFolder = _getMultiValuedFieldValue(
					ows_FSObjType, 1).equals("1");

				Set<Permission> permissions = _toPermissions(ows_PermMask);

				SharepointObject sharepointObject = new SharepointObject(
					id, url, path, isFolder, size, createdDate,
					lastModifiedDate, author, checkedOutBy, permissions);

				sharepointObjects.add(sharepointObject);
			}
		}

		return sharepointObjects;
	}

	private Date _toDate(String dateString) {
		DateFormat format = new SimpleDateFormat(_SHAREPOINT_DATE_FORMAT);

		format.setTimeZone(_UTC_TIME_ZONE);

		try {
			return format.parse(dateString);
		}
		catch (ParseException pe) {
		}

		return new Date(0);
	}

	private Set<Permission> _toPermissions(String hexPermissionsMask) {
		long mask = Long.valueOf(hexPermissionsMask.substring(2), 16);

		Set<Permission> permissions = EnumSet.noneOf(Permission.class);

		for (Permission permission : Permission.values()) {
			long permissionBit = permission.getMask()&mask;

			if (permissionBit != 0) {
				permissions.add(permission);
			}
		}

		return permissions;
	}

	private QueryField[] _toQueryFields(String[] queryFieldNames) {
		QueryField[] queryFields = new QueryField[queryFieldNames.length];

		int i = 0;

		for (String queryFieldName : queryFieldNames) {
			queryFields[i++] = new QueryField(queryFieldName);
		}

		return queryFields;
	}

	private static final String _SHAREPOINT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final String _SHAREPOINT_MULTI_VALUE_SEPARATOR_PATTERN =
		Pattern.quote(";#");

	private static final TimeZone _UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");

	private static final XMLHelper _XML_HELPER = new XMLHelper();

	private static Log _log = LogFactoryUtil.getLog(
		GetObjectsByQueryOperation.class);

	private String _libraryName;
	private ListsSoap _listsSoap;
	private int _pathPrefixToRemoveLength;
	private SharepointConnectionImpl _sharepointConnectionImpl;
	private String _sitePath;

}