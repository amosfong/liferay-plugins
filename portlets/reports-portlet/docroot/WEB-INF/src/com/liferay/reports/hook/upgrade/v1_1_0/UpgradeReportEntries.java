/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.reports.hook.upgrade.v1_1_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wesley Gong
 * @author Calvin Keum
 */
public class UpgradeReportEntries extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("reports_entry")) {
			updateReportEntries();
		}
	}

	protected void updateReportEntries() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement("select * from reports_entry");

			rs = ps.executeQuery();

			while (rs.next()) {
				long entryId = rs.getLong("entryId");
				String reportParameters = rs.getString("reportParameters");

				Matcher matcher = _ENTRY_REPORT_PARAMETER_PATTERN.matcher(
					reportParameters);

				if (!matcher.find()) {
					continue;
				}

				JSONArray reportParametersJSONArray =
					JSONFactoryUtil.createJSONArray();

				String[] keyValuePairs = StringUtil.split(reportParameters);

				for (String keyValuePair : keyValuePairs) {
					if (Validator.isNull(keyValuePair) ||
						!keyValuePair.contains(StringPool.EQUAL)) {

						continue;
					}

					JSONObject reportParameterJSONObject =
						JSONFactoryUtil.createJSONObject();

					reportParameterJSONObject.put("key", keyValuePair.split(
						StringPool.EQUAL)[0]);
					reportParameterJSONObject.put("value", keyValuePair.split(
						StringPool.EQUAL)[1]);

					reportParametersJSONArray.put(reportParameterJSONObject);
				}

				StringBundler sb = new StringBundler(4);

				sb.append("update reports_entry set reportParameters = '");
				sb.append(reportParametersJSONArray.toString());
				sb.append("' where entryId = ");
				sb.append(entryId);

				runSQL(sb.toString());
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final Pattern _ENTRY_REPORT_PARAMETER_PATTERN =
		Pattern.compile("[.*=.*]+");

}