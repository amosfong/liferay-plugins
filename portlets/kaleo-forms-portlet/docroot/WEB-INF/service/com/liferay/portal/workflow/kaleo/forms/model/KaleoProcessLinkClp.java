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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.workflow.kaleo.forms.service.ClpSerializer;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class KaleoProcessLinkClp extends BaseModelImpl<KaleoProcessLink>
	implements KaleoProcessLink {
	public KaleoProcessLinkClp() {
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
	public long getPrimaryKey() {
		return _kaleoProcessLinkId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoProcessLinkId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoProcessLinkId;
	}

	@Override
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

	@Override
	public long getKaleoProcessLinkId() {
		return _kaleoProcessLinkId;
	}

	@Override
	public void setKaleoProcessLinkId(long kaleoProcessLinkId) {
		_kaleoProcessLinkId = kaleoProcessLinkId;

		if (_kaleoProcessLinkRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoProcessLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoProcessLinkId",
						long.class);

				method.invoke(_kaleoProcessLinkRemoteModel, kaleoProcessLinkId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoProcessId() {
		return _kaleoProcessId;
	}

	@Override
	public void setKaleoProcessId(long kaleoProcessId) {
		_kaleoProcessId = kaleoProcessId;

		if (_kaleoProcessLinkRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoProcessLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoProcessId", long.class);

				method.invoke(_kaleoProcessLinkRemoteModel, kaleoProcessId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getWorkflowTaskName() {
		return _workflowTaskName;
	}

	@Override
	public void setWorkflowTaskName(String workflowTaskName) {
		_workflowTaskName = workflowTaskName;

		if (_kaleoProcessLinkRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoProcessLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setWorkflowTaskName",
						String.class);

				method.invoke(_kaleoProcessLinkRemoteModel, workflowTaskName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getDDMTemplateId() {
		return _DDMTemplateId;
	}

	@Override
	public void setDDMTemplateId(long DDMTemplateId) {
		_DDMTemplateId = DDMTemplateId;

		if (_kaleoProcessLinkRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoProcessLinkRemoteModel.getClass();

				Method method = clazz.getMethod("setDDMTemplateId", long.class);

				method.invoke(_kaleoProcessLinkRemoteModel, DDMTemplateId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess getKaleoProcess() {
		try {
			String methodName = "getKaleoProcess";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess returnObj =
				(com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getKaleoProcessLinkRemoteModel() {
		return _kaleoProcessLinkRemoteModel;
	}

	public void setKaleoProcessLinkRemoteModel(
		BaseModel<?> kaleoProcessLinkRemoteModel) {
		_kaleoProcessLinkRemoteModel = kaleoProcessLinkRemoteModel;
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

		Class<?> remoteModelClass = _kaleoProcessLinkRemoteModel.getClass();

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

		Object returnValue = method.invoke(_kaleoProcessLinkRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
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
	public Object clone() {
		KaleoProcessLinkClp clone = new KaleoProcessLinkClp();

		clone.setKaleoProcessLinkId(getKaleoProcessLinkId());
		clone.setKaleoProcessId(getKaleoProcessId());
		clone.setWorkflowTaskName(getWorkflowTaskName());
		clone.setDDMTemplateId(getDDMTemplateId());

		return clone;
	}

	@Override
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
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoProcessLinkClp)) {
			return false;
		}

		KaleoProcessLinkClp kaleoProcessLink = (KaleoProcessLinkClp)obj;

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

	@Override
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