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
String randomId = StringPool.BLANK;

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WorkflowTask workflowTask = null;

long kaleoProcessId = 0;

long ddlRecordId = 0;

if (row != null) {
	randomId = PwdGenerator.getPassword(PwdGenerator.KEY3, 4);

	workflowTask = (WorkflowTask)row.getObject();

	kaleoProcessId = (Long)row.getParameter("kaleoProcessId");

	ddlRecordId = (Long)row.getParameter("ddlRecordId");
}
else {
	workflowTask = (WorkflowTask)request.getAttribute(WebKeys.WORKFLOW_TASK);

	kaleoProcessId = ParamUtil.getLong(request, "kaleoProcessId");

	ddlRecordId = ParamUtil.getLong(request, "ddlRecordId");
}

KaleoProcessLink kaleoProcessLink = KaleoProcessLinkLocalServiceUtil.fetchKaleoProcessLink(kaleoProcessId, workflowTask.getName());

long kaleoProcessLinkId = 0;

if (kaleoProcessLink != null) {
	kaleoProcessLinkId = kaleoProcessLink.getKaleoProcessLinkId();
}

List<String> transitionNames = WorkflowTaskManagerUtil.getNextTransitionNames(company.getCompanyId(), user.getUserId(), workflowTask.getWorkflowTaskId());

boolean showCompleteFormButton = false;

if ((kaleoProcessLinkId > 0) && !workflowTask.isCompleted() && _isAssignedToUser(workflowTask, user) && KaleoProcessPermission.contains(permissionChecker, kaleoProcessId, ActionKeys.COMPLETE_FORM)) {
	showCompleteFormButton = true;
}

long[] pooledActorsIds = WorkflowTaskManagerUtil.getPooledActorsIds(company.getCompanyId(), workflowTask.getWorkflowTaskId());
%>

<liferay-ui:icon-menu showExpanded="<%= row == null %>" showWhenSingleIcon="<%= row == null %>">
	<c:if test="<%= showCompleteFormButton %>">
		<portlet:actionURL name="completeForm" var="completeFormURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="kaleoProcessId" value="<%= StringUtil.valueOf(kaleoProcessId) %>" />
			<portlet:param name="workflowTaskId" value="<%= StringUtil.valueOf(workflowTask.getWorkflowTaskId()) %>" />
			<portlet:param name="assigneeUserId" value="<%= StringUtil.valueOf(workflowTask.getAssigneeUserId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			cssClass='<%= "workflow-task-" + randomId + " task-change-status-link" %>'
			id='<%= randomId + "completeForm" %>'
			image="page"
			message="complete-form"
			method="get"
			url="<%= completeFormURL %>"
		/>
	</c:if>

	<c:if test="<%= !workflowTask.isCompleted() && _isAssignedToUser(workflowTask, user) %>">

		<%
		for (String transitionName : transitionNames) {
		%>

			<portlet:actionURL name="completeWorkflowTask" var="completeWorkflowTaskURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="workflowTaskId" value="<%= StringUtil.valueOf(workflowTask.getWorkflowTaskId()) %>" />
				<portlet:param name="assigneeUserId" value="<%= StringUtil.valueOf(workflowTask.getAssigneeUserId()) %>" />
				<portlet:param name="transitionName" value="<%= transitionName %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				cssClass='<%= "workflow-task-" + randomId + " task-change-status-link" %>'
				id='<%= randomId + HtmlUtil.escapeAttribute(transitionName) + "taskChangeStatusLink" %>'
				image="../aui/random"
				message="<%= HtmlUtil.escape(transitionName) %>"
				method="get"
				url="<%= completeWorkflowTaskURL %>"
			/>

		<%
		}
		%>

	</c:if>

	<c:if test="<%= !workflowTask.isCompleted() && !_isAssignedToUser(workflowTask, user) %>">
		<portlet:actionURL name="assignWorkflowTask" var="assignToMeURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="workflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
			<portlet:param name="assigneeUserId" value="<%= String.valueOf(user.getUserId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			cssClass='<%= "workflow-task-" + randomId + " task-assign-to-me-link" %>'
			id='<%= randomId + "taskAssignToMeLink" %>'
			image="assign"
			message="assign-to-me"
			method="get"
			url="<%= assignToMeURL %>"
		/>
	</c:if>

	<c:if test="<%= _hasOtherAssignees(pooledActorsIds, workflowTask, user) %>">
		<portlet:actionURL name="assignWorkflowTask" var="assignURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="workflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			cssClass='<%= "workflow-task-" + randomId + " task-assign-link" %>'
			id='<%= randomId + "taskAssignLink" %>'
			image="assign"
			message="assign-to-..."
			method="get"
			url="<%= assignURL %>"
		/>
	</c:if>

	<c:if test="<%= !workflowTask.isCompleted() %>">
		<portlet:actionURL name="updateWorkflowTask" var="updateDueDateURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="workflowTaskId" value="<%= StringUtil.valueOf(workflowTask.getWorkflowTaskId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon
			cssClass='<%= "workflow-task-" + randomId + " task-due-date-link" %>'
			id='<%= randomId + "taskDueDateLink" %>'
			image="time"
			message="update-due-date"
			method="get"
			url="<%= updateDueDateURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>

<div class="hide" id="<%= randomId %>updateAsignee">
	<c:if test="<%= _hasOtherAssignees(pooledActorsIds, workflowTask, user) %>">
		<aui:select label="assign-to" name="assigneeUserId">

			<%
			for (long pooledActorId : pooledActorsIds) {
				if (pooledActorId == user.getUserId()) {
					continue;
				}
			%>

				<aui:option label="<%= HtmlUtil.escape(PortalUtil.getUserName(pooledActorId, StringPool.BLANK)) %>" selected="<%= workflowTask.getAssigneeUserId() == pooledActorId %>" value="<%= String.valueOf(pooledActorId) %>" />

			<%
			}
			%>

		</aui:select>
	</c:if>
</div>

<div class="hide" id="<%= randomId %>updateAsigneeToMe">
	<aui:input name="asigneeUserId" type="hidden" value="<%= user.getUserId() %>" />
</div>

<div class="hide" id="<%= randomId %>updateDueDate">
	<aui:input bean="<%= workflowTask %>" model="<%= WorkflowTask.class %>" name="dueDate" />
</div>

<div class="hide" id="<%= randomId %>updateComments">
	<aui:input cols="55" name="comment" rows="10" type="textarea" />
</div>

<c:if test="<%= showCompleteFormButton %>">
	<div class="hide" id="<%= randomId %>editForm">
		<liferay-util:include page="/display/edit_form.jsp" servletContext="<%= application %>">
			<portlet:param name="workflowTaskId" value="<%= String.valueOf(workflowTask.getWorkflowTaskId()) %>" />
			<portlet:param name="kaleoProcessLinkId" value="<%= String.valueOf(kaleoProcessLink.getKaleoProcessLinkId()) %>" />
			<portlet:param name="ddlRecordId" value="<%= String.valueOf(ddlRecordId) %>" />
			<portlet:param name="formName" value='<%= randomId + "fm" %>' />
		</liferay-util:include>
	</div>
</c:if>

<aui:script use="liferay-kaleo-forms,liferay-workflow-tasks">
	var onTaskClickFn = A.rbind(Liferay.WorkflowTasks.onTaskClick, Liferay.WorkflowTasks, '<%= randomId %>');
	var onCompleteTaskClickFn = A.rbind(Liferay.KaleoForms.onCompleteTask, Liferay.KaleoForms, '<portlet:namespace />' , '<%= randomId %>');

	<c:if test="<%= showCompleteFormButton %>">
		Liferay.delegateClick('<portlet:namespace /><%= randomId %>completeForm', onCompleteTaskClickFn);
	</c:if>

	<c:if test="<%= !workflowTask.isCompleted() && _isAssignedToUser(workflowTask, user) %>">

		<%
		for (String transitionName : transitionNames) {
		%>

			Liferay.delegateClick('<portlet:namespace /><%= randomId + HtmlUtil.escapeJS(transitionName) %>taskChangeStatusLink', onTaskClickFn);

		<%
		}
		%>

	</c:if>

	Liferay.delegateClick('<portlet:namespace /><%= randomId %>taskAssignToMeLink', onTaskClickFn);
	Liferay.delegateClick('<portlet:namespace /><%= randomId %>taskAssignLink', onTaskClickFn);
	Liferay.delegateClick('<portlet:namespace /><%= randomId %>taskDueDateLink', onTaskClickFn);
</aui:script>