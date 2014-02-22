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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.DataLevel;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.reports.model.Definition;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.DefinitionLocalServiceUtil;
import com.liferay.reports.service.SourceLocalServiceUtil;
import com.liferay.reports.service.persistence.DefinitionExportActionableDynamicQuery;
import com.liferay.reports.service.persistence.SourceExportActionableDynamicQuery;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Michael C. Han
 * @author Mate Thurzo
 */
public class AdminPortletDataHandler extends BasePortletDataHandler {

	public static final String NAMESPACE = "reports";

	public AdminPortletDataHandler() {
		setDataLevel(DataLevel.SITE);
		setDeletionSystemEventStagedModelTypes(
			new StagedModelType(Definition.class),
			new StagedModelType(Source.class));
		setExportControls(
			new PortletDataHandlerBoolean(
				NAMESPACE, "definitions", true, false,
				new PortletDataHandlerControl[] {
					new PortletDataHandlerBoolean(
						NAMESPACE, "sources", true, false, null,
						Source.class.getName())
				},
				Definition.class.getName()));
		setPublishToLiveByDefault(true);
	}

	@Override
	protected PortletPreferences doDeleteData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		if (portletDataContext.addPrimaryKey(
				AdminPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		DefinitionLocalServiceUtil.deleteDefinitions(
			portletDataContext.getScopeGroupId());

		SourceLocalServiceUtil.deleteSources(
			portletDataContext.getScopeGroupId());

		return portletPreferences;
	}

	@Override
	protected String doExportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		portletDataContext.addPortletPermissions(RESOURCE_NAME);

		Element rootElement = addExportDataRootElement(portletDataContext);

		rootElement.addAttribute(
			"group-id", String.valueOf(portletDataContext.getScopeGroupId()));

		if (portletDataContext.getBooleanParameter(NAMESPACE, "sources")) {
			ActionableDynamicQuery sourceActionableDynamicQuery =
				new SourceExportActionableDynamicQuery(portletDataContext);

			sourceActionableDynamicQuery.performActions();
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "definitions")) {
			ActionableDynamicQuery definitionActionableDynamicQuery =
				new DefinitionExportActionableDynamicQuery(portletDataContext);

			definitionActionableDynamicQuery.performActions();
		}

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPortletPermissions(RESOURCE_NAME);

		if (portletDataContext.getBooleanParameter(NAMESPACE, "sources")) {
			Element sourcesElement =
				portletDataContext.getImportDataGroupElement(Source.class);

			List<Element> sourceElements = sourcesElement.elements();

			for (Element sourceElement : sourceElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, sourceElement);
			}
		}

		if (portletDataContext.getBooleanParameter(NAMESPACE, "definitions")) {
			Element definitionsElement =
				portletDataContext.getImportDataGroupElement(Definition.class);

			List<Element> definitionElements = definitionsElement.elements();

			for (Element definitionElement : definitionElements) {
				StagedModelDataHandlerUtil.importStagedModel(
					portletDataContext, definitionElement);
			}
		}

		return null;
	}

	@Override
	protected void doPrepareManifestSummary(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws Exception {

		ActionableDynamicQuery sourceActionableDynamicQuery =
			new SourceExportActionableDynamicQuery(portletDataContext);

		sourceActionableDynamicQuery.performCount();

		ActionableDynamicQuery definitionActionableDynamicQuery =
			new DefinitionExportActionableDynamicQuery(portletDataContext);

		definitionActionableDynamicQuery.performCount();
	}

	protected static final String RESOURCE_NAME = "com.liferay.reports.admin";

}