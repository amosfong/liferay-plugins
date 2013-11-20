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

package com.liferay.bbb.model.impl;

import com.liferay.bbb.model.BBBServer;
import com.liferay.bbb.model.BBBServerModel;
import com.liferay.bbb.model.BBBServerSoap;

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
 * The base model implementation for the BBBServer service. Represents a row in the &quot;BBBServer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.bbb.model.BBBServerModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BBBServerImpl}.
 * </p>
 *
 * @author Shinn Lok
 * @see BBBServerImpl
 * @see com.liferay.bbb.model.BBBServer
 * @see com.liferay.bbb.model.BBBServerModel
 * @generated
 */
@JSON(strict = true)
public class BBBServerModelImpl extends BaseModelImpl<BBBServer>
	implements BBBServerModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a b b b server model instance should use the {@link com.liferay.bbb.model.BBBServer} interface instead.
	 */
	public static final String TABLE_NAME = "BBBServer";
	public static final Object[][] TABLE_COLUMNS = {
			{ "bbbServerId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "url", Types.VARCHAR },
			{ "secret", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table BBBServer (bbbServerId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(75) null,url VARCHAR(75) null,secret VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table BBBServer";
	public static final String ORDER_BY_JPQL = " ORDER BY bbbServer.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY BBBServer.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.bbb.model.BBBServer"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.bbb.model.BBBServer"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.bbb.model.BBBServer"),
			true);
	public static long GROUPID_COLUMN_BITMASK = 1L;
	public static long NAME_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static BBBServer toModel(BBBServerSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		BBBServer model = new BBBServerImpl();

		model.setBbbServerId(soapModel.getBbbServerId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setUrl(soapModel.getUrl());
		model.setSecret(soapModel.getSecret());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<BBBServer> toModels(BBBServerSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<BBBServer> models = new ArrayList<BBBServer>(soapModels.length);

		for (BBBServerSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.bbb.model.BBBServer"));

	public BBBServerModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _bbbServerId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setBbbServerId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _bbbServerId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return BBBServer.class;
	}

	@Override
	public String getModelClassName() {
		return BBBServer.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("bbbServerId", getBbbServerId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("url", getUrl());
		attributes.put("secret", getSecret());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long bbbServerId = (Long)attributes.get("bbbServerId");

		if (bbbServerId != null) {
			setBbbServerId(bbbServerId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}

		String secret = (String)attributes.get("secret");

		if (secret != null) {
			setSecret(secret);
		}
	}

	@JSON
	@Override
	public long getBbbServerId() {
		return _bbbServerId;
	}

	@Override
	public void setBbbServerId(long bbbServerId) {
		_bbbServerId = bbbServerId;
	}

	@JSON
	@Override
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

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
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

	@JSON
	@Override
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

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@JSON
	@Override
	public String getUrl() {
		if (_url == null) {
			return StringPool.BLANK;
		}
		else {
			return _url;
		}
	}

	@Override
	public void setUrl(String url) {
		_url = url;
	}

	@JSON
	@Override
	public String getSecret() {
		if (_secret == null) {
			return StringPool.BLANK;
		}
		else {
			return _secret;
		}
	}

	@Override
	public void setSecret(String secret) {
		_secret = secret;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			BBBServer.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public BBBServer toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (BBBServer)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		BBBServerImpl bbbServerImpl = new BBBServerImpl();

		bbbServerImpl.setBbbServerId(getBbbServerId());
		bbbServerImpl.setGroupId(getGroupId());
		bbbServerImpl.setCompanyId(getCompanyId());
		bbbServerImpl.setUserId(getUserId());
		bbbServerImpl.setUserName(getUserName());
		bbbServerImpl.setCreateDate(getCreateDate());
		bbbServerImpl.setModifiedDate(getModifiedDate());
		bbbServerImpl.setName(getName());
		bbbServerImpl.setUrl(getUrl());
		bbbServerImpl.setSecret(getSecret());

		bbbServerImpl.resetOriginalValues();

		return bbbServerImpl;
	}

	@Override
	public int compareTo(BBBServer bbbServer) {
		int value = 0;

		value = getName().compareToIgnoreCase(bbbServer.getName());

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

		if (!(obj instanceof BBBServer)) {
			return false;
		}

		BBBServer bbbServer = (BBBServer)obj;

		long primaryKey = bbbServer.getPrimaryKey();

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
		BBBServerModelImpl bbbServerModelImpl = this;

		bbbServerModelImpl._originalGroupId = bbbServerModelImpl._groupId;

		bbbServerModelImpl._setOriginalGroupId = false;

		bbbServerModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<BBBServer> toCacheModel() {
		BBBServerCacheModel bbbServerCacheModel = new BBBServerCacheModel();

		bbbServerCacheModel.bbbServerId = getBbbServerId();

		bbbServerCacheModel.groupId = getGroupId();

		bbbServerCacheModel.companyId = getCompanyId();

		bbbServerCacheModel.userId = getUserId();

		bbbServerCacheModel.userName = getUserName();

		String userName = bbbServerCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			bbbServerCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			bbbServerCacheModel.createDate = createDate.getTime();
		}
		else {
			bbbServerCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			bbbServerCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			bbbServerCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		bbbServerCacheModel.name = getName();

		String name = bbbServerCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			bbbServerCacheModel.name = null;
		}

		bbbServerCacheModel.url = getUrl();

		String url = bbbServerCacheModel.url;

		if ((url != null) && (url.length() == 0)) {
			bbbServerCacheModel.url = null;
		}

		bbbServerCacheModel.secret = getSecret();

		String secret = bbbServerCacheModel.secret;

		if ((secret != null) && (secret.length() == 0)) {
			bbbServerCacheModel.secret = null;
		}

		return bbbServerCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{bbbServerId=");
		sb.append(getBbbServerId());
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
		sb.append(", name=");
		sb.append(getName());
		sb.append(", url=");
		sb.append(getUrl());
		sb.append(", secret=");
		sb.append(getSecret());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.bbb.model.BBBServer");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>bbbServerId</column-name><column-value><![CDATA[");
		sb.append(getBbbServerId());
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
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>url</column-name><column-value><![CDATA[");
		sb.append(getUrl());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>secret</column-name><column-value><![CDATA[");
		sb.append(getSecret());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = BBBServer.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			BBBServer.class
		};
	private long _bbbServerId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _url;
	private String _secret;
	private long _columnBitmask;
	private BBBServer _escapedModel;
}