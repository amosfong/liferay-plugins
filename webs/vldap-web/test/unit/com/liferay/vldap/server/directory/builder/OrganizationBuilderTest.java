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
import com.liferay.portal.util.comparator.UserScreenNameComparator;
import com.liferay.vldap.BaseVLDAPTestCase;
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
	}

	@Test
	public void testBuildDirectoriesNoFilter() throws Exception {
		List<Directory> directories = _organizationBuilder.buildDirectories(
			searchBase, null);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member",
				"cn=testScreenName,ou=Users,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "liferayOrganization"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	@Test
	public void testBuildDirectoriesNoScreenName() throws Exception {
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("ou", "testName");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _organizationBuilder.buildDirectories(
			searchBase, null);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member",
				"cn=testScreenName,ou=Users,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "liferayOrganization"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	@Test
	public void testBuildDirectoriesValidScreenName() throws Exception {
		when(
			userLocalService.getUserByScreenName(
				Mockito.anyLong(), Mockito.anyString())
		).thenReturn(
			_user
		);

		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("ou", "testName");
		filterConstraint.addAttribute(
			"member", "screenName=testScreenName,ou=test,cn=test,blah=test");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _organizationBuilder.buildDirectories(
			searchBase, null);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member",
				"cn=testScreenName,ou=Users,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "liferayOrganization"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	protected void setupOrganizations() throws Exception {
		Organization organization = mock(Organization.class);

		when(
			organization.getName()
		).thenReturn(
			"testName"
		);

		when(
			organization.getOrganizationId()
		).thenReturn(
			42l
		);

		List<Organization> organizations = new ArrayList<Organization>();

		organizations.add(organization);

		when(
			organizationLocalService.dynamicQuery(
				Mockito.any(DynamicQuery.class))
		).thenReturn(
			organizations
		);

		when(
			_user.getOrganizations()
		).thenReturn(
			organizations
		);
	}

	protected void setupUsers() throws Exception {
		_user = mock(User.class);

		when(
			_user.getScreenName()
		).thenReturn(
			"testScreenName"
		);

		List<User> users = new ArrayList<User>();

		users.add(_user);

		when(
			userLocalService.search(
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

	private OrganizationBuilder _organizationBuilder =
		new OrganizationBuilder();
	private User _user;

}