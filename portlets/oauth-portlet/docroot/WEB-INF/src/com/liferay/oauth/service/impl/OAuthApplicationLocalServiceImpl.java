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

package com.liferay.oauth.service.impl;

import com.liferay.oauth.OAuthApplicationCallbackURIException;
import com.liferay.oauth.OAuthApplicationNameException;
import com.liferay.oauth.OAuthApplicationWebsiteURLException;
import com.liferay.oauth.model.OAuthApplication;
import com.liferay.oauth.model.OAuthUser;
import com.liferay.oauth.service.base.OAuthApplicationLocalServiceBaseImpl;
import com.liferay.oauth.util.OAuthUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.io.InputStream;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class OAuthApplicationLocalServiceImpl
	extends OAuthApplicationLocalServiceBaseImpl {

	public OAuthApplication addOAuthApplication(
			long userId, String name, String description, int accessLevel,
			String callbackURI, String websiteURL,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// OAuth application

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(name, callbackURI, websiteURL);

		long oAuthApplicationId = counterLocalService.increment();

		OAuthApplication oAuthApplication = oAuthApplicationPersistence.create(
			oAuthApplicationId);

		oAuthApplication.setCompanyId(user.getCompanyId());
		oAuthApplication.setUserId(user.getUserId());
		oAuthApplication.setUserName(user.getFullName());
		oAuthApplication.setCreateDate(serviceContext.getCreateDate(now));
		oAuthApplication.setModifiedDate(serviceContext.getModifiedDate(now));
		oAuthApplication.setName(name);
		oAuthApplication.setDescription(description);

		String consumerKey = serviceContext.getUuid();

		if (Validator.isNull(consumerKey)) {
			consumerKey = PortalUUIDUtil.generate();
		}

		oAuthApplication.setConsumerKey(consumerKey);

		oAuthApplication.setConsumerSecret(
			OAuthUtil.randomizeToken(consumerKey));
		oAuthApplication.setAccessLevel(accessLevel);
		oAuthApplication.setCallbackURI(callbackURI);
		oAuthApplication.setWebsiteURL(websiteURL);

		oAuthApplicationPersistence.update(oAuthApplication, false);

		// Resources

		resourceLocalService.addModelResources(
			oAuthApplication, serviceContext);

		return oAuthApplication;
	}

	public void deleteLogo(long oAuthApplicationId)
		throws PortalException, SystemException {

		OAuthApplication oAuthApplication =
			oAuthApplicationPersistence.findByPrimaryKey(oAuthApplicationId);

		long logoId = oAuthApplication.getLogoId();

		if (logoId > 0) {
			oAuthApplication.setLogoId(0);

			oAuthApplicationPersistence.update(oAuthApplication, false);

			imageLocalService.deleteImage(logoId);
		}
	}

	@Override
	public OAuthApplication deleteOAuthApplication(long oAuthApplicationId)
		throws PortalException, SystemException {

		OAuthApplication oAuthApplication =
			oAuthApplicationPersistence.findByPrimaryKey(oAuthApplicationId);

		return deleteOAuthApplication(oAuthApplication);
	}

	@Override
	public OAuthApplication deleteOAuthApplication(
			OAuthApplication oAuthApplication)
		throws PortalException, SystemException {

		// OAuth application

		oAuthApplicationPersistence.remove(oAuthApplication);

		// OAuth users

		List<OAuthUser> oAuthUsers =
			oAuthUserPersistence.findByOAuthApplicationId(
				oAuthApplication.getOAuthApplicationId());

		for (OAuthUser oAuthUser : oAuthUsers) {
			oAuthUserPersistence.remove(oAuthUser);
		}

		// Resources

		resourceLocalService.deleteResource(
			oAuthApplication, ResourceConstants.SCOPE_INDIVIDUAL);

		// Image

		imageLocalService.deleteImage(oAuthApplication.getLogoId());

		return oAuthApplication;
	}

	public OAuthApplication fetchOAuthApplication(String consumerKey)
		throws SystemException {

		return oAuthApplicationPersistence.fetchByConsumerKey(consumerKey);
	}

	public OAuthApplication getOAuthApplication(String consumerKey)
		throws PortalException, SystemException {

		return oAuthApplicationPersistence.findByConsumerKey(consumerKey);
	}

	public List<OAuthApplication> getOAuthApplications(
			long companyId, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		return oAuthApplicationPersistence.findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	public int getOAuthApplicationsCount(long companyId)
		throws SystemException {

		return oAuthApplicationPersistence.countByCompanyId(companyId);
	}

	public List<OAuthApplication> search(
			long companyId, String keywords,
			LinkedHashMap<String, Object> params, int start, int end,
			OrderByComparator orderByComparator)
		throws SystemException {

		keywords = CustomSQLUtil.keywords(keywords)[0];

		if ((params != null) && params.containsKey("userId")) {
			long userId = (Long)params.get("userId");

			if (Validator.isNotNull(keywords)) {
				return oAuthApplicationPersistence.findByU_N(
					userId, keywords, start, end, orderByComparator);
			}

			return oAuthApplicationPersistence.findByUserId(
				userId, start, end, orderByComparator);
		}

		if (Validator.isNotNull(keywords)) {
			return oAuthApplicationPersistence.findByC_N(
				companyId, keywords, start, end, orderByComparator);
		}

		return oAuthApplicationPersistence.findByCompanyId(
			companyId, start, end, orderByComparator);
	}

	public int searchCount(
			long companyId, String keywords,
			LinkedHashMap<String, Object> params)
		throws SystemException {

		keywords = CustomSQLUtil.keywords(keywords)[0];

		if ((params != null) && params.containsKey("userId")) {
			long userId = (Long)params.get("userId");

			if (Validator.isNotNull(keywords)) {
				return oAuthApplicationPersistence.countByU_N(userId, keywords);
			}

			return oAuthApplicationPersistence.countByUserId(userId);
		}

		if (Validator.isNotNull(keywords)) {
			return oAuthApplicationPersistence.countByC_N(companyId, keywords);
		}

		return oAuthApplicationPersistence.countByCompanyId(companyId);
	}

	public OAuthApplication updateLogo(
			long oAuthApplicationId, InputStream inputStream)
		throws PortalException, SystemException {

		OAuthApplication oAuthApplication =
			oAuthApplicationPersistence.findByPrimaryKey(oAuthApplicationId);

		long logoId = oAuthApplication.getLogoId();

		if (logoId <= 0) {
			logoId = counterLocalService.increment();

			oAuthApplication.setLogoId(logoId);

			oAuthApplicationPersistence.update(oAuthApplication, false);
		}

		imageLocalService.updateImage(logoId, inputStream);

		return oAuthApplication;
	}

	public OAuthApplication updateOAuthApplication(
			long oAuthApplicationId, String name, String description,
			String callbackURI, String websiteURL,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		validate(name, callbackURI, websiteURL);

		OAuthApplication oAuthApplication =
			oAuthApplicationPersistence.findByPrimaryKey(oAuthApplicationId);

		oAuthApplication.setModifiedDate(serviceContext.getModifiedDate(null));
		oAuthApplication.setName(name);
		oAuthApplication.setDescription(description);
		oAuthApplication.setCallbackURI(callbackURI);
		oAuthApplication.setWebsiteURL(websiteURL);

		oAuthApplicationPersistence.update(oAuthApplication, false);

		return oAuthApplication;
	}

	protected void validate(String name, String callbackURI, String websiteURL)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new OAuthApplicationNameException();
		}

		if (!Validator.isUri(callbackURI)) {
			throw new OAuthApplicationCallbackURIException();
		}

		if (!Validator.isUrl(websiteURL)) {
			throw new OAuthApplicationWebsiteURLException();
		}
	}

}