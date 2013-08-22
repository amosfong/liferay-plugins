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

<portlet:renderURL var="viewDefinitionsURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="definitions" />
</portlet:renderURL>

<%
String backURL = ParamUtil.getString(request, "backURL", viewDefinitionsURL);

long definitionId = ParamUtil.getLong(request, "definitionId");

Definition definition = DefinitionLocalServiceUtil.fetchDefinition(definitionId);

String name = BeanParamUtil.getString(definition, request, "name");
String description = BeanParamUtil.getString(definition, request, "description");
long sourceId = BeanParamUtil.getLong(definition, request, "sourceId");

String reportName = StringPool.BLANK;

if (definition != null) {
	reportName = definition.getReportName();
}
%>

<portlet:renderURL var="definitionsURL">
	<portlet:param name="tabs1" value="definitions" />
</portlet:renderURL>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (definition == null) ? "new-report-definition" : definition.getName(locale) %>'
/>

<div class="report-message"></div>

<portlet:actionURL name="editDefinition" var="actionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/definition/edit_definition.jsp" />
</portlet:actionURL>

<aui:form action="<%= actionURL %>" enctype="multipart/form-data" method="post" name="fm">
	<liferay-ui:error exception="<%= DefinitionFileException.class %>" message="please-enter-a-valid-file" />
	<liferay-ui:error exception="<%= DefinitionNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= definition %>" model="<%= Definition.class %>" />

	<aui:input name="redirect" type="hidden" value="<%= definitionsURL %>" />
	<aui:input name="viewDefinitionsURL" type="hidden" value="<%= viewDefinitionsURL %>" />

	<c:if test="<%= definition != null %>">
		<aui:input name="definitionId" type="hidden" />

		<%= definitionId %>
	</c:if>

	<aui:fieldset>
		<aui:field-wrapper label="definition-name">
			<liferay-ui:input-localized name="name" xml="<%= name %>" />
		</aui:field-wrapper>

		<aui:field-wrapper label="description">
			<liferay-ui:input-localized name="description" type="textarea" xml="<%= description %>" />
		</aui:field-wrapper>

		<aui:select label="data-source-name" name="sourceId">
			<aui:option label="<%= ReportDataSourceType.PORTAL.getValue() %>" selected="<%= sourceId == 0 %>" value="<%= 0 %>" />

			<%
			List<Source> sources = SourceServiceUtil.getSources(themeDisplay.getSiteGroupId(), null, null, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (Source source : sources) {
			%>

				<aui:option label="<%= source.getName(locale) %>" selected="<%= sourceId == source.getSourceId() %>" value="<%= source.getSourceId() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:field-wrapper label="template">
			<span class="existing-report" style='<%= Validator.isNull(reportName) ? "display: none;" : "display: block;" %>'>
				<%= reportName %>

				<img class="remove-existing-report" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png" />

				<aui:input name="reportName" type="hidden" value="<%= reportName %>" />
			</span>

			<aui:input cssClass="template-report" name="templateReport" style='<%= Validator.isNull(reportName) ? "display: block;" : "display: none;" %>' type="file" />

			<aui:button cssClass="cancel-update-template-report" style="display:none;" value="cancel" />
		</aui:field-wrapper>

		<aui:field-wrapper helpMessage="definition-report-parameters-help" label="report-parameters">
			<aui:input cssClass="report-parameters" name="reportParameters" type="hidden" />

			<aui:column>
				<aui:input cssClass="parameters-key" inlineLabel="key" name="key" size="20" type="text" />
			</aui:column>

			<aui:column>
				<aui:input cssClass="parameters-value-field-set parameters-value" inlineLabel="default-value" name="value" size="20" type="text" />

				<%
				Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone, locale);
				%>

				<aui:field-wrapper cssClass="parameters-input-date">
					<liferay-ui:input-date
						dayParam="parameterDateDay"
						dayValue="<%= calendar.get(Calendar.DATE) %>"
						disabled="<%= false %>"
						firstDayOfWeek="<%= calendar.getFirstDayOfWeek() - 1 %>"
						monthParam="parameterDateMonth"
						monthValue="<%= calendar.get(Calendar.MONTH) %>"
						yearParam="parameterDateYear"
						yearValue="<%= calendar.get(Calendar.YEAR) %>"
					/>
				</aui:field-wrapper>
			</aui:column>

			<aui:column>
				<aui:select cssClass="parameters-input-type" inlineLabel="type" name="type">
					<aui:option label="text" value="text" />
					<aui:option label="date" value="date" />
				</aui:select>
			</aui:column>
			<aui:column>
				<span class="add-parameter">
					<liferay-ui:icon cssClass="add-parameter-button" image="add" />
				</span>
			</aui:column>
		</aui:field-wrapper>
		<aui:field-wrapper>
			<aui:column>
				<div class="report-tags"></div>
			</aui:column>
		</aui:field-wrapper>
	</aui:fieldset>

	<c:if test="<%= definition == null %>">
		<aui:field-wrapper label="permissions">
			<liferay-ui:input-permissions modelName="<%= Definition.class.getName() %>" />
		</aui:field-wrapper>
	</c:if>

	<aui:button-row>
		<portlet:renderURL var="viewURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/admin/view.jsp" />
			<portlet:param name="tabs1" value="definitions" />
		</portlet:renderURL>

		<aui:button type="submit" value='<%= (definition != null) ? "update" : "save" %>' />

		<c:if test="<%= definition != null %>">
			<c:if test="<%= DefinitionPermission.contains(permissionChecker, definition, ActionKeys.ADD_REPORT) %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "addReport();" %>' value="add-report" />

				<aui:button onClick='<%= renderResponse.getNamespace() + "addScheduler();" %>' value="add-schedule" />
			</c:if>

			<aui:button onClick='<%= renderResponse.getNamespace() + "deleteDefinition();" %>' value="delete" />
		</c:if>

		<aui:button href="<%= viewURL %>" type="cancel" />
	</aui:button-row>
</aui:form>

<script type="text/javascript">
	AUI().ready(
		function(A) {
			Liferay.Report.initialize(
				{
					parameters:'<%= BeanParamUtil.getString(definition, request, "reportParameters") %>',
					namespace:'<portlet:namespace />'
				}
			);
		}
	);

	function <portlet:namespace />addReport() {
		submitForm(document.<portlet:namespace />fm, '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/report/generate_report.jsp" /><portlet:param name="definitionId" value="<%= String.valueOf(definitionId) %>" /></portlet:renderURL>');
	}

	function <portlet:namespace />addScheduler() {
		submitForm(document.<portlet:namespace />fm, '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/report/edit_schedule.jsp" /><portlet:param name="definitionId" value="<%= String.valueOf(definitionId) %>" /></portlet:renderURL>');
	}

	function <portlet:namespace />deleteDefinition() {
		if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) {
			submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="deleteDefinition" windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="redirect" value="<%= definitionsURL %>" /></portlet:actionURL>');
		}
	}
</script>