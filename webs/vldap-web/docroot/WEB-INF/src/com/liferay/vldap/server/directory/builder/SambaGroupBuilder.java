package com.liferay.vldap.server.directory.builder;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.RoleConstants;
import com.liferay.vldap.server.directory.FilterConstraint;
import com.liferay.vldap.server.directory.SearchBase;
import com.liferay.vldap.server.directory.ldap.Directory;
import com.liferay.vldap.server.directory.ldap.SambaMachineDirectory;
import com.liferay.vldap.server.directory.ldap.SambaGroupDirectory;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
				List<Directory> subdirectories = getSambaRoles(
					searchBase.getTop(), company, searchBase.getOrganization(),
					name, sambaSID, gidNumber);

				directories.addAll(subdirectories);
			}
		}

		return directories;
	}

	protected void filterTuples(
		List<Tuple> matchingTuples, int fieldIndex, String value) {

		if ((value == null) || value.equals(StringPool.STAR)) {
			return;
		}

		Iterator<Tuple> iterator = matchingTuples.iterator();

		while (iterator.hasNext()) {
			Tuple tuple = iterator.next();

			Object tupleValue = tuple.getObject(fieldIndex);

			if (tupleValue == null) {
				iterator.remove();
			}
			else if (tupleValue instanceof Pattern) {
				Pattern pattern = (Pattern) tupleValue;

				Matcher matcher = pattern.matcher(value);

				if (!matcher.matches()) {
					iterator.remove();
				}
			}
			else if (tupleValue instanceof String) {
				if (!value.equals(tupleValue)) {
					iterator.remove();
				}
			}
		}
	}
	protected List<Tuple> getDefaultTuples(Company company) {
		List<Tuple> matchingTuples = new ArrayList<Tuple>();

		String domainPrefix = "S-1-5-21-" + company.getCompanyId();

		matchingTuples.add(
			new Tuple(
				"nobody", "S-1-0-0", _NOBODY_POSIX_GROUP_ID,
				new String[] {}));
		matchingTuples.add(
			new Tuple(
				"everyone", "S-1-1-0", _USER_POSIX_GROUP_ID,
				new String[] { RoleConstants.USER }));
		matchingTuples.add(
			new Tuple(
				"network", "S-1-5-2", null, new String[] {}));
		matchingTuples.add(
			new Tuple(
				"authenticated", "S-1-5-11", null, new String[] {}));
		matchingTuples.add(
			new Tuple(
				"domain admins", domainPrefix + "-512", _ADMIN_POSIX_GROUP_ID,
				new String[] { RoleConstants.ADMINISTRATOR }));
		matchingTuples.add(
			new Tuple(
				"domain users", domainPrefix + "-513", _USER_POSIX_GROUP_ID,
				new String[] { RoleConstants.USER }));
		matchingTuples.add(
			new Tuple(
				"domain guests", domainPrefix + "-514", _GUEST_POSIX_GROUP_ID,
				new String[] { RoleConstants.GUEST }));
		matchingTuples.add(
			new Tuple(
				"root", "S-1-5-32-544", _ADMIN_POSIX_GROUP_ID,
				new String[] { RoleConstants.ADMINISTRATOR }));
		matchingTuples.add(
			new Tuple(
				"users", "S-1-5-32-545", _USER_POSIX_GROUP_ID,
				new String[] { RoleConstants.USER }));
		matchingTuples.add(
			new Tuple(
				"nogroup", "S-1-5-32-546", _GUEST_POSIX_GROUP_ID,
				new String[] { RoleConstants.GUEST }));

		return matchingTuples;
	}

	protected List<Directory> getSambaRoles(
		String top, Company company, Organization organization,
		String displayName, String sambaSID, String gidNumber)
		throws Exception {

		List<Directory> directories = new ArrayList<Directory>();

		List<Tuple> matchingTuples = getDefaultTuples(company);

		filterTuples(matchingTuples, 0, displayName);
		filterTuples(matchingTuples, 2, gidNumber);
		filterTuples(matchingTuples, 1, sambaSID);

		for (Tuple tuple : matchingTuples) {
			Directory directory = new SambaGroupDirectory(
				top, company, organization, tuple);

			directories.add(directory);
		}

		return directories;
	}

	private static final String _ADMIN_POSIX_GROUP_ID = "0";
	private static final String _GUEST_POSIX_GROUP_ID = "65534";
	private static final String _NOBODY_POSIX_GROUP_ID = "99";
	private static final String _USER_POSIX_GROUP_ID =
		PortletPropsValues.POSIX_GROUP_ID;

}
