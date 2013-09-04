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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the web ex site remote service. This utility wraps {@link com.liferay.meeting.webex.service.impl.WebExSiteServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Anant Singh
 * @see WebExSiteService
 * @see com.liferay.meeting.webex.service.base.WebExSiteServiceBaseImpl
 * @see com.liferay.meeting.webex.service.impl.WebExSiteServiceImpl
 * @generated
 */
public class WebExSiteServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.meeting.webex.service.impl.WebExSiteServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static void addWebExSite(long groupId, java.lang.String name,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addWebExSite(groupId, name, apiURL, login, password, partnerKey,
			siteKey, serviceContext);
	}

	public static com.liferay.meeting.webex.model.WebExSite deleteWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteWebExSite(webExSiteId);
	}

	public static com.liferay.meeting.webex.model.WebExSite fetchSiteKeyWebExSite(
		long siteKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSiteKeyWebExSite(siteKey);
	}

	public static com.liferay.meeting.webex.model.WebExSite getWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWebExSite(webExSiteId);
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWebExSites(groupId, start, end, obc);
	}

	public static int getWebExSitesCount(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWebExSitesCount(groupId);
	}

	public static void updateWebExSite(long webExSiteId,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateWebExSite(webExSiteId, apiURL, login, password,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static WebExSiteService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WebExSiteService.class.getName());

			if (invokableService instanceof WebExSiteService) {
				_service = (WebExSiteService)invokableService;
			}
			else {
				_service = new WebExSiteServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(WebExSiteServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(WebExSiteService service) {
	}

	private static WebExSiteService _service;
}