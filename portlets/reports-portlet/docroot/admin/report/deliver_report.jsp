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
String fileName = ParamUtil.getString(request, "fileName");
%>

<portlet:renderURL var="backURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/report/requested_report_detail.jsp" />
	<portlet:param name="entryId" value="<%= String.valueOf(entryId) %>" />
</portlet:renderURL>

<portlet:actionURL name="deliverReport" var="actionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="redirect" value="<%= backURL %>" />
	<portlet:param name="entryId" value="<%= String.valueOf(entryId) %>" />
	<portlet:param name="fileName" value="<%= fileName %>" />
</portlet:actionURL>

<portlet:actionURL name="editDataSource" var="editDataSourceURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/data_source/edit_data_source.jsp" />
</portlet:actionURL>

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<aui:input name="entryId" type="hidden" value="<%= entryId %>" />

	<liferay-ui:error exception="<%= EntryEmailDeliveryException.class %>" message="please-enter-a-valid-email-address" />

	<aui:fieldset>
		<aui:field-wrapper label="report-name">
			<%= StringUtil.extractLast(fileName, StringPool.FORWARD_SLASH) %>
		</aui:field-wrapper>

		<aui:input label="email-recipient" name="emailAddresses" type="text" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="deliver" />

		<aui:button href="<%= backURL %>" type="cancel" />
	</aui:button-row>
</aui:form>