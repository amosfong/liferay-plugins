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

package com.liferay.portal.workflow.kaleo.designer.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManagerUtil;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalServiceUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eduardo Lundgren
 */
public class KaleoDesignerUtil {

	public static WorkflowDefinition deployWorkflowDefinition(
			long companyId, long userId, Map<Locale, String> titleMap,
			String content)
		throws WorkflowException {

		try {
			WorkflowDefinition workflowDefinition =
				WorkflowDefinitionManagerUtil.deployWorkflowDefinition(
					companyId, userId, _getLocalizedTitleXML(titleMap),
					content.getBytes());

			return workflowDefinition;
		}
		catch (WorkflowException we) {
			_log.error(we, we);

			throw we;
		}
	}

	public static KaleoDraftDefinition getKaleoDraftDefinition(
			HttpServletRequest request)
		throws Exception {

		KaleoDraftDefinition kaleoDraftDefinition =
			(KaleoDraftDefinition)request.getAttribute(
				WebKeys.KALEO_DRAFT_DEFINITION);

		if (kaleoDraftDefinition != null) {
			return kaleoDraftDefinition;
		}

		String name = ParamUtil.getString(request, "name");

		if (Validator.isNull(name)) {
			return null;
		}

		int version = ParamUtil.getInteger(request, "version");
		int draftVersion = ParamUtil.getInteger(request, "draftVersion", -1);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		if ((version > 0) && (draftVersion >= 0)) {
			return KaleoDraftDefinitionLocalServiceUtil.getKaleoDraftDefinition(
				name, version, draftVersion, serviceContext);
		}

		try {
			return KaleoDraftDefinitionLocalServiceUtil.
				getLatestKaleoDraftDefinition(name, version, serviceContext);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Could not find Kaleo draft definition for {name=" + name +
						", version=" + version + "}");
			}
		}

		if (version <= 0) {
			return null;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Map<Locale, String> titleMap = new HashMap<Locale, String>();

		titleMap.put(LocaleUtil.getDefault(), name);

		WorkflowDefinition workflowDefinition =
			WorkflowDefinitionManagerUtil.getWorkflowDefinition(
				serviceContext.getCompanyId(), name, version);

		String content = workflowDefinition.getContent();

		return KaleoDraftDefinitionLocalServiceUtil.addKaleoDraftDefinition(
			themeDisplay.getUserId(), themeDisplay.getCompanyGroupId(), name,
			titleMap, content, version, 1, serviceContext);
	}

	public static KaleoDraftDefinition getKaleoDraftDefinition(
			PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return getKaleoDraftDefinition(request);
	}

	public static boolean isPublished(
		KaleoDraftDefinition kaleoDraftDefinition) {

		if (kaleoDraftDefinition.getVersion() == 0) {
			return false;
		}

		try {
			WorkflowDefinition workflowDefinition =
				WorkflowDefinitionManagerUtil.getWorkflowDefinition(
					kaleoDraftDefinition.getCompanyId(),
					kaleoDraftDefinition.getName(),
					kaleoDraftDefinition.getVersion());

			if (workflowDefinition.isActive() &&
				(kaleoDraftDefinition.getDraftVersion() == 1)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	private static String _getLocalizedTitleXML(Map<Locale, String> titleMap) {
		String title = StringPool.BLANK;

		if (titleMap == null) {
			return title;
		}

		String defaultLanguageId = LocaleUtil.toLanguageId(
			LocaleUtil.getDefault());

		for (Locale locale : LanguageUtil.getAvailableLocales()) {
			String languageId = LocaleUtil.toLanguageId(locale);

			String localizedTitle = titleMap.get(locale);

			if (Validator.isNotNull(localizedTitle)) {
				title = LocalizationUtil.updateLocalization(
					title, "Title", localizedTitle, languageId,
					defaultLanguageId);
			}
		}

		return title;
	}

	private static Log _log = LogFactoryUtil.getLog(KaleoDesignerUtil.class);

}