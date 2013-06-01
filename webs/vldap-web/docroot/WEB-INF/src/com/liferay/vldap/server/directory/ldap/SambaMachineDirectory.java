package com.liferay.vldap.server.directory.ldap;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Organization;
import com.liferay.vldap.util.PortletPropsValues;

import java.util.ArrayList;
import java.util.List;

public class SambaMachineDirectory extends Directory {

	public SambaMachineDirectory(
		String top, Company company, Organization organization, String name) {

		_domainName = name;

		addAttribute("sambaDomainName", _domainName);
		addAttribute("objectclass", "sambaDomain");
		addAttribute("objectclass", "top");

		StringBundler sambaSID = new StringBundler();
		sambaSID.append("S-1-5-21-");
		sambaSID.append(company.getCompanyId());

		addAttribute("sambaSID", sambaSID.toString());

		addAttribute("sambaNextUserRid", "1000");
		addAttribute("sambaAlgorithmicRidBase", "1000");
		addAttribute("sambaPwdHistoryLength", "0");
		addAttribute("sambaLockoutThreshold", "0");

		setName(
			top, company, "Organizations", organization.getName(),
			"Samba Machines", "sambaDomainName=" + _domainName);
	}

	public String getDomainName() {
		return _domainName;
	}

	private String _domainName;

}
