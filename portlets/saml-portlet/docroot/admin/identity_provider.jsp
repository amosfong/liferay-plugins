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
<%@ include file="/admin/init.jsp" %>

<%
boolean attributesEnabled = PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_ENABLED);
boolean attributesNamespaceEnabled = PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_NAMESPACE_ENABLED);
String attributeNames = PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES);
int assertionLifetime = PortletPrefsPropsUtil.getInteger(PortletPropsKeys.SAML_IDP_ASSERTION_LIFETIME);
String nameIdentifierFormat = PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_FORMAT);
String nameIdentifierAttribute = PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE);
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
			<aui:input label="session-maximum-age" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_SESSION_MAXIMUM_AGE + "--" %>' value="<%= MetadataManagerUtil.getSessionMaximumAge() %>" />

			<aui:input label="session-timeout" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_SESSION_TIMEOUT + "--" %>' value="<%= MetadataManagerUtil.getSessionTimeout() %>" />
		</aui:fieldset>

		<liferay-ui:panel collapsible="<%= true %>" extended="<%= false %>" title="service-provider-defaults">
			<aui:input label="assertion-lifetime" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_ASSERTION_LIFETIME + "--" %>' value="<%= String.valueOf(assertionLifetime) %>" />

			<aui:fieldset label="name-identifier">
				<aui:select label="name-identifier-format" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_FORMAT + "--" %>'>
					<aui:option label="email-address" selected="<%= nameIdentifierFormat.equals(NameIDType.EMAIL) %>" value="<%= NameIDType.EMAIL %>" />
					<aui:option label="unspecified" selected="<%= nameIdentifierFormat.equals(NameIDType.UNSPECIFIED) %>" value="<%= NameIDType.UNSPECIFIED %>" />
					<aui:option label="entity" selected="<%= nameIdentifierFormat.equals(NameIDType.ENTITY) %>" value="<%= NameIDType.ENTITY %>" />
					<aui:option label="persistent" selected="<%= nameIdentifierFormat.equals(NameIDType.PERSISTENT) %>" value="<%= NameIDType.PERSISTENT %>" />
					<aui:option label="trancient" selected="<%= nameIdentifierFormat.equals(NameIDType.TRANSIENT) %>" value="<%= NameIDType.TRANSIENT %>" />
					<aui:option label="x509-subject-name" selected="<%= nameIdentifierFormat.equals(NameIDType.X509_SUBJECT) %>" value="<%= NameIDType.X509_SUBJECT %>" />
					<aui:option label="windows-domain-qualified-name" selected="<%= nameIdentifierFormat.equals(NameIDType.WIN_DOMAIN_QUALIFIED) %>" value="<%= NameIDType.WIN_DOMAIN_QUALIFIED %>" />
					<aui:option label="kerberos" selected="<%= nameIdentifierFormat.equals(NameIDType.KERBEROS) %>" value="<%= NameIDType.KERBEROS %>" />
					<aui:option label="encrypted" selected="<%= nameIdentifierFormat.equals(NameIDType.ENCRYPTED) %>" value="<%= NameIDType.ENCRYPTED %>" />
				</aui:select>

				<aui:input label="name-identifier-attribute" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE + "--" %>' value="<%= nameIdentifierAttribute %>" />
			</aui:fieldset>

			<aui:fieldset label="attributes">
				<aui:input label="attributes-enabled" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_ENABLED + "--" %>' type="checkbox" value="<%= attributesEnabled %>" />

				<aui:input label="attributes-namespace-enabled" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_NAMESPACE_ENABLED + "--" %>' type="checkbox" value="<%= attributesNamespaceEnabled %>" />

				<aui:input label="attributes" name='<%= "settings--" + PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES + "--" %>' type="textarea" value="<%= attributeNames %>" />
			</aui:fieldset>

		</liferay-ui:panel>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>