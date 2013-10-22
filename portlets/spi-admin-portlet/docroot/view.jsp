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
List<SPIDefinition> spiDefinitions = SPIDefinitionServiceUtil.getSPIDefinitions();

PortletURL portletURL = renderResponse.createRenderURL();
%>

<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>" />

<liferay-ui:search-container
	emptyResultsMessage="no-spi-definitions-are-defined"
	iteratorURL="<%= portletURL %>"
	total="<%= spiDefinitions.size() %>"
>
	<liferay-ui:search-container-results
		results="<%= ListUtil.subList(spiDefinitions, searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.resiliency.spi.model.SPIDefinition"
		modelVar="spiDefinition"
	>

		<portlet:renderURL var="rowURL">
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="mvcPath" value="/edit_spi_definition.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="spiDefinitionId" value="<%= String.valueOf(spiDefinition.getSpiDefinitionId()) %>" />
		</portlet:renderURL>

		<liferay-ui:search-container-column-jsp
			cssClass="spi-status-column"
			name="status"
			path="/spi_status.jsp"
		/>

		<liferay-ui:search-container-column-text
			cssClass="spi-name-column"
			href="<%= rowURL %>"
			name="name"
			value="<%= spiDefinition.getName() %>"
		/>

		<liferay-ui:search-container-column-text
			cssClass="spi-description-column"
			href="<%= rowURL %>"
			name="description"
			value="<%= spiDefinition.getDescription() %>"
		/>

		<liferay-ui:search-container-column-text
			cssClass="spi-applications-column"
			href="<%= rowURL %>"
			name="applications"
			value="<%= spiDefinition.getServletContextNames() %>"
		/>

		<liferay-ui:search-container-column-text
			cssClass="spi-connector-port-column"
			href="<%= rowURL %>"
			name="connector-port"
			value="<%= String.valueOf(spiDefinition.getConnectorPort()) %>"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/spi_definition_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>