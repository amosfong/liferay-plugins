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

import com.liferay.salesforce.service.SalesforceLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SalesforceLocalServiceClpInvoker {
	public SalesforceLocalServiceClpInvoker() {
		_methodName30 = "getBeanIdentifier";

		_methodParameterTypes30 = new String[] {  };

		_methodName31 = "setBeanIdentifier";

		_methodParameterTypes31 = new String[] { "java.lang.String" };

		_methodName34 = "executeAdd";

		_methodParameterTypes34 = new String[] { "long", "java.util.List" };

		_methodName35 = "executeAdd";

		_methodParameterTypes35 = new String[] {
				"long", "com.liferay.portal.kernel.messaging.Message"
			};

		_methodName36 = "executeAddOrUpdate";

		_methodParameterTypes36 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName37 = "executeAddOrUpdate";

		_methodParameterTypes37 = new String[] {
				"long", "java.lang.String",
				"com.liferay.portal.kernel.messaging.Message"
			};

		_methodName38 = "executeDelete";

		_methodParameterTypes38 = new String[] { "long", "java.util.List" };

		_methodName39 = "executeDelete";

		_methodParameterTypes39 = new String[] { "long", "java.lang.String" };

		_methodName40 = "executeQuery";

		_methodParameterTypes40 = new String[] { "long", "java.lang.String" };

		_methodName41 = "executeQuery";

		_methodParameterTypes41 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.List"
			};

		_methodName42 = "executeQueryMore";

		_methodParameterTypes42 = new String[] { "long", "java.lang.String" };

		_methodName43 = "executeSearch";

		_methodParameterTypes43 = new String[] { "long", "java.lang.String" };

		_methodName44 = "executeUpdate";

		_methodParameterTypes44 = new String[] { "long", "java.util.List" };

		_methodName45 = "executeUpdate";

		_methodParameterTypes45 = new String[] {
				"long", "com.liferay.portal.kernel.messaging.Message"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return SalesforceLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			SalesforceLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			SalesforceLocalServiceUtil.executeAdd(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.portal.kernel.messaging.Message>)arguments[1]);
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeAdd(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.messaging.Message)arguments[1]);
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			SalesforceLocalServiceUtil.executeAddOrUpdate(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<com.liferay.portal.kernel.messaging.Message>)arguments[2]);
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			SalesforceLocalServiceUtil.executeAddOrUpdate(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.kernel.messaging.Message)arguments[2]);
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			SalesforceLocalServiceUtil.executeDelete(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.String>)arguments[1]);
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeDelete(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeQuery(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeQuery(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.List<java.lang.String>)arguments[3]);
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeQueryMore(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeSearch(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			SalesforceLocalServiceUtil.executeUpdate(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.portal.kernel.messaging.Message>)arguments[1]);
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			SalesforceLocalServiceUtil.executeUpdate(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.messaging.Message)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
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
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
}