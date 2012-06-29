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
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");

DisplayTerms displayTerms = searchContainer.getDisplayTerms();

String name = ParamUtil.getString(request, "name");
String description = ParamUtil.getString(request, "description");
String sourceId = ParamUtil.getString(request, "sourceId");
String reportName = ParamUtil.getString(request, "reportName");
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_reports_definition_search"
>
	<aui:fieldset>
		<aui:input label="definition-name" name="name" size="20" value="<%= name %>" />

		<aui:select label="data-source-name" name="sourceId">
			<aui:option label="all" value="" />
			<aui:option label="<%= ReportDataSourceType.PORTAL %>" value="<%= PortletConstants.PORTAL_DATA_SOURCE_ID %>" />

			<%
			List<Source> list = SourceServiceUtil.getSources(themeDisplay.getParentGroupId(), null, null, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (Iterator itr = list.iterator(); itr.hasNext();) {
				Source element = (Source)itr.next();
				if (SourcePermission.contains(permissionChecker, element, ActionKeys.VIEW)){
			%>

				<aui:option label="<%= element.getName(locale) %>" value="<%= element.getSourceId() %>" />
			<% }} %>

		</aui:select>

		<aui:input label="description" name="description" size="20" value="<%= description %>" />

		<aui:input label="template" name="reportName" size="20" value="<%= reportName %>" />
	</aui:fieldset>
</liferay-ui:search-toggle>