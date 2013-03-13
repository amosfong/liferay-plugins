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

import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Michael C. Han
 */
public class ConditionImpl implements Condition {

	public static ConditionImpl EQUALS(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.EQUALS);
	}

	public static ConditionImpl EXCLUDES(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.EXCLUDES);
	}

	public static ConditionImpl GREATER_THAN(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.GREATER_THAN);
	}

	public static ConditionImpl GREATER_THAN_EQUAL(String name, Object value) {
		return new ConditionImpl(
			name, value, ComparisonOperator.GREATER_THAN_EQUAL);
	}

	public static ConditionImpl IN(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.IN);
	}

	public static ConditionImpl INCLUDES(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.INCLUDES);
	}

	public static ConditionImpl JOIN(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.JOIN);
	}

	public static ConditionImpl LESS_THAN(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.LESS_THAN);
	}

	public static ConditionImpl LESS_THAN_EQUAL(String name, Object value) {
		return new ConditionImpl(
			name, value, ComparisonOperator.LESS_THAN_EQUAL);
	}

	public static ConditionImpl LIKE(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.LIKE);
	}

	public static ConditionImpl NOT_EQUALS(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.NOT_EQUALS);
	}

	public static ConditionImpl NOT_IN(String name, Object value) {
		return new ConditionImpl(name, value, ComparisonOperator.NOT_IN);
	}

	public boolean isGroup() {
		return _GROUP;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append(_name);
		sb.append(_comparisonOperator);

		if ((_value.getClass() == String.class) &&
			!_comparisonOperator.equals(ComparisonOperator.JOIN) &&
			!_comparisonOperator.equals(ComparisonOperator.IN)) {

			sb.append("'");
		}
		else if (_comparisonOperator.equals(ComparisonOperator.IN)) {
			sb.append("(");
		}

		sb.append(_value);

		if ((_value.getClass() == String.class) &&
			!_comparisonOperator.equals(ComparisonOperator.JOIN) &&
			!_comparisonOperator.equals(ComparisonOperator.IN)) {

			sb.append("'");
		}
		else if (_comparisonOperator.equals(ComparisonOperator.IN)) {
			sb.append(")");
		}

		return sb.toString();
	}

	private ConditionImpl(
		String name, Object value, ComparisonOperator comparisonOperator) {

		_name = name;
		_value = value;
		_comparisonOperator = comparisonOperator;
	}

	private static final boolean _GROUP = false;

	private ComparisonOperator _comparisonOperator;
	private String _name;
	private Object _value;

}