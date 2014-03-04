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

package com.liferay.osb.lcs.service.impl;

import com.liferay.lcs.service.impl.BaseLCSServiceImpl;
import com.liferay.osb.lcs.LCSClusterEntryNameException;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.impl.LCSClusterEntryImpl;
import com.liferay.osb.lcs.service.LCSClusterEntryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterEntryServiceImpl
	extends BaseLCSServiceImpl implements LCSClusterEntryService {

	@Override
	public LCSClusterEntry addLCSClusterEntry(
			long corpEntryId, String name, String description, String location,
			int type, ServiceContext serviceContext)
		throws PortalException, SystemException {

		_validate(corpEntryId, name);

		if (Validator.isNull(description)) {
			description = null;
		}

		if (Validator.isNull(location)) {
			location = null;
		}

		try {
			return doGetToObject(
				LCSClusterEntryImpl.class,
				_URL_LCS_CLUSTER_ENTRY_ADD_LCS_CLUSTER_ENTRY, "corpEntryId",
				String.valueOf(corpEntryId), "name", name, "description",
				description, "location", location, "type",
				String.valueOf(type));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public void deleteCorpEntryClusters(long corpEntryId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public LCSClusterEntry deleteLCSClusterEntry(long lcsClusterEntryId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getBeanIdentifier() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<LCSClusterEntry> getCorpEntryLCSClusterEntries(long corpEntryId)
		throws SystemException {

		List<LCSClusterEntryImpl> remoteLcsClusterEntries = null;

		try {
			remoteLcsClusterEntries = doGetToList(
				LCSClusterEntryImpl.class,
				_URL_LCS_CLUSTER_ENTRY_GET_COR_ENTRY_LCS_CLUSTER_ENTRIES,
				"corpEntryId", String.valueOf(corpEntryId));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		List<LCSClusterEntry> localLCSClusterEntries = new ArrayList
			<LCSClusterEntry>();

		for (LCSClusterEntry lcsClusterEntry : remoteLcsClusterEntries) {
			localLCSClusterEntries.add(lcsClusterEntry);
		}

		return localLCSClusterEntries;
	}

	@Override
	public LCSClusterEntry getLCSClusterEntry(long lcsClusterEntryId)
		throws SystemException {

		try {
			return doGetToObject(
				LCSClusterEntryImpl.class,
				_URL_LCS_CLUSTER_ENTRY_GET_LCS_CLUSTER_ENTRY,
				"lcsClusterEntryId", String.valueOf(lcsClusterEntryId));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@Override
	public List<LCSClusterEntry> getUserLCSClusterEntries(long userId)
		throws SystemException {

		List<LCSClusterEntryImpl> remoteLcsClusterEntries = null;

		try {
			remoteLcsClusterEntries = doGetToList(
				LCSClusterEntryImpl.class,
				_URL_LCS_CLUSTER_ENTRY_GET_USER_LCS_CLUSTER_ENTRIES, "userId",
				String.valueOf(userId));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}

		List<LCSClusterEntry> localLCSClusterEntries = new ArrayList
			<LCSClusterEntry>();

		for (LCSClusterEntry lcsClusterEntry : remoteLcsClusterEntries) {
			localLCSClusterEntries.add(lcsClusterEntry);
		}

		return localLCSClusterEntries;
	}

	@Override
	public List<LCSClusterEntry> getUserLCSClusterEntries(
		long userId, long corpEntryId) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Object invokeMethod(
			String name, String[] parameterTypes, Object[] arguments)
		throws Throwable {

		throw new UnsupportedOperationException();
	}

	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		throw new UnsupportedOperationException();
	}

	@Override
	public LCSClusterEntry updateLCSClusterEntry(
		long lcsClusterEntryId, String name, String description,
		String location) {

		throw new UnsupportedOperationException();
	}

	private void _validate(long corpEntryId, String lcsClusterEntryName)
		throws PortalException, SystemException {

		if (Validator.isNull(lcsClusterEntryName)) {
			throw new LCSClusterEntryNameException(
				"Environment must have valid name.");
		}

		List<LCSClusterEntry> lcsClusterEntries = getCorpEntryLCSClusterEntries(
			corpEntryId);

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			if (lcsClusterEntryName.equals(lcsClusterEntry.getName())) {
				throw new LCSClusterEntryNameException(
					"Environment with given name exists.");
			}
		}
	}

	private static final String _URL_LCS_CLUSTER_ENTRY =
		"/api/secure/jsonws/osb-lcs-portlet.lcsclusterentry/";

	private static final String _URL_LCS_CLUSTER_ENTRY_ADD_LCS_CLUSTER_ENTRY =
		_URL_LCS_CLUSTER_ENTRY + "add-lcs-cluster-entry";

	private static final String
		_URL_LCS_CLUSTER_ENTRY_GET_COR_ENTRY_LCS_CLUSTER_ENTRIES =
			_URL_LCS_CLUSTER_ENTRY + "get-corp-entry-lcs-cluster-entries";

	private static final String _URL_LCS_CLUSTER_ENTRY_GET_LCS_CLUSTER_ENTRY =
		_URL_LCS_CLUSTER_ENTRY + "get-lcs-cluster-entry";

	private static final String
		_URL_LCS_CLUSTER_ENTRY_GET_USER_LCS_CLUSTER_ENTRIES =
			_URL_LCS_CLUSTER_ENTRY + "get-user-lcs-cluster-entries";

}