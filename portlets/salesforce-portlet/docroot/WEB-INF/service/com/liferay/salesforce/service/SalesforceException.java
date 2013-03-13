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

package com.liferay.salesforce.service;

import com.liferay.portal.kernel.util.StringPool;

import com.sforce.soap.partner.StatusCode;

/**
 * @author Michael C. Han
 */
public class SalesforceException extends Exception {

	public SalesforceException(com.sforce.soap.partner.Error error) {
		this(error.getStatusCode(), error.getMessage());
	}

	public SalesforceError getSalesforceError() {
		return _salesforceError;
	}

	private SalesforceException(StatusCode statusCode, String message) {
		super(statusCode.getValue() + StringPool.COLON + message);

		_salesforceError = new SalesforceError(statusCode.getValue(), message);
	}

	private SalesforceError _salesforceError;

}