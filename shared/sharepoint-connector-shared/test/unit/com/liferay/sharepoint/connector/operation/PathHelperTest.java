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
	public void testBuildPathWhenNonRootFolder() {
		Assert.assertEquals(
			"/folderPath/name", pathHelper.buildPath("/folderPath", "name"));
	}

	@Test
	public void testBuildPathWhenRootFolder() {
		Assert.assertEquals("/name", pathHelper.buildPath("/", "name"));
	}

	@Test
	public void testConstructorFailsWhenInvalidSitePaths() {
		try {
			pathHelper = new PathHelper("", "/");
			Assert.fail("IllegalArgumentException not thrown for site path /");
		}
		catch (IllegalArgumentException iae) {
		}

		try {
			pathHelper = new PathHelper("", "sitePathWithoutLeadingSlash");
			Assert.fail(
				"IllegalArgumentException not thrown for site path without " +
			"leading /");
		}
		catch (IllegalArgumentException iae) {
		}

		try {
			pathHelper = new PathHelper("", "/sitePathWithTrailingSlash/");
			Assert.fail(
				"IllegalArgumentException not thrown for site path with " +
			"trailing /");
		}
		catch (IllegalArgumentException iae) {
		}
	}

	@Test
	public void testGetExtensionWhenExtensionNotPresent() {
		Assert.assertEquals("", pathHelper.getExtension("/name."));
		Assert.assertEquals("", pathHelper.getExtension("/name"));
	}

	@Test
	public void testGetExtensionWhenExtensionPresent() {
		Assert.assertEquals("ext", pathHelper.getExtension("/name.ext"));
	}

	@Test
	public void testGetLibraryName() {
		Assert.assertEquals(_LIBRARY_NAME, pathHelper.getLibraryName());
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("name.ext", pathHelper.getName("/name.ext"));
	}

	@Test
	public void testGetNameWithoutExtensionWhenExtensionNotPresent() {
		Assert.assertEquals(
			"name", pathHelper.getNameWithoutExtension("/name."));
		Assert.assertEquals(
			"name", pathHelper.getNameWithoutExtension("/name"));
	}

	@Test
	public void testGetNameWithoutExtensionWhenExtensionPresent() {
		Assert.assertEquals(
			"name", pathHelper.getNameWithoutExtension("/name.ext"));
	}

	@Test
	public void testGetParentFolderPathWhenNonRootFolder() {
		Assert.assertEquals(
			"/folder", pathHelper.getParentFolderPath("/folder/name"));
	}

	@Test
	public void testGetParentFolderPathWhenRootFolder() {
		Assert.assertEquals("/", pathHelper.getParentFolderPath("/name"));
	}

	@Test
	public void testGetSitePath() {
		Assert.assertEquals(_SITE_PATH, pathHelper.getSitePath());
	}

	@Test
	public void testToFullPathWhenNonRootPath() {
		Assert.assertEquals(
			_SITE_PATH + StringPool.SLASH + _LIBRARY_NAME + "/folder/name",
			pathHelper.toFullPath("/folder/name"));
	}

	@Test
	public void testToFullPathWhenRootPath() {
		Assert.assertEquals(
			_SITE_PATH + StringPool.SLASH + _LIBRARY_NAME,
			pathHelper.toFullPath("/"));
	}

	private static final String _LIBRARY_NAME = "libraryName";

	private static final String _SITE_PATH = "/sitePath";

	private PathHelper pathHelper = new PathHelper(_LIBRARY_NAME, _SITE_PATH);

}