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
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setWindowState(WindowState.NORMAL);

portletURL.setParameter("tabs1", "reports");
portletURL.setParameter("mvcPath", "/admin/view.jsp");

long entryId = ParamUtil.getLong(request, "entryId", -1);

Entry entry = EntryLocalServiceUtil.getEntry(entryId);

Definition definition = DefinitionLocalServiceUtil.getDefinition(entry.getDefinitionId());
%>

<portlet:renderURL var="searchRequestURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
	<portlet:param name="tabs1" value="reports" />
</portlet:renderURL>

<liferay-ui:header
	backURL="<%= searchRequestURL %>"
	title="<%= definition.getName(locale) %>"
/>

<c:if test="<%= ReportStatus.PENDING.getValue().equals(entry.getStatus()) %>">
	<div class="portlet-msg-info">
		<liferay-ui:message key="processing-report.-this-may-take-several-minutes" />
	</div>
</c:if>
<c:if test="<%= ReportStatus.ERROR.getValue().equals(entry.getStatus()) %>">
	<div class="portlet-msg-error">
		<liferay-ui:message key="an-error-occurred-while-processing-the-report" />
	</div>
</c:if>

<aui:fieldset>
	<aui:field-wrapper label="requested-report-id">
		<%= entry.getEntryId() %>
	</aui:field-wrapper>

	<aui:field-wrapper label="definition-name">
		<%= definition.getName(locale) %>
	</aui:field-wrapper>

	<aui:field-wrapper label="description">
		<%= definition.getDescription(locale) %>
	</aui:field-wrapper>

	<aui:field-wrapper label="data-source-name">

		<%
		String name = null;

		if (definition.getSourceId() == PortletConstants.PORTAL_DATA_SOURCE_ID) {
			name = String.valueOf(ReportDataSourceType.PORTAL);
		}
		else {
			Source source = SourceLocalServiceUtil.getSource(definition.getSourceId());

			name = source.getName(locale);
		}
		%>

		<%= name %>
	</aui:field-wrapper>

	<aui:field-wrapper label="report-parameters">

		<%
		for (String reportParameter : StringUtil.split(entry.getReportParameters())) {
			if (Validator.isNull(reportParameter)) {
				continue;
			}
		%>

			<%= reportParameter %>

		<%
		}
		%>

	</aui:field-wrapper>

	<c:if test="<%= entry.isScheduleRequest() %>">
		<aui:field-wrapper label="is-schedule-request">

				<%
				StringBuffer repeatingInof = new StringBuffer();
				repeatingInof.append("<br />");
				repeatingInof.append(LanguageUtil.get(pageContext, "scheduler-from")).append(" : ");
				repeatingInof.append(dateFormatDateTime.format(entry.getStartDate()));

				if (entry.getEndDate() != null) {
					repeatingInof.append("<br />");
					repeatingInof.append(LanguageUtil.get(pageContext, "scheduler-to")).append(" : ");
					repeatingInof.append(dateFormatDateTime.format(entry.getEndDate()));
				}

				repeatingInof.append("<br />");
				repeatingInof.append(LanguageUtil.get(pageContext, "scheduler-crontext")).append(" : ");
				repeatingInof.append(entry.getRecurrence());
				%>

				<%= repeatingInof.toString() %>
		</aui:field-wrapper>
	</c:if>

	<aui:field-wrapper label="requested-by">
		<%= entry.getUserName() %>
	</aui:field-wrapper>

	<aui:field-wrapper label="requested-date">
		<%= entry.getCreateDate() %>
	</aui:field-wrapper>

	<aui:field-wrapper label="completion-date">
		<%= entry.getModifiedDate() %>
	</aui:field-wrapper>
</aui:fieldset>

<%
List<String> headerNames = new ArrayList<String>();

headerNames.add("file");
headerNames.add("download");

SearchContainer downloadFileNamesContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

String[] attachmentsFiles = entry.getAttachmentsFiles();

List<String> fileNames = Arrays.asList(attachmentsFiles);

fileNames = ListUtil.subList(fileNames, downloadFileNamesContainer.getStart(), downloadFileNamesContainer.getEnd());

request.setAttribute("entry",entry);
%>

<liferay-ui:search-container delta="2" iteratorURL="<%= portletURL %>">
	<liferay-ui:search-container-results
		results="<%= fileNames %>"
		total="<%= attachmentsFiles.length %>"
	/>

	<liferay-ui:search-container-row className="java.lang.String" modelVar="fileName">
		<liferay-ui:search-container-column-text
			name="file"
			value="<%= StringUtil.extractLast(fileName, StringPool.SLASH) %>"
		/>

		<liferay-ui:search-container-column-jsp
			path="/admin/report/report_file_actions.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator searchContainer="<%= downloadFileNamesContainer %>" />
</liferay-ui:search-container>