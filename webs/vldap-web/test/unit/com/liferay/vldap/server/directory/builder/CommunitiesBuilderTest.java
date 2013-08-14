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
package com.liferay.vldap.server.directory.builder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author William Newbury
 */
import org.powermock.modules.junit4.PowerMockRunner;
@RunWith(PowerMockRunner.class)
public class CommunitiesBuilderTest extends GeneralBuilderTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_builder = new CommunitiesBuilder();
	}

	@Test
	public void testBuildDirectories() throws Exception {
		super.testBuildDirectoriesValidFilter();
	}

}