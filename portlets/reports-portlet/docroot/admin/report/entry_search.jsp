<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");

DisplayTerms displayTerms = searchContainer.getDisplayTerms();

String definitionName = ParamUtil.getString(request, "definitionName");
String userName = ParamUtil.getString(request, "userName");

Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

int endDateDay = ParamUtil.getInteger(request, "endDateDay", calendar.get(Calendar.DATE));
int endDateMonth = ParamUtil.getInteger(request, "endDateMonth", calendar.get(Calendar.MONTH));
int endDateYear = ParamUtil.getInteger(request, "endDateYear", calendar.get(Calendar.YEAR));
int endDateYearRangeEnd = calendar.get(Calendar.YEAR);
int endDateYearRangeStart = calendar.get(Calendar.YEAR) - 50;

calendar.add(Calendar.DATE, -1);

int startDateDay = ParamUtil.getInteger(request, "startDateDay", calendar.get(Calendar.DATE));
int startDateMonth = ParamUtil.getInteger(request, "startDateMonth", calendar.get(Calendar.MONTH));
int startDateYear = ParamUtil.getInteger(request, "startDateYear", calendar.get(Calendar.YEAR));
int startDateYearRangeEnd = calendar.get(Calendar.YEAR);
int startDateYearRangeStart = calendar.get(Calendar.YEAR) - 50;
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_reports_entry_search"
>
	<aui:fieldset>
		<aui:fieldset>
			<aui:input name="definitionName" size="20" value="<%= definitionName %>" />

			<aui:input label="requested-by" name="userName" size="20" value="<%= userName %>" />
		</aui:fieldset>

		<aui:fieldset>
			<aui:field-wrapper label="start-date">
				<liferay-ui:input-date
					dayParam='<%= "startDateDay" %>'
					dayValue="<%= startDateDay %>"
					monthParam='<%= "startDateMonth" %>'
					monthValue="<%= startDateMonth %>"
					yearParam='<%= "startDateYear" %>'
					yearValue="<%= startDateYear %>"
				/>
			</aui:field-wrapper>

			<aui:field-wrapper label="end-date">
				<liferay-ui:input-date
					dayParam='<%= "endDateDay" %>'
					dayValue="<%= endDateDay %>"
					monthParam='<%= "endDateMonth" %>'
					monthValue="<%= endDateMonth %>"
					yearParam='<%= "endDateYear" %>'
					yearValue="<%= endDateYear %>"
				/>
			</aui:field-wrapper>
		</aui:fieldset>
	</aui:fieldset>
</liferay-ui:search-toggle>