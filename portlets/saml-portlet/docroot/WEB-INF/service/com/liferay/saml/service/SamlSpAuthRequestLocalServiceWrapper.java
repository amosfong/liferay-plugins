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
 * Provides a wrapper for {@link SamlSpAuthRequestLocalService}.
 *
 * @author Mika Koivisto
 * @see SamlSpAuthRequestLocalService
 * @generated
 */
public class SamlSpAuthRequestLocalServiceWrapper
	implements SamlSpAuthRequestLocalService,
		ServiceWrapper<SamlSpAuthRequestLocalService> {
	public SamlSpAuthRequestLocalServiceWrapper(
		SamlSpAuthRequestLocalService samlSpAuthRequestLocalService) {
		_samlSpAuthRequestLocalService = samlSpAuthRequestLocalService;
	}

	/**
	* Adds the saml sp auth request to the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpAuthRequest the saml sp auth request
	* @return the saml sp auth request that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlSpAuthRequest addSamlSpAuthRequest(
		com.liferay.saml.model.SamlSpAuthRequest samlSpAuthRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.addSamlSpAuthRequest(samlSpAuthRequest);
	}

	/**
	* Creates a new saml sp auth request with the primary key. Does not add the saml sp auth request to the database.
	*
	* @param samlSpAuthnRequestId the primary key for the new saml sp auth request
	* @return the new saml sp auth request
	*/
	@Override
	public com.liferay.saml.model.SamlSpAuthRequest createSamlSpAuthRequest(
		long samlSpAuthnRequestId) {
		return _samlSpAuthRequestLocalService.createSamlSpAuthRequest(samlSpAuthnRequestId);
	}

	/**
	* Deletes the saml sp auth request with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpAuthnRequestId the primary key of the saml sp auth request
	* @return the saml sp auth request that was removed
	* @throws PortalException if a saml sp auth request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlSpAuthRequest deleteSamlSpAuthRequest(
		long samlSpAuthnRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.deleteSamlSpAuthRequest(samlSpAuthnRequestId);
	}

	/**
	* Deletes the saml sp auth request from the database. Also notifies the appropriate model listeners.
	*
	* @param samlSpAuthRequest the saml sp auth request
	* @return the saml sp auth request that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlSpAuthRequest deleteSamlSpAuthRequest(
		com.liferay.saml.model.SamlSpAuthRequest samlSpAuthRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.deleteSamlSpAuthRequest(samlSpAuthRequest);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _samlSpAuthRequestLocalService.dynamicQuery();
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
		return _samlSpAuthRequestLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpAuthRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _samlSpAuthRequestLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpAuthRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _samlSpAuthRequestLocalService.dynamicQuery(dynamicQuery, start,
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
		return _samlSpAuthRequestLocalService.dynamicQueryCount(dynamicQuery);
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
		return _samlSpAuthRequestLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.saml.model.SamlSpAuthRequest fetchSamlSpAuthRequest(
		long samlSpAuthnRequestId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.fetchSamlSpAuthRequest(samlSpAuthnRequestId);
	}

	/**
	* Returns the saml sp auth request with the primary key.
	*
	* @param samlSpAuthnRequestId the primary key of the saml sp auth request
	* @return the saml sp auth request
	* @throws PortalException if a saml sp auth request with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlSpAuthRequest getSamlSpAuthRequest(
		long samlSpAuthnRequestId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.getSamlSpAuthRequest(samlSpAuthnRequestId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the saml sp auth requests.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.saml.model.impl.SamlSpAuthRequestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of saml sp auth requests
	* @param end the upper bound of the range of saml sp auth requests (not inclusive)
	* @return the range of saml sp auth requests
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.saml.model.SamlSpAuthRequest> getSamlSpAuthRequests(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.getSamlSpAuthRequests(start, end);
	}

	/**
	* Returns the number of saml sp auth requests.
	*
	* @return the number of saml sp auth requests
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getSamlSpAuthRequestsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.getSamlSpAuthRequestsCount();
	}

	/**
	* Updates the saml sp auth request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param samlSpAuthRequest the saml sp auth request
	* @return the saml sp auth request that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.saml.model.SamlSpAuthRequest updateSamlSpAuthRequest(
		com.liferay.saml.model.SamlSpAuthRequest samlSpAuthRequest)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.updateSamlSpAuthRequest(samlSpAuthRequest);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _samlSpAuthRequestLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_samlSpAuthRequestLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _samlSpAuthRequestLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	@Override
	public com.liferay.saml.model.SamlSpAuthRequest addSamlSpAuthRequest(
		java.lang.String samlIdpEntityId,
		java.lang.String samlSpAuthRequestKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.addSamlSpAuthRequest(samlIdpEntityId,
			samlSpAuthRequestKey, serviceContext);
	}

	@Override
	public com.liferay.saml.model.SamlSpAuthRequest fetchSamlSpAuthRequest(
		java.lang.String samlIdpEntityId, java.lang.String samlSpAuthRequestKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.fetchSamlSpAuthRequest(samlIdpEntityId,
			samlSpAuthRequestKey);
	}

	@Override
	public com.liferay.saml.model.SamlSpAuthRequest getSamlSpAuthRequest(
		java.lang.String samlIdpEntityId, java.lang.String samlSpAuthRequestKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _samlSpAuthRequestLocalService.getSamlSpAuthRequest(samlIdpEntityId,
			samlSpAuthRequestKey);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public SamlSpAuthRequestLocalService getWrappedSamlSpAuthRequestLocalService() {
		return _samlSpAuthRequestLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedSamlSpAuthRequestLocalService(
		SamlSpAuthRequestLocalService samlSpAuthRequestLocalService) {
		_samlSpAuthRequestLocalService = samlSpAuthRequestLocalService;
	}

	@Override
	public SamlSpAuthRequestLocalService getWrappedService() {
		return _samlSpAuthRequestLocalService;
	}

	@Override
	public void setWrappedService(
		SamlSpAuthRequestLocalService samlSpAuthRequestLocalService) {
		_samlSpAuthRequestLocalService = samlSpAuthRequestLocalService;
	}

	private SamlSpAuthRequestLocalService _samlSpAuthRequestLocalService;
}