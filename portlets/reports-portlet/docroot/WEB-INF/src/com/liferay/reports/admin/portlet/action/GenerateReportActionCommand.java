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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.reports.NoSuchDefinitionException;
import com.liferay.reports.model.Definition;
import com.liferay.reports.model.Entry;
import com.liferay.reports.service.DefinitionLocalServiceUtil;
import com.liferay.reports.service.EntryServiceUtil;
import com.liferay.reports.util.ReportsUtil;
import com.liferay.util.bridges.mvc.ActionCommand;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class GenerateReportActionCommand implements ActionCommand {

	public boolean processCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortletException {

		long definitionId = ParamUtil.getLong(portletRequest, "definitionId");

		if (definitionId <= 0) {
			SessionErrors.add(
				portletRequest, NoSuchDefinitionException.class.getName());

			return false;
		}

		String emailDelivery = ParamUtil.getString(
			portletRequest, "emailDelivery");
		String emailNotifications = ParamUtil.getString(
			portletRequest, "emailNotifications");
		String format = ParamUtil.getString(portletRequest, "format");
		String generatedReportsURL = ParamUtil.getString(
			portletRequest, "generatedReportsURL");

		try {
			Definition definition = DefinitionLocalServiceUtil.getDefinition(
				definitionId);

			String[] reportParameters = StringUtil.split(
				definition.getReportParameters());

			StringBundler sb = new StringBundler(4);

			for (String reportParameter : reportParameters) {
				if (Validator.isNull(reportParameter)) {
					continue;
				}

				String[] array = StringUtil.split(
					reportParameter, StringPool.EQUAL);

				String key = array[0];
				String value = ParamUtil.getString(
					portletRequest, "parameterValue" + key);

				if (Validator.isNull(value)) {
					Calendar calendar = ReportsUtil.getDate(
						portletRequest, key, true);

					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

					value = df.format(calendar.getTime());
				}

				sb.append(StringPool.COMMA);
				sb.append(key);
				sb.append(StringPool.EQUAL);
				sb.append(value);
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Entry.class.getName(), portletRequest);

			EntryServiceUtil.addEntry(
				definitionId, format, false, null, null, false, null,
				emailNotifications, emailDelivery, null, generatedReportsURL,
				sb.toString(), serviceContext);

			portletRequest.setAttribute(
				WebKeys.REDIRECT,
				ParamUtil.getString(portletRequest,"searchRequestsURL"));
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