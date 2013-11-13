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

package com.liferay.osb.lcs.service;

import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterEntryServiceUtil {

	public static LCSClusterEntry addLCSClusterEntry(
			long corpEntryId, String name, String description, String location,
			int type)
		throws PortalException, SystemException {

		return getLCSClusterEntryService().addLCSClusterEntry(
			corpEntryId, name, description, location, type, null);
	}

	public static List<LCSClusterEntry> getCorpEntryLCSClusterEntries(
			long corpEntryId)
		throws PortalException, SystemException {

		return getLCSClusterEntryService().getCorpEntryLCSClusterEntries(
			corpEntryId);
	}

	public static LCSClusterEntry getLCSClusterEntry(long lcsClusterEntryId)
		throws PortalException, SystemException {

		return getLCSClusterEntryService().getLCSClusterEntry(
			lcsClusterEntryId);
	}

	public static LCSClusterEntryService getLCSClusterEntryService() {
		return _lcsClusterEntryService;
	}

	public static List<LCSClusterEntry> getUserLCSClusterEntries(long userId)
		throws PortalException, SystemException {

		return getLCSClusterEntryService().getUserLCSClusterEntries(userId);
	}

	public void setLCSClusterEntryService(
		LCSClusterEntryService lcsClusterEntryService) {

		_lcsClusterEntryService = lcsClusterEntryService;
	}

	private static LCSClusterEntryService _lcsClusterEntryService;

}