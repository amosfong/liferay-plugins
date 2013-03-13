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

import com.liferay.saml.service.SamlSpSessionLocalServiceUtil;

import java.io.Serializable;

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

	public Class<?> getModelClass() {
		return SamlSpSession.class;
	}

	public String getModelClassName() {
		return SamlSpSession.class.getName();
	}

	public long getPrimaryKey() {
		return _samlSpSessionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSamlSpSessionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _samlSpSessionId;
	}

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
		attributes.put("jSessionId", getJSessionId());
		attributes.put("nameIdFormat", getNameIdFormat());
		attributes.put("nameIdValue", getNameIdValue());
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

		Boolean terminated = (Boolean)attributes.get("terminated");

		if (terminated != null) {
			setTerminated(terminated);
		}
	}

	public long getSamlSpSessionId() {
		return _samlSpSessionId;
	}

	public void setSamlSpSessionId(long samlSpSessionId) {
		_samlSpSessionId = samlSpSessionId;
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

	public String getJSessionId() {
		return _jSessionId;
	}

	public void setJSessionId(String jSessionId) {
		_jSessionId = jSessionId;
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

	public boolean getTerminated() {
		return _terminated;
	}

	public boolean isTerminated() {
		return _terminated;
	}

	public void setTerminated(boolean terminated) {
		_terminated = terminated;
	}

	public BaseModel<?> getSamlSpSessionRemoteModel() {
		return _samlSpSessionRemoteModel;
	}

	public void setSamlSpSessionRemoteModel(
		BaseModel<?> samlSpSessionRemoteModel) {
		_samlSpSessionRemoteModel = samlSpSessionRemoteModel;
	}

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
		clone.setJSessionId(getJSessionId());
		clone.setNameIdFormat(getNameIdFormat());
		clone.setNameIdValue(getNameIdValue());
		clone.setTerminated(getTerminated());

		return clone;
	}

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
		if (obj == null) {
			return false;
		}

		SamlSpSessionClp samlSpSession = null;

		try {
			samlSpSession = (SamlSpSessionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

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
		StringBundler sb = new StringBundler(21);

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
		sb.append(", jSessionId=");
		sb.append(getJSessionId());
		sb.append(", nameIdFormat=");
		sb.append(getNameIdFormat());
		sb.append(", nameIdValue=");
		sb.append(getNameIdValue());
		sb.append(", terminated=");
		sb.append(getTerminated());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

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
	private String _jSessionId;
	private String _nameIdFormat;
	private String _nameIdValue;
	private boolean _terminated;
	private BaseModel<?> _samlSpSessionRemoteModel;
}