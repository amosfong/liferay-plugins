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

<%@ include file="/admin/init.jsp" %>

<liferay-util:include page="/admin/toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="toolbarItem" value="view-all" />
</liferay-util:include>

<liferay-portlet:renderURL varImpl="iteratorURL" />

<liferay-ui:search-container
	searchContainer="<%= new KaleoProcessSearch(renderRequest, iteratorURL) %>"
	total="<%= KaleoProcessServiceUtil.getKaleoProcessesCount(scopeGroupId) %>"
>
	<liferay-ui:search-container-results
		results="<%= KaleoProcessServiceUtil.getKaleoProcesses(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"
		modelVar="process"
	>

		<portlet:renderURL var="rowURL">
			<portlet:param name="mvcPath" value='<%= "/admin/view_kaleo_process.jsp" %>' />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="kaleoProcessId" value="<%= String.valueOf(process.getKaleoProcessId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			href="<%= rowURL %>"
			name="name"
		>

			<%
			buffer.append(HtmlUtil.escape(process.getName(locale)));
			%>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/kaleo_process_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>

<div class="hide" id="<portlet:namespace />export-process">
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

			var dialog = Liferay.Util.Window.getWindow(
				{
					dialog: {
						bodyContent: form,
						toolbars: {
							footer: [
								{
									label: '<%= UnicodeLanguageUtil.get(pageContext, "ok") %>',
									on: {
										click: function() {
											submitForm(form, url, false);
										}
									}
								},
								{
									label: '<%= UnicodeLanguageUtil.get(pageContext, "cancel") %>',
									on: {
										click: function() {
											dialog.hide();
										}
									}
								}
							]
						}
					},
					title: '<%= UnicodeLanguageUtil.get(pageContext, "export") %>'
				}
			)
		},
		['liferay-util-window']
	);
</aui:script>