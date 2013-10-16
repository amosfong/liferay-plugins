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

package com.liferay.portal.resiliency.spi.service.base;

import com.liferay.portal.resiliency.spi.service.SPIDefinitionServiceUtil;

import java.util.Arrays;

/**
 * @author Michael C. Han
 * @generated
 */
public class SPIDefinitionServiceClpInvoker {
	public SPIDefinitionServiceClpInvoker() {
		_methodName28 = "getBeanIdentifier";

		_methodParameterTypes28 = new String[] {  };

		_methodName29 = "setBeanIdentifier";

		_methodParameterTypes29 = new String[] { "java.lang.String" };

		_methodName34 = "addSPIDefinition";

		_methodParameterTypes34 = new String[] {
				"java.lang.String", "java.lang.String", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName35 = "deleteSPIDefinition";

		_methodParameterTypes35 = new String[] { "long" };

		_methodName36 = "getPortletIdsAndServletContextNames";

		_methodParameterTypes36 = new String[] {  };

		_methodName37 = "getSPIDefinition";

		_methodParameterTypes37 = new String[] { "long" };

		_methodName38 = "getSPIDefinition";

		_methodParameterTypes38 = new String[] { "long", "java.lang.String" };

		_methodName39 = "getSPIDefinitions";

		_methodParameterTypes39 = new String[] {  };

		_methodName40 = "startSPI";

		_methodParameterTypes40 = new String[] { "long" };

		_methodName41 = "startSPIinBackground";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "stopSPI";

		_methodParameterTypes42 = new String[] { "long" };

		_methodName43 = "stopSPIinBackground";

		_methodParameterTypes43 = new String[] { "long" };

		_methodName44 = "updateSPIDefinition";

		_methodParameterTypes44 = new String[] {
				"long", "java.lang.String", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return SPIDefinitionServiceUtil.getBeanIdentifier();
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			SPIDefinitionServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return SPIDefinitionServiceUtil.addSPIDefinition((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7],
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return SPIDefinitionServiceUtil.deleteSPIDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return SPIDefinitionServiceUtil.getPortletIdsAndServletContextNames();
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return SPIDefinitionServiceUtil.getSPIDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return SPIDefinitionServiceUtil.getSPIDefinition(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return SPIDefinitionServiceUtil.getSPIDefinitions();
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			SPIDefinitionServiceUtil.startSPI(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return SPIDefinitionServiceUtil.startSPIinBackground(((Long)arguments[0]).longValue());
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			SPIDefinitionServiceUtil.stopSPI(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return SPIDefinitionServiceUtil.stopSPIinBackground(((Long)arguments[0]).longValue());
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return SPIDefinitionServiceUtil.updateSPIDefinition(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7],
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
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
}