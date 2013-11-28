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
boolean pending = HandshakeManagerUtil.isPending();
boolean ready = HandshakeManagerUtil.isReady();

LCSClusterNode lcsClusterNode = LCSClusterNodeServiceUtil.getLCSClusterNode(KeyGeneratorUtil.getKey());

LCSClusterEntry lcsClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(lcsClusterNode.getLcsClusterEntryId());

CorpEntryIdentifier corpEntryIdentifier = new CorpEntryIdentifier();

for (CorpEntryIdentifier currentCorpEntryIdentifier : CorpEntryServiceUtil.getCorpEntryIdentifiers()) {
	if (currentCorpEntryIdentifier.getCorpEntryId() == lcsClusterEntry.getCorpEntryId()) {
		corpEntryIdentifier = currentCorpEntryIdentifier;

		break;
	}
}
%>

<div id="lcs-server-info">
	<h4><liferay-ui:message key="corp-entry-id" /></h4>

	<dl>
		<dd>
			<%= HtmlUtil.escape(corpEntryIdentifier.getName()) %>
		</dd>
	</dl>

	<h4><liferay-ui:message key="environment" /></h4>

	<dl>
		<dd>
			<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>
		</dd>
	</dl>

	<h4><liferay-ui:message key="server" /></h4>

	<dl>
		<dt>
			<liferay-ui:message key="name" />
		</dt>
		<dd>
			<%= HtmlUtil.escape(lcsClusterNode.getName()) %>
		</dd>
		<dt>
			<liferay-ui:message key="portal-version" />
		</dt>
		<dd>
			<%= lcsClusterNode.getBuildNumber() %>
		</dd>
		<dt>
			<liferay-ui:message key="description" />
		</dt>
		<dd>
			<%= HtmlUtil.escape(lcsClusterNode.getDescription()) %>
		</dd>
		<dt>
			<liferay-ui:message key="location" />
		</dt>
		<dd>
			<%= HtmlUtil.escape(lcsClusterNode.getLocation()) %>
		</dd>
	</dl>

	<c:if test="<%= ClusterExecutorUtil.isEnabled() %>">
		<dl>
			<dt>
				<liferay-ui:message key="nodes" />
			</dt>
			<dd>

				<%
				for (ClusterNode clusterNode : ClusterExecutorUtil.getClusterNodes()) {
				%>

					<%= clusterNode.getClusterNodeId() %><br />

				<%
				}
				%>

			</dd>
		</dl>
	</c:if>

	<%
	String lcsPortalURL = "http://" + PortletProps.get("osb.lcs.portlet.host.name");

	String lcsPortalPort = PortletProps.get("osb.lcs.portlet.host.port");

	if (!lcsPortalPort.equals("80")) {
		lcsPortalURL = lcsPortalURL + ":" + lcsPortalPort;
	}
	%>

	<div class="lcs-button-container">
		<a class="lcs-portal-link" href="<%= lcsPortalURL %>"><liferay-ui:message key="cloud-dashboard" /></a>
	</div>
</div>

<div id="lcs-connection-status">
	<h4><liferay-ui:message key="connection-status" /></h4>

	<div class="<%= ready ? StringPool.BLANK : "hide" %> lcs-msg" id="lcs-msg-registered">
		<liferay-ui:message key="this-liferay-instance-is-registered-and-synchronized-with-liferay-cloud-services" />

		<span class="lcs-msg-icon"></span>
	</div>

	<div class="<%= (!ready && !pending) ? StringPool.BLANK : "hide" %> lcs-msg" id="lcs-msg-disconnected">
		<liferay-ui:message key="this-liferay-instance-is-registered-but-not-connected-and-not-synchronized-with-liferay-cloud-services" />
		<span class="lcs-msg-icon"></span>
	</div>

	<div class="<%= (!ready && pending) ? StringPool.BLANK : "hide" %> lcs-msg" id="lcs-msg-pending">
		<liferay-ui:message key="this-liferay-instance-is-synchronizing-with-liferay-cloud-services" />
		<span class="lcs-msg-icon"></span>
	</div>

	<c:if test="<%= !pending %>">

		<%
		Map<String, String> lcsConnectionMetadata = HandshakeManagerUtil.getLCSConnectionMetadata();
		%>

		<div class="lcs-separator"></div>

		<dl>
			<dt>
				<liferay-ui:message key="heartbeat-interval" />
			</dt>
			<dd>
				<%= Time.getDuration(GetterUtil.getLong(lcsConnectionMetadata.get("heartbeatInterval"))) %>
			</dd>
			<dt>
				<liferay-ui:message key="message-task-interval" />
			</dt>
			<dd>
				<%= Time.getDuration(GetterUtil.getLong(lcsConnectionMetadata.get("messageTaskInterval"))) %>
			</dd>
			<dt>
				<liferay-ui:message key="metrics-task-interval" />
			</dt>
			<dd>
				<%= Time.getDuration(GetterUtil.getLong(lcsConnectionMetadata.get("jvmMetricsTaskInterval"))) %>
			</dd>

			<c:if test='<%= lcsConnectionMetadata.get("messageTaskTime") != null %>'>
				<dt>
					<liferay-ui:message key="last-message-received" />
				</dt>
				<dd>

					<%
					Date date = new Date(GetterUtil.getLong(lcsConnectionMetadata.get("messageTaskTime")));
					%>

					<%= dateFormatDateTime.format(date) %>
				</dd>
			</c:if>

			<c:if test="<%= !pending && HandshakeManagerUtil.isReady() %>">
				<dt>
					<liferay-ui:message key="connection-uptime" />
				</dt>
				<dd>

					<%
					String handshakeTime = lcsConnectionMetadata.get("handshakeTime");
					%>

					<c:if test="<%= handshakeTime != null %>">
						<%= Time.getDuration(System.currentTimeMillis() - GetterUtil.getLong(handshakeTime)) %>
					</c:if>
				</dd>
			</c:if>
		</dl>

		<div class="lcs-button-container">
			<c:if test="<%= !pending && !HandshakeManagerUtil.isReady() %>">
				<liferay-portlet:actionURL name="start" var="startURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</liferay-portlet:actionURL>

				<aui:button href="<%= startURL %>" type="submit" value="start" />
			</c:if>
			<c:if test="<%= !pending && HandshakeManagerUtil.isReady() %>">
				<liferay-portlet:actionURL name="stop" var="stopURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</liferay-portlet:actionURL>

				<aui:button href="<%= stopURL %>" type="submit" value="stop" />
			</c:if>

			<liferay-portlet:actionURL name="resetCredentials" var="resetCredentialsURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</liferay-portlet:actionURL>

			<aui:button href="<%= resetCredentialsURL %>" type="submit" value="reset-credentials" />
		</div>
	</c:if>
</div>