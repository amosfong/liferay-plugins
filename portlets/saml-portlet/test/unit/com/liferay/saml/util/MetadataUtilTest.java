/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.saml.util;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.BeforeClass;
import org.opensaml.xml.parse.BasicParserPool;
/**
 * @author Mika Koivisto
 */
public class MetadataUtilTest {

	@BeforeClass
	public static void setUp() throws Exception {
		OpenSamlBootstrap.bootstrap();

		new MetadataUtil().setParserPool(new BasicParserPool());
	}

	@Test
	public void testParseMetadata() throws Exception {
		InputStream metadataInputStream = getClass().getResourceAsStream(
			"dependencies/entities-descriptor.xml");

		String metadata = MetadataUtil.parseMetadataXml(
			metadataInputStream, "https://saml.liferay.com/shibboleth");

		Assert.assertNotNull("Can't find metadata", metadata);
	}

}
