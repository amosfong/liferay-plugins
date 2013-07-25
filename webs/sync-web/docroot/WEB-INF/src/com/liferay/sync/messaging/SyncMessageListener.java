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
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
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

	protected void addSyncDLObject(
			long typePK, String type, long modifiedDate, String event)
		throws Exception {

		if (event.equals(DLSyncConstants.EVENT_DELETE)) {
			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				0, modifiedDate, typePK, StringPool.BLANK, type, 0, 0, 
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, event, 0, 
				StringPool.BLANK, 0, StringPool.BLANK);
		}
		else if (type.equals(DLSyncConstants.TYPE_FILE)) {
			FileEntry fileEntry = null;

			try {
				fileEntry = DLAppLocalServiceUtil.getFileEntry(typePK);
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

				SyncDLObjectLocalServiceUtil.addSyncDLObject(
					dlFileVersion.getCompanyId(), modifiedDate,
					fileEntry.getFileEntryId(), fileEntry.getUuid(), type, 
					dlFileVersion.getRepositoryId(), 
					dlFileVersion.getFolderId(), dlFileVersion.getTitle(),
					dlFileVersion.getDescription(), checksum, event, lockUserId,
					lockUserName, dlFileVersion.getSize(), 
					dlFileVersion.getVersion());
			}
			else {
				String checksum = SyncUtil.getChecksum(fileEntry);

				SyncDLObjectLocalServiceUtil.addSyncDLObject(
					fileEntry.getCompanyId(), modifiedDate,
					fileEntry.getFileEntryId(), fileEntry.getUuid(), type, 
					fileEntry.getRepositoryId(), fileEntry.getFolderId(),
					fileEntry.getTitle(), fileEntry.getDescription(), checksum,
					event, lockUserId, lockUserName, fileEntry.getSize(), 
					fileEntry.getVersion());
			}
		}
		else {
			Folder folder = null;

			try {
				folder = DLAppLocalServiceUtil.getFolder(typePK);
			}
			catch (NoSuchFolderException nsfe) {
				return;
			}

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				folder.getCompanyId(), modifiedDate, folder.getFolderId(),
				folder.getUuid(), type, folder.getRepositoryId(),
				folder.getParentFolderId(), folder.getName(),
				folder.getDescription(), StringPool.BLANK, event, 0,
				StringPool.BLANK, 0, "-1");
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String event = message.getString("event");
		long modifiedDate = message.getLong("modifiedDate");
		String type = message.getString("type");
		long typePK = message.getLong("typePK");

		addSyncDLObject(typePK, type, modifiedDate, event);
	}

}