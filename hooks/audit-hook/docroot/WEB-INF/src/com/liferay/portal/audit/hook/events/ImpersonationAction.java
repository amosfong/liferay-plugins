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

package com.liferay.portal.audit.hook.events;

import com.liferay.portal.audit.util.EventTypes;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouterUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 */
public class ImpersonationAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			doRun(request, response);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		long plid = ParamUtil.getLong(request, "p_l_id");

		if (plid <= 0) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();
		User realUser = themeDisplay.getRealUser();
		String doAsUserId = themeDisplay.getDoAsUserId();

		HttpSession session = request.getSession();

		Boolean impersonatingUser = (Boolean)session.getAttribute(
			_IMPERSONATING_USER);

		if (Validator.isNotNull(doAsUserId) &&
			(user.getUserId() != realUser.getUserId())) {

			if (impersonatingUser == null) {
				session.setAttribute(_IMPERSONATING_USER, Boolean.TRUE);

				JSONObject additionalInfo = JSONFactoryUtil.createJSONObject();

				additionalInfo.put("userId", user.getUserId());
				additionalInfo.put("userName", user.getFullName());

				AuditMessage auditMessage = new AuditMessage(
					EventTypes.IMPERSONATE, themeDisplay.getCompanyId(),
					realUser.getUserId(), realUser.getFullName(),
					User.class.getName(), String.valueOf(user.getUserId()),
					null, additionalInfo);

				AuditRouterUtil.route(auditMessage);
			}
		}
		else if (impersonatingUser != null) {
			session.removeAttribute(_IMPERSONATING_USER);
		}
	}

	private static final String _IMPERSONATING_USER =
		ImpersonationAction.class + ".IMPERSONATING_USER";

}