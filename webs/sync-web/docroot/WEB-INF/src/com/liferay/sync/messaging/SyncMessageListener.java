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

package com.liferay.sync.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Lock;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.model.DLSyncConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileVersionLocalServiceUtil;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;
import com.liferay.sync.util.SyncUtil;

/**
 * @author Dennis Ju
 */
public class SyncMessageListener extends BaseMessageListener {

	protected void addSyncDLObject(Message message) throws Exception {
		String type = message.getString("type");
		long typeId = message.getLong("typeId");

		if (type.equals(DLSyncConstants.TYPE_FILE)) {
			FileEntry fileEntry = null;

			try {
				fileEntry = DLAppLocalServiceUtil.getFileEntry(typeId);
			}
			catch (NoSuchFileEntryException nsfee) {
				return;
			}

			String checksum = SyncUtil.getChecksum(fileEntry);

			long lockUserId = 0;
			String lockUserName = StringPool.BLANK;

			Lock lock = fileEntry.getLock();

			if (lock != null) {
				lockUserId = lock.getUserId();
				lockUserName = lock.getUserName();
			}

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				fileEntry.getFileEntryId(), fileEntry.getUuid(),
				fileEntry.getCompanyId(), fileEntry.getRepositoryId(),
				fileEntry.getFolderId(), fileEntry.getTitle(),
				fileEntry.getDescription(), checksum, lockUserId, lockUserName,
				fileEntry.getSize(), DLSyncConstants.TYPE_FILE,
				fileEntry.getVersion());
		}
		else if (type.equals(DLSyncConstants.TYPE_FOLDER)) {
			Folder folder = DLAppLocalServiceUtil.getFolder(typeId);

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				folder.getFolderId(), folder.getUuid(), folder.getCompanyId(),
				folder.getRepositoryId(), folder.getParentFolderId(),
				folder.getName(), folder.getDescription(), StringPool.BLANK, 0,
				StringPool.BLANK, 0, DLSyncConstants.TYPE_FOLDER, "-1");
		}
	}

	protected void deleteSyncDLObject(Message message) throws Exception {
		String type = message.getString("type");
		long typeId = message.getLong("typeId");

		if (type.equals(DLSyncConstants.TYPE_FILE)) {
			FileEntry fileEntry = null;

			try {
				fileEntry = DLAppLocalServiceUtil.getFileEntry(typeId);
			}
			catch (NoSuchFileEntryException nsfee) {
				return;
			}

			SyncDLObjectLocalServiceUtil.updateSyncDLObject(
				fileEntry.getFileEntryId(), fileEntry.getFolderId(),
				fileEntry.getTitle(), StringPool.BLANK,
				fileEntry.getDescription(), DLSyncConstants.EVENT_DELETE, 0,
				StringPool.BLANK, fileEntry.getSize(), fileEntry.getVersion());
		}
		else if (type.equals(DLSyncConstants.TYPE_FOLDER)) {
			Folder folder = DLAppLocalServiceUtil.getFolder(typeId);

			SyncDLObjectLocalServiceUtil.updateSyncDLObject(
				folder.getFolderId(), folder.getParentFolderId(),
				folder.getName(), folder.getDescription(), StringPool.BLANK,
				DLSyncConstants.EVENT_DELETE, 0, StringPool.BLANK, 0, "-1");
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String event = message.getString("event");

		if (event.equals(DLSyncConstants.EVENT_ADD)) {
			addSyncDLObject(message);
		}
		else if (event.equals(DLSyncConstants.EVENT_DELETE)) {
			deleteSyncDLObject(message);
		}
		else if (event.equals(DLSyncConstants.EVENT_UPDATE)) {
			updateSyncDLObject(message);
		}
	}

	protected void updateSyncDLObject(Message message) throws Exception {
		String type = message.getString("type");
		long typeId = message.getLong("typeId");

		if (type.equals(DLSyncConstants.TYPE_FILE)) {
			FileEntry fileEntry = null;

			try {
				fileEntry = DLAppLocalServiceUtil.getFileEntry(typeId);
			}
			catch (NoSuchFileEntryException nsfee) {
				return;
			}

			long lockUserId = 0;
			String lockUserName = StringPool.BLANK;

			Lock lock = fileEntry.getLock();

			if (lock != null) {
				lockUserId = lock.getUserId();
				lockUserName = lock.getUserName();
			}

			if (fileEntry.isCheckedOut()) {
				DLFileVersion dlFileVersion =
					DLFileVersionLocalServiceUtil.getLatestFileVersion(
						fileEntry.getFileEntryId(), false);

				String checksum = SyncUtil.getChecksum(
					dlFileVersion.getContentStream(false));

				SyncDLObjectLocalServiceUtil.updateSyncDLObject(
					dlFileVersion.getFileEntryId(), dlFileVersion.getFolderId(),
					dlFileVersion.getTitle(), dlFileVersion.getDescription(),
					checksum, DLSyncConstants.EVENT_UPDATE, lockUserId,
					lockUserName, dlFileVersion.getSize(),
					dlFileVersion.getVersion());
			}
			else {
				String checksum = SyncUtil.getChecksum(fileEntry);

				SyncDLObjectLocalServiceUtil.updateSyncDLObject(
					fileEntry.getFileEntryId(), fileEntry.getFolderId(),
					fileEntry.getTitle(), fileEntry.getDescription(), checksum,
					DLSyncConstants.EVENT_UPDATE, lockUserId, lockUserName,
					fileEntry.getSize(), fileEntry.getVersion());
			}
		}
		else if (type.equals(DLSyncConstants.TYPE_FOLDER)) {
			Folder folder = DLAppLocalServiceUtil.getFolder(typeId);

			SyncDLObjectLocalServiceUtil.updateSyncDLObject(
				folder.getFolderId(), folder.getParentFolderId(),
				folder.getName(), folder.getDescription(), StringPool.BLANK,
				DLSyncConstants.EVENT_UPDATE, 0, StringPool.BLANK, 0, "-1");
		}
	}

}