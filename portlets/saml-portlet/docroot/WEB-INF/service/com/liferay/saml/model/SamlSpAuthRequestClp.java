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

package com.liferay.saml.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.saml.service.ClpSerializer;
import com.liferay.saml.service.SamlSpAuthRequestLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class SamlSpAuthRequestClp extends BaseModelImpl<SamlSpAuthRequest>
	implements SamlSpAuthRequest {
	public SamlSpAuthRequestClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SamlSpAuthRequest.class;
	}

	@Override
	public String getModelClassName() {
		return SamlSpAuthRequest.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _samlSpAuthnRequestId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSamlSpAuthnRequestId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _samlSpAuthnRequestId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlSpAuthnRequestId", getSamlSpAuthnRequestId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("samlIdpEntityId", getSamlIdpEntityId());
		attributes.put("samlSpAuthRequestKey", getSamlSpAuthRequestKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlSpAuthnRequestId = (Long)attributes.get("samlSpAuthnRequestId");

		if (samlSpAuthnRequestId != null) {
			setSamlSpAuthnRequestId(samlSpAuthnRequestId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		String samlIdpEntityId = (String)attributes.get("samlIdpEntityId");

		if (samlIdpEntityId != null) {
			setSamlIdpEntityId(samlIdpEntityId);
		}

		String samlSpAuthRequestKey = (String)attributes.get(
				"samlSpAuthRequestKey");

		if (samlSpAuthRequestKey != null) {
			setSamlSpAuthRequestKey(samlSpAuthRequestKey);
		}
	}

	@Override
	public long getSamlSpAuthnRequestId() {
		return _samlSpAuthnRequestId;
	}

	@Override
	public void setSamlSpAuthnRequestId(long samlSpAuthnRequestId) {
		_samlSpAuthnRequestId = samlSpAuthnRequestId;

		if (_samlSpAuthRequestRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpAuthRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlSpAuthnRequestId",
						long.class);

				method.invoke(_samlSpAuthRequestRemoteModel,
					samlSpAuthnRequestId);
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

		if (_samlSpAuthRequestRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpAuthRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_samlSpAuthRequestRemoteModel, companyId);
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

		if (_samlSpAuthRequestRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpAuthRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_samlSpAuthRequestRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSamlIdpEntityId() {
		return _samlIdpEntityId;
	}

	@Override
	public void setSamlIdpEntityId(String samlIdpEntityId) {
		_samlIdpEntityId = samlIdpEntityId;

		if (_samlSpAuthRequestRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpAuthRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlIdpEntityId",
						String.class);

				method.invoke(_samlSpAuthRequestRemoteModel, samlIdpEntityId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSamlSpAuthRequestKey() {
		return _samlSpAuthRequestKey;
	}

	@Override
	public void setSamlSpAuthRequestKey(String samlSpAuthRequestKey) {
		_samlSpAuthRequestKey = samlSpAuthRequestKey;

		if (_samlSpAuthRequestRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpAuthRequestRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlSpAuthRequestKey",
						String.class);

				method.invoke(_samlSpAuthRequestRemoteModel,
					samlSpAuthRequestKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSamlSpAuthRequestRemoteModel() {
		return _samlSpAuthRequestRemoteModel;
	}

	public void setSamlSpAuthRequestRemoteModel(
		BaseModel<?> samlSpAuthRequestRemoteModel) {
		_samlSpAuthRequestRemoteModel = samlSpAuthRequestRemoteModel;
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

		Class<?> remoteModelClass = _samlSpAuthRequestRemoteModel.getClass();

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

		Object returnValue = method.invoke(_samlSpAuthRequestRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SamlSpAuthRequestLocalServiceUtil.addSamlSpAuthRequest(this);
		}
		else {
			SamlSpAuthRequestLocalServiceUtil.updateSamlSpAuthRequest(this);
		}
	}

	@Override
	public SamlSpAuthRequest toEscapedModel() {
		return (SamlSpAuthRequest)ProxyUtil.newProxyInstance(SamlSpAuthRequest.class.getClassLoader(),
			new Class[] { SamlSpAuthRequest.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SamlSpAuthRequestClp clone = new SamlSpAuthRequestClp();

		clone.setSamlSpAuthnRequestId(getSamlSpAuthnRequestId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setSamlIdpEntityId(getSamlIdpEntityId());
		clone.setSamlSpAuthRequestKey(getSamlSpAuthRequestKey());

		return clone;
	}

	@Override
	public int compareTo(SamlSpAuthRequest samlSpAuthRequest) {
		long primaryKey = samlSpAuthRequest.getPrimaryKey();

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

		if (!(obj instanceof SamlSpAuthRequestClp)) {
			return false;
		}

		SamlSpAuthRequestClp samlSpAuthRequest = (SamlSpAuthRequestClp)obj;

		long primaryKey = samlSpAuthRequest.getPrimaryKey();

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
		StringBundler sb = new StringBundler(11);

		sb.append("{samlSpAuthnRequestId=");
		sb.append(getSamlSpAuthnRequestId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", samlIdpEntityId=");
		sb.append(getSamlIdpEntityId());
		sb.append(", samlSpAuthRequestKey=");
		sb.append(getSamlSpAuthRequestKey());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.saml.model.SamlSpAuthRequest");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>samlSpAuthnRequestId</column-name><column-value><![CDATA[");
		sb.append(getSamlSpAuthnRequestId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>samlIdpEntityId</column-name><column-value><![CDATA[");
		sb.append(getSamlIdpEntityId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>samlSpAuthRequestKey</column-name><column-value><![CDATA[");
		sb.append(getSamlSpAuthRequestKey());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _samlSpAuthnRequestId;
	private long _companyId;
	private Date _createDate;
	private String _samlIdpEntityId;
	private String _samlSpAuthRequestKey;
	private BaseModel<?> _samlSpAuthRequestRemoteModel;
}