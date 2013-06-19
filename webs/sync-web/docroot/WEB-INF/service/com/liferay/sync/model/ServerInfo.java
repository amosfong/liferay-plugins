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
public class ServerInfo {

	public int getPortalBuildNumber() {
		return _portalBuildNumber;
	}

	public long getUserId() {
		return _userId;
	}

	public List<Group> getUserSites() {
		return _userSites;
	}

	public void setPortalBuildNumber(int portalBuildNumber) {
		_portalBuildNumber = portalBuildNumber;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUserSites(List<Group> userSites) {
		_userSites = userSites;
	}

	private int _portalBuildNumber;
	private long _userId;
	private List<Group> _userSites;

}