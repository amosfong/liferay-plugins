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

package com.liferay.portal.workflow.kaleo.forms.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link KaleoProcessLinkLocalService}.
 * </p>
 *
 * @author    Marcellus Tavares
 * @see       KaleoProcessLinkLocalService
 * @generated
 */
public class KaleoProcessLinkLocalServiceWrapper
	implements KaleoProcessLinkLocalService,
		ServiceWrapper<KaleoProcessLinkLocalService> {
	public KaleoProcessLinkLocalServiceWrapper(
		KaleoProcessLinkLocalService kaleoProcessLinkLocalService) {
		_kaleoProcessLinkLocalService = kaleoProcessLinkLocalService;
	}

	/**
	* Adds the kaleo process link to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLink the kaleo process link
	* @return the kaleo process link that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink addKaleoProcessLink(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.addKaleoProcessLink(kaleoProcessLink);
	}

	/**
	* Creates a new kaleo process link with the primary key. Does not add the kaleo process link to the database.
	*
	* @param kaleoProcessLinkId the primary key for the new kaleo process link
	* @return the new kaleo process link
	*/
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink createKaleoProcessLink(
		long kaleoProcessLinkId) {
		return _kaleoProcessLinkLocalService.createKaleoProcessLink(kaleoProcessLinkId);
	}

	/**
	* Deletes the kaleo process link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @return the kaleo process link that was removed
	* @throws PortalException if a kaleo process link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink deleteKaleoProcessLink(
		long kaleoProcessLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.deleteKaleoProcessLink(kaleoProcessLinkId);
	}

	/**
	* Deletes the kaleo process link from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLink the kaleo process link
	* @return the kaleo process link that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink deleteKaleoProcessLink(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.deleteKaleoProcessLink(kaleoProcessLink);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _kaleoProcessLinkLocalService.dynamicQuery();
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
		return _kaleoProcessLinkLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoProcessLinkLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _kaleoProcessLinkLocalService.dynamicQuery(dynamicQuery, start,
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
		return _kaleoProcessLinkLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchKaleoProcessLink(
		long kaleoProcessLinkId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.fetchKaleoProcessLink(kaleoProcessLinkId);
	}

	/**
	* Returns the kaleo process link with the primary key.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @return the kaleo process link
	* @throws PortalException if a kaleo process link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink getKaleoProcessLink(
		long kaleoProcessLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.getKaleoProcessLink(kaleoProcessLinkId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the kaleo process links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.workflow.kaleo.forms.model.impl.KaleoProcessLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of kaleo process links
	* @param end the upper bound of the range of kaleo process links (not inclusive)
	* @return the range of kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> getKaleoProcessLinks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.getKaleoProcessLinks(start, end);
	}

	/**
	* Returns the number of kaleo process links.
	*
	* @return the number of kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public int getKaleoProcessLinksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.getKaleoProcessLinksCount();
	}

	/**
	* Updates the kaleo process link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLink the kaleo process link
	* @return the kaleo process link that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.updateKaleoProcessLink(kaleoProcessLink);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _kaleoProcessLinkLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_kaleoProcessLinkLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _kaleoProcessLinkLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink addKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName,
		long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.addKaleoProcessLink(kaleoProcessId,
			workflowTaskName, ddmTemplateId);
	}

	public void deleteKaleoProcessLinks(long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoProcessLinkLocalService.deleteKaleoProcessLinks(kaleoProcessId);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.fetchKaleoProcessLink(kaleoProcessId,
			workflowTaskName);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> getKaleoProcessLinks(
		long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.getKaleoProcessLinks(kaleoProcessId);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessLinkId, long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.updateKaleoProcessLink(kaleoProcessLinkId,
			kaleoProcessId);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessLinkId, long kaleoProcessId,
		java.lang.String workflowTaskName, long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.updateKaleoProcessLink(kaleoProcessLinkId,
			kaleoProcessId, workflowTaskName, ddmTemplateId);
	}

	public com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName,
		long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoProcessLinkLocalService.updateKaleoProcessLink(kaleoProcessId,
			workflowTaskName, ddmTemplateId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public KaleoProcessLinkLocalService getWrappedKaleoProcessLinkLocalService() {
		return _kaleoProcessLinkLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedKaleoProcessLinkLocalService(
		KaleoProcessLinkLocalService kaleoProcessLinkLocalService) {
		_kaleoProcessLinkLocalService = kaleoProcessLinkLocalService;
	}

	public KaleoProcessLinkLocalService getWrappedService() {
		return _kaleoProcessLinkLocalService;
	}

	public void setWrappedService(
		KaleoProcessLinkLocalService kaleoProcessLinkLocalService) {
		_kaleoProcessLinkLocalService = kaleoProcessLinkLocalService;
	}

	private KaleoProcessLinkLocalService _kaleoProcessLinkLocalService;
}