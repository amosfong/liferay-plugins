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
String backURL = ParamUtil.getString(request, "backURL");

KaleoProcess kaleoProcess = (KaleoProcess)request.getAttribute(WebKeys.KALEO_PROCESS);

long kaleoProcessId = BeanParamUtil.getLong(kaleoProcess, request, "kaleoProcessId");
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title="<%= kaleoProcess.getName(locale) %>"
/>

<%
PortletURL portletURL = renderResponse.createRenderURL();

DDLRecordSet ddlRecordSet = kaleoProcess.getDDLRecordSet();

DDMStructure ddmStructure = ddlRecordSet.getDDMStructure();

Map<String, Map<String, String>> fieldsMap = ddmStructure.getFieldsMap();

List<String> headerNames = new ArrayList();

for (Map<String, String> fields : fieldsMap.values()) {
	String label = fields.get(FieldConstants.LABEL);

	headerNames.add(label);
}

headerNames.add("status");
headerNames.add("modified-date");
headerNames.add("author");

SearchContainer searchContainer = new SearchContainer(renderRequest, portletURL, headerNames, "no-records-were-found");

int total = DDLRecordLocalServiceUtil.getRecordsCount(kaleoProcess.getDDLRecordSetId(), WorkflowConstants.STATUS_ANY);

searchContainer.setTotal(total);

List<DDLRecord> results = DDLRecordLocalServiceUtil.getRecords(kaleoProcess.getDDLRecordSetId(), WorkflowConstants.STATUS_ANY, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

searchContainer.setResults(results);

List resultRows = searchContainer.getResultRows();

for (int i = 0; i < results.size(); i++) {
	DDLRecord ddlRecord = results.get(i);

	Fields fieldsModel = ddlRecord.getFields();

	ResultRow row = new ResultRow(ddlRecord, ddlRecord.getRecordId(), i);

	// Columns

	for (Map<String, String> fields : fieldsMap.values()) {
		String dataType = fields.get(FieldConstants.DATA_TYPE);
		String name = fields.get(FieldConstants.NAME);

		String value = null;

		if (fieldsModel.contains(name)) {
			com.liferay.portlet.dynamicdatamapping.storage.Field field = fieldsModel.get(name);

			value = field.getRenderedValue(themeDisplay);
		}
		else {
			value = StringPool.BLANK;
		}

		row.addText(value);
	}

	row.addText(LanguageUtil.get(pageContext, WorkflowConstants.toLabel(ddlRecord.getStatus())));
	row.addText(dateFormatDateTime.format(ddlRecord.getModifiedDate()));
	row.addText(HtmlUtil.escape(PortalUtil.getUserName(ddlRecord.getUserId(), ddlRecord.getUserName())));

	// Add result row

	resultRows.add(row);
}
%>

<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />