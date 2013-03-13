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

package com.liferay.saml.metadata;

import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.util.OpenSamlUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.KeyDescriptor;
import org.opensaml.saml2.metadata.RoleDescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.SingleLogoutService;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.credential.UsageType;

/**
 * @author Mika Koivisto
 */
public class MetadataGeneratorUtil {

	public static EntityDescriptor buildIdpEntityDescriptor(
			HttpServletRequest request, String entityId,
			boolean wantAuthnRequestSigned, boolean signMetadata,
			boolean requireSSL, Credential credential)
		throws Exception {

		EntityDescriptor entityDescriptor =
			OpenSamlUtil.buildEntityDescriptor();

		entityDescriptor.setEntityID(entityId);

		List<RoleDescriptor> roleDescriptors =
			entityDescriptor.getRoleDescriptors();

		RoleDescriptor roleDescriptor = buildIdpSsoDescriptor(
			request, entityId, wantAuthnRequestSigned, requireSSL, credential);

		roleDescriptors.add(roleDescriptor);

		if (signMetadata) {
			OpenSamlUtil.signObject(entityDescriptor, credential);
		}

		return entityDescriptor;
	}

	public static IDPSSODescriptor buildIdpSsoDescriptor(
			HttpServletRequest request, String entityId,
			boolean wantAuthnRequestSigned, boolean requireSSL,
			Credential credential)
		throws Exception {

		IDPSSODescriptor idpSsoDescriptor =
			OpenSamlUtil.buildIdpSsoDescriptor();

		idpSsoDescriptor.addSupportedProtocol(SAMLConstants.SAML20P_NS);

		idpSsoDescriptor.setID(entityId);
		idpSsoDescriptor.setWantAuthnRequestsSigned(wantAuthnRequestSigned);

		List<KeyDescriptor> keyDescriptors =
			idpSsoDescriptor.getKeyDescriptors();

		KeyDescriptor keyDescriptor = OpenSamlUtil.buildKeyDescriptor(
			UsageType.SIGNING, OpenSamlUtil.buildKeyInfo(credential));

		keyDescriptors.add(keyDescriptor);

		List<SingleSignOnService> singleSignOnServices =
			idpSsoDescriptor.getSingleSignOnServices();

		String portalURL = PortalUtil.getPortalURL(request, requireSSL);

		SingleSignOnService singleSignOnService =
			OpenSamlUtil.buildSingleSignOnService(
				SAMLConstants.SAML2_REDIRECT_BINDING_URI,
				portalURL.concat("/c/portal/saml/sso"));

		singleSignOnServices.add(singleSignOnService);

		singleSignOnService =
			OpenSamlUtil.buildSingleSignOnService(
				SAMLConstants.SAML2_POST_BINDING_URI,
				portalURL.concat("/c/portal/saml/sso"));

		singleSignOnServices.add(singleSignOnService);

		List<SingleLogoutService> singleLogoutServices =
			idpSsoDescriptor.getSingleLogoutServices();

		SingleLogoutService singleLogoutService =
			OpenSamlUtil.buildSingleLogoutService(
				SAMLConstants.SAML2_REDIRECT_BINDING_URI,
				portalURL.concat("/c/portal/saml/slo_redirect"));

		singleLogoutServices.add(singleLogoutService);

		return idpSsoDescriptor;
	}

	public static EntityDescriptor buildSpEntityDescriptor(
			HttpServletRequest request, String entityId,
			boolean signAuthnRequests, boolean signMetadata, boolean requireSSL,
			boolean wantAssertionsSigned, Credential credential)
		throws Exception {

		EntityDescriptor entityDescriptor =
			OpenSamlUtil.buildEntityDescriptor();

		entityDescriptor.setEntityID(entityId);

		List<RoleDescriptor> roleDescriptors =
			entityDescriptor.getRoleDescriptors();

		RoleDescriptor roleDescriptor = buildSpSsoDescriptor(
			request, entityId, signAuthnRequests, requireSSL,
			wantAssertionsSigned, credential);

		roleDescriptors.add(roleDescriptor);

		if (signMetadata) {
			OpenSamlUtil.signObject(entityDescriptor, credential);
		}

		return entityDescriptor;
	}

	public static SPSSODescriptor buildSpSsoDescriptor(
			HttpServletRequest request, String entityId,
			boolean signAuthnRequests, boolean requireSSL,
			boolean wantAssertionsSigned, Credential credential)
		throws Exception {

		SPSSODescriptor spSsoDescriptor = OpenSamlUtil.buildSpSsoDescriptor();

		spSsoDescriptor.addSupportedProtocol(SAMLConstants.SAML20P_NS);

		spSsoDescriptor.setAuthnRequestsSigned(signAuthnRequests);
		spSsoDescriptor.setID(entityId);
		spSsoDescriptor.setWantAssertionsSigned(wantAssertionsSigned);

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		String portalURL = PortalUtil.getPortalURL(request, requireSSL);

		AssertionConsumerService assertionConsumerService =
			OpenSamlUtil.buildAssertionConsumerService(
				SAMLConstants.SAML2_POST_BINDING_URI, 1, true,
				portalURL.concat("/c/portal/saml/acs"));

		assertionConsumerServices.add(assertionConsumerService);

		List<KeyDescriptor> keyDescriptors =
			spSsoDescriptor.getKeyDescriptors();

		KeyDescriptor keyDescriptor = OpenSamlUtil.buildKeyDescriptor(
			UsageType.SIGNING, OpenSamlUtil.buildKeyInfo(credential));

		keyDescriptors.add(keyDescriptor);

		List<SingleLogoutService> singleLogoutServices =
			spSsoDescriptor.getSingleLogoutServices();

		SingleLogoutService redirectSingleLogoutService =
			OpenSamlUtil.buildSingleLogoutService(
				SAMLConstants.SAML2_REDIRECT_BINDING_URI,
				portalURL.concat("/c/portal/saml/slo_redirect"));

		singleLogoutServices.add(redirectSingleLogoutService);

		SingleLogoutService soapSingleLogoutService =
			OpenSamlUtil.buildSingleLogoutService(
				SAMLConstants.SAML2_SOAP11_BINDING_URI,
				portalURL.concat("/c/portal/saml/slo_soap"));

		singleLogoutServices.add(soapSingleLogoutService);

		return spSsoDescriptor;
	}

}