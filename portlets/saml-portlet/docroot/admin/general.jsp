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
String certificateCommonName = ParamUtil.getString(request, "certificateCommonName");
String certificateOrganization = ParamUtil.getString(request, "certificateOrganization");
String certificateOrganizationUnit = ParamUtil.getString(request, "certificateOrganizationUnit");
String certificateLocality = ParamUtil.getString(request, "certificateLocality");
String certificateState = ParamUtil.getString(request, "certificateState");
String certificateCountry = ParamUtil.getString(request, "certificateCountry");
String certificateValidityDays = ParamUtil.getString(request, "certificateValidityDays");
String certificateKeyAlgorithm = ParamUtil.getString(request, "certificateKeyAlgorithm");
String certificateKeyLength = ParamUtil.getString(request, "certificateKeyLength");

String certificateFile = ParamUtil.getString(request, "certificateFile");

String certificateURL = ParamUtil.getString(request, "certificateURL");

String certificateSubjectName = null;

X509Credential credential = (X509Credential)MetadataManagerUtil.getSigningCredential();
X509Certificate certificate = null;

if (credential != null) {
	certificate = credential.getEntityCertificate();
}
%>

<portlet:actionURL name="updateGeneral" var="updateGeneralURL">
	<portlet:param name="tabs1" value="general" />
</portlet:actionURL>

<aui:form action="<%= updateGeneralURL %>">
	<liferay-ui:error key="missingCertificate" message="can-not-enable-before-signing-credential-is-created" />

	<aui:fieldset>
		<aui:input label="enabled" name='<%= "settings--" + PortletPropsKeys.SAML_ENABLED + "--" %>' type="checkbox" value="<%= SamlUtil.isEnabled() %>" />

		<aui:select label="saml-role" name='<%= "settings--" + PortletPropsKeys.SAML_ROLE + "--" %>' showEmptyOption="<%= true %>">
			<aui:option label="identity-provider" selected="<%= SamlUtil.isRoleIdp() %>" value="idp" />
			<aui:option label="service-provider" selected="<%= SamlUtil.isRoleSp() %>" value="sp" />
		</aui:select>

		<aui:input label="entity-id" name='<%= "settings--" + PortletPropsKeys.SAML_ENTITY_ID + "--" %>' value="<%= MetadataManagerUtil.getLocalEntityId() %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>

<portlet:actionURL name="updateCertificate" var="updateCertificateURL">
	<portlet:param name="tabs1" value="general" />
</portlet:actionURL>

<aui:fieldset label="certificate-and-private-key">
	<c:choose>
		<c:when test="<%= certificate != null %>">
			<dl class="property-list">
				<dt>
					<liferay-ui:message key="subject-dn" />
				</dt>
				<dd>
					<%= CertificateUtil.getSubjectName(certificate) %>
				</dd>
				<dt>
					<liferay-ui:message key="serial-number" />
				</dt>
				<dd>
					<%= CertificateUtil.getSerial(certificate) %>

					<div class="portlet-msg-info-label">
						<liferay-ui:message arguments="<%= new Object[] { certificate.getNotBefore(), certificate.getNotAfter() } %>" key="valid-from-x-until-x" />
					</div>
				</dd>
				<dt>
					<liferay-ui:message key="certificate-fingerprints" />
				</dt>
				<dd class="property-list">
					<dl>
						<dt>
							MD5
						</dt>
						<dd>
							<%= CertificateUtil.getFingerprint("MD5", certificate) %>
						</dd>
						<dt>
							SHA1
						</dt>
						<dd>
							<%= CertificateUtil.getFingerprint("SHA1", certificate) %>
						</dd>
					</dl>
				</dd>
				<dt>
					<liferay-ui:message key="signature-algorithm" />
				</dt>
				<dd>
					<%= certificate.getSigAlgName() %>
				</dd>
			</dl>

			<portlet:resourceURL var="downloadCertificateURL" />

			<aui:button-row>
				<aui:button onClick='<%= renderResponse.getNamespace() + "updateCertificate();" %>' value="replace-certificate" /> <aui:button href="<%= downloadCertificateURL %>" value="download-certificate" />
			</aui:button-row>
		</c:when>
		<c:when test="<%= (certificate == null) && Validator.isNull(MetadataManagerUtil.getLocalEntityId()) %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="entity-id-must-be-set-before-private-key-and-certificate-can-be-generated" />
			</div>
		</c:when>
	</c:choose>

	<%
	String certificateFormCssClass= "aui-helper-hidden";

	if ((certificate == null) && Validator.isNotNull(MetadataManagerUtil.getLocalEntityId())) {
		certificateFormCssClass = StringPool.BLANK;
	}
	%>

	<aui:form action="<%= updateCertificateURL %>" id='<%= renderResponse.getNamespace() + "_manual" %>'>
			<div class="<%= certificateFormCssClass %>" id="<portlet:namespace />certificateForm">
				<liferay-ui:error exception="<%= java.security.InvalidParameterException.class %>" message="please-enter-a-valid-key-length-and-algorithm" />

				<aui:input label="common-name" name="certificateCommonName" required="true" value="<%= certificateCommonName %>" />

				<aui:input label="organization" name="certificateOrganization" required="true" value="<%= certificateOrganization %>" />

				<aui:input label="organization-unit" name="certificateOrganizationUnit" value="<%= certificateOrganizationUnit %>" />

				<aui:input label="locality" name="certificateLocality" value="<%= certificateLocality %>" />

				<aui:input label="state" name="certificateState" value="<%= certificateState %>" />

				<aui:input label="country" name="certificateCountry" required="true" value="<%= certificateCountry %>" />

				<aui:input label="validity" name="certificateValidityDays" required="true" value="<%= certificateValidityDays %>" />

				<aui:select label="key-algorithm" name="certificateKeyAlgorithm" required="true">
					<aui:option label="rsa" selected='<%= certificateKeyAlgorithm.equals("RSA") %>' value="RSA" />
					<aui:option label="dsa" selected='<%= certificateKeyAlgorithm.equals("DSA") %>' value="DSA" />
				</aui:select>

				<aui:select label="key-length-bits" name="certificateKeyLength" required="true">
					<aui:option label="4096" selected='<%= certificateKeyLength.equals("4096") %>' value="4096" />
					<aui:option label="2048" selected='<%= certificateKeyLength.equals("2048") %>' value="2048" />
					<aui:option label="1024" selected='<%= certificateKeyLength.equals("1024") %>' value="1024" />
					<aui:option label="512" selected='<%= certificateKeyLength.equals("512") %>' value="512" />
				</aui:select>

				<aui:input label="key-password" name='<%= "settings--" + PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD + "[" + MetadataManagerUtil.getLocalEntityId() + "]--" %>' required="true" type="password" value="" />

				<aui:button-row>
					<aui:button id='<%= renderResponse.getNamespace() + "_manualSave" %>' type="submit" value="save" />

					<aui:button onClick='<%= renderResponse.getNamespace() + "cancelUpdateCertificate();" %>' value="cancel" />
				</aui:button-row>
			</div>
	</aui:form>
</aui:fieldset>

<aui:script>
	function <portlet:namespace />updateCertificate() {
		<portlet:namespace />toggleCertificateForm(true);
	}

	function <portlet:namespace />cancelUpdateCertificate() {
		<portlet:namespace />toggleCertificateForm(false);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />toggleCertificateForm',
		function(visible) {
			var A = AUI();

			var certificateForm = A.one('#<portlet:namespace />certificateForm');

			if (certificateForm) {
				certificateForm.toggle(visible);
			}
		}
	);
</aui:script>