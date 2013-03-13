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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRCsvDataSource;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 */
public class CsvReportFillManager extends BaseReportFillManager {

	protected JRDataSource getJRDataSource(ReportRequest reportRequest)
		throws Exception {

		JRCsvDataSource jrCsvDataSource = null;

		String charsetName = getDataSourceCharSet(reportRequest);

		if (Validator.isNotNull(charsetName)) {
			jrCsvDataSource = new JRCsvDataSource(
				getDataSourceByteArrayInputStream(reportRequest), charsetName);
		}
		else {
			jrCsvDataSource = new JRCsvDataSource(
				getDataSourceByteArrayInputStream(reportRequest));
		}

		String[] dataSourceColumnNames = getDataSourceColumnNames(
			reportRequest);

		if (dataSourceColumnNames != null) {
			jrCsvDataSource.setColumnNames(dataSourceColumnNames);
		}
		else {
			jrCsvDataSource.setUseFirstRowAsHeader(true);
		}

		jrCsvDataSource.setRecordDelimiter(StringPool.RETURN_NEW_LINE);

		return jrCsvDataSource;
	}

}