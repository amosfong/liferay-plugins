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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Michael C. Han
 */
public class SearchConditionImpl implements Condition {

	public static Condition QUOTED_VALUE(String value) {
		return new SearchConditionImpl(
			StringUtil.quote(value, StringPool.QUOTE));
	}

	public static Condition VALUE(String value) {
		return new SearchConditionImpl(value);
	}

	public SearchConditionImpl(String value) {
		_value = value;
	}

	public boolean isGroup() {
		return _GROUP;
	}

	@Override
	public String toString() {
		return _value;
	}

	private static final boolean _GROUP = false;

	private String _value;

}