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
String name = ParamUtil.getString(request, "name");
String driverUrl = ParamUtil.getString(request, "driverUrl");
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
	<portlet:param name="tabs1" value="sources" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />

	<liferay-portlet:renderURL varImpl="iteratorURL">
		<portlet:param name="name" value="<%= name %>" />
		<portlet:param name="driverUrl" value="<%= driverUrl %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:search-container
		displayTerms="<%= new DisplayTerms(renderRequest) %>"
		emptyResultsMessage="there-are-no-sources"
		headerNames="source-name,create-date"
		iteratorURL="<%= iteratorURL %>"
	>
		<liferay-ui:search-form
			page="/admin/data_source/source_search.jsp"
			servletContext="<%= application %>"
		/>

		<%
		DisplayTerms displayTerms = searchContainer.getDisplayTerms();

		if (displayTerms.isAdvancedSearch()) {
			total = SourceServiceUtil.getSourcesCount(themeDisplay.getSiteGroupId(), name, driverUrl, displayTerms.isAndOperator());

			searchContainer.setTotal(total);

			searchContainer.setResults(SourceServiceUtil.getSources(themeDisplay.getSiteGroupId(), name, driverUrl, displayTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd(), null));
		}
		else {
			total = SourceServiceUtil.getSourcesCount(themeDisplay.getSiteGroupId(), displayTerms.getKeywords(), displayTerms.getKeywords(), false);

			searchContainer.setTotal(total);

			searchContainer.setResults(SourceServiceUtil.getSources(themeDisplay.getSiteGroupId(), displayTerms.getKeywords(), displayTerms.getKeywords(), false, searchContainer.getStart(), searchContainer.getEnd(), null));
		}
		%>

		<liferay-ui:search-container-row
			className="com.liferay.reports.model.Source"
			keyProperty="sourceId"
			modelVar="source"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcPath" value="/admin/data_source/edit_data_source.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="sourceId" value="<%= String.valueOf(source.getSourceId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="source-name"
				value="<%= source.getName(locale) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="jdbc-url"
				property="driverUrl"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="jdbc-user-name"
				property="driverUserName"
			/>

			<liferay-ui:search-container-column-jsp
				path="/admin/data_source/data_source_actions.jsp"
			/>
		</liferay-ui:search-container-row>

		<div class="separator"><!-- --></div>

		<aui:button-row cssClass="search-buttons">
			<portlet:renderURL var="addSourceURL">
				<portlet:param name="mvcPath" value="/admin/data_source/edit_data_source.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</portlet:renderURL>

			<aui:button href="<%= addSourceURL %>" value="add-source" />
		</aui:button-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "sources"), currentURL);
%>