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

import com.liferay.portal.model.Organization;
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

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author William Newbury
 * @author Matthew Tambara
 */
@RunWith(PowerMockRunner.class)
public class SambaGroupBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_organization = mock(Organization.class);
		when(_organization.getName()).thenReturn("testName");

		when(_searchBase.getOrganization()).thenReturn(_organization);

		_sambaGroupBuilder = new SambaGroupBuilder();

		when(props.get(PortletPropsKeys.SEARCH_MAX_SIZE)).thenReturn("42");

	}

	@Test
	public void testBuildDirectoriesGIDNumber() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute("cn", "root");
		filterConstraint.addAttribute("sambaSID", "S-1-5-32-544");
		filterConstraint.addAttribute("gidNumber", "0");

		List<Directory> directory = _sambaGroupBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaGroupType", "4"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "sambaGroupMapping"));
		Assert.assertTrue(returnedDirectory.hasAttribute("gidNumber", "0"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("displayName", "root"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "posixGroup"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaSID", "S-1-5-32-544"));
	}

	@Test
	public void testBuildDirectoriesNullGIDNumber() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);
		filterConstraint.addAttribute("cn", "network");
		filterConstraint.addAttribute("sambaSID", "S-1-5-2");

		List<Directory> directory = _sambaGroupBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaGroupType", "4"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("objectclass", "sambaGroupMapping"));
		Assert.assertFalse(returnedDirectory.hasAttribute("gidNumber"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("displayName", "network"));
		Assert.assertFalse(
			returnedDirectory.hasAttribute("objectclass", "posixGroup"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaSID", "S-1-5-2"));
	}

	private Organization _organization;
	private SambaGroupBuilder _sambaGroupBuilder;
}