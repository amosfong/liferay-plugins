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

<h3><liferay-ui:message arguments="2" key="registration-step-x-2" /></h3>

<span class="alert alert-info lcs-registration-info">
	<liferay-ui:message key="please-add-information-about-your-server" />
</span>

<portlet:renderURL var="viewLCSClusterNodeURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:renderURL>

<portlet:actionURL name="addLCSClusterNode" var="addLCSClusterNodeURL">
	<portlet:param name="redirect" value="<%= viewLCSClusterNodeURL %>" />
</portlet:actionURL>

<aui:form action="<%= addLCSClusterNodeURL %>" name="registrationFm">
	<aui:model-context model="<%= LCSClusterNode.class %>" />

	<%
	List<CorpEntryIdentifier> corpEntryIdentifiers = CorpEntryServiceUtil.getCorpEntryIdentifiers();
	%>

	<aui:select name="corpEntryId">

		<%
		long corpEntryId = ParamUtil.getLong(request, "corpEntryId");

		for (CorpEntryIdentifier corpEntryIdentifier : corpEntryIdentifiers) {
		%>

			<aui:option label="<%= corpEntryIdentifier.getName() %>" selected="<%= corpEntryId == corpEntryIdentifier.getCorpEntryId() %>" value="<%= corpEntryIdentifier.getCorpEntryId() %>" />

		<%
		}
		%>

	</aui:select>

	<%
	long corpEntryId = ParamUtil.getLong(request, "corpEntryId");

	if (corpEntryId == 0) {
		CorpEntryIdentifier corpEntryIdentifier = corpEntryIdentifiers.get(0);

		corpEntryId = corpEntryIdentifier.getCorpEntryId();
	}

	List<LCSClusterEntry> lcsClusterEntries = LCSClusterEntryServiceUtil.getCorpEntryLCSClusterEntries(corpEntryId);
	%>

	<aui:field-wrapper cssClass="environment-section" label="environment">
		<aui:input checked="<%= true %>" id="lcsDefaultCluster" inlineLabel="right" label="use-default-environment" name="environment" type="radio" value="0" />

		<span class="lcs-helper-message">
			<liferay-ui:message key="you-will-be-able-to-edit-it-on-your-cloud-dashboard" />
		</span>

		<aui:input id="lcsClusterEntry" inlineLabel="right" label='<%= lcsClusterEntries.isEmpty() ? "there-are-no-environments-created-yet" : "choose-an-environment" %>' name="environment" type="radio" value='<%= lcsClusterEntries.isEmpty() ? "1" : "2" %>' />

		<span class="lcs-environment-input-wrapper" id="<portlet:namespace />environmentInputWrapper">
			<c:choose>
				<c:when test="<%= lcsClusterEntries.size() == 1 %>">

					<%
					LCSClusterEntry lcsClusterEntry = lcsClusterEntries.get(0);
					%>

					<aui:input disabled="<%= true %>" id="lcsClusterEntryId" name="lcsClusterEntryId" type="hidden" value="<%= lcsClusterEntry.getLcsClusterEntryId() %>" />

					<aui:input disabled="<%= true %>" label="" name="lcsClusterEntryName" type="text" value="<%= lcsClusterEntry.getName() %>" />
				</c:when>
				<c:when test="<%= lcsClusterEntries.size() > 1 %>">

					<%
					int lcsClusterEntryMethod = ParamUtil.getInteger(request, "lcsClusterEntryMethod");
					%>

					<aui:select disabled="<%= lcsClusterEntryMethod != 2 %>" id="lcsClusterEntryId" label="" name="lcsClusterEntryId">

						<%
						long lcsClusterEntryId = ParamUtil.getLong(request, "lcsClusterEntryId");

						for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
						%>

							<aui:option label="<%= lcsClusterEntry.getName() %>" selected="<%= lcsClusterEntryId == lcsClusterEntry.getLcsClusterEntryId() %>" value="<%= lcsClusterEntry.getLcsClusterEntryId() %>" />

						<%
						}
						%>

					</aui:select>
				</c:when>
			</c:choose>
		</span>

		<span class="disabled lcs-helper-message" id="<portlet:namespace />addEntryLink">
			<liferay-ui:message arguments="javascript:" key="do-you-want-to-add-a-new-environment" />
		</span>
	</aui:field-wrapper>

	<aui:input name="name" />

	<aui:field-wrapper label="portal-key">
		<%= KeyGeneratorUtil.getKey() %>
	</aui:field-wrapper>

	<aui:input name="location" />

	<aui:input name="description" />

	<aui:input checked="<%= true %>" disabled="<%= !ClusterExecutorUtil.isEnabled() %>" id="registerAllClusterNodes" label="register-all-nodes-of-this-cluster" name="registerAllClusterNodes" type="checkbox" />

	<portlet:actionURL name="resetCredentials" var="resetCredentialsURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:actionURL>

	<aui:button-row>
		<aui:button cssClass="btn-info" href="<%= resetCredentialsURL.toString() %>" name="previous" value="previous" />

		<aui:button cssClass="btn-success" disabled="<%= true %>" name="register" type="submit" value="register" />
	</aui:button-row>
</aui:form>

<aui:script use="aui-base,liferay-cloud-services">
	new Liferay.Portlet.LCS(
		{
			addLCSClusterEntryURL: '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/add_lcs_cluster_entry.jsp" /></portlet:renderURL>',
			namespace: '<portlet:namespace />',
			portletId: '<%= portletDisplay.getId() %>',
			serveCorpEntryURL: '<portlet:resourceURL id="serveCorpEntry" />',
			serveLCSClusterEntryURL: '<portlet:resourceURL id="serveLCSClusterEntry" />'
		}
	);
</aui:script>