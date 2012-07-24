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

package com.liferay.reports.admin.portlet.action;

import com.liferay.portal.kernel.cal.DayAndPosition;
import com.liferay.portal.kernel.cal.Duration;
import com.liferay.portal.kernel.cal.Recurrence;
import com.liferay.portal.kernel.cal.RecurrenceSerializer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.reports.model.Definition;
import com.liferay.reports.model.Entry;
import com.liferay.reports.service.DefinitionLocalServiceUtil;
import com.liferay.reports.service.EntryServiceUtil;
import com.liferay.reports.util.ReportsUtil;
import com.liferay.util.bridges.mvc.ActionCommand;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class AddSchedulerActionCommand implements ActionCommand {

	public boolean processCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortletException {

		long definitionId = ParamUtil.getLong(portletRequest, "definitionId");
		String emailDelivery = ParamUtil.getString(
			portletRequest, "emailDelivery");
		String emailNotifications = ParamUtil.getString(
			portletRequest, "emailNotifications");
		String format = ParamUtil.getString(portletRequest, "format");
		String generatedReportsURL = ParamUtil.getString(
			portletRequest, "generatedReportsURL");

		String portletId = PortalUtil.getPortletId(portletRequest);

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Entry.class.getName(), portletRequest);

			int endDateType = ParamUtil.getInteger(
				portletRequest, "endDateType");
			int recurrenceType = ParamUtil.getInteger(
				portletRequest, "recurrenceType");

			Date schedulerEndDate = null;

			if (endDateType == 1) {
				Calendar endCalendar = ReportsUtil.getDate(
					portletRequest, "schedulerEndDate", false);

				schedulerEndDate = endCalendar.getTime();
			}

			Calendar startCalendar = ReportsUtil.getDate(
				portletRequest, "schedulerStartDate", true);
			String cronText = getCronText(
				portletRequest, startCalendar, true, recurrenceType);

			JSONArray entryReportParametersJSONArray =
				JSONFactoryUtil.createJSONArray();

			Definition definition = DefinitionLocalServiceUtil.getDefinition(
				definitionId);

			JSONArray reportParametersJSONArray =
				JSONFactoryUtil.createJSONArray(
					definition.getReportParameters());

			for (int i = 0; i < reportParametersJSONArray.length(); i++) {
				JSONObject definitionReportParameterJSONObject =
					reportParametersJSONArray.getJSONObject(i);

				JSONObject entryReportParameterJSONObject =
					JSONFactoryUtil.createJSONObject();

				String key = definitionReportParameterJSONObject.getString(
					"key");

				entryReportParameterJSONObject.put("key", key);

				String type = ParamUtil.getString(
					portletRequest, "useVariable" + key);

				String value = StringPool.BLANK;

				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				if (type.equals("startDate")) {
					value = df.format(startCalendar.getTime());
				}
				else if (type.equals("endDate")) {
					if (schedulerEndDate != null) {
						value = df.format(schedulerEndDate.getTime());
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

						value = df.format(calendar.getTime());
					}
				}

				entryReportParameterJSONObject.put("value", value);

				entryReportParametersJSONArray.put(
					entryReportParameterJSONObject);
			}

			EntryServiceUtil.addEntry(
				definitionId, format, true, startCalendar.getTime(),
				schedulerEndDate, recurrenceType != Recurrence.NO_RECURRENCE,
				cronText, emailNotifications, emailDelivery, portletId,
				generatedReportsURL, entryReportParametersJSONArray.toString(),
				serviceContext);

		}
		catch (PortalException pe) {
			SessionErrors.add(portletRequest, pe.getClass());
			portletRequest.setAttribute(
					WebKeys.REDIRECT,
					ParamUtil.getString(portletRequest,"currentURL"));
		}
		catch (SystemException se) {
			throw new PortletException(se);
		}

		return true;
	}

	protected static void addWeeklyDayPos(
		PortletRequest portletRequest, List<DayAndPosition> list, int day) {

		boolean weeklyDayPos = ParamUtil.getBoolean(
			portletRequest, "weeklyDayPos" + day);

		if (weeklyDayPos) {
			list.add(new DayAndPosition(day, 0));
		}
	}

	protected static String getCronText(
		PortletRequest portletRequest, Calendar startDate,
		boolean timeZoneSensitive, int recurrenceType) {

		Calendar startCalendar = null;

		if (timeZoneSensitive) {
			startCalendar = CalendarFactoryUtil.getCalendar();

			startCalendar.setTime(startDate.getTime());
		}
		else {
			startCalendar = (Calendar) startDate.clone();
		}

		Recurrence recurrence = new Recurrence(
			startCalendar, new Duration(1, 0, 0, 0), recurrenceType);

		recurrence.setWeekStart(Calendar.SUNDAY);

		if (recurrenceType == Recurrence.DAILY) {
			int dailyType = ParamUtil.getInteger(portletRequest, "dailyType");

			if (dailyType == 0) {
				int dailyInterval = ParamUtil.getInteger(
					portletRequest, "dailyInterval", 1);

				recurrence.setInterval(dailyInterval);
			}
			else {
				DayAndPosition[] dayAndPosition = new DayAndPosition[] {
					new DayAndPosition(Calendar.MONDAY, 0),
					new DayAndPosition(Calendar.TUESDAY, 0),
					new DayAndPosition(Calendar.WEDNESDAY, 0),
					new DayAndPosition(Calendar.THURSDAY, 0),
					new DayAndPosition(Calendar.FRIDAY, 0)
				};

				recurrence.setByDay(dayAndPosition);
			}
		}
		else if (recurrenceType == Recurrence.WEEKLY) {
			int weeklyInterval = ParamUtil.getInteger(
				portletRequest, "weeklyInterval", 1);

			recurrence.setInterval(weeklyInterval);

			List<DayAndPosition> dayAndPosition =
				new ArrayList<DayAndPosition>();

			addWeeklyDayPos(portletRequest, dayAndPosition, Calendar.SUNDAY);
			addWeeklyDayPos(portletRequest, dayAndPosition, Calendar.MONDAY);
			addWeeklyDayPos(portletRequest, dayAndPosition, Calendar.TUESDAY);
			addWeeklyDayPos(portletRequest, dayAndPosition, Calendar.WEDNESDAY);
			addWeeklyDayPos(portletRequest, dayAndPosition, Calendar.THURSDAY);
			addWeeklyDayPos(portletRequest, dayAndPosition, Calendar.FRIDAY);
			addWeeklyDayPos(portletRequest, dayAndPosition, Calendar.SATURDAY);

			if (dayAndPosition.size() == 0) {
				dayAndPosition.add(new DayAndPosition(Calendar.MONDAY, 0));
			}

			recurrence.setByDay(dayAndPosition.toArray(new DayAndPosition[0]));
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
				int monthlyPos = ParamUtil.getInteger(
					portletRequest, "monthlyPos");
				int monthlyDay = ParamUtil.getInteger(
					portletRequest, "monthlyDay1");

				DayAndPosition[] dayAndPosition = new DayAndPosition[] {
					new DayAndPosition(monthlyDay, monthlyPos)
				};

				recurrence.setByDay(dayAndPosition);

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
				int yearlyDay = ParamUtil.getInteger(
					portletRequest, "yearlyDay0", 1);

				recurrence.setByMonth(new int[] {yearlyMonth});
				recurrence.setByMonthDay(new int[] {yearlyDay});

				int yearlyInterval = ParamUtil.getInteger(
					portletRequest, "yearlyInterval0", 1);

				recurrence.setInterval(yearlyInterval);
			}
			else {
				int yearlyPos = ParamUtil.getInteger(
					portletRequest, "yearlyPos");
				int yearlyDay = ParamUtil.getInteger(
					portletRequest, "yearlyDay1");
				int yearlyMonth = ParamUtil.getInteger(
					portletRequest, "yearlyMonth1");

				DayAndPosition[] dayAndPosition = new DayAndPosition[] {
					new DayAndPosition(yearlyDay, yearlyPos)
				};

				recurrence.setByDay(dayAndPosition);

				recurrence.setByMonth(new int[] {yearlyMonth});

				int yearlyInterval = ParamUtil.getInteger(
					portletRequest, "yearlyInterval1", 1);

				recurrence.setInterval(yearlyInterval);
			}
		}

		return RecurrenceSerializer.toCronText(recurrence);
	}

}