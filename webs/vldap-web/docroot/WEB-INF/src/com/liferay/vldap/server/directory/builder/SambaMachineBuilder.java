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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Organization;
import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.SearchBase;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.server.directory.ldap.SambaMachineDirectory;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Minhchau Dang
 */
public class SambaMachineBuilder extends DirectoryBuilder {

	public List<Directory> buildDirectories(
			String top, Company company, Organization organization,
			String sambaDomainName)
		throws Exception {

		List<Directory> directories = new ArrayList<Directory>();

		for (String curSambaDomainName :
				PortletPropsValues.SAMBA_DOMAIN_NAMES) {

			if (Validator.isNotNull(sambaDomainName) &&
				!curSambaDomainName.equals(sambaDomainName)) {

				continue;
			}

			SambaMachineDirectory sambaMachineDirectory =
				new SambaMachineDirectory(
					top, company, organization, curSambaDomainName);

			directories.add(sambaMachineDirectory);
		}

		return directories;
	}

	@Override
	public boolean isValidAttribute(String attributeId, String value) {
		if (attributeId.equalsIgnoreCase("sambaDomainName")) {
			return true;
		}
		else if (attributeId.equalsIgnoreCase("objectclass")) {
			if (value.equalsIgnoreCase("sambaDomain")) {
				return true;
			}
		}

		return false;
	}

	@Override
	protected List<Directory> buildDirectories(
			SearchBase searchBase, List<FilterConstraint> filterConstraints)
		throws Exception {

		List<Directory> directories = new ArrayList<Directory>();

		for (FilterConstraint filterConstraint : filterConstraints) {
			if (!isValidFilterConstraint(filterConstraint)) {
				continue;
			}

			String sambaDomainName = filterConstraint.getValue(
				"sambaDomainName");

			for (Company company : searchBase.getCompanies()) {
				List<Directory> subdirectories = buildDirectories(
					searchBase.getTop(), company, searchBase.getOrganization(),
					sambaDomainName);

				directories.addAll(subdirectories);
			}
		}

		return directories;
	}

}