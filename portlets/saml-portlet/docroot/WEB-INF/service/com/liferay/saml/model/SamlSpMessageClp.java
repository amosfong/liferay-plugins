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

import com.liferay.saml.service.SamlSpMessageLocalServiceUtil;

import java.io.Serializable;

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

	public Class<?> getModelClass() {
		return SamlSpMessage.class;
	}

	public String getModelClassName() {
		return SamlSpMessage.class.getName();
	}

	public long getPrimaryKey() {
		return _samlSpMessageId;
	}

	public void setPrimaryKey(long primaryKey) {
		setSamlSpMessageId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_samlSpMessageId);
	}

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

	public long getSamlSpMessageId() {
		return _samlSpMessageId;
	}

	public void setSamlSpMessageId(long samlSpMessageId) {
		_samlSpMessageId = samlSpMessageId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getSamlIdpEntityId() {
		return _samlIdpEntityId;
	}

	public void setSamlIdpEntityId(String samlIdpEntityId) {
		_samlIdpEntityId = samlIdpEntityId;
	}

	public String getSamlIdpResponseKey() {
		return _samlIdpResponseKey;
	}

	public void setSamlIdpResponseKey(String samlIdpResponseKey) {
		_samlIdpResponseKey = samlIdpResponseKey;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public boolean isExpired() {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getSamlSpMessageRemoteModel() {
		return _samlSpMessageRemoteModel;
	}

	public void setSamlSpMessageRemoteModel(
		BaseModel<?> samlSpMessageRemoteModel) {
		_samlSpMessageRemoteModel = samlSpMessageRemoteModel;
	}

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
		if (obj == null) {
			return false;
		}

		SamlSpMessageClp samlSpMessage = null;

		try {
			samlSpMessage = (SamlSpMessageClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

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