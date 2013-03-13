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

<liferay-portlet:actionURL name="configureSalesforceConnection" var="actionURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</liferay-portlet:actionURL>

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<liferay-ui:error key="connectionFailed" message="unable-to-connect-to-salesforce" />
	<liferay-ui:error key="passwordRequired" message="please-enter-a-valid-password" />
	<liferay-ui:error key="userNameRequired" message="please-enter-a-valid-user-name" />

	<aui:fieldset>
		<aui:input cssClass="lfr-input-text-container" helpMessage="salesforce-server-url-help" label="salesforce-server-url" name="salesforceServerURL" type="text" value='<%= ParamUtil.getString(request, "salesforceServerURL", GetterUtil.getString(PrefsPortletPropsUtil.getString(company.getCompanyId(), PortletPropsKeys.SALESFORCE_SERVER_URL))) %>' />

		<aui:input cssClass="lfr-input-text-container" helpMessage="salesforce-user-name-help" label="salesforce-user-name" name="salesforceUserName" type="text" value='<%= ParamUtil.getString(request, "salesforceUserName", GetterUtil.getString(PrefsPortletPropsUtil.getString(company.getCompanyId(), PortletPropsKeys.SALESFORCE_USER_NAME))) %>' />

		<aui:input cssClass="lfr-input-text-container" helpMessage="salesforce-password-help" label="salesforce-password" name="salesforcePassword" type="text" value='<%= ParamUtil.getString(request, "salesforcePassword", GetterUtil.getString(PrefsPortletPropsUtil.getString(company.getCompanyId(), PortletPropsKeys.SALESFORCE_PASSWORD))) %>' />

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>