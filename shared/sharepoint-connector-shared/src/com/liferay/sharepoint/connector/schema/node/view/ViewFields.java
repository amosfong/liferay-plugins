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

package com.liferay.sharepoint.connector.schema.node.view;

import com.liferay.sharepoint.connector.schema.node.BaseNode;

/**
 * @author Iván Zaera
 */
public class ViewFields extends BaseNode {

	public ViewFields(FieldRef...fieldRefs) {
		if (fieldRefs == null) {
			_fieldRefs = _EMPTY_FIELD_REFS;
		}
		else {
			_fieldRefs = fieldRefs;
		}
	}

	private static final FieldRef[] _EMPTY_FIELD_REFS = new FieldRef[]{};

	private final FieldRef[] _fieldRefs;

}