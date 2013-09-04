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

package com.liferay.meeting.webex.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the web ex account local service. This utility wraps {@link com.liferay.meeting.webex.service.impl.WebExAccountLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Anant Singh
 * @see WebExAccountLocalService
 * @see com.liferay.meeting.webex.service.base.WebExAccountLocalServiceBaseImpl
 * @see com.liferay.meeting.webex.service.impl.WebExAccountLocalServiceImpl
 * @generated
 */
public class WebExAccountLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.meeting.webex.service.impl.WebExAccountLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the web ex account to the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.meeting.webex.model.WebExAccount addWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addWebExAccount(webExAccount);
	}

	/**
	* Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	*
	* @param webExAccountId the primary key for the new web ex account
	* @return the new web ex account
	*/
	public static com.liferay.meeting.webex.model.WebExAccount createWebExAccount(
		long webExAccountId) {
		return getService().createWebExAccount(webExAccountId);
	}

	/**
	* Deletes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account that was removed
	* @throws PortalException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.meeting.webex.model.WebExAccount deleteWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteWebExAccount(webExAccountId);
	}

	/**
	* Deletes the web ex account from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.meeting.webex.model.WebExAccount deleteWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteWebExAccount(webExAccount);
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

	public static com.liferay.meeting.webex.model.WebExAccount fetchWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchWebExAccount(webExAccountId);
	}

	/**
	* Returns the web ex account with the primary key.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account
	* @throws PortalException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.meeting.webex.model.WebExAccount getWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWebExAccount(webExAccountId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the web ex account with the UUID in the group.
	*
	* @param uuid the UUID of web ex account
	* @param groupId the group id of the web ex account
	* @return the web ex account
	* @throws PortalException if a web ex account with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.meeting.webex.model.WebExAccount getWebExAccountByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getWebExAccountByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the web ex accounts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of web ex accounts
	* @param end the upper bound of the range of web ex accounts (not inclusive)
	* @return the range of web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccounts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWebExAccounts(start, end);
	}

	/**
	* Returns the number of web ex accounts.
	*
	* @return the number of web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public static int getWebExAccountsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWebExAccountsCount();
	}

	/**
	* Updates the web ex account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.meeting.webex.model.WebExAccount updateWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWebExAccount(webExAccount);
	}

	/**
	* Updates the web ex account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @param merge whether to merge the web ex account with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the web ex account that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.meeting.webex.model.WebExAccount updateWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateWebExAccount(webExAccount, merge);
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

	public static void addWebExAccount(long userId, long groupId,
		long webExSiteId, java.lang.String login, java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addWebExAccount(userId, groupId, webExSiteId, login, password,
			serviceContext);
	}

	public static void deleteWebExSiteWebExAccounts(long groupId,
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteWebExSiteWebExAccounts(groupId, webExSiteId);
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWebExSiteWebExAccounts(groupId, webExSiteId);
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWebExSiteWebExAccounts(groupId, webExSiteId, start, end);
	}

	public static java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getWebExSiteWebExAccounts(groupId, webExSiteId, start, end,
			obc);
	}

	public static int getWebExSiteWebExAccountsCount(long groupId,
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getWebExSiteWebExAccountsCount(groupId, webExSiteId);
	}

	public static void updateWebExAccount(long webExAccountId,
		java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateWebExAccount(webExAccountId, password, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static WebExAccountLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WebExAccountLocalService.class.getName());

			if (invokableLocalService instanceof WebExAccountLocalService) {
				_service = (WebExAccountLocalService)invokableLocalService;
			}
			else {
				_service = new WebExAccountLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(WebExAccountLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(WebExAccountLocalService service) {
	}

	private static WebExAccountLocalService _service;
}