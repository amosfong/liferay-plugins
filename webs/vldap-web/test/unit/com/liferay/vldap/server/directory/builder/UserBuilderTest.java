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

import com.liferay.portal.kernel.util.FastDateFormatFactory;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.comparator.UserScreenNameComparator;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.vldap.BaseVLDAPTestCase;
import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.time.FastDateFormat;

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
public class UserBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_userBuilder = new UserBuilder();

		_groupLocalService = getMockService(
			GroupLocalServiceUtil.class, GroupLocalService.class);

		_userLocalService = getMockService(
			UserLocalServiceUtil.class, UserLocalService.class);

		_users = new ArrayList<User>();
		when(
			_userLocalService.getCompanyUsers(
				Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt())
		).thenReturn(_users);

		_user = mock(User.class);
		_users.add(_user);

		Long testLong = new Long("42");
		when(_user.getScreenName()).thenReturn("testScreenName");
		when(_user.getCreateDate()).thenReturn(null);
		when(_user.getFullName()).thenReturn("testFullName");
		when(props.get(PortletPropsValues.POSIX_GROUP_ID)
			).thenReturn("testGroupId");
		when(_user.getFirstName()).thenReturn("testFirstName");
		when(_user.getEmailAddress()).thenReturn("test@email");
		when(_user.getModifiedDate()).thenReturn(null);
		when(_user.getLastName()).thenReturn("testLastName");
		when(_user.getUserId()).thenReturn(testLong);
		when(_user.getUuid()).thenReturn("testUuid");
		when(_user.getCompanyId()).thenReturn(testLong);

		List<Group> groups = new ArrayList<Group>();
		List<Organization> organizations = new ArrayList<Organization>();
		List<Role> roles = new ArrayList<Role>();
		List<UserGroup> userGroups = new ArrayList<UserGroup>();
		Organization organization = mock(Organization.class);
		Role role = mock(Role.class);
		UserGroup userGroup = mock(UserGroup.class);
		Group group = mock(Group.class);
		organizations.add(organization);
		roles.add(role);
		userGroups.add(userGroup);
		groups.add(group);

		when(_user.getOrganizations()).thenReturn(organizations);
		when(_user.getRoles()).thenReturn(roles);
		when(_user.getUserGroups()).thenReturn(userGroups);

		when(organization.getName()).thenReturn("testOrganizationName");
		when(group.getName()).thenReturn("testGroupName");
		when(role.getName()).thenReturn("testRoleName");
		when(userGroup.getName()).thenReturn("testUserGroupName");
		when(organization.getOrganizationId()).thenReturn(testLong);
		when(group.getGroupId()).thenReturn(testLong);
		when(role.getRoleId()).thenReturn(testLong);
		when(userGroup.getUserGroupId()).thenReturn(testLong);

		when(
			_groupLocalService.search(
				Mockito.anyLong(), Mockito.any(long[].class),
				Mockito.anyString(), Mockito.anyString(),
				Mockito.any(LinkedHashMap.class), Mockito.anyBoolean(),
				Mockito.anyInt(), Mockito.anyInt())
		).thenReturn(groups);
		when(
			_groupLocalService.getGroup(Mockito.anyLong(), Mockito.anyString())
		).thenReturn(group);

		FastDateFormat fastFormat = FastDateFormat.getInstance(
			"yyyyMMddHHmmss.SZ",  (TimeZone)null, LocaleUtil.getDefault());

		FastDateFormatFactory fastDateFormatFactory = mock(
			FastDateFormatFactory.class);
		when(
			fastDateFormatFactory.getSimpleDateFormat(Mockito.anyString())
		).thenReturn(fastFormat);

		FastDateFormatFactoryUtil fastDateFormatFactoryUtil =
			new FastDateFormatFactoryUtil();
		fastDateFormatFactoryUtil.setFastDateFormatFactory(
			fastDateFormatFactory);

		PortalUtil portalUtil = new PortalUtil();
		Portal portal = mock(Portal.class);
		portalUtil.setPortal(portal);
		when(
			portal.getClassNameId(Mockito.any(Class.class))
		).thenReturn(testLong);

		PasswordPolicy passwordPolicy = mock(PasswordPolicy.class);
		when(_user.getPasswordPolicy()).thenReturn(passwordPolicy);
		when(passwordPolicy.isExpireable()).thenReturn(false);
		when(passwordPolicy.isLockout()).thenReturn(true);
		when(
			passwordPolicy.getLockoutDuration()
		).thenReturn(new Long("7200000"));
		when(
			passwordPolicy.getResetFailureCount()
		).thenReturn(new Long("3600000"));
		when(passwordPolicy.isRequireUnlock()).thenReturn(true);
		when(
			passwordPolicy.getGraceLimit()
		).thenReturn(7200000);
		when(
			passwordPolicy.getMaxAge()
		).thenReturn(new Long("14400000"));
		when(
			passwordPolicy.getMinAge()
		).thenReturn(new Long("3600000"));
		when(
			passwordPolicy.getHistoryCount()
		).thenReturn(3600000);

		ExpandoBridge expandBridge = mock(ExpandoBridge.class);
		when(_user.getExpandoBridge()).thenReturn(expandBridge);
		when(
			expandBridge.getAttribute(
				Mockito.eq("sambaLMPassword"), Mockito.eq(false))
		).thenReturn("testLMPassword");
		when(
			expandBridge.getAttribute(
				Mockito.eq("sambaNTPassword"), Mockito.eq(false))
		).thenReturn("testNTPassword");

		when(_searchBase.getCommunity()).thenReturn(group);
		when(_searchBase.getOrganization()).thenReturn(organization);
		when(_searchBase.getRole()).thenReturn(role);
		when(_searchBase.getUserGroup()).thenReturn(userGroup);

		CompanyLocalService companyLocalService = getMockService(
			CompanyLocalServiceUtil.class, CompanyLocalService.class);
		when(
			companyLocalService.getCompanyByWebId(Mockito.anyString())
		).thenReturn(_company);
	}

	@Test
	public void testBuildDirectories() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute(
			"member", "ou=testWebId,ou=Communities," +
			"ou=testTypeValue,cn=test");
		filterConstraint.addAttribute("gidNumber", StringPool.STAR);
		filterConstraint.addAttribute("uuid", null);
		filterConstraint.addAttribute("givenName", "testFirstName");
		filterConstraint.addAttribute("sn", "testLastName");
		filterConstraint.addAttribute("cn", "testScreenName");
		filterConstraint.addAttribute("mail", "test@email");
		filterConstraint.addAttribute("uidNumber", null);
		filterConstraint.addAttribute("sambaSID", null);

		when(
			_userLocalService.search(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyInt(), Mockito.any(LinkedHashMap.class),
				Mockito.anyBoolean(), Mockito.anyInt(), Mockito.anyInt(),
				Mockito.any(UserScreenNameComparator.class)
				)
		).thenReturn(
			_users
		);

		List<Directory> directory = _userBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("sn", "testLastName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
			"member", "cn=testOrganizationName,ou=testOrganizationName," +
			"ou=Organizations,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
				"ou=Roles,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
				"ou=User Groups,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
				"ou=Communities,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaLockoutDuration", "120"));
	}

	@Test
	public void testBuildDirectoriesNullFilter() throws Exception {
		List<Directory> directory = _userBuilder.buildDirectories(
			_searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("sn", "testLastName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testOrganizationName,ou=testOrganizationName," +
				"ou=Organizations,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
				"ou=Roles,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
				"ou=User Groups,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
				"ou=Communities,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaLockoutDuration", "120"));
	}

	@Test
	public void testBuildDirectoriesSambaSID() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute(
			"member", "ou=testWebId,ou=Communities," +
			"ou=testTypeValue,cn=test");
		filterConstraint.addAttribute("gidNumber", StringPool.STAR);
		filterConstraint.addAttribute("uuid", null);
		filterConstraint.addAttribute("givenName", "testFirstName");
		filterConstraint.addAttribute("sn", "testLastName");
		filterConstraint.addAttribute("cn", "testScreenName");
		filterConstraint.addAttribute("mail", "test@email");
		filterConstraint.addAttribute("uidNumber", null);
		filterConstraint.addAttribute("sambaSID", "42-42-42");

		when(_userLocalService.fetchUser(Mockito.anyLong())).thenReturn(_user);

		List<Directory> directory = _userBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("sn", "testLastName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testOrganizationName,ou=testOrganizationName," +
				"ou=Organizations,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
				"ou=Roles,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
				"ou=User Groups,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
				"ou=Communities,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaLockoutDuration", "120"));

	}

	@Test
	public void testBuildDirectoriesUidNumber() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute(
			"member", "ou=testWebId,ou=Communities," +
			"ou=testTypeValue,cn=test");
		filterConstraint.addAttribute("gidNumber", StringPool.STAR);
		filterConstraint.addAttribute("uuid", null);
		filterConstraint.addAttribute("givenName", "testFirstName");
		filterConstraint.addAttribute("sn", "testLastName");
		filterConstraint.addAttribute("cn", "testScreenName");
		filterConstraint.addAttribute("mail", "test@email");
		filterConstraint.addAttribute("uidNumber", "42");
		filterConstraint.addAttribute("sambaSID", null);

		when(_userLocalService.fetchUser(Mockito.anyLong())).thenReturn(_user);

		List<Directory> directory = _userBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("sn", "testLastName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testOrganizationName,ou=testOrganizationName," +
				"ou=Organizations,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
				"ou=Roles,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
				"ou=User Groups,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
				"ou=Communities,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaLockoutDuration", "120"));
	}

	@Test
	public void testBuildDirectoriesUuidNotNull() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute(
			"member", "ou=testWebId,ou=Communities," +
			"ou=testTypeValue,cn=test");
		filterConstraint.addAttribute("gidNumber", StringPool.STAR);
		filterConstraint.addAttribute("uuid", "testUuid");

		when(_userLocalService.getUserByUuid(Mockito.anyString())
			).thenReturn(_user);

		List<Directory> directory = _userBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(returnedDirectory.hasAttribute("sn", "testLastName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testOrganizationName,ou=testOrganizationName," +
				"ou=Organizations,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
				"ou=Roles,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
				"ou=User Groups,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
				"ou=Communities,ou=42,o=42"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaLockoutDuration", "120"));
	}

	private GroupLocalService _groupLocalService;
	private User _user;
	private UserBuilder _userBuilder;
	private UserLocalService _userLocalService;
	private List<User> _users;

}