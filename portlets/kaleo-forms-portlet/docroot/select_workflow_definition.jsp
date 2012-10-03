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

String tabs1 = ParamUtil.getString(request, "tabs1", "published");
%>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="jspPage" value="/select_workflow_definition.jsp" />
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
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

	<liferay-portlet:renderURL varImpl="portletURL">
		<portlet:param name="jspPage" value="/select_workflow_definition.jsp" />
		<portlet:param name="tabs1" value="<%= tabs1 %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:tabs
		names="published,unpublished"
		url="<%= portletURL.toString() %>"
	/>

	<c:choose>
		<c:when test='<%= tabs1.equals("published") %>'>
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
		</c:when>
		<c:otherwise>
			<liferay-ui:search-container-results
				results="<%= KaleoDraftDefinitionLocalServiceUtil.getLatestKaleoDraftDefinitions(company.getCompanyId(), 0, searchContainer.getStart(), searchContainer.getEnd(), null) %>"
				total="<%= KaleoDraftDefinitionLocalServiceUtil.getLatestKaleoDraftDefinitionsCount(company.getCompanyId(), 0) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition"
				escapedModel="<%= true %>"
				keyProperty="kaleoDraftDefinitionId"
				modelVar="kaleoDraftDefinition"
			>

				<liferay-ui:search-container-column-text
					name="name"
					value="<%= kaleoDraftDefinition.getName() %>"
				/>

				<liferay-ui:search-container-column-text
					name="title"
					value="<%= kaleoDraftDefinition.getTitle(themeDisplay.getLanguageId()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="draft-version"
					value="<%= String.valueOf(kaleoDraftDefinition.getDraftVersion()) %>"
				/>

				<%
				StringBundler sb = new StringBundler(7);

				sb.append("javascript:Liferay.Util.getOpener().");
				sb.append(renderResponse.getNamespace());
				sb.append("openKaleoDesigner('");
				sb.append(kaleoDraftDefinition.getName());
				sb.append("', '");
				sb.append(kaleoDraftDefinition.getVersion());
				sb.append("', '', Liferay.Util.getWindowName());");

				String taglibEditURL = sb.toString();
				%>

				<liferay-ui:search-container-column-button
					align="right"
					href="<%= taglibEditURL %>"
					name="edit"
				/>
			</liferay-ui:search-container-row>
		</c:otherwise>
	</c:choose>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>