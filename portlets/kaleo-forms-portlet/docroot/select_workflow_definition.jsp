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
long ddmStructureId = ParamUtil.getLong(request, "ddmStructureId");
%>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="jspPage" value="/select_workflow_definition.jsp" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="no-workflow-definitions-are-defined"
	iteratorURL="<%= iteratorURL %>"
>

	<%
	String taglibOnClick = "Liferay.Util.getOpener()." + renderResponse.getNamespace() + "openKaleoDesigner('', '0', '', Liferay.Util.getWindowName());";
	%>

	<aui:button onClick="<%= taglibOnClick %>" value="add-definition" />

	<div class="separator"><!-- --></div>

	<liferay-ui:search-container-results
		results="<%= WorkflowDefinitionManagerUtil.getActiveWorkflowDefinitions(company.getCompanyId(), searchContainer.getStart(), searchContainer.getEnd(), null) %>"
		total="<%= WorkflowDefinitionManagerUtil.getActiveWorkflowDefinitionCount(company.getCompanyId()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.workflow.WorkflowDefinition"
		modelVar="workflowDefinition"
	>

		<%
		StringBundler sb = new StringBundler(7);

		sb.append("javascript:Liferay.Util.getOpener().");
		sb.append(portletDisplay.getNamespace());
		sb.append("selectWorkflowDefinition('");
		sb.append(workflowDefinition.getName());
		sb.append("', '");
		sb.append(workflowDefinition.getVersion());
		sb.append("', Liferay.Util.getWindow());");

		String rowHREF = sb.toString();
		%>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="name"
			value="<%= workflowDefinition.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="title"
			value="<%= workflowDefinition.getTitle(themeDisplay.getLanguageId()) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="version"
			value="<%= String.valueOf(workflowDefinition.getVersion()) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="active"
			value='<%= workflowDefinition.isActive() ? LanguageUtil.get(locale, "yes") : LanguageUtil.get(locale, "no") %>'
		/>

		<%
		sb = new StringBundler(7);

		sb.append("javascript:Liferay.Util.getOpener().");
		sb.append(renderResponse.getNamespace());
		sb.append("openKaleoDesigner('");
		sb.append(workflowDefinition.getName());
		sb.append("', '");
		sb.append(workflowDefinition.getVersion());
		sb.append("', '', Liferay.Util.getWindowName());");

		String taglibEditURL = sb.toString();
		%>

		<liferay-ui:search-container-column-button
			align="right"
			href="<%= taglibEditURL %>"
			name="edit"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>