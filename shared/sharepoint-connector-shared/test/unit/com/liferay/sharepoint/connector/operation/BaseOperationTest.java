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

package com.liferay.sharepoint.connector.operation;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.sharepoint.connector.SharepointConnectionInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class BaseOperationTest {

	@Before
	public void setUp() {
		_sharepointConnectionInfo =
			new SharepointConnectionInfo(
				"http", "server", 80, _SITE_PATH, _LIBRARY_NAME, "username",
				"password");

		_baseOperation.setSharepointConnectionInfo(_sharepointConnectionInfo);
	}

	@Test
	public void testToFullPathWithNonrootPath() {
		Assert.assertEquals(
			_SITE_PATH + StringPool.SLASH + _LIBRARY_NAME + "/folder/name",
			_baseOperation.toFullPath("/folder/name"));
	}

	@Test
	public void testToFullPathWithRootPath() {
		Assert.assertEquals(
			_SITE_PATH + StringPool.SLASH + _LIBRARY_NAME,
			_baseOperation.toFullPath("/"));
	}

	private static final String _LIBRARY_NAME = "library";

	private static final String _SITE_PATH = "/site";

	private BaseOperation _baseOperation = new BaseOperation() {};
	private SharepointConnectionInfo _sharepointConnectionInfo;

}