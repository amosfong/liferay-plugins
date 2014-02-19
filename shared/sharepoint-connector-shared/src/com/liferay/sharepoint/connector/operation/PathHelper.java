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

/**
 * @author Iván Zaera
 */
public class PathHelper {

	public PathHelper(String libraryName, String sitePath) {
		_libraryName = libraryName;
		_sitePath = sitePath;
	}

	public String buildPath(String folderPath, String name) {
		_checkPath(folderPath);

		_checkName(name);

		if (folderPath.equals(StringPool.SLASH)) {
			return StringPool.SLASH + name;
		}
		else {
			return folderPath + StringPool.SLASH + name;
		}
	}

	public String getLibraryName() {
		return _libraryName;
	}

	public String getFolderPath(String path) {
		_checkPath(path);

		int pos = path.lastIndexOf(StringPool.SLASH);

		if (pos == 0) {
			return StringPool.SLASH;
		}
		else {
			return path.substring(0, pos);
		}
	}

	public String getName(String path) {
		_checkPath(path);

		if (path.equals(StringPool.SLASH)) {
			return StringPool.SLASH;
		}
		else {
			int pos = path.lastIndexOf(StringPool.SLASH);

			return path.substring(pos + 1);
		}
	}

	public String getSitePath() {
		return _sitePath;
	}

	public String toFullPath(String path) {
		_checkPath(path);

		if (path.equals(StringPool.SLASH)) {
			return _sitePath + StringPool.SLASH + _libraryName;
		}
		else {
			return _sitePath + StringPool.SLASH + _libraryName + path;
		}
	}

	private void _checkName(String name) {
		if ((name == null) || name.contains(StringPool.SLASH)) {
			throw new IllegalArgumentException(
				"Invalid file or folder name " + name);
		}
	}

	private void _checkPath(String path) {
		if ((path == null) || (!path.equals(StringPool.SLASH) &&
			 (!path.startsWith(StringPool.SLASH) ||
			  path.endsWith(StringPool.SLASH)))) {

			throw new IllegalArgumentException("Invalid path " + path);
		}
	}

	private String _libraryName;
	private String _sitePath;

}