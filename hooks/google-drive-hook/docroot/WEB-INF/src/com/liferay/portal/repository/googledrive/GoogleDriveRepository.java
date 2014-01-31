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

package com.liferay.portal.repository.googledrive;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.About;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;
import com.google.api.services.drive.model.Revision;
import com.google.api.services.drive.model.RevisionList;

import com.liferay.portal.NoSuchRepositoryEntryException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.repository.googledrive.model.GoogleDriveFileEntry;
import com.liferay.portal.repository.googledrive.model.GoogleDriveFileVersion;
import com.liferay.portal.repository.googledrive.model.GoogleDriveFolder;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.repository.external.CredentialsProvider;
import com.liferay.repository.external.ExtRepository;
import com.liferay.repository.external.ExtRepositoryAdapter;
import com.liferay.repository.external.ExtRepositoryFileEntry;
import com.liferay.repository.external.ExtRepositoryFileVersion;
import com.liferay.repository.external.ExtRepositoryFileVersionDescriptor;
import com.liferay.repository.external.ExtRepositoryFolder;
import com.liferay.repository.external.ExtRepositoryObject;
import com.liferay.repository.external.ExtRepositoryObjectType;
import com.liferay.repository.external.ExtRepositorySearchResult;
import com.liferay.repository.external.search.ExtRepositoryQueryMapper;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Sergio González
 */
public class GoogleDriveRepository
	extends ExtRepositoryAdapter implements ExtRepository {

	public GoogleDriveRepository() {
		super(null);
	}

	@Override
	public ExtRepositoryFileEntry addExtRepositoryFileEntry(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, String changeLog, InputStream inputStream)
		throws PortalException, SystemException {

		File file = addFile(
			extRepositoryParentFolderKey, mimeType, title, description,
			inputStream);

		return new GoogleDriveFileEntry(file);
	}

	@Override
	public ExtRepositoryFolder addExtRepositoryFolder(
			String extRepositoryParentFolderKey, String name,
			String description)
		throws PortalException, SystemException {

		File file = addFile(
			extRepositoryParentFolderKey, _FOLDER_MIME_TYPE, name, description,
			null);

		return new GoogleDriveFolder(file, _rootFolderKey);
	}

	@Override
	public ExtRepositoryFileVersion cancelCheckOut(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void checkInExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, boolean createMajorVersion,
			String changeLog)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public ExtRepositoryFileEntry checkOutExtRepositoryFileEntry(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends ExtRepositoryObject> T copyExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFileEntryKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			File newFile = new File();

			ParentReference parentReference = new ParentReference();

			parentReference.setId(newExtRepositoryFolderKey);

			newFile.setParents(Arrays.asList(parentReference));

			Drive.Files.Copy driveFilesCopy = driveFiles.copy(
				extRepositoryFileEntryKey, newFile);

			driveFilesCopy.execute();

			T extRepositoryObject = null;

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.FOLDER)) {

				extRepositoryObject = (T)new GoogleDriveFolder(
					newFile, _rootFolderKey);
			}
			else {
				extRepositoryObject = (T)new GoogleDriveFileEntry(newFile);
			}

			return extRepositoryObject;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new PortalException(ioe);
		}
	}

	@Override
	public void deleteExtRepositoryObject(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.Delete driveFilesDelete = driveFiles.delete(
				extRepositoryObjectKey);

			driveFilesDelete.execute();
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new PortalException(ioe);
		}
	}

	@Override
	public String getAuthType() {
		return null;
	}

	@Override
	public InputStream getContentStream(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException {

		GoogleDriveFileEntry googleDriveFileEntry =
			(GoogleDriveFileEntry)extRepositoryFileEntry;

		return getContentStream(googleDriveFileEntry.getDownloadURL());
	}

	@Override
	public InputStream getContentStream(
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException, SystemException {

		GoogleDriveFileVersion googleDriveFileVersion =
			(GoogleDriveFileVersion)extRepositoryFileVersion;

		return getContentStream(googleDriveFileVersion.getDownloadURL());
	}

	public Drive getDrive() throws PortalException {
		HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

		Drive drive = null;

		if (httpSession != null) {
			TransientValue<Drive> transientValue =
				(TransientValue<Drive>)httpSession.getAttribute(
					GoogleDriveRepository.class.getName());

			if (transientValue != null) {
				drive = transientValue.getValue();
			}
		}
		else {
			drive = _driveThreadLocal.get();
		}

		if (drive != null) {
			return drive;
		}

		try {
			drive = buildDrive();
		}
		catch (Exception e) {
			throw new PrincipalException(e);
		}

		if (httpSession != null) {
			httpSession.setAttribute(
				GoogleDriveRepository.class.getName(),
				new TransientValue<Drive>(drive));
		}
		else {
			_driveThreadLocal.set(drive);
		}

		return drive;
	}

	@Override
	public ExtRepositoryFileVersion getExtRepositoryFileVersion(
			ExtRepositoryFileEntry extRepositoryFileEntry, String version)
		throws PortalException, SystemException {

		try {
			Drive drive = getDrive();

			Drive.Revisions driveRevisions = drive.revisions();

			Drive.Revisions.Get driveRevisionsGet = driveRevisions.get(
				extRepositoryFileEntry.getExtRepositoryModelKey(), version);

			Revision revision = driveRevisionsGet.execute();

			return new GoogleDriveFileVersion(revision);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public ExtRepositoryFileVersionDescriptor
		getExtRepositoryFileVersionDescriptor(
			String extRepositoryFileVersionKey) {

		return null;
	}

	@Override
	public List<ExtRepositoryFileVersion> getExtRepositoryFileVersions(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException {

		try {
			Drive drive = getDrive();

			Drive.Revisions driveRevisions = drive.revisions();

			Drive.Revisions.List driveRevisionsList = driveRevisions.list(
				extRepositoryFileEntry.getExtRepositoryModelKey());

			RevisionList revisionList = driveRevisionsList.execute();

			List<Revision> revisions = revisionList.getItems();

			List<ExtRepositoryFileVersion> extRepositoryFileVersions =
				new ArrayList<ExtRepositoryFileVersion>(revisions.size());

			for (Revision revision : revisions) {
				extRepositoryFileVersions.add(
					new GoogleDriveFileVersion(revision));
			}

			return extRepositoryFileVersions;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.Get driveFilesGet = driveFiles.get(
				extRepositoryObjectKey);

			File file = driveFilesGet.execute();

			T extRepositoryObject = null;

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.FOLDER)) {

				extRepositoryObject = (T)new GoogleDriveFolder(
					file, _rootFolderKey);
			}
			else {
				extRepositoryObject = (T)new GoogleDriveFileEntry(file);
			}

			return extRepositoryObject;
		}
		catch (IOException ioe) {
			throw new NoSuchRepositoryEntryException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey, String title)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.Get driveFilesGet = driveFiles.get(
				extRepositoryFolderKey);

			File file = driveFilesGet.execute();

			StringBundler sb = new StringBundler();

			sb.append("'");
			sb.append(file.getId());
			sb.append("' in parents and title contains '");
			sb.append(title);
			sb.append(" and mimeType ");

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.FOLDER)) {

				sb.append("= ");
			}
			else {
				sb.append("!= ");
			}

			sb.append(_FOLDER_MIME_TYPE);

			Drive.Files.List driveFilesList = driveFiles.list();

			driveFilesList.setQ(sb.toString());

			FileList fileList = driveFilesList.execute();

			List<File> files = fileList.getItems();

			if (files.isEmpty()) {
				throw new NoSuchRepositoryEntryException();
			}

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.FOLDER)) {

				return (T)new GoogleDriveFolder(files.get(0), _rootFolderKey);
			}
			else {
				return (T)new GoogleDriveFileEntry(files.get(0));
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new NoSuchRepositoryEntryException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> List<T> getExtRepositoryObjects(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.List driveFilesList = driveFiles.list();

			StringBundler sb = new StringBundler();

			if (extRepositoryFolderKey != null) {
				sb.append("'");
				sb.append(extRepositoryFolderKey);
				sb.append("' in parents and ");
			}

			if (!extRepositoryObjectType.equals(
					ExtRepositoryObjectType.OBJECT)) {

				sb.append("mimeType");

				if (extRepositoryObjectType.equals(
						ExtRepositoryObjectType.FILE)) {

					sb.append(" != '");
				}
				else {
					sb.append(" = '");
				}

				sb.append(_FOLDER_MIME_TYPE);
				sb.append("' and ");
			}

			sb.append("trashed = false");

			driveFilesList.setQ(sb.toString());

			FileList fileList = driveFilesList.execute();

			List<File> files = fileList.getItems();

			List<T> extRepositoryObjects = new ArrayList<T>();

			for (File file : files) {
				if (_FOLDER_MIME_TYPE.equals(file.getMimeType())) {
					extRepositoryObjects.add(
						(T)new GoogleDriveFolder(file, _rootFolderKey));
				}
				else {
					extRepositoryObjects.add((T)new GoogleDriveFileEntry(file));
				}
			}

			return extRepositoryObjects;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public int getExtRepositoryObjectsCount(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException {

		List<? extends ExtRepositoryObject> extRepositoryObjects =
			getExtRepositoryObjects(
				extRepositoryObjectType, extRepositoryFolderKey);

		return extRepositoryObjects.size();
	}

	@Override
	public ExtRepositoryFolder getExtRepositoryParentFolder(
			ExtRepositoryObject extRepositoryObject)
		throws PortalException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			File file = driveFiles.get(
				extRepositoryObject.getExtRepositoryModelKey()).execute();

			List<ParentReference> parentReferences = file.getParents();

			if (!parentReferences.isEmpty()) {
				ParentReference parentReference = parentReferences.get(0);

				Drive.Files.Get driveFilesGet = driveFiles.get(
					parentReference.getId());

				File parentFile = driveFilesGet.execute();

				return new GoogleDriveFolder(parentFile, _rootFolderKey);
			}
		}
		catch (IOException ioe) {
			//_log.error(ioe, ioe);
		}

		return null;
	}

	@Override
	public String getLiferayLogin(String extRepositoryLogin) {
		return null;
	}

	@Override
	public String getRootFolderKey() {
		return _rootFolderKey;
	}

	@Override
	public List<String> getSubfolderKeys(
			String extRepositoryFolderKey, boolean recurse)
		throws PortalException, SystemException {

		List<String> subfolderKeys = new ArrayList<String>();

		getSubfolderKeys(extRepositoryFolderKey, recurse, subfolderKeys);

		return subfolderKeys;
	}

	@Override
	public String[] getSupportedConfigurations() {
		return new String[0];
	}

	@Override
	public String[][] getSupportedParameters() {
		return new String[0][];
	}

	@Override
	public void initRepository(
			UnicodeProperties typeSettingsProperties,
			CredentialsProvider credentialsProvider)
		throws PortalException, SystemException {

		try {
			Drive drive = getDrive();

			Drive.About driveAbout = drive.about();

			Drive.About.Get driveAboutGet = driveAbout.get();

			About about = driveAboutGet.execute();

			_rootFolderKey = about.getRootFolderId();
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> T moveExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.Get driveFilesGet = driveFiles.get(
				extRepositoryObjectKey);

			File file = driveFilesGet.execute();

			Drive.Parents driveParents = drive.parents();

			List<ParentReference> parentReferences = file.getParents();

			for (ParentReference parentReference : parentReferences) {
				Drive.Parents.Delete driveParentsDelete = driveParents.delete(
					file.getId(), parentReference.getId());

				driveParentsDelete.execute();
			}

			ParentReference parentReference = new ParentReference();

			parentReference.setId(newExtRepositoryFolderKey);

			Drive.Parents.Insert driveParentsInsert = driveParents.insert(
				file.getId(), parentReference);

			driveParentsInsert.execute();

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.OBJECT)) {

				return (T)new GoogleDriveFileEntry(file);
			}
			else {
				return (T)new GoogleDriveFolder(file, _rootFolderKey);
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public List<ExtRepositorySearchResult<?>> search(
			SearchContext searchContext, Query query,
			ExtRepositoryQueryMapper extRepositoryQueryMapper)
		throws PortalException, SystemException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.List driveFilesList = driveFiles.list();

			String searchQuery = getSearchQuery(
				searchContext.getKeywords(), searchContext.getFolderIds(),
				extRepositoryQueryMapper);

			driveFilesList.setQ(searchQuery);

			FileList fileList = driveFilesList.execute();

			List<File> files = fileList.getItems();

			List<ExtRepositorySearchResult<?>> extRepositorySearchResults =
				new ArrayList<ExtRepositorySearchResult<?>>(files.size());

			for (File file : files) {
				if (_FOLDER_MIME_TYPE.equals(file.getMimeType())) {
					GoogleDriveFolder googleDriveFolder = new GoogleDriveFolder(
						file, _rootFolderKey);

					ExtRepositorySearchResult<GoogleDriveFolder>
						extRepositorySearchResult =
							new ExtRepositorySearchResult<GoogleDriveFolder>(
								googleDriveFolder, 1.0f, StringPool.BLANK);

					extRepositorySearchResults.add(extRepositorySearchResult);
				}
				else {
					GoogleDriveFileEntry googleDriveFileEntry =
						new GoogleDriveFileEntry(file);

					ExtRepositorySearchResult<GoogleDriveFileEntry>
						extRepositorySearchResult =
							new ExtRepositorySearchResult<GoogleDriveFileEntry>(
								googleDriveFileEntry, 1.0f, StringPool.BLANK);

					extRepositorySearchResults.add(extRepositorySearchResult);
				}
			}

			return extRepositorySearchResults;
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	@Override
	public ExtRepositoryFileEntry updateExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, String mimeType,
			InputStream inputStream)
		throws PortalException, SystemException {

		try {
			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.Get driveFilesGet = driveFiles.get(
				extRepositoryFileEntryKey);

			File file = driveFilesGet.execute();

			InputStreamContent inputStreamContent = new InputStreamContent(
				mimeType, inputStream);

			Drive.Files.Update driveFilesUpdate = driveFiles.update(
				extRepositoryFileEntryKey, file, inputStreamContent);

			file = driveFilesUpdate.execute();

			return new GoogleDriveFileEntry(file);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	protected File addFile(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, InputStream inputStream)
		throws PortalException, SystemException {

		try {
			File file = new File();

			file.setDescription(description);
			file.setMimeType(mimeType);

			Drive drive = getDrive();

			Drive.Files driveFiles = drive.files();

			Drive.Files.Get driveFilesGet = driveFiles.get(
				extRepositoryParentFolderKey);

			File extRepositoryParentFolderFile = driveFilesGet.execute();

			ParentReference parentReference = new ParentReference();

			parentReference.setId(extRepositoryParentFolderFile.getId());

			file.setParents(Arrays.asList(parentReference));

			file.setTitle(title);

			if (inputStream != null) {
				InputStreamContent inputStreamContent = new InputStreamContent(
					mimeType, inputStream);

				Drive.Files.Insert driveFilesInsert = driveFiles.insert(
					file, inputStreamContent);

				return driveFilesInsert.execute();
			}
			else {
				Drive.Files.Insert driveFilesInsert = driveFiles.insert(file);

				return driveFilesInsert.execute();
			}
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	protected Drive buildDrive() throws Exception {
		long userId = PrincipalThreadLocal.getUserId();

		User user = UserLocalServiceUtil.getUser(userId);

		if (user.isDefaultUser()) {
			throw new PrincipalException("User is not authenticated");
		}

		GoogleCredential.Builder builder = new GoogleCredential.Builder();

		String googleClientId = PrefsPropsUtil.getString(
			user.getCompanyId(), "google-client-id");
		String googleClientSecret = PrefsPropsUtil.getString(
			user.getCompanyId(), "google-client-secret");

		builder.setClientSecrets(googleClientId, googleClientSecret);

		JacksonFactory jsonFactory = new JacksonFactory();

		builder.setJsonFactory(jsonFactory);

		HttpTransport httpTransport = new NetHttpTransport();

		builder.setTransport(httpTransport);

		GoogleCredential googleCredential = builder.build();

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String googleAccessToken = GetterUtil.getString(
			expandoBridge.getAttribute("googleAccessToken", false));

		googleCredential.setAccessToken(googleAccessToken);

		String googleRefreshToken = GetterUtil.getString(
			expandoBridge.getAttribute("googleRefreshToken", false));

		googleCredential.setRefreshToken(googleRefreshToken);

		Drive.Builder driveBuilder = new Drive.Builder(
			httpTransport, jsonFactory, googleCredential);

		return driveBuilder.build();
	}

	protected InputStream getContentStream(String downloadURL)
		throws PortalException, SystemException {

		if (Validator.isNull(downloadURL)) {
			return null;
		}

		Drive drive = getDrive();

		HttpRequestFactory httpRequestFactory = drive.getRequestFactory();

		GenericUrl genericUrl = new GenericUrl(downloadURL);

		try {
			HttpRequest httpRequest = httpRequestFactory.buildGetRequest(
				genericUrl);

			HttpResponse httpResponse = httpRequest.execute();

			return httpResponse.getContent();
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);

			throw new SystemException(ioe);
		}
	}

	protected String getSearchQuery(
			String keywords, long[] folderIds,
			ExtRepositoryQueryMapper extRepositoryQueryMapper)
		throws SearchException {

		StringBundler sb = new StringBundler();

		sb.append("fullText contains '");
		sb.append(keywords);
		sb.append("' and ");

		for (int i = 0; i < folderIds.length; i++) {
			if (i != 0) {
				sb.append(" and ");
			}

			long folderId = folderIds[i];

			String extRepositoryFolderKey =
				extRepositoryQueryMapper.formatParameterValue(
					"folderId", String.valueOf(folderId));

			sb.append(StringPool.APOSTROPHE);
			sb.append(extRepositoryFolderKey);
			sb.append(StringPool.APOSTROPHE);

			sb.append(" in parents");
		}

		return sb.toString();
	}

	protected List<String> getSubfolderKeys(
			String extRepositoryFolderKey, boolean recurse,
			List<String> subfolderKeys)
		throws PortalException, SystemException {

		List<ExtRepositoryFolder> extRepositoryFolders =
			getExtRepositoryObjects(
				ExtRepositoryObjectType.FOLDER, extRepositoryFolderKey);

		for (ExtRepositoryFolder extRepositoryFolder : extRepositoryFolders) {
			subfolderKeys.add(extRepositoryFolder.getExtRepositoryModelKey());

			if (recurse) {
				getSubfolderKeys(
					extRepositoryFolder.getExtRepositoryModelKey(), recurse,
					subfolderKeys);
			}
		}

		return subfolderKeys;
	}

	private static final String _FOLDER_MIME_TYPE =
		"application/vnd.google-apps.folder";

	private static Log _log = LogFactoryUtil.getLog(
		GoogleDriveRepository.class);

	private ThreadLocal<Drive> _driveThreadLocal =
		new AutoResetThreadLocal<Drive>(Drive.class.getName());
	private String _rootFolderKey;

}