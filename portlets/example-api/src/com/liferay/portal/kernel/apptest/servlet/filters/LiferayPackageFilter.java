/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.kernel.apptest.servlet.filters;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.GroupConstants;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Amos Fong
 */
public class LiferayPackageFilter extends BaseFilter {

	public LiferayPackageFilter(long bundleId, String licensePageURL) {
		_bundleId = bundleId;
		_licensePageURL = licensePageURL;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		_clientIPAddresses.add(request.getRemoteAddr());

		if (_clientIPAddresses.size() > 10) {
			if (_isControlPanel(request)) {
				response.sendRedirect(_licensePageURL);

				return;
			}
			else if (_isLicensePage(request)) {
				StringBundler sb = new StringBundler(4);

				sb.append("You have exceeded the developer license ");
				sb.append("connection limit for ");
				sb.append(_bundleId);
				sb.append(".");

				request.setAttribute("ERROR_MESSAGE", sb.toString());
			}
		}

		processFilter(
			LiferayPackageFilter.class, request, response, filterChain);
	}

	private boolean _isControlPanel(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();

		if (Validator.isNull(pathInfo) ||
			!pathInfo.startsWith(StringPool.SLASH)) {

			return false;
		}

		String friendlyURL = null;

		int pos = pathInfo.indexOf(CharPool.SLASH, 1);

		if (pos != -1) {
			friendlyURL = pathInfo.substring(0, pos);
		}
		else if (pathInfo.length() > 1) {
			friendlyURL = pathInfo;
		}

		if (Validator.isNull(friendlyURL)) {
			return false;
		}

		if (friendlyURL.equals(GroupConstants.CONTROL_PANEL_FRIENDLY_URL)) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean _isLicensePage(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();

		if (Validator.isNull(pathInfo) ||
			!pathInfo.startsWith(StringPool.SLASH)) {

			return false;
		}

		if (pathInfo.equals("/portal/license")) {
			return true;
		}
		else {
			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LiferayPackageFilter.class);

	private long _bundleId;
	private Set<String> _clientIPAddresses = new HashSet<>();
	private String _licensePageURL;

}