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

package com.liferay.portal.bi.reporting.jasperreports.fillmanager;

import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.bi.reporting.ReportRequestContext;

import java.sql.Connection;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 */
public class JdbcReportFillManager extends BaseReportFillManager {

	protected Connection getConnection(ReportRequest reportRequest)
		throws Exception {

		Properties properties = new Properties();

		ReportRequestContext reportRequestContext =
			reportRequest.getReportRequestContext();

		setProperty(
			properties, "driverClassName", reportRequestContext,
			ReportRequestContext.JDBC_DRIVER_CLASS);
		setProperty(
			properties, "password", reportRequestContext,
			ReportRequestContext.JDBC_PASSWORD);
		setProperty(
			properties, "url", reportRequestContext,
			ReportRequestContext.JDBC_URL);
		setProperty(
			properties, "username", reportRequestContext,
			ReportRequestContext.JDBC_USER_NAME);

		DataSource dataSource = BasicDataSourceFactory.createDataSource(
			properties);

		return dataSource.getConnection();
	}

	protected void setProperty(
		Properties properties, String propertyKey,
		ReportRequestContext reportRequestContext, String attributeKey) {

		String value = (String)reportRequestContext.getAttribute(attributeKey);

		properties.put(propertyKey, value);
	}

}