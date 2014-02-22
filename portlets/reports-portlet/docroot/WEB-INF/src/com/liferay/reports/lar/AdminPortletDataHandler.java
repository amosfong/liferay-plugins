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

import com.liferay.portal.kernel.lar.BasePortletDataHandler;
import com.liferay.portal.kernel.lar.DataLevel;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.PortletDataHandlerBoolean;
import com.liferay.portal.kernel.lar.PortletDataHandlerControl;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.kernel.xml.Element;
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
		setDataLevel(DataLevel.SITE);
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

		if (portletDataContext.addPrimaryKey(
				AdminPortletDataHandler.class, "deleteData")) {

			return portletPreferences;
		}

		DefinitionUtil.removeByGroupId(portletDataContext.getScopeGroupId());

		SourceUtil.removeByGroupId(portletDataContext.getScopeGroupId());

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

		return getExportDataRootElementString(rootElement);
	}

	@Override
	protected PortletPreferences doImportData(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences, String data)
		throws Exception {

		portletDataContext.importPortletPermissions(RESOURCE_NAME);

		Element rootElement = portletDataContext.getImportDataRootElement();

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

	protected static final String RESOURCE_NAME = "com.liferay.reports.admin";

}