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

package com.liferay.reports.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.reports.model.Source;
import com.liferay.reports.service.SourceLocalServiceUtil;

/**
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class SourcePermission {

	public static void check(
			PermissionChecker permissionChecker, long sourceId, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, sourceId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, Source source, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, source, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long sourceId, String actionId)
		throws PortalException, SystemException {

		Source source = SourceLocalServiceUtil.getSource(sourceId);

		return contains(permissionChecker, source, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Source source, String actionId) {

		if (permissionChecker.hasOwnerPermission(
				source.getCompanyId(), Source.class.getName(),
				source.getSourceId(), source.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			source.getGroupId(), Source.class.getName(), source.getSourceId(),
			actionId);
	}

}