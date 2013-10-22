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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

SPIDefinition spiDefinition = (SPIDefinition)row.getObject();
%>

<strong class="label <%= SPIAdminConstants.getStatusCssClass(spiDefinition.getStatus()) %>">
	<liferay-ui:message key="<%= spiDefinition.getStatusLabel() %>" />
</strong>

<c:if test="<%= Validator.isNotNull(spiDefinition.getStatusMessage()) %>">
	<liferay-ui:icon-help message="<%= spiDefinition.getStatusMessage() %>" />
</c:if>