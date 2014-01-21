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

import com.liferay.portal.model.Group;
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
 */
@RunWith(PowerMockRunner.class)
public class CommunityBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setupGroups();
		setupUsers();
	}

	@Test
	public void testBuildDirectoriesNoFilter() throws Exception {
		when(
			groupLocalService.getCompanyGroups(
				Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt())
		).thenReturn(
			_groups
		);

		List<Directory> directories = _communityBuilder.buildDirectories(
			searchBase, null);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member",
				"cn=testScreenName,ou=Users,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "liferayCommunity"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	@Test
	public void testBuildDirectoriesNoScreenName() throws Exception {
		when(
			groupLocalService.search(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(),
				Mockito.any(LinkedHashMap.class), Mockito.anyBoolean(),
				Mockito.anyInt(), Mockito.anyInt())
		).thenReturn(
			_groups
		);

		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("description", "testDescription");
		filterConstraint.addAttribute("ou", "testName");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _communityBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member",
				"cn=testScreenName,ou=Users,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "liferayCommunity"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	@Test
	public void testBuildDirectoriesValidScreenName() throws Exception {
		when(
			_user.getGroups()
		).thenReturn(
			_groups
		);

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

		List<Directory> directories = _communityBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			directory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member",
				"cn=testScreenName,ou=Users,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "liferayCommunity"));
		Assert.assertTrue(directory.hasAttribute("ou", "testName"));
	}

	protected void setupGroups() throws Exception {
		Group group = mock(Group.class);

		when(
			group.getDescription()
		).thenReturn(
			"testDescription"
		);

		when(
			group.getGroupId()
		).thenReturn(
			42l
		);

		when(
			group.getName()
		).thenReturn(
			"testName"
		);

		_groups.add(group);
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

	private CommunityBuilder _communityBuilder = new CommunityBuilder();
	private List<Group> _groups = new ArrayList<Group>();
	private User _user;

}