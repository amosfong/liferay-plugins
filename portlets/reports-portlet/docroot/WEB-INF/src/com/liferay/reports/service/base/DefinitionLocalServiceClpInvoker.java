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

import com.liferay.reports.service.DefinitionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class DefinitionLocalServiceClpInvoker {
	public DefinitionLocalServiceClpInvoker() {
		_methodName0 = "addDefinition";

		_methodParameterTypes0 = new String[] {
				"com.liferay.reports.model.Definition"
			};

		_methodName1 = "createDefinition";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteDefinition";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteDefinition";

		_methodParameterTypes3 = new String[] {
				"com.liferay.reports.model.Definition"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "fetchDefinition";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getDefinition";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getDefinitionByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getDefinitions";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getDefinitionsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateDefinition";

		_methodParameterTypes15 = new String[] {
				"com.liferay.reports.model.Definition"
			};

		_methodName16 = "updateDefinition";

		_methodParameterTypes16 = new String[] {
				"com.liferay.reports.model.Definition", "boolean"
			};

		_methodName47 = "getBeanIdentifier";

		_methodParameterTypes47 = new String[] {  };

		_methodName48 = "setBeanIdentifier";

		_methodParameterTypes48 = new String[] { "java.lang.String" };

		_methodName53 = "addDefinition";

		_methodParameterTypes53 = new String[] {
				"long", "java.util.Map", "java.util.Map", "long",
				"java.lang.String", "java.lang.String", "java.io.InputStream",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName54 = "deleteDefinition";

		_methodParameterTypes54 = new String[] {
				"com.liferay.reports.model.Definition"
			};

		_methodName55 = "deleteDefinition";

		_methodParameterTypes55 = new String[] { "long" };

		_methodName56 = "deleteDefinitionTemplates";

		_methodParameterTypes56 = new String[] { "long", "java.lang.String" };

		_methodName57 = "getDefinitions";

		_methodParameterTypes57 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName58 = "getDefinitionsCount";

		_methodParameterTypes58 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName59 = "updateDefinition";

		_methodParameterTypes59 = new String[] {
				"long", "java.util.Map", "java.util.Map", "long",
				"java.lang.String", "java.lang.String", "java.io.InputStream",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName60 = "updateDefinitionResources";

		_methodParameterTypes60 = new String[] {
				"com.liferay.reports.model.Definition", "java.lang.String[][]",
				"java.lang.String[][]"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return DefinitionLocalServiceUtil.addDefinition((com.liferay.reports.model.Definition)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return DefinitionLocalServiceUtil.createDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return DefinitionLocalServiceUtil.deleteDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return DefinitionLocalServiceUtil.deleteDefinition((com.liferay.reports.model.Definition)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return DefinitionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return DefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return DefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return DefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return DefinitionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return DefinitionLocalServiceUtil.fetchDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return DefinitionLocalServiceUtil.getDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return DefinitionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return DefinitionLocalServiceUtil.getDefinitionByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return DefinitionLocalServiceUtil.getDefinitions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return DefinitionLocalServiceUtil.getDefinitionsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return DefinitionLocalServiceUtil.updateDefinition((com.liferay.reports.model.Definition)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return DefinitionLocalServiceUtil.updateDefinition((com.liferay.reports.model.Definition)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return DefinitionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			DefinitionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return DefinitionLocalServiceUtil.addDefinition(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.io.InputStream)arguments[6],
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return DefinitionLocalServiceUtil.deleteDefinition((com.liferay.reports.model.Definition)arguments[0]);
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return DefinitionLocalServiceUtil.deleteDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			DefinitionLocalServiceUtil.deleteDefinitionTemplates(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return DefinitionLocalServiceUtil.getDefinitions(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[8]);
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return DefinitionLocalServiceUtil.getDefinitionsCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return DefinitionLocalServiceUtil.updateDefinition(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.io.InputStream)arguments[6],
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			DefinitionLocalServiceUtil.updateDefinitionResources((com.liferay.reports.model.Definition)arguments[0],
				(java.lang.String[])arguments[1],
				(java.lang.String[])arguments[2]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
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
}