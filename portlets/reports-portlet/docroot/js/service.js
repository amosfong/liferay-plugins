Liferay.Service.register("Liferay.Service.Report", "com.liferay.reports.service", "reports-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Report, "Definition",
	{
		deleteDefinitionAndAttachments: true,
		updateDefinition: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Report, "Entry",
	{
		archiveRequest: true,
		deleteRequestAndAttachments: true,
		deleteReportFile: true,
		generateReport: true,
		getReportFile: true,
		scheduleReportRequest: true,
		unScheduleReportRequest: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Report, "Source",
	{
		addDataSource: true,
		deleteDataSource: true,
		getSource: true,
		updateDataSource: true
	}
);

Liferay.Service.register("Liferay.Service.Reports", "com.liferay.reports.service", "reports-portlet");

Liferay.Service.registerClass(
	Liferay.Service.Reports, "Definition",
	{
		deleteDefinition: true,
		getDefinitions: true,
		getDefinitionsCount: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Reports, "Entry",
	{
		addEntry: true,
		deleteAttachment: true,
		deleteEntry: true,
		getEntries: true,
		getEntriesCount: true,
		unscheduleEntry: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.Reports, "Source",
	{
		addSource: true,
		deleteSource: true,
		getSources: true,
		getSourcesCount: true,
		updateSource: true
	}
);