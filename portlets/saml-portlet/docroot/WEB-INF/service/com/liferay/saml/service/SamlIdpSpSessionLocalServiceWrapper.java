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
 * This class is a wrapper for {@link SamlIdpSpSessionLocalService}.
 * </p>
 *
 * @author    Mika Koivisto
 * @see       SamlIdpSpSessionLocalService
 * @generated
 */
public class SamlIdpSpSessionLocalServiceWrapper
	implements SamlIdpSpSessionLocalService,
		ServiceWrapper<SamlIdpSpSessionLocalService> {
	public SamlIdpSpSessionLocalServiceWrapper(
		SamlIdpSpSessionLocalService samlIdpSpSessionLocalService) {
		_samlIdpSpSessionLocalService = samlIdpSpSessionLocalService;
	}

	/**
	* Adds the saml idp sp session to the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpSession the saml idp sp session
	* @return the saml idp sp session that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpSession addSamlIdpSpSession(
		com.liferay.saml.model.SamlIdpSpSession samlIdpSpSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.addSamlIdpSpSession(samlIdpSpSession);
	}

	/**
	* Creates a new saml idp sp session with the primary key. Does not add the saml idp sp session to the database.
	*
	* @param samlIdpSpSessionId the primary key for the new saml idp sp session
	* @return the new saml idp sp session
	*/
	public com.liferay.saml.model.SamlIdpSpSession createSamlIdpSpSession(
		long samlIdpSpSessionId) {
		return _samlIdpSpSessionLocalService.createSamlIdpSpSession(samlIdpSpSessionId);
	}

	/**
	* Deletes the saml idp sp session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpSessionId the primary key of the saml idp sp session
	* @return the saml idp sp session that was removed
	* @throws PortalException if a saml idp sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpSession deleteSamlIdpSpSession(
		long samlIdpSpSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.deleteSamlIdpSpSession(samlIdpSpSessionId);
	}

	/**
	* Deletes the saml idp sp session from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpSession the saml idp sp session
	* @return the saml idp sp session that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpSession deleteSamlIdpSpSession(
		com.liferay.saml.model.SamlIdpSpSession samlIdpSpSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.deleteSamlIdpSpSession(samlIdpSpSession);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _samlIdpSpSessionLocalService.dynamicQuery();
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
		return _samlIdpSpSessionLocalService.dynamicQuery(dynamicQuery);
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
		return _samlIdpSpSessionLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _samlIdpSpSessionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _samlIdpSpSessionLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.saml.model.SamlIdpSpSession fetchSamlIdpSpSession(
		long samlIdpSpSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.fetchSamlIdpSpSession(samlIdpSpSessionId);
	}

	/**
	* Returns the saml idp sp session with the primary key.
	*
	* @param samlIdpSpSessionId the primary key of the saml idp sp session
	* @return the saml idp sp session
	* @throws PortalException if a saml idp sp session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpSession getSamlIdpSpSession(
		long samlIdpSpSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.getSamlIdpSpSession(samlIdpSpSessionId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the saml idp sp sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of saml idp sp sessions
	* @param end the upper bound of the range of saml idp sp sessions (not inclusive)
	* @return the range of saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.saml.model.SamlIdpSpSession> getSamlIdpSpSessions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.getSamlIdpSpSessions(start, end);
	}

	/**
	* Returns the number of saml idp sp sessions.
	*
	* @return the number of saml idp sp sessions
	* @throws SystemException if a system exception occurred
	*/
	public int getSamlIdpSpSessionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.getSamlIdpSpSessionsCount();
	}

	/**
	* Updates the saml idp sp session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSpSession the saml idp sp session
	* @return the saml idp sp session that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.saml.model.SamlIdpSpSession updateSamlIdpSpSession(
		com.liferay.saml.model.SamlIdpSpSession samlIdpSpSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.updateSamlIdpSpSession(samlIdpSpSession);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _samlIdpSpSessionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_samlIdpSpSessionLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _samlIdpSpSessionLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.saml.model.SamlIdpSpSession addSamlIdpSpSession(
		long samlIdpSsoSessionId, java.lang.String samlSpEntityId,
		java.lang.String nameIdFormat, java.lang.String nameIdValue,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.addSamlIdpSpSession(samlIdpSsoSessionId,
			samlSpEntityId, nameIdFormat, nameIdValue, serviceContext);
	}

	public com.liferay.saml.model.SamlIdpSpSession getSamlIdpSpSession(
		long samlIdpSsoSessionId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.getSamlIdpSpSession(samlIdpSsoSessionId,
			samlSpEntityId);
	}

	public java.util.List<com.liferay.saml.model.SamlIdpSpSession> getSamlIdpSpSessions(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.getSamlIdpSpSessions(samlIdpSsoSessionId);
	}

	public com.liferay.saml.model.SamlIdpSpSession updateModifiedDate(
		long samlIdpSsoSessionId, java.lang.String samlSpEntityId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSpSessionLocalService.updateModifiedDate(samlIdpSsoSessionId,
			samlSpEntityId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SamlIdpSpSessionLocalService getWrappedSamlIdpSpSessionLocalService() {
		return _samlIdpSpSessionLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSamlIdpSpSessionLocalService(
		SamlIdpSpSessionLocalService samlIdpSpSessionLocalService) {
		_samlIdpSpSessionLocalService = samlIdpSpSessionLocalService;
	}

	public SamlIdpSpSessionLocalService getWrappedService() {
		return _samlIdpSpSessionLocalService;
	}

	public void setWrappedService(
		SamlIdpSpSessionLocalService samlIdpSpSessionLocalService) {
		_samlIdpSpSessionLocalService = samlIdpSpSessionLocalService;
	}

	private SamlIdpSpSessionLocalService _samlIdpSpSessionLocalService;
}