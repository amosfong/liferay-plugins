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

		when(
			_organization.getName()
		).thenReturn(
			"testName"
		);

		when(
			searchBase.getOrganization()
		).thenReturn(
			_organization
		);

		_sambaGroupBuilder = new SambaGroupBuilder();
	}

	@Test
	public void testBuildDirectoriesNoGIDNumber() throws Exception {
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("cn", "network");
		filterConstraint.addAttribute("sambaSID", "S-1-5-2");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _sambaGroupBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("displayName", "network"));
		Assert.assertFalse(directory.hasAttribute("gidNumber"));
		Assert.assertFalse(directory.hasAttribute("objectclass", "posixGroup"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "sambaGroupMapping"));
		Assert.assertTrue(directory.hasAttribute("sambaGroupType", "4"));
		Assert.assertTrue(directory.hasAttribute("sambaSID", "S-1-5-2"));
	}

	@Test
	public void testBuildDirectoriesValidGIDNumber() throws Exception {
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("cn", "root");
		filterConstraint.addAttribute("gidNumber", "0");
		filterConstraint.addAttribute("sambaSID", "S-1-5-32-544");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _sambaGroupBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(directory.hasAttribute("displayName", "root"));
		Assert.assertTrue(directory.hasAttribute("gidNumber", "0"));
		Assert.assertTrue(directory.hasAttribute("objectclass", "posixGroup"));
		Assert.assertTrue(
			directory.hasAttribute("objectclass", "sambaGroupMapping"));
		Assert.assertTrue(directory.hasAttribute("sambaGroupType", "4"));
		Assert.assertTrue(directory.hasAttribute("sambaSID", "S-1-5-32-544"));
	}

	private Organization _organization;
	private SambaGroupBuilder _sambaGroupBuilder;

}