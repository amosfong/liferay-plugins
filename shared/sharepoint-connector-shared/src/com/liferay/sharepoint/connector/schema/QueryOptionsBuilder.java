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
import com.liferay.sharepoint.connector.schema.node.option.FolderQueryOption;
import com.liferay.sharepoint.connector.schema.node.option.IncludeMandatoryColumnsQueryOption;
import com.liferay.sharepoint.connector.schema.node.option.QueryOptionsNode;
import com.liferay.sharepoint.connector.schema.node.option.ViewAttributesQueryOption;

/**
 * @author Iván Zaera
 */
public class QueryOptionsBuilder {

	public FolderQueryOption folder(String path) {
		return new FolderQueryOption(path);
	}

	public IncludeMandatoryColumnsQueryOption includeMandatoryColumns(boolean value) {
		return new IncludeMandatoryColumnsQueryOption(value);
	}

	public QueryOptionsNode queryOptions(QueryOption... queryOptions) {
		return new QueryOptionsNode(queryOptions);
	}

	public ViewAttributesQueryOption viewAttributes(boolean recursive) {
		return new ViewAttributesQueryOption(recursive);
	}

}