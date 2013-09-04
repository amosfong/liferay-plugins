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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link WebExAccountLocalService}.
 * </p>
 *
 * @author    Anant Singh
 * @see       WebExAccountLocalService
 * @generated
 */
public class WebExAccountLocalServiceWrapper implements WebExAccountLocalService,
	ServiceWrapper<WebExAccountLocalService> {
	public WebExAccountLocalServiceWrapper(
		WebExAccountLocalService webExAccountLocalService) {
		_webExAccountLocalService = webExAccountLocalService;
	}

	/**
	* Adds the web ex account to the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount addWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.addWebExAccount(webExAccount);
	}

	/**
	* Creates a new web ex account with the primary key. Does not add the web ex account to the database.
	*
	* @param webExAccountId the primary key for the new web ex account
	* @return the new web ex account
	*/
	public com.liferay.meeting.webex.model.WebExAccount createWebExAccount(
		long webExAccountId) {
		return _webExAccountLocalService.createWebExAccount(webExAccountId);
	}

	/**
	* Deletes the web ex account with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account that was removed
	* @throws PortalException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount deleteWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.deleteWebExAccount(webExAccountId);
	}

	/**
	* Deletes the web ex account from the database. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount deleteWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.deleteWebExAccount(webExAccount);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _webExAccountLocalService.dynamicQuery();
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
		return _webExAccountLocalService.dynamicQuery(dynamicQuery);
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
		return _webExAccountLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _webExAccountLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _webExAccountLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.meeting.webex.model.WebExAccount fetchWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.fetchWebExAccount(webExAccountId);
	}

	/**
	* Returns the web ex account with the primary key.
	*
	* @param webExAccountId the primary key of the web ex account
	* @return the web ex account
	* @throws PortalException if a web ex account with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount getWebExAccount(
		long webExAccountId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.getWebExAccount(webExAccountId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.getPersistedModel(primaryKeyObj);
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
	public com.liferay.meeting.webex.model.WebExAccount getWebExAccountByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.getWebExAccountByUuidAndGroupId(uuid,
			groupId);
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
	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExAccounts(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.getWebExAccounts(start, end);
	}

	/**
	* Returns the number of web ex accounts.
	*
	* @return the number of web ex accounts
	* @throws SystemException if a system exception occurred
	*/
	public int getWebExAccountsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.getWebExAccountsCount();
	}

	/**
	* Updates the web ex account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @return the web ex account that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount updateWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.updateWebExAccount(webExAccount);
	}

	/**
	* Updates the web ex account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExAccount the web ex account
	* @param merge whether to merge the web ex account with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the web ex account that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExAccount updateWebExAccount(
		com.liferay.meeting.webex.model.WebExAccount webExAccount, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.updateWebExAccount(webExAccount, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _webExAccountLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_webExAccountLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _webExAccountLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addWebExAccount(long userId, long groupId, long webExSiteId,
		java.lang.String login, java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExAccountLocalService.addWebExAccount(userId, groupId, webExSiteId,
			login, password, serviceContext);
	}

	public void deleteWebExSiteWebExAccounts(long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExAccountLocalService.deleteWebExSiteWebExAccounts(groupId,
			webExSiteId);
	}

	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.getWebExSiteWebExAccounts(groupId,
			webExSiteId);
	}

	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.getWebExSiteWebExAccounts(groupId,
			webExSiteId, start, end);
	}

	public java.util.List<com.liferay.meeting.webex.model.WebExAccount> getWebExSiteWebExAccounts(
		long groupId, long webExSiteId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.getWebExSiteWebExAccounts(groupId,
			webExSiteId, start, end, obc);
	}

	public int getWebExSiteWebExAccountsCount(long groupId, long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExAccountLocalService.getWebExSiteWebExAccountsCount(groupId,
			webExSiteId);
	}

	public void updateWebExAccount(long webExAccountId,
		java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExAccountLocalService.updateWebExAccount(webExAccountId, password,
			serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public WebExAccountLocalService getWrappedWebExAccountLocalService() {
		return _webExAccountLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedWebExAccountLocalService(
		WebExAccountLocalService webExAccountLocalService) {
		_webExAccountLocalService = webExAccountLocalService;
	}

	public WebExAccountLocalService getWrappedService() {
		return _webExAccountLocalService;
	}

	public void setWrappedService(
		WebExAccountLocalService webExAccountLocalService) {
		_webExAccountLocalService = webExAccountLocalService;
	}

	private WebExAccountLocalService _webExAccountLocalService;
}