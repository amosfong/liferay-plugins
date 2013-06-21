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

import com.liferay.portal.kernel.test.TestCase;

import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class ConditionGroupTest extends TestCase {

	@Test
	public void testAnd() {
		Condition condition = ConditionImpl.EQUALS("Name", "Test");

		assertEquals("Name='Test'", condition.toString());

		ConditionGroup conditionGroup1 = ConditionGroup.begin(condition);

		assertEquals("Name='Test'", conditionGroup1.toString());

		conditionGroup1.and(ConditionImpl.EQUALS("Name1", "Value1"));

		assertEquals(
			"Name='Test' AND Name1='Value1'", conditionGroup1.toString());

		ConditionGroup conditionGroup2 = ConditionGroup.begin(conditionGroup1);

		conditionGroup2.and(conditionGroup1);

		assertEquals(
			"(Name='Test' AND Name1='Value1') AND (Name='Test' AND " +
				"Name1='Value1')",
			conditionGroup2.toString());
	}

	@Test
	public void testOr() {
		Condition condition = ConditionImpl.EQUALS("Name", "Test");

		ConditionGroup conditionGroup1 = ConditionGroup.begin(condition);

		conditionGroup1.or(ConditionImpl.EQUALS("Name1", "Value1"));

		assertEquals(
			"Name='Test' OR Name1='Value1'", conditionGroup1.toString());

		ConditionGroup conditionGroup2 = ConditionGroup.begin(conditionGroup1);

		conditionGroup2.or(conditionGroup1);

		assertEquals(
			"(Name='Test' OR Name1='Value1') OR (Name='Test' OR " +
				"Name1='Value1')",
			conditionGroup2.toString());
	}

}