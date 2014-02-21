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
 * <p>
 * WARNING: This test is intended to be run manually from the IDE with a valid
 * Sharepoint server available.
 * </p>
 * <p>
 * WARNING: This test will completely ** ZAP ** the contents of the Sharepoint
 * server to which it connects.
 * </p>
 * <p>
 * This test must be configured to use a valid Sharepoint server. It tests all
 * the functionality of the connector by creating and deleting files and
 * folders in that repository.
 * </p>
 * @author Iván Zaera
 */
@Ignore
public class SharepointConnectionImplTest {

	public static final String HELLO_WORLD = "Hello world!";

	public static final String LIBRARY_NAME = "Documents";

	public static final String PASSWORD = "password";

	public static final String SERVER_ADDRESS = "liferay-20jf4ic";

	public static final int SERVER_PORT = 80;

	public static final String SERVER_PROTOCOL = "http";

	public static final String SITE_PATH = StringPool.BLANK;

	public static final String USERNAME = "Administrator";

	@Before
	public void setUp() throws Exception {
		_testId = System.currentTimeMillis();

		_setUpMocks();

		_deleteAllSharepointObjects();
	}

	@After
	public void tearDown() throws Exception {
		_deleteAllSharepointObjects();
	}

	@Test
	public void testAddFile() throws Exception {
		String fileName = "CreatedFile " + _testId + ".txt";

		String folderPath = StringPool.FORWARD_SLASH;

		String filePath = folderPath + fileName;

		_sharepointConnectionImpl.addFile(
			folderPath, fileName, StringPool.BLANK,
			_getHelloWorldInputStream());

		Assert.assertNotNull(_sharepointConnectionImpl.getObject(filePath));
	}

	private void _deleteAllSharepointObjects() throws SharepointException {
		List<SharepointObject> sharepointObjects =
			_sharepointConnectionImpl.getObjects(
				StringPool.FORWARD_SLASH, ObjectTypeFilter.ALL);

		for (SharepointObject sharepointObject : sharepointObjects) {
			_sharepointConnectionImpl.deleteObject(sharepointObject.getPath());
		}
	}

	private InputStream _getHelloWorldInputStream() throws IOException {
		return new ByteArrayInputStream(HELLO_WORLD.getBytes("UTF-8"));
	}

	private void _setUpMocks() {
		new HtmlUtil().setHtml(new HtmlImpl());
	}

	private SharepointConnectionImpl _sharepointConnectionImpl =
		new SharepointConnectionImpl(
			SERVER_PROTOCOL, SERVER_ADDRESS, SERVER_PORT, SITE_PATH,
			LIBRARY_NAME, USERNAME, PASSWORD);
	private long _testId;

}