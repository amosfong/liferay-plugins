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
String redirect = ParamUtil.getString(request, "redirect");

SamlIdpSpConnection samlIdpSpConnection = null;

long samlIdpSpConnectionId = ParamUtil.getLong(request, "samlIdpSpConnectionId");

if (samlIdpSpConnectionId > 0) {
	samlIdpSpConnection = SamlIdpSpConnectionLocalServiceUtil.fetchSamlIdpSpConnection(samlIdpSpConnectionId);
}

String samlSpEntityId = ParamUtil.getString(request, "samlSpEntityId");
String name = ParamUtil.getString(request, "name");
boolean enabled = ParamUtil.getBoolean(request, "enabled");
String metadataUrl = ParamUtil.getString(request, "metadataUrl");

int assertionLifetime = ParamUtil.getInteger(request, "assertionLifetime", PortletPrefsPropsUtil.getInteger(PortletPropsKeys.SAML_IDP_ASSERTION_LIFETIME));
String attributeNames = ParamUtil.getString(request, "attributeNames", PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES));
boolean attributesEnabled = ParamUtil.getBoolean(request, "attributesEnabled", PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_ENABLED));
boolean attributesNamespaceEnabled = ParamUtil.getBoolean(request, "attributesNamespaceEnabled", PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTES_NAMESPACE_ENABLED));
String nameIdAttribute = ParamUtil.getString(request, "nameIdAttribute", PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE));
String nameIdFormat = ParamUtil.getString(request, "nameIdFormat", PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_FORMAT));
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (samlIdpSpConnection != null) ? samlIdpSpConnection.getName() : "new-service-provider" %>'
/>

<portlet:actionURL name="updateServiceProviderConnection" var="updateServiceProviderConnectionURL">
	<portlet:param name="mvcPath" value="/admin/edit_sp_connection.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateServiceProviderConnectionURL %>" enctype="multipart/form-data">
	<aui:model-context bean="<%= samlIdpSpConnection %>" model="<%= SamlIdpSpConnection.class %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="samlIdpSpConnectionId" type="hidden" />

	<liferay-ui:error exception="<%= DuplicateSamlIdpSpConnectionSamlSpEntityIdException.class %>" message="please-enter-a-unique-service-provider-entity-id" />
	<liferay-ui:error exception="<%= SamlIdpSpConnectionMetadataUrlException.class %>" message="please-enter-a-valid-metadata-endpoint-url" />
	<liferay-ui:error exception="<%= SamlIdpSpConnectionMetadataXmlException.class %>" message="please-enter-a-valid-metadata-xml" />
	<liferay-ui:error exception="<%= SamlSpIdpConnectionSamlIdpEntityIdException.class %>" message="please-enter-a-valid-service-provider-entity-id" />

	<aui:fieldset label="general">
		<aui:input label="name" name="name" value="<%= name %>" />

		<aui:input label="entity-id" name="samlSpEntityId" value="<%= samlSpEntityId %>" />

		<aui:input label="enabled" name="enabled" value="<%= enabled %>" />

		<aui:input label="assertion-lifetime" name="assertionLifetime" value="<%= String.valueOf(assertionLifetime) %>" />
	</aui:fieldset>

	<aui:fieldset label="metadata">
		<aui:input label="metadata-url" name="metadataUrl" value="<%= metadataUrl %>" />

		<aui:button-row>
			<aui:button onClick='<%= renderResponse.getNamespace() + "uploadMetadataXml();" %>' value="upload-metadata-xml" />
		</aui:button-row>


		<div class="aui-helper-hidden" id="<portlet:namespace />uploadMetadataXmlForm">
			<aui:fieldset label="upload-metadata">
				<aui:input label="metadata-xml" name="metadataXml" type="file" />
			</aui:fieldset>
		</div>
	</aui:fieldset>

	<aui:fieldset label="name-identifier">
		<aui:select label="name-identifier-format" name="nameIdFormat">
			<aui:option label="email-address" selected="<%= nameIdFormat.equals(NameIDType.EMAIL) %>" value="<%= NameIDType.EMAIL %>" />
			<aui:option label="unspecified" selected="<%= nameIdFormat.equals(NameIDType.UNSPECIFIED) %>" value="<%= NameIDType.UNSPECIFIED %>" />
			<aui:option label="entity" selected="<%= nameIdFormat.equals(NameIDType.ENTITY) %>" value="<%= NameIDType.ENTITY %>" />
			<aui:option label="persistent" selected="<%= nameIdFormat.equals(NameIDType.PERSISTENT) %>" value="<%= NameIDType.PERSISTENT %>" />
			<aui:option label="trancient" selected="<%= nameIdFormat.equals(NameIDType.TRANSIENT) %>" value="<%= NameIDType.TRANSIENT %>" />
			<aui:option label="x509-subject-name" selected="<%= nameIdFormat.equals(NameIDType.X509_SUBJECT) %>" value="<%= NameIDType.X509_SUBJECT %>" />
			<aui:option label="windows-domain-qualified-name" selected="<%= nameIdFormat.equals(NameIDType.WIN_DOMAIN_QUALIFIED) %>" value="<%= NameIDType.WIN_DOMAIN_QUALIFIED %>" />
			<aui:option label="kerberos" selected="<%= nameIdFormat.equals(NameIDType.KERBEROS) %>" value="<%= NameIDType.KERBEROS %>" />
			<aui:option label="encrypted" selected="<%= nameIdFormat.equals(NameIDType.ENCRYPTED) %>" value="<%= NameIDType.ENCRYPTED %>" />
		</aui:select>

		<aui:input label="name-identifier-attribute" name="nameIdAttribute" value="<%= nameIdAttribute %>" />
	</aui:fieldset>

	<aui:fieldset label="attributes">
		<aui:input label="attributes-enabled" name="attributesEnabled" value="<%= attributesEnabled %>" />

		<aui:input label="attributes-namespace-enabled" name="attributesNamespaceEnabled" value="<%= attributesNamespaceEnabled %>" />

		<aui:input label="attributes" name="attributeNames" value="<%= attributeNames %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>

<aui:script>
var uploadMetadataXmlForm = AUI().one("#<portlet:namespace />uploadMetadataXmlForm");

	function <portlet:namespace />uploadMetadataXml() {
		uploadMetadataXmlForm.show();
	}
</aui:script>