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

<c:if test="<%= themeDisplay.isSignedIn() && !(BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) < 7)) && !BrowserSnifferUtil.isMobile(request) && (groupId != 0) && (mbCategoryId != 0) %>">
	<div class="feedback-bar" id="<portlet:namespace />feedbackBar">
		<i class="icon-bullhorn"></i>

		<%= StringUtil.toUpperCase(LanguageUtil.get(pageContext, "feedback")) %>
	</div>

	<aui:script use="aui-base">
		var feedbackBar = A.one('#<portlet:namespace />feedbackBar');

		if (feedbackBar) {
			feedbackBar.on(
				'click',
				function(event) {
					<portlet:renderURL var="addFeedbackURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="mvcPath" value="/display/add_feedback.jsp" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</portlet:renderURL>

					Liferay.Feedbacks.getPopup('<%= addFeedbackURL %>', '<portlet:namespace />Dialog', '<%= UnicodeLanguageUtil.get(pageContext, "add-feedback") %>', true, 800, 600);
				}
			)
		}
	</aui:script>
</c:if>