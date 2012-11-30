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
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL");

KaleoProcess kaleoProcess = (KaleoProcess)request.getAttribute(WebKeys.KALEO_PROCESS);

long kaleoProcessId = BeanParamUtil.getLong(kaleoProcess, request, "kaleoProcessId");

long groupId = BeanParamUtil.getLong(kaleoProcess, request, "groupId", scopeGroupId);

long ddlRecordSetId = BeanParamUtil.getLong(kaleoProcess, request, "DDLRecordSetId");

DDLRecordSet ddlRecordSet = null;

if (ddlRecordSetId > 0) {
	ddlRecordSet = DDLRecordSetLocalServiceUtil.getRecordSet(ddlRecordSetId);
}

long ddmStructureId = BeanParamUtil.getLong(ddlRecordSet, request, "DDMStructureId");

String ddmStructureName = StringPool.BLANK;

if (Validator.isNotNull(ddmStructureId)) {
	try {
		DDMStructure ddmStructure = DDMStructureLocalServiceUtil.getStructure(ddmStructureId);

		ddmStructureName = ddmStructure.getName(locale);
	}
	catch (NoSuchStructureException nsse) {
	}
}

long ddmTemplateId = 0;

if (!SessionErrors.contains(renderRequest, KaleoProcessDDMTemplateIdException.class.getName())) {
	ddmTemplateId = BeanParamUtil.getLong(kaleoProcess, request, "DDMTemplateId");
}

String ddmTemplateName = StringPool.BLANK;

if (Validator.isNotNull(ddmTemplateId)) {
	try {
		DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getTemplate(ddmTemplateId);

		ddmTemplateName = ddmTemplate.getName(locale);
	}
	catch (NoSuchStructureException nsse) {
	}
}

String workflowDefinition = StringPool.BLANK;

String workflowDefinitionName = StringPool.BLANK;
int workflowDefinitionVersion = 0;

try {
	WorkflowDefinitionLink workflowDefinitionLink = WorkflowDefinitionLinkLocalServiceUtil.getWorkflowDefinitionLink(company.getCompanyId(), themeDisplay.getScopeGroupId(), KaleoProcess.class.getName(), kaleoProcessId, 0, true);

	workflowDefinition = workflowDefinitionLink.getWorkflowDefinitionName() + StringPool.AT + workflowDefinitionLink.getWorkflowDefinitionVersion();

	workflowDefinitionName = workflowDefinitionLink.getWorkflowDefinitionName();
	workflowDefinitionVersion = workflowDefinitionLink.getWorkflowDefinitionVersion();
}
catch (NoSuchWorkflowDefinitionLinkException nswdle) {
}

boolean kaleoProcessStarted = false;

if (kaleoProcess != null) {
	kaleoProcessStarted = (DDLRecordLocalServiceUtil.getRecordsCount(kaleoProcess.getDDLRecordSetId(), WorkflowConstants.STATUS_ANY) > 0);
}
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= (kaleoProcess == null) %>"
	title='<%= (kaleoProcess == null) ? "new-process" : kaleoProcess.getName(locale) %>'
/>

<c:if test="<%= kaleoProcessStarted %>">
	<div class="portlet-msg-info">
		<liferay-ui:message key="updating-an-entry-definition,-initial-form,-or-workflow-will-cause-loss-of-data" />
	</div>
</c:if>

<portlet:actionURL name="updateKaleoProcess" var="editKaleoProcessURL">
	<portlet:param name="mvcPath" value="/edit_kaleo_process.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="backURL" value="<%= backURL %>" />
</portlet:actionURL>

<aui:form action="<%= editKaleoProcessURL %>" method="post" name="fm">
	<aui:input name="kaleoProcessId" type="hidden" value="<%= kaleoProcessId %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />
	<aui:input name="ddlRecordSetId" type="hidden" value="<%= ddlRecordSetId %>" />
	<aui:input name="ddmStructureId" type="hidden" value="<%= ddmStructureId %>" />
	<aui:input name="ddmStructureName" type="hidden" value="<%= ddmStructureName %>" />
	<aui:input name="ddmTemplateId" type="hidden" value="<%= ddmTemplateId %>" />
	<aui:input name="ddmTemplateName" type="hidden" value="<%= ddmTemplateName %>" />
	<aui:input name="kaleoProcessLinkIds" type="hidden" value='<%= (kaleoProcess != null) ? ListUtil.toString(kaleoProcess.getKaleoProcessLinks(), "kaleoProcessLinkId") : "" %>' />
	<aui:input name="scope" type="hidden" value="1" />
	<aui:input name="workflowDefinition" type="hidden" value="<%= workflowDefinition %>" />
	<aui:input name="workflowDefinitionName" type="hidden" value="<%= workflowDefinitionName %>" />
	<aui:input name="workflowDefinitionVersion" type="hidden" value="<%= workflowDefinitionVersion %>" />
	<aui:input name="oldDDMStructureId" type="hidden" value="<%= ddmStructureId %>" />
	<aui:input name="oldWorkflowDefinition" type="hidden" value="<%= workflowDefinition %>" />

	<liferay-ui:error exception="<%= KaleoProcessDDMTemplateIdException.class %>" message="please-enter-a-valid-initial-form" />
	<liferay-ui:error exception="<%= RecordSetDDMStructureIdException.class %>" message="please-enter-a-valid-definition" />
	<liferay-ui:error exception="<%= RecordSetNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= ddlRecordSet %>" model="<%= DDLRecordSet.class %>" />

	<aui:fieldset>
		<aui:input name="name" />

		<aui:input name="description" />

		<aui:field-wrapper cssClass="kaleo-forms-portlet-selection" label="entry-definition" required="<%= true %>">
			<aui:a href="javascript:;" id="selectDDMStructureDisplay" label="<%= ddmStructureName %>" />

			<liferay-ui:icon
				id="selectDDMStructure"
				image="add"
				label="<%= true %>"
				message="select"
				url='javascript:;'
			/>
		</aui:field-wrapper>

		<aui:field-wrapper cssClass="kaleo-forms-portlet-selection" helpMessage="select-the-template-used-to-render-the-initial-form" label="initial-form" name="ddmTemplateId" required="<%= true %>">
			<aui:a href="javascript:;" id="selectDDMTemplateDisplay" label="<%= ddmTemplateName %>" />

			<liferay-ui:icon
				id="selectDDMTemplate"
				image="add"
				label="<%= true %>"
				message="select"
				url="javascript:;"
			/>
		</aui:field-wrapper>

		<aui:field-wrapper cssClass="kaleo-forms-portlet-selection" label="workflow" name="workflowDefinition">
			<aui:a href="javascript:;" id="selectWorkflowDefinitionDisplay" />

			<liferay-ui:icon
				id="selectWorkflowDefinition"
				image="add"
				label="<%= true %>"
				message="select"
				url="javascript:;"
			/>
		</aui:field-wrapper>

		<aui:field-wrapper cssClass="kaleo-forms-portlet-selection" label="workflow-task-forms" name="workflowTaskForms">
			<liferay-ui:icon
				id="assignWorkflowTaskForms"
				image="add"
				label="<%= true %>"
				message="assign"
				url="javascript:;"
			/>
		</aui:field-wrapper>

		<aui:button-row>
			<aui:button name="saveButton" type="submit" value="save" />

			<aui:button href="<%= redirect %>" name="cancelButton" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>

<aui:script>
	function <portlet:namespace />kaleoDesignerSaveCallback(workflowDefinitionName, workflowDefinitionVersion) {
		<portlet:namespace />selectWorkflowDefinition(workflowDefinitionName, workflowDefinitionVersion);
	}

	function <portlet:namespace />openDDMPortlet(strutsAction, ddmStructureId, ddmTemplateId, chooseCallback, saveCallback) {
		Liferay.Util.openDDMPortlet(
			{
				classNameId: <%= PortalUtil.getClassNameId(DDMStructure.class) %>,
				classPK: ddmStructureId,
				chooseCallback: chooseCallback,
				ddmResource: '<%= portletConfig.getInitParameter("ddm-resource") %>',
				dialog: {
					width:820
				},
				saveCallback: saveCallback,
				showManageTemplates: 'false',
				storageType: 'xml',
				structureName: 'entry-definition',
				structureType: 'com.liferay.portlet.dynamicdatalists.model.DDLRecordSet',
				struts_action: strutsAction,
				templateHeaderTitle: 'forms-for-entry-x',
				templateId: ddmTemplateId,
				templateMode: 'create',
				templateType: 'detail',
				title: '<liferay-ui:message key="entry-definitions" />'
			}
		);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />addKaleoProcessLinkId',
		function(kaleoProcessLinkId) {
			var A = AUI();

			var kaleoProcessLinkIds = A.one('#<portlet:namespace />kaleoProcessLinkIds');

			if (kaleoProcessLinkIds) {
				var values = kaleoProcessLinkIds.val().split(',');

				values.push(String(kaleoProcessLinkId));

				values = A.Array.filter(
					A.Array.dedupe(values),
					function(val) {
						return val;
					}
				);

				kaleoProcessLinkIds.val(values.join());
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />getDDMTemplate',
		function(kaleoProcessId, workflowTaskName, callback) {
			var A = AUI();

			Liferay.Service(
				'/kaleoprocesslink/fetch-kaleo-process-link',
				{
					kaleoProcessId: kaleoProcessId,
					workflowTaskName: workflowTaskName
				},
				function(json1) {
					Liferay.Service(
						'/ddmtemplate/get-template',
						{
							templateId: json1.DDMTemplateId
						},
						function(json2) {
							if (callback) {
								callback.call(this, json1, json2);
							}
						}
					);
				}
			);
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />kaleoDesignerPropertiesSaveCallback',
		function(event) {
			var A = AUI();

			var kaleoDesigner = event.currentTarget;

			var editingNode = kaleoDesigner.editingNode;

			if (editingNode) {
				var record = kaleoDesigner.propertyList.getActiveRecord();

				if (record && (record.get('attributeName') === 'forms')) {
					var forms = editingNode.get('forms');
					var workflowTaskName = editingNode.get('name');

					Liferay.Service(
						'/kaleoprocesslink/update-kaleo-process-link',
						{
							kaleoProcessId: '<%= kaleoProcessId %>',
							workflowTaskName: workflowTaskName,
							ddmTemplateId: forms.templateId
						},
						function(json) {
							<portlet:namespace />addKaleoProcessLinkId(json.kaleoProcessLinkId);

							var templateName = forms.templateName[0];

							if (templateName) {
								templateName = '(' + templateName + ')';
							}

							editingNode._uiSetName(
								A.Lang.sub(
									'{workflowTaskName} {formName}',
									{
										formName: templateName,
										workflowTaskName: workflowTaskName
									}
								)
							);
						}
					);
				}
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />openKaleoDesigner',
		function(workflowDefinitionName, workflowDefinitionVersion, saveCallback, openerWindowName) {
			var A = AUI();

			var ddmStructureId = A.one('#<portlet:namespace />ddmStructureId').val();

			Liferay.Util.openKaleoDesignerPortlet(
				{
					availablePropertyModels: 'Liferay.KaleoDesigner.AVAILABLE_PROPERTY_MODELS.KALEO_FORMS_EDIT',
					ddmStructureId: ddmStructureId,
					kaleoProcessId: '<%= kaleoProcessId %>',
					name: workflowDefinitionName,
					openerWindowName: openerWindowName,
					portletResourceNamespace: '<%= renderResponse.getNamespace() %>',
					propertiesSaveCallback: '<portlet:namespace />kaleoDesignerPropertiesSaveCallback',
					saveCallback: saveCallback,
					version: workflowDefinitionVersion,
					versionLabel: '<liferay-ui:message key="version" />'
				}
			);
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectDDMStructure',
		function(ddmStructureId, ddmStructureName, dialog) {
			var A = AUI();

			A.one('#<portlet:namespace />ddmStructureId').val(ddmStructureId);
			A.one('#<portlet:namespace />ddmStructureName').val(ddmStructureName);
			A.one('#<portlet:namespace />selectDDMStructureDisplay').html(ddmStructureName);

			A.one('#<portlet:namespace />workflowDefinition').val('');
			A.one('#<portlet:namespace />workflowDefinitionName').val('');
			A.one('#<portlet:namespace />workflowDefinitionVersion').val('0');

			A.one('#<portlet:namespace />selectWorkflowDefinitionDisplay').empty();

			A.one('#<portlet:namespace />ddmTemplateId').val('');
			A.one('#<portlet:namespace />ddmTemplateName').val('');

			A.one('#<portlet:namespace />selectDDMTemplateDisplay').empty();

			A.one('#<portlet:namespace />kaleoProcessLinkIds').val('');

			if (dialog) {
				dialog.close();
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectDDMTemplate',
		function(ddmTemplateId, ddmTemplateName, dialog) {
			var A = AUI();

			A.one('#<portlet:namespace />ddmTemplateId').val(ddmTemplateId);
			A.one('#<portlet:namespace />ddmTemplateName').val(ddmTemplateName);
			A.one('#<portlet:namespace />selectDDMTemplateDisplay').html(ddmTemplateName);

			if (dialog) {
				dialog.close();
			}
		},
		['aui-base']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectWorkflowDefinition',
		function(workflowDefinitionName, workflowDefinitionVersion, dialog) {
			var A = AUI();

			A.one('#<portlet:namespace />workflowDefinition').val(workflowDefinitionName + '@' + workflowDefinitionVersion);
			A.one('#<portlet:namespace />workflowDefinitionName').val(workflowDefinitionName);
			A.one('#<portlet:namespace />workflowDefinitionVersion').val(workflowDefinitionVersion);

			A.one('#<portlet:namespace />selectWorkflowDefinitionDisplay').html(
				A.Lang.sub(
					'{name} ({versionLabel} {version})',
					{
						name: workflowDefinitionName,
						version: workflowDefinitionVersion,
						versionLabel: '<liferay-ui:message key="version" />'
					}
				)
			);

			if (dialog) {
				dialog.close();
			}
		},
		['aui-base']
	);
</aui:script>

<aui:script use="aui-base">
	var SelectionClickActions = {
		'<portlet:namespace />selectDDMStructure': function(event) {
			<portlet:namespace />openDDMPortlet('', '', '', '<portlet:namespace />selectDDMStructure', '<portlet:namespace />selectDDMStructure');
		},

		'<portlet:namespace />selectDDMStructureDisplay': function(event) {
			var ddmStructureId = Liferay.Util.toNumber(A.one('#<portlet:namespace />ddmStructureId').val());

			if (ddmStructureId) {
				<portlet:namespace />openDDMPortlet('/dynamic_data_mapping/edit_structure', ddmStructureId, '', '<portlet:namespace />selectDDMStructure', '<portlet:namespace />selectDDMStructure');
			}
		},

		'<portlet:namespace />selectDDMTemplate': function(event) {
			var ddmStructureId = Liferay.Util.toNumber(A.one('#<portlet:namespace />ddmStructureId').val());

			if (ddmStructureId) {
				<portlet:namespace />openDDMPortlet('/dynamic_data_mapping/view_template', ddmStructureId, '', '<portlet:namespace />selectDDMTemplate');
			}
		},

		'<portlet:namespace />selectDDMTemplateDisplay': function(event) {
			var ddmStructureId = Liferay.Util.toNumber(A.one('#<portlet:namespace />ddmStructureId').val());
			var ddmTemplateId = Liferay.Util.toNumber(A.one('#<portlet:namespace />ddmTemplateId').val());

			if (ddmStructureId && ddmTemplateId) {
				<portlet:namespace />openDDMPortlet('/dynamic_data_mapping/edit_template', ddmStructureId, ddmTemplateId, '<portlet:namespace />selectDDMTemplate');
			}
		},

		'<portlet:namespace />selectWorkflowDefinition': function(event) {
			<portlet:renderURL var="workflowDefinitionURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="mvcPath" value='<%= "/select_workflow_definition.jsp" %>' />
				<portlet:param name="kaleoProcessId" value="<%= String.valueOf(kaleoProcessId) %>" />
			</portlet:renderURL>

			var ddmStructureId = Liferay.Util.toNumber(A.one('#<portlet:namespace />ddmStructureId').val());

			if (ddmStructureId) {
				Liferay.Util.openWindow(
					{
						dialog: {
							width: 600
						},
						modal: true,
						title: '<liferay-ui:message key="workflow-definitions" />',
						uri: '<%= workflowDefinitionURL %>&ddmStructureId='+ddmStructureId
					}
				);
			}
		},

		'<portlet:namespace />selectWorkflowDefinitionDisplay': function() {
			var workflowDefinitionName = A.one('#<portlet:namespace />workflowDefinitionName').val();
			var workflowDefinitionVersion = A.one('#<portlet:namespace />workflowDefinitionVersion').val();

			<portlet:namespace />openKaleoDesigner(workflowDefinitionName, workflowDefinitionVersion, '<portlet:namespace />kaleoDesignerSaveCallback');
		},

		'<portlet:namespace />assignWorkflowTaskForms': function(event) {
			var ddmStructureId = A.one('#<portlet:namespace />ddmStructureId').val();
			var workflowDefinitionName = A.one('#<portlet:namespace />workflowDefinitionName').val();
			var workflowDefinitionVersion = A.one('#<portlet:namespace />workflowDefinitionVersion').val();

			if (ddmStructureId && workflowDefinitionName) {
				Liferay.Util.openKaleoDesignerPortlet(
					{
						availablePropertyModels: 'Liferay.KaleoDesigner.AVAILABLE_PROPERTY_MODELS.KALEO_FORMS_ASSIGN_TASK_FORMS',
						ddmStructureId: ddmStructureId,
						kaleoProcessId: '<%= kaleoProcessId %>',
						name: workflowDefinitionName,
						portletResourceNamespace: '<%= renderResponse.getNamespace() %>',
						propertiesSaveCallback: '<portlet:namespace />kaleoDesignerPropertiesSaveCallback',
						uiScope: 'assign-task-forms',
						version: workflowDefinitionVersion
					}
				);
			}
		}
	};

	A.one('#<portlet:namespace />fm').delegate(
		'click',
		function(event) {
			var instance = this;

			<c:if test="<%= kaleoProcessStarted %>">
				if (!confirm('<liferay-ui:message key="updating-an-entry-definition,-initial-form,-or-workflow-will-cause-loss-of-data" /> <liferay-ui:message key="do-you-want-to-proceed-with-the-update" />')) {
					return;
				}
			</c:if>

			var anchor = event.currentTarget;

			var anchorId = anchor.get('id');

			var selectionClickAction = SelectionClickActions[anchorId];

			if (selectionClickAction) {
				selectionClickAction.apply(instance, arguments);
			}

			event.halt();
		},
		'.kaleo-forms-portlet-selection a'
	);

	<c:if test="<%= Validator.isNotNull(workflowDefinitionName) && (workflowDefinitionVersion > 0) %>">
		<portlet:namespace />selectWorkflowDefinition('<%= workflowDefinitionName %>', <%= workflowDefinitionVersion %>);
	</c:if>
</aui:script>