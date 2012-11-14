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

package com.liferay.portal.workflow.kaleo.forms.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class KaleoProcessLinkClp extends BaseModelImpl<KaleoProcessLink>
	implements KaleoProcessLink {
	public KaleoProcessLinkClp() {
	}

	public Class<?> getModelClass() {
		return KaleoProcessLink.class;
	}

	public String getModelClassName() {
		return KaleoProcessLink.class.getName();
	}

	public long getPrimaryKey() {
		return _kaleoProcessLinkId;
	}

	public void setPrimaryKey(long primaryKey) {
		setKaleoProcessLinkId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoProcessLinkId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	public long getKaleoProcessLinkId() {
		return _kaleoProcessLinkId;
	}

	public void setKaleoProcessLinkId(long kaleoProcessLinkId) {
		_kaleoProcessLinkId = kaleoProcessLinkId;
	}

	public long getKaleoProcessId() {
		return _kaleoProcessId;
	}

	public void setKaleoProcessId(long kaleoProcessId) {
		_kaleoProcessId = kaleoProcessId;
	}

	public String getWorkflowTaskName() {
		return _workflowTaskName;
	}

	public void setWorkflowTaskName(String workflowTaskName) {
		_workflowTaskName = workflowTaskName;
	}

	public long getDDMTemplateId() {
		return _DDMTemplateId;
	}

	public void setDDMTemplateId(long DDMTemplateId) {
		_DDMTemplateId = DDMTemplateId;
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess getKaleoProcess() {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getKaleoProcessLinkRemoteModel() {
		return _kaleoProcessLinkRemoteModel;
	}

	public void setKaleoProcessLinkRemoteModel(
		BaseModel<?> kaleoProcessLinkRemoteModel) {
		_kaleoProcessLinkRemoteModel = kaleoProcessLinkRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			KaleoProcessLinkLocalServiceUtil.addKaleoProcessLink(this);
		}
		else {
			KaleoProcessLinkLocalServiceUtil.updateKaleoProcessLink(this);
		}
	}

	@Override
	public KaleoProcessLink toEscapedModel() {
		return (KaleoProcessLink)ProxyUtil.newProxyInstance(KaleoProcessLink.class.getClassLoader(),
			new Class[] { KaleoProcessLink.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public KaleoProcessLink toUnescapedModel() {
		if (ProxyUtil.isProxyClass(getClass())) {
			InvocationHandler invocationHandler = ProxyUtil.getInvocationHandler(this);

			AutoEscapeBeanHandler autoEscapeBeanHandler = (AutoEscapeBeanHandler)invocationHandler;

			return (KaleoProcessLink)autoEscapeBeanHandler.getBean();
		}
		else {
			return (KaleoProcessLink)this;
		}
	}

	@Override
	public Object clone() {
		KaleoProcessLinkClp clone = new KaleoProcessLinkClp();

		clone.setKaleoProcessLinkId(getKaleoProcessLinkId());
		clone.setKaleoProcessId(getKaleoProcessId());
		clone.setWorkflowTaskName(getWorkflowTaskName());
		clone.setDDMTemplateId(getDDMTemplateId());

		return clone;
	}

	public int compareTo(KaleoProcessLink kaleoProcessLink) {
		long primaryKey = kaleoProcessLink.getPrimaryKey();

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
		if (obj == null) {
			return false;
		}

		KaleoProcessLinkClp kaleoProcessLink = null;

		try {
			kaleoProcessLink = (KaleoProcessLinkClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = kaleoProcessLink.getPrimaryKey();

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
		StringBundler sb = new StringBundler(9);

		sb.append("{kaleoProcessLinkId=");
		sb.append(getKaleoProcessLinkId());
		sb.append(", kaleoProcessId=");
		sb.append(getKaleoProcessId());
		sb.append(", workflowTaskName=");
		sb.append(getWorkflowTaskName());
		sb.append(", DDMTemplateId=");
		sb.append(getDDMTemplateId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoProcessLinkId</column-name><column-value><![CDATA[");
		sb.append(getKaleoProcessLinkId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoProcessId</column-name><column-value><![CDATA[");
		sb.append(getKaleoProcessId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowTaskName</column-name><column-value><![CDATA[");
		sb.append(getWorkflowTaskName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>DDMTemplateId</column-name><column-value><![CDATA[");
		sb.append(getDDMTemplateId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoProcessLinkId;
	private long _kaleoProcessId;
	private String _workflowTaskName;
	private long _DDMTemplateId;
	private BaseModel<?> _kaleoProcessLinkRemoteModel;
}