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
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserScreenNameComparator;
import com.liferay.vldap.BaseVLDAPTestCase;
import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.util.PortletPropsKeys;

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
public class CommunityBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_communityBuilder = new CommunityBuilder();

		Long testLong = new Long("42");

		_groupLocalService = mockService(
			GroupLocalServiceUtil.class, GroupLocalService.class);

		_userLocalService = mockService(
			UserLocalServiceUtil.class, UserLocalService.class);

		Group group = mock(Group.class);

		when(group.getName()).thenReturn("testName");
		when(group.getGroupId()).thenReturn(testLong);
		when(group.getDescription()).thenReturn("testDescription");

		_groups = new ArrayList<Group>();
		_groups.add(group);

		List<User> users = new ArrayList<User>();
		_user = mock(User.class);
		users.add(_user);

		when(props.get(PortletPropsKeys.SEARCH_MAX_SIZE)).thenReturn("42");

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

		when(_user.getScreenName()).thenReturn("testScreenName");

	}

	@Test
	public void testBuildDirectoriesFilterNullScreenName() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);

		filterConstraint.addAttribute("ou", "testName");
		filterConstraint.addAttribute("description", "testDescription");

		when(
			_groupLocalService.search(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(),
				Mockito.any(LinkedHashMap.class), Mockito.anyBoolean(),
				Mockito.anyInt(), Mockito.anyInt())
		).thenReturn(
			_groups
		);

		List<Directory> directory = _communityBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "liferayCommunity"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testScreenName,ou=Users,ou=42,o=42"));
	}

	@Test
	public void testBuildDirectoriesFilterValidScreenName() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);

		filterConstraint.addAttribute("ou", "testName");
		filterConstraint.addAttribute("description", "testDescription");
		filterConstraint.addAttribute(
			"member", "screenName=testScreenName,ou=test,cn=test,blah=test");

		when(
			_userLocalService.getUserByScreenName(
				Mockito.anyLong(), Mockito.anyString())
		).thenReturn(_user);

		when(_user.getGroups()).thenReturn(_groups);

		List<Directory> directory = _communityBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "liferayCommunity"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testScreenName,ou=Users,ou=42,o=42"));
	}

	@Test
	public void testBuildDirectoriesNullFilter() throws Exception {
		when(
			_groupLocalService.getCompanyGroups(
				Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt())
		).thenReturn(
			_groups
		);

		List<Directory> directory = _communityBuilder.buildDirectories(
			_searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "liferayCommunity"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testScreenName,ou=Users,ou=42,o=42"));

	}

	private CommunityBuilder _communityBuilder;
	private GroupLocalService _groupLocalService;
	private User _user;
	private List<Group> _groups;
	private UserLocalService _userLocalService;

}
