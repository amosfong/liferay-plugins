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

import com.liferay.bbb.service.ClpSerializer;
import com.liferay.bbb.service.MeetingParticipantLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public class MeetingParticipantClp extends BaseModelImpl<MeetingParticipant>
	implements MeetingParticipant {
	public MeetingParticipantClp() {
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
	public long getPrimaryKey() {
		return _meetingParticipantId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMeetingParticipantId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _meetingParticipantId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@Override
	public long getMeetingParticipantId() {
		return _meetingParticipantId;
	}

	@Override
	public void setMeetingParticipantId(long meetingParticipantId) {
		_meetingParticipantId = meetingParticipantId;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setMeetingParticipantId",
						long.class);

				method.invoke(_meetingParticipantRemoteModel,
					meetingParticipantId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_meetingParticipantRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_meetingParticipantRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_meetingParticipantRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_meetingParticipantRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_meetingParticipantRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_meetingParticipantRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getMeetingEntryId() {
		return _meetingEntryId;
	}

	@Override
	public void setMeetingEntryId(long meetingEntryId) {
		_meetingEntryId = meetingEntryId;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setMeetingEntryId", long.class);

				method.invoke(_meetingParticipantRemoteModel, meetingEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_meetingParticipantRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEmailAddress() {
		return _emailAddress;
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;

		if (_meetingParticipantRemoteModel != null) {
			try {
				Class<?> clazz = _meetingParticipantRemoteModel.getClass();

				Method method = clazz.getMethod("setEmailAddress", String.class);

				method.invoke(_meetingParticipantRemoteModel, emailAddress);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getMeetingParticipantRemoteModel() {
		return _meetingParticipantRemoteModel;
	}

	public void setMeetingParticipantRemoteModel(
		BaseModel<?> meetingParticipantRemoteModel) {
		_meetingParticipantRemoteModel = meetingParticipantRemoteModel;
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

		Class<?> remoteModelClass = _meetingParticipantRemoteModel.getClass();

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

		Object returnValue = method.invoke(_meetingParticipantRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			MeetingParticipantLocalServiceUtil.addMeetingParticipant(this);
		}
		else {
			MeetingParticipantLocalServiceUtil.updateMeetingParticipant(this);
		}
	}

	@Override
	public MeetingParticipant toEscapedModel() {
		return (MeetingParticipant)ProxyUtil.newProxyInstance(MeetingParticipant.class.getClassLoader(),
			new Class[] { MeetingParticipant.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		MeetingParticipantClp clone = new MeetingParticipantClp();

		clone.setMeetingParticipantId(getMeetingParticipantId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setMeetingEntryId(getMeetingEntryId());
		clone.setName(getName());
		clone.setEmailAddress(getEmailAddress());

		return clone;
	}

	@Override
	public int compareTo(MeetingParticipant meetingParticipant) {
		long primaryKey = meetingParticipant.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetingParticipantClp)) {
			return false;
		}

		MeetingParticipantClp meetingParticipant = (MeetingParticipantClp)obj;

		long primaryKey = meetingParticipant.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{meetingParticipantId=");
		sb.append(getMeetingParticipantId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", meetingEntryId=");
		sb.append(getMeetingEntryId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", emailAddress=");
		sb.append(getEmailAddress());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.bbb.model.MeetingParticipant");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>meetingParticipantId</column-name><column-value><![CDATA[");
		sb.append(getMeetingParticipantId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
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
			"<column><column-name>meetingEntryId</column-name><column-value><![CDATA[");
		sb.append(getMeetingEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailAddress());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _meetingParticipantId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _meetingEntryId;
	private String _name;
	private String _emailAddress;
	private BaseModel<?> _meetingParticipantRemoteModel;
}