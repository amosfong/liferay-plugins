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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the o auth user local service. This utility wraps {@link com.liferay.oauth.service.impl.OAuthUserLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Ivica Cardic
 * @see OAuthUserLocalService
 * @see com.liferay.oauth.service.base.OAuthUserLocalServiceBaseImpl
 * @see com.liferay.oauth.service.impl.OAuthUserLocalServiceImpl
 * @generated
 */
public class OAuthUserLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.oauth.service.impl.OAuthUserLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the o auth user to the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthUser the o auth user
	* @return the o auth user that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser addOAuthUser(
		com.liferay.oauth.model.OAuthUser oAuthUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOAuthUser(oAuthUser);
	}

	/**
	* Creates a new o auth user with the primary key. Does not add the o auth user to the database.
	*
	* @param oAuthUserId the primary key for the new o auth user
	* @return the new o auth user
	*/
	public static com.liferay.oauth.model.OAuthUser createOAuthUser(
		long oAuthUserId) {
		return getService().createOAuthUser(oAuthUserId);
	}

	/**
	* Deletes the o auth user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthUserId the primary key of the o auth user
	* @return the o auth user that was removed
	* @throws PortalException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser deleteOAuthUser(
		long oAuthUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOAuthUser(oAuthUserId);
	}

	/**
	* Deletes the o auth user from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthUser the o auth user
	* @return the o auth user that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser deleteOAuthUser(
		com.liferay.oauth.model.OAuthUser oAuthUser)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOAuthUser(oAuthUser);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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

	public static com.liferay.oauth.model.OAuthUser fetchOAuthUser(
		long oAuthUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOAuthUser(oAuthUserId);
	}

	/**
	* Returns the o auth user with the primary key.
	*
	* @param oAuthUserId the primary key of the o auth user
	* @return the o auth user
	* @throws PortalException if a o auth user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser getOAuthUser(
		long oAuthUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthUser(oAuthUserId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.oauth.model.OAuthUser> getOAuthUsers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthUsers(start, end);
	}

	/**
	* Returns the number of o auth users.
	*
	* @return the number of o auth users
	* @throws SystemException if a system exception occurred
	*/
	public static int getOAuthUsersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthUsersCount();
	}

	/**
	* Updates the o auth user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthUser the o auth user
	* @return the o auth user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser updateOAuthUser(
		com.liferay.oauth.model.OAuthUser oAuthUser)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOAuthUser(oAuthUser);
	}

	/**
	* Updates the o auth user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthUser the o auth user
	* @param merge whether to merge the o auth user with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the o auth user that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.oauth.model.OAuthUser updateOAuthUser(
		com.liferay.oauth.model.OAuthUser oAuthUser, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOAuthUser(oAuthUser, merge);
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

	public static com.liferay.oauth.model.OAuthUser addOAuthUser(long userId,
		long oAuthApplicationId, java.lang.String accessToken,
		java.lang.String accessSecret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addOAuthUser(userId, oAuthApplicationId, accessToken,
			accessSecret, serviceContext);
	}

	public static com.liferay.oauth.model.OAuthUser deleteOAuthUser(
		long userId, long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOAuthUser(userId, oAuthApplicationId);
	}

	public static com.liferay.oauth.model.OAuthUser fetchOAuthUser(
		long userId, long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOAuthUser(userId, oAuthApplicationId);
	}

	public static com.liferay.oauth.model.OAuthUser fetchOAuthUser(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOAuthUser(accessToken);
	}

	public static java.util.List<com.liferay.oauth.model.OAuthUser> getOAuthApplicationOAuthUsers(
		long oAuthApplicationId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOAuthApplicationOAuthUsers(oAuthApplicationId, start,
			end, orderByComparator);
	}

	public static int getOAuthApplicationOAuthUsersCount(
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOAuthApplicationOAuthUsersCount(oAuthApplicationId);
	}

	public static com.liferay.oauth.model.OAuthUser getOAuthUser(long userId,
		long oAuthApplicationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthUser(userId, oAuthApplicationId);
	}

	public static com.liferay.oauth.model.OAuthUser getOAuthUser(
		java.lang.String accessToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthUser(accessToken);
	}

	public static java.util.List<com.liferay.oauth.model.OAuthUser> getUserOAuthUsers(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getUserOAuthUsers(userId, start, end, orderByComparator);
	}

	public static int getUserOAuthUsersCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserOAuthUsersCount(userId);
	}

	public static com.liferay.oauth.model.OAuthUser updateOAuthUser(
		long userId, long oAuthApplicationId, java.lang.String accessToken,
		java.lang.String accessSecret,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateOAuthUser(userId, oAuthApplicationId, accessToken,
			accessSecret, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static OAuthUserLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OAuthUserLocalService.class.getName());

			if (invokableLocalService instanceof OAuthUserLocalService) {
				_service = (OAuthUserLocalService)invokableLocalService;
			}
			else {
				_service = new OAuthUserLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OAuthUserLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(OAuthUserLocalService service) {
	}

	private static OAuthUserLocalService _service;
}