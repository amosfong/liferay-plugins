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

package com.liferay.reports.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.reports.service.DefinitionServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.reports.service.DefinitionServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.reports.model.DefinitionSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.reports.model.Definition}, that is translated to a
 * {@link com.liferay.reports.model.DefinitionSoap}. Methods that SOAP cannot
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
 * @author Brian Wing Shun Chan
 * @see DefinitionServiceHttp
 * @see com.liferay.reports.model.DefinitionSoap
 * @see com.liferay.reports.service.DefinitionServiceUtil
 * @generated
 */
public class DefinitionServiceSoap {
	public static com.liferay.reports.model.DefinitionSoap deleteDefinition(
		long definitionId) throws RemoteException {
		try {
			com.liferay.reports.model.Definition returnValue = DefinitionServiceUtil.deleteDefinition(definitionId);

			return com.liferay.reports.model.DefinitionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.reports.model.DefinitionSoap getDefinition(
		long definitionId) throws RemoteException {
		try {
			com.liferay.reports.model.Definition returnValue = DefinitionServiceUtil.getDefinition(definitionId);

			return com.liferay.reports.model.DefinitionSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.reports.model.DefinitionSoap[] getDefinitions(
		long groupId, java.lang.String definitionName,
		java.lang.String description, java.lang.String sourceId,
		java.lang.String reportName, boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.reports.model.Definition> returnValue = DefinitionServiceUtil.getDefinitions(groupId,
					definitionName, description, sourceId, reportName,
					andSearch, start, end, orderByComparator);

			return com.liferay.reports.model.DefinitionSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getDefinitionsCount(long groupId,
		java.lang.String definitionName, java.lang.String description,
		java.lang.String sourceId, java.lang.String reportName,
		boolean andSearch) throws RemoteException {
		try {
			int returnValue = DefinitionServiceUtil.getDefinitionsCount(groupId,
					definitionName, description, sourceId, reportName, andSearch);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DefinitionServiceSoap.class);
}