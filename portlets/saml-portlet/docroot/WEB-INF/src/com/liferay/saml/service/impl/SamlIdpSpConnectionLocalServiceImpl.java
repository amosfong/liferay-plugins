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

package com.liferay.saml.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.saml.DuplicateSamlIdpSpConnectionSamlSpEntityIdException;
import com.liferay.saml.SamlIdpSpConnectionMetadataUrlException;
import com.liferay.saml.SamlIdpSpConnectionMetadataXmlException;
import com.liferay.saml.SamlIdpSpConnectionSamlSpEntityIdException;
import com.liferay.saml.model.SamlIdpSpConnection;
import com.liferay.saml.service.base.SamlIdpSpConnectionLocalServiceBaseImpl;
import com.liferay.saml.util.MetadataUtil;

import java.io.InputStream;

import java.util.Date;
import java.util.List;

/**
 * @author Mika Koivisto
 */
public class SamlIdpSpConnectionLocalServiceImpl
	extends SamlIdpSpConnectionLocalServiceBaseImpl {

	public SamlIdpSpConnection addSamlIdpSpConnection(
		String samlSpEntityId, String name, boolean enabled,
		int assertionLifetime, String attributeNames, boolean attributesEnabled,
		boolean attributesNamespaceEnabled, String nameIdAttribute,
		String nameIdFormat, String metadataUrl, InputStream is,
		ServiceContext serviceContext)
						throws PortalException, SystemException {

		long companyId = serviceContext.getCompanyId();
		long samlIdpSpConnectionId = counterLocalService.increment(
			SamlIdpSpConnection.class.getName());

		if (Validator.isNull(samlSpEntityId)) {
			throw new SamlIdpSpConnectionSamlSpEntityIdException();
		}

		if (samlIdpSpConnectionPersistence.fetchByC_SSEI(
				companyId, samlSpEntityId) != null) {

			throw new DuplicateSamlIdpSpConnectionSamlSpEntityIdException();
		}

		Date now = new Date();

		SamlIdpSpConnection samlIdpSpConnection =
			samlIdpSpConnectionPersistence.create(samlIdpSpConnectionId);

		samlIdpSpConnection.setCompanyId(companyId);
		samlIdpSpConnection.setCreateDate(now);
		samlIdpSpConnection.setModifiedDate(now);

		samlIdpSpConnection.setAssertionLifetime(assertionLifetime);
		samlIdpSpConnection.setAttributeNames(attributeNames);
		samlIdpSpConnection.setAttributesEnabled(attributesEnabled);
		samlIdpSpConnection.setAttributesNamespaceEnabled(
			attributesNamespaceEnabled);
		samlIdpSpConnection.setEnabled(enabled);
		samlIdpSpConnection.setName(name);
		samlIdpSpConnection.setNameIdAttribute(nameIdAttribute);
		samlIdpSpConnection.setNameIdFormat(nameIdFormat);
		samlIdpSpConnection.setSamlSpEntityId(samlSpEntityId);

		if ((is == null) && Validator.isNotNull(metadataUrl)) {
			samlIdpSpConnection.setMetadataUrl(metadataUrl);

			is = MetadataUtil.fetchMetadata(metadataUrl);
		}

		if (is == null) {
			throw new SamlIdpSpConnectionMetadataUrlException();
		}

		String metadataXml = StringPool.BLANK;

		try {
			metadataXml = MetadataUtil.parseMetadataXml(is, samlSpEntityId);
		}
		catch (Exception e) {
			throw new SamlIdpSpConnectionMetadataXmlException(e);
		}

		if (Validator.isNull(metadataXml)) {
			throw new SamlIdpSpConnectionSamlSpEntityIdException();
		}

		samlIdpSpConnection.setMetadataXml(metadataXml);
		samlIdpSpConnection.setMetadataUpdatedDate(now);

		samlIdpSpConnectionPersistence.update(samlIdpSpConnection, false);

		return samlIdpSpConnection;
	}

	public int countByCompanyId(long companyId) throws SystemException {
		return samlIdpSpConnectionPersistence.countByCompanyId(companyId);
	}

	public SamlIdpSpConnection findByC_SSEI(
			long companyId, String samlSpEntityId)
		throws PortalException, SystemException {

		return samlIdpSpConnectionPersistence.findByC_SSEI(
			companyId, samlSpEntityId);
	}

	public List<SamlIdpSpConnection> findByCompanyId(long companyId)
		throws SystemException {

		return samlIdpSpConnectionPersistence.findByCompanyId(companyId);
	}

	public List<SamlIdpSpConnection> findByCompanyId(
			long companyId, int start, int end)
		throws SystemException {

		return samlIdpSpConnectionPersistence.findByCompanyId(
			companyId, start, end);
	}

	public List<SamlIdpSpConnection> findByCompanyId(
			long companyId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return samlIdpSpConnectionPersistence.findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	public void refreshMetadata(long samlIdpSpConnectionId)
		throws PortalException, SystemException {

		SamlIdpSpConnection samlIdpSpConnection =
			samlIdpSpConnectionPersistence.findByPrimaryKey(
				samlIdpSpConnectionId);

		String metadataUrl = samlIdpSpConnection.getMetadataUrl();

		if (Validator.isNull(metadataUrl)) {
			return;
		}

		InputStream is = MetadataUtil.fetchMetadata(metadataUrl);

		if (is == null) {
			_log.warn("Unable to fetch metadata from " + metadataUrl);

			return;
		}

		String metadataXml = StringPool.BLANK;

		try {
			metadataXml = MetadataUtil.parseMetadataXml(
				is, samlIdpSpConnection.getSamlSpEntityId());
		}
		catch (Exception e) {
			_log.warn("Unable to parse metadata", e);
		}

		if (Validator.isNotNull(metadataXml)) {
			samlIdpSpConnection.setMetadataXml(metadataXml);
			samlIdpSpConnection.setMetadataUpdatedDate(new Date());

			samlIdpSpConnectionPersistence.update(samlIdpSpConnection, false);
		}
	}

	public SamlIdpSpConnection updateSamlIdpSpConnection(
			long samlIdpSpConnectionId, String samlSpEntityId, String name,
			int assertionLifetime, String attributeNames,
			boolean attributesEnabled, boolean attributesNamespaceEnabled,
			boolean enabled, String nameIdAttribute, String nameIdFormat,
			String metadataUrl, InputStream is, ServiceContext serviceContext)
		throws PortalException, SystemException {

		SamlIdpSpConnection samlIdpSpConnection =
			samlIdpSpConnectionPersistence.fetchByPrimaryKey(
				samlIdpSpConnectionId);

		long companyId = serviceContext.getCompanyId();

		if (Validator.isNull(samlSpEntityId)) {
			throw new SamlIdpSpConnectionSamlSpEntityIdException();
		}

		if (!samlSpEntityId.equals(samlIdpSpConnection.getSamlSpEntityId())) {
			if (samlIdpSpConnectionPersistence.fetchByC_SSEI(
				companyId, samlSpEntityId) != null) {

				throw new DuplicateSamlIdpSpConnectionSamlSpEntityIdException();
			}
		}

		Date now = new Date();

		samlIdpSpConnection.setModifiedDate(now);

		samlIdpSpConnection.setAssertionLifetime(assertionLifetime);
		samlIdpSpConnection.setAttributeNames(attributeNames);
		samlIdpSpConnection.setAttributesEnabled(attributesEnabled);
		samlIdpSpConnection.setAttributesNamespaceEnabled(
			attributesNamespaceEnabled);
		samlIdpSpConnection.setEnabled(enabled);
		samlIdpSpConnection.setName(name);
		samlIdpSpConnection.setNameIdAttribute(nameIdAttribute);
		samlIdpSpConnection.setNameIdFormat(nameIdFormat);
		samlIdpSpConnection.setSamlSpEntityId(samlSpEntityId);

		if ((is == null) && Validator.isNotNull(metadataUrl)) {
			samlIdpSpConnection.setMetadataUrl(metadataUrl);

			is = MetadataUtil.fetchMetadata(metadataUrl);
		}

		String metadataXml = StringPool.BLANK;

		if (is != null) {
			try {
				metadataXml = MetadataUtil.parseMetadataXml(is, samlSpEntityId);
			}
			catch (Exception e) {
				throw new SamlIdpSpConnectionMetadataXmlException(e);
			}

			if (Validator.isNull(metadataXml)) {
				throw new SamlIdpSpConnectionSamlSpEntityIdException();
			}
		}

		if (Validator.isNotNull(metadataXml)) {
			samlIdpSpConnection.setMetadataXml(metadataXml);
			samlIdpSpConnection.setMetadataUpdatedDate(now);
		}

		samlIdpSpConnectionPersistence.update(samlIdpSpConnection, false);

		return samlIdpSpConnection;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SamlIdpSpConnectionLocalServiceImpl.class);

}