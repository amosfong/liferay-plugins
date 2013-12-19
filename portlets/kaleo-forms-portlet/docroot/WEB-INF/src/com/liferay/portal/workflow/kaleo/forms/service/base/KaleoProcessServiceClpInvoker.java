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

package com.liferay.portal.workflow.kaleo.forms.service.base;

import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessServiceUtil;

import java.util.Arrays;

/**
 * @author Marcellus Tavares
 * @generated
 */
public class KaleoProcessServiceClpInvoker {
	public KaleoProcessServiceClpInvoker() {
		_methodName50 = "getBeanIdentifier";

		_methodParameterTypes50 = new String[] {  };

		_methodName51 = "setBeanIdentifier";

		_methodParameterTypes51 = new String[] { "java.lang.String" };

		_methodName56 = "addKaleoProcess";

		_methodParameterTypes56 = new String[] {
				"long", "long", "long", "long[][]",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName57 = "deleteKaleoProcess";

		_methodParameterTypes57 = new String[] { "long" };

		_methodName58 = "deleteKaleoProcessData";

		_methodParameterTypes58 = new String[] { "long" };

		_methodName59 = "getKaleoProcess";

		_methodParameterTypes59 = new String[] { "long" };

		_methodName60 = "getKaleoProcesses";

		_methodParameterTypes60 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName61 = "getKaleoProcessesCount";

		_methodParameterTypes61 = new String[] { "long" };

		_methodName62 = "updateKaleoProcess";

		_methodParameterTypes62 = new String[] {
				"long", "long", "long[][]",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return KaleoProcessServiceUtil.getBeanIdentifier();
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			KaleoProcessServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return KaleoProcessServiceUtil.addKaleoProcess(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(), (long[])arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return KaleoProcessServiceUtil.deleteKaleoProcess(((Long)arguments[0]).longValue());
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			KaleoProcessServiceUtil.deleteKaleoProcessData(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return KaleoProcessServiceUtil.getKaleoProcess(((Long)arguments[0]).longValue());
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return KaleoProcessServiceUtil.getKaleoProcesses(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return KaleoProcessServiceUtil.getKaleoProcessesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return KaleoProcessServiceUtil.updateKaleoProcess(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (long[])arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
}