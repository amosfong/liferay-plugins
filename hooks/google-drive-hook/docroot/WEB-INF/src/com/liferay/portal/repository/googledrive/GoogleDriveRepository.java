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
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
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

		return null;
	}

	@Override
	public void deleteExtRepositoryObject(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		Drive.Files files = drive.files();

		try {
			files.delete(extRepositoryObjectKey).execute();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

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

		return getContentStream(googleDriveFileEntry.getDownloadUrl());
	}

	@Override
	public InputStream getContentStream(
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException, SystemException {

		GoogleDriveFileVersion googleDriveFileVersion =
			(GoogleDriveFileVersion)extRepositoryFileVersion;

		return getContentStream(googleDriveFileVersion.getDownloadUrl());
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

		Drive drive = getDrive();

		Drive.Revisions revisions = drive.revisions();

		try {
			Revision revision = revisions.get(
				extRepositoryFileEntry.getExtRepositoryModelKey(), version).
					execute();

			return new GoogleDriveFileVersion(revision);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

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
		throws SystemException {

		try {
			Drive drive = getDrive();

			Drive.Revisions revisions = drive.revisions();

			RevisionList revisionList = revisions.list(
				extRepositoryFileEntry.getExtRepositoryModelKey()).execute();

			List<Revision> revisionListItems = revisionList.getItems();

			List<ExtRepositoryFileVersion> extRepositoryFileVersions =
				new ArrayList<ExtRepositoryFileVersion>(
					revisionListItems.size());

			for (Revision revision : revisionListItems) {
				extRepositoryFileVersions.add(
					new GoogleDriveFileVersion(revision));
			}

			return extRepositoryFileVersions;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new SystemException(ioe);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		Drive.Files files = drive.files();

		try {
			File file = files.get(extRepositoryObjectKey).execute();

			T extRepositoryEntry = null;

			if (extRepositoryObjectType.equals(
					extRepositoryObjectType.FOLDER)) {

				extRepositoryEntry = (T)new GoogleDriveFolder(
					file, _rootFolderKey);
			}
			else {
				extRepositoryEntry = (T)new GoogleDriveFileEntry(file);
			}

			return extRepositoryEntry;
		}
		catch (IOException ioe) {
			throw new NoSuchRepositoryEntryException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey, String title)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		Drive.Files files = drive.files();

		try {
			File file = files.get(extRepositoryFolderKey).execute();

			StringBundler sb = new StringBundler();

			sb.append("'");
			sb.append(file.getId());
			sb.append("' in parents and title contains '");
			sb.append(title);
			sb.append(" and mimeType ");

			if (extRepositoryObjectType.equals(
					extRepositoryObjectType.FOLDER)) {

				sb.append("= ");
			}
			else {
				sb.append("!= ");
			}

			sb.append(_FOLDER_MIME_TYPE);

			FileList fileList = files.list().setQ(sb.toString()).execute();

			List<File> fileListItems = fileList.getItems();

			if (fileListItems.isEmpty()) {
				throw new NoSuchRepositoryEntryException();
			}

			if (extRepositoryObjectType.equals(
					extRepositoryObjectType.FOLDER)) {

				return (T)new GoogleDriveFolder(
					fileListItems.get(0), _rootFolderKey);
			}
			else {
				return (T)new GoogleDriveFileEntry(fileListItems.get(0));
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new NoSuchRepositoryEntryException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> List<T> getExtRepositoryObjects(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		try {
			StringBundler sb = new StringBundler();

			if (extRepositoryFolderKey != null) {
				sb.append("'");
				sb.append(extRepositoryFolderKey);
				sb.append("' in parents and ");
			}

			if (!extRepositoryObjectType.equals(
					extRepositoryObjectType.OBJECT)) {

				sb.append("mimeType");

				if (extRepositoryObjectType.equals(
						extRepositoryObjectType.FILE)) {

					sb.append(" != '");
				}
				else {
					sb.append(" = '");
				}

				sb.append(_FOLDER_MIME_TYPE);
				sb.append("' and ");
			}

			sb.append("trashed = false");

			Drive.Files files = drive.files();

			FileList fileList = files.list().setQ(sb.toString()).execute();

			List<File> fileListItems = fileList.getItems();

			List<T> entries = new ArrayList<T>();

			for (File file : fileListItems) {
				if (_FOLDER_MIME_TYPE.equals(file.getMimeType())) {
					entries.add((T)new GoogleDriveFolder(file, _rootFolderKey));
				}
				else {
					entries.add((T)new GoogleDriveFileEntry(file));
				}
			}

			return entries;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

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
		throws PortalException, SystemException {

		Drive drive = getDrive();

		Drive.Files files = drive.files();

		try {
			File file = files.get(
				extRepositoryObject.getExtRepositoryModelKey()).execute();

			List<ParentReference> parentReferences = file.getParents();

			if (!parentReferences.isEmpty()) {
				ParentReference parentReference = parentReferences.get(0);

				File parentFile =
					drive.files().get(parentReference.getId()).execute();

				return new GoogleDriveFolder(parentFile, _rootFolderKey);
			}
		}
		catch (IOException e) {
		}

		return null;
	}

	@Override
	public String getLiferayLogin(String extRepositoryLogin) {
		return null;
	}

	@Override
	public String getRootFolderKey() throws PortalException, SystemException {
		return _rootFolderKey;
	}

	@Override
	public List<String> getSubfolderKeys(
			String extRepositoryFolderKey, boolean recurse)
		throws PortalException, SystemException {

		return null;
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

		Drive drive = getDrive();

		try {
			About about = drive.about().get().execute();

			_rootFolderKey = about.getRootFolderId();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new SystemException(ioe);
		}
	}

	@Override
	public <T extends ExtRepositoryObject> T moveExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		Drive.Files files = drive.files();

		try {
			File file = files.get(extRepositoryObjectKey).execute();

			Drive.Parents parents = drive.parents();

			List<ParentReference> parentReferences = file.getParents();

			for (ParentReference parentReference : parentReferences) {
				parents.delete(file.getId(), parentReference.getId());
			}

			ParentReference newParentReference = new ParentReference();

			newParentReference.setId(newExtRepositoryFolderKey);

			parents.insert(file.getId(), newParentReference).execute();

			if (extRepositoryObjectType.equals(
					ExtRepositoryObjectType.OBJECT)) {

				return (T)new GoogleDriveFileEntry(file);
			}
			else {
				return (T)new GoogleDriveFolder(file, _rootFolderKey);
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new SystemException(ioe);
		}
	}

	@Override
	public List<ExtRepositorySearchResult<?>> search(
			SearchContext searchContext, Query query,
			ExtRepositoryQueryMapper extRepositoryQueryMapper)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public ExtRepositoryFileEntry updateExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, String mimeType,
			InputStream inputStream)
		throws PortalException, SystemException {

		Drive drive = getDrive();

		Drive.Files files = drive.files();

		try {
			File file = files.get(extRepositoryFileEntryKey).execute();

			InputStreamContent inputStreamContent = new InputStreamContent(
				mimeType, inputStream);

			file = files.update(
				extRepositoryFileEntryKey, file, inputStreamContent).execute();

			return new GoogleDriveFileEntry(file);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new SystemException(ioe);
		}
	}

	protected File addFile(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, InputStream inputStream)
		throws PortalException, SystemException {

		File body = new File();

		body.setTitle(title);
		body.setDescription(description);
		body.setMimeType(mimeType);

		try {
			Drive drive = getDrive();

			Drive.Files files = drive.files();

			File folder = files.get(extRepositoryParentFolderKey).execute();

			body.setParents(
				Arrays.asList(new ParentReference().setId(folder.getId())));

			File file = null;

			if (inputStream != null) {
				InputStreamContent isContent = new InputStreamContent(
					mimeType, inputStream);

				file = files.insert(body, isContent).execute();
			}
			else {
				file = files.insert(body).execute();
			}

			return file;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

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
			user.getCompanyId(), "google.client.id");
		String googleClientSecret = PrefsPropsUtil.getString(
			user.getCompanyId(), "google.client.secret");

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

	protected InputStream getContentStream(String downloadUrl)
		throws PortalException, SystemException {

		if (Validator.isNull(downloadUrl)) {
			return null;
		}

		Drive drive = getDrive();

		try {
			HttpRequestFactory requestFactory = drive.getRequestFactory();

			GenericUrl genericUrl = new GenericUrl(downloadUrl);

			HttpResponse response = requestFactory.buildGetRequest(
				genericUrl).execute();

			return response.getContent();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();

			throw new SystemException(ioe);
		}
	}

	private static final String _FOLDER_MIME_TYPE =
		"application/vnd.google-apps.folder";

	private ThreadLocal<Drive> _driveThreadLocal =
		new AutoResetThreadLocal<Drive>(Drive.class.getName());
	private String _rootFolderKey;

}