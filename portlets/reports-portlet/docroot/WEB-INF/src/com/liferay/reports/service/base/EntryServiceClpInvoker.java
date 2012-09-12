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

import com.liferay.reports.service.EntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class EntryServiceClpInvoker {
	public EntryServiceClpInvoker() {
		_methodName36 = "getBeanIdentifier";

		_methodParameterTypes36 = new String[] {  };

		_methodName37 = "setBeanIdentifier";

		_methodParameterTypes37 = new String[] { "java.lang.String" };

		_methodName42 = "addEntry";

		_methodParameterTypes42 = new String[] {
				"long", "java.lang.String", "boolean", "java.util.Date",
				"java.util.Date", "boolean", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName43 = "deleteAttachment";

		_methodParameterTypes43 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName44 = "deleteEntry";

		_methodParameterTypes44 = new String[] { "long" };

		_methodName45 = "getEntries";

		_methodParameterTypes45 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.Date",
				"java.util.Date", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName46 = "getEntriesCount";

		_methodParameterTypes46 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.Date",
				"java.util.Date", "boolean"
			};

		_methodName47 = "unscheduleEntry";

		_methodParameterTypes47 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return EntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			EntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return EntryServiceUtil.addEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				(java.util.Date)arguments[3], (java.util.Date)arguments[4],
				((Boolean)arguments[5]).booleanValue(),
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				(com.liferay.portal.service.ServiceContext)arguments[12]);
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			EntryServiceUtil.deleteAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return EntryServiceUtil.deleteEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return EntryServiceUtil.getEntries(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.Date)arguments[3], (java.util.Date)arguments[4],
				((Boolean)arguments[5]).booleanValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[8]);
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return EntryServiceUtil.getEntriesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.Date)arguments[3], (java.util.Date)arguments[4],
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			EntryServiceUtil.unscheduleEntry(((Long)arguments[0]).longValue());

			return null;
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