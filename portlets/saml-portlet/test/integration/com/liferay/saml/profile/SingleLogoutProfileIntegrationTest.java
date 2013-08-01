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

import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.saml.BaseSamlTestCase;
import com.liferay.saml.SamlSloContext;
import com.liferay.saml.SamlSloRequestInfo;
import com.liferay.saml.binding.SamlBinding;
import com.liferay.saml.model.SamlIdpSpConnection;
import com.liferay.saml.model.SamlIdpSpSession;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.model.impl.SamlIdpSpConnectionImpl;
import com.liferay.saml.model.impl.SamlIdpSpSessionImpl;
import com.liferay.saml.model.impl.SamlIdpSsoSessionImpl;
import com.liferay.saml.model.impl.SamlSpSessionImpl;
import com.liferay.saml.service.SamlIdpSpConnectionLocalService;
import com.liferay.saml.service.SamlIdpSpConnectionLocalServiceUtil;
import com.liferay.saml.service.SamlIdpSpSessionLocalService;
import com.liferay.saml.service.SamlIdpSpSessionLocalServiceUtil;
import com.liferay.saml.service.SamlSpSessionLocalService;
import com.liferay.saml.service.SamlSpSessionLocalServiceUtil;
import com.liferay.saml.util.JspUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.core.LogoutRequest;
import org.opensaml.saml2.core.NameID;

import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Matthew Tambara
 * @author William Newbury
 */
@RunWith(PowerMockRunner.class)
public class SingleLogoutProfileIntegrationTest extends BaseSamlTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		SamlIdpSpSessionLocalService samlIdpSpSessionLocalService =
			getMockPortletService(
				SamlIdpSpSessionLocalServiceUtil.class,
				SamlIdpSpSessionLocalService.class);

		SamlIdpSpConnectionLocalService samlIdpSpConnectionLocalService =
			getMockPortletService(
				SamlIdpSpConnectionLocalServiceUtil.class,
				SamlIdpSpConnectionLocalService.class);

		_samlSpSessionLocalService = getMockPortletService(
			SamlSpSessionLocalServiceUtil.class,
			SamlSpSessionLocalService.class);

		_singleLogoutProfileImpl = new SingleLogoutProfileImpl();
		_singleLogoutProfileImpl.setIdentifierGenerator(identifierGenerator);
		_singleLogoutProfileImpl.setSamlBindings(samlBindings);

		prepareServiceProvider(SP_ENTITY_ID);
	}

	@Test
	public void testPerformIdpSpLogoutHasSloRequestInfo() throws Exception {
		JSONObject jsonObject = mock(JSONObject.class);
		JSONFactory jsonFactory = mock(JSONFactory.class);

		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();
		jsonFactoryUtil.setJSONFactory(jsonFactory);

		when(
			jsonFactory.createJSONObject()
		).thenReturn(
			jsonObject
		);

		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", SLO_LOGOUT_URL.concat("?cmd=logout"));

		request.setParameter("entityId", SP_ENTITY_ID);

		SamlIdpSpSessionImpl samlIdpSpSessionImpl = new SamlIdpSpSessionImpl();
		samlIdpSpSessionImpl.setSamlSpEntityId(SP_ENTITY_ID);
		samlIdpSpSessionImpl.setCompanyId(COMPANY_ID);

		List<SamlIdpSpSession> samlIdpSpSessions =
			new ArrayList<SamlIdpSpSession>();

		samlIdpSpSessions.add(samlIdpSpSessionImpl);

		when(
			SamlIdpSpSessionLocalServiceUtil.getSamlIdpSpSessions(SESSION_ID)
		).thenReturn(
			samlIdpSpSessions
		);

		SamlIdpSpConnection samlIdpSpConnection = new SamlIdpSpConnectionImpl();

		when(
			SamlIdpSpConnectionLocalServiceUtil.getSamlIdpSpConnection(
				COMPANY_ID, SP_ENTITY_ID)
		).thenReturn(
			samlIdpSpConnection
		);

		SamlIdpSsoSessionImpl samlIdpSsoSessionImpl =
			new SamlIdpSsoSessionImpl();

		samlIdpSsoSessionImpl.setSamlIdpSsoSessionId(SESSION_ID);

		SamlSloContext samlSloContext = new SamlSloContext(
			samlIdpSsoSessionImpl);

		SamlSloRequestInfo samlSloRequestInfo =
			samlSloContext.getSamlSloRequestInfo(SP_ENTITY_ID);
		samlSloRequestInfo.setStatus(SamlSloRequestInfo.REQUEST_STATUS_SUCCESS);

		_singleLogoutProfileImpl.performIdpSpLogout(
			request, new MockHttpServletResponse(), samlSloContext);

		Assert.assertEquals(
			JspUtil.PATH_PORTAL_SAML_SLO_SP_STATUS,
			request.getAttribute("tilesContent"));
		Assert.assertTrue(
			Boolean.valueOf((String)request.getAttribute("tilesPopUp")));
	}

	@Test
	public void testPerformIdpSpLogoutNoSloRequestInfo() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", SLO_LOGOUT_URL.concat("?cmd=logout"));

		SamlSloContext samlSloContext = new SamlSloContext(null);

		_singleLogoutProfileImpl.performIdpSpLogout(
			request, new MockHttpServletResponse(), samlSloContext);

		Assert.assertEquals(
			JspUtil.PATH_PORTAL_SAML_ERROR,
			request.getAttribute("tilesContent"));
		Assert.assertTrue(
			Boolean.valueOf((String)request.getAttribute("tilesPopUp")));
	}

	@Test
	public void testSendIdpLogoutRequestHttpRedirect() throws Exception {
		prepareIdentityProvider(IDP_ENTITY_ID);

		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", SLO_LOGOUT_URL.concat("?cmd=logout"));

		SamlIdpSsoSessionImpl samlIdpSsoSessionImpl =
			new SamlIdpSsoSessionImpl();

		SamlSloContext samlSloContext = new SamlSloContext(
			samlIdpSsoSessionImpl);

		SamlIdpSpSessionImpl samlIdpSpSessionImpl = new SamlIdpSpSessionImpl();

		samlIdpSpSessionImpl.setSamlSpEntityId(SP_ENTITY_ID);
		samlIdpSpSessionImpl.setNameIdFormat(NameID.EMAIL);
		samlIdpSpSessionImpl.setNameIdValue("test@liferay.com");

		SamlSloRequestInfo samlSloRequestInfo = new SamlSloRequestInfo();

		samlSloRequestInfo.setSamlIdpSpSession(samlIdpSpSessionImpl);

		MockHttpServletResponse response = new MockHttpServletResponse();

		_singleLogoutProfileImpl.sendIdpLogoutRequest(
			request, response, samlSloContext, samlSloRequestInfo);

		String redirect = response.getRedirectedUrl();

		Assert.assertNotNull(redirect);

		request = getMockHttpServletRequest("GET", redirect);

		SamlBinding samlBinding = _singleLogoutProfileImpl.getSamlBinding(
			SAMLConstants.SAML2_REDIRECT_BINDING_URI);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_singleLogoutProfileImpl.decodeSamlMessage(
				request, response, samlBinding, true);

		LogoutRequest logoutRequest =
			(LogoutRequest)samlMessageContext.getInboundSAMLMessage();

		NameID nameId = logoutRequest.getNameID();

		Assert.assertEquals(NameID.EMAIL, nameId.getFormat());
		Assert.assertEquals("test@liferay.com", nameId.getValue());
	}

	@Test
	public void testSendSpLogoutRequestNoSpSession() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", LOGOUT_URL);

		MockHttpServletResponse response = new MockHttpServletResponse();

		_singleLogoutProfileImpl.sendSpLogoutRequest(request, response);

		String redirect = response.getRedirectedUrl();

		Assert.assertNotNull(redirect);
		Assert.assertEquals(LOGOUT_URL, redirect);
	}

	@Test
	public void testSendSpLogoutRequestValidSpSession() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", LOGOUT_URL);

		SamlSpSession samlSpSession = new SamlSpSessionImpl();

		samlSpSession.setNameIdFormat(NameID.EMAIL);
		samlSpSession.setNameIdValue("test@liferay.com");

		when(
			_samlSpSessionLocalService.fetchSamlSpSessionByJSessionId(
				Mockito.anyString())
		).thenReturn(
			samlSpSession
		);

		MockHttpServletResponse response = new MockHttpServletResponse();

		_singleLogoutProfileImpl.sendSpLogoutRequest(request, response);

		String redirect = response.getRedirectedUrl();

		Assert.assertNotNull(redirect);

		request = getMockHttpServletRequest("GET", redirect);

		SamlBinding samlBinding = _singleLogoutProfileImpl.getSamlBinding(
			SAMLConstants.SAML2_REDIRECT_BINDING_URI);

		SAMLMessageContext<?, ?, ?> samlMessageContext =
			_singleLogoutProfileImpl.decodeSamlMessage(
				request, response, samlBinding, true);

		LogoutRequest logoutRequest =
			(LogoutRequest)samlMessageContext.getInboundSAMLMessage();

		NameID nameId = logoutRequest.getNameID();

		Assert.assertEquals(NameID.EMAIL, nameId.getFormat());
		Assert.assertEquals("test@liferay.com", nameId.getValue());
	}

	private SamlSpSessionLocalService _samlSpSessionLocalService;
	private SingleLogoutProfileImpl _singleLogoutProfileImpl;

}