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

package com.liferay.portal.workflow.kaleo.forms.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessModel;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the KaleoProcess service. Represents a row in the &quot;KaleoProcess&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoProcessImpl}.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessImpl
 * @see com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess
 * @see com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessModel
 * @generated
 */
@JSON(strict = true)
public class KaleoProcessModelImpl extends BaseModelImpl<KaleoProcess>
	implements KaleoProcessModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo process model instance should use the {@link com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess} interface instead.
	 */
	public static final String TABLE_NAME = "KaleoProcess";
	public static final Object[][] TABLE_COLUMNS = {
			{ "kaleoProcessId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "DDLRecordSetId", Types.BIGINT },
			{ "DDMTemplateId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table KaleoProcess (kaleoProcessId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,DDLRecordSetId LONG,DDMTemplateId LONG)";
	public static final String TABLE_SQL_DROP = "drop table KaleoProcess";
	public static final String ORDER_BY_JPQL = " ORDER BY kaleoProcess.kaleoProcessId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY KaleoProcess.kaleoProcessId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"),
			true);
	public static long DDLRECORDSETID_COLUMN_BITMASK = 1L;
	public static long GROUPID_COLUMN_BITMASK = 2L;
	public static long KALEOPROCESSID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static KaleoProcess toModel(KaleoProcessSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		KaleoProcess model = new KaleoProcessImpl();

		model.setKaleoProcessId(soapModel.getKaleoProcessId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDDLRecordSetId(soapModel.getDDLRecordSetId());
		model.setDDMTemplateId(soapModel.getDDMTemplateId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<KaleoProcess> toModels(KaleoProcessSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<KaleoProcess> models = new ArrayList<KaleoProcess>(soapModels.length);

		for (KaleoProcessSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"));

	public KaleoProcessModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoProcessId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoProcessId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoProcessId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoProcess.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoProcess.class.getName();
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

	@Override
	@JSON
	public long getKaleoProcessId() {
		return _kaleoProcessId;
	}

	@Override
	public void setKaleoProcessId(long kaleoProcessId) {
		_kaleoProcessId = kaleoProcessId;
	}

	@Override
	@JSON
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	@JSON
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	@JSON
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
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
	@JSON
	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	@JSON
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@Override
	@JSON
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	@JSON
	public long getDDLRecordSetId() {
		return _DDLRecordSetId;
	}

	@Override
	public void setDDLRecordSetId(long DDLRecordSetId) {
		_columnBitmask |= DDLRECORDSETID_COLUMN_BITMASK;

		if (!_setOriginalDDLRecordSetId) {
			_setOriginalDDLRecordSetId = true;

			_originalDDLRecordSetId = _DDLRecordSetId;
		}

		_DDLRecordSetId = DDLRecordSetId;
	}

	public long getOriginalDDLRecordSetId() {
		return _originalDDLRecordSetId;
	}

	@Override
	@JSON
	public long getDDMTemplateId() {
		return _DDMTemplateId;
	}

	@Override
	public void setDDMTemplateId(long DDMTemplateId) {
		_DDMTemplateId = DDMTemplateId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			KaleoProcess.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoProcess toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (KaleoProcess)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KaleoProcessImpl kaleoProcessImpl = new KaleoProcessImpl();

		kaleoProcessImpl.setKaleoProcessId(getKaleoProcessId());
		kaleoProcessImpl.setGroupId(getGroupId());
		kaleoProcessImpl.setCompanyId(getCompanyId());
		kaleoProcessImpl.setUserId(getUserId());
		kaleoProcessImpl.setUserName(getUserName());
		kaleoProcessImpl.setCreateDate(getCreateDate());
		kaleoProcessImpl.setModifiedDate(getModifiedDate());
		kaleoProcessImpl.setDDLRecordSetId(getDDLRecordSetId());
		kaleoProcessImpl.setDDMTemplateId(getDDMTemplateId());

		kaleoProcessImpl.resetOriginalValues();

		return kaleoProcessImpl;
	}

	@Override
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
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoProcess)) {
			return false;
		}

		KaleoProcess kaleoProcess = (KaleoProcess)obj;

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
	public void resetOriginalValues() {
		KaleoProcessModelImpl kaleoProcessModelImpl = this;

		kaleoProcessModelImpl._originalGroupId = kaleoProcessModelImpl._groupId;

		kaleoProcessModelImpl._setOriginalGroupId = false;

		kaleoProcessModelImpl._originalDDLRecordSetId = kaleoProcessModelImpl._DDLRecordSetId;

		kaleoProcessModelImpl._setOriginalDDLRecordSetId = false;

		kaleoProcessModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoProcess> toCacheModel() {
		KaleoProcessCacheModel kaleoProcessCacheModel = new KaleoProcessCacheModel();

		kaleoProcessCacheModel.kaleoProcessId = getKaleoProcessId();

		kaleoProcessCacheModel.groupId = getGroupId();

		kaleoProcessCacheModel.companyId = getCompanyId();

		kaleoProcessCacheModel.userId = getUserId();

		kaleoProcessCacheModel.userName = getUserName();

		String userName = kaleoProcessCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoProcessCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoProcessCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoProcessCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoProcessCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoProcessCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoProcessCacheModel.DDLRecordSetId = getDDLRecordSetId();

		kaleoProcessCacheModel.DDMTemplateId = getDDMTemplateId();

		return kaleoProcessCacheModel;
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

	@Override
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

	private static ClassLoader _classLoader = KaleoProcess.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			KaleoProcess.class
		};
	private long _kaleoProcessId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _DDLRecordSetId;
	private long _originalDDLRecordSetId;
	private boolean _setOriginalDDLRecordSetId;
	private long _DDMTemplateId;
	private long _columnBitmask;
	private KaleoProcess _escapedModel;
}