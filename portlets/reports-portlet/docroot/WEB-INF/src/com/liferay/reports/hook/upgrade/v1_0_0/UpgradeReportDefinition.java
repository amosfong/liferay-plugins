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

package com.liferay.reports.hook.upgrade.v1_0_0;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.reports.model.Definition;
import com.liferay.reports.service.DefinitionLocalServiceUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wesley Gong
 * @author Calvin Keum
 */
public class UpgradeReportDefinition extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("Reports_Definition")) {
			updateReportDefinitions();
		}
	}

	protected void updateReportDefinitions() throws Exception {
		List<Definition> definitions =
			DefinitionLocalServiceUtil.getDefinitions(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Definition definition : definitions) {
			String reportParameters = definition.getReportParameters();

			Matcher matcher = _pattern.matcher(reportParameters);

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

				reportParameterJSONObject.put(
					"key", keyValuePair.split(StringPool.EQUAL)[0]);
				reportParameterJSONObject.put(
					"type", keyValuePair.split(StringPool.EQUAL)[2]);
				reportParameterJSONObject.put(
					"value", keyValuePair.split(StringPool.EQUAL)[1]);

				reportParametersJSONArray.put(reportParameterJSONObject);
			}

			definition.setReportParameters(
				reportParametersJSONArray.toString());

			DefinitionLocalServiceUtil.updateDefinition(definition);
		}
	}

	private static Pattern _pattern = Pattern.compile("[.*=.*=.*]+");

}