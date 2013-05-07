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

package com.liferay.sync.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncDLObjectService}.
 *
 * @author    Brian Wing Shun Chan
 * @see       SyncDLObjectService
 * @generated
 */
public class SyncDLObjectServiceWrapper implements SyncDLObjectService,
	ServiceWrapper<SyncDLObjectService> {
	public SyncDLObjectServiceWrapper(SyncDLObjectService syncDLObjectService) {
		_syncDLObjectService = syncDLObjectService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _syncDLObjectService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_syncDLObjectService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _syncDLObjectService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SyncDLObjectService getWrappedSyncDLObjectService() {
		return _syncDLObjectService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSyncDLObjectService(
		SyncDLObjectService syncDLObjectService) {
		_syncDLObjectService = syncDLObjectService;
	}

	public SyncDLObjectService getWrappedService() {
		return _syncDLObjectService;
	}

	public void setWrappedService(SyncDLObjectService syncDLObjectService) {
		_syncDLObjectService = syncDLObjectService;
	}

	private SyncDLObjectService _syncDLObjectService;
}