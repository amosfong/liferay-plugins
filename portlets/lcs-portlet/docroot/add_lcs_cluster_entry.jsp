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
long corpEntryId = ParamUtil.getLong(request, "corpEntryId");

CorpEntryIdentifier corpEntryIdentifier = new CorpEntryIdentifier();

for (CorpEntryIdentifier currentCorpEntryIdentifier : CorpEntryServiceUtil.getCorpEntryIdentifiers()) {
	if (currentCorpEntryIdentifier.getCorpEntryId() == corpEntryId) {
		corpEntryIdentifier = currentCorpEntryIdentifier;
	}
}
%>

<h3><liferay-ui:message arguments="<%= corpEntryIdentifier.getName() %>" key="create-new-environment-for-x" /></h3>

<portlet:actionURL name="addLCSClusterEntry" var="addLCSClusterEntryURL" />

<aui:form action="<%= addLCSClusterEntryURL %>" name="fm">
	<aui:model-context model="<%= LCSClusterEntry.class %>" />

	<aui:input name="corpEntryId" type="hidden" value="<%= String.valueOf(corpEntryId) %>" />

	<aui:input autoFocus="<%= true %>" name="name" />

	<aui:input name="location" />

	<aui:input name="description" />

	<aui:button-row>
		<aui:button cssClass="btn-success" disabled="<%= true %>" name="create" type="submit" value="create" />
	</aui:button-row>
</aui:form>