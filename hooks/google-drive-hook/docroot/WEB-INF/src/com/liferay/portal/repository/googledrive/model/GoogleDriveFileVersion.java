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

package com.liferay.portal.repository.googledrive.model;

import com.google.api.services.drive.model.Revision;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.repository.external.ExtRepositoryFileVersion;

/**
 * @author Sergio González
 */
public class GoogleDriveFileVersion
	extends GoogleDriveModel implements ExtRepositoryFileVersion {

	public GoogleDriveFileVersion(
		Revision revision, String extRepositoryFileEntryKey, int version) {

		super(
			revision.getModifiedDate(), revision.getId(),
			revision.getFileSize(), revision.getLastModifyingUserName());

		_revision = revision;
		_extRepositoryFileEntryKey = extRepositoryFileEntryKey;
		_version = version + ".0";
	}

	@Override
	public String getChangeLog() {
		return StringPool.BLANK;
	}

	public String getDownloadURL() {
		return _revision.getDownloadUrl();
	}

	@Override
	public String getExtRepositoryModelKey() {
		StringBundler sb = new StringBundler(5);

		sb.append(_extRepositoryFileEntryKey);
		sb.append(StringPool.COLON);
		sb.append(_revision.getId());
		sb.append(StringPool.COLON);
		sb.append(_version);

		return sb.toString();
	}

	@Override
	public String getMimeType() {
		return _revision.getMimeType();
	}

	@Override
	public String getVersion() {
		return _version;
	}

	private String _extRepositoryFileEntryKey;
	private Revision _revision;
	private String _version;

}