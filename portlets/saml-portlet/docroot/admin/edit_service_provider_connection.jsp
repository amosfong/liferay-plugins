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
String redirect = ParamUtil.getString(request, "redirect");

SamlIdpSpConnection samlIdpSpConnection = null;

long samlIdpSpConnectionId = ParamUtil.getLong(request, "samlIdpSpConnectionId");

if (samlIdpSpConnectionId > 0) {
	samlIdpSpConnection = SamlIdpSpConnectionLocalServiceUtil.fetchSamlIdpSpConnection(samlIdpSpConnectionId);
}

int assertionLifetime = ParamUtil.getInteger(request, "assertionLifetime", PortletPrefsPropsUtil.getInteger(PortletPropsKeys.SAML_IDP_ASSERTION_LIFETIME, MetadataManagerImpl.SAML_IDP_ASSERTION_LIFETIME_DEFAULT));
String attributeNames = ParamUtil.getString(request, "attributeNames", PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES));
boolean attributesEnabled = ParamUtil.getBoolean(request, "attributesEnabled", PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_ENABLED));
boolean attributesNamespaceEnabled = ParamUtil.getBoolean(request, "attributesNamespaceEnabled", PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_NAMESPACE_ENABLED));
String nameIdAttribute = ParamUtil.getString(request, "nameIdAttribute", PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE));
String nameIdFormat = ParamUtil.getString(request, "nameIdFormat", PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_FORMAT, StringPool.BLANK));
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (samlIdpSpConnection != null) ? samlIdpSpConnection.getName() : "new-service-provider" %>'
/>

<portlet:actionURL name="updateServiceProviderConnection" var="updateServiceProviderConnectionURL">
	<portlet:param name="mvcPath" value="/admin/edit_service_provider_connection.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateServiceProviderConnectionURL %>" enctype="multipart/form-data">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= DuplicateSamlIdpSpConnectionSamlSpEntityIdException.class %>" message="please-enter-a-unique-service-provider-entity-id" />
	<liferay-ui:error exception="<%= SamlIdpSpConnectionMetadataUrlException.class %>" message="please-enter-a-valid-metadata-endpoint-url" />
	<liferay-ui:error exception="<%= SamlIdpSpConnectionMetadataXmlException.class %>" message="please-enter-a-valid-metadata-xml" />
	<liferay-ui:error exception="<%= SamlIdpSpConnectionNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= SamlIdpSpConnectionSamlSpEntityIdException.class %>" message="please-enter-a-valid-service-provider-entity-id" />

	<aui:model-context bean="<%= samlIdpSpConnection %>" model="<%= SamlIdpSpConnection.class %>" />

	<aui:input name="samlIdpSpConnectionId" type="hidden" />

	<aui:fieldset label="general">
		<aui:input name="name" required="true" />

		<aui:input helpMessage="service-provider-connection-entity-id-help" label="entity-id" name="samlSpEntityId" required="true" />

		<aui:input name="enabled" />

		<aui:input helpMessage="assertion-lifetime-help" name="assertionLifetime" required="true" value="<%= String.valueOf(assertionLifetime) %>" />
	</aui:fieldset>

	<aui:fieldset helpMessage="service-provider-metadata-help" label="metadata">
		<aui:input name="metadataUrl" />

		<aui:button-row>
			<aui:button onClick='<%= renderResponse.getNamespace() + "uploadMetadataXml();" %>' value="upload-metadata-xml" />
		</aui:button-row>

		<aui:fieldset cssClass="aui-helper-hidden hide" id="uploadMetadataXmlForm" label="upload-metadata">
			<aui:input name="metadataXml" type="file" />
		</aui:fieldset>
	</aui:fieldset>

	<aui:fieldset label="name-identifier">
		<aui:select label="name-identifier-format" name="nameIdFormat">
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

		<aui:input helpMessage="name-identifier-attribute-name-help" label="name-identifier-attribute-name" name="nameIdAttribute" required="true" value="<%= nameIdAttribute %>" />
	</aui:fieldset>

	<aui:fieldset label="attributes">
		<aui:input name="attributesEnabled" value="<%= attributesEnabled %>" />

		<aui:input helpMessage="attributes-namespace-enabled-help" name="attributesNamespaceEnabled" value="<%= attributesNamespaceEnabled %>" />

		<aui:input helpMessage="attributes-help" label="attributes" name="attributeNames" value="<%= attributeNames %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />uploadMetadataXml',
		function() {
			var A = AUI();

			var uploadMetadataXmlForm = A.one('#<portlet:namespace />uploadMetadataXmlForm');

			if (uploadMetadataXmlForm) {
				uploadMetadataXmlForm.show();
			}
		},
		['aui-base']
	);
</aui:script>