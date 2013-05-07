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

package com.liferay.sync.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.sync.model.SyncDLObject;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SyncDLObject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObject
 * @generated
 */
public class SyncDLObjectCacheModel implements CacheModel<SyncDLObject>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{objectId=");
		sb.append(objectId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", fileId=");
		sb.append(fileId);
		sb.append(", fileUuid=");
		sb.append(fileUuid);
		sb.append(", repositoryId=");
		sb.append(repositoryId);
		sb.append(", parentFolderId=");
		sb.append(parentFolderId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", checksum=");
		sb.append(checksum);
		sb.append(", event=");
		sb.append(event);
		sb.append(", lockUserId=");
		sb.append(lockUserId);
		sb.append(", lockUserName=");
		sb.append(lockUserName);
		sb.append(", size=");
		sb.append(size);
		sb.append(", type=");
		sb.append(type);
		sb.append(", version=");
		sb.append(version);
		sb.append("}");

		return sb.toString();
	}

	public SyncDLObject toEntityModel() {
		SyncDLObjectImpl syncDLObjectImpl = new SyncDLObjectImpl();

		syncDLObjectImpl.setObjectId(objectId);
		syncDLObjectImpl.setCompanyId(companyId);
		syncDLObjectImpl.setCreateDate(createDate);
		syncDLObjectImpl.setModifiedDate(modifiedDate);
		syncDLObjectImpl.setFileId(fileId);

		if (fileUuid == null) {
			syncDLObjectImpl.setFileUuid(StringPool.BLANK);
		}
		else {
			syncDLObjectImpl.setFileUuid(fileUuid);
		}

		syncDLObjectImpl.setRepositoryId(repositoryId);
		syncDLObjectImpl.setParentFolderId(parentFolderId);

		if (name == null) {
			syncDLObjectImpl.setName(StringPool.BLANK);
		}
		else {
			syncDLObjectImpl.setName(name);
		}

		if (description == null) {
			syncDLObjectImpl.setDescription(StringPool.BLANK);
		}
		else {
			syncDLObjectImpl.setDescription(description);
		}

		if (checksum == null) {
			syncDLObjectImpl.setChecksum(StringPool.BLANK);
		}
		else {
			syncDLObjectImpl.setChecksum(checksum);
		}

		if (event == null) {
			syncDLObjectImpl.setEvent(StringPool.BLANK);
		}
		else {
			syncDLObjectImpl.setEvent(event);
		}

		syncDLObjectImpl.setLockUserId(lockUserId);

		if (lockUserName == null) {
			syncDLObjectImpl.setLockUserName(StringPool.BLANK);
		}
		else {
			syncDLObjectImpl.setLockUserName(lockUserName);
		}

		syncDLObjectImpl.setSize(size);

		if (type == null) {
			syncDLObjectImpl.setType(StringPool.BLANK);
		}
		else {
			syncDLObjectImpl.setType(type);
		}

		if (version == null) {
			syncDLObjectImpl.setVersion(StringPool.BLANK);
		}
		else {
			syncDLObjectImpl.setVersion(version);
		}

		syncDLObjectImpl.resetOriginalValues();

		return syncDLObjectImpl;
	}

	public void readExternal(ObjectInput objectInput) throws IOException {
		objectId = objectInput.readLong();
		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		fileId = objectInput.readLong();
		fileUuid = objectInput.readUTF();
		repositoryId = objectInput.readLong();
		parentFolderId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		checksum = objectInput.readUTF();
		event = objectInput.readUTF();
		lockUserId = objectInput.readLong();
		lockUserName = objectInput.readUTF();
		size = objectInput.readLong();
		type = objectInput.readUTF();
		version = objectInput.readUTF();
	}

	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(objectId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(fileId);

		if (fileUuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(fileUuid);
		}

		objectOutput.writeLong(repositoryId);
		objectOutput.writeLong(parentFolderId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (checksum == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(checksum);
		}

		if (event == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(event);
		}

		objectOutput.writeLong(lockUserId);

		if (lockUserName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lockUserName);
		}

		objectOutput.writeLong(size);

		if (type == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(type);
		}

		if (version == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(version);
		}
	}

	public long objectId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long fileId;
	public String fileUuid;
	public long repositoryId;
	public long parentFolderId;
	public String name;
	public String description;
	public String checksum;
	public String event;
	public long lockUserId;
	public String lockUserName;
	public long size;
	public String type;
	public String version;
}