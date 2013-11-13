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

package com.liferay.lcs.util;

import com.liferay.portal.kernel.util.ClassLoaderPool;
import com.liferay.portal.kernel.util.MethodHandler;

/**
 * @author Ivica Cardic
 */
public class ClusterLinkHelper {

	@SuppressWarnings("unused")
	private static Object _invoke(
			MethodHandler methodHandler, String servletContextName)
		throws Exception {

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			ClassLoader classLoader = ClassLoaderPool.getClassLoader(
				servletContextName);

			currentThread.setContextClassLoader(classLoader);

			return methodHandler.invoke(true);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

}