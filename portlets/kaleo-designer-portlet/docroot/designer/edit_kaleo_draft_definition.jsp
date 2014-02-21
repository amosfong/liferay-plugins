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

		<%
		String backURL = ParamUtil.getString(request, "backURL");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(request);

		KaleoDraftDefinition kaleoDraftDefinition = (KaleoDraftDefinition)request.getAttribute(WebKeys.KALEO_DRAFT_DEFINITION);

		String content = (String)request.getAttribute(WebKeys.KALEO_DRAFT_DEFINITION_CONTENT);

		WorkflowDefinition workflowDefinition = null;

		KaleoDraftDefinition latestKaleoDraftDefinition = null;

		if (kaleoDraftDefinition != null) {
			try {
				latestKaleoDraftDefinition = KaleoDraftDefinitionLocalServiceUtil.getLatestKaleoDraftDefinition(kaleoDraftDefinition.getName(), kaleoDraftDefinition.getVersion(), serviceContext);

				if (kaleoDraftDefinition.getVersion() > 0) {
					workflowDefinition = WorkflowDefinitionManagerUtil.getWorkflowDefinition(themeDisplay.getCompanyId(), kaleoDraftDefinition.getName(), kaleoDraftDefinition.getVersion());
				}
			}
			catch (Exception e) {
			}

			if (Validator.isNull(content)) {
				content = kaleoDraftDefinition.getContent();
			}
		}

		boolean editable = true;

		String uiScope = ParamUtil.getString(request, "uiScope");

		if ((uiScope != null) && uiScope.equals("assign-task-forms")) {
			editable = false;
		}
		%>

		<liferay-ui:header
			backURL="<%= backURL %>"
			title='<%= (kaleoDraftDefinition == null) ? "new-workflow-definition-draft" : kaleoDraftDefinition.getName() %>'
		/>

		<aui:form method="post" name="fm" onSubmit="event.preventDefault();">
			<aui:model-context bean="<%= kaleoDraftDefinition %>" model="<%= KaleoDraftDefinition.class %>" />

			<aui:input name="mvcPath" type="hidden" value="/designer/edit_kaleo_draft_definition.jsp" />
			<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
			<aui:input name="content" type="hidden" value="<%= content %>" />
			<aui:input name="version" type="hidden" />
			<aui:input name="draftVersion" type="hidden" />
			<aui:input name="latestDraftVersion" type="hidden" value="<%= (latestKaleoDraftDefinition == null) ? 1 : latestKaleoDraftDefinition.getDraftVersion() %>" />

			<liferay-ui:error exception="<%= DuplicateKaleoDraftDefinitionNameException.class %>" message="please-enter-a-unique-name" />
			<liferay-ui:error exception="<%= KaleoDraftDefinitionContentException.class %>" message="please-enter-valid-content" />
			<liferay-ui:error exception="<%= KaleoDraftDefinitionNameException.class %>" message="please-enter-a-valid-name" />
			<liferay-ui:error exception="<%= WorkflowException.class %>" message="an-error-occurred-in-the-workflow-engine" />

			<aui:fieldset>

				<%
				boolean disabled = (kaleoDraftDefinition == null) ? false : true;
				%>

				<c:if test="<%= disabled %>">
					<aui:input name="name" type="hidden" />
				</c:if>

				<aui:input disabled="<%= disabled %>" name="name" />

				<c:if test="<%= editable %>">
					<aui:input name="title" />
				</c:if>

				<c:if test="<%= kaleoDraftDefinition != null %>">
					<liferay-ui:panel-container extended="<%= false %>" id="kaleoDesignerDetailsPanelContainer" persistState="<%= true %>">
						<liferay-ui:panel collapsible="<%= true %>" cssClass="lfr-portlet-workflowdesigner-panel" extended="<%= false %>" id="kaleoDesignerSectionPanel" persistState="<%= true %>" title='<%= LanguageUtil.get(pageContext, "details") %>'>
							<div class="lfr-portlet-workflowdesigner-details-view">
								<c:if test="<%= workflowDefinition != null %>">
									<liferay-portlet:renderURL portletName="151" var="workflowDefinitionEditURL">
										<portlet:param name="struts_action" value="/workflow_definitions/edit_workflow_definition" />
										<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
										<portlet:param name="redirect" value="<%= currentURL %>" />
										<portlet:param name="name" value="<%= workflowDefinition.getName() %>" />
										<portlet:param name="version" value="<%= String.valueOf(workflowDefinition.getVersion()) %>" />
									</liferay-portlet:renderURL>

									<aui:field-wrapper cssClass="lfr-portlet-workflowdesigner-field-wrapper-first" helpMessage="the-definition-name-is-defined-in-the-workflow-definition-file" label="workflow-definition-name">
										<aui:a href="<%= themeDisplay.getURLControlPanel() + StringPool.AMPERSAND + HttpUtil.getQueryString(workflowDefinitionEditURL) %>" target="_blank"><%= HtmlUtil.escape(workflowDefinition.getName()) %></aui:a>
									</aui:field-wrapper>

									<aui:field-wrapper label="workflow-definition-version">
										<%= workflowDefinition.getVersion() %>
									</aui:field-wrapper>

									<aui:field-wrapper label="workflow-definition-status">
										<%= workflowDefinition.isActive() ? LanguageUtil.get(locale, "active") : LanguageUtil.get(locale, "not-active") %>
									</aui:field-wrapper>
								</c:if>

								<c:if test="<%= kaleoDraftDefinition != null %>">
									<aui:field-wrapper label="draft-version">
										<div id="<portlet:namespace />draftVersionLabel">
											<%= kaleoDraftDefinition.getDraftVersion() %>
										</div>
									</aui:field-wrapper>

									<c:if test="<%= editable %>">
										<aui:field-wrapper label="draft-history">
											<div class="lfr-portlet-workflowdesigner-toolbar" id="<portlet:namespace />kaleoDesignerToolbarContainer"></div>
										</aui:field-wrapper>
									</c:if>
								</c:if>
							</div>
						</liferay-ui:panel>
					</liferay-ui:panel-container>
				</c:if>

				<aui:button-row>
					<c:if test="<%= editable %>">
						<c:choose>
							<c:when test="<%= kaleoDraftDefinition == null %>">
								<c:if test="<%= KaleoDesignerPermission.contains(permissionChecker, themeDisplay.getCompanyGroupId(), ActionKeys.ADD_DRAFT) %>">
									<aui:button onClick='<%= renderResponse.getNamespace() + "addKaleoDraftDefinition();" %>' value="add-draft" />
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if test="<%= KaleoDraftDefinitionPermission.contains(permissionChecker, kaleoDraftDefinition, ActionKeys.UPDATE) %>">
									<aui:button onClick='<%= renderResponse.getNamespace() + "updateKaleoDraftDefinition();" %>' value="save-draft" />
								</c:if>

								<c:if test="<%= KaleoDesignerPermission.contains(permissionChecker, themeDisplay.getCompanyGroupId(), ActionKeys.PUBLISH) %>">
									<aui:button onClick='<%= renderResponse.getNamespace() + "publishKaleoDraftDefinition();" %>' value="publish" />
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:if>

					<span class="lfr-portlet-workflowdesigner-message" id="<portlet:namespace />toolbarMessage"></span>
				</aui:button-row>
			</aui:fieldset>
		</aui:form>

		<div class="diagram-builder" id="<portlet:namespace />diagramBuilder">
			<div class="diagram-builder-content" id="<portlet:namespace />diagramBuilderContent">
				<div class="tabbable">
					<div class="tabbable-content">
						<ul class="nav nav-tabs">
							<li class="active">
								<a href="javascript:;">
									<liferay-ui:message key="nodes" />
								</a>
							</li>
							<li>
								<a href="javascript:;">
									<liferay-ui:message key="settings" />
								</a>
							</li>
						</ul>

						<div class="tab-content">
							<div class="tab-pane"></div>

							<div class="tab-pane"></div>
						</div>
					</div>
				</div>
				<div class="diagram-builder-content-container">
					<div class="tabbable">
						<div class="tabbable-content">
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="javascript:;">
										<liferay-ui:message key="view" />
									</a>
								</li>
								<li>
									<a href="javascript:;">
										<liferay-ui:message key="source" /> (Kaleo <liferay-ui:message key="xml" />)
									</a>
								</li>
							</ul>

							<div class="tab-content">
								<div class="tab-pane">
									<div class="diagram-builder-canvas">
										<div class="diagram-builder-drop-container"></div>
									</div>
								</div>
								<div class="tab-pane">
									<div class="lfr-template-editor-wrapper" id="<portlet:namespace />editorWrapper"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<aui:script>
			<c:if test="<%= KaleoDesignerPermission.contains(permissionChecker, themeDisplay.getCompanyGroupId(), ActionKeys.ADD_DRAFT) %>">
				Liferay.provide(
					window,
					'<portlet:namespace />addKaleoDraftDefinition',
					function() {
						var A = AUI();

						<portlet:namespace />updateContent();

						<portlet:namespace />updateAction('<portlet:actionURL name="addKaleoDraftDefinition" />');

						submitForm(document.<portlet:namespace />fm);
					},
					['aui-base']
				);
			</c:if>

			Liferay.provide(
				window,
				'<portlet:namespace />afterTabViewChange',
				function(event) {
					var A = AUI();

					var tabContentNode = event.newVal.get('boundingBox');

					var kaleoDesigner = <portlet:namespace />kaleoDesigner;

					if ((tabContentNode === kaleoDesigner.viewNode) && kaleoDesigner.editor) {
						setTimeout(
							function() {
								kaleoDesigner.set('definition', kaleoDesigner.editor.get('value'));
							},
							0
						);
					}
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />getKaleoDraftDefinition',
				function(name, version, draftVersion, callback) {
					var A = AUI();

					<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="draftDefinitions" var="draftDefinitionsURL">
						<portlet:param name="name" value="LIFERAY_NAME" />
						<portlet:param name="version" value="LIFERAY_VERSION" />
						<portlet:param name="draftVersion" value="LIFERAY_DRAFT_VERSION" />
					</liferay-portlet:resourceURL>

					var contentURL = '<%= draftDefinitionsURL %>';

					contentURL = contentURL.replace('LIFERAY_NAME', name);
					contentURL = contentURL.replace('LIFERAY_VERSION', version);
					contentURL = contentURL.replace('LIFERAY_DRAFT_VERSION', draftVersion);

					A.io.request(
						contentURL,
						{
							dataType: 'json',
							on: {
								success: callback
							}
						}
					);
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />getLatestKaleoDraftDefinition',
				function(event, button) {
					var A = AUI();

					var button = event.target;

					if (!button.get('disabled')) {
						<portlet:namespace />navigateKaleoDraftDefinition(Infinity);
					}
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />initKaleoDraftDefinitionToolbar',
				function() {
					var A = AUI();

					<portlet:namespace />kaleoDesignerToolbar = new A.Toolbar(
						{
							activeState: false,
							boundingBox: '#<portlet:namespace />kaleoDesignerToolbarContainer',
							children: [
								{
									icon: 'icon-arrow-left',
									id: '<portlet:namespace />undoButton',
									label: '<liferay-ui:message key="undo" />',
									on: {
										click: <portlet:namespace />undoKaleoDraftDefinition
									}
								},
								{
									icon: 'icon-arrow-right',
									id: '<portlet:namespace />redoButton',
									label: '<liferay-ui:message key="redo" />',
									on: {
										click: <portlet:namespace />redoKaleoDraftDefinition
									}
								},
								{
									icon: 'icon-forward',
									id: '<portlet:namespace />latestButton',
									label: '<liferay-ui:message key="latest-version" />',
									on: {
										click: <portlet:namespace />getLatestKaleoDraftDefinition
									}
								}
							]
						}
					).render();

					<portlet:namespace />updateToolbarButtons();
				},
				['aui-toolbar']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />navigateKaleoDraftDefinition',
				function(direction) {
					var A = AUI();

					var draftVersion = A.one('#<portlet:namespace />draftVersion');
					var draftVersionLabel = A.one('#<portlet:namespace />draftVersionLabel');
					var latestDraftVersion = A.one('#<portlet:namespace />latestDraftVersion');

					var name = A.one('#<portlet:namespace />name');
					var title = A.one('#<portlet:namespace />title_<%= LocaleUtil.toLanguageId(locale) %>');
					var versionNode = A.one('#<portlet:namespace />version');

					var draftVersionVal = Liferay.Util.toNumber(draftVersion.val()) + direction;

					var latestDraftVersionVal = Liferay.Util.toNumber(latestDraftVersion.val());
					var versionVal = Liferay.Util.toNumber(versionNode.val());

					var currentDraftVersion = Liferay.Util.clamp(draftVersionVal, 1, latestDraftVersionVal);

					A.one('#<portlet:namespace />toolbarMessage').setContent('<liferay-ui:message key="loading-workflow-definition" />&hellip;');

					<portlet:namespace />getKaleoDraftDefinition(
						name.val(),
						versionVal,
						currentDraftVersion,
						function() {
							var responseData = this.get('responseData');

							if (responseData) {
								var kaleoDesigner = <portlet:namespace />kaleoDesigner;

								var content = responseData.content;

								draftVersion.val(responseData.draftVersion);

								draftVersionLabel.setContent(responseData.draftVersion);

								title.val(responseData.title);

								<portlet:namespace />updateToolbarButtons();

								kaleoDesigner.set('definition', content);

								if (kaleoDesigner.editor) {
									kaleoDesigner.editor.set('value', kaleoDesigner.toFormattedXML());
								}

								A.one('#<portlet:namespace />toolbarMessage').setContent('');
							}
						}
					);
				},
				['aui-base']
			);

			<c:if test="<%= KaleoDesignerPermission.contains(permissionChecker, themeDisplay.getCompanyGroupId(), ActionKeys.PUBLISH) %>">
				Liferay.provide(
					window,
					'<portlet:namespace />publishKaleoDraftDefinition',
					function() {
						var A = AUI();

						<portlet:namespace />updateContent();

						<portlet:namespace />updateAction('<portlet:actionURL name="publishKaleoDraftDefinition" />');

						submitForm(document.<portlet:namespace />fm);
					},
					['aui-base']
				);
			</c:if>

			Liferay.provide(
				window,
				'<portlet:namespace />redoKaleoDraftDefinition',
				function(event) {
					var A = AUI();

					var button = event.target;

					if (!button.get('disabled')) {
						<portlet:namespace />navigateKaleoDraftDefinition(1);
					}
				},
				['aui-base']
			);

			<c:if test="<%= (kaleoDraftDefinition != null) && KaleoDraftDefinitionPermission.contains(permissionChecker, kaleoDraftDefinition, ActionKeys.UPDATE) %>">
				Liferay.provide(
					window,
					'<portlet:namespace />updateKaleoDraftDefinition',
					function() {
						var A = AUI();

						<portlet:namespace />updateContent();

						<portlet:namespace />updateAction('<portlet:actionURL name="updateKaleoDraftDefinition" />');

						submitForm(document.<portlet:namespace />fm);
					},
					['aui-base']
				);
			</c:if>

			Liferay.provide(
				window,
				'<portlet:namespace />undoKaleoDraftDefinition',
				function(event) {
					var A = AUI();

					var button = event.target;

					if (!button.get('disabled')) {
						<portlet:namespace />navigateKaleoDraftDefinition(-1);
					}
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />updateAction',
				function(action) {
					var A = AUI();

					var form = A.one(document.<portlet:namespace />fm);

					form.attr('action', action);
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />updateContent',
				function() {
					var A = AUI();

					var content = A.one('#<portlet:namespace />content');

					var activeTab = <portlet:namespace />kaleoDesigner.contentTabView.get('selection');

					if (activeTab.get('contentNode') === <portlet:namespace />kaleoDesigner.sourceNode) {
						content.val(<portlet:namespace />kaleoDesigner.editor.get('value'));
					}
					else {
						content.val(<portlet:namespace />kaleoDesigner.toFormattedXML());
					}
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />updateToolbarButtons',
				function() {
					var A = AUI();

					var draftVersion = Liferay.Util.toNumber(A.one('#<portlet:namespace />draftVersion').val());
					var latestDraftVersion = Liferay.Util.toNumber(A.one('#<portlet:namespace />latestDraftVersion').val());

					var latestButton = <portlet:namespace />kaleoDesignerToolbar.item(2);
					var redoButton = <portlet:namespace />kaleoDesignerToolbar.item(1);
					var undoButton = <portlet:namespace />kaleoDesignerToolbar.item(0);

					latestButton.set('disabled', (draftVersion === latestDraftVersion));

					redoButton.set('disabled', (draftVersion === latestDraftVersion));

					undoButton.set('disabled', (draftVersion === 1));
				},
				['aui-base']
			);

			<%
			String saveCallback = ParamUtil.getString(request, "saveCallback");
			%>

			<c:if test="<%= (kaleoDraftDefinition != null) && Validator.isNotNull(saveCallback) %>">
				Liferay.Util.getOpener()['<%= HtmlUtil.escapeJS(saveCallback) %>']('<%= HtmlUtil.escapeJS(kaleoDraftDefinition.getName()) %>', <%= kaleoDraftDefinition.getVersion() %>, <%= kaleoDraftDefinition.getDraftVersion() %>);
			</c:if>
		</aui:script>

		<aui:script use="liferay-portlet-workflow-designer">
			var emptyFn = A.Lang.emptyFn;

			var MAP_ROLE_TYPES = {
				regular: 1,
				site: 2,
				organization: 3
			};

			<portlet:namespace />kaleoDesigner = new Liferay.KaleoDesigner(
				{
					<c:choose>
						<c:when test="<%= editable %>">

							<%
							String availableFields = ParamUtil.getString(request, "availableFields");
							%>

							<c:if test="<%= Validator.isNotNull(availableFields) %>">
								availableFields: A.Object.getValue(window, '<%= HtmlUtil.escapeJS(availableFields) %>'.split('.')),
							</c:if>
						</c:when>
						<c:otherwise>
							availableFields: [],
						</c:otherwise>
					</c:choose>

					<%
					String availablePropertyModels = ParamUtil.getString(request, "availablePropertyModels");
					%>

					<c:if test="<%= Validator.isNotNull(availablePropertyModels) %>">
						availablePropertyModels: A.Object.getValue(window, '<%= HtmlUtil.escapeJS(availablePropertyModels) %>'.split('.')),
					</c:if>

					boundingBox: '#<portlet:namespace />diagramBuilder',
					data: {

						<%
						long kaleoProcessId = ParamUtil.getLong(request, "kaleoProcessId");
						%>

						kaleoProcessId: '<%= kaleoProcessId %>'
					},
					definition: '<%= HtmlUtil.escapeJS(content) %>',
					disabled: <%= !editable %>,

					<%
					String propertiesSaveCallback = ParamUtil.getString(request, "propertiesSaveCallback");
					%>

					<c:if test="<%= Validator.isNotNull(propertiesSaveCallback) %>">
						on: {
							save: Liferay.Util.getOpener()['<%= HtmlUtil.escapeJS(propertiesSaveCallback) %>']
						},
					</c:if>

					portletNamespace: '<portlet:namespace />',

					<%
					String portletResourceNamespace = ParamUtil.getString(request, "portletResourceNamespace");
					%>

					portletResourceNamespace: '<%= HtmlUtil.escapeJS(portletResourceNamespace) %>',
					srcNode: '#<portlet:namespace />diagramBuilderContent'
				}
			).render();

			<portlet:namespace />kaleoDesigner.contentTabView.after(
				{
					selectionChange: <portlet:namespace />afterTabViewChange
				}
			);

			var fields = <portlet:namespace />kaleoDesigner.get('fields');

			if (fields.size() == 0) {
				<portlet:namespace />kaleoDesigner.set(
					'fields',
					[
						{
							name: 'StartNode',
							type: 'start',
							xy: [100, 40]
						},

						{
							actions: {
								description: [Liferay.KaleoDesignerStrings.approve],
								executionType: ['onEntry'],
								name: [Liferay.KaleoDesignerStrings.approve],
								script: ['Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.getLabelStatus("approved"), workflowContext);'],
								scriptLanguage: ['javascript']
							},
							name: 'EndNode',
							type: 'end',
							xy: [100, 500]
						}
					]
				);

				<portlet:namespace />kaleoDesigner.connect('StartNode', 'EndNode');
			}

			<%
			long ddmStructureId = ParamUtil.getLong(request, "ddmStructureId");
			%>

			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="forms" var="formsResourceURL">
				<portlet:param name="ddmStructureId" value="<%= String.valueOf(ddmStructureId) %>" />
			</liferay-portlet:resourceURL>

			var createFormsAutocomplete = function(inputNode, resultTextLocator, selectFn) {
				var autocomplete = Liferay.KaleoDesignerAutoCompleteUtil.create('<portlet:namespace />', inputNode, '<%= formsResourceURL %>', null, resultTextLocator, selectFn);

				autocomplete.sendRequest('');
			};

			var createRoleAutocomplete = function(inputNode, resultTextLocator, selectFn) {
				var instance = this;

				var roleType = 0;
				var roleTypeNode = inputNode.previous('[name=roleType]');

				if (roleTypeNode) {
					roleType = roleTypeNode.val();
				}

				var type = MAP_ROLE_TYPES[roleType] || 0;

				var autocomplete = Liferay.KaleoDesignerAutoCompleteUtil.create('<portlet:namespace />', inputNode, '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="roles" />', null, resultTextLocator, selectFn);

				autocomplete.set('requestTemplate', '&<portlet:namespace />type=' + type + '&<portlet:namespace />keywords={query}');

				autocomplete.sendRequest('');
			};

			var createUserAutocomplete = function(inputNode, resultTextLocator, selectFn) {
				var autocomplete = Liferay.KaleoDesignerAutoCompleteUtil.create('<portlet:namespace />', inputNode, '<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="users" />', null, resultTextLocator, selectFn);

				autocomplete.sendRequest('');
			};

			A.getDoc().delegate(
				'focus',
				function(event) {
					var inputNode = event.currentTarget;

					var inputName = inputNode.attr('name');

					if (inputNode.hasClass('forms-cell-editor-input')) {
						if (inputName == 'templateName') {
							createFormsAutocomplete(
								inputNode,
								null,
								function(event) {
									var data = event.result.raw;

									var formsEditor = A.Widget.getByNode(inputNode.ancestor('.basecelleditor'));

									var value = {
										templateId: [data.templateId],
										templateName: [data.name]
									};

									formsEditor.set('value', value);

									<portlet:namespace />kaleoDesigner.editingNode.set('forms', value);
								}
							);
						}
					}
					else {
						if ((inputName == 'roleName') || (inputName == 'roleNameAC')) {
							createRoleAutocomplete(
								inputNode,
								null,
								function(event) {
									var data = event.result.raw;
									var roleId = inputNode.next('[name=roleId]');

									if (roleId) {
										roleId.val(data.roleId);
									}
								}
							);
						}
						else if (inputName == 'fullName') {
							createUserAutocomplete(
								inputNode,
								inputName,
								function(event) {
									var data = event.result.raw;

									A.each(
										data,
										function(item, index, collection) {
											var input = inputNode.siblings('[name=' + index + ']').first();

											if (input) {
												input.val(data[index]);
											}
										}
									);
								}
							);
						}
					}
				},
				'.forms-cell-editor-input, .assignments-cell-editor-input'
			);

			<c:choose>
				<c:when test="<%= editable %>">
					<c:choose>
						<c:when test="<%= kaleoDraftDefinition == null %>">
							var name = A.one('#<portlet:namespace />name');

							if (name) {
								name.on(
									'valueChange',
									function(event) {
										<portlet:namespace />kaleoDesigner.set('definitionName', name.val());
									}
								);
							}
						</c:when>
						<c:otherwise>
							<portlet:namespace />initKaleoDraftDefinitionToolbar();
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					var disableKaleoDesignerInteractions = function() {
						var kaleoDesigner = <portlet:namespace />kaleoDesigner;

						kaleoDesigner.close = emptyFn;

						kaleoDesigner.deleteConnectors = emptyFn;

						kaleoDesigner.deleteSelectedNode = emptyFn;

						kaleoDesigner.editConnector = emptyFn;

						kaleoDesigner.contentTabView.disableTab(1);

						kaleoDesigner.availableFieldsDrag.dd.set('lock', true);

						kaleoDesigner.fieldsDrag.dd.set('lock', true);

						kaleoDesigner.get('fields').each(
							function(item, index, collection) {
								if (item.anchorsDrag) {
									item.anchorsDrag.dd.set('lock', true);
								}
							}
						);
					};

					var loadKaleoDesignerAssignedTaskForms = function() {
						var kaleoDesigner = <portlet:namespace />kaleoDesigner;

						kaleoDesigner.get('fields').each(
							function(item, index, collection) {
								if (item.get('type') === 'task') {
									var workflowTaskName = item.get('name');

									var getDDMTemplate = Liferay.Util.getOpener()["<%= HtmlUtil.escapeJS(portletResourceNamespace) %>getDDMTemplate"];

									if (getDDMTemplate) {
										getDDMTemplate(
											'<%= kaleoProcessId %>',
											workflowTaskName,
											function(json1, json2) {
												var templateName = kaleoDesigner.getLocalizedName(json2.name);

												if (templateName) {
													item.set(
														'forms',
														{
															templateId: [json1.DDMTemplateId],
															templateName: [templateName]
														}
													);

													item._uiSetName(
														A.Lang.sub(
															'{workflowTaskName} ({templateName})',
															{
																templateName: Liferay.Util.escapeHTML(templateName),
																workflowTaskName: workflowTaskName
															}
														)
													);
												}
											}
										);
									}
								}
							}
						);
					};

					disableKaleoDesignerInteractions();
					loadKaleoDesignerAssignedTaskForms();
				</c:otherwise>
			</c:choose>

			var dialog = Liferay.Util.getWindow();

			if (dialog) {
				dialog.on(
					'visibleChange',
					function(event) {
						if (!event.newVal) {
							<c:choose>
								<c:when test="<%= (workflowDefinition != null) && !workflowDefinition.isActive() %>">
									if (confirm('<liferay-ui:message key="do-you-want-to-publish-this-draft" />')) {
										event.halt();

										<portlet:namespace />publishKaleoDraftDefinition();
									}
								</c:when>
								<c:otherwise>

									<%
									boolean refreshOpenerOnClose = ParamUtil.getBoolean(request, "refreshOpenerOnClose");
									%>

									<c:if test="<%= Validator.isNotNull(portletResourceNamespace) && refreshOpenerOnClose %>">

										<%
										String openerWindowName = ParamUtil.getString(request, "openerWindowName");
										%>

										var openerWindow = Liferay.Util.getTop();

										<c:if test="<%= Validator.isNotNull(openerWindowName) %>">
											var openerDialog = Liferay.Util.getWindow('<%= HtmlUtil.escapeJS(openerWindowName) %>');

											openerWindow = openerDialog.iframe.node.get('contentWindow').getDOM();
										</c:if>

										openerWindow.Liferay.Portlet.refresh('#p_p_id<%= HtmlUtil.escapeJS(portletResourceNamespace) %>');
									</c:if>
								</c:otherwise>
							</c:choose>
						}
					}
				);
			}
		</aui:script>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-info">
			<liferay-ui:message key="no-workflow-engine-is-deployed" />
		</div>
	</c:otherwise>
</c:choose>