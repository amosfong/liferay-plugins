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
 * This class is a wrapper for {@link MeetingEntry}.
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingEntry
 * @generated
 */
public class MeetingEntryWrapper implements MeetingEntry,
	ModelWrapper<MeetingEntry> {
	public MeetingEntryWrapper(MeetingEntry meetingEntry) {
		_meetingEntry = meetingEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return MeetingEntry.class;
	}

	@Override
	public String getModelClassName() {
		return MeetingEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("meetingEntryId", getMeetingEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("meetingServerId", getMeetingServerId());
		attributes.put("name", getName());
		attributes.put("attendeePassword", getAttendeePassword());
		attributes.put("moderatorPassword", getModeratorPassword());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long meetingEntryId = (Long)attributes.get("meetingEntryId");

		if (meetingEntryId != null) {
			setMeetingEntryId(meetingEntryId);
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

		Long meetingServerId = (Long)attributes.get("meetingServerId");

		if (meetingServerId != null) {
			setMeetingServerId(meetingServerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String attendeePassword = (String)attributes.get("attendeePassword");

		if (attendeePassword != null) {
			setAttendeePassword(attendeePassword);
		}

		String moderatorPassword = (String)attributes.get("moderatorPassword");

		if (moderatorPassword != null) {
			setModeratorPassword(moderatorPassword);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	* Returns the primary key of this meeting entry.
	*
	* @return the primary key of this meeting entry
	*/
	@Override
	public long getPrimaryKey() {
		return _meetingEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this meeting entry.
	*
	* @param primaryKey the primary key of this meeting entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_meetingEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the meeting entry ID of this meeting entry.
	*
	* @return the meeting entry ID of this meeting entry
	*/
	@Override
	public long getMeetingEntryId() {
		return _meetingEntry.getMeetingEntryId();
	}

	/**
	* Sets the meeting entry ID of this meeting entry.
	*
	* @param meetingEntryId the meeting entry ID of this meeting entry
	*/
	@Override
	public void setMeetingEntryId(long meetingEntryId) {
		_meetingEntry.setMeetingEntryId(meetingEntryId);
	}

	/**
	* Returns the group ID of this meeting entry.
	*
	* @return the group ID of this meeting entry
	*/
	@Override
	public long getGroupId() {
		return _meetingEntry.getGroupId();
	}

	/**
	* Sets the group ID of this meeting entry.
	*
	* @param groupId the group ID of this meeting entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_meetingEntry.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this meeting entry.
	*
	* @return the company ID of this meeting entry
	*/
	@Override
	public long getCompanyId() {
		return _meetingEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this meeting entry.
	*
	* @param companyId the company ID of this meeting entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_meetingEntry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this meeting entry.
	*
	* @return the user ID of this meeting entry
	*/
	@Override
	public long getUserId() {
		return _meetingEntry.getUserId();
	}

	/**
	* Sets the user ID of this meeting entry.
	*
	* @param userId the user ID of this meeting entry
	*/
	@Override
	public void setUserId(long userId) {
		_meetingEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this meeting entry.
	*
	* @return the user uuid of this meeting entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetingEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this meeting entry.
	*
	* @param userUuid the user uuid of this meeting entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_meetingEntry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this meeting entry.
	*
	* @return the user name of this meeting entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _meetingEntry.getUserName();
	}

	/**
	* Sets the user name of this meeting entry.
	*
	* @param userName the user name of this meeting entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_meetingEntry.setUserName(userName);
	}

	/**
	* Returns the create date of this meeting entry.
	*
	* @return the create date of this meeting entry
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _meetingEntry.getCreateDate();
	}

	/**
	* Sets the create date of this meeting entry.
	*
	* @param createDate the create date of this meeting entry
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_meetingEntry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this meeting entry.
	*
	* @return the modified date of this meeting entry
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _meetingEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this meeting entry.
	*
	* @param modifiedDate the modified date of this meeting entry
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_meetingEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the meeting server ID of this meeting entry.
	*
	* @return the meeting server ID of this meeting entry
	*/
	@Override
	public long getMeetingServerId() {
		return _meetingEntry.getMeetingServerId();
	}

	/**
	* Sets the meeting server ID of this meeting entry.
	*
	* @param meetingServerId the meeting server ID of this meeting entry
	*/
	@Override
	public void setMeetingServerId(long meetingServerId) {
		_meetingEntry.setMeetingServerId(meetingServerId);
	}

	/**
	* Returns the name of this meeting entry.
	*
	* @return the name of this meeting entry
	*/
	@Override
	public java.lang.String getName() {
		return _meetingEntry.getName();
	}

	/**
	* Sets the name of this meeting entry.
	*
	* @param name the name of this meeting entry
	*/
	@Override
	public void setName(java.lang.String name) {
		_meetingEntry.setName(name);
	}

	/**
	* Returns the attendee password of this meeting entry.
	*
	* @return the attendee password of this meeting entry
	*/
	@Override
	public java.lang.String getAttendeePassword() {
		return _meetingEntry.getAttendeePassword();
	}

	/**
	* Sets the attendee password of this meeting entry.
	*
	* @param attendeePassword the attendee password of this meeting entry
	*/
	@Override
	public void setAttendeePassword(java.lang.String attendeePassword) {
		_meetingEntry.setAttendeePassword(attendeePassword);
	}

	/**
	* Returns the moderator password of this meeting entry.
	*
	* @return the moderator password of this meeting entry
	*/
	@Override
	public java.lang.String getModeratorPassword() {
		return _meetingEntry.getModeratorPassword();
	}

	/**
	* Sets the moderator password of this meeting entry.
	*
	* @param moderatorPassword the moderator password of this meeting entry
	*/
	@Override
	public void setModeratorPassword(java.lang.String moderatorPassword) {
		_meetingEntry.setModeratorPassword(moderatorPassword);
	}

	/**
	* Returns the status of this meeting entry.
	*
	* @return the status of this meeting entry
	*/
	@Override
	public int getStatus() {
		return _meetingEntry.getStatus();
	}

	/**
	* Sets the status of this meeting entry.
	*
	* @param status the status of this meeting entry
	*/
	@Override
	public void setStatus(int status) {
		_meetingEntry.setStatus(status);
	}

	@Override
	public boolean isNew() {
		return _meetingEntry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_meetingEntry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _meetingEntry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_meetingEntry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _meetingEntry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _meetingEntry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_meetingEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _meetingEntry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_meetingEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_meetingEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_meetingEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MeetingEntryWrapper((MeetingEntry)_meetingEntry.clone());
	}

	@Override
	public int compareTo(MeetingEntry meetingEntry) {
		return _meetingEntry.compareTo(meetingEntry);
	}

	@Override
	public int hashCode() {
		return _meetingEntry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<MeetingEntry> toCacheModel() {
		return _meetingEntry.toCacheModel();
	}

	@Override
	public MeetingEntry toEscapedModel() {
		return new MeetingEntryWrapper(_meetingEntry.toEscapedModel());
	}

	@Override
	public MeetingEntry toUnescapedModel() {
		return new MeetingEntryWrapper(_meetingEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _meetingEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _meetingEntry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_meetingEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetingEntryWrapper)) {
			return false;
		}

		MeetingEntryWrapper meetingEntryWrapper = (MeetingEntryWrapper)obj;

		if (Validator.equals(_meetingEntry, meetingEntryWrapper._meetingEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public MeetingEntry getWrappedMeetingEntry() {
		return _meetingEntry;
	}

	@Override
	public MeetingEntry getWrappedModel() {
		return _meetingEntry;
	}

	@Override
	public void resetOriginalValues() {
		_meetingEntry.resetOriginalValues();
	}

	private MeetingEntry _meetingEntry;
}