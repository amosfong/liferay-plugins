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
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
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
public class UserGroupBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setupUsers();

		setupUserGroups();
	}

	@Test
	public void testBuildDirectoriesNoFilter() throws Exception {
		List<Directory> directories = _userGroupBuilder.buildDirectories(
			searchBase, null);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "groupOfNames"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "liferayUserGroup"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "organizationalUnit"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	@Test
	public void testBuildDirectoriesNoScreenName() throws Exception {
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("description", "testDescription");
		filterConstraint.addAttribute("ou", "testName");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _userGroupBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "groupOfNames"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "liferayUserGroup"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "organizationalUnit"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	@Test
	public void testBuildDirectoriestValidScreenName() throws Exception {
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
			"member", "screenName=testScreenName,ou=test,cn=test,test=test");
		filterConstraint.addAttribute("ou", "testName");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _userGroupBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "groupOfNames"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "liferayUserGroup"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "organizationalUnit"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	protected void setupUserGroups() throws Exception {
		UserGroup userGroup = mock(UserGroup.class);

		when(
			userGroup.getDescription()
		).thenReturn(
			"testDescription"
		);

		when(
			userGroup.getName()
		).thenReturn(
			"testName"
		);

		when(
			userGroup.getUserGroupId()
		).thenReturn(
			42l
		);

		List<UserGroup> userGroups = new ArrayList<UserGroup>();

		userGroups.add(userGroup);

		when(
			userGroupLocalService.dynamicQuery(Mockito.any(DynamicQuery.class))
		).thenReturn(
			userGroups
		);

		when(
			_user.getUserGroups()
		).thenReturn(
			userGroups
		);
	}

	protected void setupUsers() throws Exception {
		_user = mock(User.class);

		when(
			_user.getScreenName()
		).thenReturn(
			"testScreenName"
		);
	}

	private User _user;
	private UserGroupBuilder _userGroupBuilder = new UserGroupBuilder();

}