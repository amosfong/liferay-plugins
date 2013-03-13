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

import com.liferay.portal.kernel.cal.DayAndPosition;
import com.liferay.portal.kernel.cal.Duration;
import com.liferay.portal.kernel.cal.Recurrence;
import com.liferay.portal.kernel.cal.RecurrenceSerializer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.reports.model.Definition;
import com.liferay.reports.model.Entry;
import com.liferay.reports.service.DefinitionServiceUtil;
import com.liferay.reports.service.EntryServiceUtil;
import com.liferay.reports.util.ReportsUtil;
import com.liferay.util.bridges.mvc.BaseActionCommand;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class AddSchedulerActionCommand extends BaseActionCommand {

	protected void addWeeklyDayPos(
		PortletRequest portletRequest, List<DayAndPosition> dayAndPositions,
		int day) {

		boolean weeklyDayPos = ParamUtil.getBoolean(
			portletRequest, "weeklyDayPos" + day);

		if (weeklyDayPos) {
			dayAndPositions.add(new DayAndPosition(day, 0));
		}
	}

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long definitionId = ParamUtil.getLong(portletRequest, "definitionId");
		String format = ParamUtil.getString(portletRequest, "format");
		Calendar startCalendar = ReportsUtil.getDate(
			portletRequest, "schedulerStartDate", true);
		String emailNotifications = ParamUtil.getString(
			portletRequest, "emailNotifications");
		String emailDelivery = ParamUtil.getString(
			portletRequest, "emailDelivery");
		String portletId = PortalUtil.getPortletId(portletRequest);
		String generatedReportsURL = ParamUtil.getString(
			portletRequest, "generatedReportsURL");

		Date schedulerEndDate = null;

		int endDateType = ParamUtil.getInteger(portletRequest, "endDateType");

		if (endDateType == 1) {
			Calendar endCalendar = ReportsUtil.getDate(
				portletRequest, "schedulerEndDate", false);

			schedulerEndDate = endCalendar.getTime();
		}

		int recurrenceType = ParamUtil.getInteger(
			portletRequest, "recurrenceType");

		String cronText = getCronText(
			portletRequest, startCalendar, true, recurrenceType);

		JSONArray entryReportParametersJSONArray =
			JSONFactoryUtil.createJSONArray();

		Definition definition = DefinitionServiceUtil.getDefinition(
			definitionId);

		JSONArray reportParametersJSONArray = JSONFactoryUtil.createJSONArray(
			definition.getReportParameters());

		for (int i = 0; i < reportParametersJSONArray.length(); i++) {
			JSONObject definitionReportParameterJSONObject =
				reportParametersJSONArray.getJSONObject(i);

			String key = definitionReportParameterJSONObject.getString("key");

			JSONObject entryReportParameterJSONObject =
				JSONFactoryUtil.createJSONObject();

			entryReportParameterJSONObject.put("key", key);

			String value = StringPool.BLANK;

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				"yyyy-MM-dd");

			String type = ParamUtil.getString(
				portletRequest, "useVariable" + key);

			if (type.equals("startDate")) {
				value = dateFormat.format(startCalendar.getTime());
			}
			else if (type.equals("endDate")) {
				if (schedulerEndDate != null) {
					value = dateFormat.format(schedulerEndDate.getTime());
				}
				else {
					value = StringPool.NULL;
				}
			}
			else {
				value = ParamUtil.getString(
					portletRequest, "parameterValue" + key);

				if (Validator.isNull(value)) {
					Calendar calendar = ReportsUtil.getDate(
						portletRequest, key, false);

					value = dateFormat.format(calendar.getTime());
				}
			}

			entryReportParameterJSONObject.put("value", value);

			entryReportParametersJSONArray.put(entryReportParameterJSONObject);
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Entry.class.getName(), portletRequest);

		EntryServiceUtil.addEntry(
			themeDisplay.getScopeGroupId(), definitionId, format, true,
			startCalendar.getTime(), schedulerEndDate,
			recurrenceType != Recurrence.NO_RECURRENCE, cronText,
			emailNotifications, emailDelivery, portletId, generatedReportsURL,
			entryReportParametersJSONArray.toString(), serviceContext);
	}

	protected String getCronText(
		PortletRequest portletRequest, Calendar startCalendar,
		boolean timeZoneSensitive, int recurrenceType) {

		Calendar calendar = null;

		if (timeZoneSensitive) {
			calendar = CalendarFactoryUtil.getCalendar();

			calendar.setTime(startCalendar.getTime());
		}
		else {
			calendar = (Calendar)startCalendar.clone();
		}

		Recurrence recurrence = new Recurrence(
			calendar, new Duration(1, 0, 0, 0), recurrenceType);

		recurrence.setWeekStart(Calendar.SUNDAY);

		if (recurrenceType == Recurrence.DAILY) {
			int dailyType = ParamUtil.getInteger(portletRequest, "dailyType");

			if (dailyType == 0) {
				int dailyInterval = ParamUtil.getInteger(
					portletRequest, "dailyInterval", 1);

				recurrence.setInterval(dailyInterval);
			}
			else {
				DayAndPosition[] dayAndPositions = new DayAndPosition[] {
					new DayAndPosition(Calendar.MONDAY, 0),
					new DayAndPosition(Calendar.TUESDAY, 0),
					new DayAndPosition(Calendar.WEDNESDAY, 0),
					new DayAndPosition(Calendar.THURSDAY, 0),
					new DayAndPosition(Calendar.FRIDAY, 0)
				};

				recurrence.setByDay(dayAndPositions);
			}
		}
		else if (recurrenceType == Recurrence.WEEKLY) {
			int weeklyInterval = ParamUtil.getInteger(
				portletRequest, "weeklyInterval", 1);

			recurrence.setInterval(weeklyInterval);

			List<DayAndPosition> dayAndPositions =
				new ArrayList<DayAndPosition>();

			addWeeklyDayPos(portletRequest, dayAndPositions, Calendar.SUNDAY);
			addWeeklyDayPos(portletRequest, dayAndPositions, Calendar.MONDAY);
			addWeeklyDayPos(portletRequest, dayAndPositions, Calendar.TUESDAY);
			addWeeklyDayPos(
				portletRequest, dayAndPositions, Calendar.WEDNESDAY);
			addWeeklyDayPos(portletRequest, dayAndPositions, Calendar.THURSDAY);
			addWeeklyDayPos(portletRequest, dayAndPositions, Calendar.FRIDAY);
			addWeeklyDayPos(portletRequest, dayAndPositions, Calendar.SATURDAY);

			if (dayAndPositions.isEmpty()) {
				dayAndPositions.add(new DayAndPosition(Calendar.MONDAY, 0));
			}

			recurrence.setByDay(
				dayAndPositions.toArray(
					new DayAndPosition[dayAndPositions.size()]));
		}
		else if (recurrenceType == Recurrence.MONTHLY) {
			int monthlyType = ParamUtil.getInteger(
				portletRequest, "monthlyType");

			if (monthlyType == 0) {
				int monthlyDay = ParamUtil.getInteger(
					portletRequest, "monthlyDay0", 1);

				recurrence.setByMonthDay(new int[] {monthlyDay});

				int monthlyInterval = ParamUtil.getInteger(
					portletRequest, "monthlyInterval0", 1);

				recurrence.setInterval(monthlyInterval);
			}
			else {
				int monthlyDay = ParamUtil.getInteger(
					portletRequest, "monthlyDay1");
				int monthlyPos = ParamUtil.getInteger(
					portletRequest, "monthlyPos");

				DayAndPosition[] dayAndPositions = new DayAndPosition[] {
					new DayAndPosition(monthlyDay, monthlyPos)
				};

				recurrence.setByDay(dayAndPositions);

				int monthlyInterval = ParamUtil.getInteger(
					portletRequest, "monthlyInterval1", 1);

				recurrence.setInterval(monthlyInterval);
			}
		}
		else if (recurrenceType == Recurrence.YEARLY) {
			int yearlyType = ParamUtil.getInteger(portletRequest, "yearlyType");

			if (yearlyType == 0) {
				int yearlyMonth = ParamUtil.getInteger(
					portletRequest, "yearlyMonth0");

				recurrence.setByMonth(new int[] {yearlyMonth});

				int yearlyDay = ParamUtil.getInteger(
					portletRequest, "yearlyDay0", 1);

				recurrence.setByMonthDay(new int[] {yearlyDay});

				int yearlyInterval = ParamUtil.getInteger(
					portletRequest, "yearlyInterval0", 1);

				recurrence.setInterval(yearlyInterval);
			}
			else {
				int yearlyDay = ParamUtil.getInteger(
					portletRequest, "yearlyDay1");
				int yearlyPos = ParamUtil.getInteger(
					portletRequest, "yearlyPos");

				DayAndPosition[] dayAndPositions = new DayAndPosition[] {
					new DayAndPosition(yearlyDay, yearlyPos)
				};

				recurrence.setByDay(dayAndPositions);

				int yearlyMonth = ParamUtil.getInteger(
					portletRequest, "yearlyMonth1");

				recurrence.setByMonth(new int[] {yearlyMonth});

				int yearlyInterval = ParamUtil.getInteger(
					portletRequest, "yearlyInterval1", 1);

				recurrence.setInterval(yearlyInterval);
			}
		}

		return RecurrenceSerializer.toCronText(recurrence);
	}

}