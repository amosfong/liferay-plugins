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
 * Provides a wrapper for {@link KaleoDraftDefinitionLocalService}.
 *
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinitionLocalService
 * @generated
 */
public class KaleoDraftDefinitionLocalServiceWrapper
	implements KaleoDraftDefinitionLocalService,
		ServiceWrapper<KaleoDraftDefinitionLocalService> {
	public KaleoDraftDefinitionLocalServiceWrapper(
		KaleoDraftDefinitionLocalService kaleoDraftDefinitionLocalService) {
		_kaleoDraftDefinitionLocalService = kaleoDraftDefinitionLocalService;
	}

	/**
	* Adds the kaleo draft definition to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDraftDefinition the kaleo draft definition
	* @return the kaleo draft definition that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition addKaleoDraftDefinition(
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition kaleoDraftDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.addKaleoDraftDefinition(kaleoDraftDefinition);
	}

	/**
	* Creates a new kaleo draft definition with the primary key. Does not add the kaleo draft definition to the database.
	*
	* @param kaleoDraftDefinitionId the primary key for the new kaleo draft definition
	* @return the new kaleo draft definition
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition createKaleoDraftDefinition(
		long kaleoDraftDefinitionId) {
		return _kaleoDraftDefinitionLocalService.createKaleoDraftDefinition(kaleoDraftDefinitionId);
	}

	/**
	* Deletes the kaleo draft definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	* @return the kaleo draft definition that was removed
	* @throws PortalException if a kaleo draft definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition deleteKaleoDraftDefinition(
		long kaleoDraftDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.deleteKaleoDraftDefinition(kaleoDraftDefinitionId);
	}

	/**
	* Deletes the kaleo draft definition from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoDraftDefinition the kaleo draft definition
	* @return the kaleo draft definition that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition deleteKaleoDraftDefinition(
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition kaleoDraftDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.deleteKaleoDraftDefinition(kaleoDraftDefinition);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoDraftDefinitionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition fetchKaleoDraftDefinition(
		long kaleoDraftDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.fetchKaleoDraftDefinition(kaleoDraftDefinitionId);
	}

	/**
	* Returns the kaleo draft definition with the primary key.
	*
	* @param kaleoDraftDefinitionId the primary key of the kaleo draft definition
	* @return the kaleo draft definition
	* @throws PortalException if a kaleo draft definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition getKaleoDraftDefinition(
		long kaleoDraftDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getKaleoDraftDefinition(kaleoDraftDefinitionId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo draft definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo draft definitions
	* @param end the upper bound of the range of kaleo draft definitions (not inclusive)
	* @return the range of kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> getKaleoDraftDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getKaleoDraftDefinitions(start,
			end);
	}

	/**
	* Returns the number of kaleo draft definitions.
	*
	* @return the number of kaleo draft definitions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getKaleoDraftDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getKaleoDraftDefinitionsCount();
	}

	/**
	* Updates the kaleo draft definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoDraftDefinition the kaleo draft definition
	* @return the kaleo draft definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition updateKaleoDraftDefinition(
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition kaleoDraftDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.updateKaleoDraftDefinition(kaleoDraftDefinition);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _kaleoDraftDefinitionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoDraftDefinitionLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoDraftDefinitionLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition addKaleoDraftDefinition(
		long userId, long groupId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.lang.String content, int version, int draftVersion,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.addKaleoDraftDefinition(userId,
			groupId, name, titleMap, content, version, draftVersion,
			serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition deleteKaleoDraftDefinition(
		java.lang.String name, int version, int draftVersion,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.deleteKaleoDraftDefinition(name,
			version, draftVersion, serviceContext);
	}

	@Override
	public void deleteKaleoDraftDefinitions(java.lang.String name, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoDraftDefinitionLocalService.deleteKaleoDraftDefinitions(name,
			version, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition getKaleoDraftDefinition(
		java.lang.String name, int version, int draftVersion,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getKaleoDraftDefinition(name,
			version, draftVersion, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> getKaleoDraftDefinitions(
		java.lang.String name, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getKaleoDraftDefinitions(name,
			version, start, end, orderByComparator, serviceContext);
	}

	@Override
	public int getKaleoDraftDefinitionsCount(java.lang.String name,
		int version, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getKaleoDraftDefinitionsCount(name,
			version, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition getLatestKaleoDraftDefinition(
		java.lang.String name, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getLatestKaleoDraftDefinition(name,
			version, serviceContext);
	}

	@Override
	public java.util.List<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition> getLatestKaleoDraftDefinitions(
		long companyId, int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getLatestKaleoDraftDefinitions(companyId,
			version, start, end, orderByComparator);
	}

	@Override
	public int getLatestKaleoDraftDefinitionsCount(long companyId, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.getLatestKaleoDraftDefinitionsCount(companyId,
			version);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition incrementKaleoDraftDefinitionDraftVersion(
		long userId, java.lang.String name, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.incrementKaleoDraftDefinitionDraftVersion(userId,
			name, version, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition publishKaleoDraftDefinition(
		long userId, long groupId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.publishKaleoDraftDefinition(userId,
			groupId, name, titleMap, content, serviceContext);
	}

	@Override
	public com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition updateKaleoDraftDefinition(
		long userId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> titleMap,
		java.lang.String content, int version,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDraftDefinitionLocalService.updateKaleoDraftDefinition(userId,
			name, titleMap, content, version, serviceContext);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public KaleoDraftDefinitionLocalService getWrappedKaleoDraftDefinitionLocalService() {
		return _kaleoDraftDefinitionLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedKaleoDraftDefinitionLocalService(
		KaleoDraftDefinitionLocalService kaleoDraftDefinitionLocalService) {
		_kaleoDraftDefinitionLocalService = kaleoDraftDefinitionLocalService;
	}

	@Override
	public KaleoDraftDefinitionLocalService getWrappedService() {
		return _kaleoDraftDefinitionLocalService;
	}

	@Override
	public void setWrappedService(
		KaleoDraftDefinitionLocalService kaleoDraftDefinitionLocalService) {
		_kaleoDraftDefinitionLocalService = kaleoDraftDefinitionLocalService;
	}

	private KaleoDraftDefinitionLocalService _kaleoDraftDefinitionLocalService;
}