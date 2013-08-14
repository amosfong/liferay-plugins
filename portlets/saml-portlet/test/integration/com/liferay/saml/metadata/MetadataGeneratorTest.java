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

package com.liferay.saml.metadata;

import com.liferay.saml.BaseSamlTestCase;
import com.liferay.saml.util.PortletPropsKeys;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.opensaml.saml2.metadata.EntityDescriptor;

import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Mika Koivisto
 */
@RunWith(PowerMockRunner.class)
public class MetadataGeneratorTest extends BaseSamlTestCase {

	@Test
	public void testMetadataGenerator() throws Exception {
		when(
			props.get(PortletPropsKeys.SAML_ENTITY_ID)
		).thenReturn(
			SP_ENTITY_ID
		);

		when(
			props.get(PortletPropsKeys.SAML_ROLE)
		).thenReturn(
			"sp"
		);

		MockHttpServletRequest mockHttpServletRequest =
			getMockHttpServletRequest(
				"http://localhost:8080/c/portal/saml/metadata");

		EntityDescriptor entityDescriptor =
			MetadataManagerUtil.getEntityDescriptor(mockHttpServletRequest);

		Assert.assertNotNull(entityDescriptor);
	}

}