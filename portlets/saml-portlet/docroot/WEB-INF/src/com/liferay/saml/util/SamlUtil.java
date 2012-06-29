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

package com.liferay.saml.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.SSODescriptor;
import org.opensaml.saml2.metadata.SingleLogoutService;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;

import org.w3c.dom.Element;

/**
 * @author Mika Koivisto
 */
public class SamlUtil {

	public static AssertionConsumerService
			getAssertionConsumerServiceForBinding(
				SPSSODescriptor spSsoDescriptor, String binding)
		throws MetadataProviderException {

		AssertionConsumerService assertionConsumerService =
			spSsoDescriptor.getDefaultAssertionConsumerService();

		if (binding.equals(assertionConsumerService.getBinding())) {
			return assertionConsumerService;
		}

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		for (AssertionConsumerService curAssertionConsumerService :
				assertionConsumerServices) {

			if (binding.equals(curAssertionConsumerService.getBinding())) {
				return curAssertionConsumerService;
			}
		}

		throw new MetadataProviderException(
			"Binding " + binding + " is not supported");
	}

	public static Map<String, String> getAttributesMap(
		List<Attribute> attributes, Properties attributeMappings) {

		Map<String, String> attributesMap = new HashMap<String, String>();

		for (Object key : attributeMappings.keySet()) {
			String keyString = (String)key;

			String name = attributeMappings.getProperty(keyString);

			String value = _getAttributeValue(attributes, name);

			attributesMap.put(keyString, value);
		}

		return attributesMap;
	}

	public static SingleLogoutService getSingleLogoutServiceForBinding(
			SSODescriptor ssoDescriptor, String binding)
		throws MetadataProviderException {

		List<SingleLogoutService> singleLogoutServices =
			ssoDescriptor.getSingleLogoutServices();

		for (SingleLogoutService singleLogoutService : singleLogoutServices) {
			if (binding.equals(singleLogoutService.getBinding())) {
				return singleLogoutService;
			}
		}

		throw new MetadataProviderException(
			"Binding " + binding + " is not supported");
	}

	public static SingleSignOnService getSingleSignOnServiceForBinding(
			IDPSSODescriptor idpSsoDescriptor, String binding)
		throws MetadataProviderException {

		List<SingleSignOnService> singleSignOnServices =
			idpSsoDescriptor.getSingleSignOnServices();

		for (SingleSignOnService singleSignOnService : singleSignOnServices) {
			if (binding.equals(singleSignOnService.getBinding())) {
				return singleSignOnService;
			}
		}

		throw new MetadataProviderException(
			"Binding " + binding + " is not supported");
	}

	public static boolean isEnabled() {
		return GetterUtil.getBoolean(
			PropsUtil.get(PortletPropsKeys.SAML_ENABLED));
	}

	public static boolean isRoleIdp() {
		String samlRole = PropsUtil.get(PortletPropsKeys.SAML_ROLE);

		if (samlRole.equals("idp")) {
			return true;
		}

		return false;
	}

	public static boolean isRoleSp() {
		String samlRole = PropsUtil.get(PortletPropsKeys.SAML_ROLE);

		if (samlRole.equals("sp")) {
			return true;
		}

		return false;
	}

	public static AssertionConsumerService resolverAssertionConsumerService(
		SAMLMessageContext<AuthnRequest, ?, ?> samlMessageContext,
		String binding) {

		AuthnRequest authnRequest = samlMessageContext.getInboundSAMLMessage();

		Integer assertionConsumerServiceIndex = null;
		String assertionConsumerServiceURL = null;

		if (authnRequest != null) {
			assertionConsumerServiceIndex =
				authnRequest.getAssertionConsumerServiceIndex();
			assertionConsumerServiceURL =
				authnRequest.getAssertionConsumerServiceURL();
		}

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getPeerEntityRoleMetadata();

		for (AssertionConsumerService assertionConsumerService :
				spSsoDescriptor.getAssertionConsumerServices()) {

			if (!binding.equals(assertionConsumerService.getBinding())) {
				continue;
			}

			if ((assertionConsumerServiceIndex != null) &&
				(assertionConsumerService.getIndex().intValue() ==
					assertionConsumerServiceIndex.intValue())) {

				return assertionConsumerService;
			}
			else if (Validator.isNotNull(assertionConsumerServiceURL) &&
					assertionConsumerServiceURL.equals(
						assertionConsumerService.getLocation())) {

				return assertionConsumerService;
			}
		}

		for (AssertionConsumerService assertionConsumerService :
				spSsoDescriptor.getAssertionConsumerServices()) {

			if (binding.equals(assertionConsumerService.getBinding())) {
				return assertionConsumerService;
			}
		}

		return null;
	}

	private static String _getAttributeValue(
		List<Attribute> attributes, String name) {

		for (Attribute attribute : attributes) {
			if (name.equals(attribute.getName())) {
				Element element = attribute.getDOM();

				return element.getTextContent();
			}
		}

		return null;
	}

}