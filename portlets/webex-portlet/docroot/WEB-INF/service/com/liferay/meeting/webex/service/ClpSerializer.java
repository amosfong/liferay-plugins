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

package com.liferay.meeting.webex.service;

import com.liferay.meeting.webex.model.WebExAccountClp;
import com.liferay.meeting.webex.model.WebExSiteClp;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BaseModel;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anant Singh
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
						"webex-portlet-deployment-context");

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
							"webex-portlet-deployment-context");

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
				_servletContextName = "webex-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(WebExAccountClp.class.getName())) {
			return translateInputWebExAccount(oldModel);
		}

		if (oldModelClassName.equals(WebExSiteClp.class.getName())) {
			return translateInputWebExSite(oldModel);
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

	public static Object translateInputWebExAccount(BaseModel<?> oldModel) {
		WebExAccountClp oldClpModel = (WebExAccountClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWebExAccountRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWebExSite(BaseModel<?> oldModel) {
		WebExSiteClp oldClpModel = (WebExSiteClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWebExSiteRemoteModel();

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
					"com.liferay.meeting.webex.model.impl.WebExAccountImpl")) {
			return translateOutputWebExAccount(oldModel);
		}

		if (oldModelClassName.equals(
					"com.liferay.meeting.webex.model.impl.WebExSiteImpl")) {
			return translateOutputWebExSite(oldModel);
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

		if (className.equals(
					"com.liferay.meeting.webex.WebExAccountLoginException")) {
			return new com.liferay.meeting.webex.WebExAccountLoginException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.meeting.webex.WebExAccountPasswordException")) {
			return new com.liferay.meeting.webex.WebExAccountPasswordException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.meeting.webex.WebExSiteAPIURLException")) {
			return new com.liferay.meeting.webex.WebExSiteAPIURLException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.meeting.webex.WebExSiteKeyException")) {
			return new com.liferay.meeting.webex.WebExSiteKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.meeting.webex.WebExSiteLoginException")) {
			return new com.liferay.meeting.webex.WebExSiteLoginException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.meeting.webex.WebExSiteNameException")) {
			return new com.liferay.meeting.webex.WebExSiteNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.meeting.webex.WebExSitePasswordException")) {
			return new com.liferay.meeting.webex.WebExSitePasswordException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.meeting.webex.NoSuchAccountException")) {
			return new com.liferay.meeting.webex.NoSuchAccountException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.meeting.webex.NoSuchSiteException")) {
			return new com.liferay.meeting.webex.NoSuchSiteException(throwable.getMessage(),
				throwable.getCause());
		}

		return throwable;
	}

	public static Object translateOutputWebExAccount(BaseModel<?> oldModel) {
		WebExAccountClp newModel = new WebExAccountClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWebExAccountRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWebExSite(BaseModel<?> oldModel) {
		WebExSiteClp newModel = new WebExSiteClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWebExSiteRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}