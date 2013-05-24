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
import com.liferay.saml.service.SamlSpMessageLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class SamlSpMessageClp extends BaseModelImpl<SamlSpMessage>
	implements SamlSpMessage {
	public SamlSpMessageClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SamlSpMessage.class;
	}

	@Override
	public String getModelClassName() {
		return SamlSpMessage.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _samlSpMessageId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSamlSpMessageId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _samlSpMessageId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlSpMessageId", getSamlSpMessageId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("samlIdpEntityId", getSamlIdpEntityId());
		attributes.put("samlIdpResponseKey", getSamlIdpResponseKey());
		attributes.put("expirationDate", getExpirationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlSpMessageId = (Long)attributes.get("samlSpMessageId");

		if (samlSpMessageId != null) {
			setSamlSpMessageId(samlSpMessageId);
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

		String samlIdpResponseKey = (String)attributes.get("samlIdpResponseKey");

		if (samlIdpResponseKey != null) {
			setSamlIdpResponseKey(samlIdpResponseKey);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}
	}

	@Override
	public long getSamlSpMessageId() {
		return _samlSpMessageId;
	}

	@Override
	public void setSamlSpMessageId(long samlSpMessageId) {
		_samlSpMessageId = samlSpMessageId;

		if (_samlSpMessageRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlSpMessageId", long.class);

				method.invoke(_samlSpMessageRemoteModel, samlSpMessageId);
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

		if (_samlSpMessageRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_samlSpMessageRemoteModel, companyId);
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

		if (_samlSpMessageRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_samlSpMessageRemoteModel, createDate);
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

		if (_samlSpMessageRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlIdpEntityId",
						String.class);

				method.invoke(_samlSpMessageRemoteModel, samlIdpEntityId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSamlIdpResponseKey() {
		return _samlIdpResponseKey;
	}

	@Override
	public void setSamlIdpResponseKey(String samlIdpResponseKey) {
		_samlIdpResponseKey = samlIdpResponseKey;

		if (_samlSpMessageRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlIdpResponseKey",
						String.class);

				method.invoke(_samlSpMessageRemoteModel, samlIdpResponseKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getExpirationDate() {
		return _expirationDate;
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;

		if (_samlSpMessageRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpMessageRemoteModel.getClass();

				Method method = clazz.getMethod("setExpirationDate", Date.class);

				method.invoke(_samlSpMessageRemoteModel, expirationDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean isExpired() {
		try {
			String methodName = "isExpired";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getSamlSpMessageRemoteModel() {
		return _samlSpMessageRemoteModel;
	}

	public void setSamlSpMessageRemoteModel(
		BaseModel<?> samlSpMessageRemoteModel) {
		_samlSpMessageRemoteModel = samlSpMessageRemoteModel;
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

		Class<?> remoteModelClass = _samlSpMessageRemoteModel.getClass();

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

		Object returnValue = method.invoke(_samlSpMessageRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SamlSpMessageLocalServiceUtil.addSamlSpMessage(this);
		}
		else {
			SamlSpMessageLocalServiceUtil.updateSamlSpMessage(this);
		}
	}

	@Override
	public SamlSpMessage toEscapedModel() {
		return (SamlSpMessage)ProxyUtil.newProxyInstance(SamlSpMessage.class.getClassLoader(),
			new Class[] { SamlSpMessage.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SamlSpMessageClp clone = new SamlSpMessageClp();

		clone.setSamlSpMessageId(getSamlSpMessageId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setSamlIdpEntityId(getSamlIdpEntityId());
		clone.setSamlIdpResponseKey(getSamlIdpResponseKey());
		clone.setExpirationDate(getExpirationDate());

		return clone;
	}

	@Override
	public int compareTo(SamlSpMessage samlSpMessage) {
		long primaryKey = samlSpMessage.getPrimaryKey();

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

		if (!(obj instanceof SamlSpMessageClp)) {
			return false;
		}

		SamlSpMessageClp samlSpMessage = (SamlSpMessageClp)obj;

		long primaryKey = samlSpMessage.getPrimaryKey();

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
		StringBundler sb = new StringBundler(13);

		sb.append("{samlSpMessageId=");
		sb.append(getSamlSpMessageId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", samlIdpEntityId=");
		sb.append(getSamlIdpEntityId());
		sb.append(", samlIdpResponseKey=");
		sb.append(getSamlIdpResponseKey());
		sb.append(", expirationDate=");
		sb.append(getExpirationDate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(22);

		sb.append("<model><model-name>");
		sb.append("com.liferay.saml.model.SamlSpMessage");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>samlSpMessageId</column-name><column-value><![CDATA[");
		sb.append(getSamlSpMessageId());
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
			"<column><column-name>samlIdpResponseKey</column-name><column-value><![CDATA[");
		sb.append(getSamlIdpResponseKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>expirationDate</column-name><column-value><![CDATA[");
		sb.append(getExpirationDate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _samlSpMessageId;
	private long _companyId;
	private Date _createDate;
	private String _samlIdpEntityId;
	private String _samlIdpResponseKey;
	private Date _expirationDate;
	private BaseModel<?> _samlSpMessageRemoteModel;
}