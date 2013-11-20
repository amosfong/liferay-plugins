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

package com.liferay.saml.profile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.AssertionException;
import com.liferay.saml.AudienceException;
import com.liferay.saml.DestinationException;
import com.liferay.saml.ExpiredException;
import com.liferay.saml.InResponseToException;
import com.liferay.saml.IssuerException;
import com.liferay.saml.NoSuchIdpSpSessionException;
import com.liferay.saml.ReplayException;
import com.liferay.saml.SamlException;
import com.liferay.saml.SamlSsoRequestContext;
import com.liferay.saml.SignatureException;
import com.liferay.saml.StatusException;
import com.liferay.saml.SubjectException;
import com.liferay.saml.binding.SamlBinding;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.model.SamlIdpSsoSession;
import com.liferay.saml.model.SamlSpAuthRequest;
import com.liferay.saml.model.SamlSpMessage;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.resolver.AttributeResolver;
import com.liferay.saml.resolver.AttributeResolverFactory;
import com.liferay.saml.resolver.NameIdResolver;
import com.liferay.saml.resolver.NameIdResolverFactory;
import com.liferay.saml.resolver.UserResolverUtil;
import com.liferay.saml.service.SamlIdpSpSessionLocalServiceUtil;
import com.liferay.saml.service.SamlIdpSsoSessionLocalServiceUtil;
import com.liferay.saml.service.SamlSpAuthRequestLocalServiceUtil;
import com.liferay.saml.service.SamlSpMessageLocalServiceUtil;
import com.liferay.saml.service.SamlSpSessionLocalServiceUtil;
import com.liferay.saml.util.OpenSamlUtil;
import com.liferay.saml.util.PortletPropsValues;
import com.liferay.saml.util.PortletWebKeys;
import com.liferay.saml.util.SamlUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.SAMLVersion;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.saml2.core.Audience;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnContext;
import org.opensaml.saml2.core.AuthnContextClassRef;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.AuthnStatement;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.NameIDPolicy;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Status;
import org.opensaml.saml2.core.StatusCode;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.security.MetadataCriteria;
import org.opensaml.security.SAMLSignatureProfileValidator;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.credential.UsageType;
import org.opensaml.xml.security.criteria.EntityIDCriteria;
import org.opensaml.xml.security.criteria.UsageCriteria;
import org.opensaml.xml.security.trust.TrustEngine;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureTrustEngine;

/**
 * @author Mika Koivisto
 */
public class WebSsoProfileImpl extends BaseProfile implements WebSsoProfile {

	@Override
	public void processAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		try {
			doProcessAuthnRequest(request, response);
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

	@Override
	public void processResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		try {
			doProcessResponse(request, response);
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

	@Override
	public void sendAuthnRequest(
			HttpServletRequest request, HttpServletResponse response,
			String relayState)
		throws PortalException, SystemException {

		try {
			doSendAuthnRequest(request, response, relayState);
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

	protected void addSamlSsoSession(
			HttpServletRequest request, HttpServletResponse response,
			SamlSsoRequestContext samlSsoRequestContext, NameID nameId)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		SamlIdpSsoSession samlIdpSsoSession =
			SamlIdpSsoSessionLocalServiceUtil.addSamlIdpSsoSession(
				samlSsoRequestContext.getSamlSsoSessionId(), serviceContext);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		SamlIdpSpSessionLocalServiceUtil.addSamlIdpSpSession(
			samlIdpSsoSession.getSamlIdpSsoSessionId(),
			samlMessageContext.getPeerEntityId(), nameId.getFormat(),
			nameId.getValue(), serviceContext);

		Cookie cookie = new Cookie(
			PortletWebKeys.SAML_SSO_SESSION_ID,
			samlSsoRequestContext.getSamlSsoSessionId());

		cookie.setMaxAge(-1);

		if (Validator.isNull(PortalUtil.getPathContext())) {
			cookie.setPath(StringPool.SLASH);
		}
		else {
			cookie.setPath(PortalUtil.getPathContext());
		}

		cookie.setSecure(request.isSecure());

		response.addCookie(cookie);
	}

	protected SamlSsoRequestContext decodeAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession();

		SamlSsoRequestContext samlSsoRequestContext =
			(SamlSsoRequestContext)session.getAttribute(
				PortletWebKeys.SAML_SSO_REQUEST_CONTEXT);

		if (samlSsoRequestContext != null) {
			session.removeAttribute(PortletWebKeys.SAML_SSO_REQUEST_CONTEXT);

			SAMLMessageContext<AuthnRequest, Response, NameID>
				samlMessageContext =
					samlSsoRequestContext.getSAMLMessageContext();

			HttpServletRequestAdapter inHttpServletRequestAdapter =
				new HttpServletRequestAdapter(request);

			samlMessageContext.setInboundMessageTransport(
				inHttpServletRequestAdapter);

			HttpServletResponseAdapter outHttpServletRequestAdapter =
				new HttpServletResponseAdapter(response, request.isSecure());

			samlMessageContext.setOutboundMessageTransport(
				outHttpServletRequestAdapter);

			String samlSsoSessionId = getSamlSsoSessionId(request);

			if (Validator.isNotNull(samlSsoSessionId)) {
				samlSsoRequestContext.setSamlSsoSessionId(samlSsoSessionId);
			}
			else {
				samlSsoRequestContext.setNewSession(true);
				samlSsoRequestContext.setSamlSsoSessionId(
					generateIdentifier(30));
			}

			samlSsoRequestContext.setStage(
				SamlSsoRequestContext.STAGE_AUTHENTICATED);

			long userId = PortalUtil.getUserId(request);

			samlSsoRequestContext.setUserId(userId);

			return samlSsoRequestContext;
		}

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			null;

		SamlBinding samlBinding = null;

		if (StringUtil.equalsIgnoreCase(request.getMethod(), "GET")) {
			samlBinding = getSamlBinding(
				SAMLConstants.SAML2_REDIRECT_BINDING_URI);
		}
		else {
			samlBinding = getSamlBinding(SAMLConstants.SAML2_POST_BINDING_URI);
		}

		String entityId = ParamUtil.getString(request, "entityId");
		String samlRequest = ParamUtil.getString(request, "SAMLRequest");

		if (Validator.isNotNull(entityId) && Validator.isNull(samlRequest)) {
			samlMessageContext =
				(SAMLMessageContext<AuthnRequest, Response, NameID>)
					getSamlMessageContext(request, response, entityId);

			samlMessageContext.setCommunicationProfileId(
				samlBinding.getCommunicationProfileId());

			String relayState = ParamUtil.getString(request, "RelayState");

			samlMessageContext.setRelayState(relayState);
		}
		else {
			samlMessageContext =
				(SAMLMessageContext<AuthnRequest, Response, NameID>)
					decodeSamlMessage(
						request, response, samlBinding,
						MetadataManagerUtil.isWantAuthnRequestSigned());
		}

		samlSsoRequestContext = new SamlSsoRequestContext(samlMessageContext);

		String samlSsoSessionId = getSamlSsoSessionId(request);

		if (Validator.isNotNull(samlSsoSessionId)) {
			samlSsoRequestContext.setSamlSsoSessionId(samlSsoSessionId);
		}
		else {
			samlSsoRequestContext.setNewSession(true);
			samlSsoRequestContext.setSamlSsoSessionId(generateIdentifier(30));
		}

		samlSsoRequestContext.setStage(SamlSsoRequestContext.STAGE_INITIAL);

		long userId = PortalUtil.getUserId(request);

		samlSsoRequestContext.setUserId(userId);

		return samlSsoRequestContext;
	}

	protected void doProcessAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		SamlSsoRequestContext samlSsoRequestContext = decodeAuthnRequest(
			request, response);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		AuthnRequest authnRequest = samlMessageContext.getInboundSAMLMessage();
		User user = samlSsoRequestContext.getUser();

		if ((authnRequest != null) && authnRequest.isPassive() &&
			(user == null)) {

			sendFailureResponse(
				samlSsoRequestContext, StatusCode.NO_PASSIVE_URI);

			return;
		}

		boolean sessionExpired = false;

		if (!samlSsoRequestContext.isNewSession()) {
			String samlSsoSessionId =
				samlSsoRequestContext.getSamlSsoSessionId();

			SamlIdpSsoSession samlIdpSsoSession =
				SamlIdpSsoSessionLocalServiceUtil.fetchSamlIdpSso(
					samlSsoSessionId);

			if (samlIdpSsoSession != null) {
				sessionExpired = samlIdpSsoSession.isExpired();
			}
			else {
				samlSsoSessionId = null;

				samlSsoRequestContext.setSamlSsoSessionId(null);
			}

			if (sessionExpired || Validator.isNull(samlSsoSessionId)) {
				Cookie cookie = new Cookie(
					PortletWebKeys.SAML_SSO_SESSION_ID, StringPool.BLANK);

				cookie.setMaxAge(0);
				cookie.setPath(StringPool.SLASH);
				cookie.setSecure(request.isSecure());

				response.addCookie(cookie);

				samlSsoRequestContext.setNewSession(true);
				samlSsoRequestContext.setSamlSsoSessionId(
					generateIdentifier(30));
			}
		}

		if (sessionExpired || (user == null) ||
			((authnRequest != null) && authnRequest.isForceAuthn() &&
			 (user != null) &&
			 (samlSsoRequestContext.getStage() ==
				 SamlSsoRequestContext.STAGE_INITIAL))) {

			boolean forceAuthn = false;

			if (sessionExpired || ((authnRequest != null) &&
				 authnRequest.isForceAuthn())) {

				forceAuthn = true;
			}

			redirectToLogin(
				request, response, samlSsoRequestContext, forceAuthn);
		}
		else {
			sendSuccessResponse(request, response, samlSsoRequestContext);
		}
	}

	protected void doProcessResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		SAMLMessageContext<Response, SAMLObject, NameID> samlMessageContext =
			(SAMLMessageContext<Response, SAMLObject, NameID>)decodeSamlMessage(
				request, response,
				getSamlBinding(SAMLConstants.SAML2_POST_BINDING_URI), true);

		Response samlResponse = samlMessageContext.getInboundSAMLMessage();

		Status status = samlResponse.getStatus();

		StatusCode statusCode = status.getStatusCode();

		String statusCodeURI = statusCode.getValue();

		if (!statusCodeURI.equals(StatusCode.SUCCESS_URI)) {
			throw new StatusException(statusCode.getValue());
		}

		verifyInResponseTo(samlResponse);
		verifyDestination(samlMessageContext, samlResponse.getDestination());
		verifyIssuer(samlMessageContext, samlResponse.getIssuer());

		Assertion assertion = null;

		SignatureTrustEngine signatureTrustEngine =
			MetadataManagerUtil.getSignatureTrustEngine();

		List<Attribute> attributes = new ArrayList<Attribute>();

		for (Assertion curAssertion : samlResponse.getAssertions()) {
			try {
				verifyAssertion(
					curAssertion, samlMessageContext, signatureTrustEngine);
			}
			catch (SamlException samle) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						"Rejecting assertion " + curAssertion.getID(), samle);
				}

				continue;
			}

			List<AuthnStatement> authnStatements =
				curAssertion.getAuthnStatements();

			if (!authnStatements.isEmpty()) {
				Subject subject = curAssertion.getSubject();

				if ((subject != null) &&
					(subject.getSubjectConfirmations() != null)) {

					for (SubjectConfirmation subjectConfirmation :
							subject.getSubjectConfirmations()) {

						if (SubjectConfirmation.METHOD_BEARER.equals(
								subjectConfirmation.getMethod())) {

							assertion = curAssertion;

							break;
						}
					}
				}
			}

			if (assertion != null) {
				for (AttributeStatement attributeStatement :
						curAssertion.getAttributeStatements()) {

					for (Attribute attribute :
							attributeStatement.getAttributes()) {

						attributes.add(attribute);
					}
				}

				break;
			}
		}

		if (assertion == null) {
			throw new AssertionException(
				"Response does not contain any acceptable assertions");
		}

		NameID nameId = samlMessageContext.getSubjectNameIdentifier();

		if (nameId == null) {
			throw new SamlException("Name ID not present in subject");
		}

		if (_log.isDebugEnabled()) {
			_log.debug("SAML authenticated user " + nameId.getValue());
		}

		String assertionXml = OpenSamlUtil.marshall(assertion);

		List<AuthnStatement> authnStatements = assertion.getAuthnStatements();

		AuthnStatement authnStatement = authnStatements.get(0);

		String sessionIndex = authnStatement.getSessionIndex();

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		User user = UserResolverUtil.resolveUser(
			assertion, samlMessageContext, serviceContext);

		serviceContext.setUserId(user.getUserId());

		HttpSession session = request.getSession();

		SamlSpSession samlSpSession = getSamlSpSession(request);

		if (samlSpSession != null) {
			SamlSpSessionLocalServiceUtil.updateSamlSpSession(
				samlSpSession.getSamlSpSessionId(),
				samlSpSession.getSamlSpSessionKey(), assertionXml,
				session.getId(), nameId.getFormat(), nameId.getValue(),
				sessionIndex, serviceContext);
		}
		else {
			String samlSpSessionKey = generateIdentifier(30);

			samlSpSession = SamlSpSessionLocalServiceUtil.addSamlSpSession(
				samlSpSessionKey, assertionXml, session.getId(),
				nameId.getFormat(), nameId.getValue(), sessionIndex,
				serviceContext);
		}

		session.setAttribute(
			PortletWebKeys.SAML_SP_SESSION_KEY,
			samlSpSession.getSamlSpSessionKey());

		Cookie cookie = new Cookie(
			PortletWebKeys.SAML_SP_SESSION_KEY,
			samlSpSession.getSamlSpSessionKey());

		cookie.setMaxAge(-1);

		if (Validator.isNull(PortalUtil.getPathContext())) {
			cookie.setPath(StringPool.SLASH);
		}
		else {
			cookie.setPath(PortalUtil.getPathContext());
		}

		cookie.setSecure(request.isSecure());

		response.addCookie(cookie);

		StringBundler sb = new StringBundler(3);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		sb.append(themeDisplay.getPathMain());
		sb.append("/portal/saml/auth_redirect?redirect=");

		String relayState = PortalUtil.escapeRedirect(
			samlMessageContext.getRelayState());

		if (Validator.isNull(relayState)) {
			relayState = PortalUtil.getHomeURL(request);
		}

		sb.append(HttpUtil.encodeURL(relayState));

		response.sendRedirect(sb.toString());
	}

	protected void doSendAuthnRequest(
			HttpServletRequest request, HttpServletResponse response,
			String relayState)
		throws Exception {

		String entityId = MetadataManagerUtil.getDefaultIdpEntityId();

		SAMLMessageContext<SAMLObject, AuthnRequest, SAMLObject>
			samlMessageContext =
				(SAMLMessageContext<SAMLObject, AuthnRequest, SAMLObject>)
					getSamlMessageContext(request, response, entityId);

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		AssertionConsumerService assertionConsumerService =
			SamlUtil.getAssertionConsumerServiceForBinding(
				spSsoDescriptor, SAMLConstants.SAML2_POST_BINDING_URI);

		IDPSSODescriptor idpSsoDescriptor =
			(IDPSSODescriptor)samlMessageContext.getPeerEntityRoleMetadata();

		SingleSignOnService singleSignOnService =
			SamlUtil.resolveSingleSignOnService(
				idpSsoDescriptor, SAMLConstants.SAML2_POST_BINDING_URI);

		NameIDPolicy nameIdPolicy = OpenSamlUtil.buildNameIdPolicy();

		nameIdPolicy.setAllowCreate(true);
		nameIdPolicy.setFormat(MetadataManagerUtil.getNameIdFormat(entityId));

		AuthnRequest authnRequest = OpenSamlUtil.buildAuthnRequest(
			spSsoDescriptor, assertionConsumerService, singleSignOnService,
			nameIdPolicy);

		authnRequest.setID(generateIdentifier(20));

		samlMessageContext.setOutboundSAMLMessage(authnRequest);

		if (spSsoDescriptor.isAuthnRequestsSigned() ||
			idpSsoDescriptor.getWantAuthnRequestsSigned()) {

			Credential credential = MetadataManagerUtil.getSigningCredential();

			samlMessageContext.setOutboundSAMLMessageSigningCredential(
				credential);

			OpenSamlUtil.signObject(authnRequest, credential);
		}

		samlMessageContext.setPeerEntityEndpoint(singleSignOnService);
		samlMessageContext.setRelayState(relayState);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		SamlSpAuthRequestLocalServiceUtil.addSamlSpAuthRequest(
			samlMessageContext.getPeerEntityId(), authnRequest.getID(),
			serviceContext);

		sendSamlMessage(samlMessageContext);
	}

	protected Assertion getSuccessAssertion(
		SamlSsoRequestContext samlSsoRequestContext,
		AssertionConsumerService assertionConsumerService, NameID nameId) {

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		Assertion assertion = OpenSamlUtil.buildAssertion();

		DateTime issueInstantDateTime = new DateTime(DateTimeZone.UTC);

		SubjectConfirmationData subjectConfirmationData =
			getSuccessSubjectConfirmationData(
				samlSsoRequestContext, assertionConsumerService,
				issueInstantDateTime);

		Conditions conditions = getSuccessConditions(
			samlSsoRequestContext, issueInstantDateTime,
			subjectConfirmationData.getNotOnOrAfter());

		assertion.setConditions(conditions);

		assertion.setID(generateIdentifier(20));
		assertion.setIssueInstant(issueInstantDateTime);

		Issuer issuer = OpenSamlUtil.buildIssuer(
			samlMessageContext.getLocalEntityId());

		assertion.setIssuer(issuer);

		Subject subject = getSuccessSubject(
			samlSsoRequestContext, assertionConsumerService, nameId,
			subjectConfirmationData);

		assertion.setSubject(subject);

		assertion.setVersion(SAMLVersion.VERSION_20);

		List<AuthnStatement> authnStatements = assertion.getAuthnStatements();

		authnStatements.add(
			getSuccessAuthnStatement(samlSsoRequestContext, assertion));

		boolean attributesEnabled = MetadataManagerUtil.isAttributesEnabled(
			samlMessageContext.getPeerEntityId());

		if (!attributesEnabled) {
			return assertion;
		}

		AttributeResolver attributeResolver =
			AttributeResolverFactory.getAttributeResolver(
				samlMessageContext.getPeerEntityId());

		User user = samlSsoRequestContext.getUser();

		List<Attribute> attributes = attributeResolver.resolve(
			user, samlMessageContext);

		if (attributes.isEmpty()) {
			return assertion;
		}

		List<AttributeStatement> attributeStatements =
			assertion.getAttributeStatements();

		AttributeStatement attributeStatement =
			OpenSamlUtil.buildAttributeStatement();

		attributeStatements.add(attributeStatement);

		List<Attribute> attributeStatementAttributes =
			attributeStatement.getAttributes();

		attributeStatementAttributes.addAll(attributes);

		return assertion;
	}

	protected AudienceRestriction getSuccessAudienceRestriction(
		String entityId) {

		AudienceRestriction audienceRestriction =
			OpenSamlUtil.buildAudienceRestriction();

		List<Audience> audiences = audienceRestriction.getAudiences();

		Audience audience = OpenSamlUtil.buildAudience();

		audience.setAudienceURI(entityId);

		audiences.add(audience);

		return audienceRestriction;
	}

	protected AuthnContext getSuccessAuthnContext() {
		AuthnContext authnContext = OpenSamlUtil.buildAuthnContext();

		AuthnContextClassRef authnContextClassRef =
			OpenSamlUtil.buildAuthnContextClassRef();

		authnContextClassRef.setAuthnContextClassRef(
			AuthnContext.UNSPECIFIED_AUTHN_CTX);

		authnContext.setAuthnContextClassRef(authnContextClassRef);

		return authnContext;
	}

	protected AuthnStatement getSuccessAuthnStatement(
		SamlSsoRequestContext samlSsoRequestContext, Assertion assertion) {

		AuthnStatement authnStatement = OpenSamlUtil.buildAuthnStatement();

		AuthnContext authnContext = getSuccessAuthnContext();

		authnStatement.setAuthnContext(authnContext);

		authnStatement.setAuthnInstant(assertion.getIssueInstant());
		authnStatement.setSessionIndex(
			samlSsoRequestContext.getSamlSsoSessionId());

		return authnStatement;
	}

	protected Conditions getSuccessConditions(
		SamlSsoRequestContext samlSsoRequestContext, DateTime notBeforeDateTime,
		DateTime notOnOrAfterDateTime) {

		Conditions conditions = OpenSamlUtil.buildConditions();

		conditions.setNotBefore(notBeforeDateTime);
		conditions.setNotOnOrAfter(notOnOrAfterDateTime);

		List<AudienceRestriction> audienceRestrictions =
			conditions.getAudienceRestrictions();

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		AudienceRestriction audienceRestriction = getSuccessAudienceRestriction(
			samlMessageContext.getPeerEntityId());

		audienceRestrictions.add(audienceRestriction);

		return conditions;
	}

	protected NameID getSuccessNameId(
			SamlSsoRequestContext samlSsoRequestContext)
		throws Exception {

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		NameIdResolver nameIDResolver = NameIdResolverFactory.getNameIdResolver(
			samlMessageContext.getPeerEntityId());

		User user = samlSsoRequestContext.getUser();

		NameIDPolicy nameIDPolicy = null;

		AuthnRequest authnRequest = samlMessageContext.getInboundSAMLMessage();

		if (authnRequest != null) {
			nameIDPolicy = authnRequest.getNameIDPolicy();
		}

		return nameIDResolver.resolve(
			user, samlMessageContext.getPeerEntityId(), nameIDPolicy);
	}

	protected Response getSuccessResponse(
		SamlSsoRequestContext samlSsoRequestContext,
		AssertionConsumerService assertionConsumerService,
		Assertion assertion) {

		Response response = OpenSamlUtil.buildResponse();

		response.setDestination(assertionConsumerService.getLocation());
		response.setID(generateIdentifier(20));

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		if (Validator.isNotNull(samlMessageContext.getInboundSAMLMessageId())) {
			response.setInResponseTo(
				samlMessageContext.getInboundSAMLMessageId());
		}

		response.setIssueInstant(assertion.getIssueInstant());

		Issuer issuer = OpenSamlUtil.buildIssuer(
			samlMessageContext.getLocalEntityId());

		response.setIssuer(issuer);

		StatusCode statusCode = OpenSamlUtil.buildStatusCode(
			StatusCode.SUCCESS_URI);

		Status status = OpenSamlUtil.buildStatus(statusCode);

		response.setStatus(status);

		response.setVersion(SAMLVersion.VERSION_20);

		List<Assertion> assertions = response.getAssertions();

		assertions.add(assertion);

		return response;
	}

	protected Subject getSuccessSubject(
		SamlSsoRequestContext samlSsoRequestContext,
		AssertionConsumerService assertionConsumerService, NameID nameId,
		SubjectConfirmationData subjectConfirmationData) {

		SubjectConfirmation subjectConfirmation =
			OpenSamlUtil.buildSubjectConfirmation();

		subjectConfirmation.setMethod(SubjectConfirmation.METHOD_BEARER);
		subjectConfirmation.setSubjectConfirmationData(subjectConfirmationData);

		Subject subject = OpenSamlUtil.buildSubject(nameId);

		List<SubjectConfirmation> subjectConfirmations =
			subject.getSubjectConfirmations();

		subjectConfirmations.add(subjectConfirmation);

		return subject;
	}

	protected SubjectConfirmationData getSuccessSubjectConfirmationData(
		SamlSsoRequestContext samlSsoRequestContext,
		AssertionConsumerService assertionConsumerService,
		DateTime issueInstantDateTime) {

		SubjectConfirmationData subjectConfirmationData =
			OpenSamlUtil.buildSubjectConfirmationData();

		subjectConfirmationData.setRecipient(
			assertionConsumerService.getLocation());

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		int assertionLifetime = MetadataManagerUtil.getAssertionLifetime(
			samlMessageContext.getPeerEntityId());

		DateTime notOnOrAfterDateTime = issueInstantDateTime.plusSeconds(
			assertionLifetime);

		subjectConfirmationData.setNotOnOrAfter(notOnOrAfterDateTime);

		return subjectConfirmationData;
	}

	protected void redirectToLogin(
			HttpServletRequest request, HttpServletResponse response,
			SamlSsoRequestContext samlSsoRequestContext, boolean forceAuthn)
		throws SystemException {

		HttpSession session = request.getSession();

		if (forceAuthn) {
			session.invalidate();

			session = request.getSession(true);

			session.setAttribute(
				PortletWebKeys.FORCE_REAUHENTICATION, Boolean.TRUE);
		}

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		samlMessageContext.setInboundMessageTransport(null);
		samlMessageContext.setOutboundMessageTransport(null);

		session.setAttribute(
			PortletWebKeys.SAML_SSO_REQUEST_CONTEXT, samlSsoRequestContext);

		response.addHeader(
			HttpHeaders.CACHE_CONTROL,
			HttpHeaders.CACHE_CONTROL_NO_CACHE_VALUE);
		response.addHeader(
			HttpHeaders.PRAGMA, HttpHeaders.PRAGMA_NO_CACHE_VALUE);

		StringBundler sb = new StringBundler(4);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		sb.append(themeDisplay.getPathMain());
		sb.append("/portal/login?redirect=");
		sb.append(themeDisplay.getPathMain());
		sb.append("/portal/saml/sso");

		String redirect = sb.toString();

		try {
			response.sendRedirect(redirect);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	protected void sendFailureResponse(
			SamlSsoRequestContext samlSsoRequestContext, String statusURI)
		throws PortalException {

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_POST_BINDING_URI);

		AssertionConsumerService assertionConsumerService =
			SamlUtil.resolverAssertionConsumerService(
				samlMessageContext, samlBinding.getCommunicationProfileId());

		samlMessageContext.setPeerEntityEndpoint(assertionConsumerService);

		try {
			Credential credential = MetadataManagerUtil.getSigningCredential();

			samlMessageContext.setOutboundSAMLMessageSigningCredential(
				credential);
		}
		catch (SecurityException se) {
			throw new SamlException(se);
		}

		Response response = OpenSamlUtil.buildResponse();

		response.setDestination(assertionConsumerService.getLocation());
		response.setInResponseTo(samlMessageContext.getInboundSAMLMessageId());

		DateTime issueInstantDateTime = new DateTime(DateTimeZone.UTC);

		response.setIssueInstant(issueInstantDateTime);

		Issuer issuer = OpenSamlUtil.buildIssuer(
			samlMessageContext.getLocalEntityId());

		response.setIssuer(issuer);

		StatusCode statusCode = OpenSamlUtil.buildStatusCode(statusURI);

		Status status = OpenSamlUtil.buildStatus(statusCode);

		response.setStatus(status);

		samlMessageContext.setOutboundSAMLMessage(response);

		sendSamlMessage(samlMessageContext);
	}

	protected void sendSuccessResponse(
			HttpServletRequest request, HttpServletResponse response,
			SamlSsoRequestContext samlSsoRequestContext)
		throws Exception {

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		SamlBinding samlBinding = getSamlBinding(
			SAMLConstants.SAML2_POST_BINDING_URI);

		AssertionConsumerService assertionConsumerService =
			SamlUtil.resolverAssertionConsumerService(
				samlMessageContext, samlBinding.getCommunicationProfileId());

		NameID nameId = getSuccessNameId(samlSsoRequestContext);

		Assertion assertion = getSuccessAssertion(
			samlSsoRequestContext, assertionConsumerService, nameId);

		Credential credential = MetadataManagerUtil.getSigningCredential();

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getPeerEntityRoleMetadata();

		if (spSsoDescriptor.getWantAssertionsSigned()) {
			OpenSamlUtil.signObject(assertion, credential);
		}

		Response samlResponse = getSuccessResponse(
			samlSsoRequestContext, assertionConsumerService, assertion);

		samlMessageContext.setOutboundSAMLMessage(samlResponse);

		samlMessageContext.setOutboundSAMLMessageSigningCredential(credential);
		samlMessageContext.setOutboundSAMLProtocol(SAMLConstants.SAML20P_NS);

		samlMessageContext.setPeerEntityEndpoint(assertionConsumerService);

		if (samlSsoRequestContext.isNewSession()) {
			addSamlSsoSession(request, response, samlSsoRequestContext, nameId);
		}
		else {
			updateSamlSsoSession(request, samlSsoRequestContext, nameId);
		}

		sendSamlMessage(samlMessageContext);
	}

	protected void updateSamlSsoSession(
			HttpServletRequest request,
			SamlSsoRequestContext samlSsoRequestContext, NameID nameId)
		throws Exception {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			request);

		SamlIdpSsoSession samlIdpSsoSession =
			SamlIdpSsoSessionLocalServiceUtil.updateModifiedDate(
				samlSsoRequestContext.getSamlSsoSessionId());

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		try {
			SamlIdpSpSessionLocalServiceUtil.updateModifiedDate(
				samlIdpSsoSession.getSamlIdpSsoSessionId(),
				samlMessageContext.getPeerEntityId());
		}
		catch (NoSuchIdpSpSessionException nsisse) {
			SamlIdpSpSessionLocalServiceUtil.addSamlIdpSpSession(
				samlIdpSsoSession.getSamlIdpSsoSessionId(),
				samlMessageContext.getPeerEntityId(), nameId.getFormat(),
				nameId.getValue(), serviceContext);
		}
	}

	protected void verifyAssertion(
			Assertion assertion,
			SAMLMessageContext<?, ?, NameID> samlMessageContext,
			TrustEngine<Signature> trustEngine)
		throws PortalException {

		verifyReplay(samlMessageContext, assertion);
		verifyIssuer(samlMessageContext, assertion.getIssuer());
		verifyAssertionSignature(
			assertion.getSignature(), samlMessageContext, trustEngine);
		verifyConditions(samlMessageContext, assertion.getConditions());
		verifySubject(samlMessageContext, assertion.getSubject());
	}

	protected void verifyAssertionSignature(
			Signature signature, SAMLMessageContext<?, ?, ?> samlMessageContext,
			TrustEngine<Signature> trustEngine)
		throws PortalException {

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		if (signature != null) {
			verifySignature(samlMessageContext, signature, trustEngine);
		}
		else if (spSsoDescriptor.getWantAssertionsSigned() &&
				 (signature == null)) {

			throw new SignatureException("SAML assertion is not signed");
		}
	}

	protected void verifyAudienceRestrictions(
			List<AudienceRestriction> audienceRestrictions,
			SAMLMessageContext<?, ?, ?> samlMessageContext)
		throws PortalException {

		if (audienceRestrictions.isEmpty()) {
			return;
		}

		for (AudienceRestriction audienceRestriction : audienceRestrictions) {
			for (Audience audience : audienceRestriction.getAudiences()) {
				String audienceURI = audience.getAudienceURI();

				if (audienceURI.equals(samlMessageContext.getLocalEntityId())) {
					return;
				}
			}
		}

		throw new AudienceException("Unable verify audience");
	}

	protected void verifyConditions(
			SAMLMessageContext<?, ?, ?> samlMessageContext,
			Conditions conditions)
		throws PortalException {

		verifyAudienceRestrictions(
			conditions.getAudienceRestrictions(), samlMessageContext);

		if (conditions.getNotOnOrAfter() != null) {
			verifyNotOnOrAfterDateTime(
				MetadataManagerUtil.getClockSkew(),
				conditions.getNotOnOrAfter());
		}
	}

	protected void verifyDestination(
			SAMLMessageContext<?, ?, ?> samlMessageContext, String destination)
		throws PortalException {

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		for (AssertionConsumerService assertionConsumerService :
				assertionConsumerServices) {

			String binding = assertionConsumerService.getBinding();

			if (destination.equals(assertionConsumerService.getLocation()) &&
				binding.equals(
					samlMessageContext.getCommunicationProfileId())) {

				return;
			}
		}

		throw new DestinationException(
			"Destination " + destination + " does not match any assertion " +
				"consumer location with binding " +
					samlMessageContext.getCommunicationProfileId());
	}

	protected void verifyInResponseTo(Response samlResponse)
		throws PortalException, SystemException {

		if (Validator.isNull(samlResponse.getInResponseTo())) {
			return;
		}

		Issuer issuer = samlResponse.getIssuer();
		String issuerEntityId = issuer.getValue();
		String inResponseTo = samlResponse.getInResponseTo();

		SamlSpAuthRequest samlSpAuthRequest =
			SamlSpAuthRequestLocalServiceUtil.fetchSamlSpAuthRequest(
				issuerEntityId, inResponseTo);

		if (samlSpAuthRequest != null) {
			SamlSpAuthRequestLocalServiceUtil.deleteSamlSpAuthRequest(
				samlSpAuthRequest);
		}
		else {
			throw new InResponseToException(
				"Response in response to " + inResponseTo + " does not match " +
					"any authentication requests");
		}
	}

	protected void verifyIssuer(
			SAMLMessageContext<?, ?, ?> samlMessageContext, Issuer issuer)
		throws PortalException {

		String issuerFormat = issuer.getFormat();

		if ((issuerFormat != null) && !issuerFormat.equals(NameIDType.ENTITY)) {
			throw new IssuerException("Invalid issuer format " + issuerFormat);
		}

		String peerEntityId = samlMessageContext.getPeerEntityId();

		if (!peerEntityId.equals(issuer.getValue())) {
			throw new IssuerException(
				"Issuer does not match expected peer entity ID " +
					peerEntityId);
		}
	}

	protected void verifyNotOnOrAfterDateTime(long clockSkew, DateTime dateTime)
		throws PortalException {

		DateTime nowDateTime = new DateTime(DateTimeZone.UTC);

		DateTime upperBoundDateTime = dateTime.plusMillis((int)clockSkew);

		if (upperBoundDateTime.isBefore(nowDateTime)) {
			throw new ExpiredException(
				"Date " + upperBoundDateTime.toString() + " is before " +
					nowDateTime.toString());
		}
	}

	protected void verifyReplay(
			SAMLMessageContext<?, ?, ?> samlMessageContext, Assertion assertion)
		throws PortalException {

		Issuer issuer = assertion.getIssuer();
		String idpEntityId = issuer.getValue();
		String messageKey = assertion.getID();

		DateTime notOnOrAfter = new DateTime();

		notOnOrAfter = notOnOrAfter.plusMillis(
			PortletPropsValues.SAML_REPLAY_CACHE_DURATION);

		try {
			SamlSpMessage samlSpMessage =
				SamlSpMessageLocalServiceUtil.fetchSamlSpMessage(
					idpEntityId, messageKey);

			if ((samlSpMessage != null) && !samlSpMessage.isExpired()) {
				throw new ReplayException(
					"SAML assertion " + messageKey + " replayed from IdP " +
						idpEntityId);
			}
			else {
				if (samlSpMessage != null) {
					SamlSpMessageLocalServiceUtil.deleteSamlSpMessage(
						samlSpMessage);
				}

				ServiceContext serviceContext = new ServiceContext();

				long companyId = CompanyThreadLocal.getCompanyId();

				serviceContext.setCompanyId(companyId);

				SamlSpMessageLocalServiceUtil.addSamlSpMessage(
					idpEntityId, messageKey, notOnOrAfter.toDate(),
					serviceContext);
			}
		}
		catch (SystemException se) {
			throw new SamlException(se);
		}
	}

	protected void verifySignature(
			SAMLMessageContext<?, ?, ?> samlMessageContext, Signature signature,
			TrustEngine<Signature> trustEngine)
		throws PortalException {

		try {
			_samlSignatureProfileValidator.validate(signature);

			CriteriaSet criteriaSet = new CriteriaSet();

			criteriaSet.add(
				new EntityIDCriteria(samlMessageContext.getPeerEntityId()));
			criteriaSet.add(
				new MetadataCriteria(
					IDPSSODescriptor.DEFAULT_ELEMENT_NAME,
					SAMLConstants.SAML20P_NS));
			criteriaSet.add(new UsageCriteria(UsageType.SIGNING));

			if (!trustEngine.validate(signature, criteriaSet)) {
				throw new SignatureException("Unable validate signature trust");
			}
		}
		catch (Exception e) {
			if (e instanceof PortalException) {
				throw (PortalException)e;
			}

			throw new SignatureException("Unable to verify signature", e);
		}
	}

	protected void verifySubject(
			SAMLMessageContext<?, ?, NameID> samlMessageContext,
			Subject subject)
		throws PortalException {

		List<SubjectConfirmation> subjectConfirmations =
			subject.getSubjectConfirmations();

		for (SubjectConfirmation subjectConfirmation : subjectConfirmations) {
			String method = subjectConfirmation.getMethod();

			if (method.equals(SubjectConfirmation.METHOD_BEARER)) {
				SubjectConfirmationData subjectConfirmationData =
					subjectConfirmation.getSubjectConfirmationData();

				if (subjectConfirmationData == null) {
					continue;
				}

				verifyNotOnOrAfterDateTime(
					MetadataManagerUtil.getClockSkew(),
					subjectConfirmationData.getNotOnOrAfter());

				if (Validator.isNull(subjectConfirmationData.getRecipient())) {
					continue;
				}
				else {
					verifyDestination(
						samlMessageContext,
						subjectConfirmationData.getRecipient());
				}

				NameID nameId = subject.getNameID();

				samlMessageContext.setSubjectNameIdentifier(nameId);

				return;
			}
		}

		throw new SubjectException("Unable to verify subject");
	}

	private static Log _log = LogFactoryUtil.getLog(WebSsoProfileImpl.class);

	private static SAMLSignatureProfileValidator
		_samlSignatureProfileValidator = new SAMLSignatureProfileValidator();

}