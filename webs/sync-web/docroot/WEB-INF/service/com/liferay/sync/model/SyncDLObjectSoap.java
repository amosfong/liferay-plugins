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

package com.liferay.sync.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.sync.service.http.SyncDLObjectServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.sync.service.http.SyncDLObjectServiceSoap
 * @generated
 */
public class SyncDLObjectSoap implements Serializable {
	public static SyncDLObjectSoap toSoapModel(SyncDLObject model) {
		SyncDLObjectSoap soapModel = new SyncDLObjectSoap();

		soapModel.setObjectId(model.getObjectId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFileId(model.getFileId());
		soapModel.setFileUuid(model.getFileUuid());
		soapModel.setRepositoryId(model.getRepositoryId());
		soapModel.setParentFolderId(model.getParentFolderId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setChecksum(model.getChecksum());
		soapModel.setEvent(model.getEvent());
		soapModel.setLockUserId(model.getLockUserId());
		soapModel.setLockUserName(model.getLockUserName());
		soapModel.setSize(model.getSize());
		soapModel.setType(model.getType());
		soapModel.setVersion(model.getVersion());

		return soapModel;
	}

	public static SyncDLObjectSoap[] toSoapModels(SyncDLObject[] models) {
		SyncDLObjectSoap[] soapModels = new SyncDLObjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SyncDLObjectSoap[][] toSoapModels(SyncDLObject[][] models) {
		SyncDLObjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SyncDLObjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SyncDLObjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SyncDLObjectSoap[] toSoapModels(List<SyncDLObject> models) {
		List<SyncDLObjectSoap> soapModels = new ArrayList<SyncDLObjectSoap>(models.size());

		for (SyncDLObject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SyncDLObjectSoap[soapModels.size()]);
	}

	public SyncDLObjectSoap() {
	}

	public long getPrimaryKey() {
		return _objectId;
	}

	public void setPrimaryKey(long pk) {
		setObjectId(pk);
	}

	public long getObjectId() {
		return _objectId;
	}

	public void setObjectId(long objectId) {
		_objectId = objectId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	public long getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;
	}

	public String getFileUuid() {
		return _fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		_fileUuid = fileUuid;
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	public long getParentFolderId() {
		return _parentFolderId;
	}

	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getChecksum() {
		return _checksum;
	}

	public void setChecksum(String checksum) {
		_checksum = checksum;
	}

	public String getEvent() {
		return _event;
	}

	public void setEvent(String event) {
		_event = event;
	}

	public long getLockUserId() {
		return _lockUserId;
	}

	public void setLockUserId(long lockUserId) {
		_lockUserId = lockUserId;
	}

	public String getLockUserName() {
		return _lockUserName;
	}

	public void setLockUserName(String lockUserName) {
		_lockUserName = lockUserName;
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	private long _objectId;
	private long _companyId;
	private long _createDate;
	private long _modifiedDate;
	private long _fileId;
	private String _fileUuid;
	private long _repositoryId;
	private long _parentFolderId;
	private String _name;
	private String _description;
	private String _checksum;
	private String _event;
	private long _lockUserId;
	private String _lockUserName;
	private long _size;
	private String _type;
	private String _version;
}