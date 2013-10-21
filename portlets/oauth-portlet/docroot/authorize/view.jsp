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
String oAuthVerifier = (String)request.getAttribute(net.oauth.OAuth.OAUTH_VERIFIER);

String oAuthCallback = ParamUtil.getString(request, net.oauth.OAuth.OAUTH_CALLBACK);

OAuthMessage oAuthMessage = OAuthUtil.getOAuthMessage(request, currentURL);

OAuthAccessor oAuthAccessor = OAuthUtil.getOAuthAccessor(oAuthMessage);

OAuthApplication oAuthApplication = null;

if (oAuthAccessor != null) {
	OAuthConsumer oAuthConsumer = oAuthAccessor.getOAuthConsumer();

	oAuthApplication = oAuthConsumer.getOAuthApplication();
}
%>

<c:choose>
	<c:when test="<%= (oAuthAccessor != null) && (oAuthApplication != null) %>">
		<c:if test="<%= Validator.isNotNull(oAuthVerifier) %>">
			<div class="portlet-msg-info">
				<liferay-ui:message arguments="<%= oAuthVerifier %>" key="authorization-was-successful.-verification-code-is-x" translateArguments="<%= false %>" />
			</div>
		</c:if>

		<portlet:actionURL name="authorize" var="authorizeURL" />

		<aui:form action="<%= authorizeURL %>" method="post" name="fm">
			<aui:input name="mvcPath" type="hidden" value="/authorize/view.jsp" />
			<aui:input name="<%= net.oauth.OAuth.OAUTH_CALLBACK %>" type="hidden" value="<%= oAuthCallback %>" />
			<aui:input name="<%= net.oauth.OAuth.OAUTH_TOKEN %>" type="hidden" value="<%= oAuthAccessor.getRequestToken() %>" />

			<aui:layout>
				<aui:column columnWidth="<%= (oAuthApplication.getLogoId() != 0) ? 50 : 100 %>">
					<liferay-ui:message key="the-application-listed-below-is-requesting-access-to-your-account" />

					<h3>
						<aui:a href="<%= oAuthApplication.getWebsiteURL() %>" label="<%= HtmlUtil.escape(oAuthApplication.getName()) %>" target="_blank" />

						<c:if test="<%= Validator.isNotNull(oAuthApplication.getDescription()) %>">
							<liferay-ui:icon-help message="<%= HtmlUtil.escape(oAuthApplication.getDescription()) %>" />
						</c:if>
					</h3>

					<liferay-ui:message key="if-you-grant-access,-this-application-will-be-able-to-do-the-following" />

					<ul>
						<li>
							<liferay-ui:message key="read-data-on-your-behalf" />
						</li>

						<c:if test="<%= oAuthApplication.getAccessLevel() == OAuthApplicationConstants.ACCESS_WRITE %>">
							<li>
								<liferay-ui:message key="write-data-on-your-behalf" />
							</li>
						</c:if>
					</ul>
				</aui:column>

				<c:if test="<%= oAuthApplication.getLogoId() != 0 %>">
					<aui:column columnWidth="50">
						<img src="<%= HtmlUtil.escape(themeDisplay.getPathImage() + "/logo?img_id=" + oAuthApplication.getLogoId() + "&t=" + WebServerServletTokenUtil.getToken(oAuthApplication.getLogoId())) %>" />
					</aui:column>
				</c:if>
			</aui:layout>

			<aui:button-row>
				<aui:button type="submit" value="grant-access" />

				<%
				String taglibOnClick = "document.location = '".concat(HtmlUtil.escape(oAuthApplication.getWebsiteURL())).concat("'");
				%>

				<aui:button onClick="<%= taglibOnClick %>" value="deny-access" />
			</aui:button-row>
		</aui:form>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error">
			<liferay-ui:message key="your-request-failed-to-complete" />
		</div>
	</c:otherwise>
</c:choose>