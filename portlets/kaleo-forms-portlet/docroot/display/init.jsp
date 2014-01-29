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

<%@ page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowException" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowInstance" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowLog" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowLogManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTask" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil" %><%@
page import="com.liferay.portal.model.Role" %><%@
page import="com.liferay.portal.model.User" %><%@
page import="com.liferay.portal.security.auth.PrincipalException" %><%@
page import="com.liferay.portal.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.workflow.kaleo.forms.NoSuchKaleoProcessException" %><%@
page import="com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink" %><%@
page import="com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLinkLocalServiceUtil" %><%@
page import="com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.NoSuchTemplateException" %><%@
page import="com.liferay.util.PwdGenerator" %>

<%@ page import="java.io.Serializable" %>

<%!
private boolean _hasOtherAssignees(long[] pooledActorsIds, WorkflowTask workflowTask, User user) {
	if (pooledActorsIds.length == 0) {
		return false;
	}

	if (workflowTask.isCompleted()) {
		return false;
	}

	if ((pooledActorsIds.length == 1) && (pooledActorsIds[0] == user.getUserId())) {
		return false;
	}

	return true;
}

private boolean _isAssignedToUser(WorkflowTask workflowTask, User user) {
	if (workflowTask.getAssigneeUserId() == user.getUserId()) {
		return true;
	}

	return false;
}
%>