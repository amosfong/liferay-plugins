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

import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeServiceUtil {

	public static LCSClusterNode addLCSClusterNode(
			long lcsClusterEntryId, String name, String description,
			int buildNumber, String key, String location)
		throws PortalException, SystemException {

		return getLCSClusterNodeService().addLCSClusterNode(
			lcsClusterEntryId, name, description, buildNumber, key, location,
			null);
	}

	public static LCSClusterNode addLCSClusterNode(
			String siblingKey, String name, String description, String key,
			String location)
		throws PortalException, SystemException {

		return getLCSClusterNodeService().addLCSClusterNode(
			siblingKey, name, description, key, location, null);
	}

	public static LCSClusterNode getLCSClusterNode(String key)
		throws PortalException, SystemException {

		return getLCSClusterNodeService().getLCSClusterNode(key);
	}

	public static LCSClusterNodeService getLCSClusterNodeService() {
		return _lcsClusterNodeService;
	}

	public static boolean isRegistered(String key)
		throws PortalException, SystemException {

		try {
			if (getLCSClusterNode(key) != null) {
				return true;
			}
		}
		catch (SystemException se) {
			String message = se.getMessage();

			if ((message != null) &&
				message.contains("NoSuchLCSClusterNodeException")) {

				return false;
			}

			throw se;
		}

		return false;
	}

	public void setLCSClusterNodeService(
		LCSClusterNodeService lcsClusterNodeService) {

		_lcsClusterNodeService = lcsClusterNodeService;
	}

	private static LCSClusterNodeService _lcsClusterNodeService;

}