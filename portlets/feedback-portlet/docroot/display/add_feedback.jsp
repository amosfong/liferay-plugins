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

<c:choose>
	<c:when test="<%= (groupId == 0) || (categoryId == 0) %>">
		<liferay-ui:message key="please-setup-configuration-first" />
	</c:when>
	<c:otherwise>
		<div id="<portlet:namespace />errorMessage"></div>

		<portlet:actionURL name="updateFeedback" var="updateFeedbackURL" />

		<aui:form action="<%= updateFeedbackURL %>" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
			<aui:input name="groupId" type="hidden" value="<%= String.valueOf(groupId) %>" />
			<aui:input name="categoryId" type="hidden" value="<%= String.valueOf(categoryId) %>" />
			<aui:input id="type" name="type" type="hidden" value="" />

			<div class="feedback">
				<div class="feedback-start" id="<portlet:namespace />feedback-start">
					<h2>Social Office is...</h2>

					<aui:button cssClass="btn btn-success" icon="icon-thumbs-up" onClick='<%= renderResponse.getNamespace() + "displayOption(1)" %>' type="button" value='<%= LanguageUtil.get(pageContext, "positive") %>' />

					<aui:button cssClass="btn btn-danger" icon="icon-thumbs-down" onClick='<%= renderResponse.getNamespace() + "displayOption(2)" %>' type="button" value='<%= LanguageUtil.get(pageContext, "negative") %>' />
				</div>
				<div class="feedback-positive" id="<portlet:namespace />feedback-positive">
					<h3><liferay-ui:message key="all-of-our-hard-word-has-paid-off" /></h3>
					<p><%= FeedbackUtil.getFeedbackSubject("positive") %></p>

					<aui:input cssClass="feedback-body" id="feedback-body-positive" label="" name="body-positive" type="textarea" />

					<aui:input id="anonymous" label="Anonymous" name="anonymous-positive" type="checkbox" />

					<br />

					<aui:button cssClass="btn btn-primary" onclick='<%= renderResponse.getNamespace() + "updateFeedback(1)" %>' type="button" value='<%= LanguageUtil.get(pageContext, "send-feedback") %>' />
				</div>
				<div class="feedback-negative" id="<portlet:namespace />feedback-negative">
					<h3><liferay-ui:message key="what-did-we-break" /></h3>
					<p><%= FeedbackUtil.getFeedbackSubject("negative") %></p>

					<aui:input cssClass="feedback-body" id="feedback-body-negative" label="" name="body-negative" type="textarea" />

					<aui:input id="anonymous" label="Anonymous" name="anonymous-negative" type="checkbox" />

					<br />
					<aui:button cssClass="btn btn-primary" onclick='<%= renderResponse.getNamespace() + "updateFeedback(2)" %>' type="button" value='<%= LanguageUtil.get(pageContext, "send-feedback") %>' />
				</div>
				<div class="feedback-confirm" id="<portlet:namespace />feedback-confirm">
					<h3><liferay-ui:message key="your-feedback-has-been-submitted" /></h3>
					<p><liferay-ui:message key="we-appreciate-your-time-and-value-your-feedback" /></p>
				</div>
			</div>
		</aui:form>

		<aui:script>
			function <portlet:namespace />displayOption(ind) {
				var A = AUI();

				var feedbackOption;

				var type = A.one('#<portlet:namespace />type');

				if (ind == 1) {
					feedbackOption = A.one('#<portlet:namespace />feedback-positive');
					type.val('positive');
				}
				else if (ind == 2) {
					feedbackOption = A.one('#<portlet:namespace />feedback-negative');
					type.val('negative');
				}

				if (feedbackOption) {
					feedbackOption.setStyle('display', 'block');
				}

				var feedbackStart = A.one('#<portlet:namespace />feedback-start');

				if (feedbackStart) {
					feedbackStart.setStyle('display', 'none');
				}
			}

			Liferay.provide(
				window,
				'<portlet:namespace />updateFeedback',
				function(ind) {
					var A = AUI();

					var form = A.one(document.<portlet:namespace />fm);

					var message = A.one('#<portlet:namespace />errorMessage');

					var feedbackForm;
					var feedbackBody;

					if (ind == 1) {
						feedbackForm = A.one('#<portlet:namespace />feedback-positive');
						feedbackBody = A.one('#<portlet:namespace />feedback-body-positive');
					}
					else if (ind == 2) {
						feedbackForm = A.one('#<portlet:namespace />feedback-negative');
						feedbackBody = A.one('#<portlet:namespace />feedback-body-negative');
					}

					if (message) {
						if (feedbackBody.val().trim().length == 0) {
							message.html('<span class="alert alert-error"><liferay-ui:message key="feedback-is-empty" /></span>');

							return;
						}
						else {
							message.html('');
						}
					}

					A.io.request(
						form.getAttribute('action'),
						{
							after: {
								success: function(event, id, obj) {
									var response = this.get('responseData');

									if (response && (response.success == 'true')) {
										var feedbackConfirm = A.one('#<portlet:namespace />feedback-confirm');

										if (feedbackForm) {
											feedbackForm.setStyle('display', 'none');
										}

										if (feedbackConfirm) {
											feedbackConfirm.setStyle('display', 'block');
										}
									}
									else {
										if (message) {
											message.html('<span class="alert alert-error"><liferay-ui:message key="feedback-is-not-saved-successfully" /></span>');
										}
									}
								}
							},
							dataType: 'JSON',
							form: {
								id: form.getDOM()
							},
							method: 'post'
						}
					);
				},
				['aui-base,aui-io-request-deprecated']
			);
		</aui:script>
	</c:otherwise>
</c:choose>