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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sharepoint.connector.SharepointConnection;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.SharepointVersion;
import com.liferay.sharepoint.connector.exception.SharepointException;
import com.liferay.sharepoint.connector.exception.SharepointRuntimeException;
import com.liferay.sharepoint.connector.schema.query.Query;
import com.liferay.sharepoint.connector.schema.query.QueryOptionsList;

import com.microsoft.schemas.sharepoint.soap.ListsSoap;

import java.io.InputStream;

import java.util.List;

/**
 * @author Iván Zaera
 */
public class SharepointConnectionImpl implements SharepointConnection {

	public SharepointConnectionImpl(
		String serverProtocol, String serverAddress, String sitePath,
		String libraryName, String user, String password) {

		_serverAddress = serverAddress;
		_sitePath = sitePath;
		_libraryName = libraryName;
		_user = user;
		_password = password;

		_serverURL = serverProtocol + "://" + serverAddress;
		_serviceURL = _serverURL + sitePath + StringPool.SLASH;

		_checkConfiguration();

		_initOperations();
	}

	@Override
	public void addFile(
			String folderPath, String fileName, String changeLog,
			InputStream inputStream)
		throws SharepointException {

		if (changeLog == null) {
			changeLog = StringPool.BLANK;
		}

		String filePath = buildPath(folderPath, fileName);

		_addOrUpdateFileOperation.execute(filePath, changeLog, inputStream);
	}

	@Override
	public void addFolder(String folderPath, String folderName)
		throws SharepointException {

		_addFolderOperation.execute(folderPath, folderName);
	}

	public String buildPath(String parentFolderPath, String newFileName) {
		return null;
	}

	@Override
	public boolean cancelCheckOutFile(String filePath)
		throws SharepointException {

		return _cancelCheckoutFileOperation.execute(filePath);
	}

	@Override
	public boolean checkInFile(
			String filePath, String comment, CheckInType checkInType)
		throws SharepointException {

		return _checkInFileOperation.execute(filePath, comment, checkInType);
	}

	@Override
	public boolean checkOutFile(String filePath) throws SharepointException {
		return _checkOutFileOperation.execute(filePath);
	}

	@Override
	public void copyObject(String path, String newPath)
		throws SharepointException {

		_copyObjectOperation.execute(path, newPath);
	}

	@Override
	public void deleteObject(String path) throws SharepointException {
		_deleteObjectOperation.execute(path);
	}

	@Override
	public String getLibraryName() {
		return _libraryName;
	}

	public ListsSoap getListsSoap() {
		return null;
	}

	@Override
	public SharepointObject getObject(long id) throws SharepointException {
		return _getObjectByIdOperation.execute(id);
	}

	@Override
	public SharepointObject getObject(String path) throws SharepointException {
		return _getObjectByPathOperation.execute(path);
	}

	@Override
	public InputStream getObjectContent(SharepointObject sharepointObject)
		throws SharepointException {

		return _getContentOperation.execute(sharepointObject);
	}

	@Override
	public List<SharepointObject> getObjects(
			Query query, QueryOptionsList queryOptionsList)
		throws SharepointException {

		return _getObjectsByQueryOperation.execute(query, queryOptionsList);
	}

	@Override
	public List<SharepointObject> getObjects(String name)
		throws SharepointException {

		return _getObjectsByNameOperation.execute(name);
	}

	@Override
	public List<SharepointObject> getObjects(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException {

		return _getObjectsByFolderOperation.execute(
			folderPath, objectTypeFilter);
	}

	@Override
	public int getObjectsCount(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException {

		List<SharepointObject> sharepointObjects = getObjects(
			folderPath, objectTypeFilter);

		return sharepointObjects.size();
	}

	@Override
	public String getServerAddress() {
		return _serverAddress;
	}

	@Override
	public int getServerPort() {
		return 80;
	}

	@Override
	public String getServerURL() {
		return _serverURL;
	}

	@Override
	public String getServiceURL() {
		return _serviceURL;
	}

	@Override
	public String getSitePath() {
		return _sitePath;
	}

	@Override
	public String getUser() {
		return _user;
	}

	@Override
	public InputStream getVersionContent(SharepointVersion sharepointVersion)
		throws SharepointException {

		return _getContentOperation.execute(sharepointVersion);
	}

	@Override
	public List<SharepointVersion> getVersions(String filePath)
		throws SharepointException {

		return _getFileVersionsOperation.execute(filePath);
	}

	@Override
	public void moveObject(String path, String newPath)
		throws SharepointException {

		_moveObjectOperation.execute(path, newPath);
	}

	@Override
	public void updateFile(String filePath, InputStream inputStream)
		throws SharepointException {

		_addOrUpdateFileOperation.execute(filePath, null, inputStream);
	}

	private void _checkConfiguration() {
		if (_user == null) {
			throw new SharepointRuntimeException("User name cannot be null");
		}

		if (_password == null) {
			throw new SharepointRuntimeException("Password cannot be null");
		}

		if (_serverURL.endsWith(StringPool.SLASH)) {
			throw new IllegalArgumentException(
				"Server URL must not end with /");
		}

		if (!_sitePath.equals(StringPool.BLANK)) {
			if (_sitePath.equals(StringPool.SLASH)) {
				throw new IllegalArgumentException(
					"Use an empty string for root site path (instead of '/')");
			}

			if (!_sitePath.startsWith(StringPool.SLASH)) {
				throw new IllegalArgumentException(
					"Site path must start with /");
			}

			if (!_sitePath.equals(StringPool.SLASH) &&
				_sitePath.endsWith(StringPool.SLASH)) {

				throw new IllegalArgumentException(
					"Site path must not end with /");
			}
		}
	}

	private void _initOperations() {
		_getObjectsByQueryOperation = new GetObjectsByQueryOperation(this);
		_getObjectsByNameOperation = new GetObjectsByNameOperation(this);
		_getObjectsByFolderOperation = new GetObjectsByFolderOperation(this);
		_getObjectByPathOperation = new GetObjectByPathOperation(this);
		_getObjectByIdOperation = new GetObjectByIdOperation(this);
		_addFolderOperation = new AddFolderOperation(this);
		_addOrUpdateFileOperation = new AddOrUpdateFileOperation(this);
		_copyObjectOperation = new CopyObjectOperation(this);
		_moveObjectOperation = new MoveObjectOperation(this);
		_deleteObjectOperation = new DeleteObjectOperation(this);
		_checkOutFileOperation = new CheckOutFileOperation(this);
		_checkInFileOperation = new CheckInFileOperation(this);
		_cancelCheckoutFileOperation = new CancelCheckOutFileOperation(this);
		_getFileVersionsOperation = new GetFileVersionsOperation(this);
		_getContentOperation = new GetContentOperation(this);
	}

	private AddFolderOperation _addFolderOperation;
	private AddOrUpdateFileOperation _addOrUpdateFileOperation;
	private CancelCheckOutFileOperation _cancelCheckoutFileOperation;
	private CheckInFileOperation _checkInFileOperation;
	private CheckOutFileOperation _checkOutFileOperation;
	private CopyObjectOperation _copyObjectOperation;
	private DeleteObjectOperation _deleteObjectOperation;
	private GetContentOperation _getContentOperation;
	private GetFileVersionsOperation _getFileVersionsOperation;
	private GetObjectByIdOperation _getObjectByIdOperation;
	private GetObjectByPathOperation _getObjectByPathOperation;
	private GetObjectsByFolderOperation _getObjectsByFolderOperation;
	private GetObjectsByNameOperation _getObjectsByNameOperation;
	private GetObjectsByQueryOperation _getObjectsByQueryOperation;
	private String _libraryName;
	private MoveObjectOperation _moveObjectOperation;
	private String _password;
	private String _serverAddress;
	private String _serverURL;
	private String _serviceURL;
	private String _sitePath;
	private String _user;

}