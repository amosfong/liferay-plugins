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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SyncDLObject}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObject
 * @generated
 */
public class SyncDLObjectWrapper implements SyncDLObject,
	ModelWrapper<SyncDLObject> {
	public SyncDLObjectWrapper(SyncDLObject syncDLObject) {
		_syncDLObject = syncDLObject;
	}

	@Override
	public Class<?> getModelClass() {
		return SyncDLObject.class;
	}

	@Override
	public String getModelClassName() {
		return SyncDLObject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("objectId", getObjectId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("fileId", getFileId());
		attributes.put("fileUuid", getFileUuid());
		attributes.put("repositoryId", getRepositoryId());
		attributes.put("parentFolderId", getParentFolderId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("checksum", getChecksum());
		attributes.put("event", getEvent());
		attributes.put("lockUserId", getLockUserId());
		attributes.put("lockUserName", getLockUserName());
		attributes.put("size", getSize());
		attributes.put("type", getType());
		attributes.put("version", getVersion());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long objectId = (Long)attributes.get("objectId");

		if (objectId != null) {
			setObjectId(objectId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long createDate = (Long)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long modifiedDate = (Long)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long fileId = (Long)attributes.get("fileId");

		if (fileId != null) {
			setFileId(fileId);
		}

		String fileUuid = (String)attributes.get("fileUuid");

		if (fileUuid != null) {
			setFileUuid(fileUuid);
		}

		Long repositoryId = (Long)attributes.get("repositoryId");

		if (repositoryId != null) {
			setRepositoryId(repositoryId);
		}

		Long parentFolderId = (Long)attributes.get("parentFolderId");

		if (parentFolderId != null) {
			setParentFolderId(parentFolderId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String checksum = (String)attributes.get("checksum");

		if (checksum != null) {
			setChecksum(checksum);
		}

		String event = (String)attributes.get("event");

		if (event != null) {
			setEvent(event);
		}

		Long lockUserId = (Long)attributes.get("lockUserId");

		if (lockUserId != null) {
			setLockUserId(lockUserId);
		}

		String lockUserName = (String)attributes.get("lockUserName");

		if (lockUserName != null) {
			setLockUserName(lockUserName);
		}

		Long size = (Long)attributes.get("size");

		if (size != null) {
			setSize(size);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}
	}

	/**
	* Returns the primary key of this sync d l object.
	*
	* @return the primary key of this sync d l object
	*/
	@Override
	public long getPrimaryKey() {
		return _syncDLObject.getPrimaryKey();
	}

	/**
	* Sets the primary key of this sync d l object.
	*
	* @param primaryKey the primary key of this sync d l object
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_syncDLObject.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the object ID of this sync d l object.
	*
	* @return the object ID of this sync d l object
	*/
	@Override
	public long getObjectId() {
		return _syncDLObject.getObjectId();
	}

	/**
	* Sets the object ID of this sync d l object.
	*
	* @param objectId the object ID of this sync d l object
	*/
	@Override
	public void setObjectId(long objectId) {
		_syncDLObject.setObjectId(objectId);
	}

	/**
	* Returns the company ID of this sync d l object.
	*
	* @return the company ID of this sync d l object
	*/
	@Override
	public long getCompanyId() {
		return _syncDLObject.getCompanyId();
	}

	/**
	* Sets the company ID of this sync d l object.
	*
	* @param companyId the company ID of this sync d l object
	*/
	@Override
	public void setCompanyId(long companyId) {
		_syncDLObject.setCompanyId(companyId);
	}

	/**
	* Returns the create date of this sync d l object.
	*
	* @return the create date of this sync d l object
	*/
	@Override
	public long getCreateDate() {
		return _syncDLObject.getCreateDate();
	}

	/**
	* Sets the create date of this sync d l object.
	*
	* @param createDate the create date of this sync d l object
	*/
	@Override
	public void setCreateDate(long createDate) {
		_syncDLObject.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this sync d l object.
	*
	* @return the modified date of this sync d l object
	*/
	@Override
	public long getModifiedDate() {
		return _syncDLObject.getModifiedDate();
	}

	/**
	* Sets the modified date of this sync d l object.
	*
	* @param modifiedDate the modified date of this sync d l object
	*/
	@Override
	public void setModifiedDate(long modifiedDate) {
		_syncDLObject.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the file ID of this sync d l object.
	*
	* @return the file ID of this sync d l object
	*/
	@Override
	public long getFileId() {
		return _syncDLObject.getFileId();
	}

	/**
	* Sets the file ID of this sync d l object.
	*
	* @param fileId the file ID of this sync d l object
	*/
	@Override
	public void setFileId(long fileId) {
		_syncDLObject.setFileId(fileId);
	}

	/**
	* Returns the file uuid of this sync d l object.
	*
	* @return the file uuid of this sync d l object
	*/
	@Override
	public java.lang.String getFileUuid() {
		return _syncDLObject.getFileUuid();
	}

	/**
	* Sets the file uuid of this sync d l object.
	*
	* @param fileUuid the file uuid of this sync d l object
	*/
	@Override
	public void setFileUuid(java.lang.String fileUuid) {
		_syncDLObject.setFileUuid(fileUuid);
	}

	/**
	* Returns the repository ID of this sync d l object.
	*
	* @return the repository ID of this sync d l object
	*/
	@Override
	public long getRepositoryId() {
		return _syncDLObject.getRepositoryId();
	}

	/**
	* Sets the repository ID of this sync d l object.
	*
	* @param repositoryId the repository ID of this sync d l object
	*/
	@Override
	public void setRepositoryId(long repositoryId) {
		_syncDLObject.setRepositoryId(repositoryId);
	}

	/**
	* Returns the parent folder ID of this sync d l object.
	*
	* @return the parent folder ID of this sync d l object
	*/
	@Override
	public long getParentFolderId() {
		return _syncDLObject.getParentFolderId();
	}

	/**
	* Sets the parent folder ID of this sync d l object.
	*
	* @param parentFolderId the parent folder ID of this sync d l object
	*/
	@Override
	public void setParentFolderId(long parentFolderId) {
		_syncDLObject.setParentFolderId(parentFolderId);
	}

	/**
	* Returns the name of this sync d l object.
	*
	* @return the name of this sync d l object
	*/
	@Override
	public java.lang.String getName() {
		return _syncDLObject.getName();
	}

	/**
	* Sets the name of this sync d l object.
	*
	* @param name the name of this sync d l object
	*/
	@Override
	public void setName(java.lang.String name) {
		_syncDLObject.setName(name);
	}

	/**
	* Returns the description of this sync d l object.
	*
	* @return the description of this sync d l object
	*/
	@Override
	public java.lang.String getDescription() {
		return _syncDLObject.getDescription();
	}

	/**
	* Sets the description of this sync d l object.
	*
	* @param description the description of this sync d l object
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_syncDLObject.setDescription(description);
	}

	/**
	* Returns the checksum of this sync d l object.
	*
	* @return the checksum of this sync d l object
	*/
	@Override
	public java.lang.String getChecksum() {
		return _syncDLObject.getChecksum();
	}

	/**
	* Sets the checksum of this sync d l object.
	*
	* @param checksum the checksum of this sync d l object
	*/
	@Override
	public void setChecksum(java.lang.String checksum) {
		_syncDLObject.setChecksum(checksum);
	}

	/**
	* Returns the event of this sync d l object.
	*
	* @return the event of this sync d l object
	*/
	@Override
	public java.lang.String getEvent() {
		return _syncDLObject.getEvent();
	}

	/**
	* Sets the event of this sync d l object.
	*
	* @param event the event of this sync d l object
	*/
	@Override
	public void setEvent(java.lang.String event) {
		_syncDLObject.setEvent(event);
	}

	/**
	* Returns the lock user ID of this sync d l object.
	*
	* @return the lock user ID of this sync d l object
	*/
	@Override
	public long getLockUserId() {
		return _syncDLObject.getLockUserId();
	}

	/**
	* Sets the lock user ID of this sync d l object.
	*
	* @param lockUserId the lock user ID of this sync d l object
	*/
	@Override
	public void setLockUserId(long lockUserId) {
		_syncDLObject.setLockUserId(lockUserId);
	}

	/**
	* Returns the lock user uuid of this sync d l object.
	*
	* @return the lock user uuid of this sync d l object
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getLockUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObject.getLockUserUuid();
	}

	/**
	* Sets the lock user uuid of this sync d l object.
	*
	* @param lockUserUuid the lock user uuid of this sync d l object
	*/
	@Override
	public void setLockUserUuid(java.lang.String lockUserUuid) {
		_syncDLObject.setLockUserUuid(lockUserUuid);
	}

	/**
	* Returns the lock user name of this sync d l object.
	*
	* @return the lock user name of this sync d l object
	*/
	@Override
	public java.lang.String getLockUserName() {
		return _syncDLObject.getLockUserName();
	}

	/**
	* Sets the lock user name of this sync d l object.
	*
	* @param lockUserName the lock user name of this sync d l object
	*/
	@Override
	public void setLockUserName(java.lang.String lockUserName) {
		_syncDLObject.setLockUserName(lockUserName);
	}

	/**
	* Returns the size of this sync d l object.
	*
	* @return the size of this sync d l object
	*/
	@Override
	public long getSize() {
		return _syncDLObject.getSize();
	}

	/**
	* Sets the size of this sync d l object.
	*
	* @param size the size of this sync d l object
	*/
	@Override
	public void setSize(long size) {
		_syncDLObject.setSize(size);
	}

	/**
	* Returns the type of this sync d l object.
	*
	* @return the type of this sync d l object
	*/
	@Override
	public java.lang.String getType() {
		return _syncDLObject.getType();
	}

	/**
	* Sets the type of this sync d l object.
	*
	* @param type the type of this sync d l object
	*/
	@Override
	public void setType(java.lang.String type) {
		_syncDLObject.setType(type);
	}

	/**
	* Returns the version of this sync d l object.
	*
	* @return the version of this sync d l object
	*/
	@Override
	public java.lang.String getVersion() {
		return _syncDLObject.getVersion();
	}

	/**
	* Sets the version of this sync d l object.
	*
	* @param version the version of this sync d l object
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_syncDLObject.setVersion(version);
	}

	@Override
	public boolean isNew() {
		return _syncDLObject.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_syncDLObject.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _syncDLObject.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_syncDLObject.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _syncDLObject.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _syncDLObject.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_syncDLObject.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _syncDLObject.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_syncDLObject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_syncDLObject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_syncDLObject.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SyncDLObjectWrapper((SyncDLObject)_syncDLObject.clone());
	}

	@Override
	public int compareTo(com.liferay.sync.model.SyncDLObject syncDLObject) {
		return _syncDLObject.compareTo(syncDLObject);
	}

	@Override
	public int hashCode() {
		return _syncDLObject.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.sync.model.SyncDLObject> toCacheModel() {
		return _syncDLObject.toCacheModel();
	}

	@Override
	public com.liferay.sync.model.SyncDLObject toEscapedModel() {
		return new SyncDLObjectWrapper(_syncDLObject.toEscapedModel());
	}

	@Override
	public com.liferay.sync.model.SyncDLObject toUnescapedModel() {
		return new SyncDLObjectWrapper(_syncDLObject.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _syncDLObject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _syncDLObject.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_syncDLObject.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncDLObjectWrapper)) {
			return false;
		}

		SyncDLObjectWrapper syncDLObjectWrapper = (SyncDLObjectWrapper)obj;

		if (Validator.equals(_syncDLObject, syncDLObjectWrapper._syncDLObject)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SyncDLObject getWrappedSyncDLObject() {
		return _syncDLObject;
	}

	@Override
	public SyncDLObject getWrappedModel() {
		return _syncDLObject;
	}

	@Override
	public void resetOriginalValues() {
		_syncDLObject.resetOriginalValues();
	}

	private SyncDLObject _syncDLObject;
}