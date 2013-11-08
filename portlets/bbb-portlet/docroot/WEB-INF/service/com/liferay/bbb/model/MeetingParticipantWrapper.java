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
 * This class is a wrapper for {@link MeetingParticipant}.
 * </p>
 *
 * @author Shinn Lok
 * @see MeetingParticipant
 * @generated
 */
public class MeetingParticipantWrapper implements MeetingParticipant,
	ModelWrapper<MeetingParticipant> {
	public MeetingParticipantWrapper(MeetingParticipant meetingParticipant) {
		_meetingParticipant = meetingParticipant;
	}

	@Override
	public Class<?> getModelClass() {
		return MeetingParticipant.class;
	}

	@Override
	public String getModelClassName() {
		return MeetingParticipant.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("meetingParticipantId", getMeetingParticipantId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("meetingEntryId", getMeetingEntryId());
		attributes.put("name", getName());
		attributes.put("emailAddress", getEmailAddress());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long meetingParticipantId = (Long)attributes.get("meetingParticipantId");

		if (meetingParticipantId != null) {
			setMeetingParticipantId(meetingParticipantId);
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

		Long meetingEntryId = (Long)attributes.get("meetingEntryId");

		if (meetingEntryId != null) {
			setMeetingEntryId(meetingEntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}
	}

	/**
	* Returns the primary key of this meeting participant.
	*
	* @return the primary key of this meeting participant
	*/
	@Override
	public long getPrimaryKey() {
		return _meetingParticipant.getPrimaryKey();
	}

	/**
	* Sets the primary key of this meeting participant.
	*
	* @param primaryKey the primary key of this meeting participant
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_meetingParticipant.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the meeting participant ID of this meeting participant.
	*
	* @return the meeting participant ID of this meeting participant
	*/
	@Override
	public long getMeetingParticipantId() {
		return _meetingParticipant.getMeetingParticipantId();
	}

	/**
	* Sets the meeting participant ID of this meeting participant.
	*
	* @param meetingParticipantId the meeting participant ID of this meeting participant
	*/
	@Override
	public void setMeetingParticipantId(long meetingParticipantId) {
		_meetingParticipant.setMeetingParticipantId(meetingParticipantId);
	}

	/**
	* Returns the group ID of this meeting participant.
	*
	* @return the group ID of this meeting participant
	*/
	@Override
	public long getGroupId() {
		return _meetingParticipant.getGroupId();
	}

	/**
	* Sets the group ID of this meeting participant.
	*
	* @param groupId the group ID of this meeting participant
	*/
	@Override
	public void setGroupId(long groupId) {
		_meetingParticipant.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this meeting participant.
	*
	* @return the company ID of this meeting participant
	*/
	@Override
	public long getCompanyId() {
		return _meetingParticipant.getCompanyId();
	}

	/**
	* Sets the company ID of this meeting participant.
	*
	* @param companyId the company ID of this meeting participant
	*/
	@Override
	public void setCompanyId(long companyId) {
		_meetingParticipant.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this meeting participant.
	*
	* @return the user ID of this meeting participant
	*/
	@Override
	public long getUserId() {
		return _meetingParticipant.getUserId();
	}

	/**
	* Sets the user ID of this meeting participant.
	*
	* @param userId the user ID of this meeting participant
	*/
	@Override
	public void setUserId(long userId) {
		_meetingParticipant.setUserId(userId);
	}

	/**
	* Returns the user uuid of this meeting participant.
	*
	* @return the user uuid of this meeting participant
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetingParticipant.getUserUuid();
	}

	/**
	* Sets the user uuid of this meeting participant.
	*
	* @param userUuid the user uuid of this meeting participant
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_meetingParticipant.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this meeting participant.
	*
	* @return the user name of this meeting participant
	*/
	@Override
	public java.lang.String getUserName() {
		return _meetingParticipant.getUserName();
	}

	/**
	* Sets the user name of this meeting participant.
	*
	* @param userName the user name of this meeting participant
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_meetingParticipant.setUserName(userName);
	}

	/**
	* Returns the create date of this meeting participant.
	*
	* @return the create date of this meeting participant
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _meetingParticipant.getCreateDate();
	}

	/**
	* Sets the create date of this meeting participant.
	*
	* @param createDate the create date of this meeting participant
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_meetingParticipant.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this meeting participant.
	*
	* @return the modified date of this meeting participant
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _meetingParticipant.getModifiedDate();
	}

	/**
	* Sets the modified date of this meeting participant.
	*
	* @param modifiedDate the modified date of this meeting participant
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_meetingParticipant.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the meeting entry ID of this meeting participant.
	*
	* @return the meeting entry ID of this meeting participant
	*/
	@Override
	public long getMeetingEntryId() {
		return _meetingParticipant.getMeetingEntryId();
	}

	/**
	* Sets the meeting entry ID of this meeting participant.
	*
	* @param meetingEntryId the meeting entry ID of this meeting participant
	*/
	@Override
	public void setMeetingEntryId(long meetingEntryId) {
		_meetingParticipant.setMeetingEntryId(meetingEntryId);
	}

	/**
	* Returns the name of this meeting participant.
	*
	* @return the name of this meeting participant
	*/
	@Override
	public java.lang.String getName() {
		return _meetingParticipant.getName();
	}

	/**
	* Sets the name of this meeting participant.
	*
	* @param name the name of this meeting participant
	*/
	@Override
	public void setName(java.lang.String name) {
		_meetingParticipant.setName(name);
	}

	/**
	* Returns the email address of this meeting participant.
	*
	* @return the email address of this meeting participant
	*/
	@Override
	public java.lang.String getEmailAddress() {
		return _meetingParticipant.getEmailAddress();
	}

	/**
	* Sets the email address of this meeting participant.
	*
	* @param emailAddress the email address of this meeting participant
	*/
	@Override
	public void setEmailAddress(java.lang.String emailAddress) {
		_meetingParticipant.setEmailAddress(emailAddress);
	}

	@Override
	public boolean isNew() {
		return _meetingParticipant.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_meetingParticipant.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _meetingParticipant.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_meetingParticipant.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _meetingParticipant.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _meetingParticipant.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_meetingParticipant.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _meetingParticipant.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_meetingParticipant.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_meetingParticipant.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_meetingParticipant.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MeetingParticipantWrapper((MeetingParticipant)_meetingParticipant.clone());
	}

	@Override
	public int compareTo(MeetingParticipant meetingParticipant) {
		return _meetingParticipant.compareTo(meetingParticipant);
	}

	@Override
	public int hashCode() {
		return _meetingParticipant.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<MeetingParticipant> toCacheModel() {
		return _meetingParticipant.toCacheModel();
	}

	@Override
	public MeetingParticipant toEscapedModel() {
		return new MeetingParticipantWrapper(_meetingParticipant.toEscapedModel());
	}

	@Override
	public MeetingParticipant toUnescapedModel() {
		return new MeetingParticipantWrapper(_meetingParticipant.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _meetingParticipant.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _meetingParticipant.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_meetingParticipant.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetingParticipantWrapper)) {
			return false;
		}

		MeetingParticipantWrapper meetingParticipantWrapper = (MeetingParticipantWrapper)obj;

		if (Validator.equals(_meetingParticipant,
					meetingParticipantWrapper._meetingParticipant)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public MeetingParticipant getWrappedMeetingParticipant() {
		return _meetingParticipant;
	}

	@Override
	public MeetingParticipant getWrappedModel() {
		return _meetingParticipant;
	}

	@Override
	public void resetOriginalValues() {
		_meetingParticipant.resetOriginalValues();
	}

	private MeetingParticipant _meetingParticipant;
}