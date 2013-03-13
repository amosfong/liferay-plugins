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

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Mika Koivisto
 */
public class DQLDateExpression extends DQLSimpleExpression {

	public DQLDateExpression(
		String field, String value,
		DQLSimpleExpressionOperator dqlSimpleExpressionOperator) {

		super(field, value, dqlSimpleExpressionOperator);
	}

	@Override
	public String toQueryFragment() {
		StringBundler sb = new StringBundler(9);

		sb.append(_field);
		sb.append(StringPool.SPACE);
		sb.append(_dqlSimpleExpressionOperator);
		sb.append(StringPool.SPACE);
		sb.append("DATE('");
		sb.append(_value);
		sb.append("', '");
		sb.append(_INDEX_DATE_FORMAT_PATTERN);
		sb.append("')");

		return sb.toString();
	}

	private static final String _INDEX_DATE_FORMAT_PATTERN = PropsUtil.get(
		PropsKeys.INDEX_DATE_FORMAT_PATTERN);

	private DQLSimpleExpressionOperator _dqlSimpleExpressionOperator;
	private String _field;
	private String _value;

}