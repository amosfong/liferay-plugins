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
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.saml.DuplicateSamlIdpSsoSessionException;
import com.liferay.saml.model.SamlIdpSsoSession;
import com.liferay.saml.service.base.SamlIdpSsoSessionLocalServiceBaseImpl;

import java.util.Date;

/**
 * @author Mika Koivisto
 */
public class SamlIdpSsoSessionLocalServiceImpl
	extends SamlIdpSsoSessionLocalServiceBaseImpl {

	@Override
	public SamlIdpSsoSession addSamlIdpSsoSession(
			String samlIdpSsoSessionKey, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userLocalService.getUserById(serviceContext.getUserId());
		Date now = new Date();

		SamlIdpSsoSession samlIdpSsoSession =
			samlIdpSsoSessionPersistence.fetchBySamlIdpSsoSessionKey(
				samlIdpSsoSessionKey);

		if (samlIdpSsoSession != null) {
			throw new DuplicateSamlIdpSsoSessionException();
		}

		long samlIdpSsoSessionId = counterLocalService.increment(
			SamlIdpSsoSession.class.getName());

		samlIdpSsoSession = samlIdpSsoSessionPersistence.create(
			samlIdpSsoSessionId);

		samlIdpSsoSession.setCompanyId(serviceContext.getCompanyId());
		samlIdpSsoSession.setUserId(user.getUserId());
		samlIdpSsoSession.setUserName(user.getFullName());
		samlIdpSsoSession.setCreateDate(now);
		samlIdpSsoSession.setModifiedDate(now);
		samlIdpSsoSession.setSamlIdpSsoSessionKey(samlIdpSsoSessionKey);

		samlIdpSsoSessionPersistence.update(samlIdpSsoSession);

		return samlIdpSsoSession;
	}

	@Override
	public SamlIdpSsoSession fetchSamlIdpSso(String samlIdpSsoSessionKey)
		throws SystemException {

		return samlIdpSsoSessionPersistence.fetchBySamlIdpSsoSessionKey(
			samlIdpSsoSessionKey);
	}

	@Override
	public SamlIdpSsoSession getSamlIdpSso(String samlIdpSsoSessionKey)
		throws PortalException, SystemException {

		return samlIdpSsoSessionPersistence.findBySamlIdpSsoSessionKey(
			samlIdpSsoSessionKey);
	}

	@Override
	public SamlIdpSsoSession updateModifiedDate(String samlIdpSsoSessionKey)
		throws PortalException, SystemException {

		SamlIdpSsoSession samlIdpSsoSession =
			samlIdpSsoSessionPersistence.findBySamlIdpSsoSessionKey(
				samlIdpSsoSessionKey);

		samlIdpSsoSession.setModifiedDate(new Date());

		samlIdpSsoSessionPersistence.update(samlIdpSsoSession);

		return samlIdpSsoSession;
	}

}