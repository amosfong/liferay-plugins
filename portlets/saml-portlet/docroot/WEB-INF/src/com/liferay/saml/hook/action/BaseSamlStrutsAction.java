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

package com.liferay.saml.hook.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.saml.util.JspUtil;
import com.liferay.saml.util.SamlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 */
public abstract class BaseSamlStrutsAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (!isEnabled()) {
			return "/common/referer_js.jsp";
		}

		try {
			return doExecute(request, response);
		}
		catch (Exception e) {
			_log.error(e, e);

			SessionErrors.add(request, e.getClass().getName());

			JspUtil.dispatch(
				request, response, JspUtil.PATH_PORTAL_SAML_ERROR, "status");
		}

		return null;
	}

	public boolean isEnabled() {
		return SamlUtil.isEnabled();
	}

	protected abstract String doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception;

	private static Log _log = LogFactoryUtil.getLog(BaseSamlStrutsAction.class);

}