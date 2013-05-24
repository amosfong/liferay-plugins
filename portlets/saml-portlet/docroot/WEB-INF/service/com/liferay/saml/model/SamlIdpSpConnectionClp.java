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
import com.liferay.saml.service.SamlIdpSpConnectionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class SamlIdpSpConnectionClp extends BaseModelImpl<SamlIdpSpConnection>
	implements SamlIdpSpConnection {
	public SamlIdpSpConnectionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SamlIdpSpConnection.class;
	}

	@Override
	public String getModelClassName() {
		return SamlIdpSpConnection.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _samlIdpSpConnectionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSamlIdpSpConnectionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _samlIdpSpConnectionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlIdpSpConnectionId", getSamlIdpSpConnectionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("samlSpEntityId", getSamlSpEntityId());
		attributes.put("assertionLifetime", getAssertionLifetime());
		attributes.put("attributeNames", getAttributeNames());
		attributes.put("attributesEnabled", getAttributesEnabled());
		attributes.put("attributesNamespaceEnabled",
			getAttributesNamespaceEnabled());
		attributes.put("enabled", getEnabled());
		attributes.put("metadataUrl", getMetadataUrl());
		attributes.put("metadataXml", getMetadataXml());
		attributes.put("metadataUpdatedDate", getMetadataUpdatedDate());
		attributes.put("name", getName());
		attributes.put("nameIdAttribute", getNameIdAttribute());
		attributes.put("nameIdFormat", getNameIdFormat());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlIdpSpConnectionId = (Long)attributes.get(
				"samlIdpSpConnectionId");

		if (samlIdpSpConnectionId != null) {
			setSamlIdpSpConnectionId(samlIdpSpConnectionId);
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

		String samlSpEntityId = (String)attributes.get("samlSpEntityId");

		if (samlSpEntityId != null) {
			setSamlSpEntityId(samlSpEntityId);
		}

		Integer assertionLifetime = (Integer)attributes.get("assertionLifetime");

		if (assertionLifetime != null) {
			setAssertionLifetime(assertionLifetime);
		}

		String attributeNames = (String)attributes.get("attributeNames");

		if (attributeNames != null) {
			setAttributeNames(attributeNames);
		}

		Boolean attributesEnabled = (Boolean)attributes.get("attributesEnabled");

		if (attributesEnabled != null) {
			setAttributesEnabled(attributesEnabled);
		}

		Boolean attributesNamespaceEnabled = (Boolean)attributes.get(
				"attributesNamespaceEnabled");

		if (attributesNamespaceEnabled != null) {
			setAttributesNamespaceEnabled(attributesNamespaceEnabled);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
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

		String nameIdAttribute = (String)attributes.get("nameIdAttribute");

		if (nameIdAttribute != null) {
			setNameIdAttribute(nameIdAttribute);
		}

		String nameIdFormat = (String)attributes.get("nameIdFormat");

		if (nameIdFormat != null) {
			setNameIdFormat(nameIdFormat);
		}
	}

	@Override
	public long getSamlIdpSpConnectionId() {
		return _samlIdpSpConnectionId;
	}

	@Override
	public void setSamlIdpSpConnectionId(long samlIdpSpConnectionId) {
		_samlIdpSpConnectionId = samlIdpSpConnectionId;

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlIdpSpConnectionId",
						long.class);

				method.invoke(_samlIdpSpConnectionRemoteModel,
					samlIdpSpConnectionId);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, companyId);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, userId);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, userName);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, createDate);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSamlSpEntityId() {
		return _samlSpEntityId;
	}

	@Override
	public void setSamlSpEntityId(String samlSpEntityId) {
		_samlSpEntityId = samlSpEntityId;

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setSamlSpEntityId",
						String.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, samlSpEntityId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getAssertionLifetime() {
		return _assertionLifetime;
	}

	@Override
	public void setAssertionLifetime(int assertionLifetime) {
		_assertionLifetime = assertionLifetime;

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setAssertionLifetime",
						int.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, assertionLifetime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAttributeNames() {
		return _attributeNames;
	}

	@Override
	public void setAttributeNames(String attributeNames) {
		_attributeNames = attributeNames;

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setAttributeNames",
						String.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, attributeNames);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAttributesEnabled() {
		return _attributesEnabled;
	}

	@Override
	public boolean isAttributesEnabled() {
		return _attributesEnabled;
	}

	@Override
	public void setAttributesEnabled(boolean attributesEnabled) {
		_attributesEnabled = attributesEnabled;

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setAttributesEnabled",
						boolean.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, attributesEnabled);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getAttributesNamespaceEnabled() {
		return _attributesNamespaceEnabled;
	}

	@Override
	public boolean isAttributesNamespaceEnabled() {
		return _attributesNamespaceEnabled;
	}

	@Override
	public void setAttributesNamespaceEnabled(
		boolean attributesNamespaceEnabled) {
		_attributesNamespaceEnabled = attributesNamespaceEnabled;

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setAttributesNamespaceEnabled",
						boolean.class);

				method.invoke(_samlIdpSpConnectionRemoteModel,
					attributesNamespaceEnabled);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setEnabled", boolean.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, enabled);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setMetadataUrl", String.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, metadataUrl);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setMetadataXml", String.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, metadataXml);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setMetadataUpdatedDate",
						Date.class);

				method.invoke(_samlIdpSpConnectionRemoteModel,
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getNameIdAttribute() {
		return _nameIdAttribute;
	}

	@Override
	public void setNameIdAttribute(String nameIdAttribute) {
		_nameIdAttribute = nameIdAttribute;

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setNameIdAttribute",
						String.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, nameIdAttribute);
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

		if (_samlIdpSpConnectionRemoteModel != null) {
			try {
				Class<?> clazz = _samlIdpSpConnectionRemoteModel.getClass();

				Method method = clazz.getMethod("setNameIdFormat", String.class);

				method.invoke(_samlIdpSpConnectionRemoteModel, nameIdFormat);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSamlIdpSpConnectionRemoteModel() {
		return _samlIdpSpConnectionRemoteModel;
	}

	public void setSamlIdpSpConnectionRemoteModel(
		BaseModel<?> samlIdpSpConnectionRemoteModel) {
		_samlIdpSpConnectionRemoteModel = samlIdpSpConnectionRemoteModel;
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

		Class<?> remoteModelClass = _samlIdpSpConnectionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_samlIdpSpConnectionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SamlIdpSpConnectionLocalServiceUtil.addSamlIdpSpConnection(this);
		}
		else {
			SamlIdpSpConnectionLocalServiceUtil.updateSamlIdpSpConnection(this);
		}
	}

	@Override
	public SamlIdpSpConnection toEscapedModel() {
		return (SamlIdpSpConnection)ProxyUtil.newProxyInstance(SamlIdpSpConnection.class.getClassLoader(),
			new Class[] { SamlIdpSpConnection.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SamlIdpSpConnectionClp clone = new SamlIdpSpConnectionClp();

		clone.setSamlIdpSpConnectionId(getSamlIdpSpConnectionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setSamlSpEntityId(getSamlSpEntityId());
		clone.setAssertionLifetime(getAssertionLifetime());
		clone.setAttributeNames(getAttributeNames());
		clone.setAttributesEnabled(getAttributesEnabled());
		clone.setAttributesNamespaceEnabled(getAttributesNamespaceEnabled());
		clone.setEnabled(getEnabled());
		clone.setMetadataUrl(getMetadataUrl());
		clone.setMetadataXml(getMetadataXml());
		clone.setMetadataUpdatedDate(getMetadataUpdatedDate());
		clone.setName(getName());
		clone.setNameIdAttribute(getNameIdAttribute());
		clone.setNameIdFormat(getNameIdFormat());

		return clone;
	}

	@Override
	public int compareTo(SamlIdpSpConnection samlIdpSpConnection) {
		long primaryKey = samlIdpSpConnection.getPrimaryKey();

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

		if (!(obj instanceof SamlIdpSpConnectionClp)) {
			return false;
		}

		SamlIdpSpConnectionClp samlIdpSpConnection = (SamlIdpSpConnectionClp)obj;

		long primaryKey = samlIdpSpConnection.getPrimaryKey();

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

		sb.append("{samlIdpSpConnectionId=");
		sb.append(getSamlIdpSpConnectionId());
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
		sb.append(", samlSpEntityId=");
		sb.append(getSamlSpEntityId());
		sb.append(", assertionLifetime=");
		sb.append(getAssertionLifetime());
		sb.append(", attributeNames=");
		sb.append(getAttributeNames());
		sb.append(", attributesEnabled=");
		sb.append(getAttributesEnabled());
		sb.append(", attributesNamespaceEnabled=");
		sb.append(getAttributesNamespaceEnabled());
		sb.append(", enabled=");
		sb.append(getEnabled());
		sb.append(", metadataUrl=");
		sb.append(getMetadataUrl());
		sb.append(", metadataXml=");
		sb.append(getMetadataXml());
		sb.append(", metadataUpdatedDate=");
		sb.append(getMetadataUpdatedDate());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", nameIdAttribute=");
		sb.append(getNameIdAttribute());
		sb.append(", nameIdFormat=");
		sb.append(getNameIdFormat());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.saml.model.SamlIdpSpConnection");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>samlIdpSpConnectionId</column-name><column-value><![CDATA[");
		sb.append(getSamlIdpSpConnectionId());
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
			"<column><column-name>samlSpEntityId</column-name><column-value><![CDATA[");
		sb.append(getSamlSpEntityId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assertionLifetime</column-name><column-value><![CDATA[");
		sb.append(getAssertionLifetime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>attributeNames</column-name><column-value><![CDATA[");
		sb.append(getAttributeNames());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>attributesEnabled</column-name><column-value><![CDATA[");
		sb.append(getAttributesEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>attributesNamespaceEnabled</column-name><column-value><![CDATA[");
		sb.append(getAttributesNamespaceEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enabled</column-name><column-value><![CDATA[");
		sb.append(getEnabled());
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
			"<column><column-name>nameIdAttribute</column-name><column-value><![CDATA[");
		sb.append(getNameIdAttribute());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nameIdFormat</column-name><column-value><![CDATA[");
		sb.append(getNameIdFormat());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _samlIdpSpConnectionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _samlSpEntityId;
	private int _assertionLifetime;
	private String _attributeNames;
	private boolean _attributesEnabled;
	private boolean _attributesNamespaceEnabled;
	private boolean _enabled;
	private String _metadataUrl;
	private String _metadataXml;
	private Date _metadataUpdatedDate;
	private String _name;
	private String _nameIdAttribute;
	private String _nameIdFormat;
	private BaseModel<?> _samlIdpSpConnectionRemoteModel;
}