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

SPIDefinition spiDefinition = null;

if (row != null) {
	spiDefinition = (SPIDefinition)row.getObject();
}
else {
	long spiDefinitionId = ParamUtil.getLong(request, "spiDefinitionId");

	spiDefinition = SPIDefinitionServiceUtil.getSPIDefinition(spiDefinitionId);
}
%>

<liferay-ui:icon-menu>
	<c:if test="<%= SPIDefinitionPermissionUtil.contains(permissionChecker, spiDefinition, ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/edit_spi_definition.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="spiDefinitionId" value="<%= String.valueOf(spiDefinition.getSpiDefinitionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			image="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= SPIDefinitionPermissionUtil.contains(permissionChecker, spiDefinition, ActionKeys.MANAGE) %>">
		<c:choose>
			<c:when test="<%= spiDefinition.getStatus() == SPIAdminConstants.STATUS_STARTED %>">
				<portlet:actionURL name="restartSPI" var="restartURL">
					<portlet:param name="mvcPath" value="/view.jsp" />
					<portlet:param name="spiDefinitionId" value="<%= String.valueOf(spiDefinition.getSpiDefinitionId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="restart"
					src='<%= PortalUtil.getPathContext(request) + "/images/restart.png" %>'
					url="<%= restartURL %>"
				/>

				<portlet:actionURL name="stopSPI" var="stopURL">
					<portlet:param name="mvcPath" value="/view.jsp" />
					<portlet:param name="spiDefinitionId" value="<%= String.valueOf(spiDefinition.getSpiDefinitionId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="stop"
					src='<%= PortalUtil.getPathContext(request) + "/images/stop.png" %>'
					url="<%= stopURL %>"
				/>
			</c:when>
			<c:when test="<%= spiDefinition.getStatus() == SPIAdminConstants.STATUS_STOPPED %>">
				<portlet:actionURL name="startSPI" var="startURL">
					<portlet:param name="mvcPath" value="/view.jsp" />
					<portlet:param name="spiDefinitionId" value="<%= String.valueOf(spiDefinition.getSpiDefinitionId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon
					message="start"
					src='<%= PortalUtil.getPathContext(request) + "/images/start.png" %>'
					url="<%= startURL %>"
				/>
			</c:when>
		</c:choose>
	</c:if>

	<c:if test="<%= SPIDefinitionPermissionUtil.contains(permissionChecker, spiDefinition, ActionKeys.DELETE) %>">
		<c:choose>
			<c:when test="<%= spiDefinition.getStatus() == SPIAdminConstants.STATUS_STOPPED %>">
				<portlet:actionURL name="deleteSPIDefinition" var="deleteURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="spiDefinitionId" value="<%= String.valueOf(spiDefinition.getSpiDefinitionId()) %>" />
				</portlet:actionURL>

				<liferay-ui:icon-delete
					url="<%= deleteURL %>"
				/>
			</c:when>
			<c:otherwise>

				<%
				String taglibURL = "javascript:alert('" + UnicodeLanguageUtil.get(pageContext, "you-cannot-delete-a-running-spi.-please-stop-the-spi-before-deleting-it") + "');";
				%>

				<liferay-ui:icon
					image="delete"
					url="<%= taglibURL %>"
				/>
			</c:otherwise>
		</c:choose>
	</c:if>
</liferay-ui:icon-menu>