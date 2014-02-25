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

package com.liferay.sharepoint.connector;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.sharepoint.connector.operation.AddOrUpdateFileOperation;
import com.liferay.sharepoint.connector.operation.PathHelper;
import com.liferay.sharepoint.connector.schema.query.Query;
import com.liferay.sharepoint.connector.schema.query.QueryOptionsList;

import com.microsoft.schemas.sharepoint.soap.CopyLocator;
import com.microsoft.schemas.sharepoint.soap.CopySoap;
import com.microsoft.schemas.sharepoint.soap.ListsLocator;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;

import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;

/**
 * @author Iván Zaera
 */
public class SharepointConnectionImpl implements SharepointConnection {

	public static final long SHAREPOINT_ROOT_FOLDER_ID = -1;

	public SharepointConnectionImpl(
		String serverProtocol, String serverAddress, int serverPort,
		String sitePath, String libraryName, String username, String password) {

		validateConnectionConfiguration(sitePath, username, password);

		_serverProtocol = serverProtocol;
		_serverAddress = serverAddress;
		_serverPort = serverPort;
		_sitePath = sitePath;
		_libraryName = libraryName;
		_username = username;
		_password = password;

		_pathHelper = new PathHelper(libraryName, sitePath);

		initCopySoap();
		initListsSoap();

		initOperations();
	}

	@Override
	public void addFile(
			String folderPath, String fileName, String changeLog,
			InputStream inputStream)
		throws SharepointException {

		String filePath = _pathHelper.buildPath(folderPath, fileName);

		changeLog = GetterUtil.getString(changeLog);

		_addOrUpdateFileOperation.execute(filePath, changeLog, inputStream);
	}

	@Override
	public void addFolder(String folderPath, String folderName)
		throws SharepointException {
	}

	@Override
	public boolean cancelCheckOutFile(String filePath)
		throws SharepointException {

		return false;
	}

	@Override
	public boolean checkInFile(
			String filePath, String comment, CheckInType checkInType)
		throws SharepointException {

		return false;
	}

	@Override
	public boolean checkOutFile(String filePath) throws SharepointException {
		return false;
	}

	@Override
	public void copyObject(String path, String newPath)
		throws SharepointException {
	}

	@Override
	public void deleteObject(String path) throws SharepointException {
	}

	@Override
	public String getLibraryName() {
		return _libraryName;
	}

	@Override
	public SharepointObject getObject(long id) throws SharepointException {
		return null;
	}

	@Override
	public SharepointObject getObject(String path) throws SharepointException {
		return null;
	}

	@Override
	public InputStream getObjectContent(SharepointObject sharepointObject)
		throws SharepointException {

		return null;
	}

	@Override
	public List<SharepointObject> getObjects(
			Query query, QueryOptionsList queryOptionsList)
		throws SharepointException {

		return null;
	}

	@Override
	public List<SharepointObject> getObjects(String name)
		throws SharepointException {

		return null;
	}

	@Override
	public List<SharepointObject> getObjects(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException {

		return null;
	}

	@Override
	public int getObjectsCount(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException {

		return 0;
	}

	@Override
	public String getServerAddress() {
		return _serverAddress;
	}

	@Override
	public int getServerPort() {
		return _serverPort;
	}

	@Override
	public String getServerProtocol() {
		return _serverProtocol;
	}

	@Override
	public String getSitePath() {
		return _sitePath;
	}

	@Override
	public String getUsername() {
		return _username;
	}

	@Override
	public InputStream getVersionContent(SharepointVersion sharepointVersion)
		throws SharepointException {

		return null;
	}

	@Override
	public List<SharepointVersion> getVersions(String filePath)
		throws SharepointException {

		return null;
	}

	@Override
	public void moveObject(String path, String newPath)
		throws SharepointException {
	}

	@Override
	public void updateFile(String filePath, InputStream inputStream)
		throws SharepointException {
	}

	protected void configureStub(Stub stub, URL wsdlURL) {
		stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, wsdlURL.toString());
		stub._setProperty(Call.USERNAME_PROPERTY, _username);
		stub._setProperty(Call.PASSWORD_PROPERTY, _password);
	}

	protected URL getWSDLURL(String serviceName) {
		String name = "/wsdl/" + serviceName + ".wsdl";

		try {
			Class<?> clazz = getClass();

			URL wsdlResourceURL = clazz.getResource(name);

			return new URL(wsdlResourceURL.toExternalForm());
		}
		catch (MalformedURLException murle) {
			throw new SharepointRuntimeException(
				"Unable to load WSDL " + name, murle);
		}
	}

	protected void initCopySoap() {
		URL wsdlURL = getWSDLURL("copy");

		try {
			CopyLocator copyLocator = new CopyLocator();

			_copySoap = copyLocator.getCopySoap(wsdlURL);

			configureStub((Stub)_copySoap, wsdlURL);
		}
		catch (ServiceException se) {
			throw new SharepointRuntimeException(
				"Unable to configure SOAP endpoint " + wsdlURL, se);
		}
	}

	protected void initListsSoap() {
		URL wsdlURL = getWSDLURL("lists");

		try {
			ListsLocator listsLocator = new ListsLocator();

			_listsSoap = listsLocator.getListsSoap(wsdlURL);

			configureStub((Stub)_listsSoap, wsdlURL);
		}
		catch (ServiceException se) {
			throw new SharepointRuntimeException(
				"Unable to configure SOAP endpoint " + wsdlURL, se);
		}
	}

	protected void initOperations() {
		_addOrUpdateFileOperation = new AddOrUpdateFileOperation(
			_copySoap, _listsSoap);
	}

	protected void validateConnectionConfiguration(
		String sitePath, String username, String password) {

		if (!sitePath.equals(StringPool.BLANK)) {

		 	// Validate site path with

			new PathHelper(null, sitePath);
		}

		if (Validator.isNull(username)) {
			throw new SharepointRuntimeException("Username is null");
		}

		if (Validator.isNull(password)) {
			throw new SharepointRuntimeException("Password is null");
		}
	}

	private AddOrUpdateFileOperation _addOrUpdateFileOperation;
	private CopySoap _copySoap;
	private String _libraryName;
	private ListsSoap _listsSoap;
	private String _password;
	private PathHelper _pathHelper;
	private String _serverAddress;
	private int _serverPort;
	private String _serverProtocol;
	private String _sitePath;
	private String _username;

}