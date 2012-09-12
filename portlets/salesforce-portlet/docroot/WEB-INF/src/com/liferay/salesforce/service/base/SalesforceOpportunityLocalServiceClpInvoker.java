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

package com.liferay.salesforce.service.base;

import com.liferay.salesforce.service.SalesforceOpportunityLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SalesforceOpportunityLocalServiceClpInvoker {
	public SalesforceOpportunityLocalServiceClpInvoker() {
		_methodName26 = "getBeanIdentifier";

		_methodParameterTypes26 = new String[] {  };

		_methodName27 = "setBeanIdentifier";

		_methodParameterTypes27 = new String[] { "java.lang.String" };

		_methodName30 = "getOpportunitiesByAccountId";

		_methodParameterTypes30 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName31 = "getOpportunitiesByUserId";

		_methodParameterTypes31 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName32 = "getOpportunitiesByUserName";

		_methodParameterTypes32 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return SalesforceOpportunityLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			SalesforceOpportunityLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return SalesforceOpportunityLocalServiceUtil.getOpportunitiesByAccountId(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return SalesforceOpportunityLocalServiceUtil.getOpportunitiesByUserId(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return SalesforceOpportunityLocalServiceUtil.getOpportunitiesByUserName(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<java.lang.String>)arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
}