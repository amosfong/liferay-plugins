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

package com.liferay.portal.bi.reporting.jasperreports;

import com.liferay.portal.bi.reporting.jasperreports.compiler.CachedReportCompiler;
import com.liferay.portal.bi.reporting.jasperreports.compiler.DefaultReportCompiler;
import com.liferay.portal.bi.reporting.jasperreports.compiler.ReportCompiler;
import com.liferay.portal.bi.reporting.jasperreports.exporter.CsvReportFormatExporter;
import com.liferay.portal.bi.reporting.jasperreports.exporter.HtmlReportFormatExporter;
import com.liferay.portal.bi.reporting.jasperreports.exporter.PdfReportFormatExporter;
import com.liferay.portal.bi.reporting.jasperreports.exporter.RtfReportFormatExporter;
import com.liferay.portal.bi.reporting.jasperreports.exporter.TxtReportFormatExporter;
import com.liferay.portal.bi.reporting.jasperreports.exporter.XlsReportFormatExporter;
import com.liferay.portal.bi.reporting.jasperreports.exporter.XmlReportFormatExporter;
import com.liferay.portal.bi.reporting.jasperreports.fillmanager.CsvReportFillManager;
import com.liferay.portal.bi.reporting.jasperreports.fillmanager.EmptyReportFillManager;
import com.liferay.portal.bi.reporting.jasperreports.fillmanager.JdbcReportFillManager;
import com.liferay.portal.bi.reporting.jasperreports.fillmanager.PortalReportFillManager;
import com.liferay.portal.bi.reporting.jasperreports.fillmanager.ReportFillManager;
import com.liferay.portal.bi.reporting.jasperreports.fillmanager.ReportFillManagerRegistry;
import com.liferay.portal.bi.reporting.jasperreports.fillmanager.XlsReportFillManager;
import com.liferay.portal.bi.reporting.jasperreports.fillmanager.XmlReportFillManager;
import com.liferay.portal.kernel.bi.reporting.ByteArrayReportResultContainer;
import com.liferay.portal.kernel.bi.reporting.MemoryReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportDataSourceType;
import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportFormat;
import com.liferay.portal.kernel.bi.reporting.ReportFormatExporter;
import com.liferay.portal.kernel.bi.reporting.ReportFormatExporterRegistry;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.bi.reporting.ReportRequestContext;
import com.liferay.portal.kernel.bi.reporting.ReportResultContainer;
import com.liferay.portal.kernel.test.TestCase;

import java.io.File;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class ReportEngineImplTest extends TestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		_reportEngineImpl = new ReportEngineImpl();

		_reportEngineImpl.setReportCompiler(getReportCompiler());
		_reportEngineImpl.setReportFormatExporterRegistry(
			getReportFormatExporterRegistry());
		_reportEngineImpl.setReportFillManagerRegistry(
			getReportFillManagerRegistry());
	}

	@Test
	public void testCompileCsv() throws Exception {
		compile(
			ReportDataSourceType.CSV, "CsvDataSource.txt",
			"CsvDataSourceReport.jrxml", ReportFormat.CSV);
	}

	@Test
	public void testCompileXls() throws Exception {
		compile(
			ReportDataSourceType.XLS, "XlsDataSource.data.xls",
			"XlsDataSourceReport.jrxml", ReportFormat.CSV);
	}

	@Test
	public void testCompileXml() throws Exception {
		compile(
			ReportDataSourceType.XML, "northwind.xml", "OrdersReport.jrxml",
			ReportFormat.CSV);
	}

	@Test
	public void testExportCsv() throws Exception {
		export(ReportFormat.CSV);
	}

	@Test
	public void testExportPdf() throws Exception {
		export(ReportFormat.PDF);
	}

	@Test
	public void testExportRtf() throws Exception {
		export(ReportFormat.RTF);
	}

	@Test
	public void testExportTxt() throws Exception {
		export(ReportFormat.TXT);
	}

	@Test
	public void testExportXls() throws Exception {
		export(ReportFormat.XLS);
	}

	@Test
	public void testExportXml() throws Exception {
		export(ReportFormat.XML);
	}

	protected ReportRequest compile(
			ReportDataSourceType reportDataSourceType,
			String dataSourceFileName, String dataSourceReportFileName,
			ReportFormat reportFormat)
		throws Exception {

		ReportRequest reportRequest = getReportRequest(
			reportDataSourceType, dataSourceFileName, dataSourceReportFileName,
			reportFormat);

		_reportEngineImpl.compile(reportRequest);

		return reportRequest;
	}

	protected void export(ReportFormat reportFormat) throws Exception {
		ReportRequest reportRequest = compile(
			ReportDataSourceType.CSV, "CsvDataSource.txt",
			"CsvDataSourceReport.jrxml", ReportFormat.CSV);

		ReportResultContainer reportResultContainer =
			new ByteArrayReportResultContainer();

		_reportEngineImpl.execute(reportRequest, reportResultContainer);

		assertFalse(reportResultContainer.hasError());
		assertNotNull(reportResultContainer.getResults());
	}

	protected ReportCompiler getReportCompiler() {
		ReportCompiler reportCompiler = new DefaultReportCompiler();

		return new CachedReportCompiler(reportCompiler);
	}

	protected ReportFillManagerRegistry getReportFillManagerRegistry() {
		ReportFillManagerRegistry reportFillManagerRegistry =
			new ReportFillManagerRegistry();

		Map<String, ReportFillManager> reportFillManagers =
			new HashMap<String, ReportFillManager>();

		reportFillManagers.put("csv", new CsvReportFillManager());
		reportFillManagers.put("empty", new EmptyReportFillManager());
		reportFillManagers.put("jdbc", new JdbcReportFillManager());
		reportFillManagers.put("portal", new PortalReportFillManager());
		reportFillManagers.put("xls", new XlsReportFillManager());
		reportFillManagers.put("xml", new XmlReportFillManager());

		reportFillManagerRegistry.setReportFillManagers(reportFillManagers);

		return reportFillManagerRegistry;
	}

	protected ReportFormatExporterRegistry getReportFormatExporterRegistry() {
		ReportFormatExporterRegistry reportFormatExporterRegistry =
			new ReportFormatExporterRegistry();

		Map<String, ReportFormatExporter> reportFormatExporters =
			new HashMap<String, ReportFormatExporter>();

		reportFormatExporters.put("csv", new CsvReportFormatExporter());
		reportFormatExporters.put("html", new HtmlReportFormatExporter());
		reportFormatExporters.put("pdf", new PdfReportFormatExporter());
		reportFormatExporters.put("rtf", new RtfReportFormatExporter());
		reportFormatExporters.put("txt", new TxtReportFormatExporter());
		reportFormatExporters.put("xls", new XlsReportFormatExporter());
		reportFormatExporters.put("xml", new XmlReportFormatExporter());

		reportFormatExporterRegistry.setReportFormatExporters(
			reportFormatExporters);

		return reportFormatExporterRegistry;
	}

	protected ReportRequest getReportRequest(
			ReportDataSourceType reportDataSourceType,
			String dataSourceFileName, String dataSourceReportFileName,
			ReportFormat reportFormat)
		throws Exception {

		ReportRequestContext reportRequestContext = new ReportRequestContext(
			reportDataSourceType);

		File dataSourceFile = new File(_BASE_DIR + dataSourceFileName);

		byte[] dataSourceByteArray = FileUtils.readFileToByteArray(
			dataSourceFile);

		reportRequestContext.setAttribute(
			ReportRequestContext.DATA_SOURCE_BYTE_ARRAY, dataSourceByteArray);

		reportRequestContext.setAttribute(
			ReportRequestContext.DATA_SOURCE_COLUMN_NAMES,
			"city,id,name,address,state");

		File dataSourceReportFile = new File(
			_BASE_DIR + dataSourceReportFileName);

		byte[] reportByteArray = FileUtils.readFileToByteArray(
			dataSourceReportFile);

		ReportDesignRetriever reportDesignRetriever =
			new MemoryReportDesignRetriever(
				"test", new Date(), reportByteArray);

		ReportRequest reportRequest = new ReportRequest(
			reportRequestContext, reportDesignRetriever,
			new HashMap<String, String>(), reportFormat.getValue());

		return reportRequest;
	}

	private static final String _BASE_DIR =
		"webs/jasperreports-web/test/integration/com/liferay/portal/bi/" +
			"reporting/jasperreports/";

	private ReportEngineImpl _reportEngineImpl;

}