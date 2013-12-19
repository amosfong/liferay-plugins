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

import com.liferay.reports.service.EntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntryServiceClpInvoker {
	public EntryServiceClpInvoker() {
		_methodName42 = "getBeanIdentifier";

		_methodParameterTypes42 = new String[] {  };

		_methodName43 = "setBeanIdentifier";

		_methodParameterTypes43 = new String[] { "java.lang.String" };

		_methodName48 = "addEntry";

		_methodParameterTypes48 = new String[] {
				"long", "long", "java.lang.String", "boolean", "java.util.Date",
				"java.util.Date", "boolean", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName49 = "deleteAttachment";

		_methodParameterTypes49 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName50 = "deleteEntry";

		_methodParameterTypes50 = new String[] { "long" };

		_methodName51 = "getEntries";

		_methodParameterTypes51 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.Date",
				"java.util.Date", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName52 = "getEntriesCount";

		_methodParameterTypes52 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.Date",
				"java.util.Date", "boolean"
			};

		_methodName53 = "sendEmails";

		_methodParameterTypes53 = new String[] {
				"long", "java.lang.String", "java.lang.String[][]", "boolean"
			};

		_methodName54 = "unscheduleEntry";

		_methodParameterTypes54 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return EntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			EntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return EntryServiceUtil.addEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				(java.util.Date)arguments[4], (java.util.Date)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12],
				(com.liferay.portal.service.ServiceContext)arguments[13]);
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			EntryServiceUtil.deleteAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return EntryServiceUtil.deleteEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return EntryServiceUtil.getEntries(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.Date)arguments[3], (java.util.Date)arguments[4],
				((Boolean)arguments[5]).booleanValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[8]);
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return EntryServiceUtil.getEntriesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.Date)arguments[3], (java.util.Date)arguments[4],
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			EntryServiceUtil.sendEmails(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.lang.String[])arguments[2],
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			EntryServiceUtil.unscheduleEntry(((Long)arguments[0]).longValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
}