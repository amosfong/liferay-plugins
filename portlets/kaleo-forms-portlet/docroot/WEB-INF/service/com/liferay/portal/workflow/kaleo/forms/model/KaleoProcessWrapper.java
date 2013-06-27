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

package com.liferay.portal.workflow.kaleo.forms.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoProcess}.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcess
 * @generated
 */
public class KaleoProcessWrapper implements KaleoProcess,
	ModelWrapper<KaleoProcess> {
	public KaleoProcessWrapper(KaleoProcess kaleoProcess) {
		_kaleoProcess = kaleoProcess;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoProcess.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoProcess.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoProcessId", getKaleoProcessId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("DDLRecordSetId", getDDLRecordSetId());
		attributes.put("DDMTemplateId", getDDMTemplateId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoProcessId = (Long)attributes.get("kaleoProcessId");

		if (kaleoProcessId != null) {
			setKaleoProcessId(kaleoProcessId);
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

		Long DDLRecordSetId = (Long)attributes.get("DDLRecordSetId");

		if (DDLRecordSetId != null) {
			setDDLRecordSetId(DDLRecordSetId);
		}

		Long DDMTemplateId = (Long)attributes.get("DDMTemplateId");

		if (DDMTemplateId != null) {
			setDDMTemplateId(DDMTemplateId);
		}
	}

	/**
	* Returns the primary key of this kaleo process.
	*
	* @return the primary key of this kaleo process
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoProcess.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo process.
	*
	* @param primaryKey the primary key of this kaleo process
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoProcess.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo process ID of this kaleo process.
	*
	* @return the kaleo process ID of this kaleo process
	*/
	@Override
	public long getKaleoProcessId() {
		return _kaleoProcess.getKaleoProcessId();
	}

	/**
	* Sets the kaleo process ID of this kaleo process.
	*
	* @param kaleoProcessId the kaleo process ID of this kaleo process
	*/
	@Override
	public void setKaleoProcessId(long kaleoProcessId) {
		_kaleoProcess.setKaleoProcessId(kaleoProcessId);
	}

	/**
	* Returns the group ID of this kaleo process.
	*
	* @return the group ID of this kaleo process
	*/
	@Override
	public long getGroupId() {
		return _kaleoProcess.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo process.
	*
	* @param groupId the group ID of this kaleo process
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoProcess.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo process.
	*
	* @return the company ID of this kaleo process
	*/
	@Override
	public long getCompanyId() {
		return _kaleoProcess.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo process.
	*
	* @param companyId the company ID of this kaleo process
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoProcess.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo process.
	*
	* @return the user ID of this kaleo process
	*/
	@Override
	public long getUserId() {
		return _kaleoProcess.getUserId();
	}

	/**
	* Sets the user ID of this kaleo process.
	*
	* @param userId the user ID of this kaleo process
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoProcess.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo process.
	*
	* @return the user uuid of this kaleo process
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcess.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo process.
	*
	* @param userUuid the user uuid of this kaleo process
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoProcess.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo process.
	*
	* @return the user name of this kaleo process
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoProcess.getUserName();
	}

	/**
	* Sets the user name of this kaleo process.
	*
	* @param userName the user name of this kaleo process
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoProcess.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo process.
	*
	* @return the create date of this kaleo process
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kaleoProcess.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo process.
	*
	* @param createDate the create date of this kaleo process
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kaleoProcess.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo process.
	*
	* @return the modified date of this kaleo process
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kaleoProcess.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo process.
	*
	* @param modifiedDate the modified date of this kaleo process
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoProcess.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the d d l record set ID of this kaleo process.
	*
	* @return the d d l record set ID of this kaleo process
	*/
	@Override
	public long getDDLRecordSetId() {
		return _kaleoProcess.getDDLRecordSetId();
	}

	/**
	* Sets the d d l record set ID of this kaleo process.
	*
	* @param DDLRecordSetId the d d l record set ID of this kaleo process
	*/
	@Override
	public void setDDLRecordSetId(long DDLRecordSetId) {
		_kaleoProcess.setDDLRecordSetId(DDLRecordSetId);
	}

	/**
	* Returns the d d m template ID of this kaleo process.
	*
	* @return the d d m template ID of this kaleo process
	*/
	@Override
	public long getDDMTemplateId() {
		return _kaleoProcess.getDDMTemplateId();
	}

	/**
	* Sets the d d m template ID of this kaleo process.
	*
	* @param DDMTemplateId the d d m template ID of this kaleo process
	*/
	@Override
	public void setDDMTemplateId(long DDMTemplateId) {
		_kaleoProcess.setDDMTemplateId(DDMTemplateId);
	}

	@Override
	public boolean isNew() {
		return _kaleoProcess.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoProcess.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoProcess.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoProcess.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoProcess.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoProcess.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoProcess.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoProcess.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoProcess.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoProcess.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoProcess.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoProcessWrapper((KaleoProcess)_kaleoProcess.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess kaleoProcess) {
		return _kaleoProcess.compareTo(kaleoProcess);
	}

	@Override
	public int hashCode() {
		return _kaleoProcess.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess> toCacheModel() {
		return _kaleoProcess.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess toEscapedModel() {
		return new KaleoProcessWrapper(_kaleoProcess.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess toUnescapedModel() {
		return new KaleoProcessWrapper(_kaleoProcess.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoProcess.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoProcess.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoProcess.persist();
	}

	@Override
	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet getDDLRecordSet()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcess.getDDLRecordSet();
	}

	@Override
	public com.liferay.portlet.dynamicdatamapping.model.DDMTemplate getDDMTemplate()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcess.getDDMTemplate();
	}

	@Override
	public java.lang.String getDescription(java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcess.getDescription(locale);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> getKaleoProcessLinks()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcess.getKaleoProcessLinks();
	}

	@Override
	public java.lang.String getName(java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcess.getName(locale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoProcessWrapper)) {
			return false;
		}

		KaleoProcessWrapper kaleoProcessWrapper = (KaleoProcessWrapper)obj;

		if (Validator.equals(_kaleoProcess, kaleoProcessWrapper._kaleoProcess)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public KaleoProcess getWrappedKaleoProcess() {
		return _kaleoProcess;
	}

	@Override
	public KaleoProcess getWrappedModel() {
		return _kaleoProcess;
	}

	@Override
	public void resetOriginalValues() {
		_kaleoProcess.resetOriginalValues();
	}

	private KaleoProcess _kaleoProcess;
}