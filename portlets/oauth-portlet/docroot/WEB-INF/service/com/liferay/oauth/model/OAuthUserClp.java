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

package com.liferay.oauth.model;

import com.liferay.oauth.service.ClpSerializer;
import com.liferay.oauth.service.OAuthUserLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class OAuthUserClp extends BaseModelImpl<OAuthUser> implements OAuthUser {
	public OAuthUserClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthUser.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthUser.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthUserId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthUserId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthUserId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthUserId", getOAuthUserId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("oAuthApplicationId", getOAuthApplicationId());
		attributes.put("accessToken", getAccessToken());
		attributes.put("accessSecret", getAccessSecret());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthUserId = (Long)attributes.get("oAuthUserId");

		if (oAuthUserId != null) {
			setOAuthUserId(oAuthUserId);
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

		Long oAuthApplicationId = (Long)attributes.get("oAuthApplicationId");

		if (oAuthApplicationId != null) {
			setOAuthApplicationId(oAuthApplicationId);
		}

		String accessToken = (String)attributes.get("accessToken");

		if (accessToken != null) {
			setAccessToken(accessToken);
		}

		String accessSecret = (String)attributes.get("accessSecret");

		if (accessSecret != null) {
			setAccessSecret(accessSecret);
		}
	}

	@Override
	public long getOAuthUserId() {
		return _oAuthUserId;
	}

	@Override
	public void setOAuthUserId(long oAuthUserId) {
		_oAuthUserId = oAuthUserId;

		if (_oAuthUserRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthUserRemoteModel.getClass();

				Method method = clazz.getMethod("setOAuthUserId", long.class);

				method.invoke(_oAuthUserRemoteModel, oAuthUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getOAuthUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getOAuthUserId(), "uuid", _oAuthUserUuid);
	}

	@Override
	public void setOAuthUserUuid(String oAuthUserUuid) {
		_oAuthUserUuid = oAuthUserUuid;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_oAuthUserRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthUserRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_oAuthUserRemoteModel, companyId);
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

		if (_oAuthUserRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthUserRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_oAuthUserRemoteModel, userId);
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

		if (_oAuthUserRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthUserRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_oAuthUserRemoteModel, userName);
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

		if (_oAuthUserRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthUserRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_oAuthUserRemoteModel, createDate);
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

		if (_oAuthUserRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthUserRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_oAuthUserRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getOAuthApplicationId() {
		return _oAuthApplicationId;
	}

	@Override
	public void setOAuthApplicationId(long oAuthApplicationId) {
		_oAuthApplicationId = oAuthApplicationId;

		if (_oAuthUserRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthUserRemoteModel.getClass();

				Method method = clazz.getMethod("setOAuthApplicationId",
						long.class);

				method.invoke(_oAuthUserRemoteModel, oAuthApplicationId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAccessToken() {
		return _accessToken;
	}

	@Override
	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;

		if (_oAuthUserRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthUserRemoteModel.getClass();

				Method method = clazz.getMethod("setAccessToken", String.class);

				method.invoke(_oAuthUserRemoteModel, accessToken);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAccessSecret() {
		return _accessSecret;
	}

	@Override
	public void setAccessSecret(String accessSecret) {
		_accessSecret = accessSecret;

		if (_oAuthUserRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthUserRemoteModel.getClass();

				Method method = clazz.getMethod("setAccessSecret", String.class);

				method.invoke(_oAuthUserRemoteModel, accessSecret);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getOAuthUserRemoteModel() {
		return _oAuthUserRemoteModel;
	}

	public void setOAuthUserRemoteModel(BaseModel<?> oAuthUserRemoteModel) {
		_oAuthUserRemoteModel = oAuthUserRemoteModel;
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

		Class<?> remoteModelClass = _oAuthUserRemoteModel.getClass();

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

		Object returnValue = method.invoke(_oAuthUserRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			OAuthUserLocalServiceUtil.addOAuthUser(this);
		}
		else {
			OAuthUserLocalServiceUtil.updateOAuthUser(this);
		}
	}

	@Override
	public OAuthUser toEscapedModel() {
		return (OAuthUser)ProxyUtil.newProxyInstance(OAuthUser.class.getClassLoader(),
			new Class[] { OAuthUser.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OAuthUserClp clone = new OAuthUserClp();

		clone.setOAuthUserId(getOAuthUserId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setOAuthApplicationId(getOAuthApplicationId());
		clone.setAccessToken(getAccessToken());
		clone.setAccessSecret(getAccessSecret());

		return clone;
	}

	@Override
	public int compareTo(OAuthUser oAuthUser) {
		long primaryKey = oAuthUser.getPrimaryKey();

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

		if (!(obj instanceof OAuthUserClp)) {
			return false;
		}

		OAuthUserClp oAuthUser = (OAuthUserClp)obj;

		long primaryKey = oAuthUser.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{oAuthUserId=");
		sb.append(getOAuthUserId());
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
		sb.append(", oAuthApplicationId=");
		sb.append(getOAuthApplicationId());
		sb.append(", accessToken=");
		sb.append(getAccessToken());
		sb.append(", accessSecret=");
		sb.append(getAccessSecret());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.oauth.model.OAuthUser");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>oAuthUserId</column-name><column-value><![CDATA[");
		sb.append(getOAuthUserId());
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
			"<column><column-name>oAuthApplicationId</column-name><column-value><![CDATA[");
		sb.append(getOAuthApplicationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessToken</column-name><column-value><![CDATA[");
		sb.append(getAccessToken());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessSecret</column-name><column-value><![CDATA[");
		sb.append(getAccessSecret());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _oAuthUserId;
	private String _oAuthUserUuid;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _oAuthApplicationId;
	private String _accessToken;
	private String _accessSecret;
	private BaseModel<?> _oAuthUserRemoteModel;
}