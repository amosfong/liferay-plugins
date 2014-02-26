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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.sharepoint.connector.operation.AddFolderOperation;
import com.liferay.sharepoint.connector.operation.AddOrUpdateFileOperation;
import com.liferay.sharepoint.connector.operation.BatchOperation;
import com.liferay.sharepoint.connector.operation.CancelCheckOutFileOperation;
import com.liferay.sharepoint.connector.operation.CheckInFileOperation;
import com.liferay.sharepoint.connector.operation.CheckOutFileOperation;
import com.liferay.sharepoint.connector.operation.CopyObjectOperation;
import com.liferay.sharepoint.connector.operation.DeleteObjectOperation;
import com.liferay.sharepoint.connector.operation.GetContentOperation;
import com.liferay.sharepoint.connector.operation.GetObjectByPathOperation;
import com.liferay.sharepoint.connector.operation.GetObjectsByFolderOperation;
import com.liferay.sharepoint.connector.operation.GetObjectsByNameOperation;
import com.liferay.sharepoint.connector.operation.GetObjectsByQueryOperation;
import com.liferay.sharepoint.connector.operation.MoveObjectOperation;
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

		validateCredentials(username, password);

		_serverProtocol = serverProtocol;
		_serverAddress = serverAddress;
		_serverPort = serverPort;
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

		_pathHelper.validatePath(folderPath);

		_pathHelper.validateName(folderName);

		_addFolderOperation.execute(folderPath, folderName);
	}

	@Override
	public boolean cancelCheckOutFile(String filePath)
		throws SharepointException {

		_pathHelper.validatePath(filePath);

		return _cancelCheckOutFileOperation.execute(filePath);
	}

	@Override
	public boolean checkInFile(
			String filePath, String comment, CheckInType checkInType)
		throws SharepointException {

		_pathHelper.validatePath(filePath);

		return _checkInFileOperation.execute(filePath, comment, checkInType);
	}

	@Override
	public boolean checkOutFile(String filePath) throws SharepointException {
		_pathHelper.validatePath(filePath);

		return _checkOutFileOperation.execute(filePath);
	}

	@Override
	public void copySharepointObject(String path, String newPath)
		throws SharepointException {

		_pathHelper.validatePath(path);

		_pathHelper.validatePath(newPath);

		_copyObjectOperation.execute(path, newPath);
	}

	@Override
	public void deleteSharepointObject(String path) throws SharepointException {
		_pathHelper.validatePath(path);

		_deleteObjectOperation.execute(path);
	}

	@Override
	public String getLibraryName() {
		return _pathHelper.getLibraryName();
	}

	@Override
	public SharepointObject getSharepointObject(long id)
		throws SharepointException {

		return null;
	}

	@Override
	public SharepointObject getSharepointObject(String path)
		throws SharepointException {

		return null;
	}

	@Override
	public InputStream getInputStream(SharepointObject sharepointObject)
		throws SharepointException {

		return _getContentOperation.execute(sharepointObject);
	}

	@Override
	public List<SharepointObject> getSharepointObjects(
			Query query, QueryOptionsList queryOptionsList)
		throws SharepointException {

		return _getObjectsByQueryOperation.execute(query, queryOptionsList);
	}

	@Override
	public List<SharepointObject> getSharepointObjects(String name)
		throws SharepointException {

		return _getObjectsByNameOperation.execute(name);
	}

	@Override
	public List<SharepointObject> getSharepointObjects(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException {

		_pathHelper.validatePath(folderPath);

		return _getObjectsByFolderOperation.execute(
			folderPath, objectTypeFilter);
	}

	@Override
	public int getgetSharepointObjectsCount(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException {

		List<SharepointObject> sharepointObjects = getSharepointObjects(
			folderPath, objectTypeFilter);

		return sharepointObjects.size();
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
		return _pathHelper.getSitePath();
	}

	@Override
	public String getUsername() {
		return _username;
	}

	@Override
	public InputStream getInputStream(SharepointVersion sharepointVersion)
		throws SharepointException {

		return _getContentOperation.execute(sharepointVersion);
	}

	@Override
	public List<SharepointVersion> getSharepointVersions(String filePath)
		throws SharepointException {

		return null;
	}

	@Override
	public void moveSharepointObject(String path, String newPath)
		throws SharepointException {

		_pathHelper.validatePath(path);

		_pathHelper.validatePath(newPath);

		_moveObjectOperation.execute(path, newPath);
	}

	@Override
	public void updateFile(String filePath, InputStream inputStream)
		throws SharepointException {

		_pathHelper.validatePath(filePath);

		_addOrUpdateFileOperation.execute(filePath, null, inputStream);
	}

	protected void configureStub(Stub stub, URL wsdlURL) {
		stub._setProperty(Stub.ENDPOINT_ADDRESS_PROPERTY, wsdlURL.toString());
		stub._setProperty(Call.PASSWORD_PROPERTY, _password);
		stub._setProperty(Call.USERNAME_PROPERTY, _username);
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
		_batchOperation = new BatchOperation(_listsSoap, _pathHelper);

		_cancelCheckOutFileOperation = new CancelCheckOutFileOperation(
			_listsSoap);

		_checkInFileOperation = new CheckInFileOperation(_listsSoap);

		_checkOutFileOperation = new CheckOutFileOperation(_listsSoap);

		_getContentOperation = new GetContentOperation(_username, _password);

		_getObjectsByQueryOperation = new GetObjectsByQueryOperation(
			_listsSoap, _pathHelper);

		_addFolderOperation = new AddFolderOperation(
			_pathHelper, _batchOperation);

		_addOrUpdateFileOperation = new AddOrUpdateFileOperation(
			_copySoap, _checkInFileOperation);

		_getObjectsByFolderOperation = new GetObjectsByFolderOperation(
			_pathHelper, _getObjectsByQueryOperation);

		_getObjectsByNameOperation = new GetObjectsByNameOperation(
			_getObjectsByQueryOperation);

		_getObjectByPathOperation = new GetObjectByPathOperation(
			_pathHelper, _getObjectsByQueryOperation);

		_copyObjectOperation = new CopyObjectOperation(
			_copySoap, _pathHelper, _addFolderOperation, _checkInFileOperation,
			_getObjectByPathOperation, _getObjectsByFolderOperation);

		_deleteObjectOperation = new DeleteObjectOperation(
			_pathHelper, _batchOperation, _getObjectByPathOperation);

		_moveObjectOperation = new MoveObjectOperation(
			_pathHelper, _batchOperation, _copyObjectOperation,
			_deleteObjectOperation, _getObjectByPathOperation);
	}

	protected void validateCredentials(String username, String password) {
		if (Validator.isNull(username)) {
			throw new SharepointRuntimeException("Username is null");
		}

		if (Validator.isNull(password)) {
			throw new SharepointRuntimeException("Password is null");
		}
	}

	private AddFolderOperation _addFolderOperation;
	private AddOrUpdateFileOperation _addOrUpdateFileOperation;
	private BatchOperation _batchOperation;
	private CancelCheckOutFileOperation _cancelCheckOutFileOperation;
	private CheckInFileOperation _checkInFileOperation;
	private CheckOutFileOperation _checkOutFileOperation;
	private CopyObjectOperation _copyObjectOperation;
	private CopySoap _copySoap;
	private DeleteObjectOperation _deleteObjectOperation;
	private GetContentOperation _getContentOperation;
	private GetObjectByPathOperation _getObjectByPathOperation;
	private GetObjectsByFolderOperation _getObjectsByFolderOperation;
	private GetObjectsByNameOperation _getObjectsByNameOperation;
	private GetObjectsByQueryOperation _getObjectsByQueryOperation;
	private ListsSoap _listsSoap;
	private MoveObjectOperation _moveObjectOperation;
	private String _password;
	private PathHelper _pathHelper;
	private String _serverAddress;
	private int _serverPort;
	private String _serverProtocol;
	private String _username;

}