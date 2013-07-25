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
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.OrganizationLocalService;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
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
public class OrganizationBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_organizationBuilder = new OrganizationBuilder();

		Long testLong = new Long("42");

		_organizationLocalService = mockService(
			OrganizationLocalServiceUtil.class, OrganizationLocalService.class);

		_organizations = new ArrayList<Organization>();
		Organization organization = mock(Organization.class);
		_organizations.add(organization);

		when(
			_organizationLocalService.dynamicQuery(
				Mockito.any(DynamicQuery.class))
		).thenReturn(_organizations);

		when(organization.getName()).thenReturn("testName");
		when(organization.getOrganizationId()).thenReturn(testLong);

		_userLocalService = mockService(
			UserLocalServiceUtil.class, UserLocalService.class);

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

		DynamicQueryFactory dynamicQueryFactory = mock(
			DynamicQueryFactory.class);

		DynamicQuery dynamicQuery = mock(DynamicQuery.class);

		when(
			dynamicQueryFactory.forClass(
				Mockito.any(Class.class), Mockito.any(ClassLoader.class))
		).thenReturn(dynamicQuery);

		(new DynamicQueryFactoryUtil()).setDynamicQueryFactory(
			dynamicQueryFactory);

		_restrictionsFactory = mock(RestrictionsFactory.class);

		(new RestrictionsFactoryUtil()).setRestrictionsFactory(
			_restrictionsFactory);

		_criterion = mock(Criterion.class);

		when(
			_restrictionsFactory.eq(
				Mockito.anyString(), Mockito.any(Object.class))
		).thenReturn(_criterion);
	}

	@Test
	public void testBuildDirectoriesNullFilter() throws Exception {
		List<Directory> directory = _organizationBuilder.buildDirectories(
			_searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "liferayOrganization"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testScreenName,ou=Users,ou=42,o=42"));
	}

	@Test
	public void testBuildDirectoriesNullScreenName() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute("ou", "testName");

		when(
			_organizationLocalService.dynamicQuery(
				Mockito.any(DynamicQuery.class))
		).thenReturn(_organizations);

		when(
			_restrictionsFactory.ilike(
				Mockito.anyString(), Mockito.any(Object.class))
		).thenReturn(_criterion);

		List<Directory> directory = _organizationBuilder.buildDirectories(
			_searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "liferayOrganization"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testScreenName,ou=Users,ou=42,o=42"));
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

		when(_user.getOrganizations()).thenReturn(_organizations);

		List<Directory> directory = _organizationBuilder.buildDirectories(
			_searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(returnedDirectory.hasAttribute("cn", "testName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "testName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "liferayOrganization"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testScreenName,ou=Users,ou=42,o=42"));
	}

	private OrganizationBuilder _organizationBuilder;
	private Criterion _criterion;
	private OrganizationLocalService _organizationLocalService;
	private List<Organization> _organizations;
	private RestrictionsFactory _restrictionsFactory;
	private User _user;
	private UserLocalService _userLocalService;

}
