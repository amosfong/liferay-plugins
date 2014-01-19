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
public class SambaMachineBuilderTest extends BaseVLDAPTestCase {

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
	}

	@Test
	public void testBuildDirectoriesDefaultFilter() throws Exception {
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();

		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute("sambaDomainName", "testDomainName");

		filterConstraints.add(filterConstraint);

		List<Directory> directories = _sambaMachineBuilder.buildDirectories(
			searchBase, filterConstraints);

		Directory directory = directories.get(0);

		Assert.assertTrue(
			directory.hasAttribute("sambaDomainName", "testDomainName"));
		Assert.assertTrue(directory.hasAttribute("sambaNextUserRid", "1000"));
		Assert.assertTrue(
			directory.hasAttribute(
				"sambaSID", "S-1-5-21-" + company.getCompanyId()));
	}

	@Test
	public void testBuildDirectoriesValidOrganizationDomain() throws Exception {
		List<Directory> directories = _sambaMachineBuilder.buildDirectories(
			searchBase.getTop(), company, _organization, "testDomainName");

		Directory directory = directories.get(0);

		Assert.assertTrue(
			directory.hasAttribute("sambaDomainName", "testDomainName"));
		Assert.assertTrue(directory.hasAttribute("sambaNextUserRid", "1000"));
		Assert.assertTrue(
			directory.hasAttribute(
				"sambaSID", "S-1-5-21-" + company.getCompanyId()));
	}

	private Organization _organization;
	private SambaMachineBuilder _sambaMachineBuilder =
		new SambaMachineBuilder();

}