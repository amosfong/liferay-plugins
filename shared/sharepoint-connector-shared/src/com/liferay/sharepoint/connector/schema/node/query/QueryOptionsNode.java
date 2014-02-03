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

package com.liferay.sharepoint.connector.schema.node.query;

import com.liferay.sharepoint.connector.schema.node.BaseNode;
import com.liferay.sharepoint.connector.schema.node.query.option.QueryOption;

/**
 * @author Iván Zaera
 */
public class QueryOptionsNode extends BaseNode {

	public QueryOptionsNode(QueryOption...queryOptions) {
		if (queryOptions == null) {
			_queryOptions = _QUERY_OPTIONS;
		}
		else {
			_queryOptions = queryOptions;
		}
	}

	private static final QueryOption[] _QUERY_OPTIONS = new QueryOption[0];

	private QueryOption[] _queryOptions;

}