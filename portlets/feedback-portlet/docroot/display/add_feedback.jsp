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

<div id="<portlet:namespace />errorMessage"></div>

<portlet:actionURL name="updateFeedback" var="updateFeedbackURL" />

<aui:form action="<%= updateFeedbackURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="groupId" type="hidden" value="<%= String.valueOf(groupId) %>" />
	<aui:input name="mbCategoryId" type="hidden" value="<%= String.valueOf(mbCategoryId) %>" />
	<aui:input id="type" name="type" type="hidden" value="" />

	<div class="feedback-container">
		<div class="start" id="<portlet:namespace />start">
			<h2><liferay-ui:message key="social-office-is" /></h2>

			<aui:button cssClass="btn btn-success feedback-positive" icon="icon-thumbs-up" type="button" value='<%= LanguageUtil.get(pageContext, "positive") %>' />

			<aui:button cssClass="btn btn-danger feedback-negative" icon="icon-thumbs-down" type="button" value='<%= LanguageUtil.get(pageContext, "negative") %>' />
		</div>

		<div class="feedback hide" id="<portlet:namespace />feedback">
			<h3><span class="title"></span></h3>

			<p class="subject"></p>

			<aui:input cssClass="body" id="body" label="" name="body" required="<%= true %>" type="textarea" />

			<aui:input id="anonymous" label="Anonymous" name="anonymous" type="checkbox" />

			<aui:button cssClass="btn btn-primary send-feedback" type="button" value='<%= LanguageUtil.get(pageContext, "send-feedback") %>' />
		</div>

		<div class="confirmation hide" id="<portlet:namespace />confirmation">
			<h3>
				<liferay-ui:message key="your-feedback-has-been-submitted" />
			</h3>

			<p>
				<liferay-ui:message key="we-appreciate-your-time-and-value-your-feedback" />
			</p>
		</div>
	</div>
</aui:form>

<aui:script use="aui-base,aui-io-request-deprecated,aui-loading-mask-deprecated">
	var form = A.one('#<portlet:namespace />fm');

	var displayFeedBack = function(feedbackType) {
		var title = form.one('.feedback-container .feedback .title');
		var subject = form.one('.feedback-container .feedback .subject');
		var type = form.one('#<portlet:namespace />type');

		type.val(feedbackType);

		if (feedbackType == '<%= FeedbackConstant.TYPE_POSITIVE %>') {
			title.setHTML('<liferay-ui:message key="we-are-glad-you-like-it" />');
			subject.setHTML('<%= FeedbackUtil.getFeedbackSubject(FeedbackConstant.TYPE_POSITIVE) %>');
		}
		else if (feedbackType == '<%= FeedbackConstant.TYPE_NEGATIVE %>') {
			title.setHTML('<liferay-ui:message key="help-us-fix-it" />');
			subject.setHTML('<%= FeedbackUtil.getFeedbackSubject(FeedbackConstant.TYPE_NEGATIVE) %>');
		}

		var start = form.one('#<portlet:namespace />start');

		if (start) {
			start.hide();
		}

		var feedback = form.one('#<portlet:namespace />feedback');

		if (feedback) {
			feedback.show();

			var body = form.one('#<portlet:namespace />body');

			body.focus();
		}
	}

	var feedbackNegative = form.one('.feedback-container .start .feedback-negative');

	if (feedbackNegative) {
		feedbackNegative.on(
			'click',
			function(event) {
				displayFeedBack('<%= FeedbackConstant.TYPE_NEGATIVE %>');
			}
		);
	}

	var feedbackPositive = form.one('.feedback-container .start .feedback-positive');

	if (feedbackPositive) {
		feedbackPositive.on(
			'click',
			function(event) {
				displayFeedBack('<%= FeedbackConstant.TYPE_POSITIVE %>');
			}
		);
	}

	var sendFeedback = form.one('.feedback-container .feedback .send-feedback');

	if (sendFeedback) {
		sendFeedback.on(
			'click',
			function(event) {
				var errorMessage = A.one('#<portlet:namespace />errorMessage');
				var feedback = A.one('#<portlet:namespace />feedback');
				var body = A.one('#<portlet:namespace />body');

				if (errorMessage) {
					if (body.val().trim().length == 0) {
						body.focus();

						return;
					}

					errorMessage.setHTML('');
				}

				var loadingMask = new A.LoadingMask(
					{
						'strings.loading': '<%= UnicodeLanguageUtil.get(pageContext, "sending-feedback") %>',
						target: A.one('.feedback-portlet .feedback-container')
					}
				);

				loadingMask.show();

				A.io.request(
					form.getAttribute('action'),
					{
						after: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								if (response && (response.success == 'true')) {
									var confirmation = A.one('#<portlet:namespace />confirmation');

									if (feedback) {
										feedback.hide();
									}

									if (confirmation) {
										confirmation.show();
									}
								}
								else {
									if (errorMessage) {
										errorMessage.setHTML('<span class="alert alert-error"><liferay-ui:message key="your-feedback-was-not-saved-successfully" /></span>');
									}
								}

								loadingMask.hide();
							}
						},
						dataType: 'JSON',
						form: {
							id: form.getDOM()
						},
						method: 'POST'
					}
				);
			}
		);
	}
</aui:script>