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
String description = ParamUtil.getString(request, "description");
String reportName = ParamUtil.getString(request, "reportName");
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_reports_definition_search"
>
	<aui:fieldset>
		<aui:input name="definitionName" size="20" value="<%= definitionName %>" />

		<aui:select label="data-source-name" name="sourceId">
			<aui:option label="all" />
			<aui:option label="<%= ReportDataSourceType.PORTAL %>" value="0" />

			<%
			List<Source> sources = SourceServiceUtil.getSources(themeDisplay.getSiteGroupId(), null, null, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (Source source : sources) {
			%>

				<aui:option label="<%= source.getName(locale) %>" value="<%= source.getSourceId() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:input name="description" size="20" value="<%= description %>" />

		<aui:input label="template" name="reportName" size="20" value="<%= reportName %>" />
	</aui:fieldset>
</liferay-ui:search-toggle>