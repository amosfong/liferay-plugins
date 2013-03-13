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

import com.liferay.portal.kernel.bi.reporting.ReportDataSourceType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gavin Wan
 * @author Brian Wing Shun Chan
 */
public class ReportFillManagerRegistry {

	public ReportFillManager getReportFillManager(
		ReportDataSourceType reportDataSourceType) {

		ReportFillManager reportFillManager = _reportFillManagers.get(
			reportDataSourceType);

		if (_reportFillManagers == null) {
			throw new IllegalArgumentException(
				"No report fill manager found for " + reportDataSourceType);
		}

		return reportFillManager;
	}

	public void setReportFillManagers(
		Map<String, ReportFillManager> reportFillManagers) {

		for (Map.Entry<String, ReportFillManager> entry :
				reportFillManagers.entrySet()) {

			ReportDataSourceType reportDataSourceType =
				ReportDataSourceType.parse(entry.getKey());
			ReportFillManager reportFillManager = entry.getValue();

			_reportFillManagers.put(reportDataSourceType, reportFillManager);
		}
	}

	private Map<ReportDataSourceType, ReportFillManager> _reportFillManagers =
		new ConcurrentHashMap<ReportDataSourceType, ReportFillManager>();

}