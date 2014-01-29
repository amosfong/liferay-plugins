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

<%@ page import="com.liferay.portal.NoSuchWorkflowDefinitionLinkException" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil" %><%@
page import="com.liferay.portal.model.WorkflowDefinitionLink" %><%@
page import="com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil" %><%@
page import="com.liferay.portal.util.PortletKeys" %><%@
page import="com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalServiceUtil" %><%@
page import="com.liferay.portal.workflow.kaleo.forms.KaleoProcessDDMTemplateIdException" %><%@
page import="com.liferay.portal.workflow.kaleo.forms.search.KaleoProcessSearch" %><%@
page import="com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessServiceUtil" %><%@
page import="com.liferay.portal.workflow.kaleo.forms.service.permission.KaleoFormsPermission" %><%@
page import="com.liferay.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portlet.dynamicdatalists.RecordSetDDMStructureIdException" %><%@
page import="com.liferay.portlet.dynamicdatalists.RecordSetNameException" %><%@
page import="com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.NoSuchStructureException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.storage.FieldConstants" %>

<%@ page import="javax.portlet.PortletRequest" %>