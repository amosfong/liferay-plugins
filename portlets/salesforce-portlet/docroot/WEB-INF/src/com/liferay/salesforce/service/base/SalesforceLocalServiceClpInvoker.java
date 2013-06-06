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

import com.liferay.salesforce.service.SalesforceLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Michael C. Han
 * @generated
 */
public class SalesforceLocalServiceClpInvoker {
	public SalesforceLocalServiceClpInvoker() {
		_methodName26 = "getBeanIdentifier";

		_methodParameterTypes26 = new String[] {  };

		_methodName27 = "setBeanIdentifier";

		_methodParameterTypes27 = new String[] { "java.lang.String" };

		_methodName30 = "executeAdd";

		_methodParameterTypes30 = new String[] { "long", "java.util.List" };

		_methodName31 = "executeAdd";

		_methodParameterTypes31 = new String[] {
				"long", "com.liferay.portal.kernel.messaging.Message"
			};

		_methodName32 = "executeAddOrUpdate";

		_methodParameterTypes32 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName33 = "executeAddOrUpdate";

		_methodParameterTypes33 = new String[] {
				"long", "java.lang.String",
				"com.liferay.portal.kernel.messaging.Message"
			};

		_methodName34 = "executeDelete";

		_methodParameterTypes34 = new String[] { "long", "java.util.List" };

		_methodName35 = "executeDelete";

		_methodParameterTypes35 = new String[] { "long", "java.lang.String" };

		_methodName36 = "executeQuery";

		_methodParameterTypes36 = new String[] { "long", "java.lang.String" };

		_methodName37 = "executeQuery";

		_methodParameterTypes37 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.List"
			};

		_methodName38 = "executeQueryMore";

		_methodParameterTypes38 = new String[] { "long", "java.lang.String" };

		_methodName39 = "executeSearch";

		_methodParameterTypes39 = new String[] { "long", "java.lang.String" };

		_methodName40 = "executeUpdate";

		_methodParameterTypes40 = new String[] { "long", "java.util.List" };

		_methodName41 = "executeUpdate";

		_methodParameterTypes41 = new String[] {
				"long", "com.liferay.portal.kernel.messaging.Message"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return SalesforceLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			SalesforceLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			SalesforceLocalServiceUtil.executeAdd(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.portal.kernel.messaging.Message>)arguments[1]);

			return null;
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeAdd(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.messaging.Message)arguments[1]);
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			SalesforceLocalServiceUtil.executeAddOrUpdate(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.List<com.liferay.portal.kernel.messaging.Message>)arguments[2]);

			return null;
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			SalesforceLocalServiceUtil.executeAddOrUpdate(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.kernel.messaging.Message)arguments[2]);

			return null;
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			SalesforceLocalServiceUtil.executeDelete(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.String>)arguments[1]);

			return null;
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeDelete(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeQuery(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeQuery(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.List<java.lang.String>)arguments[3]);
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeQueryMore(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return SalesforceLocalServiceUtil.executeSearch(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			SalesforceLocalServiceUtil.executeUpdate(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.portal.kernel.messaging.Message>)arguments[1]);

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			SalesforceLocalServiceUtil.executeUpdate(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.messaging.Message)arguments[1]);

			return null;
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
	private String _methodName33;
	private String[] _methodParameterTypes33;
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
}