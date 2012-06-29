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

package com.liferay.reports.admin.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.reports.SourceURLException;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.SourceLocalServiceUtil;
import com.liferay.reports.util.ReportsUtil;
import com.liferay.util.bridges.mvc.ActionCommand;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 */
public class TestDataSourceActionCommand implements ActionCommand {

	public boolean processCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortletException {

		long sourceId = ParamUtil.getLong(portletRequest, "sourceId");

		try {
			Source source = SourceLocalServiceUtil.getSource(sourceId);

			ReportsUtil.validateJDBCConnection(
				source.getDriverClassName(), source.getDriverUrl(),
				source.getDriverUserName(), source.getDriverPassword());

			SessionMessages.add(portletRequest, "successful-connect-database");
		}
		catch (SourceURLException pe) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to connect with database");
			}

			SessionErrors.add(portletRequest, "could-not-connect-database");
		}
		catch (PortalException pe) {
			SessionErrors.add(portletRequest, pe.getClass());
		}
		catch (SystemException se) {
			throw new PortletException(se);
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TestDataSourceActionCommand.class);

}