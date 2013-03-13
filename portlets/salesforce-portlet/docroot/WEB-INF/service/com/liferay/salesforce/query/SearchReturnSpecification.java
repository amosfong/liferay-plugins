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

package com.liferay.salesforce.query;

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class SearchReturnSpecification {

	public SearchReturnSpecification(String objectName) {
		_objectName = objectName;
		_fieldNames = new ArrayList<String>();
	}

	public SearchReturnSpecification(String objectName, String[] fieldNames) {
		_objectName = objectName;
		_fieldNames = ListUtil.fromArray(fieldNames);
	}

	public void addField(String fieldName) {
		_fieldNames.add(fieldName);
	}

	public void addOrderBy(String fieldName) {
		if (_orderByFieldNames == null) {
			_orderByFieldNames = new ArrayList<String>();
		}

		_orderByFieldNames.add(fieldName);
	}

	public void setCondition(Condition condition) {
		_condition = condition;
	}

	public void setLimit(int limit) {
		_limit = limit;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append(_objectName);
		sb.append("(");
		sb.append(StringUtil.merge(_fieldNames));

		if (_condition != null) {
			sb.append(_WHERE);
			sb.append(_condition);
		}

		sb.append(")");

		if ((_orderByFieldNames != null) && (_orderByFieldNames.size() > 0)) {
			sb.append(_ORDER_BY);

			sb.append(StringUtil.merge(_orderByFieldNames));
		}

		if (_limit > 0) {
			sb.append(_LIMIT);
			sb.append(_limit);
		}

		return sb.toString();
	}

	private static final String _LIMIT = " LIMIT ";

	private static final String _ORDER_BY = " ORDER BY ";

	private static final String _WHERE = " WHERE ";

	private Condition _condition;
	private List<String> _fieldNames;
	private int _limit;
	private String _objectName;
	private List<String> _orderByFieldNames;

}