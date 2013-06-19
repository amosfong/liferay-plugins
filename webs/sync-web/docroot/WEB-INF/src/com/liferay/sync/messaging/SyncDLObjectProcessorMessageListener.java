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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Lock;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLSyncConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;

import java.io.InputStream;

/**
 * @author Dennis Ju
 */
public class SyncDLObjectProcessorMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message)
		throws PortalException, SystemException {

		String event = message.getString("event");

		if (event.equals(DLSyncConstants.EVENT_ADD)) {
			_add(message);
		}
		else if (event.equals(DLSyncConstants.EVENT_DELETE)) {
			_delete(message);
		}
		else if (event.equals(DLSyncConstants.EVENT_UPDATE)) {
			_update(message);
		}
	}

	private void _add(Message message) throws PortalException, SystemException {
		String type = message.getString("type");
		long typeId = message.getLong("typeId");

		if (type.equals(DLSyncConstants.TYPE_FILE)) {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(typeId);

			String checksum = StringPool.BLANK;

			try {
				DLFileEntry dlFileEntry = (DLFileEntry)fileEntry.getModel();

				InputStream inputStream = DLStoreUtil.getFileAsStream(
					dlFileEntry.getCompanyId(), dlFileEntry.getRepositoryId(),
					dlFileEntry.getName(), dlFileEntry.getVersion());

				checksum = DigesterUtil.digestBase64(inputStream);
			}
			catch (PortalException pe) {
			}
			catch (SystemException e) {
			}

			long lockUserId = 0;
			String lockUserName = StringPool.BLANK;

			Lock lock = fileEntry.getLock();

			if (lock != null) {
				lockUserId = lock.getUserId();
				lockUserName = lock.getUserName();
			}

			if (lock != null) {
				lockUserId = lock.getUserId();
				lockUserName = lock.getUserName();
			}

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				fileEntry.getFileEntryId(), fileEntry.getUuid(),
				fileEntry.getCompanyId(), fileEntry.getRepositoryId(),
				lockUserId, fileEntry.getFolderId(), fileEntry.getTitle(),
				fileEntry.getDescription(), checksum, fileEntry.getSize(),
				DLSyncConstants.TYPE_FILE, lockUserName,
				fileEntry.getVersion());
		}
		else if (type.equals(DLSyncConstants.TYPE_FOLDER)) {
			Folder folder = DLAppLocalServiceUtil.getFolder(typeId);

			SyncDLObjectLocalServiceUtil.addSyncDLObject(
				folder.getFolderId(), folder.getUuid(), folder.getCompanyId(),
				folder.getRepositoryId(), -1, folder.getParentFolderId(),
				folder.getName(), folder.getDescription(), StringPool.BLANK, 0,
				DLSyncConstants.TYPE_FOLDER, StringPool.BLANK, "-1");
		}
	}

	private void _delete(Message message)
		throws PortalException, SystemException {

		String type = message.getString("type");
		long typeId = message.getLong("typeId");

		if (type.equals(DLSyncConstants.TYPE_FILE)) {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(typeId);

			SyncDLObjectLocalServiceUtil.updateSyncDLObject(
				fileEntry.getFileEntryId(), 0, fileEntry.getFolderId(),
				fileEntry.getTitle(), StringPool.BLANK,
				fileEntry.getDescription(), DLSyncConstants.EVENT_DELETE,
				StringPool.BLANK, fileEntry.getSize(), fileEntry.getVersion());
		}
		else if (type.equals(DLSyncConstants.TYPE_FOLDER)) {
			Folder folder = DLAppLocalServiceUtil.getFolder(typeId);

			SyncDLObjectLocalServiceUtil.updateSyncDLObject(
				folder.getFolderId(), -1, folder.getParentFolderId(),
				folder.getName(), StringPool.BLANK, folder.getDescription(),
				DLSyncConstants.EVENT_DELETE, StringPool.BLANK, 0, "-1");
		}
	}

	private void _update(Message message)
		throws PortalException, SystemException {

		String type = message.getString("type");
		long typeId = message.getLong("typeId");

		if (type.equals(DLSyncConstants.TYPE_FILE)) {
			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(typeId);

			String checksum = StringPool.BLANK;

			try {
				DLFileEntry dlFileEntry = (DLFileEntry)fileEntry.getModel();

				InputStream inputStream = DLStoreUtil.getFileAsStream(
					dlFileEntry.getCompanyId(), dlFileEntry.getRepositoryId(),
					dlFileEntry.getName(), dlFileEntry.getVersion());

				checksum = DigesterUtil.digestBase64(inputStream);
			}
			catch (PortalException pe) {
			}

			long lockUserId = 0;
			String lockUserName = StringPool.BLANK;

			Lock lock = fileEntry.getLock();

			if (lock != null) {
				lockUserId = lock.getUserId();
				lockUserName = lock.getUserName();
			}

			if (lock != null) {
				lockUserId = lock.getUserId();
				lockUserName = lock.getUserName();
			}

			SyncDLObjectLocalServiceUtil.updateSyncDLObject(
				fileEntry.getFileEntryId(), lockUserId, fileEntry.getFolderId(),
				fileEntry.getTitle(), fileEntry.getDescription(), checksum,
				DLSyncConstants.EVENT_UPDATE, lockUserName, fileEntry.getSize(),
				fileEntry.getVersion());
		}
		else if (type.equals(DLSyncConstants.TYPE_FOLDER)) {
			Folder folder = DLAppLocalServiceUtil.getFolder(typeId);

			SyncDLObjectLocalServiceUtil.updateSyncDLObject(
				folder.getFolderId(), -1, folder.getParentFolderId(),
				folder.getName(), folder.getDescription(), StringPool.BLANK,
				DLSyncConstants.EVENT_UPDATE, StringPool.BLANK, 0, "-1");
		}
	}

}