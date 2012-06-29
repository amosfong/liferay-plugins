
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
long workflowTaskId = ParamUtil.getLong(request, "workflowTaskId");

long kaleoProcessLinkId = ParamUtil.getLong(request, "kaleoProcessLinkId");

KaleoProcessLink kaleoProcessLink = KaleoProcessLinkLocalServiceUtil.getKaleoProcessLink(kaleoProcessLinkId);

KaleoProcess kaleoProcess = kaleoProcessLink.getKaleoProcess();

long groupId = BeanParamUtil.getLong(kaleoProcess, request, "groupId", scopeGroupId);

long ddlRecordId = ParamUtil.getLong(request, "ddlRecordId");

String formName = ParamUtil.getString(request, "formName");
%>

<aui:form cssClass="lfr-dynamic-form" enctype="multipart/form-data" name="<%= formName %>" onSubmit='<%= "event.preventDefault(); submitForm(event.target);" %>'>
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="groupId" type="hidden" value="<%= String.valueOf(groupId) %>" />
	<aui:input name="ddlRecordId" type="hidden" value="<%= String.valueOf(ddlRecordId) %>" />
	<aui:input name="ddlRecordSetId" type="hidden" value="<%= String.valueOf(kaleoProcess.getDDLRecordSetId()) %>" />
	<aui:input name="ddmTemplateId" type="hidden" value="<%= String.valueOf(kaleoProcessLink.getDDMTemplateId()) %>" />
	<aui:input name="workflowAction" type="hidden" value="<%= WorkflowConstants.ACTION_SAVE_DRAFT %>" />
	<aui:input name="workflowTaskId" type="hidden" value="<%= String.valueOf(workflowTaskId) %>" />

	<aui:fieldset>

		<%
		DDLRecord ddlRecord = DDLRecordLocalServiceUtil.getRecord(ddlRecordId);

		Fields fields = ddlRecord.getFields();

		String content = null;

		try {
			DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getTemplate(kaleoProcessLink.getDDMTemplateId());

			content = DDMXSDUtil.getHTML(pageContext, ddmTemplate, fields, StringPool.BLANK, false, locale);
		}
		catch (NoSuchTemplateException nste) {
			DDLRecordSet ddlRecordSet = kaleoProcess.getDDLRecordSet();

			DDMStructure ddmStructure = ddlRecordSet.getDDMStructure();

			content = DDMXSDUtil.getHTML(pageContext, ddmStructure, fields, StringPool.BLANK, false, locale);
		}
		%>

		<%= content %>
	</aui:fieldset>

	<aui:button name="saveButton" type="submit" value="save" />
</aui:form>