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

package com.liferay.documentum.repository.model;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfTime;

import com.liferay.documentum.repository.DocumentumRepository;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.RepositoryEntry;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.RepositoryEntryLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileVersionException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.util.DLUtil;

import java.io.InputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class DocumentumFileEntry extends DocumentumModel implements FileEntry {

	public DocumentumFileEntry(
			DocumentumRepository documentumRepository, String uuid,
			long fileEntryId, IDfDocument idfDocument,
			List<DocumentumVersionLabel> documentumVersionLabel)
		throws DfException {

		_documentumRepository = documentumRepository;
		_uuid = uuid;
		_fileEntryId = fileEntryId;
		_documentumVersionLabels = documentumVersionLabel;

		_changeLog = idfDocument.getLogEntry();

		IDfTime createDateIdfTime = idfDocument.getCreationDate();

		_createDate = createDateIdfTime.getDate();

		_creatorName = idfDocument.getCreatorName();
		_lockOwner = idfDocument.getLockOwner();

		if (Validator.isNotNull(_lockOwner)) {
			_lock = new DocumentumLock(getCompanyId(), idfDocument);
		}

		IDfTime modifyDateIdfTime = idfDocument.getModifyDate();

		_modifiedDate = modifyDateIdfTime.getDate();

		_modifier = idfDocument.getModifier();
		_objectName = idfDocument.getObjectName();
		_permit = idfDocument.getPermit();
		_size = idfDocument.getContentSize();
		_versionLabel = idfDocument.getVersionLabel(0);
	}

	@Override
	public Object clone() {
		return this;
	}

	@Override
	public boolean containsPermission(
		PermissionChecker permissionChecker, String actionId) {

		return containsPermission(_permit, actionId);
	}

	@Override
	public Map<String, Serializable> getAttributes() {
		if (_attributes != null) {
			return _attributes;
		}

		try {
			_attributes = _documentumRepository.getAttributes(_fileEntryId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return _attributes;
	}

	public String getChangeLog() {
		return _changeLog;
	}

	@Override
	public long getCompanyId() {
		return _documentumRepository.getCompanyId();
	}

	@Override
	public InputStream getContentStream() throws SystemException {
		InputStream inputStream = _documentumRepository.getContentStream(
			_fileEntryId);

		try {
			DLAppHelperLocalServiceUtil.getFileAsStream(
				PrincipalThreadLocal.getUserId(), this, true);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return inputStream;
	}

	@Override
	public InputStream getContentStream(String version)
		throws PortalException, SystemException {

		if (version.equals(getVersion())) {
			return getContentStream();
		}

		DocumentumVersionLabel documentumVersionLabel = getVersionLabel(
			version);

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			documentumVersionLabel.getVersionId());

		return fileEntry.getContentStream();
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	public long getCustom1ImageId() {
		return 0;
	}

	public long getCustom2ImageId() {
		return 0;
	}

	@Override
	public String getExtension() {
		return FileUtil.getExtension(getTitle());
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public FileVersion getFileVersion() {
		return new DocumentumFileVersion(_documentumRepository, this);
	}

	@Override
	public FileVersion getFileVersion(String version)
		throws PortalException, SystemException {

		DocumentumVersionLabel documentumVersionLabel = getVersionLabel(
			version);

		FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
			documentumVersionLabel.getVersionId());

		return new DocumentumFileVersion(_documentumRepository, fileEntry);
	}

	@Override
	public List<FileVersion> getFileVersions(int status)
		throws SystemException {

		List<FileVersion> fileVersions = new ArrayList<FileVersion>();

		for (DocumentumVersionLabel documentumVersionLabel :
				_documentumVersionLabels) {

			try {
				FileEntry fileEntry = _documentumRepository.getFileEntry(
					documentumVersionLabel.getVersionId());

				FileVersion fileVersion = new DocumentumFileVersion(
					_documentumRepository, fileEntry);

				fileVersions.add(fileVersion);
			}
			catch (PortalException pe) {
				throw new RepositoryException(pe);
			}
		}

		return fileVersions;
	}

	@Override
	public Folder getFolder() {
		Folder parentFolder = null;

		try {
			parentFolder = super.getParentFolder();

			if (parentFolder != null) {
				return parentFolder;
			}

			parentFolder = DLAppLocalServiceUtil.getFolder(getParentFolderId());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		setParentFolder(parentFolder);

		return parentFolder;
	}

	@Override
	public long getFolderId() {
		return getParentFolderId();
	}

	@Override
	public long getGroupId() {
		return _documentumRepository.getGroupId();
	}

	@Override
	public String getIcon() {
		return DLUtil.getFileIcon(getExtension());
	}

	public String getImageType() {
		return null;
	}

	public long getLargeImageId() {
		return 0;
	}

	@Override
	public FileVersion getLatestFileVersion()
		throws PortalException, SystemException {

		return getLatestFileVersion(false);
	}

	public FileVersion getLatestFileVersion(boolean trusted)
		throws PortalException, SystemException {

		DocumentumVersionLabel documentumVersionLabel =
			_documentumVersionLabels.get(0);

		return getFileVersion(documentumVersionLabel.getVersionLabel());
	}

	@Override
	public Lock getLock() {
		return _lock;
	}

	@Override
	public String getMimeType() {
		return MimeTypesUtil.getContentType(getTitle());
	}

	@Override
	public String getMimeType(String version) {
		try {
			DocumentumVersionLabel documentumVersionLabel = getVersionLabel(
				version);

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(
				documentumVersionLabel.getVersionId());

			return fileEntry.getMimeType();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return StringPool.BLANK;
	}

	@Override
	public Object getModel() {
		return this;
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileEntry.class;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public String getNameWithExtension() {
		return getTitle();
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntryId;
	}

	@Override
	public int getReadCount() {
		return 0;
	}

	@Override
	public long getRepositoryId() {
		return _documentumRepository.getRepositoryId();
	}

	@Override
	public long getSize() {
		return _size;
	}

	public long getSmallImageId() {
		return 0;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(FileEntry.class);
	}

	@Override
	public String getTitle() {
		return _objectName;
	}

	@Override
	public long getUserId() {
		User user = getUser(_creatorName);

		if (user != null) {
			return user.getUserId();
		}

		return 0;
	}

	@Override
	public String getUserName() {
		User user = getUser(_creatorName);

		if (user != null) {
			return user.getFullName();
		}

		return StringPool.BLANK;
	}

	@Override
	public String getUserUuid() throws SystemException {
		User user = getUser(_creatorName);

		if (user != null) {
			return user.getUserUuid();
		}

		return StringPool.BLANK;
	}

	@Override
	public String getUuid() {
		return _uuid;
	}

	@Override
	public String getVersion() {
		return _versionLabel;
	}

	@Override
	public long getVersionUserId() {
		User user = getUser(_modifier);

		if (user != null) {
			return user.getUserId();
		}

		return 0;
	}

	@Override
	public String getVersionUserName() {
		User user = getUser(_modifier);

		if (user != null) {
			return user.getFullName();
		}

		return StringPool.BLANK;
	}

	@Override
	public String getVersionUserUuid() throws SystemException {
		User user = getUser(_modifier);

		if (user != null) {
			return user.getUserUuid();
		}

		return StringPool.BLANK;
	}

	@Override
	public boolean hasLock() {
		if (!isLocked()) {
			return false;
		}

		try {
			User user = UserLocalServiceUtil.getUser(
				GetterUtil.getLong(PrincipalThreadLocal.getName()));

			User lockUser = getUser(_lockOwner);

			if ((lockUser != null) &&
				(user.getUserId() == lockUser.getUserId())) {

				return true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	@Override
	public boolean isCheckedOut() {
		return isLocked();
	}

	@Override
	public boolean isDefaultRepository() {
		return false;
	}

	@Override
	public boolean isEscapedModel() {
		return false;
	}

	@Override
	public boolean isInTrash() {
		return false;
	}

	@Override
	public boolean isInTrashContainer() {
		return false;
	}

	@Override
	public boolean isManualCheckInRequired() {
		try {
			RepositoryEntry repositoryEntry =
				RepositoryEntryLocalServiceUtil.getRepositoryEntry(
					_fileEntryId);

			return repositoryEntry.isManualCheckInRequired();
		}
		catch (Exception e) {
			if (_log.isInfoEnabled()) {
				_log.info("Unable to retrieve repository entry", e);
			}

			return false;
		}
	}

	@Override
	public boolean isSupportsLocking() {
		return true;
	}

	@Override
	public boolean isSupportsMetadata() {
		return true;
	}

	@Override
	public boolean isSupportsSocial() {
		return true;
	}

	public void prepare() {
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public FileEntry toEscapedModel() {
		return this;
	}

	@Override
	public FileEntry toUnescapedModel() {
		return this;
	}

	protected DocumentumVersionLabel getVersionLabel(String version)
		throws PortalException {

		for (DocumentumVersionLabel documentumVersionLabel :
				_documentumVersionLabels) {

			if (version.equals(documentumVersionLabel.getVersionLabel())) {
				return documentumVersionLabel;
			}
		}

		throw new NoSuchFileVersionException();
	}

	protected boolean isLocked() {
		return Validator.isNotNull(_lockOwner);
	}

	private static Log _log = LogFactoryUtil.getLog(DocumentumFileEntry.class);

	private Map<String, Serializable> _attributes;
	private String _changeLog;
	private Date _createDate;
	private String _creatorName;
	private DocumentumRepository _documentumRepository;
	private List<DocumentumVersionLabel> _documentumVersionLabels;
	private long _fileEntryId;
	private Lock _lock;
	private String _lockOwner;
	private Date _modifiedDate;
	private String _modifier;
	private String _objectName;
	private int _permit;
	private long _size;
	private String _uuid;
	private String _versionLabel;

}