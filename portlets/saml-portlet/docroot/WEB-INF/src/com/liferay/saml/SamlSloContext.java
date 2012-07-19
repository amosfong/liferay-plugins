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

package com.liferay.saml;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.saml.model.SamlIdpSpSession;
import com.liferay.saml.model.SamlIdpSsoSession;
import com.liferay.saml.service.SamlIdpSpSessionLocalServiceUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.LogoutResponse;
import org.opensaml.saml2.core.NameID;

/**
 * @author Mika Koivisto
 */
public class SamlSloContext implements Serializable {

	public SamlSloContext(SamlIdpSsoSession samlIdpSsoSession) {
		this(samlIdpSsoSession, null);
	}

	public SamlSloContext(
		SamlIdpSsoSession samlIdpSsoSession,
		SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
			samlMessageContext) {

		_logoutRequestSamlMessageContext = samlMessageContext;

		if (samlIdpSsoSession == null) {
			return;
		}

		try {
			List<SamlIdpSpSession> samlIdpSpSessions =
				SamlIdpSpSessionLocalServiceUtil.getSamlIdpSpSessions(
					samlIdpSsoSession.getSamlIdpSsoSessionId());

			for (SamlIdpSpSession samlIdpSpSession : samlIdpSpSessions) {
				SamlIdpSpSessionLocalServiceUtil.deleteSamlIdpSpSession(
					samlIdpSpSession);

				String samlSpEntityId = samlIdpSpSession.getSamlSpEntityId();

				if ((samlMessageContext != null) && samlSpEntityId.equals(
						samlMessageContext.getPeerEntityId())) {

					continue;
				}

				_samlRequestInfoMap.put(
					samlSpEntityId, new SamlSloRequestInfo(samlIdpSpSession));
			}
		}
		catch (SystemException se) {
			_log.warn(se);
		}
	}

	public SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
		getLogoutRequestSamlMessageContext() {

		return _logoutRequestSamlMessageContext;
	}

	public SamlSloRequestInfo getSamlSloRequestInfo(String entityId) {
		return _samlRequestInfoMap.get(entityId);
	}

	public Set<SamlSloRequestInfo> getSamlSloRequestInfos() {
		return new HashSet(_samlRequestInfoMap.values());
	}

	public String getSamlSsoSessionId() {
		return _samlSsoSessionId;
	}

	public Set<String> getSpEntityIds() {
		return _samlRequestInfoMap.keySet();
	}

	public User getUser() {
		try {
			return UserLocalServiceUtil.fetchUserById(_userId);
		}
		catch (Exception e) {
			return null;
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setSamlSsoSessionId(String samlSsoSessionId) {
		_samlSsoSessionId = samlSsoSessionId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("userId", getUserId());

		JSONArray jsonSamlSloRequestInfos = JSONFactoryUtil.createJSONArray();

		for (SamlSloRequestInfo samlSloRequestInfo : _samlRequestInfoMap.values()) {
			jsonSamlSloRequestInfos.put(samlSloRequestInfo.toJSONObject());
		}

		jsonObject.put("samlSloRequestInfos", jsonSamlSloRequestInfos);

		return jsonObject;
	}

	private static final Log _log = LogFactoryUtil.getLog(SamlSloContext.class);

	private SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
		_logoutRequestSamlMessageContext;
	private String _samlSsoSessionId;
	private long _userId;
	private Map<String, SamlSloRequestInfo> _samlRequestInfoMap =
		new ConcurrentHashMap<String, SamlSloRequestInfo>();

}
