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
String webId = ParamUtil.getString(request, "webId", null);
%>

<aui:form name="fm">

	<liferay-ui:error key="authenticationFailed" message="authentication-failed" />

	<c:choose>
		<c:when test="<%= !OAuthUtil.isConsumerReady(portletPreferences) %>">
			<liferay-ui:header title="set-oauth-consumer-credentials" />

			<aui:input name="hostName" required="<%= true %>" value="<%= hostName %>" />
			<aui:input name="hostPort" required="<%= true %>" value="<%= hostPort %>" />
			<aui:input label="access-token-endpoint-uri" name="accessURI" required="<%= true %>" value="<%= accessTokenURI %>" />
			<aui:input label="authorize-token-endpoint-uri" name="authorizeURI" required="<%= true %>" value="<%= authorizeURI %>" />
			<aui:input label="request-token-endpoint-uri" name="requestURI" required="<%= true %>" value="<%= requestTokenURI %>" />
			<aui:input name="key" required="<%= true %>" />
			<aui:input name="secret" required="<%= true %>" />

			<%
			String taglibOnClick = renderResponse.getNamespace() + "setupOAuthConsumer();";
			%>

			<aui:button-row>
				<aui:button onClick="<%= taglibOnClick %>" value="Save" />
			</aui:button-row>
		</c:when>
		<c:when test="<%= !OAuthUtil.isAccessorReady(portletPreferences) %>">
			<%@ include file="/view_oauth_settings.jspf" %>

			<liferay-ui:header title="obtain-oauth-accessor-credentials" />

			<%
			OAuthServiceHandler oAuthServiceHandler = OAuthUtil.getOAuthServiceHandler(portletPreferences);

			Token requestToken = oAuthServiceHandler.getRequestToken();

			portletSession.setAttribute(Token.class.getName(), requestToken);
			%>

			<c:choose>
				<c:when test="<%= requestToken == null %>">
					<liferay-ui:error message="failed-to-generate-valid-request-token.-please-review-oauth-settings" />
				</c:when>
				<c:otherwise>
					<liferay-portlet:actionURL name="setupOAuth" var="setupOAuthURL" />

					<aui:button-row>
						<aui:button href="<%= oAuthServiceHandler.getAuthorizeURL(requestToken, setupOAuthURL) %>" value="authorize-access" />
					</aui:button-row>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<%@ include file="/view_oauth_settings.jspf" %>

			<liferay-ui:header title="execute-platform-specific-task-using-oauth-authorized-request" />

			<%
			String oAuthResult = null;
			OAuthServiceHandler oAuthServiceHandler = OAuthUtil.getOAuthServiceHandler(portletPreferences);

			if (webId != null) {
				LiferayOAuthPlatformTest liferayOAuthPlatformTest = new LiferayOAuthPlatformTest(hostName, hostPort, oAuthServiceHandler);

				oAuthResult = liferayOAuthPlatformTest.getRemotePortalCompany(accessToken, accessSecret, webId);
			}
			%>

			<p>
				<liferay-ui:message key="liferay-portal-oauth-test-(show-remote-portal-information)" /><br />

				<c:choose>
					<c:when test="<%= oAuthResult == null %>">
						<aui:input helpMessage="e.g.-liferay.com" name="webId" />

						<%
						String taglibOnClick = renderResponse.getNamespace() + "testAuthorizedRequest();";
						%>

						<aui:button-row>
							<aui:button onClick="<%= taglibOnClick %>" value="test" />
						</aui:button-row>
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="test-results" />:<br />

						<pre class="lfr-code-block"><%= oAuthResult %></pre>
					</c:otherwise>
				</c:choose>
			</p>
		</c:otherwise>
	</c:choose>

	<hr />

	<div class="button-container">
		<liferay-portlet:actionURL name="resetOAuth" var="resetOAuthURL" />

		<liferay-ui:message arguments="<%= resetOAuthURL %>" key="reset-oauth-settings-to-repeat-the-test" />
	</div>

	<hr />

	<p>
		<liferay-ui:message key="test-oauth-v1.0a-functionality" />
	</p>
</aui:form>

<aui:script>
	function <portlet:namespace />setupOAuthConsumer() {
		document.<portlet:namespace />fm.method = "post";
		submitForm(document.<portlet:namespace />fm, '<liferay-portlet:actionURL name="setupOAuthConsumer" />');
	}

	function <portlet:namespace />testAuthorizedRequest() {
		document.<portlet:namespace />fm.method = "post";
		submitForm(document.<portlet:namespace />fm, '<liferay-portlet:renderURL />');
	}
</aui:script>