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
String tabs1 = ParamUtil.getString(request, "tabs1", "users");

String searchFilter = ParamUtil.getString(request, "searchFilter");
String searchKeywords = DAOParamUtil.getLike(request, "keywords");

Role role = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

RowChecker userRoleChecker = InstanceFactory.newInstance(PortalClassLoaderUtil.getClassLoader(), "com.liferay.portlet.rolesadmin.search.UserRoleChecker", new Object[] {RenderResponse.class, Role.class}, new Object[] {renderResponse, role});
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="searchFilter" value="<%= searchFilter %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="no-user-was-found-that-is-a-member-of-social-office"
	iteratorURL="<%= portletURL %>"
	rowChecker="<%= userRoleChecker %>"
>

	<%
	List<User> users = null;
	int usersCount = 0;

	LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

	if (searchFilter.equals("current")) {
		if (Validator.isNotNull(role)) {
			params.put("usersRoles", new Long(role.getRoleId()));
		}

		users = UserLocalServiceUtil.search(user.getCompanyId(), searchKeywords, WorkflowConstants.STATUS_APPROVED, params, searchContainer.getStart(), searchContainer.getEnd(), (OrderByComparator)null);
		usersCount = UserLocalServiceUtil.searchCount(user.getCompanyId(), searchKeywords, WorkflowConstants.STATUS_APPROVED, params);
	}
	else {
		users = UserLocalServiceUtil.search(user.getCompanyId(), searchKeywords, WorkflowConstants.STATUS_APPROVED, null, searchContainer.getStart(), searchContainer.getEnd(), (OrderByComparator)null);
		usersCount = UserLocalServiceUtil.searchCount(user.getCompanyId(), searchKeywords, WorkflowConstants.STATUS_APPROVED, null);
	}
	%>

	<liferay-ui:search-container-results
		results="<%= users %>"
		total="<%= usersCount %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="user2"
		rowIdProperty="screenName"
	>

		<liferay-ui:search-container-column-text
			name="name"
			property="fullName"
		/>

		<liferay-ui:search-container-column-text
			name="screen-name"
			orderable="<%= true %>"
			property="screenName"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>