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
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.reports.model.Definition;

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
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Definition definition)
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

		portletDataContext.addClassedModel(definitionElement, path, definition);

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

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Definition definition)
		throws Exception {

		long userId = portletDataContext.getUserId(definition.getUserUuid());
		long sourceId = MapUtil.getLong(sourceIds, definition.getSourceId());
		String fileName = null;
		InputStream inputStream = null;

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			definitionElement, definition);

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
				definition, importedDefinition);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

}
