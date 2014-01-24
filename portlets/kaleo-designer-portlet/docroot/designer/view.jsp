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

<%@ include file="/designer/init.jsp" %>

<c:choose>
	<c:when test="<%= WorkflowEngineManagerUtil.isDeployed() %>">
		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="mvcPath" value="/designer/view.jsp" />
		</liferay-portlet:renderURL>

		<%
		List<KaleoDraftDefinition> latestKaleoDraftDefinitions = KaleoDraftDefinitionServiceUtil.getLatestKaleoDraftDefinitions(company.getCompanyId(), -1, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
		%>

		<liferay-ui:search-container
			emptyResultsMessage="no-workflow-definitions-are-defined"
			iteratorURL="<%= iteratorURL %>"
			total= "<%= latestKaleoDraftDefinitions.size() %>"
		>

			<c:if test="<%= KaleoDesignerPermission.contains(permissionChecker, themeDisplay.getCompanyGroupId(), ActionKeys.ADD_DRAFT) %>">
				<portlet:renderURL var="addKaleoDraftDefinitionURL">
					<portlet:param name="mvcPath" value='<%= "/designer/edit_kaleo_draft_definition.jsp" %>' />
					<portlet:param name="backURL" value="<%= currentURL %>" />
				</portlet:renderURL>

				<div class="btn-toolbar">
					<aui:button href="<%= addKaleoDraftDefinitionURL %>" value="add-definition" />
				</div>
			</c:if>

			<liferay-ui:search-container-results
				 results="<%= ListUtil.subList(latestKaleoDraftDefinitions, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition"
				escapedModel="<%= true %>"
				keyProperty="kaleoDraftDefinitionId"
				modelVar="kaleoDraftDefinition"
			>
				<liferay-ui:search-container-column-text
					name="name"
					value="<%= HtmlUtil.escape(kaleoDraftDefinition.getName()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="title"
					value="<%= HtmlUtil.escape(kaleoDraftDefinition.getTitle(themeDisplay.getLanguageId())) %>"
				/>

				<liferay-ui:search-container-column-text
					name="version"
					value="<%= String.valueOf(kaleoDraftDefinition.getVersion()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="draft-version"
					value="<%= String.valueOf(kaleoDraftDefinition.getDraftVersion()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="published"
					value='<%= (kaleoDraftDefinition.getVersion() > 0) ? LanguageUtil.get(locale, "yes") : LanguageUtil.get(locale, "no") %>'
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/designer/kaleo_draft_definition_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<liferay-ui:message key="no-workflow-engine-is-deployed" />
		</div>
	</c:otherwise>
</c:choose>