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
String redirect = ParamUtil.getString(request, "redirect");
%>

<div class="portlet-msg-info">
	<liferay-ui:message key="configure-forum-feedback-category" />
</div>

<c:choose>
	<c:when test="<%= !permissionChecker.isOmniadmin() %>">
		<liferay-ui:message key="setup-is-only-available-to-admin-user" />
	</c:when>
	<c:otherwise>
		<liferay-portlet:actionURL name="updateConfigurations" var="updateConfigurationsURL" />

		<aui:form action="<%= updateConfigurationsURL %>" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

			<%
			List<Group> groups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), Group.class.getName(), 0);
			%>

			<c:choose>
				<c:when test="<%= groups.size() == 0 %>">
					<liferay-ui:message key="no-group-exists" />
				</c:when>
				<c:otherwise>
					<div id="<portlet:namespace />groupErrorMessage"></div>

					<aui:select id="groupId" label="Group" name="preferences--groupId--">
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

					<aui:select id="categoryId" label="Category" name="preferences--categoryId--">
						<aui:option label="" value="0" />
					</aui:select>
				</c:otherwise>
			</c:choose>

			<aui:button-row>
				<aui:button cssClass="save-configurations" type="button" value="save" />
			</aui:button-row>
		</aui:form>

		<aui:script use="aui-base,aui-io-request-deprecated">
			var form = A.one('#<portlet:namespace />fm');

			var groupId = form.one('#<portlet:namespace />groupId');
			var groupErrorMessage = form.one('#<portlet:namespace />groupErrorMessage');
			
			var categoryId = form.one('#<portlet:namespace />categoryId');
			var categoryErrorMessage = form.one('#<portlet:namespace />categoryErrorMessage');
			
			var removeErrorMessage = function() {
				if (groupErrorMessage) {
					groupErrorMessage.setHTML('');
				}

				if (categoryErrorMessage) {
					categoryErrorMessage.setHTML('');
				}
			};

			var updateCategories = function(mbCategories, selectedCategoryId) {
				var selectOptions = [];

				selectOptions.push('<option value="0"></option>');

				if (mbCategories) {
					for (var i = 0; i < mbCategories.length; i++) {
						var mbCategoryId = mbCategories[i].mbCategoryId;
						var mbCategoryName = mbCategories[i].mbCategoryName;

						selectOptions.push('<option value="' + mbCategoryId + '">' + mbCategoryName + '</option>');
					}
				}

				selectOptions = selectOptions.join('');

				categoryId.empty();

				categoryId.append(selectOptions);

				categoryId.val(selectedCategoryId);
			}

			var getGroupCategories = function(selectedGroupId, selectedCategoryId) {
				removeErrorMessage();

				if (selectedGroupId <= 0) {
					categoryId.empty();

					return;
				}

				A.io.request(
					'<liferay-portlet:resourceURL id="getMBCategories" />',
					{
						data: {
							<portlet:namespace />groupId: selectedGroupId
						},
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								updateCategories(response["mbCategories"], selectedCategoryId);
							}
						}
					}
				);
			};

			A.on(
				'domready',
				function() {
					getGroupCategories(<%= groupId %>, <%= categoryId %>);
				}
			);

			groupId.on(
				'change',
				function(event) {
					getGroupCategories(groupId.val(), 0);	
				}
			)

			var saveConfigurations = form.one('.save-configurations');

			if (saveConfigurations) {
				saveConfigurations.on(
					'click',
					function(event) {
						if (groupErrorMessage) {
							if (groupId.val() == '0') {
								groupErrorMessage.setHTML('<span class="alert alert-error"><liferay-ui:message key="please-select-a-valid-group" /></span>');

								return;
							}
						}

						if (categoryErrorMessage) {
							if (categoryId.val() == '0') {
								categoryErrorMessage.setHTML('<span class="alert alert-error"><liferay-ui:message key="please-select-a-valid-category" /></span>');

								return;
							}
						}

						removeErrorMessage();

						submitForm(document.<portlet:namespace />fm);	
					}
				)
			}
		</aui:script>
	</c:otherwise>
</c:choose>