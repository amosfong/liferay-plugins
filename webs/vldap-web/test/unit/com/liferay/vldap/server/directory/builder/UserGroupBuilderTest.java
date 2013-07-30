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

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactory;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactory;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.UserGroupLocalService;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.vldap.BaseVLDAPTestCase;
import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.util.PortletPropsKeys;

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
 * @author Matthew Tambara
 */
@RunWith(PowerMockRunner.class)
public class UserGroupBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		Criterion criterion = mock(Criterion.class);

		DynamicQueryFactory dynamicQueryFactory = mock(
			DynamicQueryFactory.class);
		DynamicQuery dynamicQuery = mock(DynamicQuery.class);
		when(
			dynamicQueryFactory.forClass(
				Mockito.any(Class.class), Mockito.any(ClassLoader.class))
		).thenReturn(dynamicQuery);

		RestrictionsFactory restrictionsFactory = mock(
			RestrictionsFactory.class);

		DynamicQueryFactoryUtil dynamicQueryFactoryUtil =
			new DynamicQueryFactoryUtil();
		dynamicQueryFactoryUtil.setDynamicQueryFactory(
			dynamicQueryFactory);

		RestrictionsFactoryUtil restrictionsFactoryUtil =
			new RestrictionsFactoryUtil();
		restrictionsFactoryUtil.setRestrictionsFactory(
			restrictionsFactory);
		when(
			restrictionsFactory.eq(
				Mockito.anyString(), Mockito.any(Object.class))
		).thenReturn(criterion);
		when(
			restrictionsFactory.ilike(
				Mockito.anyString(), Mockito.any(Object.class))
		).thenReturn(criterion);

		_userGroupBuilder = new UserGroupBuilder();

		UserGroup userGroup = mock(UserGroup.class);
		when(userGroup.getName()).thenReturn("testName");
		when(userGroup.getUserGroupId()).thenReturn(new Long("42"));
		when(userGroup.getDescription()).thenReturn("testDescription");

		_userGroups = new ArrayList<UserGroup>();
		_userGroups.add(userGroup);

		List<User> users = new ArrayList<User>();
		_user = mock(User.class);
		users.add(_user);
		when(_user.getScreenName()).thenReturn("testScreenName");

		_userGroupLocalService = getMockService(
			UserGroupLocalServiceUtil.class, UserGroupLocalService.class);
		when(
			_userGroupLocalService.dynamicQuery(Mockito.any(DynamicQuery.class))
		).thenReturn(_userGroups);

		_userLocalService = getMockService(
			UserLocalServiceUtil.class, UserLocalService.class);

		when(props.get(PortletPropsKeys.SEARCH_MAX_SIZE)).thenReturn("42");
	}

	@Test
	public void testBuildDirectoriesFilterNullFilter() throws Exception {
		List<Directory> directory = _userGroupBuilder.buildDirectories(
			_searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "groupOfNames"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "liferayUserGroup"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "organizationalUnit"));
	}

	@Test
	public void testBuildDirectoriesFilterNullScreenName() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute("ou", "testName");
		filterConstraint.addAttribute("description", "testDescription");

		List<Directory> directory = _userGroupBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "groupOfNames"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "liferayUserGroup"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "organizationalUnit"));
	}

	@Test
	public void testBuildDirectoriestFilterValidScreenName() throws Exception {
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

		when(_user.getUserGroups()).thenReturn(_userGroups);

		List<Directory> directory = _userGroupBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "groupOfNames"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "liferayUserGroup"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "organizationalUnit"));
	}

	private DynamicQuery _dynamicQuery;
	private DynamicQueryFactory _dynamicQueryFactory;
	private UserGroupBuilder _userGroupBuilder;
	private User _user;
	private List<UserGroup> _userGroups;
	private UserGroupLocalService _userGroupLocalService;
	private UserLocalService _userLocalService;

}
