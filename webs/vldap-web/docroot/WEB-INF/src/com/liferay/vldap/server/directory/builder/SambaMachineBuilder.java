package com.liferay.vldap.server.directory.builder;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
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

public class SambaMachineBuilder extends DirectoryBuilder {

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

	public List<Directory> buildDirectories(
			String top, Company company, Organization organization,
			String domain)
		throws Exception {

		List<Directory> directories = new ArrayList<Directory>();

		for (String sambaDomain : PortletPropsValues.SAMBA_DOMAINS) {
			if (Validator.isNotNull(domain) && !sambaDomain.equals(domain)) {
				continue;
			}

			SambaMachineDirectory sambaMachine =
				new SambaMachineDirectory(
					top, company, organization, sambaDomain);

			directories.add(sambaMachine);
		}

		return directories;
	}

	protected List<Directory> buildDirectories(
			SearchBase searchBase, List<FilterConstraint> filterConstraints)
		throws Exception {

		List<Directory> directories = new ArrayList<Directory>();

		for (FilterConstraint filterConstraint : filterConstraints) {
			if (!isValidFilterConstraint(filterConstraint)) {
				continue;
			}

			String domain = filterConstraint.getValue(
				"sambaDomainName");

			for (Company company : searchBase.getCompanies()) {
				List<Directory> subdirectories = buildDirectories(
					searchBase.getTop(), company, searchBase.getOrganization(),
					domain);

				directories.addAll(subdirectories);
			}
		}

		return directories;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SambaMachineBuilder.class);

}