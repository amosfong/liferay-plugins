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

package com.liferay.saml;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.saml.model.SamlIdpSpConnection;
import com.liferay.saml.model.SamlIdpSpSession;
import com.liferay.saml.model.SamlIdpSsoSession;
import com.liferay.saml.service.SamlIdpSpConnectionLocalServiceUtil;
import com.liferay.saml.service.SamlIdpSpSessionLocalServiceUtil;

import java.io.Serializable;

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

		_samlMessageContext = samlMessageContext;

		if (samlMessageContext != null) {
			samlMessageContext.setInboundMessageTransport(null);
			samlMessageContext.setOutboundMessageTransport(null);
		}

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

				if ((samlMessageContext != null) &&
					samlSpEntityId.equals(
							samlMessageContext.getPeerEntityId())) {

					continue;
				}

				String name = samlSpEntityId;

				try {
					SamlIdpSpConnection samlIdpSpConnection =
						SamlIdpSpConnectionLocalServiceUtil.
							getSamlIdpSpConnection(
								samlIdpSpSession.getCompanyId(),
								samlSpEntityId);

					name = samlIdpSpConnection.getName();
				}
				catch (NoSuchIdpSpConnectionException nsisce) {
				}

				SamlSloRequestInfo samlSloRequestInfo =
					new SamlSloRequestInfo();

				samlSloRequestInfo.setName(name);
				samlSloRequestInfo.setSamlIdpSpSession(samlIdpSpSession);

				_samlRequestInfos.put(samlSpEntityId, samlSloRequestInfo);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	public SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
		getSamlMessageContext() {

		return _samlMessageContext;
	}

	public SamlSloRequestInfo getSamlSloRequestInfo(String entityId) {
		return _samlRequestInfos.get(entityId);
	}

	public Set<SamlSloRequestInfo> getSamlSloRequestInfos() {
		return new HashSet<SamlSloRequestInfo>(_samlRequestInfos.values());
	}

	public Set<String> getSamlSpEntityIds() {
		return _samlRequestInfos.keySet();
	}

	public String getSamlSsoSessionId() {
		return _samlSsoSessionId;
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

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (SamlSloRequestInfo samlSloRequestInfo :
				_samlRequestInfos.values()) {

			jsonArray.put(samlSloRequestInfo.toJSONObject());
		}

		jsonObject.put("samlSloRequestInfos", jsonArray);

		jsonObject.put("userId", getUserId());

		return jsonObject;
	}

	private static Log _log = LogFactoryUtil.getLog(SamlSloContext.class);

	private SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
		_samlMessageContext;
	private Map<String, SamlSloRequestInfo> _samlRequestInfos =
		new ConcurrentHashMap<String, SamlSloRequestInfo>();
	private String _samlSsoSessionId;
	private long _userId;

}