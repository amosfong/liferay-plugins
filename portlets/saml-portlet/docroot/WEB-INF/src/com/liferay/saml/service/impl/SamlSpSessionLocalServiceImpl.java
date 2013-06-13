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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.saml.NoSuchSpSessionException;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.service.base.SamlSpSessionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Mika Koivisto
 */
public class SamlSpSessionLocalServiceImpl
	extends SamlSpSessionLocalServiceBaseImpl {

	@Override
	public SamlSpSession addSamlSpSession(
			String samlSpSessionKey, String assertionXml, String jSessionId,
			String nameIdFormat, String nameIdValue, String sessionIndex,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userLocalService.getUserById(serviceContext.getUserId());
		Date now = new Date();

		long samlSpSessionId = counterLocalService.increment(
			SamlSpSession.class.getName());

		SamlSpSession samlSpSession = samlSpSessionPersistence.create(
			samlSpSessionId);

		samlSpSession.setCompanyId(serviceContext.getCompanyId());
		samlSpSession.setUserId(user.getUserId());
		samlSpSession.setUserName(user.getFullName());
		samlSpSession.setCreateDate(now);
		samlSpSession.setModifiedDate(now);
		samlSpSession.setSamlSpSessionKey(samlSpSessionKey);
		samlSpSession.setAssertionXml(assertionXml);
		samlSpSession.setJSessionId(jSessionId);
		samlSpSession.setNameIdFormat(nameIdFormat);
		samlSpSession.setNameIdValue(nameIdValue);
		samlSpSession.setSessionIndex(sessionIndex);
		samlSpSession.setTerminated(false);

		samlSpSessionPersistence.update(samlSpSession);

		return samlSpSession;
	}

	@Override
	public SamlSpSession fetchSamlSpSessionByJSessionId(String jSessionId)
		throws SystemException {

		return samlSpSessionPersistence.fetchByJSessionId(jSessionId);
	}

	@Override
	public SamlSpSession fetchSamlSpSessionBySamlSpSessionKey(
			String samlSpSessionKey)
		throws SystemException {

		return samlSpSessionPersistence.fetchBySamlSpSessionKey(
			samlSpSessionKey);
	}

	@Override
	public SamlSpSession fetchSamlSpSessionBySessionIndex(String sessionIndex)
		throws SystemException {

		if (Validator.isNull(sessionIndex)) {
			return null;
		}

		return samlSpSessionPersistence.fetchBySessionIndex(sessionIndex);
	}

	@Override
	public SamlSpSession getSamlSpSessionByJSessionId(String jSessionId)
		throws PortalException, SystemException {

		return samlSpSessionPersistence.findByJSessionId(jSessionId);
	}

	@Override
	public SamlSpSession getSamlSpSessionBySamlSpSessionKey(
			String samlSpSessionKey)
		throws PortalException, SystemException {

		return samlSpSessionPersistence.findBySamlSpSessionKey(
			samlSpSessionKey);
	}

	@Override
	public SamlSpSession getSamlSpSessionBySessionIndex(String sessionIndex)
		throws PortalException, SystemException {

		if (Validator.isNull(sessionIndex)) {
			throw new NoSuchSpSessionException();
		}

		return samlSpSessionPersistence.findBySessionIndex(sessionIndex);
	}

	@Override
	public List<SamlSpSession> getSamlSpSessions(String nameIdValue)
		throws SystemException {

		return samlSpSessionPersistence.findByNameIdValue(nameIdValue);
	}

	@Override
	public SamlSpSession updateSamlSpSession(
			long samlSpSessionId, String samlSpSessionKey, String assertionXml,
			String jSessionId, String nameIdFormat, String nameIdValue,
			String sessionIndex, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userLocalService.getUserById(serviceContext.getUserId());

		SamlSpSession samlSpSession = samlSpSessionPersistence.findByPrimaryKey(
			samlSpSessionId);

		samlSpSession.setCompanyId(serviceContext.getCompanyId());
		samlSpSession.setUserId(user.getUserId());
		samlSpSession.setUserName(user.getFullName());
		samlSpSession.setModifiedDate(new Date());
		samlSpSession.setSamlSpSessionKey(samlSpSessionKey);
		samlSpSession.setAssertionXml(assertionXml);
		samlSpSession.setJSessionId(jSessionId);
		samlSpSession.setNameIdFormat(nameIdFormat);
		samlSpSession.setNameIdValue(nameIdValue);
		samlSpSession.setSessionIndex(sessionIndex);

		return samlSpSession;
	}

}