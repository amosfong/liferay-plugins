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

package com.liferay.reports.lar;

import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.reports.model.Definition;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.DefinitionLocalServiceUtil;
import com.liferay.reports.service.SourceLocalServiceUtil;
import com.liferay.reports.service.persistence.DefinitionUtil;
import com.liferay.reports.service.persistence.SourceUtil;
import com.liferay.reports.util.PortletKeys;

import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 */
public class AdminPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "reports";

	public AdminPortletDataHandler() {
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "definitions", true,
				new PortletDataHandlerControl[] {
					new PortletDataHandlerBoolean(NAMESPACE, "sources")
				}));
		setPublishToLiveByDefault(true);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (!portletDataContext.addPrimaryKey(
				AdminPortletDataHandler.class, "deleteData")) {

			DefinitionUtil.removeByGroupId(
				portletDataContext.getScopeGroupId());

			SourceUtil.removeByGroupId(portletDataContext.getScopeGroupId());
		}

		return null;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPermissions(
			"com.liferay.reports.admin", portletDataContext.getScopeGroupId());

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("reports-admin-data");

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		if (portletDataContext.getBooleanParameter(NAMESPACE, "sources")) {
			List<Source> sources = SourceUtil.findByGroupId(
				portletDataContext.getScopeGroupId());

			for (Source source : sources) {
				exportSource(portletDataContext, rootElement, source);
			}
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "definitions")) {
			List<Definition> definitions = DefinitionUtil.findByGroupId(
				portletDataContext.getScopeGroupId());

			for (Definition definition : definitions) {
				exportDefinition(portletDataContext, rootElement, definition);
			}
		}

		return document.formattedString();
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPermissions(
			"com.liferay.reports.admin", portletDataContext.getSourceGroupId(),
			portletDataContext.getScopeGroupId());

		Document document = SAXReaderUtil.read(data);

		Element rootElement = document.getRootElement();

		Map<Long, Long> sourceIds = new HashMap<Long, Long>();

		if (portletDataContext.getBooleanParameter(NAMESPACE, "sources")) {
			for (Element sourceElement : rootElement.elements("source")) {
				String path = sourceElement.attributeValue("path");

				if (!portletDataContext.isPathNotProcessed(path)) {
					continue;
				}

				Source source = (Source)portletDataContext.getZipEntryAsObject(
					path);

				importSource(
					portletDataContext, sourceIds, sourceElement, source);
			}
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "definitions")) {
			for (Element definitionElement :
					rootElement.elements("definition")) {

				String path = definitionElement.attributeValue("path");

				if (!portletDataContext.isPathNotProcessed(path)) {
					continue;
				}

				Definition definition =
					(Definition)portletDataContext.getZipEntryAsObject(path);

				importDefinition(
					portletDataContext, sourceIds, definitionElement,
					definition);
			}
		}

		return null;
	}

	protected void exportDefinition(
			PortletDataContext portletDataContext, Element rootElement,
			Definition definition)
		throws Exception {

		if (!portletDataContext.isWithinDateRange(
				definition.getModifiedDate())) {

			return;
		}

		String path = getDefinitionPath(portletDataContext, definition);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element definitionElement = rootElement.addElement("definition");

		portletDataContext.addClassedModel(
			definitionElement, path, definition, NAMESPACE);

		for (String fullFileName : definition.getAttachmentsFiles()) {
			String binPath = getDefinitionAttachmentBinPath(
				portletDataContext, definition);
			byte[] bytes = DLStoreUtil.getFileAsBytes(
				definition.getCompanyId(), CompanyConstants.SYSTEM,
				fullFileName);

			Element attachmentElement = definitionElement.addElement(
				"attachment");

			attachmentElement.addAttribute("bin-path", binPath);
			attachmentElement.addAttribute("full-file-name", fullFileName);

			portletDataContext.addZipEntry(binPath, bytes);
		}
	}

	protected void exportSource(
			PortletDataContext portletDataContext, Element rootElement,
			Source source)
		throws Exception {

		if (!portletDataContext.isWithinDateRange(source.getModifiedDate())) {
			return;
		}

		String path = getSourcePath(portletDataContext, source);

		if (!portletDataContext.isPathNotProcessed(path)) {
			return;
		}

		Element sourceElement = rootElement.addElement("source");

		portletDataContext.addClassedModel(
			sourceElement, path, source, NAMESPACE);
	}

	protected String getDefinitionAttachmentBinPath(
		PortletDataContext portletDataContext, Definition definition) {

		StringBundler sb = new StringBundler(5);

		sb.append(portletDataContext.getPortletPath(PortletKeys.REPORTS_ADMIN));
		sb.append("/bin/");
		sb.append(definition.getDefinitionId());
		sb.append(StringPool.SLASH);
		sb.append(PortalUUIDUtil.generate());

		return sb.toString();
	}

	protected String getDefinitionPath(
		PortletDataContext portletDataContext, Definition definition) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(PortletKeys.REPORTS_ADMIN));
		sb.append("/definitions/");
		sb.append(definition.getDefinitionId());
		sb.append(".xml");

		return sb.toString();
	}

	protected String getSourcePath(
		PortletDataContext portletDataContext, Source source) {

		StringBundler sb = new StringBundler(4);

		sb.append(portletDataContext.getPortletPath(PortletKeys.REPORTS_ADMIN));
		sb.append("/sources/");
		sb.append(source.getSourceId());
		sb.append(".xml");

		return sb.toString();
	}

	protected void importDefinition(
			PortletDataContext portletDataContext, Map<Long, Long> sourceIds,
			Element definitionElement, Definition definition)
		throws Exception {

		long userId = portletDataContext.getUserId(definition.getUserUuid());
		long sourceId = MapUtil.getLong(sourceIds, definition.getSourceId());
		String fileName = null;
		InputStream inputStream = null;

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			definitionElement, definition, NAMESPACE);

		Element attachmentElement = definitionElement.element("attachment");

		try {
			if (attachmentElement != null) {
				String binPath = attachmentElement.attributeValue("bin-path");
				String fullFileName = attachmentElement.attributeValue(
					"full-file-name");

				fileName = FileUtil.getShortFileName(fullFileName);
				inputStream = portletDataContext.getZipEntryAsInputStream(
					binPath);
			}

			Definition importedDefinition = null;

			if (portletDataContext.isDataStrategyMirror()) {
				Definition existingDefinition = DefinitionUtil.fetchByUUID_G(
					definition.getUuid(), portletDataContext.getScopeGroupId());

				if (existingDefinition == null) {
					serviceContext.setUuid(definition.getUuid());

					importedDefinition =
						DefinitionLocalServiceUtil.addDefinition(
							userId, portletDataContext.getScopeGroupId(),
							definition.getNameMap(),
							definition.getDescriptionMap(), sourceId,
							definition.getReportParameters(), fileName,
							inputStream, serviceContext);
				}
				else {
					importedDefinition =
						DefinitionLocalServiceUtil.updateDefinition(
							existingDefinition.getDefinitionId(),
							definition.getNameMap(),
							definition.getDescriptionMap(), sourceId,
							definition.getReportParameters(), fileName,
							inputStream, serviceContext);
				}
			}
			else {
				importedDefinition = DefinitionLocalServiceUtil.addDefinition(
					userId, portletDataContext.getScopeGroupId(),
					definition.getNameMap(), definition.getDescriptionMap(),
					sourceId, definition.getReportParameters(), fileName,
					inputStream, serviceContext);
			}

			portletDataContext.importClassedModel(
				definition, importedDefinition, NAMESPACE);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	protected void importSource(
			PortletDataContext portletDataContext, Map<Long, Long> sourceIds,
			Element sourceElement, Source source)
		throws Exception {

		long userId = portletDataContext.getUserId(source.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			sourceElement, source, NAMESPACE);

		Source importedSource = null;

		if (portletDataContext.isDataStrategyMirror()) {
			Source existingSource = SourceUtil.fetchByUUID_G(
				source.getUuid(), portletDataContext.getScopeGroupId());

			if (existingSource == null) {
				serviceContext.setUuid(source.getUuid());

				importedSource = SourceLocalServiceUtil.addSource(
					userId, portletDataContext.getScopeGroupId(),
					source.getNameMap(), source.getDriverClassName(),
					source.getDriverUrl(), source.getDriverUserName(),
					source.getDriverPassword(), serviceContext);
			}
			else {
				importedSource = SourceLocalServiceUtil.updateSource(
					existingSource.getSourceId(), source.getNameMap(),
					source.getDriverClassName(), source.getDriverUrl(),
					source.getDriverUserName(), source.getDriverPassword(),
					serviceContext);
			}
		}
		else {
			importedSource = SourceLocalServiceUtil.addSource(
				userId, portletDataContext.getScopeGroupId(),
				source.getNameMap(), source.getDriverClassName(),
				source.getDriverUrl(), source.getDriverUserName(),
				source.getDriverPassword(), serviceContext);
		}

		sourceIds.put(source.getSourceId(), importedSource.getSourceId());

		portletDataContext.importClassedModel(
			source, importedSource, NAMESPACE);
	}

}