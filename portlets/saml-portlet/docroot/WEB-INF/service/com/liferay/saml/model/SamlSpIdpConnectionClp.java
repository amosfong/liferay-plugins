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
import com.liferay.saml.service.SamlSpIdpConnectionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class SamlSpIdpConnectionClp extends BaseModelImpl<SamlSpIdpConnection>
	implements SamlSpIdpConnection {
	public SamlSpIdpConnectionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SamlSpIdpConnection.class;
	}

	@Override
	public String getModelClassName() {
		return SamlSpIdpConnection.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _samlSpIdpConnectionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSamlSpIdpConnectionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _samlSpIdpConnectionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlSpIdpConnectionId", getSamlSpIdpConnectionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("samlIdpEntityId", getSamlIdpEntityId());
		attributes.put("assertionSignatureRequired",
			getAssertionSignatureRequired());
		attributes.put("clockSkew", getClockSkew());
		attributes.put("enabled", getEnabled());
		attributes.put("ldapImportEnabled", getLdapImportEnabled());
		attributes.put("metadataUrl", getMetadataUrl());
		attributes.put("metadataXml", getMetadataXml());
		attributes.put("metadataUpdatedDate", getMetadataUpdatedDate());
		attributes.put("name", getName());
		attributes.put("nameIdFormat", getNameIdFormat());
		attributes.put("signAuthnRequest", getSignAuthnRequest());
		attributes.put("userAttributeMappings", getUserAttributeMappings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlSpIdpConnectionId = (Long)attributes.get(
				"samlSpIdpConnectionId");

		if (samlSpIdpConnectionId != null) {
			setSamlSpIdpConnectionId(samlSpIdpConnectionId);
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

		String samlIdpEntityId = (String)attributes.get("samlIdpEntityId");

		if (samlIdpEntityId != null) {
			setSamlIdpEntityId(samlIdpEntityId);
		}

		Boolean assertionSignatureRequired = (Boolean)attributes.get(
				"assertionSignatureRequired");

		if (assertionSignatureRequired != null) {
			setAssertionSignatureRequired(assertionSignatureRequired);
		}

		Long clockSkew = (Long)attributes.get("clockSkew");

		if (clockSkew != null) {
			setClockSkew(clockSkew);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		Boolean ldapImportEnabled = (Boolean)attributes.get("ldapImportEnabled");

		if (ldapImportEnabled != null) {
			setLdapImportEnabled(ldapImportEnabled);
		}

		String metadataUrl = (String)attributes.get("metadataUrl");

		if (metadataUrl != null) {
			setMetadataUrl(metadataUrl);
		}

		String metadataXml = (String)attributes.get("metadataXml");

		if (metadataXml != null) {
			setMetadataXml(metadataXml);
		}

		Date metadataUpdatedDate = (Date)attributes.get("metadataUpdatedDate");

		if (metadataUpdatedDate != null) {
			setMetadataUpdatedDate(metadataUpdatedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String nameIdFormat = (String)attributes.get("nameIdFormat");

		if (nameIdFormat != null) {
			setNameIdFormat(nameIdFormat);
		}

		Boolean signAuthnRequest = (Boolean)attributes.get("signAuthnRequest");

		if (signAuthnRequest != null) {
			setSignAuthnRequest(signAuthnRequest);
		}

		String userAttributeMappings = (String)attributes.get(
				"userAttributeMappings");

		if (userAttributeMappings != null) {
			setUserAttributeMappings(userAttributeMappings);
		}
	}

	@Override
	public long getSamlSpIdpConnectionId() {
		return _samlSpIdpConnectionId;
	}

	@Override
	public void setSamlSpIdpConnectionId(long samlSpIdpConnectionId) {
		_samlSpIdpConnectionId = samlSpIdpConnectionId;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlSpIdpConnectionId",
						long.class);

				method.invoke(_samlSpIdpConnectionRemoteModel,
					samlSpIdpConnectionId);
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

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, companyId);
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

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, userId);
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

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, userName);
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

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, createDate);
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

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, modifiedDate);
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

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlIdpEntityId",
						String.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, samlIdpEntityId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAssertionSignatureRequired() {
		return _assertionSignatureRequired;
	}

	@Override
	public boolean isAssertionSignatureRequired() {
		return _assertionSignatureRequired;
	}

	@Override
	public void setAssertionSignatureRequired(
		boolean assertionSignatureRequired) {
		_assertionSignatureRequired = assertionSignatureRequired;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setAssertionSignatureRequired",
						boolean.class);

				method.invoke(_samlSpIdpConnectionRemoteModel,
					assertionSignatureRequired);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getClockSkew() {
		return _clockSkew;
	}

	@Override
	public void setClockSkew(long clockSkew) {
		_clockSkew = clockSkew;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setClockSkew", long.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, clockSkew);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getEnabled() {
		return _enabled;
	}

	@Override
	public boolean isEnabled() {
		return _enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		_enabled = enabled;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setEnabled", boolean.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, enabled);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getLdapImportEnabled() {
		return _ldapImportEnabled;
	}

	@Override
	public boolean isLdapImportEnabled() {
		return _ldapImportEnabled;
	}

	@Override
	public void setLdapImportEnabled(boolean ldapImportEnabled) {
		_ldapImportEnabled = ldapImportEnabled;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setLdapImportEnabled",
						boolean.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, ldapImportEnabled);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMetadataUrl() {
		return _metadataUrl;
	}

	@Override
	public void setMetadataUrl(String metadataUrl) {
		_metadataUrl = metadataUrl;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setMetadataUrl", String.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, metadataUrl);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMetadataXml() {
		return _metadataXml;
	}

	@Override
	public void setMetadataXml(String metadataXml) {
		_metadataXml = metadataXml;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setMetadataXml", String.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, metadataXml);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getMetadataUpdatedDate() {
		return _metadataUpdatedDate;
	}

	@Override
	public void setMetadataUpdatedDate(Date metadataUpdatedDate) {
		_metadataUpdatedDate = metadataUpdatedDate;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setMetadataUpdatedDate",
						Date.class);

				method.invoke(_samlSpIdpConnectionRemoteModel,
					metadataUpdatedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, name);
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

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setNameIdFormat", String.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, nameIdFormat);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getSignAuthnRequest() {
		return _signAuthnRequest;
	}

	@Override
	public boolean isSignAuthnRequest() {
		return _signAuthnRequest;
	}

	@Override
	public void setSignAuthnRequest(boolean signAuthnRequest) {
		_signAuthnRequest = signAuthnRequest;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSignAuthnRequest",
						boolean.class);

				method.invoke(_samlSpIdpConnectionRemoteModel, signAuthnRequest);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserAttributeMappings() {
		return _userAttributeMappings;
	}

	@Override
	public void setUserAttributeMappings(String userAttributeMappings) {
		_userAttributeMappings = userAttributeMappings;

		if (_samlSpIdpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlSpIdpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserAttributeMappings",
						String.class);

				method.invoke(_samlSpIdpConnectionRemoteModel,
					userAttributeMappings);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSamlSpIdpConnectionRemoteModel() {
		return _samlSpIdpConnectionRemoteModel;
	}

	public void setSamlSpIdpConnectionRemoteModel(
		BaseModel<?> samlSpIdpConnectionRemoteModel) {
		_samlSpIdpConnectionRemoteModel = samlSpIdpConnectionRemoteModel;
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

		Class<?> remoteModelClass = _samlSpIdpConnectionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_samlSpIdpConnectionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SamlSpIdpConnectionLocalServiceUtil.addSamlSpIdpConnection(this);
		}
		else {
			SamlSpIdpConnectionLocalServiceUtil.updateSamlSpIdpConnection(this);
		}
	}

	@Override
	public SamlSpIdpConnection toEscapedModel() {
		return (SamlSpIdpConnection)ProxyUtil.newProxyInstance(SamlSpIdpConnection.class.getClassLoader(),
			new Class[] { SamlSpIdpConnection.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SamlSpIdpConnectionClp clone = new SamlSpIdpConnectionClp();

		clone.setSamlSpIdpConnectionId(getSamlSpIdpConnectionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setSamlIdpEntityId(getSamlIdpEntityId());
		clone.setAssertionSignatureRequired(getAssertionSignatureRequired());
		clone.setClockSkew(getClockSkew());
		clone.setEnabled(getEnabled());
		clone.setLdapImportEnabled(getLdapImportEnabled());
		clone.setMetadataUrl(getMetadataUrl());
		clone.setMetadataXml(getMetadataXml());
		clone.setMetadataUpdatedDate(getMetadataUpdatedDate());
		clone.setName(getName());
		clone.setNameIdFormat(getNameIdFormat());
		clone.setSignAuthnRequest(getSignAuthnRequest());
		clone.setUserAttributeMappings(getUserAttributeMappings());

		return clone;
	}

	@Override
	public int compareTo(SamlSpIdpConnection samlSpIdpConnection) {
		long primaryKey = samlSpIdpConnection.getPrimaryKey();

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

		if (!(obj instanceof SamlSpIdpConnectionClp)) {
			return false;
		}

		SamlSpIdpConnectionClp samlSpIdpConnection = (SamlSpIdpConnectionClp)obj;

		long primaryKey = samlSpIdpConnection.getPrimaryKey();

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
		StringBundler sb = new StringBundler(37);

		sb.append("{samlSpIdpConnectionId=");
		sb.append(getSamlSpIdpConnectionId());
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
		sb.append(", samlIdpEntityId=");
		sb.append(getSamlIdpEntityId());
		sb.append(", assertionSignatureRequired=");
		sb.append(getAssertionSignatureRequired());
		sb.append(", clockSkew=");
		sb.append(getClockSkew());
		sb.append(", enabled=");
		sb.append(getEnabled());
		sb.append(", ldapImportEnabled=");
		sb.append(getLdapImportEnabled());
		sb.append(", metadataUrl=");
		sb.append(getMetadataUrl());
		sb.append(", metadataXml=");
		sb.append(getMetadataXml());
		sb.append(", metadataUpdatedDate=");
		sb.append(getMetadataUpdatedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", nameIdFormat=");
		sb.append(getNameIdFormat());
		sb.append(", signAuthnRequest=");
		sb.append(getSignAuthnRequest());
		sb.append(", userAttributeMappings=");
		sb.append(getUserAttributeMappings());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.saml.model.SamlSpIdpConnection");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>samlSpIdpConnectionId</column-name><column-value><![CDATA[");
		sb.append(getSamlSpIdpConnectionId());
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
			"<column><column-name>samlIdpEntityId</column-name><column-value><![CDATA[");
		sb.append(getSamlIdpEntityId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assertionSignatureRequired</column-name><column-value><![CDATA[");
		sb.append(getAssertionSignatureRequired());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>clockSkew</column-name><column-value><![CDATA[");
		sb.append(getClockSkew());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enabled</column-name><column-value><![CDATA[");
		sb.append(getEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ldapImportEnabled</column-name><column-value><![CDATA[");
		sb.append(getLdapImportEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>metadataUrl</column-name><column-value><![CDATA[");
		sb.append(getMetadataUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>metadataXml</column-name><column-value><![CDATA[");
		sb.append(getMetadataXml());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>metadataUpdatedDate</column-name><column-value><![CDATA[");
		sb.append(getMetadataUpdatedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nameIdFormat</column-name><column-value><![CDATA[");
		sb.append(getNameIdFormat());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signAuthnRequest</column-name><column-value><![CDATA[");
		sb.append(getSignAuthnRequest());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userAttributeMappings</column-name><column-value><![CDATA[");
		sb.append(getUserAttributeMappings());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _samlSpIdpConnectionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _samlIdpEntityId;
	private boolean _assertionSignatureRequired;
	private long _clockSkew;
	private boolean _enabled;
	private boolean _ldapImportEnabled;
	private String _metadataUrl;
	private String _metadataXml;
	private Date _metadataUpdatedDate;
	private String _name;
	private String _nameIdFormat;
	private boolean _signAuthnRequest;
	private String _userAttributeMappings;
	private BaseModel<?> _samlSpIdpConnectionRemoteModel;
}