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
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.reports.DefinitionFileException;
import com.liferay.reports.PortletConstants;
import com.liferay.reports.model.Definition;
import com.liferay.reports.model.impl.DefinitionImpl;
import com.liferay.reports.service.DefinitionServiceUtil;
import com.liferay.reports.util.ReportsUtil;
import com.liferay.util.bridges.mvc.ActionCommand;

import java.io.InputStream;

import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class EditDefinitionActionCommand implements ActionCommand {

	public boolean processCommand(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortletException {

		InputStream inputStream = null;
		String fileName = null;
		String viewDefinitionsURL;
		long sourceId = -1;
		long definitionId = -1;
		Map<Locale, String> definitionNameMap = null;
		Map<Locale, String> definitionDescriptionMap = null;
		String reportParameters = null;
		String cmd = null;

		ActionRequest actionRequest = (ActionRequest)portletRequest;

		String contentType = actionRequest.getContentType();

		UploadPortletRequest uploadPortletRequest = null;

		try {
			if (contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA)) {
				uploadPortletRequest = PortalUtil.getUploadPortletRequest(
					portletRequest);

				fileName = uploadPortletRequest.getFileName("templateFile");

				definitionId = ParamUtil.getLong(
					uploadPortletRequest, "definitionId");

				definitionNameMap = ReportsUtil.getLocalizationMap(
					uploadPortletRequest, "definitionName");

				definitionDescriptionMap = ReportsUtil.getLocalizationMap(
					uploadPortletRequest, "definitionDescription");

				reportParameters = ParamUtil.getString(
					uploadPortletRequest, "reportParameters");

				sourceId = ParamUtil.getLong(uploadPortletRequest, "sourceId");

				cmd = ParamUtil.getString(uploadPortletRequest, Constants.CMD);

				viewDefinitionsURL = ParamUtil.getString(
					uploadPortletRequest, "viewDefinitionsURL");

				inputStream = uploadPortletRequest.getFileAsStream(
					"templateFile");
			}
			else {
				definitionId = ParamUtil.getLong(
					portletRequest, "definitionId");

				definitionNameMap = LocalizationUtil.getLocalizationMap(
					portletRequest, "definitionName");

				definitionDescriptionMap = LocalizationUtil.getLocalizationMap(
					portletRequest, "definitionDescription");

				reportParameters = ParamUtil.getString(
					portletRequest, "reportParameters");

				sourceId = ParamUtil.getLong(portletRequest, "sourceId");

				cmd = ParamUtil.getString(portletRequest, Constants.CMD);

				viewDefinitionsURL = ParamUtil.getString(
					portletRequest, "viewDefinitionsURL");
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Definition.class.getName(), portletRequest);

			Definition definition = null;

			if (cmd.equals(Constants.ADD)) {
				if (Validator.isNull(fileName)) {
					throw new DefinitionFileException();
				}

				definition = DefinitionServiceUtil.addDefinition(
					definitionNameMap, definitionDescriptionMap, sourceId,
					reportParameters, fileName, inputStream, serviceContext);
			}
			else if (cmd.equals(Constants.UPDATE)) {
				definition = DefinitionServiceUtil.updateDefinition(
					definitionId, definitionNameMap, definitionDescriptionMap,
					sourceId, reportParameters, fileName, inputStream,
					serviceContext);
			}

			if (uploadPortletRequest != null) {
				uploadPortletRequest.setAttribute(
					PortletConstants.FILE_NAME, fileName);

				uploadPortletRequest.setAttribute(
					PortletConstants.DEFINITION, definition);
			}
			else {
				portletRequest.setAttribute(
					PortletConstants.DEFINITION, definition);
			}
			portletRequest.setAttribute(WebKeys.REDIRECT, viewDefinitionsURL);
		}
		catch (PortalException pe) {
			Definition definition = new DefinitionImpl();

			if (cmd.equals(Constants.ADD)) {
				definition.setNew(true);
			}

			definition.setSourceId(sourceId);
			definition.setNameMap(definitionNameMap);
			definition.setDescriptionMap(definitionDescriptionMap);
			definition.setReportParameters(reportParameters);

			if (uploadPortletRequest != null) {
				uploadPortletRequest.setAttribute(
					PortletConstants.DEFINITION, definition);
			}
			else {
				portletRequest.setAttribute(
					PortletConstants.DEFINITION, definition);
			}

			SessionErrors.add(portletRequest, pe.getClass());

			return false;
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}

		return true;

	}

}