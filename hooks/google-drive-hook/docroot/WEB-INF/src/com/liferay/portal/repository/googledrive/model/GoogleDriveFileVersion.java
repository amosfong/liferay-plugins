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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.repository.external.ExtRepositoryFileVersion;

import java.util.Date;

/**
 * @author Sergio González
 */
public class GoogleDriveFileVersion
	extends GoogleDriveModel implements ExtRepositoryFileVersion {

	public GoogleDriveFileVersion(Revision revision) {
		super(
			new Date(revision.getModifiedDate().getValue()), revision.getId(),
			revision.getFileSize(), revision.getLastModifyingUserName());

		_mimeType = revision.getMimeType();
	}

	@Override
	public String getChangeLog() {
		return StringPool.BLANK;
	}

	@Override
	public String getMimeType() {
		return _mimeType;
	}

	@Override
	public String getVersion() {
		return getExtRepositoryModelKey();
	}

	private final String _mimeType;

}