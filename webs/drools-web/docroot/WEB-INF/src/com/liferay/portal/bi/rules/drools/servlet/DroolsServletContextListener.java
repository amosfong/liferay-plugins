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

package com.liferay.portal.bi.rules.drools.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.mvel2.MVELRuntime;

/**
 * @author Michael C. Han
 */
public class DroolsServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		MVELRuntime.resetDebugger();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
	}

}