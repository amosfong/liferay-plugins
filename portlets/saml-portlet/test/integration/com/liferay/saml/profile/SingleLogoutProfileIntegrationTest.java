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
 * @author Matt Tambara and Will Newbury
 */

@RunWith(PowerMockRunner.class)
public class SingleLogoutProfileIntegrationTest extends BaseSamlTestCase {

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();

        _samlIdpSpSessionLocalService = getMockPortletService(
            SamlIdpSpSessionLocalServiceUtil.class,
            SamlIdpSpSessionLocalService.class);

        _samlIdpSpConnectionLocalService = getMockPortletService(
            SamlIdpSpConnectionLocalServiceUtil.class,
            SamlIdpSpConnectionLocalService.class);

        _jsonFactory = mock(JSONFactory.class);

        _jsonObject = mock(JSONObject.class);

        JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();
        jsonFactoryUtil.setJSONFactory(_jsonFactory);


        _samlSpSessionLocalService = mock(SamlSpSessionLocalService.class);
        when(
            portletBeanLocator.locate(
                Mockito.eq(SamlSpSessionLocalService.class.getName()))
        ).thenReturn(
            _samlSpSessionLocalService
        );

        SamlSpSession samlSpSession = new SamlSpSessionImpl();
        when(
            SamlSpSessionLocalServiceUtil.fetchSamlSpSessionByJSessionId(
                Mockito.anyString())
        ).thenReturn(
            samlSpSession
        );

        _singleLogoutProfileImpl.setIdentifierGenerator(identifierGenerator);
        _singleLogoutProfileImpl.setSamlBindings(samlBindings);

        prepareServiceProvider(SP_ENTITY_ID);
    }

    @Test
    public void testPerformIdpSpLogoutHasSloRequestInfo() throws Exception {
        when(
            JSONFactoryUtil.createJSONObject()
        ).thenReturn(
            _jsonObject
        );

        MockHttpServletRequest request = getMockHttpServletRequest(
            "GET", ACS_URL);
        request.setParameter("entityId", SP_ENTITY_ID);

        List<SamlIdpSpSession> samlIdpSpSessions =
            new ArrayList<SamlIdpSpSession>();

        SamlIdpSpSessionImpl samlIdpSpSessionImpl = new SamlIdpSpSessionImpl();
        samlIdpSpSessionImpl.setSamlSpEntityId(SP_ENTITY_ID);
        samlIdpSpSessionImpl.setCompanyId(COMPANY_ID);

        samlIdpSpSessions.add(samlIdpSpSessionImpl);

        SamlIdpSpConnectionImpl samlIdpSpConnectionImpl =
            new SamlIdpSpConnectionImpl();

        SamlIdpSpConnection samlIdpSpConnection = (
            SamlIdpSpConnection)samlIdpSpConnectionImpl;

        SamlIdpSsoSessionImpl samlIdpSsoSessionImpl =
            new SamlIdpSsoSessionImpl();
        samlIdpSsoSessionImpl.setSamlIdpSsoSessionId(SESSION_ID);

        when(
            SamlIdpSpSessionLocalServiceUtil.getSamlIdpSpSessions(
                SESSION_ID)
            ).thenReturn(
                samlIdpSpSessions
            );

        when(
            SamlIdpSpConnectionLocalServiceUtil.getSamlIdpSpConnection(
                COMPANY_ID, SP_ENTITY_ID)
            ).thenReturn(
                samlIdpSpConnection
            );

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
            request.getAttribute("tilesTitle"), "single-sign-on");
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
            request.getAttribute("tilesTitle"), "single-sign-on");
    }

    @Test
    public void testSendIdpLogoutRequestSoapRedirect() throws Exception {
        prepareIdentityProvider(IDP_ENTITY_ID);

        MockHttpServletRequest request = getMockHttpServletRequest(
            "GET", ACS_URL);

        MockHttpServletResponse response = new MockHttpServletResponse();

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
        HttpSession session = request.getSession(true);

        _singleLogoutProfileImpl.sendSpLogoutRequest(
            request, new MockHttpServletResponse());
    }

    private JSONFactory _jsonFactory;
    private JSONObject _jsonObject;
    private SamlIdpSpConnectionLocalService _samlIdpSpConnectionLocalService;
    private SamlIdpSpSessionLocalService _samlIdpSpSessionLocalService;
    private SamlSpSessionLocalService _samlSpSessionLocalService;
    private SingleLogoutProfileImpl _singleLogoutProfileImpl =
        new SingleLogoutProfileImpl();

}
