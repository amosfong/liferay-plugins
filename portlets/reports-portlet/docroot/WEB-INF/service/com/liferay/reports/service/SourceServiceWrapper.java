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

package com.liferay.reports.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SourceService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SourceService
 * @generated
 */
public class SourceServiceWrapper implements SourceService,
	ServiceWrapper<SourceService> {
	public SourceServiceWrapper(SourceService sourceService) {
		_sourceService = sourceService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _sourceService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_sourceService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _sourceService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.reports.model.Source addSource(long groupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String driverClassName, java.lang.String driverUrl,
		java.lang.String driverUserName, java.lang.String driverPassword,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sourceService.addSource(groupId, nameMap, driverClassName,
			driverUrl, driverUserName, driverPassword, serviceContext);
	}

	public com.liferay.reports.model.Source deleteSource(long sourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sourceService.deleteSource(sourceId);
	}

	public com.liferay.reports.model.Source getSource(long sourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sourceService.getSource(sourceId);
	}

	public java.util.List<com.liferay.reports.model.Source> getSources(
		long groupId, java.lang.String name, java.lang.String driverUrl,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sourceService.getSources(groupId, name, driverUrl, andSearch,
			start, end, orderByComparator);
	}

	public int getSourcesCount(long groupId, java.lang.String name,
		java.lang.String driverUrl, boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _sourceService.getSourcesCount(groupId, name, driverUrl,
			andSearch);
	}

	public com.liferay.reports.model.Source updateSource(long sourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.lang.String driverClassName, java.lang.String driverUrl,
		java.lang.String driverUserName, java.lang.String driverPassword,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _sourceService.updateSource(sourceId, nameMap, driverClassName,
			driverUrl, driverUserName, driverPassword, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SourceService getWrappedSourceService() {
		return _sourceService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSourceService(SourceService sourceService) {
		_sourceService = sourceService;
	}

	public SourceService getWrappedService() {
		return _sourceService;
	}

	public void setWrappedService(SourceService sourceService) {
		_sourceService = sourceService;
	}

	private SourceService _sourceService;
}