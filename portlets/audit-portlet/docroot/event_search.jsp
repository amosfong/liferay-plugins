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
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_audit_event_search"
>
	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="user-id" />
		</td>
		<td colspan="2">
			<liferay-ui:message key="user-name" />
		</td>
	</tr>
	<tr>
		<td>
			<input id="<portlet:namespace />userId" name="<portlet:namespace />userId" size="20" type="text" value="<%= (userId != 0) ? String.valueOf(userId) : StringPool.BLANK %>" />
		</td>
		<td colspan="2">
			<input id="<portlet:namespace />userName" name="<portlet:namespace />userName" size="20" type="text" value="<%= userName %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="resource-id" />
		</td>
		<td>
			<liferay-ui:message key="resource-name" />
		</td>
		<td>
			<liferay-ui:message key="resource-action" />
		</td>
	</tr>
	<tr>
		<td>
			<input id="<portlet:namespace />classPK" name="<portlet:namespace />classPK" size="20" type="text" value="<%= classPK %>" />
		</td>
		<td>
			<input id="<portlet:namespace />className" name="<portlet:namespace />className" size="20" type="text" value="<%= className %>" />
		</td>
		<td>
			<input id="<portlet:namespace />eventType" name="<portlet:namespace />eventType" size="20" type="text" value="<%= eventType %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="session-id" />
		</td>
		<td>
			<liferay-ui:message key="client-ip" />
		</td>
		<td>
			<liferay-ui:message key="client-host" />
		</td>
	</tr>
	<tr>
		<td>
			<input id="<portlet:namespace />sessionID" name="<portlet:namespace />sessionID" size="20" type="text" value="<%= sessionID %>" />
		</td>
		<td>
			<input id="<portlet:namespace />clientIP" name="<portlet:namespace />clientIP" size="20" type="text" value="<%= clientIP %>" />
		</td>
		<td>
			<input id="<portlet:namespace />clientHost" name="<portlet:namespace />clientHost" size="20" type="text" value="<%= clientHost %>" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="server-name" />
		</td>
		<td colspan="2">
			<liferay-ui:message key="server-port" />
		</td>
	</tr>
	<tr>
		<td>
			<input id="<portlet:namespace />serverName" name="<portlet:namespace />serverName" size="20" type="text" value="<%= serverName %>" />
		</td>
		<td colspan="2">
			<input id="<portlet:namespace />serverPort" name="<portlet:namespace />serverPort" size="20" type="text" value="<%= (serverPort != 0) ? String.valueOf(serverPort) : StringPool.BLANK %>" />
		</td>
	</tr>
	</table>

	<br />

	<table class="lfr-table" width="100%">
	<tr>
		<td width="50%">
			<liferay-ui:message key="start-date" />
		</td>
		<td width="50%">
			<liferay-ui:message key="end-date" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:input-date
				dayParam='<%= "startDateDay" %>'
				dayValue="<%= startDateDay %>"
				monthParam='<%= "startDateMonth" %>'
				monthValue="<%= startDateMonth %>"
				yearParam='<%= "startDateYear" %>'
				yearRangeEnd="<%= startDateYearRangeEnd %>"
				yearRangeStart="<%= startDateYearRangeStart %>"
				yearValue="<%= startDateYear %>"
			/>

			<liferay-ui:input-time
				amPmParam='<%= "startDateAmPm" %>'
				amPmValue="<%= startDateAmPm %>"
				hourParam='<%= "startDateHour" %>'
				hourValue="<%= startDateHour %>"
				minuteParam='<%= "startDateMinute" %>'
				minuteValue="<%= startDateMinute %>"
			/>
		</td>
		<td>
			<liferay-ui:input-date
				dayParam='<%= "endDateDay" %>'
				dayValue="<%= endDateDay %>"
				monthParam='<%= "endDateMonth" %>'
				monthValue="<%= endDateMonth %>"
				yearParam='<%= "endDateYear" %>'
				yearRangeEnd="<%= endDateYearRangeEnd %>"
				yearRangeStart="<%= endDateYearRangeStart %>"
				yearValue="<%= endDateYear %>"
			/>

			<liferay-ui:input-time
				amPmParam='<%= "endDateAmPm" %>'
				amPmValue="<%= endDateAmPm %>"
				hourParam='<%= "endDateHour" %>'
				hourValue="<%= endDateHour %>"
				minuteParam='<%= "endDateMinute" %>'
				minuteValue="<%= endDateMinute %>"
			/>
		</td>
	</tr>
	</table>
</liferay-ui:search-toggle>