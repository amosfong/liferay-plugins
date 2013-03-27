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
int assertionLifetime = PortletPrefsPropsUtil.getInteger(PortletPropsKeys.SAML_IDP_ASSERTION_LIFETIME, MetadataManagerImpl.SAML_IDP_ASSERTION_LIFETIME_DEFAULT);
String attributeNames = PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES);
boolean attributesEnabled = PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_ENABLED);
boolean attributesNamespaceEnabled = PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_NAMESPACE_ENABLED);
String nameIdFormat = PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_FORMAT, StringPool.BLANK);
String nameIdAttribute = PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE);
%>

<portlet:actionURL name="updateIdentityProvider" var="updateIdentityProviderURL">
	<portlet:param name="tabs1" value="identity-provider" />
</portlet:actionURL>

<aui:form action="<%= updateIdentityProviderURL %>">
	<aui:fieldset label="general">
		<aui:input label="sign-metadata" name='<%= "settings--" + PortletPropsKeys.SAML_SIGN_METADATA + "--" %>' type="checkbox" value="<%= MetadataManagerUtil.isSignMetadata() %>" />

		<aui:input label="ssl-required" name='<%= "settings--" + PortletPropsKeys.SAML_SSL_REQUIRED + "--" %>' type="checkbox" value="<%= MetadataManagerUtil.isSSLRequired() %>" />

		<aui:input label="authn-request-signature-required" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_AUTHN_REQUEST_SIGNATURE_REQUIRED + "--" %>' type="checkbox" value="<%= MetadataManagerUtil.isWantAuthnRequestSigned() %>" />
	</aui:fieldset>

	<aui:fieldset label="session">
		<aui:input helpMessage="session-maximum-age-help" label="session-maximum-age" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_SESSION_MAXIMUM_AGE + "--" %>' value="<%= MetadataManagerUtil.getSessionMaximumAge() %>" />

		<aui:input helpMessage="session-timeout-help" label="session-timeout" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_SESSION_TIMEOUT + "--" %>' value="<%= MetadataManagerUtil.getSessionTimeout() %>" />
	</aui:fieldset>

	<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" title="service-provider-defaults">
		<aui:input helpMessage="assertion-lifetime-help" label="assertion-lifetime" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_ASSERTION_LIFETIME + "--" %>' value="<%= String.valueOf(assertionLifetime) %>" />

		<aui:fieldset label="name-identifier">
			<aui:select label="name-identifier-format" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_FORMAT + "--" %>'>
				<aui:option label="email-address" selected="<%= nameIdFormat.equals(NameIDType.EMAIL) %>" value="<%= NameIDType.EMAIL %>" />
				<aui:option label="encrypted" selected="<%= nameIdFormat.equals(NameIDType.ENCRYPTED) %>" value="<%= NameIDType.ENCRYPTED %>" />
				<aui:option label="entity" selected="<%= nameIdFormat.equals(NameIDType.ENTITY) %>" value="<%= NameIDType.ENTITY %>" />
				<aui:option label="kerberos" selected="<%= nameIdFormat.equals(NameIDType.KERBEROS) %>" value="<%= NameIDType.KERBEROS %>" />
				<aui:option label="persistent" selected="<%= nameIdFormat.equals(NameIDType.PERSISTENT) %>" value="<%= NameIDType.PERSISTENT %>" />
				<aui:option label="trancient" selected="<%= nameIdFormat.equals(NameIDType.TRANSIENT) %>" value="<%= NameIDType.TRANSIENT %>" />
				<aui:option label="unspecified" selected="<%= nameIdFormat.equals(NameIDType.UNSPECIFIED) %>" value="<%= NameIDType.UNSPECIFIED %>" />
				<aui:option label="windows-domain-qualified-name" selected="<%= nameIdFormat.equals(NameIDType.WIN_DOMAIN_QUALIFIED) %>" value="<%= NameIDType.WIN_DOMAIN_QUALIFIED %>" />
				<aui:option label="x509-subject-name" selected="<%= nameIdFormat.equals(NameIDType.X509_SUBJECT) %>" value="<%= NameIDType.X509_SUBJECT %>" />
			</aui:select>

			<aui:input helpMessage="name-identifier-attribute-name-help" label="name-identifier-attribute-name" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE + "--" %>' value="<%= nameIdAttribute %>" />
		</aui:fieldset>

		<aui:fieldset label="attributes">
			<aui:input label="attributes-enabled" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_ENABLED + "--" %>' type="checkbox" value="<%= attributesEnabled %>" />

			<aui:input helpMessage="attributes-namespace-enabled-help" label="attributes-namespace-enabled" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_NAMESPACE_ENABLED + "--" %>' type="checkbox" value="<%= attributesNamespaceEnabled %>" />

			<aui:input helpMessage="attributes-help" label="attributes" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES + "--" %>' type="textarea" value="<%= attributeNames %>" />
		</aui:fieldset>
	</liferay-ui:panel>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>