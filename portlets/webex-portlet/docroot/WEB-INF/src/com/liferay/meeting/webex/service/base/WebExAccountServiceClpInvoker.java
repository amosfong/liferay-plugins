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

package com.liferay.meeting.webex.service.base;

import com.liferay.meeting.webex.service.WebExAccountServiceUtil;

import java.util.Arrays;

/**
 * @author Anant Singh
 * @generated
 */
public class WebExAccountServiceClpInvoker {
	public WebExAccountServiceClpInvoker() {
		_methodName36 = "getBeanIdentifier";

		_methodParameterTypes36 = new String[] {  };

		_methodName37 = "setBeanIdentifier";

		_methodParameterTypes37 = new String[] { "java.lang.String" };

		_methodName42 = "addWebExAccount";

		_methodParameterTypes42 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName43 = "deleteWebExAccount";

		_methodParameterTypes43 = new String[] { "long" };

		_methodName44 = "getWebExAccount";

		_methodParameterTypes44 = new String[] { "long" };

		_methodName45 = "getWebExSiteWebExAccounts";

		_methodParameterTypes45 = new String[] {
				"long", "long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName46 = "getWebExSiteWebExAccountsCount";

		_methodParameterTypes46 = new String[] { "long", "long" };

		_methodName47 = "updateWebExAccount";

		_methodParameterTypes47 = new String[] {
				"long", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return WebExAccountServiceUtil.getBeanIdentifier();
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			WebExAccountServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			WebExAccountServiceUtil.addWebExAccount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);

			return null;
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			WebExAccountServiceUtil.deleteWebExAccount(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return WebExAccountServiceUtil.getWebExAccount(((Long)arguments[0]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return WebExAccountServiceUtil.getWebExSiteWebExAccounts(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return WebExAccountServiceUtil.getWebExSiteWebExAccountsCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			WebExAccountServiceUtil.updateWebExAccount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);

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