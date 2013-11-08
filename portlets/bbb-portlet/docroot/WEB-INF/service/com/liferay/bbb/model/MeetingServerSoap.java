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
 * This class is used by SOAP remote services, specifically {@link com.liferay.bbb.service.http.MeetingServerServiceSoap}.
 *
 * @author Shinn Lok
 * @see com.liferay.bbb.service.http.MeetingServerServiceSoap
 * @generated
 */
public class MeetingServerSoap implements Serializable {
	public static MeetingServerSoap toSoapModel(MeetingServer model) {
		MeetingServerSoap soapModel = new MeetingServerSoap();

		soapModel.setMeetingServerId(model.getMeetingServerId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setUrl(model.getUrl());
		soapModel.setSecret(model.getSecret());

		return soapModel;
	}

	public static MeetingServerSoap[] toSoapModels(MeetingServer[] models) {
		MeetingServerSoap[] soapModels = new MeetingServerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MeetingServerSoap[][] toSoapModels(MeetingServer[][] models) {
		MeetingServerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MeetingServerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MeetingServerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MeetingServerSoap[] toSoapModels(List<MeetingServer> models) {
		List<MeetingServerSoap> soapModels = new ArrayList<MeetingServerSoap>(models.size());

		for (MeetingServer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MeetingServerSoap[soapModels.size()]);
	}

	public MeetingServerSoap() {
	}

	public long getPrimaryKey() {
		return _meetingServerId;
	}

	public void setPrimaryKey(long pk) {
		setMeetingServerId(pk);
	}

	public long getMeetingServerId() {
		return _meetingServerId;
	}

	public void setMeetingServerId(long meetingServerId) {
		_meetingServerId = meetingServerId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public String getSecret() {
		return _secret;
	}

	public void setSecret(String secret) {
		_secret = secret;
	}

	private long _meetingServerId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _secret;
}