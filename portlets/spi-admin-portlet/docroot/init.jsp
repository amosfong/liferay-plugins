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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.servlet.ServletContextPool" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.KeyValuePair" %><%@
page import="com.liferay.portal.kernel.util.KeyValuePairComparator" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Tuple" %><%@
page import="com.liferay.portal.kernel.util.UnicodeProperties" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.model.Portlet" %><%@
page import="com.liferay.portal.resiliency.spi.DuplicateSPIDefinitionConnectorException" %><%@
page import="com.liferay.portal.resiliency.spi.DuplicateSPIDefinitionException" %><%@
page import="com.liferay.portal.resiliency.spi.InvalidSPIDefinitionConnectorException" %><%@
page import="com.liferay.portal.resiliency.spi.SPIDefinitionActiveException" %><%@
page import="com.liferay.portal.resiliency.spi.model.SPIDefinition" %><%@
page import="com.liferay.portal.resiliency.spi.service.SPIDefinitionServiceUtil" %><%@
page import="com.liferay.portal.resiliency.spi.service.permission.SPIDefinitionPermissionUtil" %><%@
page import="com.liferay.portal.resiliency.spi.util.ActionKeys" %><%@
page import="com.liferay.portal.resiliency.spi.util.SPIAdminConstants" %><%@
page import="com.liferay.portal.resiliency.spi.util.SPIAdminUtil" %><%@
page import="com.liferay.portal.resiliency.spi.util.SPIConfigurationTemplate" %><%@
page import="com.liferay.portal.resiliency.spi.util.WebKeys" %><%@
page import="com.liferay.portal.service.PortletLocalServiceUtil" %><%@
page import="com.liferay.portal.service.PortletPreferencesLocalServiceUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portal.util.PortletKeys" %><%@
page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.List" %><%@
page import="java.util.Set" %>

<%@ page import="javax.portlet.PortletPreferences" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<%@ page contentType="text/html; charset=UTF-8" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);

WindowState windowState = liferayPortletRequest.getWindowState();

PortletPreferences preferences = PortletPreferencesLocalServiceUtil.getPreferences(
		0, themeDisplay.getScopeGroupId(), PortletKeys.PREFS_OWNER_TYPE_GROUP,
		PortletKeys.PREFS_PLID_SHARED, PortalUtil.getPortletId(request));

String portletResource = ParamUtil.getString(request, "portletResource");
%>