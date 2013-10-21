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
String redirect = ParamUtil.getString(request, "redirect");

long oAuthApplicationId = ParamUtil.getLong(request, "oAuthApplicationId");

OAuthApplication oAuthApplication = OAuthApplicationLocalServiceUtil.getOAuthApplication(oAuthApplicationId);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	localizeTitle="<%= false %>"
	title="<%= oAuthApplication.getName() %>"
/>

<aui:layout>
	<aui:column columnWidth="<%= (oAuthApplication.getLogoId() != 0) ? 50 : 100 %>">
		<c:if test="<%= Validator.isNotNull(oAuthApplication.getDescription()) %>">
			<aui:field-wrapper label="description">
				<%= HtmlUtil.escape(oAuthApplication.getDescription()) %>
			</aui:field-wrapper>
		</c:if>

		<aui:field-wrapper label="access-type">
			<liferay-ui:message key="<%= oAuthApplication.getAccessLevelLabel() %>" />
		</aui:field-wrapper>

		<aui:field-wrapper label="website-url">
			<%= HtmlUtil.escape(oAuthApplication.getWebsiteURL()) %>
		</aui:field-wrapper>

		<aui:field-wrapper label="callback-uri">
			<%= HtmlUtil.escape(oAuthApplication.getCallbackURI()) %>
		</aui:field-wrapper>

		<aui:field-wrapper label="application-credentials">
			<liferay-ui:message key="consumer-key" />:

			<%= HtmlUtil.escape(oAuthApplication.getConsumerKey()) %><br />

			<liferay-ui:message key="consumer-secret" />:

			<%= HtmlUtil.escape(oAuthApplication.getConsumerSecret()) %>
		</aui:field-wrapper>
	</aui:column>

	<c:if test="<%= oAuthApplication.getLogoId() != 0 %>">
		<aui:column columnWidth="50">
			<img src="<%= HtmlUtil.escape(themeDisplay.getPathImage() + "/logo?img_id=" + oAuthApplication.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(oAuthApplication.getLogoId())) %>" />
		</aui:column>
	</c:if>
</aui:layout>