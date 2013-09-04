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
 * Provides a wrapper for {@link WebExAccountService}.
 *
 * @author Anant Singh
 * @see WebExAccountService
 * @generated
 */
public class WebExAccountServiceWrapper implements WebExAccountService,
	ServiceWrapper<WebExAccountService> {
	public WebExAccountServiceWrapper(WebExAccountService webExAccountService) {
		_webExAccountService = webExAccountService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _webExAccountService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_webExAccountService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _webExAccountService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public void addWebExAccount(long groupId, long webExSiteId,
		java.lang.String login, java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExAccountService.addWebExAccount(groupId, webExSiteId, login,
			password, serviceContext);
	}

	@Override
	public void deleteWebExAccount(long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExAccountService.deleteWebExAccount(webExAccountId);
	}

	@Override
	public com.liferay.meeting.webex.model.WebExAccount getWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountService.getWebExAccount(webExAccountId);
	}

	@Override
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountService.getWebExSiteWebExAccounts(groupId,
			webExSiteId, start, end, obc);
	}

	@Override
	public int getWebExSiteWebExAccountsCount(long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountService.getWebExSiteWebExAccountsCount(groupId,
			webExSiteId);
	}

	@Override
	public void updateWebExAccount(long webExAccountId,
		java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExAccountService.updateWebExAccount(webExAccountId, password,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public WebExAccountService getWrappedWebExAccountService() {
		return _webExAccountService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedWebExAccountService(
		WebExAccountService webExAccountService) {
		_webExAccountService = webExAccountService;
	}

	@Override
	public WebExAccountService getWrappedService() {
		return _webExAccountService;
	}

	@Override
	public void setWrappedService(WebExAccountService webExAccountService) {
		_webExAccountService = webExAccountService;
	}

	private WebExAccountService _webExAccountService;
}