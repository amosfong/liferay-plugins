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
<h3><liferay-ui:message key="signing-out-from-services" /></h3>
<div id="output"></div>
<div id="continue"></div>
<noscript>
<div class="portlet-msg-info">
	<liferay-ui:message key="your-browser-does-not-support-javascript-so-you-need-to-sign-out-manually-from-each-service-provider" />
</div>
<% 
	for (int i = 0; i < samlSloRequestInfos.length(); i++ ) {
		JSONObject samlSloRequestInfo = samlSloRequestInfos.getJSONObject(i);
%>
	<div id="samlSp<%= i %>" class="saml-sp">
		<span class="saml-sp-label"><liferay-ui:message key="sign-out-from-x" /></span>
		<a href="?cmd=logout&entityId=<%=samlSloRequestInfo.getString("entityId") %>" target="_blank"><%= samlSloRequestInfo.getString("name") %></a>
	</div>
<%
	}
%>
<div><a href="?cmd=finish"><liferay-ui:message key="complete-sign-out" /></a></div>
</noscript>
<aui:script use="aui-io-request">
	Liferay.namespace('SAML');

	Liferay.SAML.SLO = {
		init: function(samlSloRequestInfos) {
			var instance = this;

			instance.div = A.one('#output');
			instance.div.html('');

			instance.continueDiv = A.one('#continue');
			instance.continueDiv.html('');

			for (var i = 0; i < samlSloRequestInfos.length; i++) {
				instance.map[samlSloRequestInfos[i].entityId] = 'samlSp'+i;
				instance.status[samlSloRequestInfos[i].entityId] = 0;

				var html = '<div id="samlSp' + i + '" class="saml-sp">' +
								'<span class="saml-sp-label">' + samlSloRequestInfos[i].name + '</span>' +
								'<img src="' + themeDisplay.getPathThemeImages() + '/application/loading_indicator.gif" style="width: 16px; height: 16px; padding: 0 5px;" />' +
								' <a id="samlSpRetry' + i + '" href="javascript:Liferay.SAML.SLO.retryLogout(\'' + samlSloRequestInfos[i].entityId + '\')" class="aui-helper-hidden">'+ Liferay.Language.get('retry') + '</a>' +
								'<iframe id="samlSpIframe' + i + '" src="?cmd=logout&entityId=' + samlSloRequestInfos[i].entityId + '" style="width: 0; height: 0; position: absolute; left: -9999px;"/>' +
							'</div>';

				instance.div.append(html);
			}

			instance.timer = A.later(1000, instance, instance.checkStatus, null, true);
		},

		updateStatus: function(samlSloRequestInfo) {
			var instance = this;

			if (instance.status[samlSloRequestInfo.entityId] == samlSloRequestInfo.status) {
				console.log("Status did not change for "+ samlSloRequestInfo.entityId);
				return;
			}

			instance.status[samlSloRequestInfo.entityId] = samlSloRequestInfo.status;

			var img = A.one('#' + instance.map[samlSloRequestInfo.entityId] + ' img');
			var retry = A.one('#' + instance.map[samlSloRequestInfo.entityId] + ' a');

			var imagesPath = '/saml-portlet/images'

			if (samlSloRequestInfo.status < 2) {
				img.set('src', imagesPath + '/loading_indicator.gif');
				img.set('title', Liferay.Language.get('single-sign-out-in-progress'));
				retry.hide();
			}
			else if (samlSloRequestInfo.status == 2) {
				img.set('src', imagesPath + '/success.png');
				img.set('title', Liferay.Language.get('single-sign-out-completed-successfully'))
				retry.hide();
			}
			else if (samlSloRequestInfo.status == 4) {
				img.set('src', imagesPath + '/not_supported.png');
				img.set('title', Liferay.Language.get('this-service-provider-does-not-support-single-sign-out'));
				retry.hide();
			}
			else if (samlSloRequestInfo.status == 5) {
				img.set('src', imagesPath + '/timeout.png');
				img.set('title', Liferay.Language.get('single-sign-out-request-timed-out'));
				retry.show();
			}
			else if (samlSloRequestInfo.status == 3) {
				img.set('src', imagesPath + '/error.png');
				img.set('title', Liferay.Language.get('single-sign-out-request-failed'));
				retry.show();
			}
		},

		retryLogout: function(entityId) {
			var instance = this;

			var img = A.one('#' + instance.map[entityId] + ' img');
			var iframe = A.one('#' + instance.map[entityId] + ' iframe');
			var retry = A.one('#' + instance.map[entityId] + ' a');

			img.set('src', '/saml-portlet' + '/loading_indicator.gif');
			img.set('title', 'Single logout request in progress');

			retry.hide();

			iframe.set('src', '?cmd=logout&entityId=' + entityId);

			if (instance.timer) {
				instance.timer.cancel();
			}

			instance.timer = A.later(1000, instance, instance.checkStatus, null, true);
		},

		checkStatus: function() {
			var instance = this;

			var request = A.io.request(
				'?cmd=status',
				{
					dataType: 'json',
					on: {
						success: function(event) {
							var response = this.get('responseData');
							var logoutPending = false;
							console.log(response);

							var samlSloRequestInfos = response.samlSloRequestInfos;

							for(var i = 0; i < samlSloRequestInfos.length; i++){
								if (samlSloRequestInfos[i].status < 2) {
									logoutPending = true;
								}

								instance.updateStatus(samlSloRequestInfos[i]);
							}

							if (!logoutPending) {
								if (instance.timer) {
									instance.timer.cancel();
								}

								instance.continueDiv.html('');

								var html = '<p>' +
												'<div class="portlet-msg-info">' +
												Liferay.Language.get('all-service-providers-are-processed-continuing-sign-out-automatically-in-x-seconds', '5') +
												'</div>' +
												' <a href="?cmd=finish">' + Liferay.Language.get('complete-sign-out') + '</a>' +
											'</p>';

								instance.continueDiv.append(html);

								A.later(5000, instance, instance.finishLogout, null, false);
							}
						},
						failure: function(event) {
							if (instance.timer) {
								instance.timer.cancel();
							}
						}
					}
				}
			);
		},

		finishLogout: function() {
			location.href = '?cmd=finish';
		},

		div: null,
		continueDiv: null,
		map: {},
		status: {},
		timer: null
	};

	Liferay.SAML.SLO.init(<%= samlSloRequestInfos %>);
</aui:script>
