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

package com.liferay.saml.profile;

import com.liferay.portal.kernel.exception.PortalException;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Mika Koivisto
 */
public class WebSsoProfileTest {

	@Test
	public void testVerifyNotOnOrAfterDateTimeLessThanNow() {
		DateTime dateTime = new DateTime(DateTimeZone.UTC);

		dateTime = dateTime.minusMillis(4000);

		try {
			_webSsoProfileImpl.verifyNotOnOrAfterDateTime(3000, dateTime);

			Assert.fail();
		}
		catch (PortalException pe) {
		}
	}

	@Test
	public void testVerifyNotOnOrAfterDateTimeLessThanNowSmallerSkew() {
		DateTime dateTime = new DateTime(DateTimeZone.UTC);

		dateTime = dateTime.minusMillis(300);

		try {
			_webSsoProfileImpl.verifyNotOnOrAfterDateTime(3000, dateTime);
		}
		catch (PortalException pe) {
			Assert.fail();
		}
	}

	@Test
	public void testVerifyNotOnOrAfterDateTimeLessThanSkew() {
		DateTime dateTime = new DateTime(DateTimeZone.UTC);

		dateTime = dateTime.plusMillis(200);

		try {
			_webSsoProfileImpl.verifyNotOnOrAfterDateTime(3000, dateTime);
		}
		catch (PortalException pe) {
			Assert.fail();
		}
	}

	@Test
	public void testVerifyNotOnOrAfterDateTimeMoreThanSkew() {
		DateTime dateTime = new DateTime(DateTimeZone.UTC);

		dateTime = dateTime.plusMillis(50000);

		try {
			_webSsoProfileImpl.verifyNotOnOrAfterDateTime(3000, dateTime);
		}
		catch (PortalException pe) {
			Assert.fail();
		}
	}

	private WebSsoProfileImpl _webSsoProfileImpl = new WebSsoProfileImpl();

}