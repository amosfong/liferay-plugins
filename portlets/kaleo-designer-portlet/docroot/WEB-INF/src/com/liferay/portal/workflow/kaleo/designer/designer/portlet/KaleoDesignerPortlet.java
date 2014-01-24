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

package com.liferay.portal.workflow.kaleo.designer.designer.portlet;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.RolePermissionUtil;
import com.liferay.portal.service.permission.UserPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.designer.DuplicateKaleoDraftDefinitionNameException;
import com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionContentException;
import com.liferay.portal.workflow.kaleo.designer.KaleoDraftDefinitionNameException;
import com.liferay.portal.workflow.kaleo.designer.NoSuchKaleoDraftDefinitionException;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;
import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionServiceUtil;
import com.liferay.portal.workflow.kaleo.designer.util.KaleoDesignerUtil;
import com.liferay.portal.workflow.kaleo.designer.util.WebKeys;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplateConstants;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Eduardo Lundgren
 */
public class KaleoDesignerPortlet extends MVCPortlet {

	public void addKaleoDraftDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String content = null;

		try {
			String name = ParamUtil.getString(actionRequest, "name");
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				actionRequest, "title");
			content = ParamUtil.getString(actionRequest, "content");
			int version = ParamUtil.getInteger(actionRequest, "version", 0);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			KaleoDraftDefinition kaleoDraftDefinition =
				KaleoDraftDefinitionServiceUtil.addKaleoDraftDefinition(
					themeDisplay.getUserId(), themeDisplay.getCompanyGroupId(),
					name, titleMap, content, version, 1, serviceContext);

			actionRequest.setAttribute(
				WebKeys.KALEO_DRAFT_DEFINITION, kaleoDraftDefinition);
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}

				SessionErrors.add(actionRequest, e.getClass(), e);

				actionRequest.setAttribute(
					WebKeys.KALEO_DRAFT_DEFINITION_CONTENT, content);
			}
			else {
				throw e;
			}
		}
	}

	public void publishKaleoDraftDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String content = null;

		try {
			String name = ParamUtil.getString(actionRequest, "name");
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				actionRequest, "title");
			content = ParamUtil.getString(actionRequest, "content");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			KaleoDraftDefinition kaleoDraftDefinition =
				KaleoDraftDefinitionServiceUtil.publishKaleoDraftDefinition(
					themeDisplay.getUserId(), themeDisplay.getCompanyGroupId(),
					name, titleMap, content, serviceContext);

			actionRequest.setAttribute(
				WebKeys.KALEO_DRAFT_DEFINITION, kaleoDraftDefinition);
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}

				SessionErrors.add(actionRequest, e.getClass(), e);

				actionRequest.setAttribute(
					WebKeys.KALEO_DRAFT_DEFINITION_CONTENT, content);
			}
			else {
				throw e;
			}
		}
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (!SessionErrors.contains(
				renderRequest,
				DuplicateKaleoDraftDefinitionNameException.class)) {

			try {
				KaleoDraftDefinition kaleoDraftDefinition =
					KaleoDesignerUtil.getKaleoDraftDefinition(renderRequest);

				renderRequest.setAttribute(
					WebKeys.KALEO_DRAFT_DEFINITION, kaleoDraftDefinition);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("draftDefinitions")) {
				serveDraftDefinitions(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("forms")) {
				serveForms(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("roles")) {
				serveRoles(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("users")) {
				serveUsers(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateKaleoDraftDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String content = null;

		try {
			String name = ParamUtil.getString(actionRequest, "name");
			Map<Locale, String> titleMap = LocalizationUtil.getLocalizationMap(
				actionRequest, "title");
			content = ParamUtil.getString(actionRequest, "content");
			int version = ParamUtil.getInteger(actionRequest, "version");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			KaleoDraftDefinition kaleoDraftDefinition =
				KaleoDraftDefinitionServiceUtil.updateKaleoDraftDefinition(
					themeDisplay.getUserId(), name, titleMap, content, version,
					serviceContext);

			actionRequest.setAttribute(
				WebKeys.KALEO_DRAFT_DEFINITION, kaleoDraftDefinition);
		}
		catch (Exception e) {
			if (isSessionErrorException(e)) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}

				SessionErrors.add(actionRequest, e.getClass(), e);

				actionRequest.setAttribute(
					WebKeys.KALEO_DRAFT_DEFINITION_CONTENT, content);
			}
			else {
				throw e;
			}
		}
	}

	protected Integer[] getRoleTypesObj(int type) {
		if ((type == RoleConstants.TYPE_ORGANIZATION) ||
			(type == RoleConstants.TYPE_REGULAR) ||
			(type == RoleConstants.TYPE_SITE)) {

			return new Integer[] {type};
		}
		else {
			return new Integer[0];
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicateKaleoDraftDefinitionNameException ||
			cause instanceof KaleoDraftDefinitionContentException ||
			cause instanceof KaleoDraftDefinitionNameException ||
			cause instanceof NoSuchKaleoDraftDefinitionException ||
			cause instanceof WorkflowException) {

			return true;
		}

		return false;
	}

	protected void serveDraftDefinitions(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String name = ParamUtil.getString(resourceRequest, "name");
		int version = ParamUtil.getInteger(resourceRequest, "version");
		int draftVersion = ParamUtil.getInteger(
			resourceRequest, "draftVersion");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			resourceRequest);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(name) && (draftVersion > 0)) {
			KaleoDraftDefinition kaleoDraftDefinition =
				KaleoDraftDefinitionServiceUtil.getKaleoDraftDefinition(
					name, version, draftVersion, serviceContext);

			jsonObject.put("content", kaleoDraftDefinition.getContent());
			jsonObject.put(
				"draftVersion", kaleoDraftDefinition.getDraftVersion());
			jsonObject.put("name", kaleoDraftDefinition.getName());
			jsonObject.put(
				"title",
				kaleoDraftDefinition.getTitle(themeDisplay.getLocale()));
			jsonObject.put("version", kaleoDraftDefinition.getVersion());
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveForms(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ddmStructureId = ParamUtil.getLong(
			resourceRequest, "ddmStructureId");
		String keywords = ParamUtil.getString(resourceRequest, "keywords");

		List<DDMTemplate> ddmTemplates = DDMTemplateServiceUtil.search(
			themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
			PortalUtil.getClassNameId(DDMStructure.class), ddmStructureId,
			keywords, DDMTemplateConstants.TEMPLATE_TYPE_FORM, null, 0,
			SearchContainer.DEFAULT_DELTA, (OrderByComparator)null);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("description", StringPool.BLANK);
		jsonObject.put("name", StringPool.BLANK);
		jsonObject.put("templateId", 0);

		jsonArray.put(jsonObject);

		for (DDMTemplate ddmTemplate : ddmTemplates) {
			jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"description",
				ddmTemplate.getDescription(themeDisplay.getLocale()));
			jsonObject.put(
				"name", ddmTemplate.getName(themeDisplay.getLocale()));
			jsonObject.put("templateId", ddmTemplate.getTemplateId());

			jsonArray.put(jsonObject);
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	protected void serveRoles(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String keywords = ParamUtil.getString(resourceRequest, "keywords");
		int type = ParamUtil.getInteger(resourceRequest, "type");

		List<Role> roles = RoleLocalServiceUtil.search(
			themeDisplay.getCompanyId(), keywords, getRoleTypesObj(type), 0,
			SearchContainer.DEFAULT_DELTA, (OrderByComparator)null);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Role role : roles) {
			if (!RolePermissionUtil.contains(
					themeDisplay.getPermissionChecker(), role.getRoleId(),
					ActionKeys.VIEW)) {

				continue;
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", role.getName());
			jsonObject.put("roleId", role.getRoleId());

			jsonArray.put(jsonObject);
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	protected void serveUsers(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String keywords = ParamUtil.getString(resourceRequest, "keywords");

		List<User> users = UserLocalServiceUtil.search(
			themeDisplay.getCompanyId(), keywords,
			WorkflowConstants.STATUS_APPROVED,
			new LinkedHashMap<String, Object>(), 0,
			SearchContainer.DEFAULT_DELTA, (OrderByComparator)null);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (User user : users) {
			if (!UserPermissionUtil.contains(
					themeDisplay.getPermissionChecker(), user.getUserId(),
					ActionKeys.VIEW)) {

				continue;
			}

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("emailAddress", user.getEmailAddress());
			jsonObject.put("fullName", user.getFullName());
			jsonObject.put("screenName", user.getScreenName());
			jsonObject.put("userId", user.getUserId());

			jsonArray.put(jsonObject);
		}

		writeJSON(resourceRequest, resourceResponse, jsonArray);
	}

	private static Log _log = LogFactoryUtil.getLog(KaleoDesignerPortlet.class);

}