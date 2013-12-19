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

import com.liferay.salesforce.service.SalesforceLeadLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Michael C. Han
 * @generated
 */
public class SalesforceLeadLocalServiceClpInvoker {
	public SalesforceLeadLocalServiceClpInvoker() {
		_methodName32 = "getBeanIdentifier";

		_methodParameterTypes32 = new String[] {  };

		_methodName33 = "setBeanIdentifier";

		_methodParameterTypes33 = new String[] { "java.lang.String" };

		_methodName36 = "getLeadsByCountry";

		_methodParameterTypes36 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName37 = "getLeadsBySource";

		_methodParameterTypes37 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName38 = "getLeadsByStatus";

		_methodParameterTypes38 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName39 = "getLeadsByUserId";

		_methodParameterTypes39 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName40 = "getLeadsByUserName";

		_methodParameterTypes40 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return SalesforceLeadLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			SalesforceLeadLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return SalesforceLeadLocalServiceUtil.getLeadsByCountry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return SalesforceLeadLocalServiceUtil.getLeadsBySource(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return SalesforceLeadLocalServiceUtil.getLeadsByStatus(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return SalesforceLeadLocalServiceUtil.getLeadsByUserId(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return SalesforceLeadLocalServiceUtil.getLeadsByUserName(((Long)arguments[0]).longValue(),
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
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
}