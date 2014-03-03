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
import com.liferay.sharepoint.connector.SharepointConnectionInfo;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.schema.XMLHelper;

import com.microsoft.schemas.sharepoint.soap.CopySoap;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import com.microsoft.schemas.sharepoint.soap.VersionsSoap;

import java.net.URL;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseOperation implements Operation {

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public void setCopySoap(CopySoap copySoap) {
		this.copySoap = copySoap;
	}

	@Override
	public void setListsSoap(ListsSoap listsSoap) {
		this.listsSoap = listsSoap;
	}

	@Override
	public void setOperations(Map<Class<?>, Operation> operations) {
		_operations = operations;
	}

	@Override
	public void setSharepointConnectionInfo(
		SharepointConnectionInfo sharepointConnectionInfo) {

		this.sharepointConnectionInfo = sharepointConnectionInfo;
	}

	@Override
	public void setVersionsSoap(VersionsSoap versionsSoap) {
		this.versionsSoap = versionsSoap;
	}

	public URL toURL(String path) {
		pathHelper.validatePath(path);

		URL serviceURL = sharepointConnectionInfo.getServiceURL();

		return urlHelper.toURL(
			serviceURL.toString() + sharepointConnectionInfo.getLibraryName() +
				path);
	}

	protected <O extends Operation> O getOperation(Class<O> clazz) {
		return (O)_operations.get(clazz);
	}

	protected SharepointObject getSharepointObject(
		List<SharepointObject> sharepointObjects) {

		if (sharepointObjects.isEmpty()) {
			return null;
		}

		return sharepointObjects.get(0);
	}

	protected String toFullPath(String path) {
		pathHelper.validatePath(path);

		String sitePath = sharepointConnectionInfo.getSitePath();

		String libraryName = sharepointConnectionInfo.getLibraryName();

		if (path.equals(StringPool.SLASH)) {
			return sitePath + StringPool.SLASH + libraryName;
		}

		return sitePath + StringPool.SLASH + libraryName + path;
	}

	protected static PathHelper pathHelper = new PathHelper();
	protected static URLHelper urlHelper = new URLHelper();
	protected static XMLHelper xmlHelper = new XMLHelper();

	protected CopySoap copySoap;
	protected ListsSoap listsSoap;
	protected SharepointConnectionInfo sharepointConnectionInfo;
	protected VersionsSoap versionsSoap;

	private Map<Class<?>, Operation> _operations;

}