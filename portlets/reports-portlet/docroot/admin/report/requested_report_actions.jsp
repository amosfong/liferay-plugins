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
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Entry entry = (Entry) row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Entry.class.getName() %>"
			modelResourceDescription="<%= String.valueOf(entry.getEntryId()) %>"
			resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			image="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<%
	Date now = new Date();
	%>

	<c:if test="<%= entry.isRepeating() && ((entry.getEndDate() == null) || now.before(entry.getEndDate())) %>">
		<c:if test="<%= EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>">
			<portlet:renderURL var="searchRequestURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/view.jsp" />
				<portlet:param name="tabs1" value="reports" />
			</portlet:renderURL>

			<portlet:actionURL name="unscheduleReportRequest" var="unscheduleURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="redirect" value="<%= searchRequestURL %>" />
				<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="time"
				message="unschedule"
				url="<%= unscheduleURL %>"
			/>
		</c:if>
	</c:if>

	<c:if test="<%= EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>">
		<portlet:renderURL var="searchRequestURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/view.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="tabs1" value="reports" />
		</portlet:renderURL>

		<portlet:actionURL name="archiveRequest" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="redirect" value="<%= searchRequestURL %>" />
			<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>

</liferay-ui:icon-menu>