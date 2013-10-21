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

package com.liferay.oauth.service.base;

import com.liferay.oauth.service.OAuthApplicationServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class OAuthApplicationServiceClpInvoker {
	public OAuthApplicationServiceClpInvoker() {
		_methodName34 = "getBeanIdentifier";

		_methodParameterTypes34 = new String[] {  };

		_methodName35 = "setBeanIdentifier";

		_methodParameterTypes35 = new String[] { "java.lang.String" };

		_methodName40 = "addOAuthApplication";

		_methodParameterTypes40 = new String[] {
				"java.lang.String", "java.lang.String", "int",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName41 = "deleteLogo";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "deleteOAuthApplication";

		_methodParameterTypes42 = new String[] { "long" };

		_methodName43 = "updateLogo";

		_methodParameterTypes43 = new String[] { "long", "java.io.InputStream" };

		_methodName44 = "updateOAuthApplication";

		_methodParameterTypes44 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return OAuthApplicationServiceUtil.getBeanIdentifier();
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			OAuthApplicationServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return OAuthApplicationServiceUtil.addOAuthApplication((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			OAuthApplicationServiceUtil.deleteLogo(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return OAuthApplicationServiceUtil.deleteOAuthApplication(((Long)arguments[0]).longValue());
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return OAuthApplicationServiceUtil.updateLogo(((Long)arguments[0]).longValue(),
				(java.io.InputStream)arguments[1]);
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return OAuthApplicationServiceUtil.updateOAuthApplication(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
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
}