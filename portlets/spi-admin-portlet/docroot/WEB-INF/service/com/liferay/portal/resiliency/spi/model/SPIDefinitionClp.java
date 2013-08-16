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

package com.liferay.portal.resiliency.spi.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.resiliency.spi.service.ClpSerializer;
import com.liferay.portal.resiliency.spi.service.SPIDefinitionLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class SPIDefinitionClp extends BaseModelImpl<SPIDefinition>
	implements SPIDefinition {
	public SPIDefinitionClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SPIDefinition.class;
	}

	@Override
	public String getModelClassName() {
		return SPIDefinition.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _spiDefinitionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSpiDefinitionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _spiDefinitionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("spiDefinitionId", getSpiDefinitionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("applications", getApplications());
		attributes.put("jvmArguments", getJvmArguments());
		attributes.put("typeSettings", getTypeSettings());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long spiDefinitionId = (Long)attributes.get("spiDefinitionId");

		if (spiDefinitionId != null) {
			setSpiDefinitionId(spiDefinitionId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String applications = (String)attributes.get("applications");

		if (applications != null) {
			setApplications(applications);
		}

		String jvmArguments = (String)attributes.get("jvmArguments");

		if (jvmArguments != null) {
			setJvmArguments(jvmArguments);
		}

		String typeSettings = (String)attributes.get("typeSettings");

		if (typeSettings != null) {
			setTypeSettings(typeSettings);
		}
	}

	@Override
	public long getSpiDefinitionId() {
		return _spiDefinitionId;
	}

	@Override
	public void setSpiDefinitionId(long spiDefinitionId) {
		_spiDefinitionId = spiDefinitionId;

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setSpiDefinitionId", long.class);

				method.invoke(_spiDefinitionRemoteModel, spiDefinitionId);
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

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_spiDefinitionRemoteModel, companyId);
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

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_spiDefinitionRemoteModel, userId);
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

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_spiDefinitionRemoteModel, userName);
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

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_spiDefinitionRemoteModel, createDate);
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

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_spiDefinitionRemoteModel, modifiedDate);
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

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_spiDefinitionRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_spiDefinitionRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getApplications() {
		return _applications;
	}

	@Override
	public void setApplications(String applications) {
		_applications = applications;

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setApplications", String.class);

				method.invoke(_spiDefinitionRemoteModel, applications);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getJvmArguments() {
		return _jvmArguments;
	}

	@Override
	public void setJvmArguments(String jvmArguments) {
		_jvmArguments = jvmArguments;

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setJvmArguments", String.class);

				method.invoke(_spiDefinitionRemoteModel, jvmArguments);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTypeSettings() {
		return _typeSettings;
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setTypeSettings", String.class);

				method.invoke(_spiDefinitionRemoteModel, typeSettings);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getSPIDefinitionRemoteModel() {
		return _spiDefinitionRemoteModel;
	}

	public void setSPIDefinitionRemoteModel(
		BaseModel<?> spiDefinitionRemoteModel) {
		_spiDefinitionRemoteModel = spiDefinitionRemoteModel;
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

		Class<?> remoteModelClass = _spiDefinitionRemoteModel.getClass();

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

		Object returnValue = method.invoke(_spiDefinitionRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			SPIDefinitionLocalServiceUtil.addSPIDefinition(this);
		}
		else {
			SPIDefinitionLocalServiceUtil.updateSPIDefinition(this);
		}
	}

	@Override
	public SPIDefinition toEscapedModel() {
		return (SPIDefinition)ProxyUtil.newProxyInstance(SPIDefinition.class.getClassLoader(),
			new Class[] { SPIDefinition.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SPIDefinitionClp clone = new SPIDefinitionClp();

		clone.setSpiDefinitionId(getSpiDefinitionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setApplications(getApplications());
		clone.setJvmArguments(getJvmArguments());
		clone.setTypeSettings(getTypeSettings());

		return clone;
	}

	@Override
	public int compareTo(SPIDefinition spiDefinition) {
		int value = 0;

		if (getSpiDefinitionId() < spiDefinition.getSpiDefinitionId()) {
			value = -1;
		}
		else if (getSpiDefinitionId() > spiDefinition.getSpiDefinitionId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SPIDefinitionClp)) {
			return false;
		}

		SPIDefinitionClp spiDefinition = (SPIDefinitionClp)obj;

		long primaryKey = spiDefinition.getPrimaryKey();

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
		StringBundler sb = new StringBundler(23);

		sb.append("{spiDefinitionId=");
		sb.append(getSpiDefinitionId());
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
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", applications=");
		sb.append(getApplications());
		sb.append(", jvmArguments=");
		sb.append(getJvmArguments());
		sb.append(", typeSettings=");
		sb.append(getTypeSettings());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.resiliency.spi.model.SPIDefinition");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>spiDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getSpiDefinitionId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>applications</column-name><column-value><![CDATA[");
		sb.append(getApplications());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jvmArguments</column-name><column-value><![CDATA[");
		sb.append(getJvmArguments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typeSettings</column-name><column-value><![CDATA[");
		sb.append(getTypeSettings());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _spiDefinitionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _applications;
	private String _jvmArguments;
	private String _typeSettings;
	private BaseModel<?> _spiDefinitionRemoteModel;
}