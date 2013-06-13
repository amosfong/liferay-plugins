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

package com.liferay.saml.credential;

import com.liferay.saml.BaseSamlTestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.opensaml.xml.security.CriteriaSet;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.criteria.EntityIDCriteria;

import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Mika Koivisto
 */
@RunWith(PowerMockRunner.class)
public class CredentialResolverTest extends BaseSamlTestCase {

	@Test
	public void testResolveIdpCredential() throws Exception {
		EntityIDCriteria entityIdCriteria = new EntityIDCriteria(IDP_ENTITY_ID);

		CriteriaSet criteriaSet = new CriteriaSet();

		criteriaSet.add(entityIdCriteria);

		Credential credential = credentialResolver.resolveSingle(criteriaSet);

		Assert.assertNotNull(credential);
	}

	@Test
	public void testResolveNonexistingCredential() throws Exception {
		EntityIDCriteria entityIdCriteria = new EntityIDCriteria("na");

		CriteriaSet criteriaSet = new CriteriaSet();

		criteriaSet.add(entityIdCriteria);

		Credential credential = credentialResolver.resolveSingle(criteriaSet);

		Assert.assertNull(credential);
	}

	@Test
	public void testResolveSpCredential() throws Exception {
		EntityIDCriteria entityIdCriteria = new EntityIDCriteria(SP_ENTITY_ID);

		CriteriaSet criteriaSet = new CriteriaSet();

		criteriaSet.add(entityIdCriteria);

		Credential credential = credentialResolver.resolveSingle(criteriaSet);

		Assert.assertNotNull(credential);
	}

}