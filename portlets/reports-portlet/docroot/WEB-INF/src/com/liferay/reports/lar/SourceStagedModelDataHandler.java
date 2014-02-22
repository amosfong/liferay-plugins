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
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.service.ServiceContext;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.SourceLocalServiceUtil;
import com.liferay.reports.service.persistence.SourceUtil;

/**
 * @author Mate Thurzo
 */
public class SourceStagedModelDataHandler
	extends BaseStagedModelDataHandler<Source> {

	public static final String[] CLASS_NAMES = {Source.class.getName()};

	@Override
	public void deleteStagedModel(
			String uuid, long groupId, String className, String extraData)
		throws PortalException, SystemException {

		Source source = SourceLocalServiceUtil.fetchSourceByUuidAndGroupId(
			uuid, groupId);

		if (source != null) {
			SourceLocalServiceUtil.deleteSource(source);
		}
	}

	@Override
	public String[] getClassNames() {
		return CLASS_NAMES;
	}

	@Override
	public String getDisplayName(Source source) {
		return source.getName();
	}

	@Override
	protected void doExportStagedModel(
			PortletDataContext portletDataContext, Source source)
		throws Exception {

		Element sourceElement = portletDataContext.getExportDataElement(source);

		portletDataContext.addClassedModel(
			sourceElement, ExportImportPathUtil.getModelPath(source), source);
	}

	@Override
	protected void doImportStagedModel(
			PortletDataContext portletDataContext, Source source)
		throws Exception {

		long userId = portletDataContext.getUserId(source.getUserUuid());

		ServiceContext serviceContext = portletDataContext.createServiceContext(
			source);

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

		portletDataContext.importClassedModel(source, importedSource);
	}

}