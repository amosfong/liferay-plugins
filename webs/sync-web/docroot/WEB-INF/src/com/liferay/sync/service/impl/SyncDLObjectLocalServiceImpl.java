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
 * The implementation of the sync d l object local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the {@link
 * com.liferay.sync.service.SyncDLObjectLocalService} interface.  <p> This is a
 * local service. Methods of this service will not have security checks based on
 * the propagated JAAS credentials because this service can only be accessed
 * from within the same VM.
 * </p>
 *
 * @author Michael Young
 * @author Dennis Ju
 * @see    com.liferay.sync.service.base.SyncDLObjectLocalServiceBaseImpl
 * @see    com.liferay.sync.service.SyncDLObjectLocalServiceUtil
 */
public class SyncDLObjectLocalServiceImpl
	extends SyncDLObjectLocalServiceBaseImpl {

	public SyncDLObject addSyncDLObject(
			long fileId, String fileUuid, long companyId, long repositoryId,
			long lockUserId, long parentFolderId, String name,
			String description, String checksum, long size, String type,
			String lockUserName, String version)
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

	public SyncDLObject updateSyncDLObject(
			long fileId, long lockUserId, long parentFolderId, String name,
			String description, String checksum, String event,
			String lockUserName, long size, String version)
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