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

package com.liferay.sync.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Lock;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.model.DLSyncConstants;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.impl.SyncDLObjectImpl;

import java.io.InputStream;

/**
 * @author Dennis Ju
 */
public class SyncUtil {

	public static String getChecksum(FileEntry fileEntry)
		throws PortalException, SystemException {

		DLFileEntry dlFileEntry = (DLFileEntry)fileEntry.getModel();

		long folderId = dlFileEntry.getFolderId();

		if (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			folderId = dlFileEntry.getRepositoryId();
		}

		InputStream is = DLStoreUtil.getFileAsStream(
			dlFileEntry.getCompanyId(), folderId, dlFileEntry.getName(),
			dlFileEntry.getVersion());

		return getChecksum(is);
	}

	public static String getChecksum(InputStream is) {
		return DigesterUtil.digestBase64(is);
	}

	public static SyncDLObject toSyncDLObject(FileEntry fileEntry)
		throws PortalException, SystemException {

		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setFileId(fileEntry.getFileEntryId());
		syncDLObject.setFileUuid(fileEntry.getUuid());
		syncDLObject.setParentFolderId(fileEntry.getFolderId());
		syncDLObject.setRepositoryId(fileEntry.getRepositoryId());
		syncDLObject.setName(fileEntry.getTitle());
		syncDLObject.setDescription(fileEntry.getDescription());
		syncDLObject.setChecksum(getChecksum(fileEntry));

		Lock lock = fileEntry.getLock();

		if (lock != null) {
			syncDLObject.setLockUserId(lock.getUserId());
			syncDLObject.setLockUserName(lock.getUserName());
		}
		else {
			syncDLObject.setLockUserId(0);
			syncDLObject.setLockUserName(StringPool.BLANK);
		}

		syncDLObject.setSize(fileEntry.getSize());
		syncDLObject.setType(DLSyncConstants.TYPE_FILE);
		syncDLObject.setVersion(fileEntry.getVersion());

		return syncDLObject;
	}

	public static SyncDLObject toSyncDLObject(Folder folder) {
		SyncDLObject syncDLObject = new SyncDLObjectImpl();

		syncDLObject.setFileId(folder.getFolderId());
		syncDLObject.setFileUuid(folder.getUuid());
		syncDLObject.setParentFolderId(folder.getParentFolderId());
		syncDLObject.setRepositoryId(folder.getRepositoryId());
		syncDLObject.setName(folder.getName());
		syncDLObject.setDescription(folder.getDescription());
		syncDLObject.setChecksum(StringPool.BLANK);
		syncDLObject.setLockUserId(0);
		syncDLObject.setLockUserName(StringPool.BLANK);
		syncDLObject.setSize(-1);
		syncDLObject.setType(DLSyncConstants.TYPE_FOLDER);
		syncDLObject.setVersion(StringPool.BLANK);

		return syncDLObject;
	}

}