/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.audit.model.impl;

import com.liferay.portal.audit.model.AuditEvent;
import com.liferay.portal.audit.model.AuditEventModel;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the AuditEvent service. Represents a row in the &quot;Audit_AuditEvent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portal.audit.model.AuditEventModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AuditEventImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEventImpl
 * @see com.liferay.portal.audit.model.AuditEvent
 * @see com.liferay.portal.audit.model.AuditEventModel
 * @generated
 */
public class AuditEventModelImpl extends BaseModelImpl<AuditEvent>
	implements AuditEventModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a audit event model instance should use the {@link com.liferay.portal.audit.model.AuditEvent} interface instead.
	 */
	public static final String TABLE_NAME = "Audit_AuditEvent";
	public static final Object[][] TABLE_COLUMNS = {
			{ "auditEventId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "eventType", Types.VARCHAR },
			{ "className", Types.VARCHAR },
			{ "classPK", Types.VARCHAR },
			{ "message", Types.VARCHAR },
			{ "clientHost", Types.VARCHAR },
			{ "clientIP", Types.VARCHAR },
			{ "serverName", Types.VARCHAR },
			{ "serverPort", Types.INTEGER },
			{ "sessionID", Types.VARCHAR },
			{ "additionalInfo", Types.CLOB }
		};
	public static final String TABLE_SQL_CREATE = "create table Audit_AuditEvent (auditEventId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(200) null,createDate DATE null,eventType VARCHAR(75) null,className VARCHAR(200) null,classPK VARCHAR(75) null,message STRING null,clientHost VARCHAR(255) null,clientIP VARCHAR(75) null,serverName VARCHAR(255) null,serverPort INTEGER,sessionID VARCHAR(255) null,additionalInfo TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table Audit_AuditEvent";
	public static final String ORDER_BY_JPQL = " ORDER BY auditEvent.createDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY Audit_AuditEvent.createDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.audit.model.AuditEvent"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.audit.model.AuditEvent"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.audit.model.AuditEvent"),
			true);
	public static long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.audit.model.AuditEvent"));

	public AuditEventModelImpl() {
	}

	public long getPrimaryKey() {
		return _auditEventId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAuditEventId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_auditEventId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return AuditEvent.class;
	}

	public String getModelClassName() {
		return AuditEvent.class.getName();
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

	public long getAuditEventId() {
		return _auditEventId;
	}

	public void setAuditEventId(long auditEventId) {
		_auditEventId = auditEventId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	public String getEventType() {
		if (_eventType == null) {
			return StringPool.BLANK;
		}
		else {
			return _eventType;
		}
	}

	public void setEventType(String eventType) {
		_eventType = eventType;
	}

	public String getClassName() {
		if (_className == null) {
			return StringPool.BLANK;
		}
		else {
			return _className;
		}
	}

	public void setClassName(String className) {
		_className = className;
	}

	public String getClassPK() {
		if (_classPK == null) {
			return StringPool.BLANK;
		}
		else {
			return _classPK;
		}
	}

	public void setClassPK(String classPK) {
		_classPK = classPK;
	}

	public String getMessage() {
		if (_message == null) {
			return StringPool.BLANK;
		}
		else {
			return _message;
		}
	}

	public void setMessage(String message) {
		_message = message;
	}

	public String getClientHost() {
		if (_clientHost == null) {
			return StringPool.BLANK;
		}
		else {
			return _clientHost;
		}
	}

	public void setClientHost(String clientHost) {
		_clientHost = clientHost;
	}

	public String getClientIP() {
		if (_clientIP == null) {
			return StringPool.BLANK;
		}
		else {
			return _clientIP;
		}
	}

	public void setClientIP(String clientIP) {
		_clientIP = clientIP;
	}

	public String getServerName() {
		if (_serverName == null) {
			return StringPool.BLANK;
		}
		else {
			return _serverName;
		}
	}

	public void setServerName(String serverName) {
		_serverName = serverName;
	}

	public int getServerPort() {
		return _serverPort;
	}

	public void setServerPort(int serverPort) {
		_serverPort = serverPort;
	}

	public String getSessionID() {
		if (_sessionID == null) {
			return StringPool.BLANK;
		}
		else {
			return _sessionID;
		}
	}

	public void setSessionID(String sessionID) {
		_sessionID = sessionID;
	}

	public String getAdditionalInfo() {
		if (_additionalInfo == null) {
			return StringPool.BLANK;
		}
		else {
			return _additionalInfo;
		}
	}

	public void setAdditionalInfo(String additionalInfo) {
		_additionalInfo = additionalInfo;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			AuditEvent.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AuditEvent toEscapedModel() {
		if (_escapedModelProxy == null) {
			_escapedModelProxy = (AuditEvent)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelProxyInterfaces,
					new AutoEscapeBeanHandler(this));
		}

		return _escapedModelProxy;
	}

	@Override
	public Object clone() {
		AuditEventImpl auditEventImpl = new AuditEventImpl();

		auditEventImpl.setAuditEventId(getAuditEventId());
		auditEventImpl.setCompanyId(getCompanyId());
		auditEventImpl.setUserId(getUserId());
		auditEventImpl.setUserName(getUserName());
		auditEventImpl.setCreateDate(getCreateDate());
		auditEventImpl.setEventType(getEventType());
		auditEventImpl.setClassName(getClassName());
		auditEventImpl.setClassPK(getClassPK());
		auditEventImpl.setMessage(getMessage());
		auditEventImpl.setClientHost(getClientHost());
		auditEventImpl.setClientIP(getClientIP());
		auditEventImpl.setServerName(getServerName());
		auditEventImpl.setServerPort(getServerPort());
		auditEventImpl.setSessionID(getSessionID());
		auditEventImpl.setAdditionalInfo(getAdditionalInfo());

		auditEventImpl.resetOriginalValues();

		return auditEventImpl;
	}

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
		if (obj == null) {
			return false;
		}

		AuditEvent auditEvent = null;

		try {
			auditEvent = (AuditEvent)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

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
	public void resetOriginalValues() {
		AuditEventModelImpl auditEventModelImpl = this;

		auditEventModelImpl._originalCompanyId = auditEventModelImpl._companyId;

		auditEventModelImpl._setOriginalCompanyId = false;

		auditEventModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AuditEvent> toCacheModel() {
		AuditEventCacheModel auditEventCacheModel = new AuditEventCacheModel();

		auditEventCacheModel.auditEventId = getAuditEventId();

		auditEventCacheModel.companyId = getCompanyId();

		auditEventCacheModel.userId = getUserId();

		auditEventCacheModel.userName = getUserName();

		String userName = auditEventCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			auditEventCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			auditEventCacheModel.createDate = createDate.getTime();
		}
		else {
			auditEventCacheModel.createDate = Long.MIN_VALUE;
		}

		auditEventCacheModel.eventType = getEventType();

		String eventType = auditEventCacheModel.eventType;

		if ((eventType != null) && (eventType.length() == 0)) {
			auditEventCacheModel.eventType = null;
		}

		auditEventCacheModel.className = getClassName();

		String className = auditEventCacheModel.className;

		if ((className != null) && (className.length() == 0)) {
			auditEventCacheModel.className = null;
		}

		auditEventCacheModel.classPK = getClassPK();

		String classPK = auditEventCacheModel.classPK;

		if ((classPK != null) && (classPK.length() == 0)) {
			auditEventCacheModel.classPK = null;
		}

		auditEventCacheModel.message = getMessage();

		String message = auditEventCacheModel.message;

		if ((message != null) && (message.length() == 0)) {
			auditEventCacheModel.message = null;
		}

		auditEventCacheModel.clientHost = getClientHost();

		String clientHost = auditEventCacheModel.clientHost;

		if ((clientHost != null) && (clientHost.length() == 0)) {
			auditEventCacheModel.clientHost = null;
		}

		auditEventCacheModel.clientIP = getClientIP();

		String clientIP = auditEventCacheModel.clientIP;

		if ((clientIP != null) && (clientIP.length() == 0)) {
			auditEventCacheModel.clientIP = null;
		}

		auditEventCacheModel.serverName = getServerName();

		String serverName = auditEventCacheModel.serverName;

		if ((serverName != null) && (serverName.length() == 0)) {
			auditEventCacheModel.serverName = null;
		}

		auditEventCacheModel.serverPort = getServerPort();

		auditEventCacheModel.sessionID = getSessionID();

		String sessionID = auditEventCacheModel.sessionID;

		if ((sessionID != null) && (sessionID.length() == 0)) {
			auditEventCacheModel.sessionID = null;
		}

		auditEventCacheModel.additionalInfo = getAdditionalInfo();

		String additionalInfo = auditEventCacheModel.additionalInfo;

		if ((additionalInfo != null) && (additionalInfo.length() == 0)) {
			auditEventCacheModel.additionalInfo = null;
		}

		return auditEventCacheModel;
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

	private static ClassLoader _classLoader = AuditEvent.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			AuditEvent.class
		};
	private long _auditEventId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
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
	private long _columnBitmask;
	private AuditEvent _escapedModelProxy;
}