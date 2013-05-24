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
import com.liferay.portal.util.PortalUtil;

import com.liferay.saml.service.ClpSerializer;
import com.liferay.saml.service.SamlSpSessionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class SamlSpSessionClp extends BaseModelImpl<SamlSpSession>
	implements SamlSpSession {
	public SamlSpSessionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SamlSpSession.class;
	}

	@Override
	public String getModelClassName() {
		return SamlSpSession.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _samlSpSessionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSamlSpSessionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _samlSpSessionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlSpSessionId", getSamlSpSessionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("samlSpSessionKey", getSamlSpSessionKey());
		attributes.put("assertionXml", getAssertionXml());
		attributes.put("jSessionId", getJSessionId());
		attributes.put("nameIdFormat", getNameIdFormat());
		attributes.put("nameIdValue", getNameIdValue());
		attributes.put("sessionIndex", getSessionIndex());
		attributes.put("terminated", getTerminated());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlSpSessionId = (Long)attributes.get("samlSpSessionId");

		if (samlSpSessionId != null) {
			setSamlSpSessionId(samlSpSessionId);
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

		String samlSpSessionKey = (String)attributes.get("samlSpSessionKey");

		if (samlSpSessionKey != null) {
			setSamlSpSessionKey(samlSpSessionKey);
		}

		String assertionXml = (String)attributes.get("assertionXml");

		if (assertionXml != null) {
			setAssertionXml(assertionXml);
		}

		String jSessionId = (String)attributes.get("jSessionId");

		if (jSessionId != null) {
			setJSessionId(jSessionId);
		}

		String nameIdFormat = (String)attributes.get("nameIdFormat");

		if (nameIdFormat != null) {
			setNameIdFormat(nameIdFormat);
		}

		String nameIdValue = (String)attributes.get("nameIdValue");

		if (nameIdValue != null) {
			setNameIdValue(nameIdValue);
		}

		String sessionIndex = (String)attributes.get("sessionIndex");

		if (sessionIndex != null) {
			setSessionIndex(sessionIndex);
		}

		Boolean terminated = (Boolean)attributes.get("terminated");

		if (terminated != null) {
			setTerminated(terminated);
		}
	}

	@Override
	public long getSamlSpSessionId() {
		return _samlSpSessionId;
	}

	@Override
	public void setSamlSpSessionId(long samlSpSessionId) {
		_samlSpSessionId = samlSpSessionId;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlSpSessionId", long.class);

				method.invoke(_samlSpSessionRemoteModel, samlSpSessionId);
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

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_samlSpSessionRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_samlSpSessionRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	@Override
	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_samlSpSessionRemoteModel, userName);
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

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_samlSpSessionRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_samlSpSessionRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSamlSpSessionKey() {
		return _samlSpSessionKey;
	}

	@Override
	public void setSamlSpSessionKey(String samlSpSessionKey) {
		_samlSpSessionKey = samlSpSessionKey;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlSpSessionKey",
						String.class);

				method.invoke(_samlSpSessionRemoteModel, samlSpSessionKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAssertionXml() {
		return _assertionXml;
	}

	@Override
	public void setAssertionXml(String assertionXml) {
		_assertionXml = assertionXml;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setAssertionXml", String.class);

				method.invoke(_samlSpSessionRemoteModel, assertionXml);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getJSessionId() {
		return _jSessionId;
	}

	@Override
	public void setJSessionId(String jSessionId) {
		_jSessionId = jSessionId;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setJSessionId", String.class);

				method.invoke(_samlSpSessionRemoteModel, jSessionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNameIdFormat() {
		return _nameIdFormat;
	}

	@Override
	public void setNameIdFormat(String nameIdFormat) {
		_nameIdFormat = nameIdFormat;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setNameIdFormat", String.class);

				method.invoke(_samlSpSessionRemoteModel, nameIdFormat);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNameIdValue() {
		return _nameIdValue;
	}

	@Override
	public void setNameIdValue(String nameIdValue) {
		_nameIdValue = nameIdValue;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setNameIdValue", String.class);

				method.invoke(_samlSpSessionRemoteModel, nameIdValue);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSessionIndex() {
		return _sessionIndex;
	}

	@Override
	public void setSessionIndex(String sessionIndex) {
		_sessionIndex = sessionIndex;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setSessionIndex", String.class);

				method.invoke(_samlSpSessionRemoteModel, sessionIndex);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getTerminated() {
		return _terminated;
	}

	@Override
	public boolean isTerminated() {
		return _terminated;
	}

	@Override
	public void setTerminated(boolean terminated) {
		_terminated = terminated;

		if (_samlSpSessionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpSessionRemoteModel.getClass();

				Method method = clazz.getMethod("setTerminated", boolean.class);

				method.invoke(_samlSpSessionRemoteModel, terminated);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSamlSpSessionRemoteModel() {
		return _samlSpSessionRemoteModel;
	}

	public void setSamlSpSessionRemoteModel(
		BaseModel<?> samlSpSessionRemoteModel) {
		_samlSpSessionRemoteModel = samlSpSessionRemoteModel;
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

		Class<?> remoteModelClass = _samlSpSessionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_samlSpSessionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SamlSpSessionLocalServiceUtil.addSamlSpSession(this);
		}
		else {
			SamlSpSessionLocalServiceUtil.updateSamlSpSession(this);
		}
	}

	@Override
	public SamlSpSession toEscapedModel() {
		return (SamlSpSession)ProxyUtil.newProxyInstance(SamlSpSession.class.getClassLoader(),
			new Class[] { SamlSpSession.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SamlSpSessionClp clone = new SamlSpSessionClp();

		clone.setSamlSpSessionId(getSamlSpSessionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setSamlSpSessionKey(getSamlSpSessionKey());
		clone.setAssertionXml(getAssertionXml());
		clone.setJSessionId(getJSessionId());
		clone.setNameIdFormat(getNameIdFormat());
		clone.setNameIdValue(getNameIdValue());
		clone.setSessionIndex(getSessionIndex());
		clone.setTerminated(getTerminated());

		return clone;
	}

	@Override
	public int compareTo(SamlSpSession samlSpSession) {
		long primaryKey = samlSpSession.getPrimaryKey();

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

		if (!(obj instanceof SamlSpSessionClp)) {
			return false;
		}

		SamlSpSessionClp samlSpSession = (SamlSpSessionClp)obj;

		long primaryKey = samlSpSession.getPrimaryKey();

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
		StringBundler sb = new StringBundler(27);

		sb.append("{samlSpSessionId=");
		sb.append(getSamlSpSessionId());
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
		sb.append(", samlSpSessionKey=");
		sb.append(getSamlSpSessionKey());
		sb.append(", assertionXml=");
		sb.append(getAssertionXml());
		sb.append(", jSessionId=");
		sb.append(getJSessionId());
		sb.append(", nameIdFormat=");
		sb.append(getNameIdFormat());
		sb.append(", nameIdValue=");
		sb.append(getNameIdValue());
		sb.append(", sessionIndex=");
		sb.append(getSessionIndex());
		sb.append(", terminated=");
		sb.append(getTerminated());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

		sb.append("<model><model-name>");
		sb.append("com.liferay.saml.model.SamlSpSession");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>samlSpSessionId</column-name><column-value><![CDATA[");
		sb.append(getSamlSpSessionId());
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
			"<column><column-name>samlSpSessionKey</column-name><column-value><![CDATA[");
		sb.append(getSamlSpSessionKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assertionXml</column-name><column-value><![CDATA[");
		sb.append(getAssertionXml());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jSessionId</column-name><column-value><![CDATA[");
		sb.append(getJSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nameIdFormat</column-name><column-value><![CDATA[");
		sb.append(getNameIdFormat());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nameIdValue</column-name><column-value><![CDATA[");
		sb.append(getNameIdValue());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sessionIndex</column-name><column-value><![CDATA[");
		sb.append(getSessionIndex());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>terminated</column-name><column-value><![CDATA[");
		sb.append(getTerminated());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _samlSpSessionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _samlSpSessionKey;
	private String _assertionXml;
	private String _jSessionId;
	private String _nameIdFormat;
	private String _nameIdValue;
	private String _sessionIndex;
	private boolean _terminated;
	private BaseModel<?> _samlSpSessionRemoteModel;
}