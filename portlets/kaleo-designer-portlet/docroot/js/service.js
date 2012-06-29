Liferay.Service.register("Liferay.Service.KaleoDesigner", "com.liferay.portal.workflow.kaleo.designer.service", "kaleo-designer-portlet");

Liferay.Service.registerClass(
	Liferay.Service.KaleoDesigner, "KaleoDraftDefinition",
	{
		addKaleoDraftDefinition: true,
		addWorkflowDefinitionKaleoDraftDefinition: true,
		getKaleoDraftDefinition: true,
		getKaleoDraftDefinitions: true,
		getLatestKaleoDraftDefinition: true,
		publishKaleoDraftDefinition: true,
		updateKaleoDraftDefinition: true
	}
);