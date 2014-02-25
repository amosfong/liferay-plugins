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

package com.liferay.reports.lar;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.BaseStagedModelDataHandler;
import com.liferay.portal.kernel.lar.ExportImportPathUtil;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.reports.model.Definition;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.DefinitionLocalServiceUtil;
import com.liferay.reports.service.SourceLocalServiceUtil;
import com.liferay.reports.service.persistence.DefinitionUtil;

import java.io.InputStream;

import java.util.Map;

/**
 * @author Mate Thurzo
 */
public class DefinitionStagedModelDataHandler
	extends BaseStagedModelDataHandler<Definition> {

	public static final String[] CLASS_NAMES = {Definition.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Definition definition =
			DefinitionLocalServiceUtil.fetchDefinitionByUuidAndGroupId(
				uuid, groupId);

		if (definition != null) {
			DefinitionLocalServiceUtil.deleteDefinition(definition);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Definition definition) {
		return definition.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Definition definition)
		throws Exception {

		Source source = SourceLocalServiceUtil.fetchSource(
			definition.getSourceId());

		if (source != null) {
			StagedModelDataHandlerUtil.exportReferenceStagedModel(
				portletDataContext, definition, source,
				PortletDataContext.REFERENCE_TYPE_PARENT);
		}

		Element definitionElement = portletDataContext.getExportDataElement(
			definition);

		for (String fullFileName : definition.getAttachmentsFiles()) {
			Element attachmentElement = definitionElement.addElement(
				"attachment");

			String binPath = ExportImportPathUtil.getModelPath(
				definition, fullFileName);

			attachmentElement.addAttribute("bin-path", binPath);

			attachmentElement.addAttribute("full-file-name", fullFileName);

			byte[] bytes = DLStoreUtil.getFileAsBytes(
				definition.getCompanyId(), CompanyConstants.SYSTEM,
				fullFileName);

			portletDataContext.addZipEntry(binPath, bytes);
		}

		portletDataContext.addClassedModel(
			definitionElement, ExportImportPathUtil.getModelPath(definition),
			definition);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Definition definition)
		throws Exception {

		long userId = portletDataContext.getUserId(definition.getUserUuid());

		StagedModelDataHandlerUtil.importReferenceStagedModel(
			portletDataContext, definition, Source.class,
			definition.getSourceId());

		Map<Long, Long> sourceIds =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Source.class);

		long sourceId = MapUtil.getLong(
			sourceIds, definition.getSourceId(), definition.getSourceId());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			definition);

		Element definitionElement =
			portletDataContext.getImportDataStagedModelElement(definition);

		Element attachmentElement = definitionElement.element("attachment");

		String fileName = null;
		InputStream inputStream = null;

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
				definition, importedDefinition);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

}