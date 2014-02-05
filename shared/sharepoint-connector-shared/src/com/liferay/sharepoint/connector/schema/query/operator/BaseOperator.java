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

package com.liferay.sharepoint.connector.schema.query.operator;

import com.liferay.portal.kernel.xml.simple.Element;
import com.liferay.sharepoint.connector.schema.BaseNode;
import com.liferay.sharepoint.connector.schema.query.QueryClause;
import com.liferay.sharepoint.connector.schema.query.QueryFieldRef;

/**
 * @author Iván Zaera
 */
public abstract class BaseOperator extends BaseNode implements QueryClause {

	public BaseOperator(QueryFieldRef queryFieldRef) {
		_queryFieldRef = queryFieldRef;
	}

	@Override
	protected void addAttributesAndChildren(Element element) {
		_queryFieldRef.addTo(element);
	}

	private QueryFieldRef _queryFieldRef;

}