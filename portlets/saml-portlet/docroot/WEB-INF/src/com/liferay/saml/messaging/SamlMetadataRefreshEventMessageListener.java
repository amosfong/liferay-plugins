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

package com.liferay.saml.messaging;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.saml.model.SamlIdpSpConnection;
import com.liferay.saml.service.SamlIdpSpConnectionLocalServiceUtil;
import com.liferay.saml.util.SamlUtil;

import java.util.List;

/**
 * @author Mika Koivisto
 */
public class SamlMetadataRefreshEventMessageListener
	extends BaseMessageListener {

	protected void doReceive(Message message) throws Exception {
		List<Company> companies = CompanyLocalServiceUtil.getCompanies(false);

		for (Company company : companies) {
			if (!company.isActive()) {
				continue;
			}

			Long companyId = CompanyThreadLocal.getCompanyId();

			CompanyThreadLocal.setCompanyId(company.getCompanyId());

			try {
				if (!SamlUtil.isEnabled()) {
					continue;
				}

				if (SamlUtil.isRoleIdp()) {
					try {
						refreshSpMetadata(company.getCompanyId());
					}
					catch (SystemException se) {
						_log.warn(
							"Unable to refresh metadatas for company " +
								company.getCompanyId(), se);
					}
				}
			}
			finally {
				CompanyThreadLocal.setCompanyId(companyId);
			}
		}
	}

	protected void refreshSpMetadata(long companyId) throws SystemException {
		List<SamlIdpSpConnection> samlIdpSpConnections =
			SamlIdpSpConnectionLocalServiceUtil.getSamlIdpSpConnections(
				companyId);

		for (SamlIdpSpConnection samlIdpSpConnection : samlIdpSpConnections) {
			if (!samlIdpSpConnection.isEnabled() ||
				Validator.isNull(samlIdpSpConnection.getMetadataUrl())) {

				continue;
			}

			try {
				SamlIdpSpConnectionLocalServiceUtil.updateMetadata(
					samlIdpSpConnection.getSamlIdpSpConnectionId());
			}
			catch (Exception e) {
				_log.warn(
					"Unable to refresh metadata for " +
						samlIdpSpConnection.getSamlSpEntityId(), e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		SamlMetadataRefreshEventMessageListener.class);

}