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
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.service.base.SamlSpSessionLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * @author Mika Koivisto
 */
public class SamlSpSessionLocalServiceImpl
	extends SamlSpSessionLocalServiceBaseImpl {

	public SamlSpSession addSamlSpSession(
			String jSessionId, String nameIdFormat, String nameIdValue,
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
		samlSpSession.setJSessionId(jSessionId);
		samlSpSession.setNameIdFormat(nameIdFormat);
		samlSpSession.setNameIdValue(nameIdValue);
		samlSpSession.setTerminated(false);

		samlSpSessionPersistence.update(samlSpSession);

		return samlSpSession;
	}

	public SamlSpSession fetchSamlSpSession(String jSessionId)
		throws SystemException {

		return samlSpSessionPersistence.fetchByJSessionId(jSessionId);
	}

	public SamlSpSession getSamlSpSession(String jSessionId)
		throws PortalException, SystemException {

		return samlSpSessionPersistence.findByJSessionId(jSessionId);
	}

	public List<SamlSpSession> getSamlSpSessions(String nameIdValue)
		throws SystemException {

		return samlSpSessionPersistence.findByNameIdValue(nameIdValue);
	}

}