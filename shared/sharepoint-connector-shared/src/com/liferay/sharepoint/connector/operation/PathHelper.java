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
		validateSitePath(sitePath);

		_libraryName = libraryName;
		_sitePath = sitePath;
	}

	public String buildPath(String folderPath, String name) {
		validatePath(folderPath);

		validateName(name);

		if (folderPath.equals(StringPool.SLASH)) {
			return StringPool.SLASH + name;
		}

		return folderPath + StringPool.SLASH + name;
	}

	public String getExtension(String path) {
		int pos = path.lastIndexOf(StringPool.PERIOD);

		if (pos == -1) {
			return StringPool.BLANK;
		}

		return path.substring(pos + 1);
	}

	public String getLibraryName() {
		return _libraryName;
	}

	public String getName(String path) {
		validatePath(path);

		if (path.equals(StringPool.SLASH)) {
			return StringPool.SLASH;
		}

		int pos = path.lastIndexOf(StringPool.SLASH);

		return path.substring(pos + 1);
	}

	public String getNameWithoutExtension(String path) {
		String name = getName(path);

		int pos = path.lastIndexOf(StringPool.PERIOD);

		if (pos == -1) {
			return name;
		}
		else {
			return name.substring(0, pos - 1);
		}
	}

	public String getParentFolderPath(String path) {
		validatePath(path);

		int pos = path.lastIndexOf(StringPool.SLASH);

		if (pos == 0) {
			return StringPool.SLASH;
		}

		return path.substring(0, pos);
	}

	public String getSitePath() {
		return _sitePath;
	}

	public String toFullPath(String path) {
		validatePath(path);

		if (path.equals(StringPool.SLASH)) {
			return _sitePath + StringPool.SLASH + _libraryName;
		}

		return _sitePath + StringPool.SLASH + _libraryName + path;
	}

	protected void validateName(String name) {
		if ((name == null) || name.contains(StringPool.SLASH)) {
			throw new IllegalArgumentException(
				"Invalid file or folder name " + name);
		}
	}

	protected void validatePath(String path) {
		if ((path == null) || (!path.equals(StringPool.SLASH) &&
			 (!path.startsWith(StringPool.SLASH) ||
			  path.endsWith(StringPool.SLASH)))) {

			throw new IllegalArgumentException("Invalid path " + path);
		}
	}

	protected void validateSitePath(String sitePath) {
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

	private String _libraryName;
	private String _sitePath;

}