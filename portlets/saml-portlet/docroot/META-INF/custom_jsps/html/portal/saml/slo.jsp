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

<%@ include file="/html/portal/init.jsp" %>

<%
JSONObject samlSloContextJSONObject = (JSONObject)request.getAttribute("SAML_SLO_CONTEXT");

JSONArray samlSloRequestInfosJSONArray = samlSloContextJSONObject.getJSONArray("samlSloRequestInfos");
%>

<style type="text/css">
	.portlet-msg-no-support-label {
		background-image: url(<%= themeDisplay.getPathThemeImages() %>/arrows/02_x.png);
	}

	.portlet-msg-timed-out-label {
		background-image: url(<%= themeDisplay.getPathThemeImages() %>/common/time.png);
	}

	.saml-sp {
		margin-bottom: 0.5em;
	}

	.saml-sp-label {
		background-position: 0 50%;
		background-repeat: no-repeat;
		padding: 3px 3px 3px 20px;
		font-weight: bold;
	}

	.saml-sp-retry {
		display: inline-block;
		margin-left: 10px;
	}
</style>

<h3>
	<liferay-ui:message key="signing-out-from-services" />
</h3>

<div id="samlSloResults"></div>

<div class="aui-helper-hidden" id="samlSloCompleteSignOut">
	<div class="portlet-msg-info">
		<liferay-ui:message arguments="<%= 5 %>" key="all-service-providers-are-processed.-continuing-sign-out-automatically-in-x-seconds" />
	</div>

	<a href="?cmd=finish"><liferay-ui:message key="complete-sign-out" /></a>
</div>

<noscript>
	<div class="portlet-msg-info">
		<liferay-ui:message key="your-browser-does-not-support-javascript.-you-will-need-to-sign-out-manually-from-each-service-provider" />
	</div>

	<%
	for (int i = 0; i < samlSloRequestInfosJSONArray.length(); i++ ) {
		JSONObject samlSloRequestInfoJSONObject = samlSloRequestInfosJSONArray.getJSONObject(i);

		String entityId = samlSloRequestInfoJSONObject.getString("entityId");
		String name = samlSloRequestInfoJSONObject.getString("name");
	%>

		<div class="saml-sp">
			<a class="saml-sp-label" href="?cmd=logout&entityId=<%= entityId %>" target="_blank">
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

<aui:script use="aui-io-request-deprecated,aui-template-deprecated">
	var AArray = A.Array;

	var MAP_ENTITY_STATUS = {
		2: {
			cssClass: 'portlet-msg-success-label',
			retry: false,
			title: '<%= UnicodeLanguageUtil.get(pageContext, "single-sign-out-completed-successfully") %>'
		},
		3: {
			cssClass: 'portlet-msg-error-label',
			retry: true,
			title: '<%= UnicodeLanguageUtil.get(pageContext, "single-sign-out-request-failed") %>'
		},
		4: {
			cssClass: 'portlet-msg-no-support-label',
			retry: false,
			title: '<%= UnicodeLanguageUtil.get(pageContext, "this-service-provider-does-not-support-single-sign-out") %>'
		},
		5: {
			cssClass: 'portlet-msg-timed-out-label',
			retry: true,
			title: '<%= UnicodeLanguageUtil.get(pageContext, "single-sign-out-request-timed-out") %>'
		},
		defaultStatus: {
			cssClass: 'portlet-msg-progress-label',
			retry: false,
			title: '<%= UnicodeLanguageUtil.get(pageContext, "single-sign-out-in-progress") %>'
		}
	};

	var TPL_SAML_ENTITY = new A.Template(
		'<tpl for="items">',
			'<div id="samlSp{$i}" class="saml-sp">' +
				'<span class="saml-sp-label portlet-msg-progress-label">{name}</span>' +
				'<a class="aui-helper-hidden saml-sp-retry" data-entityId="{entityId}" href="javascript:;"><%= UnicodeLanguageUtil.get(pageContext, "retry") %></a>' +
				'<iframe class="aui-helper-hidden-accessible" src="?cmd=logout&entityId={entityId}"></iframe>' +
			'</div>' +
		'</tpl>'
	);

	var SAML = Liferay.namespace('SAML');

	SAML.SLO = {
		init: function(items) {
			var instance = this;

			var entities = instance._entities;
			var entityStatus = instance._entityStatus;

			AArray.each(
				items,
				function(item, index, collection) {
					var entityId = item.entityId;

					entities[entityId] = 'samlSp' + index;
					entityStatus[entityId] = 0;
				}
			);

			var outputNode = A.one('#samlSloResults');

			TPL_SAML_ENTITY.render(
				{
					items: items
				},
				outputNode
			);

			outputNode.delegate(
				'click',
				function(event) {
					instance.retryLogout(event.currentTarget.attr('data-entityId'));
				},
				'.saml-sp-retry'
			);

			instance._completeSignOut = A.one('#samlSloCompleteSignOut');

			instance.checkStatus();
		},

		checkStatus: function() {
			var instance = this;

			var request = A.io.request(
				'?cmd=status',
				{
					dataType: 'json',
					on: {
						success: function(event) {
							var logoutPending = false;

							AArray.each(
								this.get('responseData.samlSloRequestInfos'),
								function(item, index, collection) {
									logoutPending = (item.status < 2);

									instance.updateStatus(item);
								}
							);

							if (logoutPending) {
								instance.checkStatus();
							}
							else {
								instance._completeSignOut.show();

								setTimeout(A.bind('finishLogout', instance), 5000);
							}
						}
					}
				}
			);
		},

		finishLogout: function() {
			location.href = '?cmd=finish';
		},

		retryLogout: function(entityId) {
			var instance = this;

			var entityNode = A.one('#' + instance._entities[entityId]);

			if (entityNode) {
				var defaultStatus = MAP_ENTITY_STATUS.defaultStatus;

				entityNode.one('.saml-sp-label').attr(
					{
						className: 'saml-sp-label ' + defaultStatus.cssClass,
						title: defaultStatus.title
					}
				);

				entityNode.one('.saml-sp-retry').hide();

				entityNode.one('iframe').set('src', '?cmd=logout&entityId=' + entityId);

				instance.checkStatus();
			}
		},

		updateStatus: function(samlSloRequestInfo) {
			var instance = this;

			var infoStatus = samlSloRequestInfo.status;

			var entityStatus = instance._entityStatus;

			var entityId = samlSloRequestInfo.entityId;

			var status = entityStatus[entityId];

			if (status != infoStatus) {
				entityStatus[entityId] = infoStatus;

				var entityNode = A.one('#' + instance._entities[entityId]);

				var statusDetails = MAP_ENTITY_STATUS[infoStatus] || MAP_ENTITY_STATUS.defaultStatus;

				entityNode.one('.saml-sp-label').attr(
					{
						className: 'saml-sp-label ' + statusDetails.cssClass,
						title: statusDetails.title
					}
				);

				entityNode.one('.saml-sp-retry').toggle(statusDetails.retry);
			}
		},

		_entities: {},
		_entityStatus: {}
	};

	Liferay.SAML.SLO.init(<%= samlSloRequestInfosJSONArray %>);
</aui:script>