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

SamlSpIdpConnection samlSpIdpConnection = null;

String samlIdpEntityId = MetadataManagerUtil.getDefaultIdpEntityId();

String nameIdFormat = ParamUtil.getString(request, "nameIdFormat", PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_SP_NAME_ID_FORMAT, NameIDType.EMAIL));

if (Validator.isNotNull(samlIdpEntityId)) {
	try {
		samlSpIdpConnection = SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnection(themeDisplay.getCompanyId(), samlIdpEntityId);
	}
	catch (Exception e) {
	}

	nameIdFormat = ParamUtil.getString(request, "nameIdFormat", MetadataManagerUtil.getNameIdFormat(samlIdpEntityId));
}

long clockSkew = ParamUtil.getLong(request, "clockSkew", MetadataManagerUtil.getClockSkew());
String userAttributeMappings = ParamUtil.getString(request, "userAttributeMappings", PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_SP_USER_ATTRIBUTE_MAPPINGS));
%>

<portlet:actionURL name="updateIdentityProviderConnection" var="updateIdentityProviderConnectionURL">
	<portlet:param name="tabs1" value="identity-provider-connection" />
</portlet:actionURL>

<aui:form action="<%= updateIdentityProviderConnectionURL %>" enctype="multipart/form-data">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<liferay-ui:error exception="<%= DuplicateSamlSpIdpConnectionSamlIdpEntityIdException.class %>" message="please-enter-a-unique-identity-provider-entity-id" />
	<liferay-ui:error exception="<%= SamlSpIdpConnectionMetadataUrlException.class %>" message="please-enter-a-valid-metadata-endpoint-url" />
	<liferay-ui:error exception="<%= SamlSpIdpConnectionMetadataXmlException.class %>" message="please-enter-a-valid-metadata-xml" />
	<liferay-ui:error exception="<%= SamlSpIdpConnectionSamlIdpEntityIdException.class %>" message="please-enter-a-valid-identity-provider-entity-id" />

	<aui:model-context bean="<%= samlSpIdpConnection %>" model="<%= SamlSpIdpConnection.class %>" />

	<aui:input name="samlSpIdpConnectionId" type="hidden" />

	<aui:fieldset label="general">
		<aui:input name="name" required="true" />

		<aui:input helpMessage="identity-provider-connection-entity-id-help" label="entity-id" name="samlIdpEntityId" required="true" value="<%= samlIdpEntityId %>" />

		<aui:input helpMessage="clock-skew-help" name="clockSkew" value="<%= String.valueOf(clockSkew) %>" />
	</aui:fieldset>

	<aui:fieldset helpMessage="identity-provider-metadata-help" label="metadata">
		<aui:input name="metadataUrl" />

		<aui:button-row>
			<aui:button onClick='<%= renderResponse.getNamespace() + "uploadMetadataXml();" %>' value="upload-metadata-xml" />
		</aui:button-row>

		<div class="aui-helper-hidden hide" id="<portlet:namespace />uploadMetadataXmlForm">
			<aui:fieldset label="upload-metadata">
				<aui:input name="metadataXml" type="file" />
			</aui:fieldset>
		</div>
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
	</aui:fieldset>

	<aui:fieldset label="attributes">
		<aui:input helpMessage="attribute-mapping-help" label="attribute-mapping" name="userAttributeMappings" value="<%= userAttributeMappings %>" />
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