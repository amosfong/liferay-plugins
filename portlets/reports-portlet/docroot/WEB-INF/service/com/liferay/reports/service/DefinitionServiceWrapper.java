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

package com.liferay.reports.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DefinitionService}.
 *
 * @author Brian Wing Shun Chan
 * @see DefinitionService
 * @generated
 */
public class DefinitionServiceWrapper implements DefinitionService,
	ServiceWrapper<DefinitionService> {
	public DefinitionServiceWrapper(DefinitionService definitionService) {
		_definitionService = definitionService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _definitionService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_definitionService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _definitionService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public com.liferay.reports.model.Definition addDefinition(long groupId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long sourceId, java.lang.String reportParameters,
		java.lang.String fileName, java.io.InputStream inputStream,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _definitionService.addDefinition(groupId, nameMap,
			descriptionMap, sourceId, reportParameters, fileName, inputStream,
			serviceContext);
	}

	@Override
	public com.liferay.reports.model.Definition deleteDefinition(
		long definitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _definitionService.deleteDefinition(definitionId);
	}

	@Override
	public com.liferay.reports.model.Definition getDefinition(long definitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _definitionService.getDefinition(definitionId);
	}

	@Override
	public java.util.List<com.liferay.reports.model.Definition> getDefinitions(
		long groupId, java.lang.String definitionName,
		java.lang.String description, java.lang.String sourceId,
		java.lang.String reportName, boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _definitionService.getDefinitions(groupId, definitionName,
			description, sourceId, reportName, andSearch, start, end,
			orderByComparator);
	}

	@Override
	public int getDefinitionsCount(long groupId,
		java.lang.String definitionName, java.lang.String description,
		java.lang.String sourceId, java.lang.String reportName,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _definitionService.getDefinitionsCount(groupId, definitionName,
			description, sourceId, reportName, andSearch);
	}

	@Override
	public com.liferay.reports.model.Definition updateDefinition(
		long definitionId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long sourceId, java.lang.String reportParameters,
		java.lang.String fileName, java.io.InputStream inputStream,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _definitionService.updateDefinition(definitionId, nameMap,
			descriptionMap, sourceId, reportParameters, fileName, inputStream,
			serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DefinitionService getWrappedDefinitionService() {
		return _definitionService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDefinitionService(DefinitionService definitionService) {
		_definitionService = definitionService;
	}

	@Override
	public DefinitionService getWrappedService() {
		return _definitionService;
	}

	@Override
	public void setWrappedService(DefinitionService definitionService) {
		_definitionService = definitionService;
	}

	private DefinitionService _definitionService;
}