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

package com.liferay.reports.display.portlet;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.reports.admin.portlet.AdminPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * @author Gavin Wan
 */
public class DisplayPortlet extends AdminPortlet {

	@Override
	protected boolean callActionMethod(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (!actionName.equals("archiveRequest") &&
			!actionName.equals("deleteReport") &&
			!actionName.equals("deliverReport") &&
			!actionName.equals("unscheduleReportRequest")) {

			return false;
		}

		return super.callActionMethod(actionRequest, actionResponse);
	}

}