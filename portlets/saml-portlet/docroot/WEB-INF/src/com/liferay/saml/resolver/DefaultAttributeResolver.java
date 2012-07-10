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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.util.OpenSamlUtil;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.saml.util.SamlUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.XMLObject;

/**
 * @author Mika Koivisto
 */
public class DefaultAttributeResolver implements AttributeResolver {

	public List<Attribute> resolve(
		User user, SAMLMessageContext<?, ?, ?> samlMessageContext) {

		List<Attribute> attributes = new ArrayList<Attribute>();

		String entityId = samlMessageContext.getPeerEntityId();

		boolean namespaceEnabled =
			MetadataManagerUtil.isAttributesNamespaceEnabled(
				samlMessageContext.getPeerEntityId());

		for (String name : getAttributeNames(entityId)) {
			if (name.startsWith("expando:")) {
				name = name.substring(8);

				addExpandoAttribute(
					name, namespaceEnabled, user, samlMessageContext,
					attributes);
			}
			else if (name.equals("groups")) {
				addGroupsAttribute(
					name, namespaceEnabled, user, samlMessageContext,
					attributes);
			}
			else if (name.equals("organizations")) {
				addOrganizationsAttribute(
					name, namespaceEnabled, user, samlMessageContext,
					attributes);
			}
			else if (name.equals("roles")) {
				addRolesAttribute(
					name, namespaceEnabled, user, samlMessageContext,
					attributes);
			}
			else if (name.equals("userGroups")) {
				addUserGroupsAttribute(
					name, namespaceEnabled, user, samlMessageContext,
					attributes);
			}
			else if (name.equals("userGroupRoles")) {
				addUserGroupRolesAttribute(
					name, namespaceEnabled, user, samlMessageContext,
					attributes);
			}
			else {
				addUserAttribute(
					name, namespaceEnabled, user, samlMessageContext,
					attributes);
			}
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

	protected void addExpandoAttribute(
		String attributeName, boolean namespaceEnabled, User user,
		SAMLMessageContext<?, ?, ?> samlMessageContext,
		List<Attribute> attributes) {

		Attribute attribute = null;

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String value = String.valueOf(
			expandoBridge.getAttribute(attributeName));

		if (!namespaceEnabled) {
			attribute = OpenSamlUtil.buildAttribute(attributeName, value);
		}
		else {
			String name = "urn:liferay:user:expando:".concat(attributeName);

			attribute = OpenSamlUtil.buildAttribute(
				name, Attribute.URI_REFERENCE, value);
		}


		attributes.add(attribute);
	}

	protected void addGroupsAttribute(
		String attributeName, boolean namespaceEnabled, User user,
		SAMLMessageContext<?, ?, ?> samlMessageContext,
		List<Attribute> attributes) {

		try {
			List<Group> groups = user.getGroups();

			if (groups.isEmpty()) {
				return;
			}

			Attribute attribute = OpenSamlUtil.buildAttribute();

			if (namespaceEnabled) {
				attribute.setName("urn:liferay:groups");
				attribute.setNameFormat(Attribute.URI_REFERENCE);
			}
			else {
				attribute.setName("groups");
				attribute.setNameFormat(Attribute.UNSPECIFIED);
			}

			List<XMLObject> attributeValues = attribute.getAttributeValues();

			for (Group group : groups) {
				XMLObject value = OpenSamlUtil.buildAttributeValue(
					group.getName());

				attributeValues.add(value);
			}

			attributes.add(attribute);
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to get groups for userId " + user.getUserId(), pe);
		}
		catch (SystemException se) {
			_log.error(
				"Unable to get groups for userId " + user.getUserId(), se);
		}
	}

	protected void addOrganizationsAttribute(
		String attributeName, boolean namespaceEnabled, User user,
		SAMLMessageContext<?, ?, ?> samlMessageContext,
		List<Attribute> attributes) {

		try {
			List<Organization> organizations = user.getOrganizations();

			if (organizations.isEmpty()) {
				return;
			}

			Attribute attribute = OpenSamlUtil.buildAttribute();

			if (namespaceEnabled) {
				attribute.setName("urn:liferay:organizations");
				attribute.setNameFormat(Attribute.URI_REFERENCE);
			}
			else {
				attribute.setName("organizations");
				attribute.setNameFormat(Attribute.UNSPECIFIED);
			}

			List<XMLObject> attributeValues = attribute.getAttributeValues();

			for (Organization organization : organizations) {
				XMLObject value = OpenSamlUtil.buildAttributeValue(
					organization.getName());

				attributeValues.add(value);
			}

			attributes.add(attribute);
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to get groups for userId " + user.getUserId(), pe);
		}
		catch (SystemException se) {
			_log.error(
				"Unable to get groups for userId " + user.getUserId(), se);
		}
	}

	protected void addRolesAttribute(
		String attributeName, boolean namespaceEnabled, User user,
		SAMLMessageContext<?, ?, ?> samlMessageContext,
		List<Attribute> attributes) {

		try {
			List<Role> roles = user.getRoles();

			if (roles.isEmpty()) {
				return;
			}

			Attribute attribute = OpenSamlUtil.buildAttribute();

			if (namespaceEnabled) {
				attribute.setName("urn:liferay:roles");
				attribute.setNameFormat(Attribute.URI_REFERENCE);
			}
			else {
				attribute.setName("roles");
				attribute.setNameFormat(Attribute.UNSPECIFIED);
			}

			List<XMLObject> attributeValues = attribute.getAttributeValues();

			for (Role role : roles) {
				XMLObject value = OpenSamlUtil.buildAttributeValue(
					role.getName());

				attributeValues.add(value);
			}

			attributes.add(attribute);
		}
		catch (SystemException se) {
			_log.error(
				"Unable to get roles for userId " + user.getUserId(), se);
		}
	}

	protected void addUserAttribute(
		String attributeName, boolean namespaceEnabled, User user,
		SAMLMessageContext<?, ?, ?> samlMessageContext,
		List<Attribute> attributes) {

		Attribute attribute = null;

		String value = String.valueOf(
			BeanPropertiesUtil.getObject(user, attributeName));

		if (!namespaceEnabled) {
			attribute = OpenSamlUtil.buildAttribute(attributeName, value);
		}
		else {
			String name = "urn:liferay:user:".concat(attributeName);

			attribute = OpenSamlUtil.buildAttribute(
				name, Attribute.URI_REFERENCE, value);
		}

		attributes.add(attribute);
	}

	protected void addUserGroupsAttribute(
		String attributeName, boolean namespaceEnabled, User user,
		SAMLMessageContext<?, ?, ?> samlMessageContext,
		List<Attribute> attributes) {

		try {
			List<UserGroup> userGroups = user.getUserGroups();

			if (userGroups.isEmpty()) {
				return;
			}

			Attribute attribute = OpenSamlUtil.buildAttribute();

			if (namespaceEnabled) {
				attribute.setName("urn:liferay:userGroup");
				attribute.setNameFormat(Attribute.URI_REFERENCE);
			}
			else {
				attribute.setName("userGroup");
				attribute.setNameFormat(Attribute.UNSPECIFIED);
			}

			List<XMLObject> attributeValues = attribute.getAttributeValues();

			for (UserGroup userGroup : userGroups) {
				XMLObject value = OpenSamlUtil.buildAttributeValue(
					userGroup.getName());

				attributeValues.add(value);
			}

			attributes.add(attribute);
		}
		catch (SystemException se) {
			_log.error(
				"Unable to get user roles for userId " + user.getUserId(), se);
		}
	}

	protected void addUserGroupRolesAttribute(
		String attributeName, boolean namespaceEnabled, User user,
		SAMLMessageContext<?, ?, ?> samlMessageContext,
		List<Attribute> attributes) {

		try {
			List<UserGroupRole> userGroupRoles =
				UserGroupRoleLocalServiceUtil.getUserGroupRoles(
					user.getUserId());

			if (userGroupRoles.isEmpty()) {
				return;
			}

			Map<String, List<Role>> groupRoles =
				new HashMap<String, List<Role>>();

			for (UserGroupRole userGroupRole : userGroupRoles) {
				Group group = userGroupRole.getGroup();

				List<Role> roles = groupRoles.get(group.getName());

				if (roles == null) {
					roles = new ArrayList<Role>();

					groupRoles.put(group.getName(), roles);
				}

				roles.add(userGroupRole.getRole());
			}

			for (Entry<String, List<Role>> entry : groupRoles.entrySet()) {
				String groupName = entry.getKey();
				List<Role> roles = entry.getValue();

				Attribute attribute = OpenSamlUtil.buildAttribute();

				if (namespaceEnabled) {
					String name = "urn:liferay:userGroupRole:".concat(
						groupName);

					attribute.setName(name);
					attribute.setNameFormat(Attribute.URI_REFERENCE);
				}
				else {
					String name = "userGroupRole:".concat(groupName);

					attribute.setName(name);
					attribute.setNameFormat(Attribute.UNSPECIFIED);
				}

				List<XMLObject> attributeValues =
					attribute.getAttributeValues();

				for (Role role : roles) {
					XMLObject value = OpenSamlUtil.buildAttributeValue(
						role.getName());

					attributeValues.add(value);
				}

				attributes.add(attribute);
			}
		}
		catch (PortalException pe) {
			_log.error(
				"Unable to get user group roles for userId " + user.getUserId(),
				pe);
		}
		catch (SystemException se) {
			_log.error(
				"Unable to get user group roles for userId " + user.getUserId(),
				se);
		}
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

	private static Log _log = LogFactoryUtil.getLog(
		DefaultAttributeResolver.class);

	private static final String _SALESFORCE_ENTITY_ID =
		"https://saml.salesforce.com";

}