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

package com.liferay.saml.resolver;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.saml.util.PortletPropsKeys;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mika Koivisto
 */
public class NameIdResolverFactory {

	public static NameIdResolver getNameIdResolver(String entityId) {
		long companyId = CompanyThreadLocal.getCompanyId();

		NameIdResolver nameIdResolver = _nameIdResolvers.get(
			companyId + "," + entityId);

		if (nameIdResolver != null) {
			return nameIdResolver;
		}

		Thread currentThread = Thread.currentThread();

		ClassLoader classLoader = currentThread.getContextClassLoader();

		try {
			String samlIdpMetadataNameIdResolver = PropsUtil.get(
				PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_RESOLVER,
				new Filter(entityId));

			if (Validator.isNull(samlIdpMetadataNameIdResolver)) {
				samlIdpMetadataNameIdResolver = PropsUtil.get(
					PortletPropsKeys.SAML_IDP_METADATA_NAME_ID_RESOLVER);
			}

			try {
				if (Validator.isNotNull(samlIdpMetadataNameIdResolver)) {
					nameIdResolver =
						(NameIdResolver)InstanceFactory.newInstance(
							classLoader, samlIdpMetadataNameIdResolver);
				}
			}
			catch (ClassNotFoundException cnfe) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to load name ID resolver class " +
										samlIdpMetadataNameIdResolver);
				}
			}

			if (nameIdResolver == null) {
				nameIdResolver = _nameIdResolver;
			}

			_nameIdResolvers.put(companyId + "," + entityId, nameIdResolver);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return nameIdResolver;
	}

	private static Log _log = LogFactoryUtil.getLog(
		NameIdResolverFactory.class);

	private static NameIdResolver _nameIdResolver = new DefaultNameIdResolver();
	private static Map<String, NameIdResolver> _nameIdResolvers =
		new ConcurrentHashMap<String, NameIdResolver>();

}