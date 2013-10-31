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

package com.liferay.documentum.repository.search;

import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Mika Koivisto
 */
public class DQLInFolderExpression implements DQLCriterion {

	public DQLInFolderExpression(String objectId, boolean decend) {
		_objectId = objectId;
		_decend = decend;
	}

	public String toQueryFragment() {
		StringBundler sb = new StringBundler(5);

		sb.append("FOLDER(ID('");
		sb.append(_objectId);
		sb.append("')");

		if (_decend) {
			sb.append(", DECEND");
		}

		sb.append(")");

		return sb.toString();
	}

	private boolean _decend;
	private String _objectId;

}