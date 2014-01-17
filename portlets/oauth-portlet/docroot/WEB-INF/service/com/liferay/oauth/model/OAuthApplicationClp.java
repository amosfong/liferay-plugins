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
import com.liferay.oauth.service.OAuthApplicationLocalServiceUtil;

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
public class OAuthApplicationClp extends BaseModelImpl<OAuthApplication>
	implements OAuthApplication {
	public OAuthApplicationClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return OAuthApplication.class;
	}

	@Override
	public String getModelClassName() {
		return OAuthApplication.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _oAuthApplicationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setOAuthApplicationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _oAuthApplicationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("oAuthApplicationId", getOAuthApplicationId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("consumerKey", getConsumerKey());
		attributes.put("consumerSecret", getConsumerSecret());
		attributes.put("accessLevel", getAccessLevel());
		attributes.put("logoId", getLogoId());
		attributes.put("shareableAccessToken", getShareableAccessToken());
		attributes.put("callbackURI", getCallbackURI());
		attributes.put("websiteURL", getWebsiteURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long oAuthApplicationId = (Long)attributes.get("oAuthApplicationId");

		if (oAuthApplicationId != null) {
			setOAuthApplicationId(oAuthApplicationId);
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

		String consumerKey = (String)attributes.get("consumerKey");

		if (consumerKey != null) {
			setConsumerKey(consumerKey);
		}

		String consumerSecret = (String)attributes.get("consumerSecret");

		if (consumerSecret != null) {
			setConsumerSecret(consumerSecret);
		}

		Integer accessLevel = (Integer)attributes.get("accessLevel");

		if (accessLevel != null) {
			setAccessLevel(accessLevel);
		}

		Long logoId = (Long)attributes.get("logoId");

		if (logoId != null) {
			setLogoId(logoId);
		}

		Boolean shareableAccessToken = (Boolean)attributes.get(
				"shareableAccessToken");

		if (shareableAccessToken != null) {
			setShareableAccessToken(shareableAccessToken);
		}

		String callbackURI = (String)attributes.get("callbackURI");

		if (callbackURI != null) {
			setCallbackURI(callbackURI);
		}

		String websiteURL = (String)attributes.get("websiteURL");

		if (websiteURL != null) {
			setWebsiteURL(websiteURL);
		}
	}

	@Override
	public long getOAuthApplicationId() {
		return _oAuthApplicationId;
	}

	@Override
	public void setOAuthApplicationId(long oAuthApplicationId) {
		_oAuthApplicationId = oAuthApplicationId;

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setOAuthApplicationId",
						long.class);

				method.invoke(_oAuthApplicationRemoteModel, oAuthApplicationId);
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

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_oAuthApplicationRemoteModel, companyId);
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

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_oAuthApplicationRemoteModel, userId);
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

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_oAuthApplicationRemoteModel, userName);
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

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_oAuthApplicationRemoteModel, createDate);
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

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_oAuthApplicationRemoteModel, modifiedDate);
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

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_oAuthApplicationRemoteModel, name);
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

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_oAuthApplicationRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getConsumerKey() {
		return _consumerKey;
	}

	@Override
	public void setConsumerKey(String consumerKey) {
		_consumerKey = consumerKey;

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setConsumerKey", String.class);

				method.invoke(_oAuthApplicationRemoteModel, consumerKey);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getConsumerSecret() {
		return _consumerSecret;
	}

	@Override
	public void setConsumerSecret(String consumerSecret) {
		_consumerSecret = consumerSecret;

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setConsumerSecret",
						String.class);

				method.invoke(_oAuthApplicationRemoteModel, consumerSecret);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getAccessLevel() {
		return _accessLevel;
	}

	@Override
	public void setAccessLevel(int accessLevel) {
		_accessLevel = accessLevel;

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setAccessLevel", int.class);

				method.invoke(_oAuthApplicationRemoteModel, accessLevel);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getLogoId() {
		return _logoId;
	}

	@Override
	public void setLogoId(long logoId) {
		_logoId = logoId;

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setLogoId", long.class);

				method.invoke(_oAuthApplicationRemoteModel, logoId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getShareableAccessToken() {
		return _shareableAccessToken;
	}

	@Override
	public boolean isShareableAccessToken() {
		return _shareableAccessToken;
	}

	@Override
	public void setShareableAccessToken(boolean shareableAccessToken) {
		_shareableAccessToken = shareableAccessToken;

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setShareableAccessToken",
						boolean.class);

				method.invoke(_oAuthApplicationRemoteModel, shareableAccessToken);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCallbackURI() {
		return _callbackURI;
	}

	@Override
	public void setCallbackURI(String callbackURI) {
		_callbackURI = callbackURI;

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setCallbackURI", String.class);

				method.invoke(_oAuthApplicationRemoteModel, callbackURI);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getWebsiteURL() {
		return _websiteURL;
	}

	@Override
	public void setWebsiteURL(String websiteURL) {
		_websiteURL = websiteURL;

		if (_oAuthApplicationRemoteModel != null) {
			try {
				Class<?> clazz = _oAuthApplicationRemoteModel.getClass();

				Method method = clazz.getMethod("setWebsiteURL", String.class);

				method.invoke(_oAuthApplicationRemoteModel, websiteURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.lang.String getAccessLevelLabel() {
		try {
			String methodName = "getAccessLevelLabel";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getOAuthApplicationRemoteModel() {
		return _oAuthApplicationRemoteModel;
	}

	public void setOAuthApplicationRemoteModel(
		BaseModel<?> oAuthApplicationRemoteModel) {
		_oAuthApplicationRemoteModel = oAuthApplicationRemoteModel;
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

		Class<?> remoteModelClass = _oAuthApplicationRemoteModel.getClass();

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

		Object returnValue = method.invoke(_oAuthApplicationRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			OAuthApplicationLocalServiceUtil.addOAuthApplication(this);
		}
		else {
			OAuthApplicationLocalServiceUtil.updateOAuthApplication(this);
		}
	}

	@Override
	public OAuthApplication toEscapedModel() {
		return (OAuthApplication)ProxyUtil.newProxyInstance(OAuthApplication.class.getClassLoader(),
			new Class[] { OAuthApplication.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OAuthApplicationClp clone = new OAuthApplicationClp();

		clone.setOAuthApplicationId(getOAuthApplicationId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setConsumerKey(getConsumerKey());
		clone.setConsumerSecret(getConsumerSecret());
		clone.setAccessLevel(getAccessLevel());
		clone.setLogoId(getLogoId());
		clone.setShareableAccessToken(getShareableAccessToken());
		clone.setCallbackURI(getCallbackURI());
		clone.setWebsiteURL(getWebsiteURL());

		return clone;
	}

	@Override
	public int compareTo(OAuthApplication oAuthApplication) {
		long primaryKey = oAuthApplication.getPrimaryKey();

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

		if (!(obj instanceof OAuthApplicationClp)) {
			return false;
		}

		OAuthApplicationClp oAuthApplication = (OAuthApplicationClp)obj;

		long primaryKey = oAuthApplication.getPrimaryKey();

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
		StringBundler sb = new StringBundler(31);

		sb.append("{oAuthApplicationId=");
		sb.append(getOAuthApplicationId());
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
		sb.append(", consumerKey=");
		sb.append(getConsumerKey());
		sb.append(", consumerSecret=");
		sb.append(getConsumerSecret());
		sb.append(", accessLevel=");
		sb.append(getAccessLevel());
		sb.append(", logoId=");
		sb.append(getLogoId());
		sb.append(", shareableAccessToken=");
		sb.append(getShareableAccessToken());
		sb.append(", callbackURI=");
		sb.append(getCallbackURI());
		sb.append(", websiteURL=");
		sb.append(getWebsiteURL());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(49);

		sb.append("<model><model-name>");
		sb.append("com.liferay.oauth.model.OAuthApplication");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>oAuthApplicationId</column-name><column-value><![CDATA[");
		sb.append(getOAuthApplicationId());
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
			"<column><column-name>consumerKey</column-name><column-value><![CDATA[");
		sb.append(getConsumerKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerSecret</column-name><column-value><![CDATA[");
		sb.append(getConsumerSecret());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessLevel</column-name><column-value><![CDATA[");
		sb.append(getAccessLevel());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>logoId</column-name><column-value><![CDATA[");
		sb.append(getLogoId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>shareableAccessToken</column-name><column-value><![CDATA[");
		sb.append(getShareableAccessToken());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>callbackURI</column-name><column-value><![CDATA[");
		sb.append(getCallbackURI());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>websiteURL</column-name><column-value><![CDATA[");
		sb.append(getWebsiteURL());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _oAuthApplicationId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _consumerKey;
	private String _consumerSecret;
	private int _accessLevel;
	private long _logoId;
	private boolean _shareableAccessToken;
	private String _callbackURI;
	private String _websiteURL;
	private BaseModel<?> _oAuthApplicationRemoteModel;
}