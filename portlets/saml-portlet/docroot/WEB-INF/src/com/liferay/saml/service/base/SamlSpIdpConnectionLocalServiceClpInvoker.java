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

package com.liferay.saml.service.base;

import com.liferay.saml.service.SamlSpIdpConnectionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SamlSpIdpConnectionLocalServiceClpInvoker {
	public SamlSpIdpConnectionLocalServiceClpInvoker() {
		_methodName0 = "addSamlSpIdpConnection";

		_methodParameterTypes0 = new String[] {
				"com.liferay.saml.model.SamlSpIdpConnection"
			};

		_methodName1 = "createSamlSpIdpConnection";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSamlSpIdpConnection";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSamlSpIdpConnection";

		_methodParameterTypes3 = new String[] {
				"com.liferay.saml.model.SamlSpIdpConnection"
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

		_methodName9 = "fetchSamlSpIdpConnection";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getSamlSpIdpConnection";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getSamlSpIdpConnections";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getSamlSpIdpConnectionsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateSamlSpIdpConnection";

		_methodParameterTypes14 = new String[] {
				"com.liferay.saml.model.SamlSpIdpConnection"
			};

		_methodName55 = "getBeanIdentifier";

		_methodParameterTypes55 = new String[] {  };

		_methodName56 = "setBeanIdentifier";

		_methodParameterTypes56 = new String[] { "java.lang.String" };

		_methodName61 = "addSamlSpIdpConnection";

		_methodParameterTypes61 = new String[] {
				"java.lang.String", "boolean", "long", "boolean", "boolean",
				"java.lang.String", "java.io.InputStream", "java.lang.String",
				"java.lang.String", "boolean", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName62 = "getSamlSpIdpConnection";

		_methodParameterTypes62 = new String[] { "long", "java.lang.String" };

		_methodName63 = "getSamlSpIdpConnections";

		_methodParameterTypes63 = new String[] { "long" };

		_methodName64 = "getSamlSpIdpConnections";

		_methodParameterTypes64 = new String[] { "long", "int", "int" };

		_methodName65 = "getSamlSpIdpConnections";

		_methodParameterTypes65 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName66 = "getSamlSpIdpConnectionsCount";

		_methodParameterTypes66 = new String[] { "long" };

		_methodName67 = "updateMetadata";

		_methodParameterTypes67 = new String[] { "long" };

		_methodName68 = "updateSamlSpIdpConnection";

		_methodParameterTypes68 = new String[] {
				"long", "java.lang.String", "boolean", "long", "boolean",
				"boolean", "java.lang.String", "java.io.InputStream",
				"java.lang.String", "java.lang.String", "boolean",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.addSamlSpIdpConnection((com.liferay.saml.model.SamlSpIdpConnection)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.createSamlSpIdpConnection(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.deleteSamlSpIdpConnection(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.deleteSamlSpIdpConnection((com.liferay.saml.model.SamlSpIdpConnection)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.fetchSamlSpIdpConnection(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnection(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnections(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnectionsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.updateSamlSpIdpConnection((com.liferay.saml.model.SamlSpIdpConnection)arguments[0]);
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			SamlSpIdpConnectionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.addSamlSpIdpConnection((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Long)arguments[2]).longValue(),
				((Boolean)arguments[3]).booleanValue(),
				((Boolean)arguments[4]).booleanValue(),
				(java.lang.String)arguments[5],
				(java.io.InputStream)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				((Boolean)arguments[9]).booleanValue(),
				(java.lang.String)arguments[10],
				(com.liferay.portal.service.ServiceContext)arguments[11]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnection(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnections(((Long)arguments[0]).longValue());
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnections(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnections(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.getSamlSpIdpConnectionsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			SamlSpIdpConnectionLocalServiceUtil.updateMetadata(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return SamlSpIdpConnectionLocalServiceUtil.updateSamlSpIdpConnection(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Long)arguments[3]).longValue(),
				((Boolean)arguments[4]).booleanValue(),
				((Boolean)arguments[5]).booleanValue(),
				(java.lang.String)arguments[6],
				(java.io.InputStream)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				((Boolean)arguments[10]).booleanValue(),
				(java.lang.String)arguments[11],
				(com.liferay.portal.service.ServiceContext)arguments[12]);
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
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
}