/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.kernel.apptest;

import com.liferay.portal.kernel.app.AppVerifier;
import com.liferay.portal.kernel.apptest.servlet.filters.LiferayPackageFilter;
import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.deploy.hot.LiferayPackageHotDeployException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.license.util.LicenseManagerUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = AppVerifier.class)
public class LiferayPackageAppVerifier implements AppVerifier {

	public void destroy() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}
	}

	public void init(long bundleId) throws Exception {
		try {
			Map<String, String> licenseProperties =
				LicenseManagerUtil.getLicenseProperties(_getProductId());

			String description = GetterUtil.getString(
				licenseProperties.get("description"));

			if (description.startsWith("Developer License")) {
				_registerFilter(bundleId);
			}
		}
		catch (Exception e) {
			throw new LiferayPackageHotDeployException(
				"Unable to intialize Liferay package filter");
		}
	}

	public boolean verify() {
		try {
			_verify();
		}
		catch (Exception e) {
			//log e

			return false;
		}

		return false; // test
	}

	private String _getFilterName() {
		return "Liferay Package Filter - " + _getProductId();
	}

	private int _getLicenseState(String productId, int productVersion)
		throws Exception {

		Map<String, String> licenseProperties = new HashMap<>();

		licenseProperties.put("productId", productId);
		licenseProperties.put("productVersion", String.valueOf(productVersion));
		licenseProperties.put("userCount", String.valueOf(_getUserCount()));

		return LicenseManagerUtil.getLicenseState(licenseProperties);
	}

	private String _getProductId() {
		return _productId;
	}

	private int _getProductType() {
		return GetterUtil.getInteger(_productType);
	}

	private int _getProductVersion() {
		return GetterUtil.getInteger(_productVersion);
	}

	private int _getUserCount() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select count(*) from User_ where (defaultUser = ?) and " +
					"(status = ?)");

			ps.setBoolean(1, false);
			ps.setLong(2, WorkflowConstants.STATUS_APPROVED);

			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);

				if (count > 0) {
					return count;
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		throw new Exception("Unable to count number of users on server");
	}

	private void _registerFilter(long bundleId) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();

		Filter filter = (Filter)InstanceFactory.newInstance(
			classLoader, LiferayPackageFilter.class.getName(),
			new Class[] {long.class, String.class},
			new Object[] {
				bundleId, PortalUtil.getPathContext() + "/c/portal/license"
			});

		filter = (Filter)ProxyUtil.newProxyInstance(
			classLoader, new Class[] {Filter.class},
			new ClassLoaderBeanHandler(filter, classLoader));

		Map<String, Object> properties = new HashMap<>();

		properties.put(
			"servlet-context-name", PortalUtil.getServletContextName());

		List<String> urlPatterns = new ArrayList<>();

		urlPatterns.add("/*");

		properties.put("url-pattern", urlPatterns);

		Registry registry = RegistryUtil.getRegistry();

		_serviceRegistration = registry.registerService(
			Filter.class, filter, properties);
	}

	private void _verify() throws Exception {
		if (_getProductType() == _productTypeEE) {
			int licenseState = LicenseManagerUtil.getLicenseState(
				_productIdPortal);

			if (licenseState != _stateGood) {
				LicenseManagerUtil.checkLicense(_productIdPortal);

				licenseState = LicenseManagerUtil.getLicenseState(
					_productIdPortal);
			}

			if (licenseState != _stateGood) {
				throw new LiferayPackageHotDeployException(
					"This application requires a valid Liferay Portal EE " +
						"license.");
			}
		}

		if (Validator.isNull(_getProductId())) {
			return;
		}

		int licenseState = _getLicenseState(
			_getProductId(), _getProductVersion());

		if (licenseState != _stateGood) {
			LicenseManagerUtil.checkLicense(_getProductId());

			licenseState = _getLicenseState(
				_getProductId(), _getProductVersion());
		}

		if (licenseState != _stateGood) {
			throw new LiferayPackageHotDeployException(
				"This application does not have a valid license");
		}

		Map<String, String> licenseProperties =
			LicenseManagerUtil.getLicenseProperties(_getProductId());

		if (licenseProperties == null) {
			throw new LiferayPackageHotDeployException(
				"This Liferay version does not support this application.");
		}

		if (licenseProperties != null) {
			int maxValidProductVersion = GetterUtil.getInteger(
				licenseProperties.get("productVersion"));

			if ((_getProductVersion() > 0) &&
				(_getProductVersion() > maxValidProductVersion)) {

				throw new LiferayPackageHotDeployException(
					"The version of your application is not compatible with " +
						"the registered license");
			}
		}
	}

	private final String _productId = "_PRODUCT_ID_";
	private final String _productIdPortal = "Portal";
	private final String _productType = "_PRODUCT_TYPE_";
	private final int _productTypeEE = 2;
	private final String _productVersion = "_PRODUCT_VERSION_";
	private ServiceRegistration<Filter> _serviceRegistration;
	private final int _stateGood = 3;

}