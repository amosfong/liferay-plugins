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

package com.liferay.meeting.webex.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WebExSiteService}.
 *
 * @author Anant Singh
 * @see WebExSiteService
 * @generated
 */
public class WebExSiteServiceWrapper implements WebExSiteService,
	ServiceWrapper<WebExSiteService> {
	public WebExSiteServiceWrapper(WebExSiteService webExSiteService) {
		_webExSiteService = webExSiteService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _webExSiteService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_webExSiteService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _webExSiteService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public void addWebExSite(long groupId, java.lang.String name,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExSiteService.addWebExSite(groupId, name, apiURL, login, password,
			partnerKey, siteKey, serviceContext);
	}

	@Override
	public com.liferay.meeting.webex.model.WebExSite deleteWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteService.deleteWebExSite(webExSiteId);
	}

	@Override
	public com.liferay.meeting.webex.model.WebExSite fetchSiteKeyWebExSite(
		long siteKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteService.fetchSiteKeyWebExSite(siteKey);
	}

	@Override
	public com.liferay.meeting.webex.model.WebExSite getWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteService.getWebExSite(webExSiteId);
	}

	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteService.getWebExSites(groupId, start, end, obc);
	}

	@Override
	public int getWebExSitesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteService.getWebExSitesCount(groupId);
	}

	@Override
	public void updateWebExSite(long webExSiteId, java.lang.String apiURL,
		java.lang.String login, java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExSiteService.updateWebExSite(webExSiteId, apiURL, login, password,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WebExSiteService getWrappedWebExSiteService() {
		return _webExSiteService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWebExSiteService(WebExSiteService webExSiteService) {
		_webExSiteService = webExSiteService;
	}

	@Override
	public WebExSiteService getWrappedService() {
		return _webExSiteService;
	}

	@Override
	public void setWrappedService(WebExSiteService webExSiteService) {
		_webExSiteService = webExSiteService;
	}

	private WebExSiteService _webExSiteService;
}