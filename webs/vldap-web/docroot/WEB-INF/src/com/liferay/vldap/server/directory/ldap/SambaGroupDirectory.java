package com.liferay.vldap.server.directory.ldap;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SambaGroupDirectory extends RoleDirectory {

	public SambaGroupDirectory(
			String top, Company company, Organization organization, Tuple tuple)
		throws Exception {

		super(top, company, (String) tuple.getObject(0));

		String roleName = (String) tuple.getObject(0);

		addAttribute("displayName", roleName);
		addAttribute("objectclass", "sambaGroupMapping");

		String sambaSID = (String) tuple.getObject(1);

		addAttribute("sambaSID", sambaSID);
		addAttribute("sambaGroupType", "4");

		String gidNumber = (String) tuple.getObject(2);

		if (gidNumber != null) {
			addAttribute("objectclass", "posixGroup");
			addAttribute("gidNumber", gidNumber);
		}

		String[] membershipRoles = (String[]) tuple.getObject(3);

		for (String membershipRole : membershipRoles) {
			Role role = RoleLocalServiceUtil.fetchRole(
				company.getCompanyId(), membershipRole);

			if (role != null) {
				addRoleMembers(top, company, role.getRoleId());
			}
		}

		setName(
			top, company, "Organizations", organization.getName(),
			"Samba Groups", "cn=" + roleName);
	}

}
