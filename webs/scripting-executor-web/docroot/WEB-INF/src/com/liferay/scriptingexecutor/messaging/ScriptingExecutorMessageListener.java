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

package com.liferay.scriptingexecutor.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.scriptingexecutor.util.ClassLoaderUtil;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * @author Michael C. Han
 */
public class ScriptingExecutorMessageListener extends BaseMessageListener {

	protected void deploy(ServletContext servletContext) throws Exception {
		URL url = servletContext.getResource(_SCRIPTS_DIR);

		if (url == null) {
			return;
		}

		Set<String> supportedLanguages = ScriptingUtil.getSupportedLanguages();

		Properties pluginPackageProperties = getPluginPackageProperties(
			servletContext);

		String language = getLanguage(pluginPackageProperties);

		if (!supportedLanguages.contains(language)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unsupported language " + language);
			}

			return;
		}

		String requiredDeploymentContexts = pluginPackageProperties.getProperty(
			"required-deployment-contexts");

		if (Validator.isNull(requiredDeploymentContexts)) {
			return;
		}

		ClassLoader classLoader =
			ClassLoaderUtil.getAggregatePluginsClassLoader(
				StringUtil.split(requiredDeploymentContexts), false);

		executeScripts(servletContext, language, classLoader);
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (!command.equals("deploy") && !command.equals("undeploy")) {
			return;
		}

		String servletContextName = message.getString("servletContextName");

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		if (command.equals("deploy")) {
			deploy(servletContext);
		}
		else if (command.equals("undeploy")) {
			undeploy(servletContext);
		}
	}

	protected void executeScripts(
		ServletContext servletContext, String language,
		ClassLoader classLoader) {

		InputStream inputStream = null;

		Set<String> resourcePaths = servletContext.getResourcePaths(
			_SCRIPTS_DIR);

		for (String resourcePath : resourcePaths) {
			try {
				inputStream = servletContext.getResourceAsStream(resourcePath);

				ScriptingUtil.exec(
					null, new HashMap<String, Object>(), language,
					StringUtil.read(inputStream), classLoader);
			}
			catch (Exception e) {
				_log.error("Unable to execute script " + resourcePath, e);
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}
	}

	protected String getLanguage(Properties pluginPackageProperties) {
		return pluginPackageProperties.getProperty(
			"scripting-executor-language");
	}

	protected Properties getPluginPackageProperties(
		ServletContext servletContext) {

		Properties properties = new Properties();

		try {
			String propertiesString = StringUtil.read(
				servletContext.getResourceAsStream(
					"/WEB-INF/liferay-plugin-package.properties"));

			if (propertiesString == null) {
				return properties;
			}

			String contextPath = servletContext.getRealPath(StringPool.SLASH);

			contextPath = StringUtil.replace(
				contextPath, StringPool.BACK_SLASH, StringPool.SLASH);

			propertiesString = propertiesString.replace(
				"${context.path}", contextPath);

			PropertiesUtil.load(properties, propertiesString);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		return properties;
	}

	protected void undeploy(ServletContext servletContext) throws Exception {
		if (servletContext != null) {
			Properties pluginPackageProperties = getPluginPackageProperties(
				servletContext);

			String language = getLanguage(pluginPackageProperties);

			ScriptingUtil.clearCache(language);
		}
		else {
			Set<String> supportedLanguages =
				ScriptingUtil.getSupportedLanguages();

			for (String supportedLanguage : supportedLanguages) {
				ScriptingUtil.clearCache(supportedLanguage);
			}
		}
	}

	private static final String _SCRIPTS_DIR = "/WEB-INF/classes/scripts/";

	private static Log _log = LogFactoryUtil.getLog(
		ScriptingExecutorMessageListener.class);

}