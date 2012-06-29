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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing AuditEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEvent
 * @generated
 */
public class AuditEventCacheModel implements CacheModel<AuditEvent>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{auditEventId=");
		sb.append(auditEventId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", eventType=");
		sb.append(eventType);
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", message=");
		sb.append(message);
		sb.append(", clientHost=");
		sb.append(clientHost);
		sb.append(", clientIP=");
		sb.append(clientIP);
		sb.append(", serverName=");
		sb.append(serverName);
		sb.append(", serverPort=");
		sb.append(serverPort);
		sb.append(", sessionID=");
		sb.append(sessionID);
		sb.append(", additionalInfo=");
		sb.append(additionalInfo);
		sb.append("}");

		return sb.toString();
	}

	public AuditEvent toEntityModel() {
		AuditEventImpl auditEventImpl = new AuditEventImpl();

		auditEventImpl.setAuditEventId(auditEventId);
		auditEventImpl.setCompanyId(companyId);
		auditEventImpl.setUserId(userId);

		if (userName == null) {
			auditEventImpl.setUserName(StringPool.BLANK);
		}
		else {
			auditEventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			auditEventImpl.setCreateDate(null);
		}
		else {
			auditEventImpl.setCreateDate(new Date(createDate));
		}

		if (eventType == null) {
			auditEventImpl.setEventType(StringPool.BLANK);
		}
		else {
			auditEventImpl.setEventType(eventType);
		}

		if (className == null) {
			auditEventImpl.setClassName(StringPool.BLANK);
		}
		else {
			auditEventImpl.setClassName(className);
		}

		if (classPK == null) {
			auditEventImpl.setClassPK(StringPool.BLANK);
		}
		else {
			auditEventImpl.setClassPK(classPK);
		}

		if (message == null) {
			auditEventImpl.setMessage(StringPool.BLANK);
		}
		else {
			auditEventImpl.setMessage(message);
		}

		if (clientHost == null) {
			auditEventImpl.setClientHost(StringPool.BLANK);
		}
		else {
			auditEventImpl.setClientHost(clientHost);
		}

		if (clientIP == null) {
			auditEventImpl.setClientIP(StringPool.BLANK);
		}
		else {
			auditEventImpl.setClientIP(clientIP);
		}

		if (serverName == null) {
			auditEventImpl.setServerName(StringPool.BLANK);
		}
		else {
			auditEventImpl.setServerName(serverName);
		}

		auditEventImpl.setServerPort(serverPort);

		if (sessionID == null) {
			auditEventImpl.setSessionID(StringPool.BLANK);
		}
		else {
			auditEventImpl.setSessionID(sessionID);
		}

		if (additionalInfo == null) {
			auditEventImpl.setAdditionalInfo(StringPool.BLANK);
		}
		else {
			auditEventImpl.setAdditionalInfo(additionalInfo);
		}

		auditEventImpl.resetOriginalValues();

		return auditEventImpl;
	}

	public long auditEventId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public String eventType;
	public String className;
	public String classPK;
	public String message;
	public String clientHost;
	public String clientIP;
	public String serverName;
	public int serverPort;
	public String sessionID;
	public String additionalInfo;
}