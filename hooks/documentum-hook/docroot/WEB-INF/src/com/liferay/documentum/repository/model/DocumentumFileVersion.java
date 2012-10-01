/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.documentum.repository.DocumentumRepository;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portlet.documentlibrary.model.DLFileVersion;
import com.liferay.portlet.documentlibrary.service.DLAppHelperLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class DocumentumFileVersion
	extends DocumentumModel implements FileVersion {

	public DocumentumFileVersion(
		DocumentumRepository documentumRepository, FileEntry fileEntry) {

		_documentumRepository = documentumRepository;
		_fileEntry = fileEntry;
	}

	public Map<String, Serializable> getAttributes() {
		return _fileEntry.getAttributes();
	}

	public String getChangeLog() {
		if (!(_fileEntry instanceof DocumentumFileEntry)) {
			return StringPool.BLANK;
		}

		DocumentumFileEntry documentumFileEntry =
			(DocumentumFileEntry)_fileEntry;

		return documentumFileEntry.getChangeLog();
	}

	@Override
	public long getCompanyId() {
		return _fileEntry.getCompanyId();
	}

	public InputStream getContentStream(boolean incrementCounter)
		throws SystemException {

		InputStream inputStream = _documentumRepository.getContentStream(
			_fileEntry.getFileEntryId());

		try {
			DLAppHelperLocalServiceUtil.getFileAsStream(
				PrincipalThreadLocal.getUserId(), getFileEntry(),
				incrementCounter);
		}
		catch (Exception e) {
			_log.error(e);
		}

		return inputStream;
	}

	public Date getCreateDate() {
		return _fileEntry.getCreateDate();
	}

	public long getCustom1ImageId() {
		return 0;
	}

	public long getCustom2ImageId() {
		return 0;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	public String getExtension() {
		return FileUtil.getExtension(getTitle());
	}

	public String getExtraSettings() {
		return null;
	}

	public FileEntry getFileEntry() {
		return _fileEntry;
	}

	public long getFileEntryId() {
		try {
			return getFileEntry().getFileEntryId();
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return 0;
	}

	public long getFileVersionId() {
		return _fileEntry.getFileEntryId();
	}

	public long getGroupId() {
		return _fileEntry.getGroupId();
	}

	public String getIcon() {
		return _fileEntry.getIcon();
	}

	public String getImageType() {
		return null;
	}

	public long getLargeImageId() {
		return 0;
	}

	public String getMimeType() {
		return _fileEntry.getMimeType();
	}

	public Object getModel() {
		return _fileEntry.getModel();
	}

	@Override
	public Class<?> getModelClass() {
		return DLFileVersion.class;
	}

	public Date getModifiedDate() {
		return _fileEntry.getModifiedDate();
	}

	@Override
	public long getPrimaryKey() {
		return _fileEntry.getPrimaryKey();
	}

	public long getRepositoryId() {
		return _fileEntry.getRepositoryId();
	}

	public long getSize() {
		return _fileEntry.getSize();
	}

	public long getSmallImageId() {
		return 0;
	}

	public int getStatus() {
		return 0;
	}

	public long getStatusByUserId() {
		return _fileEntry.getVersionUserId();
	}

	public String getStatusByUserName() {
		return _fileEntry.getVersionUserName();
	}

	public String getStatusByUserUuid() throws SystemException {
		return _fileEntry.getVersionUserUuid();
	}

	public Date getStatusDate() {
		return _fileEntry.getModifiedDate();
	}

	public String getTitle() {
		return _fileEntry.getTitle();
	}

	public long getUserId() {
		return _fileEntry.getUserId();
	}

	public String getUserName() {
		return _fileEntry.getUserName();
	}

	public String getUserUuid() throws SystemException {
		return _fileEntry.getUserUuid();
	}

	public String getUuid() {
		return _fileEntry.getUuid();
	}

	public String getVersion() {
		return _fileEntry.getVersion();
	}

	public boolean isApproved() {
		return false;
	}

	public boolean isDefaultRepository() {
		return false;
	}

	public boolean isDraft() {
		return false;
	}

	public boolean isEscapedModel() {
		return false;
	}

	public boolean isExpired() {
		return false;
	}

	public boolean isInTrash() {
		return false;
	}

	public boolean isPending() {
		return false;
	}

	public void prepare() {
	}

	public void setUserUuid(String userUuid) {
	}

	public FileVersion toEscapedModel() {
		return this;
	}

	private static Log _log = LogFactoryUtil.getLog(
		DocumentumFileVersion.class);

	private DocumentumRepository _documentumRepository;
	private FileEntry _fileEntry;

}