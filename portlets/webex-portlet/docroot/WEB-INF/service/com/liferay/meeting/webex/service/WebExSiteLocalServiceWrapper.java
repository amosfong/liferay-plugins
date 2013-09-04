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
 * This class is a wrapper for {@link WebExSiteLocalService}.
 * </p>
 *
 * @author    Anant Singh
 * @see       WebExSiteLocalService
 * @generated
 */
public class WebExSiteLocalServiceWrapper implements WebExSiteLocalService,
	ServiceWrapper<WebExSiteLocalService> {
	public WebExSiteLocalServiceWrapper(
		WebExSiteLocalService webExSiteLocalService) {
		_webExSiteLocalService = webExSiteLocalService;
	}

	/**
	* Adds the web ex site to the database. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @return the web ex site that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite addWebExSite(
		com.liferay.meeting.webex.model.WebExSite webExSite)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.addWebExSite(webExSite);
	}

	/**
	* Creates a new web ex site with the primary key. Does not add the web ex site to the database.
	*
	* @param webExSiteId the primary key for the new web ex site
	* @return the new web ex site
	*/
	public com.liferay.meeting.webex.model.WebExSite createWebExSite(
		long webExSiteId) {
		return _webExSiteLocalService.createWebExSite(webExSiteId);
	}

	/**
	* Deletes the web ex site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site that was removed
	* @throws PortalException if a web ex site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite deleteWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.deleteWebExSite(webExSiteId);
	}

	/**
	* Deletes the web ex site from the database. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @return the web ex site that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite deleteWebExSite(
		com.liferay.meeting.webex.model.WebExSite webExSite)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.deleteWebExSite(webExSite);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _webExSiteLocalService.dynamicQuery();
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
		return _webExSiteLocalService.dynamicQuery(dynamicQuery);
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
		return _webExSiteLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _webExSiteLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _webExSiteLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.meeting.webex.model.WebExSite fetchWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.fetchWebExSite(webExSiteId);
	}

	/**
	* Returns the web ex site with the primary key.
	*
	* @param webExSiteId the primary key of the web ex site
	* @return the web ex site
	* @throws PortalException if a web ex site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite getWebExSite(
		long webExSiteId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.getWebExSite(webExSiteId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the web ex site with the UUID in the group.
	*
	* @param uuid the UUID of web ex site
	* @param groupId the group id of the web ex site
	* @return the web ex site
	* @throws PortalException if a web ex site with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite getWebExSiteByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.getWebExSiteByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the web ex sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of web ex sites
	* @param end the upper bound of the range of web ex sites (not inclusive)
	* @return the range of web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.getWebExSites(start, end);
	}

	/**
	* Returns the number of web ex sites.
	*
	* @return the number of web ex sites
	* @throws SystemException if a system exception occurred
	*/
	public int getWebExSitesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.getWebExSitesCount();
	}

	/**
	* Updates the web ex site in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @return the web ex site that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite updateWebExSite(
		com.liferay.meeting.webex.model.WebExSite webExSite)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.updateWebExSite(webExSite);
	}

	/**
	* Updates the web ex site in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param webExSite the web ex site
	* @param merge whether to merge the web ex site with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the web ex site that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.meeting.webex.model.WebExSite updateWebExSite(
		com.liferay.meeting.webex.model.WebExSite webExSite, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.updateWebExSite(webExSite, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _webExSiteLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_webExSiteLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _webExSiteLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void addWebExSite(long userId, long groupId, java.lang.String name,
		java.lang.String apiURL, java.lang.String login,
		java.lang.String password, java.lang.String partnerKey, long siteKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExSiteLocalService.addWebExSite(userId, groupId, name, apiURL,
			login, password, partnerKey, siteKey, serviceContext);
	}

	public com.liferay.meeting.webex.model.WebExSite fetchSiteKeyWebExSite(
		long siteKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.fetchSiteKeyWebExSite(siteKey);
	}

	public com.liferay.meeting.webex.model.WebExSite getSiteKeyWebExSite(
		long siteKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.getSiteKeyWebExSite(siteKey);
	}

	public java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.getWebExSites(groupId, start, end);
	}

	public java.util.List<com.liferay.meeting.webex.model.WebExSite> getWebExSites(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _webExSiteLocalService.getWebExSites(groupId, start, end, obc);
	}

	public void updateWebExSite(long webExSiteId, java.lang.String apiURL,
		java.lang.String login, java.lang.String password,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_webExSiteLocalService.updateWebExSite(webExSiteId, apiURL, login,
			password, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public WebExSiteLocalService getWrappedWebExSiteLocalService() {
		return _webExSiteLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedWebExSiteLocalService(
		WebExSiteLocalService webExSiteLocalService) {
		_webExSiteLocalService = webExSiteLocalService;
	}

	public WebExSiteLocalService getWrappedService() {
		return _webExSiteLocalService;
	}

	public void setWrappedService(WebExSiteLocalService webExSiteLocalService) {
		_webExSiteLocalService = webExSiteLocalService;
	}

	private WebExSiteLocalService _webExSiteLocalService;
}