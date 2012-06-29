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
String definitionName = ParamUtil.getString(request, "definitionName");
String description = ParamUtil.getString(request, "description");
String sourceId = ParamUtil.getString(request, "sourceId");
String reportName = ParamUtil.getString(request, "reportName");
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
	<portlet:param name="tabs1" value="definitions" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />

	<liferay-portlet:renderURL varImpl="iteratorURL">
		<portlet:param name="definitionName" value="<%= definitionName %>" />
		<portlet:param name="description" value="<%= description %>" />
		<portlet:param name="sourceId" value="<%= sourceId %>" />
		<portlet:param name="reportName" value="<%= reportName %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:search-container
		displayTerms="<%= new DisplayTerms(renderRequest) %>"
		emptyResultsMessage="there-are-no-definitions"
		headerNames="definition-name,source-name,create-date"
		iteratorURL="<%= iteratorURL %>"
	>
		<liferay-ui:search-form
			page="/admin/definition/definition_search.jsp"
			servletContext="<%= application %>"
		/>

		<liferay-ui:search-container-results>

			<%
			DisplayTerms displayTerms = searchContainer.getDisplayTerms();

			if (displayTerms.isAdvancedSearch()) {
				results = DefinitionServiceUtil.getDefinitions(themeDisplay.getParentGroupId(), definitionName, description, sourceId, reportName, displayTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd(), null);
				total = DefinitionServiceUtil.getDefinitionsCount(themeDisplay.getParentGroupId(), definitionName, description, sourceId, reportName, displayTerms.isAndOperator());
			}
			else {
				results = DefinitionServiceUtil.getDefinitions(themeDisplay.getParentGroupId(), displayTerms.getKeywords(), displayTerms.getKeywords(), null, displayTerms.getKeywords(), false, searchContainer.getStart(), searchContainer.getEnd(), null);
				total = DefinitionServiceUtil.getDefinitionsCount(themeDisplay.getParentGroupId(), displayTerms.getKeywords(), displayTerms.getKeywords(), null, displayTerms.getKeywords(), false);
			}

			pageContext.setAttribute("results", results);
			pageContext.setAttribute("total", total);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.reports.model.Definition"
			keyProperty="definitionId"
			modelVar="definition"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcPath" value="/admin/definition/edit_definition.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="definitionId" value="<%= String.valueOf(definition.getDefinitionId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="definition-name"
				value="<%= definition.getName(locale) %>"
			/>

			<%
			Source source = null;

			try {
				source = SourceLocalServiceUtil.getSource(definition.getSourceId());
			}
			catch (NoSuchSourceException nsse) {
			}

			String name = null;

			if (definition.getSourceId() == PortletConstants.PORTAL_DATA_SOURCE_ID) {
				name = ReportDataSourceType.PORTAL.getValue();
			}
			else if (source != null) {
				name = source.getName(locale);
			}
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="source-name"
				value="<%= name %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="create-date"
				value="<%= dateFormatDateTime.format(definition.getCreateDate()) %>"
			/>

			<liferay-ui:search-container-column-jsp
				path="/admin/definition/definition_actions.jsp"
			/>
		</liferay-ui:search-container-row>

		<div class="separator"><!-- --></div>

		<aui:button-row cssClass="search-buttons">
			<portlet:renderURL var="addDefinitionURL">
				<portlet:param name="mvcPath" value="/admin/definition/edit_definition.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:renderURL>

			<aui:button href="<%= addDefinitionURL %>" value="add-definition" />
		</aui:button-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "definitions"), currentURL);
%>