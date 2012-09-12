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
String tabs1 = ParamUtil.getString(request, "tabs1", "user-groups");

String searchFilter = ParamUtil.getString(request, "searchFilter");
String searchKeywords = DAOParamUtil.getLike(request, "keywords");

Role role = RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

RowChecker userGroupRoleChecker = InstanceFactory.newInstance(PortalClassLoaderUtil.getClassLoader(), "com.liferay.portlet.rolesadmin.search.UserGroupRoleChecker", new Object[] {RenderResponse.class, Role.class}, new Object[] {renderResponse, role});
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="searchFilter" value="<%= searchFilter %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="no-user-group-was-found-that-is-a-member-of-social-office"
	iteratorURL="<%= portletURL %>"
	rowChecker="<%= userGroupRoleChecker %>"
>

	<%
	List<UserGroup> userGroups = null;
	int userGroupsCount = 0;

	LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

	if (searchFilter.equals("current")) {
		if (Validator.isNotNull(role)) {
			params.put("userGroupsRoles", new Long(role.getRoleId()));
		}

		userGroups = UserGroupLocalServiceUtil.search(user.getCompanyId(), searchKeywords, params, searchContainer.getStart(), searchContainer.getEnd(), (OrderByComparator)null);
		userGroupsCount = UserGroupLocalServiceUtil.searchCount(user.getCompanyId(), searchKeywords, params);
	}
	else {
		userGroups = UserGroupLocalServiceUtil.search(user.getCompanyId(), searchKeywords, null, searchContainer.getStart(), searchContainer.getEnd(), (OrderByComparator)null);
		userGroupsCount = UserGroupLocalServiceUtil.searchCount(user.getCompanyId(), searchKeywords, null);
	}
	%>

	<liferay-ui:search-container-results
		results="<%= userGroups %>"
		total="<%= userGroupsCount %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.UserGroup"
		escapedModel="<%= true %>"
		keyProperty="group.groupId"
		modelVar="userGroup"
	>
		<liferay-ui:search-container-column-text
			name="name"
			orderable="<%= true %>"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			name="description"
			orderable="<%= true %>"
			property="description"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>