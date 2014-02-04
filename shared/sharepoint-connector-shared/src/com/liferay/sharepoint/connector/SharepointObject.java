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

package com.liferay.sharepoint.connector;

import java.net.URL;

import java.util.Date;
import java.util.Set;

/**
 * @author Ivan Zaera
 */
public class SharepointObject {

	public SharepointObject(
		long id, URL url, String path, boolean isFolder, long size,
		Date created, Date lastModified, String author, String checkedOutBy,
		Set<Permission> permissions) {

		_id = id;
		_url = url;
		_path = path;
		_folderPath = _getFolderPath(path);
		_name = _getName(path);
		_extension = _getExtension(path);
		_isFolder = isFolder;
		_size = size;
		_created = created;
		_lastModified = lastModified;
		_author = author;
		_checkedOutBy = checkedOutBy;
		_permissions = permissions;
	}

	public String getAuthor() {
		return _author;
	}

	public String getCheckedOutBy() {
		return _checkedOutBy;
	}

	public Date getCreated() {
		return _created;
	}

	public String getExtension() {
		return _extension;
	}

	public String getFolderPath() {
		return _folderPath;
	}

	public long getId() {
		return _id;
	}

	public Date getLastModified() {
		return _lastModified;
	}

	public String getName() {
		return _name;
	}

	public String getPath() {
		return _path;
	}

	public Set<Permission> getPermissions() {
		return _permissions;
	}

	public long getSize() {
		return _size;
	}

	public URL getURL() {
		return _url;
	}

	public boolean isFile() {
		return !isFolder();
	}

	public boolean isFolder() {
		return _isFolder;
	}

	public static enum Permission {

		VIEW_LIST_ITEMS, ADD_LIST_ITEMS, EDIT_LIST_ITEMS, DELETE_LIST_ITEMS,
		APPROVE_ITEMS, VIEW_VERSIONS, DELETE_VERSIONS, CANCEL_CHECKOUT,
		MANAGE_PERSONAL_VIEWS, MANAGE_LISTS, OPEN, ADD_AND_CUSTOMIZE_PAGES,
		APPLY_THEME_AND_BORDER, APPLY_STYLE_SHEETS, VIEW_USAGE_DATA,
		CREATE_SSC_SITE, MANAGE_SUBWEBS, MANAGE_PERMISSIONS, BROWSE_DIRECTORIES,
		BROWSE_USER_INFO, ADD_DEL_PRIVATE_WEB_PARTS, UPDATE_PERSONAL_WEB_PARTS,
		MANAGE_WEB, USE_CLIENT_INTEGRATION, USE_REMOTE_APIS, CREATE_ALERTS,
		EDIT_MY_USER_INFO, ENUMERATE_PERMISSIONS;

	}

	private String _getExtension(String path) {
		return null;
	}

	private String _getFolderPath(String path) {
		return null;
	}

	private String _getName(String path) {
		return null;
	}

	private String _author;
	private String _checkedOutBy;
	private Date _created;
	private String _extension;
	private String _folderPath;
	private long _id;
	private boolean _isFolder;
	private Date _lastModified;
	private String _name;
	private String _path;
	private Set<Permission> _permissions;
	private long _size;
	private URL _url;

}