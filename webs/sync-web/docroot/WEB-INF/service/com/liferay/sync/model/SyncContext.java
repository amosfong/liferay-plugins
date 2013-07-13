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

package com.liferay.sync.model;

import com.liferay.portal.model.Group;

import java.util.List;

/**
 * @author Dennis Ju
 */
public class SyncContext {

	public String getPluginVersion() {
		return _pluginVersion;
	}

	public int getPortalBuildNumber() {
		return _portalBuildNumber;
	}

	public String getPortalEELicenseDigest() {
		return _portalEELicenseDigest;
	}

	public String getSocialOfficeEELicenseDigest() {
		return _socialOfficeEELicenseDigest;
	}

	public long getUserId() {
		return _userId;
	}

	public List<Group> getUserSites() {
		return _userSites;
	}

	public boolean isSocialOfficeInstalled() {
		return _socialOfficeInstalled;
	}

	public void setPluginVersion(String pluginVersion) {
		_pluginVersion = pluginVersion;
	}

	public void setPortalBuildNumber(int portalBuildNumber) {
		_portalBuildNumber = portalBuildNumber;
	}

	public void setPortalEELicenseDigest(String portalEELicenseDigest) {
		_portalEELicenseDigest = portalEELicenseDigest;
	}

	public void setSocialOfficeEELicenseDigest(
		String socialOfficeEELicenseDigest) {

		_socialOfficeEELicenseDigest = socialOfficeEELicenseDigest;
	}

	public void setSocialOfficeInstalled(boolean socialOfficeInstalled) {
		_socialOfficeInstalled = socialOfficeInstalled;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUserSites(List<Group> userSites) {
		_userSites = userSites;
	}

	private String _pluginVersion;
	private int _portalBuildNumber;
	private String _portalEELicenseDigest;
	private String _socialOfficeEELicenseDigest;
	private boolean _socialOfficeInstalled;
	private long _userId;
	private List<Group> _userSites;

}