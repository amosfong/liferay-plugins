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

package com.liferay.saml.admin.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropertiesParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
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
import com.liferay.saml.credential.KeyStoreManagerUtil;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.model.SamlIdpSpConnection;
import com.liferay.saml.service.SamlIdpSpConnectionLocalServiceUtil;
import com.liferay.saml.util.CertificateUtil;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.io.InputStream;

import java.security.KeyPair;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.xml.security.utils.Base64;

import org.bouncycastle.asn1.x500.X500Name;

import org.opensaml.xml.security.x509.X509Credential;

/**
 * @author Mika Koivisto
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteSamlIdpSpConnection(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		long samlIdpSpConnectionId = ParamUtil.getLong(
			actionRequest, "samlIdpSpConnectionId");

		try {
			SamlIdpSpConnectionLocalServiceUtil.deleteSamlIdpSpConnection(
				samlIdpSpConnectionId);
		}
		catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass());
		}
	}

	public void downloadCertificate(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			X509Credential credential =
				(X509Credential)MetadataManagerUtil.getSigningCredential();

			if (credential != null) {
				X509Certificate certificate = credential.getEntityCertificate();

				StringBundler sb = new StringBundler(3);
				sb.append("-----BEGIN CERTIFICATE-----\r\n");
				sb.append(Base64.encode(certificate.getEncoded(), 76));
				sb.append("\r\n-----END CERTIFICATE-----");

				String entityId = MetadataManagerUtil.getLocalEntityId();

				PortletResponseUtil.sendFile(
					resourceRequest, resourceResponse, entityId + ".pem",
					sb.toString().getBytes(), "text/plain");
			}
		}
		catch (Exception e) {
			_log.warn("Unabled to send certificate", e);
		}
	}

	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		downloadCertificate(resourceRequest, resourceResponse);
	}

	public void updateCertificate(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		long companyId = PortalUtil.getCompanyId(actionRequest);

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
		int validityDays = ParamUtil.getInteger(
			actionRequest, "certificateValidityDays");
		String keyAlgorithm = ParamUtil.getString(
			actionRequest, "certificateKeyAlgorithm");
		int keyLength = ParamUtil.getInteger(
			actionRequest, "certificateKeyLength");

		Date startDate = new Date(System.currentTimeMillis());
		Date endDate = new Date(
			System.currentTimeMillis() + validityDays * Time.DAY);

		String signatureAlgorithm = "SHA1with".concat(keyAlgorithm);

		UnicodeProperties properties = PropertiesParamUtil.getProperties(
			actionRequest, "settings--");

		String entityId = MetadataManagerUtil.getLocalEntityId();

		String certificateKeyPassword = properties.getProperty(
			PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD.concat(
				"[").concat(entityId).concat("]"));

		if (Validator.isNull(certificateKeyPassword)) {
			SessionErrors.add(actionRequest, "certificatePassword");

			return;
		}

		try {
			CompanyServiceUtil.updatePreferences(companyId, properties);

			KeyPair keyPair = CertificateUtil.generateKeyPair(
				keyAlgorithm, keyLength);

			X500Name subject = CertificateUtil.createX500Name(
				commonName, organization, organizationUnit, locality, state,
				country);

			X509Certificate cert = CertificateUtil.generateCertificate(
				keyPair, subject, subject, startDate, endDate,
				signatureAlgorithm);

			KeyStore.PrivateKeyEntry pk = new KeyStore.PrivateKeyEntry(
				keyPair.getPrivate(), new Certificate[]{cert});

			KeyStore keyStore = KeyStoreManagerUtil.getKeyStore();

			keyStore.setEntry(
				entityId, pk,
				new KeyStore.PasswordProtection(
					certificateKeyPassword.toCharArray()));

			KeyStoreManagerUtil.saveKeyStore(keyStore);
		}
		catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void updateGeneral(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			long companyId = PortalUtil.getCompanyId(actionRequest);

			UnicodeProperties properties = PropertiesParamUtil.getProperties(
				actionRequest, "settings--");

			boolean enabled = GetterUtil.getBoolean(properties.getProperty(
				PortletPropsKeys.SAML_ENABLED));

			if (enabled &&
				(MetadataManagerUtil.getSigningCredential() == null)) {

				SessionErrors.add(actionRequest, "missingCertificate");

				return;
			}

			String currentEntityId = MetadataManagerUtil.getLocalEntityId();

			String newEntityId = properties.getProperty(
				PortletPropsKeys.SAML_ENTITY_ID);

			if (Validator.isNotNull(currentEntityId) &&
				!currentEntityId.equalsIgnoreCase(newEntityId)) {

				KeyStore keyStore = KeyStoreManagerUtil.getKeyStore();

				keyStore.deleteEntry(currentEntityId);

				KeyStoreManagerUtil.saveKeyStore(keyStore);
			}

			CompanyServiceUtil.updatePreferences(companyId, properties);
		}
		catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void updateIdentityProvider(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		long companyId = PortalUtil.getCompanyId(actionRequest);

		UnicodeProperties properties = PropertiesParamUtil.getProperties(
			actionRequest, "settings--");
		String nameIdentifierAttributeType = ParamUtil.getString(
			actionRequest, "nameIdentifierAttributeType");

		if (Validator.isNotNull(nameIdentifierAttributeType)) {
			String nameIdentifierAttribute = properties.getProperty(
				PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE);

			nameIdentifierAttribute =
				nameIdentifierAttributeType.concat(":").concat(
					nameIdentifierAttribute);

			properties.setProperty(
				PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_ATTRIBUTE,
				nameIdentifierAttribute);
		}

		try {
			CompanyServiceUtil.updatePreferences(companyId, properties);
		}
		catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

	public void updateServiceProviderConnection(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long samlIdpSpConnectionId = ParamUtil.getLong(
			uploadPortletRequest, "samlIdpSpConnectionId");
		String entityId = ParamUtil.getString(
			uploadPortletRequest, "samlSpEntityId");
		boolean enabled = ParamUtil.getBoolean(uploadPortletRequest, "enabled");
		int assertionLifetime = ParamUtil.getInteger(
			uploadPortletRequest, "assertionLifetime");
		String attributeNames = ParamUtil.getString(
			uploadPortletRequest, "attributeNames");
		boolean attributesEnabled = ParamUtil.getBoolean(
			uploadPortletRequest, "attributesEnabled");
		boolean attributesNamespaceEnabled = ParamUtil.getBoolean(
			uploadPortletRequest, "attributesNamespaceEnabled");
		String name = ParamUtil.getString(uploadPortletRequest, "name");
		String nameIdAttribute = ParamUtil.getString(
			uploadPortletRequest, "nameIdAttribute");
		String nameIdFormat = ParamUtil.getString(
			uploadPortletRequest, "nameIdFormat");
		String metadataUrl = ParamUtil.getString(
			uploadPortletRequest, "metadataUrl");
		InputStream metadataXmlInputStream =
			uploadPortletRequest.getFileAsStream("metadataXml");

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				SamlIdpSpConnection.class.getName(), actionRequest);

			if (samlIdpSpConnectionId > 0) {
				SamlIdpSpConnectionLocalServiceUtil.updateSamlIdpSpConnection(
					samlIdpSpConnectionId, entityId, assertionLifetime,
					attributeNames, attributesEnabled,
					attributesNamespaceEnabled, enabled, name, nameIdAttribute,
					nameIdFormat, metadataXmlInputStream, metadataUrl,
					serviceContext);
			}
			else {
				SamlIdpSpConnectionLocalServiceUtil.addSamlIdpSpConnection(
					entityId, assertionLifetime, attributeNames,
					attributesEnabled, attributesNamespaceEnabled, enabled,
					metadataXmlInputStream, name, nameIdAttribute, nameIdFormat,
					metadataUrl, serviceContext);
			}
		}
		catch (Exception e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
	}

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

	private static Log _log = LogFactoryUtil.getLog(AdminPortlet.class);

}