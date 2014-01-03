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

package com.liferay.reports.service;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import com.liferay.reports.model.DefinitionClp;
import com.liferay.reports.model.EntryClp;
import com.liferay.reports.model.SourceClp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"reports-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"reports-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "reports-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(DefinitionClp.class.getName())) {
			return translateInputDefinition(oldModel);
		}

		if (oldModelClassName.equals(EntryClp.class.getName())) {
			return translateInputEntry(oldModel);
		}

		if (oldModelClassName.equals(SourceClp.class.getName())) {
			return translateInputSource(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputDefinition(BaseModel<?> oldModel) {
		DefinitionClp oldClpModel = (DefinitionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getDefinitionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputEntry(BaseModel<?> oldModel) {
		EntryClp oldClpModel = (EntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSource(BaseModel<?> oldModel) {
		SourceClp oldClpModel = (SourceClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSourceRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.reports.model.impl.DefinitionImpl")) {
			return translateOutputDefinition(oldModel);
		}

		if (oldModelClassName.equals("com.liferay.reports.model.impl.EntryImpl")) {
			return translateOutputEntry(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.reports.model.impl.SourceImpl")) {
			return translateOutputSource(oldModel);
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Throwable translateThrowable(Throwable throwable) {
		if (_useReflectionToTranslateThrowable) {
			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();
				objectOutputStream.close();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				ObjectInputStream objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

				objectInputStream.close();

				return throwable;
			}
			catch (ClassNotFoundException cnfe) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (SecurityException se) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (Throwable throwable2) {
				_log.error(throwable2, throwable2);

				return throwable2;
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals("com.liferay.reports.DefinitionFileException")) {
			return new com.liferay.reports.DefinitionFileException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.DefinitionNameException")) {
			return new com.liferay.reports.DefinitionNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.EntryEmailDeliveryException")) {
			return new com.liferay.reports.EntryEmailDeliveryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.reports.EntryEmailNotificationsException")) {
			return new com.liferay.reports.EntryEmailNotificationsException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.SourceCharsetException")) {
			return new com.liferay.reports.SourceCharsetException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.SourceColumnNamesException")) {
			return new com.liferay.reports.SourceColumnNamesException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.reports.SourceDriverClassNameException")) {
			return new com.liferay.reports.SourceDriverClassNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.SourceFileException")) {
			return new com.liferay.reports.SourceFileException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.reports.SourceJDBCConnectionException")) {
			return new com.liferay.reports.SourceJDBCConnectionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.SourceLoginException")) {
			return new com.liferay.reports.SourceLoginException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.SourceNameException")) {
			return new com.liferay.reports.SourceNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.SourceTypeException")) {
			return new com.liferay.reports.SourceTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.SourceURLException")) {
			return new com.liferay.reports.SourceURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.NoSuchDefinitionException")) {
			return new com.liferay.reports.NoSuchDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.NoSuchEntryException")) {
			return new com.liferay.reports.NoSuchEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.reports.NoSuchSourceException")) {
			return new com.liferay.reports.NoSuchSourceException(throwable.getMessage(),
				throwable.getCause());
		}

		return throwable;
	}

	public static Object translateOutputDefinition(BaseModel<?> oldModel) {
		DefinitionClp newModel = new DefinitionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setDefinitionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputEntry(BaseModel<?> oldModel) {
		EntryClp newModel = new EntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSource(BaseModel<?> oldModel) {
		SourceClp newModel = new SourceClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSourceRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}