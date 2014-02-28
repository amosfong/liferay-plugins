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

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.util.HtmlImpl;
import com.liferay.sharepoint.connector.SharepointConnection.CheckInType;
import com.liferay.sharepoint.connector.SharepointConnection.ObjectTypeFilter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
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
public class SharepointConnectionTest {

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
			folderPath, fileName, StringPool.BLANK,
			getInputStream(_HELLO_WORLD));

		String filePath = folderPath + fileName;

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(filePath));
	}

	@Test
	public void testAddFolder() throws Exception {
		String folderName = "CreatedFolder " + System.currentTimeMillis();

		String folderPath = StringPool.FORWARD_SLASH + folderName;

		_sharepointConnection.addFolder(StringPool.FORWARD_SLASH, folderName);

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(folderPath));
	}

	@Test
	public void testCheckOutFileThenUpdateFileThenCheckInFile()
		throws Exception {

		addTestSharepointObjects();

		String filePath = "/File " + _testId + ".txt";

		_sharepointConnection.checkOutFile(filePath);

		SharepointObject sharepointObject = null;

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNotNull(sharepointObject.getCheckedOutBy());

		_sharepointConnection.updateFile(filePath, getInputStream(_BYE_WORLD));

		_sharepointConnection.checkInFile(
			filePath, new Date().toString(), CheckInType.MAJOR);

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNull(sharepointObject.getCheckedOutBy());

		InputStream inputStream = _sharepointConnection.getInputStream(
			sharepointObject);

		String inputStreamString = toString(inputStream);

		Assert.assertEquals(_BYE_WORLD, inputStreamString);
	}

	@Test
	public void testCheckOutThenCancelCheckOut() throws Exception {
		addTestSharepointObjects();

		String filePath =  "/File " + _testId + ".txt";

		_sharepointConnection.checkOutFile(filePath);

		SharepointObject sharepointObject = null;

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNotNull(sharepointObject.getCheckedOutBy());

		_sharepointConnection.cancelCheckOutFile(filePath);

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNull(sharepointObject.getCheckedOutBy());
	}

	@Test
	public void testCheckOutThenCheckIn() throws Exception {
		addTestSharepointObjects();

		String filePath =  "/File " + _testId + ".txt";

		_sharepointConnection.checkOutFile(filePath);

		SharepointObject sharepointObject = null;

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNotNull(sharepointObject.getCheckedOutBy());

		_sharepointConnection.checkInFile(
			filePath, new Date().toString(), CheckInType.MAJOR);

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNull(sharepointObject.getCheckedOutBy());
	}

	@Test
	public void testCopyFile() throws Exception {
		addTestSharepointObjects();

		String filePath = "/File " + _testId + ".txt";

		String copiedFilePath =
			"/Folder " + _testId + "/CopiedFile " + _testId + ".txt";

		Assert.assertNull(
			_sharepointConnection.getSharepointObject(copiedFilePath));

		_sharepointConnection.copySharepointObject(filePath, copiedFilePath);

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(copiedFilePath));

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(filePath));
	}

	@Test
	public void testCopyFolder() throws Exception {
		addTestSharepointObjects();

		String folderPath = "/Folder " + _testId;

		String copiedFolderPath =
			"/Folder2 " + _testId + "/CopiedFolder " + _testId;

		_sharepointConnection.copySharepointObject(
			folderPath, copiedFolderPath);

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				folderPath + "/SubFile " + _testId + ".txt"));

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				folderPath + "/SubFile2 " + _testId + ".txt"));

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				folderPath + "/SubFolder " + _testId));

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				folderPath + "/SubFolder2 " + _testId));

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				copiedFolderPath + "/SubFile " + _testId + ".txt"));

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				copiedFolderPath + "/SubFile2 " + _testId + ".txt"));

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				copiedFolderPath + "/SubFolder " + _testId));

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				copiedFolderPath + "/SubFolder2 " + _testId));
	}

	protected void addTestSharepointObjects()
		throws IOException, SharepointException {

		String fileName = "File " + _testId + ".txt";

		String fileName2 = "File2 " + _testId + ".txt";

		String folderName = "Folder " + _testId;

		String folderName2 = "Folder2 " + _testId;

		_sharepointConnection.addFile(
			StringPool.FORWARD_SLASH, fileName, StringPool.BLANK,
			getInputStream(_HELLO_WORLD));

		_sharepointConnection.addFile(
			StringPool.FORWARD_SLASH, fileName2, StringPool.BLANK,
			getInputStream(_HELLO_WORLD));

		_sharepointConnection.addFolder(StringPool.FORWARD_SLASH, folderName);

		_sharepointConnection.addFile(
			StringPool.FORWARD_SLASH + folderName, "Sub" + fileName,
			StringPool.BLANK, getInputStream(_HELLO_WORLD));

		_sharepointConnection.addFile(
			StringPool.FORWARD_SLASH + folderName, "Sub" + fileName2,
			StringPool.BLANK, getInputStream(_HELLO_WORLD));

		_sharepointConnection.addFolder(
			StringPool.FORWARD_SLASH + folderName, "Sub" + folderName);

		_sharepointConnection.addFolder(
			StringPool.FORWARD_SLASH + folderName, "Sub" + folderName2);

		_sharepointConnection.addFolder(StringPool.FORWARD_SLASH, folderName2);
	}

	protected void deleteSharepointObjects() throws SharepointException {
		List<SharepointObject> sharepointObjects =
			_sharepointConnection.getSharepointObjects(
				StringPool.FORWARD_SLASH, ObjectTypeFilter.ALL);

		for (SharepointObject sharepointObject : sharepointObjects) {
			_sharepointConnection.deleteSharepointObject(
				sharepointObject.getPath());
		}
	}

	protected InputStream getInputStream(String content) throws IOException {
		return new ByteArrayInputStream(content.getBytes("UTF-8"));
	}

	protected void setUpMocks() {
		HtmlUtil htmlUtil = new HtmlUtil();

		htmlUtil.setHtml(new HtmlImpl());

		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(new FileImpl());
	}

	protected String toString(InputStream inputStream) throws IOException {
		byte[] bytes = FileUtil.getBytes(inputStream);

		return new String(bytes, "UTF-8");
	}

	private static final String _BYE_WORLD = "Bye world!";

	private static final String _HELLO_WORLD = "Hello world!";

	private static final String _LIBRARY_NAME = "Documents";

	private static final String _PASSWORD = "password";

	private static final String _SERVER_ADDRESS = "liferay-20jf4ic";

	private static final int _SERVER_PORT = 80;

	private static final String _SERVER_PROTOCOL = "http";

	private static final String _SITE_PATH = StringPool.BLANK;

	private static final String _USERNAME = "Administrator";

	private SharepointConnection _sharepointConnection =
		SharepointConnectionFactory.getInstance(
			_SERVER_PROTOCOL, _SERVER_ADDRESS, _SERVER_PORT, _SITE_PATH,
			_LIBRARY_NAME, _USERNAME, _PASSWORD);
	private long _testId;

}