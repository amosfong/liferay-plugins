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

<%@ include file="/admin/report/init.jsp" %>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />

	<liferay-portlet:renderURL varImpl="iteratorURL">
		<portlet:param name="definitionName" value="<%= definitionName %>" />
		<portlet:param name="endDateDay" value="<%= String.valueOf(endDateDay) %>" />
		<portlet:param name="endDateMonth" value="<%= String.valueOf(endDateMonth) %>" />
		<portlet:param name="endDateYear" value="<%= String.valueOf(endDateYear) %>" />
		<portlet:param name="startDateDay" value="<%= String.valueOf(startDateDay) %>" />
		<portlet:param name="startDateMonth" value="<%= String.valueOf(startDateMonth) %>" />
		<portlet:param name="startDateYear" value="<%= String.valueOf(startDateYear) %>" />
		<portlet:param name="userName" value="<%= userName %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:search-container
		displayTerms="<%= new DisplayTerms(renderRequest) %>"
		emptyResultsMessage="there-are-no-entries"
		headerNames="definition-name,requested-by,create-date"
		iteratorURL="<%= iteratorURL %>"
	>
		<liferay-ui:search-form
			page="/admin/report/entry_search.jsp"
			servletContext="<%= application %>"
		/>

		<liferay-ui:search-container-results>

			<%
			Date startDate = PortalUtil.getDate(startDateMonth, startDateDay, startDateYear, timeZone, null);
			Date endDate = PortalUtil.getDate(endDateMonth, endDateDay + 1, endDateYear, timeZone, null);

			DisplayTerms displayTerms = searchContainer.getDisplayTerms();

			if (displayTerms.isAdvancedSearch()) {
				results = EntryServiceUtil.getEntries(themeDisplay.getParentGroupId(), definitionName, null, startDate, endDate, displayTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd(), null);
				total = EntryServiceUtil.getEntriesCount(themeDisplay.getParentGroupId(), definitionName, null, startDate, endDate, displayTerms.isAndOperator());
			}
			else {
				results = EntryServiceUtil.getEntries(themeDisplay.getParentGroupId(), displayTerms.getKeywords(), null, null, null, false, searchContainer.getStart(), searchContainer.getEnd(), null);
				total = EntryServiceUtil.getEntriesCount(themeDisplay.getParentGroupId(), displayTerms.getKeywords(), null, null, null, false);
			}

			pageContext.setAttribute("results", results);
			pageContext.setAttribute("total", total);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.reports.model.Entry"
			keyProperty="entryId"
			modelVar="entry"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcPath" value="/admin/report/requested_report_detail.jsp" />
				<portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
			</liferay-portlet:renderURL>

			<%
			String name = StringPool.BLANK;

			try{
				Definition definition = DefinitionLocalServiceUtil.getDefinition(entry.getDefinitionId());

				name = definition.getName(locale);
			}
			catch (NoSuchDefinitionException nsde) {
				name = "definition doesn't exist";
			}
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="definition-name"
				value="<%= name %>"
			/>

			<%
			User requestedUesr = UserLocalServiceUtil.getUser(entry.getUserId());
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="source-name"
				value="<%= requestedUesr.getScreenName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="report-generation-date"
				value="<%= dateFormatDateTime.format(entry.getCreateDate()) %>"
			/>

			<liferay-ui:search-container-column-jsp
				path="/admin/report/requested_report_actions.jsp"
			/>
		</liferay-ui:search-container-row>

		<div class="separator"><!-- --></div>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, "reports"), currentURL);
%>