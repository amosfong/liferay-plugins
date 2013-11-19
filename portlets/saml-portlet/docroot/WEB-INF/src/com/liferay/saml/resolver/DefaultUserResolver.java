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

package com.liferay.saml.resolver;

import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.security.ldap.PortalLDAPImporterUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.util.SamlUtil;
import com.liferay.util.PwdGenerator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.core.Response;

/**
 * @author Mika Koivisto
 */
public class DefaultUserResolver implements UserResolver {

	@Override
	public User resolveUser(
			Assertion assertion,
			SAMLMessageContext<Response, SAMLObject, NameID> samlMessageContext,
			ServiceContext serviceContext)
		throws Exception {

		if (_log.isDebugEnabled()) {
			NameID nameId = samlMessageContext.getSubjectNameIdentifier();

			_log.debug(
				"Resolving user with name ID format " + nameId.getFormat() +
					" and value " + nameId.getValue());
		}

		User user = null;

		long companyId = CompanyThreadLocal.getCompanyId();

		String subjectNameIdentifier = getSubjectNameIdentifier(
			companyId, assertion, samlMessageContext);
		String subjectNameIdentifierType = getSubjectNameIdentifierType(
			companyId, assertion, samlMessageContext);

		if (SamlUtil.isLDAPImportEnabled()) {
			user = importLdapUser(
				companyId, subjectNameIdentifier, subjectNameIdentifierType);
		}

		if (user == null) {
			return importUser(
				companyId, subjectNameIdentifier, subjectNameIdentifierType,
				assertion, samlMessageContext, serviceContext);
		}

		return user;
	}

	protected User addUser(
			long companyId, Map<String, String> attributesMap,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Adding user with attributes map " +
					MapUtil.toString(attributesMap));
		}

		long creatorUserId = 0;
		boolean autoPassword = false;
		String password1 = PwdGenerator.getPassword();
		String password2 = password1;
		boolean autoScreenName = false;
		String screenName = attributesMap.get("screenName");
		String emailAddress = attributesMap.get("emailAddress");
		long facebookId = 0;
		String openId = StringPool.BLANK;
		Locale locale = serviceContext.getLocale();
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

		String uuid = attributesMap.get("uuid");

		serviceContext.setUuid(uuid);

		User user = UserLocalServiceUtil.addUser(
			creatorUserId, companyId, autoPassword, password1, password2,
			autoScreenName, screenName, emailAddress, facebookId, openId,
			locale, firstName, middleName, lastName, prefixId, suffixId, male,
			birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds,
			organizationIds, roleIds, userGroupIds, sendEmail, serviceContext);

		UserLocalServiceUtil.updatePasswordReset(user.getUserId(), false);

		UserLocalServiceUtil.updateEmailAddressVerified(user.getUserId(), true);

		return user;
	}

	protected List<Attribute> getAttributes(
			Assertion assertion,
			SAMLMessageContext<Response, SAMLObject, NameID>
				samlMessageContext) {

		List<Attribute> attributes = new ArrayList<Attribute>();

		for (AttributeStatement attributeStatement :
				assertion.getAttributeStatements()) {

			attributes.addAll(attributeStatement.getAttributes());
		}

		return attributes;
	}

	protected Map<String, String> getAttributesMap(
		List<Attribute> attributes,
		SAMLMessageContext<Response, SAMLObject, NameID> samlMessageContext) {

		String peerEntityId = samlMessageContext.getPeerEntityId();

		try {
			String userAttributeMappings =
				MetadataManagerUtil.getUserAttributeMappings(peerEntityId);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Attributes mapping for " + peerEntityId + " " +
						userAttributeMappings);
			}

			if (Validator.isNotNull(userAttributeMappings)) {
				Properties userAttributeMappingsProperties =
					PropertiesUtil.load(userAttributeMappings);

				return SamlUtil.getAttributesMap(
					attributes, userAttributeMappingsProperties);
			}
		}
		catch (Exception e) {
		}

		return Collections.emptyMap();
	}

	protected String getSubjectNameIdentifier(
		long companyId, Assertion assertion,
		SAMLMessageContext<Response, SAMLObject, NameID> samlMessageContext) {

		NameID nameId = samlMessageContext.getSubjectNameIdentifier();

		return nameId.getValue();
	}

	protected String getSubjectNameIdentifierType(
		long companyId, Assertion assertion,
		SAMLMessageContext<Response, SAMLObject, NameID> samlMessageContext) {

		NameID nameId = samlMessageContext.getSubjectNameIdentifier();

		String format = nameId.getFormat();

		if (format.equals(NameIDType.EMAIL)) {
			return _SUBJECT_NAME_TYPE_EMAIL_ADDRESS;
		}

		return _SUBJECT_NAME_TYPE_SCREENNAME;
	}

	protected User getUser(
			long companyId, String subjectNameIdentifier,
			String subjectNameIdentifierType)
		throws PortalException, SystemException {

		try {
			if (subjectNameIdentifierType.endsWith(
					_SUBJECT_NAME_TYPE_EMAIL_ADDRESS)) {

				return UserLocalServiceUtil.getUserByEmailAddress(
					companyId, subjectNameIdentifier);
			}
			else if (subjectNameIdentifierType.endsWith(
						_SUBJECT_NAME_TYPE_SCREENNAME)) {

				return UserLocalServiceUtil.getUserByScreenName(
					companyId, subjectNameIdentifier);
			}
			else if (subjectNameIdentifierType.endsWith(
						_SUBJECT_NAME_TYPE_UUID)) {

				return UserLocalServiceUtil.getUserByUuidAndCompanyId(
					subjectNameIdentifier, companyId);
			}
		}
		catch (NoSuchUserException nsue) {
		}

		return null;
	}

	protected User importLdapUser(
			long companyId, String subjectNameIdentifier,
			String subjectNameIdentifierType)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Importing user from LDAP with identifier " +
					subjectNameIdentifier + " of type " +
						subjectNameIdentifierType);
		}

		User user = null;

		if (subjectNameIdentifierType.endsWith(
				_SUBJECT_NAME_TYPE_EMAIL_ADDRESS)) {

			user = PortalLDAPImporterUtil.importLDAPUser(
				companyId, subjectNameIdentifier, StringPool.BLANK);
		}
		else {
			user = PortalLDAPImporterUtil.importLDAPUser(
				companyId, StringPool.BLANK, subjectNameIdentifier);
		}

		return user;
	}

	protected User importUser(
			long companyId, String subjectNameIdentifier,
			String subjectNameIdentifierType, Assertion assertion,
			SAMLMessageContext<Response, SAMLObject, NameID>
				samlMessageContext, ServiceContext serviceContext)
		throws PortalException, SystemException {

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Importing user with identifier " + subjectNameIdentifier +
					" of type " + subjectNameIdentifierType);
		}

		User user = getUser(
			companyId, subjectNameIdentifier, subjectNameIdentifierType);

		if (user != null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Found user " + user.toString());
			}

			return user;
		}

		List<Attribute> attributes = getAttributes(
			assertion, samlMessageContext);

		Map<String, String> attributesMap = getAttributesMap(
			attributes, samlMessageContext);

		user = addUser(companyId, attributesMap, serviceContext);

		if (_log.isDebugEnabled()) {
			_log.debug("Added user " + user.toString());
		}

		return user;
	}

	private static final String _SUBJECT_NAME_TYPE_EMAIL_ADDRESS =
		"emailAddress";

	private static final String _SUBJECT_NAME_TYPE_SCREENNAME = "screenName";

	private static final String _SUBJECT_NAME_TYPE_UUID = "uuid";

	private static Log _log = LogFactoryUtil.getLog(DefaultUserResolver.class);

}