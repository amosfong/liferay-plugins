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

package com.liferay.documentum.repository;

import com.documentum.com.DfClientX;
import com.documentum.fc.client.DfAuthenticationException;
import com.documentum.fc.client.DfTypedObjectException;
import com.documentum.fc.client.IDfClient;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfFolder;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSessionManager;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfType;
import com.documentum.fc.client.IDfTypedObject;
import com.documentum.fc.client.IDfVersionLabels;
import com.documentum.fc.client.IDfVersionTreeLabels;
import com.documentum.fc.client.IDfVirtualDocument;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfAttr;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfList;
import com.documentum.fc.common.IDfLoginInfo;
import com.documentum.fc.common.IDfTime;
import com.documentum.fc.common.IDfValue;
import com.documentum.operations.IDfCancelCheckoutOperation;
import com.documentum.operations.IDfCheckinNode;
import com.documentum.operations.IDfCheckinOperation;
import com.documentum.operations.IDfCheckoutNode;
import com.documentum.operations.IDfCheckoutOperation;
import com.documentum.operations.IDfCopyOperation;
import com.documentum.operations.IDfOperationError;

import com.liferay.documentum.repository.model.Constants;
import com.liferay.documentum.repository.model.DocumentumFileEntry;
import com.liferay.documentum.repository.model.DocumentumFolder;
import com.liferay.documentum.repository.model.DocumentumLock;
import com.liferay.documentum.repository.model.DocumentumVersionLabel;
import com.liferay.documentum.repository.search.DQLQueryBuilder;
import com.liferay.portal.DuplicateLockException;
import com.liferay.portal.InvalidRepositoryException;
import com.liferay.portal.NoSuchRepositoryEntryException;
import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.BaseRepositoryImpl;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.HitsImpl;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.SystemProperties;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.model.User;
import com.liferay.portal.repository.proxy.FileEntryProxyBean;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.DuplicateFolderNameException;
import com.liferay.portlet.documentlibrary.FileExtensionException;
import com.liferay.portlet.documentlibrary.FileNameException;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.NoSuchFileVersionException;
import com.liferay.portlet.documentlibrary.NoSuchFolderException;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.persistence.DLFolderUtil;
import com.liferay.util.PwdGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

/**
 * @author Mika Koivisto
 */
public class DocumentumRepository extends BaseRepositoryImpl {

	@Override
	public FileEntry addFileEntry(
			long folderId, String sourceFileName, String mimeType, String title,
			String description, String changeLog, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (Validator.isNull(title)) {
			if (size == 0) {
				throw new FileNameException();
			}
			else {
				title = sourceFileName;
			}
		}

		IDfSession idfSession = null;

		File file = null;

		try {
			idfSession = getIdfSession();

			validateTitle(idfSession, folderId, title);

			IDfDocument idfDocument = (IDfDocument)idfSession.newObject(
				Constants.DM_DOCUMENT);

			String contentType = getContentType(
				idfSession, mimeType, sourceFileName, title);

			if (Validator.isNull(contentType)) {
				throw new FileExtensionException(
					"Unsupported file type " + title);
			}

			idfDocument.setContentType(contentType);

			idfDocument.setLogEntry(changeLog);
			idfDocument.setObjectName(title);
			idfDocument.setTitle(description);

			IDfId idfId = toFolderObjectId(idfSession, folderId);

			IDfFolder idfFolder = (IDfFolder)idfSession.getObject(idfId);

			idfDocument.link(idfFolder.getFolderPath(0));

			updateProperties(idfDocument, serviceContext);

			StringBundler sb = new StringBundler(5);

			sb.append(SystemProperties.get(SystemProperties.TMP_DIR));
			sb.append("/liferay/documentum/");
			sb.append(PwdGenerator.getPassword());
			sb.append(StringPool.UNDERLINE);
			sb.append(title);

			String fileName = sb.toString();

			file = new File(fileName);

			FileUtil.write(file, is);

			idfDocument.setFile(fileName);

			idfDocument.save();

			return toFileEntry(idfDocument);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		catch (IOException ioe) {
			throw new RepositoryException(ioe);
		}
		finally {
			releaseSession(idfSession);

			if ((file != null) && file.exists()) {
				file.delete();
			}
		}
	}

	@Override
	public Folder addFolder(
			long parentFolderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			validateTitle(idfSession, parentFolderId, title);

			IDfFolder idfFolder = (IDfFolder)idfSession.newObject(
				Constants.DM_FOLDER);

			idfFolder.setObjectName(title);
			idfFolder.setTitle(description);

			IDfId idfId = toFolderObjectId(idfSession, parentFolderId);

			IDfFolder idfParentFolder = (IDfFolder)idfSession.getObject(idfId);

			idfFolder.link(idfParentFolder.getFolderPath(0));

			idfFolder.save();

			return toFolder(idfFolder);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public FileVersion cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(idfId);

			cancelCheckOut(idfDocument);

			return toFileEntry(idfDocument).getFileVersion();
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		clearManualCheckInRequired(fileEntryId, serviceContext);

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(idfId);

			checkinFileEntry(idfDocument, true, major);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public void checkInFileEntry(
		long fileEntryId, String lockUuid, ServiceContext serviceContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		setManualCheckInRequired(fileEntryId, serviceContext);

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(idfId);

			checkoutFileEntry(idfDocument, false);

			return getFileEntry(idfSession, fileEntryId);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public FileEntry checkOutFileEntry(
		long fileEntryId, String owner, long expirationTime,
		ServiceContext serviceContext) {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry copyFileEntry(
			long groupId, long fileEntryId, long destFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId fileEntryIDfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(
				fileEntryIDfId);

			validateTitle(
				idfSession, destFolderId, idfDocument.getObjectName());

			IDfCopyOperation idfCopyOperation = _dfClientX.getCopyOperation();

			IDfId destFolderIDfId = toFolderObjectId(idfSession, destFolderId);

			idfCopyOperation.setDestinationFolderId(destFolderIDfId);

			idfCopyOperation.add(idfDocument);

			if (!idfCopyOperation.execute()) {
				IDfList idfList = idfCopyOperation.getErrors();

				IDfOperationError idfOperationError =
					(IDfOperationError)idfList.get(0);

				throw new PrincipalException(idfOperationError.getMessage());
			}

			IDfList idfList = idfCopyOperation.getNewObjects();

			IDfId newFileEntryIDfId = (IDfId)idfList.get(0);

			return toFileEntry(
				(IDfDocument)idfSession.getObject(newFileEntryIDfId));
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(idfId);

			deleteMappedEntry(idfSession, idfDocument);

			idfDocument.destroyAllVersions();
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = toFolderObjectId(idfSession, folderId);

			IDfFolder idfFolder = (IDfFolder)idfSession.getObject(idfId);

			deleteFolder(idfSession, idfFolder);
		}
		catch (Exception e) {
			if (e instanceof DfException) {
				DfException dfe = (DfException)e;

				processException(dfe);
			}

			throw new RepositoryException(e);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	public Map<String, Serializable> getAttributes(long fileEntryId)
		throws SystemException {

		IDfSession idfSession = null;

		try {
			Map<String, Serializable> attributes =
				new HashMap<String, Serializable>();

			idfSession = getIdfSession();

			IDfId idfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(idfId);

			for (int i = 0; i < idfDocument.getAttrCount(); i++) {
				IDfAttr idfAttr = idfDocument.getAttr(i);

				String name = idfAttr.getName();

				if (!idfAttr.isRepeating()) {
					IDfValue idfValue = idfDocument.getValue(name);

					attributes.put(name, getAttributeValue(idfValue));

					continue;
				}

				List<Serializable> values = new ArrayList<Serializable>();

				int j = 0;

				IDfValue idfValue = idfDocument.getRepeatingValue(name, j++);

				while (idfValue != null) {
					Serializable value = getAttributeValue(idfValue);

					values.add(value);

					try {
						idfValue = idfDocument.getRepeatingValue(name, j++);
					}
					catch (DfTypedObjectException dtoe) {
						idfValue = null;
					}
				}

				attributes.put(name, (Serializable)values);
			}

			return attributes;
		}
		catch (DfException dfe) {
			throw new RepositoryException(dfe);
		}
		catch (PortalException pe) {
			throw new RepositoryException(pe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	public InputStream getContentStream(long fileEntryId)
		throws SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(idfId);

			return idfDocument.getContent();
		}
		catch (DfException dfe) {
			throw new RepositoryException(dfe);
		}
		catch (PortalException pe) {
			throw new RepositoryException(pe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return getFileEntries(folderId, null, start, end, obc);
	}

	@Override
	public List<FileEntry> getFileEntries(
		long folderId, long documentTypeId, int start, int end,
		OrderByComparator obc) {

		return new ArrayList<FileEntry>();
	}

	@Override
	public List<FileEntry> getFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			String[] contentTypes = toContentTypes(idfSession, mimeTypes);

			String queryString =
				DQLQueryBuilder.buildFileEntriesSelectQueryString(
					this, folderId, contentTypes, obc);

			return (List<FileEntry>)getFoldersOrFileEntries(
				idfSession, queryString, start, end);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public int getFileEntriesCount(long folderId) throws SystemException {
		return getFileEntriesCount(folderId, null);
	}

	@Override
	public int getFileEntriesCount(long folderId, long documentTypeId) {
		return 0;
	}

	@Override
	public int getFileEntriesCount(long folderId, String[] mimeTypes)
		throws SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			String[] contentTypes = toContentTypes(idfSession, mimeTypes);

			String queryString =
				DQLQueryBuilder.buildFileEntriesCountQueryString(
					this, folderId, contentTypes);

			return getFoldersOrFileEntriesCount(idfSession, queryString);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public FileEntry getFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			return getFileEntry(idfSession, fileEntryId);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public FileEntry getFileEntry(long folderId, String title)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = getObjectId(idfSession, folderId, true, title);

			if (idfId != null) {
				IDfDocument idfDocument = (IDfDocument)idfSession.getObject(
					idfId);

				return toFileEntry(idfDocument);
			}
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}

		throw new NoSuchFileEntryException(
			"No Documentum file entry with {folderId=" + folderId + ", title=" +
				title + "}");
	}

	@Override
	public FileEntry getFileEntryByUuid(String uuid)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.findByUUID_G(
			uuid, getGroupId());

		if (repositoryEntry == null) {
			throw new NoSuchFileEntryException(
				"No Documentum file entry with {uuid=" + uuid + "}");
		}

		return getFileEntry(repositoryEntry.getRepositoryEntryId());
	}

	@Override
	public FileVersion getFileVersion(long fileVersionId)
		throws PortalException, SystemException {

		FileEntry fileEntry = getFileEntry(fileVersionId);

		return fileEntry.getFileVersion();
	}

	@Override
	public Folder getFolder(long folderId)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			return toFolder(getIdfFolder(idfSession, folderId));
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public Folder getFolder(long parentFolderId, String title)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = getObjectId(idfSession, parentFolderId, false, title);

			if (idfId != null) {
				IDfFolder idfFolder = (IDfFolder)idfSession.getObject(idfId);

				return toFolder(idfFolder);
			}
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}

		throw new NoSuchFolderException(
			"No Documentum folder with {parentFolderId=" + parentFolderId +
				", title=" + title + "}");
	}

	@Override
	public List<Folder> getFolders(
			long parentFolderId, boolean includeMountFolders, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			String queryString = DQLQueryBuilder.buildFoldersSelectQueryString(
				this, parentFolderId, obc);

			return (List<Folder>)getFoldersOrFileEntries(
				idfSession, queryString, start, end);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return getFoldersAndFileEntries(folderId, null, start, end, obc);
	}

	@Override
	public List<Object> getFoldersAndFileEntries(
			long folderId, String[] mimeTypes, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			String[] contentTypes = toContentTypes(idfSession, mimeTypes);

			String queryString =
				DQLQueryBuilder.buildFoldersAndFileEntriesSelectQueryString(
					this, folderId, contentTypes, obc);

			return (List<Object>)getFoldersOrFileEntries(
				idfSession, queryString, start, end);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId)
		throws SystemException {

		return getFoldersAndFileEntriesCount(folderId, null);
	}

	@Override
	public int getFoldersAndFileEntriesCount(long folderId, String[] mimeTypes)
		throws SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			String[] contentTypes = toContentTypes(idfSession, mimeTypes);

			String queryString =
				DQLQueryBuilder.buildFoldersAndFileEntriesCountQueryString(
					this, folderId, contentTypes);

			return getFoldersOrFileEntriesCount(idfSession, queryString);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public int getFoldersCount(long parentFolderId, boolean includeMountfolders)
		throws SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			String queryString = DQLQueryBuilder.buildFoldersCountQueryString(
				this, parentFolderId);

			return getFoldersOrFileEntriesCount(idfSession, queryString);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public int getFoldersFileEntriesCount(List<Long> folderIds, int status)
		throws SystemException {

		int count = 0;

		for (long folderId : folderIds) {
			count += getFoldersAndFileEntriesCount(folderId);
		}

		return count;
	}

	@Override
	public List<Folder> getMountFolders(
		long parentFolderId, int start, int end, OrderByComparator obc) {

		return new ArrayList<Folder>();
	}

	@Override
	public int getMountFoldersCount(long parentFolderId) {
		return 0;
	}

	@Override
	public void getSubfolderIds(List<Long> folderIds, long folderId)
		throws SystemException {

		List<Folder> subfolders = getFolders(
			folderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		getSubfolderIds(folderIds, subfolders, true);
	}

	@Override
	public List<Long> getSubfolderIds(long folderId, boolean recurse)
		throws SystemException {

		List<Long> subfolderIds = new ArrayList<Long>();

		List<Folder> subfolders = getFolders(
			folderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		getSubfolderIds(subfolderIds, subfolders, recurse);

		return subfolderIds;
	}

	@Override
	public String[] getSupportedConfigurations() {
		return _SUPPORTED_CONFIGURATIONS;
	}

	@Override
	public String[][] getSupportedParameters() {
		return _SUPPORTED_PARAMETERS;
	}

	@Override
	public void initRepository() throws PortalException, SystemException {
		UnicodeProperties unicodeProperties = getTypeSettingsProperties();

		_cabinet = unicodeProperties.getProperty(_CABINET);

		if (Validator.isNull(_cabinet)) {
			throw new InvalidRepositoryException();
		}

		_repository = unicodeProperties.getProperty(_REPOSITORY);

		if (Validator.isNull(_repository)) {
			throw new InvalidRepositoryException();
		}

		_dfClientX = new DfClientX();

		getIdfSessionManager();
	}

	@Override
	public Lock lockFolder(long folderId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Lock lockFolder(
		long folderId, String owner, boolean inheritable, long expirationTime) {

		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId fileEntryIDfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(
				fileEntryIDfId);

			validateTitle(idfSession, newFolderId, idfDocument.getObjectName());

			IDfFolder oldIDfFolder = (IDfFolder)idfSession.getObject(
				idfDocument.getFolderId(0));

			idfDocument.unlink(oldIDfFolder.getFolderPath(0));

			IDfId newFolderIDfId = toFolderObjectId(idfSession, newFolderId);

			IDfFolder newIDfFolder = (IDfFolder)idfSession.getObject(
				newFolderIDfId);

			idfDocument.link(newIDfFolder.getFolderPath(0));

			idfDocument.save();

			return toFileEntry(idfDocument);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public Folder moveFolder(
			long folderId, long newParentFolderId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId folderIDfId = toFolderObjectId(idfSession, folderId);

			IDfId newParentFolderIDfId = toFolderObjectId(
				idfSession, newParentFolderId);

			IDfFolder idfFolder = (IDfFolder)idfSession.getObject(folderIDfId);

			validateTitle(
				idfSession, newParentFolderId, idfFolder.getObjectName());

			IDfId oldParentFolderIDfId = toObjectId(
				idfSession, idfFolder.getAncestorId(1));

			IDfFolder oldParentIDfFolder = (IDfFolder)idfSession.getObject(
				oldParentFolderIDfId);

			idfFolder.unlink(oldParentIDfFolder.getFolderPath(0));

			IDfFolder newParentIDfFolder = (IDfFolder)idfSession.getObject(
				newParentFolderIDfId);

			idfFolder.link(newParentIDfFolder.getFolderPath(0));

			idfFolder.save();

			return toFolder(idfFolder);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	public Lock refreshFileEntryLock(String lockUuid, long expirationTime) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Lock refreshFileEntryLock(
		String lockUuid, long companyId, long expirationTime) {

		throw new UnsupportedOperationException();
	}

	public Lock refreshFolderLock(String lockUuid, long expirationTime) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Lock refreshFolderLock(
		String lockUuid, long companyId, long expirationTime) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		File file = null;

		try {
			idfSession = getIdfSession();

			IDfId fileEntryIDfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(
				fileEntryIDfId);

			IDfId chronicleIdIDfId = idfDocument.getChronicleId();

			IDfVersionTreeLabels idfVersionTreeLabels =
				idfSession.getVersionTreeLabels(chronicleIdIDfId);

			int versionCount = idfVersionTreeLabels.getVersionCount();

			IDfId versionIDfId = null;

			for (int i = 0; i < versionCount; i++) {
				IDfVersionLabels idfVersionLabels =
					idfVersionTreeLabels.getVersion(i);

				String versionLabel = idfVersionLabels.getVersionLabel(0);

				if (versionLabel.equals(version)) {
					versionIDfId = idfVersionLabels.getObjectId();
				}
			}

			if (versionIDfId == null) {
				throw new NoSuchFileVersionException();
			}

			IDfDocument versionIDfDocument = (IDfDocument)idfSession.getObject(
				versionIDfId);

			String versionIDfDocumentObjectName = idfDocument.getObjectName();

			if (!versionIDfDocumentObjectName.equals(
					idfDocument.getObjectName())) {

				FileEntry fileEntry = toFileEntry(versionIDfDocument);

				validateTitle(
					idfSession, fileEntry.getFolderId(),
					versionIDfDocumentObjectName);
			}

			file = checkoutFileEntry(idfDocument, true);

			idfDocument.setContentType(versionIDfDocument.getContentType());
			idfDocument.setFile(file.getAbsolutePath());
			idfDocument.setLogEntry("Reverted to " + version);
			idfDocument.setObjectName(versionIDfDocumentObjectName);
			idfDocument.setTitle(versionIDfDocument.getTitle());

			checkinFileEntry(idfDocument, true, false);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);

			if ((file != null) && file.exists()) {
				file.delete();
			}
		}
	}

	@Override
	public Hits search(long creatorUserId, int status, int start, int end) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Hits search(
		long creatorUserId, long folderId, String[] mimeTypes, int status,
		int start, int end) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		IDfSession idfSession = null;
		IDfCollection idfCollection = null;

		try {
			long startTime = System.currentTimeMillis();

			idfSession = getIdfSession();

			String searchCountQueryString =
				DQLQueryBuilder.buildSearchCountQueryString(
					this, searchContext, query);

			IDfQuery idfQuery = _dfClientX.getQuery();

			idfQuery.setDQL(searchCountQueryString);

			if (_log.isDebugEnabled()) {
				_log.debug("Executing query: " + searchCountQueryString);
			}

			idfCollection = idfQuery.execute(
				idfSession, IDfQuery.DF_READ_QUERY);

			long total = 0;

			if (idfCollection.next()) {
				total = idfCollection.getLong("num_hits");
			}

			idfCollection.close();

			int start = searchContext.getStart();
			int end = searchContext.getEnd();

			if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS)) {
				start = 0;
				end = (int)total;
			}
			else if (end > total) {
				end = (int)total;
			}

			int subsetTotal = end - start;

			com.liferay.portal.kernel.search.Document[] documents =
				new DocumentImpl[subsetTotal];
			String[] snippets = new String[subsetTotal];
			float[] scores = new float[subsetTotal];

			if (total > 0) {
				String searchSelectQueryString =
					DQLQueryBuilder.buildSearchSelectQueryString(
						this, searchContext, query);

				idfQuery.setDQL(searchSelectQueryString);

				if (_log.isDebugEnabled()) {
					_log.debug("Executing query: " + searchSelectQueryString);
				}

				idfCollection = idfQuery.execute(
					idfSession, IDfQuery.DF_READ_QUERY);

				for (int i = 0; i < start && i < total; i++) {
					idfCollection.next();
				}

				int index = 0;

				for (int i = start; (i < end) && idfCollection.next(); i++) {
					com.liferay.portal.kernel.search.Document document =
						new DocumentImpl();

					IDfId idfId = idfCollection.getId(Constants.R_OBJECT_ID);

					IDfDocument idfDocument = (IDfDocument)idfSession.getObject(
						idfId);

					if (_log.isTraceEnabled()) {
						_log.trace(idfDocument.dump());
					}

					FileEntry fileEntry = toFileEntry(idfDocument);

					document.addKeyword(
						Field.ENTRY_CLASS_NAME, fileEntry.getModelClassName());
					document.addKeyword(
						Field.ENTRY_CLASS_PK, fileEntry.getFileEntryId());
					document.addKeyword(Field.TITLE, fileEntry.getTitle());

					documents[index] = document;

					scores[index] = 1;

					snippets[index] = StringPool.BLANK;

					index++;
				}
			}

			float searchTime =
				(float)(System.currentTimeMillis() - startTime) / Time.SECOND;

			Hits hits = new HitsImpl();

			hits.setDocs(documents);
			hits.setLength((int)total);
			hits.setQuery(query);
			hits.setQueryTerms(new String[0]);
			hits.setScores(scores);
			hits.setSearchTime(searchTime);
			hits.setSnippets(snippets);
			hits.setStart(startTime);

			return hits;
		}
		catch (Exception e) {
			if (e instanceof DfException) {
				try {
					DfException dfe = (DfException)e;

					processException(dfe);
				}
				catch (PortalException pe) {
					throw new SearchException(pe);
				}
			}

			throw new SearchException(e);
		}
		finally {
			if (idfCollection != null) {
				try {
					idfCollection.close();
				}
				catch (DfException dfe) {
				}
			}

			releaseSession(idfSession);
		}
	}

	public String toFolderObjectId(long folderId)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfFolderId = toFolderObjectId(idfSession, folderId);

			return idfFolderId.getId();
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public void unlockFolder(long folderId, String lockUuid) {
		throw new UnsupportedOperationException();
	}

	@Override
	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		File file = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = toFileEntryObjectId(idfSession, fileEntryId);

			IDfDocument idfDocument = (IDfDocument)idfSession.getObject(idfId);

			if (Validator.isNull(title)) {
				title = sourceFileName;

				if (Validator.isNull(title)) {
					title = idfDocument.getObjectName();
				}
			}

			if (!title.equals(idfDocument.getObjectName())) {
				FileEntry fileEntry = toFileEntry(idfDocument);

				validateTitle(idfSession, fileEntry.getFolderId(), title);
			}

			FileEntry fileEntry = toFileEntry(idfDocument);

			boolean autoCheckin = !fileEntry.isCheckedOut();

			if (!fileEntry.hasLock()) {
				checkoutFileEntry(idfDocument, false);
			}

			idfDocument.setLogEntry(changeLog);
			idfDocument.setObjectName(title);
			idfDocument.setTitle(description);

			updateProperties(idfDocument, serviceContext);

			if (is != null) {
				StringBundler sb = new StringBundler(5);

				sb.append(SystemProperties.get(SystemProperties.TMP_DIR));
				sb.append("/liferay/documentum/");
				sb.append(PwdGenerator.getPassword());
				sb.append(StringPool.UNDERLINE);
				sb.append(sourceFileName);

				String fileName = sb.toString();

				file = new File(fileName);

				FileUtil.write(file, is);

				String contentType = getContentType(
					idfSession, mimeType, sourceFileName, title);

				if (Validator.isNull(contentType)) {
					throw new FileExtensionException(
						"Unsupported file type " + title);
				}

				idfDocument.setContentType(contentType);

				idfDocument.setFile(fileName);
			}

			if (autoCheckin) {
				idfDocument = checkinFileEntry(idfDocument, true, majorVersion);
			}
			else {
				idfDocument.saveLock();
			}

			return toFileEntry(idfDocument);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		catch (IOException ioe) {
			throw new RepositoryException(ioe);
		}
		finally {
			releaseSession(idfSession);

			if ((file != null) && file.exists()) {
				file.delete();
			}
		}
	}

	@Override
	public Folder updateFolder(
			long folderId, String title, String description,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		IDfSession idfSession = null;

		try {
			idfSession = getIdfSession();

			IDfId idfId = toFolderObjectId(idfSession, folderId);

			IDfFolder idfFolder = (IDfFolder)idfSession.getObject(idfId);

			idfFolder.setObjectName(title);
			idfFolder.setTitle(description);

			idfFolder.save();

			return toFolder(idfFolder);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
		finally {
			releaseSession(idfSession);
		}
	}

	@Override
	public boolean verifyFileEntryCheckOut(long fileEntryId, String lockUuid) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean verifyInheritableLock(long folderId, String lockUuid) {
		throw new UnsupportedOperationException();
	}

	protected void cancelCheckOut(IDfDocument idfDocument)
		throws PortalException, SystemException {

		try {
			IDfCancelCheckoutOperation idfCancelCheckoutOperation =
				_dfClientX.getCancelCheckoutOperation();

			idfCancelCheckoutOperation.add(idfDocument);

			if (!idfCancelCheckoutOperation.execute()) {
				IDfList idfList = idfCancelCheckoutOperation.getErrors();

				IDfOperationError idfOperationError =
					(IDfOperationError)idfList.get(0);

				throw new RepositoryException(idfOperationError.getMessage());
			}
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
	}

	protected IDfDocument checkinFileEntry(
			IDfDocument idfDocument, boolean incrementVersion,
			boolean majorVersion)
		throws PortalException, SystemException {

		try {
			IDfCheckinOperation idfCheckinOperation =
				_dfClientX.getCheckinOperation();

			if (incrementVersion) {
				if (majorVersion) {
					idfCheckinOperation.setCheckinVersion(
						IDfCheckinOperation.NEXT_MAJOR);
					idfCheckinOperation.setVersionLabels(
						Constants.VERSION_LABEL_CURRENT);
				}
				else {
					idfCheckinOperation.setCheckinVersion(
						IDfCheckinOperation.NEXT_MINOR);
				}
			}
			else {
				idfCheckinOperation.setCheckinVersion(
					IDfCheckinOperation.SAME_VERSION);
			}

			IDfCheckinNode idfCheckinNode =
				(IDfCheckinNode)idfCheckinOperation.add(idfDocument);

			if (!idfCheckinOperation.execute()) {
				IDfList idfList = idfCheckinOperation.getErrors();

				IDfOperationError idfOperationError =
					(IDfOperationError)idfList.get(0);

				throw new RepositoryException(idfOperationError.getMessage());
			}

			IDfId idfDocumentIDfId = idfDocument.getObjectId();
			IDfId idfCheckinNodeIDfId = idfCheckinNode.getNewObjectId();

			updateMappedId(
				idfDocumentIDfId.getId(), idfCheckinNodeIDfId.getId());

			IDfSession idfSession = idfDocument.getSession();

			return (IDfDocument)idfSession.getObject(
				idfCheckinNode.getNewObjectId());
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
	}

	protected File checkoutFileEntry(IDfDocument idfDocument, boolean download)
		throws PortalException, SystemException {

		try {
			IDfCheckoutOperation idfCheckoutOperation =
				_dfClientX.getCheckoutOperation();

			idfCheckoutOperation.setDownloadContent(download);

			if (download) {
				StringBundler sb = new StringBundler(5);

				sb.append(SystemProperties.get(SystemProperties.TMP_DIR));
				sb.append("/liferay/documentum/");
				sb.append(PwdGenerator.getPassword());
				sb.append(StringPool.UNDERLINE);

				IDfId idfId = idfDocument.getObjectId();

				sb.append(idfId.getId());

				String destDirectory = sb.toString();

				FileUtil.mkdirs(destDirectory);

				idfCheckoutOperation.setDestinationDirectory(destDirectory);
			}

			IDfCheckoutNode idfCheckoutNode = null;

			if (idfDocument.isVirtualDocument()) {
				IDfVirtualDocument idfVirtualDocument =
					idfDocument.asVirtualDocument(
						Constants.VERSION_LABEL_CURRENT, false);

				idfCheckoutNode = (IDfCheckoutNode)idfCheckoutOperation.add(
					idfVirtualDocument);
			}
			else {
				idfCheckoutNode = (IDfCheckoutNode)idfCheckoutOperation.add(
					idfDocument);
			}

			if (!idfCheckoutOperation.execute()) {
				Lock lock = new DocumentumLock(getCompanyId(), idfDocument);

				throw new DuplicateLockException(lock);
			}

			if (download) {
				return new File(idfCheckoutNode.getFilePath());
			}

			return null;
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
	}

	protected void deleteFolder(IDfSession idfSession, IDfFolder idfFolder)
		throws Exception {

		IDfCollection idfCollection = idfFolder.getContents(null);

		while (idfCollection.next()) {
			IDfTypedObject idfTypedObject = idfCollection.getTypedObject();

			IDfId idfId = toObjectId(
				idfSession, idfTypedObject.getString(Constants.R_OBJECT_ID));

			IDfSysObject idfSysObject = (IDfSysObject)idfSession.getObject(
				idfId);

			deleteMappedEntry(idfSession, idfSysObject);

			if (idfSysObject instanceof IDfFolder) {
				deleteFolder(idfSession, (IDfFolder)idfSysObject);
			}
			else {
				idfSysObject.destroyAllVersions();
			}
		}

		deleteMappedEntry(idfSession, idfFolder);

		idfFolder.destroyAllVersions();
	}

	protected void deleteMappedEntry(
			IDfSession idfSession, IDfSysObject idfSysObject)
		throws DfException {

		IDfId chronicleIdIDfId = idfSysObject.getChronicleId();

		IDfVersionTreeLabels idfVersionTreeLabels =
			idfSession.getVersionTreeLabels(chronicleIdIDfId);

		for (int i = 0; i < idfVersionTreeLabels.getVersionCount(); i++) {
			IDfVersionLabels idfVersionLabels = idfVersionTreeLabels.getVersion(
				i);

			IDfId idfVersionLabelsIDfId = idfVersionLabels.getObjectId();

			try {
				RepositoryEntryUtil.removeByR_M(
					getRepositoryId(), idfVersionLabelsIDfId.getId());
			}
			catch (NoSuchRepositoryEntryException nsree) {
			}
			catch (SystemException se) {
			}
		}

		try {
			IDfId idfId = idfSysObject.getObjectId();

			RepositoryEntryUtil.removeByR_M(getRepositoryId(), idfId.getId());
		}
		catch (NoSuchRepositoryEntryException nsree) {
		}
		catch (SystemException se) {
		}
	}

	protected Serializable getAttributeValue(IDfValue idfValue) {
		if (idfValue == null) {
			return null;
		}

		int dataType = idfValue.getDataType();

		if (dataType == IDfType.DF_BOOLEAN) {
			return idfValue.asBoolean();
		}
		else if (dataType == IDfType.DF_DOUBLE) {
			return idfValue.asDouble();
		}
		else if (dataType == IDfType.DF_ID) {
			IDfId idfId = idfValue.asId();

			if (idfId != null) {
				return idfId.getId();
			}

			return null;
		}
		else if (dataType == IDfType.DF_INTEGER) {
			return idfValue.asInteger();
		}
		else if (dataType == IDfType.DF_STRING) {
			return idfValue.asString();
		}
		else if (dataType == IDfType.DF_TIME) {
			IDfTime idfTime = idfValue.asTime();

			if (idfTime != null) {
				return idfTime.getDate();
			}

			return null;
		}
		else {
			return idfValue.asString();
		}
	}

	protected String getContentType(
			IDfSession idfSession, String mimeType, String sourceFileName,
			String title)
		throws DfException {

		if (Validator.isNull(mimeType)) {
			mimeType = MimeTypesUtil.getContentType(sourceFileName);
		}

		if (Validator.isNull(mimeType)) {
			mimeType = MimeTypesUtil.getContentType(title);
		}

		if (Validator.isNull(mimeType)) {
			return null;
		}

		if (mimeType.indexOf(StringPool.SEMICOLON) != -1) {
			mimeType = mimeType.substring(
				0, mimeType.indexOf(StringPool.SEMICOLON));
		}

		IDfQuery idfQuery = _dfClientX.getQuery();

		idfQuery.setDQL(
			"SELECT name FROM dm_format WHERE mime_type LIKE '%" + mimeType +
				"%'");

		IDfCollection idfCollection = idfQuery.execute(
			idfSession, IDfQuery.DF_READ_QUERY);

		try {
			if (idfCollection.next()) {
				return idfCollection.getString("name");
			}
		}
		finally {
			idfCollection.close();
		}

		return null;
	}

	protected FileEntry getFileEntry(IDfSession idfSession, long fileEntryId)
		throws PortalException, SystemException {

		try {
			IDfId idfId = toFileEntryObjectId(idfSession, fileEntryId);

			return toFileEntry((IDfDocument)idfSession.getObject(idfId));
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
	}

	protected List<?> getFoldersOrFileEntries(
			IDfSession idfSession, String queryString, int start, int end)
		throws SystemException {

		IDfCollection idfCollection = null;

		try {
			List<Object> foldersOrFileEntries = new ArrayList<Object>();

			IDfQuery idfQuery = _dfClientX.getQuery();

			if (start == QueryUtil.ALL_POS) {
				start = 0;
			}

			idfQuery.setDQL(queryString);

			if (_log.isDebugEnabled()) {
				_log.debug("Executing query: " + queryString);
			}

			idfCollection = idfQuery.execute(
				idfSession, IDfQuery.DF_READ_QUERY);

			for (int i = 0; i < start; i++) {
				if (!idfCollection.next()) {
					break;
				}
			}

			int i = start;

			while (idfCollection.next()) {
				i++;

				if ((i > end) && (end != QueryUtil.ALL_POS)) {
					break;
				}

				IDfId idfId = idfCollection.getId(Constants.R_OBJECT_ID);

				IDfSysObject idfSysObject = (IDfSysObject)idfSession.getObject(
					idfId);

				if (_log.isTraceEnabled()) {
					_log.trace(idfSysObject.dump());
				}

				try {
					Object folderOrfileEntry = toFolderOrFileEntry(
						idfSysObject);

					foldersOrFileEntries.add(folderOrfileEntry);
				}
				catch (RepositoryException re) {
					_log.warn(re, re);
				}
			}

			return foldersOrFileEntries;
		}
		catch (Exception e) {
			if (e instanceof DfException) {
				try {
					DfException dfe = (DfException)e;

					processException(dfe);
				}
				catch (PortalException pe) {
					throw new RepositoryException(pe);
				}
			}

			throw new RepositoryException(e);
		}
		finally {
			if (idfCollection != null) {
				try {
					idfCollection.close();
				}
				catch (DfException dfe) {
				}
			}
		}
	}

	protected int getFoldersOrFileEntriesCount(
			IDfSession idfSession, String queryString)
		throws SystemException {

		IDfCollection idfCollection = null;

		try {
			IDfQuery idfQuery = _dfClientX.getQuery();

			idfQuery.setDQL(queryString);

			if (_log.isDebugEnabled()) {
				_log.debug("Executing query: " + queryString);
			}

			idfCollection = idfQuery.execute(
				idfSession, IDfQuery.DF_READ_QUERY);

			int total = 0;

			if (idfCollection.next()) {
				total = idfCollection.getInt("num_hits");
			}

			return total;
		}
		catch (Exception e) {
			if (e instanceof DfException) {
				try {
					DfException dfe = (DfException)e;

					processException(dfe);
				}
				catch (PortalException pe) {
					throw new RepositoryException(pe);
				}
			}

			throw new RepositoryException(e);
		}
		finally {
			if (idfCollection != null) {
				try {
					idfCollection.close();
				}
				catch (DfException dfe) {
				}
			}
		}
	}

	protected IDfFolder getIdfFolder(IDfSession idfSession, long folderId)
		throws PortalException, SystemException {

		try {
			IDfId idfId = toFolderObjectId(idfSession, folderId);

			return (IDfFolder)idfSession.getObject(idfId);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
	}

	protected IDfSession getIdfSession()
		throws PortalException, SystemException {

		try {
			IDfSessionManager idfSessionManager = getIdfSessionManager();

			return idfSessionManager.getSession(_repository);
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
	}

	protected IDfSessionManager getIdfSessionManager() throws SystemException {
		HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

		IDfSessionManager idfSessionManager = null;

		if (httpSession != null) {
			TransientValue<IDfSessionManager> transientValue =
				(TransientValue<IDfSessionManager>)httpSession.getAttribute(
					_SESSION_KEY);

			if (transientValue != null) {
				idfSessionManager = transientValue.getValue();
			}
		}
		else {
			idfSessionManager = _idfSessionManagerThreadLocal.get();
		}

		if (idfSessionManager != null) {
			return idfSessionManager;
		}

		try {
			IDfClient idfClient = _dfClientX.getLocalClient();

			idfSessionManager = idfClient.newSessionManager();

			IDfLoginInfo idfLoginInfo = _dfClientX.getLoginInfo();

			String password = PrincipalThreadLocal.getPassword();

			idfLoginInfo.setPassword(password);

			String login = getLogin();

			idfLoginInfo.setUser(login);

			idfSessionManager.setIdentity(_repository, idfLoginInfo);

			if (httpSession != null) {
				httpSession.setAttribute(
					_SESSION_KEY,
					new TransientValue<IDfSessionManager>(idfSessionManager));
			}
			else {
				_idfSessionManagerThreadLocal.set(idfSessionManager);
			}

			return idfSessionManager;
		}
		catch (DfException dfe) {
			throw new RepositoryException(dfe);
		}
	}

	protected String getLogin() throws RepositoryException {
		String login = PrincipalThreadLocal.getName();

		try {
			Company company = CompanyLocalServiceUtil.getCompany(
				getCompanyId());

			String authType = company.getAuthType();

			if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				return login;
			}

			long userId = GetterUtil.getLong(login);

			User user = userLocalService.getUser(userId);

			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				login = user.getEmailAddress();
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				login = user.getScreenName();
			}

			return login;
		}
		catch (Exception e) {
			throw new RepositoryException(e);
		}
	}

	protected IDfId getObjectId(
			IDfSession idfSession, long folderId, boolean fileEntry,
			String name)
		throws SystemException {

		IDfCollection idfCollection = null;

		try {
			IDfId idfId = toFolderObjectId(idfSession, folderId);

			StringBundler sb = new StringBundler(7);

			sb.append("SELECT r_object_id FROM ");

			if (fileEntry) {
				sb.append(Constants.DM_DOCUMENT);
			}
			else {
				sb.append(Constants.DM_FOLDER);
			}

			sb.append(" WHERE object_name = '");
			sb.append(name);
			sb.append("' AND FOLDER (ID('");
			sb.append(idfId.getId());
			sb.append("'))");

			IDfQuery idfQuery = _dfClientX.getQuery();

			idfQuery.setDQL(sb.toString());

			idfCollection = idfQuery.execute(
				idfSession, IDfQuery.DF_READ_QUERY);

			if (idfCollection.next()) {
				return toObjectId(
					idfSession, idfCollection.getString(Constants.R_OBJECT_ID));
			}
		}
		catch (DfException dfe) {
			throw new RepositoryException(dfe);
		}
		catch (PortalException pe) {
			throw new RepositoryException(pe);
		}
		finally {
			if (idfCollection != null) {
				try {
					idfCollection.close();
				}
				catch (DfException dfe) {
				}
			}
		}

		return null;
	}

	protected void getSubfolderIds(
			List<Long> subfolderIds, List<Folder> subfolders, boolean recurse)
		throws SystemException {

		for (Folder subfolder : subfolders) {
			long subfolderId = subfolder.getFolderId();

			subfolderIds.add(subfolderId);

			if (recurse) {
				List<Folder> subSubFolders = getFolders(
					subfolderId, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null);

				getSubfolderIds(subfolderIds, subSubFolders, recurse);
			}
		}
	}

	protected void processException(DfException dfe) throws PortalException {
		if (dfe instanceof DfAuthenticationException) {
			String message = dfe.getMessage();

			try {
				message = "Unable to login with user " + getLogin();
			}
			catch (Exception e) {
			}

			throw new PrincipalException(message, dfe);
		}
	}

	protected void releaseSession(IDfSession idfSession) {
		if (idfSession != null) {
			try {
				IDfSessionManager idfSessionManager = getIdfSessionManager();

				idfSessionManager.release(idfSession);
			}
			catch (SystemException se) {
				_log.warn("Unable to get session manager", se);
			}
		}
	}

	protected String[] toContentTypes(IDfSession idfSession, String[] mimeTypes)
		throws SystemException {

		if ((mimeTypes == null) || (mimeTypes.length == 0)) {
			return mimeTypes;
		}

		List<String> contentTypes = new ArrayList<String>();

		IDfCollection idfCollection = null;

		try {
			IDfQuery idfQuery = _dfClientX.getQuery();

			for (int i = 0; i < mimeTypes.length; i++) {
				String contentType = _contentTypes.get(mimeTypes[i]);

				if (Validator.isNotNull(contentType)) {
					contentTypes.add(contentType);

					continue;
				}

				String mimeQuery =
					"SELECT name FROM dm_format WHERE mime_type LIKE '%" +
						mimeTypes[i] + "%'";

				idfQuery.setDQL(mimeQuery);

				idfCollection = idfQuery.execute(
					idfSession, IDfQuery.DF_READ_QUERY);

				if (idfCollection.next()) {
					contentType = idfCollection.getString("name");

					contentTypes.add(contentType);

					_contentTypes.put(mimeTypes[i], contentType);
				}
				else {
					_log.info(
						"Unable to determine documentum content type for " +
						"mime type " + mimeTypes[i]);
				}

				idfCollection.close();
			}

			return contentTypes.toArray(new String[contentTypes.size()]);
		}
		catch (DfException de) {
			throw new RepositoryException(de);
		}
		finally {
			if (idfCollection != null) {
				try {
					idfCollection.close();
				}
				catch (DfException de) {
				}
			}
		}
	}

	protected FileEntry toFileEntry(IDfDocument idfDocument)
		throws SystemException {

		try {
			IDfId fileEntryIDfId = idfDocument.getObjectId();

			Object[] ids = getRepositoryEntryIds(fileEntryIDfId.getId());

			long fileEntryId = (Long)ids[0];
			String uuid = (String)ids[1];

			IDfSession idfSession = idfDocument.getSession();

			String folderId = idfDocument.getString(Constants.I_FOLDER_ID);

			long parentFolderId = 0;

			if (Validator.isNotNull(folderId)) {
				Object[] parentIds = getRepositoryEntryIds(folderId);

				parentFolderId = (Long)parentIds[0];
			}

			IDfId chronicleIdIDfId = idfDocument.getChronicleId();

			IDfVersionTreeLabels idfVersionTreeLabels =
				idfSession.getVersionTreeLabels(chronicleIdIDfId);

			List<DocumentumVersionLabel> documentumVersionLabels =
				new ArrayList<DocumentumVersionLabel>(
					idfVersionTreeLabels.getVersionCount());

			for (int i = 0; i < idfVersionTreeLabels.getVersionCount(); i++) {
				IDfVersionLabels idfVersionLabels =
					idfVersionTreeLabels.getVersion(i);

				IDfId idfVersionLabelsIDfId = idfVersionLabels.getObjectId();

				Object[] versionIds = getRepositoryEntryIds(
					idfVersionLabelsIDfId.getId());

				long versionId = (Long)versionIds[0];
				String versionUuid = (String)versionIds[1];

				DocumentumVersionLabel documentumVersionLabel =
					new DocumentumVersionLabel(
						idfVersionLabels.getImplicitVersionLabel(), versionId,
						versionUuid);

				documentumVersionLabels.add(documentumVersionLabel);
			}

			DocumentumFileEntry documentumFileEntry = new DocumentumFileEntry(
				this, uuid, fileEntryId, idfDocument, documentumVersionLabels);

			documentumFileEntry.setParentFolderId(parentFolderId);

			boolean updateAssetEnabled = _updateAssetEnabledThreadLocal.get();

			if (!updateAssetEnabled) {
				return documentumFileEntry;
			}

			try {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
					DLFileEntryConstants.getClassName(), fileEntryId);

				if (assetEntry != null) {
					return documentumFileEntry;
				}

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader =
					currentThread.getContextClassLoader();

				FileEntry fileEntryProxy =
					(FileEntry)ProxyUtil.newProxyInstance(
						contextClassLoader, new Class[] {FileEntry.class},
						new ClassLoaderBeanHandler(
							documentumFileEntry, contextClassLoader));

				FileEntry fileEntryProxyBean = new FileEntryProxyBean(
					fileEntryProxy, contextClassLoader);

				FileVersion fileVersion = fileEntryProxyBean.getFileVersion();

				_updateAssetEnabledThreadLocal.set(false);

				dlAppHelperLocalService.addFileEntry(
					PrincipalThreadLocal.getUserId(), fileEntryProxyBean,
					fileVersion, new ServiceContext());
			}
			catch (Exception e) {
				_log.error("Unable to update asset", e);
			}
			finally {
				_updateAssetEnabledThreadLocal.set(updateAssetEnabled);
			}

			return documentumFileEntry;
		}
		catch (DfException dfe) {
			throw new RepositoryException(dfe);
		}
	}

	protected IDfId toFileEntryObjectId(IDfSession idfSession, long fileEntryId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			fileEntryId);

		if (repositoryEntry == null) {
			throw new NoSuchFileEntryException(
				"No Documentum file entry with {fileEntryId=" + fileEntryId +
					"}");
		}

		return toObjectId(idfSession, repositoryEntry.getMappedId());
	}

	protected Folder toFolder(IDfFolder idfFolder) throws SystemException {
		try {
			IDfId idfId = idfFolder.getObjectId();

			Object[] ids = getRepositoryEntryIds(idfId.getId());

			long folderId = (Long)ids[0];
			String uuid = (String)ids[1];

			long parentFolderId = 0;

			if (idfFolder.getAncestorIdCount() > 1) {
				Object[] parentIds = getRepositoryEntryIds(
					idfFolder.getAncestorId(1));

				parentFolderId = (Long)parentIds[0];
			}
			else {
				Folder folder = DLAppLocalServiceUtil.getMountFolder(
					getRepositoryId());

				parentFolderId = folder.getParentFolderId();
			}

			DocumentumFolder documentumFolder = new DocumentumFolder(
				this, uuid, folderId, idfFolder);

			documentumFolder.setParentFolderId(parentFolderId);

			return documentumFolder;
		}
		catch (DfException dfe) {
			throw new RepositoryException(dfe);
		}
		catch (PortalException pe) {
			throw new RepositoryException(pe);
		}
	}

	protected IDfId toFolderObjectId(IDfSession idfSession, long folderId)
		throws PortalException, SystemException {

		try {
			RepositoryEntry repositoryEntry =
				RepositoryEntryUtil.fetchByPrimaryKey(folderId);

			if (repositoryEntry != null) {
				return toObjectId(idfSession, repositoryEntry.getMappedId());
			}

			DLFolder dlFolder = DLFolderUtil.fetchByPrimaryKey(folderId);

			if (dlFolder == null) {
				throw new NoSuchFolderException(
					"No Documentum folder with {folderId=" + folderId + "}");
			}
			else if (!dlFolder.isMountPoint()) {
				throw new RepositoryException(
					"Documentum repository should not be used for folder ID " +
						folderId);
			}

			IDfFolder idfFolder = idfSession.getFolderByPath(
				StringPool.SLASH + _cabinet);

			IDfId idfFolderId = idfFolder.getObjectId();

			repositoryEntry = RepositoryEntryUtil.fetchByR_M(
				getRepositoryId(), idfFolderId.getId());

			if (repositoryEntry == null) {
				long repositoryEntryId = counterLocalService.increment();

				repositoryEntry = RepositoryEntryUtil.create(repositoryEntryId);

				repositoryEntry.setGroupId(getGroupId());
				repositoryEntry.setRepositoryId(getRepositoryId());
				repositoryEntry.setMappedId(idfFolderId.getId());

				RepositoryEntryUtil.update(repositoryEntry);
			}

			return idfFolderId;
		}
		catch (DfException dfe) {
			processException(dfe);

			throw new RepositoryException(dfe);
		}
	}

	protected Object toFolderOrFileEntry(IDfSysObject idfSysObject)
		throws SystemException {

		if (idfSysObject instanceof IDfDocument) {
			IDfDocument idfDocument = (IDfDocument)idfSysObject;

			return toFileEntry(idfDocument);
		}
		else if (idfSysObject instanceof IDfFolder) {
			IDfFolder idfFolder = (IDfFolder)idfSysObject;

			return toFolder(idfFolder);
		}
		else {
			throw new RepositoryException(
				"Unsupported object type " + idfSysObject);
		}
	}

	protected IDfId toObjectId(IDfSession idfSession, String objectId)
		throws SystemException {

		try {
			return idfSession.getIdByQualification(
				"dm_sysobject (ALL) where r_object_id = '" + objectId + "'");
		}
		catch (DfException dfe) {
			throw new RepositoryException(dfe);
		}
	}

	protected void updateMappedId(String oldMappedId, String mappedId)
		throws SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByR_M(
			getRepositoryId(), oldMappedId);

		if (!mappedId.equals(repositoryEntry.getMappedId())) {
			repositoryEntry.setMappedId(mappedId);

			RepositoryEntryUtil.update(repositoryEntry);
		}
	}

	protected void updateProperties(
		IDfDocument idfDocument, ServiceContext serviceContext) {
	}

	protected void validateTitle(
			IDfSession idfSession, long folderId, String title)
		throws PortalException, SystemException {

		IDfId idfObjectId = getObjectId(idfSession, folderId, true, title);

		if (idfObjectId != null) {
			throw new DuplicateFileException(title);
		}

		idfObjectId = getObjectId(idfSession, folderId, false, title);

		if (idfObjectId != null) {
			throw new DuplicateFolderNameException(title);
		}
	}

	private static final String _CABINET = "CABINET";

	private static final String _CONFIGURATION_DFC = "CONFIGURATION_DFC";

	private static final String _REPOSITORY = "REPOSITORY";

	private static final String _SESSION_KEY =
		DocumentumRepository.class.getName() + ".sessionManager";

	private static final String[] _SUPPORTED_CONFIGURATIONS = {
		_CONFIGURATION_DFC
	};

	private static final String[][] _SUPPORTED_PARAMETERS = {
		new String[] {_REPOSITORY, _CABINET}
	};

	private static Log _log = LogFactoryUtil.getLog(DocumentumRepository.class);

	private static Map<String, String> _contentTypes =
		new ConcurrentHashMap<String, String>();

	private String _cabinet;
	private DfClientX _dfClientX;
	private AutoResetThreadLocal<IDfSessionManager>
		_idfSessionManagerThreadLocal =
			new AutoResetThreadLocal<IDfSessionManager>(
				IDfSessionManager.class.getName());
	private String _repository;
	private AutoResetThreadLocal<Boolean> _updateAssetEnabledThreadLocal =
		new AutoResetThreadLocal<Boolean>(
			DocumentumRepository.class + "_updateAssetEnabledThreadLocal",
			Boolean.TRUE);

}