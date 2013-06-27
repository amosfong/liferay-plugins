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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoProcessLink}.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLink
 * @generated
 */
public class KaleoProcessLinkWrapper implements KaleoProcessLink,
	ModelWrapper<KaleoProcessLink> {
	public KaleoProcessLinkWrapper(KaleoProcessLink kaleoProcessLink) {
		_kaleoProcessLink = kaleoProcessLink;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoProcessLink.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoProcessLink.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoProcessLinkId", getKaleoProcessLinkId());
		attributes.put("kaleoProcessId", getKaleoProcessId());
		attributes.put("workflowTaskName", getWorkflowTaskName());
		attributes.put("DDMTemplateId", getDDMTemplateId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoProcessLinkId = (Long)attributes.get("kaleoProcessLinkId");

		if (kaleoProcessLinkId != null) {
			setKaleoProcessLinkId(kaleoProcessLinkId);
		}

		Long kaleoProcessId = (Long)attributes.get("kaleoProcessId");

		if (kaleoProcessId != null) {
			setKaleoProcessId(kaleoProcessId);
		}

		String workflowTaskName = (String)attributes.get("workflowTaskName");

		if (workflowTaskName != null) {
			setWorkflowTaskName(workflowTaskName);
		}

		Long DDMTemplateId = (Long)attributes.get("DDMTemplateId");

		if (DDMTemplateId != null) {
			setDDMTemplateId(DDMTemplateId);
		}
	}

	/**
	* Returns the primary key of this kaleo process link.
	*
	* @return the primary key of this kaleo process link
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoProcessLink.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo process link.
	*
	* @param primaryKey the primary key of this kaleo process link
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoProcessLink.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo process link ID of this kaleo process link.
	*
	* @return the kaleo process link ID of this kaleo process link
	*/
	@Override
	public long getKaleoProcessLinkId() {
		return _kaleoProcessLink.getKaleoProcessLinkId();
	}

	/**
	* Sets the kaleo process link ID of this kaleo process link.
	*
	* @param kaleoProcessLinkId the kaleo process link ID of this kaleo process link
	*/
	@Override
	public void setKaleoProcessLinkId(long kaleoProcessLinkId) {
		_kaleoProcessLink.setKaleoProcessLinkId(kaleoProcessLinkId);
	}

	/**
	* Returns the kaleo process ID of this kaleo process link.
	*
	* @return the kaleo process ID of this kaleo process link
	*/
	@Override
	public long getKaleoProcessId() {
		return _kaleoProcessLink.getKaleoProcessId();
	}

	/**
	* Sets the kaleo process ID of this kaleo process link.
	*
	* @param kaleoProcessId the kaleo process ID of this kaleo process link
	*/
	@Override
	public void setKaleoProcessId(long kaleoProcessId) {
		_kaleoProcessLink.setKaleoProcessId(kaleoProcessId);
	}

	/**
	* Returns the workflow task name of this kaleo process link.
	*
	* @return the workflow task name of this kaleo process link
	*/
	@Override
	public java.lang.String getWorkflowTaskName() {
		return _kaleoProcessLink.getWorkflowTaskName();
	}

	/**
	* Sets the workflow task name of this kaleo process link.
	*
	* @param workflowTaskName the workflow task name of this kaleo process link
	*/
	@Override
	public void setWorkflowTaskName(java.lang.String workflowTaskName) {
		_kaleoProcessLink.setWorkflowTaskName(workflowTaskName);
	}

	/**
	* Returns the d d m template ID of this kaleo process link.
	*
	* @return the d d m template ID of this kaleo process link
	*/
	@Override
	public long getDDMTemplateId() {
		return _kaleoProcessLink.getDDMTemplateId();
	}

	/**
	* Sets the d d m template ID of this kaleo process link.
	*
	* @param DDMTemplateId the d d m template ID of this kaleo process link
	*/
	@Override
	public void setDDMTemplateId(long DDMTemplateId) {
		_kaleoProcessLink.setDDMTemplateId(DDMTemplateId);
	}

	@Override
	public boolean isNew() {
		return _kaleoProcessLink.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoProcessLink.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoProcessLink.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoProcessLink.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoProcessLink.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoProcessLink.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoProcessLink.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoProcessLink.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoProcessLink.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoProcessLink.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoProcessLink.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoProcessLinkWrapper((KaleoProcessLink)_kaleoProcessLink.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink) {
		return _kaleoProcessLink.compareTo(kaleoProcessLink);
	}

	@Override
	public int hashCode() {
		return _kaleoProcessLink.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> toCacheModel() {
		return _kaleoProcessLink.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink toEscapedModel() {
		return new KaleoProcessLinkWrapper(_kaleoProcessLink.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink toUnescapedModel() {
		return new KaleoProcessLinkWrapper(_kaleoProcessLink.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoProcessLink.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoProcessLink.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoProcessLink.persist();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess getKaleoProcess()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLink.getKaleoProcess();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoProcessLinkWrapper)) {
			return false;
		}

		KaleoProcessLinkWrapper kaleoProcessLinkWrapper = (KaleoProcessLinkWrapper)obj;

		if (Validator.equals(_kaleoProcessLink,
					kaleoProcessLinkWrapper._kaleoProcessLink)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public KaleoProcessLink getWrappedKaleoProcessLink() {
		return _kaleoProcessLink;
	}

	@Override
	public KaleoProcessLink getWrappedModel() {
		return _kaleoProcessLink;
	}

	@Override
	public void resetOriginalValues() {
		_kaleoProcessLink.resetOriginalValues();
	}

	private KaleoProcessLink _kaleoProcessLink;
}