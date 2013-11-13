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

package com.liferay.osb.lcs.model.impl;

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class LCSClusterEntryImpl implements LCSClusterEntry {

	@Override
	public Object clone() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int compareTo(LCSClusterEntry lcsClusterEntry) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getCorpEntryId() {
		return _corpEntryId;
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	@Override
	public String getLocation() {
		return _location;
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Class<?> getModelClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getModelClassName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public long getPrimaryKey() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public boolean isCachedModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCluster() {
		if (getType() == LCSConstants.LCS_CLUSTER_ENTRY_TYPE_CLUSTER) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isEnvironment() {
		if (getType() == LCSConstants.LCS_CLUSTER_ENTRY_TYPE_ENVIRONMENT) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isEscapedModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isNew() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void persist() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void resetOriginalValues() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCorpEntryId(long corpEntryId) {
		_corpEntryId = corpEntryId;
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	@Override
	public void setLocation(String location) {
		_location = location;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setNew(boolean n) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setType(int type) {
		_type = type;
	}

	@Override
	public CacheModel<LCSClusterEntry> toCacheModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public LCSClusterEntry toEscapedModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public LCSClusterEntry toUnescapedModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toXmlString() {
		throw new UnsupportedOperationException();
	}

	private long _corpEntryId;
	private String _description;
	private long _lcsClusterEntryId;
	private String _location;
	private String _name;
	private int _type;

}