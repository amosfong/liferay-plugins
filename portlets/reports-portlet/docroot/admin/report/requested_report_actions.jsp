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
ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Entry entry = (Entry) row.getObject();

String entryId = String.valueOf(entry.getEntryId());
%>

<liferay-ui:icon-menu cssClass="">
	<c:if test="<%= EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Entry.class.getName() %>"
			modelResourceDescription="<%= entryId %>"
			resourcePrimKey="<%= entryId %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon
			image="permissions"
			url="<%= permissionsURL %>"
		/>
	</c:if>

	<c:if test='<%= entry.isRepeating() && (entry.getEndDate() == null || DateUtil.newDate().before(entry.getEndDate())) %>' >
		<c:if test='<%= EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>'>
			<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="searchRequestURL">
				<portlet:param name="mvcPath" value="/admin/view.jsp" />
				<portlet:param name="tabs1" value="reports" />
			</portlet:renderURL>

			<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" name="unscheduleReportRequest" var="unscheduleURL">
				<portlet:param name="entryId" value="<%= entryId %>" />
				<portlet:param name="redirect" value="<%= searchRequestURL %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				image="time"
				message="unschedule"
				url="<%= unscheduleURL %>"
			/>
		</c:if>
	</c:if>

	<c:if test='<%= EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>'>
		<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="searchRequestURL">
			<portlet:param name="mvcPath" value="/admin/view.jsp" />
			<portlet:param name="tabs1" value="reports" />
		</portlet:renderURL>

		<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" name="archiveRequest" var="deleteURL">
			<portlet:param name="entryId" value="<%= entryId %>" />
			<portlet:param name="redirect" value="<%= searchRequestURL %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>

</liferay-ui:icon-menu>