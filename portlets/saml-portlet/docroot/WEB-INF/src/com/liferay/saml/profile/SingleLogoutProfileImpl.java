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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.SamlException;
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

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.methods.PostMethod;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.binding.BasicSAMLMessageContext;
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
				SamlUtil.getSingleLogoutServiceForBinding(
					idpSsoDescriptor, SAMLConstants.SAML2_REDIRECT_BINDING_URI);

			if (singleLogoutService != null) {
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

		try {
			SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
				samlMessageContext =
					(SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>)
						getSamlMessageContext(request, response);

			sendIdpLogoutRequest(request, response, samlMessageContext);

			String samlSsoSessionId = CookieUtil.get(
				request, PortletWebKeys.SAML_SSO_SESSION_ID);

			SamlIdpSsoSession samlIdpSsoSession =
				SamlIdpSsoSessionLocalServiceUtil.fetchSamlIdpSso(
					samlSsoSessionId);

			if (samlIdpSsoSession != null) {
				SamlIdpSsoSessionLocalServiceUtil.deleteSamlIdpSsoSession(
					samlIdpSsoSession);
			}

			Cookie cookie = new Cookie(
				PortletWebKeys.SAML_SSO_SESSION_ID, StringPool.BLANK);

			cookie.setMaxAge(0);
			cookie.setPath(StringPool.SLASH);
			cookie.setSecure(request.isSecure());

			response.addCookie(cookie);
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

	public void processLogoutRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		try {
			if (SamlUtil.isRoleIdp()) {
				processIdpLogoutRequest(request, response);
			}
			else if (SamlUtil.isRoleSp()) {
				processSpLogoutRequest(request, response);
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

	public void processLogoutResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		try {
			doProcessLogoutResponse(request, response);
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

	public void sendLogoutRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		try {
			doSendLogoutRequest(request, response);
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

	protected void doProcessLogoutResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_REDIRECT_BINDING_URI);

		decodeSamlMessage(request, response, samlBinding, true);

		HttpSession session = request.getSession();

		SamlSpSession samlSpSession =
			SamlSpSessionLocalServiceUtil.fetchSamlSpSession(session.getId());

		if (samlSpSession != null) {
			SamlSpSessionLocalServiceUtil.deleteSamlSpSession(samlSpSession);
		}

		String portalURL = PortalUtil.getPortalURL(request);

		String redirect = portalURL.concat("/c/portal/logout");

		response.sendRedirect(redirect);
	}

	protected void doSendLogoutRequest(
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
			SamlUtil.getSingleLogoutServiceForBinding(
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
			throw new SamlException(
				"Unable to perform single logout. Current session was not " +
					"initiated through SAML.");
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

	protected SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>
			getSoapSamlMessageContext(
				SamlIdpSpSession samlIdpSpSession,
				SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
					samlMessageContext)
		throws Exception {

		SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>
			soapSamlMessageContext =
				new BasicSAMLMessageContext
					<LogoutResponse, LogoutRequest, NameID>();

		soapSamlMessageContext.setLocalEntityMetadata(
			samlMessageContext.getLocalEntityMetadata());
		soapSamlMessageContext.setLocalEntityRole(
			IDPSSODescriptor.DEFAULT_ELEMENT_NAME);

		IDPSSODescriptor idpSsoDescriptor =
			(IDPSSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		soapSamlMessageContext.setLocalEntityRoleMetadata(idpSsoDescriptor);

		MetadataProvider metadataProvider =
			samlMessageContext.getMetadataProvider();

		soapSamlMessageContext.setMetadataProvider(metadataProvider);

		EntityDescriptor entityDescriptor =
			metadataProvider.getEntityDescriptor(
				samlIdpSpSession.getSamlSpEntityId());

		soapSamlMessageContext.setPeerEntityId(entityDescriptor.getEntityID());
		soapSamlMessageContext.setPeerEntityMetadata(entityDescriptor);

		SPSSODescriptor spSsoDescriptor = entityDescriptor.getSPSSODescriptor(
			SAMLConstants.SAML20P_NS);

		soapSamlMessageContext.setPeerEntityRoleMetadata(spSsoDescriptor);

		return soapSamlMessageContext;
	}

	protected void processIdpLogoutRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_REDIRECT_BINDING_URI);

		SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
			samlMessageContext =
				(SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>)
					decodeSamlMessage(request, response, samlBinding, true);

		String statusCodeURI = sendIdpLogoutRequest(
			request, response, samlMessageContext);

		sendLogoutResponse(
			request, response, statusCodeURI, samlMessageContext);
	}

	protected void processSpLogoutRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_SOAP11_BINDING_URI);

		SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
			samlMessageContext =
				(SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>)
					decodeSamlMessage(request, response, samlBinding, true);

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

		MessageEncoder messageEncoder = samlBinding.getMessageEncoder();

		messageEncoder.encode(samlMessageContext);
	}

	protected String sendIdpLogoutRequest(
			HttpServletRequest request, HttpServletResponse response,
			SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
				samlMessageContext)
		throws Exception {

		String samlSsoSessionId = CookieUtil.get(
			request, PortletWebKeys.SAML_SSO_SESSION_ID);

		String statusCodeURI = StatusCode.SUCCESS_URI;

		if (Validator.isNull(samlSsoSessionId)) {
			return statusCodeURI;
		}

		SamlIdpSsoSession samlIdpSsoSession =
			SamlIdpSsoSessionLocalServiceUtil.fetchSamlIdpSso(samlSsoSessionId);

		if (samlIdpSsoSession == null) {
			return StatusCode.UNKNOWN_PRINCIPAL_URI;
		}

		List<SamlIdpSpSession> samlIdpSpSessions =
			SamlIdpSpSessionLocalServiceUtil.getSamlIdpSpSessions(
				samlIdpSsoSession.getSamlIdpSsoSessionId());

		for (SamlIdpSpSession samlIdpSpSession : samlIdpSpSessions) {
			SamlIdpSpSessionLocalServiceUtil.deleteSamlIdpSpSession(
				samlIdpSpSession);

			String samlSpEntityId = samlIdpSpSession.getSamlSpEntityId();

			if (samlSpEntityId.equals(samlMessageContext.getPeerEntityId())) {
				continue;
			}

			try {
				SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>
					soapSamlMessageContext = getSoapSamlMessageContext(
						samlIdpSpSession, samlMessageContext);

				SamlIdpSpSessionLocalServiceUtil.deleteSamlIdpSpSession(
					samlIdpSpSession);

				String status = sendSoapLogoutRequest(
					samlIdpSpSession, soapSamlMessageContext);

				if (!status.equals(StatusCode.SUCCESS_URI)) {
					statusCodeURI = StatusCode.PARTIAL_LOGOUT_URI;
				}
			}
			catch (Exception e) {
				_log.warn(
					"Unable to complete single logout request to " +
						samlSpEntityId,
				e);

				statusCodeURI = StatusCode.PARTIAL_LOGOUT_URI;
			}
		}

		return statusCodeURI;
	}

	protected void sendLogoutResponse(
			HttpServletRequest request, HttpServletResponse response,
			String statusCodeURI,
			SAMLMessageContext<LogoutRequest, LogoutResponse, NameID>
				samlMessageContext)
		throws Exception {

		LogoutResponse logoutResponse = OpenSamlUtil.buildLogoutResponse();

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getPeerEntityRoleMetadata();

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_REDIRECT_BINDING_URI);

		SingleLogoutService singleLogoutService =
			SamlUtil.getSingleLogoutServiceForBinding(
				spSsoDescriptor, samlBinding.getCommunicationProfileId());

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

		Cookie cookie = new Cookie(
			PortletWebKeys.SAML_SSO_SESSION_ID, StringPool.BLANK);

		cookie.setMaxAge(0);
		cookie.setPath(StringPool.SLASH);
		cookie.setSecure(request.isSecure());

		response.addCookie(cookie);

		HttpSession session = request.getSession();

		session.invalidate();

		sendSamlMessage(samlMessageContext);
	}

	protected String sendSoapLogoutRequest(
			SamlIdpSpSession samlIdpSpSession,
			SAMLMessageContext<LogoutResponse, LogoutRequest, NameID>
				samlMessageContext)
		throws Exception {

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getPeerEntityRoleMetadata();

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_SOAP11_BINDING_URI);

		SingleLogoutService singleLogoutService =
			SamlUtil.getSingleLogoutServiceForBinding(
				spSsoDescriptor, samlBinding.getCommunicationProfileId());

		if (singleLogoutService == null) {
			_log.info(
				"Unable to find single logout service for service provider " +
					spSsoDescriptor.getID());

			return StatusCode.PARTIAL_LOGOUT_URI;
		}

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

		IDPSSODescriptor idpSsoDescriptor =
			(IDPSSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		Issuer issuer = OpenSamlUtil.buildIssuer(idpSsoDescriptor.getID());

		logoutRequest.setIssuer(issuer);

		NameID nameId = OpenSamlUtil.buildNameId(
			samlIdpSpSession.getNameIdFormat(),
			samlIdpSpSession.getNameIdValue());

		logoutRequest.setNameID(nameId);

		logoutRequest.setVersion(SAMLVersion.VERSION_20);

		samlMessageContext.setOutboundSAMLMessage(logoutRequest);

		Credential credential = MetadataManagerUtil.getSigningCredential();

		samlMessageContext.setOutboundSAMLMessageSigningCredential(credential);

		samlMessageContext.setOutboundSAMLProtocol(SAMLConstants.SAML20P_NS);

		OpenSamlUtil.signObject(logoutRequest, credential);

		samlMessageContext.setPeerEntityEndpoint(singleLogoutService);

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

		String statusCodeURI = statusCode.getValue();

		if (statusCodeURI.equals(StatusCode.SUCCESS_URI)) {
			return StatusCode.SUCCESS_URI;
		}

		return StatusCode.PARTIAL_LOGOUT_URI;
	}

	private static Log _log = LogFactoryUtil.getLog(
		SingleLogoutProfileImpl.class);

}