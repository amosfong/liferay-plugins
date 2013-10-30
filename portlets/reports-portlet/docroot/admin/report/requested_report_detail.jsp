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
long entryId = ParamUtil.getLong(request, "entryId", -1);

Entry entry = EntryLocalServiceUtil.getEntry(entryId);

Definition definition = DefinitionLocalServiceUtil.getDefinition(entry.getDefinitionId());

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", "reports");
portletURL.setParameter("mvcPath", "/admin/view.jsp");
portletURL.setWindowState(WindowState.NORMAL);
%>

<portlet:renderURL var="searchRequestURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
	<portlet:param name="tabs1" value="reports" />
</portlet:renderURL>

<liferay-ui:header
	backURL="<%= searchRequestURL %>"
	title="<%= definition.getName(locale) %>"
/>

<%
String status = entry.getStatus();
%>

<c:choose>
	<c:when test="<%= status.equals(ReportStatus.ERROR.getValue()) %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="an-error-occurred-while-processing-the-report" />
		</div>
	</c:when>
	<c:when test="<%= status.equals(ReportStatus.PENDING.getValue()) %>">
		<div class="portlet-msg-info">
			<liferay-ui:message key="processing-report.-this-may-take-several-minutes" />
		</div>
	</c:when>
</c:choose>

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
		Source source = SourceLocalServiceUtil.fetchSource(definition.getSourceId());
		%>

		<%= (source == null) ? ReportDataSourceType.PORTAL.getValue() : source.getName(locale) %>
	</aui:field-wrapper>

	<aui:field-wrapper label="report-parameters">

		<%
		for (String reportParameter : StringUtil.split(entry.getReportParameters())) {
		%>

			<%= Validator.isNull(reportParameter) ? StringPool.BLANK : reportParameter %>

		<%
		}
		%>

	</aui:field-wrapper>

	<c:if test="<%= entry.isScheduleRequest() %>">
		<aui:field-wrapper label="is-schedule-request">

				<%
				StringBundler sb = new StringBundler((entry.getEndDate() != null) ? 18 : 12);

				sb.append("<br />");
				sb.append(LanguageUtil.get(pageContext, "scheduler-from"));
				sb.append(StringPool.BLANK);
				sb.append(StringPool.COLON);
				sb.append(StringPool.BLANK);
				sb.append(dateFormatDateTime.format(entry.getStartDate()));

				if (entry.getEndDate() != null) {
					sb.append("<br />");
					sb.append(LanguageUtil.get(pageContext, "scheduler-to"));
					sb.append(StringPool.BLANK);
					sb.append(StringPool.COLON);
					sb.append(StringPool.BLANK);
					sb.append(dateFormatDateTime.format(entry.getEndDate()));
				}

				sb.append("<br />");
				sb.append(LanguageUtil.get(pageContext, "scheduler-crontext"));
				sb.append(StringPool.BLANK);
				sb.append(StringPool.COLON);
				sb.append(StringPool.BLANK);
				sb.append(entry.getRecurrence());
				%>

				<%= sb.toString() %>
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

List<String> attachmentsFiles = Arrays.asList(entry.getAttachmentsFiles());

request.setAttribute("entry", entry);
%>

<liferay-ui:search-container
	delta="2"
	iteratorURL="<%= portletURL %>"
	searchContainer="<%= new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null) %>"
	total="<%= attachmentsFiles.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= ListUtil.subList(attachmentsFiles, searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="java.lang.String"
		modelVar="fileName"
	>
		<liferay-ui:search-container-column-text
			name="file"
			value="<%= StringUtil.extractLast(fileName, StringPool.SLASH) %>"
		/>

		<liferay-ui:search-container-column-jsp
			path="/admin/report/report_file_actions.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>