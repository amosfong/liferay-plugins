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

package com.liferay.reports.service.base;

import com.liferay.reports.service.SourceServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SourceServiceClpInvoker {
	public SourceServiceClpInvoker() {
		_methodName30 = "getBeanIdentifier";

		_methodParameterTypes30 = new String[] {  };

		_methodName31 = "setBeanIdentifier";

		_methodParameterTypes31 = new String[] { "java.lang.String" };

		_methodName36 = "addSource";

		_methodParameterTypes36 = new String[] {
				"java.util.Map", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName37 = "deleteSource";

		_methodParameterTypes37 = new String[] { "long" };

		_methodName38 = "getSources";

		_methodParameterTypes38 = new String[] {
				"long", "java.lang.String", "java.lang.String", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName39 = "getSourcesCount";

		_methodParameterTypes39 = new String[] {
				"long", "java.lang.String", "java.lang.String", "boolean"
			};

		_methodName40 = "updateSource";

		_methodParameterTypes40 = new String[] {
				"long", "java.util.Map", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return SourceServiceUtil.getBeanIdentifier();
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			SourceServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return SourceServiceUtil.addSource((java.util.Map<java.util.Locale, java.lang.String>)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return SourceServiceUtil.deleteSource(((Long)arguments[0]).longValue());
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return SourceServiceUtil.getSources(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6]);
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return SourceServiceUtil.getSourcesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return SourceServiceUtil.updateSource(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
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