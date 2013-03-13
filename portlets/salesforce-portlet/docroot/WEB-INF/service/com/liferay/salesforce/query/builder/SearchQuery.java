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

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.salesforce.query.Condition;
import com.liferay.salesforce.query.SearchGroup;
import com.liferay.salesforce.query.SearchReturnSpecification;

import java.util.List;

/**
 * @author Michael C. Han
 */
public class SearchQuery {

	public static String build(
		Condition condition,
		List<SearchReturnSpecification> searchReturnSpecifications) {

		return build(condition, null, searchReturnSpecifications);
	}

	public static String build(
		Condition condition, SearchGroup searchGroup,
		List<SearchReturnSpecification> searchReturnSpecifications) {

		StringBundler sb = new StringBundler(7);

		sb.append("FIND {");
		sb.append(condition.toString());
		sb.append("}");

		if (searchGroup != null) {
			sb.append(" IN ");
			sb.append(searchGroup.toString());
		}

		sb.append(" RETURNING ");
		sb.append(StringUtil.merge(searchReturnSpecifications));

		return sb.toString();
	}

	public static String build(
		Condition searchCondition,
		SearchReturnSpecification... searchReturnSpecifications) {

		List<SearchReturnSpecification> searchReturnSpecificationsList =
			ListUtil.fromArray(searchReturnSpecifications);

		return build(searchCondition, null, searchReturnSpecificationsList);
	}

}