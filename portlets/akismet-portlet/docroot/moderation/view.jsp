<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
String tabs1 = ParamUtil.getString(request, "tabs1", "message-boards");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
portletURL.setWindowState(WindowState.MAXIMIZED);

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<liferay-ui:tabs
	names="message-boards,discussions"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("message-boards") %>'>
		<%@ include file="/moderation/message_boards.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/moderation/discussions.jspf" %>
	</c:otherwise>
</c:choose>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />deleteMBMessages',
		function() {
			var deleteMBMessages = true;

			var deleteMBMessageIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

			if (deleteMBMessageIds && confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-the-selected-messages") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />deleteMBMessageIds.value = deleteMBMessageIds;
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="deleteMBMessages"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />notSpam',
		function() {
			var notSpam = true;

			var notSpamMBMessageIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

			if (deleteMBMessageIds && confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-mark-the-selected-messages-as-not-spam") %>')) {
				document.<portlet:namespace />fm.<portlet:namespace />notSpamMBMessageIds.value = notSpamMBMessageIds;
				submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="markNotSpam"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>");
			}
		},
		['liferay-util-list-fields']
	);
</aui:script>