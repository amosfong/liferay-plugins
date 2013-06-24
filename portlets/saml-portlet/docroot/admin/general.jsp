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
String certificateCommonName = ParamUtil.getString(request, "certificateCommonName");
String certificateCountry = ParamUtil.getString(request, "certificateCountry");
String certificateLocality = ParamUtil.getString(request, "certificateLocality");
String certificateKeyAlgorithm = ParamUtil.getString(request, "certificateKeyAlgorithm", "RSA");
String certificateKeyLength = ParamUtil.getString(request, "certificateKeyLength", "2048");
String certificateOrganization = ParamUtil.getString(request, "certificateOrganization");
String certificateOrganizationUnit = ParamUtil.getString(request, "certificateOrganizationUnit");
String certificateState = ParamUtil.getString(request, "certificateState");
String certificateValidityDays = ParamUtil.getString(request, "certificateValidityDays", "356");

UnicodeProperties properties = PropertiesParamUtil.getProperties(request, "settings--");

String entityId = properties.getProperty(PortletPropsKeys.SAML_ENTITY_ID, MetadataManagerUtil.getLocalEntityId());

X509Certificate x509Certificate = null;

try {
	X509Credential x509Credential = (X509Credential)MetadataManagerUtil.getSigningCredential();

	if (x509Credential != null) {
		x509Certificate = x509Credential.getEntityCertificate();
	}
}
catch (Exception e) {
}
%>

<portlet:actionURL name="updateGeneral" var="updateGeneralURL">
	<portlet:param name="tabs1" value="general" />
</portlet:actionURL>

<aui:form action="<%= updateGeneralURL %>">
	<liferay-ui:error key="certificateInvalid" message="please-create-a-signing-credential-before-enabling" />
	<liferay-ui:error key="identityProviderInvalid" message="please-configure-identity-provider-before-enabling" />

	<aui:fieldset>
		<c:if test="<%= x509Certificate != null %>">
			<aui:input label="enabled" name='<%= "settings--" + PortletPropsKeys.SAML_ENABLED + "--" %>' type="checkbox" value="<%= SamlUtil.isEnabled() %>" />
		</c:if>

		<aui:select label="saml-role" name='<%= "settings--" + PortletPropsKeys.SAML_ROLE + "--" %>' required="true" showEmptyOption="<%= true %>">

			<%
			String samlRole = properties.getProperty(PortletPropsKeys.SAML_ROLE, PortletPrefsPropsUtil.getString(PortletPropsKeys.SAML_ROLE, StringPool.BLANK));
			%>

			<aui:option label="identity-provider" selected='<%= samlRole.equals("idp") %>' value="idp" />
			<aui:option label="service-provider" selected='<%= samlRole.equals("sp") %>' value="sp" />
		</aui:select>

		<aui:input helpMessage="entity-id-help" label="entity-id" name='<%= "settings--" + PortletPropsKeys.SAML_ENTITY_ID + "--" %>' required="true" value="<%= entityId %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>

<portlet:actionURL name="updateCertificate" var="updateCertificateURL">
	<portlet:param name="tabs1" value="general" />
</portlet:actionURL>

<c:if test="<%= Validator.isNotNull(entityId) %>">
	<aui:fieldset label="certificate-and-private-key">
		<c:choose>
			<c:when test="<%= x509Certificate != null %>">

				<%
				Date now = new Date();
				%>

				<c:if test="<%= now.after(x509Certificate.getNotAfter()) %>">
					<div class="portlet-msg-alert"><liferay-ui:message arguments="<%= new Object[] {x509Certificate.getNotAfter()} %>" key="certificate-expired-on-x" /></div>
				</c:if>

				<dl class="property-list">
					<dt>
						<liferay-ui:message key="subject-dn" />
					</dt>
					<dd>
						<%= CertificateUtil.getSubjectName(x509Certificate) %>
					</dd>
					<dt>
						<liferay-ui:message key="serial-number" />
					</dt>
					<dd>
						<%= CertificateUtil.getSerial(x509Certificate) %>

						<div class="portlet-msg-info-label">
							<liferay-ui:message arguments="<%= new Object[] {x509Certificate.getNotBefore(), x509Certificate.getNotAfter()} %>" key="valid-from-x-until-x" />
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
								<%= CertificateUtil.getFingerprint("MD5", x509Certificate) %>
							</dd>
							<dt>
								SHA1
							</dt>
							<dd>
								<%= CertificateUtil.getFingerprint("SHA1", x509Certificate) %>
							</dd>
						</dl>
					</dd>
					<dt>
						<liferay-ui:message key="signature-algorithm" />
					</dt>
					<dd>
						<%= x509Certificate.getSigAlgName() %>
					</dd>
				</dl>

				<portlet:resourceURL var="downloadCertificateURL" />

				<aui:button-row>
					<aui:button onClick='<%= renderResponse.getNamespace() + "toggleCertificateForm(true);" %>' value="replace-certificate" /> <aui:button href="<%= downloadCertificateURL %>" value="download-certificate" />
				</aui:button-row>
			</c:when>
			<c:when test="<%= (x509Certificate == null) && Validator.isNull(MetadataManagerUtil.getLocalEntityId()) %>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="entity-id-must-be-set-before-private-key-and-certificate-can-be-generated" />
				</div>
			</c:when>
		</c:choose>

		<aui:form action="<%= updateCertificateURL %>">
			<div class="<%= ((x509Certificate == null) && Validator.isNotNull(MetadataManagerUtil.getLocalEntityId())) ? "" : "aui-helper-hidden hide" %>" id="<portlet:namespace />certificateForm">
				<liferay-ui:error exception="<%= CertificateKeyPasswordException.class %>" message="please-enter-a-valid-key-password" />
				<liferay-ui:error exception="<%= InvalidParameterException.class %>" message="please-enter-a-valid-key-length-and-algorithm" />

				<aui:input label="common-name" name="certificateCommonName" required="true" value="<%= certificateCommonName %>" />

				<aui:input label="organization" name="certificateOrganization" required="true" value="<%= certificateOrganization %>" />

				<aui:input label="organization-unit" name="certificateOrganizationUnit" value="<%= certificateOrganizationUnit %>" />

				<aui:input label="locality" name="certificateLocality" value="<%= certificateLocality %>" />

				<aui:input label="state" name="certificateState" value="<%= certificateState %>" />

				<aui:input label="country" name="certificateCountry" required="true" value="<%= certificateCountry %>" />

				<aui:input label="validity-days" name="certificateValidityDays" required="true" value="<%= certificateValidityDays %>" />

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
					<aui:button type="submit" value="save" />

					<aui:button onClick='<%= renderResponse.getNamespace() + "toggleCertificateForm(false);" %>' value="cancel" />
				</aui:button-row>
			</div>
		</aui:form>
	</aui:fieldset>
</c:if>

<aui:script>
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