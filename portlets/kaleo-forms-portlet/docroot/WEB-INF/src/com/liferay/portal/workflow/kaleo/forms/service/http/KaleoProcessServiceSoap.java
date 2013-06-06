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

package com.liferay.portal.workflow.kaleo.forms.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess}, that is translated to a
 * {@link com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessServiceHttp
 * @see com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap
 * @see com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessServiceUtil
 * @generated
 */
public class KaleoProcessServiceSoap {
	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap addKaleoProcess(
		long groupId, long ddlRecordSetId, long ddmTemplateId,
		long[] kaleoProcessLinkIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess returnValue =
				KaleoProcessServiceUtil.addKaleoProcess(groupId,
					ddlRecordSetId, ddmTemplateId, kaleoProcessLinkIds,
					serviceContext);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap deleteKaleoProcess(
		long kaleoProcessId) throws RemoteException {
		try {
			com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess returnValue =
				KaleoProcessServiceUtil.deleteKaleoProcess(kaleoProcessId);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteKaleoProcessData(long kaleoProcessId)
		throws RemoteException {
		try {
			KaleoProcessServiceUtil.deleteKaleoProcessData(kaleoProcessId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap getKaleoProcess(
		long kaleoProcessId) throws RemoteException {
		try {
			com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess returnValue =
				KaleoProcessServiceUtil.getKaleoProcess(kaleoProcessId);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap[] getKaleoProcesses(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess> returnValue =
				KaleoProcessServiceUtil.getKaleoProcesses(groupId, start, end,
					orderByComparator);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getKaleoProcessesCount(long groupId)
		throws RemoteException {
		try {
			int returnValue = KaleoProcessServiceUtil.getKaleoProcessesCount(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap updateKaleoProcess(
		long kaleoProcessId, long ddmTemplateId, long[] kaleoProcessLinkIds,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess returnValue =
				KaleoProcessServiceUtil.updateKaleoProcess(kaleoProcessId,
					ddmTemplateId, kaleoProcessLinkIds, serviceContext);

			return com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KaleoProcessServiceSoap.class);
}