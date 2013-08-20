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

package com.liferay.vldap.server.directory;

import com.liferay.portal.kernel.bean.BeanLocator;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactory;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactory;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactory;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalService;
import com.liferay.portal.service.CompanyLocalServiceUtil;
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
 * @author William Newbury
 */
@RunWith(PowerMockRunner.class)
public class BaseVLDAPTestCase extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setupServiceMocks();
		setupPortal();
		setupProps();
		setupCompany();
		setupConfiguration();
		setupFactories();
		setupBuilderBase();
	}

	@After
	public void tearDown() {
		for (Class<?> utilClass : _serviceUtilClasses) {
			try {
				Field field = utilClass.getDeclaredField("_service");

				field.setAccessible(true);

				field.set(utilClass, null);
			}
			catch (Exception e) {
			}
		}
	}

	protected <T> T getMockService(Class<?> utilType, Class<T> serviceType) {
		_serviceUtilClasses.add(utilType);

		T serviceMock = mock(serviceType);

		when(
			_portalBeanLocator.locate(
				Mockito.eq(serviceType.getName()))
		).thenReturn(
			serviceMock
		);

		return serviceMock;
	}

	protected void setupBuilderBase() {
		Long testLong = 42l;

		_searchBase = mock(SearchBase.class);

		when(_searchBase.getCompanies()).thenReturn(_companies);
		when(_searchBase.getSizeLimit()).thenReturn(testLong);
		when(_searchBase.getTop()).thenReturn("Liferay");
	}

	protected void setupCompany() throws Exception {
		Long testLong = 42l;

		_company = mock(Company.class);

		when(_company.getWebId()).thenReturn("liferay.com");
		when(_company.getCompanyId()).thenReturn(testLong);

		_companies = new ArrayList<Company>();
		_companies.add(_company);

		CompanyLocalService companyLocalService = getMockService(
			CompanyLocalServiceUtil.class, CompanyLocalService.class);

		when(
			companyLocalService.getCompanies()
		).thenReturn(
			_companies
		);

		when(
			companyLocalService.getCompanies(Mockito.anyBoolean())
		).thenReturn(
			_companies
		);

		when(
			companyLocalService.getCompanyByWebId(Mockito.eq("liferay.com"))
		).thenReturn(
			_company
		);
	}

	protected void setupConfiguration() {
		Thread currentThread = Thread.currentThread();

		PortletClassLoaderUtil.setClassLoader(
			currentThread.getContextClassLoader());

		Configuration configuration = mock(Configuration.class);

		String[] sambaNames = new String[1];
		sambaNames[0] = "testDomainName";

		when(
			configuration.getArray(PortletPropsKeys.SAMBA_DOMAIN_NAMES)
			).thenReturn(sambaNames);

		String[] sambaHosts = new String[0];

		when(
			configuration.getArray(PortletPropsKeys.SAMBA_HOSTS_ALLOWED)
			).thenReturn(sambaHosts);

		ConfigurationFactory configurationFactory = mock(
			ConfigurationFactory.class);

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

		ConfigurationFactoryUtil.setConfigurationFactory(configurationFactory);
	}

	protected void setupFactories() throws Exception {
		Criterion criterion = mock(Criterion.class);

		DynamicQuery dynamicQuery = mock(DynamicQuery.class);

		DynamicQueryFactory dynamicQueryFactory = mock(
			DynamicQueryFactory.class);

		when(
			dynamicQueryFactory.forClass(
				Mockito.any(Class.class), Mockito.any(ClassLoader.class))
		).thenReturn(
			dynamicQuery
		);

		DynamicQueryFactoryUtil dynamicQueryFactoryUtil =
			new DynamicQueryFactoryUtil();
		dynamicQueryFactoryUtil.setDynamicQueryFactory(dynamicQueryFactory);

		RestrictionsFactory restrictionsFactory = mock(
			RestrictionsFactory.class);

		when(
			restrictionsFactory.eq(
				Mockito.anyString(), Mockito.any(Object.class))
		).thenReturn(
			criterion
		);
		when(
			restrictionsFactory.ilike(
				Mockito.anyString(), Mockito.any(Object.class))
		).thenReturn(
			criterion
		);

		RestrictionsFactoryUtil restrictionsFactoryUtil =
			new RestrictionsFactoryUtil();
		restrictionsFactoryUtil.setRestrictionsFactory(restrictionsFactory);
	}

	protected void setupPortal() {
		_portalBeanLocator = mock(BeanLocator.class);

		PortalBeanLocatorUtil.setBeanLocator(_portalBeanLocator);
	}

	protected void setupProps() {
		props = mock(Props.class);

		PropsUtil.setProps(props);

		when(props.get(PortletPropsKeys.SEARCH_MAX_SIZE)).thenReturn("42");
	}

	protected void setupServiceMocks() {
		_serviceUtilClasses = new ArrayList<Class<?>>();
	}

	protected List<Company> _companies;
	protected Company _company;
	protected BeanLocator _portalBeanLocator;
	protected SearchBase _searchBase;
	protected List<Class<?>> _serviceUtilClasses;
	protected Props props;

}