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
import com.liferay.sharepoint.connector.schema.query.QueryNode;
import com.liferay.sharepoint.connector.schema.query.QueryOptionsNode;

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

	public String buildPath(String parentFolderPath, String newFileName) {
		return null;
	}

	@Override
	public boolean cancelCheckout(String filePath) throws SharepointException {
		return _cancelCheckoutOp.run(filePath);
	}

	@Override
	public boolean checkInFile(
			String filePath, String comment, CheckInType checkInType)
		throws SharepointException {

		return _checkInFileOp.run(filePath, comment, checkInType);
	}

	@Override
	public boolean checkOutFile(String filePath) throws SharepointException {
		return _checkOutFileOp.run(filePath);
	}

	@Override
	public void copy(String path, String newPath) throws SharepointException {
		_copyOp.run(path, newPath);
	}

	@Override
	public int countByParentFolder(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException {

		List<SharepointObject> sharepointObjects = searchByParentFolder(
			folderPath, objectTypeFilter);

		return sharepointObjects.size();
	}

	@Override
	public void createFile(
			String folderPath, String fileName, String changeLog,
			InputStream inputStream)
		throws SharepointException {

		if (changeLog == null) {
			changeLog = StringPool.BLANK;
		}

		String filePath = buildPath(folderPath, fileName);

		_createOrUpdateFileOp.run(filePath, changeLog, inputStream);
	}

	@Override
	public void createFolder(String folderPath, String folderName)
		throws SharepointException {

		_createFolderOp.run(folderPath, folderName);
	}

	@Override
	public void delete(String path) throws SharepointException {
		_deleteOp.run(path);
	}

	@Override
	public SharepointObject getById(long id) throws SharepointException {
		return _getByIdOp.run(id);
	}

	@Override
	public SharepointObject getByPath(String path) throws SharepointException {
		return _getByPathOp.run(path);
	}

	@Override
	public InputStream getFileContent(SharepointObject sharepointObject)
		throws SharepointException {

		return _getFileContentOp.run(sharepointObject);
	}

	@Override
	public InputStream getFileContent(SharepointVersion sharepointVersion)
		throws SharepointException {

		return _getFileContentOp.run(sharepointVersion);
	}

	@Override
	public List<SharepointVersion> getFileVersions(String filePath)
		throws SharepointException {

		return _getFileVersionsOp.run(filePath);
	}

	@Override
	public String getLibraryName() {
		return _libraryName;
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
	public void move(String path, String newPath) throws SharepointException {
		_moveOp.run(path, newPath);
	}

	@Override
	public List<SharepointObject> searchByName(String name)
		throws SharepointException {

		return _searchByNameOp.run(name);
	}

	@Override
	public List<SharepointObject> searchByParentFolder(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException {

		return _searchByParentFolderOp.run(folderPath, objectTypeFilter);
	}

	@Override
	public List<SharepointObject> searchByQuery(
			QueryNode queryNode, QueryOptionsNode queryOptionsNode)
		throws SharepointException {

		return _searchByQueryOp.run(queryNode, queryOptionsNode);
	}

	@Override
	public void updateFile(String filePath, InputStream inputStream)
		throws SharepointException {

		_createOrUpdateFileOp.run(filePath, null, inputStream);
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
		_searchByQueryOp = new SearchByQueryOp(this);
		_searchByNameOp = new SearchByNameOp(this);
		_searchByParentFolderOp = new SearchByParentFolderOp(this);
		_getByPathOp = new GetByPathOp(this);
		_getByIdOp = new GetByIdOp(this);
		_createFolderOp = new CreateFolderOp(this);
		_createOrUpdateFileOp = new CreateOrUpdateFileOp(this);
		_copyOp = new CopyOp(this);
		_moveOp = new MoveOp(this);
		_deleteOp = new DeleteOp(this);
		_checkOutFileOp = new CheckOutFileOp(this);
		_checkInFileOp = new CheckInFileOp(this);
		_cancelCheckoutOp = new CancelCheckOutOp(this);
		_getFileVersionsOp = new GetFileVersionsOp(this);
		_getFileContentOp = new GetFileContentOp(this);
	}

	private CancelCheckOutOp _cancelCheckoutOp;
	private CheckInFileOp _checkInFileOp;
	private CheckOutFileOp _checkOutFileOp;
	private CopyOp _copyOp;
	private CreateFolderOp _createFolderOp;
	private CreateOrUpdateFileOp _createOrUpdateFileOp;
	private DeleteOp _deleteOp;
	private GetByIdOp _getByIdOp;
	private GetByPathOp _getByPathOp;
	private GetFileContentOp _getFileContentOp;
	private GetFileVersionsOp _getFileVersionsOp;
	private String _libraryName;
	private MoveOp _moveOp;
	private String _password;
	private SearchByNameOp _searchByNameOp;
	private SearchByParentFolderOp _searchByParentFolderOp;
	private SearchByQueryOp _searchByQueryOp;
	private String _serverAddress;
	private String _serverURL;
	private String _serviceURL;
	private String _sitePath;
	private String _user;

}