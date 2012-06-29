<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String definitionName = ParamUtil.getString(request, "definitionName");
String userName = ParamUtil.getString(request, "userName");

Calendar today = CalendarFactoryUtil.getCalendar(timeZone, locale);

Calendar yesterday = CalendarFactoryUtil.getCalendar(timeZone, locale);

yesterday.add(Calendar.DATE, -1);

int endDateDay = ParamUtil.getInteger(request, "endDateDay", today.get(Calendar.DATE));
int endDateMonth = ParamUtil.getInteger(request, "endDateMonth", today.get(Calendar.MONTH));
int endDateYear = ParamUtil.getInteger(request, "endDateYear", today.get(Calendar.YEAR));
int endDateYearRangeEnd = today.get(Calendar.YEAR);
int endDateYearRangeStart = today.get(Calendar.YEAR) - 50;

int startDateDay = ParamUtil.getInteger(request, "startDateDay", yesterday.get(Calendar.DATE));
int startDateMonth = ParamUtil.getInteger(request, "startDateMonth", yesterday.get(Calendar.MONTH));
int startDateYear = ParamUtil.getInteger(request, "startDateYear", yesterday.get(Calendar.YEAR));
int startDateYearRangeEnd = yesterday.get(Calendar.YEAR);
int startDateYearRangeStart = yesterday.get(Calendar.YEAR) - 50;
%>