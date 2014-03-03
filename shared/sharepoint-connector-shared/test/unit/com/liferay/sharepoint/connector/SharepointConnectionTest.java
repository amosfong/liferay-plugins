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
			getInputStream(_CONTENT_HELLO_WORLD));

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

		_sharepointConnection.updateFile(filePath, getInputStream(_CONTENT_BYE_WORLD));

		_sharepointConnection.checkInFile(
			filePath, new Date().toString(), CheckInType.MAJOR);

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNull(sharepointObject.getCheckedOutBy());

		InputStream inputStream = _sharepointConnection.getInputStream(
			sharepointObject);

		String inputStreamString = toString(inputStream);

		Assert.assertEquals(_CONTENT_BYE_WORLD, inputStreamString);
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

	@Test
	public void testDeleteFile() throws Exception {
		addTestSharepointObjects();

		String filePath = "/File " + _testId + ".txt";

		_sharepointConnection.deleteSharepointObject(filePath);

		Assert.assertNull(_sharepointConnection.getSharepointObject(filePath));
	}

	@Test
	public void testDeleteFolder() throws Exception {
		addTestSharepointObjects();

		String folderPath = "/Folder " + _testId;

		_sharepointConnection.deleteSharepointObject(folderPath);

		Assert.assertNull(
			_sharepointConnection.getSharepointObject(folderPath));
	}

	@Test
	public void testGetFileByPath() throws Exception {
		addTestSharepointObjects();

		String fileExtension = "txt";

		String fileName = "File " + _testId + "." + fileExtension;

		String filePath = "/" + fileName;

		SharepointObject sharepointObject =
			_sharepointConnection.getSharepointObject(filePath);

		Assert.assertNotNull(sharepointObject);

		Assert.assertTrue(sharepointObject.isFile());

		String expectedSharepointObjectURL = _SERVER_PROTOCOL + "://" +
			_SERVER_ADDRESS + _SITE_PATH + "/" + _LIBRARY_NAME + filePath;

		Assert.assertEquals(
			expectedSharepointObjectURL, sharepointObject.getURL().toString());

		Assert.assertEquals(filePath, sharepointObject.getPath());

		Assert.assertEquals("/", sharepointObject.getFolderPath());

		Assert.assertEquals(fileName, sharepointObject.getName());

		Assert.assertEquals(fileExtension, sharepointObject.getExtension());
	}

	@Test
	public void testGetFileByPathAndId() throws Exception {
		addTestSharepointObjects();

		String filePath = "/File " + _testId + ".txt";

		SharepointObject sharepointObject0 =
			_sharepointConnection.getSharepointObject(filePath);

		SharepointObject sharepointObject1 =
			_sharepointConnection.getSharepointObject(
				sharepointObject0.getId());

		Assert.assertEquals(
			sharepointObject0.getId(), sharepointObject1.getId());
	}

	@Test
	public void testGetFolderByPath() throws Exception {
		addTestSharepointObjects();

		String folderName = "Folder " + _testId;

		String folderPath = "/" + folderName;

		SharepointObject sharepointFolder =
			_sharepointConnection.getSharepointObject(folderPath);

		Assert.assertNotNull(sharepointFolder);

		Assert.assertTrue(sharepointFolder.isFolder());

		String expectedSharepointFolderURL = _SERVER_PROTOCOL + "://" +
			_SERVER_ADDRESS + _SITE_PATH + "/" + _LIBRARY_NAME + folderPath;

		Assert.assertEquals(
			expectedSharepointFolderURL, sharepointFolder.getURL().toString());

		Assert.assertEquals(folderPath, sharepointFolder.getPath());

		Assert.assertEquals("/", sharepointFolder.getFolderPath());

		Assert.assertEquals(folderName, sharepointFolder.getName());

		Assert.assertEquals("", sharepointFolder.getExtension());
	}

	@Test
	public void testGetObjectsCount() throws Exception {
		addTestSharepointObjects();

		String folderPath = "/Folder " + _testId;

		Assert.assertEquals(
			4,
			_sharepointConnection.getSharepointObjectsCount(
				folderPath, ObjectTypeFilter.ALL));

		Assert.assertEquals(
			2,
			_sharepointConnection.getSharepointObjectsCount(
				folderPath, ObjectTypeFilter.FILES));

		Assert.assertEquals(
			2,
			_sharepointConnection.getSharepointObjectsCount(
				folderPath, ObjectTypeFilter.FOLDERS));

		Assert.assertEquals(
			4,
			_sharepointConnection.getSharepointObjectsCount(
				StringPool.FORWARD_SLASH, ObjectTypeFilter.ALL));
	}

	@Test
	public void testGetRootFolderById() throws Exception {
		SharepointObject rootFolder =
			_sharepointConnection.getSharepointObject(
				SharepointConnectionImpl.SHAREPOINT_ROOT_FOLDER_ID);

		assertValidRootFolder(rootFolder);
	}

	@Test
	public void testGetRootFolderByPath() throws Exception {
		SharepointObject rootFolder = _sharepointConnection.getSharepointObject(
			"/");

		assertValidRootFolder(rootFolder);
	}

	@Test
	public void testGetSharepointObjectInputStream() throws Exception {
		addTestSharepointObjects();

		String filePath = "/File " + _testId + ".txt";

		SharepointObject sharepointObject =
			_sharepointConnection.getSharepointObject(filePath);

		InputStream sharepointObjectContent =
			_sharepointConnection.getInputStream(sharepointObject);

		String sharepointObjectContentString = toString(
			sharepointObjectContent);

		Assert.assertEquals(_CONTENT_HELLO_WORLD, sharepointObjectContentString);
	}

	protected void addTestSharepointObjects()
		throws IOException, SharepointException {

		String fileName = "File " + _testId + ".txt";

		String fileName2 = "File2 " + _testId + ".txt";

		String folderName = "Folder " + _testId;

		String folderName2 = "Folder2 " + _testId;

		_sharepointConnection.addFile(
			StringPool.FORWARD_SLASH, fileName, StringPool.BLANK,
			getInputStream(_CONTENT_HELLO_WORLD));

		_sharepointConnection.addFile(
			StringPool.FORWARD_SLASH, fileName2, StringPool.BLANK,
			getInputStream(_CONTENT_HELLO_WORLD));

		_sharepointConnection.addFolder(StringPool.FORWARD_SLASH, folderName);

		_sharepointConnection.addFile(
			StringPool.FORWARD_SLASH + folderName, "Sub" + fileName,
			StringPool.BLANK, getInputStream(_CONTENT_HELLO_WORLD));

		_sharepointConnection.addFile(
			StringPool.FORWARD_SLASH + folderName, "Sub" + fileName2,
			StringPool.BLANK, getInputStream(_CONTENT_HELLO_WORLD));

		_sharepointConnection.addFolder(
			StringPool.FORWARD_SLASH + folderName, "Sub" + folderName);

		_sharepointConnection.addFolder(
			StringPool.FORWARD_SLASH + folderName, "Sub" + folderName2);

		_sharepointConnection.addFolder(StringPool.FORWARD_SLASH, folderName2);
	}

	protected void assertValidRootFolder(SharepointObject rootFolder) {
		Assert.assertNotNull(rootFolder);

		Assert.assertTrue(rootFolder.isFolder());

		String expectedRootFolderURL = _SERVER_PROTOCOL + "://" +
			_SERVER_ADDRESS + _SITE_PATH + "/" + _LIBRARY_NAME;

		Assert.assertEquals(
			expectedRootFolderURL, rootFolder.getURL().toString());

		Assert.assertEquals("/", rootFolder.getPath());

		Assert.assertEquals("/", rootFolder.getFolderPath());

		Assert.assertEquals("/", rootFolder.getName());

		Assert.assertEquals("", rootFolder.getExtension());

		Assert.assertEquals(
			SharepointConnectionImpl.SHAREPOINT_ROOT_FOLDER_ID,
			rootFolder.getId());
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
		return new ByteArrayInputStream(content.getBytes(StringPool.UTF8));
	}

	protected void setUpMocks() {
		FileUtil fileUtil = new FileUtil();

		fileUtil.setFile(new FileImpl());

		HtmlUtil htmlUtil = new HtmlUtil();

		htmlUtil.setHtml(new HtmlImpl());
	}

	protected String toString(InputStream inputStream) throws IOException {
		byte[] bytes = FileUtil.getBytes(inputStream);

		return new String(bytes, StringPool.UTF8);
	}

	private static final String _CONTENT_BYE_WORLD = "Bye world!";

	private static final String _CONTENT_HELLO_WORLD = "Hello world!";

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