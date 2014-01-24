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
import com.google.api.services.drive.model.Permission;

import com.liferay.repository.external.ExtRepositoryObject;

import java.util.Date;

/**
 * @author Sergio González
 */
public class GoogleDriveObject
	extends GoogleDriveModel implements ExtRepositoryObject {

	public GoogleDriveObject(File file) {
		super(file);

		DateTime modifiedDateTime = file.getCreatedDate();

		_modifiedDate = new Date(modifiedDateTime.getValue());

		_extension = file.getFileExtension();

		_description = file.getDescription();

		_permission = file.getUserPermission();
	}

	@Override
	public boolean containsPermission(
		ExtRepositoryPermission extRepositoryPermission) {

		String role = _permission.getRole();

		if (extRepositoryPermission.equals(ExtRepositoryPermission.ACCESS)) {
			return true;
		}
		else if (ExtRepositoryPermission.ADD_DOCUMENT.equals(
					extRepositoryPermission)) {

			return role.equals("owner") || role.equals("writer");
		}
		else if (ExtRepositoryPermission.ADD_FOLDER.equals(
					extRepositoryPermission)) {

			return role.equals("owner") || role.equals("writer");
		}
		else if (ExtRepositoryPermission.ADD_SUBFOLDER.equals(
					extRepositoryPermission)) {

			return role.equals("owner") || role.equals("writer");
		}
		else if (ExtRepositoryPermission.DELETE.equals(
					extRepositoryPermission)) {

			return role.equals("owner");
		}
		else if (ExtRepositoryPermission.UPDATE.equals(
					extRepositoryPermission)) {

			return role.equals("owner") || role.equals("writer");
		}
		else if (ExtRepositoryPermission.VIEW.equals(extRepositoryPermission)) {
			return true;
		}

		return false;
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public String getExtension() {
		return _extension;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	private String _description;
	private String _extension;
	private Date _modifiedDate;
	private Permission _permission;

}