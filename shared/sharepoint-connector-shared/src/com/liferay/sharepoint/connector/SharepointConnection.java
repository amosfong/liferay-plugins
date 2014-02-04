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
import com.liferay.sharepoint.connector.schema.query.QueryNode;
import com.liferay.sharepoint.connector.schema.query.QueryOptionsNode;

import java.io.InputStream;

import java.util.List;

/**
 * @author Ivan Zaera
 */
public interface SharepointConnection {

	public boolean cancelCheckout(String filePath) throws SharepointException;

	public boolean checkInFile(
			String filePath, String comment, CheckInType checkInType)
		throws SharepointException;

	public boolean checkOutFile(String filePath) throws SharepointException;

	public void copy(String path, String newPath) throws SharepointException;

	public int countByParentFolder(String folderPath, ObjectTypeFilter filter)
		throws SharepointException;

	public void createFile(
			String folderPath, String fileName, String changeLog,
			InputStream inputStream)
		throws SharepointException;

	public void createFolder(String folderPath, String folderName)
		throws SharepointException;

	public void delete(String path) throws SharepointException;

	public SharepointObject getById(long id) throws SharepointException;

	public SharepointObject getByPath(String path) throws SharepointException;

	public InputStream getFileContent(SharepointObject sharepointObject)
		throws SharepointException;

	public InputStream getFileContent(SharepointVersion sharepointVersion)
		throws SharepointException;

	public List<SharepointVersion> getFileVersions(String filePath)
		throws SharepointException;

	public String getLibraryName();

	public void move(String path, String newPath) throws SharepointException;

	public List<SharepointObject> searchByName(String name)
		throws SharepointException;

	public List<SharepointObject> searchByParentFolder(
			String folderPath, ObjectTypeFilter filter)
		throws SharepointException;

	public List<SharepointObject> searchByQuery(
			QueryNode queryNode, QueryOptionsNode queryOptionsNode)
		throws SharepointException;

	public void updateFile(String filePath, InputStream inputStream)
		throws SharepointException;

	String getServerURL();

	String getSitePath();

	public enum CheckInType {

		MINOR(0), MAJOR(1), OVERWRITE(2);

		CheckInType(int protocolValue) {
			_protocolValue = protocolValue;
		}

		public int getProtocolValue() {
			return _protocolValue;
		}

		private final int _protocolValue;
	}

	public enum ObjectTypeFilter {

		FILES, FOLDERS, ALL

	}

	String getServerAddress();

	int getServerPort();

	String getUser();

	String getServiceURL();

}