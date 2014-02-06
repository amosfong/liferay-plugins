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

import com.liferay.sharepoint.connector.exception.SharepointException;
import com.liferay.sharepoint.connector.schema.query.Query;
import com.liferay.sharepoint.connector.schema.query.QueryOptionsList;

import java.io.InputStream;

import java.util.List;

/**
 * @author Ivan Zaera
 */
public interface SharepointConnection {

	public void addFile(
			String folderPath, String fileName, String changeLog,
			InputStream inputStream)
		throws SharepointException;

	public void addFolder(String folderPath, String folderName)
		throws SharepointException;

	public boolean cancelCheckOutFile(String filePath)
		throws SharepointException;

	public boolean checkInFile(
			String filePath, String comment, CheckInType checkInType)
		throws SharepointException;

	public boolean checkOutFile(String filePath) throws SharepointException;

	public void copyObject(String path, String newPath)
		throws SharepointException;

	public void deleteObject(String path) throws SharepointException;

	public String getLibraryName();

	public SharepointObject getObject(long id) throws SharepointException;

	public SharepointObject getObject(String path) throws SharepointException;

	public InputStream getObjectContent(SharepointObject sharepointObject)
		throws SharepointException;

	public List<SharepointObject> getObjects(
			Query query, QueryOptionsList queryOptionsList)
		throws SharepointException;

	public List<SharepointObject> getObjects(String name)
		throws SharepointException;

	public List<SharepointObject> getObjects(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException;

	public int getObjectsCount(
			String folderPath, ObjectTypeFilter objectTypeFilter)
		throws SharepointException;

	public String getServerAddress();

	public int getServerPort();

	public String getServerURL();

	public String getServiceURL();

	public String getSitePath();

	public String getUsername();

	public InputStream getVersionContent(SharepointVersion sharepointVersion)
		throws SharepointException;

	public List<SharepointVersion> getVersions(String filePath)
		throws SharepointException;

	public void moveObject(String path, String newPath)
		throws SharepointException;

	public void updateFile(String filePath, InputStream inputStream)
		throws SharepointException;

	public enum CheckInType {

		MINOR(0), MAJOR(1), OVERWRITE(2);

		public int getProtocolValue() {
			return _protocolValue;
		}

		private CheckInType(int protocolValue) {
			_protocolValue = protocolValue;
		}

		private final int _protocolValue;
	}

	public enum ObjectTypeFilter {

		FILES, FOLDERS, ALL

	}

}