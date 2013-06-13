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
import com.liferay.portal.service.ServiceContext;
import com.liferay.saml.model.SamlSpAuthRequest;
import com.liferay.saml.service.base.SamlSpAuthRequestLocalServiceBaseImpl;

import java.util.Date;

/**
 * @author Mika Koivisto
 */
public class SamlSpAuthRequestLocalServiceImpl
	extends SamlSpAuthRequestLocalServiceBaseImpl {

	@Override
	public SamlSpAuthRequest addSamlSpAuthRequest(
			String samlIdpEntityId, String samlSpAuthRequestKey,
			ServiceContext serviceContext)
		throws SystemException {

		long samlSpAuthRequestId = counterLocalService.increment(
			SamlSpAuthRequest.class.getName());

		SamlSpAuthRequest samlSpAuthRequest =
			samlSpAuthRequestPersistence.create(samlSpAuthRequestId);

		samlSpAuthRequest.setCompanyId(serviceContext.getCompanyId());
		samlSpAuthRequest.setCreateDate(new Date());
		samlSpAuthRequest.setSamlIdpEntityId(samlIdpEntityId);
		samlSpAuthRequest.setSamlSpAuthRequestKey(samlSpAuthRequestKey);

		samlSpAuthRequestPersistence.update(samlSpAuthRequest);

		return samlSpAuthRequest;
	}

	@Override
	public SamlSpAuthRequest fetchSamlSpAuthRequest(
			String samlIdpEntityId, String samlSpAuthRequestKey)
		throws SystemException {

		return samlSpAuthRequestPersistence.fetchBySIEI_SSARK(
			samlIdpEntityId, samlSpAuthRequestKey);
	}

	@Override
	public SamlSpAuthRequest getSamlSpAuthRequest(
			String samlIdpEntityId, String samlSpAuthRequestKey)
		throws PortalException, SystemException {

		return samlSpAuthRequestPersistence.findBySIEI_SSARK(
			samlIdpEntityId, samlSpAuthRequestKey);
	}

}