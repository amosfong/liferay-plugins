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
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.saml.BaseSamlTestCase;
import com.liferay.saml.SamlSsoRequestContext;
import com.liferay.saml.metadata.MetadataGeneratorUtil;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.service.SamlSpAuthRequestLocalService;
import com.liferay.saml.service.SamlSpAuthRequestLocalServiceUtil;
import com.liferay.saml.util.OpenSamlUtil;
import com.liferay.saml.util.PortletWebKeys;
import com.liferay.saml.util.SamlUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

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
import org.opensaml.saml2.core.Issuer;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.NameIDPolicy;
import org.opensaml.saml2.core.Response;
import org.opensaml.saml2.core.Subject;
import org.opensaml.saml2.core.SubjectConfirmationData;
import org.opensaml.saml2.metadata.AssertionConsumerService;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SPSSODescriptor;
import org.opensaml.saml2.metadata.SingleSignOnService;
import org.opensaml.ws.transport.http.HttpServletRequestAdapter;
import org.opensaml.ws.transport.http.HttpServletResponseAdapter;
import org.opensaml.xml.signature.Signature;

import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Mika Koivisto
 */

@RunWith(PowerMockRunner.class)
public class WebSsoProfileIntegrationTest extends BaseSamlTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_samlSpAuthRequestLocalService = mock(
			SamlSpAuthRequestLocalService.class);

		when(
			portletBeanLocator.locate(
				Mockito.eq(SamlSpAuthRequestLocalService.class.getName()))
		).thenReturn(
			_samlSpAuthRequestLocalService
		);

		_webSsoProfileImpl.setIdentifierGenerator(identifierGenerator);
		_webSsoProfileImpl.setSamlBindings(samlBindings);
	}

	@Test
	public void testDecodeAuthnRequestIdpInitiatedSso() throws Exception {
		prepareIdentityProvider(IDP_ENTITY_ID);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", SSO_URL);

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
		prepareServiceProvider(SP_ENTITY_ID);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", LOGIN_URL);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_webSsoProfileImpl.doSendAuthnRequest(
			mockHttpServletRequest, mockHttpServletResponse, RELAY_STATE);

		String redirect = mockHttpServletResponse.getRedirectedUrl();

		prepareIdentityProvider(IDP_ENTITY_ID);

		mockHttpServletRequest = getMockHttpServletRequest("GET", redirect);

		mockHttpServletResponse = new MockHttpServletResponse();

		SamlSsoRequestContext samlSsoRequestContext =
			_webSsoProfileImpl.decodeAuthnRequest(
				mockHttpServletRequest, mockHttpServletResponse);

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		mockHttpServletRequest = getMockHttpServletRequest("GET", SSO_URL);

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
		Assert.assertEquals(1000, samlSsoRequestContext.getUserId());
		Assert.assertEquals(
			SamlSsoRequestContext.STAGE_AUTHENTICATED,
			samlSsoRequestContext.getStage());

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
		prepareServiceProvider(SP_ENTITY_ID);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", LOGIN_URL);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_webSsoProfileImpl.doSendAuthnRequest(
			mockHttpServletRequest, mockHttpServletResponse, RELAY_STATE);

		String redirect = mockHttpServletResponse.getRedirectedUrl();

		Assert.assertNotNull(redirect);

		prepareIdentityProvider(IDP_ENTITY_ID);

		mockHttpServletRequest = getMockHttpServletRequest("GET", redirect);

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

	@Test
	public void testVerifyAssertionSignatureNoSignatureDontWant()
		throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		EntityDescriptor entityDescriptor =
			samlMessageContext.getLocalEntityMetadata();

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)entityDescriptor.getSPSSODescriptor(
				SAMLConstants.SAML20P_NS);

		spSsoDescriptor.setWantAssertionsSigned(false);

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		_webSsoProfileImpl.verifyAssertionSignature(
			null, samlMessageContext,
			MetadataManagerUtil.getSignatureTrustEngine());
	}

	@Test(expected = PortalException.class)
	public void testVerifyAssertionSignatureNoSignatureDoWant()
		throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		EntityDescriptor entityDescriptor =
			samlMessageContext.getLocalEntityMetadata();

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)entityDescriptor.getSPSSODescriptor(
				SAMLConstants.SAML20P_NS);

		spSsoDescriptor.setWantAssertionsSigned(true);

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		_webSsoProfileImpl.verifyAssertionSignature(
			null, samlMessageContext,
			MetadataManagerUtil.getSignatureTrustEngine());
	}

	@Test
	public void testVerifyAssertionSignatureValid() throws Exception {
		prepareServiceProvider(SP_ENTITY_ID);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_webSsoProfileImpl.doSendAuthnRequest(
			getMockHttpServletRequest("GET", LOGIN_URL),
			mockHttpServletResponse, RELAY_STATE);

		prepareIdentityProvider(IDP_ENTITY_ID);

		HttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(
				"GET", mockHttpServletResponse.getRedirectedUrl());

		SamlSsoRequestContext samlSsoRequestContext =
			_webSsoProfileImpl.decodeAuthnRequest(
				mockHttpServletRequest, new MockHttpServletResponse());

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		Assertion assertion = OpenSamlUtil.buildAssertion();

		OpenSamlUtil.signObject(assertion, getCredential(IDP_ENTITY_ID));

		SPSSODescriptor spSsoDescriptor =
			MetadataGeneratorUtil.buildSpSsoDescriptor(
				mockHttpServletRequest, "testid", false, false, false,
				getCredential(IDP_ENTITY_ID));

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		Response samlResponse = _webSsoProfileImpl.getSuccessResponse(
			samlSsoRequestContext, assertionConsumerServices.get(0), assertion);

		samlMessageContext.setOutboundSAMLMessage(samlResponse);

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		_webSsoProfileImpl.verifyAssertionSignature(
			assertion.getSignature(), samlMessageContext,
			MetadataManagerUtil.getSignatureTrustEngine());
	}

	@Test(expected=PortalException.class)
	public void testVerifyAssertionSignatureWrongSignature() throws Exception {
		prepareServiceProvider(SP_ENTITY_ID);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_webSsoProfileImpl.doSendAuthnRequest(
			getMockHttpServletRequest("GET", LOGIN_URL),
			mockHttpServletResponse, RELAY_STATE);

		prepareIdentityProvider(IDP_ENTITY_ID);

		HttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(
				"GET", mockHttpServletResponse.getRedirectedUrl());

		SamlSsoRequestContext samlSsoRequestContext =
			_webSsoProfileImpl.decodeAuthnRequest(
				mockHttpServletRequest, new MockHttpServletResponse());

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		Assertion assertion = OpenSamlUtil.buildAssertion();

		OpenSamlUtil.signObject(assertion, getCredential(IDP_ENTITY_ID));

		SPSSODescriptor spSsoDescriptor =
			MetadataGeneratorUtil.buildSpSsoDescriptor(
				mockHttpServletRequest, "testid", false, false, false,
				getCredential(IDP_ENTITY_ID));

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		Response samlResponse = _webSsoProfileImpl.getSuccessResponse(
			samlSsoRequestContext, assertionConsumerServices.get(0), assertion);

		samlMessageContext.setOutboundSAMLMessage(samlResponse);

		samlMessageContext.setPeerEntityId(IDP_ENTITY_ID);

		Signature signature = assertion.getSignature();

		signature.setSignatureAlgorithm("Fail");

		_webSsoProfileImpl.verifyAssertionSignature(
			signature, samlMessageContext,
			MetadataManagerUtil.getSignatureTrustEngine());
	}

	@Test
	public void testVerifyAudienceRestrictionsAllow() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		List<AudienceRestriction> audienceRestrictions =
			new ArrayList<AudienceRestriction>();

		AudienceRestriction audienceRestriction =
			_webSsoProfileImpl.getSuccessAudienceRestriction(
				samlMessageContext.getLocalEntityId());

		audienceRestrictions.add(audienceRestriction);

		_webSsoProfileImpl.verifyAudienceRestrictions(
			audienceRestrictions, samlMessageContext);
	}

	@Test(expected=PortalException.class)
	public void testVerifyAudienceRestrictionsDeny() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		List<AudienceRestriction> audienceRestrictions =
			new ArrayList<AudienceRestriction>();

		AudienceRestriction audienceRestriction =
			_webSsoProfileImpl.getSuccessAudienceRestriction("Fail");

		audienceRestrictions.add(audienceRestriction);

		_webSsoProfileImpl.verifyAudienceRestrictions(
			audienceRestrictions, samlMessageContext);
	}

	@Test
	public void testVerifyDestinationAllow() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setCommunicationProfileId("location");
		samlMessageContext.setLocalEntityId("testidp");

		AssertionConsumerService assertionConsumerService=
			OpenSamlUtil.buildAssertionConsumerService(
				"location", 2, false, "target");

		EntityDescriptor entityDescriptor =
			samlMessageContext.getLocalEntityMetadata();

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)entityDescriptor.getSPSSODescriptor(
				SAMLConstants.SAML20P_NS);

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		assertionConsumerServices.add(assertionConsumerService);

		_webSsoProfileImpl.verifyDestination(samlMessageContext, "target");

	}

	@Test(expected=PortalException.class)
	public void testVerifyDestinationDeny() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setCommunicationProfileId("location");
		samlMessageContext.setLocalEntityId("testidp");

		AssertionConsumerService assertionConsumerService=
			OpenSamlUtil.buildAssertionConsumerService(
				"location", 2, false, "target");

		EntityDescriptor entityDescriptor =
			samlMessageContext.getLocalEntityMetadata();

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)entityDescriptor.getSPSSODescriptor(
				SAMLConstants.SAML20P_NS);

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		assertionConsumerServices.add(assertionConsumerService);

		_webSsoProfileImpl.verifyDestination(samlMessageContext, "fail");
	}

	@Test(expected=PortalException.class)
	public void testVerifyInResponseToNoAuthRequest() throws Exception {
		Response response = OpenSamlUtil.buildResponse();

		response.setInResponseTo("responseto");
		response.setIssuer(OpenSamlUtil.buildIssuer("value"));

		_webSsoProfileImpl.verifyInResponseTo(response);
	}

	@Test
	public void testVerifyInResponseToNullResponse() throws Exception {
		Response response = OpenSamlUtil.buildResponse();

		response.setInResponseTo(null);
		response.setIssuer(OpenSamlUtil.buildIssuer("value"));

		_webSsoProfileImpl.verifyInResponseTo(response);
	}

	@Test
	public void testVerifyInResponseToValid() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		EntityDescriptor entityDescriptor =
			samlMessageContext.getLocalEntityMetadata();

		SPSSODescriptor spSsoDescriptor =
			(SPSSODescriptor)entityDescriptor.getSPSSODescriptor(
				SAMLConstants.SAML20P_NS);

		AssertionConsumerService assertionConsumerService =
			SamlUtil.getAssertionConsumerServiceForBinding(
				spSsoDescriptor, SAMLConstants.SAML2_POST_BINDING_URI);

		IDPSSODescriptor idpSsoDescriptor =
			MetadataGeneratorUtil.buildIdpSsoDescriptor(
				mockHttpServletRequest, samlMessageContext.getPeerEntityId(),
				false, false, getCredential(IDP_ENTITY_ID));

		SingleSignOnService singleSignOnService =
			SamlUtil.getSingleSignOnServiceForBinding(
				idpSsoDescriptor, SAMLConstants.SAML2_REDIRECT_BINDING_URI);

		NameIDPolicy nameIdPolicy = OpenSamlUtil.buildNameIdPolicy();

		nameIdPolicy.setAllowCreate(true);
		nameIdPolicy.setFormat(MetadataManagerUtil.getNameIdFormat());

		AuthnRequest authnRequest = OpenSamlUtil.buildAuthnRequest(
			spSsoDescriptor, assertionConsumerService, singleSignOnService,
			nameIdPolicy);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			mockHttpServletRequest);

		SamlSpAuthRequestLocalServiceUtil.addSamlSpAuthRequest(
			samlMessageContext.getPeerEntityId(), authnRequest.getID(),
			serviceContext);

		Response response = OpenSamlUtil.buildResponse();

		response.setInResponseTo(authnRequest.getID());
		response.setIssuer(
			OpenSamlUtil.buildIssuer(samlMessageContext.getPeerEntityId()));

		_webSsoProfileImpl.verifyInResponseTo(response);
	}

	@Test
	public void testVerifyIssuerAllow() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setPeerEntityId("value");

		_webSsoProfileImpl.verifyIssuer(
			samlMessageContext, OpenSamlUtil.buildIssuer("value"));
	}

	@Test(expected=PortalException.class)
	public void testVerifyIssuerInvalidFormat() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setPeerEntityId("value");

		Issuer issuer = OpenSamlUtil.buildIssuer("value");

		issuer.setFormat("Invalid Format");

		_webSsoProfileImpl.verifyIssuer(samlMessageContext, issuer);
	}

	@Test(expected=PortalException.class)
	public void testVerifyIssuerInvalidIssuer() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest("GET", ACS_URL);

		prepareServiceProvider(SP_ENTITY_ID);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_webSsoProfileImpl.getSamlMessageContext(
				mockHttpServletRequest, new MockHttpServletResponse());

		samlMessageContext.setPeerEntityId("value");

		_webSsoProfileImpl.verifyIssuer(
			samlMessageContext, OpenSamlUtil.buildIssuer("Invalid Issuer"));
	}

	@Test
	public void testVerifySubjectAllow() throws Exception {
		prepareServiceProvider(SP_ENTITY_ID);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_webSsoProfileImpl.doSendAuthnRequest(
			getMockHttpServletRequest("GET", LOGIN_URL),
			mockHttpServletResponse, RELAY_STATE);

		prepareIdentityProvider(IDP_ENTITY_ID);

		HttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(
				"GET", mockHttpServletResponse.getRedirectedUrl());

		SamlSsoRequestContext samlSsoRequestContext =
			_webSsoProfileImpl.decodeAuthnRequest(
				mockHttpServletRequest, new MockHttpServletResponse());

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		samlMessageContext.setCommunicationProfileId("location");
		samlMessageContext.setLocalEntityId("testidp");

		AssertionConsumerService assertionConsumerService =
			OpenSamlUtil.buildAssertionConsumerService(
				"location", 2, false, "target");

		SPSSODescriptor spSsoDescriptor =
			MetadataGeneratorUtil.buildSpSsoDescriptor(
				mockHttpServletRequest, samlMessageContext.getLocalEntityId(),
				false, false, false,
				MetadataManagerUtil.getSigningCredential());

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		assertionConsumerServices.add(assertionConsumerService);

		NameID nameID = OpenSamlUtil.buildNameId("format", "value");

		SubjectConfirmationData subjectConfirmationData =
			_webSsoProfileImpl.getSuccessSubjectConfirmationData(
				samlSsoRequestContext, assertionConsumerService,
				new DateTime());

		Subject subject = _webSsoProfileImpl.getSuccessSubject(
			samlSsoRequestContext, assertionConsumerService, nameID,
			subjectConfirmationData);

		_webSsoProfileImpl.verifySubject(samlMessageContext, subject);
	}

	@Test(expected=PortalException.class)
	public void testVerifySubjectExpired() throws Exception {
		prepareServiceProvider(SP_ENTITY_ID);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_webSsoProfileImpl.doSendAuthnRequest(
			getMockHttpServletRequest("GET", LOGIN_URL),
			mockHttpServletResponse, RELAY_STATE);

		prepareIdentityProvider(IDP_ENTITY_ID);

		HttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(
				"GET", mockHttpServletResponse.getRedirectedUrl());

		SamlSsoRequestContext samlSsoRequestContext =
			_webSsoProfileImpl.decodeAuthnRequest(
				mockHttpServletRequest, new MockHttpServletResponse());

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		samlMessageContext.setCommunicationProfileId("location");
		samlMessageContext.setLocalEntityId("testidp");

		AssertionConsumerService assertionConsumerService=
			OpenSamlUtil.buildAssertionConsumerService(
				"location", 2, false, "target");

		SPSSODescriptor spSsoDescriptor =
			MetadataGeneratorUtil.buildSpSsoDescriptor(
				mockHttpServletRequest, samlMessageContext.getLocalEntityId(),
				false, false, false,
				MetadataManagerUtil.getSigningCredential());

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		NameID nameID = OpenSamlUtil.buildNameId("format", "value");

		SubjectConfirmationData subjectConfirmationData =
			_webSsoProfileImpl.getSuccessSubjectConfirmationData(
				samlSsoRequestContext, assertionConsumerService,
				new DateTime(1993, 1, 23, 1, 45));

		Subject subject = _webSsoProfileImpl.getSuccessSubject(
			samlSsoRequestContext, assertionConsumerService, nameID,
			subjectConfirmationData);

		_webSsoProfileImpl.verifySubject(samlMessageContext, subject);
	}

	@Test(expected=PortalException.class)
	public void testVerifySubjectNoBearerSubjectConfirmation()
		throws Exception {
		prepareServiceProvider(SP_ENTITY_ID);

		MockHttpServletResponse mockHttpServletResponse =
			new MockHttpServletResponse();

		_webSsoProfileImpl.doSendAuthnRequest(
			getMockHttpServletRequest("GET", LOGIN_URL),
			mockHttpServletResponse, RELAY_STATE);

		prepareIdentityProvider(IDP_ENTITY_ID);

		HttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(
				"GET", mockHttpServletResponse.getRedirectedUrl());

		SamlSsoRequestContext samlSsoRequestContext =
			_webSsoProfileImpl.decodeAuthnRequest(
				mockHttpServletRequest, new MockHttpServletResponse());

		SAMLMessageContext<AuthnRequest, Response, NameID> samlMessageContext =
			samlSsoRequestContext.getSAMLMessageContext();

		samlMessageContext.setCommunicationProfileId("location");
		samlMessageContext.setLocalEntityId("testidp");

		AssertionConsumerService assertionConsumerService=
			OpenSamlUtil.buildAssertionConsumerService(
				"fail", 2, false, "target");

		SPSSODescriptor spSsoDescriptor =
			MetadataGeneratorUtil.buildSpSsoDescriptor(
				mockHttpServletRequest, samlMessageContext.getLocalEntityId(),
				false, false, false,
				MetadataManagerUtil.getSigningCredential());

		samlMessageContext.setLocalEntityRoleMetadata(spSsoDescriptor);

		List<AssertionConsumerService> assertionConsumerServices =
			spSsoDescriptor.getAssertionConsumerServices();

		assertionConsumerServices.add(assertionConsumerService);

		NameID nameID = OpenSamlUtil.buildNameId("format", "value");

		SubjectConfirmationData subjectConfirmationData =
			_webSsoProfileImpl.getSuccessSubjectConfirmationData(
				samlSsoRequestContext, assertionConsumerService,
				new DateTime());

		Subject subject = _webSsoProfileImpl.getSuccessSubject(
			samlSsoRequestContext, assertionConsumerService, nameID,
			subjectConfirmationData);

		_webSsoProfileImpl.verifySubject(samlMessageContext, subject);
	}

	private SamlSpAuthRequestLocalService _samlSpAuthRequestLocalService;
	private WebSsoProfileImpl _webSsoProfileImpl = new WebSsoProfileImpl();

}
