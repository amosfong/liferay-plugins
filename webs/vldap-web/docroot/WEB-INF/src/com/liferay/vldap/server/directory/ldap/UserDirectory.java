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

package com.liferay.vldap.server.directory.ldap;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.security.samba.PortalSambaUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.vldap.server.handler.util.LdapHandlerThreadLocal;
import com.liferay.vldap.util.LdapUtil;
import com.liferay.vldap.util.PortletPropsValues;

import java.text.Format;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class UserDirectory extends Directory {

	public UserDirectory(String top, Company company, User user)
		throws Exception {

		addAttribute("cn", user.getScreenName());

		Date createDate = user.getCreateDate();

		if (createDate == null) {
			createDate = new Date();
		}

		addAttribute("createTimestamp", _format.format(createDate));

		addAttribute("displayName", user.getFullName());
		addAttribute("gidNumber", PortletPropsValues.POSIX_GROUP_ID);
		addAttribute("givenName", user.getFirstName());
		addAttribute("mail", user.getEmailAddress());

		Date modifyDate = user.getModifiedDate();

		if (modifyDate == null) {
			modifyDate = new Date();
		}

		addAttribute("modifyTimestamp", _format.format(modifyDate));

		addAttribute("objectclass", "groupOfNames");
		addAttribute("objectclass", "inetOrgPerson");
		addAttribute("objectclass", "liferayPerson");
		addAttribute("objectclass", "top");
		addAttribute("sn", user.getLastName());
		addAttribute("uid", user.getScreenName());
		addAttribute("uidNumber", String.valueOf(user.getUserId()));
		addAttribute("uuid", user.getUuid());

		if (LdapHandlerThreadLocal.isHostAllowed(
				PortletPropsValues.SAMBA_HOSTS_ALLOWED)) {

			addSambaAttributes(company, user);
		}

		String name = LdapUtil.buildName(top, company);

		setName(top, company, "Users", "cn=" + user.getScreenName());

		long groupClassNameId = PortalUtil.getClassNameId(
			Group.class.getName());

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("usersGroups", user.getUserId());

		List<Group> groups = GroupLocalServiceUtil.search(
			user.getCompanyId(), new long[] {groupClassNameId}, null, null,
			params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groups) {
			StringBundler sb = new StringBundler(6);

			sb.append("cn=");
			sb.append(group.getName());
			sb.append(",ou=");
			sb.append(group.getName());
			sb.append(",ou=Communities,");
			sb.append(name);

			addAttribute("member", sb.toString());
		}

		for (Organization organization : user.getOrganizations()) {
			StringBundler sb = new StringBundler(6);

			sb.append("cn=");
			sb.append(organization.getName());
			sb.append(",ou=");
			sb.append(organization.getName());
			sb.append(",ou=Organizations,");
			sb.append(name);

			addAttribute("member", sb.toString());
		}

		for (Role role : user.getRoles()) {
			StringBundler sb = new StringBundler(6);

			sb.append("cn=");
			sb.append(role.getName());
			sb.append(",ou=");
			sb.append(role.getName());
			sb.append(",ou=Roles,");
			sb.append(name);

			addAttribute("member", sb.toString());
		}

		for (UserGroup userGroup : user.getUserGroups()) {
			StringBundler sb = new StringBundler(6);

			sb.append("cn=");
			sb.append(userGroup.getName());
			sb.append(",ou=");
			sb.append(userGroup.getName());
			sb.append(",ou=User Groups,");
			sb.append(name);

			addAttribute("member", sb.toString());
		}
	}

	protected void addSambaAttributes(Company company, User user)
		throws Exception {

		addAttribute("objectclass", "sambaSamAccount");

		PasswordPolicy passwordPolicy = user.getPasswordPolicy();

		StringBundler sb = new StringBundler(13);

		sb.append(CharPool.OPEN_BRACKET);
		sb.append('U');

		if (!user.isActive()) {
			sb.append('D');
		}

		if (!passwordPolicy.isExpireable()) {
			sb.append('X');
		}

		for (int i = sb.length(); i < 12; i++) {
			sb.append(CharPool.SPACE);
		}

		sb.append(CharPool.CLOSE_BRACKET);

		addAttribute("sambaAcctFlags", sb.toString());

		addAttribute("sambaForceLogoff", "-1");
		addAttribute(
			"sambaLMPassword", PortalSambaUtil.getSambaLMPassword(user));

		long sambaLockoutDuration =
			passwordPolicy.getLockoutDuration() / Time.MINUTE;

		if (!passwordPolicy.isLockout()) {
			sambaLockoutDuration = 0;
		}

		addAttribute(
			"sambaLockoutDuration", String.valueOf(sambaLockoutDuration));

		long sambaLockoutObservationWindow =
			passwordPolicy.getResetFailureCount() / Time.MINUTE;

		if (passwordPolicy.isRequireUnlock()) {
			sambaLockoutObservationWindow = Integer.MAX_VALUE;
		}

		addAttribute(
			"sambaLockoutObservationWindow",
			String.valueOf(sambaLockoutObservationWindow));

		addAttribute(
			"sambaLockoutThreshold",
			String.valueOf(passwordPolicy.getGraceLimit()));
		addAttribute("sambaLogonToChgPwd", "2");

		long sambaMaxPwdAge = passwordPolicy.getMaxAge() / Time.MINUTE;

		if (!passwordPolicy.isExpireable()) {
			sambaMaxPwdAge = -1;
		}

		addAttribute("sambaMaxPwdAge", String.valueOf(sambaMaxPwdAge));

		addAttribute(
			"sambaMinPwdAge",
			String.valueOf(passwordPolicy.getMinAge() / Time.MINUTE));
		addAttribute(
			"sambaMinPwdLength", String.valueOf(passwordPolicy.getMinLength()));
		addAttribute(
			"sambaNTPassword", PortalSambaUtil.getSambaNTPassword(user));

		int sambaPwdHistoryLength = passwordPolicy.getHistoryCount();

		if (!passwordPolicy.isHistory()) {
			sambaPwdHistoryLength = 0;
		}

		addAttribute(
			"sambaPwdHistoryLength", String.valueOf(sambaPwdHistoryLength));

		addAttribute("sambaRefuseMachinePwdChange", "0");
		addAttribute(
			"sambaSID",
			"S-1-5-21-" + company.getCompanyId() + "-" + user.getUserId());
	}

	private static Format _format =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyyMMddHHmmss.SZ");

}