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

package com.liferay.saml.util;

import org.junit.Assert;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Mika Koivisto
 */
public class SamlUtilTest {

	@Test
	public void testGetRequestPath() {
		MockHttpServletRequest request = new MockHttpServletRequest(
			"GET", "/c/portal/login;jsessionid=ACD311312312323BF.worker1");

		String path = SamlUtil.getRequestPath(request);

		Assert.assertEquals("/c/portal/login", path);
	}

	@Test
	public void testGetRequestPathWithContext() {
		MockHttpServletRequest request = new MockHttpServletRequest(
			"GET",
			"/portal/c/portal/login;jsessionid=ACD311312312323BF.worker1");
		request.setContextPath("/portal");

		String path = SamlUtil.getRequestPath(request);

		Assert.assertEquals("/c/portal/login", path);
	}

	@Test
	public void testGetRequestPathWithoutJsessionId() {
		MockHttpServletRequest request = new MockHttpServletRequest(
			"GET", "/c/portal/login");

		String path = SamlUtil.getRequestPath(request);

		Assert.assertEquals("/c/portal/login", path);
	}

}