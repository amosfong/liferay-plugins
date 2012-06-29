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

package com.liferay.saml.resolver;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.saml.util.OpenSamlUtil;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.saml.util.SamlUtil;

import java.util.ArrayList;
import java.util.List;

import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;

/**
 * @author Mika Koivisto
 */
public class DefaultAttributeResolver implements AttributeResolver {

	public List<Attribute> resolve(
		User user, SAMLMessageContext<?, ?, ?> samlMessageContext) {

		List<Attribute> attributes = new ArrayList<Attribute>();

		String entityId = samlMessageContext.getPeerEntityId();

		for (String name : getAttributeNames(entityId)) {
			String value = null;

			if (name.startsWith("expando:")) {
				name = name.substring(8);

				ExpandoBridge expandoBridge = user.getExpandoBridge();

				value = String.valueOf(expandoBridge.getAttribute(name));
			}
			else {
				value = String.valueOf(
					BeanPropertiesUtil.getObject(user, name));
			}

			Attribute attribute = OpenSamlUtil.buildAttribute(name, value);

			attributes.add(attribute);
		}

		if (isPeerSalesForce(entityId)) {
			List<Attribute> salesForceAttributes = getSalesForceAttributes(
				samlMessageContext);

			if (!salesForceAttributes.isEmpty()) {
				attributes.addAll(salesForceAttributes);
			}
		}

		return attributes;
	}

	protected String[] getAttributeNames(String entityId) {
		String[] samlIdpMetadataAttributeNames = PropsUtil.getArray(
			PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES,
			new Filter(entityId));

		if (samlIdpMetadataAttributeNames == null) {
			samlIdpMetadataAttributeNames = PropsUtil.getArray(
				PortletPropsKeys.SAML_IDP_METADATA_ATTRIBUTE_NAMES);
		}

		return samlIdpMetadataAttributeNames;
	}

	protected List<Attribute> getSalesForceAttributes(
		SAMLMessageContext<?, ?, ?> samlMessageContext) {

		List<Attribute> attributes = new ArrayList<Attribute>();

		String samlIdpMetadataSalesForceLogoutUrl = GetterUtil.getString(
			PropsUtil.get(
				PortletPropsKeys.SAML_IDP_METADATA_SALESFORCE_LOGOUT_URL));

		Attribute logoutURLAttribute = OpenSamlUtil.buildAttribute(
			"logoutURL", samlIdpMetadataSalesForceLogoutUrl);

		attributes.add(logoutURLAttribute);

		String samlIdpMetadataSalesForceSsoStartPage = GetterUtil.getString(
			PropsUtil.get(
				PortletPropsKeys.SAML_IDP_METADATA_SALESFORCE_SSO_START_PAGE));

		try {
			IDPSSODescriptor idpSsoDescriptor =
				(IDPSSODescriptor)
					samlMessageContext.getLocalEntityRoleMetadata();

			SingleSignOnService singleSignOnService =
				SamlUtil.getSingleSignOnServiceForBinding(
					idpSsoDescriptor, SAMLConstants.SAML2_POST_BINDING_URI);

			samlIdpMetadataSalesForceSsoStartPage =
				singleSignOnService.getLocation();
		}
		catch (MetadataProviderException mpe) {
		}

		Attribute ssoStartPageAattribute = OpenSamlUtil.buildAttribute(
			"ssoStartPage", samlIdpMetadataSalesForceSsoStartPage);

		attributes.add(ssoStartPageAattribute);

		return attributes;
	}

	protected boolean isPeerSalesForce(String entityId) {
		if (entityId.equals(_SALESFORCE_ENTITY_ID)) {
			return true;
		}

		return GetterUtil.getBoolean(
			PropsUtil.get(
				PortletPropsKeys.
					SAML_IDP_METADATA_SALESFORCE_ATTRIBUTES_ENABLED,
				new Filter(entityId)), false);
	}

	private static final String _SALESFORCE_ENTITY_ID =
		"https://saml.salesforce.com";

}