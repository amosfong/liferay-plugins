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

package com.liferay.reports.model.impl;

import com.liferay.portal.kernel.cal.TZSRecurrence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;
import com.liferay.reports.service.permission.ActionKeys;

/**
 * @author Brian Wing Shun Chan
 * @author Gavin Wan
 */
public class EntryImpl extends EntryBaseImpl {

	public EntryImpl() {
	}

	public String getAttachmentsDir() {
		return "reports/".concat(String.valueOf(getEntryId()));
	}

	public String[] getAttachmentsFiles()
		throws PortalException, SystemException {

		try {
			return DLStoreUtil.getFileNames(
				getCompanyId(), CompanyConstants.SYSTEM, getAttachmentsDir());
		}
		catch (NoSuchDirectoryException nsde) {
		}

		return new String[0];
	}

	public String getJobName() {
		return ActionKeys.ADD_REPORT.concat(String.valueOf(getEntryId()));
	}

	public TZSRecurrence getRecurrenceObj() {
		return (TZSRecurrence)JSONFactoryUtil.deserialize(getRecurrence());
	}

	public String getSchedulerRequestName() {
		return DestinationNames.REPORT_REQUEST.concat(StringPool.SLASH).concat(
			String.valueOf(getEntryId()));
	}

}