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

package com.liferay.vldap;

import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactory;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Company;
import com.liferay.vldap.server.directory.SearchBase;
import com.liferay.vldap.util.PortletPropsKeys;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
* @author Matthew Tambara
* @author William Newbury
*/

@RunWith(PowerMockRunner.class)
public class BaseVLDAPTestCase extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setupServiceMocks();
		setupPortal();
		setupProps();
		setupConfiguration();
		setupBuilderBase();
	}

	@After
	public void tearDown() {
		for (Class<?> utilClass : serviceUtils) {
			try {
				Field field = utilClass.getDeclaredField("_service");

				field.setAccessible(true);

				field.set(utilClass, null);
			}
			catch (Exception e) {
			}
		}
	}

	protected <T> T mockService(Class<?> utilType, Class<T> serviceType) {
		serviceUtils.add(utilType);

		T serviceMock = mock(serviceType);

		when(
			portalBeanLocator.locate(
				Mockito.eq(serviceType.getName()))
		).thenReturn(
			serviceMock
		);

		return serviceMock;
	}

	protected void setupBuilderBase() {
		_searchBase = mock(SearchBase.class);
		_company = mock(Company.class);
		_companies = new ArrayList<Company>();

		_companies.add(_company);

		Long testLong = new Long("42");
		when(_searchBase.getCompanies()).thenReturn(_companies);
		when(_company.getWebId()).thenReturn("42");
		when(_company.getCompanyId()).thenReturn(testLong);
		when(_searchBase.getSizeLimit()).thenReturn(testLong);
		when(_searchBase.getTop()).thenReturn("42");
	}

	protected void setupConfiguration() {
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
			configurationFactory.getConfiguration(
				Mockito.any(ClassLoader.class), Mockito.eq("service"))
		).thenReturn(
			configuration
		);

		String[] sambaNames = new String[1];
		sambaNames[0] = "testDomainName";
		when(
			configuration.getArray(PortletPropsKeys.SAMBA_DOMAIN_NAMES)
		).thenReturn(sambaNames);
		String[] sambaHosts = new String[0];
		when(
			configuration.getArray(PortletPropsKeys.SAMBA_HOSTS_ALLOWED)
		).thenReturn(sambaHosts);
	}

	protected void setupPortal() {

		portalBeanLocator = mock(BeanLocator.class);
		PortalBeanLocatorUtil.setBeanLocator(portalBeanLocator);

	}

	protected void setupProps() {
		props = mock(Props.class);

		PropsUtil.setProps(props);
	}

	protected void setupServiceMocks() {
		serviceUtils = new ArrayList<Class<?>>();
	}

	protected List<Company> _companies;
	protected Company _company;
	protected SearchBase _searchBase;
	protected BeanLocator portalBeanLocator;
	protected Props props;
	protected List<Class<?>> serviceUtils;

}