Liferay.Service.register("Liferay.Service.KaleoForms", "com.liferay.portal.workflow.kaleo.forms.service", "kaleo-forms-portlet");

Liferay.Service.registerClass(
	Liferay.Service.KaleoForms, "KaleoProcess",
	{
		addKaleoProcess: true,
		deleteKaleoProcess: true,
		deleteKaleoProcessData: true,
		getKaleoProcess: true,
		updateKaleoProcess: true
	}
);

Liferay.Service.registerClass(
	Liferay.Service.KaleoForms, "KaleoProcessLink",
	{
		fetchKaleoProcessLink: true,
		updateKaleoProcessLink: true
	}
);