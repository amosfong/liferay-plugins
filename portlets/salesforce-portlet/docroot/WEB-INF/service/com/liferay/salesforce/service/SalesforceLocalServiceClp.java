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

package com.liferay.salesforce.service;

import com.liferay.portal.service.InvokableLocalService;

/**
 * @author Michael C. Han
 * @generated
 */
public class SalesforceLocalServiceClp implements SalesforceLocalService {
	public SalesforceLocalServiceClp(
		InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

		_methodName0 = "getBeanIdentifier";

		_methodParameterTypes0 = new String[] {  };

		_methodName1 = "setBeanIdentifier";

		_methodParameterTypes1 = new String[] { "java.lang.String" };

		_methodName3 = "executeAdd";

		_methodParameterTypes3 = new String[] { "long", "java.util.List" };

		_methodName4 = "executeAdd";

		_methodParameterTypes4 = new String[] {
				"long", "com.liferay.portal.kernel.messaging.Message"
			};

		_methodName5 = "executeAddOrUpdate";

		_methodParameterTypes5 = new String[] {
				"long", "java.lang.String", "java.util.List"
			};

		_methodName6 = "executeAddOrUpdate";

		_methodParameterTypes6 = new String[] {
				"long", "java.lang.String",
				"com.liferay.portal.kernel.messaging.Message"
			};

		_methodName7 = "executeDelete";

		_methodParameterTypes7 = new String[] { "long", "java.util.List" };

		_methodName8 = "executeDelete";

		_methodParameterTypes8 = new String[] { "long", "java.lang.String" };

		_methodName9 = "executeQuery";

		_methodParameterTypes9 = new String[] { "long", "java.lang.String" };

		_methodName10 = "executeQuery";

		_methodParameterTypes10 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.List"
			};

		_methodName11 = "executeQueryMore";

		_methodParameterTypes11 = new String[] { "long", "java.lang.String" };

		_methodName12 = "executeSearch";

		_methodParameterTypes12 = new String[] { "long", "java.lang.String" };

		_methodName13 = "executeUpdate";

		_methodParameterTypes13 = new String[] { "long", "java.util.List" };

		_methodName14 = "executeUpdate";

		_methodParameterTypes14 = new String[] {
				"long", "com.liferay.portal.kernel.messaging.Message"
			};
	}

	@Override
	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName0,
					_methodParameterTypes0, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		try {
			_invokableLocalService.invokeMethod(_methodName1,
				_methodParameterTypes1,
				new Object[] { ClpSerializer.translateInput(beanIdentifier) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		throw new UnsupportedOperationException();
	}

	@Override
	public void executeAdd(long companyId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		try {
			_invokableLocalService.invokeMethod(_methodName3,
				_methodParameterTypes3,
				new Object[] { companyId, ClpSerializer.translateInput(messages) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof com.liferay.salesforce.service.MultipleSalesforceException) {
				throw (com.liferay.salesforce.service.MultipleSalesforceException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public java.lang.String executeAdd(long companyId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName4,
					_methodParameterTypes4,
					new Object[] {
						companyId,
						
					ClpSerializer.translateInput(message)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof com.liferay.salesforce.service.SalesforceException) {
				throw (com.liferay.salesforce.service.SalesforceException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void executeAddOrUpdate(long companyId, java.lang.String externalId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		try {
			_invokableLocalService.invokeMethod(_methodName5,
				_methodParameterTypes5,
				new Object[] {
					companyId,
					
				ClpSerializer.translateInput(externalId),
					
				ClpSerializer.translateInput(messages)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof com.liferay.salesforce.service.MultipleSalesforceException) {
				throw (com.liferay.salesforce.service.MultipleSalesforceException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void executeAddOrUpdate(long companyId, java.lang.String externalId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		try {
			_invokableLocalService.invokeMethod(_methodName6,
				_methodParameterTypes6,
				new Object[] {
					companyId,
					
				ClpSerializer.translateInput(externalId),
					
				ClpSerializer.translateInput(message)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof com.liferay.salesforce.service.SalesforceException) {
				throw (com.liferay.salesforce.service.SalesforceException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void executeDelete(long companyId,
		java.util.List<java.lang.String> objectIds)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		try {
			_invokableLocalService.invokeMethod(_methodName7,
				_methodParameterTypes7,
				new Object[] { companyId, ClpSerializer.translateInput(
						objectIds) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof com.liferay.salesforce.service.MultipleSalesforceException) {
				throw (com.liferay.salesforce.service.MultipleSalesforceException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public boolean executeDelete(long companyId, java.lang.String objectId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] {
						companyId,
						
					ClpSerializer.translateInput(objectId)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof com.liferay.salesforce.service.SalesforceException) {
				throw (com.liferay.salesforce.service.SalesforceException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch executeQuery(
		long companyId, java.lang.String queryString)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName9,
					_methodParameterTypes9,
					new Object[] {
						companyId,
						
					ClpSerializer.translateInput(queryString)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.messaging.MessageBatch)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.messaging.Message executeQuery(
		long companyId, java.lang.String objectId, java.lang.String objectType,
		java.util.List<java.lang.String> fieldNames)
		throws com.liferay.portal.kernel.dao.orm.ObjectNotFoundException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName10,
					_methodParameterTypes10,
					new Object[] {
						companyId,
						
					ClpSerializer.translateInput(objectId),
						
					ClpSerializer.translateInput(objectType),
						
					ClpSerializer.translateInput(fieldNames)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.dao.orm.ObjectNotFoundException) {
				throw (com.liferay.portal.kernel.dao.orm.ObjectNotFoundException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.messaging.Message)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch executeQueryMore(
		long companyId, java.lang.String queryLocator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName11,
					_methodParameterTypes11,
					new Object[] {
						companyId,
						
					ClpSerializer.translateInput(queryLocator)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.messaging.MessageBatch)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.messaging.MessageBatch executeSearch(
		long companyId, java.lang.String searchString)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName12,
					_methodParameterTypes12,
					new Object[] {
						companyId,
						
					ClpSerializer.translateInput(searchString)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.messaging.MessageBatch)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void executeUpdate(long companyId,
		java.util.List<com.liferay.portal.kernel.messaging.Message> messages)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.MultipleSalesforceException {
		try {
			_invokableLocalService.invokeMethod(_methodName13,
				_methodParameterTypes13,
				new Object[] { companyId, ClpSerializer.translateInput(messages) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof com.liferay.salesforce.service.MultipleSalesforceException) {
				throw (com.liferay.salesforce.service.MultipleSalesforceException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void executeUpdate(long companyId,
		com.liferay.portal.kernel.messaging.Message message)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.salesforce.service.SalesforceException {
		try {
			_invokableLocalService.invokeMethod(_methodName14,
				_methodParameterTypes14,
				new Object[] { companyId, ClpSerializer.translateInput(message) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof com.liferay.salesforce.service.SalesforceException) {
				throw (com.liferay.salesforce.service.SalesforceException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	private InvokableLocalService _invokableLocalService;
	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
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
}