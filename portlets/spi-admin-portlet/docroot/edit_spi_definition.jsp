<%--
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
--%>
<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long spiDefinitionId = ParamUtil.getLong(renderRequest, "spiDefinitionId", 0);

SPIDefinition spiDefinition = null;

int connectorPort = SPIConfigurationTemplate.getConnectorPortMin();
String javaExecutable = SPIConfigurationTemplate.getJavaExecutable();
int maxThreads = SPIConfigurationTemplate.getMaxThreads();
String name = StringPool.BLANK;
long pingInterval = SPIConfigurationTemplate.getSPIPingInterval();
String[] portletIds = StringPool.EMPTY_ARRAY;
long registerTimeout = SPIConfigurationTemplate.getSPIRegisterTimeout();
String[] servletContextNames = StringPool.EMPTY_ARRAY;
int status = SPIAdminConstants.STATUS_STOPPED;
long shutdownTimeout = SPIConfigurationTemplate.getSPIShutdownTimeout();

if (spiDefinitionId > 0) {
	spiDefinition = SPIDefinitionServiceUtil.getSPIDefinition(spiDefinitionId);

	name = spiDefinition.getName();
	connectorPort = spiDefinition.getConnectorPort();
	javaExecutable = spiDefinition.getJavaExecutable();
	maxThreads = spiDefinition.getMaxThreads();
	pingInterval = spiDefinition.getPingInterval();
	portletIds = StringUtil.split(spiDefinition.getPortletIds());
	registerTimeout = spiDefinition.getRegisterTimeout();
	shutdownTimeout = spiDefinition.getShutdownTimeout();
	servletContextNames = StringUtil.split(
			spiDefinition.getServletContextNames());
	status = spiDefinition.getStatus();
}

Tuple tuple = SPIDefinitionServiceUtil.getPortletIdsAndServletContextNames();

Set<String> usedPortletIds = (Set<String>)tuple.getObject(0);
Set<String> usedServletContextNames = (Set<String>)tuple.getObject(1);

String portalServletContextName = PortalUtil.getPathContext();

ServletContext portalServletContext = ServletContextPool.get(portalServletContextName);
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	localizeTitle="<%= (spiDefinition == null) %>"
	title='<%= (spiDefinition == null) ? "add-spi" : name + " (" + LanguageUtil.get(pageContext, SPIAdminConstants.getStatusLabel(status))+ ")" %>'
/>

<c:if test="<%= (status == SPIAdminConstants.STATUS_STARTED) || (status == SPIAdminConstants.STATUS_STARTING) %>">
	<div class="alert alert-info">
		<liferay-ui:message key="spi-currently-active" />
	</div>
</c:if>

<portlet:actionURL name='<%= (spiDefinition != null) ? "editSPIDefinition" : "addSPIDefinition" %>' var="editSPIDefinitionURL" />

<aui:form action="<%= editSPIDefinitionURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); "+ renderResponse.getNamespace() + "saveSPIDefinition();" %>'>
	<aui:input name="mvcPath" type="hidden" value="/edit_spi_definition.jsp" />
	<aui:input name="portletIds" type="hidden" value="" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="spiDefinitionId" type="hidden" value="<%= spiDefinitionId %>" />
	<aui:input name="servletContextNames" type="hidden" value="" />

	<liferay-ui:error exception="<%= DuplicateSPIDefinitionConnectorException.class %>" message="please-enter-a-unique-spi-connector-address-and-port" />
	<liferay-ui:error exception="<%= DuplicateSPIDefinitionException.class %>" message="please-enter-a-unique-spi-name" />
	<liferay-ui:error exception="<%= InvalidSPIDefinitionConnectorException.class %>" message="please-enter-a-valid-spi-connectos-address-and-port" />
	<liferay-ui:error exception="<%= SPIDefinitionActiveException.class %>" message="cannot-update-a-running-spi-configuration" />

	<aui:model-context bean="<%= spiDefinition %>" model="<%= SPIDefinition.class %>" />

	<liferay-ui:panel-container extended="true" id="spiDefinitionGeneralPanel" persistState="true">
		<liferay-ui:panel collapsible="true" extended="true" id="spiDefinitionGeneralPanel" persistState="true" title="general">
			<aui:fieldset>
				<aui:input autoFocus="<%= (spiDefinition == null) && (windowState.equals(WindowState.MAXIMIZED)) %>" disabled="<%= spiDefinition != null %>" name="name" />

				<aui:input autoFocus="<%= (spiDefinition != null) && (windowState.equals(WindowState.MAXIMIZED)) %>" name="description" />

			</aui:fieldset>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="true" extended="true" id="spiDefinitionRuntimeConfigurationPanel" persistState="true" title="spi-configurations">
			<liferay-ui:panel collapsible="true" extended="false" id="spiDefinitionSPIRuntimePanel" persistState="true" title="spi-runtime">
				<aui:fieldset>
					<aui:input helpMessage="maximum-worker-threads-help" label="maximum-worker-threads" max="<%= SPIConfigurationTemplate.getMaxThreads() %>" min="<%= SPIConfigurationTemplate.getMinThreads() %>" name="TypeSettingsProperties--max-threads--" required="true" type="number" value="<%= maxThreads %>" />

					<aui:input helpMessage="connector-port-help" label="connector-port" max="<%= SPIConfigurationTemplate.getConnectorPortMax() %>" min="<%= SPIConfigurationTemplate.getConnectorPortMin() %>" name="connectorPort" required="true" type="number" value="<%= connectorPort %>" />
				</aui:fieldset>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="true" extended="false" id="spiDefinitionSPIApplicationsPanel" persistState="true" title="spi-applications">
				<aui:fieldset>

					<%

					// Left list
					List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();

					for (String servletContextName : servletContextNames) {
						leftList.add(new KeyValuePair(servletContextName, servletContextName));
					}

					leftList = ListUtil.sort(leftList, new KeyValuePairComparator(false, true));

					// Right list

					List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

					Arrays.sort(servletContextNames);

					List<String> pluginServletContextNames = SPIAdminUtil.getPluginServletContextNames();

					for (String pluginServletContextName : pluginServletContextNames) {
						if ((Arrays.binarySearch(servletContextNames, pluginServletContextName) < 0) &&
							(!usedServletContextNames.contains(pluginServletContextName))) {

							rightList.add(new KeyValuePair(pluginServletContextName, pluginServletContextName));
						}
					}

					rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
					%>

					<liferay-ui:input-move-boxes
						leftBoxName="currentServletContextNames"
						leftList="<%= leftList %>"
						leftTitle="current"
						rightBoxName="availableServletContextNames"
						rightList="<%= rightList %>"
						rightTitle="available"
					/>
				</aui:fieldset>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="true" extended="false" id="spiDefinitionJavaRuntimePanel" persistState="true" title="java-runtime">
				<aui:fieldset>
					<aui:input helpMessage="jvm-arguments-help" label="jvm-arguments" name="jvmArguments" />
				</aui:fieldset>
			</liferay-ui:panel>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="true" defaultState="closed" extended="true" helpMessage="advanced-configurations-help" id="spiDefinitionAdvancedConfigurationPanel" persistState="false" title="advanced-configurations">
			<liferay-ui:panel collapsible="true" extended="false" id="spiDefinitionAdvancedSPIRuntimePanel" persistState="true" title="advanced-spi-runtime">
				<aui:fieldset>
					<aui:input helpMessage="java-executable-help" label="java-executable" name="TypeSettingsProperties--java-executable--" type="text" value="<%= javaExecutable %>" />

					<aui:input helpMessage="spi-ping-interval-help" label="spi-ping-interval" name="TypeSettingsProperties--ping-interval--" type="text" value="<%= pingInterval %>" />

					<aui:input helpMessage="spi-register-timeout-help" label="spi-register-timeout" name="TypeSettingsProperties--register-timeout--" type="text" value="<%= registerTimeout %>" />

					<aui:input helpMessage="spi-shutdown-timeout-help" label="spi-shutdown-timeout" name="TypeSettingsProperties--shutdown-timeout--" type="text" value="<%= shutdownTimeout %>" />
				</aui:fieldset>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="true" extended="false" helpMessage="spi-core-applications-help" id="spiDefinitionSPICoreApplicationsPanel" persistState="true" title="spi-core-applications">
				<aui:fieldset>

					<%

					// Left list
					List<KeyValuePair> leftList = new ArrayList<KeyValuePair>();

					for (String portletId : portletIds) {
						Portlet portlet = PortletLocalServiceUtil.getPortletById(
								portletId);

						leftList.add(new KeyValuePair(portletId, PortalUtil.getPortletTitle(portlet, portalServletContext, locale)));
					}

					leftList = ListUtil.sort(leftList, new KeyValuePairComparator(false, true));

					// Right list

					List<KeyValuePair> rightList = new ArrayList<KeyValuePair>();

					Arrays.sort(portletIds);

					List<Portlet> corePortlets = SPIAdminUtil.getCorePortlets();

					for (Portlet corePortlet : corePortlets) {
						if ((Arrays.binarySearch(portletIds, corePortlet.getPortletId()) < 0) &&
							(!usedPortletIds.contains(corePortlet))) {

							rightList.add(
									new KeyValuePair(
											corePortlet.getPortletId(),
											PortalUtil.getPortletTitle(corePortlet, portalServletContext, locale)));
						}
					}

					rightList = ListUtil.sort(rightList, new KeyValuePairComparator(false, true));
					%>

					<liferay-ui:input-move-boxes
						leftBoxName="currentPortletIds"
						leftList="<%= leftList %>"
						leftTitle="current"
						rightBoxName="availablePortletIds"
						rightList="<%= rightList %>"
						rightTitle="available"
					/>
				</aui:fieldset>
			</liferay-ui:panel>
		</liferay-ui:panel>
	</liferay-ui:panel-container>

	<aui:button-row>
		<aui:button name="save" type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />saveSPIDefinition',
		function() {
			document.<portlet:namespace />fm.<portlet:namespace />portletIds.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentPortletIds);
			document.<portlet:namespace />fm.<portlet:namespace />servletContextNames.value = Liferay.Util.listSelect(document.<portlet:namespace />fm.<portlet:namespace />currentServletContextNames);

			submitForm(document.<portlet:namespace />fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(pageContext, (spiDefinition == null) ? "add-spi" : name), currentURL);
%>