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

package com.liferay.salesforce.service.base;

import com.liferay.salesforce.service.SalesforceContactLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Michael C. Han
 * @generated
 */
public class SalesforceContactLocalServiceClpInvoker {
	public SalesforceContactLocalServiceClpInvoker() {
		_methodName32 = "getBeanIdentifier";

		_methodParameterTypes32 = new String[] {  };

		_methodName33 = "setBeanIdentifier";

		_methodParameterTypes33 = new String[] { "java.lang.String" };

		_methodName36 = "getContactsByAccountId";

		_methodParameterTypes36 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName37 = "getContactsByOwnerId";

		_methodParameterTypes37 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName38 = "getContactsByUserName";

		_methodParameterTypes38 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return SalesforceContactLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			SalesforceContactLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return SalesforceContactLocalServiceUtil.getContactsByAccountId(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return SalesforceContactLocalServiceUtil.getContactsByOwnerId(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return SalesforceContactLocalServiceUtil.getContactsByUserName(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName38;
	private String[] _methodParameterTypes38;
}