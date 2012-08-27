/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.profile.SingleLogoutProfileUtil;
import com.liferay.saml.util.SamlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 */
public class SingleLogoutAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (!SamlUtil.isEnabled()) {
			return "/common/referer_js.jsp";
		}

		try {
			String requestURI = request.getRequestURI();

			if (SamlUtil.isRoleIdp() && requestURI.endsWith("/slo_redirect")) {
				SingleLogoutProfileUtil.processLogoutRequest(request, response);
			}
			else if (SamlUtil.isRoleSp()) {
				String method = request.getMethod();

				if (requestURI.endsWith("/slo_soap") &&
					method.equalsIgnoreCase(HttpMethods.POST)) {

					SingleLogoutProfileUtil.processLogoutRequest(
						request, response);
				}
				else if (method.equalsIgnoreCase(HttpMethods.GET) &&
						 requestURI.endsWith("/slo_redirect")) {

					SingleLogoutProfileUtil.processLogoutResponse(
						request, response);
				}
			}
		}
		catch (Exception e) {
			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e, request,
				response);
		}

		return null;
	}

}