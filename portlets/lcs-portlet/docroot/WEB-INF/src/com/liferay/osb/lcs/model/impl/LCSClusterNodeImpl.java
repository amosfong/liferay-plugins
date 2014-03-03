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
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeImpl implements LCSClusterNode {

	@Override
	public Object clone() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int compareTo(LCSClusterNode lcsClusterNode) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> getAvailablePatchNames() {
		return _availablePatchNames;
	}

	@Override
	public int getBuildNumber() {
		return _buildNumber;
	}

	@Override
	public Date getConfigurationModifiedDate() {
		return _configurationModifiedDate;
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
	public String getKey() {
		return _key;
	}

	@Override
	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	@Override
	public long getLcsClusterNodeId() {
		return _lcsClusterNodeId;
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
	public int getMonitoringStatus() {
		return _monitoringStatus;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public Map<String, Integer> getPatchIdentifiers() {
		return _patchIdentifiers;
	}

	@Override
	public int getPatchingToolStatus() {
		return _patchingToolStatus;
	}

	@Override
	public int getPatchingToolVersion() {
		return _patchingToolVersion;
	}

	@Override
	public String getPortalEdition() {
		return _portalEdition;
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
	public int getStatus() {
		return _status;
	}

	@Override
	public boolean isCachedModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEscapedModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isMonitoringUnavailable() {
		if (getMonitoringStatus() == LCSConstants.MONITORING_UNAVAILABLE) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isNew() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOffline() {
		if (getStatus() == LCSConstants.SERVER_OFFLINE) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isPatchingToolUnavailable() {
		if (getPatchingToolStatus() == LCSConstants.PATCHING_TOOL_UNAVAILABLE) {
			return true;
		}

		return false;
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
	public void setAvailablePatchNames(List<String> availablePatchNames) {
		_availablePatchNames = availablePatchNames;
	}

	@Override
	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setConfigurationModifiedDate(Date configurationModifiedDate) {
		_configurationModifiedDate = configurationModifiedDate;
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
	public void setKey(String key) {
		_key = key;
	}

	@Override
	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	@Override
	public void setLcsClusterNodeId(long lcsClusterNodeId) {
		_lcsClusterNodeId = lcsClusterNodeId;
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
	public void setMonitoringStatus(int monitoringStatus) {
		_monitoringStatus = monitoringStatus;
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
	public void setPatchIdentifiers(Map<String, Integer> patchIdentifiers) {
		_patchIdentifiers = patchIdentifiers;
	}

	@Override
	public void setPatchingToolStatus(int patchingToolStatus) {
		_patchingToolStatus = patchingToolStatus;
	}

	@Override
	public void setPatchingToolVersion(int patchingToolVersion) {
		_patchingToolVersion = patchingToolVersion;
	}

	@Override
	public void setPortalEdition(String portalEdition) {
		_portalEdition = portalEdition;
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
	public void setStatus(int status) {
		_status = status;
	}

	@Override
	public CacheModel<LCSClusterNode> toCacheModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public LCSClusterNode toEscapedModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public LCSClusterNode toUnescapedModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toXmlString() {
		throw new UnsupportedOperationException();
	}

	private List<String> _availablePatchNames = new ArrayList<String>();
	private int _buildNumber;
	private Date _configurationModifiedDate;
	private String _description;
	private String _key;
	private long _lcsClusterEntryId;
	private long _lcsClusterNodeId;
	private String _location;
	private int _monitoringStatus;
	private String _name;
	private Map<String, Integer> _patchIdentifiers = new HashMap<String, Integer>();
	private int _patchingToolStatus;
	private int _patchingToolVersion;
	private String _portalEdition;
	private int _status;

}