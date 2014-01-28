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

<liferay-ui:error key="authenticationFailed" message="authentication-failed" />

<div id="lcsMainContainer">

	<c:choose>
		<c:when test="<%= LCSUtil.isCredentialsSet() %>">
			<c:choose>
				<c:when test="<%= LCSClusterNodeServiceUtil.isRegistered(KeyGeneratorUtil.getKey()) %>">
					<liferay-util:include page="/view_lcs_cluster_node.jsp" servletContext="<%= application %>" />
				</c:when>
				<c:otherwise>
					<liferay-util:include page="/register.jsp" servletContext="<%= application %>" />
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/login.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</div>