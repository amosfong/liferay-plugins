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

import com.liferay.sharepoint.connector.schema.marker.QueryOption;
import com.liferay.sharepoint.connector.schema.node.option.Folder;
import com.liferay.sharepoint.connector.schema.node.option.IncludeMandatoryColumns;
import com.liferay.sharepoint.connector.schema.node.option.QueryOptions;
import com.liferay.sharepoint.connector.schema.node.option.ViewAttributes;

/**
 * @author Iván Zaera
 */
public class QueryOptionsBuilder {

	public Folder folder(String path) {
		return new Folder(path);
	}

	public IncludeMandatoryColumns includeMandatoryColumns(boolean value) {
		return new IncludeMandatoryColumns(value);
	}

	public QueryOptions queryOptions(QueryOption... queryOptions) {
		return new QueryOptions(queryOptions);
	}

	public ViewAttributes viewAttributes(boolean recursive) {
		return new ViewAttributes(recursive);
	}

}