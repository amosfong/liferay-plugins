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
import com.liferay.portal.service.GroupLocalService;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalService;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalService;
import com.liferay.portal.service.UserGroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
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
 * @author William Newbury
 */
@RunWith(PowerMockRunner.class)
public class BaseVLDAPTestCase extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setupPortal();

		setupConfiguration();
		setupCompany();
		setupORM();
		setupProps();
		setupSearchBase();
	}

	@After
	public void tearDown() {
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

	protected <T> T getMockPortalService(
		Class<?> serviceUtilClass, Class<T> serviceClass) {

		serviceUtilClasses.add(serviceUtilClass);

		T serviceMock = mock(serviceClass);

		when(
			portalBeanLocator.locate(Mockito.eq(serviceClass.getName()))
		).thenReturn(
			serviceMock
		);

		return serviceMock;
	}

	protected void setupCompany() throws Exception {
		company = mock(Company.class);

		when(
			company.getCompanyId()
		).thenReturn(
			42l
		);

		when(
			company.getWebId()
		).thenReturn(
			"liferay.com"
		);

		companies.add(company);

		CompanyLocalService companyLocalService = getMockPortalService(
			CompanyLocalServiceUtil.class, CompanyLocalService.class);

		when(
			companyLocalService.getCompanies()
		).thenReturn(
			companies
		);

		when(
			companyLocalService.getCompanies(Mockito.anyBoolean())
		).thenReturn(
			companies
		);

		when(
			companyLocalService.getCompanyByWebId(Mockito.eq("liferay.com"))
		).thenReturn(
			company
		);
	}

	protected void setupConfiguration() {
		Thread currentThread = Thread.currentThread();

		PortletClassLoaderUtil.setClassLoader(
			currentThread.getContextClassLoader());

		Configuration configuration = mock(Configuration.class);

		when(
			configuration.getArray(PortletPropsKeys.SAMBA_DOMAIN_NAMES)
		).thenReturn(
			new String[] {"testDomainName"}
		);

		when(
			configuration.getArray(PortletPropsKeys.SAMBA_HOSTS_ALLOWED)
		).thenReturn(
			new String[0]
		);

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

	protected void setupORM() throws Exception {
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
		portalBeanLocator = mock(BeanLocator.class);

		PortalBeanLocatorUtil.setBeanLocator(portalBeanLocator);

		groupLocalService = getMockPortalService(
			GroupLocalServiceUtil.class, GroupLocalService.class);
		organizationLocalService = getMockPortalService(
			OrganizationLocalServiceUtil.class, OrganizationLocalService.class);
		roleLocalService = getMockPortalService(
			RoleLocalServiceUtil.class, RoleLocalService.class);
		userGroupLocalService = getMockPortalService(
			UserGroupLocalServiceUtil.class, UserGroupLocalService.class);
		userLocalService = getMockPortalService(
			UserLocalServiceUtil.class, UserLocalService.class);
	}

	protected void setupProps() {
		props = mock(Props.class);

		PropsUtil.setProps(props);

		when(
			props.get(PortletPropsKeys.SEARCH_MAX_SIZE)
		).thenReturn(
			"42"
		);
	}

	protected void setupSearchBase() {
		searchBase = mock(SearchBase.class);

		when(
			searchBase.getCompanies()
		).thenReturn(
			companies
		);

		when(
			searchBase.getSizeLimit()
		).thenReturn(
			42l
		);

		when(
			searchBase.getTop()
		).thenReturn(
			"Liferay"
		);
	}

	protected List<Company> companies = new ArrayList<Company>();
	protected Company company;
	protected GroupLocalService groupLocalService;
	protected OrganizationLocalService organizationLocalService;
	protected BeanLocator portalBeanLocator;
	protected Props props;
	protected RoleLocalService roleLocalService;
	protected SearchBase searchBase;
	protected List<Class<?>> serviceUtilClasses = new ArrayList<Class<?>>();
	protected UserGroupLocalService userGroupLocalService;
	protected UserLocalService userLocalService;

}