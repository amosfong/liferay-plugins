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

<liferay-util:include page="/html/portlet/document_library/view_entries.jsp" useCustomPage="<%= false %>" />

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />checkOutAndOpen',
		function(checkOutURL, webDavURL) {
			var A = AUI();
			var UA = A.UA;
			var WIN = A.config.win;

			if (webDavURL && UA.ie) {
				try {
					var executor = new WIN.ActiveXObject('SharePoint.OpenDocuments');

					executor.EditDocument(webDavURL);

					A.io.request(
						checkOutURL,
						{
							method: 'POST',
							after: {
								success: function(event, id, obj) {
									location.href = "<%= currentURL %>";
								}
							}
						}
					);
				}
				catch (exception) {
					var errorMessage = A.Lang.sub(
						Liferay.Language.get('cannot-open-the-requested-document-due-to-the-following-reason'),
						[exception.message]
					);

					var documentContainer = A.one('#<portlet:namespace />documentContainer');

					var existingMessage = documentContainer.one('.portlet-msg-error');

					if (existingMessage) {
						existingMessage.setContent(errorMessage);
					}
					else {
						var output = A.Node.create('<div class="lfr-message-response portlet-msg-error" />');

						output.html(errorMessage);

						documentContainer.prepend(output);
					}
				}
			}
		}
	);
</aui:script>