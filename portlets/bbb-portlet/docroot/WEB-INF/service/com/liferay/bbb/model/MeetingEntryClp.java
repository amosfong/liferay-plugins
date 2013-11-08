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
import com.liferay.bbb.service.MeetingEntryLocalServiceUtil;

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
public class MeetingEntryClp extends BaseModelImpl<MeetingEntry>
	implements MeetingEntry {
	public MeetingEntryClp() {
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
	public long getPrimaryKey() {
		return _meetingEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMeetingEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _meetingEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	@Override
	public long getMeetingEntryId() {
		return _meetingEntryId;
	}

	@Override
	public void setMeetingEntryId(long meetingEntryId) {
		_meetingEntryId = meetingEntryId;

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setMeetingEntryId", long.class);

				method.invoke(_meetingEntryRemoteModel, meetingEntryId);
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

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_meetingEntryRemoteModel, groupId);
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

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_meetingEntryRemoteModel, companyId);
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

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_meetingEntryRemoteModel, userId);
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

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_meetingEntryRemoteModel, userName);
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

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_meetingEntryRemoteModel, createDate);
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

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_meetingEntryRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getMeetingServerId() {
		return _meetingServerId;
	}

	@Override
	public void setMeetingServerId(long meetingServerId) {
		_meetingServerId = meetingServerId;

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setMeetingServerId", long.class);

				method.invoke(_meetingEntryRemoteModel, meetingServerId);
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

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_meetingEntryRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAttendeePassword() {
		return _attendeePassword;
	}

	@Override
	public void setAttendeePassword(String attendeePassword) {
		_attendeePassword = attendeePassword;

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setAttendeePassword",
						String.class);

				method.invoke(_meetingEntryRemoteModel, attendeePassword);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getModeratorPassword() {
		return _moderatorPassword;
	}

	@Override
	public void setModeratorPassword(String moderatorPassword) {
		_moderatorPassword = moderatorPassword;

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModeratorPassword",
						String.class);

				method.invoke(_meetingEntryRemoteModel, moderatorPassword);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_meetingEntryRemoteModel != null) {
			try {
				Class<?> clazz = _meetingEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_meetingEntryRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getMeetingEntryRemoteModel() {
		return _meetingEntryRemoteModel;
	}

	public void setMeetingEntryRemoteModel(BaseModel<?> meetingEntryRemoteModel) {
		_meetingEntryRemoteModel = meetingEntryRemoteModel;
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

		Class<?> remoteModelClass = _meetingEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_meetingEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			MeetingEntryLocalServiceUtil.addMeetingEntry(this);
		}
		else {
			MeetingEntryLocalServiceUtil.updateMeetingEntry(this);
		}
	}

	@Override
	public MeetingEntry toEscapedModel() {
		return (MeetingEntry)ProxyUtil.newProxyInstance(MeetingEntry.class.getClassLoader(),
			new Class[] { MeetingEntry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		MeetingEntryClp clone = new MeetingEntryClp();

		clone.setMeetingEntryId(getMeetingEntryId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setMeetingServerId(getMeetingServerId());
		clone.setName(getName());
		clone.setAttendeePassword(getAttendeePassword());
		clone.setModeratorPassword(getModeratorPassword());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(MeetingEntry meetingEntry) {
		long primaryKey = meetingEntry.getPrimaryKey();

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

		if (!(obj instanceof MeetingEntryClp)) {
			return false;
		}

		MeetingEntryClp meetingEntry = (MeetingEntryClp)obj;

		long primaryKey = meetingEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(25);

		sb.append("{meetingEntryId=");
		sb.append(getMeetingEntryId());
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
		sb.append(", meetingServerId=");
		sb.append(getMeetingServerId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", attendeePassword=");
		sb.append(getAttendeePassword());
		sb.append(", moderatorPassword=");
		sb.append(getModeratorPassword());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.bbb.model.MeetingEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>meetingEntryId</column-name><column-value><![CDATA[");
		sb.append(getMeetingEntryId());
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
			"<column><column-name>meetingServerId</column-name><column-value><![CDATA[");
		sb.append(getMeetingServerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>attendeePassword</column-name><column-value><![CDATA[");
		sb.append(getAttendeePassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>moderatorPassword</column-name><column-value><![CDATA[");
		sb.append(getModeratorPassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _meetingEntryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _meetingServerId;
	private String _name;
	private String _attendeePassword;
	private String _moderatorPassword;
	private int _status;
	private BaseModel<?> _meetingEntryRemoteModel;
}