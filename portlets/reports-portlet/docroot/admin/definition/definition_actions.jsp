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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Definition definition = (Definition)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= DefinitionPermission.contains(permissionChecker, definition, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/definition/edit_definition.jsp" />
			<portlet:param name="definitionId" value="<%= String.valueOf(definition.getDefinitionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= DefinitionPermission.contains(permissionChecker, definition, ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= Definition.class.getName() %>"
			modelResourceDescription="<%= definition.getName(locale) %>"
			resourcePrimKey="<%= String.valueOf(definition.getDefinitionId()) %>"
			var="permissionsURL"
		/>

		<liferay-ui:icon
			image="permissions"
			url="<%= permissionsURL %>"
		/>
	</c:if>

	<c:if test="<%= DefinitionPermission.contains(permissionChecker, definition, ActionKeys.ADD_REPORT) %>">
		<portlet:renderURL var="addReportURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/report/generate_report.jsp" />
			<portlet:param name="definitionId" value="<%= String.valueOf(definition.getDefinitionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="add_template"
			message="add-report"
			url="<%= addReportURL %>"
		/>
	</c:if>

	<c:if test="<%= DefinitionPermission.contains(permissionChecker, definition, ActionKeys.ADD_REPORT) %>">
		<portlet:renderURL var="addScheduleURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/report/edit_schedule.jsp" />
			<portlet:param name="definitionId" value="<%= String.valueOf(definition.getDefinitionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="time"
			message="add-schedule"
			url="<%= addScheduleURL %>"
		/>
	</c:if>

	<c:if test="<%= DefinitionPermission.contains(permissionChecker, definition, ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteDefinition" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="tabs1" value="definitions" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="definitionId" value="<%= String.valueOf(definition.getDefinitionId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>