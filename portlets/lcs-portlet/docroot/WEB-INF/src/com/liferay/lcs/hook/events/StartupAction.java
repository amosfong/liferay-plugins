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

package com.liferay.lcs.hook.events;

import com.liferay.lcs.util.HandshakeManagerUtil;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.NoSuchPortletPreferencesException;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.lang.reflect.Method;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class StartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		try {
			readPortletModelHints();

			if (!HandshakeManagerUtil.isPending()) {
				LCSUtil.setupCredentials();

				HandshakeManagerUtil.start();
			}
		}
		catch (Exception e) {
			if (!(e instanceof NoSuchPortletPreferencesException)) {
				throw new ActionException(e);
			}
		}
	}

	protected void readPortletModelHints() throws Exception {
		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> modelHintsUtilClass = Class.forName(
			"com.liferay.portal.model.ModelHintsUtil", false,
			portalClassLoader);

		Method readMethod = modelHintsUtilClass.getMethod(
			"read", ClassLoader.class, String.class);

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		readMethod.invoke(
			null, contextClassLoader, "META-INF/portlet-model-hints.xml");
		readMethod.invoke(
			null, contextClassLoader, "META-INF/portlet-model-hints-ext.xml");
	}

}