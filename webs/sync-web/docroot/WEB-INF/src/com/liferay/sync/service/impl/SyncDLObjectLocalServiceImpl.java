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

package com.liferay.sync.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.model.DLSyncConstants;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.service.base.SyncDLObjectLocalServiceBaseImpl;

import java.util.Date;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class SyncDLObjectLocalServiceImpl
	extends SyncDLObjectLocalServiceBaseImpl {

	@Override
	public SyncDLObject addSyncDLObject(
			long fileId, String fileUuid, long companyId, long repositoryId,
			long parentFolderId, String name, String description,
			String checksum, long lockUserId, String lockUserName, long size,
			String type, String version)
		throws PortalException, SystemException {

		if (!isDefaultRepository(parentFolderId)) {
			return null;
		}

		Date now = new Date();

		long syncId = counterLocalService.increment();

		SyncDLObject syncDLObject = syncDLObjectPersistence.create(syncId);

		syncDLObject.setCompanyId(companyId);
		syncDLObject.setCreateDate(now.getTime());
		syncDLObject.setModifiedDate(now.getTime());
		syncDLObject.setFileId(fileId);
		syncDLObject.setFileUuid(fileUuid);
		syncDLObject.setRepositoryId(repositoryId);
		syncDLObject.setParentFolderId(parentFolderId);
		syncDLObject.setName(name);
		syncDLObject.setChecksum(checksum);
		syncDLObject.setDescription(description);
		syncDLObject.setEvent(DLSyncConstants.EVENT_ADD);
		syncDLObject.setLockUserId(lockUserId);
		syncDLObject.setLockUserName(lockUserName);
		syncDLObject.setSize(size);
		syncDLObject.setType(type);
		syncDLObject.setVersion(version);

		return syncDLObjectPersistence.update(syncDLObject);
	}

	@Override
	public SyncDLObject updateSyncDLObject(
			long fileId, long parentFolderId, String name, String description,
			String checksum, String event, long lockUserId, String lockUserName,
			long size, String version)
		throws PortalException, SystemException {

		if (!isDefaultRepository(parentFolderId)) {
			return null;
		}

		SyncDLObject SyncDLObject = null;

		if (event == DLSyncConstants.EVENT_DELETE) {
			SyncDLObject = syncDLObjectPersistence.fetchByFileId(fileId);

			if (SyncDLObject == null) {
				return null;
			}
		}
		else {
			SyncDLObject = syncDLObjectPersistence.findByFileId(fileId);
		}

		SyncDLObject.setModifiedDate(System.currentTimeMillis());
		SyncDLObject.setParentFolderId(parentFolderId);
		SyncDLObject.setName(name);
		SyncDLObject.setChecksum(checksum);
		SyncDLObject.setDescription(description);
		SyncDLObject.setEvent(event);
		SyncDLObject.setLockUserId(lockUserId);
		SyncDLObject.setLockUserName(lockUserName);
		SyncDLObject.setSize(size);
		SyncDLObject.setVersion(version);

		return syncDLObjectPersistence.update(SyncDLObject);
	}

	protected boolean isDefaultRepository(long folderId)
		throws PortalException, SystemException {

		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			return true;
		}

		Folder folder = dlAppLocalService.getFolder(folderId);

		return folder.isDefaultRepository();
	}

}