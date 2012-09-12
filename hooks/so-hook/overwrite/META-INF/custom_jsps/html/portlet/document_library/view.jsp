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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<liferay-util:include page="/html/portlet/document_library/view.jsp" useCustomPage="<%= false %>" />

<aui:script>
	function <portlet:namespace />editAndCheckout(checkoutURL) {
		var A = AUI();

		A.io.request(checkoutURL);
	}

	function <portlet:namespace />openDialog(uri, title) {
		var A = AUI();

		var dialog = new A.Dialog(
			{
				align: {
					node: null,
					points: ['tc', 'tc']
				},
				constrain2view: true,
				destroyOnClose: true,
				modal: true,
				resizable: false,
				title: title,
				width: 750
			}
		).plug(
			A.Plugin.IO,
			{
				uri: uri
			}
		).render();
	}
</aui:script>