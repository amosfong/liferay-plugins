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

package com.liferay.portal.bi.reporting.jasperreports.exporter;

import com.liferay.portal.kernel.bi.reporting.ReportExportException;
import com.liferay.portal.kernel.bi.reporting.ReportFormatExporter;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.bi.reporting.ReportResultContainer;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public abstract class BaseReportFormatExporter implements ReportFormatExporter {

	@Override
	public void format(
			Object report, ReportRequest request,
			ReportResultContainer container)
		throws ReportExportException {

		JRExporter jrExporter = getJRExporter();

		try {
			jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, report);
			jrExporter.setParameter(
				JRExporterParameter.OUTPUT_STREAM, container.getOutputStream());

			jrExporter.exportReport();
		}
		catch (Exception e) {
			throw new ReportExportException(
				"Unable to export report using " + jrExporter.getClass(), e);
		}
	}

	protected abstract JRExporter getJRExporter();

}