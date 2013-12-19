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

package com.liferay.reports.service.base;

import com.liferay.reports.service.SourceServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SourceServiceClpInvoker {
	public SourceServiceClpInvoker() {
		_methodName36 = "getBeanIdentifier";

		_methodParameterTypes36 = new String[] {  };

		_methodName37 = "setBeanIdentifier";

		_methodParameterTypes37 = new String[] { "java.lang.String" };

		_methodName42 = "addSource";

		_methodParameterTypes42 = new String[] {
				"long", "java.util.Map", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName43 = "deleteSource";

		_methodParameterTypes43 = new String[] { "long" };

		_methodName44 = "getSource";

		_methodParameterTypes44 = new String[] { "long" };

		_methodName45 = "getSources";

		_methodParameterTypes45 = new String[] {
				"long", "java.lang.String", "java.lang.String", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName46 = "getSourcesCount";

		_methodParameterTypes46 = new String[] {
				"long", "java.lang.String", "java.lang.String", "boolean"
			};

		_methodName47 = "updateSource";

		_methodParameterTypes47 = new String[] {
				"long", "java.util.Map", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return SourceServiceUtil.getBeanIdentifier();
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			SourceServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return SourceServiceUtil.addSource(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return SourceServiceUtil.deleteSource(((Long)arguments[0]).longValue());
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return SourceServiceUtil.getSource(((Long)arguments[0]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return SourceServiceUtil.getSources(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6]);
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return SourceServiceUtil.getSourcesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return SourceServiceUtil.updateSource(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
}