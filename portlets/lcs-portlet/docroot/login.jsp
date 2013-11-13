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

<h3><liferay-ui:message arguments="1" key="registration-step-x-2" /></h3>

<portlet:actionURL name="login" var="loginURL" />

<aui:form action="<%= loginURL %>" autocomplete="off" name="fm">
	<aui:input name="login">
		<aui:validator name="required" />

		<aui:validator name="email" />
	</aui:input>

	<aui:input name="password" type="password">
		<aui:validator name="required" />
	</aui:input>

	<aui:button-row>
		<aui:button disabled="true" name="loginButton" type="submit" value="next" />
	</aui:button-row>
</aui:form>

<aui:field-wrapper>
	<liferay-ui:message arguments="https://www.liferay.com/home?p_p_id=58&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_58_struts_action=%2Flogin%2Fforgot_password" key="forgot-your-x-or-x" />

	<liferay-ui:message arguments="https://www.liferay.com/home?p_p_id=58&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&saveLastPath=0&_58_struts_action=%2Flogin%2Fcreate_account" key="you-dont-have-account.-create-a-new-account" />
</aui:field-wrapper>

<aui:script use="aui-base">
	A.one('#<portlet:namespace/>fm').on(
		'input',
		function(event) {
			var userNameInputTxt = A.one('#<portlet:namespace/>login');
			var passwordInputTxt = A.one('#<portlet:namespace/>password');

			var loginButtonDisabled = !(userNameInputTxt.val() && passwordInputTxt.val());

			Liferay.Util.toggleDisabled(A.one('#<portlet:namespace />loginButton'), loginButtonDisabled);
		}
	);
</aui:script>