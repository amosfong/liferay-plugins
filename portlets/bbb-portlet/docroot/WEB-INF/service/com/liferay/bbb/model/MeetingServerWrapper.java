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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MeetingServer}.
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingServer
 * @generated
 */
public class MeetingServerWrapper implements MeetingServer,
	ModelWrapper<MeetingServer> {
	public MeetingServerWrapper(MeetingServer meetingServer) {
		_meetingServer = meetingServer;
	}

	@Override
	public Class<?> getModelClass() {
		return MeetingServer.class;
	}

	@Override
	public String getModelClassName() {
		return MeetingServer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("meetingServerId", getMeetingServerId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("secret", getSecret());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long meetingServerId = (Long)attributes.get("meetingServerId");

		if (meetingServerId != null) {
			setMeetingServerId(meetingServerId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String secret = (String)attributes.get("secret");

		if (secret != null) {
			setSecret(secret);
		}
	}

	/**
	* Returns the primary key of this meeting server.
	*
	* @return the primary key of this meeting server
	*/
	@Override
	public long getPrimaryKey() {
		return _meetingServer.getPrimaryKey();
	}

	/**
	* Sets the primary key of this meeting server.
	*
	* @param primaryKey the primary key of this meeting server
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_meetingServer.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the meeting server ID of this meeting server.
	*
	* @return the meeting server ID of this meeting server
	*/
	@Override
	public long getMeetingServerId() {
		return _meetingServer.getMeetingServerId();
	}

	/**
	* Sets the meeting server ID of this meeting server.
	*
	* @param meetingServerId the meeting server ID of this meeting server
	*/
	@Override
	public void setMeetingServerId(long meetingServerId) {
		_meetingServer.setMeetingServerId(meetingServerId);
	}

	/**
	* Returns the group ID of this meeting server.
	*
	* @return the group ID of this meeting server
	*/
	@Override
	public long getGroupId() {
		return _meetingServer.getGroupId();
	}

	/**
	* Sets the group ID of this meeting server.
	*
	* @param groupId the group ID of this meeting server
	*/
	@Override
	public void setGroupId(long groupId) {
		_meetingServer.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this meeting server.
	*
	* @return the company ID of this meeting server
	*/
	@Override
	public long getCompanyId() {
		return _meetingServer.getCompanyId();
	}

	/**
	* Sets the company ID of this meeting server.
	*
	* @param companyId the company ID of this meeting server
	*/
	@Override
	public void setCompanyId(long companyId) {
		_meetingServer.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this meeting server.
	*
	* @return the user ID of this meeting server
	*/
	@Override
	public long getUserId() {
		return _meetingServer.getUserId();
	}

	/**
	* Sets the user ID of this meeting server.
	*
	* @param userId the user ID of this meeting server
	*/
	@Override
	public void setUserId(long userId) {
		_meetingServer.setUserId(userId);
	}

	/**
	* Returns the user uuid of this meeting server.
	*
	* @return the user uuid of this meeting server
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetingServer.getUserUuid();
	}

	/**
	* Sets the user uuid of this meeting server.
	*
	* @param userUuid the user uuid of this meeting server
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_meetingServer.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this meeting server.
	*
	* @return the user name of this meeting server
	*/
	@Override
	public java.lang.String getUserName() {
		return _meetingServer.getUserName();
	}

	/**
	* Sets the user name of this meeting server.
	*
	* @param userName the user name of this meeting server
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_meetingServer.setUserName(userName);
	}

	/**
	* Returns the create date of this meeting server.
	*
	* @return the create date of this meeting server
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _meetingServer.getCreateDate();
	}

	/**
	* Sets the create date of this meeting server.
	*
	* @param createDate the create date of this meeting server
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_meetingServer.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this meeting server.
	*
	* @return the modified date of this meeting server
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _meetingServer.getModifiedDate();
	}

	/**
	* Sets the modified date of this meeting server.
	*
	* @param modifiedDate the modified date of this meeting server
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_meetingServer.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this meeting server.
	*
	* @return the name of this meeting server
	*/
	@Override
	public java.lang.String getName() {
		return _meetingServer.getName();
	}

	/**
	* Sets the name of this meeting server.
	*
	* @param name the name of this meeting server
	*/
	@Override
	public void setName(java.lang.String name) {
		_meetingServer.setName(name);
	}

	/**
	* Returns the url of this meeting server.
	*
	* @return the url of this meeting server
	*/
	@Override
	public java.lang.String getUrl() {
		return _meetingServer.getUrl();
	}

	/**
	* Sets the url of this meeting server.
	*
	* @param url the url of this meeting server
	*/
	@Override
	public void setUrl(java.lang.String url) {
		_meetingServer.setUrl(url);
	}

	/**
	* Returns the secret of this meeting server.
	*
	* @return the secret of this meeting server
	*/
	@Override
	public java.lang.String getSecret() {
		return _meetingServer.getSecret();
	}

	/**
	* Sets the secret of this meeting server.
	*
	* @param secret the secret of this meeting server
	*/
	@Override
	public void setSecret(java.lang.String secret) {
		_meetingServer.setSecret(secret);
	}

	@Override
	public boolean isNew() {
		return _meetingServer.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_meetingServer.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _meetingServer.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_meetingServer.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _meetingServer.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _meetingServer.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_meetingServer.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _meetingServer.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_meetingServer.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_meetingServer.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_meetingServer.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MeetingServerWrapper((MeetingServer)_meetingServer.clone());
	}

	@Override
	public int compareTo(MeetingServer meetingServer) {
		return _meetingServer.compareTo(meetingServer);
	}

	@Override
	public int hashCode() {
		return _meetingServer.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<MeetingServer> toCacheModel() {
		return _meetingServer.toCacheModel();
	}

	@Override
	public MeetingServer toEscapedModel() {
		return new MeetingServerWrapper(_meetingServer.toEscapedModel());
	}

	@Override
	public MeetingServer toUnescapedModel() {
		return new MeetingServerWrapper(_meetingServer.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _meetingServer.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _meetingServer.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_meetingServer.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetingServerWrapper)) {
			return false;
		}

		MeetingServerWrapper meetingServerWrapper = (MeetingServerWrapper)obj;

		if (Validator.equals(_meetingServer, meetingServerWrapper._meetingServer)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public MeetingServer getWrappedMeetingServer() {
		return _meetingServer;
	}

	@Override
	public MeetingServer getWrappedModel() {
		return _meetingServer;
	}

	@Override
	public void resetOriginalValues() {
		_meetingServer.resetOriginalValues();
	}

	private MeetingServer _meetingServer;
}