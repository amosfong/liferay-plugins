/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.saml.service.SamlIdpSpSessionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class SamlIdpSpSessionClp extends BaseModelImpl<SamlIdpSpSession>
	implements SamlIdpSpSession {
	public SamlIdpSpSessionClp() {
	}

	public Class<?> getModelClass() {
		return SamlIdpSpSession.class;
	}

	public String getModelClassName() {
		return SamlIdpSpSession.class.getName();
	}

	public long getPrimaryKey() {
		return _samlIdpSpSessionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSamlIdpSpSessionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_samlIdpSpSessionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("samlIdpSpSessionId", getSamlIdpSpSessionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("samlIdpSsoSessionId", getSamlIdpSsoSessionId());
		attributes.put("samlSpEntityId", getSamlSpEntityId());
		attributes.put("nameIdFormat", getNameIdFormat());
		attributes.put("nameIdValue", getNameIdValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long samlIdpSpSessionId = (Long)attributes.get("samlIdpSpSessionId");

		if (samlIdpSpSessionId != null) {
			setSamlIdpSpSessionId(samlIdpSpSessionId);
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

		Long samlIdpSsoSessionId = (Long)attributes.get("samlIdpSsoSessionId");

		if (samlIdpSsoSessionId != null) {
			setSamlIdpSsoSessionId(samlIdpSsoSessionId);
		}

		String samlSpEntityId = (String)attributes.get("samlSpEntityId");

		if (samlSpEntityId != null) {
			setSamlSpEntityId(samlSpEntityId);
		}

		String nameIdFormat = (String)attributes.get("nameIdFormat");

		if (nameIdFormat != null) {
			setNameIdFormat(nameIdFormat);
		}

		String nameIdValue = (String)attributes.get("nameIdValue");

		if (nameIdValue != null) {
			setNameIdValue(nameIdValue);
		}
	}

	public long getSamlIdpSpSessionId() {
		return _samlIdpSpSessionId;
	}

	public void setSamlIdpSpSessionId(long samlIdpSpSessionId) {
		_samlIdpSpSessionId = samlIdpSpSessionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getSamlIdpSsoSessionId() {
		return _samlIdpSsoSessionId;
	}

	public void setSamlIdpSsoSessionId(long samlIdpSsoSessionId) {
		_samlIdpSsoSessionId = samlIdpSsoSessionId;
	}

	public String getSamlSpEntityId() {
		return _samlSpEntityId;
	}

	public void setSamlSpEntityId(String samlSpEntityId) {
		_samlSpEntityId = samlSpEntityId;
	}

	public String getNameIdFormat() {
		return _nameIdFormat;
	}

	public void setNameIdFormat(String nameIdFormat) {
		_nameIdFormat = nameIdFormat;
	}

	public String getNameIdValue() {
		return _nameIdValue;
	}

	public void setNameIdValue(String nameIdValue) {
		_nameIdValue = nameIdValue;
	}

	public BaseModel<?> getSamlIdpSpSessionRemoteModel() {
		return _samlIdpSpSessionRemoteModel;
	}

	public void setSamlIdpSpSessionRemoteModel(
		BaseModel<?> samlIdpSpSessionRemoteModel) {
		_samlIdpSpSessionRemoteModel = samlIdpSpSessionRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			SamlIdpSpSessionLocalServiceUtil.addSamlIdpSpSession(this);
		}
		else {
			SamlIdpSpSessionLocalServiceUtil.updateSamlIdpSpSession(this);
		}
	}

	@Override
	public SamlIdpSpSession toEscapedModel() {
		return (SamlIdpSpSession)ProxyUtil.newProxyInstance(SamlIdpSpSession.class.getClassLoader(),
			new Class[] { SamlIdpSpSession.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public SamlIdpSpSession toUnescapedModel() {
		if (ProxyUtil.isProxyClass(getClass())) {
			InvocationHandler invocationHandler = ProxyUtil.getInvocationHandler(this);

			AutoEscapeBeanHandler autoEscapeBeanHandler = (AutoEscapeBeanHandler)invocationHandler;

			return (SamlIdpSpSession)autoEscapeBeanHandler.getBean();
		}
		else {
			return (SamlIdpSpSession)this;
		}
	}

	@Override
	public Object clone() {
		SamlIdpSpSessionClp clone = new SamlIdpSpSessionClp();

		clone.setSamlIdpSpSessionId(getSamlIdpSpSessionId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setSamlIdpSsoSessionId(getSamlIdpSsoSessionId());
		clone.setSamlSpEntityId(getSamlSpEntityId());
		clone.setNameIdFormat(getNameIdFormat());
		clone.setNameIdValue(getNameIdValue());

		return clone;
	}

	public int compareTo(SamlIdpSpSession samlIdpSpSession) {
		long primaryKey = samlIdpSpSession.getPrimaryKey();

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
		if (obj == null) {
			return false;
		}

		SamlIdpSpSessionClp samlIdpSpSession = null;

		try {
			samlIdpSpSession = (SamlIdpSpSessionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = samlIdpSpSession.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{samlIdpSpSessionId=");
		sb.append(getSamlIdpSpSessionId());
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
		sb.append(", samlIdpSsoSessionId=");
		sb.append(getSamlIdpSsoSessionId());
		sb.append(", samlSpEntityId=");
		sb.append(getSamlSpEntityId());
		sb.append(", nameIdFormat=");
		sb.append(getNameIdFormat());
		sb.append(", nameIdValue=");
		sb.append(getNameIdValue());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.saml.model.SamlIdpSpSession");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>samlIdpSpSessionId</column-name><column-value><![CDATA[");
		sb.append(getSamlIdpSpSessionId());
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
			"<column><column-name>samlIdpSsoSessionId</column-name><column-value><![CDATA[");
		sb.append(getSamlIdpSsoSessionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>samlSpEntityId</column-name><column-value><![CDATA[");
		sb.append(getSamlSpEntityId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nameIdFormat</column-name><column-value><![CDATA[");
		sb.append(getNameIdFormat());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nameIdValue</column-name><column-value><![CDATA[");
		sb.append(getNameIdValue());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _samlIdpSpSessionId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _samlIdpSsoSessionId;
	private String _samlSpEntityId;
	private String _nameIdFormat;
	private String _nameIdValue;
	private BaseModel<?> _samlIdpSpSessionRemoteModel;
}