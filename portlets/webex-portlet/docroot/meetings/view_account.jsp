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
String backURL = ParamUtil.getString(request, "redirect");

long webExAccountId = ParamUtil.getLong(request, "webExAccountId");

WebExAccount webExAccount = WebExAccountServiceUtil.getWebExAccount(webExAccountId);

request.setAttribute("view_account.jsp-webExAccount", webExAccount);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title="<%= webExAccount.getLogin() %>"
/>

<aui:layout cssClass="account">
	<aui:column columnWidth="<%= 75 %>" cssClass="folder-column folder-column-first" first="<%= true %>">
		<dl class="property-list">
			<dt>
				<liferay-ui:message key="site-name" />:
			</dt>
			<dd>

				<%
				WebExSite webExSite = webExAccount.getWebExSite();
				%>

				<%= HtmlUtil.escape(webExSite.getName()) %>
			</dd>
			<dt>
				<liferay-ui:message key="account-login" />:
			</dt>
			<dd>
				<%= HtmlUtil.escape(webExAccount.getLogin()) %>
			</dd>
		</dl>

		<%@ include file="/meetings/meetings.jspf" %>
	</aui:column>

	<aui:column columnWidth="<%= 25 %>" cssClass="detail-column detail-column-last" last="<%= true %>">
		<div class="folder-icon">
			<liferay-ui:icon
				cssClass="folder-avatar"
				image="../file_system/large/spreadsheet"
				message='<%= LanguageUtil.get(pageContext, "account") %>'
			/>

			<div class="account-name">
				<h4><%= HtmlUtil.escape(webExAccount.getLogin()) %></h4>
			</div>
		</div>

		<%
		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		%>

		<liferay-util:include page="/meetings/account_action.jsp" servletContext="<%= application %>" />
	</aui:column>
</aui:layout>