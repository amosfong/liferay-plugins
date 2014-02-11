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
String tabs2 = ParamUtil.getString(request, "tabs2", "email-from");

String emailFromName = ParamUtil.getString(request, "emailFromName", AdminUtil.getEmailFromName(portletPreferences));
String emailFromAddress = ParamUtil.getString(request, "emailFromAddress", AdminUtil.getEmailFromAddress(portletPreferences));

String emailDeliverySubject = ParamUtil.getString(request, "emailDeliverySubject", AdminUtil.getEmailDeliverySubject(portletPreferences));
String emailDeliveryBody = ParamUtil.getString(request, "emailDeliveryBody", AdminUtil.getEmailDeliveryBody(portletPreferences));

String emailNotificationsSubject = ParamUtil.getString(request, "emailNotificationsSubject", AdminUtil.getEmailNotificationsSubject(portletPreferences));
String emailNotificationsBody = ParamUtil.getString(request, "emailNotificationsBody", AdminUtil.getEmailNotificationsBody(portletPreferences));

String editorParam = StringPool.BLANK;
String editorBody = StringPool.BLANK;

if (tabs2.equals("delivery-email")) {
	editorParam = "emailDeliveryBody";
	editorBody = emailDeliveryBody;
}
else if (tabs2.equals("notifications-email")) {
	editorParam = "emailNotificationsBody";
	editorBody = emailNotificationsBody;
}
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL">
	<portlet:param name="tabs2" value="<%= tabs2 %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names="email-from,delivery-email,notifications-email"
	param="tabs2"
	url="<%= configurationRenderURL %>"
/>

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />

	<liferay-ui:error key="emailDeliveryBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailDeliverySubject" message="please-enter-a-valid-subject" />
	<liferay-ui:error key="emailFromAddress" message="please-enter-a-valid-email-address" />
	<liferay-ui:error key="emailFromName" message="please-enter-a-valid-name" />
	<liferay-ui:error key="emailNotificationsBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailNotificationsSubject" message="please-enter-a-valid-subject" />

	<aui:fieldset>
		<c:choose>
			<c:when test='<%= tabs2.equals("email-from") %>'>
				<aui:input cssClass="lfr-input-text-container" label="name" name="preferences--emailFromName--" value="<%= emailFromName %>" />

				<aui:input cssClass="lfr-input-text-container" label="address" name="preferences--emailFromAddress--" value="<%= emailFromAddress %>" />
			</c:when>
			<c:otherwise>
				<c:choose>
					<c:when test='<%= tabs2.equals("delivery-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject" name="preferences--emailDeliverySubject--" value="<%= emailDeliverySubject %>" />
					</c:when>
					<c:when test='<%= tabs2.equals("notifications-email") %>'>
						<aui:input cssClass="lfr-input-text-container" label="subject" name="preferences--emailNotificationsSubject--" value="<%= emailNotificationsSubject %>" />
					</c:when>
				</c:choose>

				<aui:input cssClass="lfr-textarea-container" label="body" name='<%= "preferences--" + editorParam + "--" %>' type="textarea" value="<%= editorBody %>" />

				<div class="definition-of-terms">
					<h4><liferay-ui:message key="definition-of-terms" /></h4>

					<dl>
						<dt>
							[$FROM_ADDRESS$]
						</dt>
						<dd>
							<%= HtmlUtil.escape(emailFromAddress) %>
						</dd>
						<dt>
							[$FROM_NAME$]
						</dt>
						<dd>
							<%= HtmlUtil.escape(emailFromName) %>
						</dd>
						<dt>
							[$PAGE_URL$]
						</dt>
						<dd>
							<liferay-ui:message key="the-report-url" />
						</dd>
						<dt>
							[$REPORT_NAME$]
						</dt>
						<dd>
							<liferay-ui:message key="the-name-of-the-report" />
						</dd>
					</dl>
				</div>
			</c:otherwise>
		</c:choose>

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>