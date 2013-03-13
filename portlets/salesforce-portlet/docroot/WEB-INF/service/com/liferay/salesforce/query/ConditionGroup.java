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
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class ConditionGroup implements Condition {

	public static ConditionGroup begin(Condition condition) {
		return new ConditionGroup(condition);
	}

	public ConditionGroup and(Condition condition) {
		_conditions.add(LogicalOperator.AND);
		_conditions.add(condition);

		return this;
	}

	public boolean isGroup() {
		return _GROUP;
	}

	public ConditionGroup not(Condition condition) {
		_conditions.add(LogicalOperator.NOT);
		_conditions.add(condition);

		return this;
	}

	public ConditionGroup or(Condition condition) {
		_conditions.add(LogicalOperator.OR);
		_conditions.add(condition);

		return this;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler();

		for (int i = 0; i < _conditions.size(); i++) {
			Condition condition = _conditions.get(i);

			if (condition.isGroup()) {
				sb.append(StringPool.OPEN_PARENTHESIS);
			}

			sb.append(condition);

			if (condition.isGroup()) {
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			if ((i + 1) != _conditions.size()) {
				sb.append(StringPool.SPACE);
			}
		}

		return sb.toString();
	}

	private ConditionGroup(Condition condition) {
		_conditions.add(condition);
	}

	private static final boolean _GROUP = true;

	private List<Condition> _conditions = new ArrayList<Condition>();

}