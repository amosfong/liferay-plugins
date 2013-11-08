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

package com.liferay.bbb.model.impl;

import com.liferay.bbb.model.MeetingEntry;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MeetingEntry in entity cache.
 *
 * @author Shinn Lok
 * @see MeetingEntry
 * @generated
 */
public class MeetingEntryCacheModel implements CacheModel<MeetingEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{meetingEntryId=");
		sb.append(meetingEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", meetingServerId=");
		sb.append(meetingServerId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", attendeePassword=");
		sb.append(attendeePassword);
		sb.append(", moderatorPassword=");
		sb.append(moderatorPassword);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MeetingEntry toEntityModel() {
		MeetingEntryImpl meetingEntryImpl = new MeetingEntryImpl();

		meetingEntryImpl.setMeetingEntryId(meetingEntryId);
		meetingEntryImpl.setGroupId(groupId);
		meetingEntryImpl.setCompanyId(companyId);
		meetingEntryImpl.setUserId(userId);

		if (userName == null) {
			meetingEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			meetingEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			meetingEntryImpl.setCreateDate(null);
		}
		else {
			meetingEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			meetingEntryImpl.setModifiedDate(null);
		}
		else {
			meetingEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		meetingEntryImpl.setMeetingServerId(meetingServerId);

		if (name == null) {
			meetingEntryImpl.setName(StringPool.BLANK);
		}
		else {
			meetingEntryImpl.setName(name);
		}

		if (attendeePassword == null) {
			meetingEntryImpl.setAttendeePassword(StringPool.BLANK);
		}
		else {
			meetingEntryImpl.setAttendeePassword(attendeePassword);
		}

		if (moderatorPassword == null) {
			meetingEntryImpl.setModeratorPassword(StringPool.BLANK);
		}
		else {
			meetingEntryImpl.setModeratorPassword(moderatorPassword);
		}

		meetingEntryImpl.setStatus(status);

		meetingEntryImpl.resetOriginalValues();

		return meetingEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		meetingEntryId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		meetingServerId = objectInput.readLong();
		name = objectInput.readUTF();
		attendeePassword = objectInput.readUTF();
		moderatorPassword = objectInput.readUTF();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(meetingEntryId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(meetingServerId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (attendeePassword == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(attendeePassword);
		}

		if (moderatorPassword == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(moderatorPassword);
		}

		objectOutput.writeInt(status);
	}

	public long meetingEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long meetingServerId;
	public String name;
	public String attendeePassword;
	public String moderatorPassword;
	public int status;
}