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

package com.liferay.documentum.repository.model;

import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfTime;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.Lock;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author Mika Koivisto
 */
public class DocumentumLock
	extends DocumentumModel implements CacheModel<Lock>, Lock {

	public DocumentumLock(long companyId, IDfSysObject idfSysObject)
		throws DfException {

		_companyId = companyId;

		User lockOwner = getUser(idfSysObject.getLockOwner());

		IDfTime idfTime = idfSysObject.getLockDate();

		_createDate = idfTime.getDate();

		_userId = lockOwner.getUserId();
		_userName = lockOwner.getFullName();
	}

	@Override
	public Object clone() {
		return this;
	}

	@Override
	public int compareTo(Lock lock) {
		long primaryKey = lock.getPrimaryKey();

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

		if (!(obj instanceof Lock)) {
			return false;
		}

		Lock lock = (Lock)obj;

		if (Validator.equals(getPrimaryKey(), lock.getPrimaryKey())) {
			return true;
		}

		return false;
	}

	@Override
	public String getClassName() {
		return null;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return null;
	}

	@Override
	public Date getExpirationDate() {
		return null;
	}

	@Override
	public long getExpirationTime() {
		return 0;
	}

	@Override
	public boolean getInheritable() {
		return false;
	}

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public long getLockId() {
		return 0;
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		return Collections.emptyMap();
	}

	@Override
	public Class<?> getModelClass() {
		return Lock.class;
	}

	@Override
	public String getModelClassName() {
		return Lock.class.getName();
	}

	@Override
	public String getOwner() {
		return getUserName();
	}

	@Override
	public long getPrimaryKey() {
		return 0;
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return null;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public String getUserUuid() {
		return null;
	}

	@Override
	public String getUuid() {
		return null;
	}

	@Override
	public boolean isCachedModel() {
		return false;
	}

	@Override
	public boolean isEscapedModel() {
		return false;
	}

	@Override
	public boolean isExpired() {
		return false;
	}

	@Override
	public boolean isInheritable() {
		return false;
	}

	@Override
	public boolean isNeverExpires() {
		return true;
	}

	@Override
	public boolean isNew() {
		return false;
	}

	@Override
	public void persist() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void resetOriginalValues() {
	}

	public void save() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
	}

	@Override
	public void setClassName(String className) {
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setEscapedModel(boolean escapedModel) {
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
	}

	@Override
	public void setExpirationDate(Date expirationDate) {
	}

	@Override
	public void setInheritable(boolean inheritable) {
	}

	@Override
	public void setKey(String key) {
	}

	@Override
	public void setLockId(long lockId) {
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
	}

	@Override
	public void setNew(boolean n) {
	}

	@Override
	public void setOwner(String owner) {
	}

	@Override
	public void setPrimaryKey(long pk) {
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public void setUuid(String uuid) {
	}

	@Override
	public CacheModel<Lock> toCacheModel() {
		return this;
	}

	@Override
	public Lock toEntityModel() {
		return this;
	}

	@Override
	public Lock toEscapedModel() {
		return this;
	}

	@Override
	public Lock toUnescapedModel() {
		return this;
	}

	@Override
	public String toXmlString() {
		return StringPool.BLANK;
	}

	private long _companyId;
	private Date _createDate;
	private long _userId;
	private String _userName;

}