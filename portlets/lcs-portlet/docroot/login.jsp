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

<h3><liferay-ui:message arguments="1" key="registration-step-x-2" /></h3>

<portlet:actionURL name="setupOAuth" var="setupOAuthURL" />

<%
Token requestToken = OAuthUtil.getRequestToken();

portletSession.setAttribute(Token.class.getName(), requestToken);
%>

<aui:button-row>
	<aui:button cssClass="btn-success" name="authorizeAccess" onClick='<%= renderResponse.getNamespace() + "loadAuthorizeAccess();" %>' value="authorize-access" />
</aui:button-row>

<aui:field-wrapper>
	<liferay-ui:message arguments="https://www.liferay.com/home?p_p_id=58&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_58_struts_action=%2Flogin%2Fforgot_password" key="forgot-your-x-or-x" />

	<liferay-ui:message arguments="https://www.liferay.com/home?p_p_id=58&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&saveLastPath=0&_58_struts_action=%2Flogin%2Fcreate_account" key="you-dont-have-account.-create-a-new-account" />
</aui:field-wrapper>

<aui:script>
	function <portlet:namespace />loadAuthorizeAccess() {
		window.location.href = '<%= OAuthUtil.getAuthorizeURL(setupOAuthURL, requestToken) %>';
	}
</aui:script>