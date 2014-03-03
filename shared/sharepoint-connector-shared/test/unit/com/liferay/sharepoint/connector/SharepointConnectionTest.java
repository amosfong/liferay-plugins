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
		setUpMocks();

		deleteSharepointObjects();
	}

	@After
	public void tearDown() throws Exception {
		deleteSharepointObjects();
	}

	@Test
	public void testAddFile() throws Exception {
		String folderPath = StringPool.SLASH;
		String fileName = "CreatedFile " + _timestamp + ".txt";

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

		_sharepointConnection.addFolder(StringPool.SLASH, folderName);

		String folderPath = StringPool.SLASH + folderName;

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(folderPath));
	}

	@Test
	public void testCheckOutFileThenUpdateFileThenCheckInFile()
		throws Exception {

		addSharepointObjects();

		String filePath = "/File " + _timestamp + ".txt";

		_sharepointConnection.checkOutFile(filePath);

		SharepointObject sharepointObject =
			_sharepointConnection.getSharepointObject(filePath);

		Assert.assertNotNull(sharepointObject.getCheckedOutBy());

		_sharepointConnection.updateFile(
			filePath, getInputStream(_CONTENT_BYE_WORLD));

		_sharepointConnection.checkInFile(
			filePath, String.valueOf(new Date()), CheckInType.MAJOR);

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNull(sharepointObject.getCheckedOutBy());

		InputStream inputStream = _sharepointConnection.getInputStream(
			sharepointObject);

		String inputStreamString = toString(inputStream);

		Assert.assertEquals(_CONTENT_BYE_WORLD, inputStreamString);
	}

	@Test
	public void testCheckOutThenCancelCheckOut() throws Exception {
		addSharepointObjects();

		String filePath =  "/File " + _timestamp + ".txt";

		_sharepointConnection.checkOutFile(filePath);

		SharepointObject sharepointObject =
			_sharepointConnection.getSharepointObject(filePath);

		Assert.assertNotNull(sharepointObject.getCheckedOutBy());

		_sharepointConnection.cancelCheckOutFile(filePath);

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNull(sharepointObject.getCheckedOutBy());
	}

	@Test
	public void testCheckOutThenCheckIn() throws Exception {
		addSharepointObjects();

		String filePath =  "/File " + _timestamp + ".txt";

		_sharepointConnection.checkOutFile(filePath);

		SharepointObject sharepointObject =
			_sharepointConnection.getSharepointObject(filePath);

		Assert.assertNotNull(sharepointObject.getCheckedOutBy());

		_sharepointConnection.checkInFile(
			filePath, String.valueOf(new Date()), CheckInType.MAJOR);

		sharepointObject = _sharepointConnection.getSharepointObject(filePath);

		Assert.assertNull(sharepointObject.getCheckedOutBy());
	}

	@Test
	public void testCopyFile() throws Exception {
		addSharepointObjects();

		String copiedFilePath =
			"/Folder " + _timestamp + "/CopiedFile " +
				_timestamp + ".txt";

		Assert.assertNull(
			_sharepointConnection.getSharepointObject(copiedFilePath));

		String filePath = "/File " + _timestamp + ".txt";

		_sharepointConnection.copySharepointObject(filePath, copiedFilePath);

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(copiedFilePath));
		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(filePath));
	}

	@Test
	public void testCopyFolder() throws Exception {
		addSharepointObjects();

		String folderPath = "/Folder1 " + _timestamp;
		String copiedFolderPath =
			"/Folder2 " + _timestamp + "/CopiedFolder " +
				_timestamp;

		_sharepointConnection.copySharepointObject(
			folderPath, copiedFolderPath);

		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				folderPath + "/Subfile1 " + _timestamp + ".txt"));
		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				folderPath + "/Subfile2 " + _timestamp + ".txt"));
		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				folderPath + "/Subfolder1 " + _timestamp));
		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				folderPath + "/Subfolder2 " + _timestamp));
		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				copiedFolderPath + "/Subfile1 " + _timestamp +
					".txt"));
		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				copiedFolderPath + "/Subfile2 " + _timestamp +
					".txt"));
		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				copiedFolderPath + "/Subfolder1 " + _timestamp));
		Assert.assertNotNull(
			_sharepointConnection.getSharepointObject(
				copiedFolderPath + "/Subfolder2 " + _timestamp));
	}

	@Test
	public void testDeleteFile() throws Exception {
		addSharepointObjects();

		String filePath = "/File " + _timestamp + ".txt";

		_sharepointConnection.deleteSharepointObject(filePath);

		Assert.assertNull(_sharepointConnection.getSharepointObject(filePath));
	}

	@Test
	public void testDeleteFolder() throws Exception {
		addSharepointObjects();

		String folderPath = "/Folder " + _timestamp;

		_sharepointConnection.deleteSharepointObject(folderPath);

		Assert.assertNull(
			_sharepointConnection.getSharepointObject(folderPath));
	}

	@Test
	public void testGetFileByPath() throws Exception {
		addSharepointObjects();

		String fileExtension = "txt";

		String fileName =
			"File " + _timestamp + "." + fileExtension;

		String filePath = StringPool.SLASH + fileName;

		SharepointObject sharepointObject =
			_sharepointConnection.getSharepointObject(filePath);

		Assert.assertNotNull(sharepointObject);
		Assert.assertEquals(fileExtension, sharepointObject.getExtension());
		Assert.assertEquals(StringPool.SLASH, sharepointObject.getFolderPath());
		Assert.assertEquals(fileName, sharepointObject.getName());
		Assert.assertEquals(filePath, sharepointObject.getPath());
		Assert.assertEquals(
			_SERVER_PROTOCOL + "://" + _SERVER_ADDRESS + _SITE_PATH +
				StringPool.SLASH + _LIBRARY_NAME + filePath,
			String.valueOf(sharepointObject.getURL()));
		Assert.assertTrue(sharepointObject.isFile());
	}

	@Test
	public void testGetFileByPathAndSharepointObjectId() throws Exception {
		addSharepointObjects();

		String filePath = "/File " + _timestamp + ".txt";

		SharepointObject sharepointObject1 =
			_sharepointConnection.getSharepointObject(filePath);
		SharepointObject sharepointObject2 =
			_sharepointConnection.getSharepointObject(
				sharepointObject1.getSharepointObjectId());

		Assert.assertEquals(
			sharepointObject1.getSharepointObjectId(),
			sharepointObject2.getSharepointObjectId());
	}

	@Test
	public void testGetFolderByPath() throws Exception {
		addSharepointObjects();

		String folderName = "Folder " + _timestamp;

		String folderPath = StringPool.SLASH + folderName;

		SharepointObject sharepointObject =
			_sharepointConnection.getSharepointObject(folderPath);

		Assert.assertNotNull(sharepointObject);
		Assert.assertEquals(StringPool.BLANK, sharepointObject.getExtension());
		Assert.assertEquals(StringPool.SLASH, sharepointObject.getFolderPath());
		Assert.assertEquals(folderName, sharepointObject.getName());
		Assert.assertEquals(folderPath, sharepointObject.getPath());
		Assert.assertEquals(
			_SERVER_PROTOCOL + "://" + _SERVER_ADDRESS + _SITE_PATH +
				StringPool.SLASH + _LIBRARY_NAME + folderPath,
			String.valueOf(sharepointObject.getURL()));
		Assert.assertTrue(sharepointObject.isFolder());
	}

	@Test
	public void testGetObjectsCount() throws Exception {
		addSharepointObjects();

		Assert.assertEquals(
			4,
			_sharepointConnection.getSharepointObjectsCount(
				StringPool.SLASH, ObjectTypeFilter.ALL));

		String folderPath = "/Folder " + _timestamp;

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
	}

	@Test
	public void testGetRootFolderBySharepointObjectId() throws Exception {
		SharepointObject rootFolderSharepointObject =
			_sharepointConnection.getSharepointObject(
				SharepointConnectionImpl.
					SHAREPOINT_ROOT_FOLDER_SHAREPOINT_OBJECT_ID);

		assertRootFolderSharepointObject(rootFolderSharepointObject);
	}

	@Test
	public void testGetRootFolderByPath() throws Exception {
		SharepointObject rootFolderSharepointObject =
			_sharepointConnection.getSharepointObject(StringPool.SLASH);

		assertRootFolderSharepointObject(rootFolderSharepointObject);
	}

	@Test
	public void testGetSharepointObjectInputStream() throws Exception {
		addSharepointObjects();

		SharepointObject sharepointObject =
			_sharepointConnection.getSharepointObject(
				"/File " + _timestamp + ".txt");

		InputStream inputStream = _sharepointConnection.getInputStream(
			sharepointObject);

		Assert.assertEquals(_CONTENT_HELLO_WORLD, toString(inputStream));
	}

	protected void addSharepointObjects()
		throws IOException, SharepointException {

		String fileName1 = "File1 " + _timestamp + ".txt";

		_sharepointConnection.addFile(
			StringPool.SLASH, fileName1, StringPool.BLANK,
			getInputStream(_CONTENT_HELLO_WORLD));

		String fileName2 = "File2 " + _timestamp + ".txt";

		_sharepointConnection.addFile(
			StringPool.SLASH, fileName2, StringPool.BLANK,
			getInputStream(_CONTENT_HELLO_WORLD));

		String folderName1 = "Folder1 " + _timestamp;

		_sharepointConnection.addFolder(StringPool.SLASH, folderName1);

		_sharepointConnection.addFile(
			StringPool.SLASH + folderName1, "Sub" + fileName1,
			StringPool.BLANK, getInputStream(_CONTENT_HELLO_WORLD));

		_sharepointConnection.addFile(
			StringPool.SLASH + folderName1, "Sub" + fileName2,
			StringPool.BLANK, getInputStream(_CONTENT_HELLO_WORLD));

		_sharepointConnection.addFolder(
			StringPool.SLASH + folderName1, "Sub" + folderName1);

		String folderName2 = "Folder2 " + _timestamp;

		_sharepointConnection.addFolder(
			StringPool.SLASH + folderName1, "Sub" + folderName2);

		_sharepointConnection.addFolder(StringPool.SLASH, folderName2);
	}

	protected void assertRootFolderSharepointObject(
		SharepointObject rootFolderSharepointObject) {

		Assert.assertNotNull(rootFolderSharepointObject);
		Assert.assertEquals(StringPool.BLANK, rootFolderSharepointObject.getExtension());
		Assert.assertEquals(
			StringPool.SLASH, rootFolderSharepointObject.getFolderPath());
		Assert.assertEquals(
			StringPool.SLASH, rootFolderSharepointObject.getName());
		Assert.assertEquals(
			StringPool.SLASH, rootFolderSharepointObject.getPath());
		Assert.assertEquals(
			SharepointConnectionImpl.SHAREPOINT_ROOT_FOLDER_SHAREPOINT_OBJECT_ID,
			rootFolderSharepointObject.getSharepointObjectId());
		Assert.assertEquals(
			_SERVER_PROTOCOL + "://" + _SERVER_ADDRESS + _SITE_PATH +
				StringPool.SLASH + _LIBRARY_NAME,
			String.valueOf(rootFolderSharepointObject.getURL()));
		Assert.assertTrue(rootFolderSharepointObject.isFolder());
	}

	protected void deleteSharepointObjects() throws SharepointException {
		List<SharepointObject> sharepointObjects =
			_sharepointConnection.getSharepointObjects(
				StringPool.SLASH, ObjectTypeFilter.ALL);

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
	private long _timestamp = System.currentTimeMillis();

}