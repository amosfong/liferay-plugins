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

import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.CompanyLocalServiceUtil;
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
public class CompanyBuilderTest extends BaseVLDAPTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_companyBuilder = new CompanyBuilder();
		_companyLocalService = getMockService(
			CompanyLocalServiceUtil.class, CompanyLocalService.class);

		when(props.get(PortletPropsKeys.SEARCH_MAX_SIZE)).thenReturn("42");

	}

	@Test
	public void testBuildDirectoriesNullCompanyWebId() throws Exception {
		when(
			_companyLocalService.getCompanies(Mockito.anyBoolean())
		).thenReturn(
			_companies
		);

		FilterConstraint filterConstraint = new FilterConstraint();
		filterConstraint.addAttribute("ou", null);
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);

		List<Directory> directory = _companyBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "organizationalUnit"));
		Assert.assertTrue(returnedDirectory.hasAttribute("objectclass", "top"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "42"));

	}

	@Test
	public void testBuildDirectoriesNullFilter() throws Exception {
		when(
			_companyLocalService.getCompanies()
		).thenReturn(
			_companies
		);

		List<Directory> directory = _companyBuilder.buildDirectories(
			_searchBase, null);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "organizationalUnit"));
		Assert.assertTrue(returnedDirectory.hasAttribute("objectclass", "top"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "42"));

	}

	@Test
	public void testBuildDirectoriesValidCompanyWebId() throws Exception {
		when(
			_companyLocalService.getCompanyByWebId(Mockito.anyString())
		).thenReturn(
			_company
		);

		FilterConstraint filterConstraint = new FilterConstraint();
		filterConstraint.addAttribute("ou", "42");
		List<FilterConstraint> filterConstraints =
			new ArrayList<FilterConstraint>();
		filterConstraints.add(filterConstraint);

		List<Directory> directory = _companyBuilder.buildDirectories(
			_searchBase, filterConstraints);

		Directory returnedDirectory = directory.get(0);

		Assert.assertTrue(
			returnedDirectory.hasAttribute(
				"objectclass", "organizationalUnit"));
		Assert.assertTrue(returnedDirectory.hasAttribute("objectclass", "top"));
		Assert.assertTrue(returnedDirectory.hasAttribute("ou", "42"));

	}

	private CompanyBuilder _companyBuilder;
	private CompanyLocalService _companyLocalService;

}