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

package com.liferay.saml.hook.filter;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.struts.LastPath;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.profile.SingleLogoutProfileUtil;
import com.liferay.saml.profile.WebSsoProfileUtil;
import com.liferay.saml.service.SamlSpSessionLocalServiceUtil;
import com.liferay.saml.util.PortletWebKeys;
import com.liferay.saml.util.SamlUtil;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Mika Koivisto
 */
public class SamlSpSsoFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled() {
		if (SamlUtil.isEnabled() && SamlUtil.isRoleSp()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		if (!SamlUtil.isEnabled() || !SamlUtil.isRoleSp()) {
			return false;
		}

		try {
			User user = PortalUtil.getUser(request);

			if (user != null) {
				return true;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		String requestPath = SamlUtil.getRequestPath(request);

		if (requestPath.equals("/c/portal/login") ||
			requestPath.equals("/c/portal/logout")) {

			return true;
		}

		return false;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	protected void login(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException, SystemException {

		String relayState = ParamUtil.getString(request, "redirect");

		if (Validator.isNotNull(relayState)) {
			relayState = PortalUtil.escapeRedirect(relayState);
		}

		HttpSession session = request.getSession();

		LastPath lastPath = (LastPath)session.getAttribute(WebKeys.LAST_PATH);

		if (GetterUtil.getBoolean(
				PropsUtil.get(PropsKeys.AUTH_FORWARD_BY_LAST_PATH)) &&
			(lastPath != null) && Validator.isNull(relayState)) {

			StringBundler sb = new StringBundler(4);

			sb.append(PortalUtil.getPortalURL(request));
			sb.append(lastPath.getContextPath());
			sb.append(lastPath.getPath());
			sb.append(
				HttpUtil.parameterMapToString(lastPath.getParameterMap()));

			relayState = sb.toString();
		}
		else if (Validator.isNull(relayState)) {
			relayState = PortalUtil.getPathMain();
		}

		WebSsoProfileUtil.sendAuthnRequest(request, response, relayState);
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		String requestPath = SamlUtil.getRequestPath(request);

		if (requestPath.equals("/c/portal/login")) {
			login(request, response);
		}
		else if (requestPath.equals("/c/portal/logout") &&
				 SingleLogoutProfileUtil.isSingleLogoutSupported(request)) {

			SamlSpSession samlSpSession =
				SingleLogoutProfileUtil.getSamlSpSession(request);

			if (samlSpSession != null) {
				SingleLogoutProfileUtil.processSpLogout(request, response);
			}
			else {
				filterChain.doFilter(request, response);
			}
		}
		else {
			SamlSpSession samlSpSession =
				SingleLogoutProfileUtil.getSamlSpSession(request);

			if ((samlSpSession != null) && samlSpSession.isTerminated()) {
				SamlSpSessionLocalServiceUtil.deleteSamlSpSession(
					samlSpSession);

				HttpSession session = request.getSession();

				session.invalidate();

				Cookie cookie = new Cookie(
					PortletWebKeys.SAML_SP_SESSION_KEY, StringPool.BLANK);

				cookie.setMaxAge(0);

				if (Validator.isNull(PortalUtil.getPathContext())) {
					cookie.setPath(StringPool.SLASH);
				}
				else {
					cookie.setPath(PortalUtil.getPathContext());
				}

				cookie.setSecure(request.isSecure());

				response.addCookie(cookie);

				response.sendRedirect(
					PortalUtil.getCurrentCompleteURL(request));
			}
			else {
				filterChain.doFilter(request, response);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SamlSpSsoFilter.class);

}