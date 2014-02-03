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

package com.liferay.sharepoint.connector.schema;

import com.liferay.sharepoint.connector.schema.node.query.QueryFieldRef;
import com.liferay.sharepoint.connector.schema.node.query.ViewFields;

/**
 * @author Iván Zaera
 */
public class ViewFieldsBuilder {

	public QueryFieldRef fieldRef(String fieldName) {
		return new QueryFieldRef(fieldName);
	}

	public ViewFields viewFields(QueryFieldRef... fieldRefs) {
		return new ViewFields(fieldRefs);
	}

}