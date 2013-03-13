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

package com.liferay.saml.hook.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.saml.profile.SingleLogoutProfileUtil;
import com.liferay.saml.util.SamlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 */
public class SingleLogoutAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {
		if (!SamlUtil.isEnabled() || !SamlUtil.isRoleIdp()) {
			return;
		}

		try {
			SingleLogoutProfileUtil.processIdpLogout(request, response);
		}
		catch (Exception e) {
			_log.warn("Unable to perform single logout", e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SingleLogoutAction.class);

}