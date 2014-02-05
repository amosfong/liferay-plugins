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

/**
 * @author Ivan Zaera
 */
public class SharepointVersion {

	public SharepointVersion(
		String id, URL url, String version, String comments, long size,
		Date createdDate, String createdBy) {

		_id = id;
		_url = url;
		_version = version;
		_comments = comments;
		_size = size;
		_createdDate = createdDate;
		_createdBy = createdBy;
	}

	public String getComments() {
		return _comments;
	}

	public String getCreatedBy() {
		return _createdBy;
	}

	public Date getCreatedDate() {
		return _createdDate;
	}

	public String getId() {
		return _id;
	}

	public long getSize() {
		return _size;
	}

	public URL getURL() {
		return _url;
	}

	public String getVersion() {
		return _version;
	}

	@Override
	public String toString() {
		return "version:" + _id;
	}

	private final String _comments;
	private final String _createdBy;
	private final Date _createdDate;
	private final String _id;
	private final long _size;
	private final URL _url;
	private final String _version;

}