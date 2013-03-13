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
import com.liferay.saml.model.SamlSpIdpConnection;
import com.liferay.saml.service.SamlIdpSpConnectionLocalServiceUtil;
import com.liferay.saml.service.SamlSpIdpConnectionLocalServiceUtil;
import com.liferay.saml.util.SamlUtil;

import java.util.List;

/**
 * @author Mika Koivisto
 */
public class CheckSamlMetadataMessageListener extends BaseMessageListener {

	@Override
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

				try {
					if (SamlUtil.isRoleIdp()) {
						updateSpMetadata(company.getCompanyId());
					}
					else if (SamlUtil.isRoleSp()) {
						updateIdpMetadata(company.getCompanyId());
					}
				}
				catch (SystemException se) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to refresh metadata for company " +
								company.getCompanyId(), se);
					}
				}
			}
			finally {
				CompanyThreadLocal.setCompanyId(companyId);
			}
		}
	}

	protected void updateIdpMetadata(long companyId) throws SystemException {
		List<SamlSpIdpConnection> samlSpIdpConnections =
			SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnections(
				companyId);

		for (SamlSpIdpConnection samlSpIdpConnection : samlSpIdpConnections) {
			if (!samlSpIdpConnection.isEnabled() ||
				Validator.isNull(samlSpIdpConnection.getMetadataUrl())) {

				continue;
			}

			try {
				SamlSpIdpConnectionLocalServiceUtil.updateMetadata(
					samlSpIdpConnection.getSamlSpIdpConnectionId());
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to refresh IdP metadata for " +
							samlSpIdpConnection.getSamlIdpEntityId(),
						e);
				}
			}
		}
	}

	protected void updateSpMetadata(long companyId) throws SystemException {
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
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to refresh SP metadata for " +
							samlIdpSpConnection.getSamlSpEntityId(),
						e);
				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CheckSamlMetadataMessageListener.class);

}