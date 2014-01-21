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
 */
@RunWith(PowerMockRunner.class)
public class UserBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		setupUsers();

		setupExpando();
		setupFastDateFormat();
		setupGroups();
		setupOrganizations();
		setupPasswordPolicy();
		setupPortalUtil();
		setupRoles();
		setupUserGroups();
	}

	@Test
	public void testBuildDirectoriesNoFilter() throws Exception {
		List<Directory> directories = _userBuilder.buildDirectories(
			searchBase, null);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
					"ou=Communities,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testOrganizationName,ou=testOrganizationName," +
					"ou=Organizations,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
					"ou=Roles,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
					"ou=User Groups,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("sambaLockoutDuration", "120"));
		Assert.assertTrue(directory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(directory.hasAttribute("sn", "testLastName"));
	}

	@Test
	public void testBuildDirectoriesNoSambaSIDOrUidNumberOrUUID()
		throws Exception {

		when(
			userLocalService.search(
				Mockito.anyLong(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyInt(), Mockito.any(LinkedHashMap.class),
				Mockito.anyBoolean(), Mockito.anyInt(), Mockito.anyInt(),
				Mockito.any(UserScreenNameComparator.class))
		).thenReturn(
			_users
		);

		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("cn", "testScreenName");
		filterConstraint.addAttribute("gidNumber", StringPool.STAR);
		filterConstraint.addAttribute("givenName", "testFirstName");
		filterConstraint.addAttribute("mail", "test@email");
		filterConstraint.addAttribute(
			"member",
			"ou=testGroupName,ou=Communities,ou=liferay.com,cn=test");
		filterConstraint.addAttribute("sambaSID", null);
		filterConstraint.addAttribute("sn", "testLastName");
		filterConstraint.addAttribute("uidNumber", null);
		filterConstraint.addAttribute("uuid", null);

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _userBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
					"ou=Communities,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
			"member", "cn=testOrganizationName,ou=testOrganizationName," +
				"ou=Organizations,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
					"ou=Roles,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
					"ou=User Groups,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("sambaLockoutDuration", "120"));
		Assert.assertTrue(directory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(directory.hasAttribute("sn", "testLastName"));
	}

	@Test
	public void testBuildDirectoriesValidSambaSID() throws Exception {
		when(userLocalService.fetchUser(Mockito.anyLong())).thenReturn(_user);

		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("cn", "testScreenName");
		filterConstraint.addAttribute("gidNumber", StringPool.STAR);
		filterConstraint.addAttribute("givenName", "testFirstName");
		filterConstraint.addAttribute("mail", "test@email");
		filterConstraint.addAttribute(
			"member",
			"ou=testGroupName,ou=Communities,ou=liferay.com,cn=test");
		filterConstraint.addAttribute("sambaSID", "42-42-42");
		filterConstraint.addAttribute("sn", "testLastName");
		filterConstraint.addAttribute("uidNumber", null);
		filterConstraint.addAttribute("uuid", null);

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _userBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
					"ou=Communities,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testOrganizationName,ou=testOrganizationName," +
					"ou=Organizations,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
				"ou=Roles,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
					"ou=User Groups,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("sambaLockoutDuration", "120"));
		Assert.assertTrue(directory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(directory.hasAttribute("sn", "testLastName"));
	}

	@Test
	public void testBuildDirectoriesValidUidNumber() throws Exception {
		when(userLocalService.fetchUser(Mockito.anyLong())).thenReturn(_user);

		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("cn", "testScreenName");
		filterConstraint.addAttribute("gidNumber", StringPool.STAR);
		filterConstraint.addAttribute("givenName", "testFirstName");
		filterConstraint.addAttribute("mail", "test@email");
		filterConstraint.addAttribute(
			"member",
			"ou=testGroupName,ou=Communities,ou=liferay.com,cn=test");
		filterConstraint.addAttribute("sambaSID", null);
		filterConstraint.addAttribute("sn", "testLastName");
		filterConstraint.addAttribute("uidNumber", "42");
		filterConstraint.addAttribute("uuid", null);

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _userBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
					"ou=Communities,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testOrganizationName,ou=testOrganizationName," +
					"ou=Organizations,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
					"ou=Roles,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
					"ou=User Groups,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("sambaLockoutDuration", "120"));
		Assert.assertTrue(directory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(directory.hasAttribute("sn", "testLastName"));
	}

	@Test
	public void testBuildDirectoriesValidUUID() throws Exception {
		when(
			userLocalService.getUserByUuid(Mockito.anyString())
		).thenReturn(
			_user
		);

		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("gidNumber", StringPool.STAR);
		filterConstraint.addAttribute(
			"member",
			"ou=testGroupName,ou=Communities,ou=liferay.com,cn=test");
		filterConstraint.addAttribute("uuid", "testUuid");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _userBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("cn", "testScreenName"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testGroupName,ou=testGroupName," +
					"ou=Communities,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testOrganizationName,ou=testOrganizationName," +
					"ou=Organizations,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testRoleName,ou=testRoleName," +
					"ou=Roles,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute(
				"member", "cn=testUserGroupName,ou=testUserGroupName," +
					"ou=User Groups,ou=liferay.com,o=Liferay"));
		Assert.assertTrue(
			directory.hasAttribute("sambaLockoutDuration", "120"));
		Assert.assertTrue(directory.hasAttribute("sambaMaxPwdAge", "-1"));
		Assert.assertTrue(directory.hasAttribute("sn", "testLastName"));
	}

	protected void setupExpando() throws Exception {
		ExpandoBridge expandBridge = mock(ExpandoBridge.class);

		when(
			expandBridge.getAttribute(
				Mockito.eq("sambaLMPassword"), Mockito.eq(false))
		).thenReturn(
			"testLMPassword"
		);

		when(
			expandBridge.getAttribute(
				Mockito.eq("sambaNTPassword"), Mockito.eq(false))
		).thenReturn(
			"testNTPassword"
		);

		when(
			_user.getExpandoBridge()
		).thenReturn(
			expandBridge
		);
	}

	protected void setupFastDateFormat() throws Exception {
		FastDateFormat fastDateFormat = FastDateFormat.getInstance(
			"yyyyMMddHHmmss.SZ", (TimeZone)null, LocaleUtil.getDefault());

		FastDateFormatFactory fastDateFormatFactory = mock(
			FastDateFormatFactory.class);

		when(
			fastDateFormatFactory.getSimpleDateFormat(Mockito.anyString())
		).thenReturn(
			fastDateFormat
		);

		FastDateFormatFactoryUtil fastDateFormatFactoryUtil =
			new FastDateFormatFactoryUtil();

		fastDateFormatFactoryUtil.setFastDateFormatFactory(
			fastDateFormatFactory);
	}

	protected void setupGroups() throws Exception {
		Group group = mock(Group.class);

		when(
			group.getGroupId()
		).thenReturn(
			42l
		);

		when(
			group.getName()
		).thenReturn(
			"testGroupName"
		);

		when(
			groupLocalService.getGroup(
				Mockito.eq(42l), Mockito.eq("testGroupName"))
		).thenReturn(
			group
		);

		List<Group> groups = new ArrayList<Group>();

		groups.add(group);

		when(
			groupLocalService.search(
				Mockito.anyLong(), Mockito.any(long[].class),
				Mockito.anyString(), Mockito.anyString(),
				Mockito.any(LinkedHashMap.class), Mockito.anyBoolean(),
				Mockito.anyInt(), Mockito.anyInt())
		).thenReturn(
			groups
		);

		when(
			searchBase.getCommunity()
		).thenReturn(
			group
		);
	}

	protected void setupOrganizations() throws Exception {
		Organization organization = mock(Organization.class);

		when(
			organization.getName()
		).thenReturn(
			"testOrganizationName"
		);

		when(
			organization.getOrganizationId()
		).thenReturn(
			42l
		);

		List<Organization> organizations = new ArrayList<Organization>();

		organizations.add(organization);

		when(
			_user.getOrganizations()
		).thenReturn(
			organizations
		);

		when(
			searchBase.getOrganization()
		).thenReturn(
			organization
		);
	}

	protected void setupPasswordPolicy() throws Exception {
		PasswordPolicy passwordPolicy = mock(PasswordPolicy.class);

		when(
			passwordPolicy.getGraceLimit()
		).thenReturn(
			7200000
		);

		when(
			passwordPolicy.getHistoryCount()
		).thenReturn(
			3600000
		);

		when(
			passwordPolicy.getLockoutDuration()
		).thenReturn(
			7200000l
		);

		when(
			passwordPolicy.getMaxAge()
		).thenReturn(
			14400000l
		);

		when(
			passwordPolicy.getMinAge()
		).thenReturn(
			3600000l
		);

		when(
			passwordPolicy.getResetFailureCount()
		).thenReturn(
			3600000l
		);

		when(
			passwordPolicy.isExpireable()
		).thenReturn(
			false
		);

		when(
			passwordPolicy.isLockout()
		).thenReturn(
			true
		);

		when(
			passwordPolicy.isRequireUnlock()
		).thenReturn(
			true
		);

		when(
			_user.getPasswordPolicy()
		).thenReturn(
			passwordPolicy
		);
	}

	protected void setupPortalUtil() throws Exception {
		Portal portal = mock(Portal.class);

		when(
			portal.getClassNameId(Mockito.any(Class.class))
		).thenReturn(
			42l
		);

		PortalUtil portalUtil = new PortalUtil();

		portalUtil.setPortal(portal);
	}

	@Override
	protected void setupProps() {
		super.setupProps();

		when(
			props.get(PortletPropsValues.POSIX_GROUP_ID)
		).thenReturn(
			"testGroupId"
		);
	}

	protected void setupRoles() throws Exception {
		Role role = mock(Role.class);

		when(
			role.getName()
		).thenReturn(
			"testRoleName"
		);

		when(
			role.getRoleId()
		).thenReturn(
			42l
		);

		List<Role> roles = new ArrayList<Role>();

		roles.add(role);

		when(
			_user.getRoles()
		).thenReturn(
			roles
		);

		when(
			searchBase.getRole()
		).thenReturn(
			role
		);
	}

	protected void setupUserGroups() throws Exception {
		UserGroup userGroup = mock(UserGroup.class);

		when(
			userGroup.getName()
		).thenReturn(
			"testUserGroupName"
		);

		when(
			userGroup.getUserGroupId()
		).thenReturn(
			42l
		);

		List<UserGroup> userGroups = new ArrayList<UserGroup>();

		userGroups.add(userGroup);

		when(
			_user.getUserGroups()
		).thenReturn(
			userGroups
		);

		when(
			searchBase.getUserGroup()
		).thenReturn(
			userGroup
		);
	}

	protected void setupUsers() throws Exception {
		_user = mock(User.class);

		when(
			_user.getCompanyId()
		).thenReturn(
			42l
		);

		when(
			_user.getCreateDate()
		).thenReturn(
			null
		);

		when(
			_user.getEmailAddress()
		).thenReturn(
			"test@email"
		);

		when(
			_user.getFirstName()
		).thenReturn(
			"testFirstName"
		);

		when(
			_user.getFullName()
		).thenReturn(
			"testFullName"
		);

		when(
			_user.getLastName()
		).thenReturn(
			"testLastName"
		);

		when(
			_user.getModifiedDate()
		).thenReturn(
			null
		);

		when(
			_user.getScreenName()
		).thenReturn(
			"testScreenName"
		);

		when(
			_user.getUserId()
		).thenReturn(
			42l
		);

		when(
			_user.getUuid()
		).thenReturn(
			"testUuid"
		);

		_users.add(_user);

		when(
			userLocalService.getCompanyUsers(
				Mockito.anyLong(), Mockito.anyInt(), Mockito.anyInt())
		).thenReturn(
			_users
		);
	}

	private User _user;
	private UserBuilder _userBuilder = new UserBuilder();
	private List<User> _users = new ArrayList<User>();

}