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

package com.liferay.portal.resiliency.spi.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SPIDefinitionService}.
 *
 * @author Michael C. Han
 * @see SPIDefinitionService
 * @generated
 */
public class SPIDefinitionServiceWrapper implements SPIDefinitionService,
	ServiceWrapper<SPIDefinitionService> {
	public SPIDefinitionServiceWrapper(
		SPIDefinitionService spiDefinitionService) {
		_spiDefinitionService = spiDefinitionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _spiDefinitionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_spiDefinitionService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _spiDefinitionService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SPIDefinitionService getWrappedSPIDefinitionService() {
		return _spiDefinitionService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSPIDefinitionService(
		SPIDefinitionService spiDefinitionService) {
		_spiDefinitionService = spiDefinitionService;
	}

	@Override
	public SPIDefinitionService getWrappedService() {
		return _spiDefinitionService;
	}

	@Override
	public void setWrappedService(SPIDefinitionService spiDefinitionService) {
		_spiDefinitionService = spiDefinitionService;
	}

	private SPIDefinitionService _spiDefinitionService;
}