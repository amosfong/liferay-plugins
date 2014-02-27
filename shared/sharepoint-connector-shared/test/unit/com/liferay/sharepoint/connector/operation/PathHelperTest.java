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

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class PathHelperTest {

	@Test
	public void testBuildPathWithNonrootFolder() {
		Assert.assertEquals(
			"/folderPath/name", pathHelper.buildPath("/folderPath", "name"));
	}

	@Test
	public void testBuildPathWithRootFolder() {
		Assert.assertEquals(
			"/name", pathHelper.buildPath(StringPool.SLASH, "name"));
	}

	@Test
	public void testGetExtensionWhithMissingExtension() {
		Assert.assertEquals(
			StringPool.BLANK, pathHelper.getExtension("/name."));
		Assert.assertEquals(StringPool.BLANK, pathHelper.getExtension("/name"));
	}

	@Test
	public void testGetExtensionWithExtension() {
		Assert.assertEquals("ext", pathHelper.getExtension("/name.ext"));
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("name.ext", pathHelper.getName("/name.ext"));
	}

	@Test
	public void testGetNameWithoutExtensionWithExtension() {
		Assert.assertEquals(
			"name", pathHelper.getNameWithoutExtension("/name.ext"));
	}

	@Test
	public void testGetNameWithoutExtensionWithMissingExtension() {
		Assert.assertEquals(
			"name", pathHelper.getNameWithoutExtension("/name."));
		Assert.assertEquals(
			"name", pathHelper.getNameWithoutExtension("/name"));
	}

	@Test
	public void testGetParentFolderPathWithNonrootFolder() {
		Assert.assertEquals(
			"/folder", pathHelper.getParentFolderPath("/folder/name"));
	}

	@Test
	public void testGetParentFolderPathWithRootFolder() {
		Assert.assertEquals("/", pathHelper.getParentFolderPath("/name"));
	}

	private PathHelper pathHelper = new PathHelper();

}