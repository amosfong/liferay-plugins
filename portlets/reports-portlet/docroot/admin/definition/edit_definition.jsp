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

<portlet:renderURL var="viewDefinitionsURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="definitions" />
</portlet:renderURL>

<%
String backURL = ParamUtil.getString(request, "backURL", viewDefinitionsURL);

Definition definition = null;

long definitionId = ParamUtil.getLong(request, "definitionId");

if (definitionId > 0) {
	definition = DefinitionLocalServiceUtil.getDefinition(definitionId);
}

String fileName = null;
boolean isNew = false;
String reportParameters = StringPool.BLANK;

if ((definition == null) || definition.isNew()) {
	isNew = true;
}
else {
	definitionId = definition.getDefinitionId();
	fileName = definition.getReportName();
	reportParameters = definition.getReportParameters();
}

Calendar today = CalendarFactoryUtil.getCalendar(timeZone, locale);
%>

<portlet:renderURL var="definitionsURL">
	<portlet:param name="tabs1" value="definitions" />
</portlet:renderURL>

<script type="text/javascript">
	AUI().ready(
		function(A) {
			Liferay.Report.initialize(
				{
					parameters:'<%= reportParameters %>',
					namespace:'<portlet:namespace />'
				}
			);
		}
	);

	function <portlet:namespace />addScheduler() {
		submitForm(document.<portlet:namespace />fm, '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/report/edit_schedule.jsp" /><portlet:param name="definitionId" value="<%= String.valueOf(definitionId) %>" /></portlet:renderURL>');
	}

	function <portlet:namespace />addDefinition() {
		document.<portlet:namespace />fm.encoding = "multipart/form-data";
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="editDefinition" windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/definition/edit_definition.jsp" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" /></portlet:actionURL>');
	}

	function <portlet:namespace />updateDefinition() {
		var isTemplateUpdated = AUI().one('.templateUpdated').get('value');

		if (isTemplateUpdated == 'true') {
			document.<portlet:namespace />fm.encoding = "multipart/form-data";
		}

		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="editDefinition" windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/definition/edit_definition.jsp" /><portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" /></portlet:actionURL>');
	}

	function <portlet:namespace />generateImmdiately() {
		submitForm(document.<portlet:namespace />fm, '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/report/generate_report.jsp" /><portlet:param name="definitionId" value="<%= String.valueOf(definitionId) %>" /></portlet:renderURL>');
	}

	function <portlet:namespace />deleteDefinition() {
		if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") %>')) {
			submitForm(document.<portlet:namespace />fm, '<portlet:actionURL name="deleteDefinition" windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="redirect" value="<%= definitionsURL%>" /></portlet:actionURL>');
		}
	}
</script>

<liferay-ui:header
	backURL="<%= backURL %>"
	title='<%= (definition == null) ? "new-report-definition" : definition.getName(locale) %>'
/>

<div class="report-message"></div>

<portlet:actionURL var="actionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/definition/edit_definition.jsp" />
</portlet:actionURL>

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<liferay-ui:error exception="<%= DefinitionFileException.class %>" message="please-enter-a-valid-file" />
	<liferay-ui:error exception="<%= DefinitionNameException.class %>" message="please-enter-a-valid-name" />

	<aui:input name="viewDefinitionsURL" type="hidden" value="<%= viewDefinitionsURL %>" />

	<c:if test="<%= !isNew %>">
		<aui:input name="definitionId" type="hidden" value="<%= definition.getDefinitionId() %>" /><%= definition.getDefinitionId() %>
	</c:if>

	<aui:fieldset>
		<aui:field-wrapper label="definition-name">
			<liferay-ui:input-localized name="definitionName" xml='<%= BeanPropertiesUtil.getString(definition, "name") %>' />
		</aui:field-wrapper>

		<aui:field-wrapper label="description">
			<liferay-ui:input-localized name="definitionDescription" type="textarea" xml='<%= BeanPropertiesUtil.getString(definition, "description") %>' />
		</aui:field-wrapper>

		<aui:select label="data-source-name" name="sourceId">
			<aui:option label="<%= ReportDataSourceType.PORTAL.getValue() %>" selected="<%= (definition != null) && (definition.getSourceId() == PortletConstants.PORTAL_DATA_SOURCE_ID) %>" value="<%= PortletConstants.PORTAL_DATA_SOURCE_ID %>" />

			<%
			List<Source> list = SourceServiceUtil.getSources(themeDisplay.getParentGroupId(), null, null, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			for (Iterator itr = list.iterator(); itr.hasNext();) {
				Source element = (Source)itr.next();

				if (SourcePermission.contains(permissionChecker, element, ActionKeys.VIEW)){
			%>

				<aui:option label="<%= element.getName(locale) %>" selected="<%= !isNew && (definition.getSourceId() == element.getSourceId()) %>" value="<%= element.getSourceId() %>" />

			<%
				}
			}
			%>

		</aui:select>

		<aui:field-wrapper label="template">
			<aui:input inputCssClass="templateUpdated" name="templateUpdated" type="hidden" value="<%= isNew %>" />

			<span class="existingFile" style='<%= (Validator.isNull(fileName))?"display: none;":"display: block;" %>'>
				<%= fileName %> <img class="removeExisting" src="<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png" />
				<aui:input name="fileName" type="hidden" value="<%= fileName %>" />
			</span>

			<aui:input inputCssClass="templateFile" label="" name="templateFile" style='<%= (Validator.isNull(fileName))?"display: block;":"display: none;" %>' type="file" />

			<aui:button inputCssClass="cancelUpdateTemplateFile" style="display:none;" value="cancel" />

		</aui:field-wrapper>

		<aui:field-wrapper helpMessage="definition-report-parameters-help" label="report-parameters">
			<aui:input inputCssClass="reportParameters" name="reportParameters" type="hidden" />
			<aui:column>
				<aui:input inlineLabel="key" inputCssClass="parameters-key" name="key" size="20" type="text" />
			</aui:column>
			<aui:column>
				<aui:input cssClass="parameters-value-field-set" inlineLabel="default-value" inputCssClass="parameters-value" name="value" size="20" type="text" />

				<aui:field-wrapper cssClass="parameters-input-date">
					<liferay-ui:input-date
						dayParam="parameterDateDay"
						dayValue="<%= today.get(Calendar.DATE) %>"
						disabled="<%= false %>"
						firstDayOfWeek="<%= today.getFirstDayOfWeek() - 1 %>"
						monthParam="parameterDateMonth"
						monthValue="<%= today.get(Calendar.MONTH) %>"
						yearParam="parameterDateYear"
						yearRangeEnd="<%= today.get(Calendar.YEAR) + 100 %>"
						yearRangeStart="<%= today.get(Calendar.YEAR) - 100 %>"
						yearValue="<%= today.get(Calendar.YEAR) %>"
					/>
				</aui:field-wrapper>
			</aui:column>
			<aui:column>
				<aui:select inlineLabel="type" inputCssClass="parameters-input-type" name="">
					<aui:option label="text" value="text" />
					<aui:option label="date" value="date" />
				</aui:select>
			</aui:column>
			<aui:column>
				<span class="add-parameter"><liferay-ui:icon image="add" cssClass="add-parameter-button" /></span>
			</aui:column>
		</aui:field-wrapper>
		<aui:field-wrapper label="">
			<aui:column>
				<div class="report-tags"></div>
			</aui:column>
		</aui:field-wrapper>
	</aui:fieldset>

	<c:if test="<%= isNew %>">
		<aui:field-wrapper label="permissions">
			<liferay-ui:input-permissions modelName="<%= Definition.class.getName() %>" />
		</aui:field-wrapper>
	</c:if>

	<aui:button-row>
		<portlet:renderURL var="viewURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="tabs1" value="definitions" />
			<portlet:param name="mvcPath" value="/admin/view.jsp" />
		</portlet:renderURL>

		<c:if test="<%= isNew %>">

			<%
				String addDefinition = renderResponse.getNamespace() + "addDefinition();";
			%>

			<aui:button onClick="<%= addDefinition %>" value="save" />
		</c:if>

		<c:if test="<%= !isNew %>">
			<aui:button onClick='<%= renderResponse.getNamespace() + "updateDefinition();" %>' value="update" />

			<c:if test="<%= !isNew && DefinitionPermission.contains(permissionChecker, definition, ActionKeys.ADD_REPORT) %>">
				<aui:button onClick='<%= renderResponse.getNamespace() + "generateImmdiately();" %>' value="add-report" />

				<aui:button onClick='<%= renderResponse.getNamespace() + "addScheduler();" %>' value="add-schedule" />
			</c:if>

			<aui:button onClick='<%= renderResponse.getNamespace() + "deleteDefinition();" %>' value="delete" />
		</c:if>

		<aui:button href="<%= viewURL %>" type="cancel" />
	</aui:button-row>
</aui:form>