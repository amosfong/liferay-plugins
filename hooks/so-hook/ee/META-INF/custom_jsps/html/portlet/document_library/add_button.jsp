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

<%
Folder folder = (Folder)request.getAttribute("view.jsp-folder");

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

long repositoryId = GetterUtil.getLong((String)request.getAttribute("view.jsp-repositoryId"));

List<DLFileEntryType> fileEntryTypes = Collections.emptyList();

if ((folder == null) || folder.isSupportsMetadata()) {
	fileEntryTypes = DLFileEntryTypeLocalServiceUtil.getFolderFileEntryTypes(DLUtil.getGroupIds(themeDisplay), folderId, true);
}
%>

<liferay-ui:icon-menu align="left" direction="down" icon="" message="add" showExpanded="<%= false %>" showWhenSingleIcon="<%= true %>">
	<c:if test="<%= DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_FOLDER) %>">
		<portlet:renderURL var="addFolderURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="struts_action" value="/document_library/edit_folder" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
			<portlet:param name="parentFolderId" value="<%= String.valueOf(folderId) %>" />
		</portlet:renderURL>

		<%
		String taglibOnClickAddFolder = liferayPortletResponse.getNamespace() + "openDialog('" + addFolderURL.toString() + "', '" + UnicodeLanguageUtil.get(pageContext, "folder") +"');";
		%>

		<liferay-ui:icon
			image="folder"
			message='<%= (folder != null) ? "subfolder" : "folder" %>'
			onClick="<%= taglibOnClickAddFolder %>"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= ((folder == null) || folder.isSupportsShortcuts()) && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_SHORTCUT) %>">
		<portlet:renderURL var="editFileShortcutURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="struts_action" value="/document_library/edit_file_shortcut" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
			<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
		</portlet:renderURL>

		<%
		String taglibOnClickAddShortcut = liferayPortletResponse.getNamespace() + "openDialog('" + editFileShortcutURL.toString() + "', '" + UnicodeLanguageUtil.get(pageContext, "shortcut") +"');";
		%>

		<liferay-ui:icon
			image="add_instance"
			message="shortcut"
			onClick="<%= taglibOnClickAddShortcut %>"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= (folderId == DLFolderConstants.DEFAULT_PARENT_FOLDER_ID) && (DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_REPOSITORY)) %>">
		<portlet:renderURL var="addRepositoryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
			<portlet:param name="struts_action" value="/document_library/edit_repository" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<%
		String taglibOnClickAddRepository = liferayPortletResponse.getNamespace() + "openDialog('" + addRepositoryURL.toString() + "', '" + UnicodeLanguageUtil.get(pageContext, "repository") +"');";
		%>

		<liferay-ui:icon
			image="add_drive"
			message="repository"
			onClick="<%= taglibOnClickAddRepository %>"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= ((folder == null) || folder.isSupportsMultipleUpload()) && DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_DOCUMENT) %>">
		<portlet:renderURL var="editFileEntryURL">
			<portlet:param name="struts_action" value="/document_library/upload_multiple_file_entries" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="backURL" value="<%= currentURL %>" />
			<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
			<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			cssClass="aui-helper-hidden upload-multiple-documents"
			image="../document_library/add_multiple_documents"
			message="multiple-documents"
			url="<%= editFileEntryURL %>"
		/>
	</c:if>

	<c:if test="<%= DLFolderPermission.contains(permissionChecker, scopeGroupId, folderId, ActionKeys.ADD_DOCUMENT) %>">
		<c:if test="<%= fileEntryTypes.isEmpty() %>">
			<portlet:renderURL var="editFileEntryURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="backURL" value="<%= currentURL %>" />
				<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
				<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
			</portlet:renderURL>

			<%
			String taglibOnClickAddFile = liferayPortletResponse.getNamespace() + "openDialog('" + editFileEntryURL.toString() + "', '" + UnicodeLanguageUtil.get(pageContext, "basic-document") +"');";
			%>

			<liferay-ui:icon
				image="copy"
				message="basic-document"
				onClick="<%= taglibOnClickAddFile %>"
				url="javascript:;"
			/>
		</c:if>

		<c:if test="<%= (folder == null) || folder.isSupportsMetadata() %>">

			<%
			for (DLFileEntryType fileEntryType : fileEntryTypes) {
			%>

				<portlet:renderURL var="addFileEntryTypeURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
					<portlet:param name="struts_action" value="/document_library/edit_file_entry" />
					<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.ADD %>" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
					<portlet:param name="fileEntryTypeId" value="<%= String.valueOf(fileEntryType.getFileEntryTypeId()) %>" />
				</portlet:renderURL>

				<%
				String taglibOnClickAddFileType = liferayPortletResponse.getNamespace() + "openDialog('" + addFileEntryTypeURL.toString() + "', '" + UnicodeLanguageUtil.get(pageContext, HtmlUtil.escape(fileEntryType.getName())) +"');";
				%>

				<liferay-ui:icon
					image="copy"
					message="<%= HtmlUtil.escape(fileEntryType.getName()) %>"
					onClick="<%= taglibOnClickAddFileType %>"
					url="javascript:;"
				/>

			<%
			}
			%>

		</c:if>
	</c:if>
</liferay-ui:icon-menu>

<aui:script use="aui-base,aui-swf">
	if (A.SWF.isFlashVersionAtLeast(9)) {
		var uploadMultipleDocumentsIcon = A.all('.upload-multiple-documents:hidden');

		uploadMultipleDocumentsIcon.show();
	}
</aui:script>