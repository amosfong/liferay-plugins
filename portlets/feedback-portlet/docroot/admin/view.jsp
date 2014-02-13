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
	<liferay-ui:message key="configure-feedback-message-board-category" />
</div>

<c:choose>
	<c:when test="<%= !permissionChecker.isOmniadmin() %>">
		<liferay-ui:message key="setup-is-only-available-for-administrators" />
	</c:when>
	<c:otherwise>
		<liferay-portlet:actionURL name="updateConfigurations" var="updateConfigurationsURL" />

		<aui:form action="<%= updateConfigurationsURL %>" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

			<%
			List<Group> groups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), Group.class.getName(), 0);
			%>

			<c:choose>
				<c:when test="<%= groups.isEmpty() %>">
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

					<div id="<portlet:namespace />mbCategoryErrorMessage"></div>

					<aui:select id="mbCategoryId" label="Category" name="preferences--mbCategoryId--">
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

			var groupErrorMessage = form.one('#<portlet:namespace />groupErrorMessage');
			var groupId = form.one('#<portlet:namespace />groupId');
			var mbCategoryErrorMessage = form.one('#<portlet:namespace />mbCategoryErrorMessage');
			var mbCategoryId = form.one('#<portlet:namespace />mbCategoryId');

			var getGroupCategories = function(selectedGroupId, selectedMBCategoryId) {
				removeErrorMessage();

				if (selectedGroupId <= 0) {
					mbCategoryId.empty();

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

								updateCategories(response["mbCategories"], selectedMBCategoryId);
							}
						}
					}
				);
			};

			var removeErrorMessage = function() {
				if (groupErrorMessage) {
					groupErrorMessage.setHTML('');
				}

				if (mbCategoryErrorMessage) {
					mbCategoryErrorMessage.setHTML('');
				}
			};

			var updateCategories = function(mbCategories, selectedMBCategoryId) {
				var selectOptions = [];

				selectOptions.push('<option value="0"></option>');

				if (mbCategories) {
					for (var i = 0; i < mbCategories.length; i++) {
						selectOptions.push('<option value="' + mbCategories[i].mbCategoryId + '">' + mbCategories[i].mbCategoryName + '</option>');
					}
				}

				selectOptions = selectOptions.join('');

				mbCategoryId.empty();

				mbCategoryId.append(selectOptions);

				mbCategoryId.val(selectedMBCategoryId);
			}

			A.on(
				'domready',
				function() {
					getGroupCategories(<%= groupId %>, <%= mbCategoryId %>);
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

						if (mbCategoryErrorMessage) {
							if (mbCategoryId.val() == '0') {
								mbCategoryErrorMessage.setHTML('<span class="alert alert-error"><liferay-ui:message key="please-select-a-valid-category" /></span>');

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