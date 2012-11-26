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

<portlet:renderURL var="searchSourcesURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="sources" />
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
</portlet:renderURL>

<%
String backURL = ParamUtil.getString(request, "backURL", searchSourcesURL);

Source source = null;

long sourceId = ParamUtil.getLong(request, "sourceId");

if (sourceId > 0) {
	source = SourceLocalServiceUtil.getSource(sourceId);
}
%>

<aui:script>
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
		},
		['aui-dialog', 'aui-io']
	);
</aui:script>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (source == null) ? "new-data-source" : source.getName(locale) %>'
/>

<portlet:actionURL name="editDataSource" var="actionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/data_source/edit_data_source.jsp" />
	<portlet:param name="redirect" value="<%= searchSourcesURL %>" />
</portlet:actionURL>

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (source == null) ? Constants.ADD : Constants.UPDATE %>" />
	<c:if test="<%= (source != null) %>">
		<aui:input name="sourceId" type="hidden" value="<%= source.getSourceId() %>" />
	</c:if>

	<liferay-ui:error exception="<%= SourceDriverClassNameException.class %>" message="please-enter-a-valid-data-source-driver" />
	<liferay-ui:error exception="<%= SourceLoginException.class %>" message="please-enter-a-valid-user-name" />
	<liferay-ui:error exception="<%= SourceNameException.class %>" message="please-enter-a-valid-data-source-name" />
	<liferay-ui:error exception="<%= SourceTypeException.class %>" message="please-enter-a-valid-data-source-type" />
	<liferay-ui:error exception="<%= SourceURLException.class %>" message="please-enter-a-valid-data-source-url" />

	<aui:fieldset>
		<aui:field-wrapper label="data-source-name">
			<liferay-ui:input-localized name="name" xml='<%= BeanPropertiesUtil.getString(source, "name") %>' />
		</aui:field-wrapper>

		<aui:input label="jdbc-driver-class-name" name="driverClassName" value="<%= (source == null) ? StringPool.BLANK : source.getDriverClassName() %>" />

		<aui:input label="jdbc-url" name="driverUrl" value="<%= (source == null) ? StringPool.BLANK : source.getDriverUrl() %>" />

		<aui:input label="jdbc-user-name" name="driverUserName" value="<%= (source == null) ? StringPool.BLANK : source.getDriverUserName() %>" />

		<aui:input autocomplete="off" label="jdbc-password" name="driverPassword" type="password" />

		<c:if test="<%= source == null %>">
			<aui:field-wrapper label="permissions">
				<liferay-ui:input-permissions modelName="<%= Source.class.getName() %>" />
			</aui:field-wrapper>
		</c:if>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= searchSourcesURL %>" type="cancel" />

			<%
			String taglibOnClick = renderResponse.getNamespace() + "testDatabaseConnection();";
			%>

			<aui:button name="testDatabaseConnectionButton" onClick="<%= taglibOnClick %>" value="test-database-connection" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>