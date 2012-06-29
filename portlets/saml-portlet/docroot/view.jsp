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

<%@ page import="com.liferay.portal.kernel.util.PropsUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.saml.util.PortletPropsKeys" %>
<%@ page import="com.liferay.saml.util.SamlUtil" %>

<%
User user = PortalUtil.getUser(request);

String entityId = PropsUtil.get(PortletPropsKeys.SAML_ENTITY_ID);
String keepAliveURL = PropsUtil.get(PortletPropsKeys.SAML_IDP_METADATA_SESSION_KEEP_ALIVE_URL);

if (SamlUtil.isEnabled() && SamlUtil.isRoleSp() && Validator.isNotNull(keepAliveURL) && !user.isDefaultUser()) {
%>

	<script src="<%= keepAliveURL %>?entityId=<%= entityId %>" type="text/javascript"></script>

<%
}
%>