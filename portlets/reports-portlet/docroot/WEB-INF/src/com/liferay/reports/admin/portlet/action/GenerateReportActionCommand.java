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

package com.liferay.reports.admin.portlet.action;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.reports.model.Definition;
import com.liferay.reports.model.Entry;
import com.liferay.reports.service.DefinitionServiceUtil;
import com.liferay.reports.service.EntryServiceUtil;
import com.liferay.reports.util.ReportsUtil;
import com.liferay.util.bridges.mvc.BaseActionCommand;

import java.text.DateFormat;

import java.util.Calendar;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class GenerateReportActionCommand extends BaseActionCommand {

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long definitionId = ParamUtil.getLong(portletRequest, "definitionId");

		String format = ParamUtil.getString(portletRequest, "format");
		String emailNotifications = ParamUtil.getString(
			portletRequest, "emailNotifications");
		String emailDelivery = ParamUtil.getString(
			portletRequest, "emailDelivery");
		String generatedReportsURL = ParamUtil.getString(
			portletRequest, "generatedReportsURL");

		JSONArray entryReportParametersJSONArray =
			JSONFactoryUtil.createJSONArray();
		JSONArray reportParametersJSONArray = JSONFactoryUtil.createJSONArray();

		Definition definition = DefinitionServiceUtil.getDefinition(
			definitionId);

		if (Validator.isNotNull(definition.getReportParameters())) {
			reportParametersJSONArray = JSONFactoryUtil.createJSONArray(
				definition.getReportParameters());
		}

		for (int i = 0; i < reportParametersJSONArray.length(); i++) {
			JSONObject definitionReportParameterJSONObject =
				reportParametersJSONArray.getJSONObject(i);

			String key = definitionReportParameterJSONObject.getString("key");

			JSONObject entryReportParameterJSONObject =
				JSONFactoryUtil.createJSONObject();

			entryReportParameterJSONObject.put("key", key);

			String value = ParamUtil.getString(
				portletRequest, "parameterValue" + key);

			String type = definitionReportParameterJSONObject.getString("type");

			if (type.equals("date")) {
				Calendar calendar = ReportsUtil.getDate(
					portletRequest, key, true);

				DateFormat dateFormat =
					DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

				value = dateFormat.format(calendar.getTime());
			}

			entryReportParameterJSONObject.put("value", value);

			entryReportParametersJSONArray.put(entryReportParameterJSONObject);
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Entry.class.getName(), portletRequest);

		EntryServiceUtil.addEntry(
			themeDisplay.getScopeGroupId(), definitionId, format, false, null,
			null, false, null, emailNotifications, emailDelivery, null,
			generatedReportsURL, entryReportParametersJSONArray.toString(),
			serviceContext);
	}

}