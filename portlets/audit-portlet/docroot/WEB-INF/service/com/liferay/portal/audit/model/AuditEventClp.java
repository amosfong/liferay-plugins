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

package com.liferay.portal.audit.model;

import com.liferay.portal.audit.service.AuditEventLocalServiceUtil;
import com.liferay.portal.audit.service.ClpSerializer;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
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
 * @author Brian Wing Shun Chan
 */
public class AuditEventClp extends BaseModelImpl<AuditEvent>
	implements AuditEvent {
	public AuditEventClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return AuditEvent.class;
	}

	@Override
	public String getModelClassName() {
		return AuditEvent.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _auditEventId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAuditEventId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _auditEventId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("auditEventId", getAuditEventId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("eventType", getEventType());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());
		attributes.put("message", getMessage());
		attributes.put("clientHost", getClientHost());
		attributes.put("clientIP", getClientIP());
		attributes.put("serverName", getServerName());
		attributes.put("serverPort", getServerPort());
		attributes.put("sessionID", getSessionID());
		attributes.put("additionalInfo", getAdditionalInfo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long auditEventId = (Long)attributes.get("auditEventId");

		if (auditEventId != null) {
			setAuditEventId(auditEventId);
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

		String eventType = (String)attributes.get("eventType");

		if (eventType != null) {
			setEventType(eventType);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		String classPK = (String)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String message = (String)attributes.get("message");

		if (message != null) {
			setMessage(message);
		}

		String clientHost = (String)attributes.get("clientHost");

		if (clientHost != null) {
			setClientHost(clientHost);
		}

		String clientIP = (String)attributes.get("clientIP");

		if (clientIP != null) {
			setClientIP(clientIP);
		}

		String serverName = (String)attributes.get("serverName");

		if (serverName != null) {
			setServerName(serverName);
		}

		Integer serverPort = (Integer)attributes.get("serverPort");

		if (serverPort != null) {
			setServerPort(serverPort);
		}

		String sessionID = (String)attributes.get("sessionID");

		if (sessionID != null) {
			setSessionID(sessionID);
		}

		String additionalInfo = (String)attributes.get("additionalInfo");

		if (additionalInfo != null) {
			setAdditionalInfo(additionalInfo);
		}
	}

	@Override
	public long getAuditEventId() {
		return _auditEventId;
	}

	@Override
	public void setAuditEventId(long auditEventId) {
		_auditEventId = auditEventId;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setAuditEventId", long.class);

				method.invoke(_auditEventRemoteModel, auditEventId);
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

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_auditEventRemoteModel, companyId);
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

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_auditEventRemoteModel, userId);
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

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_auditEventRemoteModel, userName);
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

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_auditEventRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEventType() {
		return _eventType;
	}

	@Override
	public void setEventType(String eventType) {
		_eventType = eventType;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setEventType", String.class);

				method.invoke(_auditEventRemoteModel, eventType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClassName() {
		return _className;
	}

	@Override
	public void setClassName(String className) {
		_className = className;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setClassName", String.class);

				method.invoke(_auditEventRemoteModel, className);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(String classPK) {
		_classPK = classPK;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", String.class);

				method.invoke(_auditEventRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMessage() {
		return _message;
	}

	@Override
	public void setMessage(String message) {
		_message = message;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setMessage", String.class);

				method.invoke(_auditEventRemoteModel, message);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClientHost() {
		return _clientHost;
	}

	@Override
	public void setClientHost(String clientHost) {
		_clientHost = clientHost;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setClientHost", String.class);

				method.invoke(_auditEventRemoteModel, clientHost);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getClientIP() {
		return _clientIP;
	}

	@Override
	public void setClientIP(String clientIP) {
		_clientIP = clientIP;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setClientIP", String.class);

				method.invoke(_auditEventRemoteModel, clientIP);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getServerName() {
		return _serverName;
	}

	@Override
	public void setServerName(String serverName) {
		_serverName = serverName;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setServerName", String.class);

				method.invoke(_auditEventRemoteModel, serverName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getServerPort() {
		return _serverPort;
	}

	@Override
	public void setServerPort(int serverPort) {
		_serverPort = serverPort;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setServerPort", int.class);

				method.invoke(_auditEventRemoteModel, serverPort);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSessionID() {
		return _sessionID;
	}

	@Override
	public void setSessionID(String sessionID) {
		_sessionID = sessionID;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setSessionID", String.class);

				method.invoke(_auditEventRemoteModel, sessionID);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAdditionalInfo() {
		return _additionalInfo;
	}

	@Override
	public void setAdditionalInfo(String additionalInfo) {
		_additionalInfo = additionalInfo;

		if (_auditEventRemoteModel != null) {
			try {
				Class<?> clazz = _auditEventRemoteModel.getClass();

				Method method = clazz.getMethod("setAdditionalInfo",
						String.class);

				method.invoke(_auditEventRemoteModel, additionalInfo);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getAuditEventRemoteModel() {
		return _auditEventRemoteModel;
	}

	public void setAuditEventRemoteModel(BaseModel<?> auditEventRemoteModel) {
		_auditEventRemoteModel = auditEventRemoteModel;
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

		Class<?> remoteModelClass = _auditEventRemoteModel.getClass();

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

		Object returnValue = method.invoke(_auditEventRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			AuditEventLocalServiceUtil.addAuditEvent(this);
		}
		else {
			AuditEventLocalServiceUtil.updateAuditEvent(this);
		}
	}

	@Override
	public AuditEvent toEscapedModel() {
		return (AuditEvent)ProxyUtil.newProxyInstance(AuditEvent.class.getClassLoader(),
			new Class[] { AuditEvent.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AuditEventClp clone = new AuditEventClp();

		clone.setAuditEventId(getAuditEventId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setEventType(getEventType());
		clone.setClassName(getClassName());
		clone.setClassPK(getClassPK());
		clone.setMessage(getMessage());
		clone.setClientHost(getClientHost());
		clone.setClientIP(getClientIP());
		clone.setServerName(getServerName());
		clone.setServerPort(getServerPort());
		clone.setSessionID(getSessionID());
		clone.setAdditionalInfo(getAdditionalInfo());

		return clone;
	}

	@Override
	public int compareTo(AuditEvent auditEvent) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), auditEvent.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AuditEventClp)) {
			return false;
		}

		AuditEventClp auditEvent = (AuditEventClp)obj;

		long primaryKey = auditEvent.getPrimaryKey();

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
		StringBundler sb = new StringBundler(31);

		sb.append("{auditEventId=");
		sb.append(getAuditEventId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", eventType=");
		sb.append(getEventType());
		sb.append(", className=");
		sb.append(getClassName());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", message=");
		sb.append(getMessage());
		sb.append(", clientHost=");
		sb.append(getClientHost());
		sb.append(", clientIP=");
		sb.append(getClientIP());
		sb.append(", serverName=");
		sb.append(getServerName());
		sb.append(", serverPort=");
		sb.append(getServerPort());
		sb.append(", sessionID=");
		sb.append(getSessionID());
		sb.append(", additionalInfo=");
		sb.append(getAdditionalInfo());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.audit.model.AuditEvent");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>auditEventId</column-name><column-value><![CDATA[");
		sb.append(getAuditEventId());
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
			"<column><column-name>eventType</column-name><column-value><![CDATA[");
		sb.append(getEventType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>className</column-name><column-value><![CDATA[");
		sb.append(getClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>message</column-name><column-value><![CDATA[");
		sb.append(getMessage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>clientHost</column-name><column-value><![CDATA[");
		sb.append(getClientHost());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>clientIP</column-name><column-value><![CDATA[");
		sb.append(getClientIP());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serverName</column-name><column-value><![CDATA[");
		sb.append(getServerName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serverPort</column-name><column-value><![CDATA[");
		sb.append(getServerPort());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionID</column-name><column-value><![CDATA[");
		sb.append(getSessionID());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>additionalInfo</column-name><column-value><![CDATA[");
		sb.append(getAdditionalInfo());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _auditEventId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private String _eventType;
	private String _className;
	private String _classPK;
	private String _message;
	private String _clientHost;
	private String _clientIP;
	private String _serverName;
	private int _serverPort;
	private String _sessionID;
	private String _additionalInfo;
	private BaseModel<?> _auditEventRemoteModel;
}