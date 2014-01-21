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
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.vldap.BaseVLDAPTestCase;
import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.ldap.Directory;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author William Newbury
 */
@RunWith(PowerMockRunner.class)
public class RoleBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setupUsers();

		setupRoles();
	}

	@Test
	public void testBuildDirectoriesFilterNoFilter() throws Exception {
		List<Directory> directories = _roleBuilder.buildDirectories(
			searchBase, null);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	@Test
	public void testBuildDirectoriesFilterNoScreenName() throws Exception {
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("description", "testDescription");
		filterConstraint.addAttribute("ou", "testName");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _roleBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	@Test
	public void testBuildDirectoriesFilterValidScreenName() throws Exception {
		when(
			userLocalService.getUserByScreenName(
				Mockito.anyLong(), Mockito.anyString())
		).thenReturn(
			_user
		);

		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("description", "testDescription");
		filterConstraint.addAttribute(
			"member", "screenName=testScreenName,ou=test,cn=test,blah=test");
		filterConstraint.addAttribute("ou", "testName");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _roleBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	protected void setupRoles() throws Exception {
		Role role = mock(Role.class);

		when(
			role.getDescription()
		).thenReturn(
			"testDescription"
		);

		when(
			role.getName()
		).thenReturn(
			"testName"
		);

		when(
			role.getRoleId()
		).thenReturn(
			42l
		);

		List<Role> roles = new ArrayList<Role>();

		roles.add(role);

		when(
			roleLocalService.dynamicQuery(Mockito.any(DynamicQuery.class))
		).thenReturn(
			roles
		);

		when(
			_user.getRoles()
		).thenReturn(
			roles
		);
	}

	protected void setupUsers() throws Exception {
		_user = mock(
			User.class
		);

		when(
			_user.getScreenName()
		).thenReturn(
			"testScreenName"
		);
	}

	private RoleBuilder _roleBuilder = new RoleBuilder();
	private User _user;

}