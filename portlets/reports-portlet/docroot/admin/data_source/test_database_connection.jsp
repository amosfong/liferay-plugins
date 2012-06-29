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

<%
long sourceId = ParamUtil.getLong(request, "sourceId", 0);
String driverClassName = ParamUtil.getString(request, "driverClassName");
String driverUrl = ParamUtil.getString(request, "driverUrl");
String driverUserName = ParamUtil.getString(request, "driverUserName");
String driverPassword = ParamUtil.getString(request, "driverPassword");

if (Validator.isNull(driverPassword) && (sourceId > 0)) {
	Source source = SourceLocalServiceUtil.getSource(sourceId);
	driverPassword = source.getDriverPassword();
}

%>

<c:choose>
	<c:when test="<%= ReportsUtil.validateJDBCConnection(driverClassName, driverUrl, driverUserName, driverPassword) %>">
		<div class="portlet-msg-success">
			<liferay-ui:message key="you-have-successfully-connected-to-the-database" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error">
			<liferay-ui:message key="could-not-connect-to-the-database.-please-verify-that-the-settings-are-correct" />
		</div>
	</c:otherwise>
</c:choose>