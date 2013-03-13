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

import com.sforce.soap.partner.StatusCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class MultipleSalesforceException extends Exception {

	public void addSalesforceError(com.sforce.soap.partner.Error error) {
		_addSalesforceError(error.getStatusCode(), error.getMessage());
	}

	public List<SalesforceError> getSalesforceErrors() {
		return _salesforceErrors;
	}

	public boolean hasSalesforceError() {
		if ((_salesforceErrors != null) && !_salesforceErrors.isEmpty()) {
			return true;
		}
		else {
			return false;
		}
	}

	private void _addSalesforceError(StatusCode statusCode, String message) {
		if (_salesforceErrors == null) {
			_salesforceErrors = new ArrayList<SalesforceError>();
		}

		SalesforceError salesforceError = new SalesforceError(
			statusCode.getValue(), message);

		_salesforceErrors.add(salesforceError);
	}

	private List<SalesforceError> _salesforceErrors;

}