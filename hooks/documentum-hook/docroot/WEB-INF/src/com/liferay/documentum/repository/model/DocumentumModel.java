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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mika Koivisto
 */
public abstract class DocumentumModel {

	@Override
	public Object clone() {
		return this;
	}

	public boolean containsPermission(int permit, String actionId) {
		if (_unsupportedActionKeys.contains(actionId)) {
			return false;
		}

		if (ActionKeys.ACCESS.equals(actionId)) {
			return permit >= Constants.DF_PERMIT_BROWSE;
		}
		else if (ActionKeys.ADD_DOCUMENT.equals(actionId)) {
			return permit >= Constants.DF_PERMIT_WRITE;
		}
		else if (ActionKeys.ADD_FOLDER.equals(actionId)) {
			return permit >= Constants.DF_PERMIT_WRITE;
		}
		else if (ActionKeys.ADD_SUBFOLDER.equals(actionId)) {
			return permit >= Constants.DF_PERMIT_WRITE;
		}
		else if (ActionKeys.DELETE.equals(actionId)) {
			return permit >= Constants.DF_PERMIT_DELETE;
		}
		else if (ActionKeys.VIEW.equals(actionId)) {
			return permit >= Constants.DF_PERMIT_READ;
		}
		else if (ActionKeys.UPDATE.equals(actionId)) {
			return permit >= Constants.DF_PERMIT_WRITE;
		}

		return false;
	}

	public abstract long getCompanyId();

	public String getDescription() {
		return StringPool.BLANK;
	}

	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), getModelClassName(), getPrimaryKey());
	}

	public abstract Class<?> getModelClass();

	public String getModelClassName() {
		Class<?> clazz = getModelClass();

		return clazz.getName();
	}

	public long getParentFolderId() {
		return _parentFolderId;
	}

	public abstract long getPrimaryKey();

	public Serializable getPrimaryKeyObj() {
		return getPrimaryKey();
	}

	public void setCompanyId(long companyId) {
	}

	public void setCreateDate(Date date) {
	}

	public void setGroupId(long groupId) {
	}

	public void setModifiedDate(Date modifiedDate) {
	}

	public void setParentFolder(Folder parentFolder) {
		_parentFolder = parentFolder;
	}

	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
	}

	public void setUserId(long userId) {
	}

	public void setUserName(String userName) {
	}

	public void setUuid(String uuid) {
	}

	@SuppressWarnings("unused")
	protected Folder getParentFolder() throws PortalException, SystemException {
		return _parentFolder;
	}

	protected User getUser(String creatorName) {
		User user = null;

		try {
			Company company = CompanyLocalServiceUtil.getCompany(
				getCompanyId());

			String authType = company.getAuthType();

			if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				long userId = GetterUtil.getLong(creatorName);

				user = UserLocalServiceUtil.getUser(userId);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				user = UserLocalServiceUtil.getUserByEmailAddress(
					getCompanyId(), creatorName);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				user = UserLocalServiceUtil.getUserByScreenName(
					getCompanyId(), creatorName);
			}
		}
		catch (Exception e) {
		}

		if (user == null) {
			try {
				user = UserLocalServiceUtil.getDefaultUser(getCompanyId());
			}
			catch (Exception e) {
			}
		}

		return user;
	}

	private static Set<String> _unsupportedActionKeys = new HashSet<String>();

	private Folder _parentFolder;
	private long _parentFolderId;

	static {
		_unsupportedActionKeys.add(ActionKeys.ADD_DISCUSSION);
		_unsupportedActionKeys.add(ActionKeys.ADD_SHORTCUT);
		_unsupportedActionKeys.add(ActionKeys.DELETE_DISCUSSION);
		_unsupportedActionKeys.add(ActionKeys.PERMISSIONS);
		_unsupportedActionKeys.add(ActionKeys.UPDATE_DISCUSSION);
	}

}