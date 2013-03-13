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
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.bi.reporting.ReportResultContainer;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class TxtReportFormatExporter extends BaseReportFormatExporter {

	public void format(
			Object report, ReportRequest reportRequest,
			ReportResultContainer reportResultContainer)
		throws ReportExportException {

		try {
			JRExporter jrExporter = getJRExporter();

			Map<String, String> reportParameters =
				reportRequest.getReportParameters();

			String characterEncoding = GetterUtil.getString(
				reportParameters.get(
					JRExporterParameter.CHARACTER_ENCODING.toString()),
				StringPool.UTF8);

			jrExporter.setParameter(
				JRTextExporterParameter.CHARACTER_ENCODING, characterEncoding);

			Float characterHeight = GetterUtil.getFloat(
				reportParameters.get(
					JRTextExporterParameter.CHARACTER_HEIGHT.toString()),
				11.9f);

			jrExporter.setParameter(
				JRTextExporterParameter.CHARACTER_HEIGHT, characterHeight);

			Float characterWidth = GetterUtil.getFloat(
				reportParameters.get(
					JRTextExporterParameter.CHARACTER_WIDTH.toString()),
				6.55f);

			jrExporter.setParameter(
				JRTextExporterParameter.CHARACTER_WIDTH, characterWidth);

			jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, report);
			jrExporter.setParameter(
				JRExporterParameter.OUTPUT_STREAM,
				reportResultContainer.getOutputStream());

			jrExporter.exportReport();
		}
		catch (Exception e) {
			throw new ReportExportException(e);
		}
	}

	protected JRExporter getJRExporter() {
		return new JRTextExporter();
	}

}