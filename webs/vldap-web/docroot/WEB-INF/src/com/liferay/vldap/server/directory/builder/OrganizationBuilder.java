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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.User;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.SearchBase;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.server.directory.ldap.OrganizationDirectory;
import com.liferay.vldap.util.LdapUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.directory.shared.ldap.model.name.Dn;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class OrganizationBuilder extends DirectoryBuilder {

	@Override
	public boolean isValidAttribute(String attributeId, String value) {
		if (attributeId.equalsIgnoreCase("cn") ||
			attributeId.equalsIgnoreCase("member") ||
			attributeId.equalsIgnoreCase("ou")) {

			return true;
		}
		else if (attributeId.equalsIgnoreCase("objectclass")) {
			if (value.equalsIgnoreCase("groupOfNames") ||
				value.equalsIgnoreCase("organizationalUnit") ||
				value.equalsIgnoreCase("liferayOrganization") ||
				value.equalsIgnoreCase("top") || value.equalsIgnoreCase("*")) {

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

		for (Company company : searchBase.getCompanies()) {
			List<Organization> organizations = getOrganizations(
				company, filterConstraints, (int)searchBase.getSizeLimit());

			for (Organization organization : organizations) {
				Directory directory = new OrganizationDirectory(
					searchBase.getTop(), company, organization);

				directories.add(directory);
			}
		}

		return directories;
	}

	protected List<Organization> getOrganizations(
			Company company, List<FilterConstraint> filterConstraints,
			int sizeLimit)
		throws Exception {

		if (filterConstraints == null) {
			return getOrganizations(company.getCompanyId(), null, sizeLimit);
		}

		List<Organization> organizations = new ArrayList<Organization>();

		for (FilterConstraint filterConstraint : filterConstraints) {
			if (!isValidFilterConstraint(filterConstraint)) {
				continue;
			}

			String name = filterConstraint.getValue("ou");

			if (name == null) {
				name = filterConstraint.getValue("cn");
			}

			String member = filterConstraint.getValue("member");

			String screenName = LdapUtil.getRdnValue(new Dn(member), 3);

			if (screenName != null) {
				User user = UserLocalServiceUtil.getUserByScreenName(
					company.getCompanyId(), screenName);

				for (Organization organization : user.getOrganizations()) {
					if ((name != null) &&
						!name.equals(organization.getName())) {

						continue;
					}

					organizations.add(organization);
				}
			}
			else {
				organizations.addAll(
					getOrganizations(company.getCompanyId(), name, sizeLimit));
			}
		}

		return organizations;
	}

	protected List<Organization> getOrganizations(
			long companyId, String name, int sizeLimit)
		throws Exception {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			Organization.class, PortalClassLoaderUtil.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyId", companyId));

		if (name != null) {
			dynamicQuery.add(RestrictionsFactoryUtil.ilike("name", name));
		}

		dynamicQuery.setLimit(0, sizeLimit);

		return OrganizationLocalServiceUtil.dynamicQuery(dynamicQuery);
	}

}