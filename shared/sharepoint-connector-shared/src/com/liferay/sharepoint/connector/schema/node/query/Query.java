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

import com.liferay.sharepoint.connector.schema.marker.Clause;
import com.liferay.sharepoint.connector.schema.node.BaseNode;

/**
 * @author Iván Zaera
 */
public class Query extends BaseNode {

	public Query(Clause clause) {
		_clause = clause;
	}

	private Clause _clause;

}