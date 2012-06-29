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
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.reports.NoSuchSourceException;
import com.liferay.reports.service.SourceServiceUtil;
import com.liferay.util.bridges.mvc.ActionCommand;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 */
public class DeleteDataSourceActionCommand implements ActionCommand {

	public boolean processCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortletException {

		long sourceId = ParamUtil.getLong(portletRequest, "sourceId");

		if (sourceId == -1) {
			SessionErrors.add(
				portletRequest, NoSuchSourceException.class.getName());

			return false;
		}

		try {
			SourceServiceUtil.deleteSource(sourceId);
		}
		catch (PortalException pe) {
			SessionErrors.add(portletRequest, pe.getClass());
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		return true;
	}

}