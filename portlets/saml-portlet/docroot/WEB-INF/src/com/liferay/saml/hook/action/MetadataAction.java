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

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.util.PortalUtil;
import com.liferay.saml.metadata.MetadataManagerUtil;
import com.liferay.saml.util.OpenSamlUtil;
import com.liferay.saml.util.SamlUtil;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opensaml.saml2.metadata.EntityDescriptor;

/**
 * @author Mika Koivisto
 */
public class MetadataAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (!SamlUtil.isEnabled()) {
			return "/common/referer_js.jsp";
		}

		try {
			return doExecute(request, response);
		}
		catch (Exception e) {
			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e, request,
				response);
		}

		return null;
	}

	protected String doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		response.setContentType(ContentTypes.TEXT_XML);

		EntityDescriptor entityDescriptor =
			MetadataManagerUtil.getEntityDescriptor(request);

		String metadata = OpenSamlUtil.marshallSamlObject(entityDescriptor);

		PrintWriter printWriter = response.getWriter();

		printWriter.print(metadata);

		return null;
	}

}