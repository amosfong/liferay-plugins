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
String randomId = PwdGenerator.getPassword(PwdGenerator.KEY3, 4);

String backURL = ParamUtil.getString(request, "backURL");

WorkflowTask workflowTask = (WorkflowTask)request.getAttribute(WebKeys.WORKFLOW_TASK);

WorkflowInstance workflowInstance = WorkflowInstanceManagerUtil.getWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId());

Map<String, Serializable> workflowContext = workflowInstance.getWorkflowContext();

long companyId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_COMPANY_ID));
long groupId = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_GROUP_ID));
String className = (String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);
long classPK = GetterUtil.getLong((String)workflowContext.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

DDLRecord ddlRecord = DDLRecordLocalServiceUtil.getDDLRecord(classPK);

KaleoProcess kaleoProcess = KaleoProcessLocalServiceUtil.getDDLRecordSetKaleoProcess(ddlRecord.getRecordSetId());

DDLRecordSet ddlRecordSet = kaleoProcess.getDDLRecordSet();

String headerTitle = LanguageUtil.get(pageContext, workflowTask.getName());

headerTitle = headerTitle.concat(StringPool.COLON + StringPool.SPACE + ddlRecordSet.getName(locale));
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= false %>"
	title="<%= headerTitle %>"
/>

<aui:layout>
	<aui:column columnWidth="<%= 75 %>" cssClass="lfr-asset-column lfr-asset-column-details" first="<%= true %>">
		<liferay-ui:error exception="<%= WorkflowTaskDueDateException.class %>" message="please-enter-a-valid-due-date" />

		<aui:layout>
			<aui:column columnWidth="60">
				<div class="lfr-asset-assigned">
					<aui:field-wrapper label="assigned-to">
						<c:choose>
							<c:when test="<%= workflowTask.isAssignedToSingleUser() %>">
								<%= HtmlUtil.escape(PortalUtil.getUserName(workflowTask.getAssigneeUserId(), StringPool.BLANK)) %>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="nobody" />
							</c:otherwise>
						</c:choose>

						<c:if test="<%= !workflowTask.isAssignedToSingleUser() %>">
							<%= StringPool.DASH %>

							<portlet:actionURL name="assignWorkflowTask" var="assignToMeURL">
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="workflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
								<portlet:param name="assigneeUserId" value="<%= String.valueOf(user.getUserId()) %>" />
							</portlet:actionURL>

							<span class="workflow-task task-assign-to-me-link"><aui:a href="<%= assignToMeURL %>" id='<%= randomId + "taskAssignToMeLink" %>' label="assign-to-me" /></span>
						</c:if>

						&nbsp;

						<%
						long[] pooledActorsIds = WorkflowTaskManagerUtil.getPooledActorsIds(company.getCompanyId(), workflowTask.getWorkflowTaskId());
						%>

						<c:if test="<%= _hasOtherAssignees(pooledActorsIds, workflowTask, user) %>">
							<%= StringPool.DASH %>

							<portlet:actionURL name="assignWorkflowTask" var="assignURL">
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="workflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
							</portlet:actionURL>

							<span class="workflow-task task-assign-link"><aui:a href="<%= assignURL %>" id='<%= randomId + "taskAssignLink" %>' label="assign-to-..." /></span>
						</c:if>
					</aui:field-wrapper>
				</div>

				<div class="lfr-asset-status">
					<aui:field-wrapper label="state">
						<%= LanguageUtil.get(pageContext, HtmlUtil.escape(WorkflowInstanceLinkLocalServiceUtil.getState(companyId, groupId, className, classPK))) %>
					</aui:field-wrapper>
				</div>
			</aui:column>

			<aui:column>
				<div class="lfr-asset-date">
					<aui:field-wrapper label="create-date">
						<%= dateFormatDateTime.format(workflowTask.getCreateDate()) %>
					</aui:field-wrapper>
				</div>

				<div class="lfr-asset-due-date">
					<aui:field-wrapper label="due-date">
						<%= (workflowTask.getDueDate() == null) ? LanguageUtil.get(pageContext, "never") : dateFormatDateTime.format(workflowTask.getDueDate()) %>

						<c:if test="<%= !workflowTask.isCompleted() %>">
							<portlet:actionURL name="updateWorkflowTask" var="updateDueDateURL">
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="workflowTaskId" value="<%= StringUtil.valueOf(workflowTask.getWorkflowTaskId()) %>" />
							</portlet:actionURL>

							<%= StringPool.DASH %> (<span class="workflow-task task-due-date-link"><aui:a href="<%= updateDueDateURL %>" id='<%= randomId + "taskDueDateLink" %>' label="change" />)
						</c:if>
					</aui:field-wrapper>
				</div>
			</aui:column>
		</aui:layout>

		<c:if test="<%= Validator.isNotNull(workflowTask.getDescription()) %>">
			<div class="lfr-asset-field">
				<aui:field-wrapper label="description">
					<%= workflowTask.getDescription() %>
				</aui:field-wrapper>
			</div>
		</c:if>

		<liferay-ui:panel-container cssClass="task-panel-container" extended="<%= true %>">
			<liferay-ui:panel defaultState="open" title="current-entry">

				<%
				Fields fields = ddlRecord.getFields();

				DDMStructure ddmStructure = ddlRecordSet.getDDMStructure();
				%>

				<liferay-ddm:html
					classNameId="<%= PortalUtil.getClassNameId(DDMStructure.class) %>"
					classPK="<%= ddmStructure.getStructureId() %>"
					fields="<%= fields %>"
					fieldsNamespace="<%= randomId %>"
					readOnly="<%= true %>"
					requestedLocale="<%= locale %>"
				/>

			</liferay-ui:panel>

			<liferay-ui:panel defaultState="closed" title="activities">

				<%
				List<Integer> logTypes = new ArrayList<Integer>();

				logTypes.add(WorkflowLog.TASK_ASSIGN);
				logTypes.add(WorkflowLog.TASK_COMPLETION);
				logTypes.add(WorkflowLog.TASK_UPDATE);
				logTypes.add(WorkflowLog.TRANSITION);

				List<WorkflowLog> workflowLogs = WorkflowLogManagerUtil.getWorkflowLogsByWorkflowInstance(company.getCompanyId(), workflowTask.getWorkflowInstanceId(), logTypes, QueryUtil.ALL_POS, QueryUtil.ALL_POS, WorkflowComparatorFactoryUtil.getLogCreateDateComparator(true));

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
								<c:when test="<%= (workflowLog.getType() == WorkflowLog.TRANSITION) %>">
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
											%>

												<%= LanguageUtil.format(pageContext, "task-initially-assigned-to-the-x-role", new Object[] {HtmlUtil.escape(actorName)}) %>

											<%
											}
											%>

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

			<div class="task-name">
				<%= LanguageUtil.get(pageContext, HtmlUtil.escape(workflowTask.getName())) %>
			</div>
		</div>

		<%
		request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
		%>

		<liferay-util:include page="/display/workflow_task_action.jsp" servletContext="<%= application %>">
			<liferay-util:param name="kaleoProcessId" value="<%= String.valueOf(kaleoProcess.getKaleoProcessId()) %>" />
			<liferay-util:param name="ddlRecordId" value="<%= String.valueOf(ddlRecord.getRecordId()) %>" />
		</liferay-util:include>
	</aui:column>
</aui:layout>

<aui:script use="liferay-workflow-tasks">
	var onTaskClickFn = A.rbind(Liferay.WorkflowTasks.onTaskClick, Liferay.WorkflowTasks, '');

	Liferay.delegateClick('<portlet:namespace /><%= randomId %>taskAssignToMeLink', onTaskClickFn);
	Liferay.delegateClick('<portlet:namespace /><%= randomId %>taskAssignLink', onTaskClickFn);
	Liferay.delegateClick('<portlet:namespace /><%= randomId %>taskDueDateLink', onTaskClickFn);
</aui:script>