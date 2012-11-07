/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.saml.hook.auth;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AutoLogin;
import com.liferay.portal.security.auth.AutoLoginException;
import com.liferay.portal.security.ldap.PortalLDAPImporterUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.service.SamlSpSessionLocalServiceUtil;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.saml.util.PortletWebKeys;
import com.liferay.saml.util.SamlUtil;
import com.liferay.util.PwdGenerator;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.NameIDType;

/**
 * @author Mika Koivisto
 */
public class SamlSpAutoLoginHook implements AutoLogin {

	public String[] login(
			HttpServletRequest request, HttpServletResponse response)
		throws AutoLoginException {

		String[] credentials = null;

		try {
			if (!SamlUtil.isEnabled() || !SamlUtil.isRoleSp()) {
				return credentials;
			}

			HttpSession session = request.getSession();

			NameID nameId = (NameID)session.getAttribute(
				PortletWebKeys.SAML_SP_NAME_ID);

			if (nameId == null) {
				return credentials;
			}

			User user = null;

			long companyId = PortalUtil.getCompanyId(request);

			String nameIdFormat = nameId.getFormat();
			String nameIdValue = nameId.getValue();

			if (GetterUtil.getBoolean(
					PropsUtil.get(
						PortletPropsKeys.SAML_SP_LDAP_IMPORT_ENABLED))) {

				if (nameIdFormat.equals(NameIDType.EMAIL)) {
					user = PortalLDAPImporterUtil.importLDAPUser(
						companyId, nameIdValue, StringPool.BLANK);
				}
				else {
					user = PortalLDAPImporterUtil.importLDAPUser(
						companyId, StringPool.BLANK, nameIdValue);
				}
			}

			if (user == null) {
				try {
					if (nameIdFormat.equals(NameIDType.EMAIL)) {
						user = UserLocalServiceUtil.getUserByEmailAddress(
							companyId, nameIdValue);
					}
					else {
						user = UserLocalServiceUtil.getUserByScreenName(
							companyId, nameIdValue);
					}
				}
				catch (NoSuchUserException nsue) {
				}
			}

			if (user == null) {
				user = addUser(request);
			}

			if (user != null) {
				credentials = new String[3];

				credentials[0] = String.valueOf(user.getUserId());
				credentials[1] = user.getPassword();
				credentials[2] = Boolean.TRUE.toString();

				ServiceContext serviceContext =
					ServiceContextFactory.getInstance(request);

				serviceContext.setUserId(user.getUserId());

				SamlSpSessionLocalServiceUtil.addSamlSpSession(
					session.getId(), nameIdFormat, nameIdValue, serviceContext);
			}

			return credentials;
		}
		catch (Exception e) {
			_log.warn(e, e);

			throw new AutoLoginException(e);
		}
	}

	protected User addUser(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();

		List<Attribute>attributes = (List<Attribute>)session.getAttribute(
			PortletWebKeys.SAML_SP_ATTRIBUTES);

		Properties userAttributeMappings = PropertiesUtil.load(
			PropsUtil.get(PortletPropsKeys.SAML_SP_USER_ATTRIBUTE_MAPPINGS));

		Map<String, String> attributesMap = SamlUtil.getAttributesMap(
			attributes, userAttributeMappings);

		long creatorUserId = 0;
		long companyId = PortalUtil.getCompanyId(request);
		boolean autoPassword = false;
		String password1 = PwdGenerator.getPassword();
		String password2 = password1;
		boolean autoScreenName = false;
		String screenName = attributesMap.get("screenName");
		String emailAddress = attributesMap.get("emailAddress");
		long facebookId = 0;
		String openId = StringPool.BLANK;
		Locale locale = getLocale(request);
		String firstName = attributesMap.get("firstName");
		String middleName = StringPool.BLANK;
		String lastName = attributesMap.get("lastName");
		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = Calendar.JANUARY;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		String jobTitle = StringPool.BLANK;
		long[] groupIds = null;
		long[] organizationIds = null;
		long[] roleIds = null;
		long[] userGroupIds = null;
		boolean sendEmail = false;

		ServiceContext serviceContext = new ServiceContext();

		String uuid = attributesMap.get("uuid");

		serviceContext.setUuid(uuid);

		return UserLocalServiceUtil.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, facebookId, openId,
			locale, firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);
	}

	protected Locale getLocale(HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Locale locale = LocaleUtil.getDefault();

		if (themeDisplay != null) {

			// ThemeDisplay should never be null, but some users complain of
			// this error. Cause is unknown.

			locale = themeDisplay.getLocale();
		}

		return locale;
	}

	private static Log _log = LogFactoryUtil.getLog(SamlSpAutoLoginHook.class);

}