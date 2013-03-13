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

package com.liferay.portal.audit;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Mika Koivisto
 */
public class CSVLogMessageFormatter implements LogMessageFormatter {

	public String format(AuditMessage auditMessage) {
		StringBundler sb = new StringBundler(_columns.length * 4 - 1);

		JSONObject jsonObject = auditMessage.toJSONObject();

		for (int i = 0; i < _columns.length; i++) {
			sb.append(StringPool.QUOTE);
			sb.append(jsonObject.getString(_columns[i]));
			sb.append(StringPool.QUOTE);

			if ((i + 1) < _columns.length) {
				sb.append(StringPool.COMMA);
			}
		}

		return sb.toString();
	}

	public void setColumns(String columns) {
		_columns = StringUtil.split(columns);
	}

	private String[] _columns;

}