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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Source source = (Source)row.getObject();

String sourceId = String.valueOf(source.getSourceId());
%>

<liferay-ui:icon-menu cssClass="">
	<c:if test="<%= SourcePermission.contains(permissionChecker, source, ActionKeys.VIEW) %>">
		<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" name="testDataSource" var="testConnectionURL">
			<portlet:param name="sourceId" value="<%= sourceId %>" />
			<portlet:param name="tabs1" value="sources" />
		</portlet:actionURL>

		<liferay-ui:icon
			image="view"
			message="test-database-connection"
			url="<%= testConnectionURL %>"
		/>
	</c:if>

	<c:if test="<%= SourcePermission.contains(permissionChecker, source, ActionKeys.UPDATE) %>">
		<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>" var="editURL">
			<portlet:param name="mvcPath" value="/admin/data_source/edit_data_source.jsp" />
			<portlet:param name="sourceId" value="<%= sourceId %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= SourcePermission.contains(permissionChecker, source, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Source.class.getName() %>"
			modelResourceDescription="<%= source.getName(locale) %>"
			resourcePrimKey="<%= sourceId %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon
			image="permissions"
			url="<%= permissionsURL %>"
		/>
	</c:if>

	<c:if test="<%= SourcePermission.contains(permissionChecker, source, ActionKeys.DELETE) %>">
		<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>" name="deleteDataSource" var="deleteURL">
			<portlet:param name="sourceId" value="<%= sourceId %>" />
			<portlet:param name="tabs1" value="sources" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>