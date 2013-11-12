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

package com.liferay.saml.admin.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.CompanyServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.CertificateKeyPasswordException;
import com.liferay.saml.credential.KeyStoreManagerUtil;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.model.SamlIdpSpConnection;
import com.liferay.saml.service.SamlIdpSpConnectionLocalServiceUtil;
import com.liferay.saml.service.SamlSpIdpConnectionLocalServiceUtil;
import com.liferay.saml.util.CertificateUtil;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.InputStream;

import java.security.KeyPair;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.xml.security.utils.Base64;

import org.bouncycastle.asn1.x500.X500Name;

import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.xml.security.x509.X509Credential;

/**
 * @author Mika Koivisto
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteSamlIdpSpConnection(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long samlIdpSpConnectionId = ParamUtil.getLong(
			actionRequest, "samlIdpSpConnectionId");

		SamlIdpSpConnectionLocalServiceUtil.deleteSamlIdpSpConnection(
			samlIdpSpConnectionId);
	}

	public void downloadCertificate(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		X509Credential x509Credential =
			(X509Credential)MetadataManagerUtil.getSigningCredential();

		if (x509Credential == null) {
			return;
		}

		X509Certificate x509Certificate = x509Credential.getEntityCertificate();

		StringBundler sb = new StringBundler(3);

		sb.append("-----BEGIN CERTIFICATE-----\r\n");
		sb.append(Base64.encode(x509Certificate.getEncoded(), 76));
		sb.append("\r\n-----END CERTIFICATE-----");

		String content = sb.toString();

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse,
			MetadataManagerUtil.getLocalEntityId() + ".pem", content.getBytes(),
			ContentTypes.TEXT_PLAIN);
	}

	@Override
	public void serveResource(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		try {
			downloadCertificate(resourceRequest, resourceResponse);
		}
		catch (Exception e) {
			_log.error("Unable to send certificate", e);
		}
	}

	public void updateCertificate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UnicodeProperties properties = getProperties(actionRequest);

		String entityId = MetadataManagerUtil.getLocalEntityId();

		String certificateKeyPassword = properties.getProperty(
			PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD + "["+ entityId +
				"]");

		if (Validator.isNull(certificateKeyPassword)) {
			throw new CertificateKeyPasswordException();
		}

		String commonName = ParamUtil.getString(
			actionRequest, "certificateCommonName");
		String organization = ParamUtil.getString(
			actionRequest, "certificateOrganization");
		String organizationUnit = ParamUtil.getString(
			actionRequest, "certificateOrganizationUnit");
		String locality = ParamUtil.getString(
			actionRequest, "certificateLocality");
		String state = ParamUtil.getString(actionRequest, "certificateState");
		String country = ParamUtil.getString(
			actionRequest, "certificateCountry");

		X500Name subjectX500Name = CertificateUtil.createX500Name(
			commonName, organization, organizationUnit, locality, state,
			country);

		String keyAlgorithm = ParamUtil.getString(
			actionRequest, "certificateKeyAlgorithm");
		int keyLength = ParamUtil.getInteger(
			actionRequest, "certificateKeyLength");

		KeyPair keyPair = CertificateUtil.generateKeyPair(
			keyAlgorithm, keyLength);

		Date startDate = new Date(System.currentTimeMillis());

		int validityDays = ParamUtil.getInteger(
			actionRequest, "certificateValidityDays");

		Date endDate = new Date(startDate.getTime() + validityDays * Time.DAY);

		X509Certificate x509Certificate = CertificateUtil.generateCertificate(
			keyPair, subjectX500Name, subjectX500Name, startDate, endDate,
			"SHA1with" + keyAlgorithm);

		KeyStore.PrivateKeyEntry privateKeyEntry = new KeyStore.PrivateKeyEntry(
			keyPair.getPrivate(), new Certificate[] {x509Certificate});

		KeyStore keyStore = KeyStoreManagerUtil.getKeyStore();

		keyStore.setEntry(
			entityId, privateKeyEntry,
			new KeyStore.PasswordProtection(
				certificateKeyPassword.toCharArray()));

		KeyStoreManagerUtil.saveKeyStore(keyStore);

		updateProperties(actionRequest, properties);
	}

	public void updateGeneral(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UnicodeProperties properties = getProperties(actionRequest);

		boolean enabled = GetterUtil.getBoolean(
			properties.getProperty(PortletPropsKeys.SAML_ENABLED));

		if (enabled && (MetadataManagerUtil.getSigningCredential() == null)) {
			SessionErrors.add(actionRequest, "certificateInvalid");

			return;
		}

		String samlRole = properties.getProperty(
			PortletPropsKeys.SAML_ROLE, StringPool.BLANK);

		if (enabled && samlRole.equals("sp")) {
			String defaultIdpEntityId =
				MetadataManagerUtil.getDefaultIdpEntityId();

			MetadataProvider metadataProvider =
				MetadataManagerUtil.getMetadataProvider();

			if (Validator.isNull(defaultIdpEntityId) ||
				(metadataProvider.getRole(
					defaultIdpEntityId, IDPSSODescriptor.DEFAULT_ELEMENT_NAME,
					SAMLConstants.SAML20P_NS) == null)) {

				SessionErrors.add(actionRequest, "identityProviderInvalid");

				return;
			}
		}

		String currentEntityId = MetadataManagerUtil.getLocalEntityId();

		String newEntityId = properties.getProperty(
			PortletPropsKeys.SAML_ENTITY_ID);

		if (Validator.isNotNull(currentEntityId) &&
			!StringUtil.equalsIgnoreCase(currentEntityId, newEntityId)) {

			KeyStore keyStore = KeyStoreManagerUtil.getKeyStore();

			keyStore.deleteEntry(currentEntityId);

			KeyStoreManagerUtil.saveKeyStore(keyStore);
		}

		updateProperties(actionRequest, properties);
	}

	public void updateIdentityProvider(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UnicodeProperties properties = getProperties(actionRequest);

		String nameIdentifierAttributeType = ParamUtil.getString(
			actionRequest, "nameIdentifierAttributeType");

		if (Validator.isNotNull(nameIdentifierAttributeType)) {
			String nameIdentifierAttribute = properties.getProperty(
				PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE);

			nameIdentifierAttribute =
				nameIdentifierAttributeType + ":" + nameIdentifierAttribute;

			properties.setProperty(
				PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE,
				nameIdentifierAttribute);
		}

		updateProperties(actionRequest, properties);
	}

	public void updateIdentityProviderConnection(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long samlSpIdpConnectionId = ParamUtil.getLong(
			uploadPortletRequest, "samlSpIdpConnectionId");

		String samlIdpEntityId = ParamUtil.getString(
			uploadPortletRequest, "samlIdpEntityId");
		boolean assertionSignatureRequired = ParamUtil.getBoolean(
			uploadPortletRequest, "assertionSignatureRequired");
		long clockSkew = ParamUtil.getLong(uploadPortletRequest, "clockSkew");
		boolean enabled = true;
		boolean ldapImportEnabled = ParamUtil.getBoolean(
			uploadPortletRequest, "ldapImportEnabled");
		String metadataUrl = ParamUtil.getString(
			uploadPortletRequest, "metadataUrl");
		InputStream metadataXmlInputStream =
			uploadPortletRequest.getFileAsStream("metadataXml");
		String name = ParamUtil.getString(uploadPortletRequest, "name");
		String nameIdFormat = ParamUtil.getString(
			uploadPortletRequest, "nameIdFormat");
		boolean signAuthnRequest = ParamUtil.getBoolean(
			uploadPortletRequest, "signAuthnRequest");
		String userAttributeMappings = ParamUtil.getString(
			uploadPortletRequest, "userAttributeMappings");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		if (samlSpIdpConnectionId <= 0) {
			SamlSpIdpConnectionLocalServiceUtil.addSamlSpIdpConnection(
				samlIdpEntityId, assertionSignatureRequired, clockSkew, enabled,
				ldapImportEnabled, metadataUrl, metadataXmlInputStream, name,
				nameIdFormat, signAuthnRequest, userAttributeMappings,
				serviceContext);
		}
		else {
			SamlSpIdpConnectionLocalServiceUtil.updateSamlSpIdpConnection(
				samlSpIdpConnectionId, samlIdpEntityId,
				assertionSignatureRequired, clockSkew, enabled,
				ldapImportEnabled, metadataUrl, metadataXmlInputStream, name,
				nameIdFormat, signAuthnRequest, userAttributeMappings,
				serviceContext);
		}

		UnicodeProperties properties = new UnicodeProperties();

		properties.setProperty(
			PortletPropsKeys.SAML_SP_DEFAULT_IDP_ENTITY_ID, samlIdpEntityId);

		updateProperties(actionRequest, properties);
	}

	public void updateServiceProvider(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UnicodeProperties properties = getProperties(actionRequest);

		updateProperties(actionRequest, properties);
	}

	public void updateServiceProviderConnection(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long samlIdpSpConnectionId = ParamUtil.getLong(
			uploadPortletRequest, "samlIdpSpConnectionId");

		String samlSpEntityId = ParamUtil.getString(
			uploadPortletRequest, "samlSpEntityId");
		int assertionLifetime = ParamUtil.getInteger(
			uploadPortletRequest, "assertionLifetime");
		String attributeNames = ParamUtil.getString(
			uploadPortletRequest, "attributeNames");
		boolean attributesEnabled = ParamUtil.getBoolean(
			uploadPortletRequest, "attributesEnabled");
		boolean attributesNamespaceEnabled = ParamUtil.getBoolean(
			uploadPortletRequest, "attributesNamespaceEnabled");
		boolean enabled = ParamUtil.getBoolean(uploadPortletRequest, "enabled");
		String metadataUrl = ParamUtil.getString(
			uploadPortletRequest, "metadataUrl");
		InputStream metadataXmlInputStream =
			uploadPortletRequest.getFileAsStream("metadataXml");
		String name = ParamUtil.getString(uploadPortletRequest, "name");
		String nameIdAttribute = ParamUtil.getString(
			uploadPortletRequest, "nameIdAttribute");
		String nameIdFormat = ParamUtil.getString(
			uploadPortletRequest, "nameIdFormat");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			SamlIdpSpConnection.class.getName(), uploadPortletRequest);

		if (samlIdpSpConnectionId <= 0) {
			SamlIdpSpConnectionLocalServiceUtil.addSamlIdpSpConnection(
				samlSpEntityId, assertionLifetime, attributeNames,
				attributesEnabled, attributesNamespaceEnabled, enabled,
				metadataUrl, metadataXmlInputStream, name, nameIdAttribute,
				nameIdFormat, serviceContext);
		}
		else {
			SamlIdpSpConnectionLocalServiceUtil.updateSamlIdpSpConnection(
				samlIdpSpConnectionId, samlSpEntityId, assertionLifetime,
				attributeNames, attributesEnabled, attributesNamespaceEnabled,
				enabled, metadataUrl, metadataXmlInputStream, name,
				nameIdAttribute, nameIdFormat, serviceContext);
		}

		String redirect = ParamUtil.getString(uploadPortletRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			redirect = PortalUtil.escapeRedirect(redirect);

			actionRequest.setAttribute(WebKeys.REDIRECT, redirect);
		}

		sendRedirect(actionRequest, actionResponse);
	}

	@Override
	protected void checkPermissions(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException();
		}
	}

	protected UnicodeProperties getProperties(PortletRequest portletRequest) {
		return PropertiesParamUtil.getProperties(portletRequest, "settings--");
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		return true;
	}

	protected void updateProperties(
			PortletRequest portletRequest, UnicodeProperties properties)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		CompanyServiceUtil.updatePreferences(
			themeDisplay.getCompanyId(), properties);
	}

	private static Log _log = LogFactoryUtil.getLog(AdminPortlet.class);

}