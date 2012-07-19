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

package com.liferay.saml.profile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.SamlException;
import com.liferay.saml.SamlSloContext;
import com.liferay.saml.SamlSloRequestInfo;
import com.liferay.saml.binding.SamlBinding;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.model.SamlIdpSpSession;
import com.liferay.saml.model.SamlIdpSsoSession;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.service.SamlIdpSpSessionLocalServiceUtil;
import com.liferay.saml.service.SamlIdpSsoSessionLocalServiceUtil;
import com.liferay.saml.service.SamlSpSessionLocalServiceUtil;
import com.liferay.saml.transport.HttpClientInTransport;
import com.liferay.saml.transport.HttpClientOutTransport;
import com.liferay.saml.util.OpenSamlUtil;
import com.liferay.saml.util.PortletWebKeys;
import com.liferay.saml.util.SamlUtil;
import com.liferay.util.CookieUtil;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.methods.PostMethod;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.LogoutResponse;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.SSODescriptor;
import org.opensaml.saml2.metadata.SingleLogoutService;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.ws.message.decoder.MessageDecoder;
import org.opensaml.ws.message.encoder.MessageEncoder;
import org.opensaml.ws.security.SecurityPolicyResolver;
import org.opensaml.xml.security.credential.Credential;

/**
 * @author Mika Koivisto
 */
public class SingleLogoutProfileImpl
	extends BaseProfile implements SingleLogoutProfile {

	public boolean isSingleLogoutSupported(HttpServletRequest request) {
		try {
			MetadataProvider metadataProvider =
				MetadataManagerUtil.getMetadataProvider();

			String entityId = MetadataManagerUtil.getDefaultIdpEntityId();

			EntityDescriptor entityDescriptor =
				metadataProvider.getEntityDescriptor(entityId);

			IDPSSODescriptor idpSsoDescriptor =
				entityDescriptor.getIDPSSODescriptor(SAMLConstants.SAML20P_NS);

			SingleLogoutService singleLogoutService =
				SamlUtil.resolveSingleLogoutService(
					idpSsoDescriptor, SAMLConstants.SAML2_REDIRECT_BINDING_URI);

			if ((singleLogoutService != null) && 
					!singleLogoutService.getBinding().equals(
						SAMLConstants.SAML2_SOAP11_BINDING_URI)) {

				return true;
			}
		}
		catch (Exception e) {
			_log.warn("Unable to verify single logout support", e);
		}

		return false;
	}

	public void processIdpLogout(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		String requestURI = request.getRequestURI();

		try {
			response.addHeader(
				HttpHeaders.CACHE_CONTROL,
				HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
			response.addHeader(
				HttpHeaders.PRAGMA, HttpHeaders.PRAGMA_NO_CACHE_VALUE);

			if (requestURI.equals("/c/portal/logout")) {
				initiateIdpSingleLogout(request, response, null);
			}
			else if (requestURI.equals("/c/portal/saml/slo_logout")) {
				SamlSloContext samlSloContext =
					getSamlSloContext(request, null);

				if (samlSloContext == null) {
					redirectToLogout(request, response);

					return;
				}

				String cmd = ParamUtil.getString(request, Constants.CMD);

				if (Validator.isNull(cmd)) {
					request.setAttribute(
						PortletWebKeys.SAML_SLO_CONTEXT,
						samlSloContext.toJSONObject());

					dispatch(request, response, _PATH_PORTAL_SAML_SLO);
				}
				else if (cmd.equals("logout")) {
					doCmdIdpSpLogout(request, response, samlSloContext);
				}
				else if (cmd.equals("status")) {
					doCmdIdpStatus(request, response, samlSloContext);
				}
				else if (cmd.equals("finish")) {
					doCmdIdpFinishLogout(request, response, samlSloContext);
				}
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}
			else {
				throw new SamlException(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void processSingleLogout(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		String requestURI = request.getRequestURI();
		String method = request.getMethod();

		SamlBinding samlBinding = null;

		if (requestURI.endsWith("/slo_soap") &&
			method.equalsIgnoreCase(HttpMethods.POST)) {

			samlBinding = getSamlBinding(
				SAMLConstants.SAML2_SOAP11_BINDING_URI);
		}
		else if (requestURI.endsWith("/slo_redirect") &&
				method.equalsIgnoreCase(HttpMethods.GET)) {

			samlBinding = getSamlBinding(
				SAMLConstants.SAML2_REDIRECT_BINDING_URI);
		}
		else {
			throw new SamlException("Invalid SAML request");
		}

		try {
			SAMLMessageContext<?, ?, ?> samlMessageContext =
				decodeSamlMessage(request, response, samlBinding, true);

			Object inSamlMessage =
				samlMessageContext.getInboundSAMLMessage();

			if (inSamlMessage instanceof LogoutRequest) {
				processSingleLogoutRequest(
					request, response,
					(SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>)
						samlMessageContext);
			}
			else if (inSamlMessage instanceof LogoutResponse) {
				processSingleLogoutResponse(
					request, response,
					(SAMLMessageContext<LogoutResponse, ?, ?>)
						samlMessageContext);
			}
			else {
				throw new SamlException("Unrecognized SAML Message");
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}
			else {
				throw new SamlException(e);
			}
		}
	}

	public void processSpLogout(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		try {
			sendSpLogoutRequest(request, response);
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}
			else if (e instanceof SystemException) {
				throw (SystemException)e;
			}
			else {
				throw new SamlException(e);
			}
		}
	}

	protected void dispatch(
			HttpServletRequest request, HttpServletResponse response,
			String path)
		throws Exception {

		dispatch(request, response, path, false);
	}

	protected void dispatch(
			HttpServletRequest request, HttpServletResponse response,
			String path, boolean popup)
		throws Exception {

		request.setAttribute("tilesTitle", "single-sign-out");
		request.setAttribute("tilesContent", path);
		request.setAttribute("tilesPopUp", Boolean.toString(popup));

		RequestDispatcher requestDispatcher =
			request.getRequestDispatcher(_PATH_HTML_PORTAL_SAML_SAML_PORTAL);

		requestDispatcher.include(request, response);
	}

	protected void doCmdIdpFinishLogout(
			HttpServletRequest request, HttpServletResponse response,
			SamlSloContext samlSloContext)
		throws Exception {

		if (samlSloContext.getLogoutRequestSamlMessageContext() != null) {
			String statusCode = StatusCode.SUCCESS_URI;

			for (SamlSloRequestInfo samlRequestInfo :
					samlSloContext.getSamlSloRequestInfos()) {

				if (!samlRequestInfo.getStatusCode().equals(
						StatusCode.SUCCESS_URI)) {
					statusCode = StatusCode.PARTIAL_LOGOUT_URI;

					break;
				}
			}

			sendIdpLogoutResponse(
				request, response, statusCode, samlSloContext);
		}
		else {
			redirectToLogout(request, response);
		}
	}

	@SuppressWarnings("unchecked")
	protected void doCmdIdpSpLogout(
			HttpServletRequest request, HttpServletResponse response,
			SamlSloContext samlSloContext)
		throws Exception {

		String entityId = ParamUtil.getString(request, "entityId");

		SamlSloRequestInfo samlSloRequestInfo =
			samlSloContext.getSamlSloRequestInfo(entityId);

		if (samlSloRequestInfo == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Requested logout for service provider " + entityId +
						" the user is currently not logged in");
			}

			dispatch(request, response, _PATH_PORTAL_SAML_ERROR, true);

			return;
		}

		if (samlSloRequestInfo.getStatus() ==
				SamlSloRequestInfo.REQUEST_STATUS_SUCCESS) {

			request.setAttribute(
				"samlSloRequestInfo", samlSloRequestInfo.toJSONObject());

			dispatch(request, response, _PATH_PORTAL_SAML_SLO_SP_STATUS, true);

			return;
		}

		SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>
			samlMessageContext =
				(SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>)
					getSamlMessageContext(request, response, entityId);

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)
				samlMessageContext.getPeerEntityRoleMetadata();

		SingleLogoutService singleLogoutService =
			SamlUtil.resolveSingleLogoutService(
				spSsoDescriptor, SAMLConstants.SAML2_SOAP11_BINDING_URI);

		if (singleLogoutService == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Single logout not supported by " + entityId);
			}

			samlSloRequestInfo.setStatusCode(
				StatusCode.UNSUPPORTED_BINDING_URI);
			samlSloRequestInfo.setStatus(
				SamlSloRequestInfo.REQUEST_STATUS_UNSUPPORTED);

			request.setAttribute(
				PortletWebKeys.SAML_SLO_REQUEST_INFO,
				samlSloRequestInfo.toJSONObject());

			dispatch(request, response, _PATH_PORTAL_SAML_SLO_SP_STATUS, true);
		}
		else {
			try {
				sendIdpLogoutRequest(
					request, response, samlSloRequestInfo);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Single logout for service provider " + entityId +
							" with binding " +
								singleLogoutService.getBinding() + " to " +
									singleLogoutService.getLocation() +
										" failed",
						e);
				}

				samlSloRequestInfo.setStatusCode(
					StatusCode.PARTIAL_LOGOUT_URI);
				samlSloRequestInfo.setStatus(
					SamlSloRequestInfo.REQUEST_STATUS_FAILED);

				request.setAttribute(
					"samlSloRequestInfo", samlSloRequestInfo.toJSONObject());

				dispatch(
					request, response, _PATH_PORTAL_SAML_SLO_SP_STATUS, true);
			}
		}
	}

	protected void doCmdIdpStatus(
			HttpServletRequest request, HttpServletResponse response,
			SamlSloContext samlSloContext)
		throws Exception {

		for (SamlSloRequestInfo samlRequestInfo :
				samlSloContext.getSamlSloRequestInfos()) {

			int status = samlRequestInfo.getStatus();

			if (status == SamlSloRequestInfo.REQUEST_STATUS_INITIATED) {
				DateTime initiateTime = samlRequestInfo.getInitiateTime();
				DateTime expireTime = initiateTime.plusSeconds(10);

				if (expireTime.isBeforeNow()) {
					samlRequestInfo.setStatusCode(
						StatusCode.PARTIAL_LOGOUT_URI);
					samlRequestInfo.setStatus(
						SamlSloRequestInfo.REQUEST_STATUS_TIMED_OUT);
				}
			}
		}

		JSONObject jsonSamlSloContext = samlSloContext.toJSONObject();

		response.setContentType(ContentTypes.TEXT_JAVASCRIPT);

		PrintWriter writer = response.getWriter();

		writer.write(jsonSamlSloContext.toString());
	}

	protected SamlSloContext getSamlSloContext(
			HttpServletRequest request,
			SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
				samlMessageContext)
		throws Exception{

		HttpSession session = request.getSession();

		SamlSloContext samlSloContext =
			(SamlSloContext)session.getAttribute(
				PortletWebKeys.SAML_SLO_CONTEXT);

		String samlSsoSessionId = CookieUtil.get(
			request, PortletWebKeys.SAML_SSO_SESSION_ID);

		if ((samlSloContext == null) && Validator.isNotNull(samlSsoSessionId)) {
			SamlIdpSsoSession samlIdpSsoSession =
				SamlIdpSsoSessionLocalServiceUtil.fetchSamlIdpSso(
					samlSsoSessionId);

			if (samlIdpSsoSession != null) {
				samlSloContext = new SamlSloContext(
					samlIdpSsoSession, samlMessageContext);
				samlSloContext.setSamlSsoSessionId(samlSsoSessionId);
				samlSloContext.setUserId(PortalUtil.getUserId(request));

				session.setAttribute(
					PortletWebKeys.SAML_SLO_CONTEXT,
					samlSloContext);
			}
		}

		return samlSloContext;
	}

	protected void initiateIdpSingleLogout(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
				samlMessageContext)
		throws Exception {

		SamlSloContext samlSloContext = getSamlSloContext(request, null);

		if (samlSloContext != null) {
			String portalURL = PortalUtil.getPortalURL(request);

			String redirect = portalURL.concat("/c/portal/saml/slo_logout");

			response.sendRedirect(redirect);
		}
		else {
			redirectToLogout(request, response);
		}
	}

	protected void processIdpLogoutRequest(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
				samlMessageContext)
		throws Exception {

		SamlSloContext samlSloContext = getSamlSloContext(
			request, samlMessageContext);

		String binding = samlMessageContext.getCommunicationProfileId();

		if (binding.equals(SAMLConstants.SAML2_SOAP11_BINDING_URI)) {
			sendIdpLogoutResponse(
				request, response, StatusCode.UNSUPPORTED_BINDING_URI,
				samlSloContext);
		}
		else if (samlSloContext == null) {
			sendIdpLogoutResponse(
				request, response, StatusCode.UNKNOWN_PRINCIPAL_URI,
				new SamlSloContext(null, samlMessageContext));
		}
		else if (!samlSloContext.getSpEntityIds().isEmpty()) {
			initiateIdpSingleLogout(request, response, samlMessageContext);
		}
		else {
			sendIdpLogoutResponse(
				request, response, StatusCode.SUCCESS_URI, samlSloContext);
		}
	}

	protected void processIdpLogoutResponse(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<LogoutResponse, ?, ?> samlMessageContext)
		throws Exception {

		SamlSloContext samlSloContext = getSamlSloContext(request, null);

		if (samlSloContext == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Received logout response from " +
						samlMessageContext.getPeerEntityId() +
							" without active SSO session");
			}

			dispatch(request, response, _PATH_PORTAL_SAML_ERROR);

			return;
		}

		String entityId = samlMessageContext.getInboundMessageIssuer();

		SamlSloRequestInfo samlSloRequestInfo =
			samlSloContext.getSamlSloRequestInfo(entityId);

		if (samlSloRequestInfo == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Received unsolicited logout response from " +
						samlMessageContext.getPeerEntityId());
			}

			dispatch(request, response, _PATH_PORTAL_SAML_ERROR);

			return;
		}

		LogoutResponse logoutResponse =
			samlMessageContext.getInboundSAMLMessage();

		Status status = logoutResponse.getStatus();

		StatusCode statusCode = status.getStatusCode();

		samlSloRequestInfo.setStatusCode(statusCode.getValue());

		request.setAttribute(
			PortletWebKeys.SAML_SLO_REQUEST_INFO,
			samlSloRequestInfo.toJSONObject());

		dispatch(request, response, _PATH_PORTAL_SAML_SLO_SP_STATUS, true);
	}

	protected void processSingleLogoutRequest(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
				samlMessageContext)
		throws Exception {

		if (SamlUtil.isRoleIdp()) {
			processIdpLogoutRequest(request, response, samlMessageContext);
		}
		else if (SamlUtil.isRoleSp()) {
			processSpLogoutRequest(request, response, samlMessageContext);
		}
	}

	protected void processSingleLogoutResponse(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<LogoutResponse, ?, ?> samlMessageContext)
		throws Exception {

		if (SamlUtil.isRoleIdp()) {
			processIdpLogoutResponse(request, response, samlMessageContext);
		}
		else if (SamlUtil.isRoleSp()) {
			processSpLogoutResponse(request, response, samlMessageContext);
		}
	}

	protected void processSpLogoutRequest(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
				samlMessageContext)
		throws Exception {

		LogoutRequest logoutRequest =
			samlMessageContext.getInboundSAMLMessage();

		NameID nameId = logoutRequest.getNameID();

		List<SamlSpSession> samlSpSessions =
			SamlSpSessionLocalServiceUtil.getSamlSpSessions(nameId.getValue());

		String statusCodeURI = StatusCode.SUCCESS_URI;

		if (samlSpSessions.isEmpty()) {
			statusCodeURI = StatusCode.UNKNOWN_PRINCIPAL_URI;
		}

		for (SamlSpSession samlSpSession : samlSpSessions) {
			samlSpSession.setTerminated(true);

			SamlSpSessionLocalServiceUtil.updateSamlSpSession(
				samlSpSession, false);

			statusCodeURI = StatusCode.SUCCESS_URI;
		}

		SSODescriptor ssoDescriptor =
			(SSODescriptor) samlMessageContext.getPeerEntityRoleMetadata();

		SingleLogoutService singleLogoutService =
			SamlUtil.resolveSingleLogoutService(
				ssoDescriptor,
				samlMessageContext.getCommunicationProfileId());

		samlMessageContext.setPeerEntityEndpoint(singleLogoutService);

		samlMessageContext.setOutboundSAMLMessageSigningCredential(
			MetadataManagerUtil.getSigningCredential());

		LogoutResponse logoutResponse = OpenSamlUtil.buildLogoutResponse();

		samlMessageContext.setOutboundSAMLMessage(logoutResponse);

		logoutResponse.setID(generateIdentifier(20));
		logoutResponse.setInResponseTo(logoutRequest.getID());
		logoutResponse.setIssueInstant(new DateTime(DateTimeZone.UTC));

		Issuer issuer = OpenSamlUtil.buildIssuer(
			samlMessageContext.getLocalEntityId());

		logoutResponse.setIssuer(issuer);

		StatusCode statusCode = OpenSamlUtil.buildStatusCode(statusCodeURI);

		Status status = OpenSamlUtil.buildStatus(statusCode);

		logoutResponse.setStatus(status);

		logoutResponse.setVersion(SAMLVersion.VERSION_20);

		sendSamlMessage(samlMessageContext);
	}

	protected void processSpLogoutResponse(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<LogoutResponse, ?, ?> samlMessageContext)
		throws Exception {

		HttpSession session = request.getSession();

		SamlSpSession samlSpSession =
			SamlSpSessionLocalServiceUtil.fetchSamlSpSession(session.getId());

		if (samlSpSession != null) {
			SamlSpSessionLocalServiceUtil.deleteSamlSpSession(samlSpSession);
		}

		redirectToLogout(request, response);
	}

	protected void redirectToLogout(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (SamlUtil.isRoleIdp()) {
			terminateSsoSession(request, response);
		}

		String portalURL = PortalUtil.getPortalURL(request);

		String redirect = portalURL.concat("/c/portal/logout");

		response.sendRedirect(redirect);
	}

	protected void sendAsyncLogoutRequest(
			SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>
				samlMessageContext)
		throws Exception {

		SingleLogoutService singleLogoutService =
			(SingleLogoutService) samlMessageContext.getPeerEntityEndpoint();

		LogoutRequest logoutRequest = OpenSamlUtil.buildLogoutRequest();

		logoutRequest.setDestination(singleLogoutService.getLocation());
		logoutRequest.setID(generateIdentifier(20));
		logoutRequest.setIssueInstant(new DateTime(DateTimeZone.UTC));

		SSODescriptor ssoDescriptor =
			(SSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		Issuer issuer = OpenSamlUtil.buildIssuer(ssoDescriptor.getID());

		logoutRequest.setIssuer(issuer);

		logoutRequest.setNameID(samlMessageContext.getSubjectNameIdentifier());

		logoutRequest.setVersion(SAMLVersion.VERSION_20);

		samlMessageContext.setOutboundSAMLMessage(logoutRequest);

		Credential credential = MetadataManagerUtil.getSigningCredential();

		samlMessageContext.setOutboundSAMLMessageSigningCredential(credential);

		samlMessageContext.setOutboundSAMLProtocol(SAMLConstants.SAML20P_NS);

		OpenSamlUtil.signObject(logoutRequest, credential);

		SamlBinding samlBinding = getSamlBinding(
			singleLogoutService.getBinding());

		MessageEncoder messageEncoder = samlBinding.getMessageEncoder();

		messageEncoder.encode(samlMessageContext);
	}

	@SuppressWarnings("unchecked")
	protected void sendIdpLogoutRequest(
		HttpServletRequest request, HttpServletResponse response,
		SamlSloRequestInfo samlSloRequestInfo) throws Exception {

		SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>
			samlMessageContext =
				(SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>)
					getSamlMessageContext(
						request, response, samlSloRequestInfo.getEntityId());

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)
				samlMessageContext.getPeerEntityRoleMetadata();

		SingleLogoutService singleLogoutService =
			SamlUtil.resolveSingleLogoutService(
				spSsoDescriptor,
				SAMLConstants.SAML2_REDIRECT_BINDING_URI);

		samlMessageContext.setPeerEntityEndpoint(singleLogoutService);

		SamlIdpSpSession samlIdpSpSession =
			samlSloRequestInfo.getSamlIdpSpSession();

		NameID nameId = OpenSamlUtil.buildNameId(
			samlIdpSpSession.getNameIdFormat(),
			samlIdpSpSession.getNameIdValue());

		samlMessageContext.setSubjectNameIdentifier(nameId);

		samlSloRequestInfo.setInitiateTime(new DateTime());
		samlSloRequestInfo.setStatus(
			SamlSloRequestInfo.REQUEST_STATUS_INITIATED);

		String binding = singleLogoutService.getBinding();

		if (binding.equals(SAMLConstants.SAML2_SOAP11_BINDING_URI)) {
			String statusCode = sendSyncLogoutRequest(samlMessageContext);

			samlSloRequestInfo.setStatusCode(statusCode);

			dispatch(request, response, _PATH_PORTAL_SAML_SLO_SP_STATUS, true);
		}
		else {
			sendAsyncLogoutRequest(samlMessageContext);
		}
	}

	protected void sendIdpLogoutResponse(
			HttpServletRequest request, HttpServletResponse response,
			String statusCodeURI,
			SamlSloContext samlSloContext)
		throws Exception {

		SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
			samlMessageContext =
				samlSloContext.getLogoutRequestSamlMessageContext();

		LogoutResponse logoutResponse = OpenSamlUtil.buildLogoutResponse();

		SSODescriptor ssoDescriptor =
			(SSODescriptor)samlMessageContext.getPeerEntityRoleMetadata();

		SingleLogoutService singleLogoutService =
			SamlUtil.resolveSingleLogoutService(
				ssoDescriptor, samlMessageContext.getCommunicationProfileId());

		logoutResponse.setDestination(singleLogoutService.getLocation());

		logoutResponse.setID(generateIdentifier(20));

		LogoutRequest logoutRequest =
			samlMessageContext.getInboundSAMLMessage();

		logoutResponse.setInResponseTo(logoutRequest.getID());

		logoutResponse.setIssueInstant(new DateTime(DateTimeZone.UTC));

		Issuer issuer = OpenSamlUtil.buildIssuer(
			samlMessageContext.getLocalEntityId());

		logoutResponse.setIssuer(issuer);

		StatusCode statusCode = OpenSamlUtil.buildStatusCode(statusCodeURI);

		Status status = OpenSamlUtil.buildStatus(statusCode);

		logoutResponse.setStatus(status);

		logoutResponse.setVersion(SAMLVersion.VERSION_20);

		samlMessageContext.setOutboundSAMLMessage(logoutResponse);

		samlMessageContext.setOutboundSAMLMessageSigningCredential(
			MetadataManagerUtil.getSigningCredential());
		samlMessageContext.setOutboundSAMLProtocol(SAMLConstants.SAML20P_NS);
		samlMessageContext.setPeerEntityEndpoint(singleLogoutService);

		if (!statusCodeURI.equals(StatusCode.UNSUPPORTED_BINDING_URI)) {
			terminateSsoSession(request, response);

			HttpSession session = request.getSession();

			session.invalidate();
		}

		sendSamlMessage(samlMessageContext);
	}

	protected String sendSyncLogoutRequest(
			SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>
				samlMessageContext)
		throws Exception {

		SingleLogoutService singleLogoutService =
			(SingleLogoutService) samlMessageContext.getPeerEntityEndpoint();

		PostMethod postMethod = new PostMethod(
			singleLogoutService.getLocation());

		HttpClientInTransport httpClientInTransport = new HttpClientInTransport(
			postMethod, singleLogoutService.getLocation());

		samlMessageContext.setInboundMessageTransport(httpClientInTransport);

		HttpClientOutTransport httpClientOutTransport =
			new HttpClientOutTransport(postMethod);

		samlMessageContext.setOutboundMessageTransport(httpClientOutTransport);

		LogoutRequest logoutRequest = OpenSamlUtil.buildLogoutRequest();

		logoutRequest.setDestination(singleLogoutService.getLocation());
		logoutRequest.setID(generateIdentifier(20));
		logoutRequest.setIssueInstant(new DateTime(DateTimeZone.UTC));

		SSODescriptor ssoDescriptor =
			(SSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		Issuer issuer = OpenSamlUtil.buildIssuer(ssoDescriptor.getID());

		logoutRequest.setIssuer(issuer);

		logoutRequest.setNameID(samlMessageContext.getSubjectNameIdentifier());

		logoutRequest.setVersion(SAMLVersion.VERSION_20);

		samlMessageContext.setOutboundSAMLMessage(logoutRequest);

		Credential credential = MetadataManagerUtil.getSigningCredential();

		samlMessageContext.setOutboundSAMLMessageSigningCredential(credential);

		samlMessageContext.setOutboundSAMLProtocol(SAMLConstants.SAML20P_NS);

		OpenSamlUtil.signObject(logoutRequest, credential);

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_SOAP11_BINDING_URI);

		MessageEncoder messageEncoder = samlBinding.getMessageEncoder();

		messageEncoder.encode(samlMessageContext);

		SecurityPolicyResolver securityPolicyResolver =
			MetadataManagerUtil.getSecurityPolicyResolver(
				samlBinding.getCommunicationProfileId(), true);

		samlMessageContext.setSecurityPolicyResolver(securityPolicyResolver);

		MessageDecoder messageDecoder = samlBinding.getMessageDecoder();

		messageDecoder.decode(samlMessageContext);

		LogoutResponse logoutResponse =
			samlMessageContext.getInboundSAMLMessage();

		Status status = logoutResponse.getStatus();

		StatusCode statusCode = status.getStatusCode();

		return statusCode.getValue();
	}

	@SuppressWarnings("unchecked")
	protected void sendSpLogoutRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		LogoutRequest logoutRequest = OpenSamlUtil.buildLogoutRequest();

		String entityId = MetadataManagerUtil.getDefaultIdpEntityId();

		SAMLMessageContext<SAMLObject, LogoutRequest, SAMLObject>
			samlMessageContext =
				(SAMLMessageContext<SAMLObject, LogoutRequest, SAMLObject>)
					getSamlMessageContext(request, response, entityId);

		IDPSSODescriptor idpSsoDescriptor =
			(IDPSSODescriptor)samlMessageContext.getPeerEntityRoleMetadata();

		SingleLogoutService singleLogoutService =
			SamlUtil.resolveSingleLogoutService(
				idpSsoDescriptor, SAMLConstants.SAML2_REDIRECT_BINDING_URI);

		logoutRequest.setDestination(singleLogoutService.getLocation());

		logoutRequest.setID(generateIdentifier(20));

		DateTime issueInstantDateTime = new DateTime(DateTimeZone.UTC);

		logoutRequest.setIssueInstant(issueInstantDateTime);

		Issuer issuer = OpenSamlUtil.buildIssuer(
			samlMessageContext.getLocalEntityId());

		logoutRequest.setIssuer(issuer);

		HttpSession session = request.getSession();

		SamlSpSession samlSpSession =
			SamlSpSessionLocalServiceUtil.fetchSamlSpSession(session.getId());

		if (samlSpSession == null) {
			redirectToLogout(request, response);

			return;
		}

		String nameIdFormat = samlSpSession.getNameIdFormat();
		String nameIdValue = samlSpSession.getNameIdValue();

		NameID nameId = OpenSamlUtil.buildNameId(nameIdFormat, nameIdValue);

		logoutRequest.setNameID(nameId);

		logoutRequest.setVersion(SAMLVersion.VERSION_20);

		samlMessageContext.setOutboundSAMLMessage(logoutRequest);

		samlMessageContext.setOutboundSAMLMessageSigningCredential(
			MetadataManagerUtil.getSigningCredential());
		samlMessageContext.setPeerEntityEndpoint(singleLogoutService);

		sendSamlMessage(samlMessageContext);
	}

	protected void terminateSsoSession(
		HttpServletRequest request, HttpServletResponse response) {

		String samlSsoSessionId = CookieUtil.get(
			request, PortletWebKeys.SAML_SSO_SESSION_ID);

		if (Validator.isNotNull(samlSsoSessionId)) {
			try {
				SamlIdpSsoSession samlIdpSsoSession =
					SamlIdpSsoSessionLocalServiceUtil.fetchSamlIdpSso(
						samlSsoSessionId);

				if (samlIdpSsoSession != null) {
					SamlIdpSsoSessionLocalServiceUtil.deleteSamlIdpSsoSession(
						samlIdpSsoSession);

					List<SamlIdpSpSession> samlIdpSpSessions =
						SamlIdpSpSessionLocalServiceUtil.getSamlIdpSpSessions(
							samlIdpSsoSession.getSamlIdpSsoSessionId());

					for (SamlIdpSpSession samlIdpSpSession :
							samlIdpSpSessions) {

						SamlIdpSpSessionLocalServiceUtil.deleteSamlIdpSpSession(
							samlIdpSpSession);
					}
				}
			}
			catch (SystemException se) {
				_log.error(se);
			}
		}

		Cookie cookie = new Cookie(
			PortletWebKeys.SAML_SSO_SESSION_ID,
			StringPool.BLANK);

		cookie.setMaxAge(0);
		cookie.setPath(StringPool.SLASH);
		cookie.setSecure(request.isSecure());

		response.addCookie(cookie);
	}

	private static final String _PATH_HTML_PORTAL_SAML_SAML_PORTAL = "/html/portal/saml/saml_portal.jsp";
	private static final String _PATH_PORTAL_SAML_ERROR = "/portal/saml/error.jsp";
	private static final String _PATH_PORTAL_SAML_SLO = "/portal/saml/slo.jsp";
	private static final String _PATH_PORTAL_SAML_SLO_SP_STATUS = "/portal/saml/slo_sp_status.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		SingleLogoutProfileImpl.class);

}