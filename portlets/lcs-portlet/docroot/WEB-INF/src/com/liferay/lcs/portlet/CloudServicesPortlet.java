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

package com.liferay.lcs.portlet;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.lcs.oauth.OAuthUtil;
import com.liferay.lcs.util.HandshakeManagerUtil;
import com.liferay.lcs.util.LCSClusterNodeUtil;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.osb.lcs.LCSClusterEntryNameException;
import com.liferay.osb.lcs.model.CorpEntryIdentifier;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.service.CorpEntryServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil;
import com.liferay.portal.NoSuchPortletPreferencesException;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.scribe.model.Token;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Peter Shin
 */
public class CloudServicesPortlet extends MVCPortlet {

	public void addLCSClusterNode(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long corpEntryId = ParamUtil.getLong(actionRequest, "corpEntryId");
		long lcsClusterEntryId = ParamUtil.getLong(
			actionRequest, "lcsClusterEntryId");
		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		String location = ParamUtil.getString(actionRequest, "location");

		addLCSClusterNode(
			corpEntryId, lcsClusterEntryId, name, description, location);
	}

	public void resetCredentials(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		stop(actionRequest, actionResponse);

		deletePortletPreferences(actionRequest);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			String resourceId = resourceRequest.getResourceID();

			if (resourceId.equals("serveCorpEntry")) {
				serveCorpEntry(resourceRequest, resourceResponse);
			}
			else if (resourceId.equals("serveLCSClusterEntry")) {
				serveLCSClusterEntry(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("result", "failure");

			if (e instanceof LCSClusterEntryNameException) {
				jsonObject.put("message", e.getMessage());

				_log.error(e.getMessage());
			}
			else {
				_log.error(e, e);
			}

			writeJSON(resourceRequest, resourceResponse, jsonObject);
		}
	}

	public void setupOAuth(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletSession portletSession = actionRequest.getPortletSession();

		Token requestToken = (Token)portletSession.getAttribute(
			Token.class.getName());

		String oAuthVerifier = ParamUtil.getString(
			actionRequest, "oauth_verifier");

		Token token = OAuthUtil.extractAccessToken(requestToken, oAuthVerifier);

		javax.portlet.PortletPreferences jxPortletPreferences =
			getJxPortletPreferences(actionRequest);

		jxPortletPreferences.setValue("lcsAccessSecret", token.getSecret());
		jxPortletPreferences.setValue("lcsAccessToken", token.getToken());

		jxPortletPreferences.store();

		LCSUtil.setupCredentials();

		if (LCSUtil.getCredentialsStatus() != LCSUtil.CREDENTIALS_SET) {
			SessionErrors.add(actionRequest, "authenticationFailed");
		}
	}

	public void start(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		HandshakeManagerUtil.start();

		sendRedirect(actionRequest, actionResponse);
	}

	public void stop(ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		HandshakeManagerUtil.stop();

		sendRedirect(actionRequest, actionResponse);
	}

	protected LCSClusterEntry addLCSClusterEntry(
			long corpEntryId, String name, String description, String location)
		throws Exception {

		int type = LCSConstants.LCS_CLUSTER_ENTRY_TYPE_ENVIRONMENT;

		if (ClusterExecutorUtil.isEnabled()) {
			type = LCSConstants.LCS_CLUSTER_ENTRY_TYPE_CLUSTER;
		}

		return LCSClusterEntryServiceUtil.addLCSClusterEntry(
			corpEntryId, name, description, location, type);
	}

	protected void addLCSClusterNode(
			long corpEntryId, long lcsClusterEntryId, String name,
			String description, String location)
		throws Exception {

		if (lcsClusterEntryId <= 0) {
			lcsClusterEntryId = getDefaultLCSClusterEntry(corpEntryId);
		}

		LCSClusterNodeUtil.registerLCSClusterNode(
			lcsClusterEntryId, name, description, location);
	}

	protected void deletePortletPreferences(PortletRequest portletRequest)
		throws Exception {

		try {
			String portletId = PortalUtil.getPortletId(portletRequest);

			PortletPreferencesLocalServiceUtil.deletePortletPreferences(
				CompanyConstants.SYSTEM, PortletKeys.PREFS_OWNER_TYPE_COMPANY,
				0, portletId);
		}
		catch (NoSuchPortletPreferencesException nsppe) {
			if (_log.isWarnEnabled()) {
				_log.warn(nsppe, nsppe);
			}
		}
	}

	protected JSONArray getCorpEntriesJSONArray(PortletRequest portletRequest)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<CorpEntryIdentifier> corpEntryIdentifiers =
			CorpEntryServiceUtil.getCorpEntryIdentifiers();

		for (CorpEntryIdentifier corpEntryIdentifier : corpEntryIdentifiers) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("corpEntryId", corpEntryIdentifier.getCorpEntryId());
			jsonObject.put("name", corpEntryIdentifier.getName());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected long getCorpEntryId(
		PortletRequest portletRequest, JSONArray corpEntriesJSONArray) {

		long corpEntryId = ParamUtil.getLong(portletRequest, "corpEntryId");

		if (corpEntryId > 0) {
			return corpEntryId;
		}

		if (corpEntriesJSONArray.length() == 0) {
			return corpEntryId;
		}

		JSONObject jsonObject = corpEntriesJSONArray.getJSONObject(
			corpEntriesJSONArray.length() - 1);

		return jsonObject.getLong("corpEntryId");
	}

	protected long getDefaultLCSClusterEntry(long corpEntryId)
		throws Exception {

		long lcsClusterEntryId = 0;

		List<LCSClusterEntry> lcsClusterEntries =
			LCSClusterEntryServiceUtil.getCorpEntryLCSClusterEntries(
				corpEntryId);

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			if (Validator.equals(lcsClusterEntry.getName(), "UNCLASSIFIED")) {
				lcsClusterEntryId = lcsClusterEntry.getLcsClusterEntryId();

				break;
			}
		}

		if (lcsClusterEntryId == 0) {
			LCSClusterEntry lcsClusterEntry =
				LCSClusterEntryServiceUtil.addLCSClusterEntry(
					corpEntryId, "UNCLASSIFIED", "UNCLASSIFIED", "UNCLASSIFIED",
					LCSConstants.LCS_CLUSTER_ENTRY_TYPE_ENVIRONMENT);

			lcsClusterEntryId = lcsClusterEntry.getLcsClusterEntryId();
		}

		return lcsClusterEntryId;
	}

	protected javax.portlet.PortletPreferences getJxPortletPreferences(
			PortletRequest portletRequest)
		throws Exception {

		PortletPreferences portletPreferences = null;

		String portletId = PortalUtil.getPortletId(portletRequest);

		try {
			portletPreferences =
				PortletPreferencesLocalServiceUtil.getPortletPreferences(
					CompanyConstants.SYSTEM,
					PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0, portletId);
		}
		catch (NoSuchPortletPreferencesException nsppe) {
			portletPreferences =
				PortletPreferencesLocalServiceUtil.addPortletPreferences(
					CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
					PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0, portletId, null,
					null);
		}

		return PortletPreferencesFactoryUtil.fromXML(
			CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
			PortletKeys.PREFS_OWNER_TYPE_COMPANY, 0, portletId,
			portletPreferences.getPreferences());
	}

	protected JSONArray getLCSClusterEntriesJSONArray(long corpEntryId)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<LCSClusterEntry> lcsClusterEntries =
			LCSClusterEntryServiceUtil.getCorpEntryLCSClusterEntries(
				corpEntryId);

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			jsonArray.put(getLCSClusterEntryJSONObject(lcsClusterEntry));
		}

		return jsonArray;
	}

	protected JSONObject getLCSClusterEntryJSONObject(
		LCSClusterEntry lcsClusterEntry) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"lcsClusterEntryId", lcsClusterEntry.getLcsClusterEntryId());
		jsonObject.put("name", lcsClusterEntry.getName());
		jsonObject.put("type", lcsClusterEntry.getType());

		return jsonObject;
	}

	protected void serveCorpEntry(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray corpEntriesJSONArray = getCorpEntriesJSONArray(
			resourceRequest);

		jsonObject.put("corpEntries", corpEntriesJSONArray);

		long corpEntryId = getCorpEntryId(
			resourceRequest, corpEntriesJSONArray);

		jsonObject.put(
			"lcsClusterEntries", getLCSClusterEntriesJSONArray(corpEntryId));

		jsonObject.put("result", "success");

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveLCSClusterEntry(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long corpEntryId = ParamUtil.getLong(resourceRequest, "corpEntryId");
		String name = ParamUtil.getString(resourceRequest, "name");
		String description = ParamUtil.getString(
			resourceRequest, "description");
		String location = ParamUtil.getString(resourceRequest, "location");

		LCSClusterEntry lcsClusterEntry = addLCSClusterEntry(
			corpEntryId, name, description, location);

		JSONObject jsonObject = getLCSClusterEntryJSONObject(lcsClusterEntry);

		jsonObject.put("result", "success");

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static Log _log = LogFactoryUtil.getLog(CloudServicesPortlet.class);

}