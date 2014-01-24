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

import com.google.api.client.util.DateTime;
import com.google.api.services.drive.model.File;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.repository.external.ExtRepositoryModel;

import java.util.Date;
import java.util.List;

/**
 * @author Sergio González
 */
public class GoogleDriveModel implements ExtRepositoryModel {

	public GoogleDriveModel(File file) {
		DateTime createDateTime = file.getCreatedDate();

		_createdDate = new Date(createDateTime.getValue());

		_id = file.getId();

		_size = GetterUtil.getLong(file.getFileSize());

		List<String> ownerNames = file.getOwnerNames();

		if (!ownerNames.isEmpty()) {
			_owner = ownerNames.get(0);
		}
	}

	@Override
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public String getExtRepositoryModelKey() {
		return _id;
	}

	@Override
	public String getOwner() {
		return _owner;
	}

	@Override
	public long getSize() {
		return _size;
	}

	private Date _createdDate;
	private String _id;
	private String _owner = StringPool.BLANK;
	private long _size;

}