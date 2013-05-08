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

package com.liferay.portal.workflow.kaleo.designer.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link KaleoDraftDefinitionService}.
 *
 * @author    Eduardo Lundgren
 * @see       KaleoDraftDefinitionService
 * @generated
 */
public class KaleoDraftDefinitionServiceWrapper
	implements KaleoDraftDefinitionService,
		ServiceWrapper<KaleoDraftDefinitionService> {
	public KaleoDraftDefinitionServiceWrapper(
		KaleoDraftDefinitionService kaleoDraftDefinitionService) {
		_kaleoDraftDefinitionService = kaleoDraftDefinitionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kaleoDraftDefinitionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoDraftDefinitionService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoDraftDefinitionService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition addKaleoDraftDefinition(
		long userId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.lang.String content, int version, int draftVersion,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionService.addKaleoDraftDefinition(userId,
			name, titleMap, content, version, draftVersion, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition getKaleoDraftDefinition(
		java.lang.String name, int version, int draftVersion,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionService.getKaleoDraftDefinition(name,
			version, draftVersion, serviceContext);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> getKaleoDraftDefinitions()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionService.getKaleoDraftDefinitions();
	}

	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition getLatestKaleoDraftDefinition(
		java.lang.String name, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionService.getLatestKaleoDraftDefinition(name,
			version, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition publishKaleoDraftDefinition(
		long userId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionService.publishKaleoDraftDefinition(userId,
			name, titleMap, content, serviceContext);
	}

	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition updateKaleoDraftDefinition(
		long userId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.lang.String content, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionService.updateKaleoDraftDefinition(userId,
			name, titleMap, content, version, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public KaleoDraftDefinitionService getWrappedKaleoDraftDefinitionService() {
		return _kaleoDraftDefinitionService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedKaleoDraftDefinitionService(
		KaleoDraftDefinitionService kaleoDraftDefinitionService) {
		_kaleoDraftDefinitionService = kaleoDraftDefinitionService;
	}

	public KaleoDraftDefinitionService getWrappedService() {
		return _kaleoDraftDefinitionService;
	}

	public void setWrappedService(
		KaleoDraftDefinitionService kaleoDraftDefinitionService) {
		_kaleoDraftDefinitionService = kaleoDraftDefinitionService;
	}

	private KaleoDraftDefinitionService _kaleoDraftDefinitionService;
}