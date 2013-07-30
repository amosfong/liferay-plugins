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
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.RoleLocalServiceUtil;
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
public class RoleBuilderTest extends BaseVLDAPTestCase {

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
		dynamicQueryFactoryUtil.setDynamicQueryFactory(dynamicQueryFactory);

		RestrictionsFactoryUtil restrictionsFactoryUtil =
			new RestrictionsFactoryUtil();
		restrictionsFactoryUtil.setRestrictionsFactory(restrictionsFactory);
		when(
			restrictionsFactory.eq(
				Mockito.anyString(), Mockito.any(Object.class))
		).thenReturn(criterion);
		when(
			restrictionsFactory.ilike(
				Mockito.anyString(), Mockito.any(Object.class))
		).thenReturn(criterion);

		_userLocalService = getMockService(
			UserLocalServiceUtil.class, UserLocalService.class);

		Role role = mock(Role.class);
		when(role.getName()).thenReturn("testName");
		when(role.getRoleId()).thenReturn(new Long("42"));
		when(role.getDescription()).thenReturn("testDescription");

		_roleBuilder = new RoleBuilder();
		_roles = new ArrayList<Role>();
		_roles.add(role);

		_roleLocalService = getMockService(
			RoleLocalServiceUtil.class, RoleLocalService.class);
		when(
			_roleLocalService.dynamicQuery(Mockito.any(DynamicQuery.class))
		).thenReturn(_roles);

		List<User> users = new ArrayList<User>();
		_user = mock(User.class);
		users.add(_user);
		when(_user.getScreenName()).thenReturn("testScreenName");

		when(props.get(PortletPropsKeys.SEARCH_MAX_SIZE)).thenReturn("42");
	}

	@Test
	public void testBuildDirectoriesFilterNullFilter() throws Exception {
		List<Directory> directory = _roleBuilder.buildDirectories(
			_searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
	}

	@Test
	public void testBuildDirectoriesFilterNullScreenName() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute("ou", "testName");
		filterConstraint.addAttribute("description", "testDescription");

		List<Directory> directory = _roleBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
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

		when(_user.getRoles()).thenReturn(_roles);

		List<Directory> directory = _roleBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("description", "testDescription"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
	}

	private DynamicQuery _dynamicQuery;
	private DynamicQueryFactory _dynamicQueryFactory;
	private RoleBuilder _roleBuilder;
	private RoleLocalService _roleLocalService;
	private List<Role> _roles;
	private User _user;
	private UserLocalService _userLocalService;

}