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
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.compat.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.compat.portal.util.PortalUtil" %><%@
page import="com.liferay.feedback.util.FeedbackConstant" %><%@
page import="com.liferay.feedback.util.FeedbackUtil" %><%@
page import="com.liferay.feedback.util.PortletKeys" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.servlet.BrowserSnifferUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.model.Group" %><%@
page import="com.liferay.portal.service.GroupLocalServiceUtil" %><%@
page import="com.liferay.portal.service.PortletPreferencesLocalServiceUtil" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.List" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);

portletPreferences = PortletPreferencesLocalServiceUtil.fetchPreferences(themeDisplay.getCompanyId(), themeDisplay.getCompanyId(), PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0, PortletKeys.FEEDBACK_ADMIN);

long groupId = 0;
long mbCategoryId = 0;

if (portletPreferences != null) {
	groupId = GetterUtil.getLong(portletPreferences.getValue("groupId", null));
	mbCategoryId = GetterUtil.getLong(portletPreferences.getValue("mbCategoryId", null));
}

Format dateFormatDate = FastDateFormatFactoryUtil.getSimpleDateFormat("dd MMM yyyy", locale, timeZone);
Format timeFormatDate = FastDateFormatFactoryUtil.getTime(locale, timeZone);
%>