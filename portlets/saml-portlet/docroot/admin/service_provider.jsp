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

<%
boolean ldapImportEnabled = PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_SP_LDAP_IMPORT_ENABLED);
%>

<portlet:actionURL name="updateServiceProvider" var="updateServiceProviderURL">
	<portlet:param name="tabs1" value="service-provider" />
</portlet:actionURL>

<aui:form action="<%= updateServiceProviderURL %>">
	<aui:fieldset label="general">
		<aui:input label="assertion-signature-required" name='<%= "settings--" + PortletPropsKeys.SAML_SP_ASSERTION_SIGNATURE_REQUIRED + "--" %>' type="checkbox" value="<%= MetadataManagerUtil.isWantAssertionsSigned() %>" />

		<aui:input helpMessage="clock-skew-help" label="clock-skew" name='<%= "settings--" + PortletPropsKeys.SAML_SP_CLOCK_SKEW + "--" %>' value="<%= MetadataManagerUtil.getClockSkew() %>" />

		<aui:input label="ldap-import-enabled" name='<%= "settings--" + PortletPropsKeys.SAML_SP_LDAP_IMPORT_ENABLED + "--" %>' type="checkbox" value="<%= ldapImportEnabled %>" />

		<aui:input label="sign-authn-requests" name='<%= "settings--" + PortletPropsKeys.SAML_SP_SIGN_AUTHN_REQUEST + "--" %>' type="checkbox" value="<%= MetadataManagerUtil.isSignAuthnRequests() %>" />

		<aui:input label="sign-metadata" name='<%= "settings--" + PortletPropsKeys.SAML_SIGN_METADATA + "--" %>' type="checkbox" value="<%= MetadataManagerUtil.isSignMetadata() %>" />

		<aui:input label="ssl-required" name='<%= "settings--" + PortletPropsKeys.SAML_SSL_REQUIRED + "--" %>' type="checkbox" value="<%= MetadataManagerUtil.isSSLRequired() %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>