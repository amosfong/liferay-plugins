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

<div class="portlet-msg-info">
	<liferay-ui:message key="configure-forum-feedback-category" />
</div>

<c:choose>
	<c:when test="<%= !permissionChecker.isOmniadmin() %>">
		<liferay-ui:message key="setup-is-only-available-to-admin-user" />
	</c:when>
	<c:otherwise>

		<%
		String redirect = ParamUtil.getString(request, "redirect");

		List<Group> groups = new ArrayList<Group>();

		for (long companyId : PortalUtil.getCompanyIds()) {
			List<Group> curGroups = GroupLocalServiceUtil.getGroups(companyId, Group.class.getName(), 0);

			for (Group group : curGroups) {
				if (SocialOfficeServiceUtil.isSocialOfficeGroup(group.getGroupId()) && group.isRegularSite()) {
					String groupName = group.getName();

					if (Validator.isNull(groupName)) {
						continue;
					}

					if ((group.getType() == GroupConstants.TYPE_SITE_PRIVATE) || (group.getType() == GroupConstants.TYPE_SITE_RESTRICTED) || (group.getType() == GroupConstants.TYPE_SITE_OPEN)) {
						groups.add(group);
					}
				}
			}
		}
		%>

		<liferay-portlet:actionURL name="updateConfigurations" var="updateConfigurationsURL" />

		<aui:form action="<%= updateConfigurationsURL %>" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

			<c:choose>
				<c:when test="<%= groups.size() == 0 %>">
					<liferay-ui:message key="no-group-exists" />
				</c:when>
				<c:otherwise>
					<aui:fieldset cssClass="scope-section-holder">
						<liferay-ui:panel-container extended="<%= true %>" id="groupsPanelContainer" persistState="<%= true %>">
							<div id="<portlet:namespace />groupErrorMessage"></div>

							<br />

							<aui:select id="groupId" label="Group" name="preferences--groupId--" onChange='<%= renderResponse.getNamespace() + "selectGroupCategory(this.value, 0)" %>'>
								<aui:option label="" value="0" />

								<%
								for (Group group: groups) {
								%>

									<aui:option label="<%= group.getName() %>" selected="<%= group.getGroupId() == groupId %>" value="<%= group.getGroupId() %>" />

								<%
								}
								%>

							</aui:select>

							<div id="<portlet:namespace />categoryErrorMessage"></div>

							<br />

							<aui:select id="categoryId" label="Category" name="preferences--categoryId--">
								<aui:option label="" value="0" />
							</aui:select>
						</liferay-ui:panel-container>
					</aui:fieldset>
				</c:otherwise>
			</c:choose>

			<aui:button-row>
				<aui:button onClick='<%= renderResponse.getNamespace() + "updateConfigurations()" %>' value="save" />
			</aui:button-row>
		</aui:form>

		<aui:script>
			AUI().ready(
				'aui-base',
				function(A) {
					<portlet:namespace />selectGroupCategory(<%= groupId %>, <%= categoryId %>);
				}
			);

			function <portlet:namespace />removeErrorMessage() {
				var A = AUI();

				var groupErrorMsg = A.one('#<portlet:namespace />groupErrorMessage');

				var categoryErrorMsg = A.one('#<portlet:namespace />categoryErrorMessage');

				if (groupErrorMsg) {
					groupErrorMsg.html('');
				}

				if (categoryErrorMsg) {
					categoryErrorMsg.html('');
				}
			}

			function <portlet:namespace />updateDropdownList(selectId, selectData, selectedVal) {
				var A = AUI();

				var selectElement = A.one('#' + selectId);

				var selectOptions = [];

				selectOptions.push('<option value="0"></option>');

				if (selectData) {
					for (var i = 0; i < selectData.length; i++) {
						var mbCategoryId = selectData[i].mbCategoryId;
						var mbCategoryName = selectData[i].mbCategoryName;

						selectOptions.push('<option value="' + mbCategoryId + '">' + mbCategoryName + '</option>');
					}
				}

				selectOptions = selectOptions.join('');

				selectElement.empty();
				selectElement.append(selectOptions);
				selectElement.val(selectedVal);
			}

			Liferay.provide(
				window,
				'<portlet:namespace />selectGroupCategory',
				function(groupId, categoryId) {
					var A = AUI();

					<portlet:namespace />removeErrorMessage();

					if (groupId <= 0) {
						A.one("#<portlet:namespace />categoryId").empty();

						return;
					}

					A.io.request(
						'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="getMBCategories" />',
						{
							data: {
								<portlet:namespace />groupId: groupId
							},
							dataType: 'json',
							method: 'post',
							on: {
								success: function(event, id, obj) {
									var response = this.get('responseData');

									<portlet:namespace />updateDropdownList("<portlet:namespace />categoryId", response["mbCategories"], categoryId);
								}
							}
						}
					);
				},
				['aui-io']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />updateConfigurations',
				function() {
					var A = AUI();

					var groupEl = A.one('#<portlet:namespace />groupId');

					var groupErrorMsg = A.one('#<portlet:namespace />groupErrorMessage');

					var categoryEl = A.one('#<portlet:namespace />categoryId');

					var categoryErrorMsg = A.one('#<portlet:namespace />categoryErrorMessage');

					if (groupErrorMsg) {
						if (groupEl.val() == '0') {
								groupErrorMsg.html('<span class="alert alert-error"><liferay-ui:message key="please-select-a-valid-group" /></span>');

								return;
						}
						else {
							groupErrorMsg.html('');
						}
					}

					if (categoryErrorMsg) {
						if (categoryEl.val() == '0') {
							categoryErrorMsg.html('<span class="alert alert-error"><liferay-ui:message key="please-select-a-valid-category" /></span>');

							return;
						}
						else {
							categoryErrorMsg.html('');
						}
					}

					submitForm(document.<portlet:namespace />fm);
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>