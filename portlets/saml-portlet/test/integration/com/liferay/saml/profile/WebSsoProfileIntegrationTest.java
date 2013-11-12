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

import com.liferay.saml.AudienceException;
import com.liferay.saml.BaseSamlTestCase;
import com.liferay.saml.DestinationException;
import com.liferay.saml.ExpiredException;
import com.liferay.saml.InResponseToException;
import com.liferay.saml.IssuerException;
import com.liferay.saml.SamlSsoRequestContext;
import com.liferay.saml.SignatureException;
import com.liferay.saml.SubjectException;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.model.SamlSpAuthRequest;
import com.liferay.saml.model.impl.SamlSpAuthRequestImpl;
import com.liferay.saml.service.SamlSpAuthRequestLocalService;
import com.liferay.saml.service.SamlSpAuthRequestLocalServiceUtil;
import com.liferay.saml.service.SamlSpMessageLocalService;
import com.liferay.saml.service.SamlSpMessageLocalServiceUtil;
import com.liferay.saml.util.OpenSamlUtil;
import com.liferay.saml.util.PortletWebKeys;
import com.liferay.saml.util.SamlUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.AudienceRestriction;
import org.opensaml.saml2.core.AuthnRequest;
import org.opensaml.saml2.core.Conditions;
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.NameIDType;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmation;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.security.credential.Credential;

import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Mika Koivisto
 * @author Matthew Tambara
 * @author William Newbury
 */
@RunWith(PowerMockRunner.class)
public class WebSsoProfileIntegrationTest extends BaseSamlTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_samlSpAuthRequestLocalService = getMockPortletService(
			SamlSpAuthRequestLocalServiceUtil.class,
			SamlSpAuthRequestLocalService.class);

		_webSsoProfileImpl.setIdentifierGenerator(identifierGenerator);
		_webSsoProfileImpl.setSamlBindings(samlBindings);

		prepareServiceProvider(SP_ENTITY_ID);
	}

	@Test
	public void testDecodeAuthnRequestIdpInitiatedSso() throws Exception {
		prepareIdentityProvider(IDP_ENTITY_ID);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(SSO_URL);

		mockHttpServletRequest.setParameter("entityId", IDP_ENTITY_ID);
		mockHttpServletRequest.setParameter("RelayState", RELAY_STATE);

		SamlSsoRequestContext samlSsoRequestContext =
			_webSsoProfileImpl.decodeAuthnRequest(
				mockHttpServletRequest, new MockHttpServletResponse());

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		Assert.assertEquals(
			IDP_ENTITY_ID, samlMessageContext.getLocalEntityId());
		Assert.assertNotNull(samlMessageContext.getLocalEntityMetadata());
		Assert.assertNotNull(samlMessageContext.getLocalEntityRoleMetadata());
		Assert.assertTrue(
			samlMessageContext.getLocalEntityRoleMetadata() instanceof
				IDPSSODescriptor);
		Assert.assertEquals(
			IDP_ENTITY_ID, samlMessageContext.getPeerEntityId());
		Assert.assertNotNull(samlMessageContext.getPeerEntityMetadata());
		Assert.assertNull(samlMessageContext.getPeerEntityRoleMetadata());
		Assert.assertEquals(RELAY_STATE, samlMessageContext.getRelayState());
		Assert.assertTrue(samlSsoRequestContext.isNewSession());
	}

	@Test
	public void testDecodeAuthnRequestStageAuthenticated() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(LOGIN_URL);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_webSsoProfileImpl.doSendAuthnRequest(
			mockHttpServletRequest, mockHttpServletResponse, RELAY_STATE);

		String redirect = mockHttpServletResponse.getRedirectedUrl();

		prepareIdentityProvider(IDP_ENTITY_ID);

		mockHttpServletRequest = getMockHttpServletRequest(redirect);

		mockHttpServletResponse = new MockHttpServletResponse();

		SamlSsoRequestContext samlSsoRequestContext =
			_webSsoProfileImpl.decodeAuthnRequest(
				mockHttpServletRequest, mockHttpServletResponse);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		mockHttpServletRequest = getMockHttpServletRequest(SSO_URL);

		HttpSession mockSession = mockHttpServletRequest.getSession();

		mockSession.setAttribute(
			PortletWebKeys.SAML_SSO_REQUEST_CONTEXT, samlSsoRequestContext);

		when(
			portal.getUserId(Mockito.any(MockHttpServletRequest.class))
		).thenReturn(
			1000l
		);

		samlSsoRequestContext = _webSsoProfileImpl.decodeAuthnRequest(
			mockHttpServletRequest, mockHttpServletResponse);

		samlMessageContext = samlSsoRequestContext.getSAMLMessageContext();

		Assert.assertNull(
			mockSession.getAttribute(PortletWebKeys.SAML_SSO_REQUEST_CONTEXT));
		Assert.assertEquals(
			SamlSsoRequestContext.STAGE_AUTHENTICATED,
			samlSsoRequestContext.getStage());
		Assert.assertEquals(1000, samlSsoRequestContext.getUserId());

		HttpServletResponseAdapter httpServletResponseAdapter =
			(HttpServletResponseAdapter)
				samlMessageContext.getOutboundMessageTransport();

		Assert.assertEquals(
			mockHttpServletResponse,
			httpServletResponseAdapter.getWrappedResponse());

		HttpServletRequestAdapter httpServletRequestAdapter =
			(HttpServletRequestAdapter)
				samlMessageContext.getInboundMessageTransport();

		Assert.assertEquals(
			mockHttpServletRequest,
			httpServletRequestAdapter.getWrappedRequest());
	}

	@Test
	public void testDecodeAuthnRequestStageInitial() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(LOGIN_URL);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_webSsoProfileImpl.doSendAuthnRequest(
			mockHttpServletRequest, mockHttpServletResponse, RELAY_STATE);

		String redirect = mockHttpServletResponse.getRedirectedUrl();

		Assert.assertNotNull(redirect);

		prepareIdentityProvider(IDP_ENTITY_ID);

		mockHttpServletRequest = getMockHttpServletRequest(redirect);

		mockHttpServletResponse = new MockHttpServletResponse();

		SamlSsoRequestContext samlSsoRequestContext =
			_webSsoProfileImpl.decodeAuthnRequest(
				mockHttpServletRequest, mockHttpServletResponse);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		Assert.assertEquals(
			IDP_ENTITY_ID, samlMessageContext.getLocalEntityId());
		Assert.assertNotNull(samlMessageContext.getLocalEntityMetadata());
		Assert.assertNotNull(samlMessageContext.getLocalEntityRoleMetadata());
		Assert.assertTrue(
			samlMessageContext.getLocalEntityRoleMetadata() instanceof
				IDPSSODescriptor);
		Assert.assertEquals(SP_ENTITY_ID, samlMessageContext.getPeerEntityId());
		Assert.assertNotNull(samlMessageContext.getPeerEntityMetadata());
		Assert.assertNotNull(samlMessageContext.getPeerEntityRoleMetadata());
		Assert.assertTrue(
			samlMessageContext.getPeerEntityRoleMetadata() instanceof
				SPSSODescriptor);
		Assert.assertEquals(RELAY_STATE, samlMessageContext.getRelayState());

		AuthnRequest authnRequest = samlMessageContext.getInboundSAMLMessage();

		Assert.assertEquals(identifiers.get(0), authnRequest.getID());
		Assert.assertEquals(2, identifiers.size());
		Assert.assertTrue(samlSsoRequestContext.isNewSession());
	}

	@Test(expected = SignatureException.class)
	public void testVerifyAssertionSignatureInvalidSignature()
		throws Exception {

		Assertion assertion = OpenSamlUtil.buildAssertion();

		Credential credential = getCredential(UNKNOWN_ENTITY_ID);

		OpenSamlUtil.signObject(assertion, credential);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		_webSsoProfileImpl.verifyAssertionSignature(
			assertion.getSignature(), samlMessageContext,
			MetadataManagerUtil.getSignatureTrustEngine());
	}

	@Test
	public void testVerifyAssertionSignatureNoSignatureNotRequired()
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		EntityDescriptor entityDescriptor =
			samlMessageContext.getLocalEntityMetadata();

		SPSSODescriptor spSsoDescriptor = entityDescriptor.getSPSSODescriptor(
			SAMLConstants.SAML20P_NS);

		spSsoDescriptor.setWantAssertionsSigned(false);

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		_webSsoProfileImpl.verifyAssertionSignature(
			null, samlMessageContext,
			MetadataManagerUtil.getSignatureTrustEngine());
	}

	@Test(expected = SignatureException.class)
	public void testVerifyAssertionSignatureNoSignatureRequired()
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		EntityDescriptor entityDescriptor =
			samlMessageContext.getLocalEntityMetadata();

		SPSSODescriptor spSsoDescriptor = entityDescriptor.getSPSSODescriptor(
			SAMLConstants.SAML20P_NS);

		spSsoDescriptor.setWantAssertionsSigned(true);

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		_webSsoProfileImpl.verifyAssertionSignature(
			null, samlMessageContext,
			MetadataManagerUtil.getSignatureTrustEngine());
	}

	@Test
	public void testVerifyAssertionSignatureValidSignature() throws Exception {
		Assertion assertion = OpenSamlUtil.buildAssertion();

		Credential credential = getCredential(IDP_ENTITY_ID);

		OpenSamlUtil.signObject(assertion, credential);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		_webSsoProfileImpl.verifyAssertionSignature(
			assertion.getSignature(), samlMessageContext,
			MetadataManagerUtil.getSignatureTrustEngine());
	}

	@Test
	public void testVerifyAudienceRestrictionsAllow() throws Exception {
		List<AudienceRestriction> audienceRestrictions =
			new ArrayList<AudienceRestriction>();

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		AudienceRestriction audienceRestriction =
			_webSsoProfileImpl.getSuccessAudienceRestriction(
				samlMessageContext.getLocalEntityId());

		audienceRestrictions.add(audienceRestriction);

		_webSsoProfileImpl.verifyAudienceRestrictions(
			audienceRestrictions, samlMessageContext);
	}

	@Test(expected = AudienceException.class)
	public void testVerifyAudienceRestrictionsDeny() throws Exception {
		List<AudienceRestriction> audienceRestrictions =
			new ArrayList<AudienceRestriction>();

		AudienceRestriction audienceRestriction =
			_webSsoProfileImpl.getSuccessAudienceRestriction(UNKNOWN_ENTITY_ID);

		audienceRestrictions.add(audienceRestriction);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		_webSsoProfileImpl.verifyAudienceRestrictions(
			audienceRestrictions, samlMessageContext);
	}

	@Test
	public void testVerifyConditionsNoDates() throws Exception {
		prepareIdentityProvider(IDP_ENTITY_ID);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(SSO_URL);

		SAMLMessageContext<AuthnRequest, Response, NameID>
			idpSamlMessageContext =
				(SAMLMessageContext<AuthnRequest, Response, NameID>)
					_webSsoProfileImpl.getSamlMessageContext(
						mockHttpServletRequest, new MockHttpServletResponse());

		idpSamlMessageContext.setPeerEntityId(SP_ENTITY_ID);

		SamlSsoRequestContext samlSsoRequestContext = new SamlSsoRequestContext(
			idpSamlMessageContext);

		Conditions conditions = _webSsoProfileImpl.getSuccessConditions(
			samlSsoRequestContext, null, null);

		prepareServiceProvider(SP_ENTITY_ID);

		mockHttpServletRequest = getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> spSamlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		_webSsoProfileImpl.verifyConditions(spSamlMessageContext, conditions);
	}

	@Test
	public void testVerifyDestinationAllow() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setCommunicationProfileId(
			SAMLConstants.SAML2_POST_BINDING_URI);

		_webSsoProfileImpl.verifyDestination(samlMessageContext, ACS_URL);
	}

	@Test(expected = DestinationException.class)
	public void testVerifyDestinationDeny() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setCommunicationProfileId(
			SAMLConstants.SAML2_POST_BINDING_URI);

		_webSsoProfileImpl.verifyDestination(
			samlMessageContext, "http://www.fail.com/c/portal/saml/acs");
	}

	@Test
	public void testVerifyInResponseToInvalidResponse() throws Exception {
		Response response = OpenSamlUtil.buildResponse();

		response.setIssuer(OpenSamlUtil.buildIssuer(IDP_ENTITY_ID));

		_webSsoProfileImpl.verifyInResponseTo(response);
	}

	@Test(expected = InResponseToException.class)
	public void testVerifyInResponseToNoAuthnRequest() throws Exception {
		Response response = OpenSamlUtil.buildResponse();

		response.setInResponseTo("responseto");
		response.setIssuer(OpenSamlUtil.buildIssuer(IDP_ENTITY_ID));

		_webSsoProfileImpl.verifyInResponseTo(response);
	}

	@Test
	public void testVerifyInResponseToValidResponse() throws Exception {
		Response response = OpenSamlUtil.buildResponse();

		SamlSpAuthRequest samlSpAuthRequest = new SamlSpAuthRequestImpl();

		samlSpAuthRequest.setSamlIdpEntityId(IDP_ENTITY_ID);

		String samlSpAuthRequestKey = identifierGenerator.generateIdentifier(
			30);

		samlSpAuthRequest.setSamlSpAuthRequestKey(samlSpAuthRequestKey);

		when(
			_samlSpAuthRequestLocalService.fetchSamlSpAuthRequest(
				Mockito.any(String.class), Mockito.any(String.class))
		).thenReturn(
			samlSpAuthRequest
		);

		response.setInResponseTo(samlSpAuthRequestKey);
		response.setIssuer(OpenSamlUtil.buildIssuer(IDP_ENTITY_ID));

		_webSsoProfileImpl.verifyInResponseTo(response);
	}

	@Test(expected = IssuerException.class)
	public void testVerifyIssuerInvalidFormat() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		Issuer issuer = OpenSamlUtil.buildIssuer(IDP_ENTITY_ID);

		issuer.setFormat(NameIDType.UNSPECIFIED);

		_webSsoProfileImpl.verifyIssuer(samlMessageContext, issuer);
	}

	@Test(expected = IssuerException.class)
	public void testVerifyIssuerInvalidIssuer() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		_webSsoProfileImpl.verifyIssuer(
			samlMessageContext, OpenSamlUtil.buildIssuer(UNKNOWN_ENTITY_ID));
	}

	@Test
	public void testVerifyIssuerValidIssuer() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		_webSsoProfileImpl.verifyIssuer(
			samlMessageContext, OpenSamlUtil.buildIssuer(IDP_ENTITY_ID));
	}

	@Test
	public void testVerifyReplayNoConditionsDate() throws Exception {
		prepareIdentityProvider(IDP_ENTITY_ID);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(SSO_URL);

		SAMLMessageContext<AuthnRequest, Response, NameID>
			idpSamlMessageContext =
				(SAMLMessageContext<AuthnRequest, Response, NameID>)
					_webSsoProfileImpl.getSamlMessageContext(
						mockHttpServletRequest, new MockHttpServletResponse());

		idpSamlMessageContext.setPeerEntityId(SP_ENTITY_ID);

		SamlSsoRequestContext samlSsoRequestContext = new SamlSsoRequestContext(
			idpSamlMessageContext);

		Conditions conditions = _webSsoProfileImpl.getSuccessConditions(
			samlSsoRequestContext, null, null);

		prepareServiceProvider(SP_ENTITY_ID);

		mockHttpServletRequest = getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<?, ?, ?> spSamlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		Assertion assertion = OpenSamlUtil.buildAssertion();

		assertion.setConditions(conditions);

		Issuer issuer = OpenSamlUtil.buildIssuer(IDP_ENTITY_ID);

		assertion.setIssuer(issuer);

		String messageId = identifierGenerator.generateIdentifier();

		assertion.setID(messageId);

		SamlSpMessageLocalService samlSpMessageLocalService =
			getMockPortletService(
				SamlSpMessageLocalServiceUtil.class,
				SamlSpMessageLocalService.class);

		when(
			samlSpMessageLocalService.fetchSamlSpMessage(
				Mockito.eq(IDP_ENTITY_ID), Mockito.eq(messageId))
		).thenReturn(
			null
		);

		_webSsoProfileImpl.verifyReplay(spSamlMessageContext, assertion);
	}

	@Test(expected = ExpiredException.class)
	public void testVerifySubjectExpired() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			(SAMLMessageContext<AuthnRequest, Response, NameID>)
				_webSsoProfileImpl.getSamlMessageContext(
					mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setCommunicationProfileId(
			SAMLConstants.SAML2_POST_BINDING_URI);
		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		NameID nameId = OpenSamlUtil.buildNameId(
			NameIDType.UNSPECIFIED, "test");

		DateTime issueDate = new DateTime(DateTimeZone.UTC);

		issueDate = issueDate.minusYears(1);

		Subject subject = getSubject(samlMessageContext, nameId, issueDate);

		_webSsoProfileImpl.verifySubject(samlMessageContext, subject);
	}

	@Test(expected = SubjectException.class)
	public void testVerifySubjectNoBearerSubjectConfirmation()
		throws Exception {

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			(SAMLMessageContext<AuthnRequest, Response, NameID>)
				_webSsoProfileImpl.getSamlMessageContext(
					mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setCommunicationProfileId(
			SAMLConstants.SAML2_POST_BINDING_URI);
		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		NameID nameId = OpenSamlUtil.buildNameId(
			NameIDType.UNSPECIFIED, "test");

		Subject subject = getSubject(
			samlMessageContext, nameId, new DateTime());

		List<SubjectConfirmation> subjectConfirmations =
			subject.getSubjectConfirmations();

		SubjectConfirmation subjectConfirmation = subjectConfirmations.get(0);

		subjectConfirmation.setMethod(
			SubjectConfirmation.METHOD_SENDER_VOUCHES);

		_webSsoProfileImpl.verifySubject(samlMessageContext, subject);
	}

	@Test
	public void testVerifySubjectValidSubject() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(ACS_URL);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			(SAMLMessageContext<AuthnRequest, Response, NameID>)
				_webSsoProfileImpl.getSamlMessageContext(
					mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setCommunicationProfileId(
			SAMLConstants.SAML2_POST_BINDING_URI);
		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		NameID nameId = OpenSamlUtil.buildNameId(
			NameIDType.UNSPECIFIED, "test");

		Subject subject = getSubject(
			samlMessageContext, nameId, new DateTime());

		_webSsoProfileImpl.verifySubject(samlMessageContext, subject);

		NameID resolvedNameId = samlMessageContext.getSubjectNameIdentifier();

		Assert.assertNotNull(resolvedNameId);
		Assert.assertEquals(nameId.getFormat(), resolvedNameId.getFormat());
		Assert.assertEquals(nameId.getValue(), resolvedNameId.getValue());
	}

	protected Subject getSubject(
			SAMLMessageContext<AuthnRequest, Response, NameID>
				samlMessageContext,
			NameID nameId, DateTime issueDate)
		throws Exception {

		SamlSsoRequestContext samlSsoRequestContext = new SamlSsoRequestContext(
			samlMessageContext);

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)samlMessageContext.getLocalEntityRoleMetadata();

		AssertionConsumerService assertionConsumerService =
			SamlUtil.getAssertionConsumerServiceForBinding(
				spSsoDescriptor, SAMLConstants.SAML2_POST_BINDING_URI);

		SubjectConfirmationData subjectConfirmationData =
			_webSsoProfileImpl.getSuccessSubjectConfirmationData(
				samlSsoRequestContext, assertionConsumerService, issueDate);

		return _webSsoProfileImpl.getSuccessSubject(
			samlSsoRequestContext, assertionConsumerService, nameId,
			subjectConfirmationData);
	}

	private SamlSpAuthRequestLocalService _samlSpAuthRequestLocalService;
	private WebSsoProfileImpl _webSsoProfileImpl = new WebSsoProfileImpl();

}