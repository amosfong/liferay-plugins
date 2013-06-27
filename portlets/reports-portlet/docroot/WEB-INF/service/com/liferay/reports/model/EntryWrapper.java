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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Entry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Entry
 * @generated
 */
public class EntryWrapper implements Entry, ModelWrapper<Entry> {
	public EntryWrapper(Entry entry) {
		_entry = entry;
	}

	@Override
	public Class<?> getModelClass() {
		return Entry.class;
	}

	@Override
	public String getModelClassName() {
		return Entry.class.getName();
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

	/**
	* Returns the primary key of this entry.
	*
	* @return the primary key of this entry
	*/
	@Override
	public long getPrimaryKey() {
		return _entry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this entry.
	*
	* @param primaryKey the primary key of this entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_entry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the entry ID of this entry.
	*
	* @return the entry ID of this entry
	*/
	@Override
	public long getEntryId() {
		return _entry.getEntryId();
	}

	/**
	* Sets the entry ID of this entry.
	*
	* @param entryId the entry ID of this entry
	*/
	@Override
	public void setEntryId(long entryId) {
		_entry.setEntryId(entryId);
	}

	/**
	* Returns the group ID of this entry.
	*
	* @return the group ID of this entry
	*/
	@Override
	public long getGroupId() {
		return _entry.getGroupId();
	}

	/**
	* Sets the group ID of this entry.
	*
	* @param groupId the group ID of this entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_entry.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this entry.
	*
	* @return the company ID of this entry
	*/
	@Override
	public long getCompanyId() {
		return _entry.getCompanyId();
	}

	/**
	* Sets the company ID of this entry.
	*
	* @param companyId the company ID of this entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_entry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this entry.
	*
	* @return the user ID of this entry
	*/
	@Override
	public long getUserId() {
		return _entry.getUserId();
	}

	/**
	* Sets the user ID of this entry.
	*
	* @param userId the user ID of this entry
	*/
	@Override
	public void setUserId(long userId) {
		_entry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this entry.
	*
	* @return the user uuid of this entry
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _entry.getUserUuid();
	}

	/**
	* Sets the user uuid of this entry.
	*
	* @param userUuid the user uuid of this entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_entry.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this entry.
	*
	* @return the user name of this entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _entry.getUserName();
	}

	/**
	* Sets the user name of this entry.
	*
	* @param userName the user name of this entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_entry.setUserName(userName);
	}

	/**
	* Returns the create date of this entry.
	*
	* @return the create date of this entry
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _entry.getCreateDate();
	}

	/**
	* Sets the create date of this entry.
	*
	* @param createDate the create date of this entry
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_entry.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this entry.
	*
	* @return the modified date of this entry
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _entry.getModifiedDate();
	}

	/**
	* Sets the modified date of this entry.
	*
	* @param modifiedDate the modified date of this entry
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_entry.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the definition ID of this entry.
	*
	* @return the definition ID of this entry
	*/
	@Override
	public long getDefinitionId() {
		return _entry.getDefinitionId();
	}

	/**
	* Sets the definition ID of this entry.
	*
	* @param definitionId the definition ID of this entry
	*/
	@Override
	public void setDefinitionId(long definitionId) {
		_entry.setDefinitionId(definitionId);
	}

	/**
	* Returns the format of this entry.
	*
	* @return the format of this entry
	*/
	@Override
	public java.lang.String getFormat() {
		return _entry.getFormat();
	}

	/**
	* Sets the format of this entry.
	*
	* @param format the format of this entry
	*/
	@Override
	public void setFormat(java.lang.String format) {
		_entry.setFormat(format);
	}

	/**
	* Returns the schedule request of this entry.
	*
	* @return the schedule request of this entry
	*/
	@Override
	public boolean getScheduleRequest() {
		return _entry.getScheduleRequest();
	}

	/**
	* Returns <code>true</code> if this entry is schedule request.
	*
	* @return <code>true</code> if this entry is schedule request; <code>false</code> otherwise
	*/
	@Override
	public boolean isScheduleRequest() {
		return _entry.isScheduleRequest();
	}

	/**
	* Sets whether this entry is schedule request.
	*
	* @param scheduleRequest the schedule request of this entry
	*/
	@Override
	public void setScheduleRequest(boolean scheduleRequest) {
		_entry.setScheduleRequest(scheduleRequest);
	}

	/**
	* Returns the start date of this entry.
	*
	* @return the start date of this entry
	*/
	@Override
	public java.util.Date getStartDate() {
		return _entry.getStartDate();
	}

	/**
	* Sets the start date of this entry.
	*
	* @param startDate the start date of this entry
	*/
	@Override
	public void setStartDate(java.util.Date startDate) {
		_entry.setStartDate(startDate);
	}

	/**
	* Returns the end date of this entry.
	*
	* @return the end date of this entry
	*/
	@Override
	public java.util.Date getEndDate() {
		return _entry.getEndDate();
	}

	/**
	* Sets the end date of this entry.
	*
	* @param endDate the end date of this entry
	*/
	@Override
	public void setEndDate(java.util.Date endDate) {
		_entry.setEndDate(endDate);
	}

	/**
	* Returns the repeating of this entry.
	*
	* @return the repeating of this entry
	*/
	@Override
	public boolean getRepeating() {
		return _entry.getRepeating();
	}

	/**
	* Returns <code>true</code> if this entry is repeating.
	*
	* @return <code>true</code> if this entry is repeating; <code>false</code> otherwise
	*/
	@Override
	public boolean isRepeating() {
		return _entry.isRepeating();
	}

	/**
	* Sets whether this entry is repeating.
	*
	* @param repeating the repeating of this entry
	*/
	@Override
	public void setRepeating(boolean repeating) {
		_entry.setRepeating(repeating);
	}

	/**
	* Returns the recurrence of this entry.
	*
	* @return the recurrence of this entry
	*/
	@Override
	public java.lang.String getRecurrence() {
		return _entry.getRecurrence();
	}

	/**
	* Sets the recurrence of this entry.
	*
	* @param recurrence the recurrence of this entry
	*/
	@Override
	public void setRecurrence(java.lang.String recurrence) {
		_entry.setRecurrence(recurrence);
	}

	/**
	* Returns the email notifications of this entry.
	*
	* @return the email notifications of this entry
	*/
	@Override
	public java.lang.String getEmailNotifications() {
		return _entry.getEmailNotifications();
	}

	/**
	* Sets the email notifications of this entry.
	*
	* @param emailNotifications the email notifications of this entry
	*/
	@Override
	public void setEmailNotifications(java.lang.String emailNotifications) {
		_entry.setEmailNotifications(emailNotifications);
	}

	/**
	* Returns the email delivery of this entry.
	*
	* @return the email delivery of this entry
	*/
	@Override
	public java.lang.String getEmailDelivery() {
		return _entry.getEmailDelivery();
	}

	/**
	* Sets the email delivery of this entry.
	*
	* @param emailDelivery the email delivery of this entry
	*/
	@Override
	public void setEmailDelivery(java.lang.String emailDelivery) {
		_entry.setEmailDelivery(emailDelivery);
	}

	/**
	* Returns the portlet ID of this entry.
	*
	* @return the portlet ID of this entry
	*/
	@Override
	public java.lang.String getPortletId() {
		return _entry.getPortletId();
	}

	/**
	* Sets the portlet ID of this entry.
	*
	* @param portletId the portlet ID of this entry
	*/
	@Override
	public void setPortletId(java.lang.String portletId) {
		_entry.setPortletId(portletId);
	}

	/**
	* Returns the page u r l of this entry.
	*
	* @return the page u r l of this entry
	*/
	@Override
	public java.lang.String getPageURL() {
		return _entry.getPageURL();
	}

	/**
	* Sets the page u r l of this entry.
	*
	* @param pageURL the page u r l of this entry
	*/
	@Override
	public void setPageURL(java.lang.String pageURL) {
		_entry.setPageURL(pageURL);
	}

	/**
	* Returns the report parameters of this entry.
	*
	* @return the report parameters of this entry
	*/
	@Override
	public java.lang.String getReportParameters() {
		return _entry.getReportParameters();
	}

	/**
	* Sets the report parameters of this entry.
	*
	* @param reportParameters the report parameters of this entry
	*/
	@Override
	public void setReportParameters(java.lang.String reportParameters) {
		_entry.setReportParameters(reportParameters);
	}

	/**
	* Returns the status of this entry.
	*
	* @return the status of this entry
	*/
	@Override
	public java.lang.String getStatus() {
		return _entry.getStatus();
	}

	/**
	* Sets the status of this entry.
	*
	* @param status the status of this entry
	*/
	@Override
	public void setStatus(java.lang.String status) {
		_entry.setStatus(status);
	}

	/**
	* Returns the error message of this entry.
	*
	* @return the error message of this entry
	*/
	@Override
	public java.lang.String getErrorMessage() {
		return _entry.getErrorMessage();
	}

	/**
	* Sets the error message of this entry.
	*
	* @param errorMessage the error message of this entry
	*/
	@Override
	public void setErrorMessage(java.lang.String errorMessage) {
		_entry.setErrorMessage(errorMessage);
	}

	@Override
	public boolean isNew() {
		return _entry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_entry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _entry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_entry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _entry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _entry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_entry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _entry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_entry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_entry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_entry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new EntryWrapper((Entry)_entry.clone());
	}

	@Override
	public int compareTo(com.liferay.reports.model.Entry entry) {
		return _entry.compareTo(entry);
	}

	@Override
	public int hashCode() {
		return _entry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.reports.model.Entry> toCacheModel() {
		return _entry.toCacheModel();
	}

	@Override
	public com.liferay.reports.model.Entry toEscapedModel() {
		return new EntryWrapper(_entry.toEscapedModel());
	}

	@Override
	public com.liferay.reports.model.Entry toUnescapedModel() {
		return new EntryWrapper(_entry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _entry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _entry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_entry.persist();
	}

	@Override
	public java.lang.String getAttachmentsDir() {
		return _entry.getAttachmentsDir();
	}

	@Override
	public java.lang.String[] getAttachmentsFiles()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _entry.getAttachmentsFiles();
	}

	@Override
	public java.lang.String getJobName() {
		return _entry.getJobName();
	}

	@Override
	public com.liferay.portal.kernel.cal.TZSRecurrence getRecurrenceObj() {
		return _entry.getRecurrenceObj();
	}

	@Override
	public java.lang.String getSchedulerRequestName() {
		return _entry.getSchedulerRequestName();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EntryWrapper)) {
			return false;
		}

		EntryWrapper entryWrapper = (EntryWrapper)obj;

		if (Validator.equals(_entry, entryWrapper._entry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public Entry getWrappedEntry() {
		return _entry;
	}

	@Override
	public Entry getWrappedModel() {
		return _entry;
	}

	@Override
	public void resetOriginalValues() {
		_entry.resetOriginalValues();
	}

	private Entry _entry;
}