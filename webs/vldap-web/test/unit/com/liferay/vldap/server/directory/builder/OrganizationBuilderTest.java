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

package com.liferay.vldap.server.directory.builder;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.OrganizationLocalService;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserScreenNameComparator;
import com.liferay.vldap.server.directory.BaseVLDAPTestCase;
import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.ldap.Directory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author William Newbury
 * @author Matthew Tambara
 */
@RunWith(PowerMockRunner.class)
public class OrganizationBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setupUsers();
		setupOrganizations();

		_organizationBuilder = new OrganizationBuilder();
	}

	@Test
	public void testBuildDirectoriesNullFilter() throws Exception {
		List<Directory> directory = _organizationBuilder.buildDirectories(
			searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "liferayOrganization"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member",
				"cn=testScreenName,ou=Users,ou=liferay.com,o=Liferay"));
	}

	@Test
	public void testBuildDirectoriesNullScreenName() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute("ou", "testName");

		List<Directory> directory = _organizationBuilder.buildDirectories(
			searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "liferayOrganization"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member",
				"cn=testScreenName,ou=Users,ou=liferay.com,o=Liferay"));
	}

	@Test
	public void testBuildDirectoriesValidScreenName() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute("ou", "testName");
		filterConstraint.addAttribute(
			"member", "screenName=testScreenName,ou=test,cn=test,blah=test");

		when(
			_userLocalService.getUserByScreenName(
				Mockito.anyLong(), Mockito.anyString())
		).thenReturn(_user);

		List<Directory> directory = _organizationBuilder.buildDirectories(
			searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "liferayOrganization"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member",
				"cn=testScreenName,ou=Users,ou=liferay.com,o=Liferay"));
	}

	protected void setupOrganizations() throws Exception {
		Organization organization = mock(Organization.class);

		when(organization.getName()).thenReturn("testName");
		when(organization.getOrganizationId()).thenReturn(42l);

		List<Organization> organizations = new ArrayList<Organization>();
		organizations.add(organization);

		when(_user.getOrganizations()).thenReturn(organizations);

		OrganizationLocalService organizationLocalService = getMockPortalService(
			OrganizationLocalServiceUtil.class, OrganizationLocalService.class);

		when(
			organizationLocalService.dynamicQuery(
				Mockito.any(DynamicQuery.class))
		).thenReturn(
			organizations
		);
	}

	protected void setupUsers() throws Exception {
		_user = mock(User.class);

		when(_user.getScreenName()).thenReturn("testScreenName");

		List<User> users = new ArrayList<User>();
		users.add(_user);

		_userLocalService = getMockPortalService(
			UserLocalServiceUtil.class, UserLocalService.class);

		when(
			_userLocalService.search(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyInt(), Mockito.any(LinkedHashMap.class),
				Mockito.anyBoolean(), Mockito.anyInt(), Mockito.anyInt(),
				Mockito.any(UserScreenNameComparator.class)
				)
		).thenReturn(
			users
		);
	}

	private OrganizationBuilder _organizationBuilder;
	private User _user;
	private UserLocalService _userLocalService;

}