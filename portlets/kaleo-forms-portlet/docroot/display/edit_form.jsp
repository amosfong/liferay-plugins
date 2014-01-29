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

<%@ include file="/display/init.jsp" %>

<%
long workflowTaskId = ParamUtil.getLong(request, "workflowTaskId");

long kaleoProcessLinkId = ParamUtil.getLong(request, "kaleoProcessLinkId");

KaleoProcessLink kaleoProcessLink = KaleoProcessLinkLocalServiceUtil.getKaleoProcessLink(kaleoProcessLinkId);

KaleoProcess kaleoProcess = kaleoProcessLink.getKaleoProcess();

long groupId = BeanParamUtil.getLong(kaleoProcess, request, "groupId", scopeGroupId);

long ddlRecordId = ParamUtil.getLong(request, "ddlRecordId");

String formName = ParamUtil.getString(request, "formName");
%>

<aui:form cssClass="lfr-dynamic-form" enctype="multipart/form-data" name="<%= HtmlUtil.escapeAttribute(formName) %>" onSubmit='<%= "event.preventDefault(); submitForm(event.target);" %>'>
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

		long classNameId = 0;
		long classPK = 0;

		try {
			DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getTemplate(kaleoProcessLink.getDDMTemplateId());

			classNameId = PortalUtil.getClassNameId(DDMTemplate.class);
			classPK = ddmTemplate.getTemplateId();
		}
		catch (NoSuchTemplateException nste) {
			DDLRecordSet ddlRecordSet = kaleoProcess.getDDLRecordSet();

			DDMStructure ddmStructure = ddlRecordSet.getDDMStructure();

			classNameId = PortalUtil.getClassNameId(DDMStructure.class);
			classPK = ddmStructure.getStructureId();
		}
		%>

		<liferay-ddm:html
			classNameId="<%= classNameId %>"
			classPK="<%= classPK %>"
			fields="<%= fields %>"
			requestedLocale="<%= locale %>"
		/>
	</aui:fieldset>

	<aui:button name="saveButton" type="submit" value="save" />
</aui:form>