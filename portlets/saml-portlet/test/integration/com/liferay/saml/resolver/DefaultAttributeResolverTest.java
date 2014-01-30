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

package com.liferay.saml.resolver;

import com.liferay.portal.kernel.bean.BeanProperties;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.UserGroupRoleLocalService;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.saml.BaseSamlTestCase;
import com.liferay.saml.metadata.MetadataManager;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.util.SamlUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;

import org.opensaml.common.binding.BasicSAMLMessageContext;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Response;
import org.opensaml.xml.XMLObject;

/**
 * @author Mika Koivisto
 */
public class DefaultAttributeResolverTest extends BaseSamlTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		BeanPropertiesUtil beanPropertiesUtil = new BeanPropertiesUtil();

		_beanProperties = mock(BeanProperties.class);

		beanPropertiesUtil.setBeanProperties(_beanProperties);

		MetadataManagerUtil metadataManagerUtil = new MetadataManagerUtil();

		_metadataManager = mock(MetadataManager.class);

		metadataManagerUtil.setMetadataManager(_metadataManager);

		when(
			_metadataManager.isAttributesEnabled(Mockito.eq(SP_ENTITY_ID))
		).thenReturn(
			true
		);

		_user = mock(User.class);

		_expandoBridge = mock(ExpandoBridge.class);

		when(
			_user.getExpandoBridge()
		).thenReturn(
			_expandoBridge
		);

		_defaultAttributeResolver = new DefaultAttributeResolver();

		_samlMessageContext =
			new BasicSAMLMessageContext<AuthnRequest, Response, NameID>();

		_samlMessageContext.setPeerEntityId(SP_ENTITY_ID);
	}

	@Test
	public void testResolveExpandoAttributes() throws Exception {
		when(
			_metadataManager.getAttributeNames(Mockito.eq(SP_ENTITY_ID))
		).thenReturn(
			new String[] { "expando:customerId" }
		);

		when(
			_expandoBridge.getAttribute(Mockito.eq("customerId"))
		).thenReturn(
			"12345"
		);

		List<Attribute> attributes = _defaultAttributeResolver.resolve(
			_user, _samlMessageContext);

		assertAttributeEquals(attributes, "customerId", "12345");
	}

	@Test
	public void testResolveGroupsAttributes() throws Exception {
		when(
			_metadataManager.getAttributeNames(Mockito.eq(SP_ENTITY_ID))
		).thenReturn(
			new String[] { "groups" }
		);

		List<Group> groups = new ArrayList<Group>();

		Group group1 = mock(Group.class);

		groups.add(group1);

		Group group2 = mock(Group.class);

		groups.add(group2);

		when(
			group1.getName()
		).thenReturn(
			"Test 1"
		);
		when(
			group2.getName()
		).thenReturn(
			"Test 2"
		);

		when(
			_user.getGroups()
		).thenReturn(
			groups
		);

		List<Attribute> attributes = _defaultAttributeResolver.resolve(
			_user, _samlMessageContext);

		assertAttributeValuesEquals(
			attributes, "groups", new String[] {"Test 1", "Test 2"});
	}

	@Test
	public void testResolveOrganizationsAttributes() throws Exception {
		when(
			_metadataManager.getAttributeNames(Mockito.eq(SP_ENTITY_ID))
		).thenReturn(
			new String[] { "organizations" }
		);

		List<Organization> organizations = new ArrayList<Organization>();

		Organization organization1 = mock(Organization.class);

		organizations.add(organization1);

		Organization organization2 = mock(Organization.class);

		organizations.add(organization2);

		when(
			organization1.getName()
		).thenReturn(
			"Test 1"
		);
		when(
			organization2.getName()
		).thenReturn(
			"Test 2"
		);

		when(
			_user.getOrganizations()
		).thenReturn(
			organizations
		);

		List<Attribute> attributes = _defaultAttributeResolver.resolve(
			_user, _samlMessageContext);

		assertAttributeValuesEquals(
			attributes, "organizations", new String[] {"Test 1", "Test 2"});
	}

	@Test
	public void testResolveRolesAttributes() throws Exception {
		when(
			_metadataManager.getAttributeNames(Mockito.eq(SP_ENTITY_ID))
		).thenReturn(
			new String[] { "roles" }
		);

		List<Role> roles = new ArrayList<Role>();

		Role role1 = mock(Role.class);

		roles.add(role1);

		Role role2 = mock(Role.class);

		roles.add(role2);

		when(
			role1.getName()
		).thenReturn(
			"Test 1"
		);
		when(
			role2.getName()
		).thenReturn(
			"Test 2"
		);

		when(
			_user.getRoles()
		).thenReturn(
			roles
		);

		List<Attribute> attributes = _defaultAttributeResolver.resolve(
			_user, _samlMessageContext);

		assertAttributeValuesEquals(
			attributes, "roles", new String[] {"Test 1", "Test 2"});
	}

	@Test
	public void testResolveStaticAttributes() throws Exception {
		when(
			_metadataManager.getAttributeNames(Mockito.eq(SP_ENTITY_ID))
		).thenReturn(
			new String[] {
				"static:emailAddress=test@liferay.com",
				"static:screenName=test=test2"}
		);

		List<Attribute> attributes = _defaultAttributeResolver.resolve(
			_user, _samlMessageContext);

		assertAttributeEquals(attributes, "emailAddress", "test@liferay.com");
		assertAttributeEquals(attributes, "screenName", "test=test2");
	}

	@Test
	public void testResolveUserAttributes() throws Exception {
		when(
			_beanProperties.getObject(
				Mockito.any(User.class), Mockito.eq("emailAddress"))
		).thenReturn(
			"test@liferay.com"
		);
		when(
			_beanProperties.getObject(
				Mockito.any(User.class), Mockito.eq("firstName"))
		).thenReturn(
			"Test"
		);
		when(
			_beanProperties.getObject(
				Mockito.any(User.class), Mockito.eq("lastName"))
		).thenReturn(
			"Test"
		);
		when(
			_beanProperties.getObject(
				Mockito.any(User.class), Mockito.eq("screenName"))
		).thenReturn(
			"test"
		);
		when(
			_beanProperties.getObject(
				Mockito.any(User.class), Mockito.eq("uuid"))
		).thenReturn(
			"xxxx-xxxx-xxx-xxxx"
		);

		when(
			_metadataManager.getAttributeNames(Mockito.eq(SP_ENTITY_ID))
		).thenReturn(
			new String[] {
				"emailAddress", "firstName", "lastName", "screenName", "uuid"}
		);

		List<Attribute> attributes = _defaultAttributeResolver.resolve(
			_user, _samlMessageContext);

		assertAttributeEquals(attributes, "emailAddress", "test@liferay.com");
		assertAttributeEquals(attributes, "firstName", "Test");
		assertAttributeEquals(attributes, "lastName", "Test");
		assertAttributeEquals(attributes, "screenName", "test");
		assertAttributeEquals(attributes, "uuid", "xxxx-xxxx-xxx-xxxx");
	}

	@Test
	public void testResolveUserGroupRolesAttributes() throws Exception {
		when(
			_metadataManager.getAttributeNames(Mockito.eq(SP_ENTITY_ID))
		).thenReturn(
			new String[] { "userGroupRoles" }
		);

		UserGroupRoleLocalService userGroupRoleLocalService =
			getMockPortalService(
				UserGroupRoleLocalServiceUtil.class,
				UserGroupRoleLocalService.class);

		Group group1 = mock(Group.class);

		when(
			group1.getName()
		).thenReturn(
			"Group Test 1"
		)
;
		Role role1 = mock(Role.class);

		Role role2 = mock(Role.class);

		when(
			role1.getName()
		).thenReturn(
			"Role Test 1"
		);
		when(
			role2.getName()
		).thenReturn(
			"Role Test 2"
		);

		List<UserGroupRole> userGroupRoles = new ArrayList<UserGroupRole>();

		UserGroupRole userGroupRole1 = mock(UserGroupRole.class);

		userGroupRoles.add(userGroupRole1);

		UserGroupRole userGroupRole2 = mock(UserGroupRole.class);

		userGroupRoles.add(userGroupRole2);

		when(
			userGroupRole1.getGroup()
		).thenReturn(
			group1
		);
		when(
			userGroupRole1.getRole()
		).thenReturn(
			role1
		);
		when(
			userGroupRole2.getGroup()
		).thenReturn(
			group1
		);
		when(
			userGroupRole2.getRole()
		).thenReturn(
			role2
		);

		when(
			userGroupRoleLocalService.getUserGroupRoles(Mockito.anyLong())
		).thenReturn(
			userGroupRoles
		);

		List<Attribute> attributes = _defaultAttributeResolver.resolve(
			_user, _samlMessageContext);

		assertAttributeValuesEquals(
			attributes, "userGroupRole:Group Test 1",
			new String[] {"Role Test 1", "Role Test 2"});
	}

	@Test
	public void testResolveUserGroupsAttributes() throws Exception {
		when(
			_metadataManager.getAttributeNames(Mockito.eq(SP_ENTITY_ID))
		).thenReturn(
			new String[] { "userGroups" }
		);

		List<UserGroup> userGroups = new ArrayList<UserGroup>();

		UserGroup userGroup1 = mock(UserGroup.class);

		userGroups.add(userGroup1);

		UserGroup userGroup2 = mock(UserGroup.class);

		userGroups.add(userGroup2);

		when(
			userGroup1.getName()
		).thenReturn(
			"Test 1"
		);
		when(
			userGroup2.getName()
		).thenReturn(
			"Test 2"
		);

		when(
			_user.getUserGroups()
		).thenReturn(
			userGroups
		);

		List<Attribute> attributes = _defaultAttributeResolver.resolve(
			_user, _samlMessageContext);

		assertAttributeValuesEquals(
			attributes, "userGroups", new String[] {"Test 1", "Test 2"});
	}

	protected void assertAttributeEquals(
		List<Attribute> attributes, String attributeName,
		String attributeValue) {

		Attribute attribute = SamlUtil.getAttribute(attributes, attributeName);

		Assert.assertEquals(
			attributeValue, SamlUtil.getValueAsString(attribute));
	}

	protected void assertAttributeValuesEquals(
		List<Attribute> attributes, String attributeName,
		String[] attributeValues) {

		Attribute attribute = SamlUtil.getAttribute(attributes, attributeName);

		Assert.assertNotNull(attribute);

		List<XMLObject> values = attribute.getAttributeValues();

		Assert.assertEquals(attributeValues.length, values.size());

		for (XMLObject xmlObject : values) {
			String value = SamlUtil.getValueAsString(xmlObject);

			Assert.assertTrue(ArrayUtil.contains(attributeValues, value));
		}
	}

	private BeanProperties _beanProperties;
	private DefaultAttributeResolver _defaultAttributeResolver;
	private ExpandoBridge _expandoBridge;
	private MetadataManager _metadataManager;
	private SAMLMessageContext<AuthnRequest, Response, NameID>
		_samlMessageContext;
	private User _user;

}