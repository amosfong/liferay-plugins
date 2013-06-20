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
import com.liferay.portal.kernel.io.delta.ByteChannelReader;
import com.liferay.portal.kernel.io.delta.ByteChannelWriter;
import com.liferay.portal.kernel.io.delta.DeltaUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.sync.model.SyncContext;
import com.liferay.sync.model.SyncDLObject;
import com.liferay.sync.model.SyncDLObjectUpdate;
import com.liferay.sync.service.base.SyncDLObjectServiceBaseImpl;
import com.liferay.sync.util.SyncUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class SyncDLObjectServiceImpl extends SyncDLObjectServiceBaseImpl {

	@Override
	public SyncDLObject addFileEntry(
			long repositoryId, long folderId, String sourceFileName,
			String mimeType, String title, String description, String changeLog,
			File file, ServiceContext serviceContext)
		throws PortalException, SystemException {

		FileEntry fileEntry = dlAppService.addFileEntry(
			repositoryId, folderId, sourceFileName, mimeType, title,
			description, changeLog, file, serviceContext);

		return SyncUtil.toSyncDLObject(fileEntry);
	}

	@Override
	public SyncDLObject addFolder(
			long repositoryId, long parentFolderId, String name,
			String description, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Folder folder = dlAppService.addFolder(
			repositoryId, parentFolderId, name, description, serviceContext);

		return SyncUtil.toSyncDLObject(folder);
	}

	@Override
	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		dlAppService.deleteFileEntry(fileEntryId);
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		dlAppService.deleteFolder(folderId);
	}

	@Override
	public InputStream getFileDeltaAsStream(
			long fileEntryId, String sourceVersion, String destinationVersion)
		throws PortalException, SystemException {

		InputStream deltaInputStream = null;

		FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

		InputStream sourceInputStream = null;
		File sourceFile = FileUtil.createTempFile();
		FileInputStream sourceFileInputStream = null;
		FileChannel sourceFileChannel = null;
		File checksumsFile = FileUtil.createTempFile();
		OutputStream checksumsOutputStream = null;
		WritableByteChannel checksumsWritableByteChannel = null;

		try {
			sourceInputStream = fileEntry.getContentStream(sourceVersion);

			FileUtil.write(sourceFile, sourceInputStream);

			sourceFileInputStream = new FileInputStream(sourceFile);

			sourceFileChannel = sourceFileInputStream.getChannel();

			checksumsOutputStream = new FileOutputStream(checksumsFile);

			checksumsWritableByteChannel = Channels.newChannel(
				checksumsOutputStream);

			ByteChannelWriter checksumsByteChannelWriter =
				new ByteChannelWriter(checksumsWritableByteChannel);

			DeltaUtil.checksums(sourceFileChannel, checksumsByteChannelWriter);

			checksumsByteChannelWriter.finish();
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(sourceFileInputStream);
			StreamUtil.cleanUp(sourceFileChannel);
			StreamUtil.cleanUp(checksumsOutputStream);
			StreamUtil.cleanUp(checksumsWritableByteChannel);

			FileUtil.delete(sourceFile);
		}

		InputStream destinationInputStream = null;
		ReadableByteChannel destinationReadableByteChannel = null;
		InputStream checksumsInputStream = null;
		ReadableByteChannel checksumsReadableByteChannel = null;
		OutputStream deltaOutputStream = null;
		WritableByteChannel deltaOutputStreamWritableByteChannel = null;

		try {
			destinationInputStream = fileEntry.getContentStream(
				destinationVersion);

			destinationReadableByteChannel = Channels.newChannel(
				destinationInputStream);

			checksumsInputStream = new FileInputStream(checksumsFile);

			checksumsReadableByteChannel = Channels.newChannel(
				checksumsInputStream);

			ByteChannelReader checksumsByteChannelReader =
				new ByteChannelReader(checksumsReadableByteChannel);

			File deltaFile = FileUtil.createTempFile();

			deltaOutputStream = new FileOutputStream(deltaFile);

			deltaOutputStreamWritableByteChannel = Channels.newChannel(
				deltaOutputStream);

			ByteChannelWriter deltaByteChannelWriter = new ByteChannelWriter(
				deltaOutputStreamWritableByteChannel);

			DeltaUtil.delta(
				destinationReadableByteChannel, checksumsByteChannelReader,
				deltaByteChannelWriter);

			deltaByteChannelWriter.finish();

			deltaInputStream = new FileInputStream(deltaFile);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(destinationInputStream);
			StreamUtil.cleanUp(destinationReadableByteChannel);
			StreamUtil.cleanUp(checksumsInputStream);
			StreamUtil.cleanUp(checksumsReadableByteChannel);
			StreamUtil.cleanUp(deltaOutputStream);
			StreamUtil.cleanUp(deltaOutputStreamWritableByteChannel);

			FileUtil.delete(checksumsFile);
		}

		return deltaInputStream;
	}

	@Override
	public SyncDLObject getFileEntrySyncDLObject(
			long groupId, long folderId, String title)
		throws PortalException, SystemException {

		FileEntry fileEntry = dlAppService.getFileEntry(
			groupId, folderId, title);

		return SyncUtil.toSyncDLObject(fileEntry);
	}

	@Override
	public List<SyncDLObject> getFileEntrySyncDLObjects(
			long repositoryId, long folderId)
		throws PortalException, SystemException {

		List<FileEntry> fileEntries = dlAppService.getFileEntries(
			repositoryId, folderId);

		List<SyncDLObject> syncDLObjects = new ArrayList<SyncDLObject>(
			fileEntries.size());

		for (FileEntry fileEntry : fileEntries) {
			SyncDLObject syncDLObject = SyncUtil.toSyncDLObject(fileEntry);

			syncDLObjects.add(syncDLObject);
		}

		return syncDLObjects;
	}

	@Override
	public SyncDLObject getFolderSyncDLObject(long folderId)
		throws PortalException, SystemException {

		Folder folder = dlAppService.getFolder(folderId);

		return SyncUtil.toSyncDLObject(folder);
	}

	@Override
	public List<SyncDLObject> getFolderSyncDLObjects(
			long repositoryId, long parentFolderId)
		throws PortalException, SystemException {

		List<Folder> folders = dlAppService.getFolders(
			repositoryId, parentFolderId);

		List<SyncDLObject> syncDLObjects = new ArrayList<SyncDLObject>(
			folders.size());

		for (Folder folder : folders) {
			SyncDLObject syncDLObject = SyncUtil.toSyncDLObject(folder);

			syncDLObjects.add(syncDLObject);
		}

		return syncDLObjects;
	}

	@Override
	public SyncContext getSyncContext()
		throws PortalException, SystemException {

		SyncContext syncContext = new SyncContext();

		syncContext.setPortalBuildNumber(ReleaseInfo.getBuildNumber());
		syncContext.setUserId(getUserId());
		syncContext.setUserSites(syncDLObjectService.getUserSites());

		return syncContext;
	}

	@Override
	public SyncDLObjectUpdate getSyncDLObjectUpdate(
			long companyId, long repositoryId, long lastAccessDate)
		throws PortalException, SystemException {

		repositoryService.checkRepository(repositoryId);

		List<SyncDLObject> syncDLObjects = syncDLObjectFinder.filterFindByC_M_R(
			companyId, lastAccessDate, repositoryId);

		for (SyncDLObject syncDLObject : syncDLObjects) {
			if (syncDLObject.getModifiedDate() > lastAccessDate) {
				lastAccessDate = syncDLObject.getModifiedDate();
			}
		}

		return new SyncDLObjectUpdate(syncDLObjects, lastAccessDate);
	}

	@Override
	public List<Group> getUserSites() throws PortalException, SystemException {
		return groupService.getUserSites();
	}

	@Override
	public SyncDLObject moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		FileEntry fileEntry = dlAppService.moveFileEntry(
			fileEntryId, newFolderId, serviceContext);

		return SyncUtil.toSyncDLObject(fileEntry);
	}

	@Override
	public SyncDLObject moveFolder(
			long folderId, long parentFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Folder folder = dlAppService.moveFolder(
			folderId, parentFolderId, serviceContext);

		return SyncUtil.toSyncDLObject(folder);
	}

	@Override
	public SyncDLObject updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, File file, ServiceContext serviceContext)
		throws PortalException, SystemException {

		FileEntry fileEntry = dlAppService.updateFileEntry(
			fileEntryId, sourceFileName, mimeType, title, description,
			changeLog, majorVersion, file, serviceContext);

		return SyncUtil.toSyncDLObject(fileEntry);
	}

	@Override
	public SyncDLObject updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream deltaInputStream, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		FileEntry fileEntry = dlAppLocalService.getFileEntry(fileEntryId);

		InputStream originalInputStream = null;
		File patchedFile = null;
		InputStream patchedInputStream = null;

		try {
			originalInputStream = fileEntry.getContentStream();

			patchedFile = FileUtil.createTempFile();

			patchFile(originalInputStream, deltaInputStream, patchedFile);

			patchedInputStream = new FileInputStream(patchedFile);

			fileEntry = dlAppService.updateFileEntry(
				fileEntryId, sourceFileName, mimeType, title, description,
				changeLog, majorVersion, patchedInputStream, size,
				serviceContext);

			return SyncUtil.toSyncDLObject(fileEntry);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(originalInputStream);
			StreamUtil.cleanUp(patchedInputStream);

			FileUtil.delete(patchedFile);
		}
	}

	@Override
	public SyncDLObject updateFolder(
			long folderId, String name, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Folder folder = dlAppService.updateFolder(
			folderId, name, description, serviceContext);

		return SyncUtil.toSyncDLObject(folder);
	}

	protected void patchFile(
			InputStream originalInputStream, InputStream deltaInputStream,
			File patchedFile)
		throws PortalException {

		File originalFile = null;
		FileInputStream originalFileInputStream = null;
		FileChannel originalFileChannel = null;
		FileOutputStream patchedFileOutputStream = null;
		WritableByteChannel patchedWritableByteChannel = null;
		ReadableByteChannel deltaReadableByteChannel = null;

		try {
			originalFile = FileUtil.createTempFile();

			FileUtil.write(originalFile, originalInputStream);

			originalFileInputStream = new FileInputStream(originalFile);

			originalFileChannel = originalFileInputStream.getChannel();

			patchedFileOutputStream = new FileOutputStream(patchedFile);

			patchedWritableByteChannel = Channels.newChannel(
				patchedFileOutputStream);

			deltaReadableByteChannel = Channels.newChannel(deltaInputStream);

			ByteChannelReader deltaByteChannelReader = new ByteChannelReader(
				deltaReadableByteChannel);

			DeltaUtil.patch(
				originalFileChannel, patchedWritableByteChannel,
				deltaByteChannelReader);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
		finally {
			StreamUtil.cleanUp(originalFileInputStream);
			StreamUtil.cleanUp(originalFileChannel);
			StreamUtil.cleanUp(patchedFileOutputStream);
			StreamUtil.cleanUp(patchedWritableByteChannel);
			StreamUtil.cleanUp(deltaReadableByteChannel);

			FileUtil.delete(originalFile);
		}
	}

}