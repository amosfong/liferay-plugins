<%--
/**
* Copyright  2000-2012 Liferay, Inc. All rights reserved.
*
* This file is part of Liferay Social Office. Liferay Social Office is free
* software: you can redistribute it and/or modify it under the terms of the GNU
* Affero General Public License as published by the Free Software Foundation,
* either version 3 of the License, or (at your option) any later version.
*
* Liferay Social Office is distributed in the hope that it will be useful, but
* WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
* FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
* for more details.
*
* You should have received a copy of the GNU General Public License along with
* Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
*/
--%>

<%@ include file="/init.jsp" %>

<%
Group group = layout.getGroup();
Group userGroup = user.getGroup();

String keywords = ParamUtil.getString(request, "keywords");

String tabs1 = ParamUtil.getString(request, "tabs1", "users");
String searchFilter = ParamUtil.getString(request, "searchFilter", "available");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
%>

<c:choose>
	<c:when test="<%= group.isControlPanel() %>">
		<span class="configuration-message">
			<%= LanguageUtil.get(pageContext, "give-users-social-office-access") %>
		</span>
	</c:when>
	<c:otherwise>
		<span class="configuration-message">
			<%= LanguageUtil.get(pageContext, "almost-done-now-its-time-to-figure-out-who-to-give-social-office-to") %>
		</span>
	</c:otherwise>
</c:choose>

<liferay-ui:tabs
	names="users,organizations,user-groups"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<aui:form action="<%= portletURL %>" method="post" name="fm">
	<aui:input name="addIds" type="hidden" />
	<aui:input name="removeIds" type="hidden" />

	<aui:input label="" name="keywords" size="30" value="<%= HtmlUtil.escape(keywords) %>" />
	<aui:button type="submit" value="search" />

	<div id="filterRadioButtons">
		<span class="filter-title"><%= LanguageUtil.get(pageContext, "view") %>&#58;</span>
		<aui:input checked='<%= searchFilter.equals("available") %>' label="available" name="searchFilter" type="radio" value="available" />
		<aui:input checked='<%= searchFilter.equals("current") %>' label="current" name="searchFilter" type="radio" value="current" />
	</div>

	<c:choose>
		<c:when test='<%= tabs1.equals("users") %>'>
			<liferay-util:include page="/configurations/assign_so_users.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("organizations") %>'>
			<liferay-util:include page="/configurations/assign_so_organizations.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("user-groups") %>'>
			<liferay-util:include page="/configurations/assign_so_user_groups.jsp" servletContext="<%= application %>" />
		</c:when>
	</c:choose>
</aui:form>

<div class="so-welcome-setup">
	<c:if test='<%= !group.isControlPanel() && tabs1.equals("users") %>'>
		<div id="addAllUsers">
			<aui:input label="give-every-new-liferay-portal-user-access-to-social-office-can-be-configured-later" name="" type="checkbox" value="" />
		</div>
	</c:if>

	<%
	String taglibOnClickFinished = renderResponse.getNamespace() + "updateRole(true)";
	String taglibOnClickSave = renderResponse.getNamespace() + "updateRole(false)";
	%>

	<aui:button-row>
		<aui:button onClick="<%= taglibOnClickSave %>" value="save" />
		<c:if test="<%= !group.isControlPanel() %>">
			<aui:button onClick="<%= taglibOnClickFinished %>" value="finish" />
		</c:if>
	</aui:button-row>
</div>

<liferay-portlet:actionURL portletName="<%= PortletKeys.SITE_REDIRECTOR %>" var="dashboardURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
	<portlet:param name="struts_action" value="/my_sites/view" />
	<portlet:param name="groupId" value="<%= String.valueOf(userGroup.getGroupId()) %>" />
	<portlet:param name="privateLayout" value="<%= Boolean.TRUE.toString() %>" />
</liferay-portlet:actionURL>

<script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateRole',
		function(finished) {
			var A = AUI();

			var addAllUsers = A.one('#addAllUsers input[type=checkbox]');

			if (addAllUsers && addAllUsers.get('checked')) {
				if (finished) {
					uri = '<portlet:actionURL name="addRoleAllUsers"><portlet:param name="redirect" value="<%= dashboardURL %>" /></portlet:actionURL>';
				}
				else {
					uri = '<portlet:actionURL name="addRoleAllUsers"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>';
				}
			}
			else {
				document.<portlet:namespace />fm.<portlet:namespace />addIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
				document.<portlet:namespace />fm.<portlet:namespace />removeIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

				if (<%= tabs1.equals("users") %>) {
					if (finished) {
						uri = '<portlet:actionURL name="updateUsersRole"><portlet:param name="redirect" value="<%= dashboardURL %>" /></portlet:actionURL>';
					}
					else {
						uri = '<portlet:actionURL name="updateUsersRole"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>';
					}
				}
				else {
					if (finished) {
						uri = '<portlet:actionURL name="updateGroupsRole"><portlet:param name="redirect" value="<%= dashboardURL %>" /></portlet:actionURL>';
					}
					else {
						uri = '<portlet:actionURL name="updateGroupsRole"><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:actionURL>';
					}
				}
			}

			submitForm(document.<portlet:namespace />fm, uri);
		},
		['liferay-util-list-fields']
	);
</script>

<aui:script use="aui-base">
	var radioButtons = A.all('#filterRadioButtons input[type=radio]');

	radioButtons.on(
		'change',
		function(event) {
			submitForm(document.<portlet:namespace />fm);
		}
	);
</aui:script>