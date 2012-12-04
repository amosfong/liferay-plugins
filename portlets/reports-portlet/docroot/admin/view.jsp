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
String tabs1 = ParamUtil.getString(request, "tabs1", "reports");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);

String tabs1Names = "reports";

boolean showDefinitionsTab = AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_DEFINITION);
boolean showDataSourcesTab = AdminPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_SOURCE);

if (portletName.equals("1")) {
	if (showDefinitionsTab) {
		tabs1Names += ",definitions";
	}

	if (showDataSourcesTab) {
		tabs1Names += ",sources";
	}
}
%>

<liferay-ui:tabs
	names="<%= tabs1Names %>"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("reports") %>'>
		<liferay-util:include page="/admin/report/entries.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= showDefinitionsTab && tabs1.equals("definitions") %>'>
		<liferay-util:include page="/admin/definition/definitions.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= showDataSourcesTab && tabs1.equals("sources") %>'>
		<liferay-util:include page="/admin/data_source/sources.jsp" servletContext="<%= application %>" />
	</c:when>
</c:choose>