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

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.SourceLocalServiceUtil;
import com.liferay.reports.util.ReportsUtil;
import com.liferay.util.bridges.mvc.BaseActionCommand;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 */
public class TestDataSourceActionCommand extends BaseActionCommand {

	@Override
	protected void doProcessCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws Exception {

		long sourceId = ParamUtil.getLong(portletRequest, "sourceId");

		Source source = SourceLocalServiceUtil.getSource(sourceId);

		ReportsUtil.validateJDBCConnection(
			source.getDriverClassName(), source.getDriverUrl(),
			source.getDriverUserName(), source.getDriverPassword());
	}

}