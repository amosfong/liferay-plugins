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

package com.liferay.reports.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.reports.service.EntryLocalServiceUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryClp extends BaseModelImpl<Entry> implements Entry {
	public EntryClp() {
	}

	public Class<?> getModelClass() {
		return Entry.class;
	}

	public String getModelClassName() {
		return Entry.class.getName();
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _entryId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("entryId", getEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("definitionId", getDefinitionId());
		attributes.put("format", getFormat());
		attributes.put("scheduleRequest", getScheduleRequest());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("repeating", getRepeating());
		attributes.put("recurrence", getRecurrence());
		attributes.put("emailNotifications", getEmailNotifications());
		attributes.put("emailDelivery", getEmailDelivery());
		attributes.put("portletId", getPortletId());
		attributes.put("pageURL", getPageURL());
		attributes.put("reportParameters", getReportParameters());
		attributes.put("status", getStatus());
		attributes.put("errorMessage", getErrorMessage());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
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

		Long definitionId = (Long)attributes.get("definitionId");

		if (definitionId != null) {
			setDefinitionId(definitionId);
		}

		String format = (String)attributes.get("format");

		if (format != null) {
			setFormat(format);
		}

		Boolean scheduleRequest = (Boolean)attributes.get("scheduleRequest");

		if (scheduleRequest != null) {
			setScheduleRequest(scheduleRequest);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Boolean repeating = (Boolean)attributes.get("repeating");

		if (repeating != null) {
			setRepeating(repeating);
		}

		String recurrence = (String)attributes.get("recurrence");

		if (recurrence != null) {
			setRecurrence(recurrence);
		}

		String emailNotifications = (String)attributes.get("emailNotifications");

		if (emailNotifications != null) {
			setEmailNotifications(emailNotifications);
		}

		String emailDelivery = (String)attributes.get("emailDelivery");

		if (emailDelivery != null) {
			setEmailDelivery(emailDelivery);
		}

		String portletId = (String)attributes.get("portletId");

		if (portletId != null) {
			setPortletId(portletId);
		}

		String pageURL = (String)attributes.get("pageURL");

		if (pageURL != null) {
			setPageURL(pageURL);
		}

		String reportParameters = (String)attributes.get("reportParameters");

		if (reportParameters != null) {
			setReportParameters(reportParameters);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String errorMessage = (String)attributes.get("errorMessage");

		if (errorMessage != null) {
			setErrorMessage(errorMessage);
		}
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
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

	public long getDefinitionId() {
		return _definitionId;
	}

	public void setDefinitionId(long definitionId) {
		_definitionId = definitionId;
	}

	public String getFormat() {
		return _format;
	}

	public void setFormat(String format) {
		_format = format;
	}

	public boolean getScheduleRequest() {
		return _scheduleRequest;
	}

	public boolean isScheduleRequest() {
		return _scheduleRequest;
	}

	public void setScheduleRequest(boolean scheduleRequest) {
		_scheduleRequest = scheduleRequest;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public boolean getRepeating() {
		return _repeating;
	}

	public boolean isRepeating() {
		return _repeating;
	}

	public void setRepeating(boolean repeating) {
		_repeating = repeating;
	}

	public String getRecurrence() {
		return _recurrence;
	}

	public void setRecurrence(String recurrence) {
		_recurrence = recurrence;
	}

	public String getEmailNotifications() {
		return _emailNotifications;
	}

	public void setEmailNotifications(String emailNotifications) {
		_emailNotifications = emailNotifications;
	}

	public String getEmailDelivery() {
		return _emailDelivery;
	}

	public void setEmailDelivery(String emailDelivery) {
		_emailDelivery = emailDelivery;
	}

	public String getPortletId() {
		return _portletId;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	public String getPageURL() {
		return _pageURL;
	}

	public void setPageURL(String pageURL) {
		_pageURL = pageURL;
	}

	public String getReportParameters() {
		return _reportParameters;
	}

	public void setReportParameters(String reportParameters) {
		_reportParameters = reportParameters;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public String getErrorMessage() {
		return _errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		_errorMessage = errorMessage;
	}

	public java.lang.String getSchedulerRequestName() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getJobName() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String[] getAttachmentsFiles() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getAttachmentsDir() {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portal.kernel.cal.TZSRecurrence getRecurrenceObj() {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getEntryRemoteModel() {
		return _entryRemoteModel;
	}

	public void setEntryRemoteModel(BaseModel<?> entryRemoteModel) {
		_entryRemoteModel = entryRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			EntryLocalServiceUtil.addEntry(this);
		}
		else {
			EntryLocalServiceUtil.updateEntry(this);
		}
	}

	@Override
	public Entry toEscapedModel() {
		return (Entry)ProxyUtil.newProxyInstance(Entry.class.getClassLoader(),
			new Class[] { Entry.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		EntryClp clone = new EntryClp();

		clone.setEntryId(getEntryId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setDefinitionId(getDefinitionId());
		clone.setFormat(getFormat());
		clone.setScheduleRequest(getScheduleRequest());
		clone.setStartDate(getStartDate());
		clone.setEndDate(getEndDate());
		clone.setRepeating(getRepeating());
		clone.setRecurrence(getRecurrence());
		clone.setEmailNotifications(getEmailNotifications());
		clone.setEmailDelivery(getEmailDelivery());
		clone.setPortletId(getPortletId());
		clone.setPageURL(getPageURL());
		clone.setReportParameters(getReportParameters());
		clone.setStatus(getStatus());
		clone.setErrorMessage(getErrorMessage());

		return clone;
	}

	public int compareTo(Entry entry) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(), entry.getModifiedDate());

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

		EntryClp entry = null;

		try {
			entry = (EntryClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = entry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(43);

		sb.append("{entryId=");
		sb.append(getEntryId());
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
		sb.append(", definitionId=");
		sb.append(getDefinitionId());
		sb.append(", format=");
		sb.append(getFormat());
		sb.append(", scheduleRequest=");
		sb.append(getScheduleRequest());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", repeating=");
		sb.append(getRepeating());
		sb.append(", recurrence=");
		sb.append(getRecurrence());
		sb.append(", emailNotifications=");
		sb.append(getEmailNotifications());
		sb.append(", emailDelivery=");
		sb.append(getEmailDelivery());
		sb.append(", portletId=");
		sb.append(getPortletId());
		sb.append(", pageURL=");
		sb.append(getPageURL());
		sb.append(", reportParameters=");
		sb.append(getReportParameters());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append(", errorMessage=");
		sb.append(getErrorMessage());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(67);

		sb.append("<model><model-name>");
		sb.append("com.liferay.reports.model.Entry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>entryId</column-name><column-value><![CDATA[");
		sb.append(getEntryId());
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
			"<column><column-name>definitionId</column-name><column-value><![CDATA[");
		sb.append(getDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>format</column-name><column-value><![CDATA[");
		sb.append(getFormat());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scheduleRequest</column-name><column-value><![CDATA[");
		sb.append(getScheduleRequest());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>repeating</column-name><column-value><![CDATA[");
		sb.append(getRepeating());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recurrence</column-name><column-value><![CDATA[");
		sb.append(getRecurrence());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailNotifications</column-name><column-value><![CDATA[");
		sb.append(getEmailNotifications());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailDelivery</column-name><column-value><![CDATA[");
		sb.append(getEmailDelivery());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletId</column-name><column-value><![CDATA[");
		sb.append(getPortletId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pageURL</column-name><column-value><![CDATA[");
		sb.append(getPageURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reportParameters</column-name><column-value><![CDATA[");
		sb.append(getReportParameters());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>errorMessage</column-name><column-value><![CDATA[");
		sb.append(getErrorMessage());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _entryId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _definitionId;
	private String _format;
	private boolean _scheduleRequest;
	private Date _startDate;
	private Date _endDate;
	private boolean _repeating;
	private String _recurrence;
	private String _emailNotifications;
	private String _emailDelivery;
	private String _portletId;
	private String _pageURL;
	private String _reportParameters;
	private String _status;
	private String _errorMessage;
	private BaseModel<?> _entryRemoteModel;
}