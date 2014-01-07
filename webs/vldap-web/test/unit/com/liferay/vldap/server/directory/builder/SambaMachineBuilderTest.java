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
import com.liferay.vldap.server.directory.BaseVLDAPTestCase;
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
public class SambaMachineBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_sambaMachineBuilder = new SambaMachineBuilder();

		_organization = mock(Organization.class);

		when(_organization.getName()).thenReturn("testName");

		when(_searchBase.getOrganization()).thenReturn(_organization);
	}

	@Test
	public void testBuildDirectoriesOrganizationDomain() throws Exception {
		List<Directory> directory = _sambaMachineBuilder.buildDirectories(
			_searchBase.getTop(), _company, _organization, "testDomainName");

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"sambaDomainName", "testDomainName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaSID", "S-1-5-21-" + 42l));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaNextUserRid", "1000"));
	}

	@Test
	public void testBuildDirectoriesValidFilterConstraint() throws Exception {
		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("sambaDomainName", "testDomainName");

		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);

		List<Directory> directory = _sambaMachineBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"sambaDomainName", "testDomainName"));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaSID", "S-1-5-21-" + 42l));
		Assert.assertTrue(
			returnedDirectory.hasAttribute("sambaNextUserRid", "1000"));
	}

	private Organization _organization;
	private SambaMachineBuilder _sambaMachineBuilder;

}