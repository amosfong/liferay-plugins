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

package com.liferay.portal.bi.reporting.jasperreports.compiler;

import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;
import com.liferay.portal.kernel.util.LRUMap;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class CachedReportCompiler implements ReportCompiler {

	public CachedReportCompiler(ReportCompiler reportCompiler) {
		this(reportCompiler, _DEFAULT_MAX_SIZE);
	}

	public CachedReportCompiler(ReportCompiler reportCompiler, int maxSize) {
		_reportCompiler = reportCompiler;
		_cachedJasperReports = Collections.synchronizedMap(
			new LRUMap<String, CachedJasperReport>(maxSize));
	}

	public JasperReport compile(ReportDesignRetriever reportDesignRetriever)
		throws JRException {

		return compile(reportDesignRetriever, false);
	}

	public JasperReport compile(
			ReportDesignRetriever reportDesignRetriever, boolean force)
		throws JRException {

		String reportName = reportDesignRetriever.getReportName();

		Date modifiedDate = reportDesignRetriever.getModifiedDate();

		long timestamp = modifiedDate.getTime();

		CachedJasperReport cachedJasperReport = _cachedJasperReports.get(
			reportName);

		if ((cachedJasperReport == null) ||
			(cachedJasperReport.getTimestamp() != timestamp) || force) {

			cachedJasperReport = new CachedJasperReport(
				_reportCompiler.compile(reportDesignRetriever), timestamp);

			_cachedJasperReports.put(reportName, cachedJasperReport);
		}

		return cachedJasperReport.getJasperReport();
	}

	public void flush() {
		_cachedJasperReports.clear();
	}

	private static final int _DEFAULT_MAX_SIZE = 25;

	private Map<String, CachedJasperReport> _cachedJasperReports;
	private ReportCompiler _reportCompiler;

	private class CachedJasperReport {

		public CachedJasperReport(JasperReport jasperReport, long timestamp) {
			_jasperReport = jasperReport;
			_timestamp = timestamp;
		}

		public JasperReport getJasperReport() {
			return _jasperReport;
		}

		public long getTimestamp() {
			return _timestamp;
		}

		private JasperReport _jasperReport;
		private long _timestamp;

	}

}