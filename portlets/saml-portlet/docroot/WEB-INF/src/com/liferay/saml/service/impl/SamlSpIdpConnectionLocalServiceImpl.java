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

package com.liferay.saml.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.saml.DuplicateSamlSpIdpConnectionSamlIdpEntityIdException;
import com.liferay.saml.SamlSpIdpConnectionMetadataUrlException;
import com.liferay.saml.SamlSpIdpConnectionMetadataXmlException;
import com.liferay.saml.SamlSpIdpConnectionSamlIdpEntityIdException;
import com.liferay.saml.model.SamlSpIdpConnection;
import com.liferay.saml.service.base.SamlSpIdpConnectionLocalServiceBaseImpl;
import com.liferay.saml.util.MetadataUtil;

import java.io.InputStream;

import java.util.Date;
import java.util.List;

/**
 * @author Mika Koivisto
 */
public class SamlSpIdpConnectionLocalServiceImpl
	extends SamlSpIdpConnectionLocalServiceBaseImpl {

	@Override
	public SamlSpIdpConnection addSamlSpIdpConnection(
			String samlIdpEntityId, boolean assertionSignatureRequired,
			long clockSkew, boolean enabled, boolean ldapImportEnabled,
			String metadataUrl, InputStream metadataXmlInputStream, String name,
			String nameIdFormat, boolean signAuthnRequest,
			String userAttributeMappings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Date now = new Date();

		if (Validator.isNull(samlIdpEntityId)) {
			throw new SamlSpIdpConnectionSamlIdpEntityIdException();
		}

		if (samlSpIdpConnectionPersistence.fetchByC_SIEI(
				serviceContext.getCompanyId(), samlIdpEntityId) != null) {

			throw new DuplicateSamlSpIdpConnectionSamlIdpEntityIdException();
		}

		long samlSpIdpConnectionId = counterLocalService.increment(
			SamlSpIdpConnection.class.getName());

		SamlSpIdpConnection samlSpIdpConnection =
			samlSpIdpConnectionPersistence.create(samlSpIdpConnectionId);

		samlSpIdpConnection.setCompanyId(serviceContext.getCompanyId());
		samlSpIdpConnection.setCreateDate(now);
		samlSpIdpConnection.setModifiedDate(now);
		samlSpIdpConnection.setSamlIdpEntityId(samlIdpEntityId);
		samlSpIdpConnection.setAssertionSignatureRequired(
			assertionSignatureRequired);
		samlSpIdpConnection.setClockSkew(clockSkew);
		samlSpIdpConnection.setEnabled(enabled);
		samlSpIdpConnection.setLdapImportEnabled(ldapImportEnabled);
		samlSpIdpConnection.setMetadataUpdatedDate(now);

		if ((metadataXmlInputStream == null) &&
			Validator.isNotNull(metadataUrl)) {

			samlSpIdpConnection.setMetadataUrl(metadataUrl);

			metadataXmlInputStream = MetadataUtil.getMetadata(metadataUrl);
		}

		if (metadataXmlInputStream == null) {
			throw new SamlSpIdpConnectionMetadataUrlException();
		}

		samlSpIdpConnection.setMetadataXml(
			getMetadataXml(metadataXmlInputStream, samlIdpEntityId));
		samlSpIdpConnection.setName(name);
		samlSpIdpConnection.setNameIdFormat(nameIdFormat);
		samlSpIdpConnection.setSamlIdpEntityId(samlIdpEntityId);
		samlSpIdpConnection.setSignAuthnRequest(signAuthnRequest);
		samlSpIdpConnection.setUserAttributeMappings(userAttributeMappings);

		samlSpIdpConnectionPersistence.update(samlSpIdpConnection);

		return samlSpIdpConnection;
	}

	@Override
	public SamlSpIdpConnection getSamlSpIdpConnection(
			long companyId, String samlIdpEntityId)
		throws PortalException, SystemException {

		return samlSpIdpConnectionPersistence.findByC_SIEI(
			companyId, samlIdpEntityId);
	}

	@Override
	public List<SamlSpIdpConnection> getSamlSpIdpConnections(long companyId)
		throws SystemException {

		return samlSpIdpConnectionPersistence.findByCompanyId(companyId);
	}

	@Override
	public List<SamlSpIdpConnection> getSamlSpIdpConnections(
			long companyId, int start, int end)
		throws SystemException {

		return samlSpIdpConnectionPersistence.findByCompanyId(
			companyId, start, end);
	}

	@Override
	public List<SamlSpIdpConnection> getSamlSpIdpConnections(
			long companyId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return samlSpIdpConnectionPersistence.findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	@Override
	public int getSamlSpIdpConnectionsCount(long companyId)
		throws SystemException {

		return samlSpIdpConnectionPersistence.countByCompanyId(companyId);
	}

	@Override
	public void updateMetadata(long samlSpIdpConnectionId)
		throws PortalException, SystemException {

		SamlSpIdpConnection samlSpIdpConnection =
			samlSpIdpConnectionPersistence.findByPrimaryKey(
				samlSpIdpConnectionId);

		String metadataUrl = samlSpIdpConnection.getMetadataUrl();

		if (Validator.isNull(metadataUrl)) {
			return;
		}

		InputStream metadataXmlInputStream = MetadataUtil.getMetadata(
			metadataUrl);

		if (metadataXmlInputStream == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get metadata from " + metadataUrl);
			}

			return;
		}

		String metadataXml = StringPool.BLANK;

		try {
			metadataXml = MetadataUtil.parseMetadataXml(
				metadataXmlInputStream,
				samlSpIdpConnection.getSamlIdpEntityId());
		}
		catch (Exception e) {
			_log.warn("Unable to parse metadata", e);
		}

		if (Validator.isNotNull(metadataXml)) {
			samlSpIdpConnection.setMetadataUpdatedDate(new Date());
			samlSpIdpConnection.setMetadataXml(metadataXml);

			samlSpIdpConnectionPersistence.update(samlSpIdpConnection);
		}
	}

	@Override
	public SamlSpIdpConnection updateSamlSpIdpConnection(
			long samlSpIdpConnectionId, String samlIdpEntityId,
			boolean assertionSignatureRequired, long clockSkew, boolean enabled,
			boolean ldapImportEnabled, String metadataUrl,
			InputStream metadataXmlInputStream, String name,
			String nameIdFormat, boolean signAuthnRequest,
			String userAttributeMappings, ServiceContext serviceContext)
		throws PortalException, SystemException {

		Date now = new Date();

		if (Validator.isNull(samlIdpEntityId)) {
			throw new SamlSpIdpConnectionSamlIdpEntityIdException();
		}

		SamlSpIdpConnection samlSpIdpConnection =
			samlSpIdpConnectionPersistence.findByPrimaryKey(
				samlSpIdpConnectionId);

		if (!samlIdpEntityId.equals(samlSpIdpConnection.getSamlIdpEntityId())) {
			if (samlSpIdpConnectionPersistence.fetchByC_SIEI(
					serviceContext.getCompanyId(), samlIdpEntityId) != null) {

				throw
					new DuplicateSamlSpIdpConnectionSamlIdpEntityIdException();
			}
		}

		samlSpIdpConnection.setCompanyId(serviceContext.getCompanyId());
		samlSpIdpConnection.setCreateDate(now);
		samlSpIdpConnection.setModifiedDate(now);
		samlSpIdpConnection.setSamlIdpEntityId(samlIdpEntityId);
		samlSpIdpConnection.setAssertionSignatureRequired(
			assertionSignatureRequired);
		samlSpIdpConnection.setClockSkew(clockSkew);
		samlSpIdpConnection.setEnabled(enabled);
		samlSpIdpConnection.setLdapImportEnabled(ldapImportEnabled);
		samlSpIdpConnection.setMetadataUpdatedDate(now);

		if ((metadataXmlInputStream == null) &&
			Validator.isNotNull(metadataUrl)) {

			samlSpIdpConnection.setMetadataUrl(metadataUrl);

			metadataXmlInputStream = MetadataUtil.getMetadata(metadataUrl);
		}

		String metadataXml = StringPool.BLANK;

		if (metadataXmlInputStream != null) {
			metadataXml = getMetadataXml(
				metadataXmlInputStream, samlIdpEntityId);
		}

		if (Validator.isNotNull(metadataXml)) {
			samlSpIdpConnection.setMetadataUpdatedDate(now);
			samlSpIdpConnection.setMetadataXml(metadataXml);
		}

		samlSpIdpConnection.setName(name);
		samlSpIdpConnection.setNameIdFormat(nameIdFormat);
		samlSpIdpConnection.setSamlIdpEntityId(samlIdpEntityId);
		samlSpIdpConnection.setSignAuthnRequest(signAuthnRequest);
		samlSpIdpConnection.setUserAttributeMappings(userAttributeMappings);

		samlSpIdpConnectionPersistence.update(samlSpIdpConnection);

		return samlSpIdpConnection;
	}

	protected String getMetadataXml(
			InputStream metadataXmlInputStream, String samlIdpEntityId)
		throws PortalException {

		String metadataXml = StringPool.BLANK;

		try {
			metadataXml = MetadataUtil.parseMetadataXml(
				metadataXmlInputStream, samlIdpEntityId);
		}
		catch (Exception e) {
			throw new SamlSpIdpConnectionMetadataXmlException(e);
		}

		if (Validator.isNull(metadataXml)) {
			throw new SamlSpIdpConnectionSamlIdpEntityIdException();
		}

		return metadataXml;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SamlSpIdpConnectionLocalServiceImpl.class);

}