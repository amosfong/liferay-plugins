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

package com.liferay.repository.external.model;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.repository.external.ExtRepositoryAdapter;
import com.liferay.repository.external.ExtRepositoryObject;
import com.liferay.repository.external.ExtRepositoryObject.ExtRepositoryPermission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Iván Zaera
 * @author Sergio González
 */
public abstract class ExtRepositoryObjectAdapter<T>
	extends ExtRepositoryModelAdapter<T> {

	@SuppressWarnings("unused")
	public boolean containsPermission(
			PermissionChecker permissionChecker, String actionId)
		throws PortalException, SystemException {

		try {
			ExtRepositoryPermission extRepositoryPermission =
				ExtRepositoryPermission.valueOf(actionId);

			return _extRepositoryObject.containsPermission(
				extRepositoryPermission);
		}
		catch (IllegalArgumentException iae) {
			throw new RepositoryException(
				"Unexpected permission action " + actionId);
		}
	}

	public List<Long> getAncestorFolderIds()
		throws PortalException, SystemException {

		List<Long> folderIds = new ArrayList<Long>();

		Folder folder = getParentFolder();

		while (!folder.isRoot()) {
			folderIds.add(folder.getFolderId());

			folder = folder.getParentFolder();
		}

		return folderIds;
	}

	public List<Folder> getAncestors() throws PortalException, SystemException {
		List<Folder> folders = new ArrayList<Folder>();

		Folder folder = getParentFolder();

		while ((folder != null) && !folder.isRoot()) {
			folders.add(folder);

			folder = getParentFolder();
		}

		if (folder != null) {
			folders.add(folder);
		}

		return folders;
	}

	public String getExtension() {
		return _extRepositoryObject.getExtension();
	}

	@Override
	public ExtRepositoryObject getExtRepositoryModel() {
		return _extRepositoryObject;
	}

	@Override
	public Date getModifiedDate() {
		return _extRepositoryObject.getModifiedDate();
	}

	public abstract String getName();

	public Folder getParentFolder() throws PortalException, SystemException {
		ExtRepositoryAdapter extRepositoryAdapter = getRepository();

		Folder parentFolder = extRepositoryAdapter.getParentFolder(this);

		if (parentFolder.isRoot()) {
			return DLAppLocalServiceUtil.getMountFolder(getRepositoryId());
		}
		else {
			return parentFolder;
		}
	}

	public boolean isInTrash() {
		return false;
	}

	public boolean isInTrashContainer() {
		return false;
	}

	public boolean isSupportsMetadata() {
		return false;
	}

	public boolean isSupportsSocial() {
		return false;
	}

	protected ExtRepositoryObjectAdapter(
		ExtRepositoryAdapter extRepositoryAdapter, long extRepositoryObjectId,
		String uuid, ExtRepositoryObject extRepositoryObject) {

		super(
			extRepositoryAdapter, extRepositoryObjectId, uuid,
			extRepositoryObject);

		_extRepositoryObject = extRepositoryObject;
	}

	private ExtRepositoryObject _extRepositoryObject;

}