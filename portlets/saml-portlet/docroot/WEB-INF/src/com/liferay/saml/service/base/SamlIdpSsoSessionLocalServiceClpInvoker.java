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

import com.liferay.saml.service.SamlIdpSsoSessionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Mika Koivisto
 * @generated
 */
public class SamlIdpSsoSessionLocalServiceClpInvoker {
	public SamlIdpSsoSessionLocalServiceClpInvoker() {
		_methodName0 = "addSamlIdpSsoSession";

		_methodParameterTypes0 = new String[] {
				"com.liferay.saml.model.SamlIdpSsoSession"
			};

		_methodName1 = "createSamlIdpSsoSession";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSamlIdpSsoSession";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSamlIdpSsoSession";

		_methodParameterTypes3 = new String[] {
				"com.liferay.saml.model.SamlIdpSsoSession"
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

		_methodName10 = "fetchSamlIdpSsoSession";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getSamlIdpSsoSession";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getSamlIdpSsoSessions";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getSamlIdpSsoSessionsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateSamlIdpSsoSession";

		_methodParameterTypes15 = new String[] {
				"com.liferay.saml.model.SamlIdpSsoSession"
			};

		_methodName62 = "getBeanIdentifier";

		_methodParameterTypes62 = new String[] {  };

		_methodName63 = "setBeanIdentifier";

		_methodParameterTypes63 = new String[] { "java.lang.String" };

		_methodName68 = "addSamlIdpSsoSession";

		_methodParameterTypes68 = new String[] {
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName69 = "fetchSamlIdpSso";

		_methodParameterTypes69 = new String[] { "java.lang.String" };

		_methodName70 = "getSamlIdpSso";

		_methodParameterTypes70 = new String[] { "java.lang.String" };

		_methodName71 = "updateModifiedDate";

		_methodParameterTypes71 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.addSamlIdpSsoSession((com.liferay.saml.model.SamlIdpSsoSession)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.createSamlIdpSsoSession(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.deleteSamlIdpSsoSession(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.deleteSamlIdpSsoSession((com.liferay.saml.model.SamlIdpSsoSession)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.fetchSamlIdpSsoSession(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.getSamlIdpSsoSession(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.getSamlIdpSsoSessions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.getSamlIdpSsoSessionsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.updateSamlIdpSsoSession((com.liferay.saml.model.SamlIdpSsoSession)arguments[0]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			SamlIdpSsoSessionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.addSamlIdpSsoSession((java.lang.String)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.fetchSamlIdpSso((java.lang.String)arguments[0]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.getSamlIdpSso((java.lang.String)arguments[0]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return SamlIdpSsoSessionLocalServiceUtil.updateModifiedDate((java.lang.String)arguments[0]);
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
}