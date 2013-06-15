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

package com.liferay.saml.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for SamlSpIdpConnection. This utility wraps
 * {@link com.liferay.saml.service.impl.SamlSpIdpConnectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Mika Koivisto
 * @see SamlSpIdpConnectionLocalService
 * @see com.liferay.saml.service.base.SamlSpIdpConnectionLocalServiceBaseImpl
 * @see com.liferay.saml.service.impl.SamlSpIdpConnectionLocalServiceImpl
 * @generated
 */
public class SamlSpIdpConnectionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.saml.service.impl.SamlSpIdpConnectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the saml sp idp connection to the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpIdpConnection the saml sp idp connection
	* @return the saml sp idp connection that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpIdpConnection addSamlSpIdpConnection(
		com.liferay.saml.model.SamlSpIdpConnection samlSpIdpConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSamlSpIdpConnection(samlSpIdpConnection);
	}

	/**
	* Creates a new saml sp idp connection with the primary key. Does not add the saml sp idp connection to the database.
	*
	* @param samlSpIdpConnectionId the primary key for the new saml sp idp connection
	* @return the new saml sp idp connection
	*/
	public static com.liferay.saml.model.SamlSpIdpConnection createSamlSpIdpConnection(
		long samlSpIdpConnectionId) {
		return getService().createSamlSpIdpConnection(samlSpIdpConnectionId);
	}

	/**
	* Deletes the saml sp idp connection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpIdpConnectionId the primary key of the saml sp idp connection
	* @return the saml sp idp connection that was removed
	* @throws PortalException if a saml sp idp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpIdpConnection deleteSamlSpIdpConnection(
		long samlSpIdpConnectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSamlSpIdpConnection(samlSpIdpConnectionId);
	}

	/**
	* Deletes the saml sp idp connection from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpIdpConnection the saml sp idp connection
	* @return the saml sp idp connection that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpIdpConnection deleteSamlSpIdpConnection(
		com.liferay.saml.model.SamlSpIdpConnection samlSpIdpConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSamlSpIdpConnection(samlSpIdpConnection);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpIdpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpIdpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.saml.model.SamlSpIdpConnection fetchSamlSpIdpConnection(
		long samlSpIdpConnectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSamlSpIdpConnection(samlSpIdpConnectionId);
	}

	/**
	* Returns the saml sp idp connection with the primary key.
	*
	* @param samlSpIdpConnectionId the primary key of the saml sp idp connection
	* @return the saml sp idp connection
	* @throws PortalException if a saml sp idp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpIdpConnection getSamlSpIdpConnection(
		long samlSpIdpConnectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSamlSpIdpConnection(samlSpIdpConnectionId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the saml sp idp connections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpIdpConnectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml sp idp connections
	* @param end the upper bound of the range of saml sp idp connections (not inclusive)
	* @return the range of saml sp idp connections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.saml.model.SamlSpIdpConnection> getSamlSpIdpConnections(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSamlSpIdpConnections(start, end);
	}

	/**
	* Returns the number of saml sp idp connections.
	*
	* @return the number of saml sp idp connections
	* @throws SystemException if a system exception occurred
	*/
	public static int getSamlSpIdpConnectionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSamlSpIdpConnectionsCount();
	}

	/**
	* Updates the saml sp idp connection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param samlSpIdpConnection the saml sp idp connection
	* @return the saml sp idp connection that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.saml.model.SamlSpIdpConnection updateSamlSpIdpConnection(
		com.liferay.saml.model.SamlSpIdpConnection samlSpIdpConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSamlSpIdpConnection(samlSpIdpConnection);
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

	public static com.liferay.saml.model.SamlSpIdpConnection addSamlSpIdpConnection(
		java.lang.String samlIdpEntityId, boolean assertionSignatureRequired,
		long clockSkew, boolean enabled, boolean ldapImportEnabled,
		java.lang.String metadataUrl,
		java.io.InputStream metadataXmlInputStream, java.lang.String name,
		java.lang.String nameIdFormat, boolean signAuthnRequest,
		java.lang.String userAttributeMappings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSamlSpIdpConnection(samlIdpEntityId,
			assertionSignatureRequired, clockSkew, enabled, ldapImportEnabled,
			metadataUrl, metadataXmlInputStream, name, nameIdFormat,
			signAuthnRequest, userAttributeMappings, serviceContext);
	}

	public static com.liferay.saml.model.SamlSpIdpConnection getSamlSpIdpConnection(
		long companyId, java.lang.String samlIdpEntityId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSamlSpIdpConnection(companyId, samlIdpEntityId);
	}

	public static java.util.List<com.liferay.saml.model.SamlSpIdpConnection> getSamlSpIdpConnections(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSamlSpIdpConnections(companyId);
	}

	public static java.util.List<com.liferay.saml.model.SamlSpIdpConnection> getSamlSpIdpConnections(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSamlSpIdpConnections(companyId, start, end);
	}

	public static java.util.List<com.liferay.saml.model.SamlSpIdpConnection> getSamlSpIdpConnections(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSamlSpIdpConnections(companyId, start, end,
			orderByComparator);
	}

	public static int getSamlSpIdpConnectionsCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSamlSpIdpConnectionsCount(companyId);
	}

	public static void updateMetadata(long samlSpIdpConnectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateMetadata(samlSpIdpConnectionId);
	}

	public static com.liferay.saml.model.SamlSpIdpConnection updateSamlSpIdpConnection(
		long samlSpIdpConnectionId, java.lang.String samlIdpEntityId,
		boolean assertionSignatureRequired, long clockSkew, boolean enabled,
		boolean ldapImportEnabled, java.lang.String metadataUrl,
		java.io.InputStream metadataXmlInputStream, java.lang.String name,
		java.lang.String nameIdFormat, boolean signAuthnRequest,
		java.lang.String userAttributeMappings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSamlSpIdpConnection(samlSpIdpConnectionId,
			samlIdpEntityId, assertionSignatureRequired, clockSkew, enabled,
			ldapImportEnabled, metadataUrl, metadataXmlInputStream, name,
			nameIdFormat, signAuthnRequest, userAttributeMappings,
			serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static SamlSpIdpConnectionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SamlSpIdpConnectionLocalService.class.getName());

			if (invokableLocalService instanceof SamlSpIdpConnectionLocalService) {
				_service = (SamlSpIdpConnectionLocalService)invokableLocalService;
			}
			else {
				_service = new SamlSpIdpConnectionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SamlSpIdpConnectionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(SamlSpIdpConnectionLocalService service) {
	}

	private static SamlSpIdpConnectionLocalService _service;
}