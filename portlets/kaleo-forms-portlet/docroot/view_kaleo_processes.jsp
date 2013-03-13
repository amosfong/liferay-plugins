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
String tabs1 = ParamUtil.getString(request, "tabs1");
%>

<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="toolbarItem" value="view-all" />
</liferay-util:include>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	searchContainer="<%= new KaleoProcessSearch(renderRequest, iteratorURL) %>"
>
	<liferay-ui:search-container-results
		results="<%= KaleoProcessServiceUtil.getKaleoProcesses(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
		total="<%= KaleoProcessServiceUtil.getKaleoProcessesCount(scopeGroupId) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"
		modelVar="process"
	>

		<portlet:renderURL var="rowURL">
			<portlet:param name="mvcPath" value='<%= "/view_kaleo_process.jsp" %>' />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="kaleoProcessId" value="<%= String.valueOf(process.getKaleoProcessId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			href="<%= rowURL %>"
			name="name"
		>

			<%
			buffer.append(process.getName(locale));
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/kaleo_process_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<div class="aui-helper-hidden" id="<portlet:namespace />export-process">
	<aui:select label="file-extension" name="fileExtension">
		<aui:option value="csv">CSV</aui:option>
		<aui:option value="xml">XML</aui:option>
	</aui:select>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />exportKaleoProcess',
		function(url) {
			var A = AUI();

			var form = A.Node.create('<form method="post" />');

			var content = A.one('#<portlet:namespace />export-process');

			if (content) {
				form.append(content);

				content.show();
			}

			var dialog = new A.Dialog(
				{
					bodyContent: form,
					buttons: [
						{
							handler: function() {
								submitForm(form, url, false);
							},
							label: Liferay.Language.get('ok')
						},
						{
							handler: function() {
								this.close();
							},
							label: Liferay.Language.get('cancel')
						}
					],
					centered: true,
					modal: true,
					title: '<liferay-ui:message key="export" />',
					width: 400
				}
			).render();
		},
		['aui-dialog']
	);
</aui:script>