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

package com.liferay.oauth.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OAuthUserLocalService}.
 * </p>
 *
 * @author    Ivica Cardic
 * @see       OAuthUserLocalService
 * @generated
 */
public class OAuthUserLocalServiceWrapper implements OAuthUserLocalService,
	ServiceWrapper<OAuthUserLocalService> {
	public OAuthUserLocalServiceWrapper(
		OAuthUserLocalService oAuthUserLocalService) {
		_oAuthUserLocalService = oAuthUserLocalService;
	}

	/**
	* Adds the o auth user to the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthUser the o auth user
	* @return the o auth user that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthUser addOAuthUser(
		com.liferay.oauth.model.OAuthUser oAuthUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.addOAuthUser(oAuthUser);
	}

	/**
	* Creates a new o auth user with the primary key. Does not add the o auth user to the database.
	*
	* @param oAuthUserId the primary key for the new o auth user
	* @return the new o auth user
	*/
	public com.liferay.oauth.model.OAuthUser createOAuthUser(long oAuthUserId) {
		return _oAuthUserLocalService.createOAuthUser(oAuthUserId);
	}

	/**
	* Deletes the o auth user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthUserId the primary key of the o auth user
	* @return the o auth user that was removed
	* @throws PortalException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthUser deleteOAuthUser(long oAuthUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.deleteOAuthUser(oAuthUserId);
	}

	/**
	* Deletes the o auth user from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthUser the o auth user
	* @return the o auth user that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthUser deleteOAuthUser(
		com.liferay.oauth.model.OAuthUser oAuthUser)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.deleteOAuthUser(oAuthUser);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _oAuthUserLocalService.dynamicQuery();
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
		return _oAuthUserLocalService.dynamicQuery(dynamicQuery);
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
		return _oAuthUserLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _oAuthUserLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _oAuthUserLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.oauth.model.OAuthUser fetchOAuthUser(long oAuthUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.fetchOAuthUser(oAuthUserId);
	}

	/**
	* Returns the o auth user with the primary key.
	*
	* @param oAuthUserId the primary key of the o auth user
	* @return the o auth user
	* @throws PortalException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthUser getOAuthUser(long oAuthUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getOAuthUser(oAuthUserId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the o auth users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth users
	* @param end the upper bound of the range of o auth users (not inclusive)
	* @return the range of o auth users
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.oauth.model.OAuthUser> getOAuthUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getOAuthUsers(start, end);
	}

	/**
	* Returns the number of o auth users.
	*
	* @return the number of o auth users
	* @throws SystemException if a system exception occurred
	*/
	public int getOAuthUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getOAuthUsersCount();
	}

	/**
	* Updates the o auth user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthUser the o auth user
	* @return the o auth user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthUser updateOAuthUser(
		com.liferay.oauth.model.OAuthUser oAuthUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.updateOAuthUser(oAuthUser);
	}

	/**
	* Updates the o auth user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthUser the o auth user
	* @param merge whether to merge the o auth user with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the o auth user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.oauth.model.OAuthUser updateOAuthUser(
		com.liferay.oauth.model.OAuthUser oAuthUser, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.updateOAuthUser(oAuthUser, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _oAuthUserLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_oAuthUserLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _oAuthUserLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.oauth.model.OAuthUser addOAuthUser(long userId,
		long oAuthApplicationId, java.lang.String accessToken,
		java.lang.String accessSecret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.addOAuthUser(userId, oAuthApplicationId,
			accessToken, accessSecret, serviceContext);
	}

	public com.liferay.oauth.model.OAuthUser deleteOAuthUser(long userId,
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.deleteOAuthUser(userId, oAuthApplicationId);
	}

	public com.liferay.oauth.model.OAuthUser fetchOAuthUser(long userId,
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.fetchOAuthUser(userId, oAuthApplicationId);
	}

	public com.liferay.oauth.model.OAuthUser fetchOAuthUser(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.fetchOAuthUser(accessToken);
	}

	public java.util.List<com.liferay.oauth.model.OAuthUser> getOAuthApplicationOAuthUsers(
		long oAuthApplicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getOAuthApplicationOAuthUsers(oAuthApplicationId,
			start, end, orderByComparator);
	}

	public int getOAuthApplicationOAuthUsersCount(long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getOAuthApplicationOAuthUsersCount(oAuthApplicationId);
	}

	public com.liferay.oauth.model.OAuthUser getOAuthUser(long userId,
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getOAuthUser(userId, oAuthApplicationId);
	}

	public com.liferay.oauth.model.OAuthUser getOAuthUser(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getOAuthUser(accessToken);
	}

	public java.util.List<com.liferay.oauth.model.OAuthUser> getUserOAuthUsers(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getUserOAuthUsers(userId, start, end,
			orderByComparator);
	}

	public int getUserOAuthUsersCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.getUserOAuthUsersCount(userId);
	}

	public com.liferay.oauth.model.OAuthUser updateOAuthUser(long userId,
		long oAuthApplicationId, java.lang.String accessToken,
		java.lang.String accessSecret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _oAuthUserLocalService.updateOAuthUser(userId,
			oAuthApplicationId, accessToken, accessSecret, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OAuthUserLocalService getWrappedOAuthUserLocalService() {
		return _oAuthUserLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOAuthUserLocalService(
		OAuthUserLocalService oAuthUserLocalService) {
		_oAuthUserLocalService = oAuthUserLocalService;
	}

	public OAuthUserLocalService getWrappedService() {
		return _oAuthUserLocalService;
	}

	public void setWrappedService(OAuthUserLocalService oAuthUserLocalService) {
		_oAuthUserLocalService = oAuthUserLocalService;
	}

	private OAuthUserLocalService _oAuthUserLocalService;
}