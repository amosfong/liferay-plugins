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

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * @author Matthew Tambara W
 * @author William Newbury
 */
@RunWith(PowerMockRunner.class)
public class SingleLogoutProfileIntegrationTest extends BaseSamlTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		SamlIdpSpSessionLocalService _samlIdpSpSessionLocalService =
			getMockPortletService(
				SamlIdpSpSessionLocalServiceUtil.class,
				SamlIdpSpSessionLocalService.class);

		SamlIdpSpConnectionLocalService _samlIdpSpConnectionLocalService =
			getMockPortletService(
				SamlIdpSpConnectionLocalServiceUtil.class,
				SamlIdpSpConnectionLocalService.class);

		SamlSpSessionLocalService _samlSpSessionLocalService =
			getMockPortletService(
				SamlSpSessionLocalServiceUtil.class,
				SamlSpSessionLocalService.class);

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
			JSONFactoryUtil.createJSONObject()
		).thenReturn(
			jsonObject
		);

		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", ACS_URL);
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
			"/portal/saml/slo_sp_status.jsp", request.getAttribute(
				"tilesContent"));
		Assert.assertTrue(
			Boolean.valueOf((String)request.getAttribute("tilesPopUp")));
		Assert.assertEquals(
			"single-sign-on", request.getAttribute("tilesTitle"));
	}

	@Test
	public void testPerformIdpSpLogoutNoSloRequestInfo() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", ACS_URL);

		SamlIdpSsoSessionImpl samlIdpSsoSessionImpl =
			new SamlIdpSsoSessionImpl();

		SamlSloContext samlSloContext = new SamlSloContext(
			samlIdpSsoSessionImpl);

		_singleLogoutProfileImpl.performIdpSpLogout(
			request, new MockHttpServletResponse(), samlSloContext);

		Assert.assertEquals(
			"/portal/saml/error.jsp", request.getAttribute("tilesContent"));
		Assert.assertTrue(
			Boolean.valueOf((String)request.getAttribute("tilesPopUp")));
		Assert.assertEquals(
			"single-sign-on", request.getAttribute("tilesTitle"));
	}

	@Test
	public void testSendIdpLogoutRequestSoapRedirect() throws Exception {
		prepareIdentityProvider(IDP_ENTITY_ID);

		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", ACS_URL);
		SamlIdpSsoSessionImpl samlIdpSsoSessionImpl =
			new SamlIdpSsoSessionImpl();
		SamlSloContext samlSloContext = new SamlSloContext(
			samlIdpSsoSessionImpl);

		SamlSloRequestInfo samlSloRequestInfo = new SamlSloRequestInfo();
		SamlIdpSpSessionImpl samlIdpSpSessionImpl = new SamlIdpSpSessionImpl();
		samlIdpSpSessionImpl.setSamlSpEntityId(SP_ENTITY_ID);
		samlSloRequestInfo.setSamlIdpSpSession(samlIdpSpSessionImpl);

		_singleLogoutProfileImpl.sendIdpLogoutRequest(
			request, new MockHttpServletResponse(), samlSloContext,
			samlSloRequestInfo);
	}

	@Test
	public void testSendSpLogoutRequestNoSpSession() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", LOGIN_URL);

		_singleLogoutProfileImpl.sendSpLogoutRequest(
			request, new MockHttpServletResponse());
	}

	@Test
	public void testSendSpLogoutRequestValidSpSession() throws Exception {
		MockHttpServletRequest request = getMockHttpServletRequest(
			"GET", LOGIN_URL);

		SamlSpSession samlSpSession = new SamlSpSessionImpl();
		when(
			SamlSpSessionLocalServiceUtil.fetchSamlSpSessionByJSessionId(
				Mockito.anyString())
		).thenReturn(
			samlSpSession
		);

		HttpSession session = request.getSession(true);

		_singleLogoutProfileImpl.sendSpLogoutRequest(
			request, new MockHttpServletResponse());
	}

	private SingleLogoutProfileImpl _singleLogoutProfileImpl =
		new SingleLogoutProfileImpl();

}