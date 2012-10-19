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

package com.liferay.saml.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SamlSpIdpConnectionLocalService}.
 * </p>
 *
 * @author    Mika Koivisto
 * @see       SamlSpIdpConnectionLocalService
 * @generated
 */
public class SamlSpIdpConnectionLocalServiceWrapper
	implements SamlSpIdpConnectionLocalService,
		ServiceWrapper<SamlSpIdpConnectionLocalService> {
	public SamlSpIdpConnectionLocalServiceWrapper(
		SamlSpIdpConnectionLocalService samlSpIdpConnectionLocalService) {
		_samlSpIdpConnectionLocalService = samlSpIdpConnectionLocalService;
	}

	/**
	* Adds the saml sp idp connection to the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpIdpConnection the saml sp idp connection
	* @return the saml sp idp connection that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpIdpConnection addSamlSpIdpConnection(
		com.liferay.saml.model.SamlSpIdpConnection samlSpIdpConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.addSamlSpIdpConnection(samlSpIdpConnection);
	}

	/**
	* Creates a new saml sp idp connection with the primary key. Does not add the saml sp idp connection to the database.
	*
	* @param samlSpIdpConnectionId the primary key for the new saml sp idp connection
	* @return the new saml sp idp connection
	*/
	public com.liferay.saml.model.SamlSpIdpConnection createSamlSpIdpConnection(
		long samlSpIdpConnectionId) {
		return _samlSpIdpConnectionLocalService.createSamlSpIdpConnection(samlSpIdpConnectionId);
	}

	/**
	* Deletes the saml sp idp connection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpIdpConnectionId the primary key of the saml sp idp connection
	* @return the saml sp idp connection that was removed
	* @throws PortalException if a saml sp idp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpIdpConnection deleteSamlSpIdpConnection(
		long samlSpIdpConnectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.deleteSamlSpIdpConnection(samlSpIdpConnectionId);
	}

	/**
	* Deletes the saml sp idp connection from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpIdpConnection the saml sp idp connection
	* @return the saml sp idp connection that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpIdpConnection deleteSamlSpIdpConnection(
		com.liferay.saml.model.SamlSpIdpConnection samlSpIdpConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.deleteSamlSpIdpConnection(samlSpIdpConnection);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _samlSpIdpConnectionLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.saml.model.SamlSpIdpConnection fetchSamlSpIdpConnection(
		long samlSpIdpConnectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.fetchSamlSpIdpConnection(samlSpIdpConnectionId);
	}

	/**
	* Returns the saml sp idp connection with the primary key.
	*
	* @param samlSpIdpConnectionId the primary key of the saml sp idp connection
	* @return the saml sp idp connection
	* @throws PortalException if a saml sp idp connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpIdpConnection getSamlSpIdpConnection(
		long samlSpIdpConnectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.getSamlSpIdpConnection(samlSpIdpConnectionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the saml sp idp connections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of saml sp idp connections
	* @param end the upper bound of the range of saml sp idp connections (not inclusive)
	* @return the range of saml sp idp connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlSpIdpConnection> getSamlSpIdpConnections(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.getSamlSpIdpConnections(start,
			end);
	}

	/**
	* Returns the number of saml sp idp connections.
	*
	* @return the number of saml sp idp connections
	* @throws SystemException if a system exception occurred
	*/
	public int getSamlSpIdpConnectionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.getSamlSpIdpConnectionsCount();
	}

	/**
	* Updates the saml sp idp connection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param samlSpIdpConnection the saml sp idp connection
	* @return the saml sp idp connection that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpIdpConnection updateSamlSpIdpConnection(
		com.liferay.saml.model.SamlSpIdpConnection samlSpIdpConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.updateSamlSpIdpConnection(samlSpIdpConnection);
	}

	/**
	* Updates the saml sp idp connection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param samlSpIdpConnection the saml sp idp connection
	* @param merge whether to merge the saml sp idp connection with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the saml sp idp connection that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlSpIdpConnection updateSamlSpIdpConnection(
		com.liferay.saml.model.SamlSpIdpConnection samlSpIdpConnection,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.updateSamlSpIdpConnection(samlSpIdpConnection,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _samlSpIdpConnectionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_samlSpIdpConnectionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _samlSpIdpConnectionLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public com.liferay.saml.model.SamlSpIdpConnection addSamlSpIdpConnection(
		java.lang.String samlIdpEntityId, boolean assertionSignatureRequired,
		long clockSkew, boolean enabled, boolean ldapImportEnabled,
		java.lang.String metadataUrl,
		java.io.InputStream metadataXmlInputStream, java.lang.String name,
		java.lang.String nameIdFormat, boolean signAuthnRequest,
		java.lang.String userAttributeMappings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.addSamlSpIdpConnection(samlIdpEntityId,
			assertionSignatureRequired, clockSkew, enabled, ldapImportEnabled,
			metadataUrl, metadataXmlInputStream, name, nameIdFormat,
			signAuthnRequest, userAttributeMappings, serviceContext);
	}

	public com.liferay.saml.model.SamlSpIdpConnection getSamlSpIdpConnection(
		long companyId, java.lang.String samlIdpEntityId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.getSamlSpIdpConnection(companyId,
			samlIdpEntityId);
	}

	public java.util.List<com.liferay.saml.model.SamlSpIdpConnection> getSamlSpIdpConnections(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.getSamlSpIdpConnections(companyId);
	}

	public java.util.List<com.liferay.saml.model.SamlSpIdpConnection> getSamlSpIdpConnections(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.getSamlSpIdpConnections(companyId,
			start, end);
	}

	public java.util.List<com.liferay.saml.model.SamlSpIdpConnection> getSamlSpIdpConnections(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.getSamlSpIdpConnections(companyId,
			start, end, orderByComparator);
	}

	public int getSamlSpIdpConnectionsCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.getSamlSpIdpConnectionsCount(companyId);
	}

	public void updateMetadata(long samlSpIdpConnectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_samlSpIdpConnectionLocalService.updateMetadata(samlSpIdpConnectionId);
	}

	public com.liferay.saml.model.SamlSpIdpConnection updateSamlSpIdpConnection(
		long samlSpIdpConnectionId, java.lang.String samlIdpEntityId,
		boolean assertionSignatureRequired, long clockSkew, boolean enabled,
		boolean ldapImportEnabled, java.lang.String metadataUrl,
		java.io.InputStream metadataXmlInputStream, java.lang.String name,
		java.lang.String nameIdFormat, boolean signAuthnRequest,
		java.lang.String userAttributeMappings,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpIdpConnectionLocalService.updateSamlSpIdpConnection(samlSpIdpConnectionId,
			samlIdpEntityId, assertionSignatureRequired, clockSkew, enabled,
			ldapImportEnabled, metadataUrl, metadataXmlInputStream, name,
			nameIdFormat, signAuthnRequest, userAttributeMappings,
			serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SamlSpIdpConnectionLocalService getWrappedSamlSpIdpConnectionLocalService() {
		return _samlSpIdpConnectionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSamlSpIdpConnectionLocalService(
		SamlSpIdpConnectionLocalService samlSpIdpConnectionLocalService) {
		_samlSpIdpConnectionLocalService = samlSpIdpConnectionLocalService;
	}

	public SamlSpIdpConnectionLocalService getWrappedService() {
		return _samlSpIdpConnectionLocalService;
	}

	public void setWrappedService(
		SamlSpIdpConnectionLocalService samlSpIdpConnectionLocalService) {
		_samlSpIdpConnectionLocalService = samlSpIdpConnectionLocalService;
	}

	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;
}