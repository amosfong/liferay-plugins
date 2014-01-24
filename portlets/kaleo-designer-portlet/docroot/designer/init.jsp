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

<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowDefinition" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowEngineManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowException" %><%@
page import="com.liferay.portal.service.ServiceContext" %><%@
page import="com.liferay.portal.service.ServiceContextFactory" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.DuplicateKaleoDraftDefinitionNameException" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionContentException" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionNameException" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalServiceUtil" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionServiceUtil" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.service.permission.KaleoDesignerPermission" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.service.permission.KaleoDraftDefinitionPermission" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.util.ActionKeys" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.util.WebKeys" %><%@
page import="com.liferay.portlet.PortletURLUtil" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
PortletURL currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);

String currentURL = currentURLObj.toString();
%>