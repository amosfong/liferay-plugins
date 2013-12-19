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

package com.liferay.saml.service.base;

import com.liferay.saml.service.SamlSpSessionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Mika Koivisto
 * @generated
 */
public class SamlSpSessionLocalServiceClpInvoker {
	public SamlSpSessionLocalServiceClpInvoker() {
		_methodName0 = "addSamlSpSession";

		_methodParameterTypes0 = new String[] {
				"com.liferay.saml.model.SamlSpSession"
			};

		_methodName1 = "createSamlSpSession";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSamlSpSession";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSamlSpSession";

		_methodParameterTypes3 = new String[] {
				"com.liferay.saml.model.SamlSpSession"
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

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchSamlSpSession";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getSamlSpSession";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getSamlSpSessions";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getSamlSpSessionsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateSamlSpSession";

		_methodParameterTypes15 = new String[] {
				"com.liferay.saml.model.SamlSpSession"
			};

		_methodName62 = "getBeanIdentifier";

		_methodParameterTypes62 = new String[] {  };

		_methodName63 = "setBeanIdentifier";

		_methodParameterTypes63 = new String[] { "java.lang.String" };

		_methodName68 = "addSamlSpSession";

		_methodParameterTypes68 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName69 = "fetchSamlSpSessionByJSessionId";

		_methodParameterTypes69 = new String[] { "java.lang.String" };

		_methodName70 = "fetchSamlSpSessionBySamlSpSessionKey";

		_methodParameterTypes70 = new String[] { "java.lang.String" };

		_methodName71 = "fetchSamlSpSessionBySessionIndex";

		_methodParameterTypes71 = new String[] { "java.lang.String" };

		_methodName72 = "getSamlSpSessionByJSessionId";

		_methodParameterTypes72 = new String[] { "java.lang.String" };

		_methodName73 = "getSamlSpSessionBySamlSpSessionKey";

		_methodParameterTypes73 = new String[] { "java.lang.String" };

		_methodName74 = "getSamlSpSessionBySessionIndex";

		_methodParameterTypes74 = new String[] { "java.lang.String" };

		_methodName75 = "getSamlSpSessions";

		_methodParameterTypes75 = new String[] { "java.lang.String" };

		_methodName76 = "updateSamlSpSession";

		_methodParameterTypes76 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.addSamlSpSession((com.liferay.saml.model.SamlSpSession)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.createSamlSpSession(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.deleteSamlSpSession(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.deleteSamlSpSession((com.liferay.saml.model.SamlSpSession)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.fetchSamlSpSession(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.getSamlSpSession(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.getSamlSpSessions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.getSamlSpSessionsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.updateSamlSpSession((com.liferay.saml.model.SamlSpSession)arguments[0]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			SamlSpSessionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.addSamlSpSession((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.fetchSamlSpSessionByJSessionId((java.lang.String)arguments[0]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.fetchSamlSpSessionBySamlSpSessionKey((java.lang.String)arguments[0]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.fetchSamlSpSessionBySessionIndex((java.lang.String)arguments[0]);
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.getSamlSpSessionByJSessionId((java.lang.String)arguments[0]);
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.getSamlSpSessionBySamlSpSessionKey((java.lang.String)arguments[0]);
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.getSamlSpSessionBySessionIndex((java.lang.String)arguments[0]);
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.getSamlSpSessions((java.lang.String)arguments[0]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return SamlSpSessionLocalServiceUtil.updateSamlSpSession(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(com.liferay.portal.service.ServiceContext)arguments[7]);
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
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
}