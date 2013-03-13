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

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 */
public class XmlReportFillManager extends BaseReportFillManager {

	protected JRDataSource getJRDataSource(ReportRequest reportRequest)
		throws Exception {

		return new JRXmlDataSource(
			getDataSourceByteArrayInputStream(reportRequest));
	}

}