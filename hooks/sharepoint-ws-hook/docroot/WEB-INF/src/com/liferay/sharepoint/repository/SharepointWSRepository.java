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

package com.liferay.sharepoint.repository;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.CompanyConstants;
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
import com.liferay.sharepoint.connector.SharepointConnection;
import com.liferay.sharepoint.connector.SharepointConnectionFactory;
import com.liferay.sharepoint.connector.SharepointException;
import com.liferay.sharepoint.connector.SharepointObject;
import com.liferay.sharepoint.connector.SharepointRuntimeException;
import com.liferay.sharepoint.connector.operation.PathHelper;
import com.liferay.sharepoint.repository.model.SharepointWSFileEntry;
import com.liferay.sharepoint.repository.model.SharepointWSFolder;

import java.io.InputStream;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author Ivan Zaera
 */
public class SharepointWSRepository
	extends ExtRepositoryAdapter implements ExtRepository {

	public SharepointWSRepository() {
		super(null);
	}

	@Override
	public ExtRepositoryFileEntry addExtRepositoryFileEntry(
			String extRepositoryParentFolderKey, String mimeType, String title,
			String description, String changeLog, InputStream inputStream)
		throws PortalException, SystemException {

		try {
			SharepointConnection sharepointConnection =
				getSharepointConnection();

			SharepointObject parentFolderSharepointObject =
				sharepointConnection.getSharepointObject(
					toSharepointObjectId(extRepositoryParentFolderKey));

			String parentFolderPath = parentFolderSharepointObject.getPath();

			sharepointConnection.addFile(
				parentFolderPath, title, changeLog, inputStream);

			String filePath = pathHelper.buildPath(parentFolderPath, title);

			SharepointObject fileSharepointObject =
				sharepointConnection.getSharepointObject(filePath);

			return new SharepointWSFileEntry(fileSharepointObject);
		}
		catch (SharepointException se) {
			throw new PortalException(se);
		}
		catch (SharepointRuntimeException sre) {
			throw new SystemException(sre);
		}
	}

	@Override
	public ExtRepositoryFolder addExtRepositoryFolder(
			String extRepositoryParentFolderKey, String name,
			String description)
		throws PortalException, SystemException {

		try {
			SharepointConnection sharepointConnection =
				getSharepointConnection();

			SharepointObject parentFolderSharepointObject =
				sharepointConnection.getSharepointObject(
					toSharepointObjectId(extRepositoryParentFolderKey));

			String parentFolderPath = parentFolderSharepointObject.getPath();

			sharepointConnection.addFolder(parentFolderPath, name);

			String folderPath = pathHelper.buildPath(parentFolderPath, name);

			SharepointObject folderSharepointObject =
				sharepointConnection.getSharepointObject(folderPath);

			return new SharepointWSFolder(folderSharepointObject);
		}
		catch (SharepointException se) {
			throw new PortalException(se);
		}
		catch (SharepointRuntimeException sre) {
			throw new SystemException(sre);
		}
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
		return CompanyConstants.AUTH_TYPE_SN;
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
		throws PortalException, SystemException {

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
		return _SUPPORTED_CONFIGURATIONS;
	}

	@Override
	public String[][] getSupportedParameters() {
		return _SUPPORTED_PARAMETERS;
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

	protected SharepointConnection buildSharepointConnection()
		throws RepositoryException {

		try {
			int serverPort = 80;

			if (_protocol.equals("https")) {
				serverPort = 443;
			}

			return SharepointConnectionFactory.getInstance(
				_protocol, _host, serverPort, _sitePath, _libraryName,
				_credentialsProvider.getLogin(),
				_credentialsProvider.getPassword());
		}
		catch (SharepointRuntimeException sre) {
			throw new RepositoryException(
				"Unable to communicate with the Sharepoint server", sre);
		}
	}

	protected SharepointConnection getSharepointConnection()
		throws RepositoryException {

		SharepointConnection sharepointConnection = null;

		HttpSession httpSession = PortalSessionThreadLocal.getHttpSession();

		if (httpSession != null) {
			TransientValue<SharepointConnection> transientValue =
				(TransientValue<SharepointConnection>)
					httpSession.getAttribute(
						SharepointWSRepository.class.getName());

			if (transientValue != null) {
				sharepointConnection = transientValue.getValue();
			}
		}
		else {
			sharepointConnection = _sharepointConnectionThreadLocal.get();
		}

		if (sharepointConnection != null) {
			return sharepointConnection;
		}

		sharepointConnection = buildSharepointConnection();

		if (httpSession != null) {
			TransientValue<SharepointConnection> transientValue =
				new TransientValue<SharepointConnection>(sharepointConnection);

			httpSession.setAttribute(
				SharepointWSRepository.class.getName(), transientValue);
		}

		_sharepointConnectionThreadLocal.set(sharepointConnection);

		return sharepointConnection;
	}

	protected long toSharepointObjectId(String key) {
		return GetterUtil.getLong(key);
	}

	protected static PathHelper pathHelper = new PathHelper();

	private static final String _CONFIGURATION_WS = "SHAREPOINT_WS";

	private static final String _LIBRARY_NAME = "LIBRARY_NAME";

	private static final String _SITE_URL = "SITE_URL";

	private static final String[] _SUPPORTED_CONFIGURATIONS =
		{_CONFIGURATION_WS};

	private static final String[][] _SUPPORTED_PARAMETERS =
		{{_SITE_URL, _LIBRARY_NAME}};

	private CredentialsProvider _credentialsProvider;
	private String _host;
	private String _libraryName;
	private String _protocol;
	private AutoResetThreadLocal<SharepointConnection>
		_sharepointConnectionThreadLocal =
			new AutoResetThreadLocal<SharepointConnection>(
				SharepointConnection.class.getName());
	private String _sitePath;

}