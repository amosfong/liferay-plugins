<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

Role role = RoleLocalServiceUtil.getRole(user.getCompanyId(), RoleConstants.SOCIAL_OFFICE_USER);

RowChecker organizationRoleChecker = InstanceFactory.newInstance(PortalClassLoaderUtil.getClassLoader(), "com.liferay.portlet.rolesadmin.search.OrganizationRoleChecker", new Object[] {RenderResponse.class, Role.class}, new Object[] {renderResponse, role});
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="searchFilter" value="<%= searchFilter %>" />
</liferay-portlet:renderURL>

<liferay-ui:search-container
	emptyResultsMessage="no-organization-was-found-that-is-a-member-of-social-office"
	iteratorURL="<%= portletURL %>"
	rowChecker="<%= organizationRoleChecker %>"
>

	<%
	List<Organization> organizations = null;
	int organizationsCount = 0;

	LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

	if (searchFilter.equals("current")) {
		if (Validator.isNotNull(role)) {
			params.put("organizationsRoles", new Long(role.getRoleId()));
		}

		organizations = OrganizationLocalServiceUtil.search(user.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, searchKeywords, null, null, null, params, searchContainer.getStart(), searchContainer.getEnd());
		organizationsCount = OrganizationLocalServiceUtil.searchCount(user.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, searchKeywords, null, null, null, params);
	}
	else {
		organizations = OrganizationLocalServiceUtil.search(user.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, searchKeywords, null, null, null, null, searchContainer.getStart(), searchContainer.getEnd());
		organizationsCount = OrganizationLocalServiceUtil.searchCount(user.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, searchKeywords, null, null, null, null);
	}
	%>

	<liferay-ui:search-container-results
		results="<%= organizations %>"
		total="<%= organizationsCount %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.Organization"
		escapedModel="<%= true %>"
		keyProperty="group.groupId"
		modelVar="organization"
	>
		<liferay-ui:search-container-column-text
			name="name"
			orderable="<%= true %>"
			property="name"
		/>

		<liferay-ui:search-container-column-text
			buffer="buffer"
			name="parent-organization"
		>

		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="type"
			orderable="<%= true %>"
			value="<%= LanguageUtil.get(pageContext, organization.getType()) %>"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>