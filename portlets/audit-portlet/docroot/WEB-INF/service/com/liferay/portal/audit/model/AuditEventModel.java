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

package com.liferay.portal.audit.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the AuditEvent service. Represents a row in the &quot;Audit_AuditEvent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.audit.model.impl.AuditEventModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.audit.model.impl.AuditEventImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEvent
 * @see com.liferay.portal.audit.model.impl.AuditEventImpl
 * @see com.liferay.portal.audit.model.impl.AuditEventModelImpl
 * @generated
 */
public interface AuditEventModel extends BaseModel<AuditEvent> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a audit event model instance should use the {@link AuditEvent} interface instead.
	 */

	/**
	 * Returns the primary key of this audit event.
	 *
	 * @return the primary key of this audit event
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this audit event.
	 *
	 * @param primaryKey the primary key of this audit event
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the audit event ID of this audit event.
	 *
	 * @return the audit event ID of this audit event
	 */
	public long getAuditEventId();

	/**
	 * Sets the audit event ID of this audit event.
	 *
	 * @param auditEventId the audit event ID of this audit event
	 */
	public void setAuditEventId(long auditEventId);

	/**
	 * Returns the company ID of this audit event.
	 *
	 * @return the company ID of this audit event
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this audit event.
	 *
	 * @param companyId the company ID of this audit event
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this audit event.
	 *
	 * @return the user ID of this audit event
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this audit event.
	 *
	 * @param userId the user ID of this audit event
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this audit event.
	 *
	 * @return the user uuid of this audit event
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this audit event.
	 *
	 * @param userUuid the user uuid of this audit event
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this audit event.
	 *
	 * @return the user name of this audit event
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this audit event.
	 *
	 * @param userName the user name of this audit event
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this audit event.
	 *
	 * @return the create date of this audit event
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this audit event.
	 *
	 * @param createDate the create date of this audit event
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the event type of this audit event.
	 *
	 * @return the event type of this audit event
	 */
	@AutoEscape
	public String getEventType();

	/**
	 * Sets the event type of this audit event.
	 *
	 * @param eventType the event type of this audit event
	 */
	public void setEventType(String eventType);

	/**
	 * Returns the class name of this audit event.
	 *
	 * @return the class name of this audit event
	 */
	@AutoEscape
	public String getClassName();

	/**
	 * Sets the class name of this audit event.
	 *
	 * @param className the class name of this audit event
	 */
	public void setClassName(String className);

	/**
	 * Returns the class p k of this audit event.
	 *
	 * @return the class p k of this audit event
	 */
	@AutoEscape
	public String getClassPK();

	/**
	 * Sets the class p k of this audit event.
	 *
	 * @param classPK the class p k of this audit event
	 */
	public void setClassPK(String classPK);

	/**
	 * Returns the message of this audit event.
	 *
	 * @return the message of this audit event
	 */
	@AutoEscape
	public String getMessage();

	/**
	 * Sets the message of this audit event.
	 *
	 * @param message the message of this audit event
	 */
	public void setMessage(String message);

	/**
	 * Returns the client host of this audit event.
	 *
	 * @return the client host of this audit event
	 */
	@AutoEscape
	public String getClientHost();

	/**
	 * Sets the client host of this audit event.
	 *
	 * @param clientHost the client host of this audit event
	 */
	public void setClientHost(String clientHost);

	/**
	 * Returns the client i p of this audit event.
	 *
	 * @return the client i p of this audit event
	 */
	@AutoEscape
	public String getClientIP();

	/**
	 * Sets the client i p of this audit event.
	 *
	 * @param clientIP the client i p of this audit event
	 */
	public void setClientIP(String clientIP);

	/**
	 * Returns the server name of this audit event.
	 *
	 * @return the server name of this audit event
	 */
	@AutoEscape
	public String getServerName();

	/**
	 * Sets the server name of this audit event.
	 *
	 * @param serverName the server name of this audit event
	 */
	public void setServerName(String serverName);

	/**
	 * Returns the server port of this audit event.
	 *
	 * @return the server port of this audit event
	 */
	public int getServerPort();

	/**
	 * Sets the server port of this audit event.
	 *
	 * @param serverPort the server port of this audit event
	 */
	public void setServerPort(int serverPort);

	/**
	 * Returns the session i d of this audit event.
	 *
	 * @return the session i d of this audit event
	 */
	@AutoEscape
	public String getSessionID();

	/**
	 * Sets the session i d of this audit event.
	 *
	 * @param sessionID the session i d of this audit event
	 */
	public void setSessionID(String sessionID);

	/**
	 * Returns the additional info of this audit event.
	 *
	 * @return the additional info of this audit event
	 */
	@AutoEscape
	public String getAdditionalInfo();

	/**
	 * Sets the additional info of this audit event.
	 *
	 * @param additionalInfo the additional info of this audit event
	 */
	public void setAdditionalInfo(String additionalInfo);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(AuditEvent auditEvent);

	public int hashCode();

	public CacheModel<AuditEvent> toCacheModel();

	public AuditEvent toEscapedModel();

	public AuditEvent toUnescapedModel();

	public String toString();

	public String toXmlString();
}