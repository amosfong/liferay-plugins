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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SPIDefinition. This utility wraps
 * {@link com.liferay.portal.resiliency.spi.service.impl.SPIDefinitionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Michael C. Han
 * @see SPIDefinitionLocalService
 * @see com.liferay.portal.resiliency.spi.service.base.SPIDefinitionLocalServiceBaseImpl
 * @see com.liferay.portal.resiliency.spi.service.impl.SPIDefinitionLocalServiceImpl
 * @generated
 */
public class SPIDefinitionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.resiliency.spi.service.impl.SPIDefinitionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the s p i definition to the database. Also notifies the appropriate model listeners.
	*
	* @param spiDefinition the s p i definition
	* @return the s p i definition that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.resiliency.spi.model.SPIDefinition addSPIDefinition(
		com.liferay.portal.resiliency.spi.model.SPIDefinition spiDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSPIDefinition(spiDefinition);
	}

	/**
	* Creates a new s p i definition with the primary key. Does not add the s p i definition to the database.
	*
	* @param spiDefinitionId the primary key for the new s p i definition
	* @return the new s p i definition
	*/
	public static com.liferay.portal.resiliency.spi.model.SPIDefinition createSPIDefinition(
		long spiDefinitionId) {
		return getService().createSPIDefinition(spiDefinitionId);
	}

	/**
	* Deletes the s p i definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param spiDefinitionId the primary key of the s p i definition
	* @return the s p i definition that was removed
	* @throws PortalException if a s p i definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.resiliency.spi.model.SPIDefinition deleteSPIDefinition(
		long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSPIDefinition(spiDefinitionId);
	}

	/**
	* Deletes the s p i definition from the database. Also notifies the appropriate model listeners.
	*
	* @param spiDefinition the s p i definition
	* @return the s p i definition that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.resiliency.spi.model.SPIDefinition deleteSPIDefinition(
		com.liferay.portal.resiliency.spi.model.SPIDefinition spiDefinition)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSPIDefinition(spiDefinition);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.resiliency.spi.model.impl.SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.resiliency.spi.model.impl.SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition fetchSPIDefinition(
		long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSPIDefinition(spiDefinitionId);
	}

	/**
	* Returns the s p i definition with the primary key.
	*
	* @param spiDefinitionId the primary key of the s p i definition
	* @return the s p i definition
	* @throws PortalException if a s p i definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.resiliency.spi.model.SPIDefinition getSPIDefinition(
		long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPIDefinition(spiDefinitionId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the s p i definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.resiliency.spi.model.impl.SPIDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s p i definitions
	* @param end the upper bound of the range of s p i definitions (not inclusive)
	* @return the range of s p i definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.resiliency.spi.model.SPIDefinition> getSPIDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPIDefinitions(start, end);
	}

	/**
	* Returns the number of s p i definitions.
	*
	* @return the number of s p i definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int getSPIDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPIDefinitionsCount();
	}

	/**
	* Updates the s p i definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param spiDefinition the s p i definition
	* @return the s p i definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.resiliency.spi.model.SPIDefinition updateSPIDefinition(
		com.liferay.portal.resiliency.spi.model.SPIDefinition spiDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSPIDefinition(spiDefinition);
	}

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

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition addSPIDefinition(
		long userId, java.lang.String name, java.lang.String connectorAddress,
		int connectorPort, java.lang.String description,
		java.lang.String jvmArguments, java.lang.String portletIds,
		java.lang.String servletContextNames, java.lang.String typeSettings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSPIDefinition(userId, name, connectorAddress,
			connectorPort, description, jvmArguments, portletIds,
			servletContextNames, typeSettings, serviceContext);
	}

	public static com.liferay.portal.kernel.util.Tuple getPortletIdsAndServletContextNames()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPortletIdsAndServletContextNames();
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition getSPIDefinition(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPIDefinition(companyId, name);
	}

	public static java.util.List<com.liferay.portal.resiliency.spi.model.SPIDefinition> getSPIDefinitions()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPIDefinitions();
	}

	public static java.util.List<com.liferay.portal.resiliency.spi.model.SPIDefinition> getSPIDefinitions(
		long companyId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPIDefinitions(companyId, status);
	}

	public static java.util.List<com.liferay.portal.resiliency.spi.model.SPIDefinition> getSPIDefinitions(
		long companyId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSPIDefinitions(companyId, statuses);
	}

	public static void startSPI(long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().startSPI(spiDefinitionId);
	}

	public static long startSPIinBackground(long userId, long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().startSPIinBackground(userId, spiDefinitionId);
	}

	public static void stopSPI(long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().stopSPI(spiDefinitionId);
	}

	public static long stopSPIinBackground(long userId, long spiDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().stopSPIinBackground(userId, spiDefinitionId);
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition updateSPIDefinition(
		long spiDefinitionId, int status, java.lang.String statusMessage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSPIDefinition(spiDefinitionId, status, statusMessage);
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition updateSPIDefinition(
		long userId, long spiDefinitionId, java.lang.String connectorAddress,
		int connectorPort, java.lang.String description,
		java.lang.String jvmArguments, java.lang.String portletIds,
		java.lang.String servletContextNames, java.lang.String typeSettings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSPIDefinition(userId, spiDefinitionId,
			connectorAddress, connectorPort, description, jvmArguments,
			portletIds, servletContextNames, typeSettings, serviceContext);
	}

	public static com.liferay.portal.resiliency.spi.model.SPIDefinition updateTypeSettings(
		long userId, long spiDefinitionId, java.lang.String typeSettings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTypeSettings(userId, spiDefinitionId, typeSettings,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static SPIDefinitionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SPIDefinitionLocalService.class.getName());

			if (invokableLocalService instanceof SPIDefinitionLocalService) {
				_service = (SPIDefinitionLocalService)invokableLocalService;
			}
			else {
				_service = new SPIDefinitionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SPIDefinitionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SPIDefinitionLocalService service) {
	}

	private static SPIDefinitionLocalService _service;
}