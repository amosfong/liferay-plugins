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

package com.liferay.portal.workflow.kaleo.designer.service.base;

import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionServiceUtil;

import java.util.Arrays;

/**
 * @author Eduardo Lundgren
 * @generated
 */
public class KaleoDraftDefinitionServiceClpInvoker {
	public KaleoDraftDefinitionServiceClpInvoker() {
		_methodName24 = "getBeanIdentifier";

		_methodParameterTypes24 = new String[] {  };

		_methodName25 = "setBeanIdentifier";

		_methodParameterTypes25 = new String[] { "java.lang.String" };

		_methodName30 = "addKaleoDraftDefinition";

		_methodParameterTypes30 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.lang.String",
				"int", "int", "com.liferay.portal.service.ServiceContext"
			};

		_methodName31 = "getKaleoDraftDefinition";

		_methodParameterTypes31 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName32 = "getKaleoDraftDefinitions";

		_methodParameterTypes32 = new String[] {  };

		_methodName33 = "getLatestKaleoDraftDefinition";

		_methodParameterTypes33 = new String[] {
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName34 = "publishKaleoDraftDefinition";

		_methodParameterTypes34 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName35 = "updateKaleoDraftDefinition";

		_methodParameterTypes35 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.lang.String",
				"int", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			return KaleoDraftDefinitionServiceUtil.getBeanIdentifier();
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			KaleoDraftDefinitionServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return KaleoDraftDefinitionServiceUtil.addKaleoDraftDefinition(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return KaleoDraftDefinitionServiceUtil.getKaleoDraftDefinition((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return KaleoDraftDefinitionServiceUtil.getKaleoDraftDefinitions();
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return KaleoDraftDefinitionServiceUtil.getLatestKaleoDraftDefinition((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return KaleoDraftDefinitionServiceUtil.publishKaleoDraftDefinition(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return KaleoDraftDefinitionServiceUtil.updateKaleoDraftDefinition(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
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
}