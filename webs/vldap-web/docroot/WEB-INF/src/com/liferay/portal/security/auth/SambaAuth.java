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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.samba.PortalSambaUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Map;

/**
 * @author Minhchau Dang
 */
public class SambaAuth implements Authenticator {

	public SambaAuth() {
		PortalSambaUtil.checkAttributes();
	}

	@Override
	public int authenticateByEmailAddress(
		long companyId, String emailAddress, String password,
		Map<String, String[]> headerMap, Map<String, String[]> parameterMap) {

		try {
			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				companyId, emailAddress);

			PortalSambaUtil.setSambaLMPassword(user, password);
			PortalSambaUtil.setSambaNTPassword(user, password);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return SUCCESS;
	}

	@Override
	public int authenticateByScreenName(
		long companyId, String screenName, String password,
		Map<String, String[]> headerMap, Map<String, String[]> parameterMap) {

		try {
			User user = UserLocalServiceUtil.fetchUserByScreenName(
				companyId, screenName);

			PortalSambaUtil.setSambaLMPassword(user, password);
			PortalSambaUtil.setSambaNTPassword(user, password);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return SUCCESS;
	}

	@Override
	public int authenticateByUserId(
		long companyId, long userId, String password,
		Map<String, String[]> headerMap, Map<String, String[]> parameterMap) {

		try {
			User user = UserLocalServiceUtil.fetchUserById(userId);

			PortalSambaUtil.setSambaLMPassword(user, password);
			PortalSambaUtil.setSambaNTPassword(user, password);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		return SUCCESS;
	}

	private static Log _log = LogFactoryUtil.getLog(SambaAuth.class);

}