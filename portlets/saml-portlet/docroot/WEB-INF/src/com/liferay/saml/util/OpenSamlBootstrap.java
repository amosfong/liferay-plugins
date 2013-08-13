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

package com.liferay.saml.util;

import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;

import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.ConfigurationException;

/**
 * @author Mika Koivisto
 */
public class OpenSamlBootstrap extends DefaultBootstrap {

	public static synchronized void bootstrap() throws ConfigurationException {
		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(
				PortletClassLoaderUtil.getClassLoader());

			initializeXMLSecurity();

			initializeXMLTooling(_xmlToolingConfigs);

			initializeArtifactBuilderFactories();

			initializeGlobalSecurityConfiguration();

			initializeParserPool();

			initializeESAPI();
		}
		finally {
			currentThread.setContextClassLoader(classLoader);
		}
	}

	private static String[] _xmlToolingConfigs = {
		"/default-config.xml", "/encryption-config.xml",
		"/encryption-validation-config.xml", "/saml1-metadata-config.xml",
		"/saml2-assertion-config.xml",
		"/saml2-assertion-delegation-restriction-config.xml",
		"/saml2-core-validation-config.xml", "/saml2-metadata-config.xml",
		"/saml2-metadata-idp-discovery-config.xml",
		"/saml2-metadata-query-config.xml",
		"/saml2-metadata-validation-config.xml", "/saml2-protocol-config.xml",
		"/saml2-protocol-thirdparty-config.xml", "/schema-config.xml",
		"/signature-config.xml", "/signature-validation-config.xml",
		"/soap11-config.xml"
	};

}