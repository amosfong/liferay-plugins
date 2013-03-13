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

package com.liferay.salesforce.query.builder;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.salesforce.query.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class SelectQuery {

	public static String build(
		List<String> objectNames, List<String> fieldNames, Condition condition,
		List<String> orderByFieldNames) {

		StringBundler sb = new StringBundler(8);

		sb.append(_SELECT);
		sb.append(StringUtil.merge(fieldNames));
		sb.append(_FROM);
		sb.append(StringUtil.merge(objectNames));

		if (condition != null) {
			sb.append(_WHERE);
			sb.append(condition.toString());
		}

		if ((orderByFieldNames != null) && !orderByFieldNames.isEmpty()) {
			sb.append(_ORDER_BY);
			sb.append(StringUtil.merge(orderByFieldNames));
		}

		return sb.toString();
	}

	public static String build(String objectName, List<String> fieldNames) {
		List<String> objectNames = new ArrayList<String>();

		objectNames.add(objectName);

		return build(objectNames, fieldNames, null, null);
	}

	public static String build(
		String objectName, List<String> fieldNames, Condition condition) {

		List<String> objectNames = new ArrayList<String>();

		objectNames.add(objectName);

		return build(objectNames, fieldNames, condition, null);
	}

	public static String build(
		String objectName, List<String> fieldNames, Condition condition,
		List<String> orderByFieldNames) {

		List<String> objectNames = new ArrayList<String>();

		objectNames.add(objectName);

		return build(objectNames, fieldNames, condition, orderByFieldNames);
	}

	private static final String _FROM = " FROM ";

	private static final String _ORDER_BY = " ORDER BY ";

	private static final String _SELECT = "SELECT ";

	private static final String _WHERE = " WHERE ";

}