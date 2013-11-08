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

import com.liferay.bbb.model.MeetingParticipant;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MeetingParticipant in entity cache.
 *
 * @author Shinn Lok
 * @see MeetingParticipant
 * @generated
 */
public class MeetingParticipantCacheModel implements CacheModel<MeetingParticipant>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{meetingParticipantId=");
		sb.append(meetingParticipantId);
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
		sb.append(", meetingEntryId=");
		sb.append(meetingEntryId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MeetingParticipant toEntityModel() {
		MeetingParticipantImpl meetingParticipantImpl = new MeetingParticipantImpl();

		meetingParticipantImpl.setMeetingParticipantId(meetingParticipantId);
		meetingParticipantImpl.setGroupId(groupId);
		meetingParticipantImpl.setCompanyId(companyId);
		meetingParticipantImpl.setUserId(userId);

		if (userName == null) {
			meetingParticipantImpl.setUserName(StringPool.BLANK);
		}
		else {
			meetingParticipantImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			meetingParticipantImpl.setCreateDate(null);
		}
		else {
			meetingParticipantImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			meetingParticipantImpl.setModifiedDate(null);
		}
		else {
			meetingParticipantImpl.setModifiedDate(new Date(modifiedDate));
		}

		meetingParticipantImpl.setMeetingEntryId(meetingEntryId);

		if (name == null) {
			meetingParticipantImpl.setName(StringPool.BLANK);
		}
		else {
			meetingParticipantImpl.setName(name);
		}

		if (emailAddress == null) {
			meetingParticipantImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			meetingParticipantImpl.setEmailAddress(emailAddress);
		}

		meetingParticipantImpl.resetOriginalValues();

		return meetingParticipantImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		meetingParticipantId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		meetingEntryId = objectInput.readLong();
		name = objectInput.readUTF();
		emailAddress = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(meetingParticipantId);
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
		objectOutput.writeLong(meetingEntryId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (emailAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(emailAddress);
		}
	}

	public long meetingParticipantId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long meetingEntryId;
	public String name;
	public String emailAddress;
}