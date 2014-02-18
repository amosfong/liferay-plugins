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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sharepoint.connector.SharepointConnection;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.SharepointResultException;

import com.microsoft.schemas.sharepoint.soap.CopyErrorCode;
import com.microsoft.schemas.sharepoint.soap.CopyResult;
import com.microsoft.schemas.sharepoint.soap.CopySoap;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import com.microsoft.schemas.sharepoint.soap.holders.CopyResultCollectionHolder;

import java.net.URL;

import java.rmi.RemoteException;

import java.util.List;

import org.apache.axis.holders.UnsignedIntHolder;

/**
 * @author Ivan Zaera
 */
public class CopyObjectOperation extends BaseOperation {

	public CopyObjectOperation(
		CopySoap copySoap, ListsSoap listsSoap, String libraryName,
		String sitePath) {

		_copySoap = copySoap;

		_addFolderOperation = new AddFolderOperation(listsSoap, libraryName);

		_checkInFileOperation = new CheckInFileOperation(listsSoap);

		_getObjectByPathOperation = new GetObjectByPathOperation(
			listsSoap, libraryName, sitePath);

		_getObjectsByFolderOperation = new GetObjectsByFolderOperation(
			listsSoap, libraryName, sitePath);
	}

	public void execute(String path, String newPath)
		throws SharepointException {

		SharepointObject sharepointObject = _getObjectByPathOperation.execute(
			path);

		if (sharepointObject == null) {
			throw new SharepointException(
				"Unable to find Sharepoint object at " + path);
		}

		if (sharepointObject.isFile()) {
			_copyFile(path, newPath);
		}
		else {
			_copyFolder(path, newPath);
		}
	}

	private void _copyFile(String path, String newPath)
		throws SharepointException {

		URL pathURL = urlHelper.toURL(path);

		URL newPathURL = urlHelper.toURL(newPath);

		CopyResultCollectionHolder copyResultCollectionHolder =
			new CopyResultCollectionHolder();

		try {
			_copySoap.copyIntoItemsLocal(
				pathURL.toString(), new String[] {newPathURL.toString()},
				new UnsignedIntHolder(), copyResultCollectionHolder);
		}
		catch (RemoteException re) {
			throw new SharepointException(
				"Unable to communicate with the Sharepoint server", re);
		}

		CopyResult copyResult = copyResultCollectionHolder.value[0];

		CopyErrorCode copyErrorCode = copyResult.getErrorCode();

		if (copyErrorCode != CopyErrorCode.Success) {
			throw new SharepointResultException(
					copyErrorCode.toString(), copyResult.getErrorMessage());
		}

		_checkInFileOperation.execute(
			newPath, StringPool.BLANK, SharepointConnection.CheckInType.MAJOR);
	}

	private void _copyFolder(String path, String newPath)
		throws SharepointException {

		_createFolder(newPath);

		List<SharepointObject> sharepointObjects =
			_getObjectsByFolderOperation.execute(
				path, SharepointConnection.ObjectTypeFilter.ALL);

		for (SharepointObject sharepointObject : sharepointObjects) {
			String objectPath = buildPath(path, sharepointObject.getName());
			String newObjectPath = buildPath(
				newPath, sharepointObject.getName());

			if (sharepointObject.isFile()) {
				_copyFile(objectPath, newObjectPath);
			}
			else {
				_copyFolder(objectPath, newObjectPath);
			}
		}
	}

	private void _createFolder(String folderPath) {
		try {
			String folderPathFolder = getPathFolder(folderPath);

			String folderPathName = getPathName(folderPath);

			_addFolderOperation.execute(folderPathFolder, folderPathName);
		}
		catch (SharepointException se) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to create folder at " + folderPath, se);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CopyObjectOperation.class);

	private AddFolderOperation _addFolderOperation;
	private CheckInFileOperation _checkInFileOperation;
	private CopySoap _copySoap;
	private GetObjectByPathOperation _getObjectByPathOperation;
	private GetObjectsByFolderOperation _getObjectsByFolderOperation;

}