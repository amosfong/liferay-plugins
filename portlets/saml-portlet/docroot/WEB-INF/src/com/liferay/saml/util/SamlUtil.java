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

package com.liferay.saml.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.EntitiesDescriptor;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.SSODescriptor;
import org.opensaml.saml2.metadata.SingleLogoutService;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.util.DatatypeHelper;

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

	public static EntityDescriptor getEntityDescriptorById(
		String entityId, EntitiesDescriptor descriptor) {

		List<EntityDescriptor> entityDescriptors =
			descriptor.getEntityDescriptors();

		if ((entityDescriptors != null) && !entityDescriptors.isEmpty()) {
			for (EntityDescriptor entityDescriptor : entityDescriptors) {
				if (DatatypeHelper.safeEquals(
						entityDescriptor.getEntityID(), entityId)) {

					return entityDescriptor;
				}
			}
		}

		return null;
	}

	public static EntityDescriptor getEntityDescriptorById(
		String entityId, XMLObject metadata) {

		if (metadata instanceof EntityDescriptor) {
			EntityDescriptor entityDescriptor = (EntityDescriptor)metadata;

			if (DatatypeHelper.safeEquals(
					entityDescriptor.getEntityID(), entityId)) {

				return entityDescriptor;
			}
		}
		else if (metadata instanceof EntitiesDescriptor) {
			return getEntityDescriptorById(
				entityId, (EntitiesDescriptor)metadata);
		}

		return null;
	}

	public static String getRequestPath(HttpServletRequest request) {
		String requestURI = request.getRequestURI();

		String contextPath = request.getContextPath();

		if (Validator.isNotNull(contextPath) &&
			!contextPath.equals(StringPool.SLASH)) {

			requestURI = requestURI.substring(contextPath.length());
		}

		Matcher matcher = _pattern.matcher(requestURI);

		return matcher.replaceFirst(StringPool.BLANK);
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
		return PortletPrefsPropsUtil.getBoolean(PortletPropsKeys.SAML_ENABLED);
	}

	public static boolean isLdapImportEnabled() {
		return PortletPrefsPropsUtil.getBoolean(
			PortletPropsKeys.SAML_SP_LDAP_IMPORT_ENABLED);
	}

	public static boolean isRoleIdp() {
		String samlRole = PortletPrefsPropsUtil.getString(
			PortletPropsKeys.SAML_ROLE);

		if (Validator.isNotNull(samlRole) && samlRole.equals("idp")) {
			return true;
		}

		return false;
	}

	public static boolean isRoleSp() {
		String samlRole = PortletPrefsPropsUtil.getString(
			PortletPropsKeys.SAML_ROLE);

		if (Validator.isNotNull(samlRole) && samlRole.equals("sp")) {
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

	public static SingleLogoutService resolveSingleLogoutService(
		SSODescriptor ssoDescriptor, String preferredBinding) {

		List<SingleLogoutService> singleLogoutServices =
			ssoDescriptor.getSingleLogoutServices();

		for (SingleLogoutService singleLogoutService : singleLogoutServices) {
			if (preferredBinding.equals(singleLogoutService.getBinding())) {
				return singleLogoutService;
			}
		}

		if (!singleLogoutServices.isEmpty()) {
			return singleLogoutServices.get(0);
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

	private static Pattern _pattern = Pattern.compile(";jsessionid=.*");

}