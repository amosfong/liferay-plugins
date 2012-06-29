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
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.reports.PortletConstants;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.SourceServiceUtil;
import com.liferay.util.bridges.mvc.ActionCommand;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Gavin Wan
 */
public class EditDataSourceActionCommand implements ActionCommand {

	public boolean processCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortletException {

		String cmd = ParamUtil.getString(portletRequest, Constants.CMD);

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			portletRequest, "name");

		String driverClassName = ParamUtil.getString(
			portletRequest, "driverClassName");
		String driverUrl = ParamUtil.getString(portletRequest, "driverUrl");
		String driverUserName = ParamUtil.getString(
			portletRequest, "driverUserName");
		String driverPassword = ParamUtil.getString(
			portletRequest, "driverPassword");

		long sourceId = ParamUtil.getLong(portletRequest, "sourceId");

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Source.class.getName(), portletRequest);

			Source source = null;

			if (cmd.equals(Constants.ADD)) {
				source = SourceServiceUtil.addSource(
					nameMap, driverClassName, driverUrl, driverUserName,
					driverPassword, serviceContext);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				source = SourceServiceUtil.updateSource(
					sourceId, nameMap, driverClassName, driverUrl,
					driverUserName, driverPassword, serviceContext);
			}

			portletRequest.setAttribute(PortletConstants.DATA_SOURCE, source);
		}
		catch (PortalException pe) {
			SessionErrors.add(portletRequest, pe.getClass());
		}
		catch (SystemException se) {
			throw new PortletException(se);
		}

		return true;
	}

}