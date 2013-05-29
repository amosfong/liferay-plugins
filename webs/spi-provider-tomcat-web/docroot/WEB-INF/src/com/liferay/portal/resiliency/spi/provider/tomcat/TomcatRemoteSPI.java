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

package com.liferay.portal.resiliency.spi.provider.tomcat;

import com.liferay.portal.kernel.resiliency.spi.SPIConfiguration;
import com.liferay.portal.kernel.resiliency.spi.remote.RemoteSPI;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

/**
 * @author Shuyang Zhou
 */
public class TomcatRemoteSPI extends RemoteSPI {

	public TomcatRemoteSPI(SPIConfiguration spiConfiguration) {
		super(spiConfiguration);
	}

	@Override
	public void addServlet(
			String contextPath, String docBasePath, String mappingPattern,
			String servletClassName)
		throws RemoteException {

		try {
			Host host = _tomcat.getHost();

			Context context = (Context)host.findChild(contextPath);

			if (context == null) {
				context = _tomcat.addContext(contextPath, docBasePath);
			}

			context.setCrossContext(true);

			Thread currentThread = Thread.currentThread();

			ClassLoader classLoader = currentThread.getContextClassLoader();

			Class<Servlet> servletClass = (Class<Servlet>)classLoader.loadClass(
				servletClassName);

			Servlet servlet = servletClass.newInstance();

			Tomcat.addServlet(context, servletClassName, servlet);

			context.addServletMapping(mappingPattern, servletClassName);
		}
		catch (Exception e) {
			throw new RemoteException("Unable to add servlet", e);
		}
	}

	@Override
	public void addWebapp(String contextPath, String docBasePath)
		throws RemoteException {

		try {
			Context context = _tomcat.addWebapp(contextPath, docBasePath);

			context.setCrossContext(true);
		}
		catch (ServletException se) {
			throw new RemoteException("Unable to add web application", se);
		}
	}

	@Override
	public void destroy() throws RemoteException {
		try {
			_tomcat.destroy();
		}
		catch (LifecycleException le) {
			throw new RemoteException("Failed to destroy", le);
		}
		finally {
			UnicastRemoteObject.unexportObject(this, true);
		}
	}

	@Override
	public String getSPIProviderName() {
		return TomcatSPIProvider.NAME;
	}

	@Override
	public void init() throws RemoteException {
		try {
			_tomcat.init();
		}
		catch (LifecycleException le) {
			throw new RemoteException("Unable to init", le);
		}
	}

	@Override
	public void start() throws RemoteException {
		try {
			_tomcat.start();
		}
		catch (LifecycleException le) {
			throw new RemoteException("Unable to start", le);
		}
	}

	@Override
	public void stop() throws RemoteException {
		try {
			_tomcat.stop();
		}
		catch (LifecycleException le) {
			throw new RemoteException("Unable to stop", le);
		}
	}

	@Override
	public String toString() {
		return spiConfiguration.getSPIId();
	}

	private void readObject(ObjectInputStream objectInputStream)
		throws ClassNotFoundException, IOException {

		objectInputStream.defaultReadObject();

		_tomcat = new Tomcat();

		_tomcat.setBaseDir(spiConfiguration.getBaseDir());
		_tomcat.setPort(spiConfiguration.getConnectorPort());

		Connector connector = _tomcat.getConnector();

		connector.setProperty("keepAliveTimeout", "-1");
		connector.setProperty("maxKeepAliveRequests", "-1");
	}

	private static final long serialVersionUID = 1L;

	private transient Tomcat _tomcat;

}