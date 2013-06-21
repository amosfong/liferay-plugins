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

import com.liferay.portal.kernel.test.TestCase;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.salesforce.query.ConditionGroup;
import com.liferay.salesforce.query.ConditionImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class SelectQueryTest extends TestCase {

	@Test
	public void testBuild() {
		List<String> fieldNames = new ArrayList<String>();

		fieldNames.add("AccountNumber");
		fieldNames.add("BillingStreet");
		fieldNames.add("BillingCity");

		assertEquals(
			"SELECT AccountNumber,BillingStreet,BillingCity FROM Account",
			SelectQuery.build("Account", fieldNames));

		ConditionGroup condition = ConditionGroup.begin(
			ConditionImpl.EQUALS("AccountNumber", 12345));

		ConditionGroup conditionGroup = ConditionGroup.begin(
			ConditionImpl.LIKE("BillingStreet", "123 Test%"));

		conditionGroup.or(ConditionImpl.EQUALS("BillingCity", "Los Angeles"));

		condition.and(conditionGroup);

		assertEquals(
			"SELECT AccountNumber,BillingStreet,BillingCity FROM Account " +
				"WHERE AccountNumber=12345 AND (BillingStreet LIKE '123 " +
					"Test%' OR BillingCity='Los Angeles')",
			SelectQuery.build("Account", fieldNames, condition));

		List<String> orderByFieldNames = new ArrayList<String>();

		orderByFieldNames.add("AccountNumber");
		orderByFieldNames.add("PostalCode");

		StringBundler sb = new StringBundler(4);

		sb.append("SELECT AccountNumber,BillingStreet,BillingCity FROM ");
		sb.append("Account WHERE AccountNumber=12345 AND (BillingStreet LIKE ");
		sb.append("'123 Test%' OR BillingCity='Los Angeles') ORDER BY ");
		sb.append("AccountNumber,PostalCode");

		assertEquals(
			sb.toString(),
			SelectQuery.build(
				"Account", fieldNames, condition, orderByFieldNames));
	}

}