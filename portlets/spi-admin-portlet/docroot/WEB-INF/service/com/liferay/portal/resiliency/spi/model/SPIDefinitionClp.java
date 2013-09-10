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
		attributes.put("connectorAddress", getConnectorAddress());
		attributes.put("connectorPort", getConnectorPort());
		attributes.put("description", getDescription());
		attributes.put("jvmArguments", getJvmArguments());
		attributes.put("portletIds", getPortletIds());
		attributes.put("servletContextNames", getServletContextNames());
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

		String connectorAddress = (String)attributes.get("connectorAddress");

		if (connectorAddress != null) {
			setConnectorAddress(connectorAddress);
		}

		Integer connectorPort = (Integer)attributes.get("connectorPort");

		if (connectorPort != null) {
			setConnectorPort(connectorPort);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String jvmArguments = (String)attributes.get("jvmArguments");

		if (jvmArguments != null) {
			setJvmArguments(jvmArguments);
		}

		String portletIds = (String)attributes.get("portletIds");

		if (portletIds != null) {
			setPortletIds(portletIds);
		}

		String servletContextNames = (String)attributes.get(
				"servletContextNames");

		if (servletContextNames != null) {
			setServletContextNames(servletContextNames);
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
	public String getConnectorAddress() {
		return _connectorAddress;
	}

	@Override
	public void setConnectorAddress(String connectorAddress) {
		_connectorAddress = connectorAddress;

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setConnectorAddress",
						String.class);

				method.invoke(_spiDefinitionRemoteModel, connectorAddress);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getConnectorPort() {
		return _connectorPort;
	}

	@Override
	public void setConnectorPort(int connectorPort) {
		_connectorPort = connectorPort;

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setConnectorPort", int.class);

				method.invoke(_spiDefinitionRemoteModel, connectorPort);
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
	public String getPortletIds() {
		return _portletIds;
	}

	@Override
	public void setPortletIds(String portletIds) {
		_portletIds = portletIds;

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setPortletIds", String.class);

				method.invoke(_spiDefinitionRemoteModel, portletIds);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getServletContextNames() {
		return _servletContextNames;
	}

	@Override
	public void setServletContextNames(String servletContextNames) {
		_servletContextNames = servletContextNames;

		if (_spiDefinitionRemoteModel != null) {
			try {
				Class<?> clazz = _spiDefinitionRemoteModel.getClass();

				Method method = clazz.getMethod("setServletContextNames",
						String.class);

				method.invoke(_spiDefinitionRemoteModel, servletContextNames);
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

	@Override
	public boolean isAlive() {
		try {
			String methodName = "isAlive";

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

	@Override
	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties() {
		try {
			String methodName = "getTypeSettingsProperties";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.kernel.util.UnicodeProperties returnObj = (com.liferay.portal.kernel.util.UnicodeProperties)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties) {
		try {
			String methodName = "setTypeSettingsProperties";

			Class<?>[] parameterTypes = new Class<?>[] {
					com.liferay.portal.kernel.util.UnicodeProperties.class
				};

			Object[] parameterValues = new Object[] { typeSettingsProperties };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getTypeSettingsProperty(java.lang.String key,
		java.lang.String defaultValue) {
		try {
			String methodName = "getTypeSettingsProperty";

			Class<?>[] parameterTypes = new Class<?>[] {
					java.lang.String.class, java.lang.String.class
				};

			Object[] parameterValues = new Object[] { key, defaultValue };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getTypeSettingsProperty(java.lang.String key) {
		try {
			String methodName = "getTypeSettingsProperty";

			Class<?>[] parameterTypes = new Class<?>[] { java.lang.String.class };

			Object[] parameterValues = new Object[] { key };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
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
		clone.setConnectorAddress(getConnectorAddress());
		clone.setConnectorPort(getConnectorPort());
		clone.setDescription(getDescription());
		clone.setJvmArguments(getJvmArguments());
		clone.setPortletIds(getPortletIds());
		clone.setServletContextNames(getServletContextNames());
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
		StringBundler sb = new StringBundler(29);

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
		sb.append(", connectorAddress=");
		sb.append(getConnectorAddress());
		sb.append(", connectorPort=");
		sb.append(getConnectorPort());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", jvmArguments=");
		sb.append(getJvmArguments());
		sb.append(", portletIds=");
		sb.append(getPortletIds());
		sb.append(", servletContextNames=");
		sb.append(getServletContextNames());
		sb.append(", typeSettings=");
		sb.append(getTypeSettings());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

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
			"<column><column-name>connectorAddress</column-name><column-value><![CDATA[");
		sb.append(getConnectorAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>connectorPort</column-name><column-value><![CDATA[");
		sb.append(getConnectorPort());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jvmArguments</column-name><column-value><![CDATA[");
		sb.append(getJvmArguments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portletIds</column-name><column-value><![CDATA[");
		sb.append(getPortletIds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>servletContextNames</column-name><column-value><![CDATA[");
		sb.append(getServletContextNames());
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
	private String _connectorAddress;
	private int _connectorPort;
	private String _description;
	private String _jvmArguments;
	private String _portletIds;
	private String _servletContextNames;
	private String _typeSettings;
	private BaseModel<?> _spiDefinitionRemoteModel;
}