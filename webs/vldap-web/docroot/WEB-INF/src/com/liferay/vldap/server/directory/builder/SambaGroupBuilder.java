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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Organization;
import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.SearchBase;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.server.directory.ldap.SambaGroup;
import com.liferay.vldap.server.directory.ldap.SambaGroupDirectory;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Minhchau Dang
 */
public class SambaGroupBuilder extends DirectoryBuilder {

	@Override
	public boolean isValidAttribute(String attributeId, String value) {
		if (attributeId.equalsIgnoreCase("cn") ||
			attributeId.equalsIgnoreCase("displayName") ||
			attributeId.equalsIgnoreCase("gidNumber") ||
			attributeId.equalsIgnoreCase("sambaGroupType") ||
			attributeId.equalsIgnoreCase("sambaSID") ||
			attributeId.equalsIgnoreCase("sambaSIDList")) {

			return true;
		}
		else if (attributeId.equalsIgnoreCase("objectclass")) {
			if (value.equalsIgnoreCase("liferayRole") ||
				value.equalsIgnoreCase("posixGroup") ||
				value.equalsIgnoreCase("sambaGroupMapping") ||
				value.equalsIgnoreCase("top") || value.equalsIgnoreCase("*")) {

				return true;
			}
		}

		return false;
	}

	protected void addSambaGroup(
		List<SambaGroup> sambaGroups, String name, String sambaSID,
		String gidNumber) {

		SambaGroup sambaGroup = new SambaGroup(name, sambaSID, gidNumber);

		sambaGroups.add(sambaGroup);
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

			String name = filterConstraint.getValue("cn");

			if (Validator.isNull(name)) {
				name = filterConstraint.getValue("displayName");
			}

			String sambaSID = filterConstraint.getValue("sambaSID");

			if (sambaSID == null) {
				sambaSID = filterConstraint.getValue("sambaSIDList");
			}

			String gidNumber = filterConstraint.getValue("gidNumber");

			for (Company company : searchBase.getCompanies()) {
				List<Directory> subdirectories = getSambaGroupDirectories(
					searchBase.getTop(), company, searchBase.getOrganization(),
					name, sambaSID, gidNumber);

				directories.addAll(subdirectories);
			}
		}

		return directories;
	}

	protected void filterSambaGroups(
		List<SambaGroup> sambaGroups, String fieldName, String value) {

		if ((value == null) || value.equals(StringPool.STAR)) {
			return;
		}

		Iterator<SambaGroup> iterator = sambaGroups.iterator();

		while (iterator.hasNext()) {
			SambaGroup sambaGroup = iterator.next();

			String sambaGroupValue = null;

			if (fieldName.equals("gidNumber")) {
				sambaGroupValue = sambaGroup.getGIDNumber();
			}
			else if (fieldName.equals("name")) {
				sambaGroupValue = sambaGroup.getName();
			}
			else if (fieldName.equals("sambaSID")) {
				sambaGroupValue = sambaGroup.getSambaSID();
			}

			if ((sambaGroupValue == null) || !value.equals(sambaGroupValue)) {
				iterator.remove();
			}
		}
	}

	protected List<Directory> getSambaGroupDirectories(
			String top, Company company, Organization organization, String name,
			String sambaSID, String gidNumber)
		throws Exception {

		List<Directory> directories = new ArrayList<Directory>();

		List<SambaGroup> sambaGroups = getSambaGroups(company);

		filterSambaGroups(sambaGroups, "name", name);
		filterSambaGroups(sambaGroups, "gidNumber", gidNumber);
		filterSambaGroups(sambaGroups, "sambaSID", sambaSID);

		for (SambaGroup sambaGroup : sambaGroups) {
			Directory directory = new SambaGroupDirectory(
				top, company, organization, sambaGroup);

			directories.add(directory);
		}

		return directories;
	}

	protected List<SambaGroup> getSambaGroups(Company company) {
		List<SambaGroup> sambaGroups = new ArrayList<SambaGroup>();

		addSambaGroup(sambaGroups, "authenticated", "S-1-5-11", null);

		String domainPrefix = "S-1-5-21-" + company.getCompanyId();

		addSambaGroup(
			sambaGroups, "domain admins", domainPrefix + "-512",
			_ADMIN_POSIX_GROUP_ID);
		addSambaGroup(
			sambaGroups, "domain guests", domainPrefix + "-514",
			_GUEST_POSIX_GROUP_ID);
		addSambaGroup(
			sambaGroups, "domain users", domainPrefix + "-513",
			_USER_POSIX_GROUP_ID);

		addSambaGroup(sambaGroups, "everyone", "S-1-1-0", _USER_POSIX_GROUP_ID);
		addSambaGroup(sambaGroups, "network", "S-1-5-2", null);
		addSambaGroup(sambaGroups, "nobody", "S-1-0-0", _NOBODY_POSIX_GROUP_ID);
		addSambaGroup(
			sambaGroups, "nogroup", "S-1-5-32-546", _GUEST_POSIX_GROUP_ID);
		addSambaGroup(
			sambaGroups, "root", "S-1-5-32-544", _ADMIN_POSIX_GROUP_ID);
		addSambaGroup(
			sambaGroups, "users", "S-1-5-32-545", _USER_POSIX_GROUP_ID);

		return sambaGroups;
	}

	private static final String _ADMIN_POSIX_GROUP_ID = "0";

	private static final String _GUEST_POSIX_GROUP_ID = "65534";

	private static final String _NOBODY_POSIX_GROUP_ID = "99";

	private static final String _USER_POSIX_GROUP_ID =
		PortletPropsValues.POSIX_GROUP_ID;

}