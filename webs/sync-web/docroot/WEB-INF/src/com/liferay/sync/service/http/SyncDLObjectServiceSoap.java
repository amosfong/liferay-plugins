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

package com.liferay.sync.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.sync.service.SyncDLObjectServiceUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.sync.service.SyncDLObjectServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.sync.model.SyncDLObjectSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.sync.model.SyncDLObject}, that is translated to a
 * {@link com.liferay.sync.model.SyncDLObjectSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObjectServiceHttp
 * @see com.liferay.sync.model.SyncDLObjectSoap
 * @see com.liferay.sync.service.SyncDLObjectServiceUtil
 * @generated
 */
public class SyncDLObjectServiceSoap {
	public static com.liferay.sync.model.SyncDLObjectSoap addFolder(
		long repositoryId, long parentFolderId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.addFolder(repositoryId,
					parentFolderId, name, description, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteFileEntry(long fileEntryId)
		throws RemoteException {
		try {
			SyncDLObjectServiceUtil.deleteFileEntry(fileEntryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteFolder(long folderId) throws RemoteException {
		try {
			SyncDLObjectServiceUtil.deleteFolder(folderId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap[] getAllSyncDLObjects(
		long repositoryId, long folderId) throws RemoteException {
		try {
			java.util.List<com.liferay.sync.model.SyncDLObject> returnValue = SyncDLObjectServiceUtil.getAllSyncDLObjects(repositoryId,
					folderId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap getFileEntrySyncDLObject(
		long groupId, long folderId, java.lang.String title)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.getFileEntrySyncDLObject(groupId,
					folderId, title);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap[] getFileEntrySyncDLObjects(
		long repositoryId, long folderId) throws RemoteException {
		try {
			java.util.List<com.liferay.sync.model.SyncDLObject> returnValue = SyncDLObjectServiceUtil.getFileEntrySyncDLObjects(repositoryId,
					folderId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap getFolderSyncDLObject(
		long folderId) throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.getFolderSyncDLObject(folderId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap[] getFolderSyncDLObjects(
		long repositoryId, long parentFolderId) throws RemoteException {
		try {
			java.util.List<com.liferay.sync.model.SyncDLObject> returnValue = SyncDLObjectServiceUtil.getFolderSyncDLObjects(repositoryId,
					parentFolderId);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncContext getSyncContext(
		java.lang.String uuid) throws RemoteException {
		try {
			com.liferay.sync.model.SyncContext returnValue = SyncDLObjectServiceUtil.getSyncContext(uuid);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectUpdate getSyncDLObjectUpdate(
		long companyId, long repositoryId, long lastAccessDate)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObjectUpdate returnValue = SyncDLObjectServiceUtil.getSyncDLObjectUpdate(companyId,
					repositoryId, lastAccessDate);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.model.GroupSoap[] getUserSites()
		throws RemoteException {
		try {
			java.util.List<com.liferay.portal.model.Group> returnValue = SyncDLObjectServiceUtil.getUserSites();

			return com.liferay.portal.model.GroupSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap moveFileEntry(
		long fileEntryId, long newFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.moveFileEntry(fileEntryId,
					newFolderId, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap moveFolder(
		long folderId, long parentFolderId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.moveFolder(folderId,
					parentFolderId, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.sync.model.SyncDLObjectSoap updateFolder(
		long folderId, java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.sync.model.SyncDLObject returnValue = SyncDLObjectServiceUtil.updateFolder(folderId,
					name, description, serviceContext);

			return com.liferay.sync.model.SyncDLObjectSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SyncDLObjectServiceSoap.class);
}