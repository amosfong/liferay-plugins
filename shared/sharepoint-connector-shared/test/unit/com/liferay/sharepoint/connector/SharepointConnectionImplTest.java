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

package com.liferay.sharepoint.connector;

import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.HtmlImpl;
import com.liferay.sharepoint.connector.SharepointConnection.ObjectTypeFilter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
@Ignore
public class SharepointConnectionImplTest {

	@Before
	public void setUp() throws Exception {
		_testId = System.currentTimeMillis();

		setUpMocks();

		deleteSharepointObjects();
	}

	@After
	public void tearDown() throws Exception {
		deleteSharepointObjects();
	}

	@Test
	public void testAddFile() throws Exception {
		String folderPath = StringPool.FORWARD_SLASH;
		String fileName = "CreatedFile " + _testId + ".txt";

		_sharepointConnection.addFile(
			folderPath, fileName, StringPool.BLANK, getInputStream());

		String filePath = folderPath + fileName;

		Assert.assertNotNull(_sharepointConnection.getObject(filePath));
	}

	protected void deleteSharepointObjects() throws SharepointException {
		List<SharepointObject> sharepointObjects =
			_sharepointConnection.getObjects(
				StringPool.FORWARD_SLASH, ObjectTypeFilter.ALL);

		for (SharepointObject sharepointObject : sharepointObjects) {
			_sharepointConnection.deleteObject(sharepointObject.getPath());
		}
	}

	protected InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(_HELLO_WORLD.getBytes("UTF-8"));
	}

	protected void setUpMocks() {
		HtmlUtil htmlUtil = new HtmlUtil();
		
		htmlUtil.setHtml(new HtmlImpl());
	}

	private static final String _HELLO_WORLD = "Hello world!";

	private static final String _LIBRARY_NAME = "Documents";

	private static final String _PASSWORD = "password";

	private static final String _SERVER_ADDRESS = "liferay-20jf4ic";

	private static final int _SERVER_PORT = 80;

	private static final String _SERVER_PROTOCOL = "http";

	private static final String _SITE_PATH = StringPool.BLANK;

	private static final String _USERNAME = "Administrator";

	private SharepointConnection _sharepointConnection =
		new SharepointConnectionImpl(
			_SERVER_PROTOCOL, _SERVER_ADDRESS, _SERVER_PORT, _SITE_PATH,
			_LIBRARY_NAME, _USERNAME, _PASSWORD);
	private long _testId;

}