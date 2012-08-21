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

package com.liferay.saml.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.saml.model.SamlSpIdpConnection;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing SamlSpIdpConnection in entity cache.
 *
 * @author Mika Koivisto
 * @see SamlSpIdpConnection
 * @generated
 */
public class SamlSpIdpConnectionCacheModel implements CacheModel<SamlSpIdpConnection>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{samlSpIdpConnectionId=");
		sb.append(samlSpIdpConnectionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", samlIdpEntityId=");
		sb.append(samlIdpEntityId);
		sb.append(", assertionSignatureRequired=");
		sb.append(assertionSignatureRequired);
		sb.append(", clockSkew=");
		sb.append(clockSkew);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", ldapImportEnabled=");
		sb.append(ldapImportEnabled);
		sb.append(", metadataUrl=");
		sb.append(metadataUrl);
		sb.append(", metadataXml=");
		sb.append(metadataXml);
		sb.append(", metadataUpdatedDate=");
		sb.append(metadataUpdatedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", nameIdFormat=");
		sb.append(nameIdFormat);
		sb.append(", signAuthnRequest=");
		sb.append(signAuthnRequest);
		sb.append(", userAttributeMappings=");
		sb.append(userAttributeMappings);
		sb.append("}");

		return sb.toString();
	}

	public SamlSpIdpConnection toEntityModel() {
		SamlSpIdpConnectionImpl samlSpIdpConnectionImpl = new SamlSpIdpConnectionImpl();

		samlSpIdpConnectionImpl.setSamlSpIdpConnectionId(samlSpIdpConnectionId);
		samlSpIdpConnectionImpl.setCompanyId(companyId);
		samlSpIdpConnectionImpl.setUserId(userId);

		if (userName == null) {
			samlSpIdpConnectionImpl.setUserName(StringPool.BLANK);
		}
		else {
			samlSpIdpConnectionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			samlSpIdpConnectionImpl.setCreateDate(null);
		}
		else {
			samlSpIdpConnectionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			samlSpIdpConnectionImpl.setModifiedDate(null);
		}
		else {
			samlSpIdpConnectionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (samlIdpEntityId == null) {
			samlSpIdpConnectionImpl.setSamlIdpEntityId(StringPool.BLANK);
		}
		else {
			samlSpIdpConnectionImpl.setSamlIdpEntityId(samlIdpEntityId);
		}

		samlSpIdpConnectionImpl.setAssertionSignatureRequired(assertionSignatureRequired);
		samlSpIdpConnectionImpl.setClockSkew(clockSkew);
		samlSpIdpConnectionImpl.setEnabled(enabled);
		samlSpIdpConnectionImpl.setLdapImportEnabled(ldapImportEnabled);

		if (metadataUrl == null) {
			samlSpIdpConnectionImpl.setMetadataUrl(StringPool.BLANK);
		}
		else {
			samlSpIdpConnectionImpl.setMetadataUrl(metadataUrl);
		}

		if (metadataXml == null) {
			samlSpIdpConnectionImpl.setMetadataXml(StringPool.BLANK);
		}
		else {
			samlSpIdpConnectionImpl.setMetadataXml(metadataXml);
		}

		if (metadataUpdatedDate == Long.MIN_VALUE) {
			samlSpIdpConnectionImpl.setMetadataUpdatedDate(null);
		}
		else {
			samlSpIdpConnectionImpl.setMetadataUpdatedDate(new Date(
					metadataUpdatedDate));
		}

		if (name == null) {
			samlSpIdpConnectionImpl.setName(StringPool.BLANK);
		}
		else {
			samlSpIdpConnectionImpl.setName(name);
		}

		if (nameIdFormat == null) {
			samlSpIdpConnectionImpl.setNameIdFormat(StringPool.BLANK);
		}
		else {
			samlSpIdpConnectionImpl.setNameIdFormat(nameIdFormat);
		}

		samlSpIdpConnectionImpl.setSignAuthnRequest(signAuthnRequest);

		if (userAttributeMappings == null) {
			samlSpIdpConnectionImpl.setUserAttributeMappings(StringPool.BLANK);
		}
		else {
			samlSpIdpConnectionImpl.setUserAttributeMappings(userAttributeMappings);
		}

		samlSpIdpConnectionImpl.resetOriginalValues();

		return samlSpIdpConnectionImpl;
	}

	public long samlSpIdpConnectionId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String samlIdpEntityId;
	public boolean assertionSignatureRequired;
	public long clockSkew;
	public boolean enabled;
	public boolean ldapImportEnabled;
	public String metadataUrl;
	public String metadataXml;
	public long metadataUpdatedDate;
	public String name;
	public String nameIdFormat;
	public boolean signAuthnRequest;
	public String userAttributeMappings;
}