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

<portlet:renderURL var="searchSourcesURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
	<portlet:param name="tabs1" value="sources" />
</portlet:renderURL>

<%
String backURL = ParamUtil.getString(request, "backURL", searchSourcesURL);

long sourceId = ParamUtil.getLong(request, "sourceId");

Source source = SourceLocalServiceUtil.fetchSource(sourceId);

String name = BeanParamUtil.getString(source, request, "name");
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (source == null) ? "new-data-source" : source.getName(locale) %>'
/>

<portlet:actionURL name="editDataSource" var="actionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/data_source/edit_data_source.jsp" />
	<portlet:param name="redirect" value="<%= searchSourcesURL %>" />
</portlet:actionURL>

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<liferay-ui:error exception="<%= SourceDriverClassNameException.class %>" message="please-enter-a-valid-data-source-driver" />
	<liferay-ui:error exception="<%= SourceLoginException.class %>" message="please-enter-a-valid-user-name" />
	<liferay-ui:error exception="<%= SourceNameException.class %>" message="please-enter-a-valid-data-source-name" />
	<liferay-ui:error exception="<%= SourceTypeException.class %>" message="please-enter-a-valid-data-source-type" />
	<liferay-ui:error exception="<%= SourceURLException.class %>" message="please-enter-a-valid-data-source-url" />

	<aui:model-context bean="<%= source %>" model="<%= Source.class %>" />

	<aui:input name="sourceId" type="hidden" />

	<aui:fieldset>
		<aui:field-wrapper label="data-source-name">
			<liferay-ui:input-localized name="name" xml="<%= name %>" />
		</aui:field-wrapper>

		<aui:input label="jdbc-driver-class-name" name="driverClassName" />

		<aui:input label="jdbc-url" name="driverUrl" />

		<aui:input label="jdbc-user-name" name="driverUserName" />

		<aui:input autocomplete="off" label="jdbc-password" name="driverPassword" type="password" />

		<c:if test="<%= source == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions modelName="<%= Source.class.getName() %>" />
			</aui:field-wrapper>
		</c:if>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= searchSourcesURL %>" type="cancel" />

			<aui:button name="testDatabaseConnectionButton" onClick='<%= renderResponse.getNamespace() + "testDatabaseConnection();" %>' value="test-database-connection" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script use="aui-dialog,aui-io">
	Liferay.provide(
		window,
		'<portlet:namespace />testDatabaseConnection',
		function() {
			var A = AUI();

			var url = "<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/admin/data_source/test_database_connection.jsp" /></portlet:renderURL>";

			var data = {};

			<c:if test="<%= (source != null) %>">
				data.<portlet:namespace />sourceId = document.<portlet:namespace />fm['<portlet:namespace />sourceId'].value;
			</c:if>

			data.<portlet:namespace />driverClassName = document.<portlet:namespace />fm['<portlet:namespace />driverClassName'].value;
			data.<portlet:namespace />driverUrl = document.<portlet:namespace />fm['<portlet:namespace />driverUrl'].value;
			data.<portlet:namespace />driverUserName = document.<portlet:namespace />fm['<portlet:namespace />driverUserName'].value;
			data.<portlet:namespace />driverPassword = document.<portlet:namespace />fm['<portlet:namespace />driverPassword'].value;

			if (url != null) {
				var dialog = new A.Dialog(
					{
						centered: true,
						destroyOnClose: true,
						modal: true,
						title: Liferay.Language.get('source'),
						width: 600
					}
				).render();

				dialog.plug(
					A.Plugin.IO,
					{
						data: data,
						uri: url
					}
				);
			}
		}
	);
</aui:script>