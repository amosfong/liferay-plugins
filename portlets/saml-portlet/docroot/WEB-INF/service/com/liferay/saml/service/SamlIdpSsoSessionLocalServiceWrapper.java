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

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SamlIdpSsoSessionLocalService}.
 *
 * @author Mika Koivisto
 * @see SamlIdpSsoSessionLocalService
 * @generated
 */
public class SamlIdpSsoSessionLocalServiceWrapper
	implements SamlIdpSsoSessionLocalService,
		ServiceWrapper<SamlIdpSsoSessionLocalService> {
	public SamlIdpSsoSessionLocalServiceWrapper(
		SamlIdpSsoSessionLocalService samlIdpSsoSessionLocalService) {
		_samlIdpSsoSessionLocalService = samlIdpSsoSessionLocalService;
	}

	/**
	* Adds the saml idp sso session to the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSsoSession the saml idp sso session
	* @return the saml idp sso session that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlIdpSsoSession addSamlIdpSsoSession(
		com.liferay.saml.model.SamlIdpSsoSession samlIdpSsoSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.addSamlIdpSsoSession(samlIdpSsoSession);
	}

	/**
	* Creates a new saml idp sso session with the primary key. Does not add the saml idp sso session to the database.
	*
	* @param samlIdpSsoSessionId the primary key for the new saml idp sso session
	* @return the new saml idp sso session
	*/
	@Override
	public com.liferay.saml.model.SamlIdpSsoSession createSamlIdpSsoSession(
		long samlIdpSsoSessionId) {
		return _samlIdpSsoSessionLocalService.createSamlIdpSsoSession(samlIdpSsoSessionId);
	}

	/**
	* Deletes the saml idp sso session with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSsoSessionId the primary key of the saml idp sso session
	* @return the saml idp sso session that was removed
	* @throws PortalException if a saml idp sso session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlIdpSsoSession deleteSamlIdpSsoSession(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.deleteSamlIdpSsoSession(samlIdpSsoSessionId);
	}

	/**
	* Deletes the saml idp sso session from the database. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSsoSession the saml idp sso session
	* @return the saml idp sso session that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlIdpSsoSession deleteSamlIdpSsoSession(
		com.liferay.saml.model.SamlIdpSsoSession samlIdpSsoSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.deleteSamlIdpSsoSession(samlIdpSsoSession);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _samlIdpSsoSessionLocalService.dynamicQuery();
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
		return _samlIdpSsoSessionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSsoSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _samlIdpSsoSessionLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSsoSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _samlIdpSsoSessionLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _samlIdpSsoSessionLocalService.dynamicQueryCount(dynamicQuery);
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
		return _samlIdpSsoSessionLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.saml.model.SamlIdpSsoSession fetchSamlIdpSsoSession(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.fetchSamlIdpSsoSession(samlIdpSsoSessionId);
	}

	/**
	* Returns the saml idp sso session with the primary key.
	*
	* @param samlIdpSsoSessionId the primary key of the saml idp sso session
	* @return the saml idp sso session
	* @throws PortalException if a saml idp sso session with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlIdpSsoSession getSamlIdpSsoSession(
		long samlIdpSsoSessionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.getSamlIdpSsoSession(samlIdpSsoSessionId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the saml idp sso sessions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlIdpSsoSessionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml idp sso sessions
	* @param end the upper bound of the range of saml idp sso sessions (not inclusive)
	* @return the range of saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.saml.model.SamlIdpSsoSession> getSamlIdpSsoSessions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.getSamlIdpSsoSessions(start, end);
	}

	/**
	* Returns the number of saml idp sso sessions.
	*
	* @return the number of saml idp sso sessions
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSamlIdpSsoSessionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.getSamlIdpSsoSessionsCount();
	}

	/**
	* Updates the saml idp sso session in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param samlIdpSsoSession the saml idp sso session
	* @return the saml idp sso session that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlIdpSsoSession updateSamlIdpSsoSession(
		com.liferay.saml.model.SamlIdpSsoSession samlIdpSsoSession)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.updateSamlIdpSsoSession(samlIdpSsoSession);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _samlIdpSsoSessionLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_samlIdpSsoSessionLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _samlIdpSsoSessionLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.saml.model.SamlIdpSsoSession addSamlIdpSsoSession(
		java.lang.String samlIdpSsoSessionKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.addSamlIdpSsoSession(samlIdpSsoSessionKey,
			serviceContext);
	}

	@Override
	public com.liferay.saml.model.SamlIdpSsoSession fetchSamlIdpSso(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.fetchSamlIdpSso(samlIdpSsoSessionKey);
	}

	@Override
	public com.liferay.saml.model.SamlIdpSsoSession getSamlIdpSso(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.getSamlIdpSso(samlIdpSsoSessionKey);
	}

	@Override
	public com.liferay.saml.model.SamlIdpSsoSession updateModifiedDate(
		java.lang.String samlIdpSsoSessionKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlIdpSsoSessionLocalService.updateModifiedDate(samlIdpSsoSessionKey);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SamlIdpSsoSessionLocalService getWrappedSamlIdpSsoSessionLocalService() {
		return _samlIdpSsoSessionLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSamlIdpSsoSessionLocalService(
		SamlIdpSsoSessionLocalService samlIdpSsoSessionLocalService) {
		_samlIdpSsoSessionLocalService = samlIdpSsoSessionLocalService;
	}

	@Override
	public SamlIdpSsoSessionLocalService getWrappedService() {
		return _samlIdpSsoSessionLocalService;
	}

	@Override
	public void setWrappedService(
		SamlIdpSsoSessionLocalService samlIdpSsoSessionLocalService) {
		_samlIdpSsoSessionLocalService = samlIdpSsoSessionLocalService;
	}

	private SamlIdpSsoSessionLocalService _samlIdpSsoSessionLocalService;
}