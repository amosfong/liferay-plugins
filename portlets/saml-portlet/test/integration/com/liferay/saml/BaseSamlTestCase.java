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

import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactory;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.credential.FileSystemKeyStoreManagerImpl;
import com.liferay.saml.credential.KeyStoreCredentialResolver;
import com.liferay.saml.metadata.MetadataGeneratorUtil;
import com.liferay.saml.metadata.MetadataManagerImpl;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.provider.CachingChainingMetadataProvider;
import com.liferay.saml.provider.DBMetadataProvider;
import com.liferay.saml.util.OpenSamlBootstrap;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.util.portlet.PortletProps;

import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;

import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.provider.MetadataProvider;
import org.opensaml.saml2.metadata.provider.MetadataProviderException;
import org.opensaml.xml.parse.BasicParserPool;
import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.credential.CredentialResolver;
import org.opensaml.xml.security.criteria.EntityIDCriteria;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Mika Koivisto
 */
@RunWith(PowerMockRunner.class)
public class BaseSamlTestCase extends PowerMockito {

	public static final String IDP_ENTITY_ID = "testidp";
	public static final String SP_ENTITY_ID = "testsp";

	@Before
	public void setUp() throws Exception {
		OpenSamlBootstrap.bootstrap();

		portal = mock(Portal.class);

		new PortalUtil().setPortal(portal);

		when(
			portal.getPortalURL(
				Mockito.any(MockHttpServletRequest.class), Mockito.eq(false))
		).thenReturn(
			"http://localhost:8080"
		);
		when(
			portal.getPortalURL(
				Mockito.any(MockHttpServletRequest.class), Mockito.eq(true))
		).thenReturn(
			"https://localhost:8443"
		);

		props = mock(Props.class);

		PropsUtil.setProps(props);

		when(
			props.get(PropsKeys.LIFERAY_HOME)
		).thenReturn(
			System.getProperty("java.io.tmpdir")
		);
		when(
			props.get(PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD)
		).thenReturn(
			"liferay"
		);
		when(
			props.get(PortletPropsKeys.SAML_KEYSTORE_PASSWORD)
		).thenReturn(
			"liferay"
		);
		when(
			props.get(PortletPropsKeys.SAML_KEYSTORE_PATH)
		).thenReturn(
			"classpath:/com/liferay/saml/credential/dependencies/keystore.jks"
		);
		when(
			props.get(PortletPropsKeys.SAML_KEYSTORE_TYPE)
		).thenReturn(
			"jks"
		);
		when(
			props.get(
				PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD.concat(
					"[" + IDP_ENTITY_ID + "]"))
		).thenReturn(
			"liferay"
		);
		when(
			props.get(
				PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD.concat(
					"[" + SP_ENTITY_ID + "]"))
		).thenReturn(
			"liferay"
		);
		when(
			props.getArray(PortletPropsKeys.SAML_METADATA_PATHS)
		).thenReturn(
			new String[0]
		);

		Thread currentThread = Thread.currentThread();

		PortletClassLoaderUtil.setClassLoader(
			currentThread.getContextClassLoader());

		ConfigurationFactory configurationFactory = mock(
			ConfigurationFactory.class);

		ConfigurationFactoryUtil.setConfigurationFactory(configurationFactory);

		Configuration configuration = mock(Configuration.class);

		when(
			configurationFactory.getConfiguration(
				Mockito.any(ClassLoader.class), Mockito.eq("portlet"))
		).thenReturn(
			configuration
		);
		when(
			configuration.get(PortletPropsKeys.SAML_KEYSTORE_MANAGER_IMPL)
		).thenReturn(
			FileSystemKeyStoreManagerImpl.class.getName()
		);

		// Must have this line otherwise will cause NoClassDefFoundError

		PortletProps.get(PortletPropsKeys.SAML_KEYSTORE_MANAGER_IMPL);

		credentialResolver = new KeyStoreCredentialResolver();

		MetadataManagerImpl metadataManagerImpl = new MetadataManagerImpl();

		metadataManagerImpl.setCredentialResolver(credentialResolver);
		metadataManagerImpl.setParserPool(new BasicParserPool());

		new MetadataManagerUtil().setMetadataManager(metadataManagerImpl);

		CachingChainingMetadataProvider metadataProvider =
			(CachingChainingMetadataProvider)
				metadataManagerImpl.getMetadataProvider();

		for (MetadataProvider provider : metadataProvider.getProviders()) {
			metadataProvider.removeMetadataProvider(provider);
		}

		metadataProvider.addMetadataProvider(new TestMetadataProvider());

		portalBeanLocator = mock(BeanLocator.class);

		PortalBeanLocatorUtil.setBeanLocator(portalBeanLocator);

		portletBeanLocator = mock(BeanLocator.class);

		PortletBeanLocatorUtil.setBeanLocator(
			"saml-portlet", portletBeanLocator);
	}

	protected MockHttpServletRequest getMockHttpServletRequest(
		String method, String url) {

		String protocol = url.substring(0, url.indexOf(":"));
		String queryString = StringPool.BLANK;
		String requestURI = StringPool.BLANK;
		String serverName = StringPool.BLANK;
		int serverPort = 80;

		if (url.indexOf(StringPool.COLON, protocol.length() + 3) > 0) {
			serverName = url.substring(
				protocol.length() + 3,
				url.indexOf(StringPool.COLON, protocol.length() + 3));
			serverPort = GetterUtil.getInteger(
				url.substring(
					url.indexOf(StringPool.COLON, protocol.length() + 3),
					url.indexOf(StringPool.SLASH, protocol.length() + 3)));
		}
		else {
			serverName = url.substring(
				protocol.length() + 3,
				url.indexOf(StringPool.SLASH, protocol.length() + 3));
		}

		if (url.indexOf(StringPool.QUESTION) > 0) {
			queryString = url.substring(
				url.indexOf(StringPool.QUESTION) + 1, url.length());
			requestURI = url.substring(
				url.indexOf(StringPool.SLASH, protocol.length() + 3),
				url.indexOf(StringPool.QUESTION));
		}
		else {
			requestURI = url.substring(
				url.indexOf(StringPool.SLASH, protocol.length() + 3));
		}

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest("GET", requestURI);

		mockHttpServletRequest.setQueryString(queryString);
		mockHttpServletRequest.setContextPath(StringPool.SLASH);
		mockHttpServletRequest.setServerPort(serverPort);
		mockHttpServletRequest.setServerName(serverName);

		if (Validator.isNull(queryString)) {
			return mockHttpServletRequest;
		}

		String[] parameters = StringUtil.split(
			queryString, StringPool.AMPERSAND);

		for (String parameter : parameters) {
			String[] kvp = StringUtil.split(parameter, StringPool.EQUAL);

			try {
				String value = URLDecoder.decode(kvp[1], StringPool.UTF8);

				mockHttpServletRequest.setParameter(kvp[0], value);
			}
			catch (UnsupportedEncodingException usee) {
			}
		}

		return mockHttpServletRequest;
	}

	protected CredentialResolver credentialResolver;
	protected Portal portal;
	protected BeanLocator portalBeanLocator;
	protected BeanLocator portletBeanLocator;
	protected Props props;

	private class TestMetadataProvider extends DBMetadataProvider {

		public EntityDescriptor getEntityDescriptor(String entityId)
			throws MetadataProviderException {

			try {
				return doGetEntityDecriptor(entityId);
			}
			catch (Exception e) {
				throw new MetadataProviderException(e);
			}
		}

		protected EntityDescriptor doGetEntityDecriptor(String entityId)
			throws Exception {

			EntityIDCriteria entityIdCriteria = new EntityIDCriteria(entityId);

			CriteriaSet criteriaSet = new CriteriaSet();

			criteriaSet.add(entityIdCriteria);

			Credential signingCredential = credentialResolver.resolveSingle(
				criteriaSet);

			MockHttpServletRequest mockHttpServletRequest =
				getMockHttpServletRequest(
					"GET", "http://localhost:8080/c/portal/saml/metadata");

			if (entityId.equals(IDP_ENTITY_ID)) {
				return MetadataGeneratorUtil.buildIdpEntityDescriptor(
					mockHttpServletRequest, entityId, true, true, false,
					signingCredential);
			}
			else if (entityId.equals(SP_ENTITY_ID)) {
				return MetadataGeneratorUtil.buildSpEntityDescriptor(
					mockHttpServletRequest, entityId, true, true, false, false,
					signingCredential);
			}

			return null;
		}
	}

}