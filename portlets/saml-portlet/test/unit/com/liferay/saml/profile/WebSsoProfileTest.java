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

package com.liferay.saml.profile;

import com.liferay.portal.kernel.exception.PortalException;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mika Koivisto
 */
public class WebSsoProfileTest {

	@Before
	public void setUp() {
		_webSsoProfileImpl = new WebSsoProfileImpl();
	}

	@Test
	public void testVerifyNotOnOrAfterDateTimeLessThanNow() {
		DateTime notOnOrAfter = new DateTime(DateTimeZone.UTC);

		notOnOrAfter = notOnOrAfter.minusMillis(4000);

		try {
			_webSsoProfileImpl.verifyNotOnOrAfterDateTime(3000, notOnOrAfter);

			Assert.fail("Expected exception");
		}
		catch (PortalException pe) {
		}
	}

	@Test
	public void testVerifyNotOnOrAfterDateTimeLessThanNowSmallerSkew() {
		DateTime notOnOrAfter = new DateTime(DateTimeZone.UTC);

		notOnOrAfter = notOnOrAfter.minusMillis(300);

		try {
			_webSsoProfileImpl.verifyNotOnOrAfterDateTime(3000, notOnOrAfter);
		}
		catch (PortalException pe) {
			Assert.fail("Verifying date failed");
		}
	}

	@Test
	public void testVerifyNotOnOrAfterDateTimeLessThanSkew() {
		DateTime notOnOrAfter = new DateTime(DateTimeZone.UTC);

		notOnOrAfter = notOnOrAfter.plusMillis(200);

		try {
			_webSsoProfileImpl.verifyNotOnOrAfterDateTime(3000, notOnOrAfter);
		}
		catch (PortalException pe) {
			Assert.fail("Verifying date failed");
		}
	}

	@Test
	public void testVerifyNotOnOrAfterDateTimeMoreThanSkew() {
		DateTime notOnOrAfter = new DateTime(DateTimeZone.UTC);

		notOnOrAfter = notOnOrAfter.plusMillis(50000);

		try {
			_webSsoProfileImpl.verifyNotOnOrAfterDateTime(3000, notOnOrAfter);
		}
		catch (PortalException pe) {
			Assert.fail("Verifying date failed");
		}
	}

	private WebSsoProfileImpl _webSsoProfileImpl;

}