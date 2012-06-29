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

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.social.NoSuchRequestException;
import com.liferay.reports.service.EntryLocalServiceUtil;
import com.liferay.util.bridges.mvc.ActionCommand;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Gavin Wan
 */
public class DeliverReportActionCommand implements ActionCommand {

	public boolean processCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortletException {

		long entryId = ParamUtil.getLong(portletRequest, "entryId");

		if (entryId == -1) {
			SessionErrors.add(
				portletRequest, NoSuchRequestException.class.getName());

			return false;
		}

		String deliveryMailAddresses = ParamUtil.getString(
			portletRequest, "emailDelivery");
		String fileName = ParamUtil.getString(portletRequest, "fileName");

		try {
			EntryLocalServiceUtil.sendEmails(
				entryId, fileName, StringUtil.split(deliveryMailAddresses),
				false);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		return true;
	}

}