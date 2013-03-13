<%--
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
--%>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<aui:script>
	Liferay.provide(
		Liferay.Util,
		'openKaleoDesignerPortlet',
		function(config) {
			var A = AUI();

			var kaleoURL = Liferay.PortletURL.createRenderURL();

			kaleoURL.setParameter('availableFields', config.availableFields);
			kaleoURL.setParameter('availablePropertyModels', config.availablePropertyModels);
			kaleoURL.setParameter('ddmStructureId', config.ddmStructureId);
			kaleoURL.setParameter('draftVersion', config.draftVersion);
			kaleoURL.setParameter('kaleoProcessId', config.kaleoProcessId);
			kaleoURL.setParameter('name', config.name);
			kaleoURL.setParameter('openerWindowName', config.openerWindowName);
			kaleoURL.setParameter('portletResourceNamespace', config.portletResourceNamespace);
			kaleoURL.setParameter('propertiesSaveCallback', config.propertiesSaveCallback);
			kaleoURL.setParameter('saveCallback', config.saveCallback);
			kaleoURL.setParameter('uiScope', config.uiScope);
			kaleoURL.setParameter('version', config.version);

			kaleoURL.setPortletId('2_WAR_kaleodesignerportlet');
			kaleoURL.setWindowState('pop_up');

			config.uri = kaleoURL.toString();

			var dialogConfig = config.dialog;

			if (!dialogConfig) {
				var region = A.one(Liferay.Util.getOpener()).get('region');

				dialogConfig = {
					modal: true,
					title: config.name,
					width: region.width * 0.85
				};

				config.dialog = dialogConfig;
			}

			if (!('align' in dialogConfig)) {
				dialogConfig.align = Liferay.Util.Window.ALIGN_CENTER;
			}

			var dialogIframeConfig = config.dialogIframe;

			if (!dialogIframeConfig) {
				dialogIframeConfig = {
					closeOnEscape: false
				};

				config.dialogIframe = dialogIframeConfig;
			}

			Liferay.Util.openWindow(config);
		},
		['liferay-portlet-url']
	);
</aui:script>