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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.net.URL;

import java.util.Date;
import java.util.Set;

/**
 * @author Ivan Zaera
 */
public class SharepointObject {

	public SharepointObject(
		String author, String checkedOutBy, Date createdDate, boolean folder,
		Date lastModifiedDate, String path, Set<Permission> permissions,
		long sharepointObjectId, long size, URL url) {

		_author = author;
		_checkedOutBy = checkedOutBy;
		_createdDate = createdDate;
		_folder = folder;
		_lastModifiedDate = lastModifiedDate;
		_path = path;
		_permissions = permissions;
		_sharepointObjectId = sharepointObjectId;
		_size = size;
		_url = url;

		_extension = _getExtension(path);
		_folderPath = _getFolderPath(path);
		_name = _getName(path);
	}

	public String getAuthor() {
		return _author;
	}

	public String getCheckedOutBy() {
		return _checkedOutBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public String getExtension() {
		return _extension;
	}

	public String getFolderPath() {
		return _folderPath;
	}

	public long getId() {
		return _sharepointObjectId;
	}

	public Date getLastModifiedDate() {
		return _lastModifiedDate;
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
		return _folder;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{_author=");
		sb.append(_author);
		sb.append(", checkedOutBy=");
		sb.append(_checkedOutBy);
		sb.append(", createdDate=");
		sb.append(_createdDate);
		sb.append(", extension=");
		sb.append(_extension);
		sb.append(", folderPath=");
		sb.append(_folderPath);
		sb.append(", folder=");
		sb.append(_folder);
		sb.append(", lastModifiedDate=");
		sb.append(_lastModifiedDate);
		sb.append(", name=");
		sb.append(_name);
		sb.append(", path=");
		sb.append(_path);
		sb.append(", permissions=");
		sb.append(_permissions);
		sb.append(", sharepointObjectId=");
		sb.append(_sharepointObjectId);
		sb.append(", size=");
		sb.append(_size);
		sb.append(", url=");
		sb.append(_url);
		sb.append("}");

		return sb.toString();
	}

	public enum Permission {

		VIEW_LIST_ITEMS(0x0000000000000001L),
		ADD_LIST_ITEMS(0x0000000000000002L),
		EDIT_LIST_ITEMS(0x0000000000000004L),
		DELETE_LIST_ITEMS(0x0000000000000008L),
		APPROVE_ITEMS(0x0000000000000010L), OPEN_ITEMS(0x0000000000000020L),
		VIEW_VERSIONS(0x0000000000000040L),
		DELETE_VERSIONS(0x0000000000000080L),
		CANCEL_CHECKOUT(0x0000000000000100L),
		MANAGE_PERSONAL_VIEWS(0x0000000000000200L),
		MANAGE_LISTS(0x0000000000000800L), VIEW_FORM_PAGES(0x0000000000001000L),
		OPEN(0x0000000000010000L), VIEW_PAGES(0x0000000000020000L),
		ADD_AND_CUSTOMIZE_PAGES(0x0000000000040000L),
		APPLY_THEME_AND_BORDER(0x0000000000080000L),
		APPLY_STYLE_SHEETS(0x0000000000100000L),
		VIEW_USAGE_DATA(0x0000000000200000L),
		CREATE_SSC_SITE(0x0000000000400000L),
		MANAGE_SUBWEBS(0x0000000000800000L), CREATE_GROUPS(0x0000000001000000L),
		MANAGE_PERMISSIONS(0x0000000002000000L),
		BROWSE_DIRECTORIES(0x0000000004000000L),
		BROWSE_USER_INFO(0x0000000008000000L),
		ADD_DEL_PRIVATE_WEB_PARTS(0x0000000010000000L),
		UPDATE_PERSONAL_WEB_PARTS(0x0000000020000000L),
		MANAGE_WEB(0x0000000040000000L),
		USE_CLIENT_INTEGRATION(0x0000001000000000L),
		USE_REMOTE_APIS(0x0000002000000000L),
		MANAGE_ALERTS(0x0000004000000000L), CREATE_ALERTS(0x0000008000000000L),
		EDIT_MY_USER_INFO(0x0000010000000000L),
		ENUMERATE_PERMISSIONS(0x4000000000000000L);

		public long getMask() {
			return _mask;
		}

		private Permission(long mask) {
			_mask = mask;
		}

		private final long _mask;

	}

	private String _getExtension(String path) {
		int i = path.lastIndexOf(StringPool.PERIOD);

		if (i == -1) {
			return StringPool.BLANK;
		}
		else {
			return path.substring(i + 1);
		}
	}

	private String _getFolderPath(String path) {
		int i = path.lastIndexOf(StringPool.SLASH);

		if (i == 0) {
			return StringPool.SLASH;
		}
		else {
			return path.substring(0, i);
		}
	}

	private String _getName(String path) {
		if (path.equals(StringPool.SLASH)) {
			return StringPool.SLASH;
		}
		else {
			int i = path.lastIndexOf(StringPool.SLASH);

			return path.substring(i + 1);
		}
	}

	private String _author;
	private String _checkedOutBy;
	private Date _createdDate;
	private String _extension;
	private String _folderPath;
	private boolean _folder;
	private Date _lastModifiedDate;
	private String _name;
	private String _path;
	private Set<Permission> _permissions;
	private long _sharepointObjectId;
	private long _size;
	private URL _url;

}