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

package com.liferay.documentum.repository.model;

import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfTime;

import com.liferay.documentum.repository.DocumentumRepository;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class DocumentumFolder extends DocumentumModel implements Folder {

	public DocumentumFolder(
			DocumentumRepository documentumRepository, String uuid,
			long folderId, IDfFolder idfFolder)
		throws DfException {

		_documentumRepository = documentumRepository;
		_uuid = uuid;
		_folderId = folderId;

		IDfTime createDateIdfTime = idfFolder.getCreationDate();

		_createDate = createDateIdfTime.getDate();

		_creatorName = idfFolder.getCreatorName();
		_description = idfFolder.getTitle();

		IDfTime modifyDateIdfTime = idfFolder.getModifyDate();

		_modifiedDate = modifyDateIdfTime.getDate();

		_objectName = idfFolder.getObjectName();
		_permit = idfFolder.getPermit();
	}

	public boolean containsPermission(
		PermissionChecker permissionChecker, String actionId) {

		return containsPermission(_permit, actionId);
	}

	public List<Long> getAncestorFolderIds()
		throws PortalException, SystemException {

		List<Long> folderIds = new ArrayList<Long>();

		Folder folder = this;

		while (!folder.isRoot()) {
			folder = folder.getParentFolder();

			folderIds.add(folder.getFolderId());
		}

		return folderIds;
	}

	public List<Folder> getAncestors() throws PortalException, SystemException {
		List<Folder> folders = new ArrayList<Folder>();

		Folder folder = this;

		while (!folder.isRoot()) {
			folder = folder.getParentFolder();

			folders.add(folder);
		}

		return folders;
	}

	public Map<String, Serializable> getAttributes() {
		return null;
	}

	@Override
	public long getCompanyId() {
		return _documentumRepository.getCompanyId();
	}

	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public String getDescription() {
		return _description;
	}

	public long getFolderId() {
		return _folderId;
	}

	public long getGroupId() {
		return _documentumRepository.getGroupId();
	}

	public Date getLastPostDate() {
		return getModifiedDate();
	}

	public Object getModel() {
		return this;
	}

	@Override
	public Class<?> getModelClass() {
		return DLFolder.class;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public String getName() {
		if (isRoot()) {
			try {
				Folder folder = DLAppLocalServiceUtil.getMountFolder(
					getRepositoryId());

				return folder.getName();
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return _objectName;
	}

	@Override
	public Folder getParentFolder() throws PortalException, SystemException {
		Folder parentFolder = null;

		try {
			parentFolder = super.getParentFolder();

			if (parentFolder != null) {
				return parentFolder;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		parentFolder = DLAppLocalServiceUtil.getFolder(getParentFolderId());

		setParentFolder(parentFolder);

		return parentFolder;
	}

	@Override
	public long getPrimaryKey() {
		return _folderId;
	}

	public long getRepositoryId() {
		return _documentumRepository.getRepositoryId();
	}

	public long getUserId() {
		User user = getUser(_creatorName);

		if (user != null) {
			return user.getUserId();
		}

		return 0;
	}

	public String getUserName() {
		User user = getUser(_creatorName);

		if (user != null) {
			return user.getFullName();
		}

		return StringPool.BLANK;
	}

	public String getUserUuid() throws SystemException {
		User user = getUser(_creatorName);

		if (user != null) {
			return user.getUserUuid();
		}

		return StringPool.BLANK;
	}

	public String getUuid() {
		return _uuid;
	}

	public boolean hasInheritableLock() {
		return false;
	}

	public boolean hasLock() {
		return false;
	}

	public boolean isDefaultRepository() {
		return false;
	}

	public boolean isEscapedModel() {
		return false;
	}

	public boolean isLocked() {
		return false;
	}

	public boolean isMountPoint() {
		return false;
	}

	public boolean isRoot() {
		if (getParentFolderId() == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isSupportsLocking() {
		return false;
	}

	public boolean isSupportsMetadata() {
		return false;
	}

	public boolean isSupportsMultipleUpload() {
		return false;
	}

	public boolean isSupportsShortcuts() {
		return false;
	}

	public boolean isSupportsSocial() {
		return false;
	}

	public boolean isSupportsSubscribing() {
		return false;
	}

	public void prepare() {
	}

	public void setUserUuid(String userUuid) {
	}

	public Folder toEscapedModel() {
		return this;
	}

	public Folder toUnescapedModel() {
		return this;
	}

	private static Log _log = LogFactoryUtil.getLog(DocumentumFolder.class);

	private Date _createDate;
	private String _creatorName;
	private String _description;
	private DocumentumRepository _documentumRepository;
	private long _folderId;
	private Date _modifiedDate;
	private String _objectName;
	private int _permit;
	private String _uuid;

}