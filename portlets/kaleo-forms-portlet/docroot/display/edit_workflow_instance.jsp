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
String backURL = ParamUtil.getString(request, "backURL");

WorkflowInstance workflowInstance = (WorkflowInstance)request.getAttribute(WebKeys.WORKFLOW_INSTANCE);

Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

long companyId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
long groupId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID));
String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

DDLRecord ddlRecord = DDLRecordLocalServiceUtil.getDDLRecord(classPK);

KaleoProcess kaleoProcess = KaleoProcessLocalServiceUtil.getDDLRecordSetKaleoProcess(ddlRecord.getRecordSetId());

DDLRecordSet ddlRecordSet = kaleoProcess.getDDLRecordSet();

String headerTitle = LanguageUtil.get(pageContext, workflowInstance.getWorkflowDefinitionName());

headerTitle = headerTitle.concat(StringPool.COLON + StringPool.SPACE + ddlRecordSet.getName(locale));
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= false %>"
	title="<%= headerTitle %>"
/>

<aui:layout>
	<aui:column columnWidth="<%= 75 %>" cssClass="lfr-asset-column lfr-asset-column-details" first="<%= true %>">
		<aui:layout>
			<aui:column columnWidth="60">
				<div class="lfr-asset-status">
					<aui:field-wrapper label="state">
						<%= LanguageUtil.get(pageContext, HtmlUtil.escape(workflowInstance.getState())) %>
					</aui:field-wrapper>
				</div>
			</aui:column>

			<aui:column>
				<div class="lfr-asset-date">
					<aui:field-wrapper label="end-date">
						<%= (workflowInstance.getEndDate() == null) ? LanguageUtil.get(pageContext, "never") : dateFormatDateTime.format(workflowInstance.getEndDate()) %>
					</aui:field-wrapper>
				</div>
			</aui:column>
		</aui:layout>

		<liferay-ui:panel-container cssClass="task-panel-container" extended="<%= true %>" id="preview">
			<liferay-ui:panel defaultState="open" title="current-entry">

				<%
				Fields fields = ddlRecord.getFields();

				DDMStructure ddmStructure = ddlRecordSet.getDDMStructure();
				%>

				<liferay-ddm:html
					classNameId="<%= PortalUtil.getClassNameId(DDMStructure.class) %>"
					classPK="<%= ddmStructure.getStructureId() %>"
					fields="<%= fields %>"
					readOnly="<%= true %>"
					requestedLocale="<%= locale %>"
				/>
			</liferay-ui:panel>

			<%
			List<WorkflowTask> workflowTasks = WorkflowTaskManagerUtil.getWorkflowTasksByWorkflowInstance(company.getCompanyId(), user.getUserId(), workflowInstance.getWorkflowInstanceId(), null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			%>

			<c:if test="<%= !workflowTasks.isEmpty() %>">
				<liferay-ui:panel defaultState="open" title="tasks">

					<%
					PortletURL portletURL = renderResponse.createRenderURL();
					%>

					<liferay-ui:search-container
						emptyResultsMessage="there-are-no-tasks"
						iteratorURL="<%= portletURL %>"
					>
						<liferay-ui:search-container-results
							results="<%= workflowTasks %>"
						/>

						<liferay-ui:search-container-row
							className="com.liferay.portal.kernel.workflow.WorkflowTask"
							modelVar="workflowTask"
							stringKey="<%= true %>"
						>
							<liferay-ui:search-container-row-parameter
								name="kaleoProcessId"
								value="<%= kaleoProcess.getKaleoProcessId() %>"
							/>

							<liferay-ui:search-container-row-parameter
								name="ddlRecordId"
								value="<%= ddlRecord.getRecordId() %>"
							/>

							<liferay-ui:search-container-column-text
								buffer="buffer"
								name="task"
							>

								<%
								buffer.append("<span class=\"task-name\" id=\"");
								buffer.append(workflowTask.getWorkflowTaskId());
								buffer.append("\">");
								buffer.append(LanguageUtil.get(pageContext, HtmlUtil.escape(workflowTask.getName())));
								buffer.append("</span>");
								%>

							</liferay-ui:search-container-column-text>

							<c:choose>
								<c:when test="<%= workflowTask.getDueDate() == null %>">
									<liferay-ui:search-container-column-text
										name="due-date"
										value='<%= LanguageUtil.get(pageContext, "never") %>'
									/>
								</c:when>
								<c:otherwise>
									<liferay-ui:search-container-column-date
										name="due-date"
										value="<%= workflowTask.getDueDate() %>"
									/>
								</c:otherwise>
							</c:choose>

							<liferay-ui:search-container-column-text
								name="completed"
								value='<%= workflowTask.isCompleted() ? LanguageUtil.get(pageContext, "yes") : LanguageUtil.get(pageContext, "no") %>'
							/>

							<liferay-ui:search-container-column-jsp
								align="right"
								path="/display/workflow_task_action.jsp"
							/>
						</liferay-ui:search-container-row>
						<liferay-ui:search-iterator />
					</liferay-ui:search-container>
				</liferay-ui:panel>
			</c:if>

			<liferay-ui:panel defaultState="closed" title="activities">

				<%
				List<Integer> logTypes = new ArrayList<Integer>();

				logTypes.add(WorkflowLog.TASK_ASSIGN);
				logTypes.add(WorkflowLog.TASK_COMPLETION);
				logTypes.add(WorkflowLog.TASK_UPDATE);
				logTypes.add(WorkflowLog.TRANSITION);

				List<WorkflowLog> workflowLogs = WorkflowLogManagerUtil.getWorkflowLogsByWorkflowInstance(company.getCompanyId(), workflowInstance.getWorkflowInstanceId(), logTypes, QueryUtil.ALL_POS, QueryUtil.ALL_POS, WorkflowComparatorFactoryUtil.getLogCreateDateComparator(true));

				for (WorkflowLog workflowLog : workflowLogs) {
					Role curRole = null;
					User curUser = null;
					String actorName = null;

					if (workflowLog.getRoleId() != 0) {
						curRole = RoleLocalServiceUtil.getRole(workflowLog.getRoleId());
						actorName = curRole.getDescriptiveName();
					}
					else if (workflowLog.getUserId() != 0) {
						curUser = UserLocalServiceUtil.getUser(workflowLog.getUserId());
						actorName = curUser.getFullName();
					}
				%>

					<div class="task-activity task-type-<%= workflowLog.getType() %>">
						<div class="task-activity-date">
							<%= dateFormatDateTime.format(workflowLog.getCreateDate()) %>
						</div>

						<div class="task-activity-message">
							<c:choose>
								<c:when test="<%= workflowLog.getType() == WorkflowLog.TASK_COMPLETION %>">
									<%= LanguageUtil.format(pageContext, "x-completed-the-task-x", new Object[] {HtmlUtil.escape(actorName), HtmlUtil.escape(workflowLog.getState())}) %>
								</c:when>
								<c:when test="<%= workflowLog.getType() == WorkflowLog.TASK_UPDATE %>">
									<%= LanguageUtil.format(pageContext, "x-updated-the-due-date", HtmlUtil.escape(actorName)) %>
								</c:when>
								<c:when test="<%= workflowLog.getType() == WorkflowLog.TRANSITION %>">
									<%= LanguageUtil.format(pageContext, "x-changed-the-state-from-x-to-x", new Object[] {HtmlUtil.escape(actorName), HtmlUtil.escape(workflowLog.getPreviousState()), HtmlUtil.escape(workflowLog.getState())}) %>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="<%= (workflowLog.getPreviousUserId() == 0) && (curUser != null) %>">
											<%= LanguageUtil.format(pageContext, curUser.isMale() ? "x-assigned-the-task-to-himself" : "x-assigned-the-task-to-herself", HtmlUtil.escape(curUser.getFullName())) %>
										</c:when>
										<c:otherwise>

											<%
											String previousActorName = null;

											if (curRole == null) {
												previousActorName = PortalUtil.getUserName(workflowLog.getPreviousUserId(), StringPool.BLANK);
											%>

												<%= LanguageUtil.format(pageContext, "task-assigned-to-x.-previous-assignee-was-x", new Object[] {HtmlUtil.escape(actorName), HtmlUtil.escape(previousActorName)}) %>

											<%
											}
											else {
												previousActorName = curRole.getDescriptiveName();
											}
											%>

											<%= LanguageUtil.format(pageContext, "task-initially-assigned-to-the-x-role", new Object[] {HtmlUtil.escape(actorName)}) %>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</div>

						<div class="task-activity-comment">
							<%= workflowLog.getComment() %>
						</div>
					</div>

				<%
				}
				%>

			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</aui:column>

	<aui:column columnWidth="<%= 25 %>" cssClass="lfr-asset-column lfr-asset-column-actions" last="<%= true %>">
		<div class="lfr-asset-summary">
			<liferay-ui:icon
				cssClass="lfr-asset-avatar"
				image="../file_system/large/task"
				message="download"
			/>

			<div class="lfr-asset-name">
				<%= HtmlUtil.escape(workflowInstance.getWorkflowDefinitionName()) %>
			</div>
		</div>

		<%
		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		%>

		<liferay-util:include page="/display/workflow_instance_action.jsp" servletContext="<%= application %>" />
	</aui:column>
</aui:layout>

<%
PortalUtil.addPortletBreadcrumbEntry(request, headerTitle, currentURL);
%>