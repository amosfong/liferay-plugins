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
import com.liferay.saml.binding.SamlBinding;
import com.liferay.saml.binding.impl.HttpPostBinding;
import com.liferay.saml.binding.impl.HttpRedirectBinding;
import com.liferay.saml.binding.impl.HttpSoap11Binding;
import com.liferay.saml.credential.FileSystemKeyStoreManagerImpl;
import com.liferay.saml.credential.KeyStoreCredentialResolver;
import com.liferay.saml.metadata.MetadataGeneratorUtil;
import com.liferay.saml.metadata.MetadataManagerImpl;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.provider.CachingChainingMetadataProvider;
import com.liferay.saml.provider.DBMetadataProvider;
import com.liferay.saml.util.OpenSamlBootstrap;
import com.liferay.saml.util.PortletPropsKeys;
import com.liferay.saml.util.SamlIdentifierGenerator;
import com.liferay.saml.util.VelocityEngineFactory;

import java.io.UnsupportedEncodingException;

import java.lang.reflect.Field;

import java.net.URLDecoder;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import org.opensaml.common.IdentifierGenerator;
import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.metadata.EntityDescriptor;
import org.opensaml.saml2.metadata.IDPSSODescriptor;
import org.opensaml.saml2.metadata.SingleSignOnService;
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

	@Before
	public void setUp() throws Exception {
		setupProps();

		setupConfiguration();
		setupIdentifiers();
		setupMetadata();
		setupPortal();
		setupSamlBindings();

		OpenSamlBootstrap.bootstrap();
	}

	@After
	public void tearDown() {
		identifiers.clear();

		for (Class<?> serviceUtilClass : serviceUtilClasses) {
			try {
				Field field = serviceUtilClass.getDeclaredField("_service");

				field.setAccessible(true);

				field.set(serviceUtilClass, null);
			}
			catch (Exception e) {
			}
		}
	}

	protected Credential getCredential(String entityId) throws Exception {
		EntityIDCriteria entityIdCriteria = new EntityIDCriteria(entityId);

		CriteriaSet criteriaSet = new CriteriaSet();

		criteriaSet.add(entityIdCriteria);

		return credentialResolver.resolveSingle(criteriaSet);
	}

	protected MockHttpServletRequest getMockHttpServletRequest(String url) {
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
					url.indexOf(StringPool.COLON, protocol.length() + 3) + 1,
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
		mockHttpServletRequest.setSecure(protocol.equals("https"));
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

	protected <T> T getMockPortalService(
		Class<?> serviceUtilClass, Class<T> serviceClass) {

		serviceUtilClasses.add(serviceUtilClass);

		T service = mock(serviceClass);

		when(
			portalBeanLocator.locate(Mockito.eq(serviceClass.getName()))
		).thenReturn(
			service
		);

		return service;
	}

	protected <T> T getMockPortletService(
		Class<?> serviceUtilClass, Class<T> serviceClass) {

		serviceUtilClasses.add(serviceUtilClass);

		T service = mock(serviceClass);

		when(
			portletBeanLocator.locate(
				Mockito.eq(serviceClass.getName()))
		).thenReturn(
			service
		);

		return service;
	}

	protected void prepareIdentityProvider(String entityId) {
		when(
			props.get(PortletPropsKeys.SAML_ENTITY_ID)
		).thenReturn(
			entityId
		);

		when(
			props.get(PortletPropsKeys.SAML_ROLE)
		).thenReturn(
			"idp"
		);
	}

	protected void prepareServiceProvider(String entityId) {
		when(
			props.get(PortletPropsKeys.SAML_ENTITY_ID)
		).thenReturn(
			entityId
		);

		when(
			props.get(PortletPropsKeys.SAML_ROLE)
		).thenReturn(
			"sp"
		);

		when(
			props.get(PortletPropsKeys.SAML_SP_DEFAULT_IDP_ENTITY_ID)
		).thenReturn(
			IDP_ENTITY_ID
		);
	}

	protected void setupConfiguration() {
		Thread currentThread = Thread.currentThread();

		PortletClassLoaderUtil.setClassLoader(
			currentThread.getContextClassLoader());

		Configuration configuration = mock(Configuration.class);

		ConfigurationFactory configurationFactory = mock(
			ConfigurationFactory.class);

		ConfigurationFactoryUtil.setConfigurationFactory(configurationFactory);

		when(
			configurationFactory.getConfiguration(
				Mockito.any(ClassLoader.class), Mockito.eq("portlet"))
		).thenReturn(
			configuration
		);

		when(
			configurationFactory.getConfiguration(
				Mockito.any(ClassLoader.class), Mockito.eq("service"))
		).thenReturn(
			configuration
		);

		when(
			configuration.get(PortletPropsKeys.SAML_KEYSTORE_MANAGER_IMPL)
		).thenReturn(
			FileSystemKeyStoreManagerImpl.class.getName()
		);
	}

	protected void setupIdentifiers() {
		identifierGenerator = mock(IdentifierGenerator.class);

		when(
			identifierGenerator.generateIdentifier(Mockito.anyInt())
		).thenAnswer(
			new Answer<String>() {

				@Override
				public String answer(InvocationOnMock invocationOnMock)
					throws Throwable {

					int length = GetterUtil.getInteger(
						invocationOnMock.getArguments()[0]);

					String identifier =
						samlIdentifierGenerator.generateIdentifier(length);

					identifiers.add(identifier);

					return identifier;
				}

			}
		);
	}

	protected void setupMetadata() throws Exception {
		MetadataManagerImpl metadataManagerImpl = new MetadataManagerImpl();

		credentialResolver = new KeyStoreCredentialResolver();

		metadataManagerImpl.setCredentialResolver(credentialResolver);

		metadataManagerImpl.setParserPool(new BasicParserPool());

		MetadataManagerUtil metadataManagerUtil = new MetadataManagerUtil();

		metadataManagerUtil.setMetadataManager(metadataManagerImpl);

		CachingChainingMetadataProvider cachingChainingMetadataProvider =
			(CachingChainingMetadataProvider)
				metadataManagerImpl.getMetadataProvider();

		for (MetadataProvider metadataProvider :
				cachingChainingMetadataProvider.getProviders()) {

			cachingChainingMetadataProvider.removeMetadataProvider(
				metadataProvider);
		}

		cachingChainingMetadataProvider.addMetadataProvider(
			new MockMetadataProvider());
	}

	protected void setupPortal() {
		httpClient = mock(HttpClient.class);

		when(
			props.get(PropsKeys.VELOCITY_ENGINE_LOGGER)
		).thenReturn(
			"org.apache.velocity.runtime.log.SimpleLog4JLogSystem"
		);

		when(
			props.get(PropsKeys.VELOCITY_ENGINE_LOGGER_CATEGORY)
		).thenReturn(
			"org.apache.velocity"
		);

		PortalUtil portalUtil = new PortalUtil();

		portal = mock(Portal.class);

		portalUtil.setPortal(portal);

		when(
			portal.getPathMain()
		).thenReturn(
			Portal.PATH_MAIN
		);

		when(
			portal.getPortalURL(
				Mockito.any(MockHttpServletRequest.class))
		).thenReturn(
			PORTAL_URL
		);

		when(
			portal.getPortalURL(
				Mockito.any(MockHttpServletRequest.class), Mockito.eq(false))
		).thenReturn(
			PORTAL_URL
		);

		when(
			portal.getPortalURL(
				Mockito.any(MockHttpServletRequest.class), Mockito.eq(true))
		).thenReturn(
			StringUtil.replace(
				PORTAL_URL, new String[] {"http://", "https://"},
				new String[] {"8080", "8443"})
		);

		portalBeanLocator = mock(BeanLocator.class);

		PortalBeanLocatorUtil.setBeanLocator(portalBeanLocator);

		portletBeanLocator = mock(BeanLocator.class);

		PortletBeanLocatorUtil.setBeanLocator(
			"saml-portlet", portletBeanLocator);
	}

	protected void setupProps() {
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
			props.get(
				PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD + "[" +
					IDP_ENTITY_ID + "]")
		).thenReturn(
			"liferay"
		);

		when(
			props.get(
				PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD + "[" +
					SP_ENTITY_ID + "]")
		).thenReturn(
			"liferay"
		);

		when(
			props.get(
				PortletPropsKeys.SAML_KEYSTORE_CREDENTIAL_PASSWORD + "[" +
					UNKNOWN_ENTITY_ID + "]")
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
			props.getArray(PortletPropsKeys.SAML_METADATA_PATHS)
		).thenReturn(
			new String[0]
		);
	}

	protected void setupSamlBindings() {
		samlBindings = new ArrayList<SamlBinding>();

		samlBindings.add(
			new HttpPostBinding(
				new BasicParserPool(),
				VelocityEngineFactory.getVelocityEngine()));
		samlBindings.add(new HttpRedirectBinding(new BasicParserPool()));
		samlBindings.add(
			new HttpSoap11Binding(new BasicParserPool(), httpClient));
	}

	protected static final String ACS_URL =
		"http://localhost:8080/c/portal/saml/acs";

	protected static final long COMPANY_ID = 1;

	protected static final String IDP_ENTITY_ID = "testidp";

	protected static final String LOGIN_URL =
		"http://localhost:8080/c/portal/login";

	protected static final String LOGOUT_URL =
		"http://localhost:8080/c/portal/logout";

	protected static final String METADATA_URL =
		"http://localhost:8080/c/portal/saml/metadata";

	protected static final String PORTAL_URL = "http://localhost:8080";

	protected static final String RELAY_STATE =
		"http://localhost:8080/relaystate";

	protected static final long SESSION_ID = 2;

	protected static final String SLO_LOGOUT_URL =
		"http://localhost:8080/c/portal/saml/slo_logout";

	protected static final String SP_ENTITY_ID = "testsp";

	protected static final String SSO_URL =
		"http://localhost:8080/c/portal/saml/sso";

	protected static final String UNKNOWN_ENTITY_ID = "testunknown";

	protected CredentialResolver credentialResolver;
	protected HttpClient httpClient;
	protected IdentifierGenerator identifierGenerator;
	protected List<String> identifiers = new ArrayList<String>();
	protected Portal portal;
	protected BeanLocator portalBeanLocator;
	protected BeanLocator portletBeanLocator;
	protected Props props;
	protected List<SamlBinding> samlBindings;
	protected IdentifierGenerator samlIdentifierGenerator =
		new SamlIdentifierGenerator();
	protected List<Class<?>> serviceUtilClasses = new ArrayList<Class<?>>();

	private class MockMetadataProvider extends DBMetadataProvider {

		@Override
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

			Credential credential = credentialResolver.resolveSingle(
				criteriaSet);

			MockHttpServletRequest mockHttpServletRequest =
				getMockHttpServletRequest(
					"http://localhost:8080/c/portal/saml/metadata");

			if (entityId.equals(IDP_ENTITY_ID)) {
				EntityDescriptor entityDescriptor =
					MetadataGeneratorUtil.buildIdpEntityDescriptor(
						mockHttpServletRequest, entityId, true, true, false,
						credential);

				IDPSSODescriptor idpSsoDescriptor =
					entityDescriptor.getIDPSSODescriptor(
						SAMLConstants.SAML20P_NS);

				List<SingleSignOnService> singleSignOnServices =
					idpSsoDescriptor.getSingleSignOnServices();

				for (SingleSignOnService singleSignOnService :
						singleSignOnServices) {

					String binding = singleSignOnService.getBinding();

					if (binding.equals(SAMLConstants.SAML2_POST_BINDING_URI)) {
						singleSignOnServices.remove(singleSignOnService);

						break;
					}
				}

				return entityDescriptor;
			}
			else if (entityId.equals(SP_ENTITY_ID)) {
				return MetadataGeneratorUtil.buildSpEntityDescriptor(
					mockHttpServletRequest, entityId, true, true, false, false,
					credential);
			}

			return null;
		}
	}

}