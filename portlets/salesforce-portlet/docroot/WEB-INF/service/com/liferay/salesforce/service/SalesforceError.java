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

import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

/**
 * @author Michael C. Han
 */
public class SalesforceError implements Serializable {

	public SalesforceError(String code, String message) {
		_code = code;
		_message = message;
	}

	public String getCode() {
		return _code;
	}

	public String getMessage() {
		return _message;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{code=");
		sb.append(_code);
		sb.append(", message=");
		sb.append(_message);
		sb.append("}");

		return sb.toString();
	}

	private String _code;
	private String _message;

}