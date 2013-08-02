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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.bi.reporting.ReportDataSourceType" %><%@
page import="com.liferay.portal.kernel.bi.reporting.ReportFormat" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.DisplayTerms" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.model.User" %><%@
page import="com.liferay.portal.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portlet.calendar.EventDurationException" %><%@
page import="com.liferay.portlet.calendar.EventStartDateException" %><%@
page import="com.liferay.portlet.calendar.EventTitleException" %><%@
page import="com.liferay.reports.DefinitionFileException" %><%@
page import="com.liferay.reports.DefinitionNameException" %><%@
page import="com.liferay.reports.EntryEmailDeliveryException" %><%@
page import="com.liferay.reports.EntryEmailNotificationsException" %><%@
page import="com.liferay.reports.ReportStatus" %><%@
page import="com.liferay.reports.SourceDriverClassNameException" %><%@
page import="com.liferay.reports.SourceJDBCConnectionException" %><%@
page import="com.liferay.reports.SourceLoginException" %><%@
page import="com.liferay.reports.SourceNameException" %><%@
page import="com.liferay.reports.SourceTypeException" %><%@
page import="com.liferay.reports.SourceURLException" %><%@
page import="com.liferay.reports.admin.util.AdminUtil" %><%@
page import="com.liferay.reports.model.Definition" %><%@
page import="com.liferay.reports.model.Entry" %><%@
page import="com.liferay.reports.model.Source" %><%@
page import="com.liferay.reports.service.DefinitionLocalServiceUtil" %><%@
page import="com.liferay.reports.service.DefinitionServiceUtil" %><%@
page import="com.liferay.reports.service.EntryLocalServiceUtil" %><%@
page import="com.liferay.reports.service.EntryServiceUtil" %><%@
page import="com.liferay.reports.service.SourceLocalServiceUtil" %><%@
page import="com.liferay.reports.service.SourceServiceUtil" %><%@
page import="com.liferay.reports.service.permission.ActionKeys" %><%@
page import="com.liferay.reports.service.permission.AdminPermission" %><%@
page import="com.liferay.reports.service.permission.DefinitionPermission" %><%@
page import="com.liferay.reports.service.permission.EntryPermission" %><%@
page import="com.liferay.reports.service.permission.SourcePermission" %><%@
page import="com.liferay.reports.util.PortletKeys" %><%@
page import="com.liferay.reports.util.ReportsUtil" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Date" %><%@
page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);

WindowState windowState = WindowState.NORMAL;

if (renderRequest != null) {
	windowState = renderRequest.getWindowState();
}
else if (resourceRequest != null) {
	windowState = resourceRequest.getWindowState();
}

Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

String languageId = LanguageUtil.getLanguageId(request);
%>