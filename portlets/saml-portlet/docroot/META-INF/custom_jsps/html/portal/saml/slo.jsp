<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
<%@ include file="/html/portal/init.jsp" %>

<%
JSONObject samlSloContext = (JSONObject)request.getAttribute("SAML_SLO_CONTEXT");

JSONArray samlSloRequestInfos = samlSloContext.getJSONArray("samlSloRequestInfos");
%>

<style type="text/css">
	.saml-sp .sso-complete {
		background-image: url(<%= themeDisplay.getPathThemeImages() %>/messages/success.png)
	}

	.saml-sp .sso-failed {
		background-image: url(<%= themeDisplay.getPathThemeImages() %>/messages/error.png)
	}

	.saml-sp .sso-no-support {
		background-image: url(<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png)
	}

	.saml-sp .sso-progress {
		background-image: url(<%= themeDisplay.getPathThemeImages() %>/aui/loading_indicator.gif)
	}

	.saml-sp .sso-timed-out {
		background-image: url(<%= themeDisplay.getPathThemeImages() %>/common/time.png)
	}

	.saml-sp img {
		height: 16px;
		margin: 0 5px;
		width: 16px;
	}

	.saml-sp iframe {
		height: 0;
		left: -9999px;
		position: absolute;
		width: 0;
	}
</style>

<h3>
	<liferay-ui:message key="signing-out-from-services" />
</h3>

<div id="samlSloOutput"></div>

<div id="samlSloContinue"></div>

<noscript>
	<div class="portlet-msg-info">
		<liferay-ui:message key="your-browser-does-not-support-javascript-so-you-need-to-sign-out-manually-from-each-service-provider" />
	</div>

	<%
	for (int i = 0; i < samlSloRequestInfos.length(); i++ ) {
		JSONObject samlSloRequestInfo = samlSloRequestInfos.getJSONObject(i);

		String entityId = samlSloRequestInfo.getString("entityId");
		String name = samlSloRequestInfo.getString("name");

		StringBundler entityURL = new StringBundler(3);

		entityURL.append("?cmd=logout");
		entityURL.append("entityId=");
		entityURL.append(entityId);
	%>

		<div class="saml-sp" id="<%= "samlSp" + i %>">
			<a class="saml-sp-label" href="<%= entityURL %>" target="_blank">
				<liferay-ui:message arguments="<%= name %>" key="sign-out-from-x" />
			</a>
		</div>

	<%
	}
	%>

	<div>
		<a href="?cmd=finish">
			<liferay-ui:message key="complete-sign-out" />
		</a>
	</div>
</noscript>

<aui:script use="aui-io-request,aui-template">
	var Language = Liferay.Language;

	Liferay.namespace('SAML');

	Liferay.SAML.SLO = {
		continueNode: null,
		map: {},
		status: {},
		timer: null,

		init: function(data) {
			var instance = this;

			var items = [];

			var retry = Language.get('retry');

			var samlSpTemplate = new A.Template(
				'<tpl for="items">',
					'<div id="{samlSpId}" class="saml-sp">' +
						'<span class="saml-sp-label">{name}</span>' +
						'<img src="' + themeDisplay.getPathThemeImages() + '/spacer.png" class="sso-progress" />' +
						'<a id="{retryId}" href="javascript:Liferay.SAML.SLO._retryLogout({entityId})" class="aui-helper-hidden">' +
							retry +
						'</a>' +
						'<iframe id="{iframeId}" src="?cmd=logout&entityId={entityId}" />' +
					'</div>' +
				'</tpl>'
			);

			var continueNode = A.one('#samlSloContinue');
			var outputNode = A.one('#samlSloOutput');

			A.Array.each(
				data,
				function(item, index, collection) {
					var entityId = item.entityId;
					var samlSpId = 'samlSp' + index;

					instance.map[entityId] = samlSpId;
					instance.status[entityId] = 0;

					items.push(
						{
							entityId: entityId,
							iframeId: 'samlSpIframe' + index,
							name: item.name,
							retryId: 'samlSpRetry' + index,
							samlSpId: samlSpId
						}
					);
				}
			);

			samlSpTemplate.render(
				{
					items: items
				},
				outputNode
			);

			instance.continueNode = continueNode;

			instance.timer = A.later(1000, instance, instance._checkStatus, null, true);
		},

		_checkStatus: function() {
			var instance = this;

			var request = A.io.request(
				'?cmd=status',
				{
					dataType: 'json',
					on: {
						failure: function(event) {
							var timer = instance.timer;

							if (timer) {
								timer.cancel();
							}
						},
						success: function(event) {
							var logoutPending = false;

							var response = this.get('responseData');

							var samlSloRequestInfos = response.samlSloRequestInfos;

							A.Array.each(
								samlSloRequestInfos,
								function(item, index, collection) {
									var status = item.status;

									if (status < 2) {
										logoutPending = true;
									}

									instance._updateStatus(item);
								}
							);

							if (!logoutPending) {
								var continueNode = instance.continueNode;
								var timer = instance.timer;

								var portletMessageInfo = A.Node.create(
									'<p>' +
										'<div class="portlet-msg-info">' +
											Language.get('all-service-providers-are-processed-continuing-sign-out-automatically-in-x-seconds', '5') +
										'</div>' +
										'<a href="?cmd=finish">' + Language.get('complete-sign-out') + '</a>' +
									'</p>'
								);

								if (timer) {
									timer.cancel();
								}

								continueNode.setContent(portletMessageInfo);

								A.later(5000, instance, instance._finishLogout, null, false);
							}
						}
					}
				}
			);
		},

		_finishLogout: function() {
			location.href = '?cmd=finish';
		},

		_retryLogout: function(entityId) {
			var instance = this;

			var timer = instance.timer;

			var entityNode = A.one('#' + instance.map[entityId]);

			var iframe = entityNode.one('iframe');
			var img = entityNode.one('img');
			var retry = entityNode.one('a');

			img.attr(
				{
					className: 'sso-progress',
					title: 'Single logout request in progress'
				}
			);

			retry.hide();

			iframe.set('src', '?cmd=logout&entityId=' + entityId);

			if (timer) {
				timer.cancel();
			}

			timer = A.later(1000, instance, instance._checkStatus, null, true);
		},

		_updateStatus: function(samlSloRequestInfo) {
			var instance = this;

			var className;
			var title;

			var infoStatus = samlSloRequestInfo.status;

			var status = instance.status[samlSloRequestInfo.entityId];

			var entityId = A.one('#' + instance.map[samlSloRequestInfo.entityId]);

			var img = entityId.one('img');

			var retry = entityId.one('a');

			if (status != infoStatus) {
				status = infoStatus;

				if (infoStatus < 2) {
					className = 'sso-progress';

					title = Language.get('single-sign-out-in-progress');

					retry.hide();
				}
				else if (infoStatus === 2) {
					className = 'sso-complete';

					title = Language.get('single-sign-out-completed-successfully');

					retry.hide();
				}
				else if (infoStatus === 3) {
					className = 'sso-failed';

					title = Language.get('single-sign-out-request-failed');

					retry.show();
				}
				else if (infoStatus === 4) {
					className = 'sso-no-support';

					title = Language.get('this-service-provider-does-not-support-single-sign-out');

					retry.hide();
				}
				else if (infoStatus === 5) {
					className = 'sso-timed-out';

					title = Language.get('single-sign-out-request-timed-out');

					retry.show();
				}

				img.attr(
					{
						className: className,
						title: title
					}
				);
			}
		}
	};

	Liferay.SAML.SLO.init(<%= samlSloRequestInfos %>);
</aui:script>