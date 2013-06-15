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

package com.liferay.portal.workflow.kaleo.forms.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for KaleoProcessLink. This utility wraps
 * {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Marcellus Tavares
 * @see KaleoProcessLinkLocalService
 * @see com.liferay.portal.workflow.kaleo.forms.service.base.KaleoProcessLinkLocalServiceBaseImpl
 * @see com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkLocalServiceImpl
 * @generated
 */
public class KaleoProcessLinkLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.workflow.kaleo.forms.service.impl.KaleoProcessLinkLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the kaleo process link to the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLink the kaleo process link
	* @return the kaleo process link that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink addKaleoProcessLink(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addKaleoProcessLink(kaleoProcessLink);
	}

	/**
	* Creates a new kaleo process link with the primary key. Does not add the kaleo process link to the database.
	*
	* @param kaleoProcessLinkId the primary key for the new kaleo process link
	* @return the new kaleo process link
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink createKaleoProcessLink(
		long kaleoProcessLinkId) {
		return getService().createKaleoProcessLink(kaleoProcessLinkId);
	}

	/**
	* Deletes the kaleo process link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @return the kaleo process link that was removed
	* @throws PortalException if a kaleo process link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink deleteKaleoProcessLink(
		long kaleoProcessLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoProcessLink(kaleoProcessLinkId);
	}

	/**
	* Deletes the kaleo process link from the database. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLink the kaleo process link
	* @return the kaleo process link that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink deleteKaleoProcessLink(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteKaleoProcessLink(kaleoProcessLink);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchKaleoProcessLink(
		long kaleoProcessLinkId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchKaleoProcessLink(kaleoProcessLinkId);
	}

	/**
	* Returns the kaleo process link with the primary key.
	*
	* @param kaleoProcessLinkId the primary key of the kaleo process link
	* @return the kaleo process link
	* @throws PortalException if a kaleo process link with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink getKaleoProcessLink(
		long kaleoProcessLinkId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoProcessLink(kaleoProcessLinkId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> getKaleoProcessLinks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoProcessLinks(start, end);
	}

	/**
	* Returns the number of kaleo process links.
	*
	* @return the number of kaleo process links
	* @throws SystemException if a system exception occurred
	*/
	public static int getKaleoProcessLinksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoProcessLinksCount();
	}

	/**
	* Updates the kaleo process link in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param kaleoProcessLink the kaleo process link
	* @return the kaleo process link that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink kaleoProcessLink)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateKaleoProcessLink(kaleoProcessLink);
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

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink addKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName,
		long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addKaleoProcessLink(kaleoProcessId, workflowTaskName,
			ddmTemplateId);
	}

	public static void deleteKaleoProcessLinks(long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteKaleoProcessLinks(kaleoProcessId);
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink fetchKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchKaleoProcessLink(kaleoProcessId, workflowTaskName);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink> getKaleoProcessLinks(
		long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getKaleoProcessLinks(kaleoProcessId);
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessLinkId, long kaleoProcessId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoProcessLink(kaleoProcessLinkId, kaleoProcessId);
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessLinkId, long kaleoProcessId,
		java.lang.String workflowTaskName, long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoProcessLink(kaleoProcessLinkId, kaleoProcessId,
			workflowTaskName, ddmTemplateId);
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink updateKaleoProcessLink(
		long kaleoProcessId, java.lang.String workflowTaskName,
		long ddmTemplateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateKaleoProcessLink(kaleoProcessId, workflowTaskName,
			ddmTemplateId);
	}

	public static void clearService() {
		_service = null;
	}

	public static KaleoProcessLinkLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					KaleoProcessLinkLocalService.class.getName());

			if (invokableLocalService instanceof KaleoProcessLinkLocalService) {
				_service = (KaleoProcessLinkLocalService)invokableLocalService;
			}
			else {
				_service = new KaleoProcessLinkLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(KaleoProcessLinkLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(KaleoProcessLinkLocalService service) {
	}

	private static KaleoProcessLinkLocalService _service;
}