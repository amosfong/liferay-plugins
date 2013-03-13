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

long auditEventId = ParamUtil.getLong(request, "auditEventId");

AuditEvent auditEvent = null;

try {
	auditEvent = AuditEventLocalServiceUtil.getAuditEvent(auditEventId);
}
catch (NoSuchEventException nsee) {
}
%>

<liferay-ui:tabs
	backURL="<%= redirect %>"
	names="event"
/>

<c:choose>
	<c:when test="<%= auditEvent == null %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="the-event-could-not-be-found" />
		</div>
	</c:when>
	<c:otherwise>
		<table class="lfr-table">
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="event-id" /></strong>
			</td>
			<td width="99%">
				<%= auditEvent.getAuditEventId() %>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="create-date" /></strong>
			</td>
			<td>
				<%= dateFormatDateTime.format(auditEvent.getCreateDate()) %>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="separator"><!-- --></div>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="user-id" /></strong>
			</td>
			<td>
				<%= auditEvent.getUserId() %>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="user-name" /></strong>
			</td>
			<td>
				<%= auditEvent.getUserName() %>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="separator"><!-- --></div>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="resource-id" /></strong>
			</td>
			<td>
				<%= auditEvent.getClassPK() %>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="resource-name" /></strong>
			</td>
			<td>
				<%= (String)PortalClassInvoker.invoke(false, new MethodKey(ClassResolverUtil.resolve("com.liferay.portal.security.permission.ResourceActionsUtil", PortalClassLoaderUtil.getClassLoader()), "getModelResource", PageContext.class, String.class), pageContext, auditEvent.getClassName()) %>

				(<%= auditEvent.getClassName() %>)
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="resource-action" /></strong>
			</td>
			<td>
				<%= (String)PortalClassInvoker.invoke(false, new MethodKey(ClassResolverUtil.resolve("com.liferay.portal.security.permission.ResourceActionsUtil", PortalClassLoaderUtil.getClassLoader()), "getAction", PageContext.class, String.class), pageContext, auditEvent.getEventType()) %>

				(<%= auditEvent.getEventType() %>)
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<div class="separator"><!-- --></div>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="client-host" /></strong>
			</td>
			<td>
				<%= Validator.isNotNull(auditEvent.getClientHost()) ? auditEvent.getClientHost() : LanguageUtil.get(pageContext, "none") %>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="client-ip" /></strong>
			</td>
			<td>
				<%= Validator.isNotNull(auditEvent.getClientIP()) ? auditEvent.getClientIP() : LanguageUtil.get(pageContext, "none") %>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="server-name" /></strong>
			</td>
			<td>
				<%= Validator.isNotNull(auditEvent.getServerName()) ? auditEvent.getServerName() : LanguageUtil.get(pageContext, "none") %>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="session-id" /></strong>
			</td>
			<td>
				<%= Validator.isNotNull(auditEvent.getSessionID()) ? auditEvent.getSessionID() : LanguageUtil.get(pageContext, "none") %>
			</td>
		</tr>
		<tr>
			<td class="nobr">
				<strong><liferay-ui:message key="additional-information" /></strong>
			</td>
			<td>
				<%= Validator.isNotNull(auditEvent.getAdditionalInfo()) ? auditEvent.getAdditionalInfo() : LanguageUtil.get(pageContext, "none") %>
			</td>
		</tr>
		</table>
	</c:otherwise>
</c:choose>