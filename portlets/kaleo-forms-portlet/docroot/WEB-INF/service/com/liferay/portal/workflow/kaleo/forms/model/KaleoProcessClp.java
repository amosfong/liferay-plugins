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

package com.liferay.portal.workflow.kaleo.forms.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalServiceUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marcellus Tavares
 */
public class KaleoProcessClp extends BaseModelImpl<KaleoProcess>
	implements KaleoProcess {
	public KaleoProcessClp() {
	}

	public Class<?> getModelClass() {
		return KaleoProcess.class;
	}

	public String getModelClassName() {
		return KaleoProcess.class.getName();
	}

	public long getPrimaryKey() {
		return _kaleoProcessId;
	}

	public void setPrimaryKey(long primaryKey) {
		setKaleoProcessId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoProcessId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoProcessId", getKaleoProcessId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("DDLRecordSetId", getDDLRecordSetId());
		attributes.put("DDMTemplateId", getDDMTemplateId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoProcessId = (Long)attributes.get("kaleoProcessId");

		if (kaleoProcessId != null) {
			setKaleoProcessId(kaleoProcessId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		Long DDLRecordSetId = (Long)attributes.get("DDLRecordSetId");

		if (DDLRecordSetId != null) {
			setDDLRecordSetId(DDLRecordSetId);
		}

		Long DDMTemplateId = (Long)attributes.get("DDMTemplateId");

		if (DDMTemplateId != null) {
			setDDMTemplateId(DDMTemplateId);
		}
	}

	public long getKaleoProcessId() {
		return _kaleoProcessId;
	}

	public void setKaleoProcessId(long kaleoProcessId) {
		_kaleoProcessId = kaleoProcessId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getDDLRecordSetId() {
		return _DDLRecordSetId;
	}

	public void setDDLRecordSetId(long DDLRecordSetId) {
		_DDLRecordSetId = DDLRecordSetId;
	}

	public long getDDMTemplateId() {
		return _DDMTemplateId;
	}

	public void setDDMTemplateId(long DDMTemplateId) {
		_DDMTemplateId = DDMTemplateId;
	}

	public com.liferay.portlet.dynamicdatamapping.model.DDMTemplate getDDMTemplate() {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portlet.dynamicdatalists.model.DDLRecordSet getDDLRecordSet() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getName(java.util.Locale locale) {
		throw new UnsupportedOperationException();
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> getKaleoProcessLinks() {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getDescription(java.util.Locale locale) {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getKaleoProcessRemoteModel() {
		return _kaleoProcessRemoteModel;
	}

	public void setKaleoProcessRemoteModel(BaseModel<?> kaleoProcessRemoteModel) {
		_kaleoProcessRemoteModel = kaleoProcessRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			KaleoProcessLocalServiceUtil.addKaleoProcess(this);
		}
		else {
			KaleoProcessLocalServiceUtil.updateKaleoProcess(this);
		}
	}

	@Override
	public KaleoProcess toEscapedModel() {
		return (KaleoProcess)ProxyUtil.newProxyInstance(KaleoProcess.class.getClassLoader(),
			new Class[] { KaleoProcess.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoProcessClp clone = new KaleoProcessClp();

		clone.setKaleoProcessId(getKaleoProcessId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setDDLRecordSetId(getDDLRecordSetId());
		clone.setDDMTemplateId(getDDMTemplateId());

		return clone;
	}

	public int compareTo(KaleoProcess kaleoProcess) {
		long primaryKey = kaleoProcess.getPrimaryKey();

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

		KaleoProcessClp kaleoProcess = null;

		try {
			kaleoProcess = (KaleoProcessClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = kaleoProcess.getPrimaryKey();

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

		sb.append("{kaleoProcessId=");
		sb.append(getKaleoProcessId());
		sb.append(", groupId=");
		sb.append(getGroupId());
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
		sb.append(", DDLRecordSetId=");
		sb.append(getDDLRecordSetId());
		sb.append(", DDMTemplateId=");
		sb.append(getDDMTemplateId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoProcessId</column-name><column-value><![CDATA[");
		sb.append(getKaleoProcessId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
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
			"<column><column-name>DDLRecordSetId</column-name><column-value><![CDATA[");
		sb.append(getDDLRecordSetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>DDMTemplateId</column-name><column-value><![CDATA[");
		sb.append(getDDMTemplateId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoProcessId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _DDLRecordSetId;
	private long _DDMTemplateId;
	private BaseModel<?> _kaleoProcessRemoteModel;
}