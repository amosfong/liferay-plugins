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
import com.liferay.portal.kernel.repository.cmis.CMISRepositoryHandler;
import com.liferay.portal.kernel.repository.cmis.CMISRepositoryUtil;
import com.liferay.portal.kernel.repository.cmis.Session;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Query;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.RepositoryEntryUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;

import com.microsoft.schemas.sharepoint.soap.ListsLocator;
import com.microsoft.schemas.sharepoint.soap.ListsSoap;
import com.microsoft.schemas.sharepoint.soap.RestoreVersionResponseRestoreVersionResult;
import com.microsoft.schemas.sharepoint.soap.UpdateListItemsResponseUpdateListItemsResult;
import com.microsoft.schemas.sharepoint.soap.UpdateListItemsUpdates;
import com.microsoft.schemas.sharepoint.soap.VersionsLocator;
import com.microsoft.schemas.sharepoint.soap.VersionsSoap;

import java.io.InputStream;

import java.net.URL;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Stub;
import org.apache.axis.message.MessageElement;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.IncludeRelationships;

/**
 * @author Jonathan Lee
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class SharepointRepository extends CMISRepositoryHandler {

	@Override
	public FileVersion cancelCheckOut(long fileEntryId)
		throws PortalException, SystemException {

		String objectId = toObjectId(fileEntryId);

		String latestVersionId = getLatestVersionId(objectId);

		try {
			ListsSoap listsSoap = getListsSoap();

			String path = getPath(latestVersionId);

			if (!listsSoap.undoCheckOut(path)) {
				throw new RepositoryException(
					"Unable to cancel check out for file entry " + fileEntryId);
			}

			latestVersionId = getLatestVersionId(objectId);

			FileEntry fileEntry = toFileEntry(latestVersionId);

			return fileEntry.getFileVersion();
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, boolean major, String changeLog,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		clearManualCheckInRequired(fileEntryId, serviceContext);

		try {
			ListsSoap listsSoap = getListsSoap();

			String objectId = toObjectId(fileEntryId);

			String path = getPath(objectId);

			String checkinType = "0";

			if (major) {
				checkinType = "1";
			}

			if (!listsSoap.checkInFile(path, changeLog, checkinType)) {
				throw new RepositoryException(
					"Unable to check in file entry " + fileEntryId);
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}
	}

	@Override
	public void checkInFileEntry(
			long fileEntryId, String lockUuid, ServiceContext serviceContext)
		throws PortalException, SystemException {

		checkInFileEntry(fileEntryId, false, StringPool.BLANK, serviceContext);
	}

	@Override
	public FileEntry checkOutFileEntry(
			long fileEntryId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		setManualCheckInRequired(fileEntryId, serviceContext);

		String objectId = toObjectId(fileEntryId);

		try {
			ListsSoap listsSoap = getListsSoap();

			String path = getPath(objectId);

			if (!listsSoap.checkOutFile(path, "false", null)) {
				throw new RepositoryException(
					"Unable to check out file entry " + fileEntryId);
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}

		return toFileEntry(objectId);
	}

	@Override
	public void deleteFileEntry(long fileEntryId)
		throws PortalException, SystemException {

		try {
			MessageElement batchMessageElement = buildBatchMessageElement();

			MessageElement methodMessageElement = buildMethodMessageElement(
				"Delete");

			String objectId = toObjectId(fileEntryId);

			MessageElement idMessageElement = buildFieldMessageElement(
				"ID", StringUtil.extractFirst(objectId, StringPool.DASH));

			methodMessageElement.addChild(idMessageElement);

			MessageElement fileRefMessageElement = buildFieldMessageElement(
				"FileRef", getPath(objectId));

			methodMessageElement.addChild(fileRefMessageElement);

			batchMessageElement.addChild(methodMessageElement);

			UpdateListItemsUpdates updateListItemsUpdates =
				new UpdateListItemsUpdates(
					new MessageElement[] {batchMessageElement});

			ListsSoap listsSoap = getListsSoap();

			UpdateListItemsResponseUpdateListItemsResult
				updateListItemsResponseUpdateListItemsResult =
					listsSoap.updateListItems(
						getTypeSettingsValue(_REPOSITORY_ID),
						updateListItemsUpdates);

			String errorCode = getErrorCode(
				updateListItemsResponseUpdateListItemsResult.get_any(),
				"1,Delete");

			if (!errorCode.equals(_ERROR_CODE_DEFAULT)) {
				throw new RepositoryException(
					"Unable to delete file entry " + fileEntryId + ": " +
						errorCode);
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}
	}

	@Override
	public void deleteFolder(long folderId)
		throws PortalException, SystemException {

		try {
			MessageElement batchMessageElement = buildBatchMessageElement();

			MessageElement methodMessageElement = buildMethodMessageElement(
				"Delete");

			String objectId = toObjectId(folderId);

			MessageElement idMessageElement = buildFieldMessageElement(
				"ID", objectId);

			methodMessageElement.addChild(idMessageElement);

			MessageElement fileRefMessageElement = buildFieldMessageElement(
				"FileRef", getPath(objectId));

			methodMessageElement.addChild(fileRefMessageElement);

			batchMessageElement.addChild(methodMessageElement);

			UpdateListItemsUpdates updateListItemsUpdates =
				new UpdateListItemsUpdates(
					new MessageElement[] {batchMessageElement});

			ListsSoap listsSoap = getListsSoap();

			UpdateListItemsResponseUpdateListItemsResult
				updateListItemsResponseUpdateListItemsResult =
					listsSoap.updateListItems(
						getTypeSettingsValue(_REPOSITORY_ID),
						updateListItemsUpdates);

			String errorCode = getErrorCode(
				updateListItemsResponseUpdateListItemsResult.get_any(),
				"1,Delete");

			if (!errorCode.equals(_ERROR_CODE_DEFAULT)) {
				throw new RepositoryException(
					"Unable to delete folder " + folderId + ": " + errorCode);
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}
	}

	@Override
	public Session getSession() throws SystemException {
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put(
			SessionParameter.ATOMPUB_URL, getTypeSettingsValue(_ATOMPUB_URL));
		parameters.put(
			SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		Locale locale = LocaleUtil.getDefault();

		parameters.put(
			SessionParameter.LOCALE_ISO3166_COUNTRY, locale.getCountry());
		parameters.put(
			SessionParameter.LOCALE_ISO639_LANGUAGE, locale.getLanguage());

		parameters.put(
			SessionParameter.PASSWORD, PrincipalThreadLocal.getPassword());
		parameters.put(SessionParameter.USER, getLogin());

		CMISRepositoryUtil.checkRepository(
			getRepositoryId(), parameters, getTypeSettingsProperties(),
			_REPOSITORY_ID);

		Session session = CMISRepositoryUtil.createSession(parameters);

		if (session == null) {
			throw new RepositoryException("Unable to create session");
		}

		session.setDefaultContext(
			null, true, true, false, IncludeRelationships.NONE.value(), null,
			false, "cmis:name ASC", true, 1000);

		return session;
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
	public boolean isDocumentRetrievableByVersionSeriesId() {
		return false;
	}

	public boolean isExtensionRequiredInTitle() {
		return true;
	}

	@Override
	public boolean isRefreshBeforePermissionCheck() {
		return true;
	}

	@Override
	public FileEntry moveFileEntry(
			long fileEntryId, long newFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			MessageElement batchMessageElement = buildBatchMessageElement();

			MessageElement methodMessageElement = buildMethodMessageElement(
				"Move");

			String fileEntryIdObjectId = toObjectId(fileEntryId);

			MessageElement idMessageElement = buildFieldMessageElement(
				"ID", fileEntryIdObjectId);

			methodMessageElement.addChild(idMessageElement);

			MessageElement fileRefMessageElement = buildFieldMessageElement(
				"FileRef", getPath(fileEntryIdObjectId));

			methodMessageElement.addChild(fileRefMessageElement);

			String newFolderIdObjectId = toObjectId(newFolderId);

			MessageElement moveNewUrlMessageElement = buildFieldMessageElement(
				"MoveNewUrl",
				getPath(newFolderIdObjectId, fileEntryIdObjectId));

			methodMessageElement.addChild(moveNewUrlMessageElement);

			batchMessageElement.addChild(methodMessageElement);

			UpdateListItemsUpdates updateListItemsUpdates =
				new UpdateListItemsUpdates(
					new MessageElement[] {batchMessageElement});

			ListsSoap listsSoap = getListsSoap();

			UpdateListItemsResponseUpdateListItemsResult
				updateListItemsResponseUpdateListItemsResult =
					listsSoap.updateListItems(
						getTypeSettingsValue(_REPOSITORY_ID),
						updateListItemsUpdates);

			String errorCode = getErrorCode(
				updateListItemsResponseUpdateListItemsResult.get_any(),
				"1,Move");

			if (!errorCode.equals(_ERROR_CODE_DEFAULT)) {
				throw new RepositoryException(
					"Unable to move file entry " + fileEntryId + ": " +
						errorCode);
			}

			return toFileEntry(fileEntryIdObjectId);
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}
	}

	@Override
	public Folder moveFolder(
			long folderId, long parentFolderId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			MessageElement batchMessageElement = buildBatchMessageElement();

			MessageElement methodMessageElement = buildMethodMessageElement(
				"Move");

			String folderIdObjectId = toObjectId(folderId);

			MessageElement idMessageElement = buildFieldMessageElement(
				"ID", folderIdObjectId);

			methodMessageElement.addChild(idMessageElement);

			MessageElement fileRefMessageElement = buildFieldMessageElement(
				"FileRef", getPath(folderIdObjectId));

			methodMessageElement.addChild(fileRefMessageElement);

			String parentFolderIdObjectId = toObjectId(parentFolderId);

			MessageElement moveNewUrlMessageElement = buildFieldMessageElement(
				"MoveNewUrl",
				getPath(parentFolderIdObjectId, folderIdObjectId));

			methodMessageElement.addChild(moveNewUrlMessageElement);

			batchMessageElement.addChild(methodMessageElement);

			UpdateListItemsUpdates updates = new UpdateListItemsUpdates(
				new MessageElement[] {batchMessageElement});

			ListsSoap listsSoap = getListsSoap();

			UpdateListItemsResponseUpdateListItemsResult
				updateListItemsResponseUpdateListItemsResult =
					listsSoap.updateListItems(
						getTypeSettingsValue(_REPOSITORY_ID), updates);

			String errorCode = getErrorCode(
				updateListItemsResponseUpdateListItemsResult.get_any(),
				"1,Move");

			if (!errorCode.equals(_ERROR_CODE_DEFAULT)) {
				throw new RepositoryException(
					"Unable to move folder " + folderId + ": " + errorCode);
			}

			return toFolder(folderIdObjectId);
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}
	}

	@Override
	public void revertFileEntry(
			long fileEntryId, String version, ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			String objectId = toObjectId(fileEntryId);

			if (isCheckOutAllowable(objectId)) {
				checkOutFileEntry(fileEntryId, serviceContext);
			}

			VersionsSoap versionsSoap = getVersionsSoap();

			String path = getPath(objectId);

			RestoreVersionResponseRestoreVersionResult
				restoreVersionResponseRestoreVersionResult =
					versionsSoap.restoreVersion(path, version);

			String errorCode = getErrorCode(
				restoreVersionResponseRestoreVersionResult.get_any(),
				"1,RestoreVersion");

			if (!errorCode.equals(_ERROR_CODE_DEFAULT)) {
				throw new RepositoryException(
					"Unable to revert file entry " + fileEntryId + ": " +
						errorCode);
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}
	}

	@Override
	public Hits search(SearchContext searchContext) throws SearchException {
		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setScoreEnabled(false);

		return super.search(searchContext);
	}

	@Override
	public Hits search(SearchContext searchContext, Query query)
		throws SearchException {

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.setScoreEnabled(false);

		return super.search(searchContext, query);
	}

	@Override
	public FileEntry updateFileEntry(
			long fileEntryId, String sourceFileName, String mimeType,
			String title, String description, String changeLog,
			boolean majorVersion, InputStream is, long size,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		try {
			String objectId = toObjectId(fileEntryId);

			FileEntry fileEntry = toFileEntry(objectId);

			boolean autoCheckIn = !fileEntry.isCheckedOut();

			if (!fileEntry.hasLock()) {
				checkOutFileEntry(fileEntryId, serviceContext);
			}

			String checkOutDocumentObjectId = getLatestVersionId(objectId);

			Map<String, Object> properties = null;

			String objectName = getObjectName(objectId);

			if (Validator.isNotNull(title) && !title.equals(objectName)) {
				properties = new HashMap<String, Object>();

				properties.put(PropertyIds.NAME, title);
			}

			updateFileEntry(
				checkOutDocumentObjectId, mimeType, properties, is,
				sourceFileName, size, serviceContext);

			fileEntry = toFileEntry(checkOutDocumentObjectId);

			if (autoCheckIn) {
				checkInFileEntry(
					fileEntryId, majorVersion, changeLog, serviceContext);
			}

			return fileEntry;
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}
	}

	protected MessageElement buildBatchMessageElement() {
		QName qName = new QName(StringPool.BLANK, "Batch");

		return new MessageElement(qName);
	}

	protected MessageElement buildFieldMessageElement(
			String name, String textNode)
		throws Exception {

		QName qName = new QName(StringPool.BLANK, "Field");

		MessageElement messageElement = new MessageElement(qName);

		messageElement.addAttribute(StringPool.BLANK, "Name", name);

		messageElement.addTextNode(textNode);

		return messageElement;
	}

	protected MessageElement buildMethodMessageElement(String cmd) {
		QName qName = new QName(StringPool.BLANK, "Method");

		MessageElement messageElement = new MessageElement(qName);

		messageElement.addAttribute(StringPool.BLANK, "Cmd", cmd);
		messageElement.addAttribute(StringPool.BLANK, "ID", "1");

		return messageElement;
	}

	protected String getErrorCode(MessageElement[] messageElements, String id) {
		for (MessageElement messageElement : messageElements) {
			QName resultQName = new QName(
				"http://schemas.microsoft.com/sharepoint/soap/", "Result");

			Iterator<MessageElement> resultIterator =
				messageElement.getChildElements(resultQName);

			while (resultIterator.hasNext()) {
				MessageElement resultMessageElement = resultIterator.next();

				if (!id.equals(resultMessageElement.getAttribute("ID"))) {
					continue;
				}

				QName errorCodeQName = new QName(
					"http://schemas.microsoft.com/sharepoint/soap/",
					"ErrorCode");

				Iterator<MessageElement> errorCodeIterator =
					resultMessageElement.getChildElements(errorCodeQName);

				if (!errorCodeIterator.hasNext()) {
					continue;
				}

				MessageElement errorCodeMessageElement =
					errorCodeIterator.next();

				return errorCodeMessageElement.getValue();
			}
		}

		return _ERROR_CODE_DEFAULT;
	}

	protected URL getListsServiceURL() throws Exception {
		if (_listsServiceURL != null) {
			return _listsServiceURL;
		}

		String atomPubURL = getTypeSettingsValue(_ATOMPUB_URL);

		String servicesURL = StringUtil.extractFirst(atomPubURL, "/cmis");

		_listsServiceURL = new URL(servicesURL.concat("/Lists.asmx"));

		return _listsServiceURL;
	}

	protected ListsSoap getListsSoap() throws Exception {
		ListsLocator listsLocator = new ListsLocator();

		ListsSoap listsSoap = listsLocator.getListsSoap(getListsServiceURL());

		Stub stub = (Stub)listsSoap;

		stub._setProperty(
			Call.PASSWORD_PROPERTY, PrincipalThreadLocal.getPassword());
		stub._setProperty(Call.USERNAME_PROPERTY, getLogin());

		return listsSoap;
	}

	protected String getPath(String objectId)
		throws PortalException, SystemException {

		try {
			List<String> objectPaths = getObjectPaths(objectId);

			String objectPath = objectPaths.get(0);

			String sitePath = getTypeSettingsValue(_SITE_PATH);

			return sitePath.concat(objectPath);
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}

			throw new RepositoryException(e);
		}
	}

	protected String getPath(String folderId, String objectId)
		throws PortalException, SystemException {

		String path = getPath(folderId);

		if (StringUtil.endsWith(path, StringPool.SLASH)) {
			return path.concat(getObjectName(objectId));
		}
		else {
			return path.concat(StringPool.SLASH).concat(
				getObjectName(objectId));
		}
	}

	protected String getTypeSettingsValue(String typeSettingsKey) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return CMISRepositoryUtil.getTypeSettingsValue(
			typeSettingsProperties, typeSettingsKey);
	}

	protected URL getVersionsServiceURL() throws Exception {
		if (_versionsServiceURL != null) {
			return _versionsServiceURL;
		}

		String atomPubURL = getTypeSettingsValue(_ATOMPUB_URL);

		String servicesURL = StringUtil.extractFirst(atomPubURL, "/cmis");

		_versionsServiceURL = new URL(servicesURL.concat("/Versions.asmx"));

		return _versionsServiceURL;
	}

	protected VersionsSoap getVersionsSoap() throws Exception {
		VersionsLocator versionsLocator = new VersionsLocator();

		VersionsSoap versionsSoap = versionsLocator.getVersionsSoap(
			getVersionsServiceURL());

		Stub stub = (Stub)versionsSoap;

		stub._setProperty(
			Call.PASSWORD_PROPERTY, PrincipalThreadLocal.getPassword());
		stub._setProperty(Call.USERNAME_PROPERTY, getLogin());

		return versionsSoap;
	}

	protected String toObjectId(long repositoryEntryId)
		throws PortalException, SystemException {

		RepositoryEntry repositoryEntry = RepositoryEntryUtil.fetchByPrimaryKey(
			repositoryEntryId);

		if (repositoryEntry == null) {
			throw new NoSuchFileEntryException(
				"No CMIS file entry with {repositoryEntryId=" +
					repositoryEntryId + "}");
		}

		return repositoryEntry.getMappedId();
	}

	private static final String _ATOMPUB_URL = "ATOMPUB_URL";

	private static final String _CONFIGURATION_ATOMPUB = "SHAREPOINT_ATOMPUB";

	private static final String _ERROR_CODE_DEFAULT = "0x00000000";

	private static final String _REPOSITORY_ID = "REPOSITORY_ID";

	private static final String _SITE_PATH = "SITE_PATH";

	private static final String[] _SUPPORTED_CONFIGURATIONS = {
		_CONFIGURATION_ATOMPUB
	};

	private static final String[][] _SUPPORTED_PARAMETERS = {
		{_ATOMPUB_URL, _REPOSITORY_ID, _SITE_PATH}
	};

	private URL _listsServiceURL;
	private URL _versionsServiceURL;

}