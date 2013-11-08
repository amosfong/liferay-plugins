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

package com.liferay.bbb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.bbb.service.http.MeetingParticipantServiceSoap}.
 *
 * @author Shinn Lok
 * @see com.liferay.bbb.service.http.MeetingParticipantServiceSoap
 * @generated
 */
public class MeetingParticipantSoap implements Serializable {
	public static MeetingParticipantSoap toSoapModel(MeetingParticipant model) {
		MeetingParticipantSoap soapModel = new MeetingParticipantSoap();

		soapModel.setMeetingParticipantId(model.getMeetingParticipantId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMeetingEntryId(model.getMeetingEntryId());
		soapModel.setName(model.getName());
		soapModel.setEmailAddress(model.getEmailAddress());

		return soapModel;
	}

	public static MeetingParticipantSoap[] toSoapModels(
		MeetingParticipant[] models) {
		MeetingParticipantSoap[] soapModels = new MeetingParticipantSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MeetingParticipantSoap[][] toSoapModels(
		MeetingParticipant[][] models) {
		MeetingParticipantSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MeetingParticipantSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MeetingParticipantSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MeetingParticipantSoap[] toSoapModels(
		List<MeetingParticipant> models) {
		List<MeetingParticipantSoap> soapModels = new ArrayList<MeetingParticipantSoap>(models.size());

		for (MeetingParticipant model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MeetingParticipantSoap[soapModels.size()]);
	}

	public MeetingParticipantSoap() {
	}

	public long getPrimaryKey() {
		return _meetingParticipantId;
	}

	public void setPrimaryKey(long pk) {
		setMeetingParticipantId(pk);
	}

	public long getMeetingParticipantId() {
		return _meetingParticipantId;
	}

	public void setMeetingParticipantId(long meetingParticipantId) {
		_meetingParticipantId = meetingParticipantId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getMeetingEntryId() {
		return _meetingEntryId;
	}

	public void setMeetingEntryId(long meetingEntryId) {
		_meetingEntryId = meetingEntryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	private long _meetingParticipantId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _meetingEntryId;
	private String _name;
	private String _emailAddress;
}