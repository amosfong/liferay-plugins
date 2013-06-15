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

package com.liferay.wsrp.service.base;

import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WSRPConsumerPortletLocalServiceClpInvoker {
	public WSRPConsumerPortletLocalServiceClpInvoker() {
		_methodName0 = "addWSRPConsumerPortlet";

		_methodParameterTypes0 = new String[] {
				"com.liferay.wsrp.model.WSRPConsumerPortlet"
			};

		_methodName1 = "createWSRPConsumerPortlet";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteWSRPConsumerPortlet";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteWSRPConsumerPortlet";

		_methodParameterTypes3 = new String[] {
				"com.liferay.wsrp.model.WSRPConsumerPortlet"
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

		_methodName10 = "fetchWSRPConsumerPortlet";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getWSRPConsumerPortlet";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getWSRPConsumerPortlets";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getWSRPConsumerPortletsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateWSRPConsumerPortlet";

		_methodParameterTypes15 = new String[] {
				"com.liferay.wsrp.model.WSRPConsumerPortlet"
			};

		_methodName40 = "getBeanIdentifier";

		_methodParameterTypes40 = new String[] {  };

		_methodName41 = "setBeanIdentifier";

		_methodParameterTypes41 = new String[] { "java.lang.String" };

		_methodName46 = "addWSRPConsumerPortlet";

		_methodParameterTypes46 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName47 = "addWSRPConsumerPortlet";

		_methodParameterTypes47 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName48 = "deleteWSRPConsumerPortlet";

		_methodParameterTypes48 = new String[] { "long" };

		_methodName49 = "deleteWSRPConsumerPortlet";

		_methodParameterTypes49 = new String[] { "java.lang.String" };

		_methodName50 = "deleteWSRPConsumerPortlet";

		_methodParameterTypes50 = new String[] {
				"com.liferay.wsrp.model.WSRPConsumerPortlet"
			};

		_methodName51 = "deleteWSRPConsumerPortlets";

		_methodParameterTypes51 = new String[] { "long" };

		_methodName52 = "destroyWSRPConsumerPortlet";

		_methodParameterTypes52 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName53 = "destroyWSRPConsumerPortlets";

		_methodParameterTypes53 = new String[] {  };

		_methodName54 = "getWSRPConsumerPortlet";

		_methodParameterTypes54 = new String[] { "long", "java.lang.String" };

		_methodName55 = "getWSRPConsumerPortlet";

		_methodParameterTypes55 = new String[] { "java.lang.String" };

		_methodName56 = "getWSRPConsumerPortlets";

		_methodParameterTypes56 = new String[] { "long", "int", "int" };

		_methodName57 = "getWSRPConsumerPortletsCount";

		_methodParameterTypes57 = new String[] { "long" };

		_methodName58 = "initFailedWSRPConsumerPortlets";

		_methodParameterTypes58 = new String[] {  };

		_methodName59 = "initWSRPConsumerPortlet";

		_methodParameterTypes59 = new String[] {
				"long", "long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName60 = "initWSRPConsumerPortlets";

		_methodParameterTypes60 = new String[] {  };

		_methodName61 = "updateWSRPConsumerPortlet";

		_methodParameterTypes61 = new String[] { "long", "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet((com.liferay.wsrp.model.WSRPConsumerPortlet)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.createWSRPConsumerPortlet(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.deleteWSRPConsumerPortlet(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.deleteWSRPConsumerPortlet((com.liferay.wsrp.model.WSRPConsumerPortlet)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.fetchWSRPConsumerPortlet(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortletsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.updateWSRPConsumerPortlet((com.liferay.wsrp.model.WSRPConsumerPortlet)arguments[0]);
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			WSRPConsumerPortletLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.addWSRPConsumerPortlet((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.deleteWSRPConsumerPortlet(((Long)arguments[0]).longValue());
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			WSRPConsumerPortletLocalServiceUtil.deleteWSRPConsumerPortlet((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.deleteWSRPConsumerPortlet((com.liferay.wsrp.model.WSRPConsumerPortlet)arguments[0]);
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			WSRPConsumerPortletLocalServiceUtil.deleteWSRPConsumerPortlets(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlet(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			WSRPConsumerPortletLocalServiceUtil.destroyWSRPConsumerPortlets();

			return null;
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlet((java.lang.String)arguments[0]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortletsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			WSRPConsumerPortletLocalServiceUtil.initFailedWSRPConsumerPortlets();

			return null;
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			WSRPConsumerPortletLocalServiceUtil.initWSRPConsumerPortlet(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5]);

			return null;
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			WSRPConsumerPortletLocalServiceUtil.initWSRPConsumerPortlets();

			return null;
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return WSRPConsumerPortletLocalServiceUtil.updateWSRPConsumerPortlet(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
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
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
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
	private String _methodName61;
	private String[] _methodParameterTypes61;
}