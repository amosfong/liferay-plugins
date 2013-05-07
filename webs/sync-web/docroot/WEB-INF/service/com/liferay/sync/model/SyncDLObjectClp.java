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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.sync.service.ClpSerializer;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class SyncDLObjectClp extends BaseModelImpl<SyncDLObject>
	implements SyncDLObject {
	public SyncDLObjectClp() {
	}

	public Class<?> getModelClass() {
		return SyncDLObject.class;
	}

	public String getModelClassName() {
		return SyncDLObject.class.getName();
	}

	public long getPrimaryKey() {
		return _objectId;
	}

	public void setPrimaryKey(long primaryKey) {
		setObjectId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _objectId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	public long getObjectId() {
		return _objectId;
	}

	public void setObjectId(long objectId) {
		_objectId = objectId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setObjectId", long.class);

				method.invoke(_syncDLObjectRemoteModel, objectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_syncDLObjectRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(long createDate) {
		_createDate = createDate;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", long.class);

				method.invoke(_syncDLObjectRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", long.class);

				method.invoke(_syncDLObjectRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getFileId() {
		return _fileId;
	}

	public void setFileId(long fileId) {
		_fileId = fileId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setFileId", long.class);

				method.invoke(_syncDLObjectRemoteModel, fileId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getFileUuid() {
		return _fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		_fileUuid = fileUuid;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setFileUuid", String.class);

				method.invoke(_syncDLObjectRemoteModel, fileUuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setRepositoryId", long.class);

				method.invoke(_syncDLObjectRemoteModel, repositoryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getParentFolderId() {
		return _parentFolderId;
	}

	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setParentFolderId", long.class);

				method.invoke(_syncDLObjectRemoteModel, parentFolderId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_syncDLObjectRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_syncDLObjectRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getChecksum() {
		return _checksum;
	}

	public void setChecksum(String checksum) {
		_checksum = checksum;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setChecksum", String.class);

				method.invoke(_syncDLObjectRemoteModel, checksum);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getEvent() {
		return _event;
	}

	public void setEvent(String event) {
		_event = event;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setEvent", String.class);

				method.invoke(_syncDLObjectRemoteModel, event);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getLockUserId() {
		return _lockUserId;
	}

	public void setLockUserId(long lockUserId) {
		_lockUserId = lockUserId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setLockUserId", long.class);

				method.invoke(_syncDLObjectRemoteModel, lockUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLockUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getLockUserId(), "uuid", _lockUserUuid);
	}

	public void setLockUserUuid(String lockUserUuid) {
		_lockUserUuid = lockUserUuid;
	}

	public String getLockUserName() {
		return _lockUserName;
	}

	public void setLockUserName(String lockUserName) {
		_lockUserName = lockUserName;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setLockUserName", String.class);

				method.invoke(_syncDLObjectRemoteModel, lockUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setSize", long.class);

				method.invoke(_syncDLObjectRemoteModel, size);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_syncDLObjectRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setVersion", String.class);

				method.invoke(_syncDLObjectRemoteModel, version);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSyncDLObjectRemoteModel() {
		return _syncDLObjectRemoteModel;
	}

	public void setSyncDLObjectRemoteModel(BaseModel<?> syncDLObjectRemoteModel) {
		_syncDLObjectRemoteModel = syncDLObjectRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _syncDLObjectRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_syncDLObjectRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SyncDLObjectLocalServiceUtil.addSyncDLObject(this);
		}
		else {
			SyncDLObjectLocalServiceUtil.updateSyncDLObject(this);
		}
	}

	@Override
	public SyncDLObject toEscapedModel() {
		return (SyncDLObject)ProxyUtil.newProxyInstance(SyncDLObject.class.getClassLoader(),
			new Class[] { SyncDLObject.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SyncDLObjectClp clone = new SyncDLObjectClp();

		clone.setObjectId(getObjectId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setFileId(getFileId());
		clone.setFileUuid(getFileUuid());
		clone.setRepositoryId(getRepositoryId());
		clone.setParentFolderId(getParentFolderId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setChecksum(getChecksum());
		clone.setEvent(getEvent());
		clone.setLockUserId(getLockUserId());
		clone.setLockUserName(getLockUserName());
		clone.setSize(getSize());
		clone.setType(getType());
		clone.setVersion(getVersion());

		return clone;
	}

	public int compareTo(SyncDLObject syncDLObject) {
		int value = 0;

		if (getCompanyId() < syncDLObject.getCompanyId()) {
			value = -1;
		}
		else if (getCompanyId() > syncDLObject.getCompanyId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getModifiedDate() < syncDLObject.getModifiedDate()) {
			value = -1;
		}
		else if (getModifiedDate() > syncDLObject.getModifiedDate()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getRepositoryId() < syncDLObject.getRepositoryId()) {
			value = -1;
		}
		else if (getRepositoryId() > syncDLObject.getRepositoryId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncDLObjectClp)) {
			return false;
		}

		SyncDLObjectClp syncDLObject = (SyncDLObjectClp)obj;

		long primaryKey = syncDLObject.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{objectId=");
		sb.append(getObjectId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", fileId=");
		sb.append(getFileId());
		sb.append(", fileUuid=");
		sb.append(getFileUuid());
		sb.append(", repositoryId=");
		sb.append(getRepositoryId());
		sb.append(", parentFolderId=");
		sb.append(getParentFolderId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", checksum=");
		sb.append(getChecksum());
		sb.append(", event=");
		sb.append(getEvent());
		sb.append(", lockUserId=");
		sb.append(getLockUserId());
		sb.append(", lockUserName=");
		sb.append(getLockUserName());
		sb.append(", size=");
		sb.append(getSize());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.liferay.sync.model.SyncDLObject");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>objectId</column-name><column-value><![CDATA[");
		sb.append(getObjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileId</column-name><column-value><![CDATA[");
		sb.append(getFileId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileUuid</column-name><column-value><![CDATA[");
		sb.append(getFileUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>repositoryId</column-name><column-value><![CDATA[");
		sb.append(getRepositoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentFolderId</column-name><column-value><![CDATA[");
		sb.append(getParentFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>checksum</column-name><column-value><![CDATA[");
		sb.append(getChecksum());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>event</column-name><column-value><![CDATA[");
		sb.append(getEvent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lockUserId</column-name><column-value><![CDATA[");
		sb.append(getLockUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lockUserName</column-name><column-value><![CDATA[");
		sb.append(getLockUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>size</column-name><column-value><![CDATA[");
		sb.append(getSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
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
	private String _lockUserUuid;
	private String _lockUserName;
	private long _size;
	private String _type;
	private String _version;
	private BaseModel<?> _syncDLObjectRemoteModel;
}