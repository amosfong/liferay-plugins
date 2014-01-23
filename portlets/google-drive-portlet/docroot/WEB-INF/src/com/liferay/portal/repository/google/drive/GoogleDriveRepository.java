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

package com.liferay.portal.repository.google.drive;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.repository.external.CredentialsProvider;
import com.liferay.repository.external.ExtRepository;
import com.liferay.repository.external.ExtRepositoryFileEntry;
import com.liferay.repository.external.ExtRepositoryFileVersion;
import com.liferay.repository.external.ExtRepositoryFileVersionDescriptor;
import com.liferay.repository.external.ExtRepositoryFolder;
import com.liferay.repository.external.ExtRepositoryObject;
import com.liferay.repository.external.ExtRepositoryObjectType;
import com.liferay.repository.external.ExtRepositorySearchResult;
import com.liferay.repository.external.search.ExtRepositoryQueryMapper;

import java.io.InputStream;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Sergio González
 */
public class GoogleDriveRepository implements ExtRepository {

	@Override
	public ExtRepositoryFileEntry addExtRepositoryFileEntry(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, String changeLog, InputStream inputStream)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public ExtRepositoryFolder addExtRepositoryFolder(
			String extRepositoryParentFolderKey, String name,
			String description)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public ExtRepositoryFileVersion cancelCheckOut(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public void checkInExtRepositoryFileEntry(
			String extRepositoryFileEntryKey, boolean createMajorVersion,
			String changeLog)
		throws PortalException, SystemException {
	}

	@Override
	public ExtRepositoryFileEntry checkOutExtRepositoryFileEntry(
			String extRepositoryFileEntryKey)
		throws PortalException, SystemException {

		return null;
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
	}

	@Override
	public String getAuthType() {
		return null;
	}

	@Override
	public InputStream getContentStream(
			ExtRepositoryFileEntry extRepositoryFileEntry)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public InputStream getContentStream(
			ExtRepositoryFileVersion extRepositoryFileVersion)
		throws PortalException, SystemException {

		return null;
	}

	public Drive getDrive() throws PortalException {
		HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

		Drive drive = null;

		if (httpSession != null) {
			TransientValue<Drive> transientValue =
				(TransientValue<Drive>)httpSession.getAttribute(_SESSION_KEY);

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

		return createDrive();
	}

	@Override
	public ExtRepositoryFileVersion getExtRepositoryFileVersion(
			ExtRepositoryFileEntry extRepositoryFileEntry, String version)
		throws PortalException, SystemException {

		return null;
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

		return null;
	}

	@Override
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public <T extends ExtRepositoryObject> T getExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey, String title)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public <T extends ExtRepositoryObject> List<T> getExtRepositoryObjects(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public int getExtRepositoryObjectsCount(
			ExtRepositoryObjectType<? extends ExtRepositoryObject>
				extRepositoryObjectType,
			String extRepositoryFolderKey)
		throws PortalException, SystemException {

		return 0;
	}

	@Override
	public ExtRepositoryFolder getExtRepositoryParentFolder(
			ExtRepositoryObject extRepositoryObject)
		throws PortalException, SystemException {

		return null;
	}

	@Override
	public String getLiferayLogin(String extRepositoryLogin) {
		return null;
	}

	@Override
	public String getRootFolderKey() throws PortalException, SystemException {
		return null;
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
	}

	@Override
	public <T extends ExtRepositoryObject> T moveExtRepositoryObject(
			ExtRepositoryObjectType<T> extRepositoryObjectType,
			String extRepositoryObjectKey, String newExtRepositoryFolderKey,
			String newTitle)
		throws PortalException, SystemException {

		return null;
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

		return null;
	}

	protected Drive createDrive() throws PortalException {
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();

		long userId = PrincipalThreadLocal.getUserId();

		try {
			User user = UserLocalServiceUtil.getUser(userId);

			if (user.isDefaultUser()) {
				throw new PrincipalException("User is not logged in");
			}

			String googleClientId = PrefsPropsUtil.getString(
				user.getCompanyId(), "google.client.id");
			String googleClientSecret = PrefsPropsUtil.getString(
				user.getCompanyId(), "google.client.secret");

			GoogleCredential.Builder builder = new GoogleCredential.Builder();

			builder.setTransport(httpTransport);
			builder.setJsonFactory(jsonFactory);
			builder.setClientSecrets(googleClientId, googleClientSecret);

			GoogleCredential googleCredential = builder.build();

			ExpandoBridge expandoBridge = user.getExpandoBridge();

			String googleAccessToken = GetterUtil.getString(
				expandoBridge.getAttribute("googleAccessToken", false));
			String googleRefreshToken = GetterUtil.getString(
				expandoBridge.getAttribute("googleRefreshToken", false));

			googleCredential.setAccessToken(googleAccessToken);
			googleCredential.setRefreshToken(googleRefreshToken);

			Drive.Builder driveBuilder = new Drive.Builder(
				httpTransport, jsonFactory, googleCredential);

			Drive drive = driveBuilder.build();

			HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

			if (httpSession != null) {
				httpSession.setAttribute(
					_SESSION_KEY, new TransientValue<Drive>(drive));
			}
			else {
				_driveThreadLocal.set(drive);
			}

			return drive;
		}
		catch (Exception e) {
			throw new PrincipalException(e);
		}
	}

	private static final String _SESSION_KEY =
		GoogleDriveRepository.class.getName() + ".drive";

	private AutoResetThreadLocal<Drive> _driveThreadLocal =
		new AutoResetThreadLocal<Drive>(Drive.class.getName());

}