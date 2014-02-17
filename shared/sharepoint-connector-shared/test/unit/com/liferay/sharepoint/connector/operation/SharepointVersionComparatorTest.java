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

import com.liferay.sharepoint.connector.SharepointVersion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Iván Zaera
 */
public class SharepointVersionComparatorTest {

	@Before
	public void setUp() {
		_sharepointVersionComparator = new SharepointVersionComparator();
	}

	@Test
	public void testEquals() {
		SharepointVersion sharepointVersion1 = _createSharepointVersion("1.1");

		SharepointVersion sharepointVersion2 = _createSharepointVersion("1.1");

		Assert.assertEquals(
			0,
			_sharepointVersionComparator.compare(
				sharepointVersion1, sharepointVersion2));
	}

	@Test
	public void testGreaterThanMajor() {
		SharepointVersion sharepointVersion1 = _createSharepointVersion("8.1");

		SharepointVersion sharepointVersion2 = _createSharepointVersion("9.0");

		Assert.assertEquals(
			1,
			_sharepointVersionComparator.compare(
				sharepointVersion1, sharepointVersion2));
	}

	@Test
	public void testGreaterThanMinor() {
		SharepointVersion sharepointVersion1 = _createSharepointVersion("9.0");

		SharepointVersion sharepointVersion2 = _createSharepointVersion("9.1");

		Assert.assertEquals(
			1,
			_sharepointVersionComparator.compare(
				sharepointVersion1, sharepointVersion2));
	}

	@Test
	public void testLessThanMajor() {
		SharepointVersion sharepointVersion1 = _createSharepointVersion("9.0");

		SharepointVersion sharepointVersion2 = _createSharepointVersion("8.1");

		Assert.assertEquals(
			-1,
			_sharepointVersionComparator.compare(
				sharepointVersion1, sharepointVersion2));
	}

	@Test
	public void testLessThanMinor() {
		SharepointVersion sharepointVersion1 = _createSharepointVersion("9.1");

		SharepointVersion sharepointVersion2 = _createSharepointVersion("9.0");

		Assert.assertEquals(
			-1,
			_sharepointVersionComparator.compare(
				sharepointVersion1, sharepointVersion2));
	}

	private SharepointVersion _createSharepointVersion(String version) {
		return new SharepointVersion(null, null, null, null, 0, null, version);
	}

	private SharepointVersionComparator _sharepointVersionComparator;

}